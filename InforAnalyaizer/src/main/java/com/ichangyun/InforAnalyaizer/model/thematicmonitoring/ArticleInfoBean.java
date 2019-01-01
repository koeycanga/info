/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.model.thematicmonitoring;

import com.ichangyun.InforAnalyaizer.model.BaseBean;

/**
 * 文字信息实体类
 * 对应数据表 A_ArticleBasicInfo
 * @author renhao
 * @date 2018-11-13 16:19
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
     * 相关文章ID
     */
    private String Relate_Article_ID;

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
     * 发布时间(备用)
     */
    private String Releasetime_bak;

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
    // 网站网址
    private String Website;

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
     * 查询时间参数
     */
    private String montime_bak;

 
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
     * 方案ID
     */
    private String Plan_ID;

    /**
     * 只有年月日的日期
     */
    private String YMD_Date ;

    /**
     * 只有时间和分钟的日期
     */
    private String Hi_Date;

    /**
     * 监测词
     */
    private String MonitoringWord;

    /**
     * 用户预警规则敏感词ID
     */
    private int SensitiveWord_ID;

    /**
     * 敏感词
     */
    private String SensitiveWord;

    /**
     * 昨天预警总数
     */
    private int yesterday_total;

    /**
     * 今天预警总数
     */
    private int totday_total;

    /**
     * 搜索类型,all,title,text
     */
    private String search_type;

    /**
     * userid
     */
    private String userid;

    /**
     *  用户预警的文章的数量
     */
    private int yj_cnt;

    /**
     * 敏感词NO
     */
    private int SensitiveWordNo;

    /**
     * 推荐敏感词种类ID
     */
    private String MasterID;

    /**
     * 推荐敏感词种类名称
     */
    private String ControlName ;

    /**
     * 敏感词名称
     */
    private String MasterValue;

    /**
     * 排位顺序
     */
    private int DisplayOrder;

    /**
     * 是否预警
     */
    private  String isearlywarning;

    /**
     * 是否显示预警词
     */
    private boolean isshowyjc;

    /**
     * 敏感词区分ID
     */
    private String SensitiveWordType_ID;

    /**
     *  标识位  用来做sql语句中的一些标示
     */
    private String flag;

    private String Keyword_ID;

    private String HotWord;

    private String c_hour;

    private String ReprintType;

    private String ReprintSourceArticle_ID;

    
    public String getMontime_bak() {
		return montime_bak;
	}

	public void setMontime_bak(String montime_bak) {
		this.montime_bak = montime_bak;
	}
    
    public String getRelate_Article_ID() {
        return Relate_Article_ID;
    }

    public void setRelate_Article_ID(String relate_Article_ID) {
        Relate_Article_ID = relate_Article_ID;
    }

    public String getReprintType() {
        return ReprintType;
    }

    public void setReprintType(String reprintType) {
        ReprintType = reprintType;
    }

    public String getReprintSourceArticle_ID() {
        return ReprintSourceArticle_ID;
    }

    public void setReprintSourceArticle_ID(String reprintSourceArticle_ID) {
        ReprintSourceArticle_ID = reprintSourceArticle_ID;
    }

    public String getC_hour() {
        return c_hour;
    }

    public void setC_hour(String c_hour) {
        this.c_hour = c_hour;
    }

    public String getKeyword_ID() {
        return Keyword_ID;
    }

    public void setKeyword_ID(String keyword_ID) {
        Keyword_ID = keyword_ID;
    }

    public String getHotWord() {
        return HotWord;
    }

    public void setHotWord(String hotWord) {
        HotWord = hotWord;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

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

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }
}
