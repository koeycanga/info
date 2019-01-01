/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.model.numbercontroll;

/**
 * 采番表的实体类
 * 对应数据表：m_numberingcontrol
 * @author renhao
 * @Date:2018-11-9
 */
public class NumberingcontrolBean {

    /**
     * 采番ID
     */
    private String ControlID;

    /**
     * 采番名称
     */
    private String ControlName;

    /**
     * 采番位首字母
     */
    private String FirstCharacter;

    /**
     * 采番总位数
     */
    private int NumberOfDigits;

    /**
     * 采番当前数值
     */
    private int PresentValue;

    public String getControlID() {
        return ControlID;
    }
    public void setControlID(String controlID) {
        ControlID = controlID;
    }
    public String getControlName() {
        return ControlName;
    }
    public void setControlName(String controlName) {
        ControlName = controlName;
    }
    public String getFirstCharacter() {
        return FirstCharacter;
    }
    public void setFirstCharacter(String firstCharacter) {
        FirstCharacter = firstCharacter;
    }
    public int getNumberOfDigits() {
        return NumberOfDigits;
    }
    public void setNumberOfDigits(int numberOfDigits) {
        NumberOfDigits = numberOfDigits;
    }
    public int getPresentValue() {
        return PresentValue;
    }
    public void setPresentValue(int presentValue) {
        PresentValue = presentValue;
    }
}
