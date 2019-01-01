/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.mapper.classification;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ichangyun.InforAnalyaizer.model.classification.ClassificationInfoBean;
import com.ichangyun.InforAnalyaizer.model.webinfo.WebInfoBean;

/**
 * 信息源绑定Mapper
 * @author renhao
 * Date:2018-11-12
 */
public interface ClassificationSourceMapper {

    /**
     * 获得分类体系总数目
     * @param cb 查询参数
     * @return 分类体系总数目
     * Date:2018-11-12
     */
    public ClassificationInfoBean getClassifcInfoCount(ClassificationInfoBean cb) ;

    /**
     * 获得分类体系信息列表集合
     * @param cb  查询参数
     * @return 分类体系信息列表集合
     * Date:2018-11-12
     */
    public List<ClassificationInfoBean> getClassifcInfo(ClassificationInfoBean cb);

    /**
     * 获得分类体系子节点的信息列表集合
     * @param cb 查询参数
     * @return 分类体系子节点的信息列表集合
     * Date:2018-11-12
     */
    public List<ClassificationInfoBean> getchild(ClassificationInfoBean cb);

    /**
     * 获得网站信息总数目
     * @param wb  查询参数
     * @return 得网站信息总数目
     * Date:2018-11-12
     */
    public int getWebInfoCount(WebInfoBean wb);

    /**
     * 获得网站信息列表集合
     * @param wb  查询参数
     * @return  网站信息列表集合
     * Date:2018-11-12
     */
    public List<WebInfoBean> getWebInfo(WebInfoBean wb);

    /**
     * 获得已绑定的网站信息总数
     * @param wb
     * @return  已绑定的网站信息总数
     * Date:2018-11-12
     */
    public int getAlWebInfoCount(WebInfoBean wb);

    /**
     * 获得已绑定的网站信息集合
     * @param wb 分类体系ID参数
     * @return 已绑定的网站信息集合
     * Date:2018-11-12
     */
    public List<WebInfoBean> getAlWebInfo(WebInfoBean wb);

    /**
     * 获得网站NO
     * @return 网站NO
     * Date:2018-11-12
     */
    public int getWebNo();

    /**
     * 添加新的信息源绑定信息
     * @param list 要添加的信息源信息
     * Date:2018-11-12
     */
    public void addNewSource(List<WebInfoBean> list);

    /**
     * 删除某分类体系下已绑定的信息源信息
     * @param classification_ID  要删除绑定的分类体系ID
     * @param list 要删除绑定的信息源信息
     * Date:2018-11-12
     */
    public void delteSource(@Param("p1")String classification_ID, @Param("p2")List<Integer> list);

    /**
     * 修改分类体系的信息源信息
     * @param Classification_ID  要修改的分类体系ID
     * @param updater  修改者
     * Date:2018-11-12
     */
    public void updatesource(@Param("p1")String Classification_ID ,@Param("p2")String updater);

}
