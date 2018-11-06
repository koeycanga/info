package com.ichangyun.InforAnalyaizer.mapper.usermanage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ichangyun.InforAnalyaizer.model.KBean;
import com.ichangyun.InforAnalyaizer.model.usermanage.RoleManageBean;

public interface RoleMapper {

	public List<KBean> sayhello();

	public int getRoleCount();

	public List<RoleManageBean> getRole(@Param("param")Map map);

	public int getRoleCountByID(@Param("param")Map map);

	public int AddNewRole(@Param("param")Map map);

	public int updateRole(RoleManageBean ub);

	public int delRole(List<RoleManageBean> list);
	
	List<RoleManageBean> queryAllRole();

}
