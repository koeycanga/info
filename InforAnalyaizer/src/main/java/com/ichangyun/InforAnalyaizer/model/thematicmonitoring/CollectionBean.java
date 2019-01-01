/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.model.thematicmonitoring;

import com.ichangyun.InforAnalyaizer.model.BaseBean;

/**
 * 收藏信息相关实体类
 * 对应数据表 m_collectiontype 和    m_collectionfield
 * @author renhao
 * @Date:2018-11-14 10:29
 */
public class CollectionBean extends BaseBean {

    /**
     * 用户ID
     */
    private String User_ID;

    /**
     * 收藏区分ID
     */
    private String CollectionType_ID;


    /**
     * 收藏区分名称
     */
    private String CollectionTypeName;

    /**
     * 父收藏区分ID
     */
    private String Parent_CollectionType_ID;

    /**
     * 表示顺
     */
    private int DisplayOrder;


    /**
     * 有效flag 0无效，1：有效
     */
    private String ValidFlag;

    /**
     * 作成者
     */
    private String CreateUser;

    /**
     * 作成日時
     */
    private String CreateDateTime;

    /**
     * 更新者
     */
    private String UpdateUser;

    /**
     * 更新日時
     */
    private String UpdateDateTime;

    /**
     * 子节点数目
     */
    private int children_lg;

    /**
     * 文章ID
     */
    private String Article_ID;

    public String getArticle_ID() {
        return Article_ID;
    }
    public void setArticle_ID(String article_ID) {
        Article_ID = article_ID;
    }
    public int getChildren_lg() {
        return children_lg;
    }
    public void setChildren_lg(int children_lg) {
        this.children_lg = children_lg;
    }
    public String getUser_ID() {
        return User_ID;
    }
    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }
    public String getCollectionType_ID() {
        return CollectionType_ID;
    }
    public void setCollectionType_ID(String collectionType_ID) {
        CollectionType_ID = collectionType_ID;
    }
    public String getCollectionTypeName() {
        return CollectionTypeName;
    }
    public void setCollectionTypeName(String collectionTypeName) {
        CollectionTypeName = collectionTypeName;
    }
    public String getParent_CollectionType_ID() {
        return Parent_CollectionType_ID;
    }
    public void setParent_CollectionType_ID(String parent_CollectionType_ID) {
        Parent_CollectionType_ID = parent_CollectionType_ID;
    }
    public int getDisplayOrder() {
        return DisplayOrder;
    }
    public void setDisplayOrder(int displayOrder) {
        DisplayOrder = displayOrder;
    }
    public String getValidFlag() {
        return ValidFlag;
    }
    public void setValidFlag(String validFlag) {
        ValidFlag = validFlag;
    }
    public String getCreateUser() {
        return CreateUser;
    }
    public void setCreateUser(String createUser) {
        CreateUser = createUser;
    }
    public String getCreateDateTime() {
        return CreateDateTime;
    }
    public void setCreateDateTime(String createDateTime) {
        CreateDateTime = createDateTime;
    }
    public String getUpdateUser() {
        return UpdateUser;
    }
    public void setUpdateUser(String updateUser) {
        UpdateUser = updateUser;
    }
    public String getUpdateDateTime() {
        return UpdateDateTime;
    }
    public void setUpdateDateTime(String updateDateTime) {
        UpdateDateTime = updateDateTime;
    }
}
