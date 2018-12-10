/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 *  �����鱨ϵͳ
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
	 * ����ȴ���Ϣ����
	 * @return
	 */
	public List<HotWordBean> getHotWord();

	/**
	 * �������ĳ�ȴʵ�������Ϣ����
	 * @param hb
	 * @return
	 */
	public int getArticleCountByHotWord(HotWordBean hb);

	/**
	 * �������ĳ�ȴʵ�������Ϣ����
	 * @param hb
	 * @return
	 */
	public List<ArticleInfoBean> getArticleByHotWord(HotWordBean hb);

	/**
	 * �������������Ϣ����
	 * @param ab
	 * @return
	 */
	public List<ArticleInfoBean> getSimContent(ArticleInfoBean ab);

	/**
	 * ���������Ϣ����
	 * @return
	 */
	public List<ArticleInfoBean> getNewestDatas();

	/**
	 * ���Ԥ����Ϣtop10����
	 * @param userid 
	 * @return
	 */
	public List<ArticleInfoBean> getWarnDatas(@Param("userid")String userid);

	/**
	 * ��ø�����Ϣtop10����
	 * @return
	 */
	public List<ArticleInfoBean> getNegativeDatas();

	public Map getJCMsg(@Param("today")String today, @Param("yesterday")String yesterday, @Param("userid")String userid);

}
