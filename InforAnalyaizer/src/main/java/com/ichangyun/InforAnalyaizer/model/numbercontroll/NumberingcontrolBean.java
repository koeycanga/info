/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 *  �����鱨ϵͳ
 */
package com.ichangyun.InforAnalyaizer.model.numbercontroll;


/**
 *   �ɷ���ʵ����
 *   ��Ӧ���ݱ�m_numberingcontrol��ʵ����
 *   @author renhao
 *   Date:2018-11-9
 */
public class NumberingcontrolBean {

	/**
	 * �ɷ�ID
	 */
	private String ControlID;
	
	/**
	 * �ɷ�����
	 */
	private String ControlName;
	
	/**
	 * �ɷ�λ����ĸ
	 */
	private String FirstCharacter;
	
	/**
	 * �ɷ���λ��
	 */
	private int NumberOfDigits;
	
	/**
	 * �ɷ���ǰ��ֵ
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
