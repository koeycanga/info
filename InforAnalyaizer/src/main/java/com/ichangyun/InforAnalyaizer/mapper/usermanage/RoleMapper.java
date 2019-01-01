/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.mapper.usermanage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ichangyun.InforAnalyaizer.model.usermanage.RoleManageBean;

/**
 * RoleMapper：角色管理mapper
 *
 * @author renhao
 * @Date:2018-11-9
 */
public interface RoleMapper {

    /**
     * 获得角色总数目
     * @param rb 
     * @return
     * Date:2018-11-9
     */
    public int getRoleCount(RoleManageBean rb);

    /**
     * 根据条件查询角色信息
     * @param map
     * @return
     * Date:2018-11-9
     */
    public List<RoleManageBean> getRole(@Param("param")Map map);

    /**
     * 根据角色ID获得角色数目
     * @param map
     * @return
     * Date:2018-11-9
     */
    public int getRoleCountByID(@Param("param")Map map);

    
    public int getRoleCountByIDWithOutSelf(RoleManageBean rb);
    
    /**
     * 新增角色
     * @param map
     * @return
     * @Date:2018-11-9
     */
    public int AddNewRole(@Param("param")Map map);

    /**
     * 更新角色信息
     * @param ub
     * @return
     * @Date:2018-11-9
     */
    public int updateRole(RoleManageBean ub);

    /**
     * 删除角色信息
     * @param list  要删除的角色ID列表
     * @return
     * Date:2018-11-9
     */
    public int delRole(List<RoleManageBean> list);

    /**
     * 查询所有角色
     * @return 所有角色信息
     * Date:2018-11-9
     */
    public List<RoleManageBean> queryAllRole(RoleManageBean rb);

    /**
     * 根据ID查询角色信息
     * @param urole 要查询的角色ID
     * @return 查询到的角色信息
     */
    public RoleManageBean queryById(RoleManageBean rb);

	

}
