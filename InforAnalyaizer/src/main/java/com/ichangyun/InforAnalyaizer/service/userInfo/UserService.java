/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 * �����鱨����ϵͳ
 */
package com.ichangyun.InforAnalyaizer.service.userInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ichangyun.InforAnalyaizer.model.userInfo.User;

/**
 * UserService ҵ���ӿ�
 * @author renhao
 * @date 2018/11/09
 */
public interface UserService {

    public User isUserExist(String username, String passwd);

    public void addCookie(HttpServletResponse response, User user,String passwd);

    public void delCookie(HttpServletRequest request, HttpServletResponse response);
}
