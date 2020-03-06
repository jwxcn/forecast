package com.hexing.forecast.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "weather_hour", schema = "forecast", catalog = "")
public class WeatherHourEntity {
    private byte[] id;
    private byte[] regionId;
    private Timestamp date;
    private Double temperature;
    private Integer weatherCondition;
    private Double humidity;
    private Double windSpeed;

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
    @Column(name = "temperature")
    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
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
    @Column(name = "humidity")
    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    @Basic
    @Column(name = "windSpeed")
    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherHourEntity that = (WeatherHourEntity) o;
        return Arrays.equals(id, that.id) &&
                Arrays.equals(regionId, that.regionId) &&
                Objects.equals(date, that.date) &&
                Objects.equals(temperature, that.temperature) &&
                Objects.equals(weatherCondition, that.weatherCondition) &&
                Objects.equals(humidity, that.humidity) &&
                Objects.equals(windSpeed, that.windSpeed);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(date, temperature, weatherCondition, humidity, windSpeed);
        result = 31 * result + Arrays.hashCode(id);
        result = 31 * result + Arrays.hashCode(regionId);
        return result;
    }
}
