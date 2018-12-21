/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.controller.notice;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ichangyun.InforAnalyaizer.model.BaseBean;
import com.ichangyun.InforAnalyaizer.service.notice.NoticeResService;
/**
 * Controller：简报结果
 *
 * @author lan
 * @date 2018/11/19
 */
@Controller
@RequestMapping("/noticeRes")
public class NoticeResController {
	@Autowired
	private NoticeResService noticeResService;
    /**
     * queryAll：按时间排序查询所有简报结果
     *
     * @param BaseBean 分页工具类
     * @return map
     */
	@RequestMapping("/queryAll")
	@ResponseBody
	public Map<String, Object> queryAll(BaseBean baseBean){
	   Map<String, Object> list = noticeResService.queryAll(baseBean.getPageNow(),baseBean.getRowSize());
	       return list;
	   }
    /**
     * toNoticeRes：跳转到结果页
     *
     * @return String
     */
	//跳转简报结果页面
	@RequestMapping("/toNoticeRes")
	public String toNoticeRes(){
		return "frontpage/noticeRes";
	}
}
