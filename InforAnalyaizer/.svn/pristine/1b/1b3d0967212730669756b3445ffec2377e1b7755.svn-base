package com.ichangyun.InforAnalyaizer.service.impl;

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

import com.ichangyun.InforAnalyaizer.mapper.UserMapper;
import com.ichangyun.InforAnalyaizer.model.CommBean;
import com.ichangyun.InforAnalyaizer.model.User;
import com.ichangyun.InforAnalyaizer.service.UserService;
import com.ichangyun.InforAnalyaizer.utils.PBKDF2;

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
		param.put("name", "测试一");
		userMapper.update(param);
		return "OK";
	}

	@Override
	public User isUserExist(String username, String passwd) {
		Map param = new HashMap();
		param.put("username", username);
		String name = userMapper.getName(param);
		try {
			String md_passwd = PBKDF2.getPBKDF2(passwd, DatatypeConverter.printHexBinary((username+name).getBytes()));
			param.put("passwd", md_passwd);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		
		User user = userMapper.isUserExist(param);

		return user;
	}

	@Override
	public void addCookie(HttpServletResponse response, User user,String passwd) {
		Cookie cookie = new Cookie(CommBean.COOKIE_NAME,user.getUser_ID());//创建新cookie
        cookie.setMaxAge(CommBean.COOKIE_TIME);// 设置存在时间
        cookie.setPath("/");//设置作用域
        response.addCookie(cookie);//将cookie添加到response的cookie数组中返回给客户端
        
        Cookie cookie2 = new Cookie(CommBean.COOKIE_PWD,passwd);//创建新cookie
        cookie2.setMaxAge(CommBean.COOKIE_TIME);// 设置存在时间
        cookie2.setPath("/");//设置作用域
        response.addCookie(cookie2);//将cookie添加到response的cookie数组中返回给客户端
	}

	@Override
	public void delCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
        if (cookies!=null)
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
