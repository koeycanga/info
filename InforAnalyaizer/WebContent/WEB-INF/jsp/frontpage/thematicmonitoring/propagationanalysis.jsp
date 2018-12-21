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
<fmt:message key="E0052" var="E0052" bundle="${sysInfo}" />
<fmt:message key="I0029" var="I0029" bundle="${sysInfo}" />  
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>竞争情报分析系统</title>
<link id="lnk" rel="stylesheet" type="text/css" href="">

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
    <div class="cy_CIASFE_condition02">
       <div class="cy_CIASFE_conbox04">
			<a href="${ctx}/thematicmonitoring/informationaggregation"><div class="cy_CIASFE_conbox06">信息汇总</div></a>
			<a href="${ctx}/thematicmonitoring/contentanalysis"><div class="cy_CIASFE_conbox06">内容分析</div></a>
			<a href="#"><div class="cy_CIASFE_conbox05">传播分析</div></a>
		</div>
			
		<div class="cy_CIASFE_search">
			<select v-model="search_type">
			     <option v-for="opt in options" v-bind:value="opt.description">{{opt.masterValue}}</option>
			</select>
			<div class="cy_CMICBMS_schbox01"><input type="text" v-model="search_key" placeholder="请输入…" style="cursor: text"></div>
			<input type="button" v-on:click="btn_search()" class="cy_CMICBMS_schbtn" value="检索">
		</div>
		<div class="cy_CIASFE_condition01">
			
			<div class="cy_CIASFE_conbox01">
			  <table border="0">
				   <tr>
				   <td>{{jc_time_name}}：  </td>
				    <td v-for="(data,index) in jc_time_datas">
					   <input  type="radio" name="montime" v-on:change="btn_search()"
						    v-bind:value="data.description" v-model="montime" 
						    v-bind:id="'montime_'+index" style="display: none;"
						     v-bind:checked="index==0">
						     <label v-bind:for="'montime_'+index">{{data.masterValue}}</label> 
					</td>
					<td>
					   <input type="radio" name="montime" value="10" v-model="montime" id="montime_6" style="display: none;"><label for="montime_6">自定义</label>
					   <input type="date" id="fromdate" class="cy_CIASFE_timetb">—<input type="date" id="todate" class="cy_CIASFE_timetb"><input type="button" v-on:click="btn_search()" class="cy_CMICBMS_schbtn" value="确认">
				   </td>
				   </tr>
			   </table>
			</div>
		 
		  <div class="cy_CIASFE_conbox02">
				<div class="cy_CIASFE_conbox03">
				    <table border="0">
				        <tr>
				            <td>{{infsour_name}}：</td>
				            <td v-for="(data,index) in infsour_datas">
				               <input type="radio" name="infsour" v-on:change="btn_search()" v-bind:value="data.description" 
				                v-model="infsour" v-bind:id="'infsour_'+index" style="display: none;" v-bind:checked="index==0">
				               <label v-bind:for="'infsour_'+index">{{data.masterValue}}</label>
				            </td>
				        </tr>
				    </table>
				</div>
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
					<p>信息来源分布</p>
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
<script type="text/javascript" src="${ctx}/js/laydate/laydate.js"></script>
<script type="text/javascript" src="${ctx}/js/comm.js"></script>
<script type="text/javascript" src="${ctx}/js/ic_components.js"></script>

<script type="text/javascript">

AdaptationResolution('${ctx}');//分辨率适配

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
		  E0019:'${E0019}',
		  E0052:'${E0052}',
		  I0029:'${I0029}'
	};

var menu_datas = JSON.parse('${front_menu}');  //菜单数据来源于 classes/resources.properties

var menu_url_data = "thematicmonitoring";

Vue.component('ic_top_menu',ic_top_menu);   // ic_top_menu 引自 js/ic_component.js

Vue.component('ic_user_info',ic_user_info);  // ic_user_info 引自 js/ic_component.js

Vue.component('ic_jcfa',ic_jcfa);  // ic_jcfa 引自 js/ic_component.js

var option_MTFB ; //媒体分布饼图option
var myChart_MTFB; //媒体分布饼图dom

var option_FZQS ;  //发展趋势折线图option
var myChart_FZQS ; //发展趋势折线图dom

var option_CBTJ ;// 传播途径option
var myChart_CBTJ; //传播途径dom

option_CBTJ ={
        tooltip: {
            trigger: 'item',
            triggerOn: 'mousemove'
        },
        series: [
            {
                type: 'tree',
                expandAndCollapse:true,
                data: [], //{"name": "flare", "children": [{"name": "cluster"}]}

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
                        fontSize: 15
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
                initialTreeDepth:10,
                animationDuration: 550,
                animationDurationUpdate: 750
            }
        ]
    };

option_FZQS = {
		title: {
			text: ''
		},
		tooltip: {
			trigger: 'axis'
		},
		legend: {
			data:[]
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
			data: [],
			axisLabel: {  
				   interval:0,  
				   rotate:40  
			} 
		},
		yAxis: {
			type: 'value'
		},
		series: []
	};



var app = new Vue({
	el:"#app",
	data:{
		isfirstInJSP:true,  //是否第一次进入jsp
		isHaveSearch:false, //是否已经搜索过
		search_key:'',
		montime:'0',  //监测时间
		infsour:'-1',  //信息来源
		 plan_ID:'',   //方案ID
		  /****************以下是顶部搜索条件相关参数**********************************/
		search_type:'',
	    options: [],
	    
	    jc_time_name:'',
	    jc_time_datas:[],
	    
	    infsour_name:'',
	    infsour_datas:[]
        /****************以上是顶部搜索条件相关参数**********************************/
	},
	methods:{
		getGeneralPurpose:function(tdata){  //获得顶部筛选条件
			 var _this = this;
			 axios.get('../GeneralPurpose/getSearchPropaOps')
               .then(function (response) {
                   var data = JSON.parse(response.data);
           
                   _this.options = data.options;
                   _this.search_type = data.options[0].description;
                   
					 _this.jc_time_datas = data.jc_time;
                   _this.jc_time_name = data.jc_time[0].controlName;
                   
                   _this.infsour_datas = data.infsour ;
                   _this.infsour_name = data.infsour[0].controlName;
            
                   if(_this.isfirstInJSP){
                	 var ins = [];
                	 for(var i=1;i<data.infsour.length;i++){
                		 ins[i-1] = data.infsour[i].masterValue;
                	 }
   					_this.createMTFB();
   					option_MTFB.legend.data = ins ;
   					myChart_MTFB.setOption(option_MTFB, true);
   					
   					_this.createFZQS();
   					option_FZQS.legend.data = ins ;
   					myChart_FZQS.setOption(option_FZQS, true);
   					
   					_this.createCBTJ();
   					
   					_this.dealSearchForChart(tdata);
   					
   					_this.isfirstInJSP = false;
   				}
               })
               .catch(function (error) {
                   console.log(error);
               });
			 
		 },
		searchByFA:function(plan_ID){    //根据方案ID查询对应的文章信息
			 this.plan_ID  = plan_ID;
			 this.isHaveSearch = true;
    		 this.btn_search();
		 },
		btn_search:function(){         //检索按钮相关
			var amontime = this.montime;
			if(this.montime=='10'){
				amontime = $("#fromdate").val()+"-"+$("#todate").val();
        	}
			var _this = this;
        	layer.msg(Info.I0011, {
        		  icon: 16
        		  ,shade: 0.01
        		});
    		axios.get('../thematicmonitoring/searchpropaga',{
    			params: {
	    				search_key:_this.search_key.trim(),
	    				montime:amontime,
	    				infsour:_this.infsour,
	    				Plan_ID:_this.plan_ID,
                    	search_type:_this.search_type, //检索类型
    				}
    			})
    			.then(function (response) {
    				
    				var data = JSON.parse(response.data);
    				
    				if(_this.isfirstInJSP){
    					_this.getGeneralPurpose(data);
    				}else{
    				    _this.dealSearchForChart(data);
    				}
    				layer.closeAll();
    			})
    			.catch(function (error) {
    			    console.log(error);
    			});
		},
		dealSearchForChart:function(data){   //查询图表所需的data
			
			option_MTFB.series[0].data = data.mtfb;      //处理媒体分布的data
			myChart_MTFB.setOption(option_MTFB, true);
			
			/*********************以下是处理发展趋势***************************************************************/
			var arr = [];  //存放折线图横轴所需数据的数组
			var tss  = data.time_datas.split(",");
			for(var i=0;i<tss.length;i++){
				arr[i] = tss[i];
			}
			option_FZQS.xAxis.data = arr;
			
			var xw_fzqs_series = [];
			var map = new Map();
            /************************以下for循环的作用是生成各信息来源对应折线图的option**************/
			for(var i=0;i<data.fzqs_data.length;i++){
				if(map.get(data.fzqs_data[i].masterValue)==null){
					var tdata = [];
					for(var j=0;j<arr.length;j++){ //每个信息来源的option的data初始设定为[0,0,0,0........]这样的数组
						tdata[j] = 0;
					}
					var fadata = {
							name:data.fzqs_data[i].masterValue,
							type:'line',
							stack: '总量',
							data:tdata
						}
					xw_fzqs_series.push(fadata);
					map.set(data.fzqs_data[i].masterValue,data.fzqs_data[i].masterValue);
				}
			}
			 /************************以上for循环的作用是生成各信息来源对应折线图的option**************/
			 
		   /************************以下for循环的作用是设置各信息来源对应折线图的option的data值***************/
			//var isds = true;
			for(var i=0;i<data.fzqs_data.length;i++){
				var name = data.fzqs_data[i].masterValue;
				for(var j=0;j<xw_fzqs_series.length;j++){
					if(xw_fzqs_series[j].name==name){
						for(var n=0;n<arr.length;n++){
							if(arr[n]==(data.fzqs_data[i].c_hour+":00")||
								arr[n]==(data.fzqs_data[i].releasetime+" "+data.fzqs_data[i].c_hour+":00")||
								arr[n]==data.fzqs_data[i].releasetime){
								//isds = false;
								xw_fzqs_series[j].data[n]+=data.fzqs_data[i].cnt;
								break;
							}
						}
						break;
					}
				}
			}
			option_FZQS.series = xw_fzqs_series;
			myChart_FZQS.setOption(option_FZQS, true);
			/**********************以上是处理发展趋势**********************************************************************************/
			
			/**********************以下是处理传播途径**************************************************/
			//console.log(data.cbtj_data);
			option_CBTJ.series[0].data = [data.cbtj_data];//data.cbtj_data;
			myChart_CBTJ.setOption(option_CBTJ, true);
			
		},
		createMTFB:function(){   //创建媒体分布饼图
			var dom = document.getElementById("cy_CIASFE_meddis");
			myChart_MTFB = echarts.init(dom);
			option_MTFB = {
				title : {
					text: '',
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
					data: []  
				},
				series : [
					{
						name: '媒体',
						type: 'pie',
						radius : '50%',//饼状图大小
						center: ['35%', '50%'], //饼状图位置
						data:  [],
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

			if (option_MTFB && typeof option_MTFB === "object") {
				myChart_MTFB.setOption(option_MTFB, true);
			}
			
		},
		createFZQS:function(){   // 发展趋势
			var dom = document.getElementById("cy_CIASFE_devtre");
			myChart_FZQS = echarts.init(dom);
			
			if (option_FZQS && typeof option_FZQS === "object") {
				myChart_FZQS.setOption(option_FZQS, true);
			}
		},
		createCBTJ:function(){  //传播途径
			var dom = document.getElementById("cy_CIASFE_sprway");
		    myChart_CBTJ = echarts.init(dom);
		
		    if (option_CBTJ && typeof option_CBTJ === "object") {
				myChart_CBTJ.setOption(option_CBTJ, true);
			}
		}
	},
	mounted:function(){
		//this.getGeneralPurpose();
		//this.createFZQS();
		//this.createCBTJ();
		if(!this.isHaveSearch){
			this.getGeneralPurpose();
		}
	}
});

</script>

</html>