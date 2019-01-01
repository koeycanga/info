/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系
 */
package com.ichangyun.InforAnalyaizer.service.userInfo.impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ichangyun.InforAnalyaizer.mapper.userInfoMapper.UserMapper;
import com.ichangyun.InforAnalyaizer.model.CommBean;
import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.service.userInfo.UserService;
import com.ichangyun.InforAnalyaizer.utils.PBKDF2;

/**
 * UserService的实体
 * @author renhao
 * @date 2018/11/09
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * User存在性Check
     * @param username 用户名
     * @param passwd 密码
     * @return user 用户情报
     */
    @Override
    public User isUserExist(String username, String passwd) {

        Map<String, String> param = new HashMap<String, String>();
        param.put("username", username);
        try {
            // 根据password和salt生成密文
            String md_passwd = PBKDF2.getPBKDF2(passwd, DatatypeConverter.printHexBinary((username).getBytes()));
            param.put("passwd", md_passwd);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        // User存在性Check
        User user = userMapper.isUserExist(param);
        return user;
    }

    /**
     * Add Cookie
     * @param response
     * @param user 用户名
     * @param passwd 密码
     */
    @Override
    public void addCookie(HttpServletResponse response, User user, String passwd) {
        // 创建新cookie
        Cookie cookie = new Cookie(CommBean.COOKIE_NAME,user.getUser_ID());
        cookie.setMaxAge(CommBean.COOKIE_TIME);// 设置存在时间
        cookie.setPath("/");//设置作用域
        response.addCookie(cookie);//将cookie添加到response的cookie数组中返回给客户端

        Cookie cookie2 = new Cookie(CommBean.COOKIE_PWD,passwd);//创建新cookie
        cookie2.setMaxAge(CommBean.COOKIE_TIME);// 设置存在时间
        cookie2.setPath("/");//设置作用域
        response.addCookie(cookie2);//将cookie添加到response的cookie数组中返回给客户端
    }

    /**
     * Delete Cookie
     * @param request
     * @param response
     */
    @Override
    public void delCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies!=null) {
            for(Cookie cookie : cookies){
                //如果找到同名cookie，就将value设置为null，将存活时间设置为0，再替换掉原cookie，这样就相当于删除了。
                if(cookie.getName().equals(CommBean.COOKIE_NAME)||cookie.getName().equals(CommBean.COOKIE_PWD)){
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
    }
}
