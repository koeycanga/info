/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.mapper.front;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.ArticleInfoBean;

/**
 * 详情页对应Mapper
 * @author renhao
 * 2018-11-19 17:05
 */
public interface DetailsPageMapper {

    /**
     * 根据文章ID获得文章信息
     * @param article_id
     * @param userid
     * @return
     */
    public ArticleInfoBean getArticleByID(@Param("article_id") String article_id,@Param("userid") String userid);

    /**
     * 根据文章ID获得文章在媒体上的报道情况
     * @param article_id
     * @return
     */
    public List<ArticleInfoBean> getBDArticleByID(@Param("article_id")String article_id,@Param("userid")String userid);

    /**
     * 获得相似文章
     * @return
     */
    public List<ArticleInfoBean> getSimContent(ArticleInfoBean ab);

    /**
     * 获得相关文章信息
     * @param article_id
     * @param userid
     * @return
     */
    public List<ArticleInfoBean> getRelateDataByID(@Param("article_id")String article_id,@Param("userid")String userid);

    /**
     * 根据文章ID获得其所在的分类体系名称路径
     * @param article_id
     * @return
     */
	public List<String> getClassificationPathById(@Param("article_id")String article_id);
}
