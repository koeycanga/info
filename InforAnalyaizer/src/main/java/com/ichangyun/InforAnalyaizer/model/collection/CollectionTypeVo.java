/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.model.collection;

import java.util.ArrayList;
import java.util.List;

public class CollectionTypeVo extends CollectionType{
	private Integer isParent = 0;//是否为父节点，1是，0不是
	private List<CollectionTypeVo> children = new ArrayList<>();//子节点集合
	private boolean is_show=false;			//是否显示
	private String ppath = "";			//父节点path
	private String childrenNum;			//有多少子节点，方便加表示顺序
/*	父类信息
 * private String collectiontypename; 	节点名

    private String parentCollectiontypeId;父节点ID

    private Integer displayorder;		表示顺

    private String validflag;			有效flag  0无效   1有效
    
	private String userId;				用户ID

    private String collectiontypeId;	节点ID

 * */
	
	public Integer getIsParent() {
		return isParent;
	}
	public String getChildrenNum() {
		return childrenNum;
	}
	public void setChildrenNum(String childrenNum) {
		this.childrenNum = childrenNum;
	}
	public String getPpath() {
		return ppath;
	}
	public void setPpath(String ppath) {
		this.ppath = ppath;
	}
	public void setIsParent(Integer isParent) {
		this.isParent = isParent;
	}
	public List<CollectionTypeVo> getChildren() {
		return children;
	}
	public void setChildren(List<CollectionTypeVo> children) {
		this.children = children;
	}
	public boolean isIs_show() {
		return is_show;
	}
	public void setIs_show(boolean is_show) {
		this.is_show = is_show;
	}
	
}
