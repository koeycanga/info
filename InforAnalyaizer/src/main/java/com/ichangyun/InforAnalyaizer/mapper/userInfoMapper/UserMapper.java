/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 * �����鱨����ϵ
 */
package com.ichangyun.InforAnalyaizer.mapper.userInfoMapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ichangyun.InforAnalyaizer.model.User;
import com.ichangyun.InforAnalyaizer.model.classification.ClassificationInfoBean;

/**
 * UserMapper
 * @author renhao
 * @date 2018/11/09
 */
public interface UserMapper {

    /**
     * User������Check
     * @param param SQL���õ�parameter
     * @return user �û��鱨
     */
    User isUserExist(@Param("param")Map param);

    /**
     * User_ID��Ӧ������ȡ��
     * @param param SQL���õ�parameter
     * @return user �û�������
     */
    String getName(@Param("param")Map param);

}
