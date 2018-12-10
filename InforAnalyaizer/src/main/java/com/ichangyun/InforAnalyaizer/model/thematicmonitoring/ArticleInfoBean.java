/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 * �����鱨����ϵͳ
 */
package com.ichangyun.InforAnalyaizer.model.thematicmonitoring;

/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 *  �����鱨ϵͳ
 */
import com.ichangyun.InforAnalyaizer.model.BaseBean;

/** ������Ϣʵ����,��Ӧ���ݱ� A_ArticleBasicInfo
 * @author renhao
 * 2018-11-13 16:19
 */
public class ArticleInfoBean extends BaseBean {

	/**
	 * �ɼ�����ɣ�
	 */
	private String CollectionField_ID;
	
	/**
	 * ����ID
	 */
	private String Article_ID;
	
	/**
	 * ����ID
	 */
	private String Classification_ID;
	
	/**
	 * ����
	 */
	private String ArticleTitle;
	
	/**
	 * ժҪ
	 */
	private String ArticleAbstract;
	
	/**
	 * ����
	 */
	private String ArticleText;
	
	/**
	 * ����
	 */
	private String ArticleAuthor;
	
	
	/**
	 * ����ʱ��
	 */
	private String Releasetime;
	
	
	/**
	 * ����ʱ��(����)
	 */
	private String Releasetime_bak;
	
	
	/**
	 * ԭ������URL
	 */
	private String ArticleURL;
	
	/**
	 * �ؼ���
	 */
	private String ArticleKeyWord;
	
	
	/**
	 * ͼƬ���·��
	 */
	private String PictureStoragePath;
	
	
	/**
	 * ��Դ��վID
	 */
	private String Website_ID;
	
	/**
	 * ��Դ��վ����
	 */
	private String WebsiteName;
	
	
	/**
	 * ��Դ����
	 */
	private String SourceDivision;
	
	/**
	 * �������
	 */
	private String EmotionDivision;

	
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
	 * ��ѯ����
	 */
	private String search_key;
	
	/**
	 * ��ѯʱ�����
	 */
	private String montime;
	
	/**
	 * ��ѯ������Բ���
	 */
	private String emoana;
	
	/**
	 * �Ƿ�ϲ��������²���
	 */
	private String simart;
	
	/**
	 * �������
	 */
	private String sort;
	
	/**
	 * ��Դ����
	 */
	private String infsour;
	
	/**
	 * ʱ����ʼ
	 */
	private String montime_start;
	
	/**
	 * ʱ�����
	 */
	private String montime_end;
	
	/**
	 * ������������
	 */
	private int sim_cnt;
	
	/**
	 * count(*)�ֶα���
	 */
	private int cnt;
	
	/**
	 * ���±��ղصĴ���
	 */
	private int collcnt;

	
	/**
	 * ����ID
	 */
	private String Plan_ID;
	
	/**
	 * ֻ�������յ�����
	 */
	private String YMD_Date ;
	
	
	/**
	 * ֻ��ʱ��ͷ��ӵ�����
	 */
	private String Hi_Date;
	
	
	/**
	 * ����
	 */
	private String MonitoringWord;
	
	
	
	/**
	 * �û�Ԥ���������д�ID
	 */
	private int SensitiveWord_ID;
	
	/**
	 * ���д�
	 */
	private String SensitiveWord;
	
	
	/**
	 * ����Ԥ������
	 */
	private int yesterday_total;
	
	/**
	 * ����Ԥ������
	 */
	private int totday_total;
	
	/**
	 * ��������,all,title,text
	 */
	private String search_type;

	/**
	 * userid
	 */
	private String userid;

    /**
     *  �û�Ԥ�������µ�����
     */
	private int yj_cnt;

	/**
	 * ���д�NO
	 */
	private int SensitiveWordNo;

	/**
	 * �Ƽ����д�����ID
	 */
	private String MasterID;
	
	/**
	 * �Ƽ����д���������
	 */
	private String ControlName ;
	
	/**
	 * ���д�����
	 */
	private String MasterValue;
	
	/**
	 * ��λ˳��
	 */
	private int DisplayOrder;
	
	
	/**
	 * �Ƿ�Ԥ��
	 */
	private  String isearlywarning;
	
	/**
	 * �Ƿ���ʾԤ����
	 */
	private boolean isshowyjc;
	
	/**
	 * ���д�����ID
	 */
	private String SensitiveWordType_ID;
	
	public String getSensitiveWordType_ID() {
		return SensitiveWordType_ID;
	}

	public void setSensitiveWordType_ID(String sensitiveWordType_ID) {
		SensitiveWordType_ID = sensitiveWordType_ID;
	}

	public boolean isIsshowyjc() {
		return isshowyjc;
	}

	public void setIsshowyjc(boolean isshowyjc) {
		this.isshowyjc = isshowyjc;
	}

	public String getIsearlywarning() {
		return isearlywarning;
	}

	public void setIsearlywarning(String isearlywarning) {
		this.isearlywarning = isearlywarning;
	}

	private String yjfs;
	
	public String getYjfs() {
		return yjfs;
	}

	public void setYjfs(String yjfs) {
		this.yjfs = yjfs;
	}

	public int getSensitiveWord_ID() {
		return SensitiveWord_ID;
	}

	public void setSensitiveWord_ID(int sensitiveWord_ID) {
		SensitiveWord_ID = sensitiveWord_ID;
	}
	
	public int getDisplayOrder() {
		return DisplayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		DisplayOrder = displayOrder;
	}

	public String getMasterValue() {
		return MasterValue;
	}

	public void setMasterValue(String masterValue) {
		MasterValue = masterValue;
	}

	public String getMasterID() {
		return MasterID;
	}

	public void setMasterID(String masterID) {
		MasterID = masterID;
	}

	public String getControlName() {
		return ControlName;
	}

	public void setControlName(String controlName) {
		ControlName = controlName;
	}

	public int getSensitiveWordNo() {
		return SensitiveWordNo;
	}

	public void setSensitiveWordNo(int sensitiveWordNo) {
		SensitiveWordNo = sensitiveWordNo;
	}

	public int getYesterday_total() {
		return yesterday_total;
	}

	public void setYesterday_total(int yesterday_total) {
		this.yesterday_total = yesterday_total;
	}

	public int getTotday_total() {
		return totday_total;
	}

	public void setTotday_total(int totday_total) {
		this.totday_total = totday_total;
	}

	public String getSensitiveWord() {
		return SensitiveWord;
	}

	public void setSensitiveWord(String sensitiveWord) {
		SensitiveWord = sensitiveWord;
	}

	public String getMonitoringWord() {
		return MonitoringWord;
	}

	public void setMonitoringWord(String monitoringWord) {
		MonitoringWord = monitoringWord;
	}

	public String getHi_Date() {
		return Hi_Date;
	}

	public void setHi_Date(String hi_Date) {
		Hi_Date = hi_Date;
	}

	public String getReleasetime_bak() {
		return Releasetime_bak;
	}

	public void setReleasetime_bak(String releasetime_bak) {
		Releasetime_bak = releasetime_bak;
	}
	
	public String getYMD_Date() {
		return YMD_Date;
	}

	public void setYMD_Date(String yMD_Date) {
		YMD_Date = yMD_Date;
	}

	public String getPlan_ID() {
		return Plan_ID;
	}

	public void setPlan_ID(String plan_ID) {
		Plan_ID = plan_ID;
	}
	
	public int getYj_cnt() {
		return yj_cnt;
	}

	public void setYj_cnt(int yj_cnt) {
		this.yj_cnt = yj_cnt;
	}

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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
}
