package com.youngzeu.mplus.entity.convert;

import com.youngzeu.mplus.entity.base.BaseEntity;

public class ConvertEntityKey extends BaseEntity {
    private String typeId;

    private String dataId;

    public ConvertEntityKey(String typeId, String dataId) {
        this.typeId = typeId;
        this.dataId = dataId;
    }

    public ConvertEntityKey() {
        super();
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId == null ? null : dataId.trim();
    }
}