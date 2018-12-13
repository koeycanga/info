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
	public Object tohotword(String wordid,String flag,HttpServletRequest request) {
		
		String title = "";
		
		if(flag.equals("0")) {
			title = "�ȴ���";
		}
		
		if(flag.equals("1")) {
			title = "��������";
		}
		
		request.setAttribute("flag", flag);
	
		request.setAttribute("table_title",title);
		
		request.setAttribute("word",wordid);
		
		return new ModelAndView("frontpage/worddetail");
	}
	
	/**
	 * �����ҳ������Ϣ  Ԥ����Ϣ  ������Ϣ��JSON������
	 * @return
	 */
	@RequestMapping("/getHomeDatas")
	public Object getHomeDatas(HttpSession session) {
	    
		String[] datas = homeService.getTopTenDatas(session);
		
		String jcmsg = homeService.getJCMsg(session);
		
		String res = "{\"newest_datas\":"+datas[0]+",\"warn_datas\":"+datas[1]+",\"negative_datas\":"+datas[2]+","+jcmsg+"}";
		
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
	
	@RequestMapping("/getHotWordFromDetial")
	public Object getHotWordFromDetial(String flag) {
		
		String json_hotword = homeService.getHotWordFromDetial(flag);
		
		return json_hotword;
	}
	
	@RequestMapping("/getJJFSWord")
	public Object getJJFSWord() {
		
		String json_jjfsword = homeService. getJJFSWord();
		
		return json_jjfsword;
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
