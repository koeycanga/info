package com.ichangyun.InforAnalyaizer.model.numbercontroll;


/**
 * 
 *  对应数据表 m_numberingcontrol 的Bean
 *
 */
public class NumberingcontrolBean {

	private String ControlID;
	private String ControlName;
	private String FirstCharacter;
	private int NumberOfDigits;
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