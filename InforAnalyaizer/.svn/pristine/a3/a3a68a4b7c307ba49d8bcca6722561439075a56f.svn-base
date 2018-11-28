package com.ichangyun.InforAnalyaizer.service.collection.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ichangyun.InforAnalyaizer.mapper.collection.CollectionTypeMapper;
import com.ichangyun.InforAnalyaizer.mapper.collection.MyCollectionMapper;
import com.ichangyun.InforAnalyaizer.model.BaseBean;
import com.ichangyun.InforAnalyaizer.model.SearchOptBean;
import com.ichangyun.InforAnalyaizer.model.User;
import com.ichangyun.InforAnalyaizer.model.collection.CollectionTypeVo;
import com.ichangyun.InforAnalyaizer.model.collection.MyCollectionVo;
import com.ichangyun.InforAnalyaizer.service.collection.CollectionService;
import com.ichangyun.InforAnalyaizer.utils.Obj2Map;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
/**
 * Service：我的收藏
 *
 * @author lan
 * @date 2018/11/27
 */
@Service
public class CollectionServiceImpl implements CollectionService {
	@Autowired
	private MyCollectionMapper collectionMapper;
	@Autowired
	private CollectionTypeMapper typeMapper;
    /**
     * getOpts：返回检索选项
     *
     * @return list
     */
	@Override
	public List<SearchOptBean> getOpts() {
		// TODO Auto-generated method stub
		return this.collectionMapper.getOpts();
	}
    /**
     * queryAll：返回查询收藏文章
     *
     * @param MyCollectionVo	条件
     * @param BaseBean	分页类
     * @param User		当前用户
     * @return list
     */
	@Override
	public List<MyCollectionVo> queryAll(MyCollectionVo vo, BaseBean bb, User u) {
		// TODO Auto-generated method stub
		vo.setUserId(u.getUser_ID());
		Map<String, Object> key = new HashMap<>();
		key = Obj2Map.object2Map(vo);
		int l_pre = (bb.getPageNow() - 1) * bb.getRowSize();
		// 查询条件的map参数
		key.put("l_pre", l_pre);
		key.put("rowSize", bb.getRowSize());
		return this.collectionMapper.queryAll(key);
	}
    /**
     * queryCount：返回查询收藏文章的数量
     *
     * @param MyCollectionVo	条件
     * @param User		当前用户
     * @return int
     */
	@Override
	public int queryCount(MyCollectionVo vo, User u) {
		// TODO Auto-generated method stub
		vo.setUserId(u.getUser_ID());
		Map<String, Object> key = new HashMap<>();
		key = Obj2Map.object2Map(vo);
		return this.collectionMapper.queryCount(key);
	}
    /**
     * delete：删除收藏文章
     *
     * @param String[] checkedId	信息id
     * @return String
     */
	@Override
	public String delete(String[] checkedId) {
		// TODO Auto-generated method stub
		MyCollectionVo vo1 = new MyCollectionVo();
		vo1.setKey(checkedId[0]);							//获取当前文件夹信息
		String userid = vo1.getUserId();					//收藏用户信息
		String collectionTypeId = vo1.getCollectiontypeId();//收藏节点信息
		
		String artIds;										//收藏文章id
		List<String> aIds = new ArrayList<>();
		Map<String, String> key = new HashMap<>();
		for (String id : checkedId) {
			MyCollectionVo vo = new MyCollectionVo();
			vo.setKey(id);
			aIds.add(vo.getArticleId());
		}
		
		
		StringBuilder aid = new StringBuilder();	//将所有选中的文章的id做成字符串
		aid.append("'");
		for (int i = 0; i < aIds.size(); i++) {
			if (i < aIds.size() - 1) {
				aid.append(aIds.get(i) + "','");
			} else {
				aid.append(aIds.get(i) + "'");
			}
		}
		artIds = aid.toString();
		key.put("userid", userid);
		key.put("collectiontypeid", collectionTypeId);
		key.put("articleId", artIds);
		try {
			this.collectionMapper.delete(key);
		} catch (DataAccessException e) {
			// TODO: handle exception
			return "exist";
		}
		return "ok";
	}
    /**
     * getTypes：获取当前用户的收藏夹信息
     *
     * @param User u	当前用户
     * @return list
     */
	@Override
	public List<CollectionTypeVo> getTypes(User u) {
		// TODO Auto-generated method stub
		List<CollectionTypeVo>list = this.typeMapper.queryAllType(u.getUser_ID());
		 List<CollectionTypeVo> neat = this.neat(list,"0");
		 return this.setIsParent(neat);
	}
    /**
     * addType：添加收藏夹
     *
     * @param  CollectionTypeVo	收藏夹信息
     * @return String
     */
	@Override
	public String addType(CollectionTypeVo vo) {
		// TODO Auto-generated method stub
		String msg = "ok";
		vo.setValidflag("1");
		int count = this.typeMapper.queryCount(vo.getParentCollectiontypeId());
		vo.setDisplayorder(count);
		try {
			this.typeMapper.insertSelective(vo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg = "fault";
		}
		return msg;
	}
    /**
     * addType：添加收藏夹
     *
     * @param  CollectionTypeVo	收藏夹信息
     * @return String
     */
	@Override
	public String updateType(CollectionTypeVo vo) {
		// TODO Auto-generated method stub
		String msg = "ok";
		vo.setValidflag("1");
		try {
			this.typeMapper.updateByPrimaryKeySelective(vo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg = "fault";
		}
		return msg;
	}

    /**
     * move：移动文章
     *
     * @param  String[] checkedId
     * @param  collectiontypeId	收藏夹id
     * @return String
     */
	@Override
	public String move(String[] checkedId, String collectiontypeId) {
		// TODO Auto-generated method stub
		MyCollectionVo vo1 = new MyCollectionVo();
		vo1.setKey(checkedId[0]);							//获取当前文件夹信息
		String userid = vo1.getUserId();					//收藏用户信息
		String collectionTypeId = vo1.getCollectiontypeId();//收藏节点信息
		String artIds;										//收藏文章id
		List<String> aIds = new ArrayList<>();
		Map<String, String> key = new HashMap<>();
		for (String id : checkedId) {
			MyCollectionVo vo = new MyCollectionVo();
			vo.setKey(id);
			aIds.add(vo.getArticleId());
		}
		StringBuilder aid = new StringBuilder();	//将所有选中的文章的id做成字符串
		aid.append("'");
		for (int i = 0; i < aIds.size(); i++) {
			if (i < aIds.size() - 1) {
				aid.append(aIds.get(i) + "','");
			} else {
				aid.append(aIds.get(i) + "'");
			}
		}
		artIds = aid.toString();
		key.put("userid", userid);
		key.put("collectiontypeid", collectionTypeId);
		key.put("articleId", artIds);
		key.put("updateTypeId", collectiontypeId);
		try {
			this.collectionMapper.updateType(key);
		} catch (Exception e) {
			// TODO: handle exception
			return "exist";
		}
		return "ok";
	}

	//将包含全部对象的集合整理为每个对象带有子节点的集合
	public List<CollectionTypeVo> neat(List<CollectionTypeVo> list,String pid){
		List<CollectionTypeVo> res = new ArrayList<>();
			for (CollectionTypeVo vo : list) {
				if(vo.getParentCollectiontypeId().equals(pid)) {
					vo.setChildren(neat(list, vo.getCollectiontypeId()));
					res.add(vo);
				}
			}
		
		return res;
	}
	//将父节点表识添加上去
	public List<CollectionTypeVo> setIsParent(List<CollectionTypeVo> list){
		for (CollectionTypeVo vo : list) {
			if(vo.getChildren().size()>0) {
				vo.setIsParent(1);
				vo.setChildren(this.setIsParent(vo.getChildren()));
			}
		}
		return list;
	}

	@Override
	public String deleteType(CollectionTypeVo vo) {
		// TODO Auto-generated method stub
		try {
			this.typeMapper.deleteByPrimaryKey(vo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "fault";
		}
		return "ok";
	}
	
}
