package com.hexing.forecast.services.powerForecast;

import com.hexing.forecast.bean.PowerForecastInput;
import com.hexing.forecast.bean.PowerForecastOutput;
import com.hexing.forecast.component.NormalizationComponent;
import com.hexing.forecast.entity.*;
import com.hexing.forecast.repository.*;
import com.hexing.forecast.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 创建发电预测的输入输出
 */
@Service
public class CreatePowerDataService {
    @Autowired
    PowerRepository powerRepository;
    @Autowired
    WeatherDayRepository weatherDayRepository;
    @Autowired
    SubstationRepository substationRepository;
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    PanelTemperatureRepository panelTemperatureRepository;
    @Autowired
    NormalizationRepository normalizationRepository;
    @Autowired
    NormalizationComponent normalizationComponent;

    public List<PowerForecastInput> inputList;
    public List<PowerForecastOutput> outputList;

    /**
     * 创建发电预测的输入
     * @param fromDate
     * @param toDate
     * @param substationId
     * @param isForecast 判断是否是预测（全部过程包括训练、评价、预测）
     * @return
     * @throws Exception
     */
    public boolean createInputAndOutput(Date fromDate, Date toDate, String substationId, boolean isForecast) throws Exception {
        if (substationId == null || fromDate.compareTo(toDate) > 0 || (fromDate.compareTo(toDate) == 0 && !isForecast))
            return false;
        Optional<SubstationEntity> optionalSubstationEntity = substationRepository.findById(substationId.getBytes());
        if (!optionalSubstationEntity.isPresent())
            return false;
        SubstationEntity substationEntity = optionalSubstationEntity.get();
        RegionEntity regionEntity = regionRepository.findById(substationEntity.getRegionId()).get();

        inputList = new ArrayList<>();
        if (isForecast)
            outputList = null;
        else
            outputList = new ArrayList<>();

        DateUtils dateUtils = new DateUtils();
        List<Date> dates = dateUtils.getDates(fromDate, toDate);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        //获取所有的发电数据
        String startTime = simpleDateFormat.format(dateUtils.getDaysAfter(dates.get(0), -1));
        String endTime = simpleDateFormat.format(dates.get(dates.size() - 1));
        List<PowerEntity> powerEntities = powerRepository.findByDatesAndSubstationId(startTime, endTime, substationId.getBytes());
        if (powerEntities == null || powerEntities.size() == 0)
            return false;
        List<WeatherDayEntity> weatherDayEntities = weatherDayRepository.findByDatesAndRegionId(startTime, endTime, regionEntity.getId());
        if (weatherDayEntities == null || weatherDayEntities.size() == 0)
            return false;
        List<PanelTemperatureEntity> panelTemperatureEntities = panelTemperatureRepository.findByDateAndSubstationId(startTime, endTime, substationId.getBytes());

        //需要保存最高、最低温度，最大、最小发电量，最大、最小湿度，最大、最小电池板温度
        double maxPower = 0, minPower = 0, maxMaxTemperature = 0, minMaxTemperature = 0, maxMinTemperature = 0, minMinTemperature = 0, maxHumidity = 0, minHumidity = 0,
                maxPanelTemperature = 0, minPanelTemperature = 0;
        //创建输入输出层数据
        for (Date date: dates) {
            PowerForecastInput input = new PowerForecastInput();
            PowerForecastOutput output = new PowerForecastOutput();
            Date oneDayBefore = dateUtils.getDaysAfter(date, -1); //获取前一天
            for (PowerEntity powerEntity: powerEntities) {
                Date powerDate = new Date(powerEntity.getDate().getTime());
                if (dateUtils.isSameDay(powerDate, oneDayBefore)) {
                    if (dateUtils.getHour(powerDate) == 8)
                        input.setPower8Yes(powerEntity.getPower());
                    else if (dateUtils.getHour(powerDate) == 9)
                        input.setPower9Yes(powerEntity.getPower());
                    else if (dateUtils.getHour(powerDate) == 10)
                        input.setPower10Yes(powerEntity.getPower());
                    else if (dateUtils.getHour(powerDate) == 11)
                        input.setPower11Yes(powerEntity.getPower());
                    else if (dateUtils.getHour(powerDate) == 12)
                        input.setPower12Yes(powerEntity.getPower());
                    else if (dateUtils.getHour(powerDate) == 13)
                        input.setPower13Yes(powerEntity.getPower());
                    else if (dateUtils.getHour(powerDate) == 14)
                        input.setPower14Yes(powerEntity.getPower());
                    else if (dateUtils.getHour(powerDate) == 15)
                        input.setPower15Yes(powerEntity.getPower());
                    else if (dateUtils.getHour(powerDate) == 16)
                        input.setPower16Yes(powerEntity.getPower());
                    else if (dateUtils.getHour(powerDate) == 17)
                        input.setPower17Yes(powerEntity.getPower());
                    else if (dateUtils.getHour(powerDate) == 18)
                        input.setPower18Yes(powerEntity.getPower());

                    if (maxPower < powerEntity.getPower())
                        maxPower = powerEntity.getPower();
                    if (minPower > powerEntity.getPower())
                        minPower = powerEntity.getPower();
                }
                if (dateUtils.isSameDay(powerDate, date) && !isForecast) {
                    if (dateUtils.getHour(powerDate) == 8)
                        output.setPower8Yes(powerEntity.getPower());
                    else if (dateUtils.getHour(powerDate) == 9)
                        output.setPower9Yes(powerEntity.getPower());
                    else if (dateUtils.getHour(powerDate) == 10)
                        output.setPower10Yes(powerEntity.getPower());
                    else if (dateUtils.getHour(powerDate) == 11)
                        output.setPower11Yes(powerEntity.getPower());
                    else if (dateUtils.getHour(powerDate) == 12)
                        output.setPower12Yes(powerEntity.getPower());
                    else if (dateUtils.getHour(powerDate) == 13)
                        output.setPower13Yes(powerEntity.getPower());
                    else if (dateUtils.getHour(powerDate) == 14)
                        output.setPower14Yes(powerEntity.getPower());
                    else if (dateUtils.getHour(powerDate) == 15)
                        output.setPower15Yes(powerEntity.getPower());
                    else if (dateUtils.getHour(powerDate) == 16)
                        output.setPower16Yes(powerEntity.getPower());
                    else if (dateUtils.getHour(powerDate) == 17)
                        output.setPower17Yes(powerEntity.getPower());
                    else if (dateUtils.getHour(powerDate) == 18)
                        output.setPower18Yes(powerEntity.getPower());

                    if (maxPower < powerEntity.getPower())
                        maxPower = powerEntity.getPower();
                    if (minPower > powerEntity.getPower())
                        minPower = powerEntity.getPower();
                }
            }
            for (WeatherDayEntity weahterDayEntity: weatherDayEntities) {
                Date weatherDate = new Date(weahterDayEntity.getDate().getTime());
                if (dateUtils.isSameDay(weatherDate, oneDayBefore)) {
                    input.setMaxTemperatureYes(weahterDayEntity.getMaxTemperature());
                    input.setMinTemperatureYes(weahterDayEntity.getMinTemperature());
                    input.setHumidityYes(weahterDayEntity.getAvgHumidity());
                    input.setWeatherConditionYes(weahterDayEntity.getWeatherCondition());

                    if (maxMaxTemperature < weahterDayEntity.getMaxTemperature())
                        maxMaxTemperature = weahterDayEntity.getMaxTemperature();
                    if (minMaxTemperature > weahterDayEntity.getMaxTemperature())
                        minMaxTemperature = weahterDayEntity.getMaxTemperature();
                    if (maxMinTemperature < weahterDayEntity.getMinTemperature())
                        maxMinTemperature = weahterDayEntity.getMinTemperature();
                    if (minMinTemperature > weahterDayEntity.getMinTemperature())
                        minMinTemperature = weahterDayEntity.getMinTemperature();
                    if (maxHumidity < weahterDayEntity.getAvgHumidity())
                        maxHumidity = weahterDayEntity.getAvgHumidity();
                    if (minHumidity > weahterDayEntity.getAvgHumidity())
                        minHumidity = weahterDayEntity.getAvgHumidity();
                }
                if (dateUtils.isSameDay(weatherDate, date) && !isForecast) {
                    input.setMaxTemperature(weahterDayEntity.getMaxTemperature());
                    input.setMinTemperature(weahterDayEntity.getMinTemperature());
                    input.setHumidity(weahterDayEntity.getAvgHumidity());
                    input.setWeatherCondition(weahterDayEntity.getWeatherCondition());

                    if (maxMaxTemperature < weahterDayEntity.getMaxTemperature())
                        maxMaxTemperature = weahterDayEntity.getMaxTemperature();
                    if (minMaxTemperature > weahterDayEntity.getMaxTemperature())
                        minMaxTemperature = weahterDayEntity.getMaxTemperature();
                    if (maxMinTemperature < weahterDayEntity.getMinTemperature())
                        maxMinTemperature = weahterDayEntity.getMinTemperature();
                    if (minMinTemperature > weahterDayEntity.getMinTemperature())
                        minMinTemperature = weahterDayEntity.getMinTemperature();
                    if (maxHumidity < weahterDayEntity.getAvgHumidity())
                        maxHumidity = weahterDayEntity.getAvgHumidity();
                    if (minHumidity > weahterDayEntity.getAvgHumidity())
                        minHumidity = weahterDayEntity.getAvgHumidity();
                }
            }

            input.setPanelTemperature(0);
            input.setPanelTemperatureYes(0);
            if (panelTemperatureEntities == null || panelTemperatureEntities.size() == 0)
                continue;
            for (PanelTemperatureEntity panelTemperatureEntity: panelTemperatureEntities) {
                Date panelTemperatureDate = new Date(panelTemperatureEntity.getDate().getTime());
                if (dateUtils.isSameDay(panelTemperatureDate, oneDayBefore)) {
                    input.setPanelTemperatureYes(panelTemperatureEntity.getTemperature());

                    if (maxPanelTemperature < panelTemperatureEntity.getTemperature())
                        maxPanelTemperature = panelTemperatureEntity.getTemperature();
                    if (minPanelTemperature > panelTemperatureEntity.getTemperature())
                        minPanelTemperature = panelTemperatureEntity.getTemperature();
                }
                if (dateUtils.isSameDay(panelTemperatureDate, date) && !isForecast) {
                    input.setPanelTemperature(panelTemperatureEntity.getTemperature());
                    if (maxPanelTemperature < panelTemperatureEntity.getTemperature())
                        maxPanelTemperature = panelTemperatureEntity.getTemperature();
                    if (minPanelTemperature > panelTemperatureEntity.getTemperature())
                        minPanelTemperature = panelTemperatureEntity.getTemperature();
                }
            }
            inputList.add(input);
            if (!isForecast)
                outputList.add(output);
        }

        //保存归一化参数
        normalizationComponent.insertNormalization(maxPower, minPower, "power");
        normalizationComponent.insertNormalization(maxMaxTemperature, minMaxTemperature, "power_maxTemperature");
        normalizationComponent.insertNormalization(maxMinTemperature, minMinTemperature, "power_minTemperature");
        normalizationComponent.insertNormalization(maxHumidity, minHumidity, "power_humidity");
        normalizationComponent.insertNormalization(maxPanelTemperature, minPanelTemperature, "power_panelTemperature");

        //对输入输出进行归一化操作
        normalizeInputAndOutput();
        return true;
    }

    /**
     * 光伏发电预测输入输出数据的归一化操作
     */
    private void normalizeInputAndOutput() {
        if (inputList.size() == 0)
            return;

        //发电功率
        List<NormalizationEntity> powerNormalizations = normalizationRepository.findBySymbol("power");
        if (powerNormalizations != null && powerNormalizations.size() > 0) {
            double maxPower = powerNormalizations.get(0).getMaxValue(), minPower = powerNormalizations.get(0).getMinValue();
            for (PowerForecastInput input: inputList) {
                normalizePowerOutput(input, maxPower, minPower);
            }
            if (outputList != null && outputList.size() > 0) {
                for (PowerForecastOutput output: outputList) {
                    normalizePowerOutput(output, maxPower, minPower);
                }
            }
        }

        //最高温度
        List<NormalizationEntity> maxTemperatureNormalizations = normalizationRepository.findBySymbol("power_maxTemperature");
        if (maxTemperatureNormalizations != null && maxTemperatureNormalizations.size() > 0) {
            double maxMaxTemperature = maxTemperatureNormalizations.get(0).getMaxValue(),
                    minMaxTemperature = maxTemperatureNormalizations.get(0).getMinValue();
            for (PowerForecastInput input: inputList) {
                input.setMaxTemperature(normalizationComponent.normalize(maxMaxTemperature, minMaxTemperature, input.getMaxTemperature()));
                input.setMaxTemperatureYes(normalizationComponent.normalize(maxMaxTemperature, minMaxTemperature, input.getMaxTemperatureYes()));
            }
        }

        //最低温度
        List<NormalizationEntity> minTemperatureNormalizations = normalizationRepository.findBySymbol("power_minTemperature");
        if (minTemperatureNormalizations != null && minTemperatureNormalizations.size() > 0) {
            double maxMinTemperature = minTemperatureNormalizations.get(0).getMaxValue(),
                    minMinTemperature = minTemperatureNormalizations.get(0).getMinValue();
            for (PowerForecastInput input: inputList) {
                input.setMinTemperature(normalizationComponent.normalize(maxMinTemperature, minMinTemperature, input.getMinTemperature()));
                input.setMinTemperatureYes(normalizationComponent.normalize(maxMinTemperature, minMinTemperature, input.getMinTemperatureYes()));
            }
        }

        //湿度
        List<NormalizationEntity> HumidityNormalizations = normalizationRepository.findBySymbol("power_humidity");
        if (HumidityNormalizations != null && HumidityNormalizations.size() > 0) {
            double maxHumidity = HumidityNormalizations.get(0).getMaxValue(),
                    minHumidity = HumidityNormalizations.get(0).getMinValue();
            for (PowerForecastInput input: inputList) {
                input.setHumidity(normalizationComponent.normalize(maxHumidity, minHumidity, input.getHumidity()));
                input.setHumidityYes(normalizationComponent.normalize(maxHumidity, minHumidity, input.getHumidityYes()));
            }
        }

        //电池板温度
        List<NormalizationEntity> panelNormalizations = normalizationRepository.findBySymbol("power_panelTemperature");
        if (panelNormalizations != null && panelNormalizations.size() > 0) {
            double maxPanel = panelNormalizations.get(0).getMaxValue(),
                    minPanel = panelNormalizations.get(0).getMinValue();
            for (PowerForecastInput input: inputList) {
                input.setPanelTemperature(normalizationComponent.normalize(maxPanel, minPanel, input.getPanelTemperature()));
                input.setPanelTemperatureYes(normalizationComponent.normalize(maxPanel, minPanel, input.getPanelTemperatureYes()));
            }
        }
    }

    /**
     * 发电功率的归一化
     * @param output
     * @param maxPower
     * @param minPower
     */
    private void normalizePowerOutput(PowerForecastOutput output, double maxPower, double minPower) {
        output.setPower8Yes(normalizationComponent.normalize(maxPower, minPower, output.getPower8Yes()));
        output.setPower9Yes(normalizationComponent.normalize(maxPower, minPower, output.getPower9Yes()));
        output.setPower10Yes(normalizationComponent.normalize(maxPower, minPower, output.getPower10Yes()));
        output.setPower11Yes(normalizationComponent.normalize(maxPower, minPower, output.getPower11Yes()));
        output.setPower12Yes(normalizationComponent.normalize(maxPower, minPower, output.getPower12Yes()));
        output.setPower13Yes(normalizationComponent.normalize(maxPower, minPower, output.getPower13Yes()));
        output.setPower14Yes(normalizationComponent.normalize(maxPower, minPower, output.getPower14Yes()));
        output.setPower15Yes(normalizationComponent.normalize(maxPower, minPower, output.getPower15Yes()));
        output.setPower16Yes(normalizationComponent.normalize(maxPower, minPower, output.getPower16Yes()));
        output.setPower17Yes(normalizationComponent.normalize(maxPower, minPower, output.getPower17Yes()));
        output.setPower18Yes(normalizationComponent.normalize(maxPower, minPower, output.getPower18Yes()));
    }
}
