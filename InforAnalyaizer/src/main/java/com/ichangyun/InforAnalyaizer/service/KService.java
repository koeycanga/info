package com.ichangyun.InforAnalyaizer.service;

import java.util.List;

import com.ichangyun.InforAnalyaizer.model.KBean;

public interface KService {

	public void sayhello();
	
	public String getAll(int pageNow,int rowNumber);

	public int getRowCount();
}
