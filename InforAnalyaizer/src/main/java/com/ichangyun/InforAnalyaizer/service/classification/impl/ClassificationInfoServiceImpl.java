/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.service.classification.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ichangyun.InforAnalyaizer.mapper.classification.ClassificationInfoMapper;
import com.ichangyun.InforAnalyaizer.mapper.numbercontrol.NumberControlMapper;
import com.ichangyun.InforAnalyaizer.model.classification.ClassificationInfoBean;
import com.ichangyun.InforAnalyaizer.model.numbercontroll.NumberingcontrolBean;
import com.ichangyun.InforAnalyaizer.service.classification.ClassificationInfoService;
import com.ichangyun.InforAnalyaizer.service.numberingcontrol.NumberingcontrolService;
import com.ichangyun.InforAnalyaizer.utils.DateUtils;

@Service
public class ClassificationInfoServiceImpl implements ClassificationInfoService {


    //分类体系Mapper
	@Autowired
	private ClassificationInfoMapper classificationInfoMapper;

	@Autowired
	private NumberingcontrolService numberingcontrolService;
	
	@Resource(name = "transactionManager")
    private PlatformTransactionManager platformTransactionManager;
	
	@Override
	public int getClassifcInfoCount(ClassificationInfoBean cb) {
		
		return classificationInfoMapper.getClassifcInfoCount(cb);
	}

	@Override
	public String getClassifcInfo(ClassificationInfoBean cb) {
		
		cb.setL_pre((cb.getPageNow()-1)*cb.getRowSize());
		
		List<ClassificationInfoBean> list = classificationInfoMapper.getClassifcInfo(cb);
		
		JSONArray listArray=(JSONArray) JSONArray.toJSON(list);
		
		return listArray.toJSONString();
	}

	@Override
	public String getClassificationInfoID() {
		
		return numberingcontrolService.getNextCFID("NC00000004");
		
	}

	@Override
	public boolean AddNew(ClassificationInfoBean cb) {
		TransactionStatus status = null;
       try {
    	   //关闭Spring事务自动提交
	       DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	       def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
	       status = platformTransactionManager.getTransaction(def);
	       
	       int display_order = classificationInfoMapper.getDisplayOrder(cb);
	       
	       cb.setDisplayOrder(display_order);
	       
	       int res = classificationInfoMapper.addNew(cb);
	       
	       platformTransactionManager.commit(status);  //提交事务
	       
	       if(res==1) {
	    	   return true;
	       }
       
	       return false;
       }catch(Exception e) {
    	   if(status!=null) {platformTransactionManager.rollback(status);}
    	   e.printStackTrace();
    	   return false;
       }
	}

	@Override
	public boolean exist(ClassificationInfoBean cb) {
	    
		int count = classificationInfoMapper.exist(cb);
		
		if(count>=1) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean existExceptID(ClassificationInfoBean cb) {
		int count = classificationInfoMapper.existExceptID(cb);
		if(count>=1) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean updateData(ClassificationInfoBean cb) {
		
		int count = classificationInfoMapper.updateData(cb);
		
		if(count==1) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean delDSata(String json) {
		
		JSONArray parseArray = JSONArray.parseArray(json);
		
		for(int i=0;i<parseArray.size();i++) {
			JSONObject jo = (JSONObject) parseArray.get(i);
			ClassificationInfoBean cb = new ClassificationInfoBean();
			cb.setClassification_ID(jo.getString("id"));
			delData(cb);
		}
		
		return true;
	}

	private void delData(ClassificationInfoBean cb) {
		
		List<ClassificationInfoBean> children_list = getChildren(cb);
		for(ClassificationInfoBean ccb:children_list) {
			delData(ccb);
		}
		classificationInfoMapper.delData(cb);
	}

	private List<ClassificationInfoBean> getChildren(ClassificationInfoBean cb) {
		
		return classificationInfoMapper.getChildren(cb);
	}

	@Override
	public void updateOrder(String cur_Classification_ID, int cur_displayOrder, String ch_Classification_ID,
			int ch_displayOrder) {
		
		ClassificationInfoBean cb1 = new ClassificationInfoBean();
		cb1.setClassification_ID(cur_Classification_ID);
		cb1.setDisplayOrder(cur_displayOrder);
		classificationInfoMapper.updateOrder(cb1);
		
		
		ClassificationInfoBean cb2 = new ClassificationInfoBean();
		cb2.setClassification_ID(ch_Classification_ID);
		cb2.setDisplayOrder(ch_displayOrder);
		classificationInfoMapper.updateOrder(cb2);
	}

	@Override
	public String getChildernByID(ClassificationInfoBean cb) {
		
		List<ClassificationInfoBean> list = classificationInfoMapper.getChildernByID(cb);
		
		JSONArray listArray=(JSONArray) JSONArray.toJSON(list);
		
		return listArray.toJSONString();
	}

	@Override
	public String getInfoByID(ClassificationInfoBean cb) {
		ClassificationInfoBean acb = classificationInfoMapper.getInfoByID(cb);
		JSONObject jobj = (JSONObject) JSONObject.toJSON(acb);
		return jobj.toJSONString();
	}

	@Override
	public String getAllClassification() {
		
		String yesterday = DateUtils.format(DateUtils.addDateDays(new Date(), -1), DateUtils.DATE_PATTERN);
		
		String today = DateUtils.format(new Date(), DateUtils.DATE_PATTERN);
		
		List<ClassificationInfoBean> list = classificationInfoMapper.getAllClassification(yesterday,today);
		
		List<ClassificationInfoBean> rlist = new ArrayList<ClassificationInfoBean>();
		
		Map<String,ClassificationInfoBean> map = new HashMap<String,ClassificationInfoBean>();
		
		for(int i=list.size()-1;i>=0;i--) {  //去掉重复的根节点
			ClassificationInfoBean cb = list.get(i);
			if(cb.getParent_Classification_ID().equals("0000000000")) { 
				if(map.get(cb.getClassification_ID())==null) {
					if(cb.getChildren_lg()>0&&isHaveChildren(cb,list)) {
						cb.setYesterday_cnt(0);
						cb.setToday_cnt(0);
					}
					map.put(cb.getClassification_ID(),cb);
				}else {
					list.remove(i);
				}
			}else {
				if(cb.getChildren_lg()>0) {
					if(isHaveChildren(cb,list)) {
 						 cb.setCnt(0);
					}
				}
			}
		}
		
		for(ClassificationInfoBean cb : list) {
			rlist.add(cb);
			if(!cb.getParent_Classification_ID().equals("0000000000")) {  //不是根节点
				dealCntCB(cb,list,yesterday,today);
			}
		}
		
		JSONArray listArray=(JSONArray) JSONArray.toJSON(rlist);
				
		return listArray.toJSONString();
	}

	private boolean isHaveChildren(ClassificationInfoBean cb, List<ClassificationInfoBean> list) {
		for(ClassificationInfoBean acb:list) {
			 if(acb.getParent_Classification_ID().equals(cb.getClassification_ID())) {
				 return true;
			 }
		}
		return false;
	}

	private void dealCntCB(ClassificationInfoBean cb, List<ClassificationInfoBean> list, String yesterday,String today) {
		
		if(cb.getReleasetime()!=null&&!cb.getReleasetime().equals("")&&cb.getCnt()>0) {
			
			ClassificationInfoBean root = getRootNode(cb,list);
            
			if(cb.getReleasetime().equals(yesterday)) {
				root.setYesterday_cnt(root.getYesterday_cnt()+cb.getCnt());
			}
			
			if(cb.getReleasetime().equals(today)) {
				root.setToday_cnt(root.getToday_cnt()+cb.getCnt());
			}
		}
		
	}

	/**
	 * 获得一个
	 * @param cb
	 * @param list
	 * @return
	 */
	private ClassificationInfoBean getRootNode(ClassificationInfoBean cb, List<ClassificationInfoBean> list) {
		for(ClassificationInfoBean acb : list) {
			if(acb.getClassification_ID().equals(cb.getParent_Classification_ID())) {
				if(acb.getParent_Classification_ID().equals("0000000000")) {
					return acb;
				}else {
					return getRootNode(acb,list);
				}
				
			}
		}
		return null;
	}
	
}
