/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系
 */
package com.ichangyun.InforAnalyaizer.mapper.userInfoMapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.model.classification.ClassificationInfoBean;

/**
 * UserMapper
 * @author renhao
 * @date 2018/11/09
 */
public interface UserMapper {

    /**
     * User存在性Check
     * @param param SQL文用的parameter
     * @return user 用户情报
     */
    User isUserExist(@Param("param")Map param);

    /**
     * User_ID对应的姓名取得
     * @param param SQL文用的parameter
     * @return user 用户的姓名
     */
    String getName(@Param("param")Map param);

}
