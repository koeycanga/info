package com.ishangyun.InforAnalyaizer.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
 
/**
 * created by viking on 2018/07/04
 * controller²ã½Ó¿ÚÀà
 */
@RestController
@RequestMapping("/aaa")
public class AAAController {
    Logger log = Logger.getLogger(AAAController.class);
 
    @RequestMapping("/hello")
    @ResponseBody
    public Object userTest(){
        return "hello body.";
    }
 
}

