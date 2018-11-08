<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>中国医疗器械-后台管理</title>
<link rel="stylesheet" type="text/css" href="../css/cy_CIAS_style.css">
<script>

function return_tologin(){
	
	window.location.href = "../login.jsp";
}

</script>
</head>

<body style="background-color: #e4e5ea">
<div id="app">

<!-----头部----->
<div class="cy_CMICBMS_top">
	<div class="cy_CMICBMS_logo">
			<div class="cy_CMICBMS_box01"></div>
		<div class="cy_CMICBMS_box02"><img src="../image/logo.png"></div>
	<div class="cy_CMICBMS_box03">中国医疗器械有限公司</div>

</div>
	<div class="cy_CMICBMS_manag">
	<div class="cy_CMICBMS_box05"><a href="#" v-on:click="logout()">退出登录<img src="../image/exit.png"></a></div>
	<div class="cy_CMICBMS_box04"><a href="">跳转到前台{{menu_data[0].name}}</a></div>

	</div>
</div>
<!-----左侧导航栏----->
<div class="cy_CMICBMS_navig">
		<div class="cy_CMICBMS_box01">后台管理系统V1.0</div>
        
		<ic_menu v-for="data in menu_data" v-bind:model="data"></ic_menu>  <!-- 左侧菜单栏 -->
     </div>
</div>

<!--右侧表格部分-->

<div class="cy_CMICBMS_body">

	<iframe src="../user/toUserList" name="mainiframe" frameborder="0" iFrameHeight()>
		
	</iframe>

</div>
<!--<footer class="cy_CMICBMS_foot">Copyright &copy; 2018-2021 湖北畅云时讯软件技术有限公司版权所有</footer>-->

<script type="text/javascript" src="${ctx}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${ctx}/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/js/vue.min.js"></script>
<script type="text/javascript" src="${ctx}/js/axios.min.js"></script>
<script type="text/javascript" src="${ctx}/js/polyfill.min.js"></script>
<script type="text/javascript" src="${ctx}/js/ic_components.js"></script>
<script type="text/javascript" src="${ctx}/js/comm.js"></script>

<script>
 
   var ic_menu = {
		  
		   template:'<div>'+
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
		   menu_data :[{
		                "name":"用户管理","imgsrc":"../image/useric.png","depth":0,   //一级菜单depth为0
		                "children":[
		                	    {"name":"账号管理","depth":1,"href":"../user/toUserList"},    //二级菜单depth为1  二级菜单有href属性
		                	    {"name":"角色管理","depth":1,"href":"../role/index"}
		                	    ]
		                },
		                {
	                	 "name":"情报规划","imgsrc":"../image/ingpl.png","depth":0, 
	                	 "children":[
		                	    {"name":"分类体系","depth":1,"href":"../classifcationinfo/index"},   
		                	    {"name":"过滤词管理","depth":1,"href":"../filterWords/toFilterWords"},
		                	    {"name":"信息源绑定","depth":1,"href":"../classificationSource/index"}
		                	    ]
		                },
		                {
		                	 "name":"情报采集","imgsrc":"../image/intgt.png","depth":0,
		                	 "children":[]
		                },
		                {
		                	 "name":"情报加工","imgsrc":"../image/ingpro.png","depth":0,
		                	 "children":[]
		                },
		                {
		                	 "name":"信息管理","imgsrc":"../image/imfmn.png","depth":0,
		                	 "children":[]
		                }
		              ]
	   },
	   methods:{
		   
		   logout:function(){
			   layer.confirm("确定要退出吗?", {
		            btn : [ '确定', '取消' ]//按钮
		        }, function(index) {
		        	axios.get('../front/logout')
		    			.then(function (response) {
		    				window.location.href = "../login.jsp";
		    			})
		    			.catch(function (error) {
		    			    console.log(error);
		    			});
		        }); 
		   }
		   
	   },mounted:function(){
		   $(".cy_CMICBMS_fstmenu")[0].click();
		  
	   }
   });
 
      
</script>

</body>
</html>
