/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 * �����鱨����ϵͳ
 */
package com.ichangyun.InforAnalyaizer.service.front;
/**
 * ����ҳ��Ӧservice
 * @author renhao
 * 2018-11-19 17:02
 */
public interface DetailsPageService {

	/**
	 * ��������ID���������Ϣ
	 * @param article_id  ����id
	 * @return
	 */
	public String getArticleByID(String article_id);

	/**
	 * ��������ID������µ�ý�屨����Ϣ
	 * @param article_id
	 * @return
	 */
	public String getBDArticleByID(String article_id);

}
