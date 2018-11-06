package com.ichangyun.InforAnalyaizer.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Validated
@Service
public class ValidatorService {

	 public String checkUser(@NotEmpty(message = "用户名不能为空")  String username) throws ConstraintViolationException {
		return "User [username=" + username + ", password= ]";
	}

}
