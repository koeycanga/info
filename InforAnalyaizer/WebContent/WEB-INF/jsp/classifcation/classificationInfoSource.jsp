<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="ss" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources" var="sysInfo" />  <!-- basename: 在classes文件夹下properties文件的文件名 -->
<fmt:message key="E0006" var="E0006" bundle="${sysInfo}" /> 
<fmt:message key="E0024" var="E0024" bundle="${sysInfo}" /> 
<fmt:message key="E0039" var="E0039" bundle="${sysInfo}" /> 
<fmt:message key="I0011" var="I0011" bundle="${sysInfo}" />
<fmt:message key="I0002" var="I0002" bundle="${sysInfo}" />
<fmt:message key="I0024" var="I0024" bundle="${sysInfo}" />
<fmt:message key="MaxSearchCnt" var="MaxSearchCnt" bundle="${sysInfo}" />
<fmt:message key="W0001" var="W0001" bundle="${sysInfo}" />
<!doctype html> 
<html>
<head>
<meta charset="UTF-8">
<title></title>
<!-- <link rel="stylesheet" type="text/css" href="${ctx}/css/cy_CIAS_style.css">   --> 
<link rel="stylesheet" type="text/css" href="${ctx}/layui/css/layui.css" />
<link id="lnk" rel="stylesheet" type="text/css" href="">
</head>
<body >
<div id="app" v-cloak>
<div class="cy_CMICBMS_bodybg">
		<p><b>情报规划</b> > <span><b>信息源绑定</b></span></p>
	<div class="cy_CMICBMS_tablebox">
		<div class="cy_CMICBMS_box07">
			<select style="width:150px" v-model="isbinding">
				<option value="" >是否绑定信息源</option>
				<option value="1" >是</option>
				<option value="2" >否</option>
			</select>
			<div class="cy_CMICBMS_schbox01">
				<input type="text" v-model="search_key" placeholder="请输入分类名称进行检索...">
			</div>
			<input type="button" class="cy_CMICBMS_schbtn" v-on:click="btn_search()" value="检索">
		</div>
		<div class="cy_CMICBMS_box08">
			<input type="button" class="cy_CMICBMS_edbtn" value="编辑"  v-on:click="EditData()">
		</div>
		<div class="cy_CMICBMS_box08">
<table width="100%" border="0" class="cy_CMICBMS_accmngtb" 	cellspacing="0">
  <tbody>
    <tr>
      <th scope="col" width="25px"><input type="checkbox" v-on:click="changeAllChecked()" name="dch" id="thch" v-model="checked" class="cy_CMICBMS_checkbox"></th>
      <th scope="col" width="40%">分类名称</th>
      <th scope="col" width="10%">绑定信息源</th>
      <th scope="col" width="20%">绑定目标网站数量</th>
      <th scope="col" width="auto">最近编辑时间</th>
      <th nowrap scope="col" width="auto">最近编辑者</th>
    </tr>
    <tr class="cy_CMICBMS_treven" v-for="data in datas">
      <td>
         <ic_tree_dv_checkbox v-bind:model="data"></ic_tree_dv_checkbox>
      </td>
      <td>
        <div class="cy_tree_node"> 
            <ic_tree_dv_name v-bind:model="data"></ic_tree_dv_name>
         </div>
      </td>
      <td>
         <ic_tree_dv_binding v-bind:model="data"></ic_tree_dv_binding>       
      </td>
      <td>
         <ic_tree_dv_num v-bind:model="data"></ic_tree_dv_num>
      </td>
      <td nowrap><ic_tree_dv_updatetime v-bind:model="data"></ic_tree_dv_updatetime></td>
      <td nowrap><ic_tree_dv_updater v-bind:model="data"></ic_tree_dv_updater></td>
    </tr>
     <tr>
		<td v-if="datas.length==0" colspan="6">{{info}}</td>
	</tr>
  </tbody>
</table>
		</div>
   <pager ref="pagecomponent"></pager>  <!-- ref 固定为 pagecomponent  -->
</div>

<!--新增按钮弹窗-->
<div class="cy_hidebg" id="cy_hidebg" style="display:block;" v-show="isshowtcc"></div>
<div id="cy_CMICBMS_infed" style="display:block;" v-show="isshowtcc">
	<div class="cy_CMICBMS_infedtop"><div class="cy_CMICBMS_infedtit">编辑信息源</div><div class="cy_CMICBMS_infedclose" v-on:click="hide()">X</div></div>
	<div class="cy_CMICBMS_infedtb">
		<div class="cy_CMICBMS_infedtbtit">{{ssfl_msg}}</div>
		<div class="cy_CMICBMS_infedtbbd">
			<div class="cy_CMICBMS_infedtbbdL">
				<div class="cy_CMICBMS_infedLbox">
				可选网站:<input type="text" v-model="search_key_webinfo" placeholder="请输入网站名称进行检索..." class="cy_CMICBMS_infedschbox">
				      <input type="button" v-on:click="btn_web_search()" class="cy_CMICBMS_infedschbtn" value="检索">
				</div>
				<div style="height:3px;">&nbsp;</div>
				<div class="cy_CMICBMS_infedLbox">
					<table width="100%" border="0" cellspacing="0">
						<tbody>
						<tr>
						  <th style="width: 20px;"><input type="checkbox" id="left_sel" name="l_boxs"  v-model="web_checked" v-on:click="left_sel()"></th>
						  <td style="width: auto;font-weight: 700;">网站名称</td>
						</tr>
						<tr v-for="(wdata,index) in webinfo_datas">
						  <th v-bind:style="index==webinfo_datas.length-1?{'border-bottom': '1px #e0e0e0 solid'}:{}">
						     <input type="checkbox" name="l_boxs" onclick="left_box_sel(this)" v-bind:value="wdata.website_ID" v-model="web_checkedNames">
						   </th>
						  <td v-bind:style="index==webinfo_datas.length-1?{'border-bottom': '1px #e0e0e0 solid'}:{}">{{wdata.websiteName}}</td>
						</tr>
						 <tr>
							<td v-if="webinfo_datas.length==0" colspan="6">{{info}}</td>
						</tr>
					  </tbody>
					</table>
				</div>

			<left_pager v-bind:model="'search_left'" ref="left_pagecomponent"></left_pager>
			</div>
			
			
			<div class="cy_CMICBMS_infedtbbdM">
				<input type="button" id="cy_CMICBMS_bindbtn" v-on:click="joingright()" value=">>">
				<input type="button" id="cy_CMICBMS_releabtn" v-on:click="joingleft()" value="<<">
			</div>
			
			<div class="cy_CMICBMS_infedtbbdR">
				<div class="cy_CMICBMS_infedRbox">已绑定网站:</div>
				<div class="cy_CMICBMS_infedLbox">
					<table width="100%" border="0" cellspacing="0">
						<tbody>
						<tr>
						  <th style="width: 20px;"><input type="checkbox" name="r_boxs" id="right_sel" v-model="web_al_checked" v-on:click="right_sel()"></th>
						  <td style="width: auto;font-weight: 700;">网站名称</td>
						</tr>
						<tr v-for="(rdata,index) in webinfo_al_datas">
						  <th v-bind:style="index==webinfo_al_datas.length-1?{'border-bottom': '1px #e0e0e0 solid'}:{}">
						     <input type="checkbox" name="r_boxs" onclick="right_box_sel(this)"  v-bind:value="rdata.website_ID" v-model="web_al_checkedNames">
						  </th>
						  <td v-bind:style="index==webinfo_al_datas.length-1?{'border-bottom': '1px #e0e0e0 solid'}:{}">{{rdata.websiteName}}</td>
						</tr>
						 <tr>
							<td v-if="webinfo_al_datas.length==0" colspan="6">{{info}}</td>
						</tr>
					  </tbody>
					</table>
				</div>
				<right_pager v-bind:model="'search_right'" ref="right_pagecomponent"></right_pager>
		       </div>
			</div>
		<div class="cy_CMICBMS_infeddn"><input type="button" v-on:click="confim()" value="确定"></div>
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

//提示信息集合  来源于 classes/resources.properties
var Info = {
	E0006:'${E0006}',
	E0024:'${E0024}',
	I0011:'${I0011}',
	I0002:'${I0002}',
	I0024:'${I0024}',
	E0039:'${E0039}',    
	MaxSearchCnt:'${MaxSearchCnt}',
	W0001:'${W0001}'
};

//某个复选框被点击后，判断全选/反选情况
function checka(obj){
	if(!obj.checked){
		checked = false;
		$("#thch").prop("checked",false);
	}else{
		if($("input[name='dch']:checked").length==$("input[name='dch']").length-1){
			checked = true;
			$("#thch").prop("checked",true);
		}
	}
}

//某个复选框被点击后，判断全选/反选情况 (弹出层左侧的复选框按钮)
function left_box_sel(obj){
	if(!obj.checked){
		web_checked = false;
		$("#left_sel").prop("checked",false);
	}else{
		if($("input[name='l_boxs']:checked").length==$("input[name='l_boxs']").length-1){
			checked = true;
			$("#left_sel").prop("checked",true);
		}
	}
}

//某个复选框被点击后，判断全选/反选情况 (弹出层右侧的复选框按钮)
function right_box_sel(obj){
	if(!obj.checked){
		web_checked = false;
		$("#right_sel").prop("checked",false);
	}else{
		if($("input[name='r_boxs']:checked").length==$("input[name='r_boxs']").length-1){
			checked = true;
			$("#right_sel").prop("checked",true);
		}
	}
}

//树形结构用的的节点类
function TreeNode(val,depth,children_lg,father_node){
	this.val = val;                   //节点value
	this.is_show = true;              //节点是否显示   
	this.is_al_show = true;           //节点是否需要显示,如果为false则永远不显示
	this.is_open = false;             //节点是打开还是关闭状态
	this.depth = depth;               //节点所在的层级, 0为第一层,1为第二层
	this.i_have_ajax = false;         //是否已经进行过ajax请求
	this.children_lg = children_lg;   //子节点数量
	if(children_lg>0){               
		this.imgsrc = "${ctx}/image/is-tbarr01.png"; //如果该节点有子节点，则该节点前面有箭头图标
	}else{
		this.imgsrc = "";
	}
	this.children = [];     //存放子节点的数组
	
	this.father_node = father_node;  //父节点
	
	this.binding = "";      //绑定字段
	this.bindingnum = "";   //绑定数量
}

//checkbox 一栏用到的树形结构组件
var ic_tree_dv_checkbox = {
		template: '<div>'+
		           '<div v-show="model.is_show&&model.is_al_show" v-bind:style="t_style">'+
		           '<input type="checkbox" v-bind:disabled="model.children_lg>0" name="dch"  v-bind:value="model.val.classification_ID"  onclick="checka(this)" v-model="checkedNames" class="cy_CMICBMS_checkbox"><label for="cy_CMICBMS_checkbox"></label></div>'+
		          '<ic_tree_dv_checkbox  v-for="(model,index) in model.children" v-bind:key="index" v-bind:model="model"></ic_tree_dv_checkbox>'+
		          '</div>',
		props: ['model'],
		data:function(){
			return{
				t_style : {padding: '10px 0px !important'} 
			}
		}
};

//名称 一栏用到的树形结构组件
var ic_tree_dv_name = {
		template: '<div >'+
		           '<div v-show="model.is_show&&model.is_al_show" v-bind:title="model.val.classificationName" class="cy_tree_node_hover" v-bind:style="t_style">'+
		           '<img v-if="model.imgsrc" v-bind:src="model.imgsrc" v-on:click="opClose(model.val.classification_ID)" style="cursor:pointer">'+
		           '{{model.val.classificationName}}</div>'+
		          '<ic_tree_dv_name  v-for="model in model.children"  v-bind:model="model"></ic_tree_dv_name>'+
		          '</div>',
		props: ['model'],
		data:function(){
			return{
				t_style : {padding: '10px 0px !important'} 
			}
		},
		methods:{
			opClose:function(id){
				this.$parent.opClose(id);
			}
		},
		mounted:function(){
			this.t_style = {padding: '10px '+(26+50*this.model.depth)+'px !important'} ;
		}
};

//是否绑定 一栏用到的树形结构组件
var ic_tree_dv_binding = {
		template: '<div >'+
		           '<div v-show="model.is_show&&model.is_al_show" v-bind:style="t_style">'+
		           '{{model.binding}}&nbsp;</div>'+
		          '<ic_tree_dv_binding  v-for="model in model.children"  v-bind:model="model"></ic_tree_dv_binding>'+
		          '</div>',
		props: ['model'],
		data:function(){
			return {
				t_style : {padding: '11px 0px !important'} 
			}
		}
};

//绑定数量 一栏用到的树形结构组件
var ic_tree_dv_num = {
		template: '<div >'+
		           '<div v-show="model.is_show&&model.is_al_show" v-bind:style="t_style">'+
		           '{{model.bindingnum}}&nbsp;</div>'+
		          '<ic_tree_dv_num  v-for="model in model.children" v-bind:model="model"></ic_tree_dv_num>'+
		          '</div>',
		props: ['model'],
		data:function(){
			return {
				t_style : {padding: '11px 0px !important'} 
			}
		}
};

//updatetime 一栏用到的树形结构组件
var ic_tree_dv_updatetime = {
		template: '<div >'+
		           '<div v-show="model.is_show&&model.is_al_show" v-bind:style="t_style">'+
		           '{{model.val.updateDateTime}}&nbsp;</div>'+
		          '<ic_tree_dv_updatetime  v-for="model in model.children"  v-bind:model="model"></ic_tree_dv_updatetime>'+
		          '</div>',
		props: ['model'],
		data:function(){
			return {
				t_style : {padding: '11px 0px !important'} 
			}
		}
};

//updater 一栏用到的树形结构组件
var ic_tree_dv_updater = {
		template: '<div >'+
		           '<div v-show="model.is_show&&model.is_al_show" v-bind:style="t_style">'+
		           '{{model.val.updateUser}}&nbsp;</div>'+
		          '<ic_tree_dv_updater  v-for="model in model.children"  v-bind:model="model"></ic_tree_dv_updater>'+
		          '</div>',
		props: ['model'],
		data:function(){
			return {
				t_style : {padding: '10px 0px !important'} 
			}
		}
};

//Vue.config.devtools = true;

/*注册页面需要用到的相关组件*/
Vue.component('ic_tree_dv_checkbox',ic_tree_dv_checkbox);
Vue.component('ic_tree_dv_name',ic_tree_dv_name);
Vue.component('ic_tree_dv_binding',ic_tree_dv_binding);
Vue.component('ic_tree_dv_num',ic_tree_dv_num);
Vue.component('ic_tree_dv_updatetime',ic_tree_dv_updatetime);
Vue.component('ic_tree_dv_updater',ic_tree_dv_updater);

var checked = false;      //复选框相关
var checkedNames = [];    //复选框相关
var checkedArr = [];    //复选框相关

var web_checked = false;    //弹出层左侧复选框相关
var web_checkedNames = [];  //弹出层左侧复选框相关
var web_checkedArr = [];    //弹出层左侧复选框相关

var web_al_checked = false;  //弹出层右侧复选框相关
var web_al_checkedNames = [];  //弹出层右侧复选框相关
var web_al_checkedArr = [];    //弹出层右侧复选框相关

var app = new Vue({
	
	el:"#app",
	data:{
		info:Info.I0024,
		isfirstinjsp:true,   //是否第一次进入页面
		search_key:'',       //查询条件
		isbinding:'',        //查询条件--是否绑定信息源
		datas:[],            //查询所得的数据集合
		isshowtcc:false,    //是否显示弹出层
		ssfl_msg:'',//'所属分类：上下游企业 > > 上游企业 > > 松下2'
		
		webinfo_datas:[],     //所有的网站信息
		webinfo_al_datas:[],  //已绑定网站信息
		search_key_webinfo:''  //弹出层查询网站信息条件
	},
	components:{
	     'pager':ic_pager,          //分页组件
	     'left_pager':ic_l_pager,   //弹出层左侧分页组件
	     'right_pager':ic_l_pager   //弹出层右侧分页组件
	  },
	methods:{
		left_sel:function(){         //弹出层左侧复选框按钮选择后相关处理
			if(web_checked){
				$("input[name='l_boxs']").prop("checked",false);
				web_checkedNames = [];
			}else{
				$("input[name='l_boxs']").prop("checked",true);
				web_checkedNames = web_checkedArr;
			}
		},
		right_sel:function(){      //弹出层右侧复选框按钮选择后相关处理
			if(web_al_checked){
				$("input[name='r_boxs']").prop("checked",false);
				web_al_checkedNames = [];
			}else{
				$("input[name='r_boxs']").prop("checked",true);
				web_al_checkedNames = web_al_checkedArr;
			}
		},
		btn_search:function(){      //查询按钮
			  checkedNames = [];
			  this.$refs.pagecomponent.pageBean.pageNow = 1;
			  this.search(this.$refs.pagecomponent.pageBean);
		},
		dealDigui:function(node,flag){    //递归打开/关闭node树形节点及其子节点
			  if(node.children_lg>0){
				  if(flag){           //打开
					  if(node.is_open){
						  for(var i=0;i<node.children.length;i++){
					    	  if(node.children[i].val.parent_Classification_ID==node.val.classification_ID){
					    		  node.children[i].is_show = flag;
					    		  this.dealDigui(node.children[i],flag);
					    	  }  
					      }
					  }
				  }else{   //关闭
					  for(var i=0;i<node.children.length;i++){
				    	  if(node.children[i].val.parent_Classification_ID==node.val.classification_ID){
				    		  node.children[i].is_show = flag;
				    		  this.dealDigui(node.children[i],flag);
				    	  }  
				      }
				  }
			  }
		  },
	    dealopClose:function(id,search_key,isbinding){  //递归打开ID为id的node节点及其子节点
	    	var click_node = this.getClickNode(id,this.datas)[1];
	    	if(!click_node.is_open){                      //打开
		        if(!click_node.i_have_ajax){                         //还未ajax请求
				    var _this = this;
		        	axios.get('../classificationSource/getchild',{
		    			params: {
		    				  Classification_ID:id
		    				}
		    			})
		    			.then(function (response) {
		    				var data = JSON.parse(response.data);
		    				for(var i=0;i<data.length;i++){
		    					 var tn = new TreeNode(data[i],click_node.depth+1,data[i].children_lg,click_node);
		    					 if(data[i].children_lg==0){
		    						 if(data[i].binding_lg==0){
		    							 tn.binding = "否";
		    						 }else{
		    							 tn.binding = "是";
		    						 }
		    						 tn.bindingnum = data[i].binding_lg;
		    					 }
		    					
		    					 click_node.children.push(tn);
		    				  }
		    				 click_node.i_have_ajax = true;
		    				 for(var i=0;i<data.length;i++){
		    					  checkedArr.push(data[i].classification_ID);
		    				  }
		    				 if(checked){
		    					 $("input[name='dch']").prop("checked",true);
		    					 checkedNames = checkedArr;
		    				 }

		    				 if(click_node.children.length>0){   //如果click_node具有子节点,则进一步打开它的所有子节点
		    					
			    			     click_node.is_open = true;
			    				  
			    			     click_node.imgsrc = "${ctx}/image/is-tbarr02.png";
			    			     
			    			     for(var i=0;i<click_node.children.length;i++){   //根据查询条件判断click_node的子节点是否永远不显示
			    			    	 var b = true;
			    			         /*以下是判断click_node的子节点是否在查询条件内*/
			    			    	 if(click_node.children[i].val.classificationName.indexOf(search_key)<0){
			    			    		 b = false;
			    					 }
			    			    	 if(isbinding=='1'){
			    			    		 if(click_node.children[i].bindingnum==0){
			    			    			 b = false;
			    			    		 }
			    			    	 }
			    			    	 if(isbinding=='2'){
			    			    		 if(click_node.children[i].bindingnum>0){
			    			    			 b = false;
			    			    		 }
			    			    	 }
			    			    	 /*以上是判断click_node的子节点是否在查询条件内*/
			    			    	 
			    			    	click_node.children[i].is_al_show = b;  //如果该click_node的子节点不在查询条件内，则永远不显示
			    			    	 
		    						 _this.dealopClose(click_node.children[i].val.classification_ID,search_key,isbinding);  //递归
		    						 
		    					 }
			    			     /*以下是判断该click_node前面的箭头图标是否需要显示*/
			    			     var cnt = click_node.children.length;
			    			     for(var i=0;i<click_node.children.length;i++){ 
			    			    	 if(!click_node.children[i].is_al_show){
			    			    		 cnt--
			    			    	 }
			    			     }
			    			     if(cnt==0){                     //如果该click_node的子节点全都不需要显示,则该click_node前面的箭头图标也不需要显示
			    			    	 click_node.imgsrc = "";
			    			     }else{
			    			    	 _this.dealNodeImg(click_node);  //否则该click_node以及其所有的父节点前面的箭头图标都需要显示
			    			     }
			    			     /*以上是判断该click_node前面的箭头图标是否需要显示*/
		    				 }
		    			})
		    			.catch(function (error) {
		    			    console.log(error);
		    			});
		        }
			}
	    },
	    dealNodeImg:function(node){      //设置 node以及其所有的父节点前面的箭头图标显示
	    	node.imgsrc = "${ctx}/image/is-tbarr02.png";
	    	if(node.father_node!=null){
	    		this.dealNodeImg(node.father_node);
	    	}
	    },
		opClose:function(id){   //打开/关闭 ID为id的节点
			var click_node = this.getClickNode(id,this.datas)[1];
			if(!click_node.is_open){                      //打开
		        if(!click_node.i_have_ajax){                         //还未ajax请求
				    var _this = this;
		        	axios.get('../classificationSource/getchild',{
		    			params: {
		    				  Classification_ID:id
		    				}
		    			})
		    			.then(function (response) {
		    				var data = JSON.parse(response.data);
		    				for(var i=0;i<data.length;i++){
		    					 var tn = new TreeNode(data[i],click_node.depth+1,data[i].children_lg,click_node);
		    					 if(data[i].children_lg==0){
		    						 if(data[i].binding_lg==0){
		    							 tn.binding = "否";
		    						 }else{
		    							 tn.binding = "是";
		    						 }
		    						 tn.bindingnum = data[i].binding_lg;
		    					 }
		    					 click_node.children.push(tn);
		    				  }
		    				click_node.i_have_ajax = true;
		    				 for(var i=0;i<data.length;i++){
		    					  checkedArr.push(data[i].classification_ID);
		    				  }
		    				 if(checked){
		    					 $("input[name='dch']").prop("checked",true);
		    					 checkedNames = checkedArr;
		    				 }
		    				
		    			})
		    			.catch(function (error) {
		    			    console.log(error);
		    			});
		        }
	  
		        click_node.is_open = true;
	  
		        click_node.imgsrc = "${ctx}/image/is-tbarr02.png";
	      
		        this.dealDigui(click_node,true);
			}else{         //关上
				  
				  click_node.is_open = false;
				  
				  click_node.imgsrc = "${ctx}/image/is-tbarr01.png";
				  
				  this.dealDigui(click_node,false);
				  
			  }
		},
		getClickNode:function(id,arr){  //获得ID为id的节点信息类
			  
			  var node = null;
			  var b = true;
			  for(var i=0;i<arr.length;i++){
				  if(arr[i].val.classification_ID==id){
					  node = [];
					  node[0] = i;
					  node[1] = arr[i];
					  b = false;
					  break;
				  }
			  }
			  if(b){
				  for(var i=0;i<arr.length;i++){
					  if(arr[i].children.length>0){
					     node = this.getClickNode(id,arr[i].children);
					     if(node!=null){
					    	 return node;
					     }
					  }
				  }
			  }
			  return node;
			  
		  },
		hide:function(){                 //关闭弹出层
		      this.isshowtcc = false;
		},
		getAllNodeFromANode:function(node,arr){     //获得
			if(node.val.parent_Classification_ID!='0000000000'){
				 var parent_node = this.getClickNode(node.val.parent_Classification_ID,this.datas)[1];
				 arr.push(parent_node);
				 this.getAllNodeFromANode(parent_node,arr);
			}
		},
		EditData:function(){              //弹出编辑分类弹出层
			
			if(checkedNames.length!=1){
				layer.msg(Info.E0006);
			}else{
				$(".cy_hidebg").css("height",($(document).height()+document.body.scrollHeight));
				this.ssfl_msg = '所属分类：';
				
				var clicknode = this.getClickNode(checkedNames[0],this.datas)[1];
				
				if(clicknode.children_lg==0){
					
					this.isshowtcc = true;
					
					var ssflarr = [];
					ssflarr.push(clicknode);
					this.getAllNodeFromANode(clicknode,ssflarr);

					for(var i=ssflarr.length-1;i>=0;i--){
						if(i==ssflarr.length-1){
							this.ssfl_msg += ssflarr[i].val.classificationName;
						}else{
							this.ssfl_msg += " > > "+ssflarr[i].val.classificationName;
						}
					}

					this.search_left(this.$refs.left_pagecomponent.pageBean);        //查询左边的网站信息
					this.search_right(this.$refs.right_pagecomponent.pageBean);      //查询右边的网站信息
					
				}else{
					layer.msg(Info.E0024);
				}
			}
		},
		btn_web_search:function(){    //弹出层左侧的网站查询
			this.$refs.left_pagecomponent.pageBean.pageNow = 1;
			this.search_left(this.$refs.left_pagecomponent.pageBean);
		},
		joingleft:function(){       //弹出层右侧侧的数据加入到左侧
			if(web_al_checkedNames.length>0){
				 var json = createJSON(web_al_checkedNames);   //createJSON 引自 js/comm.js
				 var l_index = layer.msg(Info.I0011, {
	        		  icon: 16
	        		  ,shade: 0.01,
	        		  time:false
	        		});
			    var _this = this;
			    axios.post('../classificationSource/joingleft',
						 
						 {
		    				Classification_ID:checkedNames[0],
		    				json:json
		    			}
				       ).then(function (response) {
		    				
		    				 layer.close(l_index);
		    				 _this.search_left(_this.$refs.left_pagecomponent.pageBean);
		    				 if(web_al_checkedNames.length==web_al_checkedArr.length){
		    					 if(_this.$refs.right_pagecomponent.pageBean.pageNow!=1){
		    						 _this.$refs.right_pagecomponent.pageBean.pageNow-=1;
		    					 }
		    				 }
		    				 _this.search_right(_this.$refs.right_pagecomponent.pageBean);
		    			})
		    			.catch(function (error) {
		    			    console.log(error);
		    			    layer.close(l_index);
		    			});
			}else{
				layer.msg(Info.E0039);
			}
		},
		joingright:function(){   //弹出层左侧侧的数据加入到右侧
		    if(web_checkedNames.length>0){
			    var json = createJSON(web_checkedNames);   //createJSON 引自 js/comm.js
			    var l_index = layer.msg(Info.I0011, {
	        		  icon: 16
	        		  ,shade: 0.01,
	        		  time:false
	        		});
			    var _this = this;
				 axios.post('../classificationSource/joingright',
						 
						 {
		    				Classification_ID:checkedNames[0],
		    				json:json
		    			}
				       ).then(function (response) {
		    				
		    				 layer.close(l_index);
		    				 if(web_checkedNames.length==web_checkedArr.length){
		    					 if(_this.$refs.left_pagecomponent.pageBean.pageNow!=1){
		    						 _this.$refs.left_pagecomponent.pageBean.pageNow-=1;
		    					 }
		    				 }
		    				 _this.search_left(_this.$refs.left_pagecomponent.pageBean);
		    				 _this.search_right(_this.$refs.right_pagecomponent.pageBean);
		    			})
		    			.catch(function (error) {
		    			    console.log(error);
		    			    layer.close(l_index);
		    			});
		    }else{
				layer.msg(Info.E0039);
			}
		},
		confim:function(){             ////弹出层确认按钮
			var _this = this;
			axios.get('../classificationSource/updatesource',{
    			params: {
    				Classification_ID:checkedNames[0]
    				}
    			})
    			.then(function (response) {
    				 _this.search(_this.$refs.pagecomponent.pageBean);
    				 _this.hide();
    			})
    			.catch(function (error) {
    			    console.log(error);
    			});
		},
		  changeAllChecked:function(){     //全选/反选复选框
				if(checked){
					$("input[name='dch']").prop("checked",false);
					checkedNames = [];
				}else{
					$("input[name='dch']").prop("checked",true);
					checkedNames = checkedArr;
				}
		  }
		
	},
	computed:{
		search_right:function(){                  //弹出层右侧数据查询
			return function(pageBean){
				var l_index = layer.msg(Info.I0011, {
	        		  icon: 16
	        		  ,shade: 0.01,
	        		  time:false
	        		});
				web_al_checked = false;
				web_al_checkedNames = [];
				var _this = this;
				 axios.get('../classificationSource/searchalweb',{
		    			params: {
		    				Classification_ID:checkedNames[0],
		    				pageNow:pageBean.pageNow,
		    				rowSize:pageBean.rs_selected
		    				}
		    			})
		    			.then(function (response) {

		    				 var data = JSON.parse(response.data);
	    				   _this.webinfo_al_datas = data.resdata;
	    				   web_al_checkedArr = [];
	    				   for(var i=0;i<data.resdata.length;i++){
	    					 web_al_checkedArr.push(data.resdata[i].website_ID);
	    				   }
	    				 
	    				 _this.$refs.right_pagecomponent.dealAfterSearch(data.rowCount); 

		    		      layer.close(l_index);
		    				  
		    			})
		    			.catch(function (error) {
		    			    console.log(error);
		    			    layer.close(l_index);
		    			});
			}
		},
		search_left:function(){             //弹出层左侧数据查询
			return function(pageBean){
				 var l_index = layer.msg(Info.I0011, {
	        		  icon: 16
	        		  ,shade: 0.01,
	        		  time:false
	        		});
				 web_checked = false;
				 web_checkedNames = [];
				 var _this = this;
				 
				 axios.get('../classificationSource/searchweb',{
		    			params: {
		    				WebsiteName:_this.search_key_webinfo.trim(),
		    				Classification_ID:checkedNames[0],
		    				pageNow:pageBean.pageNow,
		    				rowSize:pageBean.rs_selected
		    				}
		    			})
		    			.then(function (response) {

		    				 var data = JSON.parse(response.data);
		    				
		    				 _this.webinfo_datas = data.resdata;
		    			     
		    				 web_checkedArr = [];
		    				 for(var i=0;i<data.resdata.length;i++){
		    					 web_checkedArr.push(data.resdata[i].website_ID);
		    				 }
		    				 
		    				 _this.$refs.left_pagecomponent.dealAfterSearch(data.rowCount); 
		    				 
		    				  layer.close(l_index);
		    				  
		    			})
		    			.catch(function (error) {
		    			    console.log(error);
		    			    layer.close(l_index);
		    			});
				 
			}
		},
		search:function(){                                  //页面信息查询
			 return function(pageBean){
				  var l_index = layer.msg(Info.I0011, {
	        		  icon: 16
	        		  ,shade: 0.01,
	        		  time:false
	        		});
	        	
	        	var _this = this;
	        	axios.get('../classificationSource/search',{
	    			params: {
	    				ClassificationName:_this.search_key.trim(),
	    				isbinding:_this.isbinding,
	    				Parent_Classification_ID:_this.father_node_id,
	    				pageNow:pageBean.pageNow,
	    				rowSize:pageBean.rs_selected
	    				}
	    			})
	    			.then(function (response) {
	    				
	    				 checkedNames = [];
	    				 _this.datas = [];
	    				 var data = JSON.parse(response.data);
	    				 if(data.rowCount>parseInt(Info.MaxSearchCnt)&&!_this.isfirstinjsp){
                        	 layer.msg(Info.W0001);
                        	_this.$refs.pagecomponent.dealAfterSearch(0);  //查询完成后回调分页组件的函数,处理分页组件的相关参数
                         }else{
		    				 
		    				 var temp = data.resdata;
		    				 for(var i=0;i<temp.length;i++){                              //查询到的数据存放到this.datas集合中
		    					 var tn = new TreeNode(temp[i],0,temp[i].children_lg,null);  
		    					 if(temp[i].children_lg==0){
		    						 if(temp[i].binding_lg==0){
		    							 tn.binding = "否";
		    						 }else{
		    							 tn.binding = "是";
		    						 }
		    						 tn.bindingnum = temp[i].binding_lg;
		    					 }
		    					 checked = false;
		    					 _this.datas.push(tn);
		    				 }
		    				
		    				 checkedArr = [];
		    
		    				  for(var i=0;i<data.resdata.length;i++){          //将查询结果放入到checkedArr用于全选/反选的判断
		    					  checkedArr.push(data.resdata[i].classification_ID);
		    				  }
		    			
		    				  if(data.rowCount=='0'&&!_this.isfirstinjsp){
		    					 layer.msg(Info.I0002);
		    				  }
		    				  
		    				 _this.$refs.pagecomponent.dealAfterSearch(data.rowCount); 
		    				
		    				 if(_this.search_key.trim()!=''||_this.isbinding!=''){    //如果查询词不为空，则递归打开所有节点
		    					 for(var i=0;i<_this.datas.length;i++){
		    						 _this.dealopClose(_this.datas[i].val.classification_ID,_this.search_key.trim(),_this.isbinding);
		    					 }
		    				 }
                         }
	    				 _this.isfirstinjsp = false;
	    				  layer.close(l_index);
	    				  
	    			})
	    			.catch(function (error) {
	    			    console.log(error);
	    			    layer.close(l_index);
	    			});
			 }
		}
	},
  watch: {
		checkedNames: function() {
			if (checkedNames.length != checkedArr.length||checkedNames.length==0) {
				checked = false;
			} else {
				checked = true;
			}
		}
	 },
	mounted:function(){     //加载后执行
		 this.search(this.$refs.pagecomponent.pageBean);  
		
	}
	
	
});

</script>

</body>

</html>
