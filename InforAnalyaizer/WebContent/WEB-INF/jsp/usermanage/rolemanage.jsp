<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="ss" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources" var="sysInfo" />  <!-- basename: 在classes文件夹下properties文件的文件名 -->
<fmt:message key="I0002" var="I0002" bundle="${sysInfo}" />
<fmt:message key="I0003" var="I0003" bundle="${sysInfo}" /> 
<fmt:message key="I0004" var="I0004" bundle="${sysInfo}" /> 
<fmt:message key="W0002" var="W0002" bundle="${sysInfo}" />
<fmt:message key="W0004" var="W0004" bundle="${sysInfo}" /> 
<fmt:message key="E0006" var="E0006" bundle="${sysInfo}" /> 
<fmt:message key="E0016" var="E0016" bundle="${sysInfo}" /> 
<fmt:message key="E0017" var="E0017" bundle="${sysInfo}" /> 
<fmt:message key="I0004" var="I0004" bundle="${sysInfo}" /> 
<fmt:message key="E0018" var="E0018" bundle="${sysInfo}" /> 
<fmt:message key="E0019" var="E0019" bundle="${sysInfo}" />
<fmt:message key="E0028" var="E0028" bundle="${sysInfo}" />
<fmt:message key="E0022" var="E0022" bundle="${sysInfo}" />
<fmt:message key="I0007" var="I0007" bundle="${sysInfo}" /> 
<fmt:message key="I0008" var="I0008" bundle="${sysInfo}" /> 
<fmt:message key="I0011" var="I0011" bundle="${sysInfo}" />
<fmt:message key="I0014" var="I0014" bundle="${sysInfo}" />
<fmt:message key="I0024" var="I0024" bundle="${sysInfo}" />

<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
 
<link rel="stylesheet" type="text/css" href="${ctx}/layui/css/layui.css" />
<link id="lnk" rel="stylesheet" type="text/css" href="">
<link rel="stylesheet" type="text/css" href="${ctx}/css/cy_zg_win.css">
</head>
<body >
<div id="app" class="cy_CMICBMS_bodybg" v-cloak>
		<p><b>用户管理</b> >  <span><b>角色管理</b></span></p>
		<div class="cy_CMICBMS_tablebox">
		<div class="cy_CMICBMS_box08">
			<input type="button" v-on:click="NewRole()" class="cy_CMICBMS_addbtn" value="新增">
			<input type="button" v-on:click="EditRole()" class="cy_CMICBMS_edbtn" value="编辑">
			<input type="button" v-on:click="DelRole()" class="cy_CMICBMS_dltbtn" value="删除">
		</div>
		<div class="cy_CMICBMS_box08">
<table width="100%" border="0" class="cy_CMICBMS_chrtmngtb" cellspacing="0">
  <tbody>
    <tr>
      <th scope="col" width="25px"><input type="checkbox" v-model="checked" v-on:click="changeAllChecked()"></th>
      <th scope="col" >角色名称</th>
      <th scope="col" >角色描述</th>
      <th scope="col" >最后编辑时间</th>
      <th scope="col" >状态</th>
    </tr>
    <tr class="cy_CMICBMS_treven" v-for="data in datas">
      <td width="25px"><input type="checkbox" v-bind:value="data.userRole_ID"  v-model="checkedNames"></td>
      <td >{{data.userRoleName}}</td>
      <td >{{data.description}}</td>
      <td >{{data.updateDateTime}}</td>
      <td  v-if="data.status=='0'">暂未使用</td>
      <td  v-if="data.status=='1'"><span>使用中</span></td>
    </tr>
    <tr>
		<td v-if="datas.length==0" colspan="5">{{info}}</td>
	</tr>
  </tbody>
</table>
 <pager ref="pagecomponent"></pager>  <!-- ref 固定为 pagecomponent  -->
 
 
 <div  class="cy_hidebg" v-bind:style="showtcc"  id="cy_hidebg"></div> <!-- 遮罩层 -->
 <div  id="cy_CMICBMS_add" v-bind:style="showtcc" >
	<div class="cy_CMICBMS_addtop"><div class="cy_CMICBMS_addtit">{{tcc_title}}</div><div class="cy_CMICBMS_addclose" v-on:click="hideTcc()">X</div></div>
	<div class="cy_CMICBMS_addtb">
		<div>
		  <span>*</span>角色名称：<input type="text" maxlength="20" v-on:blur="checkRoleName()" v-model="roleInfo.role_name" placeholder="输入角色名称" class="cy_CMICBMS_addinput">
		  <div v-if="errInfo.role_name_err" class="cy_CMICBMS_errortip">${E0016}</div>
		</div>
		<div style="float:left;padding:21px !important">
		  <div style="float:left;">
		     权限设置：<span style="visibility:hidden;">*</span>&nbsp;
		  <input v-on:click="checkqx()" type="checkbox" id="yhgl" name="Authority" value="1">&nbsp;用户管理&nbsp;&nbsp;<br/>
		  <input v-on:click="checkqx()" style="margin:0px 0px 0 84px;" type="checkbox" id="qbgh" name="Authority" value="1"> 情报规划&nbsp;<br/>
		  <input v-on:click="checkqx()" style="margin:0px 0px 0 84px;" type="checkbox" id="qbcj" name="Authority" value="1"> 情报采集&nbsp;<br/>
		  <input v-on:click="checkqx()" style="margin:0px 0px 0 84px;" type="checkbox" id="qbjg" name="Authority" value="1"> 情报加工&nbsp;<br/>
		  <input v-on:click="checkqx()" style="margin:0px 0px 0 84px;" type="checkbox" id="xxgl" name="Authority" value="1"> 信息管理
		  </div>
		  <div style="width:470px;height:100px;margin:0px 0 0 0px;vertical-align: text-top;">
		       <div style="height:100px;float: left;">角色描述:</div>
		       <div style="width:390px;height:100px;float: left;">
		           <textarea v-model="roleInfo.role_des" maxlength="50" style="width:100%;height:100%;resize:none;background-color: white !important;border: 1px solid #cccccc;background-color: #fbfbfb;border-radius: 5px;"></textarea>
		      </div>
		  </div>
		</div>
		</div>
		<div style="margin:250px 0 0 400px;">
		    <input type="button" v-if="isnew" v-on:click="AddNewRole()" value="确定" class="cy_CMICBMS_schbtn">
		    <input type="button" v-else v-on:click="UpdateRole()" value="确定" class="cy_CMICBMS_schbtn">
		 </div>
	</div>
</div>
</div>
<script type="text/javascript" src="${ctx}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${ctx}/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/js/vue.min.js"></script>
<script type="text/javascript" src="${ctx}/js/axios.min.js"></script>
<script type="text/javascript" src="${ctx}/js/polyfill.min.js"></script>
<script type="text/javascript" src="${ctx}/js/comm.js"></script>
<script type="text/javascript" src="${ctx}/js/ic_components.js"></script>
<script>

AdaptationResolution('${ctx}'); //分辨率适配


//提示信息，详见clasees/resources.properties
var Info = { 
		I0002:'${I0002}',
		I0003:'${I0003}',
		I0004:'${I0004}',
		W0002:'${W0002}',
		W0004:'${W0004}',
		E0006:'${E0006}',
		E0018:'${E0018}',
		E0019:'${E0019}',
		E0022:'${E0022}',
		E0028:'${E0028}',
		I0007:'${I0007}',
		I0008:'${I0008}',
		I0011:'${I0011}',
		I0014:'${I0014}',
		I0024:'${I0024}'
};

 
  var app = new Vue({
	  
	  el:"#app",
	  data:{
		  datas:[],
		  checked: false,      //复选框相关
	      checkedNames: [],    //复选框相关
		  checkedArr: [],      //复选框相关
		  showtcc:{display:'none'},   //是否显示弹出层
		  tcc_title : '',      //弹出层的标题
		 isdisabled:false,     //
		 isnew:true,           //是弹出新增还是修改界面   true为新增   false为修改
		 roleInfo:{            //绑定新增/修改界面的一些信息
			 role_id:'',          
			 role_name:'',
			 role_des:'',
		     UpdateDateTime:''
		 },
		 errInfo:{                  //是否显示错误提示信息
			 role_id_err:false,
			 role_name_err:false
			// role_des_err:false
		 },
		 info:Info.I0024
	  },
	  components:{
		     'pager':ic_pager    //分页组件  引自js/ic_components.js
		  },
	  methods:{
		  /*checkRoleID:function(){            //检查角色ID是否填写
			  if(this.roleInfo.role_id.trim()==''){
				  this.errInfo.role_id_err = true;
			  }else{
				  this.errInfo.role_id_err = false;
			  }
		  },*/
		  checkRoleName:function(){  //检查角色名是否填写
			  if(this.roleInfo.role_name.trim()==''){
				  this.errInfo.role_name_err = true;
			  }else{
				  this.errInfo.role_name_err = false;
			  }
		  },
		  NewRole:function(){ //弹出新增界面
			  this.isnew = true;
			  this.tcc_title = Info.I0003;
			  this.showtcc = {display:'block'};
		  },
		  getTargetDataById:function(id){  //根据角色ID获得对应的角色信息
			  for(var i=0;i<this.datas.length;i++){
				  if(id==this.datas[i].userRole_ID){
					  return this.datas[i];
				  }
			  }
		  },
		  DelRole:function(){  //删除角色
			  if(this.checkedNames.length==0){  //如果用户没有选择任何数据则弹出错误信息
				  layer.msg(Info.E0019);
			  }else{
				  var iscontinue = true;
				  for(var i=0;i<this.checkedNames.length;i++){    //判断用户选择的数据   如果有还在使用中的角色则不能删除
					   var data = this.getTargetDataById(this.checkedNames[i]);
					   if(data.status=='1'){ //如果角色还在使用中
						   layer.msg(Info.E0028);
						   iscontinue = false;
						   break;
					   }
				  }
				  if(iscontinue){
					  var _this = this;
					  layer.confirm(IC_GETINFOBYAttrs(Info.W0002,['角色']), {
				            btn : [ '确定', '取消' ]//按钮
				        }, function(index) {
				            layer.close(index);
				            var json = createJSON(_this.checkedNames);   //createJSON() 引自js/comm.js
				           // _this.checkedNames = [];
							 axios.post('../role/delrole',
									 {
									 json:json
							      })
				    			.then(function (response) {
				    				if(response.data=='ok'){
				    					if(_this.checkedNames.length==_this.checkedArr.length){
				    					     if(_this.$refs.pagecomponent.pageBean.pageNow!=1){
				    					    	 _this.$refs.pagecomponent.pageBean.pageNow-=1;
				    					     }
				    					}
				    					layer.msg(Info.I0014);
				    					_this.search(_this.$refs.pagecomponent.pageBean,false);
				    				}
				    				if(response.data=='nok'){
				    					layer.msg(Info.E0022);
				    				}
				    			})
				    			.catch(function (error) {
				    			    console.log(error);
				    			});
				        }); 
				  }
			  }
		  },
		  EditRole:function(){  //弹出修改角色界面
			  if(this.checkedNames.length!=1){
				  layer.msg(Info.E0006);
			  }else{
				  this.isnew = false;
				  this.isdisabled = true;
			  	  this.tcc_title = Info.I0004;
			  	  this.showtcc = {display:'block'};
			  	  for(var i=0;i<this.datas.length;i++){
			  		  if(this.checkedNames[0]==this.datas[i].userRole_ID){
					  	  this.roleInfo.role_id = this.checkedNames[0];
					  	  this.roleInfo.role_name = this.datas[i].userRoleName;
					      this.roleInfo.role_des = this.datas[i].description;
					      this.roleInfo.UpdateDateTime = this.datas[i].updateDateTime;
					      var auth = this.datas[i].authority;
					      if(auth.charAt(0)=='1'){$("#yhgl").prop("checked",true);}
					      if(auth.charAt(1)=='1'){$("#qbgh").prop("checked",true);}
					      if(auth.charAt(2)=='1'){$("#qbcj").prop("checked",true);}
					      if(auth.charAt(3)=='1'){$("#qbjg").prop("checked",true);}
					      if(auth.charAt(4)=='1'){$("#xxgl").prop("checked",true);}
					  	  break;
			  		  }
			  	  }
			  	// this.roleInfo.role_des = this.getDescription();
			  }  
		  },
		  hideTcc:function(){     //关闭弹出层
			  this.roleInfo.role_id='';
			  this.roleInfo.role_name='';
			  this.roleInfo.role_des='';
			  this.errInfo.role_id_err = false;
			  this.errInfo.role_name_err = false;
			  //this.errInfo.role_des_err = false;
			  this.isdisabled = false;
			  $("[name='Authority']").prop("checked",false);
			  this.showtcc = {display:'none'};
			  
		  },
		  getDescription:function(){  //根据角色的权限生成角色描述
			var res = "";
			if($("[name='Authority']").eq(0).is(":checked")){
			     if(res==""){
			    	 res += "用户管理";
			     }else{
			    	 res +="+用户管理";
			     }
			} 
			if($("[name='Authority']").eq(1).is(":checked")){
			     if(res==""){
			    	 res += "情报规划";
			     }else{
			    	 res +="+情报规划";
			     }
			} 
			if($("[name='Authority']").eq(2).is(":checked")){
			     if(res==""){
			    	 res += "情报采集";
			     }else{
			    	 res +="+情报采集 ";
			     }
			} 
			
			if($("[name='Authority']").eq(3).is(":checked")){
			     if(res==""){
			    	 res += "情报加工";
			     }else{
			    	 res +="+情报加工";
			     }
			} 
			if($("[name='Authority']").eq(4).is(":checked")){
			     if(res==""){
			    	 res += "信息管理";
			     }else{
			    	 res +="+信息管理";
			     }
			} 
			return res;
		  },
		  UpdateRole:function(){ //修改角色信息
			  var b = true;
			  if(this.roleInfo.role_name.trim()==''){
					 this.errInfo.role_name_err = true;
					 b = false;
			  }
			  if(this.roleInfo.role_name.indexOf("超级管理员")>=0||this.roleInfo.role_des.indexOf("超级管理员")>=0){
				  layer.msg("超级管理员角色已经存在");
				  b = false;
			  }
			  if(b){
				 var auth = this.getAuth();
			     var _this = this;
			     if(this.roleInfo.role_des.trim()==''){
			    	 this.roleInfo.role_des = this.getDescription();
			     }
				 axios.get('../role/updaterole',{
		    			params: {
		    				UserRole_ID:_this.roleInfo.role_id.trim(),
		    				UserRoleName:_this.roleInfo.role_name.trim(),
		    				Description:_this.roleInfo.role_des.trim(),//this.getDescription(),
		    				Authority:auth,
		    				UpdateDateTime:this.roleInfo.UpdateDateTime
		    			   }
		    			})
		    			.then(function (response) {
		    				if(response.data=='ok'){
		    					layer.msg(Info.I0007);
		    					_this.search(_this.$refs.pagecomponent.pageBean,false);
		    					_this.checkedNames.push(_this.roleInfo.role_id.trim());
		    					_this.hideTcc();
		    				}
		    				if(response.data=='nok'){
		    					layer.msg(Info.E0022);
		    				}
		    				if(response.data=='already update'){
		    					layer.msg(Info.W0004);
		    				}
		    				
		    			})
		    			.catch(function (error) {
		    				layer.msg(Info.E0022);
		    			    console.log(error);
		    			});
			  }
		  },
		  AddNewRole:function(){
			 var b = true;
			 if(this.roleInfo.role_name.trim()==''){
				 this.errInfo.role_name_err = true;
				 b = false;
			 }
			 if(b){
				 var auth = this.getAuth();
				 var _this = this;
				 if(this.roleInfo.role_des.trim()==''){
			    	 this.roleInfo.role_des = this.getDescription();
			     }
				 if(this.roleInfo.role_name.indexOf("超级管理员")>=0||this.roleInfo.role_des.indexOf("超级管理员")>=0){
					  layer.msg("超级管理员角色已经存在");
					  return;
				  }
				 axios.get('../role/addnewrole',{
		    			params: {
		    				//UserRole_ID:this.roleInfo.role_id.trim(),
		    				UserRoleName:this.roleInfo.role_name.trim(),
		    				Description:this.roleInfo.role_des.trim(),
		    				Authority:auth
		    			   }
		    			})
		    			.then(function (response) {
		    				if(response.data=='ok'){
		    					layer.msg(Info.I0008);
		    					_this.hideTcc();
		    					_this.search_after_update(_this.$refs.pagecomponent.pageBean,false);
		    				}
		    				if(response.data=='exist'){
		    					layer.msg(Info.E0018);
		    				}
		    				if(response.data=='nok'){
		    					layer.msg(Info.E0022);
		    				}
		    			})
		    			.catch(function (error) {
		    			    console.log(error);
		    			});
			 }			  
		  },
		  getAuth:function(){  //生成用户权限
			 var res = "";
			 if($("#yhgl").is(':checked')){
				 res+='1';
			 }else{
				 res+='0';
			 }
			 if($("#qbgh").is(':checked')){
				 res+='1';
			 }else{
				 res+='0';
			 }
			 if($("#qbcj").is(':checked')){
				 res+='1';
			 }else{
				 res+='0';
			 }
			 if($("#qbjg").is(':checked')){
				 res+='1';
			 }else{
				 res+='0';
			 }
			 if($("#xxgl").is(':checked')){
				 res+='1';
			 }else{
				 res+='0';
			 }
			 
			 return res;
		  },
		  changeAllChecked:function(){ //全选or反选
			  if (!this.checked) {
					this.checkedNames = this.checkedArr;
				} else {
					this.checkedNames = [];
				}
		  },
		  search_after_update:function(pageBean,b){   //在新增or修改数据后查询
			  pageBean.pageNow = 1;
			  this.search(pageBean,b);
		  }
	  },
	  watch: {
		"checkedNames": function() {   //观察checkedNames的状态变化
			if (this.checkedNames.length != this.checkedArr.length||this.checkedNames.length==0) {
				this.checked = false;
			} else {
				this.checked = true;
			}
		}
	 },
	  computed:{
			search:function(){  //查询数据
		        return function(pageBean,ismsg){
		        	var _this = this;
		        	this.checkedNames = [];
		        	if(ismsg){
			        	layer.msg(Info.I0011, {
			        		  icon: 16
			        		  ,shade: 0.01
			        		});
		        	}
		    		axios.get('../role/search',{
		    			params: {
		    				pageNow:pageBean.pageNow,
		    				rowSize:pageBean.rs_selected
		    				}
		    			})
		    			.then(function (response) {
		    				 var data = JSON.parse(response.data);
		    				
		    				_this.$refs.pagecomponent.dealAfterSearch(data.rowCount); 
		    				
		    				  _this.datas =  data.resdata;
		    				  _this.checkedArr = [];
		    				  for(var i=0;i<data.resdata.length;i++){
		    					  _this.checkedArr.push(data.resdata[i].userRole_ID);
		    				  }
		    				  if(ismsg){
		    				  	 layer.closeAll();
		    				  }
		    			})
		    			.catch(function (error) {
		    			    console.log(error);
		    			});
					
				}
			}
		  },
		  mounted:function(){  //Vue加载完成后执行
			  
			  this.search(this.$refs.pagecomponent.pageBean);
			  
		  }
  });

</script>
</body>

</html>
    