/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.mapper.thematicmonitoring;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ichangyun.InforAnalyaizer.model.front.HotWordBean;
import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.ArticleInfoBean;
import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.CollectionBean;
import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.ThematicmonitoringBean;

/**
 * 专题监测Mapper
 * @author renhao
 * 2018-11-13 10:24
 */
public interface ThematicmonitoringMapper {

	/**
	 * 获得所有方案
	 * @param userid 
	 * @return
	 */
	public List<ThematicmonitoringBean> getAllFA(@Param("userid")String userid);

	/**
	 * 判断方案名称是否已存在
	 * @param planinfo_name
	 * @param userid 
	 * @return
	 */
	public int exist( @Param("param") String planinfo_name, @Param("userid")String userid);

	/**
	 * 保存方案
	 * @param tb
	 */
	public void SaveNewfa(ThematicmonitoringBean tb);

	/**
	 * 保存方案详细信息
	 * @param list
	 */
	public void SaveNewfaDetail(List<ThematicmonitoringBean> list);

	/**
	 * 删除方案
	 * @param planid
	 */
	public void delfa(@Param("param") String planid);

	/**
	 * 删除方案详细信息
	 * @param planid
	 */
	public void delfadetail(@Param("param") String planid);

	/**
	 * 获得方案详细信息
	 * @param planid  方案id
	 * @return
	 */
	public List<ThematicmonitoringBean> getDetail(@Param("param")String planid);

	/**
	 * 判断除plan_id外方案名称是否存在
	 * @param plan_id
	 * @param planinfo_name
	 * @param string 
	 * @return
	 */
	public int existwithID(@Param("p1")String plan_id, @Param("p2")String planinfo_name,@Param("userid")String userid);

	/**
	 * 获得文章总数(不合并相似文章)
	 * @param ab 查询参数
	 * @return
	 */
	public int getArticleRowCount(ArticleInfoBean ab);

	/**
	 * 获得文章信息列表(不合并相似文章)
	 * @param ab 查询参数
	 * @return
	 */
	public List<ArticleInfoBean> getArticle(ArticleInfoBean ab);

	/**
	 * 预警文章
	 * @param list
	 * @param userid
	 */
	public void toyj(@Param("list")List<String> list,@Param("userid")String userid);

	/**
	 * 删除文章
	 * @param list 要删除的文章id集合
	 */
	public void delarticle(@Param("list")List<String> list,@Param("userid")String userid,@Param("deletemode")String deletemode);

	/**
	 * 获得相似文章
	 * @param ab
	 * @return
	 */
	public List<ArticleInfoBean> getSimContent(ArticleInfoBean ab);

	/**
	 * 获得我的收藏分类
	 * @param user_ID
	 * @return
	 */
	public List<CollectionBean> getCollectionType(@Param("param")String user_ID);

	/**
	 * 获得下一个收藏分类排位顺序
	 * @param cb
	 * @return
	 */
	public int getNextDisplayorder(CollectionBean cb);

	/**
	 * 添加文章到我的收藏
	 * @param cb
	 */
	public void addnewconllect(CollectionBean cb);

	/**
	 * 获得我的收藏中文章数量
	 * @param cb
	 * @return
	 */
	public int getConllectCount(CollectionBean cb);

	/**
	 * 获得内容分析页面所需信息
	 * @param ab
	 * @return
	 */
	public List<ArticleInfoBean> getContentList(ArticleInfoBean ab);

	/**
	 * 获得最新消息条目数
	 * @param ab
	 * @return
	 */
	public int getlastestNews(ArticleInfoBean ab);

	/**
	 * 获得最近一次查询文章的最新文章发布时间
	 * @param ab
	 * @return
	 */
	public String getSearchLaestRelsetime(ArticleInfoBean ab);

	/**
	 * 查询文章总数(合并相似文章)
	 * @param ab
	 * @return
	 */
	public int getArticleRowCountWithHB(ArticleInfoBean ab);

	/**
	 * 查询文章(合并相似文章)
	 * @param ab
	 * @return
	 */
	public List<ArticleInfoBean> getArticleWithHB(ArticleInfoBean ab);

	/**
	 * 获得专题监测页面热词云信息
	 * @return
	 */
	public List<HotWordBean> getHotWord();

	/**
	 * 根据方案ID和用户ID获得方案详细信息
	 * @param pl_id
	 * @param userid
	 * @return
	 */
	public List<ThematicmonitoringBean> getFAByIdAndUser(@Param("pl_id")String pl_id, @Param("userid")String userid);

	/**
	 * 获得符合专题监测规则的文章总数
	 * @param sql
	 * @return
	 */
	public int getArticleRowCountWithPlanInfo(@Param("sql")String sql);

	/**
	 * 获得符合专题监测规则的文章集合
	 * @param sql
	 * @param l_pre
	 * @param rowSize
	 * @param sql_m1
	 * @param sql_m2
	 * @param sql_m3
	 * @return
	 */
	public List<ThematicmonitoringBean> getArticleWithPlanInfo(@Param("sql")String sql, @Param("l_pre")int l_pre, @Param("rowSize")int rowSize, @Param("sql_m1")String sql_m1, @Param("sql_m2")String sql_m2, @Param("sql_m3")String sql_m3);

	/**
	 * 添加符合符合专题监测方案的文章到监测表
	 * @param list
	 */
	public void insertArtileWithPlanInfoBatch(List<ThematicmonitoringBean> list);

	/**
	 * 根据方案ID删除文章监测表中的相关文章
	 * @param pl_id
	 */
	public void delArticleWithPlanInfo(@Param("pl_id")String pl_id);


	//public int getPlanInfoWordUseCount(@Param("sql")String sql,@Param("Article_ID")String Article_ID);

	/**
	 * 更新方案
	 * @param plan_id
	 * @param planinfo_name
	 * @param fromDate
	 * @param toDate
	 * @param num  组合数
	 */
	public void updatefa(@Param("plan_id")String plan_id, @Param("planinfo_name")String planinfo_name, @Param("fromDate")String fromDate, @Param("toDate")String toDate, @Param("num")int num,@Param("userid")String userid);

}
