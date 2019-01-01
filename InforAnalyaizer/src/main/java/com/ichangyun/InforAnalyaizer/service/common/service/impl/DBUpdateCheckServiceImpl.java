/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.service.common.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.ichangyun.InforAnalyaizer.model.CommBean;
import com.ichangyun.InforAnalyaizer.model.classification.ClassificationInfoBean;
import com.ichangyun.InforAnalyaizer.model.classificationfilterwords.FilterWordsVo;
import com.ichangyun.InforAnalyaizer.model.collection.CollectionTypeVo;
import com.ichangyun.InforAnalyaizer.model.notice.NoticeVo;
import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.model.userInfo.UserInfo;
import com.ichangyun.InforAnalyaizer.model.userInfo.UserInfoKey;
import com.ichangyun.InforAnalyaizer.model.userInfo.UserInfoVo;
import com.ichangyun.InforAnalyaizer.model.usermanage.RoleManageBean;
import com.ichangyun.InforAnalyaizer.service.common.service.DBUpdateCheckService;

/**
 * 排他CheckService
 *
 * @author ichangyun
 * @date 2018/11/19
 */
@Service
public class DBUpdateCheckServiceImpl implements DBUpdateCheckService {
    Logger log = Logger.getLogger(UserController.class);

    // 账号管理mapper
    @Autowired
    private UserInfoMapper userInfoMapper;

    // 角色管理mapper
    @Autowired
    private RoleMapper roleMapper;

    // 分类体系mapper
    @Autowired
    private ClassificationInfoMapper classificationInfoMapper;

    // 分类过滤词mapper
    @Autowired
    private ClassificationFilterwordsMapper filterwordsMapper;

    // 简报任务mapper
    @Autowired
    private NoticeMapper noticeMapper;

    // 收藏夹mapper
    @Autowired
    private CollectionTypeMapper collectionTypeMapper;

    /**
     * 排他Check
     *
     * @param checkKbn  处理区分(1:账号编辑/2:角色编辑/3:分类体系编辑/4:分类过滤词编辑/5:信息源编辑/6:简报任务/7:收藏夹编辑)
     * @param paramList PK
     * @param updateDateTime 更新日时
     * @return
     */
    @Override
    public boolean DBUpdateCheck(String checkKbn, List<String> paramList, String updateDateTime,HttpSession session) {

        // 排他check
        boolean isExclusiveFlg = false;
        String dateTimeTemp = "";

        // [处理区分1:账号编辑]的场合
        if ("1".equals(checkKbn)) {
            // 设置检索条件
            UserInfoKey key = new UserInfoKey();
            key.setUserId(paramList.get(0).toString());
            // 根据检索条件，取得数据库最新的数据
            UserInfo info = this.userInfoMapper.selectByPrimaryKey(key);
            UserInfoVo vo = new UserInfoVo();
            vo.loading(info);
            dateTimeTemp = vo.getUupdatedatetime();
            if (updateDateTime.equals(dateTimeTemp)) {
                isExclusiveFlg = true;
            }
        }

        // [处理区分2:角色编辑]的场合
        if ("2".equals(checkKbn)) {
            // 根据检索条件，取得数据库最新的数据
        	RoleManageBean rb = new RoleManageBean();
        	User u = (User) session.getAttribute(CommBean.SESSION_NAME);
        	rb.setUserRole_ID(paramList.get(0).toString());
            rb.setCollectionField_ID(u.getCollectionField_ID());
            rb.setCustomer_ID(u.getCustomer_ID());
            RoleManageBean dataBean = roleMapper.queryById(rb);
            dateTimeTemp = dataBean.getUpdateDateTime();
            if (updateDateTime.equals(dateTimeTemp)) {
                isExclusiveFlg = true;
            }
        }
        // [处理区分3:分类体系编辑]的场合
        if ("3".equals(checkKbn)) {
            ClassificationInfoBean cb = new ClassificationInfoBean();
            cb.setClassification_ID(paramList.get(0).toString());
            ClassificationInfoBean infoBean = classificationInfoMapper.getInfoByID(cb);
            if (infoBean.getUpdateDateTime().equals(updateDateTime)) {
                isExclusiveFlg = true;
            }
        }
        // [处理区分4:分类过滤词编辑]的场合
        if ("4".equals(checkKbn)) {
            FilterWordsVo vo = this.filterwordsMapper.queryOne(paramList.get(0).toString());
            if (vo.getUpdatedatetime().equals(updateDateTime)) {
                isExclusiveFlg = true;
            }
        }
        // [处理区分5:信息源编辑的场合
        if ("5".equals(checkKbn)) {
            // TODO

        }
        // [处理区分6:简报任务]的场合
        if ("6".equals(checkKbn)) {
            NoticeVo vo = this.noticeMapper.queryById(paramList.get(0).toString());
            if (vo.getUpdatedatetime().equals(updateDateTime)) {
                isExclusiveFlg = true;
            }
        }
        // [处理区分7:收藏夹编辑]的场合
        if ("7".equals(checkKbn)) {
            CollectionTypeVo vo = this.collectionTypeMapper.queryOne(paramList.get(0).toString());
            if (vo.getUpdatedatetime().equals(updateDateTime)) {
                isExclusiveFlg = true;
            }
        }
        return isExclusiveFlg;
    }

}
