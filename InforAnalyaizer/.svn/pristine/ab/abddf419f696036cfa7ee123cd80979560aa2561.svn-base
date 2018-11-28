/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.service.classification;

import com.ichangyun.InforAnalyaizer.model.classification.ClassificationInfoBean;
import com.ichangyun.InforAnalyaizer.model.webinfo.WebInfoBean;

/**
 * 信息源绑定service	
 * @author renhao
 * Date:2018-11-12
 */
public interface ClassificationSourceService {

	/**
	 * 获得要查询的分类体系总数
	 * @param cb 查询参数
	 * @return 要查询的分类体系总数
	 * Date:2018-11-12
	 */
	public int getClassifcInfoCount(ClassificationInfoBean cb);

	/**
	 * 获得要查询的分类体系信息JSON字面量
	 * @param cb
	 * @return 要查询的分类体系信息JSON字面量
	 * Date:2018-11-12
	 */
	public String getClassifcInfo(ClassificationInfoBean cb);

	/**
	 * 获得分类体系的子节点信息
	 * @param cb  查询参数
	 * @return  分类体系的子节点信息
	 * Date:2018-11-12
	 */
	public String getchild(ClassificationInfoBean cb);

	/**
	 * 获得要查询的网站信息总数
	 * @param wb  查询参数
	 * @return 网站信息总数
	 * Date:2018-11-12
	 */
	public int getWebInfoCount(WebInfoBean wb);

	/**
	 * 获得要查询的网站信息JSON字面量
	 * @param wb 查询参数
	 * @return  要查询的网站信息JSON字面量
	 * Date:2018-11-12
	 */
	public String getWebInfo(WebInfoBean wb);

	/**
	 * 获得已绑定的网站信息总数
	 * @param wb 查询参数
	 * @return 所有的网站信息总数
	 * Date:2018-11-12
	 */
	public int getAlWebInfoCount(WebInfoBean wb);

	/**
	 * 获得已绑定的网站信息JSON字面量
	 * @param wb 查询参数
	 * @return 所有的网站信息JSON字面量
	 * Date:2018-11-12
	 */
	public String getAlWebInfo(WebInfoBean wb);

	/**
	 * 添加新的信息源绑定信息
	 * @param classification_ID  要添加绑定的分类体系ID
	 * @param json   要添加的绑定信息json字面量
	 * @param creater 添加者
	 * Date:2018-11-12
	 */
	public void addNewSource(String classification_ID, String json, String creater);

	/**
	 * 删除已绑定的信息源
	 * @param classification_ID 要删除绑定的分类体系ID
	 * @param json 要删除的绑定信息JSON字面量
	 */
	public void delteSource(String classification_ID, String json);

	/**
	 * 修改分类体系绑定信息
	 * @param Classification_ID 要修改的分类体系ID
	 * @param updater  修改者
	 */
	public void updatesource(String Classification_ID,String updater);

}
