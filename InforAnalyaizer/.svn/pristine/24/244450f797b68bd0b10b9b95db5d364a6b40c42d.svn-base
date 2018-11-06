package com.ichangyun.InforAnalyaizer.mapper.classificationfilterwords;


import java.util.List;
import java.util.Map;

import com.ichangyun.InforAnalyaizer.model.classificationfilterwords.ClassificationFilterwords;
import com.ichangyun.InforAnalyaizer.model.classificationfilterwords.ClassificationFilterwordsWithBLOBs;
import com.ichangyun.InforAnalyaizer.model.classificationfilterwords.FilterWordsVo;

public interface ClassificationFilterwordsMapper {
    List<String> queryParents();

	int deleteByPrimaryKey(String classificationId);

    int insert(ClassificationFilterwordsWithBLOBs record);

    int insertSelective(ClassificationFilterwordsWithBLOBs record);

    ClassificationFilterwordsWithBLOBs selectByPrimaryKey(String classificationId);

    int updateByPrimaryKeySelective(ClassificationFilterwordsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ClassificationFilterwordsWithBLOBs record);

    int updateByPrimaryKey(ClassificationFilterwords record);
    
    List<FilterWordsVo> selectByFwVo(Map<String, Object> vo);

	int queryCount(Map<String, Object> key);

	FilterWordsVo queryOne(String classificationId);

	void updateFwVo(FilterWordsVo vo);

	List<FilterWordsVo> queryChild(String parent_Classification_ID);
}