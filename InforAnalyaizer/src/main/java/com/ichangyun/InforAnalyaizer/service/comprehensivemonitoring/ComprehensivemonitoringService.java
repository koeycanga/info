/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 * �����鱨����ϵͳ
 */
package com.ichangyun.InforAnalyaizer.service.comprehensivemonitoring;

import javax.servlet.http.HttpSession;

import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.ArticleInfoBean;

/**�ۺϼ���Ӧservice
 * @author renhao
 * 2018-11-19 10:05
 */
public interface ComprehensivemonitoringService {

	/**
	 * ��ѯ�ۺϼ������������
	 * @param ab 
	 * @return
	 */
	public int getArticleRowCount(ArticleInfoBean ab);

	
	/**
	 * ��ѯ�ۺϼ��������������
	 * @param ab
	 * @return
	 */
	public String getArticleJSON(ArticleInfoBean ab);


	/**
	 * ɾ������
	 * @param json Ҫɾ�������µ�id json������
	 * @param deletemode
	 * @return
	 */
	public boolean delarticle(String json, String userid, String deletemode);


	/**
	 * Ԥ������
	 * @param json
	 * @param session
	 * @return
	 */
	public boolean toyj(String json, HttpSession session);


	/**
	 * ����ϴβ�ѯʱ���µ�����ʱ��
	 * @param ab
	 * @return
	 */
	public String getSearchLaestRelsetime(ArticleInfoBean ab);


	/**
	 * �������������Ŀ��
	 * @param ab
	 * @return
	 */
	public int getlastestNews(ArticleInfoBean ab);


	/**
	 * �����������json������
	 * @param ab
	 * @return
	 */
	public String getSimContent(ArticleInfoBean ab);

}
