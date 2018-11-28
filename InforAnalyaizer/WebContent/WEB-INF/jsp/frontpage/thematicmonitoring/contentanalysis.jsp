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
<link rel="stylesheet" type="text/css" href="${ctx}/css/cy_CIAS_style.css">
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
				<a href="#"><div class="cy_CIASFE_conbox05">内容分析</div></a>
				<a href="${ctx}/thematicmonitoring/propagationanalysis"><div class="cy_CIASFE_conbox06">传播分析</div></a>
			</div>
			<div class="cy_CIASFE_conbox01">监测时间：
			    <input type="radio" v-model="montime" name="montime" value="0" id="montime_0" style="display: none;" checked><label for="montime_0">今天</label>
				<input type="radio" v-model="montime" name="montime" value="-1" id="montime_1" style="display: none;"><label for="montime_1">24小时</label>
           		<input type="radio" v-model="montime" name="montime" value="-2" id="montime_2" style="display: none;"><label for="montime_2">2天</label>
           		<input type="radio" v-model="montime" name="montime" value="-3" id="montime_3" style="display: none;"><label for="montime_3">3天</label>
           		<input type="radio" v-model="montime" name="montime" value="-7" id="montime_4" style="display: none;"><label for="montime_4">7天</label>
           		<input type="radio" v-model="montime" name="montime" value="-10" id="montime_5" style="display: none;"><label for="montime_5">10天</label>
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
					<p>情感属性</p>
					<div id="cy_CIASFE_emoana">

					</div>
				</div>
				<div class="cy_CIASFE_conanabox02">
					<p>热词云</p>
					<div id="cy_CIASFE_hotword"><div align="center" id="wcdv1" style="width:92%;height: 350px;margin:20px 0 0 20px;"></div></div>
				</div>
			</div>
			<div class="cy_CIASFE_conanabox03">
					<p>事件脉络</p>
					<div id="cy_CIASFE_evecon">
						<div class="cy_CIASFE_tmln">
							<div class="cy_CIASFE_tmlnwr">
								<div class="cy_CIASFE_tmlndate">2018/06/28</div>
								<div class="cy_CIASFE_tmlncircle"></div>
								<div class="cy_CIASFE_tmlntime">17:20</div>
								<div class="cy_CIASFE_tmlnevent">80余部国产新片三天时间里陆续推荐，影视产业到底是有多赚钱？<span>（烟台晚报）</span></div>
							</div>
							<div class="cy_CIASFE_tmlnwr">
								<div class="cy_CIASFE_tmlndate"></div>
								<div class="cy_CIASFE_tmlncircle"></div>
								<div class="cy_CIASFE_tmlntime">16:13</div>
								<div class="cy_CIASFE_tmlnevent">中国电影发行放映协会完善电影“退改签”方案<span>（池州晚报）</span></div>
							</div>
							<div class="cy_CIASFE_tmlnwr">
								<div class="cy_CIASFE_tmlndate"></div>
								<div class="cy_CIASFE_tmlncircle"></div>
								<div class="cy_CIASFE_tmlntime">12:58</div>
								<div class="cy_CIASFE_tmlnevent">电影票能退改签了 但还有一个坏消息？<span>（烟台晚报）</span></div>
							</div>
							<div class="cy_CIASFE_tmlnwr">
								<div class="cy_CIASFE_tmlndate"></div>
								<div class="cy_CIASFE_tmlncircle"></div>
								<div class="cy_CIASFE_tmlntime">09:37</div>
								<div class="cy_CIASFE_tmlnevent">终于等到了！电影票能退改签了！我就想问问，烂片看一半能退钱吗？<span>（烟台晚报）</span></div>
							</div>
							<div class="cy_CIASFE_tmlnwr" style="padding-bottom: 0;">
								<div class="cy_CIASFE_tmlndate">2018/06/27</div>
								<div class="cy_CIASFE_tmlncircle"></div>
								<div class="cy_CIASFE_tmlntime">22:21</div>
								<div class="cy_CIASFE_tmlnevent">电影票也可以“退改签”了！ 网友：“烂片”要慌了！<span>（烟台晚报）</span></div>
							</div>
							
						</div>
					</div>
			</div>
			<div class="cy_CIASFE_conanabox03">
					<p>观点分析</p>
					<div id="cy_CIASFE_anaopt">
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
		
		qgsx_data:[],   //情感属性环状图 data
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
    		axios.get('../thematicmonitoring/searchcontent',{
    			params: {
    				search_key:_this.search_key.trim(),
    				montime:_this.montime,
    				infsour:_this.infsour,
                    search_type:_this.selected,
    				}
    			})
    			.then(function (response) {
    				
    				console.log(response.data);
    				
    				var data = JSON.parse(response.data);
    				
    				_this.qgsx_data = data.qgsx_data;
    				
    				_this.createQGSX(_this.qgsx_data);
    				
    				_this.createGDFX();
    				
    				layer.closeAll();
    			})
    			.catch(function (error) {
    			    console.log(error);
    			});
		},
		createGDFX:function(){
			var dom = document.getElementById("cy_CIASFE_anaopt");
			var myChart = echarts.init(dom);
			var app = {};
			option = null;
			option = {
				title : {
					text: ' ',
					subtext: ' ',
					x:'center'
				},
				tooltip : {
					trigger: 'item',
					formatter: "{a} <br/>{b} : {c} ({d}%)"
				},
				legend: {
					x : 'center',
					y : 'bottom',
					data:['rose1','rose2','rose3','rose4','rose5','rose6','rose7','rose8']
				},
				toolbox: {
					show : false,
					feature : {
						mark : {show: true},
						dataView : {show: true, readOnly: false},
						magicType : {
							show: true,
							type: ['pie', 'funnel']
						},
						restore : {show: true},
						saveAsImage : {show: true}
					}
				},
				calculable : true,
				series : [
					{
						name:'面积模式',
						type:'pie',
						radius : [30, 150],
						center : ['50%', '50%'],
						roseType : 'area',
						data:[
							{value:10, name:'rose1'},
							{value:5, name:'rose2'},
							{value:15, name:'rose3'},
							{value:25, name:'rose4'},
							{value:20, name:'rose5'},
							{value:35, name:'rose6'},
							{value:30, name:'rose7'},
							{value:40, name:'rose8'}
						]
					}
				]
			};

			if (option && typeof option === "object") {
				myChart.setOption(option, true);
			}
		},
		createQGSX:function(sdata){  // 情感属性
			var _this = this;
			var dom = document.getElementById("cy_CIASFE_emoana");
			var myChart = echarts.init(dom);
			var app = {};
			option = null;
			app.title = '环形图';
		
			option = {
				tooltip: {
					trigger: 'item',
					formatter: "{a} <br/>{b}: {c} ({d}%)"
				},
				legend: {
					orient: 'vertical',
					x: 'left'
				},
				series: [
					{
						name:'情感属性',
						type:'pie',
						radius: ['40%', '60%'],
						avoidLabelOverlap: false,
						label: {
							normal: {
								show: false,
								position: 'center'
							},
							emphasis: {
								show: true,
								textStyle: {
									fontSize: '20',
									fontWeight: 'bold'
								}
							}
						},
						labelLine: {
							normal: {
								show: false
							}
						},
						data:sdata
							
					}
				]
			};
			if (option && typeof option === "object") {
				myChart.setOption(option, true);
			}
		},
		getHotWord: function () {
            axios.get('../thematicmonitoring/gethotwords')
                .then(function (response) {
                    var data = JSON.parse(response.data);
                    console.log(data.length);
                    var str = '[';
                    for (var i = 0; i < data.length; i++) {
                        if (i == 0) {
                            str += '{"name":"' + data[i].hotWord + '","value":"10"}';
                        } else {
                            str += ',{"name":"' + data[i].hotWord + '","value":"10"}';
                        }
                    }
                    str += ']';
                    optionHotWords.series[0].data = JSON.parse(str);
                    chart1.setOption(optionHotWords);


                })
                .catch(function (error) {
                    console.log(error);
                });
        },
	},
	mounted:function(){
		
		this.btn_search();
		this.getHotWord();
		
	}
	
});
var optionHotWords = {
        tooltip: {},
        series: [{
            type: 'wordCloud',
            gridSize: 15,// 词的间隔
            sizeRange: [20, 35],//词的尺寸
            rotationRange: [0, 0],//角度
            shape: 'diamond',//可选形状 square diamond circle ellipse
            left: 'center',
            top: 'center',
            width: '100%',
            height: '100%',
            right: null,
            bottom: null,
            drawOutOfBound: true,
            textStyle: {
                normal: {
                    fontFamily: '宋体',
                    fontSize: 10,
                    color: function () {
                        var colors = ['#fda67e', '#81cacc', '#cca8ba', "#88cc81", "#82a0c5", '#fddb7e', '#735ba1', '#bda29a', '#6e7074', '#546570', '#c4ccd3'];
                        return colors[parseInt(Math.random() * 10)];
                    }
                },
                emphasis: {
                    shadowBlur: 10,
                    shadowColor: '#333'
                }
            },
            data: []
        }]
    };
    var chart1 = echarts.init(document.getElementById('wcdv1'));
    chart1.setOption(optionHotWords);

    chart1.on('click', function (param) {
        // console.log(param.data.name);//重要的参数都在这里！

        var hotword = param.data.name;

        window.location.href = "../front/tohotword?word=" + hotword;
    });


</script>

</html>
