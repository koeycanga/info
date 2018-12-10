package com.ichangyun.InforAnalyaizer.mapper.comprehensivemonitoring;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.ArticleInfoBean;

/**
 * �ۺϼ���Ӧmapper
 * @author renhao
 * 2018-11-19 10:18
 */
public interface ComprehensivemonitoringMapper {

	/**
	 * ��ѯ�ۺϼ������������(���ϲ���������)
	 * @param ab
	 * @return
	 */
	public int getArticleRowCount(ArticleInfoBean ab);

	
	
	/**
	 * ��ѯ�ۺϼ��ҳ��������������(���ϲ���������)
	 * @param ab
	 * @return
	 */
	public List<ArticleInfoBean> getArticle(ArticleInfoBean ab);



	/**
	 * ɾ������
	 * @param list
	 */
	public void delarticle(@Param("list")List<String> list,@Param("userid")String userid,@Param("deletemode")String deletemode);



	/**
	 * Ԥ������
	 * @param list
	 * @param userid
	 */
	public void toyj(@Param("list")List<String> list, @Param("userid")String userid);


    /**
     * ����ϴβ�ѯ����ʱ���µ����·���ʱ��
     * @param ab
     * @return
     */
	public String getSearchLaestRelsetime(ArticleInfoBean ab);



	/**
	 * ���������Ϣ��Ŀ��
	 * @param ab
	 * @return
	 */
	public int getlastestNews(ArticleInfoBean ab);


    /**
     * ����������� 
     * @param ab
     * @return
     */
	public List<ArticleInfoBean> getSimContent(ArticleInfoBean ab);


    /**
     * ��ѯ�ۺϼ������������(�ϲ���������)
     * @param ab
     * @return
     */
	public int getArticleRowWithHBCount(ArticleInfoBean ab);


	/**
     * ��ѯ�ۺϼ������(�ϲ���������)
     * @param ab
     * @return
     */
	public List<ArticleInfoBean> getArticleWithHB(ArticleInfoBean ab);

}
