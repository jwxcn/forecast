package com.hexing.forecast.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "power", schema = "forecast", catalog = "")
public class PowerEntity {
    private byte[] id;
    private byte[] substationId;
    private Timestamp date;
    private Double power;

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
    @Column(name = "power")
    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PowerEntity that = (PowerEntity) o;
        return Arrays.equals(id, that.id) &&
                Arrays.equals(substationId, that.substationId) &&
                Objects.equals(date, that.date) &&
                Objects.equals(power, that.power);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(date, power);
        result = 31 * result + Arrays.hashCode(id);
        result = 31 * result + Arrays.hashCode(substationId);
        return result;
    }
}
