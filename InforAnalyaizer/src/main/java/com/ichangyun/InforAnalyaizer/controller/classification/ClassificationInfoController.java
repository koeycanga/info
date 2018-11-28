/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
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
import com.ichangyun.InforAnalyaizer.model.User;
import com.ichangyun.InforAnalyaizer.model.classification.ClassificationInfoBean;
import com.ichangyun.InforAnalyaizer.service.classification.ClassificationInfoService;
import com.ichangyun.InforAnalyaizer.service.common.service.DBUpdateCheckService;


/**
 * 分类体系Controller  
 * @author renhao
 * Date:2018-11-9
 */
@RestController
@RequestMapping("/qbgh/classifcationinfo")
public class ClassificationInfoController {

	
	//分类体系service
	@Autowired
	private ClassificationInfoService classificationInfoService;
	
	@Autowired
	private DBUpdateCheckService dbUpdateCheckService;
	
	/**
	 * 进入分类体系页面
	 * @return
	 * Date:2018-11-9
	 */
	@RequestMapping("/index")
	@ResponseBody
	public Object index() {
		return new ModelAndView("classifcation/classifcationinfo");
	}
	
	/**
	 * 根据参数查询分类体系信息
	 * @param cb  查询参数
	 * @return  要查询的分类体系信息
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
	 * 新增分类体系信息
	 * @param cb  新增分类体系的各参数
	 * @param session  
	 * @return  ok:成功   exist:分类名称已存在     nok:异常 
	 * Date:2018-11-12
	 */
	@RequestMapping("/addclassifcationinfo")
	public Object addclassifcationinfo(ClassificationInfoBean cb,HttpSession session) {
		
		String res = "ok";
		
		if(cb.getParent_Classification_ID()==null||cb.getParent_Classification_ID().equals("")) {
			cb.setParent_Classification_ID("0000000000");   //如果没有父节点ID   则说明新增一个根节点
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
	 * 修改分类体系信息
	 * @param cb  要修改的分类体系参数
	 * @param session
	 * @return  ok：成功   exist:分类体系名称已存在  nok:异常
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
	 * 删除分类体系信息
	 * @param map  要删除的分类体系ID  集合
	 * @return  ok: 成功     nok:异常
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
	 * 更改分类体系排位顺序
	 * @param cur_Classification_ID   要交换的分类体系ID 1
	 * @param cur_displayOrder        要交换的分类体系排位顺序 1
	 * @param ch_Classification_ID    要交换的分类体系ID 2
	 * @param ch_displayOrder         要交换的分类体系排位顺序 2
	 * Date:2018-11-12
	 */
	@RequestMapping("/updateorder")
	public void updateOrder(String cur_Classification_ID,int cur_displayOrder,String ch_Classification_ID,int ch_displayOrder) {

		classificationInfoService.updateOrder(cur_Classification_ID, cur_displayOrder, ch_Classification_ID, ch_displayOrder);
	}
	
	
	/**
	 * 根据ID 获得分类体系的子节点
	 * @param cb   要获得子节点的分类体系ID参数
	 * @return   分类体系子节点信息的JSON 字面量
	 * Date:2018-11-12
	 */
	@RequestMapping("/getchild")
	public Object getchild(ClassificationInfoBean cb) {
		
		String json = classificationInfoService.getChildernByID(cb);
		
		return json;
	}
	
	
}
