/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.model.collection;

public class MyCollectionVo extends MyCollection{
	/*父实体类信息
	 * private Integer displayorder; 表示顺
	 * 
	 * private String userId;		用户Id
	 * 
	 * private String collectiontypeId;所属节点ID
	 * 
	 * private String articleId;		文章Id
	 * 
	 * */
	
	//从文章信息表中查询信息
	private String articleTitle;//文章标题
	
	private String releasetime;//发布时间
	
	private String id;			//此对象id（用户id+节点id+文章id）
	
	private String allparentname;
	
	

	public String getAllparentname() {
		return allparentname;
	}

	public void setAllparentname(String allparentname) {
		this.allparentname = allparentname;
	}

	public String getId() {
		if(null!=this.getUserId()&&null!=this.getCollectiontypeId()&&null!=this.getArticleId()) {
			
			this.id=this.getUserId()+"。"+this.getCollectiontypeId()+"。"+this.getArticleId();
			return id;
		}
		return null;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getReleasetime() {
		return releasetime;
	}

	public void setReleasetime(String releasetime) {
		this.releasetime = releasetime;
	}
	//使用id为主键属性赋值
	public void setKey(String id) {
		String[] ids = id.split("。");
		this.setUserId(ids[0]);
		this.setCollectiontypeId(ids[1]);
		this.setArticleId(ids[2]);
	}
}
