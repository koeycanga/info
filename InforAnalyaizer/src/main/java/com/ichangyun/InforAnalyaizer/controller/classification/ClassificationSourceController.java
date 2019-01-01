/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.controller.classification;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ichangyun.InforAnalyaizer.model.CommBean;
import com.ichangyun.InforAnalyaizer.model.classification.ClassificationInfoBean;
import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.model.webinfo.WebInfoBean;
import com.ichangyun.InforAnalyaizer.service.classification.ClassificationSourceService;

/**
 * 信息源绑定对应控制器
 * @author renhao
 * Date:2018-11-12
 */

@RestController
@RequestMapping("/qbgh/classificationSource")
public class ClassificationSourceController {

    //信息源绑定service
    @Autowired
    private ClassificationSourceService classificationSourceService;

    /**
     * 进入信息源绑定页面
     * @return
     * Date:2018-11-12
     */
    @RequestMapping("/index")
    @ResponseBody
    public Object index() {
        return new ModelAndView("classifcation/classificationInfoSource");
    }

    /**
     * 查询信息源绑定信息
     * @param cb 查询参数
     * @return 要查询的信息源绑定信息
     * Date:2018-11-12
     */
    @RequestMapping("/search")
    public Object search(ClassificationInfoBean cb,HttpSession session) {

    	User u = (User) session.getAttribute(CommBean.SESSION_NAME);
    	cb.setCustomer_ID(u.getCustomer_ID());
    	cb.setCollectionField_ID(u.getCollectionField_ID());
    	
        int rowCount = classificationSourceService.getClassifcInfoCount(cb);

        String json_res = classificationSourceService.getClassifcInfo(cb);

        String res = "{\"rowCount\":\""+rowCount+"\",\"resdata\":"+json_res+"}";

        return res;
    }

    /**
     * 获得分类 体系的子节点
     * @param cb 查询参数
     * @return  分类 体系的子节点的JSON字面量
     * Date:2018-11-12
     */
    @RequestMapping("/getchild")
    public Object getchild(ClassificationInfoBean cb) {

        String json_res = classificationSourceService.getchild(cb);

        return json_res;
    }

    /**
     * 查询网站信息
     * @param wb 查询参数
     * @return  要查询的网站信息的JSON字面量
     * Date:2018-11-12
     */
    @RequestMapping("/searchweb")
    public Object searchweb(WebInfoBean wb,HttpSession session) {

    	User u = (User) session.getAttribute(CommBean.SESSION_NAME);

    	wb.setCollectionField_ID(u.getCollectionField_ID());
    	
        int rowCount = classificationSourceService.getWebInfoCount(wb);

        String json_res = classificationSourceService.getWebInfo(wb);

        String res = "{\"rowCount\":\""+rowCount+"\",\"resdata\":"+json_res+"}";

        return res ;
    }

    /**
     * 查询已绑定的网站信息
     * @param wb  查询参数
     * @return  要查询的网站信息的JSON字面量
     * Date:2018-11-12
     */
    @RequestMapping("/searchalweb")
    public Object searchalweb(WebInfoBean wb) {

        int rowCount = classificationSourceService.getAlWebInfoCount(wb);

        String json_res = classificationSourceService.getAlWebInfo(wb);

        String res = "{\"rowCount\":\""+rowCount+"\",\"resdata\":"+json_res+"}";

        return res;
    }

    /**
     * 将网站信息绑定到信息源上
     * @param map  要绑定的网站信息及信息源参数
     * @param session
     * Date:2018-11-12
     */
    @RequestMapping("/joingright")
    public void joingright(@RequestBody Map map,HttpSession session) {

        String Classification_ID = (String) map.get("Classification_ID");

        String json = (String) map.get("json");

        String creater = ((User)session.getAttribute(CommBean.SESSION_NAME)).getUser_ID() ;

        classificationSourceService.addNewSource(Classification_ID,json,creater);

    }

    /**
     * 删除信息源已绑定的网站信息
     * @param map 要删除的网站信息及信息源参数
     * Date:2018-11-12
     */
    @RequestMapping("/joingleft")
    public void joingleft(@RequestBody Map map) {
        String Classification_ID = (String) map.get("Classification_ID");

        String json = (String) map.get("json");

        classificationSourceService.delteSource(Classification_ID,json);
    }

    /**
     * 修改信息源信息
     * @param Classification_ID
     * @param session
     * Date:2018-11-12
     */
    @RequestMapping("/updatesource")
    public void updatesource(String Classification_ID,HttpSession session) {

        String updater = ((User)session.getAttribute(CommBean.SESSION_NAME)).getUser_ID() ;

        classificationSourceService.updatesource(Classification_ID,updater);

    }

}
