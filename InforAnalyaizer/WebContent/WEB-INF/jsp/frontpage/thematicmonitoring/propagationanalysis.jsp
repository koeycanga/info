<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="ss" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources" var="sysInfo" />
<fmt:message key="front_menu" var="front_menu" bundle="${sysInfo}" />
<fmt:message key="W0003" var="W0003" bundle="${sysInfo}" />
<fmt:message key="E0004" var="E0004" bundle="${sysInfo}" />
<fmt:message key="E0014" var="E0014" bundle="${sysInfo}" />
<fmt:message key="E0025" var="E0025" bundle="${sysInfo}" />
<fmt:message key="I0011" var="I0011" bundle="${sysInfo}" />  
<fmt:message key="I0012" var="I0012" bundle="${sysInfo}" />
<fmt:message key="I0013" var="I0013" bundle="${sysInfo}" /> 
<fmt:message key="W0002" var="W0002" bundle="${sysInfo}" /> 
<fmt:message key="E0022" var="E0022" bundle="${sysInfo}" /> 
<fmt:message key="I0008" var="I0008" bundle="${sysInfo}" />
<fmt:message key="I0013" var="I0013" bundle="${sysInfo}" /> 
<fmt:message key="I0014" var="I0014" bundle="${sysInfo}" />
<fmt:message key="E0029" var="E0029" bundle="${sysInfo}" />
<fmt:message key="E0030" var="E0030" bundle="${sysInfo}" />
<fmt:message key="I0017" var="I0017" bundle="${sysInfo}" />
<fmt:message key="I0018" var="I0018" bundle="${sysInfo}" />
<fmt:message key="I0019" var="I0019" bundle="${sysInfo}" />
<fmt:message key="E0031" var="E0031" bundle="${sysInfo}" /> 
<fmt:message key="E0019" var="E0019" bundle="${sysInfo}" />   

<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>竞争情报分析系统</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/cy_CIAS_style.css">

</head>

<body style="background-color: #f2f3f8;">
<div id="app" v-cloak>
<div class="cy_hidebg" id="cy_hidebg"></div>
<!--头部-->
<div class="cy_CIASFE_top">
	<div class="cy_CIASFE_logo"><img src="${ctx}/image/fontend-logo.png"></div>
	<div class="cy_CIASFE_tit">国药器械-竞争情报分析系统</div>
	<ic_top_menu></ic_top_menu> <!-- 上部菜单栏 -->
	<ic_user_info ref="ic_user_info"></ic_user_info> <!-- 登录用户信息框 -->
</div>

<!--左侧数据-->
<div class="cy_CIAFE_main">
	<div class="cy_CIASFE_intmonbody">
	
	<ic_jcfa></ic_jcfa>
	
	<div class="cy_CIASFE_intmonbodyright">
	
<!--条件筛选-->
		<div class="cy_CIASFE_search">
			<select v-model="selected">
				<option v-for="opt in options" v-bind:value="opt.searchtype">{{opt.value}}</option>
			</select>
			<div class="cy_CMICBMS_schbox01"><input type="text" v-model="search_key" placeholder="请输入…" style="cursor: text"></div>
			<input type="button" v-on:click="btn_search()" class="cy_CMICBMS_schbtn" value="检索">
		</div>
		<div class="cy_CIASFE_condition01">
			<div class="cy_CIASFE_conbox04">
				<a href="${ctx}/thematicmonitoring/informationaggregation"><div class="cy_CIASFE_conbox06">信息汇总</div></a>
				<a href="${ctx}/thematicmonitoring/contentanalysis"><div class="cy_CIASFE_conbox06">内容分析</div></a>
				<a href="#"><div class="cy_CIASFE_conbox05">传播分析</div></a>
			</div>
			<div class="cy_CIASFE_conbox01">监测时间：<input type="radio" name="montime" value="单选" id="montime_0" style="display: none;" checked><label for="montime_0">今天</label>
				<input type="radio" v-model="montime" name="montime" value="0" id="montime_1" style="display: none;"><label for="montime_1">24小时</label>
           		<input type="radio" v-model="montime" name="montime" value="-1" id="montime_2" style="display: none;"><label for="montime_2">2天</label>
           		<input type="radio" v-model="montime" name="montime" value="-2" id="montime_3" style="display: none;"><label for="montime_3">3天</label>
           		<input type="radio" v-model="montime" name="montime" value="-3" id="montime_4" style="display: none;"><label for="montime_4">7天</label>
           		<input type="radio" v-model="montime" name="montime" value="-7" id="montime_5" style="display: none;"><label for="montime_5">10天</label>
           		<input type="radio" v-model="montime" name="montime" value="10" id="montime_6" style="display: none;"><label for="montime_6">自定义</label><input type="date" class="cy_CIASFE_timetb">—<input type="date" class="cy_CIASFE_timetb"><input type="button" class="cy_CMICBMS_schbtn" value="确认">
            </div>
		 
		  <div class="cy_CIASFE_conbox02">
				<div class="cy_CIASFE_conbox03">媒体平台：
					<input type="radio" v-model="infsour" name="infsour" value="-1" id="infsour_0" style="display: none;" checked><label for="infsour_0">全部</label>
					<input type="radio" v-model="infsour" name="infsour" value="0" id="infsour_1" style="display: none;"><label for="infsour_1">新闻</label>
					<input type="radio" v-model="infsour" name="infsour" value="1" id="infsour_2" style="display: none;"><label for="infsour_2">微博</label>
					<input type="radio" v-model="infsour" name="infsour" value="2" id="infsour_3" style="display: none;"><label for="infsour_3">微信</label>
					<input type="radio" v-model="infsour" name="infsour" value="3" id="infsour_4" style="display: none;"><label for="infsour_4">论坛</label>
					<input type="radio" v-model="infsour" name="infsour" value="4" id="infsour_5" style="display: none;"><label for="infsour_5">博客</label>
				</div>
			</div>
	  </div>
	  
<!--	内容-->
	<!--	空数据-->
		<div class="cy_CIASFE_ctnodata">
			<img src="${ctx}/image/fontend-nodata.png">
		</div>
	<!--	详细-->
		<div class="cy_CIASFE_conanamain">
			<div class="cy_CIASFE_conanabox01">
				<div class="cy_CIASFE_conanabox02" style="margin-right: 30px;">
					<p>发展趋势</p>
					<div id="cy_CIASFE_devtre">

					</div>
				</div>
				<div class="cy_CIASFE_conanabox02">
					<p>媒体分布</p>
					<div id="cy_CIASFE_meddis">

					</div>
				</div>
			</div>
			<div class="cy_CIASFE_conanabox03">
					<p>传播途径</p>
					<div id="cy_CIASFE_sprway">
						<script type="text/javascript">
						
							   </script>
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
<script type="text/javascript" src="${ctx}/js/echarts.min.js"></script>
<script type="text/javascript" src="${ctx}/js/cy_ectheme.js"></script>
<script type="text/javascript" src="${ctx}/js/echarts-wordcloud.min.js"></script>
<script type="text/javascript" src="${ctx}/js/vue.min.js"></script>
<script type="text/javascript" src="${ctx}/js/axios.min.js"></script>
<script type="text/javascript" src="${ctx}/js/polyfill.min.js"></script>
<script type="text/javascript" src="${ctx}/js/ic_components.js"></script>
<script type="text/javascript" src="${ctx}/js/comm.js"></script>

<script type="text/javascript">
var Info = {
		  W0003:'${W0003}',
		  E0004:'${E0004}',
		  E0014:'${E0014}',
		  E0025:'${E0025}',
		  I0011:'${I0011}',
		  I0012:'${I0012}',
		  I0013:'${I0013}',
		  W0002:'${W0002}',
		  E0022:'${E0022}',
		  I0008:'${I0008}',
		  I0013:'${I0013}',
		  I0014:'${I0014}',
		  E0030:'${E0030}',
		  I0017:'${I0017}',
		  I0018:'${I0018}',
		  E0029:'${E0029}',
		  E0031:'${E0031}',
		  I0019:'${I0019}',
		  E0019:'${E0019}'
	};

var menu_datas = JSON.parse('${front_menu}');  //菜单数据来源于 classes/resources.properties

var menu_url_data = "thematicmonitoring";

Vue.component('ic_top_menu',ic_top_menu);   // ic_top_menu 引自 js/ic_component.js

Vue.component('ic_user_info',ic_user_info);  // ic_user_info 引自 js/ic_component.js

Vue.component('ic_jcfa',ic_jcfa);  // ic_jcfa 引自 js/ic_component.js

var app = new Vue({
	el:"#app",
	data:{
		search_key:'',
		montime:'0',  //监测时间
		infsour:'-1',  //信息来源
        selected: 'all', //搜索类型,默认'all' 全文检索
        options: [
            { searchtype: 'all', value: '全文匹配' },
            { searchtype: 'title', value: '标题匹配' },
            { searchtype: 'text', value: '正文匹配' }
        ]
	},
	methods:{
		
		btn_search:function(){
			if(this.montime=='10'){
        		this.montime = $("#fromdate").val()+"-"+$("#todate").val();
        	}
			var _this = this;
        	layer.msg(Info.I0011, {
        		  icon: 16
        		  ,shade: 0.01
        		});
    		axios.get('../thematicmonitoring/searchpropaga',{
    			params: {
	    				search_key:_this.search_key.trim(),
	    				montime:_this.montime,
	    				infsour:_this.infsour,
                    	search_type:_this.selected, //检索类型
    				}
    			})
    			.then(function (response) {
    				
    				
    				layer.closeAll();
    			})
    			.catch(function (error) {
    			    console.log(error);
    			});
		},
		createFZQS:function(){
			var dom = document.getElementById("cy_CIASFE_devtre");
			var myChart = echarts.init(dom);
			var app = {};
			option = null;
			option = {
				title: {
					text: '折线图堆叠'
				},
				tooltip: {
					trigger: 'axis'
				},
				legend: {
					data:['邮件营销','联盟广告','视频广告','直接访问','搜索引擎']
				},
				grid: {
					left: '3%',
					right: '4%',
					bottom: '3%',
					containLabel: true
				},
				toolbox: {
					feature: {
						saveAsImage: {}
					}
				},
				xAxis: {
					type: 'category',
					boundaryGap: false,
					data: ['周一','周二','周三','周四','周五','周六','周日']
				},
				yAxis: {
					type: 'value'
				},
				series: [
					{
						name:'邮件营销',
						type:'line',
						stack: '总量',
						data:[120, 132, 101, 134, 90, 230, 210]
					},
					{
						name:'联盟广告',
						type:'line',
						stack: '总量',
						data:[220, 182, 191, 234, 290, 330, 310]
					},
					{
						name:'视频广告',
						type:'line',
						stack: '总量',
						data:[150, 232, 201, 154, 190, 330, 410]
					},
					{
						name:'直接访问',
						type:'line',
						stack: '总量',
						data:[320, 332, 301, 334, 390, 330, 320]
					},
					{
						name:'搜索引擎',
						type:'line',
						stack: '总量',
						data:[820, 932, 901, 934, 1290, 1330, 1320]
					}
				]
			};
			;
			if (option && typeof option === "object") {
				myChart.setOption(option, true);
			}
		},
		createMTFB:function(){
			var dom = document.getElementById("cy_CIASFE_meddis");
			var myChart = echarts.init(dom);
			var app = {};
			option = null;
			var data = genData(50);

			option = {
				title : {
					text: '同名数量统计',
					subtext: '纯属虚构',
					x:'center'
				},
				tooltip : {
					trigger: 'item',
					formatter: "{a} <br/>{b} : {c} ({d}%)"
				},
				legend: {
					type: 'scroll',
					orient: 'vertical',
					right: 10,
					top: 20,
					bottom: 20,
					data: data.legendData,

					selected: data.selected
				},
				series : [
					{
						name: '姓名',
						type: 'pie',
						radius : '50%',//饼状图大小
						center: ['35%', '50%'], //饼状图位置
						data: data.seriesData,
						itemStyle: {
							emphasis: {
								shadowBlur: 10,
								shadowOffsetX: 0,
								shadowColor: 'rgba(0, 0, 0, 0.5)'
							}
						}
					}
				]
			};
			function genData(count) {
				var nameList = [
					'赵', '钱', '孙', '李', '周', '吴', '郑', '王', '冯', '陈', '褚', '卫', '蒋', '沈', '韩', '杨', '朱', '秦', '尤', '许', '何', '吕', '施', '张', '孔', '曹', 
				];
				var legendData = [];
				var seriesData = [];
				var selected = {};
				for (var i = 0; i < 50; i++) {
					name = Math.random() > 0.65
						? makeWord(4, 1) + '·' + makeWord(3, 0)
						: makeWord(2, 1);
					legendData.push(name);
					seriesData.push({
						name: name,
						value: Math.round(Math.random() * 100000)
					});
					selected[name] = i < 6;
				}

				return {
					legendData: legendData,
					seriesData: seriesData,
					selected: selected
				};

				function makeWord(max, min) {
					var nameLen = Math.ceil(Math.random() * max + min);
					var name = [];
					for (var i = 0; i < nameLen; i++) {
						name.push(nameList[Math.round(Math.random() * nameList.length - 1)]);
					}
					return name.join('');
				}
			}
			;
			if (option && typeof option === "object") {
				myChart.setOption(option, true);
			}
		},
		
		createCBTJ:function(){
			var dom = document.getElementById("cy_CIASFE_sprway");
			var myChart = echarts.init(dom);
			var app = {};
			option = null;
			myChart.showLoading();
			$.get('data/asset/data/flare.json', function (data) {
				myChart.hideLoading();

				echarts.util.each(data.children, function (datum, index) {
					index % 2 === 0 && (datum.collapsed = true);
				});

				myChart.setOption(option = {
					tooltip: {
						trigger: 'item',
						triggerOn: 'mousemove'
					},
					series: [
						{
							type: 'tree',

							data: [data],

							top: '1%',
							left: '7%',
							bottom: '1%',
							right: '20%',

							symbolSize: 7,

							label: {
								normal: {
									position: 'left',
									verticalAlign: 'middle',
									align: 'right',
									fontSize: 9
								}
							},

							leaves: {
								label: {
									normal: {
										position: 'right',
										verticalAlign: 'middle',
										align: 'left'
									}
								}
							},

							expandAndCollapse: true,
							animationDuration: 550,
							animationDurationUpdate: 750
						}
					]
				});
			});;
			if (option && typeof option === "object") {
				myChart.setOption(option, true);
			}
		}
	},
	mounted:function(){
		this.createFZQS();
		this.createMTFB();
		this.createCBTJ();
	}
});

</script>

</html>