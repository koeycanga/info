/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.model.notice;

public class NoticeVo extends Notice{
    private String typename;//简报任务名
    //private String plan;//简报执行计划
    //private String generationtime;//生成时间
    //根据简报类型判断生成时间——每X,XX点XX分生成
    public String getGenerationtime() {
        if(this.getNoticetype().equals("1")) {
            String p = "每天"+this.getMonitortimeend()+"生成";//日报
            return p;
        }else if(this.getNoticetype().equals("2")) {//周报
            int d = Integer.parseInt(this.getMonitordateend());
            String p = "每周"+NoticeVo.getWeek(d)+this.getMonitortimeend()+"生成";
            return p;
        }else if(this.getNoticetype().equals("3")) {//月报
            int d = Integer.parseInt(this.getMonitordateend());
            String p = "每月"+NoticeVo.getMonth(d)+this.getMonitortimeend()+"生成";
            return p;
        }
        return "";
    }

    //根据简报类型组成发送计划——每X,XX点XX分发送
    public String getPlan() {
        if(this.getNoticetype().equals("1")) {
            String p = "每天"+this.getSendtime()+"发送";//日报
            return p;
        }else if(this.getNoticetype().equals("2")) {//周报
            int d = Integer.parseInt(this.getSenddate());
            String p = "每周"+NoticeVo.getWeek(d)+this.getSendtime()+"发送";
            return p;
        }else if(this.getNoticetype().equals("3")) {//月报
            int d = Integer.parseInt(this.getSenddate());
            String p = "每月"+NoticeVo.getMonth(d)+this.getSendtime()+"发送";
            return p;
        }
        return "";
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public NoticeVo(String typename) {
        super();
        this.typename = typename;
    }

    public NoticeVo() {
        super();
    }
    static String getWeek(int d) {
        return "周"+"一二三四五六日".charAt(d-1);
    }
    static String getMonth(int d) {
        String[] month = {"一","二","三","四","五","六","七","八","九","十",
                "十一","十二","十三","十四","十五","十六","十七","十八","十九","二十",
                "二十一","二十二","二十三","二十四","二十五","二十六","二十七","二十八","二十九","三十","三十一"};
        return month[d-1]+"号";
    }
}
