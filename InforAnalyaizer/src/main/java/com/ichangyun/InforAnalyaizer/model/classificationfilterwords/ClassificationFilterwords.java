package com.ichangyun.InforAnalyaizer.model.classificationfilterwords;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class ClassificationFilterwords {
    private String classificationId;

    private String informationtropism;

    private String createuser;
    @JSONField(format="yyyy-MM-dd")
    private Date createdatetime;

    private String updateuser;
    @JSONField(format="yyyy-MM-dd")
    private Date updatedatetime;

    public String getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(String classificationId) {
        this.classificationId = classificationId == null ? null : classificationId.trim();
    }

    public String getInformationtropism() {
        return informationtropism;
    }

    public void setInformationtropism(String informationtropism) {
        this.informationtropism = informationtropism == null ? null : informationtropism.trim();
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    public Date getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
    }

    public Date getUpdatedatetime() {
        return updatedatetime;
    }

    public void setUpdatedatetime(Date updatedatetime) {
        this.updatedatetime = updatedatetime;
    }

	public ClassificationFilterwords(String classificationId, String informationtropism, String createuser,
			Date createdatetime, String updateuser, Date updatedatetime) {

		this.classificationId = classificationId;
		this.informationtropism = informationtropism;
		this.createuser = createuser;
		this.createdatetime = createdatetime;
		this.updateuser = updateuser;
		this.updatedatetime = updatedatetime;
	}

	public ClassificationFilterwords() {
		
	}
    
}