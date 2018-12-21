/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.service.notice;

import java.util.Map;

public interface NoticeResService {
	public Map<String, Object> queryAll(int pageNow, int rowSize);
}
