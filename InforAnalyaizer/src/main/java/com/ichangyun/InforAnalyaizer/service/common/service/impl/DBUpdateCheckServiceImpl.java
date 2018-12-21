/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 * �����鱨����ϵͳ
 */
package com.ichangyun.InforAnalyaizer.service.common.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ichangyun.InforAnalyaizer.controller.usermanage.UserController;
import com.ichangyun.InforAnalyaizer.mapper.classification.ClassificationInfoMapper;
import com.ichangyun.InforAnalyaizer.mapper.classificationfilterwords.ClassificationFilterwordsMapper;
import com.ichangyun.InforAnalyaizer.mapper.collection.CollectionTypeMapper;
import com.ichangyun.InforAnalyaizer.mapper.notice.NoticeMapper;
import com.ichangyun.InforAnalyaizer.mapper.userInfoMapper.UserInfoMapper;
import com.ichangyun.InforAnalyaizer.mapper.usermanage.RoleMapper;
import com.ichangyun.InforAnalyaizer.model.classification.ClassificationInfoBean;
import com.ichangyun.InforAnalyaizer.model.classificationfilterwords.FilterWordsVo;
import com.ichangyun.InforAnalyaizer.model.collection.CollectionTypeVo;
import com.ichangyun.InforAnalyaizer.model.notice.NoticeVo;
import com.ichangyun.InforAnalyaizer.model.userInfo.UserInfo;
import com.ichangyun.InforAnalyaizer.model.userInfo.UserInfoKey;
import com.ichangyun.InforAnalyaizer.model.userInfo.UserInfoVo;
import com.ichangyun.InforAnalyaizer.model.usermanage.RoleManageBean;
import com.ichangyun.InforAnalyaizer.service.common.service.DBUpdateCheckService;
import com.ichangyun.InforAnalyaizer.utils.DateUtils;

@Service
public class DBUpdateCheckServiceImpl implements DBUpdateCheckService {
	Logger log = Logger.getLogger(UserController.class);

	// �˺Ź���mapper
	@Autowired
	private UserInfoMapper userInfoMapper;

	// ��ɫ����mapper
	@Autowired
	private RoleMapper roleMapper;

	// ������ϵmapper
	@Autowired
	private ClassificationInfoMapper classificationInfoMapper;

	// ������˴�mapper
	@Autowired
	private ClassificationFilterwordsMapper filterwordsMapper;

	// ������mapper
	@Autowired
	private NoticeMapper noticeMapper;

	// �ղؼ�mapper
	@Autowired
	private CollectionTypeMapper collectionTypeMapper;
	/**
	 * ����Check
	 * 
	 * @param checkKbn       ��������(1:�˺ű༭/2:��ɫ�༭/3:������ϵ�༭/4:������˴ʱ༭/5:��ϢԴ�༭/6:������/7:�ղؼб༭)
	 * @param paramList      PK
	 * @param updateDateTime ������ʱ
	 * @return
	 */
	@Override
	public boolean DBUpdateCheck(String checkKbn, List<String> paramList, String updateDateTime) {

		// ����check
		boolean isExclusiveFlg = false;
		String dateTimeTemp = "";

		// [��������1:�˺ű༭]�ĳ���
		if ("1".equals(checkKbn)) {
			// ���ü�������
			UserInfoKey key = new UserInfoKey();
			key.setUserId(paramList.get(0).toString());
			// ���ݼ���������ȡ�����ݿ����µ�����
			UserInfo info = this.userInfoMapper.selectByPrimaryKey(key);
			UserInfoVo vo = new UserInfoVo();
			vo.loading(info);
			dateTimeTemp = vo.getUupdatedatetime();
			if (updateDateTime.equals(dateTimeTemp)) {
				isExclusiveFlg = true;
			}
		}

		// [��������2:��ɫ�༭]�ĳ���
		if ("2".equals(checkKbn)) {
			// ���ݼ���������ȡ�����ݿ����µ�����
			RoleManageBean dataBean = roleMapper.queryById(paramList.get(0).toString());
			dateTimeTemp = dataBean.getUpdateDateTime();
			if (updateDateTime.equals(dateTimeTemp)) {
				isExclusiveFlg = true;
			}
		}
		// [��������3:������ϵ�༭]�ĳ���
		if ("3".equals(checkKbn)) {
			ClassificationInfoBean cb = new ClassificationInfoBean();
			cb.setClassification_ID(paramList.get(0).toString());
			ClassificationInfoBean infoBean = classificationInfoMapper.getInfoByID(cb);
			if (infoBean.getUpdateDateTime().equals(updateDateTime)) {
				isExclusiveFlg = true;
			}
		}
		// [��������4:������˴ʱ༭]�ĳ���
		if ("4".equals(checkKbn)) {
			// TODO
			FilterWordsVo vo = this.filterwordsMapper.queryOne(paramList.get(0).toString());
			if (vo.getUpdatedatetime().equals(updateDateTime)) {
				isExclusiveFlg = true;
			}
		}
		// [��������5:��ϢԴ�༭�ĳ���
		if ("5".equals(checkKbn)) {
			// TODO

		}
		// [��������6:������]�ĳ���
		if ("6".equals(checkKbn)) {
			// TODO
			NoticeVo vo = this.noticeMapper.queryById(paramList.get(0).toString());
			if (vo.getUpdatedatetime().equals(updateDateTime)) {
				isExclusiveFlg = true;
			}
		}
		// [��������7:�ղؼб༭]�ĳ���
		if ("7".equals(checkKbn)) {
			// TODO
			CollectionTypeVo vo = this.collectionTypeMapper.queryOne(paramList.get(0).toString());
			if (vo.getUpdatedatetime().equals(updateDateTime)) {
				isExclusiveFlg = true;
			}
		}
		return isExclusiveFlg;
	}

}
