package com.ichangyun.InforAnalyaizer.mapper.classificationfilterwords;


import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ichangyun.InforAnalyaizer.model.classificationfilterwords.ClassificationFilterwordsWithBLOBs;
import com.ichangyun.InforAnalyaizer.model.classificationfilterwords.FilterWordsVo;

public interface ClassificationFilterwordsMapper {
    List<String> queryParents();		//查询所有父节点

	int deleteByPrimaryKey(String classificationId);		//通过id删除节点的过滤词信息


    int insertSelective(ClassificationFilterwordsWithBLOBs record);//按条件添加节点过滤词信息

    ClassificationFilterwordsWithBLOBs selectByPrimaryKey(String classificationId);//查询节点信息过滤词信息
    
    List<FilterWordsVo> selectByFwVo(Map<String, Object> vo);//条件查询所有节点过滤词信息

	int queryCount(Map<String, Object> key);		//查询节点总数

	FilterWordsVo queryOne(String classificationId);	//根据id查询单个节点

	void updateFwVo(FilterWordsVo vo);					//编辑节点过滤词信息

	List<FilterWordsVo> queryChild(FilterWordsVo vo);//查询当前节点的子节点
	
	List<FilterWordsVo> queryById(String ids);		//通过id查询节点

	List<FilterWordsVo> queryByName(String names);			//通过名称查询节点

	void insertMany(List<FilterWordsVo> createVo) throws DataAccessException;//批量新增

	void updateMany(List<FilterWordsVo> vos) throws DataAccessException;	//批量更新
}