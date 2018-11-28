package com.ichangyun.InforAnalyaizer.service.classificationfilterwords;

import java.util.List;
import java.util.Map;

import com.ichangyun.InforAnalyaizer.model.User;
import com.ichangyun.InforAnalyaizer.model.classificationfilterwords.FilterWordsVo;

public interface FilterWordsService {
	//������ѯ���нڵ���˴���Ϣ
	Map<String, Object> queryAllFilterWords(FilterWordsVo vo, int pageNow, int rowSize);
	//���ݽڵ�id��ѯ�ڵ���˴�
	FilterWordsVo queryOne(String classificationId);
	//���½ڵ���˴���Ϣ
	String updateFwVo(FilterWordsVo vo, User u);
	//���µ�ǰ�ڵ���ӽڵ�
	List<FilterWordsVo> queryChild(FilterWordsVo vo);

}
