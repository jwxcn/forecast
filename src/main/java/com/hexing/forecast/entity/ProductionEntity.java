package com.hexing.forecast.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "production", schema = "forecast", catalog = "")
public class ProductionEntity {
    private byte[] id;
    private byte[] energyconsumerId;
    private Timestamp date;
    private Double production;

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
    @Column(name = "production")
    public Double getProduction() {
        return production;
    }

    public void setProduction(Double production) {
        this.production = production;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductionEntity that = (ProductionEntity) o;
        return Arrays.equals(id, that.id) &&
                Arrays.equals(energyconsumerId, that.energyconsumerId) &&
                Objects.equals(date, that.date) &&
                Objects.equals(production, that.production);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(date, production);
        result = 31 * result + Arrays.hashCode(id);
        result = 31 * result + Arrays.hashCode(energyconsumerId);
        return result;
    }
}
