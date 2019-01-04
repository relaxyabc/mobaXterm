package com.wanna.keygen.core;

/**
 * @author wanna
 * @since 2019-01-02
 */
public enum LicenseType {

    /**
     * 专业版
     */
    Professional(1),

    /**
     * 教育版
     */
    Educational(3),

    /**
     * 个人版
     */
    Personal(4);

    private int code;

    LicenseType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }}
