/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.service.usermanage;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ichangyun.InforAnalyaizer.model.usermanage.RoleManageBean;

/**
 * 角色管理Service
 * @author renhao
 * @Date:2018-11-9
 */
public interface RoleService {

    /**
     * 获得角色总数
     * @param session 
     * @return 角色总数
     * Date:2018-11-9
     */
    public int getRoleCount(HttpSession session);

    /**
     * 获得当前页面所需显示的角色数据
     * @param pageNow  当前第几页
     * @param rowSize  每页显示条目数
     * @param session 
     * @return 当前页面所需显示的角色数据
     * Date:2018-11-9
     */
    public String getRole(int pageNow, int rowSize, HttpSession session);

    /**
     * 新增角色
     * @param roleName   角色名称
     * @param roleDes    角色描述
     * @param createrID  创建者ID
     * @param Authority  角色权限
     * @param session 
     * @return true 成功   false 失败
     * Date:2018-11-9
     */
    public boolean AddNewRole(String roleName, String roleDes, String createrID, String Authority, HttpSession session);

    /**
     * 判断角色ID是否已存在
     * @param roleID 要判断的角色ID
     * @param session 
     * @return
     * Date:2018-11-9
     */
    public boolean exist(String roleID, HttpSession session);

    /**
     * 修改角色信息
     * @param ub 要修改的角色信息实体类
     * @return true 成功   false 失败
     * Date:2018-11-9
     */
    public boolean updateRole(RoleManageBean ub);

    /**
     * 删除角色
     * @param json 要删除的角色ID json字面量
     * @return true 成功   false 失败
     * Date:2018-11-9
     */
    public boolean delRole(String json);

    /**
     * 获得所有角色信息
     * @return 所有角色信息列表
     * Date:2018-11-9
     */
    List<RoleManageBean> queryAllRole(HttpSession session);

    /**
     * 根据角色ID查询角色信息
     * @param urole   要查询的角色ID
     * @return  查询到的角色信息
     * Date:2018-11-9
     */
    public RoleManageBean queryById(String urole,HttpSession session);

    /**
     * 判断角色ID是否已存在(排除自身)
     * @param ub
     * @param session
     * @return
     */
	public boolean existWithOutSelf(RoleManageBean ub, HttpSession session);
}
