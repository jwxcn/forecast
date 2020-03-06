package com.hexing.forecast.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "substation", schema = "forecast", catalog = "")
public class SubstationEntity {
    private byte[] id;
    private String name;
    private byte[] regionId;

    @Id
    @Column(name = "ID")
    public byte[] getId() {
        return id;
    }

    public void setId(byte[] id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "RegionID")
    public byte[] getRegionId() {
        return regionId;
    }

    public void setRegionId(byte[] regionId) {
        this.regionId = regionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubstationEntity that = (SubstationEntity) o;
        return Arrays.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Arrays.equals(regionId, that.regionId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name);
        result = 31 * result + Arrays.hashCode(id);
        result = 31 * result + Arrays.hashCode(regionId);
        return result;
    }
}
