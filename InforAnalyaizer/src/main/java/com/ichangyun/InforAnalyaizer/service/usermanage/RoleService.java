package com.ichangyun.InforAnalyaizer.service.usermanage;

import java.util.List;

import com.ichangyun.InforAnalyaizer.model.usermanage.RoleManageBean;

public interface RoleService {

	public int getRoleCount();

	public String getRole(int pageNow, int rowSize);

	public boolean AddNewRole(String roleName, String roleDes, String createrID, String Authority);  //������ɫ

	public boolean exist(String roleID);   //�жϽ�ɫID�Ƿ��Ѵ���

	public boolean updateRole(RoleManageBean ub);

	public boolean delRole(String json);
	
	List<RoleManageBean> queryAllRole();

	public RoleManageBean queryById(String urole);
}
