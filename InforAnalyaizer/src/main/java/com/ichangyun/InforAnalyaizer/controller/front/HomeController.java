/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 *  �����鱨ϵͳ
 */
package com.ichangyun.InforAnalyaizer.controller.front;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.ichangyun.InforAnalyaizer.model.CommBean;
import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.model.front.HotWordBean;
import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.ArticleInfoBean;
import com.ichangyun.InforAnalyaizer.service.front.HomeService;

/**
 * ��ҳ�Ŀ�����
 * @author renhao
 * Date:2018-11-12
 */

@RestController
@RequestMapping("/front")
public class HomeController {

	@Autowired
	private HomeService homeService;
	
	/**
	 * ������ҳ
	 * @return
	 */
	@RequestMapping("/index")
	@ResponseBody
	public Object index() {
		
		return new ModelAndView("frontpage/home");
	}
	
	/**
	 * ���뵽�ȴ�������ҳ��
	 * @return
	 */
	@RequestMapping("/tohotword")
	public Object tohotword(String word,HttpServletRequest request) {
		
		request.setAttribute("table_title", "�ȴ���");
		
		request.setAttribute("word",word);
		
		return new ModelAndView("frontpage/worddetail");
	}
	
	/**
	 * �����ҳ������Ϣ  Ԥ����Ϣ  ������Ϣ��JSON������
	 * @return
	 */
	@RequestMapping("/getHomeDatas")
	public Object getHomeDatas(HttpSession session) {
	    
		String newest_datas = homeService.getNewestDatas();
		
		String warn_datas = homeService.getWarnDatas(session);
		
		String negative_datas = homeService.getNegativeDatas();
		
		String jcmsg = homeService.getJCMsg(session);
		
		String res = "{\"newest_datas\":"+newest_datas+",\"warn_datas\":"+warn_datas+",\"negative_datas\":"+negative_datas+","+jcmsg+"}";
		
		return res;
	}
	
	
	/**
	 * ����ȴ�����Ϣ
	 * @return
	 */
	@RequestMapping("/getHotWord")
	public Object getHotWord() {
		
		String json_hotword = homeService.getHotWord();
		
		return json_hotword;
	}
	
	
	/**
	 * �����ȴʻ�����ڸ��ȴʵ�������Ϣ����
	 * @param hb
	 * @return
	 */
	@RequestMapping("/searchbyhotword")
	public Object searchByHotWord(HotWordBean hb,HttpSession session) {
		
		User u = (User) session.getAttribute(CommBean.SESSION_NAME);
		
		String Userid = u.getUser_ID();
		
		hb.setCreateUser(Userid);
		
		int rowCount = homeService.getArticleCountByHotWord(hb);
		
		String json_res = homeService.getArticleByHotWord(hb);
		
		String res = "{\"rowCount\":\""+rowCount+"\",\"resdata\":"+json_res+"}";

	    return res;
		
	}
	
	@RequestMapping("/getSimContent")
	public Object getSimContent(ArticleInfoBean ab) {
		String json = homeService.getSimContent(ab);
	    
		return json;
	}
	
	
	/**
	 * ��õ�¼�û���Ϣ
	 * @param session
	 * @return
	 */
	@RequestMapping("/getUser")
	public Object getUser(HttpSession session) {
		
		User user = (User)session.getAttribute(CommBean.SESSION_NAME);
	
		
		return user.getName();
	}
	
	/**
	 * �û��˳�ϵͳ
	 * @param session
	 */
	@RequestMapping("/logout")
	public void logout(HttpSession session) {
		
		session.setAttribute(CommBean.SESSION_NAME,null);
		
	}
}
