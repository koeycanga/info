/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.model.classification;

import com.ichangyun.InforAnalyaizer.model.BaseBean;

/**
 * 分类体系数据ｂｅａｎ
 * 对应数据表 M_ClassificationInfo
 *
 */
public class ClassificationInfoBean extends BaseBean{

	/**
	 * 分类体系ＩＤ
	 */
	private String Classification_ID;
	
	/**
	 * 分类体系名称
	 */
	private String ClassificationName;
	
	/**
	 * 分类体系父节点ＩＤ
	 */
	private String Parent_Classification_ID;
	
	
	/**
	 * 分类体系节点路径
	 */
	private String nodePath;
	
	
	/**
	 * 分类体系排位顺序
	 */
	private int DisplayOrder;
	
	/**
	 * 分类体系描述
	 */
	private String Description;
	
	/**
	 * 分类体系验证标识位
	 */
	private String ValidFlag;
	
	/**
	 * 创建者
	 */
	private String CreateUser;
	
	/**
	 * 创建时间
	 */
	private String CreateDateTime;
	
	/**
	 * 修改者
	 */
	private String UpdateUser;
	
	/**
	 * 修改时间
	 */
	private String UpdateDateTime;
	
	/**
	 * 分类体系子节点总数
	 */
	private int children_lg;
	
	/**
	 * 分类体系是否已绑定网站
	 */
	private String isbinding;
	
	/**
	 * 分类体系绑定网站数量
	 */
	private int binding_lg;
	
	/**
	 * 文章体系对应文章发布时间
	 */
	private String Releasetime;
	
	/**
	 * 查询数量标识位
	 */
	private int cnt;
	
	/**
	 * 昨天统计总数量
	 */
	private int yesterday_cnt;
	
	
	/**
	 * 今天统计总数量
	 */
	private int today_cnt;
	
	/**
	 * 前端是否点击
	 */
	private boolean isclick;
	
	public ClassificationInfoBean() {
		this.isclick = false;
	}
	
	public String getNodePath() {
		return nodePath;
	}

	public void setNodePath(String nodePath) {
		this.nodePath = nodePath;
	}
	
	public boolean isIsclick() {
		return isclick;
	}
	public void setIsclick(boolean isclick) {
		this.isclick = isclick;
	}
	public int getYesterday_cnt() {
		return yesterday_cnt;
	}
	public void setYesterday_cnt(int yesterday_cnt) {
		this.yesterday_cnt = yesterday_cnt;
	}
	public int getToday_cnt() {
		return today_cnt;
	}
	public void setToday_cnt(int today_cnt) {
		this.today_cnt = today_cnt;
	}
	public String getReleasetime() {
		return Releasetime;
	}
	public void setReleasetime(String releasetime) {
		Releasetime = releasetime;
	}
	
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getBinding_lg() {
		return binding_lg;
	}
	public void setBinding_lg(int binding_lg) {
		this.binding_lg = binding_lg;
	}
	public String getIsbinding() {
		return isbinding;
	}
	public void setIsbinding(String isbinding) {
		this.isbinding = isbinding;
	}
	public int getChildren_lg() {
		return children_lg;
	}
	public void setChildren_lg(int children_lg) {
		this.children_lg = children_lg;
	}
	public String getClassification_ID() {
		return Classification_ID;
	}
	public void setClassification_ID(String classification_ID) {
		Classification_ID = classification_ID;
	}
	public String getClassificationName() {
		return ClassificationName;
	}
	public void setClassificationName(String classificationName) {
		ClassificationName = classificationName;
	}
	public String getParent_Classification_ID() {
		return Parent_Classification_ID;
	}
	public void setParent_Classification_ID(String parent_Classification_ID) {
		Parent_Classification_ID = parent_Classification_ID;
	}
	public int getDisplayOrder() {
		return DisplayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		DisplayOrder = displayOrder;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
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
