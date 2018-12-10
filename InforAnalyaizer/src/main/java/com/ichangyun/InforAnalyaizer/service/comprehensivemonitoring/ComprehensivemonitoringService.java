/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.service.comprehensivemonitoring;

import javax.servlet.http.HttpSession;

import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.ArticleInfoBean;

/**综合监测对应service
 * @author renhao
 * 2018-11-19 10:05
 */
public interface ComprehensivemonitoringService {

	/**
	 * 查询综合监测文章总数量
	 * @param ab 
	 * @return
	 */
	public int getArticleRowCount(ArticleInfoBean ab);

	
	/**
	 * 查询综合监测所需文章数据
	 * @param ab
	 * @return
	 */
	public String getArticleJSON(ArticleInfoBean ab);


	/**
	 * 删除文章
	 * @param json 要删除的文章的id json字面量
	 * @param deletemode
	 * @return
	 */
	public boolean delarticle(String json, String userid, String deletemode);


	/**
	 * 预警文章
	 * @param json
	 * @param session
	 * @return
	 */
	public boolean toyj(String json, HttpSession session);


	/**
	 * 获得上次查询时最新的文章时间
	 * @param ab
	 * @return
	 */
	public String getSearchLaestRelsetime(ArticleInfoBean ab);


	/**
	 * 获得最新文章条目数
	 * @param ab
	 * @return
	 */
	public int getlastestNews(ArticleInfoBean ab);


	/**
	 * 获得相似文章json字面量
	 * @param ab
	 * @return
	 */
	public String getSimContent(ArticleInfoBean ab);

}
