/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 * �����鱨����ϵͳ
 */
package com.ichangyun.InforAnalyaizer.controller.front;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.ichangyun.InforAnalyaizer.model.CommBean;
import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.ArticleInfoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ichangyun.InforAnalyaizer.service.front.DetailsPageService;

/**
 * ����ҳ��Ϣ ��ӦController
 * @author renhao
 * 2018-11-19
 */

@RestController
@RequestMapping("/detailspage")
public class DetailsPageController {

	@Autowired
	private DetailsPageService detailsPageService;
	
	/**
	 * ���뵽����ҳ
	 * @param from
	 * @param request
	 * @return
	 */
	@RequestMapping("/toDetailsPage")
	@ResponseBody
	public Object toDetailsPage(String from,String article_id,HttpServletRequest request) {
		
		 /*String path=request.getServletContext().getContextPath();
		         System.out.println(path);
		          String realPath=request.getServletContext().getRealPath("/templates");
		          System.out.println(realPath);*/
		
		
		String title = "";
		
		if(from.equals("front")) {
			title = "��ҳ";
		}
		
		if(from.equals("comprehensivemonitoring")) {
			title = "�ۺϼ��";
		}
		
		if(from.equals("thematicmonitoring")) {
			title = "ר����";
		}
		
		if(from.equals("earlywarning")) {
			title = "Ԥ������";
		}
		
		request.setAttribute("m_title", title);
		request.setAttribute("m_url", from);
		request.setAttribute("m_article_id",article_id);
		
		return new ModelAndView("frontpage/detailspage");
	}
	
	/**
	 * ����ID������µ���ϸ��Ϣ
	 * @param article_id  ����id
	 * @return ������ϸ��Ϣ��json������
	 */
	@RequestMapping("/getArticleByID")
	public Object getArticleByID(String article_id, HttpSession session) {

		User user = (User) session.getAttribute(CommBean.SESSION_NAME);
		String userid = user.getUser_ID();
		
		String article_data = detailsPageService.getArticleByID(article_id,userid);      //������Ϣ
		
		String bd_data = detailsPageService.getBDArticleByID(article_id);          //������ý���ϵĳ������
		
		String json = "{\"article_data\":"+article_data+",\"bd_data\":"+bd_data+"}";
		
		return json;
	}

	/**
	 * �����������
	 * @return
	 */
	@RequestMapping("/getSimContent")
	public Object getSimContent(ArticleInfoBean ab, HttpSession session) {

		User user = (User) session.getAttribute(CommBean.SESSION_NAME);
		String userid = user.getUser_ID();
		ab.setUserid(userid);

		String json = detailsPageService.getSimContent(ab);

		return json;
	}

}
