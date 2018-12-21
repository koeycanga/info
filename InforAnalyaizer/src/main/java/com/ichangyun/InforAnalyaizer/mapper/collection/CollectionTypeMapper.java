/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 *  �����鱨ϵͳ
 */
package com.ichangyun.InforAnalyaizer.mapper.collection;

import java.util.List;
import java.util.Map;

import com.ichangyun.InforAnalyaizer.model.collection.CollectionType;
import com.ichangyun.InforAnalyaizer.model.collection.CollectionTypeKey;
import com.ichangyun.InforAnalyaizer.model.collection.CollectionTypeVo;

public interface CollectionTypeMapper {
    int deleteByPrimaryKey(CollectionTypeKey key);//����ɾ��

    int insertSelective(CollectionType record);//���

    int updateByPrimaryKeySelective(CollectionType record);//����

	List<CollectionTypeVo> queryAllType(String user_ID);//��ѯȫ��

	int queryCount(String parentCollectiontypeId);//��ѯ�����

	CollectionTypeVo queryOne(String string);//����id��ѯ

	void beforeOrder(CollectionTypeVo vo);		//��ǰ�ڵ������нڵ��һ
	
	void afterOrder(CollectionTypeVo vo);		//��ǰ�ڵ������нڵ��һ
	
	void changeOrder1(Map<String, Object> map);	//�������������ڵ�֮��Ľڵ�ȫ����һ

	void changeOrder2(Map<String, Object> map);//�������������ڵ�֮��ڵ�ȫ����һ
}