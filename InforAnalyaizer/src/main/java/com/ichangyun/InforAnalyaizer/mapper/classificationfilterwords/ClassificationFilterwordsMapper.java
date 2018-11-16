package com.ichangyun.InforAnalyaizer.mapper.classificationfilterwords;


import java.util.List;
import java.util.Map;

import com.ichangyun.InforAnalyaizer.model.classificationfilterwords.ClassificationFilterwords;
import com.ichangyun.InforAnalyaizer.model.classificationfilterwords.ClassificationFilterwordsWithBLOBs;
import com.ichangyun.InforAnalyaizer.model.classificationfilterwords.FilterWordsVo;

public interface ClassificationFilterwordsMapper {
    List<String> queryParents();		//��ѯ���и��ڵ�

	int deleteByPrimaryKey(String classificationId);		//ͨ��idɾ���ڵ�Ĺ��˴���Ϣ

    int insert(ClassificationFilterwordsWithBLOBs record);  //��ӽڵ���˴���Ϣ

    int insertSelective(ClassificationFilterwordsWithBLOBs record);//��������ӽڵ���˴���Ϣ

    ClassificationFilterwordsWithBLOBs selectByPrimaryKey(String classificationId);//��ѯ�ڵ���Ϣ���˴���Ϣ

    int updateByPrimaryKeySelective(ClassificationFilterwordsWithBLOBs record);//�������༭�ڵ���Ϣ���˴���Ϣ

    int updateByPrimaryKeyWithBLOBs(ClassificationFilterwordsWithBLOBs record);//�༭�ڵ���Ϣ���˴���Ϣ

    int updateByPrimaryKey(ClassificationFilterwords record);				//�༭���˴���Ϣ
    
    List<FilterWordsVo> selectByFwVo(Map<String, Object> vo);//������ѯ���нڵ���˴���Ϣ

	int queryCount(Map<String, Object> key);		//��ѯ�ڵ�����

	FilterWordsVo queryOne(String classificationId);	//����id��ѯ�����ڵ�

	void updateFwVo(FilterWordsVo vo);					//�༭�ڵ���˴���Ϣ

	List<FilterWordsVo> queryChild(String parent_Classification_ID);//��ѯ��ǰ�ڵ���ӽڵ�
}