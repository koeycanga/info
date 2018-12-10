/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 *  �����鱨ϵͳ
 */
package com.ichangyun.InforAnalyaizer.controller.classification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.ichangyun.InforAnalyaizer.model.CommBean;
import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.model.classification.ClassificationInfoBean;
import com.ichangyun.InforAnalyaizer.service.classification.ClassificationInfoService;
import com.ichangyun.InforAnalyaizer.service.common.service.DBUpdateCheckService;


/**
 * ������ϵController  
 * @author renhao
 * Date:2018-11-9
 */
@RestController
@RequestMapping("/qbgh/classifcationinfo")
public class ClassificationInfoController {

	
	//������ϵservice
	@Autowired
	private ClassificationInfoService classificationInfoService;
	
	@Autowired
	private DBUpdateCheckService dbUpdateCheckService;
	
	/**
	 * ���������ϵҳ��
	 * @return
	 * Date:2018-11-9
	 */
	@RequestMapping("/index")
	@ResponseBody
	public Object index() {
		return new ModelAndView("classifcation/classifcationinfo");
	}
	
	/**
	 * ���ݲ�����ѯ������ϵ��Ϣ
	 * @param cb  ��ѯ����
	 * @return  Ҫ��ѯ�ķ�����ϵ��Ϣ
	 * Date:2018-11-12
	 */
	@RequestMapping("/search")
	public Object search(ClassificationInfoBean cb) {
		
		int rowCount = classificationInfoService.getClassifcInfoCount(cb);
		
		String json_res = classificationInfoService.getClassifcInfo(cb);
		
		String res = "{\"rowCount\":\""+rowCount+"\",\"resdata\":"+json_res+"}";
		
		return res;
	}
	
	
	/**
	 * ����������ϵ��Ϣ
	 * @param cb  ����������ϵ�ĸ�����
	 * @param session  
	 * @return  ok:�ɹ�   exist:���������Ѵ���     nok:�쳣 
	 * Date:2018-11-12
	 */
	@RequestMapping("/addclassifcationinfo")
	public Object addclassifcationinfo(ClassificationInfoBean cb,HttpSession session) {
		
		String res = "ok";
		
		if(cb.getParent_Classification_ID()==null||cb.getParent_Classification_ID().equals("")) {
			cb.setParent_Classification_ID("0000000000");   //���û�и��ڵ�ID   ��˵������һ�����ڵ�
		}
		
		if(classificationInfoService.exist(cb)) {
			 res = "exist";
		}else {
		
			User user = (User) session.getAttribute(CommBean.SESSION_NAME);
	
			cb.setCreateUser(user.getUser_ID());

		    if(!classificationInfoService.AddNew(cb)) {
		    	res = "nok";
	         }else {
	        	 res = classificationInfoService.getInfoByID(cb);
	         }
		}
		return res;
	}
	
	
	/**
	 * �޸ķ�����ϵ��Ϣ
	 * @param cb  Ҫ�޸ĵķ�����ϵ����
	 * @param session
	 * @return  ok���ɹ�   exist:������ϵ�����Ѵ���  nok:�쳣
	 * Date:2018-11-12
	 */
	@RequestMapping("/updateclassifcationinfo")
	public Object updateclassifcationinfo(ClassificationInfoBean cb,HttpSession session) {
		
		String res = "ok";
		
		if(classificationInfoService.existExceptID(cb)) {
			 res = "exist";
		}else {
			User user = (User) session.getAttribute(CommBean.SESSION_NAME);
			
			cb.setUpdateUser(user.getUser_ID());
			
			List<String> paramList = new ArrayList<String>();
			paramList.add(cb.getClassification_ID());
			if(!dbUpdateCheckService.DBUpdateCheck("3", paramList, cb.getUpdateDateTime())) {
				res = "already update";
			}else {
				if(!classificationInfoService.updateData(cb)) {
					res = "nok";
				}
			}
		}
		return res;
	}
	
	
	/**
	 * ɾ��������ϵ��Ϣ
	 * @param map  Ҫɾ���ķ�����ϵID  ����
	 * @return  ok: �ɹ�     nok:�쳣
	 * Date:2018-11-12
	 */
	@RequestMapping("/deldata")
	public Object delData(@RequestBody Map map) {
		String res = "ok";
		String json = (String) map.get("json");
		if(json==null||"".equals(json)) {
			res = "nok";
		}else {
			if(!classificationInfoService.delDSata(json)) {
				res = "nok";
			}
		}
		return res;
	}
	
	
	/**
	 * ���ķ�����ϵ��λ˳��
	 * @param cur_Classification_ID   Ҫ�����ķ�����ϵID 1
	 * @param cur_displayOrder        Ҫ�����ķ�����ϵ��λ˳�� 1
	 * @param ch_Classification_ID    Ҫ�����ķ�����ϵID 2
	 * @param ch_displayOrder         Ҫ�����ķ�����ϵ��λ˳�� 2
	 * Date:2018-11-12
	 */
	@RequestMapping("/updateorder")
	public void updateOrder(String cur_Classification_ID,int cur_displayOrder,String ch_Classification_ID,int ch_displayOrder) {

		classificationInfoService.updateOrder(cur_Classification_ID, cur_displayOrder, ch_Classification_ID, ch_displayOrder);
	}
	
	
	/**
	 * ����ID ��÷�����ϵ���ӽڵ�
	 * @param cb   Ҫ����ӽڵ�ķ�����ϵID����
	 * @return   ������ϵ�ӽڵ���Ϣ��JSON ������
	 * Date:2018-11-12
	 */
	@RequestMapping("/getchild")
	public Object getchild(ClassificationInfoBean cb) {
		
		String json = classificationInfoService.getChildernByID(cb);
		
		return json;
	}
	
	
}
