package com.youngzeu.mplus.entity.convert;

import java.time.LocalDateTime;
import java.util.Date;

public class ConvertEntity extends ConvertEntityKey {
    private String dataValue;

    private String dataName;

    private String spareStr1;

    private String spareStr2;

    private String insertUser;

    private String updateUser;

    public ConvertEntity(String typeId, String dataId, String dataValue, String dataName, String spareStr1, String spareStr2, String insertUser, String updateUser) {
        super(typeId, dataId);
        this.dataValue = dataValue;
        this.dataName = dataName;
        this.spareStr1 = spareStr1;
        this.spareStr2 = spareStr2;
        this.insertUser = insertUser;
        this.updateUser = updateUser;
    }

    public ConvertEntity() {
        super();
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue == null ? null : dataValue.trim();
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName == null ? null : dataName.trim();
    }

    public String getSpareStr1() {
        return spareStr1;
    }

    public void setSpareStr1(String spareStr1) {
        this.spareStr1 = spareStr1 == null ? null : spareStr1.trim();
    }

    public String getSpareStr2() {
        return spareStr2;
    }

    public void setSpareStr2(String spareStr2) {
        this.spareStr2 = spareStr2 == null ? null : spareStr2.trim();
    }

    public String getInsertUser() {
        return insertUser;
    }

    public void setInsertUser(String insertUser) {
        this.insertUser = insertUser == null ? null : insertUser.trim();
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

}