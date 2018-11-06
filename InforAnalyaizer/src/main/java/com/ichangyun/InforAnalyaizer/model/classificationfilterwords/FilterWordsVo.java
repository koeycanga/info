package com.ichangyun.InforAnalyaizer.model.classificationfilterwords;

import java.util.Date;

import com.ichangyun.InforAnalyaizer.model.BaseBean;

public class FilterWordsVo extends ClassificationFilterwordsWithBLOBs{


	private String classificationName;
	private String parent_Classification_ID;
	private String parent_name;
	private int displayOrder;
	private int isParent;


	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getParent_name() {
		return parent_name;
	}

	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}

	public String getClassificationName() {
		return classificationName;
	}

	public void setClassificationName(String classificationName) {
		this.classificationName = classificationName;
	}

	public String getParent_Classification_ID() {
		return parent_Classification_ID;
	}

	public void setParent_Classification_ID(String parent_Classification_ID) {
		this.parent_Classification_ID = parent_Classification_ID;
	}


	public int getIsParent() {
		return isParent;
	}

	public void setIsParent(int isParent) {
		this.isParent = isParent;
	}

	public FilterWordsVo(String classificationId, String informationtropism, String createuser, Date createdatetime,
			String updateuser, Date updatedatetime, String allcorephrases, String allexcludephrases,
			String titlecorephrases, String titleexcludephrases, String summarycorephrases,
			String summaryexcludephrases, String textcorephrases, String textexcludephrases, BaseBean bb,
			String classificationName,String parent_Classification_ID,String parent_name,int displayOrder) {
		super(classificationId, informationtropism, createuser, createdatetime, updateuser, updatedatetime,
				allcorephrases, allexcludephrases, titlecorephrases, titleexcludephrases, summarycorephrases,
				summaryexcludephrases, textcorephrases, textexcludephrases);
		this.classificationName = classificationName;
		this.parent_Classification_ID = parent_Classification_ID;
		this.parent_name = parent_name;
		this.displayOrder = displayOrder;
	}

	public FilterWordsVo() {
		super();
	}
	

	

}
