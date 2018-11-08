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
<div class="cy_hidebg" id="cy_hidebg"></div>
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
		
			      <div v-for="(wdata,index) in warn_datas" class="cy_CIASFE_wrninfbox02"><span v-bind:class="index<=2?'cy_CIASFE_span01':'cy_CIASFE_span02'">{{index+1}}</span><a href="">{{wdata.name}}</a></div>
			
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
		<div style="margin: 60px 0 0 40px;">
		    <table style="border:1;width:100%;height:100%;align:center;">
		       <Tr>
		          <Td align="center"  id="wcdv1" style="width:354px;height:250px;"><td>
		          <Td align="center" id="wcdv2" style="width:354px;height:250px;"><td>
		       </Tr>
		    </table>
		</div>
		
	</div>
	
<!--负面信息-->
	<div class="cy_CIASFE_homebox03">
		<div class="cy_CIASFE_homeboxtit">负面信息TOP10</div>
			<div class="cy_CIASFE_ngtinfbox01">
				<div v-for="(ndata,index) in negative_datas" class="cy_CIASFE_wrninfbox02"><span v-bind:class="index<=2?'cy_CIASFE_span03':'cy_CIASFE_span02'">{{index+1}}</span><a href="">{{ndata.name}}</a></div>
			</div>
	</div>
</div>
<div class="cy_CIASFE_footer01">
<a href="">使用手册</a>&nbsp;&nbsp;&nbsp;&nbsp;联系我们（电话：1648726161  邮箱：sales@ichangyun.com）    Copyright&copy;2018-2021 &nbsp;&nbsp;&nbsp;&nbsp;湖北畅云时讯软件技术有限公司版权所有
</div>
</div>
<!-- =======================修改个人信息========================= -->
<div id="userform">
    <div id="cy_CMICBMS_add">
        <div class="cy_CMICBMS_addtop">
            <div class="cy_CMICBMS_addtit">个人信息</div>
            <div class="cy_CMICBMS_addclose" onClick="hide();">X</div>
        </div>
        <div class="cy_CMICBMS_addtb">
            <div class="cy_CMICBMS_addinput">用户角色：{{userData.urolename}}</div><br>
            <div class="cy_CMICBMS_addinput">用户名：{{userData.uid}}</div><br>
            <div class="cy_CMICBMS_addinput">姓名：{{userData.uname}}</div><br>
            <div>
                旧密码：<input type="text" placeholder="输入原密码"
                              class="cy_CMICBMS_addinput" v-model="uPwd">
            </div>
            <div>
                <span>*</span>设置密码：<input type="password" placeholder="设置密码"
                                          class="cy_CMICBMS_addinput" v-model="changePwd">
            </div>
            <div>
                <span>*</span>确认密码：<input type="password" placeholder="确认密码"
                                          class="cy_CMICBMS_addinput" v-model="checkPwd">
            </div>
            <div class="cy_CMICBMS_addinput">所属部门：{{userData.udep}}</div><br>
            <div class="cy_CMICBMS_addinput">手机号码：{{userData.utel}}</div><br>
            <div class="cy_CMICBMS_addinput">邮箱地址：{{userData.uemail}}</div><br>

            <div style="margin: 40px 20px 0 0;">
                <input type="button" value="确定" class="cy_CMICBMS_schbtn"
                       v-on:click="submit">
            </div>
        </div>

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
<script type="text/javascript" src="${ctx}/js/ic_components.js"></script>
<script type="text/javascript" src="${ctx}/js/comm.js"></script>
<script>

function hide()  //去除隐藏层和弹出层
{
   document.getElementById("cy_hidebg").style.display="none";
   document.getElementById("cy_CMICBMS_add").style.display="none";
}
var userVm = new Vue({
    el: '#userform',
    data: {
        userData: {},
        changePwd:"",
        checkPwd:"",
        uPwd:""
    },

    methods: {
        submit:
            function () {
                var _this = this;
                if(_this.uPwd!=_this.userData.upwd){
                    layer.msg('原密码不正确');
                    return false;
                }else if(_this.changePwd!=_this.checkPwd){
                    layer.msg('确认密码不一致');
                    return false;
                }else if(_this.changePwd==""){
                    layer.msg('密码不得为空');
                    return false;
                }
                _this.userData.upwd = _this.changePwd;
                var user = this.userData;
                axios.get("../user/updateUser", {
                    params: {
                        unum:user.unum,
                        uid:user.uid,
                        uname:user.uname,
                        udep:user.udep,
                        utel:user.utel,
                        uemail:user.uemail,
                        upwd:user.upwd,
                        urole:user.urole,
                        ustatus:user.ustatus
                    }
                }).then(function (response) {
                    if (response.data == "OK") {
                        window.hide();
                        layer.msg('修改成功');
                    } 
                })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
    }
});
    
    var menu_datas = JSON.parse('${front_menu}');  //菜单数据来源于 classes/resources.properties
    
    Vue.component('ic_top_menu',ic_top_menu);   // ic_top_menu 引自 js/ic_component.js
    
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
    		
    		newest_datas:[],               //最新信息
    		
    		warn_datas:[],                 //预警信息top10
    		
    		negative_datas:[],               //负面信息top10
    		userData:{}			//用户信息
    	},
    	methods:{
            updateUser:
                function () {
            		userVm.changePwd="";
            		userVm.checkPwd="";
            		userVm.uPwd="";
            		console.log(this.$refs.ic_user_info.userData)
					userVm.userData = this.$refs.ic_user_info.userData;
            		var hideobj=document.getElementById("cy_hidebg");
            	   	cy_hidebg.style.display="block";  //显示隐藏层
            	   	document.getElementById("cy_CMICBMS_add").style.display="block";  //显示弹出层
                }
    	},
    	mounted:function(){

   			this.newest_datas = [{"name":"华帝“退全款”处处设限，消费者感觉被“套路”","href":"#"},{"name":"2条信息","href":"#"},{"name":"3条信息","href":"#"},{"name":"4条信息","href":"#"},{"name":"5条信息","href":"#"}];
    	
    	    this.warn_datas = [{"name":"中国太保跃升至《财富》世界500强第220","href":"#"},{"name":"中国石化加油站扫码引发爆炸致4死？回应：","href":"#"},
			    	    	{"name":"上海地铁迎来第5000辆列车","href":"#"},{"name":"成渝间开行“深夜动车”方便两地市民高铁出行","href":"#"},
			    	    	{"name":"日本新潜艇号称“领先中国十五年” 到底什么水平","href":"#"},{"name":"每人2亿韩元韩国法院就世越号遇难者家属索赔案...","href":"#"}];
    	    
    	    
    	    this.negative_datas = [
    	    	{"name":"华帝“退全款”处处设限，消费者感觉被“套路”1","href":"#"},
    	    	{"name":"华帝“退全款”处处设限，消费者感觉被“套路”2","href":"#"},
    	    	{"name":"华帝“退全款”处处设限，消费者感觉被“套路”3","href":"#"},
    	    	{"name":"华帝“退全款”处处设限，消费者感觉被“套路”4","href":"#"},
    	    	{"name":"华帝“退全款”处处设限，消费者感觉被“套路”5","href":"#"},
    	    	{"name":"华帝“退全款”处处设限，消费者感觉被“套路”6","href":"#"},
    	    	{"name":"华帝“退全款”处处设限，消费者感觉被“套路”7","href":"#"},
    	    	{"name":"华帝“退全款”处处设限，消费者感觉被“套路”8","href":"#"}
    	    ];
    	}
    });
    

	  
	  var chart1 = echarts.init(document.getElementById('wcdv1'));
	  var chart2 = echarts.init(document.getElementById('wcdv2'));
	    
	    var option = {
	            tooltip: {},
	            series: [ {
	                type: 'wordCloud',
	                gridSize: 15,
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
	                data: [
	                    {
	                        name: '长江',
	                        value: 10000
	                    },
	                    {
	                        name: '跨越式',
	                        value: 6181
	                    },
	                    {
	                        name: '企业',
	                        value: 4386
	                    },
	                    {
	                        name: '公司',
	                        value: 4055
	                    },
	                    {
	                        name: '市场',
	                        value: 2467
	                    },
	                    {
	                        name: '亚洲',
	                        value: 2244
	                    },
	                    {
	                        name: '中国',
	                        value: 1898
	                    },
	                    {
	                        name: '长城',
	                        value: 1484
	                    },
	                    {
	                        name: '星球',
	                        value: 1112
	                    },
	                    {
	                        name: '美好山河',
	                        value: 965
	                    }
	                ]
	            } ]
	        };

	        chart1.setOption(option);
	       
	        chart2.setOption(option);
	  
  
    
</script>
</html>
