package com.hexing.forecast.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "panel_temperature", schema = "forecast", catalog = "")
public class PanelTemperatureEntity {
    private byte[] id;
    private byte[] substationId;
    private Timestamp date;
    private Double temperature;

    @Id
    @Column(name = "ID")
    public byte[] getId() {
        return id;
    }

    public void setId(byte[] id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SubstationID")
    public byte[] getSubstationId() {
        return substationId;
    }

    public void setSubstationId(byte[] substationId) {
        this.substationId = substationId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PanelTemperatureEntity that = (PanelTemperatureEntity) o;
        return Arrays.equals(id, that.id) &&
                Arrays.equals(substationId, that.substationId) &&
                Objects.equals(date, that.date) &&
                Objects.equals(temperature, that.temperature);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(date, temperature);
        result = 31 * result + Arrays.hashCode(id);
        result = 31 * result + Arrays.hashCode(substationId);
        return result;
    }
}
