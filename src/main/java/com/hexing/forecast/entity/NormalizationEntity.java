package com.hexing.forecast.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "normalization", schema = "forecast", catalog = "")
public class NormalizationEntity {
    private byte[] id;
    private String symbol;
    private double maxValue;
    private double minValue;

    @Id
    @Column(name = "ID")
    public byte[] getId() {
        return id;
    }

    public void setId(byte[] id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Symbol")
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Basic
    @Column(name = "maxValue")
    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    @Basic
    @Column(name = "minValue")
    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalizationEntity that = (NormalizationEntity) o;
        return Double.compare(that.maxValue, maxValue) == 0 &&
                Double.compare(that.minValue, minValue) == 0 &&
                Arrays.equals(id, that.id) &&
                Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(symbol, maxValue, minValue);
        result = 31 * result + Arrays.hashCode(id);
        return result;
    }
}
