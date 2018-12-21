/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 *  �����鱨ϵͳ
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
 * Controller���ҵ��ղ�
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
     * toCollection����ת���ҵ��ղ�ҳ��
     *
     * @return String
     */
	@RequestMapping("toCollection")
	public String toCollection() {
		return "frontpage/collection";
	}
    /**
     * getOpts��ȡ������ѡ���ȫ�ġ�ģ��
     *
     * @return List
     */
	@RequestMapping("getOpts")
	@ResponseBody
	public List<SearchOptBean> getOpts(){
		return this.collectionService.getOpts();
	}
    /**
     * queryAll����ѯ��������
     *
     * @param MyCollectionVo ��������Vo
     * @param BaseBean ��ҳ������
     * @param SearchOptBean ����ѡ��
     * @param HttpSession ��ȡ��ǰuser��session
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
     * delete������ɾ���ղؼ��ڵ�����
     *
     * @param String[] checkedId ��ȡҪɾ��������id
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
     * deleteOne��ɾ���ղؼ��ڵ�����
     *
     * @param String id ��ȡҪɾ��������id
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
     * getTypes����ȡ����ղؼ��б�
     *
     * @param HttpSession ��ǰuser
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
     * getTypes������ղؼ�
     *
     * @param HttpSession ��ǰuser
     * @param CollectionTypeVo ����ղؼе���Ϣ
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
     * updateType�������ղؼ�
     *
     * @param HttpSession ��ǰuser
     * @param CollectionTypeVo �����ղؼе���Ϣ
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
		if (!dbUpdateCheckService.DBUpdateCheck("7", id,vo.getUpdatedatetime())) {
			map.put("msg", "checkFalse");
			return map;
		}
		String msg = this.collectionService.updateType(vo);
		map.put("msg", msg);
		return map;
	}
    /**
     * deleteType��ɾ���ղؼ�
     *
     * @param HttpSession ��ǰuser
     * @param CollectionTypeVo ɾ���ղؼе���Ϣ
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
     * move���ƶ�����
     *
     * @param String[] checkedId Ҫ�ƶ�������id
     * @param String collectiontypeId Ŀ���ղؼ�id
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
     * moveType���ƶ��ڵ�
     *
     * @param String collectiontypeId Ҫ�ƶ�������id
     * @param String targetId Ŀ���ղؼ�id�������������_top
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
