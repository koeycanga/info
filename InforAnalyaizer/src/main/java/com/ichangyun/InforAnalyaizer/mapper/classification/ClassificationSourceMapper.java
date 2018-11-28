/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 *  �����鱨ϵͳ
 */
package com.ichangyun.InforAnalyaizer.mapper.classification;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ichangyun.InforAnalyaizer.model.classification.ClassificationInfoBean;
import com.ichangyun.InforAnalyaizer.model.webinfo.WebInfoBean;

/**
 * ��ϢԴ��Mapper
 * @author renhao
 * Date:2018-11-12
 */
public interface ClassificationSourceMapper {

	/**
	 * ��÷�����ϵ����Ŀ
	 * @param cb ��ѯ����
	 * @return ������ϵ����Ŀ
	 * Date:2018-11-12
	 */
	public ClassificationInfoBean getClassifcInfoCount(ClassificationInfoBean cb) ;

	/**
	 * ��÷�����ϵ��Ϣ�б���
	 * @param cb  ��ѯ����
	 * @return ������ϵ��Ϣ�б���
	 * Date:2018-11-12
	 */
	public List<ClassificationInfoBean> getClassifcInfo(ClassificationInfoBean cb);

	/**
	 * ��÷�����ϵ�ӽڵ����Ϣ�б���
	 * @param cb ��ѯ����
	 * @return ������ϵ�ӽڵ����Ϣ�б���
	 * Date:2018-11-12
	 */
	public List<ClassificationInfoBean> getchild(ClassificationInfoBean cb);

	/**
	 * �����վ��Ϣ����Ŀ
	 * @param wb  ��ѯ����
	 * @return ����վ��Ϣ����Ŀ
	 * Date:2018-11-12
	 */
	public int getWebInfoCount(WebInfoBean wb);

	/**
	 * �����վ��Ϣ�б���
	 * @param wb  ��ѯ����
	 * @return  ��վ��Ϣ�б���
	 * Date:2018-11-12
	 */
	public List<WebInfoBean> getWebInfo(WebInfoBean wb);

	/**
	 * ����Ѱ󶨵���վ��Ϣ����
	 * @param wb 
	 * @return  �Ѱ󶨵���վ��Ϣ����
	 * Date:2018-11-12
	 */
	public int getAlWebInfoCount(WebInfoBean wb);

	/**
	 * ����Ѱ󶨵���վ��Ϣ����
	 * @param wb ������ϵID����
	 * @return �Ѱ󶨵���վ��Ϣ����
	 * Date:2018-11-12
	 */
	public List<WebInfoBean> getAlWebInfo(WebInfoBean wb);

	/**
	 * �����վNO
	 * @return ��վNO
	 * Date:2018-11-12
	 */
	public int getWebNo();

	/**
	 * ����µ���ϢԴ����Ϣ
	 * @param list Ҫ��ӵ���ϢԴ��Ϣ
	 * Date:2018-11-12
	 */
	public void addNewSource(List<WebInfoBean> list);

	/**
	 * ɾ��ĳ������ϵ���Ѱ󶨵���ϢԴ��Ϣ
	 * @param classification_ID  Ҫɾ���󶨵ķ�����ϵID
	 * @param list Ҫɾ���󶨵���ϢԴ��Ϣ
	 * Date:2018-11-12
	 */
	public void delteSource(@Param("p1")String classification_ID, @Param("p2")List<Integer> list);

	/**
	 * �޸ķ�����ϵ����ϢԴ��Ϣ
	 * @param Classification_ID  Ҫ�޸ĵķ�����ϵID
	 * @param updater  �޸���
	 * Date:2018-11-12
	 */
	public void updatesource(@Param("p1")String Classification_ID ,@Param("p2")String updater);

}
