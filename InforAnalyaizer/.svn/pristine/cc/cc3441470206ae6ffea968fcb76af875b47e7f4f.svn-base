/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.service.usermanage.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ichangyun.InforAnalyaizer.mapper.usermanage.RoleMapper;
import com.ichangyun.InforAnalyaizer.model.usermanage.RoleManageBean;
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

    //spring事务管理manager
    @Resource(name = "transactionManager")
    private PlatformTransactionManager platformTransactionManager;


    @Override
    public int getRoleCount() {

        return roleMapper.getRoleCount();
    }

    @Override
    public String getRole(int pageNow, int rowSize) {

        Map map = new HashMap();
        int l_pre = (pageNow-1)*rowSize ;
        map.put("l_pre",l_pre);
        map.put("rowSize", rowSize);

        List<RoleManageBean> list = roleMapper.getRole(map);

        JSONArray listArray=(JSONArray) JSONArray.toJSON(list);

        return listArray.toJSONString();
    }

    @Override
    public boolean AddNewRole(String roleName, String roleDes,String createrID,String Authority) {
        Map map = new HashMap();
        map.put("roleName", roleName);
        map.put("roleDes", roleDes);
        map.put("createrID", createrID);
        map.put("Authority", Authority+"00000");
        int res = roleMapper.AddNewRole(map);
        if(res==1) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean exist(String roleID) {
        Map map = new HashMap();
        map.put("roleID", roleID);
        int res = roleMapper.getRoleCountByID(map);
        if(res>=1) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateRole(RoleManageBean ub) {
        try {
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
    public List<RoleManageBean> queryAllRole() {

        return this.roleMapper.queryAllRole();

    }

    @Override
    public RoleManageBean queryById(String urole) {
        // TODO Auto-generated method stub
        return this.roleMapper.queryById(urole);
    }

}
