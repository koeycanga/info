/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.mapper.collection;

import java.util.List;
import java.util.Map;

import com.ichangyun.InforAnalyaizer.model.collection.CollectionType;
import com.ichangyun.InforAnalyaizer.model.collection.CollectionTypeKey;
import com.ichangyun.InforAnalyaizer.model.collection.CollectionTypeVo;

public interface CollectionTypeMapper {
    //条件删除
    int deleteByPrimaryKey(CollectionTypeKey key);
    //添加
    int insertSelective(CollectionType record);
    //更新
    int updateByPrimaryKeySelective(CollectionType record);
    //查询全部
    List<CollectionTypeVo> queryAllType(String user_ID);
    //查询结果数
    int queryCount(String parentCollectiontypeId);
    //根据id查询
    CollectionTypeVo queryOne(String string);
    //当前节点后的所有节点减一
    void beforeOrder(CollectionTypeVo vo);
    //当前节点后的所有节点加一
    void afterOrder(CollectionTypeVo vo);
    //往上排序，两个节点之间的节点全部加一
    void changeOrder1(Map<String, Object> map);
    //往下排序，两个节点之间节点全部减一
    void changeOrder2(Map<String, Object> map);
}