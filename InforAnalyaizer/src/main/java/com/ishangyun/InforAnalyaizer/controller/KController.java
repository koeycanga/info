package com.ishangyun.InforAnalyaizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ishangyun.InforAnalyaizer.service.KService;

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
		
		int pageSize = 0;
		
		if(rowCount%rowNumber==0) {
			pageSize = rowCount/rowNumber;
		}else {
			pageSize = rowCount/rowNumber+1;
		}
		
		String resdata = kService.getAll(pageNow,rowNumber);
		
		String str = "{\"rowCount\":"+rowCount+",\"pageSize\":\""+pageSize+"\",\"resdata\":"+resdata+"}";//[{\"name\": \"Runoob\",\"id\":\"1\" }, {\"name\": \"Google\",\"id\":\"0\"}, {\"name\":\"Taobao\",\"id\":\"2\"}]
		
		return str;
	}
	
	@RequestMapping("/tz")
	public Object tz() {
		
		return  new ModelAndView("User");
	}
}
