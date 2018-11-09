package com.ichangyun.InforAnalyaizer.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ichangyun.InforAnalyaizer.model.User;

public interface UserMapper {
	List<User> getUser(@Param("param") Map map);

	void insertUser(@Param("param") Map param);

	List<User> selectAll();

	void update(@Param("param") Map param);

	User isUserExist(@Param("param")Map param);

	String getName(@Param("param")Map param);
}
