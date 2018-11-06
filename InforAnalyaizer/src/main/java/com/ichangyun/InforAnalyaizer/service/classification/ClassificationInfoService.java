package com.ichangyun.InforAnalyaizer.service.classification;

import com.ichangyun.InforAnalyaizer.model.classification.ClassificationInfoBean;

/**
 * ������ϵ��ӦService
 * @author Administrator
 *
 */
public interface ClassificationInfoService {

	public int getClassifcInfoCount(ClassificationInfoBean cb);

	public String getClassifcInfo(ClassificationInfoBean cb);

	public String getClassificationInfoID();

	public boolean AddNew(ClassificationInfoBean cb);

	public boolean exist(ClassificationInfoBean cb);

	public boolean updateData(ClassificationInfoBean cb);

	public boolean existExceptID(ClassificationInfoBean cb);

	public boolean delDSata(String json);

	public void updateOrder(String cur_Classification_ID, int cur_displayOrder, String ch_Classification_ID,
			int ch_displayOrder);

	public String getChildernByID(ClassificationInfoBean cb);

	public String getInfoByID(ClassificationInfoBean cb);

}
