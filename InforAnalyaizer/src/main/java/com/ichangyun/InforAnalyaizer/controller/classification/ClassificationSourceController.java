/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 *  �����鱨ϵͳ
 */
package com.ichangyun.InforAnalyaizer.controller.classification;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.ichangyun.InforAnalyaizer.model.CommBean;
import com.ichangyun.InforAnalyaizer.model.User;
import com.ichangyun.InforAnalyaizer.model.classification.ClassificationInfoBean;
import com.ichangyun.InforAnalyaizer.model.webinfo.WebInfoBean;
import com.ichangyun.InforAnalyaizer.service.classification.ClassificationSourceService;

/**
 * ��ϢԴ�󶨶�Ӧ������
 * @author renhao
 * Date:2018-11-12
 */

@RestController
@RequestMapping("/qbgh/classificationSource")
public class ClassificationSourceController {

    //��ϢԴ��service	
	@Autowired
	private ClassificationSourceService classificationSourceService;
	
	/**
	 * ������ϢԴ��ҳ��
	 * @return
	 * Date:2018-11-12
	 */
	@RequestMapping("/index")
	@ResponseBody
	public Object index() {
		return new ModelAndView("classifcation/classificationInfoSource");
	}
	
	/**
	 * ��ѯ��ϢԴ����Ϣ
	 * @param cb ��ѯ����
	 * @return Ҫ��ѯ����ϢԴ����Ϣ
	 * Date:2018-11-12
	 */
	@RequestMapping("/search")
	public Object search(ClassificationInfoBean cb) {
	
		int rowCount = classificationSourceService.getClassifcInfoCount(cb);
		
		String json_res = classificationSourceService.getClassifcInfo(cb);
		
		String res = "{\"rowCount\":\""+rowCount+"\",\"resdata\":"+json_res+"}";

	    return res;
	}
	
	/**
	 * ��÷��� ��ϵ���ӽڵ�
	 * @param cb ��ѯ����
	 * @return  ���� ��ϵ���ӽڵ��JSON������
	 * Date:2018-11-12
	 */
	@RequestMapping("/getchild")
	public Object getchild(ClassificationInfoBean cb) {
		
		String json_res = classificationSourceService.getchild(cb);
		
	    return json_res;
	}
	
	/**
	 * ��ѯ��վ��Ϣ
	 * @param wb ��ѯ����
	 * @return  Ҫ��ѯ����վ��Ϣ��JSON������
	 * Date:2018-11-12
	 */
	@RequestMapping("/searchweb")
	public Object searchweb(WebInfoBean wb) {
		
		int rowCount = classificationSourceService.getWebInfoCount(wb);
		
		String json_res = classificationSourceService.getWebInfo(wb);
		
		String res = "{\"rowCount\":\""+rowCount+"\",\"resdata\":"+json_res+"}";
		
		return res ; 
	}
	
	/**
	 * ��ѯ�Ѱ󶨵���վ��Ϣ
	 * @param wb  ��ѯ����
	 * @return  Ҫ��ѯ����վ��Ϣ��JSON������
	 * Date:2018-11-12
	 */
	@RequestMapping("/searchalweb")
	public Object searchalweb(WebInfoBean wb) {
		
		int rowCount = classificationSourceService.getAlWebInfoCount(wb);
		
		String json_res = classificationSourceService.getAlWebInfo(wb);
		
		String res = "{\"rowCount\":\""+rowCount+"\",\"resdata\":"+json_res+"}";
		
		return res;
	}
	
	/**
	 * ����վ��Ϣ�󶨵���ϢԴ��
	 * @param map  Ҫ�󶨵���վ��Ϣ����ϢԴ����
	 * @param session
	 * Date:2018-11-12
	 */
	@RequestMapping("/joingright")
	public void joingright(@RequestBody Map map,HttpSession session) {
	
		String Classification_ID = (String) map.get("Classification_ID");
		
		String json = (String) map.get("json");
		
		String creater = ((User)session.getAttribute(CommBean.SESSION_NAME)).getUser_ID() ;
		
		classificationSourceService.addNewSource(Classification_ID,json,creater);
		
	}
	
	/**
	 * ɾ����ϢԴ�Ѱ󶨵���վ��Ϣ
	 * @param map Ҫɾ������վ��Ϣ����ϢԴ����
	 * Date:2018-11-12
	 */
	@RequestMapping("/joingleft")
	public void joingleft(@RequestBody Map map) {
		String Classification_ID = (String) map.get("Classification_ID");
		
		String json = (String) map.get("json");
		
		classificationSourceService.delteSource(Classification_ID,json);
	}
	
	
	/**
	 * �޸���ϢԴ��Ϣ
	 * @param Classification_ID
	 * @param session
	 * Date:2018-11-12
	 */
	@RequestMapping("/updatesource")
	public void updatesource(String Classification_ID,HttpSession session) {
		
		String updater = ((User)session.getAttribute(CommBean.SESSION_NAME)).getUser_ID() ;
		
		classificationSourceService.updatesource(Classification_ID,updater);
		
	}
	
}
