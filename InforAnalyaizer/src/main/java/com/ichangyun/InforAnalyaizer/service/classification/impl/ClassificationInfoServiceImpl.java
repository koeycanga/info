/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.service.classification.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ichangyun.InforAnalyaizer.mapper.classification.ClassificationInfoMapper;
import com.ichangyun.InforAnalyaizer.model.CommBean;
import com.ichangyun.InforAnalyaizer.model.classification.ClassificationInfoBean;
import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.service.classification.ClassificationInfoService;
import com.ichangyun.InforAnalyaizer.service.numberingcontrol.NumberingcontrolService;
import com.ichangyun.InforAnalyaizer.utils.DateUtils;

@Service
public class ClassificationInfoServiceImpl implements ClassificationInfoService {


    //分类体系Mapper
    @Autowired
    private ClassificationInfoMapper classificationInfoMapper;

    @Autowired
    private NumberingcontrolService numberingcontrolService;

    @Resource(name = "transactionManager")
    private PlatformTransactionManager platformTransactionManager;

    @Override
    public int getClassifcInfoCount(ClassificationInfoBean cb) {
        return classificationInfoMapper.getClassifcInfoCount(cb);
    }

    @Override
    public String getClassifcInfo(ClassificationInfoBean cb) {
        List<ClassificationInfoBean> list = null;
        cb.setL_pre((cb.getPageNow()-1)*cb.getRowSize());

        list = classificationInfoMapper.getClassifcInfo(cb);

        JSONArray listArray=(JSONArray) JSONArray.toJSON(list);

        return listArray.toJSONString();
    }

    @Override
    public String getClassificationInfoID() {

        try {
            return numberingcontrolService.getNextCFID("NC00000004");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    @Override
    public boolean AddNew(ClassificationInfoBean cb) {

        String id = this.getClassificationInfoID();

        cb.setClassification_ID(id);

        TransactionStatus status = null;
        try {
            //关闭Spring事务自动提交
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            status = platformTransactionManager.getTransaction(def);

            int display_order = classificationInfoMapper.getDisplayOrder(cb);

            cb.setDisplayOrder(display_order);

            if(cb.getClassificationPath().equals("")) {
                cb.setClassificationPath(cb.getClassification_ID());
            }else {
                cb.setClassificationPath(cb.getClassificationPath()+"/"+cb.getClassification_ID());
            }

            int res = classificationInfoMapper.addNew(cb);

            platformTransactionManager.commit(status);  //提交事务

            if(res==1) {
                return true;
            }

            return false;
        }catch(Exception e) {
            if(status!=null) {platformTransactionManager.rollback(status);}
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean exist(ClassificationInfoBean cb) {

        int count = classificationInfoMapper.exist(cb);

        if(count>=1) {
            return true;
        }

        return false;
    }

    @Override
    public boolean existExceptID(ClassificationInfoBean cb) {
        int count = classificationInfoMapper.existExceptID(cb);
        if(count>=1) {
            return true;
        }

        return false;
    }

    @Override
    public String updateData(ClassificationInfoBean cb) {

        int count = classificationInfoMapper.updateData(cb);

        if(count==1) {
            String updateTime = classificationInfoMapper.getUpdateTime(cb);
            return "success"+updateTime;
        }

        return "nok";
    }

    @Override
    public boolean delDSata(String json) {

        JSONArray parseArray = JSONArray.parseArray(json);

        for(int i=0;i<parseArray.size();i++) {
            JSONObject jo = (JSONObject) parseArray.get(i);
            ClassificationInfoBean cb = new ClassificationInfoBean();
            cb.setClassification_ID(jo.getString("id"));
            classificationInfoMapper.UpdateDisPlayOrderById(cb);
            classificationInfoMapper.delDataByFunction(cb);
        }

        return true;
    }


    @Override
    public void updateOrder(String cur_Classification_ID, int cur_displayOrder, String ch_Classification_ID,
            int ch_displayOrder) {

        ClassificationInfoBean cb1 = new ClassificationInfoBean();
        cb1.setClassification_ID(cur_Classification_ID);
        cb1.setDisplayOrder(cur_displayOrder);
        classificationInfoMapper.updateOrder(cb1);


        ClassificationInfoBean cb2 = new ClassificationInfoBean();
        cb2.setClassification_ID(ch_Classification_ID);
        cb2.setDisplayOrder(ch_displayOrder);
        classificationInfoMapper.updateOrder(cb2);
    }

    @Override
    public String getChildernByID(ClassificationInfoBean cb) {

        List<ClassificationInfoBean> list = classificationInfoMapper.getChildernByID(cb);

        JSONArray listArray=(JSONArray) JSONArray.toJSON(list);

        return listArray.toJSONString();
    }

    @Override
    public String getInfoByID(ClassificationInfoBean cb) {
        ClassificationInfoBean acb = classificationInfoMapper.getInfoByID(cb);
        JSONObject jobj = (JSONObject) JSONObject.toJSON(acb);
        return jobj.toJSONString();
    }

    @Override
    public String getAllClassification(HttpSession session) {

        String yesterday = DateUtils.format(DateUtils.addDateDays(new Date(), -1), DateUtils.DATE_PATTERN);

        String today = DateUtils.format(new Date(), DateUtils.DATE_PATTERN);

        ClassificationInfoBean cb = new ClassificationInfoBean();
        User u = (User) session.getAttribute(CommBean.SESSION_NAME);
    	cb.setCustomer_ID(u.getCustomer_ID());
    	cb.setCollectionField_ID(u.getCollectionField_ID());
        
        List<ClassificationInfoBean> list = classificationInfoMapper.getAllClassification(cb,yesterday,today);

        JSONArray listArray=(JSONArray) JSONArray.toJSON(list);

        return listArray.toJSONString();
    }

}
