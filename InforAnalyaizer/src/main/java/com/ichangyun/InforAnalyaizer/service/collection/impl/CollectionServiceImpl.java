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
 * Service���ҵ��ղ�
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
     * getOpts�����ؼ���ѡ��
     *
     * @return list
     */
	@Override
	public List<SearchOptBean> getOpts() {
		return this.collectionMapper.getOpts();
	}
    /**
     * queryAll�����ز�ѯ�ղ�����
     *
     * @param MyCollectionVo	����
     * @param BaseBean	��ҳ��
     * @param User		��ǰ�û�
     * @return list
     */
	@Override
	public List<MyCollectionVo> queryAll(MyCollectionVo vo, BaseBean bb, User u) {
		vo.setUserId(u.getUser_ID());
		Map<String, Object> key = new HashMap<>();
		key = Obj2Map.object2Map(vo);
		int l_pre = (bb.getPageNow() - 1) * bb.getRowSize();
		// ��ѯ������map����
		key.put("l_pre", l_pre);
		key.put("rowSize", bb.getRowSize());
		return this.collectionMapper.queryAll(key);
	}
    /**
     * queryCount�����ز�ѯ�ղ����µ�����
     *
     * @param MyCollectionVo	����
     * @param User		��ǰ�û�
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
     * delete��ɾ���ղ�����
     *
     * @param String[] checkedId	��Ϣid
     * @return String
     */
	@Override
	public String delete(String[] checkedId) {
		MyCollectionVo vo1 = new MyCollectionVo();
		vo1.setKey(checkedId[0]);							//��ȡ��ǰ�ļ�����Ϣ
		String userid = vo1.getUserId();					//�ղ��û���Ϣ
		String collectionTypeId = vo1.getCollectiontypeId();//�ղؽڵ���Ϣ
		
		String artIds;										//�ղ�����id
		List<String> aIds = new ArrayList<>();
		Map<String, String> key = new HashMap<>();
		for (String id : checkedId) {
			MyCollectionVo vo = new MyCollectionVo();
			vo.setKey(id);
			aIds.add(vo.getArticleId());
		}
		
		
		StringBuilder aid = new StringBuilder();	//������ѡ�е����µ�id�����ַ���
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
     * getTypes����ȡ��ǰ�û����ղؼ���Ϣ
     *
     * @param User u	��ǰ�û�
     * @return list
     */
	@Override
	public List<CollectionTypeVo> getTypes(User u) {
		List<CollectionTypeVo>list = this.typeMapper.queryAllType(u.getUser_ID());
		 List<CollectionTypeVo> neat = this.neat(list,"C000000000");
		 return this.setIsParent(neat);
	}
    /**
     * addType������ղؼ�
     *
     * @param  CollectionTypeVo	�ղؼ���Ϣ
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
		}//Ϊ�ղ�����
		
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
     * updateType�������ղؼ�
     *
     * @param  CollectionTypeVo	�ղؼ���Ϣ
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
     * move���ƶ�����
     *
     * @param  String[] checkedId
     * @param  collectiontypeId	�ղؼ�id
     * @return String
     */
	@Override
	public String move(String[] checkedId, String collectiontypeId) {
		MyCollectionVo vo1 = new MyCollectionVo();
		vo1.setKey(checkedId[0]);							//��ȡ��ǰ�ļ�����Ϣ
		String userid = vo1.getUserId();					//�ղ��û���Ϣ
		String collectionTypeId = vo1.getCollectiontypeId();//�ղؽڵ���Ϣ
		String artIds;										//�ղ�����id
		List<String> aIds = new ArrayList<>();
		Map<String, String> key = new HashMap<>();
		for (String id : checkedId) {
			MyCollectionVo vo = new MyCollectionVo();
			vo.setKey(id);
			aIds.add(vo.getArticleId());
		}
		StringBuilder aid = new StringBuilder();	//������ѡ�е����µ�id�����ַ���
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
     * deleteType��ɾ���ڵ�
     *
     * @param CollectionTypeVo vo	�ڵ���Ϣ
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
     * moveType���ƶ��ڵ�
     *
     * @param String collectiontypeId	Ҫ�ƶ��Ľڵ�id
     * @param String targetId		Ŀ��id���������������id_top��
     * @return String
     */
	
	@Override
	public String moveType(String collectiontypeId, String targetId) {
		String msg = "ok";
		String[]ids = targetId.split("_");
		CollectionTypeVo vo = this.typeMapper.queryOne(collectiontypeId);//��ǰ�ڵ�
		CollectionTypeVo pvo = this.typeMapper.queryOne(ids[0]);		//Ŀ��ڵ�
		//��Ŀ��ڵ�Ϊ��ǰ�ڵ���ӽڵ�ʱ
		String cpath = vo.getCollectionpath();
		String tpath = pvo.getCollectionpath();
		if(tpath.indexOf(cpath)!=-1) {
			return "fault";
		}
		if(ids.length==1&&!ids[0].equals(collectiontypeId)) {		//�ƶ���Ŀ���ļ�����	
			if(pvo.getCollectionstratum()==4) {
				return "overstep";
			}
			this.typeMapper.beforeOrder(vo);//�޸������ղؼе�˳���Դ˽ڵ��Ľڵ㶼��1
			vo.setParentCollectiontypeId(pvo.getCollectiontypeId());
			vo.setCollectionpath(pvo.getCollectionpath()+vo.getCollectiontypeId());
			vo.setCollectionstratum(vo.getCollectionpath().length()/10);
			vo.setDisplayorder(Integer.parseInt(pvo.getChildrenNum()));
			try {
				//��ʱӦ���޸�Ŀ���ղؼе�˳�򣬵�Ĭ����ӽ�β���Բ����޸�	
				
				this.typeMapper.updateByPrimaryKeySelective(vo);
			} catch (Exception e) {
				msg = "fault";
				e.printStackTrace();
			}
		}else if(ids.length==2&&!ids[0].equals(collectiontypeId)) {//�ƶ���Ŀ��ǰ��

			int oldDisplayOrder = vo.getDisplayorder();			//ԭʼ��ʾ˳�򡪡���ͬ�ļ������ƶ�ʹ��
			int targetDisplayOrder = pvo.getDisplayorder();		//Ŀ���ʾ˳�򡪡���ͬ�ļ������ƶ�ʹ��
			if (!vo.getParentCollectiontypeId().equals(pvo.getParentCollectiontypeId())) {		//��ʱ����ͬһ���ļ���������
				this.typeMapper.afterOrder(pvo);			//Ŀ�길�ڵ�ڵ���ʾ˳���һ
				this.typeMapper.beforeOrder(vo);			//��ǰ���ڵ�ڵ���ʾ˳��-1
				voLoad(vo, pvo);
				try {
					this.typeMapper.updateByPrimaryKeySelective(vo);
				} catch (Exception e) {
					msg = "fault";
					e.printStackTrace();
				}
			}else{		//��ͬһ���ļ�����
				if (oldDisplayOrder>targetDisplayOrder) {		//��������
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
				}else {				//��������
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
	//װ��vo��Ϣ������moveType()
	private void voLoad(CollectionTypeVo vo,CollectionTypeVo pvo) {	
		vo.setParentCollectiontypeId(pvo.getParentCollectiontypeId());
		vo.setCollectionpath(pvo.getCollectionpath().substring(0, pvo.getCollectionpath().length()-10)+vo.getCollectiontypeId());
		vo.setDisplayorder(pvo.getDisplayorder());
		vo.setCollectionstratum(vo.getCollectionpath().length()/10);
	}
	//������ȫ������ļ�������Ϊÿ����������ӽڵ�ļ���
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
	//�����ڵ��ʶ�����ȥ
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
