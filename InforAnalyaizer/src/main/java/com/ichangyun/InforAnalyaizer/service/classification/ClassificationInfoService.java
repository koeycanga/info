/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 *  �����鱨ϵͳ
 */
package com.ichangyun.InforAnalyaizer.service.classification;


import com.ichangyun.InforAnalyaizer.model.classification.ClassificationInfoBean;

/**
 * ������ϵService
 * @author renhao
 * Date:2018-11-12
 */
public interface ClassificationInfoService {

	/**
	 * ���Ҫ��ѯ�ķ�����ϵ����
	 * @param cb  ��ѯ����
	 * @return  Ҫ��ѯ�ķ�����ϵ����
	 * Date:2018-11-12
	 */
	public int getClassifcInfoCount(ClassificationInfoBean cb);

	
	/**
	 * ���Ҫ��ѯ�ķ�����ϵ��Ϣ��JSON������
	 * @param cb  ��ѯ����
	 * @return Ҫ��ѯ�ķ�����ϵ��Ϣ��JSON������
	 * Date:2018-11-12
	 */
	public String getClassifcInfo(ClassificationInfoBean cb);

	/**
	 * ����ID��ѯ������ϵ��Ϣ
	 * @return  Ҫ��ѯ�Ĳ�ѯ������ϵ��ϢJSON������
	 * Date:2018-11-12
	 */
	public String getClassificationInfoID();

	/**
	 * ����������ϵ��Ϣ
	 * @param cb  ����������ϵ��Ϣ�ĸ�����
	 * @return  true �����ɹ�   false  �쳣
	 * Date:2018-11-12
	 */
	public boolean AddNew(ClassificationInfoBean cb);

	/**
	 * ��ѯ������ϵ�����Ƿ��Ѵ���
	 * @param cb  Ҫ��ѯ�ķ�����ϵ���Ʋ���
	 * @return  true �Ѵ���   false ������
	 * Date:2018-11-12
	 */
	public boolean exist(ClassificationInfoBean cb);

	/**
	 * �޸ķ�����ϵ��Ϣ
	 * @param cb  Ҫ�޸ĵķ�����ϵ����
	 * @return  true �޸ĳɹ�����false���쳣
	 * Date:2018-11-12
	 */
	public boolean updateData(ClassificationInfoBean cb);

	/**
	 * ��ѯ��ĳ�ɣ�����ķ�����ϵ�����Ƿ��Ѵ���
	 * @param cb����Ҫ��ѯ�ķ�����ϵ���ƺͣɣĲ���
	 * @return��true �Ѵ���   false ������
	 * Date:2018-11-12
	 */
	public boolean existExceptID(ClassificationInfoBean cb);

	/**
	 * ɾ��������ϵ��Ϣ
	 * @param json��Ҫɾ���ķ�����Ϣ�ɣĵģ����������
	 * @return��������塡ɾ���ɹ����������塡�쳣
	 * Date:2018-11-12
	 */
	public boolean delDSata(String json);

	
	/**
	 * ���ķ�����ϵ��λ˳��
	 * @param cur_Classification_ID   Ҫ�����ķ�����ϵID 1
	 * @param cur_displayOrder        Ҫ�����ķ�����ϵ��λ˳�� 1
	 * @param ch_Classification_ID    Ҫ�����ķ�����ϵID 2
	 * @param ch_displayOrder         Ҫ�����ķ�����ϵ��λ˳�� 2
	 * Date:2018-11-12
	 */
	public void updateOrder(String cur_Classification_ID, int cur_displayOrder, String ch_Classification_ID,int ch_displayOrder);

	/**
	 * ����ID ��÷�����ϵ���ӽڵ�
	 * @param cb   Ҫ����ӽڵ�ķ�����ϵID����
	 * @return   ������ϵ�ӽڵ���Ϣ��JSON ������
	 * Date:2018-11-12
	 */
	public String getChildernByID(ClassificationInfoBean cb);

	
	/**
	 * ���ݣɣĻ�÷�����ϵ��Ϣ
	 * @param cb����Ҫ��ѯ�ķ�����ϵ��Ϣ�ɣĲ���
	 * @return����Ҫ��ѯ�ķ�����ϵ��Ϣ
	 * Date:2018-11-12
	 */
	public String getInfoByID(ClassificationInfoBean cb);


	/**
	 * ������д��ڸ��ڵ�ķ�����ϵ
	 * @return
	 */
	public String getAllClassification();

}
