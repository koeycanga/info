/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.controller.front;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ichangyun.InforAnalyaizer.model.CommBean;
import com.ichangyun.InforAnalyaizer.model.front.HotWordBean;
import com.ichangyun.InforAnalyaizer.model.thematicmonitoring.ArticleInfoBean;
import com.ichangyun.InforAnalyaizer.model.userInfo.User;
import com.ichangyun.InforAnalyaizer.service.front.HomeService;

/**
 * 首页的控制器
 * @author renhao
 * Date:2018-11-12
 */
@RestController
@RequestMapping("/front")
public class HomeController {

    @Autowired
    private HomeService homeService;

    /**
     * 进入首页
     * @return
     */
    @RequestMapping("/index")
    @ResponseBody
    public Object index() {

        return new ModelAndView("frontpage/home");
    }

    /**
     * 进入到热词云详情页面
     * @return
     */
    @RequestMapping("/tohotword")
    public Object tohotword(String wordid,String flag,HttpServletRequest request) {

        String title = "";

        if(flag.equals("0")) {
            title = "热词云";
        }

        if(flag.equals("1")) {
            title = "即将发生";
        }

        request.setAttribute("flag", flag);

        request.setAttribute("table_title",title);

        request.setAttribute("word",wordid);

        return new ModelAndView("frontpage/worddetail");
    }

    /**
     * 获得首页最新消息  预警信息  负面信息的JSON字面量
     * @return
     */
    @RequestMapping("/getHomeDatas")
    public Object getHomeDatas(HttpSession session) {

        String[] datas = homeService.getTopTenDatas(session);

        String jcmsg = homeService.getJCMsg(session);

        String res = "{\"newest_datas\":"+datas[0]+",\"warn_datas\":"+datas[1]+",\"negative_datas\":"+datas[2]+","+jcmsg+"}";

        return res;
    }

    /**
     * 获得热词云信息
     * @return
     */
    @RequestMapping("/getHotWord")
    public Object getHotWord(HttpSession session) {

        String json_hotword = homeService.getHotWord(session);

        return json_hotword;
    }

    @RequestMapping("/getHotWordFromDetial")
    public Object getHotWordFromDetial(String flag,HttpSession session) {

        String json_hotword = homeService.getHotWordFromDetial(flag,session);

        return json_hotword;
    }

    @RequestMapping("/getJJFSWord")
    public Object getJJFSWord(HttpSession session) {

        String json_jjfsword = homeService. getJJFSWord(session);

        return json_jjfsword;
    }

    /**
     * 根据热词获得属于该热词的文章信息集合
     * @param hb
     * @return
     */
    @RequestMapping("/searchbyhotword")
    public Object searchByHotWord(HotWordBean hb,HttpSession session) {

        User u = (User) session.getAttribute(CommBean.SESSION_NAME);

        String Userid = u.getUser_ID();
        String CollectionField_ID = u.getCollectionField_ID();
        hb.setCreateUser(Userid);
        hb.setCollectionField_ID(CollectionField_ID);

        int rowCount = homeService.getArticleCountByHotWord(hb);

        String json_res = homeService.getArticleByHotWord(hb);

        String res = "{\"rowCount\":\""+rowCount+"\",\"resdata\":"+json_res+"}";

        return res;

    }

    @RequestMapping("/getSimContent")
    public Object getSimContent(ArticleInfoBean ab) {
        String json = homeService.getSimContent(ab);

        return json;
    }

    /**
     * 获得登录用户信息
     * @param session
     * @return
     */
    @RequestMapping("/getUser")
    public Object getUser(HttpSession session) {

        User user = (User)session.getAttribute(CommBean.SESSION_NAME);

        return user.getName();
    }

    /**
     * 用户退出系统
     * @param session
     */
    @RequestMapping("/logout")
    public void logout(HttpSession session) {

        session.setAttribute(CommBean.SESSION_NAME,null);

    }
}
