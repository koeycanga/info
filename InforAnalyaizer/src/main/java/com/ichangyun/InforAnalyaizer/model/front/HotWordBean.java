/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 * �����鱨����ϵͳ
 */
package com.ichangyun.InforAnalyaizer.model.front;

import com.ichangyun.InforAnalyaizer.model.BaseBean;

/**
 * �ȴ������ʵ����  ��Ӧ���ݱ�a_hotwordsbasicinfo��a_articlehotwordsinfo
 * @author renhao
 * 2018-11-16 10:03
 */
public class HotWordBean extends BaseBean {
  
	/**
	 * �ɼ�����ɣ�
	 */
	private String CollectionField_ID;
	
	/**
	 * �ȴ���ID
	 */
	private int KeywordNo;
	
	/**
	 * �ȴ���
	 */
	private String HotWord;
	
	/**
	 * ��Чflag  0����Ч��1����Ч
	 */
	private String ValidFlag;
	
	/**
	 * ������
	 */
	private String CreateUser;
	
	/**
	 * �����Օr
	 */
	private String CreateDateTime;
	
	/**
	 * ������
	 */
	private String UpdateUser;
	
	/**
	 * �����Օr
	 */
	private String UpdateDateTime;
	
	public String getCollectionField_ID() {
		return CollectionField_ID;
	}
	public void setCollectionField_ID(String collectionField_ID) {
		CollectionField_ID = collectionField_ID;
	}
	public int getKeywordNo() {
		return KeywordNo;
	}
	public void setKeywordNo(int keywordNo) {
		KeywordNo = keywordNo;
	}
	public String getHotWord() {
		return HotWord;
	}
	public void setHotWord(String hotWord) {
		HotWord = hotWord;
	}
	public String getValidFlag() {
		return ValidFlag;
	}
	public void setValidFlag(String validFlag) {
		ValidFlag = validFlag;
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
