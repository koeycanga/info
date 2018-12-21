/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.model.collection;

import java.util.Date;

public class CollectionType extends CollectionTypeKey {
    private String collectiontypename;

    private String parentCollectiontypeId;

    private Integer displayorder;

    private String validflag;

    private String createuser;

    private Date createdatetime;

    private String updateuser;

    private String updatedatetime;
    
    private String collectionpath;
    
    private int collectionstratum;

    public String getCollectionpath() {
		return collectionpath;
	}

	public void setCollectionpath(String collectionpath) {
		this.collectionpath = collectionpath;
	}

	public int getCollectionstratum() {
		return collectionstratum;
	}

	public void setCollectionstratum(int collectionstratum) {
		this.collectionstratum = collectionstratum;
	}

	public String getCollectiontypename() {
        return collectiontypename;
    }

    public void setCollectiontypename(String collectiontypename) {
        this.collectiontypename = collectiontypename == null ? null : collectiontypename.trim();
    }

    public String getParentCollectiontypeId() {
        return parentCollectiontypeId;
    }

    public void setParentCollectiontypeId(String parentCollectiontypeId) {
        this.parentCollectiontypeId = parentCollectiontypeId == null ? null : parentCollectiontypeId.trim();
    }

    public Integer getDisplayorder() {
        return displayorder;
    }

    public void setDisplayorder(Integer displayorder) {
        this.displayorder = displayorder;
    }

    public String getValidflag() {
        return validflag;
    }

    public void setValidflag(String validflag) {
        this.validflag = validflag == null ? null : validflag.trim();
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

    public String getUpdatedatetime() {
        return updatedatetime;
    }

    public void setUpdatedatetime(String updatedatetime) {
        this.updatedatetime = updatedatetime;
    }
}