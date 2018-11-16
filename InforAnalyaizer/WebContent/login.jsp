<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.* " %>  
<%@ page import="java.io.*" %>  
<%@ page import="java.util.*" %>  

<%@ taglib prefix="ss" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources" var="sysInfo" />  <!-- basename: 在classes文件夹下properties文件的文件名 -->
<fmt:message key="E0002" var="E0002" bundle="${sysInfo}" /> 
<fmt:message key="E0003" var="E0003" bundle="${sysInfo}" /> 
<fmt:message key="E0011" var="E0011" bundle="${sysInfo}" />
<fmt:message key="E0013" var="E0013" bundle="${sysInfo}" /> 
<fmt:message key="E0014" var="E0014" bundle="${sysInfo}" /> 
<fmt:message key="E0015" var="E0015" bundle="${sysInfo}" />  
 <%
 //ResourceBundle resource = ResourceBundle.getBundle("resources"); // 不带properties扩展名的文件名
 //String aa = resource.getString("aa"); // 属性名
 
 //System.out.println("aa="+aa);
 
%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>竞争情报分析系统</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/cy_CIAS_style.css">
</head>

<body>
<div id="loginapp" class="cy_CIAS_lgbody">
	<div  class="cy_CIAS_lgbox">
		<div class="cy_CIAS_lgboxL"><img src="${ctx}/image/login-boxbg01.png"></div>
		<div class="cy_CIAS_lgboxR">
			<input type="text" class="cy_CIAS_lgusn" v-model="username" v-validate:zip="['required']" style="background-color: white" placeholder="请输入用户名">
			<input type="password" class="cy_CIAS_lgpsw" v-model="passwd" style="background-color: white" placeholder="请输入密码">
				<div class="cy_CIAS_lgfm" >
					<input type="text" v-on:keyup="enterlogin($event)"  class="cy_CIAS_lgvcp" v-model="checkCode" style="background-color: white" placeholder="请输入验证码">
					<div class="cy_CIAS_lgvc"><img height="100%" v-bind:src="checkimg_src" v-on:click="flash_checkimg"/></div>
				</div>
			<div class="cy_CIAS_lgfml"><input type="checkbox" id="cy_CIAS_lgrem" v-model="isremberusrname" hidden>
			<label for="cy_CIAS_lgrem"></label><span>记住用户名</span></div>
			<input type="button" class="cy_CIAS_lgbtn" value="登录" v-on:click="login">
		</div>
	</div>

</div>
<div class="cy_CIAS_lgfoot">Copyright &copy; 2018-2021 湖北畅云软件技术有限公司版权所有 </div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${ctx}/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/js/vue.min.js"></script>
<script type="text/javascript" src="${ctx}/js/axios.min.js"></script>
<script type="text/javascript" src="${ctx}/js/polyfill.min.js"></script>
<script type="text/javascript" src="${ctx}/js/md5.min.js"></script>
<script type="text/javascript">
    
     var Info = {
    		E0002:'${E0002}', 
    	    E0003:'${E0003}',
    	    E0011:'${E0011}',
    	    E0013:'${E0013}',
    	    E0014:'${E0014}',
    	    E0015:'${E0015}'
     }
   
	 var loginapp = new Vue({
		 
		 el:'#loginapp',
		 data:{
			 username:'',
			 passwd:'',
			 checkCode:'',
			 isremberusrname:false,
			 checkimg_src:'${ctx}/login/valicode'
		 },
		 methods:{
			 enterlogin:function(event){
				 if(event.keyCode==13){
					 this.login();
				 }
			 },
			 login:function(){             //登录操作
				    if(this.username==''){
				    	layer.msg(Info.E0013);
				    	return;
				    }
				    if(this.passwd==''){
				    	layer.msg(Info.E0014);
				    	return;
				    }
				    if(this.checkCode==''){
				    	layer.msg(Info.E0015);
				    	return;
				    }
				    var _this = this;
		    		axios.get('${ctx}/login/login',{
		    			params: {
		    				username:this.username,
		    				passwd:this.passwd,
		    				checkCode:this.checkCode,
		    				isremberusrname:this.isremberusrname
		    				}
		    			})
		    			.then(function (response) {
		    				console.log(response.data);
		    				if(response.data=="ok"){ //登录成功
		    					window.location.href = "${ctx}/login/toLogin";
		    				}else if(response.data=="checkCode_n_ok"){ //验证码不正确
		    					_this.checkimg_src = '${ctx}/login/valicode?date='+new Date();
		    					_this.passwd = "";
		    					_this.checkCode = "";
		    					layer.msg(Info.E0002);
		    				}else{   //用户名或密码错误
		    					_this.username = "";
		    					_this.checkimg_src = '${ctx}/login/valicode?date='+new Date();
		    					_this.passwd = "";
		    					_this.checkCode = "";
		    					layer.msg(Info.E0003);
		    				}
		    			})
		    			.catch(function (error) {
		    			    console.log(error);
		    			    layer.msg(Info.E0011);
		    			});
				 
			 },
			 flash_checkimg:function(){   //刷新验证码图片
				 this.checkimg_src = '${ctx}/login/valicode?date='+new Date().getTime();
			 },
			 ishaverember:function(){    //处理cookie
				 var _this = this;
				 axios.get('${ctx}/login/cookies'
		    			)
		    			.then(function (response) {
		    				if(response.data!=''&&response.data!='{}'){
		    					var data = JSON.parse(response.data);
		    					_this.username = data.username;
		    					_this.passwd = data.userpwd;
		    					if(data.username!=null){
		    						_this.isremberusrname = true;
		    					}
		    				}
		    			})
		    			.catch(function (error) {
		    			    console.log(error);
		    			});
			 }
		 }
	 });
	 
	 loginapp.ishaverember();
</script>

</html>