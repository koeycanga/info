/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 * �����鱨����ϵͳ
 */
package com.ichangyun.InforAnalyaizer.service.thematicmonitoring;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.ArticleInfoBean;
import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.CollectionBean;

/**
 * ר����Service
 * @author renhao
 * 2018-11-13 10:22
 */
public interface ThematicmonitoringService {

	/**
	 * ������еķ���
	 * @param session 
	 * @return
	 */
	public String getAllFA(HttpSession session);

	/**
	 * �����µķ���
	 * @param planinfo_name  //��������
	 * @param jcc_json       //��������JSON������
	 * @param session
	 * @param toDate 
	 * @param fromDate 
	 * @return true �ɹ�   false ʧ��
	 */
	public boolean SaveNewfa(String planinfo_name, String jcc_json,String planinfo_removeWord, HttpSession session, String fromDate, String toDate);

	/**
	 * �жϷ��������Ƿ��Ѵ���
	 * @param planinfo_name ��������
	 * @param session 
	 * @return true �Ѵ���  false ������
	 */
	public boolean exist(String planinfo_name, HttpSession session);

	/**
	 * ɾ������
	 * @param planid Ҫɾ���ķ���ID
	 * @return true �ɹ�   false ʧ��
	 */
	public boolean delfa(String planid);

	/**
	 * ��÷�����ϸ��Ϣ
	 * @param planid  ����ID
	 * @return  ������ϸ��Ϣ��JSON������
	 */
	public String getDetail(String planid);

	/**
	 * �жϳ�plan_id�� ���������Ƿ��Ѵ���
	 * @param plan_id   ����ID
	 * @param planinfo_name  ��������
	 * @param session 
	 * @return true �Ѵ���  false ������
	 */
	public boolean existwithID(String plan_id, String planinfo_name, HttpSession session);

	/**
	 * �޸ķ���
	 * @param plan_id ����ID
	 * @param planinfo_name ��������
	 * @param jcc_json  ��������json������
	 * @param planinfo_removeWord  �����ų���
	 * @param session
	 * @param toDate 
	 * @param fromDate 
	 * @return  true �ɹ�   false ʧ��
	 */
	public boolean updatefa(String plan_id, String planinfo_name, String jcc_json, String planinfo_removeWord,HttpSession session, String fromDate, String toDate);

	/**
	 * ���Ҫ��ѯ����������
	 * @param ab ��ѯ����
	 * @return
	 */
	public int getArticleRowCount(ArticleInfoBean ab);

	/**
	 * ���Ҫ��ѯ��������ϢJSON������
	 * @param ab
	 * @return
	 */
	public String getArticleJSON(ArticleInfoBean ab);

	/**
	 * Ԥ������
	 * @param json    ����
	 * @param session
	 * @return
	 */
	public boolean toyj(String json,HttpSession session);

	/**
	 * ɾ������
	 * @param json ����
	 * @return
	 */
	public boolean delarticle(String json,String userid ,String deletemode);

	/**
	 * �������������Ϣ��json������
	 * @param ab
	 * @return
	 */
	public String getSimContent(ArticleInfoBean ab);

	/**
	 * ����ղط�����Ϣ��json������
	 * @param user_ID
	 * @return
	 */
	public String getCollectionType(String user_ID);

	/**
	 * ������µ��ҵ��ղ�
	 * @param cb
	 * @return
	 */
	public boolean conllect(CollectionBean cb);

	/**
	 * ���������Ի�״ͼ������Ϣ��json������
	 * @param list
	 * @return
	 */
	public String getQGSXJSON(List<ArticleInfoBean> list);

	/**
	 * �������Ϣ��Ŀ��
	 * @param ab
	 * @return
	 */
	public int getlastestNews(ArticleInfoBean ab);

	/**
	 * ����ϴβ�ѯ�����µ�����ʱ��
	 * @param ab
	 * @return
	 */
	public String getSearchLaestRelsetime(ArticleInfoBean ab);

	/**
	 * ���ר����ҳ���ȴ��������Ϣ
	 * @return
	 */
	public String getHotWord();

	/**
	 * ���������Ϣ����
	 * @param ab
	 * @return
	 */
	public List<ArticleInfoBean> getContentList(ArticleInfoBean ab);

	/**
	 * ����¼����������Ϣ
	 * @param list
	 * @return
	 */
	public String getSJMLJSON(List<ArticleInfoBean> list);

	/**
	 * ���ý��ֲ�JSON������
	 * @param ab
	 * @param session 
	 * @return
	 */
	public String getMTFBData(ArticleInfoBean ab, HttpSession session);

	/**
	 * ��÷�չ����JSON������(˫��ʱ���   �� 00:00  02:00)
	 * @param ab
	 * @param flag 
	 * @param time_datas 
	 * @param session 
	 * @return
	 */
	public String getFZQS(ArticleInfoBean ab, String flag, String time_datas, HttpSession session);


	/**
	 * ��÷�չ����JSON������(����ʱ���   �� 23:00  01:00)
	 * @param ab
	 * @param string
	 * @param string2
	 * @param session
	 * @return
	 */
	public String getFZQS_DS(ArticleInfoBean ab, String string, String string2, HttpSession session);
	
	/**
	 * ��ô���;��JSON������
	 * @param ab
	 * @param session 
	 * @return
	 */
	public String getCBTHData(ArticleInfoBean ab, HttpSession session);


	/**
	 * ����ר���ⷽ��ˢ�����ݿ�ר�����Ӧ���±�
	 */
	public void FlashThematicmonitoringDB();

}
