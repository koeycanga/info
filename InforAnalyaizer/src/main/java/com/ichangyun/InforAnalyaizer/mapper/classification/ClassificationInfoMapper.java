/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.mapper.classification;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ichangyun.InforAnalyaizer.model.classification.ClassificationInfoBean;

/**
 * 分类体系信息Mapper
 * @author renhao
 * Date:2018-11-12
 */
public interface ClassificationInfoMapper {

	/**
	 * 获得要查询的分类体系总数目
	 * @param cb  查询参数
	 * @return  要查询的分类体系总数目
	 * Date:2018-11-12
	 */
	public int getClassifcInfoCount(@Param("cb") ClassificationInfoBean cb);

	/**
	 * 获得要查询的分类体系信息列表
	 * @param cb  查询参数
	 * @return  要查询的分类体系信息列表
	 * Date:2018-11-12
	 */
	public List<ClassificationInfoBean> getClassifcInfo(@Param("cb") ClassificationInfoBean cb);

	/**
	 * 获得分类体系的排位顺序
	 * @param cb  查询参数
	 * @return 分类体系的排位顺序
	 * Date:2018-11-12
	 */
	public int getDisplayOrder(@Param("cb") ClassificationInfoBean cb);

	/**
	 * 新增分类体系信息
	 * @param cb  新增的分类体系信息参数
	 * @return  
	 * Date:2018-11-12
	 */
	public int addNew(@Param("cb") ClassificationInfoBean cb);

	/**
	 * 判断分类体系名称是否已存在
	 * @param cb  分类体系名称参数
	 * @return
	 * Date:2018-11-12
	 */
	public int exist(@Param("cb") ClassificationInfoBean cb);

	/**
	 * 修改分类体系信息
	 * @param cb  要修改的分类体系参数
	 * @return
	 * Date:2018-11-12
	 */
	public int updateData(@Param("cb") ClassificationInfoBean cb);

	/**
	 * 判断除某ID以外的分类体系名称是否已存在
	 * @param cb  分类体系名称和ID参数
	 * @return
	 * Date:2018-11-12
	 */
	public int existExceptID(@Param("cb") ClassificationInfoBean cb);

	/**
	 * 获得某节点分类体系的子节点信息列表集合
	 * @param cb 某节点的分类体系参数
	 * @return  某节点分类体系的子节点信息列表集合
	 * Date:2018-11-12
	 */
	public List<ClassificationInfoBean> getChildren(@Param("cb")ClassificationInfoBean cb);

	/**
	 * 删除分类体系信息
	 * @param cb  要删除的分类体系ID参数
	 */
	public void delData(@Param("cb") ClassificationInfoBean cb);

	/**
	 * 修改分类体系的排位顺序
	 * @param cb   要修改排位顺序的分类体系参数
	 * Date:2018-11-12
	 */
	public void updateOrder(@Param("cb") ClassificationInfoBean cb);

	/**
	 * 根据ID获得分类体系的子节点列表集合
	 * @param cb   分类体系ID参数
	 * @return 分类体系的子节点列表集合
	 * Date:2018-11-12
	 */
	public List<ClassificationInfoBean> getChildernByID(@Param("cb") ClassificationInfoBean cb);

	/**
	 * 根据ID获得分类体系信息
	 * @param cb 分类体系ID参数
	 * @return 分类体系信息
	 * Date:2018-11-12
	 */
	public ClassificationInfoBean getInfoByID(@Param("cb") ClassificationInfoBean cb);

	/**
	 * 获得所有处在根节点的分类体系
	 * @param today 
	 * @param yesterday 
	 * @return
	 */
	public List<ClassificationInfoBean> getAllClassification(@Param("yesterday")String yesterday, @Param("today")String today);

	/**
	 * 使用mysql的function删除数据
	 * @param cb
	 */
	public void delDataByFunction(@Param("cb")ClassificationInfoBean cb);

	//public int getClassifcInfoCountWithName(ClassificationInfoBean cb);

	//public List<ClassificationInfoBean> getClassifcInfoWithName(ClassificationInfoBean cb);

}
