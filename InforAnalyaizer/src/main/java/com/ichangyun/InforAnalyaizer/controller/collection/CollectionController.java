/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.controller.collection;

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
import com.ichangyun.InforAnalyaizer.model.SearchOptBean;
import com.ichangyun.InforAnalyaizer.model.collection.CollectionTypeVo;
import com.ichangyun.InforAnalyaizer.model.collection.MyCollectionVo;
import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.service.collection.CollectionService;
import com.ichangyun.InforAnalyaizer.service.common.service.DBUpdateCheckService;

/**
 * 我的收藏
 *
 * @author lan
 * @date 2018/11/27
 */
@Controller
@RequestMapping("collection")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    @Autowired
    private DBUpdateCheckService dbUpdateCheckService;

    /**
     * toCollection：跳转到我的收藏页面
     *
     * @return String
     */
    @RequestMapping("toCollection")
    public String toCollection() {
        return "frontpage/collection";
    }
    /**
     * getOpts：取得搜索选项——全文、模糊
     *
     * @return List
     */
    @RequestMapping("getOpts")
    @ResponseBody
    public List<SearchOptBean> getOpts(){
        return this.collectionService.getOpts();
    }

    /**
     * queryAll：查询所有任务
     *
     * @param MyCollectionVo 检索条件Vo
     * @param BaseBean 分页工具类
     * @param SearchOptBean 搜索选项
     * @param HttpSession 获取当前user的session
     * @return map
     */
    @RequestMapping("queryAll")
    @ResponseBody
    public Map<String, Object>  queryAll(MyCollectionVo vo, BaseBean baseBean,SearchOptBean sob,HttpSession session){
        User u = (User)session.getAttribute(CommBean.SESSION_NAME);
        List<MyCollectionVo> vos = this.collectionService.queryAll(vo,baseBean,u,sob);
        int count = this.collectionService.queryCount(vo,u,sob);
        Map<String, Object> map = new HashMap<>();
        map.put("vos", vos);
        map.put("rowCount", count);
        return map;
    }

    /**
     * delete：批量删除收藏夹内的文章
     *
     * @param String[] checkedId 获取要删除的文章id
     * @return map
     */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String[] checkedId){
        String msg = this.collectionService.delete(checkedId);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        return map;
    }

    /**
     * deleteOne：删除收藏夹内的文章
     *
     * @param String id 获取要删除的文章id
     * @return map
     */
    @RequestMapping("deleteOne")
    @ResponseBody
    public Map<String, Object> deleteOne(String id){
        String[] ids = {id};
        String msg = this.collectionService.delete(ids);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        return map;
    }

    /**
     * getTypes：获取左侧收藏夹列表
     *
     * @param HttpSession 当前user
     * @return map
     */
    @RequestMapping("getTypes")
    @ResponseBody
    public List<CollectionTypeVo> getTypes(HttpSession session){
        User u = (User)session.getAttribute(CommBean.SESSION_NAME);
        List<CollectionTypeVo> types = this.collectionService.getTypes(u);
        return types;
    }

    /**
     * getTypes：添加收藏夹
     *
     * @param HttpSession 当前user
     * @param CollectionTypeVo 添加收藏夹的信息
     * @return map
     */
    @RequestMapping("addType")
    @ResponseBody
    public Map<String, Object> addType(HttpSession session,CollectionTypeVo vo){
        User u = (User)session.getAttribute(CommBean.SESSION_NAME);
        vo.setUserId(u.getUser_ID());
        String msg = this.collectionService.addType(vo);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        return map;
    }

    /**
     * updateType：更新收藏夹
     *
     * @param HttpSession 当前user
     * @param CollectionTypeVo 更新收藏夹的信息
     * @return map
     */
    @RequestMapping("updateType")
    @ResponseBody
    public Map<String, Object> updateType(HttpSession session,CollectionTypeVo vo){
        User u = (User)session.getAttribute(CommBean.SESSION_NAME);
        vo.setUserId(u.getUser_ID());
        List<String> id = new ArrayList<>();
        id.add(vo.getCollectiontypeId());
        Map<String, Object>map = new HashMap<>();
        if (!dbUpdateCheckService.DBUpdateCheck("7", id,vo.getUpdatedatetime(),session)) {
            map.put("msg", "checkFalse");
            return map;
        }
        String msg = this.collectionService.updateType(vo);
        map.put("msg", msg);
        return map;
    }

    /**
     * deleteType：删除收藏夹
     *
     * @param HttpSession 当前user
     * @param CollectionTypeVo 删除收藏夹的信息
     * @return map
     */
    @RequestMapping("deleteType")
    @ResponseBody
    public Map<String, Object> deleteType(HttpSession session,CollectionTypeVo vo){
        User u = (User)session.getAttribute(CommBean.SESSION_NAME);
        vo.setUserId(u.getUser_ID());
        String msg= this.collectionService.deleteType(vo);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        return map;
    }

    /**
     * move：移动文章
     *
     * @param String[] checkedId 要移动的文章id
     * @param String collectiontypeId 目标收藏夹id
     * @return map
     */
    @RequestMapping("move")
    @ResponseBody
    public Map<String, Object> move(String[] checkedId,String collectiontypeId){
        String msg = this.collectionService.move(checkedId,collectiontypeId);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        return map;
    }

    /**
     * moveType：移动节点
     *
     * @param String collectiontypeId 要移动的文章id
     * @param String targetId 目标收藏夹id，如果是排序则_top
     * @return map
     */
    @RequestMapping("moveType")
    @ResponseBody
    public Map<String, Object> moveType(String collectiontypeId,String targetId){
        String msg = "ok";
        Map<String, Object> map = new HashMap<>();
        msg = this.collectionService.moveType(collectiontypeId, targetId);
        map.put("msg", msg);
        return map;
    }
}
