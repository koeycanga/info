/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.mapper.classificationfilterwords;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.ichangyun.InforAnalyaizer.model.classification.ClassificationInfoBean;
import com.ichangyun.InforAnalyaizer.model.classificationfilterwords.ClassificationFilterwordsWithBLOBs;
import com.ichangyun.InforAnalyaizer.model.classificationfilterwords.FilterWordsVo;

public interface ClassificationFilterwordsMapper {
    // 查询所有父节点
    List<String> queryParents();
    // 通过id删除节点的过滤词信息
    int deleteByPrimaryKey(String classificationId);
    // 按条件添加节点过滤词信息
    int insertSelective(ClassificationFilterwordsWithBLOBs record);
    // 查询节点信息过滤词信息
    ClassificationFilterwordsWithBLOBs selectByPrimaryKey(String classificationId);
    // 条件查询所有节点过滤词信息
    List<FilterWordsVo> selectByFwVo(Map<String, Object> vo);
    // 查询节点总数
    int queryCount(Map<String, Object> key);
    // 根据id查询单个节点
    FilterWordsVo queryOne(String classificationId);
    // 编辑节点过滤词信息
    void updateFwVo(FilterWordsVo vo);
    // 查询当前节点的子节点
    List<FilterWordsVo> queryChild(FilterWordsVo vo);
    // 通过id查询节点
    List<FilterWordsVo> queryById(String ids);
    // 通过名称查询节点
    List<FilterWordsVo> queryByName(String names);
    //批量新增
    void insertMany(@Param("list")List<FilterWordsVo> createVo, @Param("userid")String userid) throws DataAccessException;
    //批量更新
    void updateMany(@Param("list")List<FilterWordsVo> vos,  @Param("userid")String userid) throws DataAccessException;
    //获得所有的分类体系名称
    List<ClassificationInfoBean> getAllClassificationNames();
    //获得某个节点所有的叶子节点
	List<String> getChildrenIDS(@Param("id")String id);
}