/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.mapper.comprehensivemonitoring;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.ArticleInfoBean;

/**
 * 综合监测对应mapper
 * @author renhao
 * 2018-11-19 10:18
 */
public interface ComprehensivemonitoringMapper {

	/**
	 * 查询综合监测文章总数量(不合并相似文章)
	 * @param ab
	 * @return
	 */
	public int getArticleRowCount(ArticleInfoBean ab);

	
	
	/**
	 * 查询综合监测页面所需文章数据(不合并相似文章)
	 * @param ab
	 * @return
	 */
	public List<ArticleInfoBean> getArticle(ArticleInfoBean ab);



	/**
	 * 删除文章
	 * @param list
	 */
	public void delarticle(@Param("list")List<String> list,@Param("userid")String userid,@Param("deletemode")String deletemode);



	/**
	 * 预警文章
	 * @param list
	 * @param userid
	 */
	public void toyj(@Param("list")List<String> list, @Param("userid")String userid);


    /**
     * 获得上次查询文章时最新的文章发布时间
     * @param ab
     * @return
     */
	public String getSearchLaestRelsetime(ArticleInfoBean ab);



	/**
	 * 获得最新消息条目数
	 * @param ab
	 * @return
	 */
	public int getlastestNews(ArticleInfoBean ab);


    /**
     * 获得相似文章 
     * @param ab
     * @return
     */
	public List<ArticleInfoBean> getSimContent(ArticleInfoBean ab);


    /**
     * 查询综合监测文章总数量(合并相似文章)
     * @param ab
     * @return
     */
	public int getArticleRowWithHBCount(ArticleInfoBean ab);


	/**
     * 查询综合监测文章(合并相似文章)
     * @param ab
     * @return
     */
	public List<ArticleInfoBean> getArticleWithHB(ArticleInfoBean ab);

}
