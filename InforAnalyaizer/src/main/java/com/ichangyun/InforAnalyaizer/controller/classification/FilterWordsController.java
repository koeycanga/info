package com.ichangyun.InforAnalyaizer.controller.classification;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ichangyun.InforAnalyaizer.model.BaseBean;
import com.ichangyun.InforAnalyaizer.model.CommBean;
import com.ichangyun.InforAnalyaizer.model.classificationfilterwords.FilterWordsVo;
import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.service.classificationfilterwords.FilterWordsService;
import com.ichangyun.InforAnalyaizer.service.common.service.DBUpdateCheckService;
import com.ichangyun.InforAnalyaizer.utils.filwterwordsUtils.InputUtil;
import com.ichangyun.InforAnalyaizer.utils.filwterwordsUtils.OutputUtil;

/**
 *
 * ���˴ʹ���controller��ӿ���
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
	 * ��ѯ���нڵ���˴���Ϣ���ɴ�����
	 *
	 * @param FilterWordsVo vo, BaseBean bb
	 * @return Map<String, Object>
	 */
	@ResponseBody
	@RequestMapping("/queryAll") // ��ѯ���нڵ���˴���Ϣ���ɴ�����
	public Map<String, Object> queryAll(FilterWordsVo vo, BaseBean bb) {
		Map<String, Object> list = this.fwService.queryAllFilterWords(vo, bb.getPageNow(), bb.getRowSize());
		return list;
	}

	/**
	 * ����id��ѯ�ڵ���˴���Ϣ
	 *
	 * @param String classificationId
	 * @return FilterWordsVo
	 */
	@ResponseBody
	@RequestMapping("/queryOne") // ����id��ѯ�ڵ���˴���Ϣ
	public FilterWordsVo queryOne(String classificationId) {
		FilterWordsVo vo = this.fwService.queryOne(classificationId);
		return vo;
	}

	/**
	 * ����id��ѯ�ڵ���˴���Ϣ
	 *
	 * @param FilterWordsVo vo
	 * @return Map<String, String>
	 */
	@ResponseBody
	@RequestMapping("/updateFwVo") // ���½ڵ���˴���Ϣ
	public Object updateFwVo(FilterWordsVo vo, HttpSession session) {
		User u = (User) session.getAttribute(CommBean.SESSION_NAME);
		Map<String, String> map = new HashMap<>();
		List<String> ids = new ArrayList<>();
		ids.add(vo.getClassificationId());
		if (vo.getUpdatedatetime() != null) {
			if (!dbUpdateCheckService.DBUpdateCheck("4", ids, vo.getUpdatedatetime())) {
				map.put("msg", "checkFalse");
				return map;
			}
		}
		String value = this.fwService.updateFwVo(vo, u);
		map.put("msg", value);
		return map;
	}

	/**
	 * ��ѯ��ǰ�ڵ�������ӽڵ�
	 *
	 * @param FilterWordsVo vo
	 * @return List<FilterWordsVo>
	 */
	@ResponseBody
	@RequestMapping("/queryChild") // ��ѯ��ǰ�ڵ�������ӽڵ�
	public List<FilterWordsVo> queryChild(FilterWordsVo vo) {
		List<FilterWordsVo> children = this.fwService.queryChild(vo);
		return children;
	}

	/**
	 * ����
	 *
	 * @param                     String[] ids
	 * @param HttpServletResponse response
	 * @throws IOException
	 * @return null
	 */
	@RequestMapping("/output")
	public void output(String[] ids, HttpServletResponse response,HttpServletRequest request) throws IOException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");//�������ڸ�ʽ
		String thisTime = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
	    String realPath=request.getServletContext().getRealPath("/templates");
		HSSFWorkbook wb = this.fwService.output(ids,realPath);
		
		OutputStream output = response.getOutputStream();
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename=" + OutputUtil.toUtf8String("������˴�-"+thisTime+".xls"));
		response.setContentType("application/msexcel");
		wb.write(output);
		output.close();
	}

	/**
	 * ����
	 *
	 * @param FilterWordsVo vo
	 * @return List<FilterWordsVo>
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@ResponseBody
	@RequestMapping("/input")
	public Map<String, Object> input(@RequestParam MultipartFile file, HttpServletRequest request, HttpSession session)
			throws IllegalStateException, IOException {
		User user = (User) session.getAttribute(CommBean.SESSION_NAME);
		List<FilterWordsVo> vos = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		String msg = "ok";
		//�ϴ��ļ����ļ���Ϊԭ�ļ���+��ǰ�û�id
		String filePath = user.getUser_ID() + "-" + file.getOriginalFilename();
		if (!filePath.endsWith(CommBean.UPLOAD_FILE_EXTENSION_XLS)&&!filePath.endsWith(CommBean.UPLOAD_FILE_EXTENSION_XLSX)) {
			msg = "fileTypeException";
			map.put("msg", msg);
			return map;
		}
		String savePath = request.getSession().getServletContext().getRealPath("/") + "uploadExcel" + filePath;
		if (!file.isEmpty()) {
			// String savePath = "/uploadExcel";
			File targetFile = new File(savePath);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			file.transferTo(targetFile);
		}
		//
		try {
			//���ϴ����ļ�ת��Ϊlist
			vos = InputUtil.input(savePath);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "fault";
			map.put("msg", msg);
			return map;
		}
		File targetFile = new File(savePath);
		targetFile.delete();
		msg = this.fwService.input(vos);	
		map.put("msg", msg);
		return map;
	}

}
