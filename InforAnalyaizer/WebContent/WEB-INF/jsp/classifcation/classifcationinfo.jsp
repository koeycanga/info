<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="ss" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources" var="sysInfo" />  <!-- basename: 在classes文件夹下properties文件的文件名 -->
<fmt:message key="I0002" var="I0002" bundle="${sysInfo}" />
<fmt:message key="I0005" var="I0005" bundle="${sysInfo}" />
<fmt:message key="I0006" var="I0006" bundle="${sysInfo}" />
<fmt:message key="I0007" var="I0007" bundle="${sysInfo}" />
<fmt:message key="I0008" var="I0008" bundle="${sysInfo}" />
<fmt:message key="I0009" var="I0009" bundle="${sysInfo}" />
<fmt:message key="I0010" var="I0010" bundle="${sysInfo}" />
<fmt:message key="I0011" var="I0011" bundle="${sysInfo}" />
<fmt:message key="E0006" var="E0006" bundle="${sysInfo}" />
<fmt:message key="E0019" var="E0019" bundle="${sysInfo}" />
<fmt:message key="E0020" var="E0020" bundle="${sysInfo}" />
<fmt:message key="E0021" var="E0021" bundle="${sysInfo}" />
<fmt:message key="E0022" var="E0022" bundle="${sysInfo}" />
<fmt:message key="E0023" var="E0023" bundle="${sysInfo}" />
<fmt:message key="W0002" var="W0002" bundle="${sysInfo}" />
<fmt:message key="W0004" var="W0004" bundle="${sysInfo}" />
<fmt:message key="E0038" var="E0038" bundle="${sysInfo}" />
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
<link rel="stylesheet" type="text/css" href="${ctx}/css/cy_zg_win.css">
 
</head>
<body >
<div id="app" class="cy_CMICBMS_bodybg" v-cloak >
		<p><b>情报规划</b> > <span><b>分类体系</b></span></p>
		<div class="cy_CMICBMS_tablebox">
		<div class="cy_CMICBMS_box07">
			<div class="cy_CMICBMS_schbox01">
				<input type="text" v-model="search_key" placeholder="请输入分类名称检索">
			</div>
			<input type="button" class="cy_CMICBMS_schbtn" v-on:click="btn_search()" value="检索">
		</div>
		<div class="cy_CMICBMS_box08">
			<input type="button" v-on:click="NewData()" class="cy_CMICBMS_addbtn" value="新增">
			<input type="button" v-on:click="EditData()" class="cy_CMICBMS_edbtn" value="编辑">
			<input type="button" v-on:click="MoveUP()" v-bind:disabled="btn_wy_disabled"  class="cy_CMICBMS_mvbtn" value="上移">
			<input type="button" v-on:click="MoveDown()" v-bind:disabled="btn_wy_disabled" class="cy_CMICBMS_mvbtn" value="下移">
			<input type="button" v-on:click="DelData()" class="cy_CMICBMS_dltbtn" value="删除">
		</div>
		<div class="cy_CMICBMS_box08">
			<table width="100%" border="0" class="cy_CMICBMS_accmngtb" 	cellspacing="0">
			  <tbody>
			    <tr>
			      <th scope="col" width="25px"><input type="checkbox" name="dch" id="thch" v-model="checked" v-on:click="changeAllChecked()" class="cy_CMICBMS_checkbox"><label for="cy_CMICBMS_checkbox"></label></th>
			      <th scope="col" width="40%">分类名称</th>
			      <th scope="col" width="30%">备注</th>
			      <th scope="col" width="15%">创建时间</th>
			      <th scope="col">创建人</th>
			    </tr>
			    <tr v-for="(data,index) in datas" class="cy_CMICBMS_treven">
			      <td>
			          <ic_tree_dv_checkbox v-bind:model="data.json_classification_ID"></ic_tree_dv_checkbox>
			      </td>
			      <td>
			          <div class="cy_tree_node"> 
			            <ic_tree_dv v-bind:model="data.json_classificationName"></ic_tree_dv>
			          </div>  
			      </td>
			      <td nowrap>
			           <ic_tree_dv v-bind:model="data.json_description"></ic_tree_dv>
			      </td>
			      <td nowrap>
			          <ic_tree_dv v-bind:model="data.json_createDateTime"></ic_tree_dv>
			       </td>
			      <td>
			          <ic_tree_dv v-bind:model="data.json_createUser"></ic_tree_dv>
			       </td>
			    </tr>
			     <tr>
					<td v-if="datas.length==0" colspan="5">{{info}}</td>
				</tr>
			  </tbody>
			</table>
		</div>
		
		
	<pager ref="pagecomponent"></pager>  <!-- ref 固定为 pagecomponent  -->
    
    <div class="cy_hidebg" v-bind:style="showtcc" id="cy_hidebg"></div> <!-- 遮罩层 -->
    <!--新增按钮弹窗-->
	<div  v-bind:style="showtcc" id="cy_CMICBMS_add" style="height:310px;">
		<div class="cy_CMICBMS_addtop"><div class="cy_CMICBMS_addtit">{{tcc_title}}</div><div class="cy_CMICBMS_addclose" v-on:click="hideTcc()">X</div></div>
		<div class="cy_CMICBMS_addtb">
		
			<div v-if="checkedNames.length==1&&isnew" style="width: 95%;text-align: left;">&nbsp;&nbsp;&nbsp;&nbsp;父节点：{{father_node_name}}</div>
			
			<div class="cy_CMICBMS_addtbinp">
			    <span>*</span>分类名称：<input type="text" maxlength="20" placeholder="输入分类名称" v-on:blur="checkTypeName()" v-model="typeInfo.type_name" class="cy_CMICBMS_addinput">
			    <div v-if="errInfo.type_name_err" class="cy_CMICBMS_errortip">请输入分类名称！</div>
			</div>
			<div style="width:470px;height:100px;margin:0px 0 0 0px;vertical-align: text-top;">
				<div style="height:100px;float: left;">备注：</div>
				<div style="width:398px;height:100px;float: left;">
				  <textarea v-model="typeInfo.type_des" placeholder="  输入备注"  maxlength="50" style="width:100%;height:100%;resize:none;background-color: white !important;border: 1px solid #cccccc;background-color: #fbfbfb;border-radius: 5px;"></textarea>
				 </div>
			</div>
			
			<div class="cy_CMICBMS_addtbinp" v-if="isnew" style="align-self:flex-end;margin:15px 20px 0 0;"><input type="button" v-on:click="SaveNewData()" value="确定" class="cy_CMICBMS_schbtn"></div>
	        <div class="cy_CMICBMS_addtbinp" v-else style="align-self:flex-end;margin:15px 20px 0 0;"><input type="button" v-on:click="UpdateData()" value="确定" class="cy_CMICBMS_schbtn"></div>
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

AdaptationResolution('${ctx}');//分辨率适配

$(document).ready(function(){
	
	/*alert($(document).height());
	alert($(window).height());
	alert($(document.body).outerHeight(true));
	alert(document.body.scrollHeight);*/
	$(".cy_hidebg").css("height",($(document).height()+document.body.scrollHeight));
});


//提示信息集合  来源于 classes/resources.properties
var Info = {
		I0002:'${I0002}',
		I0005:'${I0005}',
		E0006:'${E0006}',
		I0006:'${I0006}',
		I0007:'${I0007}',
		I0009:'${I0009}',
		I0010:'${I0010}',
		W0002:'${W0002}',
		E0019:'${E0019}',
		E0020:'${E0020}',
		E0021:'${E0021}',
		E0022:'${E0022}',
		E0023:'${E0023}',
		I0008:'${I0008}',
		I0011:'${I0011}',
		W0004:'${W0004}',
		E0038:'${E0038}',
		I0024:'${I0024}',
		MaxSearchCnt:'${MaxSearchCnt}',
		W0001:'${W0001}'
	};

//树形结构用到的节点类
function TreeNode(val,depth,children_lg,tree_id,father_node){
	this.tree_id = tree_id;            //节点ID
	this.val = val;                    //节点value
	this.is_show = true;               //节点是否显示
	this.is_open = false;              //节点是否打开
	this.depth = depth;                //节点所在的层级  第一层为0  第二层为1....
	this.i_have_ajax = false;          //是否已经进行了ajax请求
	this.children_lg = children_lg;    //子节点的数量
	if(children_lg>0){               
		this.imgsrc = "${ctx}/image/is-tbarr01.png";     //如果该节点有子节点，则该节点前面有箭头图标
	}else{
		this.imgsrc = "";
	}
	this.children = [];                 //存放子节点的数组
	this.father_node = father_node;   //父节点
	this.json_classification_ID = [];   //存放classification_ID的JSON数组
	this.json_classificationName = [];  //存放classificationName的JSON数组
	this.json_description = [];         //存放description的JSON数组
	this.json_createDateTime = [];      //存放createDateTime的JSON数组
	this.json_createUser= [];           //存放createUser的JSON数组
}


//生成树形结构 classification_ID一栏 所需的JSON字面量
function getJSONclassification_ID(tn){
	   var res = '{"name":"'+tn.val.classification_ID+'","is_show":'+tn.is_show+',"is_open":'+tn.is_open+',"depth":"'+tn.depth+'","i_have_ajax":"'+tn.i_have_ajax+'","children_lg":"'+tn.children_lg+'","imgsrc":"'+tn.imgsrc+'"';
	   if(tn.children.length>0){
		   res+=',"children":[';
		   for(var i=0;i<tn.children.length;i++){
			   if(i==0){
			   		res+=getJSONclassification_ID(tn.children[i]);
			   }else{
				    res+=","+getJSONclassification_ID(tn.children[i]);
			   }
		   }
		   res+=']';
	   }
	   res+='}';
	   return res;
	}

//生成树形结构 classificationName一栏 所需的JSON字面量
function getJSONclassificationName(tn){
   var res = '{"name":"'+tn.val.classificationName+'","style":true,"id":"'+tn.val.classification_ID+'","tree_id":"'+tn.tree_id+'","is_show":'+tn.is_show+',"is_open":'+tn.is_open+',"depth":"'+tn.depth+'","i_have_ajax":"'+tn.i_have_ajax+'","children_lg":"'+tn.children_lg+'","imgsrc":"'+tn.imgsrc+'"';
   if(tn.children.length>0){
	   res+=',"children":[';
	   for(var i=0;i<tn.children.length;i++){
		   if(i==0){
		      res+=getJSONclassificationName(tn.children[i]);
		   }else{
			   res+=","+getJSONclassificationName(tn.children[i]);
		   }
	   }
	   res+=']';
   }
   res+='}';
   return res;
}

//生成树形结构 description一栏 所需的JSON字面量
function getJSONdescription(tn){
   var res = '{"name":"'+tn.val.description+'","is_show":'+tn.is_show+',"is_open":'+tn.is_open+',"depth":"'+tn.depth+'","i_have_ajax":"'+tn.i_have_ajax+'","children_lg":"'+tn.children_lg+'","imgsrc":""';
   if(tn.children.length>0){
	   res+=',"children":[';
	   for(var i=0;i<tn.children.length;i++){
		   if(i==0){
		   		res+=getJSONdescription(tn.children[i]);
		   }else{
			   res+=","+getJSONdescription(tn.children[i]);
		   }
	   }
	   res+=']';
   }
   res+='}';
   return res;
}

//生成树形结构 createDateTime一栏 所需的JSON字面量
function getJSONcreateDateTime(tn){
   var res = '{"name":"'+tn.val.createDateTime+'","is_show":'+tn.is_show+',"is_open":'+tn.is_open+',"depth":"'+tn.depth+'","i_have_ajax":"'+tn.i_have_ajax+'","children_lg":"'+tn.children_lg+'","imgsrc":""';
   if(tn.children.length>0){
	   res+=',"children":[';
	   for(var i=0;i<tn.children.length;i++){
		   if(i==0){
		   		res+=getJSONcreateDateTime(tn.children[i]);
		   }else{
			   res+=","+getJSONcreateDateTime(tn.children[i]);
		   }
	   }
	   res+=']';
   }
   res+='}';
   return res;
}
	
//生成树形结构 createUser一栏 所需的JSON字面量	
function getJSONcreateUser(tn){
   var res = '{"name":"'+tn.val.createUser+'","is_show":'+tn.is_show+',"is_open":'+tn.is_open+',"depth":"'+tn.depth+'","i_have_ajax":"'+tn.i_have_ajax+'","children_lg":"'+tn.children_lg+'","imgsrc":""';
   if(tn.children.length>0){
	   res+=',"children":[';
	   for(var i=0;i<tn.children.length;i++){
		   if(i==0){
		   	   res+=getJSONcreateUser(tn.children[i]);
		   }else{
			   res+=","+getJSONcreateUser(tn.children[i]);
		   }
	   }
	   res+=']';
   }
   res+='}';
   return res;
}

//树形结构checkbox一栏用到的组件
var ic_tree_dv_checkbox = {
		template: '<div>'+
		           '<div v-show="model.is_show" v-bind:style="t_style">'+
		           '<input type="checkbox" name="dch"  v-bind:value="model.name" onclick="checka(this)"  v-model="checkedNames" class="cy_CMICBMS_checkbox"><label for="cy_CMICBMS_checkbox"></label></div>'+
		          '<ic_tree_dv_checkbox  v-for="(model,index) in model.children" v-bind:key="index" v-bind:model="model"></ic_tree_dv_checkbox>'+
		          '</div>',
		props: ['model','key'],
		data:function(){
			return{
				t_style : {padding: '10px 0px !important'} 
			}
		},
};

//树形结构classificationName  description createDateTime createUser栏位用到的组件
var ic_tree_dv = {
		template: '<div >'+
		           '<div v-show="model.is_show" v-bind:title="model.name" class="cy_tree_node_hover" v-bind:style="t_style">'+
		           '<img v-if="model.imgsrc" v-bind:src="model.imgsrc" v-on:click="opClose(model.id)" style="cursor:pointer">'+
		           '<span v-if="model.style"></span>{{model.name}}&nbsp;</div>'+
		          '<ic_tree_dv  v-for="(model,index) in model.children" v-bind:model="model"></ic_tree_dv>'+
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
			if(this.model.style){
				this.t_style = {padding: '10px '+(26+50*this.model.depth)+'px !important'} ;
			}
		}
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

//生成UUID
function uuid() {  
    var s = [];
    var hexDigits = "0123456789abcdef";
    for (var i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
    }
    s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
    s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
    s[8] = s[13] = s[18] = s[23] = "";
 
    var uuid = s.join("");
    return uuid;
}

var checked = false;      //复选框相关
var checkedNames = [];    //复选框相关
var checkedArr = [];    //复选框相关

var app = new Vue({
	el:"#app",
	data:{
		  isfirstinjsp:true,    //是否第一次进入页面
		  info:Info.I0024,
		  datas:[],                  //查询所得的数据集合
		  
		  search_key:'',             //查询条件
		
		  btn_wy_disabled:false,    //上移  下移按钮是否能点击
		  
		  //showtccer:'{display:block}',
		  showtcc:{display:'none'},   //是否显示弹出层
		  tcc_title : '',      //弹出层的标题
		  isdisabled:false,
		  isnew:true,         //是否是新增数据
		  
		  father_node_name:'',      //父节点名称
		  father_node_id:'' ,      //父节点ID
		  
		  tempNode:'',              //节点上移下移时 用于交换节点时保存节点的临时节点类          
		  
		  typeInfo:{                //用于存放新增/编辑 弹出层相关信息的类
			  type_id:'',
			  type_name:'',
			  type_des:'',
			  UpdateDateTime:''
		  },
		  errInfo:{                //用于存放新增/编辑 弹出层相关错误信息的类
			  type_name_err:false
		  },
	},
	components:{
	     'pager':ic_pager          //分页组件
	  },
	  methods:{
		  changeAllChecked:function(){             //全选/反选
				if(checked){
					$("input[name='dch']").prop("checked",false);
					checkedNames = [];
				}else{
					$("input[name='dch']").prop("checked",true);
					checkedNames = checkedArr;
				}
		  },
		  checkTypeName:function(){     //检测分类体系名称是否已输入
			    if(this.typeInfo.type_name.trim()==''){
			    	this.errInfo.type_name_err = true;
			    }else{
			    	this.errInfo.type_name_err = false;
			    }
		  },
		  MoveUP:function(){    //上移
			if(checkedNames.length==1){
				 var obj = this.getClickNode(checkedNames[0],this.datas);     //获得选中的节点
				 var i_index = obj[0];
			     var click_node = obj[1];
				if(click_node.val.parent_Classification_ID=='0000000000'){    //根节点  涉及到翻页
						if(i_index==0){                                       //如果已经到顶点
							if(this.$refs.pagecomponent.pageBean.pageNow==1){  //如果是第一页 则弹出提示 “已到最顶端”
								layer.msg(Info.I0005);
							}else{
								this.tempNode = this.datas[i_index];             //位移发生了翻页,pageNow-1 
								this.btn_wy_disabled = true;
								this.$refs.pagecomponent.pageBean.pageNow-=1;
								this.search(this.$refs.pagecomponent.pageBean,true);
							}
							
						}else{
							this.dealchange(i_index - 1,i_index,this.datas);    //位移没有发生分页,交换前后两节点的位置 
						}
				}else{ //非根节点  不涉及翻页
					var root_node = this.getRootNodeByid(click_node);  //获得根节点
					if(i_index==0){
						layer.msg(Info.I0005);       //已到最顶端
					}else{ 
						var parentNode = this.getClickNode(click_node.val.parent_Classification_ID,this.datas)[1];
						this.dealchange(i_index - 1,i_index,parentNode.children); //交换前后两节点的位置
						this.updateJsonData(root_node);      //更新root_node的显示对应的JSON
					}
			    }
			}else{
				layer.msg(Info.E0006);
			}
		  },
		  MoveDown:function(){  //下移
			  if(checkedNames.length==1){
				    var obj = this.getClickNode(checkedNames[0],this.datas);     //获得选中的节点
				    var i_index = obj[0];
					var click_node = obj[1];
				    if(click_node.val.parent_Classification_ID=='0000000000'){    //根节点  涉及到翻页
						if(i_index==this.datas.length-1){
							if(this.$refs.pagecomponent.pageBean.pageNow==this.$refs.pagecomponent.pageBean.pageSize){ //已到最末端
								layer.msg(Info.I0006);
							}else{
								this.tempNode = this.datas[i_index];               //位移发生了翻页, pageNow+1
								this.btn_wy_disabled = true;                          
								this.$refs.pagecomponent.pageBean.pageNow+=1;
								this.search(this.$refs.pagecomponent.pageBean,true);
							}
						}else{
							this.dealchange(i_index + 1,i_index,this.datas);   //位移没有发生翻页，交换两节点的位置
						}
				    }else{//非根节点
				    	
				    	var root_node = this.getRootNodeByid(click_node);  //获得根节点
				 
				    	var parent_node = this.getClickNode(click_node.val.parent_Classification_ID,this.datas)[1];
						if(i_index==parent_node.children.length-1){
							layer.msg(Info.I0006);
						}else{ 
						
							this.dealchange(i_index+1,i_index,parent_node.children); //交换前后两节点的位置
							
							this.updateJsonData(root_node);  //更新root_node的显示对应的JSON
						}
				    }
				  
			  }else{
				  layer.msg(Info.E0006);
			  }
		  },
		  dealchange:function(pre_index,i_index,target_arr){   //处理节点移动  交换pre_index和i_index 两节点的位置
			    /*以下是交换相邻两个节点的displayorder*/
				var temp_displayOrder = target_arr[pre_index].val.displayOrder;
				
				target_arr[pre_index].val.displayOrder = target_arr[i_index].val.displayOrder ;
				
				target_arr[i_index].val.displayOrder = temp_displayOrder;
				/*以上是交换相邻两个节点的displayorder*/
				
				this.btn_wy_disabled = true;   //在交换节点时上移下移按钮不能点击
				
				this.updateOrder(target_arr[i_index].val.classification_ID,target_arr[i_index].val.displayOrder,
						target_arr[pre_index].val.classification_ID,target_arr[pre_index].val.displayOrder,pre_index,i_index,target_arr);
		  },
		  updateOrder:function(cur_id,cur_order,ch_id,ch_order,ch_index,i_index,target_arr){  //更新节点的displayorder到数据库
			    var _this = this;
                
				axios.get('../classifcationinfo/updateorder',{
	    			params: {
	    				   cur_Classification_ID:cur_id,
	    				   cur_displayOrder:cur_order,
	    				   ch_Classification_ID:ch_id,
	    				   ch_displayOrder:ch_order
	    				}
	    			})
	    			.then(function (response) {
	
	    			})
	    			.catch(function (error) {
	    			    console.log(error);
	    			    _this.btn_wy_disabled = false;
	    			});
				
				if(ch_index>=0&&i_index>=0){
					
    				var temp = target_arr[ch_index];
    				target_arr[ch_index] = target_arr[i_index];
    				target_arr[i_index] = temp;
    				
				}
				
				this.btn_wy_disabled = false;
		  },
		  btn_search:function(){   //查询按钮相关的点击事件
			  
			  checkedNames = [];
			  this.$refs.pagecomponent.pageBean.pageNow = 1;
			  this.search(this.$refs.pagecomponent.pageBean,true);
		  },
		  EditData:function(){                  //弹出编辑层    
			 if(checkedNames.length!=1){
				 layer.msg(Info.E0006);
			 }else{
				 $(".cy_hidebg").css("height",($(document).height()+document.body.scrollHeight));
				 this.isnew = false;
				 this.tcc_title = Info.I0010;
				 $("#cy_CMICBMS_add").css("height","300px");
				 this.father_node_id = "";
				 var click_node = this.getClickNode(checkedNames[0],this.datas)[1];
				 this.typeInfo.type_id = click_node.val.classification_ID;
				 this.typeInfo.type_name = click_node.val.classificationName;
				 this.typeInfo.type_des = click_node.val.description;
				 this.typeInfo.UpdateDateTime = click_node.val.updateDateTime;
				 this.showtcc = {display:'block'};
			 }  
		  },
		  DelData:function(){                //删除数据
			  if(checkedNames.length>0){
				  
				  var _this = this;
				  layer.confirm(IC_GETINFOBYAttrs(Info.W0002,['分类体系']), {
			            btn : [ '确定', '取消' ]//按钮
			        }, function(index) {
			            layer.close(index);
			            var json = createJSON(checkedNames);   //createJSON() 引自js/comm.js
			            //checkedNames = [];
						 axios.post('../classifcationinfo/deldata',
								 {
								 json:json
						      })
			    			.then(function (response) {
			    				if(response.data=='ok'){
			    					var l_index = layer.msg('删除中', {icon: 16,shade: 0.01});
			    					checked = false;
			    					var b = true;
			    					for(var i=0;i<checkedNames.length;i++){      //如果删除的数据中有根节点,则删除数据后通过重新查询刷新数据页面
			    						var obj = _this.getClickNode(checkedNames[i],_this.datas);
			    						if(obj[1].val.parent_Classification_ID=='0000000000'){
			    							 checkedNames = [];
		    								 _this.search_after_update(_this.$refs.pagecomponent.pageBean,false);
		    								 b = false;
		    								 break;
			    						}
			    					}
			    					if(b){   //如果删除的数据里没有根节点，则直接通过删除数组中对应的数据来刷新页面，不再从后台查询
				    					for(var i=0;i<checkedNames.length;i++){
				    						 var obj = _this.getClickNode(checkedNames[i],_this.datas);
				    						 if(obj!=null){
				    							 var c_i_index = obj[0];
				    							 var c_node = obj[1];
			    								 var p_obj = _this.getClickNode(c_node.val.parent_Classification_ID,_this.datas);
			    								 if(p_obj!=null){
			    									 var p_node = p_obj[1];
			    									 var rootnode = _this.getRootNodeByid(p_node);
			    									 if(rootnode!=null){
				    									 p_node.children.splice(c_i_index, 1);
				    									 p_node.children_lg = p_node.children.length;
				    									 if(p_node.children.length==0){
				    										 p_node.imgsrc = '';
				    									 }
				    									 _this.updateJsonData(rootnode);
			    									 }
			    								 }
				    						 }
				    					}
				    					checkedNames = [];
			    					}
			    					layer.close(l_index);
			    				}
			    				
			    			})
			    			.catch(function (error) {
			    			    console.log(error);
			    			});
			        }); 
				 
			  }else{
				  layer.msg(Info.E0019);
			  }
		  },
		  getRootNodeByid:function(cnode){    //获得cnod的根节点
			  if(cnode.father_node!=null){
				  return this.getRootNodeByid(cnode.father_node);
			  }
		      return cnode;
		  },
 		getClickNode:function(id,arr){  //根据ID获得点击的节点
			  
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
		  NewData:function(){  //打开新增数据弹出层
			  if(checkedNames.length>1){
				  layer.msg(Info.E0020);
			  }else{
				  $(".cy_hidebg").css("height",($(document).height()+document.body.scrollHeight));
				  this.tcc_title = Info.I0009;
				  this.isnew = true;
				  if(checkedNames.length==1){
				  	 this.father_node_id = checkedNames[0];
				  }else{
					 this.father_node_id = '';
				  }
				  if(this.father_node_id==''){
					  $("#cy_CMICBMS_add").css("height","300px");
				  }else{
					  $("#cy_CMICBMS_add").css("height","330px");
				  }
				  var anode = this.getClickNode(checkedNames[0],this.datas);
				  if(anode!=null){
					var c_node = anode[1];//this.getClickNode(checkedNames[0],this.datas)[1];
					if(c_node.depth==3){
						layer.msg(IC_GETINFOBYAttrs(Info.E0038,['4']));//IC_GETINFOBYAttrs引自 js/comm.js
						return;
					}
				  	this.father_node_name = c_node.val.classificationName;
				  }
			  	  this.showtcc = {display:'block'};
			  }
		  },
		  UpdateData:function(){  //编辑分类
			     if(this.typeInfo.type_name.trim()==''){
				      this.errInfo.type_name_err = true;
				  }else{
					    var click_node = this.getClickNode(checkedNames[0],this.datas)[1];
					    var root_node = this.getRootNodeByid(click_node);
					    var _this = this;
			        	axios.get('../classifcationinfo/updateclassifcationinfo',{
			    			params: {
			    				Classification_ID:_this.typeInfo.type_id,
			    				ClassificationName:_this.typeInfo.type_name.trim(),
			    				Description:_this.typeInfo.type_des.trim(),
			    				UpdateDateTime:click_node.val.updateDateTime
			    				}
			    			})
			    			.then(function (response) {
			    				if(response.data.indexOf("success")>=0){         //编辑成功
			    					layer.msg(Info.I0007);
			    					click_node.val.classificationName = _this.typeInfo.type_name.trim();
			    					click_node.val.description = _this.typeInfo.type_des.trim();
			    					click_node.val.updateDateTime = response.data.replace("success","");
			    					_this.updateJsonData(root_node);
			    					_this.hideTcc();
			    					checkedNames = [];
			    				}
			    				if(response.data=="exist"){    //该分类名称已存在
			    					layer.msg(Info.E0021);
			    				}
			    				if(response.data=="nok"){      //出现未知错误
			    					layer.msg(Info.E0022);
			    				}
			    				if(response.data=="already update"){  //分类已经被其他人修改
			    					layer.msg(Info.W0004);
			    				}
			    				
			    			})
			    			.catch(function (error) {
			    			    console.log(error);
			    			});
				  }
		  },
		  getNodePath:function(fid,arr){         //获得分类从根节点到自己的ID path
			  var str = "";
			  if(fid!=''){
				  var node  = this.getClickNode(fid,arr)[1];
				  
				  str = node.val.classification_ID;
				  
				  if(node.val.parent_Classification_ID!='0000000000'){
					  str = this.getNodePath(node.val.parent_Classification_ID,arr) +"/"+str;
				  }
			  }
			  
			  return str;
		  },
		  SaveNewData:function(){    //添加新分类
			  if(this.typeInfo.type_name.trim()==''){
			      this.errInfo.type_name_err = true;
			  }else{
				    var _this = this;
				    var nodePath = this.getNodePath(this.father_node_id,this.datas);
				    nodePath = nodePath.replace("\/\/","\/");
		        	axios.get('../classifcationinfo/addclassifcationinfo',{
		    			params: {
		    				ClassificationName:_this.typeInfo.type_name.trim(),
		    				Description:_this.typeInfo.type_des.trim(),
		    				Parent_Classification_ID:_this.father_node_id,
		    				ClassificationPath:nodePath
		    				}
		    			})
		    			.then(function (response) {
		    				if(response.data=="exist"){       //分类名称已存在
		    					layer.msg(Info.E0023);
		    				}else if(response.data=="nok"){   //出现异常错误
		    					layer.msg(Info.E0022);
		    				}else{                            //添加成功
		    					layer.msg(Info.I0008);
		    					if(_this.father_node_id==''){     //如果是添加根节点  刷新页面
		    						_this.search_after_update(_this.$refs.pagecomponent.pageBean,false);
		    					}else{                            //如果添加的不是根节点   则打开其父节点
		    					    var click_node = _this.getClickNode(checkedNames[0],_this.datas)[1];
		    					    var root_node = _this.getRootNodeByid(click_node);
		    						if(!click_node.i_have_ajax){                         //还未ajax请求
		    				        	axios.get('../classifcationinfo/getchild',{
		    				    			params: {
		    				    				  Classification_ID:_this.father_node_id
		    				    				}
		    				    			})
		    				    			.then(function (response) {
		    				    				var data = JSON.parse(response.data);
		    				    				
		    				    				for(var i=0;i<data.length;i++){
		    				    					 var tn = new TreeNode(data[i],click_node.depth+1,data[i].children_lg,uuid(),click_node);
		    				    					 click_node.children.push(tn);
		    				    				  }
		    				    				click_node.i_have_ajax = true;
		    				    				
		    				    				click_node.children_lg = click_node.children.length;
		    				    				
		    				    				click_node.is_open = true;
		    				    				  
		    							        click_node.imgsrc = "${ctx}/image/is-tbarr02.png";
		    						      
		    							        _this.dealDigui(click_node,true);

		    							        _this.updateJsonData(root_node);
		    							        
	
		    				    			})
		    				    			.catch(function (error) {
		    				    			    console.log(error);
		    				    			});
		    				        }else{
		    				        	var json = JSON.parse(response.data);
			    						var tn = new TreeNode(json,click_node.depth+1,json.children_lg,uuid(),click_node);
			    						click_node.children.unshift(tn);
			    						click_node.children_lg = click_node.children.length;
			    						click_node.imgsrc = "${ctx}/image/is-tbarr02.png";
			    						click_node.is_open = true;
			    						_this.dealDigui(click_node,true);
			    						_this.updateJsonData(root_node);
		    				        }
		    						checkedNames = [];
		    					}
		    					_this.hideTcc();
		    				}
		    			})
		    			.catch(function (error) {
		    			    console.log(error);
		    			});
			  }
		  },
		  search_after_update:function(pageBean,b){

			  this.search(pageBean,b);
		  },
		  hideTcc:function(){     //关闭弹出层
			  this.typeInfo.type_id = '';
			  this.typeInfo.type_name = '';
			  this.typeInfo.type_des = '';
			  this.errInfo.type_name_err = false;
			  this.father_node_id = '';
			  this.showtcc = {display:'none'};
		  },
		  dealDigui:function(node,flag){ //递归打开or关闭树形结构的节点
			  if(node.children_lg>0){
				  if(flag){  //打开
					  if(node.is_open){  //如果该节点是 打开状态  则显示其子节点并对子节点做递归处理
						  for(var i=0;i<node.children.length;i++){
					    	  if(node.children[i].val.parent_Classification_ID==node.val.classification_ID){
					    		  node.children[i].is_show = flag;
					    		  this.dealDigui(node.children[i],flag);
					    	  }  
					      }
					  }
				  }else{  //关闭   将该节点的所有子节点置于不可见状态
					  for(var i=0;i<node.children.length;i++){
				    	  if(node.children[i].val.parent_Classification_ID==node.val.classification_ID){
				    		  node.children[i].is_show = flag;
				    		  this.dealDigui(node.children[i],flag);
				    	  }  
				      }
				  }
			  }
		  },
		  dealopClose:function(id){       //递归打开所有节点
			 var click_node = this.getClickNode(id,this.datas)[1];
			  
			  var root_node = this.getRootNodeByid(click_node);
			 
			  if(!click_node.is_open){                      //打开树节点
				        if(!click_node.i_have_ajax){                         //还未ajax请求
						    var _this = this;
				        	axios.get('../classifcationinfo/getchild',{     //发送ajax请求获得该节点的子节点信息
				    			params: {
				    				  Classification_ID:id
				    				}
				    			})
				    			.then(function (response) {
				    				var data = JSON.parse(response.data);
	                              
				    				for(var i=0;i<data.length;i++){
				    					 var tn = new TreeNode(data[i],click_node.depth+1,data[i].children_lg,uuid(),click_node);
				    					 click_node.children.push(tn);
				    				  }
				    				 click_node.i_have_ajax = true;
				    				 for(var i=0;i<data.length;i++){
				    					  checkedArr.push(data[i].classification_ID);
				    				  }
				   
				    				 _this.updateJsonData(root_node);
				    				 
				    				 if(checked){
				    					 $("input[name='dch']").prop("checked",true);
				    					 checkedNames = checkedArr;
				    				 }
				    				
				    				 if(click_node.children.length>0){
				    					 
				    					 click_node.is_open = true;
					    				  
									     click_node.imgsrc = "${ctx}/image/is-tbarr02.png";
				    					 
				    					 for(var i=0;i<click_node.children.length;i++){
				    						 _this.dealopClose(click_node.children[i].val.classification_ID);
				    					 }
				    				 }
				    			})
				    			.catch(function (error) {
				    			    console.log(error);
				    			});
				        }
			  }
		  },
		  opClose:function(id){  //打开/关闭ID为id的节点
			  
			  var click_node = this.getClickNode(id,this.datas)[1];
			  
			  var root_node = this.getRootNodeByid(click_node);
			 
			  if(!click_node.is_open){                      //打开树节点
				        if(!click_node.i_have_ajax){                         //还未ajax请求
						    var _this = this;
				        	axios.get('../classifcationinfo/getchild',{     //发送ajax请求获得该节点的子节点信息
				    			params: {
				    				  Classification_ID:id
				    				}
				    			})
				    			.then(function (response) {
				    				var data = JSON.parse(response.data);
	                              
				    				for(var i=0;i<data.length;i++){
				    					 var tn = new TreeNode(data[i],click_node.depth+1,data[i].children_lg,uuid(),click_node);
				    					 click_node.children.push(tn);
				    				  }
				    				 click_node.i_have_ajax = true;
				    				 for(var i=0;i<data.length;i++){
				    					  checkedArr.push(data[i].classification_ID);
				    				  }
				   
				    				 _this.updateJsonData(root_node);
				    				 
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
				        
				        if(click_node.i_have_ajax){
				        	this.updateJsonData(root_node);
				        }
				       
				       
			  }else{         //关上
			  
				  click_node.is_open = false;
				  
				  click_node.imgsrc = "${ctx}/image/is-tbarr01.png";
				  
				  this.dealDigui(click_node,false);
				  
				  this.updateJsonData(root_node);
			     
			  }
		  },
		  updateJsonData:function(tn){   //在树形结构改变后(打开、关闭、新增、修改、删除、移动)更新tn树形结构的各模块对应的JSON值
			  
			  tn.json_classification_ID = JSON.parse(getJSONclassification_ID(tn));
			  tn.json_classificationName = JSON.parse(getJSONclassificationName(tn));
			  tn.json_description = JSON.parse(getJSONdescription(tn));
			  tn.json_createDateTime = JSON.parse(getJSONcreateDateTime(tn));
			  tn.json_createUser= JSON.parse(getJSONcreateUser(tn));
			  
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
	  computed:{
		   search:function(){                               //查询数据
		        return function(pageBean,ismsg){
		        	 checked = false;
					  $("#thch").prop("checked",false);
					  if(ismsg){
			        	var l_index = layer.msg(Info.I0011, {
			        		  icon: 16
			        		  ,shade: 0.01,
			        		  time:false
			        		});
					  }
			        	var _this = this;
			        	axios.get('../classifcationinfo/search',{
			    			params: {
			    				ClassificationName:_this.search_key.trim(),
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
				    				 if(data.resdata.length==0&&pageBean.pageNow>1){
				    					 pageBean.pageNow-=1;
				    					 _this.search(pageBean,ismsg);
				    					 return;
				    				 }
				    				 var temp = data.resdata;
				    				 for(var i=0;i<temp.length;i++){
				    					 var tn = new TreeNode(temp[i],0,temp[i].children_lg,uuid(),null);   
				    					 _this.datas.push(tn);
				    					 _this.updateJsonData(tn);
				    				 }
				    				 if(_this.search_key.trim()!=''){  //如果查询词不 为空，则打开所有的子节点
					    				 for(var i=0;i<_this.datas.length;i++){
					    					  _this.dealopClose(_this.datas[i].val.classification_ID);
					    				 }
				    				 }
				    				 checkedArr = [];
				    				  for(var i=0;i<data.resdata.length;i++){    //将查询到的数据放入到checkedArr  用于之后全选\反选的判断
				    					  checkedArr.push(data.resdata[i].classification_ID);
				    				  }
				    				  
				    				  if(data.rowCount=='0'&&!_this.isfirstinjsp){
				    					 layer.msg(Info.I0002);
				    				  }
				    				
				    				  _this.isfirstinjsp = false;
				    				  
				    				 _this.$refs.pagecomponent.dealAfterSearch(data.rowCount);  //查询完成后回调分页组件的函数,处理分页组件的相关参数
				    				
				    				  
				    				  if(_this.btn_wy_disabled){   //如果是位移发生的分页查询
				    					  
				    					  var firstNode = _this.datas[0].val;
				    					  var lastNode = _this.datas[_this.datas.length-1].val ;
				    					  var targetNode;
				    					  if(_this.tempNode.val.displayOrder<lastNode.displayOrder){  //上移
				    						  targetNode = lastNode;
				    						  _this.datas[_this.datas.length-1] = _this.tempNode;
				    						  checkedArr[checkedArr.length-1] = _this.tempNode.val.classification_ID;
				    					  }
				    					  if(_this.tempNode.val.displayOrder>firstNode.displayOrder){  //下移
				    						  targetNode = firstNode;
				    						  _this.datas[0] = _this.tempNode;
				    						  checkedArr[0] = _this.tempNode.val.classification_ID;
				    					  }
				    					  /*以下是交换前后两个节点的displayOrder*/
				    					  var temp_order = _this.tempNode.val.displayOrder ;
				  					      _this.tempNode.val.displayOrder = targetNode.displayOrder;
				  					      targetNode.displayOrder = temp_order;
				  						  /*以下是更新节点的displayOrder到数据库*/
				  					      _this.updateOrder(_this.tempNode.val.classification_ID,_this.tempNode.val.displayOrder,
				  					    		  targetNode.classification_ID,targetNode.displayOrder,-1,-1,_this.datas);
				
			  
				    					  checkedNames.push(_this.tempNode.val.classification_ID);
				    					  
				    				  }
		                          }
			    				 if(ismsg){
			    				    layer.close(l_index);
			    				 }
			    			})
			    			.catch(function (error) {
			    			    console.log(error);
			    			    layer.close(l_index);
			    			});
			        	
				}
			}
		  },
		  mounted:function(){
			  this.search(this.$refs.pagecomponent.pageBean,false);
		  }
});

Vue.component('ic_tree_dv',ic_tree_dv);
Vue.component('ic_tree_dv_checkbox',ic_tree_dv_checkbox);
</script>
</body>

</html>
