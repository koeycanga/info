package com.ichangyun.InforAnalyaizer.service.usermanage;

import java.util.List;

import com.ichangyun.InforAnalyaizer.model.usermanage.RoleManageBean;

public interface RoleService {

	public int getRoleCount();

	public String getRole(int pageNow, int rowSize);

	public boolean AddNewRole(String roleName, String roleDes, String createrID, String Authority);  //新增角色

	public boolean exist(String roleID);   //判断角色ID是否已存在

	public boolean updateRole(RoleManageBean ub);

	public boolean delRole(String json);
	
	List<RoleManageBean> queryAllRole();
}
