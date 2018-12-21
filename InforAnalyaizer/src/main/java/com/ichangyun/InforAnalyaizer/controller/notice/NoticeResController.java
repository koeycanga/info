/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 *  �����鱨ϵͳ
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
 * Controller���򱨽��
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
     * queryAll����ʱ�������ѯ���м򱨽��
     *
     * @param BaseBean ��ҳ������
     * @return map
     */
	@RequestMapping("/queryAll")
	@ResponseBody
	public Map<String, Object> queryAll(BaseBean baseBean){
	   Map<String, Object> list = noticeResService.queryAll(baseBean.getPageNow(),baseBean.getRowSize());
	       return list;
	   }
    /**
     * toNoticeRes����ת�����ҳ
     *
     * @return String
     */
	//��ת�򱨽��ҳ��
	@RequestMapping("/toNoticeRes")
	public String toNoticeRes(){
		return "frontpage/noticeRes";
	}
}
