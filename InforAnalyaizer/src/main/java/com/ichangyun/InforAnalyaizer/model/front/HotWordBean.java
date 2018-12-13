/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.model.front;

import com.ichangyun.InforAnalyaizer.model.BaseBean;

/**
 * 热词云相关实体类  对应数据表a_hotwordsbasicinfo和a_articlehotwordsinfo
 * @author renhao
 * 2018-11-16 10:03
 */
public class HotWordBean extends BaseBean {
  
	/**
	 * 采集领域ＩＤ
	 */
	private String CollectionField_ID;
	
	/**
	 * 热词云ID
	 */
	private String HotWord_ID;
	
	
	private String Keyword_ID;
	
	
	private int KeywordNo;
	
	/**
	 * 热词云
	 */
	private String HotWord;
	
	/**
	 * 有效flag  0：无效，1：有效
	 */
	private String ValidFlag;
	
	/**
	 * 作成者
	 */
	private String CreateUser;
	
	/**
	 * 作成日時
	 */
	private String CreateDateTime;
	
	/**
	 * 更新者
	 */
	private String UpdateUser;
	
	/**
	 * 更新日時
	 */
	private String UpdateDateTime;
	
	/**
	 * 标识位
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
