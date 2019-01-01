/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.service.numberingcontrol;
/**
 * 采番ControlService
 * @author ichangyun
 * @date 2018-11-13 11:12
 */
public interface NumberingcontrolService {

    /**
     * 取得採番ID
     *
     * @param cpid 管理区分ID
     * @return
     * @throws Exception
     */
    public String getNextCFID(String cpid) throws Exception;
}
