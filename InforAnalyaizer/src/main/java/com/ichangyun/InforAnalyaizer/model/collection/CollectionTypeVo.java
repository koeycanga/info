package com.ichangyun.InforAnalyaizer.model.collection;

import java.util.ArrayList;
import java.util.List;

public class CollectionTypeVo extends CollectionType{
	private Integer isParent = 0;//�Ƿ�Ϊ���ڵ㣬1�ǣ�0����
	private List<CollectionTypeVo> children = new ArrayList<>();//�ӽڵ㼯��
	private boolean is_show=false;			//�Ƿ���ʾ
	
/*	������Ϣ
 * private String collectiontypename; 	�ڵ���

    private String parentCollectiontypeId;���ڵ�ID

    private Integer displayorder;		��ʾ˳

    private String validflag;			��Чflag  0��Ч   1��Ч
    
	private String userId;				�û�ID

    private String collectiontypeId;	�ڵ�ID

 * */
	public Integer getIsParent() {
		return isParent;
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
