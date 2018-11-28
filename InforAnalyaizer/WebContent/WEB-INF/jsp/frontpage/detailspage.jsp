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
<link rel="stylesheet" type="text/css" href="${ctx }/css/cy_CIAS_style.css">
</head>

<body style="background-color: #f2f3f8;">
<div id="app" v-cloak>
<!--头部-->
<div class="cy_CIASFE_top">
	<div class="cy_CIASFE_logo"><img src="${ctx }/image/fontend-logo.png"></div>
	<div class="cy_CIASFE_tit">国药器械-竞争情报分析系统</div>
	<ic_top_menu ></ic_top_menu> <!-- 上部菜单栏 -->
	<ic_user_info></ic_user_info> <!-- 登录用户信息框 -->
</div>

<div class="cy_CIAFE_main">	
	<div class="cy_CIAFE_intmonbox01">${m_title }> <span>${m_title }详情</span></div>
	<div class="cy_CIAFE_intmonbox02">
		<div class="cy_CIAFE_intmonbox03">
		<div class="cy_CIAFE_intmonbox04">
			<div class="cy_CIAFE_intmonbox05">
				<div class="cy_CIAFE_intmontit">{{article_data.articleTitle}}</div>
				<div class="cy_CIAFE_intmonsr">{{article_data.websiteName}}·{{article_data.releasetime}} <a target="_blank" v-bind:href="article_data.articleURL">原文链接</a></div>
			<div class="cy_CIAFE_intmonbox06">
				<p>{{article_data.articleText}}</p>
			</div>
			</div></div>
		</div>
		<div class="cy_CIAFE_intmonbox07">
			<div class="cy_CIAFE_intmonbox08">
				<div class="cy_CIAFE_intmonbox09">媒体报道分析</div>
				<div class="cy_CIAFE_intmonbox10">该文章在媒体上出现的次数为：<span>{{bd_data.length}}次</span></div>
					<div class="cy_CIASFE_intmontmln">
							<div class="cy_CIASFE_intmonttmlnwr" v-for="(bdata,index) in bd_data" v-if="(index<5&&!is_open)||is_open" v-bind:style="((index==4&&!is_open)||index==bd_data.length-1)?{'padding-bottom':'0'}:{}" >
								<div class="cy_CIASFE_tmlncircle"></div>
								<div class="cy_CIASFE_intmontime">{{bdata.releasetime}}</div>
								<div class="cy_CIASFE_intmonsor"><a v-bind:href="bdata.articleURL" target="_blank">{{bdata.websiteName}}</a></div>
							</div>
					</div>
				<div v-if="bd_data.length>5" class="cy_CIASFE_tmlnps"><a style="" v-on:click="open_mt()" href="#">{{is_open?'关闭':'展开'}}</a></div>
			</div>
			
			<div class="cy_CIAFE_intmonbox08">
				<div class="cy_CIAFE_intmonbox09">相关新闻</div>
				<div class="cy_CIAFE_intmonbox11">中消协约谈华帝“夺冠退全款”构成合同邀约</div>
				<div class="cy_CIAFE_intmonbox11">华帝用户退全款遇阻 中消协三度出马管用吗？</div>
				<div class="cy_CIAFE_intmonbox11">华帝“法国队夺冠退款”变“退卡”</div>
				<div class="cy_CIAFE_intmonbox11">华帝员工被欠薪 华帝停摆二十天老板被曝跑路</div>
				<div class="cy_CIAFE_intmonbox11">华帝“夺冠退款”变“退卡” 品牌营销套路到你了吗</div>
			</div>
		</div>
	</div>
</div>
</div>

<div class="cy_CIASFE_footer02"><a href="">使用手册</a>&nbsp;&nbsp;&nbsp;&nbsp;联系我们（电话：1648726161  邮箱：sales@ichangyun.com）    Copyright&copy;2018-2021 &nbsp;&nbsp;&nbsp;&nbsp;湖北畅云时讯软件技术有限公司版权所有</div>
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

var menu_url_data = "${m_url}";

Vue.component('ic_top_menu',ic_top_menu);   // 顶部菜单栏  ic_top_menu 引自 js/ic_component.js

Vue.component('ic_user_info',ic_user_info);  // 用户信息框 ic_user_info 引自 js/ic_component.js

var m_article_id = '${m_article_id}';

var app = new Vue({
	el:"#app",
	data:{
		m_article_id:m_article_id,
		article_data:[],
		bd_data:[],
		is_open:false
	},
	methods:{
		getArticleByID:function(){  //根据ID获得文章详情
			var _this = this;
			axios.get('../detailspage/getArticleByID',{
				params: {
					   article_id:_this.m_article_id
					}
				})
				.then(function (response) {
					
					var data = JSON.parse(response.data);
					
					_this.article_data = data.article_data;
					
					_this.bd_data = data.bd_data;
					
				})
				.catch(function (error) {
				    console.log(error);
				});
		},
		open_mt:function(){
			
			this.is_open = !this.is_open;
			
			
		}
	},
	mounted:function(){
	
		this.getArticleByID();
	}
});

</script>
</html>
