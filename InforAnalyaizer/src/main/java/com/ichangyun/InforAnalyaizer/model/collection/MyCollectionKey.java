package com.ichangyun.InforAnalyaizer.model.collection;

public class MyCollectionKey {
    private String userId;

    private String collectiontypeId;

    private String articleId;

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

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId == null ? null : articleId.trim();
    }
}