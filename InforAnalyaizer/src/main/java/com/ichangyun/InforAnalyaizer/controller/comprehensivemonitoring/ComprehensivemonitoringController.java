package com.ichangyun.InforAnalyaizer.controller.comprehensivemonitoring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * �ۺϼ�������
 * @author renhao
 * Date:2018-11-12
 */
@RestController
@RequestMapping("/comprehensivemonitoring")
public class ComprehensivemonitoringController {

	/**
	 * �����ۺϼ��ҳ��
	 * @return
	 */
	@RequestMapping("/index")
	@ResponseBody
	public Object index() {
		
		return new ModelAndView("frontpage/comprehensivemonitoring");
	}
}
