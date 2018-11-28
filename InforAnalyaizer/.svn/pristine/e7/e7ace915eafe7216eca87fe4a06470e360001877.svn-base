<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="ss" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources" var="sysInfo" />
<fmt:message key="front_menu" var="front_menu" bundle="${sysInfo}" /> 
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>竞争情报分析系统</title>
<link rel="stylesheet" type="text/css" href="../css/cy_CIAS_style.css">
</head>

<body style="background-color: #f2f3f8;">
<div id="app">
<!--复制成功提示-->
<div class="cy_CIASFE_copytip">文字链接已复制</div>

<!--头部-->
<div class="cy_CIASFE_top">
	<div class="cy_CIASFE_logo"><img src="../image/fontend-logo.png"></div>
	<div class="cy_CIASFE_tit">国药器械-竞争情报分析系统</div>
	<ic_top_menu ></ic_top_menu> <!-- 上部菜单栏 -->
	<ic_user_info></ic_user_info> <!-- 登录用户信息框 -->
</div>

<!--二级导航-->
<div class="cy_CIASFE_2ndmenu">
	<div class="cy_CIASFE_2ndmenutabchk">综合监测</div>
	<div class="cy_CIASFE_2ndmenutab">公司舆情<img src="../image/fontend-newtip.png"></div>
	<div class="cy_CIASFE_2ndmenutab">高管舆情<img src="../image/fontend-newtip.png"></div>
	<div class="cy_CIASFE_2ndmenutab">市场动向<img src="../image/fontend-newtip.png"></div>
	<div class="cy_CIASFE_2ndmenutab">国家政策<img src="../image/fontend-newtip.png"></div>
	<div class="cy_CIASFE_2ndmenutab">竞争对手<img src="../image/fontend-newtip.png"></div>
	<div class="cy_CIASFE_2ndmenutab">产品技术<img src="../image/fontend-newtip.png"></div>
	<div class="cy_CIASFE_2ndmenutab">上下游企业<img src="../image/fontend-newtip.png"></div>
	<div class="cy_CIASFE_2ndmenutab" style="border: solid 1px #e0e0e0">国家政策<img src="../image/fontend-newtip.png"></div>
</div>

<!--左侧数据-->
<div class="cy_CIAFE_main">
	<div class="cy_CIASFE_intmonbody">
	<div class="cy_CIASFE_intmonbodyleft">
		<table cellspacing="0">
			<tbody>
    <tr>
      <th width="30%">名称</th>
      <th width="30%">今天总数</th>
      <th width="40%">昨天总数</th>
    </tr>
    <tr>
      <td>监测总数</td>
      <td class="cy_CIASFE_intmonjr">8063</td>
      <td class="cy_CIASFE_intmonzr">8063</td>
    </tr>
    <tr>
      <td>公司舆情</td>
      <td class="cy_CIASFE_intmonjr">0</td>
      <td class="cy_CIASFE_intmonzr">103</td>
    </tr>
    <tr>
      <td>高管舆情</td>
      <td class="cy_CIASFE_intmonjr">203</td>
      <td class="cy_CIASFE_intmonzr">203</td>
    </tr>
    <tr>
      <td>市场动向</td>
      <td class="cy_CIASFE_intmonjr">303</td>
      <td class="cy_CIASFE_intmonzr">303</td>
    </tr>
    <tr>
      <td>国家政策</td>
      <td class="cy_CIASFE_intmonjr">103</td>
      <td class="cy_CIASFE_intmonzr">103</td>
    </tr>
    <tr>
      <td>竞争对手</td>
      <td class="cy_CIASFE_intmonjr">203</td>
      <td class="cy_CIASFE_intmonzr">203</td>
    </tr>
    <tr>
      <td>产品技术</td>
      <td class="cy_CIASFE_intmonjr">303</td>
      <td class="cy_CIASFE_intmonzr">303</td>
    </tr>
    <tr>
      <td>上下游企业</td>
      <td class="cy_CIASFE_intmonjr">103</td>
      <td class="cy_CIASFE_intmonzr">103</td>
    </tr>
    <tr>
      <td>国家政策</td>
      <td class="cy_CIASFE_intmonjr">203</td>
      <td class="cy_CIASFE_intmonzr">203</td>
    </tr>
			</tbody>
		</table>
	</div>	
	<div class="cy_CIASFE_intmonbodyright">
	
<!--条件筛选-->
		<div class="cy_CIASFE_condition">
		  <div class="cy_CIASFE_search">
				<select><option>全文匹配</option></select>
				<div class="cy_CMICBMS_schbox01"><input type="text" placeholder="请输入…" style="cursor: text"></div>
				<input type="button" class="cy_CMICBMS_schbtn" value="检索">
			</div>
			<div class="cy_CIASFE_conbox01">监测时间：<input type="radio" name="montime" value="单选" id="montime_0" style="display: none;" checked><label for="montime_0">今天</label>
				<input type="radio" name="montime" value="单选" id="montime_1" style="display: none;"><label for="montime_1">24小时</label>
           		<input type="radio" name="montime" value="单选" id="montime_2" style="display: none;"><label for="montime_2">2天</label>
           		<input type="radio" name="montime" value="单选" id="montime_3" style="display: none;"><label for="montime_3">3天</label>
           		<input type="radio" name="montime" value="单选" id="montime_4" style="display: none;"><label for="montime_4">7天</label>
           		<input type="radio" name="montime" value="单选" id="montime_5" style="display: none;"><label for="montime_5">10天</label>
           		<input type="radio" name="montime" value="单选" id="montime_6" style="display: none;"><label for="montime_6">自定义</label><input type="date" class="cy_CIASFE_timetb">—<input type="date" class="cy_CIASFE_timetb"><input type="button" class="cy_CMICBMS_schbtn" value="确认">
            </div>
		  <div class="cy_CIASFE_conbox02">
				<div class="cy_CIASFE_conbox03">
				情感分析：
					<input type="radio" name="emoana" value="单选" id="emoana_0" style="display: none;" checked><label for="emoana_0">全部</label>
					<input type="radio" name="emoana" value="单选" id="emoana_1" style="display: none;"><label for="emoana_1">正面</label>
					<input type="radio" name="emoana" value="单选" id="emoana_2" style="display: none;"><label for="emoana_2">中性</label>
					<input type="radio" name="emoana" value="单选" id="emoana_3" style="display: none;"><label for="emoana_3">负面</label>
				</div>
				<div class="cy_CIASFE_conbox03">相似文章：
					<input type="radio" name="simart" value="单选" id="simart_0" style="display: none;" checked><label for="simart_0">合并</label>
					<input type="radio" name="simart" value="单选" id="simart_1" style="display: none;"><label for="simart_1">不合并</label>
				</div>
			</div>
		  <div class="cy_CIASFE_conbox02">
				<div class="cy_CIASFE_conbox03">排序方式：
					<input type="radio" name="sort" value="单选" id="sort_0" style="display: none;" checked><label for="sort_0">时间降序</label>
					<input type="radio" name="sort" value="单选" id="sort_1" style="display: none;"><label for="sort_1">时间升序</label>
					<input type="radio" name="sort" value="单选" id="sort_2" style="display: none;"><label for="sort_2">权重降序</label>
					<input type="radio" name="sort" value="单选" id="sort_3" style="display: none;"><label for="sort_3">热度排序</label>
				</div>
				<div class="cy_CIASFE_conbox03">信息来源：
					<input type="radio" name="infsour" value="单选" id="infsour_0" style="display: none;" checked><label for="infsour_0">全部</label>
					<input type="radio" name="infsour" value="单选" id="infsour_1" style="display: none;"><label for="infsour_1">新闻</label>
					<input type="radio" name="infsour" value="单选" id="infsour_2" style="display: none;"><label for="infsour_2">微博</label>
					<input type="radio" name="infsour" value="单选" id="infsour_3" style="display: none;"><label for="infsour_3">微信</label>
					<input type="radio" name="infsour" value="单选" id="infsour_4" style="display: none;"><label for="infsour_4">论坛</label>
					<input type="radio" name="infsour" value="单选" id="infsour_5" style="display: none;"><label for="infsour_5">博客</label>
				</div>
			</div>
	  </div>
	  
<!--	内容-->
	<!--	空数据-->
		<div class="cy_CIASFE_ctnodata">
			<img src="../image/fontend-nodata.png">
		</div>
	<!--	详细-->
		<div class="cy_CIASFE_content">
			<div class="cy_CIASFE_contentbox01">
				<label><input type="checkbox" class="cy_CIASFE_contentall">全选</label>
				
				<input type="button" value="删除" class="cy_CIASFE_contentbtn01">
				<input type="button" value="预警" class="cy_CIASFE_contentbtn01">
			</div>
		</div>
		<div class="cy_CIASFE_prompt">
			您有<span>177</span>条新信息，点击查看
		</div>
		<div class="cy_CIASFE_contmain">
		<div class="cy_CIASFE_contpaste" style="border-top: 0px;">
			<div class="cy_CIASFE_contpastetit">
				<label><input type="checkbox">
				<b>进店想退世界杯套餐？华帝员工：老板跑了在讨薪</b></label>
				<div class="cy_CIASFE_contpassta01">负</div>
				<div class="cy_CIASFE_contpassta01">已预警</div>
				<div class="cy_CIASFE_contpascol">
					<div class="cy_CIASFE_colle">
						<div class="cy_CIASFE_collebox">
							<div>我的收藏</div>
							<a href="">产品</a>
							<a href="">开发</a>
							<a href="">测试</a>
						</div>
					</div>
					<div class="cy_CIASFE_share"></div>
					<div class="cy_CIASFE_dele"></div>
				</div>
				
			</div>
			<div class="cy_CIASFE_contpastecon">
				京津代理商不明原因失联，并申请法院查封冻结其库存商品，北京多家电器商场销售员证实已“停摆”20多天，顾客付款后没货，退款也无门，200多名华帝员工被欠薪。
			</div>
			<div class="cy_CIASFE_contpastfoot"><div class="cy_CIASFE_simart">相似文章：10条
				<div class="cy_CIASFE_simartbox">
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a>
				</div></div>
			<a href="">2018-09-27 18:09 中财网</a>
			</div>
		</div>
		<div class="cy_CIASFE_contpaste">
			<div class="cy_CIASFE_contpastetit">
				<label><input type="checkbox">
				<b>进店想退世界杯套餐？华帝员工：老板跑了在讨薪</b></label>
				<div class="cy_CIASFE_contpassta02">中</div>
				<div class="cy_CIASFE_contpascol">
					<div class="cy_CIASFE_colle">
						<div class="cy_CIASFE_collebox">
							<div>我的收藏</div>
							<a href="">产品</a>
							<a href="">开发</a>
							<a href="">测试</a>
						</div>
					</div>
					<div class="cy_CIASFE_share"></div>
					<div class="cy_CIASFE_dele"></div>
				</div>
			</div>
			<div class="cy_CIASFE_contpastecon">
				京津代理商不明原因失联，并申请法院查封冻结其库存商品，北京多家电器商场销售员证实已“停摆”20多天，顾客付款后没货，退款也无门，200多名华帝员工被欠薪。
			</div>
			<div class="cy_CIASFE_contpastfoot"><div class="cy_CIASFE_simart">相似文章：10条
				<div class="cy_CIASFE_simartbox">
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a>
				</div></div>
			<a href="">2018-09-27 18:09 中财网</a>
			</div>
		</div>
		<div class="cy_CIASFE_contpaste">
			<div class="cy_CIASFE_contpastetit">
				<label><input type="checkbox">
				<b>进店想退世界杯套餐？华帝员工：老板跑了在讨薪</b></label>
				<div class="cy_CIASFE_contpassta03">正</div>
				<div class="cy_CIASFE_contpascol">
					<div class="cy_CIASFE_colle">
						<div class="cy_CIASFE_collebox">
							<div>我的收藏</div>
							<a href="">产品</a>
							<a href="">开发</a>
							<a href="">测试</a>
						</div>
					</div>
					<div class="cy_CIASFE_share"></div>
					<div class="cy_CIASFE_dele"></div>
				</div>
			</div>
			<div class="cy_CIASFE_contpastecon">
				京津代理商不明原因失联，并申请法院查封冻结其库存商品，北京多家电器商场销售员证实已“停摆”20多天，顾客付款后没货，退款也无门，200多名华帝员工被欠薪。
			</div>
			<div class="cy_CIASFE_contpastfoot"><div class="cy_CIASFE_simart">相似文章：10条
				<div class="cy_CIASFE_simartbox">
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a>
				</div></div>
			<a href="">2018-09-27 18:09 中财网</a>
			</div>
		</div>
		<div class="cy_CIASFE_contpaste">
			<div class="cy_CIASFE_contpastetit">
				<label><input type="checkbox">
				<b>进店想退世界杯套餐？华帝员工：老板跑了在讨薪</b></label>
				<div class="cy_CIASFE_contpassta01">负</div>
				<div class="cy_CIASFE_contpassta01">已预警</div>
				<div class="cy_CIASFE_contpascol">
					<div class="cy_CIASFE_colle">
						<div class="cy_CIASFE_collebox">
							<div>我的收藏</div>
							<a href="">产品</a>
							<a href="">开发</a>
							<a href="">测试</a>
						</div>
					</div>
					<div class="cy_CIASFE_share"></div>
					<div class="cy_CIASFE_dele"></div>
				</div>
			</div>
			<div class="cy_CIASFE_contpastecon">
				京津代理商不明原因失联，并申请法院查封冻结其库存商品，北京多家电器商场销售员证实已“停摆”20多天，顾客付款后没货，退款也无门，200多名华帝员工被欠薪。
			</div>
			<div class="cy_CIASFE_contpastfoot"><div class="cy_CIASFE_simart">相似文章：10条
				<div class="cy_CIASFE_simartbox">
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a>
				</div></div>
			<a href="">2018-09-27 18:09 中财网</a>
			</div>
		</div>
		<div class="cy_CIASFE_contpaste">
			<div class="cy_CIASFE_contpastetit">
				<label><input type="checkbox">
				<b>进店想退世界杯套餐？华帝员工：老板跑了在讨薪</b></label>
				<div class="cy_CIASFE_contpassta02">中</div>
				<div class="cy_CIASFE_contpascol">
					<div class="cy_CIASFE_colle">
						<div class="cy_CIASFE_collebox">
							<div>我的收藏</div>
							<a href="">产品</a>
							<a href="">开发</a>
							<a href="">测试</a>
						</div>
					</div>
					<div class="cy_CIASFE_share"></div>
					<div class="cy_CIASFE_dele"></div>
				</div>
			</div>
			<div class="cy_CIASFE_contpastecon">
				京津代理商不明原因失联，并申请法院查封冻结其库存商品，北京多家电器商场销售员证实已“停摆”20多天，顾客付款后没货，退款也无门，200多名华帝员工被欠薪。
			</div>
			<div class="cy_CIASFE_contpastfoot"><div class="cy_CIASFE_simart">相似文章：10条
				<div class="cy_CIASFE_simartbox">
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a>
				</div></div>
			<a href="">2018-09-27 18:09 中财网</a>
			</div>
		</div>
		<div class="cy_CIASFE_contpaste">
			<div class="cy_CIASFE_contpastetit">
				<label><input type="checkbox">
				<b>进店想退世界杯套餐？华帝员工：老板跑了在讨薪</b></label>
				<div class="cy_CIASFE_contpassta01">负</div>
				<div class="cy_CIASFE_contpassta01">已预警</div>
				<div class="cy_CIASFE_contpascol">
					<div class="cy_CIASFE_colle">
						<div class="cy_CIASFE_collebox">
							<div>我的收藏</div>
							<a href="">产品</a>
							<a href="">开发</a>
							<a href="">测试</a>
						</div>
					</div>
					<div class="cy_CIASFE_share"></div>
					<div class="cy_CIASFE_dele"></div>
				</div>
			</div>
			<div class="cy_CIASFE_contpastecon">
				京津代理商不明原因失联，并申请法院查封冻结其库存商品，北京多家电器商场销售员证实已“停摆”20多天，顾客付款后没货，退款也无门，200多名华帝员工被欠薪。
			</div>
			<div class="cy_CIASFE_contpastfoot"><div class="cy_CIASFE_simart">相似文章：10条
				<div class="cy_CIASFE_simartbox">
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a>
				</div></div>
			<a href="">2018-09-27 18:09 中财网</a>
			</div>
		</div>
		<div class="cy_CIASFE_contpaste">
			<div class="cy_CIASFE_contpastetit">
				<label><input type="checkbox">
				<b>进店想退世界杯套餐？华帝员工：老板跑了在讨薪</b></label>
				<div class="cy_CIASFE_contpassta03">正</div>
				<div class="cy_CIASFE_contpascol">
					<div class="cy_CIASFE_colle">
						<div class="cy_CIASFE_collebox">
							<div>我的收藏</div>
							<a href="">产品</a>
							<a href="">开发</a>
							<a href="">测试</a>
						</div>
					</div>
					<div class="cy_CIASFE_share"></div>
					<div class="cy_CIASFE_dele"></div>
				</div>
			</div>
			<div class="cy_CIASFE_contpastecon">
				京津代理商不明原因失联，并申请法院查封冻结其库存商品，北京多家电器商场销售员证实已“停摆”20多天，顾客付款后没货，退款也无门，200多名华帝员工被欠薪。
			</div>
			<div class="cy_CIASFE_contpastfoot"><div class="cy_CIASFE_simart">相似文章：10条
				<div class="cy_CIASFE_simartbox">
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a>
				</div></div>
			<a href="">2018-09-27 18:09 中财网</a>
			</div>
		</div>
		<div class="cy_CIASFE_contpaste">
			<div class="cy_CIASFE_contpastetit">
				<label><input type="checkbox">
				<b>进店想退世界杯套餐？华帝员工：老板跑了在讨薪</b></label>
				<div class="cy_CIASFE_contpassta01">负</div>
				<div class="cy_CIASFE_contpassta01">已预警</div>
				<div class="cy_CIASFE_contpascol">
					<div class="cy_CIASFE_colle">
						<div class="cy_CIASFE_collebox">
							<div>我的收藏</div>
							<a href="">产品</a>
							<a href="">开发</a>
							<a href="">测试</a>
						</div>
					</div>
					<div class="cy_CIASFE_share"></div>
					<div class="cy_CIASFE_dele"></div>
				</div>
			</div>
			<div class="cy_CIASFE_contpastecon">
				京津代理商不明原因失联，并申请法院查封冻结其库存商品，北京多家电器商场销售员证实已“停摆”20多天，顾客付款后没货，退款也无门，200多名华帝员工被欠薪。
			</div>
			<div class="cy_CIASFE_contpastfoot"><div class="cy_CIASFE_simart">相似文章：10条
				<div class="cy_CIASFE_simartbox">
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a>
				</div></div>
			<a href="">2018-09-27 18:09 中财网</a>
			</div>
		</div>
		<div class="cy_CIASFE_contpaste">
			<div class="cy_CIASFE_contpastetit">
				<label><input type="checkbox">
				<b>进店想退世界杯套餐？华帝员工：老板跑了在讨薪</b></label>
				<div class="cy_CIASFE_contpassta02">中</div>
				<div class="cy_CIASFE_contpascol">
					<div class="cy_CIASFE_colle">
						<div class="cy_CIASFE_collebox">
							<div>我的收藏</div>
							<a href="">产品</a>
							<a href="">开发</a>
							<a href="">测试</a>
						</div>
					</div>
					<div class="cy_CIASFE_share"></div>
					<div class="cy_CIASFE_dele"></div>
				</div>
			</div>
			<div class="cy_CIASFE_contpastecon">
				京津代理商不明原因失联，并申请法院查封冻结其库存商品，北京多家电器商场销售员证实已“停摆”20多天，顾客付款后没货，退款也无门，200多名华帝员工被欠薪。
			</div>
			<div class="cy_CIASFE_contpastfoot"><div class="cy_CIASFE_simart">相似文章：10条
				<div class="cy_CIASFE_simartbox">
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a>
				</div></div>
			<a href="">2018-09-27 18:09 中财网</a>
			</div>
		</div>
		<div class="cy_CIASFE_contpaste">
			<div class="cy_CIASFE_contpastetit">
				<label><input type="checkbox">
				<b>进店想退世界杯套餐？华帝员工：老板跑了在讨薪</b></label>
				<div class="cy_CIASFE_contpassta03">正</div>
				<div class="cy_CIASFE_contpascol">
					<div class="cy_CIASFE_colle">
						<div class="cy_CIASFE_collebox">
							<div>我的收藏</div>
							<a href="">产品</a>
							<a href="">开发</a>
							<a href="">测试</a>
						</div>
					</div>
					<div class="cy_CIASFE_share"></div>
					<div class="cy_CIASFE_dele"></div>
				</div>
			</div>
			<div class="cy_CIASFE_contpastecon">
				京津代理商不明原因失联，并申请法院查封冻结其库存商品，北京多家电器商场销售员证实已“停摆”20多天，顾客付款后没货，退款也无门，200多名华帝员工被欠薪。
			</div>
			<div class="cy_CIASFE_contpastfoot"><div class="cy_CIASFE_simart">相似文章：10条
				<div class="cy_CIASFE_simartbox">
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a><br>
					<a href="">进店想退世界杯套餐？华帝员工：老板跑了在讨薪</a>
				</div></div>
			<a href="">2018-09-27 18:09 中财网</a>
			</div>
		</div>
		<div ></div>
<!--		分页-->
		<div class="cy_CIASFE_ctpage">
			
			<div class="cy_CIASFE_ctpg"><div class="cy_CIASFE_ctpg01">共100条</div>
				<div class="cy_CIASFE_ctpg02"><ul><li class="cy_CIASFE_ctpgsp">上一页</li><li class="cy_CIASFE_ctpgfc"><a >1</a></li><li><a href="">2</a></li><li><a href="">3</a></li><li><a href="">4</a></li><li><a href="">5</a></li><li><a href="">6</a></li><li class="cy_CIASFE_ctpgsp">…</li><li><a href="">100</a></li><li style="border-right: 1px solid #e0e0e0"><a href="">下一页</a></li></ul></div>
				<div class="cy_CIASFE_ctpg03">到第<input type="text" class="cy_CIASFE_pgbox">页<input type="button" value="确定" class="cy_CIASFE_pgbtn"></div>
			</div>
		</div>
		

		</div>

	</div>
</div>	

</div>
<div class="cy_CIASFE_footer02"><a href="">使用手册</a>&nbsp;&nbsp;&nbsp;&nbsp;联系我们（电话：1648726161  邮箱：sales@ichangyun.com）    Copyright&copy;2018-2021 &nbsp;&nbsp;&nbsp;&nbsp;湖北畅云时讯软件技术有限公司版权所有</div>
</div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${ctx}/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/js/vue.min.js"></script>
<script type="text/javascript" src="${ctx}/js/axios.min.js"></script>
<script type="text/javascript" src="${ctx}/js/polyfill.min.js"></script>
<script type="text/javascript" src="${ctx}/js/ic_components.js"></script>
<script type="text/javascript" src="${ctx}/js/comm.js"></script>
<script>

var menu_datas = JSON.parse('${front_menu}') //菜单数据来源于 classes/resources.properties
Vue.component('ic_top_menu',ic_top_menu);   // ic_top_menu 引自 js/ic_component.js

Vue.component('ic_user_info',ic_user_info);  // ic_user_info 引自 js/ic_component.js


var app = new Vue({
     el:"#app"	
 });
</script>
</html>
