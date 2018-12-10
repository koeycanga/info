/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 * �����鱨����ϵͳ
 */
package com.ichangyun.InforAnalyaizer.service.front;

import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.ArticleInfoBean;

/**
 * ����ҳ��Ӧservice
 * @author renhao
 * 2018-11-19 17:02
 */
public interface DetailsPageService {

	/**
	 * ��������ID���������Ϣ
	 * @param article_id  ����id
	 * @param userid
	 * @return
	 */
	public String getArticleByID(String article_id, String userid);

	/**
	 * ��������ID������µ�ý�屨����Ϣ
	 * @param article_id
	 * @return
	 */
	public String getBDArticleByID(String article_id);

	/**
	 * �����������
	 * @return
	 */
	public String getSimContent(ArticleInfoBean ab);
}
