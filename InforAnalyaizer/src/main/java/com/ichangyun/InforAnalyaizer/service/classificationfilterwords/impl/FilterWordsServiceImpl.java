package com.ichangyun.InforAnalyaizer.service.classificationfilterwords.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ichangyun.InforAnalyaizer.mapper.classificationfilterwords.ClassificationFilterwordsMapper;
import com.ichangyun.InforAnalyaizer.model.User;
import com.ichangyun.InforAnalyaizer.model.classificationfilterwords.ClassificationFilterwordsWithBLOBs;
import com.ichangyun.InforAnalyaizer.model.classificationfilterwords.FilterWordsVo;
import com.ichangyun.InforAnalyaizer.service.classificationfilterwords.FilterWordsService;

@Service
public class FilterWordsServiceImpl implements FilterWordsService {
	@Autowired
	private ClassificationFilterwordsMapper fwMapper;

	@Override
	public Map<String, Object> queryAllFilterWords(FilterWordsVo vo, int pageNow, int rowSize) {
		// TODO Auto-generated method stub
		Map<String, Object> key = new HashMap<>();
		int l_pre = (pageNow - 1) * rowSize;
		key.put("l_pre", l_pre);
		key.put("rowSize", rowSize);
		key.put("classificationName", vo.getClassificationName());
		List<FilterWordsVo> fwVos = this.fwMapper.selectByFwVo(key);
		Map<String, Object> res = new HashMap<>();
		int count = this.fwMapper.queryCount(key);
		List<String> list = this.fwMapper.queryParents();
		for (String p : list) {
			for (FilterWordsVo fwvo : fwVos) {
				if(fwvo.getClassificationId().equals(p)) {
					fwvo.setIsParent(1);
				}else {
					
				}
			}
		}
		res.put("fwVos", fwVos);
		res.put("rowCount", count);
		return res;
	}

	@Override
	public FilterWordsVo queryOne(String classificationId) {
		// TODO Auto-generated method stub
		return this.fwMapper.queryOne(classificationId);
	}

	@Override
	public String updateFwVo(FilterWordsVo vo, User u) {
		// TODO Auto-generated method stub
		Date t = new Date();
		Date time= new java.sql.Date(t.getTime());
		Map<String, Object> key= new HashMap<>();
		ClassificationFilterwordsWithBLOBs bs = this.fwMapper.selectByPrimaryKey(vo.getClassificationId());
		vo.setUpdatedatetime(time);
		vo.setUpdateuser(u.getUser_ID());
		try {
			if(null!=bs) {
				this.fwMapper.updateFwVo(vo);			
			}else {
				this.fwMapper.insertSelective(vo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return "fault";
		}
		return "ok";
	}

	@Override
	public List<FilterWordsVo> queryChild(String parent_Classification_ID) {
		// TODO Auto-generated method stub
		List<FilterWordsVo> fwVos = this.fwMapper.queryChild(parent_Classification_ID);
		List<String> list = this.fwMapper.queryParents();
		for (String p : list) {
			for (FilterWordsVo fwvo : fwVos) {
				if(fwvo.getClassificationId().equals(p)) {
					fwvo.setIsParent(1);
				}else {
				}
			}
		}
		return fwVos;
	}
	
}
