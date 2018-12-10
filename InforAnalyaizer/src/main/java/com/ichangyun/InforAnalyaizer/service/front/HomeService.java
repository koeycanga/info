/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 *  �����鱨ϵͳ
 */
package com.ichangyun.InforAnalyaizer.service.front;

import javax.servlet.http.HttpSession;

import com.ichangyun.InforAnalyaizer.model.front.HotWordBean;
import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.ArticleInfoBean;

/**
 * ��ҳ��Ӧ��Service
 * @author renhao
 * 2018-11-16 10:18
 */


public interface HomeService {

	/**
	 * ����ȴ��Ƶ�json������
	 * @return
	 */
	public String getHotWord();

	/**
	 * �����ȴʻ��������ý���ϵĳ��ִ���
	 * @param hb
	 * @return
	 */
	public int getArticleCountByHotWord(HotWordBean hb);

	/**
	 * �����ȴʻ�����������Ϣ��json������
	 * @param hb
	 * @return
	 */
	public String getArticleByHotWord(HotWordBean hb);

	/**
	 * ����������µ�json������
	 * @param ab
	 * @return
	 */
	public String getSimContent(ArticleInfoBean ab);

	/**
	 * ���������Ϣ��json������
	 * @return
	 */
	public String getNewestDatas();

	/**
	 * ���Ԥ����Ϣtop10��json������
	 * @param session 
	 * @return
	 */
	public String getWarnDatas(HttpSession session);

	/**
	 * ��ø�����Ϣtop10��json������
	 * @return
	 */
	public String getNegativeDatas();

	public String getJCMsg(HttpSession session);

}
