/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.mapper.collection;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ichangyun.InforAnalyaizer.model.SearchOptBean;
import com.ichangyun.InforAnalyaizer.model.collection.MyCollection;
import com.ichangyun.InforAnalyaizer.model.collection.MyCollectionKey;
import com.ichangyun.InforAnalyaizer.model.collection.MyCollectionVo;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public interface MyCollectionMapper {
	int deleteByPrimaryKey(MyCollectionKey key);

	int insert(MyCollection record);

	int insertSelective(MyCollection record);

	MyCollection selectByPrimaryKey(MyCollectionKey key);

	int updateByPrimaryKeySelective(MyCollection record);

	int updateByPrimaryKey(MyCollection record);

	List<SearchOptBean> getOpts();

	void delete(Map<String, String> key);

	void updateType(Map<String, String> key) throws DataAccessException;

	List<MyCollectionVo> queryAllO1(Map<String, Object> key);	//全文匹配

	List<MyCollectionVo> queryAllO2(Map<String, Object> key);	//标题匹配

	List<MyCollectionVo> queryAllO3(Map<String, Object> key);	//摘要匹配

	List<MyCollectionVo> queryAllO4(Map<String, Object> key);	//内容匹配
	
	int queryCountO1(Map<String, Object> key);	//全文匹配

	int queryCountO2(Map<String, Object> key);	//标题匹配

	int queryCountO3(Map<String, Object> key);	//摘要匹配

	int queryCountO4(Map<String, Object> key);	//内容匹配

}