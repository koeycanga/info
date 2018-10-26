package com.ishangyun.InforAnalyaizer.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ishangyun.InforAnalyaizer.mapper.UserMapper;
import com.ishangyun.InforAnalyaizer.model.CommBean;
import com.ishangyun.InforAnalyaizer.model.User;
import com.ishangyun.InforAnalyaizer.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	public List<User> getUser(int id) {
		Map map = new HashMap();
		map.put("id", id);
		return userMapper.getUser(map);
	}

	public void insertUser(int id, String name, int age, String sex) {
		Map param = new HashMap();
		param.put("id", id);
		param.put("name", name);
		param.put("age", age);
		param.put("sex", sex);
		userMapper.insertUser(param);
	}

	public List<User> selectAll() {
		return userMapper.selectAll();
	}

	public Object update() {
		Map param = new HashMap();
		param.put("total", 10);
		param.put("name", "����һ");
		userMapper.update(param);
		return "OK";
	}

	@Override
	public User isUserExist(String username, String passwd) {
		Map param = new HashMap();
		param.put("username", username);
		param.put("passwd", passwd);
		User user = userMapper.isUserExist(param);

		return user;
	}

	@Override
	public void addCookie(HttpServletResponse response, User user) {
		Cookie cookie = new Cookie(CommBean.COOKIE_NAME,user.getUser_ID());//������cookie
        cookie.setMaxAge(CommBean.COOKIE_TIME);// ���ô���ʱ��
        cookie.setPath("/");//����������
        response.addCookie(cookie);//��cookie��ӵ�response��cookie�����з��ظ��ͻ���
        
        Cookie cookie2 = new Cookie(CommBean.COOKIE_PWD,user.getPassword());//������cookie
        cookie2.setMaxAge(CommBean.COOKIE_TIME);// ���ô���ʱ��
        cookie2.setPath("/");//����������
        response.addCookie(cookie2);//��cookie��ӵ�response��cookie�����з��ظ��ͻ���
	}

	@Override
	public void delCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
        if (cookies!=null)
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
