package com.ichangyun.InforAnalyaizer.model.thematicmonitoring;

import com.ichangyun.InforAnalyaizer.model.BaseBean;

/**
 * ��ⷽ����Ӧbean ��Ӧ���ݱ�m_planinfo ��   m_plandetail
 * @author renhao
 * 2018-11-13 10:29
 */
public class ThematicmonitoringBean extends BaseBean {
    
	/**
	 * �û�ID
	 */
	private String User_ID;
	
	/**
	 * ����ID
	 */
	private String Plan_ID;
	
	/**
	 * ��������
	 */
	private String PlanName;
	
	/**
	 * �������
	 */
	private int UnitNumber;
	
	/**
	 * ������
	 */
	private String CreateUser;
	
	/**
	 * ����ʱ��
	 */
	private String CreateDateTime;
	
	
	/**
	 * ������
	 */
	private String UpdateUser;
	
	
	/**
	 * ����ʱ��
	 */
	private String UpdateDateTime;
	
	/**
	 * ���NO
	 */
	private int UnitNo;
	
	/**
	 * �ų���
	 */
	private String RemoveWord;
	
	/**
	 * ����1
	 */
	private String MonitoringWord1;
	
	/**
	 * ����2
	 */
	private String MonitoringWord2;
	
	/**
	 * ����3
	 */
	private String MonitoringWord3;
	
	/**
	 * ����4
	 */
	private String MonitoringWord4;
	
	public String getMonitoringWord1() {
		return MonitoringWord1;
	}
	public void setMonitoringWord1(String monitoringWord1) {
		MonitoringWord1 = monitoringWord1;
	}
	public String getMonitoringWord2() {
		return MonitoringWord2;
	}
	public void setMonitoringWord2(String monitoringWord2) {
		MonitoringWord2 = monitoringWord2;
	}
	public String getMonitoringWord3() {
		return MonitoringWord3;
	}
	public void setMonitoringWord3(String monitoringWord3) {
		MonitoringWord3 = monitoringWord3;
	}
	public String getMonitoringWord4() {
		return MonitoringWord4;
	}
	public void setMonitoringWord4(String monitoringWord4) {
		MonitoringWord4 = monitoringWord4;
	}
	public String getRemoveWord() {
		return RemoveWord;
	}
	public void setRemoveWord(String removeWord) {
		RemoveWord = removeWord;
	}
	public int getUnitNo() {
		return UnitNo;
	}
	public void setUnitNo(int unitNo) {
		UnitNo = unitNo;
	}
	public String getUser_ID() {
		return User_ID;
	}
	public void setUser_ID(String user_ID) {
		User_ID = user_ID;
	}
	public String getPlan_ID() {
		return Plan_ID;
	}
	public void setPlan_ID(String plan_ID) {
		Plan_ID = plan_ID;
	}
	public String getPlanName() {
		return PlanName;
	}
	public void setPlanName(String planName) {
		PlanName = planName;
	}
	public int getUnitNumber() {
		return UnitNumber;
	}
	public void setUnitNumber(int unitNumber) {
		UnitNumber = unitNumber;
	}
	public String getCreateUser() {
		return CreateUser;
	}
	public void setCreateUser(String createUser) {
		CreateUser = createUser;
	}
	public String getCreateDateTime() {
		return CreateDateTime;
	}
	public void setCreateDateTime(String createDateTime) {
		CreateDateTime = createDateTime;
	}
	public String getUpdateUser() {
		return UpdateUser;
	}
	public void setUpdateUser(String updateUser) {
		UpdateUser = updateUser;
	}
	public String getUpdateDateTime() {
		return UpdateDateTime;
	}
	public void setUpdateDateTime(String updateDateTime) {
		UpdateDateTime = updateDateTime;
	}
	
	
}
