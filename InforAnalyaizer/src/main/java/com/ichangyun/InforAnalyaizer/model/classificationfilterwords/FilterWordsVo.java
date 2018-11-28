package com.ichangyun.InforAnalyaizer.model.classificationfilterwords;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ichangyun.InforAnalyaizer.model.BaseBean;

public class FilterWordsVo extends ClassificationFilterwordsWithBLOBs{


	private String classificationName;					//�ڵ�����
	private String parent_Classification_ID;			//���ڵ�id
	private String parent_name;							//���ڵ�����
	private int displayOrder;							//��ʾ˳
	private int isParent;								//�ж��Ƿ�Ϊ���ڵ�
	private boolean is_show=false;						//�˽ڵ��Ƿ���ʾ
	private List<FilterWordsVo> children = new ArrayList<>();

	public List<FilterWordsVo> getChildren() {
		return children;
	}

	public void setChildren(List<FilterWordsVo> children) {
		this.children = children;
	}

	public boolean isIs_show() {
		return is_show;
	}
	public void setIs_show(boolean is_show) {
		this.is_show = is_show;
	}



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
			String updateuser, String updatedatetime, String allcorephrases, String allexcludephrases,
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
