/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.mapper.front;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ichangyun.InforAnalyaizer.model.front.HotWordBean;
import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.ArticleInfoBean;

/**
 * @author renhao
 * 2018-11-16 10:20
 */
public interface HomeMapper {

	/**
	 * 获得热词信息集合
	 * @return
	 */
	public List<HotWordBean> getHotWord();

	/**
	 * 获得属于某热词的文章信息总数
	 * @param hb
	 * @return
	 */
	public int getArticleCountByHotWord(HotWordBean hb);

	/**
	 * 获得属于某热词的文章信息集合
	 * @param hb
	 * @return
	 */
	public List<ArticleInfoBean> getArticleByHotWord(HotWordBean hb);

	/**
	 * 获得相似文章信息集合
	 * @param ab
	 * @return
	 */
	public List<ArticleInfoBean> getSimContent(ArticleInfoBean ab);

	/**
	 * 获得最新消息集合
	 * @return
	 */
	public List<ArticleInfoBean> getNewestDatas();

	/**
	 * 获得预警信息top10集合
	 * @param userid 
	 * @return
	 */
	public List<ArticleInfoBean> getWarnDatas(@Param("userid")String userid);

	/**
	 * 获得负面信息top10集合
	 * @return
	 */
	public List<ArticleInfoBean> getNegativeDatas();

	public Map getJCMsg(@Param("today")String today, @Param("yesterday")String yesterday, @Param("userid")String userid);

}
