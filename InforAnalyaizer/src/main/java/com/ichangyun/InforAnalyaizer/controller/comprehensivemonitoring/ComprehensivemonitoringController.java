/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.controller.comprehensivemonitoring;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ichangyun.InforAnalyaizer.model.CommBean;
import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.ArticleInfoBean;
import com.ichangyun.InforAnalyaizer.service.classification.ClassificationInfoService;
import com.ichangyun.InforAnalyaizer.service.comprehensivemonitoring.ComprehensivemonitoringService;
import com.ichangyun.InforAnalyaizer.utils.DateUtils;

/**
 * 
 * 综合监测控制器
 * @author renhao
 * Date:2018-11-12
 */
@RestController
@RequestMapping("/comprehensivemonitoring")
public class ComprehensivemonitoringController {

	@Autowired
	private ComprehensivemonitoringService comprehensivemonitoringService; //综合监测service
	
	@Autowired
	private ClassificationInfoService classificationInfoService; //分类体系service
	
	/**
	 * 进入综合监测页面
	 * @return
	 */
	@RequestMapping("/index")
	@ResponseBody
	public Object index() {
		
		return new ModelAndView("frontpage/comprehensivemonitoring");
	}
	
	@RequestMapping("/search")
	public Object search(ArticleInfoBean ab,HttpSession session) {
		
		String[] montime = DateUtils.dealMontime(ab.getMontime());
		
		ab.setMontime_start(montime[0]);
		ab.setMontime_end(montime[1]);
		
		String lastest_relsetime = comprehensivemonitoringService.getSearchLaestRelsetime(ab);
		
		session.setAttribute(CommBean.LAST_CONTENT_SEARCH_TIME,lastest_relsetime);
		
		int rowCount = comprehensivemonitoringService.getArticleRowCount(ab);
		
		String json_res = comprehensivemonitoringService.getArticleJSON(ab);
		
		String res = "{\"rowCount\":\""+rowCount+"\",\"resdata\":"+json_res+"}";
		
		return res;
	}
	
	/**
	 * 删除文章
	 * @param map
	 * @return
	 */
	@RequestMapping("/delarticle")
	public Object delarticle(@RequestBody Map map) {
		String res = "ok";
		
		String json = (String) map.get("json");
		
		if(!comprehensivemonitoringService.delarticle(json)) {
			res = "nok";
		}
		
		return res;
	}
	
	/**
	 * 预警文章
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping("/toyj")
	public Object toyj(@RequestBody Map map,HttpSession session) {
		String res = "ok";
		
		String json = (String) map.get("json");
		
		if(!comprehensivemonitoringService.toyj(json,session)) {
			res = "nok"; 
		}
		
		return res;
	}
	
	/**
	 * 获得最新消息条目数
	 * @param ab
	 * @return
	 */
	@RequestMapping("/getlastestNews")
	public Object getlastestNews(ArticleInfoBean ab,HttpSession session) {
		
		String last_time = (String) session.getAttribute(CommBean.LAST_CONTENT_SEARCH_TIME);
		
		ab.setReleasetime(last_time);
		
		int count = comprehensivemonitoringService.getlastestNews(ab);
		
		return count;
	}
	
	/**
	 * 获得相似文章
	 * @return
	 */
	@RequestMapping("/getSimContent")
	public Object getSimContent(ArticleInfoBean ab) {
		   
		String[] montime = DateUtils.dealMontime(ab.getMontime());
		
		ab.setMontime_start(montime[0]);
		ab.setMontime_end(montime[1]);
		
		String json = comprehensivemonitoringService.getSimContent(ab);
		    
		return json;
	}
	
	/**
	 * 获得处在根节点的分类体系
	 * @return
	 */
	@RequestMapping("/getAllClassification")
	public Object getAllClassification() {
		
		String json = classificationInfoService.getAllClassification();
		
		return json;
	}
}
