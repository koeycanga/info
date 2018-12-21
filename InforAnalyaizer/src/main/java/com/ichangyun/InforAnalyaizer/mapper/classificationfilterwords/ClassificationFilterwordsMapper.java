/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 *  �����鱨ϵͳ
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
    List<String> queryParents();		//��ѯ���и��ڵ�

	int deleteByPrimaryKey(String classificationId);		//ͨ��idɾ���ڵ�Ĺ��˴���Ϣ

    int insertSelective(ClassificationFilterwordsWithBLOBs record);//��������ӽڵ���˴���Ϣ

    ClassificationFilterwordsWithBLOBs selectByPrimaryKey(String classificationId);//��ѯ�ڵ���Ϣ���˴���Ϣ
    
    List<FilterWordsVo> selectByFwVo(Map<String, Object> vo);//������ѯ���нڵ���˴���Ϣ

	int queryCount(Map<String, Object> key);		//��ѯ�ڵ�����

	FilterWordsVo queryOne(String classificationId);	//����id��ѯ�����ڵ�

	void updateFwVo(FilterWordsVo vo);					//�༭�ڵ���˴���Ϣ

	List<FilterWordsVo> queryChild(FilterWordsVo vo);//��ѯ��ǰ�ڵ���ӽڵ�
	
	List<FilterWordsVo> queryById(String ids);		//ͨ��id��ѯ�ڵ�

	List<FilterWordsVo> queryByName(String names);			//ͨ�����Ʋ�ѯ�ڵ�

	void insertMany(@Param("list")List<FilterWordsVo> createVo, @Param("userid")String userid) throws DataAccessException;//��������

	void updateMany(@Param("list")List<FilterWordsVo> vos,  @Param("userid")String userid) throws DataAccessException;	//��������

	List<ClassificationInfoBean> getAllClassificationNames();  //������еķ�����ϵ����
}