/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.service.classificationfilterwords.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ichangyun.InforAnalyaizer.mapper.classificationfilterwords.ClassificationFilterwordsMapper;
import com.ichangyun.InforAnalyaizer.model.classification.ClassificationInfoBean;
import com.ichangyun.InforAnalyaizer.model.classificationfilterwords.ClassificationFilterwordsWithBLOBs;
import com.ichangyun.InforAnalyaizer.model.classificationfilterwords.FilterWordsVo;
import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.service.classificationfilterwords.FilterWordsService;
import com.ichangyun.InforAnalyaizer.utils.filwterwordsUtils.OutputUtil;

@Service
public class FilterWordsServiceImpl implements FilterWordsService {
    @Autowired
    private ClassificationFilterwordsMapper fwMapper;

    @Override
    public Map<String, Object> queryAllFilterWords(FilterWordsVo vo, int pageNow, int rowSize) {
        Map<String, Object> key = new HashMap<>();
        int l_pre = (pageNow - 1) * rowSize;
        key.put("l_pre", l_pre);
        key.put("rowSize", rowSize);
        key.put("classificationName", vo.getClassificationName());
        List<FilterWordsVo> fwVos = this.fwMapper.selectByFwVo(key);
        Map<String, Object> res = new HashMap<>();
        int count = this.fwMapper.queryCount(key);
        List<String> list = this.fwMapper.queryParents();
        for (String p : list) {
            for (FilterWordsVo fwvo : fwVos) {
                if(fwvo.getClassificationId().equals(p)) {
                    fwvo.setIsParent(1);
                }else {

                }
            }
        }
        res.put("fwVos", fwVos);
        res.put("rowCount", count);
        return res;
    }

    @Override
    public FilterWordsVo queryOne(String classificationId) {
        return this.fwMapper.queryOne(classificationId);
    }

    @Override
    public String updateFwVo(FilterWordsVo vo, User u) {
        ClassificationFilterwordsWithBLOBs bs = this.fwMapper.selectByPrimaryKey(vo.getClassificationId());
        vo.setUpdateuser(u.getUser_ID());
        try {
            if(null!=bs) {
                this.fwMapper.updateFwVo(vo);
            }else {
                this.fwMapper.insertSelective(vo);
            }
        } catch (Exception e) {
            return "fault";
        }
        return "ok";
    }

    @Override
    public List<FilterWordsVo> queryChild(FilterWordsVo vo) {       //通过节点id查询当前节点的子节点
        List<FilterWordsVo> fwVos = this.fwMapper.queryChild(vo);
        return fwVos;
    }

    //导出选择的节点过滤词信息
    @Override
    public Object[] output(String[] idsandclg,String path) {
    	
    	 // Resources文件内容取得
        ResourceBundle resource = ResourceBundle.getBundle("resources");
    	int maxRowCount = Integer.parseInt(resource.getString("DownloadDataCount"));
        
    	Object[] obj = new Object[2];
    	
    	String msg = "";
    	
    	List<String> ids = getTargetIDS(idsandclg);
    	
        /*******************以下代码是组成查询形如('','','')的sql语句***************************************/
        StringBuilder fwid = new StringBuilder();
        fwid.append("'");
        for (int i = 0; i < ids.size(); i++) {
            if(i==ids.size()-1) {
                fwid.append(ids.get(i)+"'");
            }else {
                fwid.append(ids.get(i)+"','");
            }
        }
        /*******************以上代码是组成查询形如('','','')的sql语句***************************************/
         
        List<FilterWordsVo> vos = this.fwMapper.queryById(fwid.toString());  //查询符合条件的分类体系及其对应的过滤词信息
        HSSFWorkbook wb = null ;
        if(vos.size()>maxRowCount) {
        	msg = "overcount";
        }else {
	        try {
	            wb =  OutputUtil.getExcel(vos,path);  //生成导出Excel所需的HSSFWorkbook
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
        }
        obj[0] = wb;
        obj[1] = msg;
        return obj;
    }

    private List<String> getTargetIDS(String[] idsandclg) {
		
    	List<String> list = new ArrayList<String>();
    	
    	for(String id:idsandclg) {
    		String[] ss = id.split(":");
    		if(!ss[1].equals("1")) {   //是叶子节点
    			list.add(ss[0]);
    		}else {
    			List<String> children_ids = fwMapper.getChildrenIDS(ss[0]);  //获得其所有的叶子节点
    			list.addAll(children_ids);
    		}
    	}
		return list;
	}

	@Override
    public String input(List<FilterWordsVo> vos,String userid) {
        String msg = "ok";
        StringBuilder names = new StringBuilder();
        //通过名称查找这些节点的id
        names.append("'");
        for (int i = 0; i < vos.size(); i++) {
            FilterWordsVo vo = vos.get(i);
            String[] name = vo.getAllParent_name().split("》");
            if(i!=vos.size()-1) {
                names.append(name[name.length-1]+"','");
            }else {
                names.append(name[name.length-1]+"'");
            }
        }
        //添加节点属性，并将需要新建还是更新的节点分类
        if(!names.toString().equals("'")) {
            List<FilterWordsVo> checkList = this.fwMapper.queryByName(names.toString());
            List<FilterWordsVo> createVo = new ArrayList<>();
            for (FilterWordsVo checkVo : checkList) {
                for (int i = 0; i < vos.size(); i++) {
                    FilterWordsVo vo = vos.get(i);
                    if(vo.getAllParent_name().equals(checkVo.getAllParent_name())) {
                        vo.setClassificationId(checkVo.getClassificationId());
                        vo.setClassificationName(checkVo.getClassificationName());
                        if(checkVo.getInformationtropism()==null) {
                            createVo.add(vo);
                            vos.remove(i);
                            i--;
                        }
                    }
                }
            }
            //批量新增createVo
            if(createVo.size()>0) {

                try {
                    this.fwMapper.insertMany(createVo,userid);
                } catch (Exception e) {
                    e.printStackTrace();
                    msg="fault";
                }
            }
            //批量更新vos
            if(vos.size()>0) {
                try {
                    this.fwMapper.updateMany(vos,userid);
                } catch (Exception e) {
                    e.printStackTrace();
                    msg="fault";
                }
            }
        }
        return msg;
    }

    @Override
    public List<ClassificationInfoBean> getAllClassificationNames() {

        return this.fwMapper.getAllClassificationNames();
    }
}
