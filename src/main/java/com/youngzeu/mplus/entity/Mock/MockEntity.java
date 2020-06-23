package com.youngzeu.mplus.entity.mock;

public class MockEntity extends MockEntityKey {
    private String mockStr1;

    private String mockStr2;

    private String mockStr3;

    private String mockStr4;

    private String mockStr5;

    private String mockTag1;

    private String mockTag2;

    private String mockTag3;

    private String insertUser;

    private String updateUser;

    public MockEntity(String systemId, String mockCode, String mockAttr, String mockStr1, String mockStr2, String mockStr3, String mockStr4, String mockStr5, String mockTag1, String mockTag2, String mockTag3, String insertUser, String updateUser) {
        super(systemId, mockCode, mockAttr);
        this.mockStr1 = mockStr1;
        this.mockStr2 = mockStr2;
        this.mockStr3 = mockStr3;
        this.mockStr4 = mockStr4;
        this.mockStr5 = mockStr5;
        this.mockTag1 = mockTag1;
        this.mockTag2 = mockTag2;
        this.mockTag3 = mockTag3;
        this.insertUser = insertUser;
        this.updateUser = updateUser;
    }

    public MockEntity() {
        super();
    }

    public String getMockStr1() {
        return mockStr1;
    }

    public void setMockStr1(String mockStr1) {
        this.mockStr1 = mockStr1 == null ? null : mockStr1.trim();
    }

    public String getMockStr2() {
        return mockStr2;
    }

    public void setMockStr2(String mockStr2) {
        this.mockStr2 = mockStr2 == null ? null : mockStr2.trim();
    }

    public String getMockStr3() {
        return mockStr3;
    }

    public void setMockStr3(String mockStr3) {
        this.mockStr3 = mockStr3 == null ? null : mockStr3.trim();
    }

    public String getMockStr4() {
        return mockStr4;
    }

    public void setMockStr4(String mockStr4) {
        this.mockStr4 = mockStr4 == null ? null : mockStr4.trim();
    }

    public String getMockStr5() {
        return mockStr5;
    }

    public void setMockStr5(String mockStr5) {
        this.mockStr5 = mockStr5 == null ? null : mockStr5.trim();
    }

    public String getMockTag1() {
        return mockTag1;
    }

    public void setMockTag1(String mockTag1) {
        this.mockTag1 = mockTag1 == null ? null : mockTag1.trim();
    }

    public String getMockTag2() {
        return mockTag2;
    }

    public void setMockTag2(String mockTag2) {
        this.mockTag2 = mockTag2 == null ? null : mockTag2.trim();
    }

    public String getMockTag3() {
        return mockTag3;
    }

    public void setMockTag3(String mockTag3) {
        this.mockTag3 = mockTag3 == null ? null : mockTag3.trim();
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