package com.ichangyun.InforAnalyaizer.mapper.collection;

import java.util.List;
import java.util.Map;

import com.ichangyun.InforAnalyaizer.model.collection.CollectionType;
import com.ichangyun.InforAnalyaizer.model.collection.CollectionTypeKey;
import com.ichangyun.InforAnalyaizer.model.collection.CollectionTypeVo;

public interface CollectionTypeMapper {
    int deleteByPrimaryKey(CollectionTypeKey key);//条件删除

    int insertSelective(CollectionType record);//添加

    int updateByPrimaryKeySelective(CollectionType record);//更新

	List<CollectionTypeVo> queryAllType(String user_ID);//查询全部

	int queryCount(String parentCollectiontypeId);//查询结果数

	CollectionTypeVo queryOne(String string);//根据id查询

	void beforeOrder(CollectionTypeVo vo);		//当前节点后的所有节点减一
	
	void afterOrder(CollectionTypeVo vo);		//当前节点后的所有节点加一
	
	void changeOrder1(Map<String, Object> map);	//往上排序，两个节点之间的节点全部加一

	void changeOrder2(Map<String, Object> map);//往下排序，两个节点之间节点全部减一
}