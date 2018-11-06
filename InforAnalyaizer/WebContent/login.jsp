<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.* " %>  
<%@ page import="java.io.*" %>  
<%@ page import="java.util.*" %>  

<%@ taglib prefix="ss" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources" var="sysInfo" />  <!-- basename: 在classes文件夹下properties文件的文件名 -->
 <%
 //ResourceBundle resource = ResourceBundle.getBundle("resources"); // 不带properties扩展名的文件名
 //String aa = resource.getString("aa"); // 属性名
 
 //System.out.println("aa="+aa);
 
%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>前端与后台登陆页</title>
<link rel="stylesheet" type="text/css" href="css/cy_CIAS_style.css">
</head>

<body>
<div id="loginapp" class="cy_CIAS_lgbody">
	<div  class="cy_CIAS_lgbox">
		<div class="cy_CIAS_lgboxL"><img src="image/login-boxbg01.png"></div>
		<div class="cy_CIAS_lgboxR">
			<input type="text" class="cy_CIAS_lgusn" v-model="username" v-validate:zip="['required']" style="background-color: white" placeholder="请输入用户名">
			<input type="password" class="cy_CIAS_lgpsw" v-model="passwd" style="background-color: white" placeholder="请输入密码">
				<div class="cy_CIAS_lgfm" >
					<input type="text" class="cy_CIAS_lgvcp" v-model="checkCode" style="background-color: white" placeholder="请输入验证码">
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

<script type="text/javascript" src="js/vue.min.js"></script>
<script type="text/javascript" src="js/axios.min.js"></script>
<script type="text/javascript" src="js/polyfill.min.js"></script>
<script type="text/javascript">
	 var loginapp = new Vue({
		 
		 el:'#loginapp',
		 data:{
			 username:'',
			 passwd:'',
			 checkCode:'',
			 isremberusrname:false,
			 checkimg_src:'user/valicode'
		 },
		 methods:{
			 login:function(){             //登录操作
				    if(this.username==''){
				    	alert("请输入用户名");
				    	return;
				    }
				    if(this.passwd==''){
				    	alert("请输入密码");
				    	return;
				    }
				    if(this.checkCode==''){
				    	alert("请输入验证码");
				    	return;
				    }
				    var _this = this;
		    		axios.get('user/login',{
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
		    					window.location.href = "user/toLogin";
		    				}else if(response.data=="checkCode_n_ok"){ //验证码不正确
		    					_this.checkimg_src = 'user/valicode?date='+new Date();
		    					_this.passwd = "";
		    					_this.checkCode = "";
		    					alert("验证码不正确");
		    				}else{   //用户名或密码错误
		    					_this.username = "";
		    					_this.checkimg_src = 'user/valicode?date='+new Date();
		    					_this.passwd = "";
		    					_this.checkCode = "";
		    					alert("用户名或密码错误");
		    				}
		    			})
		    			.catch(function (error) {
		    			    console.log(error);
		    			});
				 
			 },
			 flash_checkimg:function(){   //刷新验证码图片
				 this.checkimg_src = 'user/valicode?date='+new Date();
			 },
			 ishaverember:function(){    //处理cookie
				 var _this = this;
				 axios.get('user/cookies'
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