/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 * �����鱨����ϵͳ
 */
package com.ichangyun.InforAnalyaizer.service.common.service;

import java.util.List;

/**
 * ����Check�ӿ�
 *
 * @author ichangyun
 * @date 2018/11/19
 */
public interface DBUpdateCheckService {

    /**
     * ����Check
     * @param checkKbn ��������
     * @param paramList PK
     * @param updateDateTime ������ʱ
     * @return
     */
    public boolean DBUpdateCheck(String checkKbn,
            List<String> paramList,
            String updateDateTime);
}