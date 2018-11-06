package com.ichangyun.InforAnalyaizer.service.classificationfilterwords;

import java.util.List;
import java.util.Map;

import com.ichangyun.InforAnalyaizer.model.User;
import com.ichangyun.InforAnalyaizer.model.classificationfilterwords.FilterWordsVo;

public interface FilterWordsService {

	Map<String, Object> queryAllFilterWords(FilterWordsVo vo, int pageNow, int rowSize);

	FilterWordsVo queryOne(String classificationId);

	String updateFwVo(FilterWordsVo vo, User u);

	List<FilterWordsVo> queryChild(String parent_Classification_ID);

}
