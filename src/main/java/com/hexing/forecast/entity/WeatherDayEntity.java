package com.hexing.forecast.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "weather_day", schema = "forecast", catalog = "")
public class WeatherDayEntity {
    private byte[] id;
    private byte[] regionId;
    private Timestamp date;
    private Double maxTemperature;
    private Double minTemperature;
    private Double avgTemperature;
    private Integer weatherCondition;
    private Double avgHumidity;
    private Double avgWindSpeed;

    @Id
    @Column(name = "ID")
    public byte[] getId() {
        return id;
    }

    public void setId(byte[] id) {
        this.id = id;
    }

    @Basic
    @Column(name = "RegionID")
    public byte[] getRegionId() {
        return regionId;
    }

    public void setRegionId(byte[] regionId) {
        this.regionId = regionId;
    }

    @Basic
    @Column(name = "Date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "maxTemperature")
    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    @Basic
    @Column(name = "minTemperature")
    public Double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }

    @Basic
    @Column(name = "avgTemperature")
    public Double getAvgTemperature() {
        return avgTemperature;
    }

    public void setAvgTemperature(Double avgTemperature) {
        this.avgTemperature = avgTemperature;
    }

    @Basic
    @Column(name = "weatherCondition")
    public Integer getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(Integer weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    @Basic
    @Column(name = "avgHumidity")
    public Double getAvgHumidity() {
        return avgHumidity;
    }

    public void setAvgHumidity(Double avgHumidity) {
        this.avgHumidity = avgHumidity;
    }

    @Basic
    @Column(name = "avgWindSpeed")
    public Double getAvgWindSpeed() {
        return avgWindSpeed;
    }

    public void setAvgWindSpeed(Double avgWindSpeed) {
        this.avgWindSpeed = avgWindSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherDayEntity that = (WeatherDayEntity) o;
        return Arrays.equals(id, that.id) &&
                Arrays.equals(regionId, that.regionId) &&
                Objects.equals(date, that.date) &&
                Objects.equals(maxTemperature, that.maxTemperature) &&
                Objects.equals(minTemperature, that.minTemperature) &&
                Objects.equals(avgTemperature, that.avgTemperature) &&
                Objects.equals(weatherCondition, that.weatherCondition) &&
                Objects.equals(avgHumidity, that.avgHumidity) &&
                Objects.equals(avgWindSpeed, that.avgWindSpeed);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(date, maxTemperature, minTemperature, avgTemperature, weatherCondition, avgHumidity, avgWindSpeed);
        result = 31 * result + Arrays.hashCode(id);
        result = 31 * result + Arrays.hashCode(regionId);
        return result;
    }
}
