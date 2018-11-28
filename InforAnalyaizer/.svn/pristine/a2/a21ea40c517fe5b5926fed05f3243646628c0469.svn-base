/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ichangyun.InforAnalyaizer.model.User;

/**
 * UserService 业务层接口
 * @author renhao
 * @date 2018/11/09
 */
public interface UserService {

    User isUserExist(String username, String passwd);

    void addCookie(HttpServletResponse response, User user,String passwd);

    void delCookie(HttpServletRequest request, HttpServletResponse response);
}
