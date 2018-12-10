package com.ichangyun.InforAnalyaizer.service.classificationfilterwords.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ichangyun.InforAnalyaizer.mapper.classificationfilterwords.ClassificationFilterwordsMapper;
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
	public List<FilterWordsVo> queryChild(FilterWordsVo vo) {		//通过节点id查询当前节点的子节点
		List<FilterWordsVo> fwVos = this.fwMapper.queryChild(vo);
		List<String> list = this.fwMapper.queryParents();
		for (String p : list) {
			for (FilterWordsVo fwvo : fwVos) {
				if(fwvo.getClassificationId().equals(p)) {
					fwvo.setIsParent(1);
				}	
			}
		}
		return fwVos;
	}
	//导出选择的节点过滤词信息
	@Override
	public HSSFWorkbook output(String[] ids) {
		StringBuilder fwid = new StringBuilder();
		fwid.append("'");
		for (int i = 0; i < ids.length; i++) {
			if(i==ids.length-1) {
				fwid.append(ids[i]+"'");
			}else {
				fwid.append(ids[i]+"','");
			}
		}
		List<FilterWordsVo> vos = this.fwMapper.queryById(fwid.toString());
		HSSFWorkbook wb = null ;
		try {
			wb =  OutputUtil.getExcel(vos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wb;
	}

	@Override
	public String input(List<FilterWordsVo> vos) {
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
					}
				}
			}
		}
		//批量新增createVo
		if(createVo.size()>0) {
			
			try {			
				this.fwMapper.insertMany(createVo);
			} catch (Exception e) {
				e.printStackTrace();
				msg="fault";
			}
		}
		//批量更新vos
		if(vos.size()>0) {
			try {			
				this.fwMapper.updateMany(vos);
			} catch (Exception e) {
				e.printStackTrace();
				msg="fault";
			}	
		}
		return msg;
	}
}
