package com.hexing.forecast.component;

import com.alibaba.fastjson.JSONObject;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Nesterovs;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * BP神经网络算法
 */
@Component
public class BPNetworkComponent {
    @Autowired
    ModelEvaluationComponent modelEvaluationComponent;

    private static final Logger log = LoggerFactory.getLogger(BPNetworkComponent.class);

    /**
     * 训练模型
     * @param input 输入数据
     * @param output  输出数据
     * @param inputDimension  输入层维度
     * @param outputDimension  输出层维度
     * @param denseLayerDimension  隐含层维度
     * @param weightInit  权重初始化函数
     * @param fileName  生成模型的文件名
     * @return
     * @throws Exception
     */
    public boolean trainModel(List<Object> input, List<Object> output, int inputDimension, int outputDimension,
                              int denseLayerDimension, WeightInit weightInit, String fileName) throws Exception {
        //删除原有模型
        deleteOriginalModel(fileName);

        if (input.size() == 0 || output.size() == 0)
            return false;

        int seed = 123;        // number used to initialize a pseudorandom number generator.该参数将一组随机生成的权重确定为初始权重
        int nEpochs = 10000;    // number of training epochs 训练次数
        int trainDataNum = input.size();
        log.info("Data preparation...");

        INDArray inputArray = Nd4j.zeros(trainDataNum, inputDimension);
        INDArray outputArray = Nd4j.zeros(trainDataNum, outputDimension);
        inputArray = fillInput(inputArray, input);
        outputArray = fillInput(outputArray, output);
        DataSet ds = new DataSet(inputArray, outputArray);

        log.info("Network configuration and training...");

        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .updater(new Nesterovs(0.01, 0.9))
                .seed(seed)
                .weightInit(weightInit)
//                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
//                .updater(Updater.SGD)
                .list()
                .layer(new DenseLayer.Builder().nIn(inputDimension).nOut(denseLayerDimension)
                        .activation(Activation.TANH).build())
                .layer(new OutputLayer.Builder(LossFunctions.LossFunction.MSE)
                        .activation(Activation.IDENTITY)
                        .nIn(denseLayerDimension).nOut(outputDimension).build())
                .build();

        MultiLayerNetwork net = new MultiLayerNetwork(conf);
        net.init();

        // add an listener which outputs the error every 100 parameter updates 监听器，值等于Iterator的个数
        net.setListeners(new ScoreIterationListener(1));

        // C&P from LSTMCharModellingExample
        // Print the number of parameters in the network (and for each layer)
        System.out.println(net.summary());

        // here the actual learning takes place  训练过程
        for( int i = 0; i < nEpochs; i++ ) {
            net.fit(ds);
        }

        //保存模型
        String filePath = fileName + ".zip";
        File locationToSave = new File(filePath);//保存路径，存储位置
        boolean saveUpdater = false;
        ModelSerializer.writeModel(net, locationToSave, saveUpdater);
        return true;
    }

    /**
     * 模型测试
     * @param input 输入数据
     * @param output  输出数据
     * @param inputDimension  输入数据的维度 = 模型输入层的维度
     * @param outputDimension  输出数据的维度 = 模型输出层的维度
     * @param fileName  生成模型的文件名
     * @throws Exception
     */
    public void evalModel(List<Object> input, List<Object> output, int inputDimension, int outputDimension,
                          String fileName) throws Exception {
        //创建测试数据
        if (input.size() == 0 || output.size() == 0)
            return;
        int evalDataNum = input.size();
        INDArray inputArray = Nd4j.zeros(evalDataNum, inputDimension);
        INDArray outputArray = Nd4j.zeros(evalDataNum, outputDimension);

        inputArray = fillInput(inputArray, input);
        outputArray = fillInput(outputArray, output);

        //将输入带入训练得到的模型，得到计算输出
        INDArray result = output(inputArray, fileName);
        if (result == null)
            return;

        //使用平均绝对误差和均方根误差评估模型
        double MAE = 0, RMSE = 0;

        for (int j = 0; j < outputDimension; j++) {
            double[] outputReal = new double[evalDataNum];
            double[] outputCal = new double[evalDataNum];
            for (int i = 0; i < evalDataNum; i++) {
                outputReal[i] = Double.parseDouble(outputArray.getScalar(i, j).toString());
                outputCal[i] = Double.parseDouble(result.getScalar(i, j).toString());
            }
            MAE += modelEvaluationComponent.MAE(outputReal, outputCal);
            RMSE += modelEvaluationComponent.RMSE(outputReal, outputCal);
        }
        RMSE /= outputDimension;
    }

    /**
     * 预测结果
     * @param input
     * @param inputDimension
     * @param outputDimension
     * @param fileName
     * @return
     * @throws Exception
     */
    public List<Double> forecast(List<Object> input, int inputDimension, int outputDimension, String fileName) throws Exception {
        INDArray forecastingInput = Nd4j.zeros(input.size(), inputDimension);
        forecastingInput = fillInput(forecastingInput, input);
        INDArray output = output(forecastingInput, fileName);
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < outputDimension; i++) {
            Object o = output.getScalar(0, i);
            result.add(Double.parseDouble(o.toString()));
        }
        return result;
    }

    /**
     * 创建模型的输出
     * @param input
     * @param fileName
     * @return
     * @throws Exception
     */
    private INDArray output(INDArray input, String fileName) throws Exception {
        String file = fileName + ".zip";
        File locationToSave = new File(file);
        boolean saveUpdater = false;
        MultiLayerNetwork multiLayerNetwork = MultiLayerNetwork.load(locationToSave, saveUpdater);
        INDArray result = multiLayerNetwork.output(input);
        return result;
    }

    /**
     * 将输入输出转换为INDArray形式
     * @param inputArray
     * @param input
     * @return
     */
    private INDArray fillInput(INDArray inputArray, List<Object> input) {
        if (input.size() == 0)
            return null;
        int DataNum = input.size();

        for (int i = 0; i < DataNum; i++) {
            JSONObject jsonObject = JSONObject.parseObject(input.get(i).toString());
            int j = 0;
            for (String key : jsonObject.keySet()) {
                inputArray.putScalar(new int[]{i, j},((double) jsonObject.get(key)));
                j++;
            }
        }
        return inputArray;
    }

    /**
     * 生成新模型时删除原有模型
     * @param fileName
     */
    private void deleteOriginalModel(String fileName) {
        String filePath = fileName + ".zip";
        File file = new File(filePath);
        if (file.exists() && file.isFile())
            file.delete();
    }
}
