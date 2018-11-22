/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.service.classification;


import com.ichangyun.InforAnalyaizer.model.classification.ClassificationInfoBean;

/**
 * 分类体系Service
 * @author renhao
 * Date:2018-11-12
 */
public interface ClassificationInfoService {

	/**
	 * 获得要查询的分类体系总数
	 * @param cb  查询参数
	 * @return  要查询的分类体系总数
	 * Date:2018-11-12
	 */
	public int getClassifcInfoCount(ClassificationInfoBean cb);

	
	/**
	 * 获得要查询的分类体系信息的JSON字面量
	 * @param cb  查询参数
	 * @return 要查询的分类体系信息的JSON字面量
	 * Date:2018-11-12
	 */
	public String getClassifcInfo(ClassificationInfoBean cb);

	/**
	 * 根据ID查询分类体系信息
	 * @return  要查询的查询分类体系信息JSON字面量
	 * Date:2018-11-12
	 */
	public String getClassificationInfoID();

	/**
	 * 新增分类体系信息
	 * @param cb  新增分类体系信息的各参数
	 * @return  true 新增成功   false  异常
	 * Date:2018-11-12
	 */
	public boolean AddNew(ClassificationInfoBean cb);

	/**
	 * 查询分类体系名称是否已存在
	 * @param cb  要查询的分类体系名称参数
	 * @return  true 已存在   false 不存在
	 * Date:2018-11-12
	 */
	public boolean exist(ClassificationInfoBean cb);

	/**
	 * 修改分类体系信息
	 * @param cb  要修改的分类体系参数
	 * @return  true 修改成功　　false　异常
	 * Date:2018-11-12
	 */
	public boolean updateData(ClassificationInfoBean cb);

	/**
	 * 查询除某ＩＤ以外的分类体系名称是否已存在
	 * @param cb　　要查询的分类体系名称和ＩＤ参数
	 * @return　true 已存在   false 不存在
	 * Date:2018-11-12
	 */
	public boolean existExceptID(ClassificationInfoBean cb);

	/**
	 * 删除分类体系信息
	 * @param json　要删除的分类信息ＩＤ的ｊｓｏｎ字面量
	 * @return　ｔｒｕｅ　删除成功　　ｆａｌｓｅ　异常
	 * Date:2018-11-12
	 */
	public boolean delDSata(String json);

	
	/**
	 * 更改分类体系排位顺序
	 * @param cur_Classification_ID   要交换的分类体系ID 1
	 * @param cur_displayOrder        要交换的分类体系排位顺序 1
	 * @param ch_Classification_ID    要交换的分类体系ID 2
	 * @param ch_displayOrder         要交换的分类体系排位顺序 2
	 * Date:2018-11-12
	 */
	public void updateOrder(String cur_Classification_ID, int cur_displayOrder, String ch_Classification_ID,int ch_displayOrder);

	/**
	 * 根据ID 获得分类体系的子节点
	 * @param cb   要获得子节点的分类体系ID参数
	 * @return   分类体系子节点信息的JSON 字面量
	 * Date:2018-11-12
	 */
	public String getChildernByID(ClassificationInfoBean cb);

	
	/**
	 * 根据ＩＤ获得分类体系信息
	 * @param cb　　要查询的分类体系信息ＩＤ参数
	 * @return　　要查询的分类体系信息
	 * Date:2018-11-12
	 */
	public String getInfoByID(ClassificationInfoBean cb);


	/**
	 * 获得所有处于根节点的分类体系
	 * @return
	 */
	public String getAllClassification();

}
