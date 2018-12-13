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
import com.ichangyun.InforAnalyaizer.model.collection.CollectionTypeVo;
import com.ichangyun.InforAnalyaizer.model.collection.MyCollectionVo;
import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.service.collection.CollectionService;
import com.ichangyun.InforAnalyaizer.service.numberingcontrol.NumberingcontrolService;
import com.ichangyun.InforAnalyaizer.utils.Obj2Map;
/**
 * Service：我的收藏
 *
 * @author lan
 * @date 2018/11/27
 */
@Service
public class CollectionServiceImpl implements CollectionService {
	
	@Autowired
	private NumberingcontrolService numberService;
	
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
		List<CollectionTypeVo>list = this.typeMapper.queryAllType(u.getUser_ID());
		 List<CollectionTypeVo> neat = this.neat(list,"C000000000");
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
		
		String msg = "ok";
		String cID;
		try {
			cID = this.numberService.getNextCFID("NC00000002");
			vo.setCollectiontypeId(cID);
		} catch (Exception e1) {
			e1.printStackTrace();
		}//为收藏命名
		
		vo.setCollectionpath(vo.getPpath()+vo.getCollectiontypeId());
		vo.setCollectionstratum(vo.getCollectionpath().length()/10);
		if(vo.getCollectionstratum()>4) {
			return "overstep";
		}
		vo.setValidflag("1");
		vo.setDisplayorder(this.typeMapper.queryCount(vo.getParentCollectiontypeId()));
		
		try {
			
			
			this.typeMapper.insertSelective(vo);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "fault";
		}
		return msg;
	}
    /**
     * updateType：更新收藏夹
     *
     * @param  CollectionTypeVo	收藏夹信息
     * @return String
     */
	@Override
	public String updateType(CollectionTypeVo vo) {
		String msg = "ok";
		try {
			this.typeMapper.updateByPrimaryKeySelective(vo);
		} catch (Exception e) {
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
			return "exist";
		}
		return "ok";
	}
	

    /**
     * deleteType：删除节点
     *
     * @param CollectionTypeVo vo	节点信息
     * @return String
     */
	@Override
	public String deleteType(CollectionTypeVo vo) {
		try {
			this.typeMapper.deleteByPrimaryKey(vo);
			this.typeMapper.beforeOrder(vo);
		} catch (Exception e) {
			e.printStackTrace();
			return "fault";
		}
		return "ok";
	}
    /**
     * moveType：移动节点
     *
     * @param String collectiontypeId	要移动的节点id
     * @param String targetId		目标id（如果是排序，则是id_top）
     * @return String
     */
	
	@Override
	public String moveType(String collectiontypeId, String targetId) {
		String msg = "ok";
		String[]ids = targetId.split("_");
		CollectionTypeVo vo = this.typeMapper.queryOne(collectiontypeId);//当前节点
		CollectionTypeVo pvo = this.typeMapper.queryOne(ids[0]);		//目标节点
		//当目标节点为当前节点的子节点时
		String cpath = vo.getCollectionpath();
		String tpath = pvo.getCollectionpath();
		if(tpath.indexOf(cpath)!=-1) {
			return "fault";
		}
		if(ids.length==1&&!ids[0].equals(collectiontypeId)) {		//移动到目标文件夹内	
			if(pvo.getCollectionstratum()==4) {
				return "overstep";
			}
			this.typeMapper.beforeOrder(vo);//修改自身收藏夹的顺序，自此节点后的节点都减1
			vo.setParentCollectiontypeId(pvo.getCollectiontypeId());
			vo.setCollectionpath(pvo.getCollectionpath()+vo.getCollectiontypeId());
			vo.setCollectionstratum(vo.getCollectionpath().length()/10);
			vo.setDisplayorder(Integer.parseInt(pvo.getChildrenNum()));
			try {
				//此时应该修改目标收藏夹的顺序，但默认添加结尾所以不做修改	
				
				this.typeMapper.updateByPrimaryKeySelective(vo);
			} catch (Exception e) {
				msg = "fault";
				e.printStackTrace();
			}
		}else if(ids.length==2&&!ids[0].equals(collectiontypeId)) {//移动到目标前面

			int oldDisplayOrder = vo.getDisplayorder();			//原始表示顺序——在同文件夹下移动使用
			int targetDisplayOrder = pvo.getDisplayorder();		//目标表示顺序——在同文件夹下移动使用
			if (!vo.getParentCollectiontypeId().equals(pvo.getParentCollectiontypeId())) {		//此时不在同一个文件夹下排序
				this.typeMapper.afterOrder(pvo);			//目标父节点节点后表示顺序加一
				this.typeMapper.beforeOrder(vo);			//当前父节点节点后表示顺序-1
				voLoad(vo, pvo);
				try {
					this.typeMapper.updateByPrimaryKeySelective(vo);
				} catch (Exception e) {
					msg = "fault";
					e.printStackTrace();
				}
			}else{		//在同一个文件夹下
				if (oldDisplayOrder>targetDisplayOrder) {		//往上排序
					voLoad(vo, pvo);
					
					Map<String, Object> map = Obj2Map.object2Map(vo);
					map.put("oldDisplayOrder", oldDisplayOrder);
					this.typeMapper.changeOrder1(map);
					try {
						this.typeMapper.updateByPrimaryKeySelective(vo);
					} catch (Exception e) {
						msg = "fault";
						e.printStackTrace();
					}
				}else {				//往下排序
					voLoad(vo, pvo);
					vo.setDisplayorder(pvo.getDisplayorder()-1);
					Map<String, Object> map = Obj2Map.object2Map(vo);
					map.put("oldDisplayOrder", oldDisplayOrder);
					this.typeMapper.changeOrder2(map);
					try {
						this.typeMapper.updateByPrimaryKeySelective(vo);
					} catch (Exception e) {
						msg = "fault";
						e.printStackTrace();
					}
				}
			}

			
		}
		return msg;
	}
	//装填vo信息，用于moveType()
	private void voLoad(CollectionTypeVo vo,CollectionTypeVo pvo) {	
		vo.setParentCollectiontypeId(pvo.getParentCollectiontypeId());
		vo.setCollectionpath(pvo.getCollectionpath().substring(0, pvo.getCollectionpath().length()-10)+vo.getCollectiontypeId());
		vo.setDisplayorder(pvo.getDisplayorder());
		vo.setCollectionstratum(vo.getCollectionpath().length()/10);
	}
	//将包含全部对象的集合整理为每个对象带有子节点的集合
	private List<CollectionTypeVo> neat(List<CollectionTypeVo> list,String pid){
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
	private List<CollectionTypeVo> setIsParent(List<CollectionTypeVo> list){
		for (CollectionTypeVo vo : list) {
			if(vo.getChildren().size()>0) {
				vo.setIsParent(1);
				vo.setChildren(this.setIsParent(vo.getChildren()));
			}
		}
		return list;
	}
}
