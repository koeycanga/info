/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.service.thematicmonitoring;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.ArticleInfoBean;
import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.CollectionBean;

/**
 * 专题监测Service
 * @author renhao
 * 2018-11-13 10:22
 */
public interface ThematicmonitoringService {

	/**
	 * 获得所有的方案
	 * @param session 
	 * @return
	 */
	public String getAllFA(HttpSession session);

	/**
	 * 保存新的方案
	 * @param planinfo_name  //方案名称
	 * @param jcc_json       //方案监测词JSON字面量
	 * @param session
	 * @param toDate 
	 * @param fromDate 
	 * @return true 成功   false 失败
	 */
	public boolean SaveNewfa(String planinfo_name, String jcc_json,String planinfo_removeWord, HttpSession session, String fromDate, String toDate);

	/**
	 * 判断方案名称是否已存在
	 * @param planinfo_name 方案名称
	 * @param session 
	 * @return true 已存在  false 不存在
	 */
	public boolean exist(String planinfo_name, HttpSession session);

	/**
	 * 删除方案
	 * @param planid 要删除的方案ID
	 * @return true 成功   false 失败
	 */
	public boolean delfa(String planid);

	/**
	 * 获得方案详细信息
	 * @param planid  方案ID
	 * @return  方案详细信息的JSON字面量
	 */
	public String getDetail(String planid);

	/**
	 * 判断除plan_id外 方案名称是否已存在
	 * @param plan_id   方案ID
	 * @param planinfo_name  方案名称
	 * @param session 
	 * @return true 已存在  false 不存在
	 */
	public boolean existwithID(String plan_id, String planinfo_name, HttpSession session);

	/**
	 * 修改方案
	 * @param plan_id 方案ID
	 * @param planinfo_name 方案名称
	 * @param jcc_json  方案监测词json字面量
	 * @param planinfo_removeWord  方案排除词
	 * @param session
	 * @param toDate 
	 * @param fromDate 
	 * @return  true 成功   false 失败
	 */
	public boolean updatefa(String plan_id, String planinfo_name, String jcc_json, String planinfo_removeWord,HttpSession session, String fromDate, String toDate);

	/**
	 * 获得要查询的文章总数
	 * @param ab 查询参数
	 * @return
	 */
	public int getArticleRowCount(ArticleInfoBean ab);

	/**
	 * 获得要查询的文章信息JSON字面量
	 * @param ab
	 * @return
	 */
	public String getArticleJSON(ArticleInfoBean ab);

	/**
	 * 预警文章
	 * @param json    参数
	 * @param session
	 * @return
	 */
	public boolean toyj(String json,HttpSession session);

	/**
	 * 删除文章
	 * @param json 参数
	 * @return
	 */
	public boolean delarticle(String json,String userid ,String deletemode);

	/**
	 * 获得相似文章信息的json字面量
	 * @param ab
	 * @return
	 */
	public String getSimContent(ArticleInfoBean ab);

	/**
	 * 获得收藏分类信息的json字面量
	 * @param user_ID
	 * @return
	 */
	public String getCollectionType(String user_ID);

	/**
	 * 添加文章到我的收藏
	 * @param cb
	 * @return
	 */
	public boolean conllect(CollectionBean cb);

	/**
	 * 获得情感属性环状图所需信息的json字面量
	 * @param list
	 * @return
	 */
	public String getQGSXJSON(List<ArticleInfoBean> list);

	/**
	 * 获得新消息条目数
	 * @param ab
	 * @return
	 */
	public int getlastestNews(ArticleInfoBean ab);

	/**
	 * 获得上次查询的文章的最新时间
	 * @param ab
	 * @return
	 */
	public String getSearchLaestRelsetime(ArticleInfoBean ab);

	/**
	 * 获得专题监测页面热词云相关信息
	 * @return
	 */
	public String getHotWord();

	/**
	 * 获得文章信息集合
	 * @param ab
	 * @return
	 */
	public List<ArticleInfoBean> getContentList(ArticleInfoBean ab);

	/**
	 * 获得事件脉络相关信息
	 * @param list
	 * @return
	 */
	public String getSJMLJSON(List<ArticleInfoBean> list);

	/**
	 * 获得媒体分布JSON字面量
	 * @param ab
	 * @param session 
	 * @return
	 */
	public String getMTFBData(ArticleInfoBean ab, HttpSession session);

	/**
	 * 获得发展趋势JSON字面量(双数时间段   如 00:00  02:00)
	 * @param ab
	 * @param flag 
	 * @param time_datas 
	 * @param session 
	 * @return
	 */
	public String getFZQS(ArticleInfoBean ab, String flag, String time_datas, HttpSession session);


	/**
	 * 获得发展趋势JSON字面量(单数时间段   如 23:00  01:00)
	 * @param ab
	 * @param string
	 * @param string2
	 * @param session
	 * @return
	 */
	public String getFZQS_DS(ArticleInfoBean ab, String string, String string2, HttpSession session);
	
	/**
	 * 获得传播途径JSON字面量
	 * @param ab
	 * @param session 
	 * @return
	 */
	public String getCBTHData(ArticleInfoBean ab, HttpSession session);


	/**
	 * 根据专题监测方案刷新数据库专题监测对应文章表
	 */
	public void FlashThematicmonitoringDB();

}
