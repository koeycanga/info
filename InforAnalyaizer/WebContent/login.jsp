<%@ page language="java" contentType="text/html; charset=utf-8" import="com.ichangyun.InforAnalyaizer.model.userInfo.User,com.ichangyun.InforAnalyaizer.model.CommBean"
    pageEncoding="utf-8"%>
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
 User user =  (User)session.getAttribute(CommBean.SESSION_NAME);
 if(user!=null){
	 response.sendRedirect("./login/toLogin");
 }
%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>竞争情报分析系统</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/cy_CIAS_style-1920_1080.css">
</head>

<body onkeyup="enterlogin(event)">
<div id="loginapp" class="cy_CIAS_lgbody" v-on:keyup="enterlogin($event)">
	<div  class="cy_CIAS_lgbox">
		<div class="cy_CIAS_lgboxL"><img src="${ctx}/image/login-boxbg01.png"></div>
		<div class="cy_CIAS_lgboxR">
			<input type="text" class="cy_CIAS_lgusn" v-model="username" v-validate:zip="['required']" style="background-color: white" placeholder="请输入用户名">
			<input type="password" class="cy_CIAS_lgpsw" v-model="passwd" style="background-color: white" placeholder="请输入密码">
				<div class="cy_CIAS_lgfm" >
					<input type="text"   class="cy_CIAS_lgvcp" v-model="checkCode" style="background-color: white" placeholder="请输入验证码">
					<div class="cy_CIAS_lgvc">
					   <img height="100%" v-bind:src="checkimg_src" v-on:click="flash_checkimg"/>
					</div>
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
   
function enterlogin(event){
	 event.preventDefault();
	 if(event.keyCode==13){
		var flag = $("input[name='xzqw']:checked").val();
	   	if(flag=='0'){
	   		window.location.href = "${ctx}/front/index";
	   	}
	   	if(flag=='1'){
	   		window.location.href = "${ctx}/login/toLogin";
	   	}
	 }
}

    
    function showChose(){
		 enter_num = 1;
		 layer.open({
			    id:1,
		        type: 1,
		        title:'选择前往',
		        skin:'layui-layer-rim',
		        area:['320px', 'auto'],
		        
		        content: ' <div  class="row" style="width: 300px;height:100px;  margin-left:7px; margin-top:10px;">'
		            +'<div class="col-sm-12">'
		            +'<div class="input-group">'
		            +'<input id="firstch" type="radio" name="xzqw" checked class="form-control" value="0">'
		            +'<span class="input-group-addon"> 前台首页 </span>'
		            +'</div>'
		            +'</div>'
		              +'<div class="col-sm-12" style="margin-top: 10px">'
		              +'<div class="input-group">'
		              +'<input id="secondch" type="radio" name="xzqw" class="form-control" value="1">'
		              +'<span class="input-group-addon"> 后台管理 </span>'
		              +'</div>'
		              +'</div>'
		              +'</div>'
		        ,
		        btn:['确定','取消'],
		        btn1: function (index,layero) {
		        	var flag = $("input[name='xzqw']:checked").val();
		        	if(flag=='0'){
		        		window.location.href = "${ctx}/front/index";
		        	}
		        	if(flag=='1'){
		        		window.location.href = "${ctx}/login/toLogin";
		        	}
		    	},
		        btn2:function (index,layero) {
		        	 enter_num = 0;
		        	 layer.close(index);
		        }
		 
		    });
	 }

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
			 enter_num:0,
			 username:'',
			 passwd:'',
			 checkCode:'',
			 isremberusrname:false,
			 checkimg_src:'${ctx}/login/valicode'
		 },
		 methods:{
			 showChose:function(){
				 this.enter_num = 1;
				 layer.open({
					    id:1,
				        type: 1,
				        title:'选择前往',
				        skin:'layui-layer-rim',
				        area:['320px', 'auto'],
				        
				        content: ' <div  class="row" style="width: 300px;height:100px;  margin-left:7px; margin-top:10px;">'
				            +'<div class="col-sm-12">'
				            +'<div class="input-group">'
				            +'<input id="firstch" type="radio" name="xzqw" checked class="form-control" value="0">'
				            +'<span class="input-group-addon"> 前台首页 </span>'
				            +'</div>'
				            +'</div>'
				              +'<div class="col-sm-12" style="margin-top: 10px">'
				              +'<div class="input-group">'
				              +'<input id="secondch" type="radio" name="xzqw" class="form-control" value="1">'
				              +'<span class="input-group-addon"> 后台管理 </span>'
				              +'</div>'
				              +'</div>'
				              +'</div>'
				        ,
				        btn:['确定','取消'],
				        btn1: function (index,layero) {
				        	var flag = $("input[name='xzqw']:checked").val();
				        	if(flag=='0'){
				        		window.location.href = "${ctx}/front/index";
				        	}
				        	if(flag=='1'){
				        		window.location.href = "${ctx}/login/toLogin";
				        	}
				    	},
				        btn2:function (index,layero) {
				        	 this.enter_num = 0;
				        	 layer.close(index);
				        }
				 
				    });
			 },
			 enterlogin:function(event){
				 event.preventDefault();
				 if(event.keyCode==13){
					 if(this.enter_num==0){
					 	this.login();
					 }
					 if(this.enter_num==1){
						var flag = $("input[name='xzqw']:checked").val();
			        	if(flag=='0'){
			        		window.location.href = "${ctx}/front/index";
			        	}
			        	if(flag=='1'){
			        		window.location.href = "${ctx}/login/toLogin";
			        	}
					 }
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
		    				if(response.data.indexOf("success")>=0){ //登录成功
		    					if(response.data.indexOf("1")>=0){
		    						_this.showChose();
		    					}else{
		    						window.location.href = "${ctx}/front/index";
		    					}
		    				}else if(response.data=="checkCode_n_ok"){ //验证码不正确
		    					_this.checkimg_src = '${ctx}/login/valicode?date='+new Date().getTime();
		    					_this.passwd = "";
		    					_this.checkCode = "";
		    					layer.msg(Info.E0002);
		    				}else{   //用户名或密码错误
		    					_this.username = "";
		    					_this.checkimg_src = '${ctx}/login/valicode?date='+new Date().getTime();
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