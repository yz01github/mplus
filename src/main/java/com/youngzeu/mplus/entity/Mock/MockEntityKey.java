package com.youngzeu.mplus.entity.mock;

import com.youngzeu.mplus.entity.base.BaseEntity;

public class MockEntityKey extends BaseEntity {
    private String systemId;

    private String mockCode;

    private String mockAttr;

    public MockEntityKey(String systemId, String mockCode, String mockAttr) {
        this.systemId = systemId;
        this.mockCode = mockCode;
        this.mockAttr = mockAttr;
    }

    public MockEntityKey() {
        super();
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    public String getMockCode() {
        return mockCode;
    }

    public void setMockCode(String mockCode) {
        this.mockCode = mockCode == null ? null : mockCode.trim();
    }

    public String getMockAttr() {
        return mockAttr;
    }

    public void setMockAttr(String mockAttr) {
        this.mockAttr = mockAttr == null ? null : mockAttr.trim();
    }
}