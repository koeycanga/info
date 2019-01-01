/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.model;

/**
 * 实体类基类
 * @author renhao
 * Date:2018-11-9
 */
public class BaseBean {

    protected int l_pre;    //分页查询时 limit左边的参数
    protected int pageNow;  //当前多少页
    protected int rowSize;  //每页显示条目数

    public int getPageNow() {
        return pageNow;
    }
    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getL_pre() {
        return l_pre;
    }
    public void setL_pre(int l_pre) {
        this.l_pre = l_pre;
    }
    public int getRowSize() {
        return rowSize;
    }
    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

}
