/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.model.collection;

/**
 * 收藏夹情报Key
 * @author ichangyun
 */
public class CollectionTypeKey {
    // 用户ID
    private String userId;
    // 收藏夹ID
    private String collectiontypeId;

    /**
     * 取得用户ID
     * @return
     */
    public String getUserId() {
        return userId;
    }
    /**
     * 设定用户ID
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 取得收藏夹ID
     * @param userId
     */
    public String getCollectiontypeId() {
        return collectiontypeId;
    }
    /**
     * 设定收藏夹ID
     * @param userId
     */
    public void setCollectiontypeId(String collectiontypeId) {
        this.collectiontypeId = collectiontypeId == null ? null : collectiontypeId.trim();
    }
}