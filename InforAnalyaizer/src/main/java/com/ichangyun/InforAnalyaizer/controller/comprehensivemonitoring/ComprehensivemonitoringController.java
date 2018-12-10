/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 * �����鱨����ϵͳ
 */
package com.ichangyun.InforAnalyaizer.controller.comprehensivemonitoring;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.ichangyun.InforAnalyaizer.model.userInfo.User;
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
 * �ۺϼ�������
 * @author renhao
 * Date:2018-11-12
 */
@RestController
@RequestMapping("/comprehensivemonitoring")
public class ComprehensivemonitoringController {

	@Autowired
	private ComprehensivemonitoringService comprehensivemonitoringService; //�ۺϼ��service
	
	@Autowired
	private ClassificationInfoService classificationInfoService; //������ϵservice
	
	/**
	 * �����ۺϼ��ҳ��
	 * @return
	 */
	@RequestMapping("/index")
	@ResponseBody
	public Object index() {
		
		return new ModelAndView("frontpage/comprehensivemonitoring");
	}
	
	/**
	 * ִ��ҳ���ѯ����
	 * @param ab
	 * @param session
	 * @return
	 */
	@RequestMapping("/search")
	public Object search(ArticleInfoBean ab,HttpSession session) {
		
		String[] montime = DateUtils.dealMontime(ab.getMontime());
		
		ab.setMontime_start(montime[0]);
		ab.setMontime_end(montime[1]);

		User user = (User) session.getAttribute(CommBean.SESSION_NAME);
		String userid = user.getUser_ID();
		ab.setUserid(userid);

		String lastest_relsetime = comprehensivemonitoringService.getSearchLaestRelsetime(ab);
		
		session.setAttribute(CommBean.LAST_CONTENT_SEARCH_TIME_ZHJC,lastest_relsetime);
		
		int rowCount = comprehensivemonitoringService.getArticleRowCount(ab);
		
		String json_res = comprehensivemonitoringService.getArticleJSON(ab);
		
		String res = "{\"rowCount\":\""+rowCount+"\",\"resdata\":"+json_res+"}";
		
		return res;
	}
	
	/**
	 * ɾ������
	 * @param map
	 * @return
	 */
	@RequestMapping("/delarticle")
	public Object delarticle(@RequestBody Map map,HttpSession session) {
		String res = "ok";

		String json = (String) map.get("json");
		String deletemode = (String) map.get("deletemode");
		User user = (User) session.getAttribute(CommBean.SESSION_NAME);
		String userid = user.getUser_ID();

		if(!comprehensivemonitoringService.delarticle(json,userid,deletemode)) {
			res = "nok";
		}
		
		return res;
	}
	
	/**
	 * Ԥ������
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
	 * ���������Ϣ��Ŀ��
	 * @param ab
	 * @return
	 */
	@RequestMapping("/getlastestNews")
	public Object getlastestNews(ArticleInfoBean ab,HttpSession session) {
		
		String last_time = (String) session.getAttribute(CommBean.LAST_CONTENT_SEARCH_TIME_ZHJC);
		
		ab.setReleasetime(last_time);
		
		int count = comprehensivemonitoringService.getlastestNews(ab);
		
		return count;
	}
	
	/**
	 * �����������
	 * @return
	 */
	@RequestMapping("/getSimContent")
	public Object getSimContent(ArticleInfoBean ab,HttpSession session) {
		   
		String[] montime = DateUtils.dealMontime(ab.getMontime());
		
		ab.setMontime_start(montime[0]);
		ab.setMontime_end(montime[1]);

		User user = (User) session.getAttribute(CommBean.SESSION_NAME);
		String userid = user.getUser_ID();
		ab.setUserid(userid);
		
		String json = comprehensivemonitoringService.getSimContent(ab);
		    
		return json;
	}
	
	/**
	 * ��ô��ڸ��ڵ�ķ�����ϵ
	 * @return
	 */
	@RequestMapping("/getAllClassification")
	public Object getAllClassification() {
		
		String json = classificationInfoService.getAllClassification();
		
		return json;
	}
}
