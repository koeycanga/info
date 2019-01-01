/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.model.userInfo;

/**
 * UserInfoKey：设置用户情报Key
 *
 * @author ichangyun
 * @date 2018/11/09
 */
public class UserInfoKey {

    // 用户ID（用户名）
    private String userId;

    // 用户No
    private Integer userno;

    /** 取得用户ID  */
    public String getUserId() {
        return userId;
    }

    /** 设置用户ID  */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /** 取得用户No  */
    public Integer getUserno() {
        return userno;
    }

    /** 设置用户ID  */
    public void setUserno(Integer userno) {
        this.userno = userno;
    }

    /** 设置用户情报Key：userId， userno */
    public UserInfoKey(String userId, Integer userno) {
        super();
        this.userId = userId;
        this.userno = userno;
    }

    public UserInfoKey() {
        super();
    }

}