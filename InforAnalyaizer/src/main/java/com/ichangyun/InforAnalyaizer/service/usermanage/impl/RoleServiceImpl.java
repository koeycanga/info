/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.service.usermanage.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ichangyun.InforAnalyaizer.mapper.usermanage.RoleMapper;
import com.ichangyun.InforAnalyaizer.model.CommBean;
import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.model.usermanage.RoleManageBean;
import com.ichangyun.InforAnalyaizer.service.numberingcontrol.NumberingcontrolService;
import com.ichangyun.InforAnalyaizer.service.usermanage.RoleService;

/**
 * 角色管理service 实现类
 * @author renhao
 * @Date:2018-11-9
 */
@Service
public class RoleServiceImpl implements RoleService {

    //角色管理mapper
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private NumberingcontrolService numberingcontrolService;

    @Override
    public int getRoleCount(HttpSession session) {
    	User u = (User) session.getAttribute(CommBean.SESSION_NAME);
    	RoleManageBean rb = new RoleManageBean();
        rb.setCollectionField_ID(u.getCollectionField_ID());
        rb.setCustomer_ID(u.getCustomer_ID());
        return roleMapper.getRoleCount(rb);
    }

    @Override
    public String getRole(int pageNow, int rowSize,HttpSession session) {

        Map map = new HashMap();
        int l_pre = (pageNow-1)*rowSize ;
        map.put("l_pre",l_pre);
        map.put("rowSize", rowSize);
        
        User u = (User) session.getAttribute(CommBean.SESSION_NAME);
        map.put("CollectionField_ID", u.getCollectionField_ID());
        map.put("Customer_ID", u.getCustomer_ID());
        
        List<RoleManageBean> list = roleMapper.getRole(map);

        JSONArray listArray=(JSONArray) JSONArray.toJSON(list);

        return listArray.toJSONString();
    }

    @Override
    public boolean AddNewRole(String roleName, String roleDes,String createrID,String Authority,HttpSession session) {
        String roleID;
        try {
            roleID = numberingcontrolService.getNextCFID("NC00000005");
            Map map = new HashMap();
            map.put("roleID", roleID);
            map.put("roleName", roleName);
            map.put("roleDes", roleDes);
            map.put("createrID", createrID);
            map.put("Authority", Authority+"00000");
            User u = (User) session.getAttribute(CommBean.SESSION_NAME);
            map.put("CollectionField_ID", u.getCollectionField_ID());
            map.put("Customer_ID", u.getCustomer_ID());
            
            int res = roleMapper.AddNewRole(map);
            if(res==1) {
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean exist(String roleID,HttpSession session) {
        Map map = new HashMap();
        map.put("roleID", roleID);
        User u = (User) session.getAttribute(CommBean.SESSION_NAME);
        map.put("CollectionField_ID", u.getCollectionField_ID());
        map.put("Customer_ID", u.getCustomer_ID());
        int res = roleMapper.getRoleCountByID(map);
        if(res>=1) {
            return true;
        }else {
            return false;
        }
    }

    @Override
	public boolean existWithOutSelf(RoleManageBean rb, HttpSession session) {

        User u = (User) session.getAttribute(CommBean.SESSION_NAME);
        rb.setCollectionField_ID(u.getCollectionField_ID());
        rb.setCustomer_ID(u.getCustomer_ID());
        int res = roleMapper.getRoleCountByIDWithOutSelf(rb);
        if(res>=1) {
            return true;
        }else {
            return false;
        }
	}

    
    @Override
    public boolean updateRole(RoleManageBean ub) {
        try {
            ub.setAuthority(ub.getAuthority()+"00000");
            int res = roleMapper.updateRole(ub);
            if(res==1) {
                return true;
            }else {
                return false;
            }
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delRole(String json) {

        List<RoleManageBean> list = new ArrayList<RoleManageBean>();
        JSONArray parseArray = JSONArray.parseArray(json);

        for(int i=0;i<parseArray.size();i++) {
            JSONObject jo = (JSONObject) parseArray.get(i);
            RoleManageBean ub = new RoleManageBean();
            ub.setUserRole_ID(jo.getString("id"));
            list.add(ub);
        }
        int res = roleMapper.delRole(list);
        if(res>=1) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<RoleManageBean> queryAllRole(HttpSession session) {

    	RoleManageBean rb = new RoleManageBean();
    	User u = (User) session.getAttribute(CommBean.SESSION_NAME);
        rb.setCollectionField_ID(u.getCollectionField_ID());
        rb.setCustomer_ID(u.getCustomer_ID());
        return this.roleMapper.queryAllRole(rb);

    }

    @Override
    public RoleManageBean queryById(String urole,HttpSession session) {
    	RoleManageBean rb = new RoleManageBean();
    	User u = (User) session.getAttribute(CommBean.SESSION_NAME);
    	rb.setUserRole_ID(urole);
        rb.setCollectionField_ID(u.getCollectionField_ID());
        rb.setCustomer_ID(u.getCustomer_ID());
        return this.roleMapper.queryById(rb);
    }

	
}
