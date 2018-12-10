/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 * �����鱨����ϵ
 */
package com.ichangyun.InforAnalyaizer.service.userInfo.impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
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
import com.ichangyun.InforAnalyaizer.model.classification.ClassificationInfoBean;
import com.ichangyun.InforAnalyaizer.service.userInfo.UserService;
import com.ichangyun.InforAnalyaizer.utils.PBKDF2;

/**
 * UserService��ʵ��
 * @author renhao
 * @date 2018/11/09
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * User������Check
     * @param username �û���
     * @param passwd ����
     * @return user �û��鱨
     */
    @Override
    public User isUserExist(String username, String passwd) {
    	
    	Map<String, String> param = new HashMap<String, String>();
        param.put("username", username);
        try {
            // ����password��salt��������
            String md_passwd = PBKDF2.getPBKDF2(passwd, DatatypeConverter.printHexBinary((username).getBytes()));
            param.put("passwd", md_passwd);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        // User������Check
        User user = userMapper.isUserExist(param);
        return user;
    }

	/**
     * Add Cookie
     * @param response
     * @param user �û���
     * @param passwd ����
     */
    @Override
    public void addCookie(HttpServletResponse response, User user, String passwd) {
        // ������cookie
        Cookie cookie = new Cookie(CommBean.COOKIE_NAME,user.getUser_ID());
        cookie.setMaxAge(CommBean.COOKIE_TIME);// ���ô���ʱ��
        cookie.setPath("/");//����������
        response.addCookie(cookie);//��cookie���ӵ�response��cookie�����з��ظ��ͻ���

        Cookie cookie2 = new Cookie(CommBean.COOKIE_PWD,passwd);//������cookie
        cookie2.setMaxAge(CommBean.COOKIE_TIME);// ���ô���ʱ��
        cookie2.setPath("/");//����������
        response.addCookie(cookie2);//��cookie���ӵ�response��cookie�����з��ظ��ͻ���
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
                //����ҵ�ͬ��cookie���ͽ�value����Ϊnull�������ʱ������Ϊ0�����滻��ԭcookie���������൱��ɾ���ˡ�
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