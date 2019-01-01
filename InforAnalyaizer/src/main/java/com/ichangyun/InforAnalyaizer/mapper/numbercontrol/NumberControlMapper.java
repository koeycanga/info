/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.mapper.numbercontrol;

import org.apache.ibatis.annotations.Param;

import com.ichangyun.InforAnalyaizer.model.numbercontroll.NumberingcontrolBean;

/**
 * 采番表Mapper
 * @author renhao
 * Date:2018-11-12
 */
public interface NumberControlMapper {

    /**
     * 根据ID获得采番表信息
     * @param nb  要查询的采番表ID参数
     * @return  要查询的采番表信息
     * Date:2018-11-12
     */
    public NumberingcontrolBean getInfoByID(@Param("param") NumberingcontrolBean nb);

    /**
     * 修改采番表对应ID的当前值
     * @param nb 要修改的采番表ID 及 当前值信息
     * Date:2018-11-12
     */
    public void updatePresetVal(@Param("param") NumberingcontrolBean nb);

}
