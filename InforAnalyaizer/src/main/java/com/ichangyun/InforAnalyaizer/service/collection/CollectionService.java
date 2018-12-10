package com.ichangyun.InforAnalyaizer.service.collection;

import java.util.List;

import com.ichangyun.InforAnalyaizer.model.BaseBean;
import com.ichangyun.InforAnalyaizer.model.SearchOptBean;
import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.model.collection.CollectionTypeVo;
import com.ichangyun.InforAnalyaizer.model.collection.MyCollectionVo;

public interface CollectionService {

	List<SearchOptBean> getOpts();

	List<MyCollectionVo> queryAll(MyCollectionVo vo, BaseBean baseBean,User u);

	int queryCount(MyCollectionVo vo, User u);

	String delete(String[] checkedId);

	List<CollectionTypeVo> getTypes(User u);

	String addType(CollectionTypeVo vo);

	String updateType(CollectionTypeVo vo);

	String deleteType(CollectionTypeVo vo);

	String move(String[] checkedId, String collectiontypeId);

	String moveType(String collectiontypeId, String targetId);

}
