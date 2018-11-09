package com.ichangyun.InforAnalyaizer.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ichangyun.InforAnalyaizer.model.User;

/**
 * ҵ���ӿ�
 */
public interface UserService {
     List<User> getUser(int id);
 
     void insertUser(int id, String name, int age, String sex);
 
     List<User> selectAll();
 
     Object update();

     User isUserExist(String username, String passwd);

	void addCookie(HttpServletResponse response, User user,String passwd);

	void delCookie(HttpServletRequest request, HttpServletResponse response);
}
