/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.controller.front;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ichangyun.InforAnalyaizer.service.front.DetailsPageService;

/**
 * 详情页信息 对应Controller
 * @author renhao
 * 2018-11-19
 */

@RestController
@RequestMapping("/detailspage")
public class DetailsPageController {

	@Autowired
	private DetailsPageService detailsPageService;
	
	/**
	 * 进入到详情页
	 * @param from
	 * @param request
	 * @return
	 */
	@RequestMapping("/toDetailsPage")
	@ResponseBody
	public Object toDetailsPage(String from,String article_id,HttpServletRequest request) {
		
		String title = "";
		
		if(from.equals("front")) {
			title = "首页";
		}
		
		if(from.equals("comprehensivemonitoring")) {
			title = "综合监测";
		}
		
		if(from.equals("thematicomonitoring")) {
			title = "专题监测";
		}
		
		request.setAttribute("m_title", title);
		request.setAttribute("m_url", from);
		request.setAttribute("m_article_id",article_id);
		
		return new ModelAndView("frontpage/detailspage");
	}
	
	/**
	 * 根据ID获得文章的详细信息
	 * @param article_id  文章id
	 * @return 文章详细信息的json字面量
	 */
	@RequestMapping("/getArticleByID")
	public Object getArticleByID(String article_id) {
		
		String article_data = detailsPageService.getArticleByID(article_id);      //文章信息
		
		String bd_data = detailsPageService.getBDArticleByID(article_id);          //文章在媒体上的出现情况
		
		String json = "{\"article_data\":"+article_data+",\"bd_data\":"+bd_data+"}";
		
		return json;
	}
	
}
