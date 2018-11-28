/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.service.numberingcontrol.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ichangyun.InforAnalyaizer.mapper.numbercontrol.NumberControlMapper;
import com.ichangyun.InforAnalyaizer.model.numbercontroll.NumberingcontrolBean;
import com.ichangyun.InforAnalyaizer.service.numberingcontrol.NumberingcontrolService;

/**
 * @author renhao
 * 2018-11-13 11:12
 */
@Service
public class NumberingcontrolServiceImpl implements NumberingcontrolService {

    //采番 Mapper
	@Autowired
	private NumberControlMapper numberControlMapper;
	
	@Resource(name = "transactionManager")
    private PlatformTransactionManager platformTransactionManager;
	
	@Override
	public String getNextCFID(String cpid) {
		TransactionStatus status = null;
		try {
			//关闭Spring事务自动提交
	        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
	        status = platformTransactionManager.getTransaction(def);
			
	        NumberingcontrolBean nb = new NumberingcontrolBean();
	        nb.setControlID(cpid);
	        NumberingcontrolBean id_nb =  numberControlMapper.getInfoByID(nb);
	        
	        String first_char = id_nb.getFirstCharacter();
	        int ws = id_nb.getNumberOfDigits();
	        int presetval = id_nb.getPresentValue()+1;
	        
	        int p_lg = String.valueOf(presetval).length();
	        
	        StringBuilder sb = new StringBuilder(first_char); 
	        for(int i=0;i<ws-1-p_lg;i++) {
	        	sb.append("0");
	        }
	        sb.append(presetval);
	        
	        nb.setPresentValue(presetval);
	        numberControlMapper.updatePresetVal(nb);
	        
	        platformTransactionManager.commit(status);  //提交事务
	        
			return sb.toString();
		}catch(Exception e) {
			e.printStackTrace();
			if(status!=null) {platformTransactionManager.rollback(status);}
			return "" ;
		}
	}

}
