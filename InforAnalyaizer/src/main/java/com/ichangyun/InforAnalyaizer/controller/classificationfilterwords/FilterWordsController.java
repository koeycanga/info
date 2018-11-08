package com.ichangyun.InforAnalyaizer.controller.classificationfilterwords;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ichangyun.InforAnalyaizer.model.BaseBean;
import com.ichangyun.InforAnalyaizer.model.CommBean;
import com.ichangyun.InforAnalyaizer.model.User;
import com.ichangyun.InforAnalyaizer.model.classificationfilterwords.FilterWordsVo;
import com.ichangyun.InforAnalyaizer.service.classificationfilterwords.FilterWordsService;

@Controller
@RequestMapping("/filterWords")
public class FilterWordsController {
	@Autowired
	private FilterWordsService fwService;
	
	
	@RequestMapping("/toFilterWords")
	public String toList() {
		return "filterWords/filterWords";
	}
	@ResponseBody
	@RequestMapping("/queryAll")// ��ѯ�����û���Ϣ���ɴ�����
	public Map<String, Object> queryAll(FilterWordsVo vo, BaseBean bb) {
		Map<String, Object> list = this.fwService.queryAllFilterWords(vo, bb.getPageNow(), bb.getRowSize());
		return list;
	}
	@ResponseBody
	@RequestMapping("/queryOne") // ��ѯ�����û���Ϣ���ɴ�����
	public FilterWordsVo queryOne(String classificationId) {
		FilterWordsVo vo = this.fwService.queryOne(classificationId);
		return vo;
	}
	@ResponseBody
	@RequestMapping("/updateFwVo") // �����û���Ϣ
	public Object updateFwVo(FilterWordsVo vo, HttpSession session) {
		User u = (User) session.getAttribute(CommBean.SESSION_NAME);
		Map<String, String> map = new HashMap<>();
		String value = this.fwService.updateFwVo(vo, u);
		map.put("msg", value);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/queryChild") // ��ѯ�����û���Ϣ���ɴ�����
	public List<FilterWordsVo> queryChild(String parent_Classification_ID) {
		List<FilterWordsVo> children = this.fwService.queryChild(parent_Classification_ID);
		return children;
	}
}