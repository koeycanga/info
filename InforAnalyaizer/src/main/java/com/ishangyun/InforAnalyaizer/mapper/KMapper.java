package com.ishangyun.InforAnalyaizer.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ishangyun.InforAnalyaizer.model.KBean;

public interface KMapper {

	public List<KBean> sayhello();

	public int getRowCount();

	public List<KBean> getAll(@Param("param") Map map);
}
