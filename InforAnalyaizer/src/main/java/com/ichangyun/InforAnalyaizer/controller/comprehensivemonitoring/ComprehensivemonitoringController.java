package com.ichangyun.InforAnalyaizer.controller.comprehensivemonitoring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * ×ÛºÏ¼à²â¿ØÖÆÆ÷
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/comprehensivemonitoring")
public class ComprehensivemonitoringController {

	@RequestMapping("/index")
	@ResponseBody
	public Object index() {
		
		return new ModelAndView("frontpage/comprehensivemonitoring");
	}
}
