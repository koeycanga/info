/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.service.front;

import javax.servlet.http.HttpSession;

import com.ichangyun.InforAnalyaizer.model.front.HotWordBean;
import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.ArticleInfoBean;

/**
 * 首页对应的Service
 * @author renhao
 * 2018-11-16 10:18
 */


public interface HomeService {

	/**
	 * 获得热词云的json字面量
	 * @return
	 */
	public String getHotWord();

	/**
	 * 根据热词获得文章在媒体上的出现次数
	 * @param hb
	 * @return
	 */
	public int getArticleCountByHotWord(HotWordBean hb);

	/**
	 * 根据热词获得相关文章信息的json字面量
	 * @param hb
	 * @return
	 */
	public String getArticleByHotWord(HotWordBean hb);

	/**
	 * 获得相似文章的json字面量
	 * @param ab
	 * @return
	 */
	public String getSimContent(ArticleInfoBean ab);


	
	/**
	 * 获得首页监测相关的信息
	 * @param session
	 * @return
	 */
	public String getJCMsg(HttpSession session);

	/**
	 * 获得首页top10相关文章信息
	 * @param session
	 * @return
	 */
	public String[] getTopTenDatas(HttpSession session);

	/**
	 * 获得即将发生相关信息
	 * @return
	 */
	public String getJJFSWord();

	public String getHotWordFromDetial(String flag);

}
