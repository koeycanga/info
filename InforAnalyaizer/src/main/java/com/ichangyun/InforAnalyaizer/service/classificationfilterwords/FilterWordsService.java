package com.ichangyun.InforAnalyaizer.service.classificationfilterwords;

import java.util.List;
import java.util.Map;

import com.ichangyun.InforAnalyaizer.model.User;
import com.ichangyun.InforAnalyaizer.model.classificationfilterwords.FilterWordsVo;

public interface FilterWordsService {
	//条件查询所有节点过滤词信息
	Map<String, Object> queryAllFilterWords(FilterWordsVo vo, int pageNow, int rowSize);
	//根据节点id查询节点过滤词
	FilterWordsVo queryOne(String classificationId);
	//更新节点过滤词信息
	String updateFwVo(FilterWordsVo vo, User u);
	//查新当前节点的子节点
	List<FilterWordsVo> queryChild(String parent_Classification_ID);

}
