/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.service;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class ValidatorService {

    public String checkUser(@NotEmpty(message = "用户名不能为空")  String username)
            throws ConstraintViolationException {
        return "User [username=" + username + ", password= ]";
    }

}
