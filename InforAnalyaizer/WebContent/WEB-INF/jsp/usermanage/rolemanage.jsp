<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="ss" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources" var="sysInfo" />  <!-- basename: 在classes文件夹下properties文件的文件名 -->
<fmt:message key="I0003" var="I0003" bundle="${sysInfo}" /> 
<fmt:message key="I0004" var="I0004" bundle="${sysInfo}" /> 
<fmt:message key="W0002" var="W0002" bundle="${sysInfo}" /> 
<fmt:message key="E0006" var="E0006" bundle="${sysInfo}" /> 
<fmt:message key="E0016" var="E0016" bundle="${sysInfo}" /> 
<fmt:message key="E0017" var="E0017" bundle="${sysInfo}" /> 
<fmt:message key="I0004" var="I0004" bundle="${sysInfo}" /> 
<fmt:message key="E0018" var="E0018" bundle="${sysInfo}" /> 
<fmt:message key="E0019" var="E0019" bundle="${sysInfo}" />
<fmt:message key="E0022" var="E0022" bundle="${sysInfo}" />
<fmt:message key="I0007" var="I0007" bundle="${sysInfo}" /> 
<fmt:message key="I0008" var="I0008" bundle="${sysInfo}" /> 
<fmt:message key="I0011" var="I0011" bundle="${sysInfo}" /> 
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/cy_CIAS_style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/cy_zg_win.css">
</head>
<body >
<div id="app" class="cy_CMICBMS_bodybg" v-cloak>
		<p>用户管理 / <span>角色管理</span></p>
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
      <th scope="col"><input type="checkbox" v-model="checked" v-on:click="changeAllChecked()"></th>
      <th scope="col">用户名称</th>
      <th scope="col">角色描述</th>
      <th scope="col">最后编辑时间</th>
      <th scope="col" style="text-align: center">状态</th>
    </tr>
    <tr class="cy_CMICBMS_treven" v-for="data in datas">
      <td ><input type="checkbox" v-bind:value="data.userRole_ID"  v-model="checkedNames"></td>
      <td>{{data.userRoleName}}</td>
      <td>{{data.description}}</td>
      <td>{{data.updateDateTime}}</td>
      <td style="text-align: center" v-if="data.status=='0'">暂未使用</td>
      <td style="text-align: center" v-if="data.status=='1'"><span>使用中</span></td>
    </tr>
  </tbody>
</table>
 <pager ref="pagecomponent"></pager>  <!-- ref 固定为 pagecomponent  -->
 
 
 <div  class="cy_hidebg" v-bind:style="showtcc"  id="cy_hidebg"></div> <!-- 遮罩层 -->
 <div  id="cy_CMICBMS_add" v-bind:style="showtcc" >
	<div class="cy_CMICBMS_addtop"><div class="cy_CMICBMS_addtit">{{tcc_title}}</div><div class="cy_CMICBMS_addclose" v-on:click="hideTcc()">X</div></div>
	<div class="cy_CMICBMS_addtb">
		<div>
		  <span>*</span>角色名称：<input type="text" v-on:blur="checkRoleName()" v-model="roleInfo.role_name" placeholder="角色名称" class="cy_CMICBMS_addinput">
		  <div v-if="errInfo.role_name_err" class="cy_CMICBMS_errortip">${E0016}</div>
		</div>
		<div style="float:left;padding:22px !important">
		<span>*</span>权限设置：<div>
		  <input type="checkbox" id="yhgl" name="Authority" value="1">用户管理&nbsp;<br/>
		  <input type="checkbox" id="qbgh" name="Authority" value="1">情报规划&nbsp;<br/>
		  <input type="checkbox" id="qbcj" name="Authority" value="1">情报采集&nbsp;<br/>
		  <input type="checkbox" id="qbjg" name="Authority" value="1">情报加工&nbsp;<br/>
		  <input type="checkbox" id="xxgl" name="Authority" value="1">信息管理
		</div>
		</div>
		<div>
		    <span>*</span>角色描述：<input type="text" v-on:blur="checkRoleDes()" v-model="roleInfo.role_des" placeholder="输入角色描述" class="cy_CMICBMS_addinput">
		    <div v-if="errInfo.role_des_err" class="cy_CMICBMS_errortip">${E0017}</div>
		 </div>
		<div style="margin:40px 20px 0 0;">
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
<script type="text/javascript" src="${ctx}/js/ic_components.js"></script>
<script type="text/javascript" src="${ctx}/js/comm.js"></script>
<script>
  
var Info = {
		I0003:'${I0003}',
		I0004:'${I0004}',
		W0002:'${W0002}',
		E0006:'${E0006}',
		E0018:'${E0018}',
		E0019:'${E0019}',
		E0022:'${E0022}',
		I0007:'${I0007}',
		I0008:'${I0008}',
		I0011:'${I0011}'
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
		 isdisabled:false,
		 isnew:true,
		 roleInfo:{
			 role_id:'',          
			 role_name:'',
			 role_des:''
		 },
		 errInfo:{
			 role_id_err:false,
			 role_name_err:false,
			 role_des_err:false
		 }
	  },
	  components:{
		     'pager':ic_pager
		  },
	  methods:{
		  checkRoleID:function(){
			  if(this.roleInfo.role_id.trim()==''){
				  this.errInfo.role_id_err = true;
			  }else{
				  this.errInfo.role_id_err = false;
			  }
		  },
		  checkRoleName:function(){
			  if(this.roleInfo.role_name.trim()==''){
				  this.errInfo.role_name_err = true;
			  }else{
				  this.errInfo.role_name_err = false;
			  }
		  },
		  checkRoleDes:function(){
			  if(this.roleInfo.role_des.trim()==''){
				  this.errInfo.role_des_err = true;
			  }else{
				  this.errInfo.role_des_err = false;
			  }
		  },
		  NewRole:function(){
			  this.isnew = true;
			  this.tcc_title = Info.I0003;
			  this.showtcc = {display:'block'};
		  },
		  DelRole:function(){
			  if(this.checkedNames.length==0){
				  layer.msg(Info.E0019);
			  }else{
				  var _this = this;
				  layer.confirm(Info.W0002, {
			            btn : [ '确定', '取消' ]//按钮
			        }, function(index) {
			            layer.close(index);
			            var json = createJSON(_this.checkedNames);   //createJSON() 引自js/comm.js
			            _this.checkedNames = [];
						 axios.post('../role/delrole',
								 {
								 json:json
						      })
			    			.then(function (response) {
			    				if(response.data=='ok'){
			    					_this.search_after_update(_this.$refs.pagecomponent.pageBean);
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
		  },
		  EditRole:function(){
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
					      var auth = this.datas[i].authority;
					      if(auth.charAt(0)=='1'){$("#yhgl").prop("checked",true);}
					      if(auth.charAt(1)=='1'){$("#qbgh").prop("checked",true);}
					      if(auth.charAt(2)=='1'){$("#qbcj").prop("checked",true);}
					      if(auth.charAt(3)=='1'){$("#qbjg").prop("checked",true);}
					      if(auth.charAt(4)=='1'){$("#xxgl").prop("checked",true);}
					  	  break;
			  		  }
			  	  }
			  }
			  
		  },
		  hideTcc:function(){     //关闭弹出层
			  this.roleInfo.role_id='';
			  this.roleInfo.role_name='';
			  this.roleInfo.role_des='';
			  this.errInfo.role_id_err = false;
			  this.errInfo.role_name_err = false;
			  this.errInfo.role_des_err = false;
			  this.isdisabled = false;
			  $("[name='Authority']").prop("checked",false);
			  this.showtcc = {display:'none'};
			  
		  },
		  UpdateRole:function(){
			  var b = true;
			  if(this.roleInfo.role_name.trim()==''){
					 this.errInfo.role_name_err = true;
					 b = false;
			  }
			  if(this.roleInfo.role_des.trim()==''){
					 this.errInfo.role_des_err = true;
					 b = false;
			  }
			  if(b){
				 var auth = this.getAuth();
			     var _this = this;
				 axios.get('../role/updaterole',{
		    			params: {
		    				UserRole_ID:this.roleInfo.role_id.trim(),
		    				UserRoleName:this.roleInfo.role_name.trim(),
		    				Description:this.roleInfo.role_des.trim(),
		    				Authority:auth
		    			   }
		    			})
		    			.then(function (response) {
		    				if(response.data=='ok'){
		    					layer.msg(Info.I0007);
		    					_this.hideTcc();
		    					_this.search(_this.$refs.pagecomponent.pageBean);
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
		  AddNewRole:function(){
			 var b = true;
			 /*if(this.roleInfo.role_id.trim()==''){
				 this.errInfo.role_id_err = true;
				 b = false;
			 }*/
			 if(this.roleInfo.role_name.trim()==''){
				 this.errInfo.role_name_err = true;
				 b = false;
			 }
			 if(this.roleInfo.role_des.trim()==''){
				 this.errInfo.role_des_err = true;
				 b = false;
			 }
			 if(b){
				 var auth = this.getAuth();
				 var _this = this;
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
		    					_this.search_after_update(_this.$refs.pagecomponent.pageBean);
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
		  getAuth:function(){
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
		  changeAllChecked:function(){
			  if (!this.checked) {
					this.checkedNames = this.checkedArr;
				} else {
					this.checkedNames = [];
				}
		  },
		  search_after_update:function(pageBean){
			  pageBean.pageNow = 1;
			  this.search(pageBean);
		  }
	  },
	  watch: {
		"checkedNames": function() {
			if (this.checkedNames.length != this.checkedArr.length||this.checkedNames.length==0) {
				this.checked = false;
			} else {
				this.checked = true;
			}
		}
	 },
	  computed:{
			search:function(){
		        return function(pageBean){
		        	var _this = this;
		        	this.checkedNames = [];
		        	layer.msg(Info.I0011, {
		        		  icon: 16
		        		  ,shade: 0.01
		        		});
		    		axios.get('../role/search',{
		    			params: {
		    				pageNow:pageBean.pageNow,
		    				rowSize:pageBean.rs_selected
		    				}
		    			})
		    			.then(function (response) {
		    				//console.log(response.data);
		    				 var data = JSON.parse(response.data);
		    				
		    				_this.$refs.pagecomponent.dealAfterSearch(data.rowCount); 
		    				
		    				  _this.datas =  data.resdata;
		    				  _this.checkedArr = [];
		    				  for(var i=0;i<data.resdata.length;i++){
		    					  _this.checkedArr.push(data.resdata[i].userRole_ID);
		    				  }
		    				  layer.closeAll();
		    			})
		    			.catch(function (error) {
		    			    console.log(error);
		    			});
					
				}
			}
		  },
		  mounted:function(){
			  
			  this.search(this.$refs.pagecomponent.pageBean);
			  
		  }
  });

</script>
</body>

</html>
    