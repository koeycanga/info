/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 *  �����鱨ϵͳ
 */
package com.ichangyun.InforAnalyaizer.service.classificationfilterwords;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.model.classification.ClassificationInfoBean;
import com.ichangyun.InforAnalyaizer.model.classificationfilterwords.FilterWordsVo;

public interface FilterWordsService {
	//������ѯ���нڵ���˴���Ϣ
	public Map<String, Object> queryAllFilterWords(FilterWordsVo vo, int pageNow, int rowSize);
	//���ݽڵ�id��ѯ�ڵ���˴�
	public FilterWordsVo queryOne(String classificationId);
	//���½ڵ���˴���Ϣ
	public String updateFwVo(FilterWordsVo vo, User u);
	//���µ�ǰ�ڵ���ӽڵ�
	public List<FilterWordsVo> queryChild(FilterWordsVo vo);
	public HSSFWorkbook output(String[] ids, String realPath);
	public String input(List<FilterWordsVo> vos, String userid);
	
	//������еķ�����ϵ
	public List<ClassificationInfoBean> getAllClassificationNames();

}
