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
<fmt:message key="I0012" var="I0012" bundle="${sysInfo}" />
<fmt:message key="I0013" var="I0013" bundle="${sysInfo}" />
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>竞争情报分析系统</title>
<!--  <link rel="stylesheet" type="text/css" href="${ctx}/css/cy_CIAS_style.css"> -->
<link id="lnk" rel="stylesheet" type="text/css" href="">
</head>

<body style="background-color: #15266b;">
<div id="app" v-cloak>
<div class="cy_CIASFE_top">
	<div class="cy_CIASFE_logo"><img src="../image/fontend-logo.png"></div>
	<div class="cy_CIASFE_tit">国药器械-竞争情报分析系统</div>
    <ic_top_menu></ic_top_menu> <!-- 上部菜单栏 -->
	<ic_user_info ref="ic_user_info"></ic_user_info> <!-- 登录用户信息框 -->
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
				<div v-for="ndata in newest_datas" class="cy_CIASFE_newsbox03"><a target="_blank" v-bind:href="'../detailspage/toDetailsPage?from=front&article_id='+ndata.article_ID">{{ndata.articleTitle}}</a></div>
				
		</div>
		</div>
	</div>
	
<!--预警信息-->
	<div class="cy_CIASFE_homebox03">
		<div class="cy_CIASFE_homeboxtit">预警信息TOP10</div>
			<div class="cy_CIASFE_wrninfbox01">
		
			      <div v-for="(wdata,index) in warn_datas" class="cy_CIASFE_wrninfbox02"><span v-bind:class="index<=2?'cy_CIASFE_span01':'cy_CIASFE_span02'">{{index+1}}</span><a target="_blank" v-bind:href="'../detailspage/toDetailsPage?from=front&article_id='+wdata.article_ID">{{wdata.articleTitle}}</a></div>
			
			</div>
		</div>
	</div>
	
<!--即将发生-->
<div class="cy_CIASFE_body">
	<div class="cy_CIASFE_homebox01">
		<div class="cy_CIASFE_homeboxtit">即将发生</div>
		<div class="cy_CIASFE_coming" id="jjfsdv">
		  <!-- <img src="../image/home-coming.png"> -->
		 <table v-if="jjfs_datas.length==10" width="341" height="268" border="0" cellpadding="0" cellspacing="0">
			  <tbody>
			    <tr >
			     <td width="100%" >
			      <table width="100%"  height="100%" cellpadding="0" cellspacing="0" border="1" bordercolor="#BBFFFF" style="border-bottom-style:none;">
			      	  <tr style="border-bottom-style:none;">
			      	     <td align="center" width="70%" style="border-bottom-style:none;cursor:pointer;" v-on:click="dealJJFS(jjfs_datas[0].keyword_ID)">{{jjfs_datas[0].hotWord}}</td>
			      	     <td align="center" style="border-bottom-style:none;cursor:pointer;" v-on:click="dealJJFS(jjfs_datas[1].keyword_ID)">{{jjfs_datas[1].hotWord}}</td>
			      	   </tr >
			      </table>
			      </td>
			    </tr>
			    <tr>
			     <td>
			     	 <table width="100%"  height="100%" cellpadding="0" bordercolor="#BBFFFF" cellspacing="0" border="1">
			      	  <tr>
			      	     <td align="center" width="40%" style="cursor:pointer;" v-on:click="dealJJFS(jjfs_datas[2].keyword_ID)">{{jjfs_datas[2].hotWord}}</td>
			      	     <td align="center" rowspan="2" style="cursor:pointer;" v-on:click="dealJJFS(jjfs_datas[3].keyword_ID)">{{jjfs_datas[3].hotWord}}</td>	
			      	     
			      	   </tr>
			      	  <tr>
			      	     <td align="center" style="cursor:pointer;" v-on:click="dealJJFS(jjfs_datas[4].keyword_ID)">{{jjfs_datas[4].hotWord}}</td>
			      	  </tr>
			      </table>
			     </td>
			    </tr>
			    <tr>
			      <td >
			      	  <table width="100%"  height="100%" cellpadding="0" bordercolor="#BBFFFF" cellspacing="0" border="1" style="border-top-style:none;">
			      	    <tr>
			      	    	<Td align="center" style="border-top-style:none;cursor:pointer;" v-on:click="dealJJFS(jjfs_datas[5].keyword_ID)">{{jjfs_datas[5].hotWord}}</Td>
			      	    	<td align="center" width="70%" style="border-top-style:none;cursor:pointer;" v-on:click="dealJJFS(jjfs_datas[6].keyword_ID)">{{jjfs_datas[6].hotWord}}</td>
			      	    </tr>
					  </table>
			      </td>
			    </tr>
			    <tr>
			      <td>
			      	 <table width="100%"  height="100%" cellpadding="0" bordercolor="#BBFFFF" cellspacing="0" border="1" style="border-top-style:none;">
			      	    <tr>
			      	    	<Td align="center" style="border-top-style:none;cursor:pointer;" v-on:click="dealJJFS(jjfs_datas[7].keyword_ID)">{{jjfs_datas[7].hotWord}}</Td>
			      	    	<td align="center" width="46%" style="border-top-style:none;cursor:pointer;" v-on:click="dealJJFS(jjfs_datas[8].keyword_ID)">{{jjfs_datas[8].hotWord}}</td>
			      	    	<td align="center" style="border-top-style:none;cursor:pointer;" v-on:click="dealJJFS(jjfs_datas[9].keyword_ID)">{{jjfs_datas[9].hotWord}}</td>
			      	    </tr>
					  </table>
			      </td>
			    </tr>
			  </tbody>
			</table> 
		 </div>
	</div>
	<div class="cy_CIASFE_homebox02">
		<div class="cy_CIASFE_homeboxtit">热词云</div>
		<div style="text-align: center;">
		    <div   align="center" id="wcdv1"  style="width:800px;height: 280px;margin:30px 0 0 30px;">
		    
		    </div>
		</div>
	</div>
	
<!--负面信息-->
	<div class="cy_CIASFE_homebox03">
		<div class="cy_CIASFE_homeboxtit">负面信息TOP10</div>
			<div class="cy_CIASFE_ngtinfbox01">
				<div v-for="(ndata,index) in negative_datas" class="cy_CIASFE_wrninfbox02"><span v-bind:class="index<=2?'cy_CIASFE_span03':'cy_CIASFE_span02'">{{index+1}}</span><a v-bind:href="'../detailspage/toDetailsPage?from=front&article_id='+ndata.article_ID">{{ndata.articleTitle}}</a></div>
			</div>
	</div>
</div>
<div class="cy_CIASFE_footer01">
<a href="">使用手册</a>&nbsp;&nbsp;&nbsp;&nbsp;联系我们（电话：1648726161  邮箱：sales@ichangyun.com）    Copyright&copy;2018-2021 &nbsp;&nbsp;&nbsp;&nbsp;湖北畅云时讯软件技术有限公司版权所有
</div>
</div>

</body>
<script type="text/javascript" src="${ctx}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${ctx}/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/js/echarts.min.js"></script>
<script type="text/javascript" src="${ctx}/js/echarts-wordcloud.min.js"></script>
<script type="text/javascript" src="${ctx}/js/vue.min.js"></script>
<script type="text/javascript" src="${ctx}/js/axios.min.js"></script>
<script type="text/javascript" src="${ctx}/js/polyfill.min.js"></script>
<script type="text/javascript" src="${ctx}/js/comm.js"></script>
<script type="text/javascript" src="${ctx}/js/ic_components.js"></script>
<script>

AdaptationResolution('${ctx}');//分辨率适配

var Info = {
		W0003:'${W0003}',
		E0004:'${E0004}',
		E0014:'${E0014}',
		E0025:'${E0025}',
		I0012:'${I0012}',
		I0013:'${I0013}'
};

    var menu_datas = JSON.parse('${front_menu}');  //菜单数据来源于 classes/resources.properties
    
    Vue.component('ic_top_menu',ic_top_menu);   // ic_top_menu 引自 js/ic_component.js
    
    Vue.component('ic_user_info',ic_user_info);  // ic_user_info 引自 js/ic_component.js
    
    var app = new Vue({
    	el:'#app',
    	data:{
    		
    		t_monitor_data:{               //今日监测数据
    			total:'' ,                   //总数
    			negative:'',                //负面消息
    			special:'',                 //专题
    			warning:''                  //预警
    		},
    		y_monitor_data:{               //昨日监测数据
    			total:'' ,                   //总数
    			negative:'',                //负面消息
    			special:'',                 //专题
    			warning:''                  //预警
    		},
    		
    		jjfs_datas:[],                 //即将发生
    		
    		newest_datas:[],               //最新信息
    		
    		warn_datas:[],                 //预警信息top10
    		
    		negative_datas:[],               //负面信息top10
    		userData:{}			//用户信息
    	},
    	methods:{
    		dealJJFS:function(id){
    			window.location.href = "../front/tohotword?wordid="+id+"&flag=1";
    		},
    		getJJFSWord:function(){ //获得即将发生情报
    			var _this = this;
    			 axios.get('../front/getJJFSWord')
      			.then(function (response) {
      			     var data = JSON.parse(response.data);
      				 _this.jjfs_datas = data;
      			})
      			.catch(function (error) {
      			    console.log(error);
      			});
    			
    		},
    	    getHotWord:function(){
    	    	 axios.get('../front/getHotWord')
     			.then(function (response) {

     				var data = JSON.parse(response.data);
     				var str = '[';
     				for(var i=0;i<data.length;i++){
     					if(i==0){
     						str+='{"name":"'+data[i].hotWord+'","value":"10","id":"'+data[i].hotWord_ID+'"}';
     					}else{
     						str+=',{"name":"'+data[i].hotWord+'","value":"10","id":"'+data[i].hotWord_ID+'"}';
     					}
     				}
     				str+=']';
     				option.series[0].data = JSON.parse(str);
     				chart1.setOption(option);
     				
     			})
     			.catch(function (error) {
     			    console.log(error);
     			});
    	    },
    	    getHomeDatas:function(){
    	    	 var _this = this;
    	    	 axios.get('../front/getHomeDatas')
	      			.then(function (response) {
	      				var data = JSON.parse(response.data);
	      			
	      				_this.newest_datas = data.newest_datas;
	                    
	      				_this.warn_datas = data.warn_datas;
	      				
	      				_this.negative_datas = data.negative_datas;
	      				
	      				_this.t_monitor_data.total = data.t_total;
	      				_this.t_monitor_data.negative = data.t_fmxx;
	      				_this.t_monitor_data.special = data.t_ztjc;
	      				_this.t_monitor_data.warning = data.t_yjxx;
	      				
	      				_this.y_monitor_data.total = data.y_total;
	      				_this.y_monitor_data.negative = data.y_fmxx;
	      				_this.y_monitor_data.special = data.y_ztjc;
	      				_this.y_monitor_data.warning = data.y_yjxx;
	      				
	      			})
	      			.catch(function (error) {
	      			    console.log(error);
	      			});
    	    }
    	},
    	mounted:function(){
    	   chart1 = echarts.init(document.getElementById('wcdv1'));
           this.getJJFSWord();
    	   this.getHomeDatas();
    	   this.getHotWord();
    	}
    });
    
    var option = {
            tooltip: {},
            series: [ {
                type: 'wordCloud',
                gridSize: 18,
                sizeRange: [25, 40],
                rotationRange: [-90, 90],
                shape: 'ellipse',
                drawOutOfBound: true,
                textStyle: {
                	 normal: {
                         fontFamily: '宋体',
                         fontSize:10,
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
            } ]
        };
    
    var chart1 = echarts.init(document.getElementById('wcdv1')); 
    
    chart1.on('click', function(param) {
        console.log(param.data);//重要的参数都在这里！
        
        var hotword_id = param.data.id ;
        
        window.location.href = "../front/tohotword?wordid="+hotword_id+"&flag=0";;
    });

   
     /*   var jjfsChart = echarts.init(document.getElementById('jjfsdv'));  //即将发生矩形树图
        
        jjfs_option = {
        		
        		series: [{
        		    toolbox: {
        				show : false
        			},
        			textStyle:{
        				normal:{
        					color:'black'
        				}
        			},
        			roam:false,
        			colorAlpha:[0],
        			nodeClick:'link',
        		//	silent:true,
        		    levels:[{
        			    color:['#e0e0e0']
        		    }],
        			width:'100%',
        			height:'100%',
        			type: 'treemap',
        			data: [
        			
        			{
        				name: 'nodeA',          
        				value: 100,
        				children:[
        			
        				
        				{
        				   name:'nodeA1',
        				   value:20,
        				   children:[
        				     {
        					    name:'nodeA11',
        						value:15
        					 },
        					 {
        					    name:'nodeA12',
        						value:5
        					 }
        				   ]
        				},
        				{
        				   name:'nodeA2',
        				   value:30,
        				   children:[
        				      {
        					     name:'nodeA21',
        						 value:30
        					  },
        				      {
        					     name:'nodeA22',
        						 value:15
        					  },
        					  {
        					     name:'nodeA23',
        						 value:15
        					  }
        				   ]
        				},
        				]
        			}]
        		}]
        	};
        
        jjfsChart.setOption(jjfs_option);*/
        
       
	     /* option.series[0].data = [
	    	  {
	                name: '长江',
	                value: 10000
	            },
	            {
	                name: '跨越式',
	                value: 6181
	            }];*/
</script>
</html>
