/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.service.classificationfilterwords;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.model.classification.ClassificationInfoBean;
import com.ichangyun.InforAnalyaizer.model.classificationfilterwords.FilterWordsVo;

public interface FilterWordsService {
	//条件查询所有节点过滤词信息
	public Map<String, Object> queryAllFilterWords(FilterWordsVo vo, int pageNow, int rowSize);
	//根据节点id查询节点过滤词
	public FilterWordsVo queryOne(String classificationId);
	//更新节点过滤词信息
	public String updateFwVo(FilterWordsVo vo, User u);
	//查新当前节点的子节点
	public List<FilterWordsVo> queryChild(FilterWordsVo vo);
	public HSSFWorkbook output(String[] ids, String realPath);
	public String input(List<FilterWordsVo> vos, String userid);
	
	//获得所有的分类体系
	public List<ClassificationInfoBean> getAllClassificationNames();

}
