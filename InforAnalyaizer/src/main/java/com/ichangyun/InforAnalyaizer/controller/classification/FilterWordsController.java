package com.ichangyun.InforAnalyaizer.controller.classification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ichangyun.InforAnalyaizer.model.BaseBean;
import com.ichangyun.InforAnalyaizer.model.CommBean;
import com.ichangyun.InforAnalyaizer.model.User;
import com.ichangyun.InforAnalyaizer.model.classificationfilterwords.FilterWordsVo;
import com.ichangyun.InforAnalyaizer.service.classificationfilterwords.FilterWordsService;
import com.ichangyun.InforAnalyaizer.service.common.service.DBUpdateCheckService;
import com.ichangyun.InforAnalyaizer.utils.DateUtils;
/**
 *
 * 过滤词管理controller层接口类
 */
@Controller
@RequestMapping("/qbgh/filterWords")
public class FilterWordsController {
    @Autowired
    private FilterWordsService fwService;
    @Autowired
    private DBUpdateCheckService dbUpdateCheckService;

    @RequestMapping("/toFilterWords")
    public String toList() {
        return "classifcation/filterWords";
    }
    /**
     * 查询所有节点过滤词信息，可带条件
     *
     * @param FilterWordsVo vo, BaseBean bb
     * @return Map<String, Object>
     */
    @ResponseBody
    @RequestMapping("/queryAll")// 查询所有节点过滤词信息，可带条件
    public Map<String, Object> queryAll(FilterWordsVo vo, BaseBean bb) {
        Map<String, Object> list = this.fwService.queryAllFilterWords(vo, bb.getPageNow(), bb.getRowSize());
        return list;
    }
    /**
     * 根据id查询节点过滤词信息
     *
     * @param String classificationId
     * @return FilterWordsVo
     */
    @ResponseBody
    @RequestMapping("/queryOne") // 根据id查询节点过滤词信息
    public FilterWordsVo queryOne(String classificationId) {
        FilterWordsVo vo = this.fwService.queryOne(classificationId);
        return vo;
    }
    /**
     * 根据id查询节点过滤词信息
     *
     * @param FilterWordsVo vo
     * @return Map<String, String>
     */
    @ResponseBody
    @RequestMapping("/updateFwVo") // 更新节点过滤词信息
    public Object updateFwVo(FilterWordsVo vo, HttpSession session) {
        User u = (User) session.getAttribute(CommBean.SESSION_NAME);
        Map<String, String> map = new HashMap<>();
        List<String> ids = new ArrayList<>();
        ids.add(vo.getClassificationId());
        if(vo.getUpdatedatetime()!=null) {
        	if (!dbUpdateCheckService.DBUpdateCheck("4", ids,vo.getUpdatedatetime())) {
        		map.put("msg", "checkFalse");
        		return map;
        	}
        }
		String value = this.fwService.updateFwVo(vo, u);
        map.put("msg", value);
        return map;
    }
    /**
     * 查询当前节点的所有子节点
     *
     * @param FilterWordsVo vo
     * @return List<FilterWordsVo>
     */
    @ResponseBody
    @RequestMapping("/queryChild") // 查询当前节点的所有子节点
    public List<FilterWordsVo> queryChild(FilterWordsVo vo) {
        List<FilterWordsVo> children = this.fwService.queryChild(vo);
        return children;
    }
}
