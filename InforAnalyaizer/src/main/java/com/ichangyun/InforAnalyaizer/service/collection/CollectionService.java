/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.service.collection;

import java.util.List;

import com.ichangyun.InforAnalyaizer.model.BaseBean;
import com.ichangyun.InforAnalyaizer.model.SearchOptBean;
import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.model.collection.CollectionTypeVo;
import com.ichangyun.InforAnalyaizer.model.collection.MyCollectionVo;

public interface CollectionService {

	public List<SearchOptBean> getOpts();

	public List<MyCollectionVo> queryAll(MyCollectionVo vo, BaseBean baseBean,User u, SearchOptBean sob);

	public int queryCount(MyCollectionVo vo, User u, SearchOptBean sob);

	public String delete(String[] checkedId);

	public List<CollectionTypeVo> getTypes(User u);

	public String addType(CollectionTypeVo vo);

	public String updateType(CollectionTypeVo vo);

	public String deleteType(CollectionTypeVo vo);

	public String move(String[] checkedId, String collectiontypeId);

	public String moveType(String collectiontypeId, String targetId);

}
