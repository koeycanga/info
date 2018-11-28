package com.ichangyun.InforAnalyaizer.mapper.collection;

import java.util.List;

import com.ichangyun.InforAnalyaizer.model.collection.CollectionType;
import com.ichangyun.InforAnalyaizer.model.collection.CollectionTypeKey;
import com.ichangyun.InforAnalyaizer.model.collection.CollectionTypeVo;

public interface CollectionTypeMapper {
    int deleteByPrimaryKey(CollectionTypeKey key);//

    int insertSelective(CollectionType record);//

    int updateByPrimaryKeySelective(CollectionType record);//

	List<CollectionTypeVo> queryAllType(String user_ID);

	int queryCount(String parentCollectiontypeId);

	CollectionTypeVo queryOne(String string);
}