package com.ichangyun.InforAnalyaizer.model.collection;

public class MyCollectionVo extends MyCollection{
	/*��ʵ������Ϣ
	 * private Integer displayorder; ��ʾ˳
	 * 
	 * private String userId;		�û�Id
	 * 
	 * private String collectiontypeId;�����ڵ�ID
	 * 
	 * private String articleId;		����Id
	 * 
	 * */
	
	//��������Ϣ���в�ѯ��Ϣ
	private String articleTitle;//���±���
	
	private String releasetime;//����ʱ��
	
	private String id;			//�˶���id���û�id+�ڵ�id+����id��
	
	private String allparentname;
	
	

	public String getAllparentname() {
		return allparentname;
	}

	public void setAllparentname(String allparentname) {
		this.allparentname = allparentname;
	}

	public String getId() {
		if(null!=this.getUserId()&&null!=this.getCollectiontypeId()&&null!=this.getArticleId()) {
			
			this.id=this.getUserId()+"��"+this.getCollectiontypeId()+"��"+this.getArticleId();
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
	//ʹ��idΪ�������Ը�ֵ
	public void setKey(String id) {
		String[] ids = id.split("��");
		this.setUserId(ids[0]);
		this.setCollectiontypeId(ids[1]);
		this.setArticleId(ids[2]);
	}
}
