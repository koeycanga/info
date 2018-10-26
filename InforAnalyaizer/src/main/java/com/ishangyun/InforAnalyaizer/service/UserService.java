package com.ishangyun.InforAnalyaizer.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ishangyun.InforAnalyaizer.model.User;

/**
 * 业务层接口
 */
public interface UserService {
     List<User> getUser(int id);
 
     void insertUser(int id, String name, int age, String sex);
 
     List<User> selectAll();
 
     Object update();

     User isUserExist(String username, String passwd);

	void addCookie(HttpServletResponse response, User user);

	void delCookie(HttpServletRequest request, HttpServletResponse response);
}
