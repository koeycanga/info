package com.ichangyun.InforAnalyaizer.controller.notice;

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
import com.ichangyun.InforAnalyaizer.model.notice.NoticeType;
import com.ichangyun.InforAnalyaizer.model.notice.NoticeVo;
import com.ichangyun.InforAnalyaizer.service.common.service.DBUpdateCheckService;
import com.ichangyun.InforAnalyaizer.service.notice.NoticeService;
import com.ichangyun.InforAnalyaizer.service.numberingcontrol.NumberingcontrolService;
import com.ichangyun.InforAnalyaizer.utils.DateUtils;
/**
 * Controller：简报任务
 *
 * @author lan
 * @date 2018/11/19
 */
@Controller
@RequestMapping("notice")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private NumberingcontrolService numberService;
	@Autowired
	private DBUpdateCheckService dbUpdateCheckService;
	@RequestMapping("toNoticeList")
	public String toNoticeList() {
		return "frontpage/noticeList";
	}
    /**
     * queryAll：查询所有任务
     *
     * @param BaseBean 分页工具类
     * @return map
     */
	@RequestMapping("queryAll")
	@ResponseBody
	public Map<String, Object>queryAll(BaseBean bb){
		Map<String, Object> list = this.noticeService.queryAll(bb.getPageNow(),bb.getRowSize());
	       return list;
	}
    /**
     * getTypes：查询所有任务类型
     *
     * @return list
     */
	@RequestMapping("getTypes")
	@ResponseBody
	public List<NoticeType>getTypes(){
		List<NoticeType> list = this.noticeService.getNoticeType();
	       return list;
	}
    /**
     * addNotice：添加简报任务
     *
     *@param NoticeVo简报任务视图类
     *@param HttpSession session
     * @return map
     */
	@RequestMapping("addNotice")
	@ResponseBody
	public Map<String, Object>addNotice(NoticeVo vo,HttpSession session){
		User u = (User) session.getAttribute(CommBean.SESSION_NAME);
		String noticeID = this.numberService.getNextCFID("NC00000001");//为简报命名
		vo.setNoticeid(noticeID);
		String msg= this.noticeService.addNotice(vo,u);
		Map<String, Object>map = new HashMap<>();
		map.put("msg", msg);
		return map;
	}
	/**
     * queryOne：根据id查询简报任务
     *
     *@param String noticeid	简报id
     *@return NoticeVo			简报任务视图类
     */
	@RequestMapping("queryOne")
	@ResponseBody
	public NoticeVo addNotice(String noticeid){
		NoticeVo vo = this.noticeService.queryOne(noticeid);
		return vo;
	}
	/**
     * updateNotice：更新简报任务
     *
     *@param NoticeVo简报任务视图类
     *@param HttpSession session
     *@return map
     */
	@RequestMapping("updateNotice")
	@ResponseBody
	public Map<String, Object>updateNotice(NoticeVo vo,HttpSession session){
		User u = (User) session.getAttribute(CommBean.SESSION_NAME);
		List<String> id = new ArrayList<>();
		id.add(vo.getNoticeid());
		Map<String, Object>map = new HashMap<>();
		if (!dbUpdateCheckService.DBUpdateCheck("6", id,vo.getUpdatedatetime())) {
			map.put("msg", "checkFalse");
			return map;
		}
		String msg= this.noticeService.updateNotice(vo,u);
		map.put("msg", msg);
		return map;
	}
	/**
     * deleteNotice：删除简报任务
     *
     *@param String[] checkedId  简报id
     *@return map
     */
	@RequestMapping("deleteNotice")
	@ResponseBody
	public Map<String, Object>deleteNotice(String[] checkedId){
		String msg = this.noticeService.deleteNotice(checkedId);	
		Map<String, Object>map = new HashMap<>();
		map.put("msg", msg);
		return map;
	}
}
