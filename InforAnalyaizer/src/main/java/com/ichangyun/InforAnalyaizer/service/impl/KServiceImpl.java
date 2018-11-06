package com.ichangyun.InforAnalyaizer.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ichangyun.InforAnalyaizer.mapper.KMapper;
import com.ichangyun.InforAnalyaizer.model.KBean;
import com.ichangyun.InforAnalyaizer.service.KService;

@Service
public class KServiceImpl implements KService {

	@Autowired
	private KMapper kMapper;
	
	@Override
	public void sayhello() {
		List<KBean> list = kMapper.sayhello();
		
		for(KBean k:list) {
			System.out.println(k.getId()+"-----"+k.getName());
		}
	}

	@Override
	public String getAll(int pageNow,int rowNumber) {
		Map map = new HashMap();
		map.put("l_pre", (pageNow-1)*rowNumber);
		map.put("l_after", rowNumber);
		List<KBean> list = kMapper.getAll(map);
		
		JSONArray listArray=(JSONArray) JSONArray.toJSON(list);

		System.out.println(listArray.toJSONString());
		
		StringBuilder sb = new StringBuilder("[");
		
		for(int i=0;i<list.size();i++) {
			KBean kb = list.get(i);
		     if(i==0) {
		    	 
		    	 sb.append("{\"name\":\""+kb.getName()+"\",\"id\":\""+kb.getId()+"\"}");
		    	 
		     }else {
		    	 sb.append(",{\"name\":\""+kb.getName()+"\",\"id\":\""+kb.getId()+"\"}");
		     }
		}
		sb.append("]");
		return listArray.toJSONString();//sb.toString();
	}

	@Override
	public int getRowCount() {
		
		return kMapper.getRowCount();
	}

}
