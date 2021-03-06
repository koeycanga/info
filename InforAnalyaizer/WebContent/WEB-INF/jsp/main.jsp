<%@ page language="java" contentType="text/html; charset=utf-8" import="com.ichangyun.InforAnalyaizer.model.userInfo.User,com.ichangyun.InforAnalyaizer.model.CommBean"
    pageEncoding="utf-8"%>
<%
      String authority = "";
      boolean[] user_menu = new boolean[5];
      User user =  (User)session.getAttribute(CommBean.SESSION_NAME);
      if(user==null){
    	  response.sendRedirect("../error/session_out.jsp");
      }else{
     	   if(user.getSuperUserFlag().equals("1")){
    		  user.setAuthority("1111111111");
    	  }
    	  authority = user.getAuthority();
    	  if(authority==null||authority.equals("")||authority.equals("0000000000")){
    		  response.sendRedirect("../front/index");
    	  }
    	  for(int i=0;i<user_menu.length;i++){
    		  user_menu[i] = (authority.charAt(i)=='1');
    	  }
      }
%>
<%@ taglib prefix="ss" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources" var="sysInfo" />  <!-- basename: 在classes文件夹下properties文件的文件名 -->
<fmt:message key="W0003" var="W0003" bundle="${sysInfo}" /> 
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>中国医疗器械-后台管理</title>
<!-- <link rel="stylesheet" type="text/css" href="${ctx}/css/cy_CIAS_style.css">  -->

<link id="lnk" rel="stylesheet" type="text/css" href="">
<script>

function return_tologin(){
	window.location.href = "${ctx}/login.jsp";
}


</script>
</head>

<body style="background-color: #e4e5ea">
<div id="app" style="overflow:hidden;">

<!-----头部----->
<div class="cy_CMICBMS_top">
	<div class="cy_CMICBMS_logo">
			<div class="cy_CMICBMS_box01"></div>
		<div class="cy_CMICBMS_box02"><img src="${ctx}/image/logo.png"></div>
	<div class="cy_CMICBMS_box03">中国医疗器械有限公司</div>

</div>
	<div class="cy_CMICBMS_manag">
	<div class="cy_CMICBMS_box05"><a href="#" v-on:click="logout()">退出登录<img src="${ctx}/image/exit.png"></a></div>
	<div class="cy_CMICBMS_box04"><a href="${ctx}/front/index">跳转到前台</a></div>

	</div>
</div>
<!-----左侧导航栏----->
<div class="cy_CMICBMS_navig">
		<div class="cy_CMICBMS_box01">后台管理系统V1.0</div>
        
		<ic_menu v-for="data in menu_data" v-bind:model="data"></ic_menu>  <!-- 左侧菜单栏 -->
     </div>


<!--右侧表格部分-->

<div class="cy_CMICBMS_body" style="overflow:hidden;">

	<iframe id="mainiframe" src="" name="mainiframe" frameborder="0"  width="100%">
		
	</iframe>

</div>

</div>
<!--<footer class="cy_CMICBMS_foot">Copyright &copy; 2018-2021 湖北畅云时讯软件技术有限公司版权所有</footer>-->

<script type="text/javascript" src="${ctx}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${ctx}/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/js/vue.min.js"></script>
<script type="text/javascript" src="${ctx}/js/axios.min.js"></script>
<script type="text/javascript" src="${ctx}/js/polyfill.min.js"></script>
<script type="text/javascript" src="${ctx}/js/comm.js"></script>
<script type="text/javascript" src="${ctx}/js/ic_components.js"></script>

<script>
 
   AdaptationResolution('${ctx}'); //分辨率适配
 
   var Info = {
		   W0003:'${W0003}'
   };
   //菜单组件
   var ic_menu = {
		  
		   template:'<div v-if="model">'+
		   '<li  v-bind:class="t_class" v-on:click="toggle($event)"><a v-bind:href="model.href" target="mainiframe"><img v-if="model.imgsrc" v-bind:src="model.imgsrc"/>{{model.name}}</a></li>'+

		   '<ic_menu v-show="isopen" v-for="model in model.children" v-bind:model="model"></ic_menu>'+
		   '</div>',
		   props: ['model'],
			data:function(){
				return{
					t_class :this.model.depth==0?'cy_CMICBMS_fstmenu':'cy_CMICBMS_sndmenu',
					isopen:false
				}
			},
			methods:{
				toggle:function(event){
					this.isopen = !this.isopen ;
				}
			}
   };
 

   Vue.component('ic_menu',ic_menu);
   
   var app = new Vue({
	   el:'#app',
	   data:{
		   adata:'dasdasdas',   
		   menu_data :[
			          <% if(user_menu[0]){ %> 
			          {
		                "name":"用户管理","imgsrc":"${ctx}/image/useric.png","depth":0,   //一级菜单depth为0
		                "children":[
		                	    {"name":"账号管理","depth":1,"href":"${ctx}/yhgl/user/toUserList"},    //二级菜单depth为1  二级菜单有href属性
		                	    {"name":"角色管理","depth":1,"href":"${ctx}/yhgl/role/index"}
		                	    ]
		                },
		              <% } %>
		              <% if(user_menu[1]){ %> 
		                {
	                	 "name":"情报规划","imgsrc":"${ctx}/image/ingpl.png","depth":0, 
	                	 "children":[
		                	    {"name":"分类体系","depth":1,"href":"${ctx}/qbgh/classifcationinfo/index"},   
		                	    {"name":"过滤词管理","depth":1,"href":"${ctx}/qbgh/filterWords/toFilterWords"},
		                	    {"name":"信息源绑定","depth":1,"href":"${ctx}/qbgh/classificationSource/index"}
		                	    ]
		                },
		                <% } %>
		                <% if(user_menu[2]){ %> 
		                {
		                	 "name":"情报采集","imgsrc":"${ctx}/image/intgt.png","depth":0,
		                	 "children":[]
		                },
		                <% } %>
		                <% if(user_menu[3]){ %> 
		                {
		                	 "name":"情报加工","imgsrc":"${ctx}/image/ingpro.png","depth":0,
		                	 "children":[]
		                },
		                <% } %>
		                <% if(user_menu[4]){ %> 
		                {
		                	 "name":"信息管理","imgsrc":"${ctx}/image/imfmn.png","depth":0,
		                	 "children":[]
		                },
		                <% } %>
		              ]
	   },
	   methods:{
		   
		   logout:function(){
			   layer.confirm(Info.W0003, {
		            btn : [ '确定', '取消' ]//按钮
		        }, function(index) {
		        	axios.get('${ctx}/front/logout')
		    			.then(function (response) {
		    				window.location.href = "${ctx}/login.jsp";
		    			})
		    			.catch(function (error) {
		    			    console.log(error);
		    			});
		        }); 
		   }
		   
	   },
	   mounted:function(){
		   if( $(".cy_CMICBMS_fstmenu").length>0){
		   		$(".cy_CMICBMS_fstmenu")[0].click();
	       		$("#mainiframe").attr("src",$($(".cy_CMICBMS_sndmenu")[0]).children().eq(0).attr("href"));
		   }
	   }
   });
  
</script>

</body>
</html>
