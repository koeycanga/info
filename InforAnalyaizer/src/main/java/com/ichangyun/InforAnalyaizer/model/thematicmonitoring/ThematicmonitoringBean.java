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
	
	/**
	 * ����ID
	 */
	private String Article_ID;
	
	/**
	 * ����
	 */
	private String MonitoringWord;
	
	/**
	 * �������һ��Ӧ����������
	 */
	private int m_lg1;
	
	/**
	 * ������϶���Ӧ����������
	 */
	private int m_lg2;
	
	
	/**
	 * �����������Ӧ����������
	 */
	private int m_lg3;
	
	
	/**
	 * ��ⷽ����ʼʱ��
	 */
	private String PlanStartTime;
	
	
	/**
	 * ��ⷽ������ʱ��
	 */
	private String PlanEndTime;
	

	public String getPlanStartTime() {
		return PlanStartTime;
	}
	public void setPlanStartTime(String planStartTime) {
		PlanStartTime = planStartTime;
	}
	public String getPlanEndTime() {
		return PlanEndTime;
	}
	public void setPlanEndTime(String planEndTime) {
		PlanEndTime = planEndTime;
	}
	public int getM_lg1() {
		return m_lg1;
	}
	public void setM_lg1(int m_lg1) {
		this.m_lg1 = m_lg1;
	}
	public int getM_lg2() {
		return m_lg2;
	}
	public void setM_lg2(int m_lg2) {
		this.m_lg2 = m_lg2;
	}
	public int getM_lg3() {
		return m_lg3;
	}
	public void setM_lg3(int m_lg3) {
		this.m_lg3 = m_lg3;
	}
	public String getMonitoringWord() {
		return MonitoringWord;
	}
	public void setMonitoringWord(String monitoringWord) {
		MonitoringWord = monitoringWord;
	}
	public String getArticle_ID() {
		return Article_ID;
	}
	public void setArticle_ID(String article_ID) {
		Article_ID = article_ID;
	}
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
