/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.controller.usermanage;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ichangyun.InforAnalyaizer.model.CommBean;
import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.model.usermanage.RoleManageBean;
import com.ichangyun.InforAnalyaizer.service.common.service.DBUpdateCheckService;
import com.ichangyun.InforAnalyaizer.service.usermanage.RoleService;

/**
 * 角色管理Controll
 * @author renh
 * Date:2018-11-9
 */
@RestController
@RequestMapping("/yhgl/role")
public class RoleController {

    //角色管理service
    @Autowired
    private RoleService roleService;

    @Autowired
    private DBUpdateCheckService dbUpdateCheckService;

    /**
     * 进入角色管理页面
     * @return
     * Date:2018-11-9
     */
    @RequestMapping("/index")
    @ResponseBody
    public Object index() {
        return new ModelAndView("usermanage/rolemanage");
    }


    /**
     * 查询角色
     * @param pageNow 当前页
     * @param rowSize 每页显示条目数
     * @return rowCount 角色总条目数    resdata  当前页角色信息的json字面量
     * Date:2018-11-9
     */
    @RequestMapping("/search")
    public Object search(int pageNow,int rowSize) {

        int rowCount = roleService.getRoleCount();

        String json_res = roleService.getRole(pageNow,rowSize);

        String res = "{\"rowCount\":\""+rowCount+"\",\"resdata\":"+json_res+"}";

        return res;
    }

    /**
     * 添加角色
     * @param ub       添加角色信息
     * @param session
     * @return 返回结果  ok:成功     exist:角色名称已存在     nok:异常
     * Date:2018-11-9
     */
    @RequestMapping("/addnewrole")
    public Object AddNewRole(@Validated RoleManageBean ub,BindingResult result,HttpSession session) {
        String res = "ok";
        if(result.hasErrors()) {
            /*List<ObjectError> allErrors = result.getAllErrors();
			for(ObjectError oe:allErrors) {
				System.out.println(oe.getDefaultMessage());
			}*/
            res = "nok";
        }else {
            if(roleService.exist(ub.getUserRoleName())) {
                res = "exist";
            }else {
                User user = (User) session.getAttribute(CommBean.SESSION_NAME);
                if(!roleService.AddNewRole(ub.getUserRoleName(),ub.getDescription(),user.getUser_ID(),ub.getAuthority())) {
                    res = "nok";
                }
            }
        }
        return res;
    }

    /**
     * 修改角色
     * @param ub
     * @param result
     * @param session
     * @return  ok:成功      nok:异常
     */
    @RequestMapping("/updaterole")
    public Object UpdateRole(@Validated RoleManageBean ub,BindingResult result,HttpSession session) {
        String res = "ok";
        if(result.hasErrors()) {
            res = "nok";
        }else {
            User user = (User) session.getAttribute(CommBean.SESSION_NAME);
            ub.setUpdateUser(user.getUser_ID());
            List<String> paramList = new ArrayList<String>();
        	paramList.add(ub.getUserRole_ID());
        	if(!dbUpdateCheckService.DBUpdateCheck("2", paramList, ub.getUpdateDateTime())) {
        		res = "already update";
        	}else {
	            if(!roleService.updateRole(ub)) {
	                res = "nok";
	            }
        	}
        }
        return res;
    }


    /**
     * 删除角色
     * @param map 要删除的角色ID集合
     * @return ok:成功      nok:异常
     * Date:2018-11-9
     */
    @RequestMapping("/delrole")
    public Object delrole(@RequestBody Map map) {
        String res = "ok";
        String json = (String) map.get("json");
        if(json==null||"".equals(json)) {
            res = "nok";
        }else {
            if(!roleService.delRole(json)) {
                res = "nok";
            }
        }
        return res;
    }
}
