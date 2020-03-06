package com.hexing.forecast.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "load", schema = "forecast", catalog = "")
public class LoadEntity {
    private byte[] id;
    private byte[] energyconsumerId;
    private Timestamp date;
    private Double load;

    @Id
    @Column(name = "ID")
    public byte[] getId() {
        return id;
    }

    public void setId(byte[] id) {
        this.id = id;
    }

    @Basic
    @Column(name = "EnergyconsumerID")
    public byte[] getEnergyconsumerId() {
        return energyconsumerId;
    }

    public void setEnergyconsumerId(byte[] energyconsumerId) {
        this.energyconsumerId = energyconsumerId;
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
    @Column(name = "load")
    public Double getLoad() {
        return load;
    }

    public void setLoad(Double load) {
        this.load = load;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoadEntity that = (LoadEntity) o;
        return Arrays.equals(id, that.id) &&
                Arrays.equals(energyconsumerId, that.energyconsumerId) &&
                Objects.equals(date, that.date) &&
                Objects.equals(load, that.load);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(date, load);
        result = 31 * result + Arrays.hashCode(id);
        result = 31 * result + Arrays.hashCode(energyconsumerId);
        return result;
    }
}
