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
	private String HotWord_ID;
	
	
	private String Keyword_ID;
	
	
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
	
	/**
	 * ��ʶλ
	 */
	private String flag;
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getKeyword_ID() {
		return Keyword_ID;
	}
	public void setKeyword_ID(String keyword_ID) {
		Keyword_ID = keyword_ID;
	}
	
	public String getHotWord_ID() {
		return HotWord_ID;
	}
	public void setHotWord_ID(String hotWord_ID) {
		HotWord_ID = hotWord_ID;
	}
	
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
