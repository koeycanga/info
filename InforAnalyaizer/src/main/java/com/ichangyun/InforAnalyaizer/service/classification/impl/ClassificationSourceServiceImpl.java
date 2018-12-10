/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.service.classification.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ichangyun.InforAnalyaizer.mapper.classification.ClassificationSourceMapper;
import com.ichangyun.InforAnalyaizer.model.classification.ClassificationInfoBean;
import com.ichangyun.InforAnalyaizer.model.webinfo.WebInfoBean;
import com.ichangyun.InforAnalyaizer.service.classification.ClassificationSourceService;

@Service
public class ClassificationSourceServiceImpl implements ClassificationSourceService {

	//信息源绑定Mapper
	@Autowired
	private ClassificationSourceMapper classificationSourceMapper;

	//Spring 事务管理manager
	@Resource(name = "transactionManager")
    private PlatformTransactionManager platformTransactionManager;
	
	@Override
	public int getClassifcInfoCount(ClassificationInfoBean cb) {
		ClassificationInfoBean rcb = classificationSourceMapper.getClassifcInfoCount(cb);
		if(rcb!=null) {
			return rcb.getCnt();
		}else {
			return 0;
		}
	}

	@Override
	public String getClassifcInfo(ClassificationInfoBean cb) {
		
		cb.setL_pre((cb.getPageNow()-1)*cb.getRowSize());
		
	    List<ClassificationInfoBean> list = classificationSourceMapper.getClassifcInfo(cb);
	    
	    JSONArray ja = (JSONArray) JSONArray.toJSON(list);
	    
		return ja.toJSONString();
	}

	@Override
	public String getchild(ClassificationInfoBean cb) {
		
		List<ClassificationInfoBean> list = classificationSourceMapper.getchild(cb);
		
		JSONArray ja = (JSONArray) JSONArray.toJSON(list);
	    
		return ja.toJSONString();
	}

	@Override
	public int getWebInfoCount(WebInfoBean wb) {
		
		return classificationSourceMapper.getWebInfoCount(wb);
	}

	@Override
	public String getWebInfo(WebInfoBean wb) {
		
		wb.setL_pre((wb.getPageNow()-1)*wb.getRowSize());
		
		List<WebInfoBean> list = classificationSourceMapper.getWebInfo(wb);
		
		JSONArray ja = (JSONArray) JSONArray.toJSON(list);
	    
		return ja.toJSONString();
	}

	@Override
	public int getAlWebInfoCount(WebInfoBean wb) {
	
		return classificationSourceMapper.getAlWebInfoCount(wb);
	}

	@Override
	public String getAlWebInfo(WebInfoBean wb) {
		
		wb.setL_pre((wb.getPageNow()-1)*wb.getRowSize());
		
		List<WebInfoBean> list = classificationSourceMapper.getAlWebInfo(wb);
		
		JSONArray ja = (JSONArray) JSONArray.toJSON(list);
	    
		return ja.toJSONString();
	}

	@Override
	public void addNewSource(String classification_ID, String json, String creater) {
		
		TransactionStatus status = null;
		try {
			    /* 关闭事务自动提交 */
				DefaultTransactionDefinition def = new DefaultTransactionDefinition();
				def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
				status = platformTransactionManager.getTransaction(def);
		
				List<WebInfoBean> list = new ArrayList<WebInfoBean>();
				JSONArray ja = JSONArray.parseArray(json);
		
				int web_no = classificationSourceMapper.getWebNo();
		
				for (int i = 0; i < ja.size(); i++) {
					WebInfoBean wb = new WebInfoBean();
					JSONObject obj = (JSONObject) ja.get(i);
					String web_id = obj.getString("id");
					wb.setWebsite_ID(web_id);
					wb.setWebsite_No(web_no + (i + 1));
					wb.setClassification_ID(classification_ID);
					wb.setCreateUser(creater);
					list.add(wb);
				}
		
				classificationSourceMapper.addNewSource(list);
		
				platformTransactionManager.commit(status); // 提交事务
		}catch(Exception e) {
			if(status!=null) {platformTransactionManager.rollback(status);}
			e.printStackTrace();
		}
		
	}

	@Override
	public void delteSource(String classification_ID, String json) {
		List<Integer> list = new ArrayList<Integer>();
		JSONArray ja =  JSONArray.parseArray(json);
		for(int i=0;i<ja.size();i++) {
			WebInfoBean wb = new WebInfoBean();
			JSONObject obj = (JSONObject) ja.get(i);
			String web_id = obj.getString("id");
			list.add(Integer.parseInt(web_id));
		}
		classificationSourceMapper.delteSource(classification_ID,list);
	}

	@Override
	public void updatesource(String Classification_ID,String updater) {
		
		classificationSourceMapper.updatesource(Classification_ID,updater);
	}
}
