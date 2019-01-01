/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
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
import com.ichangyun.InforAnalyaizer.model.classification.ClassificationInfoBean;
import com.ichangyun.InforAnalyaizer.model.classificationfilterwords.FilterWordsVo;
import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.service.classificationfilterwords.FilterWordsService;
import com.ichangyun.InforAnalyaizer.service.common.service.DBUpdateCheckService;
import com.ichangyun.InforAnalyaizer.utils.filwterwordsUtils.InputUtil;

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
    @RequestMapping("/queryAll") // 查询所有节点过滤词信息，可带条件
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
        if (vo.getUpdatedatetime() != null) {
            if (!dbUpdateCheckService.DBUpdateCheck("4", ids, vo.getUpdatedatetime(),session)) {
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

    /**
     * 导出
     *
     * @param  String[] ids  要导出的分类体系id集合
     * @param HttpServletResponse response
     * @throws IOException
     */
    @RequestMapping("/output")
    public String output(String[] ids, HttpServletResponse response,HttpServletRequest request) throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");//设置日期格式
        String thisTime = df.format(new Date());// new Date()为获取当前系统时间
        String realPath=request.getServletContext().getRealPath("/templates");   //excel模板文件存放目录
        Object[] obj = this.fwService.output(ids,realPath);     //生成HSSFWorkbook文件
        HSSFWorkbook wb = (HSSFWorkbook) obj[0];
        String msg = (String) obj[1];
        if(msg.equals("")) {
	        OutputStream output = response.getOutputStream();
	        response.reset();
	        response.setHeader("Content-disposition", "attachment; filename=" +
	                toUtf8String("分类过滤词-"+thisTime+".xls"));
	        response.setContentType("application/msexcel");
	        wb.write(output);
	        output.close();
        }else {
        	request.setAttribute("dc_msg", msg);
        }
        return "classifcation/filterWords";
    }

    /**
     * 文字格式转换
     * @param s
     * @return
     */
    private String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    /**
     * 导入
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
        //上传文件，文件名为原文件名+当前用户id
        String filePath = user.getUser_ID() + "-" + file.getOriginalFilename();

        // 文件类型Check
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
            if(targetFile.length()>CommBean.FILE_MAX_SIZE) {
                targetFile.delete();
                msg = "maxSize"+CommBean.FILE_MAX_SIZE_MSG;
                map.put("msg", msg);
                return map;
            }
        }

        // 最大文件导入长度Check
        int rowCount = InputUtil.getRowCount(savePath);
        if(rowCount > CommBean.UPLOAD_FILE_MAXLENGTH) {
            File targetFile = new File(savePath);
            targetFile.delete();
            msg = "maxLength"+CommBean.UPLOAD_FILE_MAXLENGTH;
            map.put("msg", msg);
            return map;
        }
        try {
            //将上传的文件转换为list
            List<ClassificationInfoBean> allClassificationNames = this.fwService.getAllClassificationNames();
            Map<String, Object>result = InputUtil.input(savePath,allClassificationNames);
            vos=(List<FilterWordsVo>) result.get("vos");
            map.put("imputCount",vos.size());
            map.put("eMsg", result.get("eMsg"));
            map.put("eCheck", (boolean)result.get("eCheck"));
        } catch (Exception e) {
            e.printStackTrace();
            File targetFile = new File(savePath);
            targetFile.delete();
            msg = "fault";
            map.put("msg", msg);
            return map;
        }
        File targetFile = new File(savePath);
        targetFile.delete();
        msg = this.fwService.input(vos,user.getUser_ID());
        map.put("msg", msg);
        return map;
    }

}
