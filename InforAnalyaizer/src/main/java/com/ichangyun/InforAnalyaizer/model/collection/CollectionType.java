/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.model.collection;

/**
 * 收藏夹情报
 * @author ichangyun
 */
public class CollectionType extends CollectionTypeKey {
    // 收藏夹名称
    private String collectiontypename;
    // 父收藏夹ID
    private String parentCollectiontypeId;
    // 表示顺
    private Integer displayorder;
    // 有效Flag
    private String validflag;
    // 作成者
    private String createuser;
    // 作成日时
    private String createdatetime;
    // 更新者
    private String updateuser;
    // 更新日时
    private String updatedatetime;
    // 收藏夹Path
    private String collectionpath;
    // 收藏夹阶层
    private int collectionstratum;

    /**
     * 取得收藏夹Path
     * @return
     */
    public String getCollectionpath() {
        return collectionpath;
    }

    /**
     * 设定收藏夹Path
     * @param collectionpath
     */
    public void setCollectionpath(String collectionpath) {
        this.collectionpath = collectionpath;
    }

    /**
     * 取得收藏夹阶层
     * @return
     */
    public int getCollectionstratum() {
        return collectionstratum;
    }

    /**
     * 设定收藏夹阶层
     * @param collectionstratum
     */
    public void setCollectionstratum(int collectionstratum) {
        this.collectionstratum = collectionstratum;
    }

    /**
     * 取得收藏夹名称
     * @return
     */
    public String getCollectiontypename() {
        return collectiontypename;
    }

    /**
     * 设定收藏夹名称
     * @param collectiontypename
     */
    public void setCollectiontypename(String collectiontypename) {
        this.collectiontypename = collectiontypename == null ? null : collectiontypename.trim();
    }

    /**
     * 取得父收藏夹ID
     * @return
     */
    public String getParentCollectiontypeId() {
        return parentCollectiontypeId;
    }

    /**
     * 设定父收藏夹ID
     * @param parentCollectiontypeId
     */
    public void setParentCollectiontypeId(String parentCollectiontypeId) {
        this.parentCollectiontypeId = parentCollectiontypeId == null ? null : parentCollectiontypeId.trim();
    }

    /**
     * 取得表示顺
     * @return
     */
    public Integer getDisplayorder() {
        return displayorder;
    }

    /**
     * 设定表示顺
     * @param displayorder
     */
    public void setDisplayorder(Integer displayorder) {
        this.displayorder = displayorder;
    }

    /**
     * 取得有效Flag
     * @return
     */
    public String getValidflag() {
        return validflag;
    }

    /**
     * 设定有效Flag
     * @param validflag
     */
    public void setValidflag(String validflag) {
        this.validflag = validflag == null ? null : validflag.trim();
    }

    /**
     * 取得作成者
     * @return
     */
    public String getCreateuser() {
        return createuser;
    }

    /**
     * 设定作成者
     * @param createuser
     */
    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    /**
     * 取得作成日时
     * @return
     */
    public String getCreatedatetime() {
        return createdatetime;
    }

    /**
     * 设定作成日时
     * @param createdatetime
     */
    public void setCreatedatetime(String createdatetime) {
        this.createdatetime = createdatetime;
    }

    /**
     * 取得更新者
     * @return
     */
    public String getUpdateuser() {
        return updateuser;
    }

    /**
     * 设定更新者
     * @param updateuser
     */
    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
    }

    /**
     * 取得更新日时
     * @return
     */
    public String getUpdatedatetime() {
        return updatedatetime;
    }

    /**
     * 设定更新日时
     * @param updatedatetime
     */
    public void setUpdatedatetime(String updatedatetime) {
        this.updatedatetime = updatedatetime;
    }
}