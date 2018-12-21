/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 * �����鱨����ϵͳ
 */
package com.ichangyun.InforAnalyaizer.mapper.thematicmonitoring;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ichangyun.InforAnalyaizer.model.front.HotWordBean;
import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.ArticleInfoBean;
import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.CollectionBean;
import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.ThematicmonitoringBean;

/**
 * ר����Mapper
 * @author renhao
 * 2018-11-13 10:24
 */
public interface ThematicmonitoringMapper {

	/**
	 * ������з���
	 * @param userid 
	 * @return
	 */
	public List<ThematicmonitoringBean> getAllFA(@Param("userid")String userid);

	/**
	 * �жϷ��������Ƿ��Ѵ���
	 * @param planinfo_name
	 * @param userid 
	 * @return
	 */
	public int exist( @Param("param") String planinfo_name, @Param("userid")String userid);

	/**
	 * ���淽��
	 * @param tb
	 */
	public void SaveNewfa(ThematicmonitoringBean tb);

	/**
	 * ���淽����ϸ��Ϣ
	 * @param list
	 */
	public void SaveNewfaDetail(List<ThematicmonitoringBean> list);

	/**
	 * ɾ������
	 * @param planid
	 */
	public void delfa(@Param("param") String planid);

	/**
	 * ɾ��������ϸ��Ϣ
	 * @param planid
	 */
	public void delfadetail(@Param("param") String planid);

	/**
	 * ��÷�����ϸ��Ϣ
	 * @param planid  ����id
	 * @return
	 */
	public List<ThematicmonitoringBean> getDetail(@Param("param")String planid);

	/**
	 * �жϳ�plan_id�ⷽ�������Ƿ����
	 * @param plan_id
	 * @param planinfo_name
	 * @param string 
	 * @return
	 */
	public int existwithID(@Param("p1")String plan_id, @Param("p2")String planinfo_name,@Param("userid")String userid);

	/**
	 * �����������(���ϲ���������)
	 * @param ab ��ѯ����
	 * @return
	 */
	public int getArticleRowCount(ArticleInfoBean ab);

	/**
	 * ���������Ϣ�б�(���ϲ���������)
	 * @param ab ��ѯ����
	 * @return
	 */
	public List<ArticleInfoBean> getArticle(ArticleInfoBean ab);

	/**
	 * Ԥ������
	 * @param list
	 * @param userid
	 */
	public void toyj(@Param("list")List<String> list,@Param("userid")String userid);

	/**
	 * ɾ������
	 * @param list Ҫɾ��������id����
	 */
	public void delarticle(@Param("list")List<String> list,@Param("userid")String userid,@Param("deletemode")String deletemode);

	/**
	 * �����������
	 * @param ab
	 * @return
	 */
	public List<ArticleInfoBean> getSimContent(ArticleInfoBean ab);

	/**
	 * ����ҵ��ղط���
	 * @param user_ID
	 * @return
	 */
	public List<CollectionBean> getCollectionType(@Param("param")String user_ID);

	/**
	 * �����һ���ղط�����λ˳��
	 * @param cb
	 * @return
	 */
	public int getNextDisplayorder(CollectionBean cb);

	/**
	 * ������µ��ҵ��ղ�
	 * @param cb
	 */
	public void addnewconllect(CollectionBean cb);

	/**
	 * ����ҵ��ղ�����������
	 * @param cb
	 * @return
	 */
	public int getConllectCount(CollectionBean cb);

	/**
	 * ������ݷ���ҳ��������Ϣ
	 * @param ab
	 * @return
	 */
	public List<ArticleInfoBean> getContentList(ArticleInfoBean ab);

	/**
	 * ���������Ϣ��Ŀ��
	 * @param ab
	 * @return
	 */
	public int getlastestNews(ArticleInfoBean ab);

	/**
	 * ������һ�β�ѯ���µ��������·���ʱ��
	 * @param ab
	 * @return
	 */
	public String getSearchLaestRelsetime(ArticleInfoBean ab);

	/**
	 * ��ѯ��������(�ϲ���������)
	 * @param ab
	 * @return
	 */
	public int getArticleRowCountWithHB(ArticleInfoBean ab);

	/**
	 * ��ѯ����(�ϲ���������)
	 * @param ab
	 * @return
	 */
	public List<ArticleInfoBean> getArticleWithHB(ArticleInfoBean ab);

	/**
	 * ���ר����ҳ���ȴ�����Ϣ
	 * @return
	 */
	public List<HotWordBean> getHotWord();

	/**
	 * ���ݷ���ID���û�ID��÷�����ϸ��Ϣ
	 * @param pl_id
	 * @param userid
	 * @return
	 */
	public List<ThematicmonitoringBean> getFAByIdAndUser(@Param("pl_id")String pl_id, @Param("userid")String userid);

	/**
	 * ��÷���ר����������������
	 * @param sql
	 * @return
	 */
	public int getArticleRowCountWithPlanInfo(@Param("sql")String sql);

	/**
	 * ��÷���ר�����������¼���
	 * @param sql
	 * @param l_pre
	 * @param rowSize
	 * @param sql_m1
	 * @param sql_m2
	 * @param sql_m3
	 * @return
	 */
	public List<ThematicmonitoringBean> getArticleWithPlanInfo(@Param("sql")String sql, @Param("l_pre")int l_pre, @Param("rowSize")int rowSize, @Param("sql_m1")String sql_m1, @Param("sql_m2")String sql_m2, @Param("sql_m3")String sql_m3);

	/**
	 * ��ӷ��Ϸ���ר���ⷽ�������µ�����
	 * @param list
	 */
	public void insertArtileWithPlanInfoBatch(List<ThematicmonitoringBean> list);

	/**
	 * ���ݷ���IDɾ�����¼����е��������
	 * @param pl_id
	 */
	public void delArticleWithPlanInfo(@Param("pl_id")String pl_id);


	//public int getPlanInfoWordUseCount(@Param("sql")String sql,@Param("Article_ID")String Article_ID);

	/**
	 * ���·���
	 * @param plan_id
	 * @param planinfo_name
	 * @param fromDate
	 * @param toDate
	 * @param num  �����
	 */
	public void updatefa(@Param("plan_id")String plan_id, @Param("planinfo_name")String planinfo_name, @Param("fromDate")String fromDate, @Param("toDate")String toDate, @Param("num")int num,@Param("userid")String userid);

	public List<ArticleInfoBean> getMTFBData(ArticleInfoBean ab);

	public List<ArticleInfoBean> getFZQSByHour(ArticleInfoBean ab);

	public List<ArticleInfoBean> getFZQSByDay(ArticleInfoBean ab);

	public List<ArticleInfoBean> getCBTHData(ArticleInfoBean ab);

	public List<ArticleInfoBean> getFZQSByHourDS(ArticleInfoBean ab);

	public void delAllArticleWithPlanInfo();

	public List<ThematicmonitoringBean> getAllFa();

}
