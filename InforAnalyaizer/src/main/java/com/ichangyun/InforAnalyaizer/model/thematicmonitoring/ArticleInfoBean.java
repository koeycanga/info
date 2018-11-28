/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.model.thematicmonitoring;

import com.ichangyun.InforAnalyaizer.model.BaseBean;

/** 文字信息实体类,对应数据表 A_ArticleBasicInfo
 * @author renhao
 * 2018-11-13 16:19
 */
public class ArticleInfoBean extends BaseBean {

	/**
	 * 采集领域ＩＤ
	 */
	private String CollectionField_ID;
	
	/**
	 * 文章ID
	 */
	private String Article_ID;
	
	/**
	 * 分类ID
	 */
	private String Classification_ID;
	
	/**
	 * 标题
	 */
	private String ArticleTitle;
	
	/**
	 * 摘要
	 */
	private String ArticleAbstract;
	
	/**
	 * 正文
	 */
	private String ArticleText;
	
	/**
	 * 作者
	 */
	private String ArticleAuthor;
	
	/**
	 * 发布时间
	 */
	private String Releasetime;
	
	/**
	 * 原文链接URL
	 */
	private String ArticleURL;
	
	/**
	 * 关键词
	 */
	private String ArticleKeyWord;
	
	
	/**
	 * 图片存放路径
	 */
	private String PictureStoragePath;
	
	
	/**
	 * 来源网站ID
	 */
	private String Website_ID;
	
	/**
	 * 来源网站名称
	 */
	private String WebsiteName;
	
	
	/**
	 * 来源区分
	 */
	private String SourceDivision;
	
	/**
	 * 情感区分
	 */
	private String EmotionDivision;
	
	/**
	 * 预警状态
	 */
	private String EarlyWarningState;
	
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
	 * 查询参数
	 */
	private String search_key;
	
	/**
	 * 查询时间参数
	 */
	private String montime;
	
	/**
	 * 查询情感属性参数
	 */
	private String emoana;
	
	/**
	 * 是否合并相似文章参数
	 */
	private String simart;
	
	/**
	 * 排序参数
	 */
	private String sort;
	
	/**
	 * 来源参数
	 */
	private String infsour;
	
	/**
	 * 时间起始
	 */
	private String montime_start;
	
	/**
	 * 时间结束
	 */
	private String montime_end;
	
	/**
	 * 相似文章总数
	 */
	private int sim_cnt;
	
	/**
	 * count(*)字段别名
	 */
	private int cnt;
	
	/**
	 * 文章被收藏的次数
	 */
	private int collcnt;

	/**
	 * 搜索类型,all,title,text
	 */
	private String search_type;

	public String getSearch_type() {
		return search_type;
	}

	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}

	public int getCollcnt() {
		return collcnt;
	}
	public void setCollcnt(int collcnt) {
		this.collcnt = collcnt;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getSim_cnt() {
		return sim_cnt;
	}
	public void setSim_cnt(int sim_cnt) {
		this.sim_cnt = sim_cnt;
	}
	public String getWebsiteName() {
		return WebsiteName;
	}
	public void setWebsiteName(String websiteName) {
		WebsiteName = websiteName;
	}
	
	public String getMontime_start() {
		return montime_start;
	}
	public void setMontime_start(String montime_start) {
		this.montime_start = montime_start;
	}
	public String getMontime_end() {
		return montime_end;
	}
	public void setMontime_end(String montime_end) {
		this.montime_end = montime_end;
	}
	public String getSearch_key() {
		return search_key;
	}
	public void setSearch_key(String search_key) {
		this.search_key = search_key;
	}
	public String getMontime() {
		return montime;
	}
	public void setMontime(String montime) {
		this.montime = montime;
	}
	public String getEmoana() {
		return emoana;
	}
	public void setEmoana(String emoana) {
		this.emoana = emoana;
	}
	public String getSimart() {
		return simart;
	}
	public void setSimart(String simart) {
		this.simart = simart;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getInfsour() {
		return infsour;
	}
	public void setInfsour(String infsour) {
		this.infsour = infsour;
	}
	public String getCollectionField_ID() {
		return CollectionField_ID;
	}
	public void setCollectionField_ID(String collectionField_ID) {
		CollectionField_ID = collectionField_ID;
	}
	public String getArticle_ID() {
		return Article_ID;
	}
	public void setArticle_ID(String article_ID) {
		Article_ID = article_ID;
	}
	public String getClassification_ID() {
		return Classification_ID;
	}
	public void setClassification_ID(String classification_ID) {
		Classification_ID = classification_ID;
	}
	public String getArticleTitle() {
		return ArticleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		ArticleTitle = articleTitle;
	}
	public String getArticleAbstract() {
		return ArticleAbstract;
	}
	public void setArticleAbstract(String articleAbstract) {
		ArticleAbstract = articleAbstract;
	}
	public String getArticleText() {
		return ArticleText;
	}
	public void setArticleText(String articleText) {
		ArticleText = articleText;
	}
	public String getArticleAuthor() {
		return ArticleAuthor;
	}
	public void setArticleAuthor(String articleAuthor) {
		ArticleAuthor = articleAuthor;
	}
	public String getReleasetime() {
		return Releasetime;
	}
	public void setReleasetime(String releasetime) {
		Releasetime = releasetime;
	}
	public String getArticleURL() {
		return ArticleURL;
	}
	public void setArticleURL(String articleURL) {
		ArticleURL = articleURL;
	}
	public String getArticleKeyWord() {
		return ArticleKeyWord;
	}
	public void setArticleKeyWord(String articleKeyWord) {
		ArticleKeyWord = articleKeyWord;
	}
	public String getPictureStoragePath() {
		return PictureStoragePath;
	}
	public void setPictureStoragePath(String pictureStoragePath) {
		PictureStoragePath = pictureStoragePath;
	}
	public String getWebsite_ID() {
		return Website_ID;
	}
	public void setWebsite_ID(String website_ID) {
		Website_ID = website_ID;
	}
	public String getSourceDivision() {
		return SourceDivision;
	}
	public void setSourceDivision(String sourceDivision) {
		SourceDivision = sourceDivision;
	}
	public String getEmotionDivision() {
		return EmotionDivision;
	}
	public void setEmotionDivision(String emotionDivision) {
		EmotionDivision = emotionDivision;
	}
	public String getEarlyWarningState() {
		return EarlyWarningState;
	}
	public void setEarlyWarningState(String earlyWarningState) {
		EarlyWarningState = earlyWarningState;
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
