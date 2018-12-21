/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.model.collection;

public class CollectionTypeKey {
    private String userId;

    private String collectiontypeId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCollectiontypeId() {
        return collectiontypeId;
    }

    public void setCollectiontypeId(String collectiontypeId) {
        this.collectiontypeId = collectiontypeId == null ? null : collectiontypeId.trim();
    }
}