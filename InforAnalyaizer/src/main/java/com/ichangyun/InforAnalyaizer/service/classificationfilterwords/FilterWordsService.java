package com.ichangyun.InforAnalyaizer.service.classificationfilterwords;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.model.classificationfilterwords.FilterWordsVo;

public interface FilterWordsService {
	//条件查询所有节点过滤词信息
	Map<String, Object> queryAllFilterWords(FilterWordsVo vo, int pageNow, int rowSize);
	//根据节点id查询节点过滤词
	FilterWordsVo queryOne(String classificationId);
	//更新节点过滤词信息
	String updateFwVo(FilterWordsVo vo, User u);
	//查新当前节点的子节点
	List<FilterWordsVo> queryChild(FilterWordsVo vo);
	HSSFWorkbook output(String[] ids);
	String input(List<FilterWordsVo> vos);

}
