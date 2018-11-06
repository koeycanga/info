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
<link rel="stylesheet" type="text/css" href="${ctx}/css/cy_CIAS_style.css">
</head>

<body style="background-color: #15266b;">
<div id="app" v-cloak>
<div class="cy_CIASFE_top">
	<div class="cy_CIASFE_logo"><img src="../image/fontend-logo.png"></div>
	<div class="cy_CIASFE_tit">国药器械-竞争情报分析系统</div>
    <top_menu></top_menu> <!-- 上部菜单栏 -->
	<ic_user_info></ic_user_info> <!-- 登录用户信息框 -->
</div>
<div class="cy_CIASFE_body">
<!--数据统计-->
	<div class="cy_CIASFE_homebox01">
		<div class="cy_CIASFE_homeboxtit">数据统计</div>
		<div class="cy_CIASFE_databox01">今日监测总数<div class="cy_CIASFE_databox02">{{t_monitor_data.total}}</div></div>
		<div class="cy_CIASFE_databox03">
			<div class="cy_CIASFE_databox04"><span>{{t_monitor_data.negative}}</span><br>负面信息</div>
			<div class="cy_CIASFE_databox04"><span>{{t_monitor_data.special}}</span><br>专题监测</div>
			<div class="cy_CIASFE_databox05"><span>{{t_monitor_data.warning}}</span><br>预警监测</div>
		</div>
		<div class="cy_CIASFE_databox01">昨日监测总数<div class="cy_CIASFE_databox02">{{y_monitor_data.total}}</div></div>
		<div class="cy_CIASFE_databox03">
			<div class="cy_CIASFE_databox04"><span>{{y_monitor_data.negative}}</span><br>负面信息</div>
			<div class="cy_CIASFE_databox04"><span>{{y_monitor_data.special}}</span><br>专题监测</div>
			<div class="cy_CIASFE_databox05"><span>{{y_monitor_data.warning}}</span><br>预警监测</div>
		</div>
	</div>
		
<!--最新信息-->
	<div class="cy_CIASFE_homebox02">
		<div class="cy_CIASFE_homeboxtit">最新信息</div>
		<div class="cy_CIASFE_newsbox04">
			<div class="cy_CIASFE_newsbox01"><img src="../image/home-news.png"></div>
			<div class="cy_CIASFE_newsbox02">
				<div v-for="ndata in newest_datas" class="cy_CIASFE_newsbox03"><a v-bind:href="ndata.href">{{ndata.name}}</a></div>
				
				<!-- <div class="cy_CIASFE_newsbox03"><a href="">华帝“退全款”处处设限，消费者感觉被“套路”</a></div>
				<div class="cy_CIASFE_newsbox03"><a href="">上半年查处虚假互联网广告案增六成，恒大地产等...</a></div>
				<div class="cy_CIASFE_newsbox03"><a href="">中国太保跃升至《财富》世界500强第220</a></div>
				<div class="cy_CIASFE_newsbox03"><a href="">华帝“退全款”处处设限，消费者感觉被“套路”</a></div>
				<div class="cy_CIASFE_newsbox03"><a href="">上半年查处虚假互联网广告案增六成，恒大地产等...</a></div>  -->
		</div></div>
		
	</div>
	
<!--预警信息-->
	<div class="cy_CIASFE_homebox03">
		<div class="cy_CIASFE_homeboxtit">预警信息TOP10</div>
			<div class="cy_CIASFE_wrninfbox01">
				<div class="cy_CIASFE_wrninfbox02"><span class="cy_CIASFE_span01">1</span><a href="">中国太保跃升至《财富》世界500强第220</a></div>
				<div class="cy_CIASFE_wrninfbox02"><span class="cy_CIASFE_span01">2</span><a href="">中国石化加油站扫码引发爆炸致4死？回应：</a></div>
				<div class="cy_CIASFE_wrninfbox02"><span class="cy_CIASFE_span01">3</span><a href="">上海地铁迎来第5000辆列车</a></div>
				<div class="cy_CIASFE_wrninfbox02"><span class="cy_CIASFE_span02">4</span><a href="">成渝间开行“深夜动车”方便两地市民高铁出行</a></div>
				<div class="cy_CIASFE_wrninfbox02"><span class="cy_CIASFE_span02">5</span><a href="">日本新潜艇号称“领先中国十五年” 到底什么水平</a></div>
				<div class="cy_CIASFE_wrninfbox02"><span class="cy_CIASFE_span02">6</span><a href="">每人2亿韩元韩国法院就世越号遇难者家属索赔案...</a></div>
				<div class="cy_CIASFE_wrninfbox02"><span class="cy_CIASFE_span02">7</span><a href="">波音空客不公开中国买家，避免踏入中美经贸摩擦...</a></div>
				<div class="cy_CIASFE_wrninfbox02"><span class="cy_CIASFE_span02">8</span><a href="">贵州将建中国首条超级铁路，采用磁悬浮+低真空模式</a></div>
				<div class="cy_CIASFE_wrninfbox02"><span class="cy_CIASFE_span02">9</span><a href="">华帝-严格做好退款工作，请中国消费者如协会监督指导</a></div>
				<div class="cy_CIASFE_wrninfbox02"><span class="cy_CIASFE_span02">10</span><a href="">揭秘机场追星产业链，卖明星信息、当托、代拍图</a></div>
			</div>
		</div>
	</div>
	
<!--即将发生-->
<div class="cy_CIASFE_body">
	<div class="cy_CIASFE_homebox01">
		<div class="cy_CIASFE_homeboxtit">即将发生</div>
		<div class="cy_CIASFE_coming"><img src="../image/home-coming.png"></div>
	</div>
	<div class="cy_CIASFE_homebox02">
		<div class="cy_CIASFE_homeboxtit">热词云</div>
		<div class="cy_CIASFE_hotword"><img src="../image/home-hotword.png">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="../image/home-hotword.png"></div>
		
	</div>
	
<!--负面信息-->
	<div class="cy_CIASFE_homebox03">
		<div class="cy_CIASFE_homeboxtit">负面信息TOP10</div>
			<div class="cy_CIASFE_ngtinfbox01">
				<div class="cy_CIASFE_wrninfbox02"><span class="cy_CIASFE_span03">1</span><a href="">华帝“退全款”处处设限，消费者感觉被“套路”</a></div>
				<div class="cy_CIASFE_wrninfbox02"><span class="cy_CIASFE_span03">2</span><a href="">美的碧桂园柳叶府变相违规预售被查处</a></div>
				<div class="cy_CIASFE_wrninfbox02"><span class="cy_CIASFE_span03">3</span><a href="">上半年查处虚假互联网广告案增六成，恒大地产等...</a></div>
				<div class="cy_CIASFE_wrninfbox02"><span class="cy_CIASFE_span02">4</span><a href="">重拳治理影视行业“阴阳合同”偷税漏税</a></div>
				<div class="cy_CIASFE_wrninfbox02"><span class="cy_CIASFE_span02">5</span><a href="">摩拜APP大范围瘫痪/微信聊天记录将成呈堂证词...</a></div>
				<div class="cy_CIASFE_wrninfbox02"><span class="cy_CIASFE_span02">6</span><a href="">欠薪、强行盗版，态度强势...机构diss微视，这是卸载...</a></div>
				<div class="cy_CIASFE_wrninfbox02"><span class="cy_CIASFE_span02">7</span><a href="">学术造假，殴打副总，欺骗VC...“杀马特”华人明...</a></div>
				<div class="cy_CIASFE_wrninfbox02"><span class="cy_CIASFE_span02">8</span><a href="">石头理财崩盘了吗？石头理财提现困难传出跑路预...</a></div>
				<div class="cy_CIASFE_wrninfbox02"><span class="cy_CIASFE_span02">9</span><a href="">中弘股份暴雷老板跑路，事情可能比想象的严重</a></div>
				<div class="cy_CIASFE_wrninfbox02"><span class="cy_CIASFE_span02">10</span><a href="">河北-POS机代理商疑似“跑路”，数百商户被骗</a></div>
			</div>
	</div>
</div>
<div class="cy_CIASFE_footer01"><a href="">使用手册</a>&nbsp;&nbsp;&nbsp;&nbsp;联系我们（电话：1648726161  邮箱：sales@ichangyun.com）    Copyright&copy;2018-2021 &nbsp;&nbsp;&nbsp;&nbsp;湖北畅云时讯软件技术有限公司版权所有</div>
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
    
    var menu_datas = JSON.parse('${front_menu}');  //菜单数据来源于 classes/resources.properties
    
    Vue.component('top_menu',top_menu);   // top_menu 引自 js/ic_component.js
    
    Vue.component('ic_user_info',ic_user_info);  // ic_user_info 引自 js/ic_component.js
    
    var app = new Vue({
    	el:'#app',
    	data:{
    		
    		t_monitor_data:{               //今日监测数据
    			total:'8600' ,                   //总数
    			negative:'103',                //负面消息
    			special:'1200',                 //专题
    			warning:'3500'                  //预警
    		},
    		y_monitor_data:{               //昨日监测数据
    			total:'8600' ,                   //总数
    			negative:'103',                //负面消息
    			special:'1200',                 //专题
    			warning:'3500'                  //预警
    		},
    		
    		newest_datas:[]               //最新信息
    		
    	},
    	methods:{

    	},
    	mounted:function(){

   			this.newest_datas = [{"name":"华帝“退全款”处处设限，消费者感觉被“套路”","href":"#"},{"name":"2条信息","href":"#"},{"name":"3条信息","href":"#"},{"name":"4条信息","href":"#"},{"name":"5条信息","href":"#"}];
    	}
    });
    
</script>
</html>
