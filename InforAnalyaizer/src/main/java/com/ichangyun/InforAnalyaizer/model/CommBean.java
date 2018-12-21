/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.model;

/**
 * 存放通用数据的实体类
 * @author renhao
 * Date:2018-11-9
 */
public class CommBean {

    public static final String COOKIE_NAME = "ichangyun_inforanalyaizer_user_name";

    public static final String COOKIE_PWD = "ichangyun_inforanalyaizer_user_passwd";

    public static final int COOKIE_TIME = 3600*24;

    public static final String SESSION_NAME = "ichangyun_user";

    public static final String LAST_CONTENT_SEARCH_TIME_ZHJC = "last_content_search_time_zhjc";  //综合监测上次搜索的时间

    public static final String LAST_CONTENT_SEARCH_TIME_ZTJC = "last_content_search_time_ztjc";  //专题监测上次搜索的时间

    public static final String LAST_CONTENT_SEARCH_TIME_YJZX = "last_content_search_time_yjzx";  //预警中心上次搜索的时间

    public static final int DB_BATCH_NUM = 10000; //批量处理数据时一次处理的数量

    public static final String YJFS_NAME = "人工预警" ;  //预警中心里关于人工预警的名词

    public static final String UPLOAD_FILE_EXTENSION_XLS = "xls";//导入导出文件后缀xls

    public static final String UPLOAD_FILE_EXTENSION_XLSX = "xlsx";//导入导出文件后缀xlsx

    public static final int UPLOAD_FILE_MAXLENGTH = 1000;	// 最大文件导入长度

    public static final int FILTER_WORD_MAXLENGTH = 600;    // 过滤词信息最大长度

    public static final int FILE_MAX_SIZE = 1024*1024;   //导入文件的最大size
    
    public static final String 	FILE_MAX_SIZE_MSG = "1M"; //导入文件的最大size的文本信息
    
    public static final int SIM_MAX_NUM = 20;   //取相似文章时的最大数目
    
}
