/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.service.front;

import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.ArticleInfoBean;

/**
 * 详情页对应service
 * @author renhao
 * 2018-11-19 17:02
 */
public interface DetailsPageService {

	/**
	 * 根据文章ID获得文章信息
	 * @param article_id  文章id
	 * @param userid
	 * @return
	 */
	public String getArticleByID(String article_id, String userid);

	/**
	 * 根据文章ID获得文章的媒体报道信息
	 * @param article_id
	 * @return
	 */
	public String getBDArticleByID(String article_id,String userid);

	/**
	 * 获得相似文章
	 * @return
	 */
	public String getSimContent(ArticleInfoBean ab);
}
