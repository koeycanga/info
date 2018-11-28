/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 * �����鱨����ϵͳ
 */
package com.ichangyun.InforAnalyaizer.mapper.front;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.ArticleInfoBean;

/**
 * ����ҳ��ӦMapper
 * @author renhao
 * 2018-11-19 17:05
 */
public interface DetailsPageMapper {

	/**
	 * ��������ID���������Ϣ
	 * @param article_id
	 * @return
	 */
	public ArticleInfoBean getArticleByID(@Param("article_id")String article_id);

	/**
	 * ��������ID���������ý���ϵı������
	 * @param article_id
	 * @return
	 */
	public List<ArticleInfoBean> getBDArticleByID(@Param("article_id")String article_id);

}
