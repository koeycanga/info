/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 *  �����鱨����ϵͳ
 */
package com.ichangyun.InforAnalyaizer.mapper.usermanage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ichangyun.InforAnalyaizer.model.usermanage.RoleManageBean;

/**
 * RoleMapper����ɫ����mapper
 *
 * @author renhao
 * @Date:2018-11-9
 */
public interface RoleMapper {

    /**
     * ��ý�ɫ����Ŀ
     * @return
     * Date:2018-11-9
     */
    public int getRoleCount();

    /**
     * ����������ѯ��ɫ��Ϣ
     * @param map
     * @return
     * Date:2018-11-9
     */
    public List<RoleManageBean> getRole(@Param("param")Map map);

    /**
     * ���ݽ�ɫID��ý�ɫ��Ŀ
     * @param map
     * @return
     * Date:2018-11-9
     */
    public int getRoleCountByID(@Param("param")Map map);

    /**
     * ������ɫ
     * @param map
     * @return
     * @Date:2018-11-9
     */
    public int AddNewRole(@Param("param")Map map);

    /**
     * ���½�ɫ��Ϣ
     * @param ub
     * @return
     * @Date:2018-11-9
     */
    public int updateRole(RoleManageBean ub);

    /**
     * ɾ����ɫ��Ϣ
     * @param list  Ҫɾ���Ľ�ɫID�б�
     * @return
     * Date:2018-11-9
     */
    public int delRole(List<RoleManageBean> list);

    /**
     * ��ѯ���н�ɫ
     * @return ���н�ɫ��Ϣ
     * Date:2018-11-9
     */
    public List<RoleManageBean> queryAllRole();

    /**
     * ����ID��ѯ��ɫ��Ϣ
     * @param urole Ҫ��ѯ�Ľ�ɫID
     * @return ��ѯ���Ľ�ɫ��Ϣ
     */
    public RoleManageBean queryById(String urole);

}
