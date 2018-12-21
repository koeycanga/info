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
	 * �����ҳ�����ص���Ϣ
	 * @param session
	 * @return
	 */
	public String getJCMsg(HttpSession session);

	/**
	 * �����ҳtop10���������Ϣ
	 * @param session
	 * @return
	 */
	public String[] getTopTenDatas(HttpSession session);

	/**
	 * ��ü������������Ϣ
	 * @return
	 */
	public String getJJFSWord();

	
	/**
	 * �����ҳ���ȴ�����ϢJSON������
	 * @param flag
	 * @return
	 */
	public String getHotWordFromDetial(String flag);

}
