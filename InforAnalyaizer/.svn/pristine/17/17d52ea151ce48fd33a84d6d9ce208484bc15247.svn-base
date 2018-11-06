package com.ichangyun.InforAnalyaizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ichangyun.InforAnalyaizer.service.KService;

@RestController
@RequestMapping("/koey")
public class KController {

	@Autowired
	private KService kService;
	
	@RequestMapping("/hello")
	@ResponseBody
	public Object sayhello(int id) {
		System.out.println("id="+id);
		kService.sayhello();
		return "asddasok";
	}
	
	@RequestMapping("/vue")
	public Object vue(String searchinfo,int pageNow,int rowNumber) {
		
		System.out.println(searchinfo+" pageNow="+pageNow);
		
		int rowCount = kService.getRowCount();
		
		
		String resdata = kService.getAll(pageNow,rowNumber);
		
		String str = "{\"rowCount\":"+rowCount+",\"resdata\":"+resdata+"}";//[{\"name\": \"Runoob\",\"id\":\"1\" }, {\"name\": \"Google\",\"id\":\"0\"}, {\"name\":\"Taobao\",\"id\":\"2\"}]
		
		return str;
	}
	
	@RequestMapping("/tz")
	public Object tz() {
		
		return  new ModelAndView("User");
	}
}
