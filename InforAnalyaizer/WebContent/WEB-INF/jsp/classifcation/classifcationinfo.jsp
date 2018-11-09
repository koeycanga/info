<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="ss" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources" var="sysInfo" />  <!-- basename: 在classes文件夹下properties文件的文件名 -->
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
		<p>情报规划 / <span>分类体系</span></p>
		<div class="cy_CMICBMS_tablebox">
		<div class="cy_CMICBMS_box07">
			<div class="cy_CMICBMS_schbox01">
				<input type="text" v-model="search_key" placeholder="请输入关键词检索">
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
			      <th scope="col" width="15%">分类名称</th>
			      <th scope="col" width="50%">备注</th>
			      <th scope="col" width="20%">创建时间</th>
			      <th scope="col" width="auto">创建人</th>
			    </tr>
			    <tr v-for="(data,key) in datas" class="cy_CMICBMS_treven">
			      <td>
			          <ic_tree_dv_checkbox v-bind:model="data.json_classification_ID"></ic_tree_dv_checkbox>
			      </td>
			      <td>
			            <ic_tree_dv v-bind:model="data.json_classificationName"></ic_tree_dv>
			      </td>
			      <td>
			           <ic_tree_dv v-bind:model="data.json_description"></ic_tree_dv>
			      </td>
			      <td>
			          <ic_tree_dv v-bind:model="data.json_createDateTime"></ic_tree_dv>
			       </td>
			      <td>
			          <ic_tree_dv v-bind:model="data.json_createUser"></ic_tree_dv>
			       </td>
			    </tr>
			  </tbody>
			</table>
		</div>
		
		
	<pager ref="pagecomponent"></pager>  <!-- ref 固定为 pagecomponent  -->
    
    <div class="cy_hidebg" v-bind:style="showtcc" id="cy_hidebg"></div> <!-- 遮罩层 -->
    <!--新增按钮弹窗-->
	<div  v-bind:style="showtcc" id="cy_CMICBMS_add">
		<div class="cy_CMICBMS_addtop"><div class="cy_CMICBMS_addtit">{{tcc_title}}</div><div class="cy_CMICBMS_addclose" v-on:click="hideTcc()">X</div></div>
		<div class="cy_CMICBMS_addtb">
		
			<div v-if="checkedNames.length==1&&isnew" style="width: 95%;text-align: left;">&nbsp;&nbsp;&nbsp;&nbsp;父节点：{{farther_node_name}}</div>
			
			<div class="cy_CMICBMS_addtbinp">
			    <span>*</span>分类名称：<input type="text" placeholder="输入分类名称" v-on:blur="checkTypeName()" v-model="typeInfo.type_name" class="cy_CMICBMS_addinput">
			    <div v-if="errInfo.type_name_err" class="cy_CMICBMS_errortip">请输入分类名称！</div>
			</div>
			<div class="cy_CMICBMS_addtbinp">&nbsp;&nbsp;备注：<input type="text" v-model="typeInfo.type_des" placeholder="输入备注" class="cy_CMICBMS_addinput"></div>
			
			<div class="cy_CMICBMS_addtbinp" v-if="isnew" style="align-self:flex-end;margin:40px 20px 0 0;"><input type="button" v-on:click="SaveNewData()" value="确定" class="cy_CMICBMS_schbtn"></div>
	        <div class="cy_CMICBMS_addtbinp" v-else style="align-self:flex-end;margin:40px 20px 0 0;"><input type="button" v-on:click="UpdateData()" value="确定" class="cy_CMICBMS_schbtn"></div>
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
		I0011:'${I0011}'
	};

function TreeNode(val,depth,children_lg){
	this.val = val;
	this.is_show = true;
	this.is_open = false;
	this.depth = depth;
	this.i_have_ajax = false;
	this.children_lg = children_lg;
	if(children_lg>0){
		this.imgsrc = "${ctx}/image/is-tbarr01.png";
	}else{
		this.imgsrc = "";
	}
	this.children = [];
	
	this.json_classification_ID = [];
	this.json_classificationName = [];
	this.json_description = [];
	this.json_createDateTime = [];
	this.json_createUser= [];
}

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

function getJSONclassificationName(tn){
   var res = '{"name":"'+tn.val.classificationName+'","style":true,"id":"'+tn.val.classification_ID+'","is_show":'+tn.is_show+',"is_open":'+tn.is_open+',"depth":"'+tn.depth+'","i_have_ajax":"'+tn.i_have_ajax+'","children_lg":"'+tn.children_lg+'","imgsrc":"'+tn.imgsrc+'"';
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


function exchangeArr(arr,i_index,j_index){  //把arr的第j_index个元素移到  第i_index个位置
	
	var temp = arr[j_index];
	arr[j_index] = arr[i_index];
	arr[i_index] = temp;
}

var ic_tree_dv_checkbox = {
		template: '<div>'+
		           '<div v-show="model.is_show" v-bind:style="t_style">'+
		           '<input type="checkbox" name="dch"  v-bind:value="model.name" onclick="checka(this)"  v-model="checkedNames" class="cy_CMICBMS_checkbox"><label for="cy_CMICBMS_checkbox"></label></div>'+
		          '<ic_tree_dv_checkbox  v-for="(model,index) in model.children" v-bind:key="index" v-bind:model="model"></ic_tree_dv_checkbox>'+
		          '</div>',
		props: ['model'],
		data:function(){
			return{
				t_style : {padding: '10px 0px !important'} 
			}
		},
};


var ic_tree_dv = {
		template: '<div >'+
		           '<div v-show="model.is_show" v-bind:style="t_style">'+
		           '<img v-if="model.imgsrc" v-bind:src="model.imgsrc" v-on:click="opClose(model.id)" style="cursor:pointer">'+
		           '{{model.name}}&nbsp;</div>'+
		          '<ic_tree_dv  v-for="(model,index) in model.children" v-bind:key="index" v-bind:model="model"></ic_tree_dv>'+
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
				this.t_style = {padding: '10px '+(26+20*this.model.depth)+'px !important'} ;
			}
		}
};

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


var checked = false;      //复选框相关
var checkedNames = [];    //复选框相关
var checkedArr = [];    //复选框相关

var app = new Vue({
	el:"#app",
	data:{
		  datas:[],
		  
		  search_key:'',
		
		  btn_wy_disabled:false,    //上移  下移按钮是否能点击
		  
		  //showtccer:'{display:block}',
		  showtcc:{display:'none'},   //是否显示弹出层
		  tcc_title : '',      //弹出层的标题
		  isdisabled:false,
		  isnew:true,         //是否是新增数据
		  
		  farther_node_name:'',    //父节点名称
		  farther_node_id:'' ,      //父节点ID
		  
		  
		  tempNode:'',
		  
		  typeInfo:{
			  type_id:'',
			  type_name:'',
			  type_des:''
		  },
		  errInfo:{
			  type_name_err:false
		  },
	},
	components:{
	     'pager':ic_pager
	  },
	  methods:{
		  changeAllChecked:function(){
				if(checked){
					$("input[name='dch']").prop("checked",false);
					checkedNames = [];
				}else{
					$("input[name='dch']").prop("checked",true);
					checkedNames = checkedArr;
				}
		  },
		  checkTypeName:function(){
			    if(this.typeInfo.type_name.trim()==''){
			    	this.errInfo.type_name_err = true;
			    }else{
			    	this.errInfo.type_name_err = false;
			    }
		  },
		  MoveUP:function(){    //上移
			if(checkedNames.length==1){
				 var obj = this.getClickNode(checkedNames[0],this.datas);
				 var i_index = obj[0];
			     var click_node = obj[1];
			     var root_node = this.getRootNodeByid(click_node,this.datas);  //获得根节点
				if(click_node.val.parent_Classification_ID=='0000000000'){    //根节点  涉及到翻页
						if(i_index==0){
							if(this.$refs.pagecomponent.pageBean.pageNow==1){
								layer.msg(Info.I0005);
							}else{
								this.tempNode = this.datas[i_index];
								this.btn_wy_disabled = true;
								this.$refs.pagecomponent.pageBean.pageNow-=1;
								this.search(this.$refs.pagecomponent.pageBean);
							}
							
						}else{
							this.dealchange(i_index - 1,i_index,this.datas);
						}
				}else{
			    	//非根节点
					if(i_index==0){
						layer.msg(Info.I0005);
					}else{ 
						var parentNode = this.getClickNode(click_node.val.parent_Classification_ID,this.datas)[1];
						this.dealchange(i_index - 1,i_index,parentNode.children);
						this.updateJsonData(root_node);
					}
			    }
			}else{
				layer.msg(Info.E0006);
			}
		  },
		  MoveDown:function(){  //下移
			  if(checkedNames.length==1){
				    var obj = this.getClickNode(checkedNames[0],this.datas);
				    var i_index = obj[0];
					var click_node = obj[1];
				    if(click_node.val.parent_Classification_ID=='0000000000'){    //根节点  涉及到翻页
						if(i_index==this.datas.length-1){
							if(this.$refs.pagecomponent.pageBean.pageNow==this.$refs.pagecomponent.pageBean.pageSize){
								layer.msg(Info.I0006);
							}else{
								this.tempNode = this.datas[i_index];
								this.btn_wy_disabled = true;
								this.$refs.pagecomponent.pageBean.pageNow+=1;
								this.search(this.$refs.pagecomponent.pageBean);
							}
						}else{
							this.dealchange(i_index + 1,i_index,this.datas);
						}
				    }else{
				    	//非根节点
				    	var root_node = this.getRootNodeByid(click_node,this.datas);  //获得根节点
				 
				    	var parent_node = this.getClickNode(click_node.val.parent_Classification_ID,this.datas)[1];
						if(i_index==parent_node.children.length-1){
							layer.msg(Info.I0006);
						}else{ 
						
							this.dealchange(i_index+1,i_index,parent_node.children);
							
							this.updateJsonData(root_node);
						}
				    }
				  
			  }else{
				  layer.msg(Info.E0006);
			  }
		  },
		  dealchange:function(pre_index,i_index,target_arr){
			 	
				var temp_displayOrder = target_arr[pre_index].val.displayOrder;
				
				target_arr[pre_index].val.displayOrder = target_arr[i_index].val.displayOrder ;
				
				target_arr[i_index].val.displayOrder = temp_displayOrder;
				
				this.btn_wy_disabled = true;
				
				this.updateOrder(target_arr[i_index].val.classification_ID,target_arr[i_index].val.displayOrder,
						target_arr[pre_index].val.classification_ID,target_arr[pre_index].val.displayOrder,pre_index,i_index,target_arr);
		  },
		  updateOrder:function(cur_id,cur_order,ch_id,ch_order,ch_index,i_index,target_arr){
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
		  btn_search:function(){
			  
			  checkedNames = [];
			  this.$refs.pagecomponent.pageBean.pageNow = 1;
			  this.search(this.$refs.pagecomponent.pageBean);
		  },
		  EditData:function(){
			 if(checkedNames.length!=1){
				 layer.msg(Info.E0006);
			 }else{
				 this.isnew = false;
				 this.tcc_title = Info.I0010;
				 this.farther_node_id = "";
				 var click_node = this.getClickNode(checkedNames[0],this.datas)[1];
				 this.typeInfo.type_id = click_node.val.classification_ID;
				 this.typeInfo.type_name = click_node.val.classificationName;
				 this.typeInfo.type_des = click_node.val.description;
				 this.showtcc = {display:'block'};
			 }  
		  },
		  DelData:function(){
			  if(checkedNames.length>0){
				  var _this = this;
				  layer.confirm(Info.W0002, {
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
			    					for(var i=0;i<checkedNames.length;i++){
			    						var obj = _this.getClickNode(checkedNames[i],_this.datas);
			    						if(obj[1].val.parent_Classification_ID=='0000000000'){
			    							 checkedNames = [];
		    								 _this.search_after_update(_this.$refs.pagecomponent.pageBean);
		    								 b = false;
		    								 break;
			    						}
			    					}
			    					if(b){
				    					for(var i=0;i<checkedNames.length;i++){
				    						 var obj = _this.getClickNode(checkedNames[i],_this.datas);
				    						 if(obj!=null){
				    							 var c_i_index = obj[0];
				    							 var c_node = obj[1];
				    							 if(c_node.val.parent_Classification_ID=='0000000000'){
				    								 //_this.datas.splice(c_i_index, 1);
				    								 checkedNames = [];
				    								 _this.search_after_update(_this.$refs.pagecomponent.pageBean);
				    								 break;
				    							 }else{
				    								 var p_obj = _this.getClickNode(c_node.val.parent_Classification_ID,_this.datas);
				    								 if(p_obj!=null){
				    									 var p_node = p_obj[1];
				    									 var rootnode = _this.getRootNodeByid(p_node,_this.datas);
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
		  getRootNodeByid:function(cnode,arr){
			  
			  var node = null;
			  if(cnode!=null){
				  if(cnode.val.parent_Classification_ID=='0000000000'){
					  return cnode;
				  }else{
					  node = this.getClickNode(cnode.val.parent_Classification_ID,this.datas)[1];
					  if(node!=null){
						  if(node.val.parent_Classification_ID!='0000000000'){
							   return this.getRootNodeByid(node,arr);
						  }else{
							  return node;
						  }
					  }
				  }
			  }
			  return node;
		  },
 		getClickNode:function(id,arr){
			  
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
				  this.tcc_title = Info.I0009;
				  this.isnew = true;
				  if(checkedNames.length==1){
				  	 this.farther_node_id = checkedNames[0];
				  }else{
					 this.farther_node_id = '';
				  }
				  var anode = this.getClickNode(checkedNames[0],this.datas);
				  if(anode!=null){
					var c_node = this.getClickNode(checkedNames[0],this.datas)[1];  
				  	this.farther_node_name = c_node.val.classificationName;
				  }
			  	  this.showtcc = {display:'block'};
			  }
		  },
		  UpdateData:function(){  //编辑分类
			     if(this.typeInfo.type_name.trim()==''){
				      this.errInfo.type_name_err = true;
				  }else{
					    var click_node = this.getClickNode(checkedNames[0],this.datas)[1];
					    var root_node = this.getRootNodeByid(click_node,this.datas);
					    var _this = this;
			        	axios.get('../classifcationinfo/updateclassifcationinfo',{
			    			params: {
			    				Classification_ID:_this.typeInfo.type_id,
			    				ClassificationName:_this.typeInfo.type_name.trim(),
			    				Description:_this.typeInfo.type_des.trim()
			    				}
			    			})
			    			.then(function (response) {
			    				if(response.data=="ok"){
			    					layer.msg(Info.I0007);
			    					click_node.val.classificationName = _this.typeInfo.type_name.trim();
			    					click_node.val.description = _this.typeInfo.type_des.trim();
			    					_this.updateJsonData(root_node);
			    					_this.hideTcc();
			    				}
			    				if(response.data=="exist"){
			    					layer.msg(Info.E0021);
			    				}
			    				if(response.data=="nok"){
			    					layer.msg(Info.E0022);
			    				}
			    				
			    			})
			    			.catch(function (error) {
			    			    console.log(error);
			    			});
				  }
		  },
		  SaveNewData:function(){    //添加新分类
			  if(this.typeInfo.type_name.trim()==''){
			      this.errInfo.type_name_err = true;
			  }else{
				    var _this = this;
		        	axios.get('../classifcationinfo/addclassifcationinfo',{
		    			params: {
		    				ClassificationName:_this.typeInfo.type_name.trim(),
		    				Description:_this.typeInfo.type_des.trim(),
		    				Parent_Classification_ID:_this.farther_node_id
		    				}
		    			})
		    			.then(function (response) {
		    				if(response.data=="exist"){
		    					layer.msg(Info.E0023);
		    				}else if(response.data=="nok"){
		    					layer.msg(Info.E0022);
		    				}else{
		    					layer.msg(Info.I0008);
		    					if(_this.farther_node_id==''){
		    						_this.search_after_update(_this.$refs.pagecomponent.pageBean);
		    					}else{
		    					    var click_node = _this.getClickNode(checkedNames[0],_this.datas)[1];
		    					    var root_node = _this.getRootNodeByid(click_node,_this.datas);
		    						if(!click_node.i_have_ajax){                         //还未ajax请求
		    				        	axios.get('../classifcationinfo/getchild',{
		    				    			params: {
		    				    				  Classification_ID:_this.farther_node_id
		    				    				}
		    				    			})
		    				    			.then(function (response) {
		    				    				var data = JSON.parse(response.data);
		    				    				for(var i=0;i<data.length;i++){
		    				    					 var tn = new TreeNode(data[i],click_node.depth+1,data[i].children_lg);
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
			    						var tn = new TreeNode(json,click_node.depth+1,json.children_lg);
			    						click_node.children.unshift(tn);
			    						click_node.children_lg = click_node.children.length;
			    						click_node.imgsrc = "${ctx}/image/is-tbarr02.png";
			    						click_node.is_open = true;
			    						_this.dealDigui(click_node,true);
			    						_this.updateJsonData(root_node);
		    				        }
		    					}
		    					_this.hideTcc();
		    				}
		    			})
		    			.catch(function (error) {
		    			    console.log(error);
		    			});
			  }
		  },
		  search_after_update:function(pageBean){
			  pageBean.pageNow = 1;
			  this.search(pageBean);
		  },
		  hideTcc:function(){     //关闭弹出层
			  this.typeInfo.type_id = '';
			  this.typeInfo.type_name = '';
			  this.typeInfo.type_des = '';
			  this.errInfo.type_name_err = false;
			  this.farther_node_id = '';
			  this.showtcc = {display:'none'};
		  },
		  dealDigui:function(node,flag){
			  if(node.children_lg>0){
				  if(flag){
					  if(node.is_open){
						  for(var i=0;i<node.children.length;i++){
					    	  if(node.children[i].val.parent_Classification_ID==node.val.classification_ID){
					    		  node.children[i].is_show = flag;
					    		  this.dealDigui(node.children[i],flag);
					    	  }  
					      }
					  }
				  }else{
					  for(var i=0;i<node.children.length;i++){
				    	  if(node.children[i].val.parent_Classification_ID==node.val.classification_ID){
				    		  node.children[i].is_show = flag;
				    		  this.dealDigui(node.children[i],flag);
				    	  }  
				      }
				  }
			  }
		  },
		  opClose:function(id){
			  var click_node = this.getClickNode(id,this.datas)[1];
			  var root_node = this.getRootNodeByid(click_node,this.datas);
			  if(!click_node.is_open){                      //打开
				        if(!click_node.i_have_ajax){                         //还未ajax请求
						    var _this = this;
				        	axios.get('../classifcationinfo/getchild',{
				    			params: {
				    				  Classification_ID:id
				    				}
				    			})
				    			.then(function (response) {
				    				var data = JSON.parse(response.data);
				    				for(var i=0;i<data.length;i++){
				    					 var tn = new TreeNode(data[i],click_node.depth+1,data[i].children_lg);
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
		  updateJsonData:function(tn){
			  
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
			
		   search:function(){
		        return function(pageBean){
		        	 checked = false;
					  $("#thch").prop("checked",false);
			        	var l_index = layer.msg(Info.I0011, {
			        		  icon: 16
			        		  ,shade: 0.01
			        		});
			        	
			        	var _this = this;
			        	axios.get('../classifcationinfo/search',{
			    			params: {
			    				ClassificationName:_this.search_key.trim(),
			    				Parent_Classification_ID:_this.farther_node_id,
			    				pageNow:pageBean.pageNow,
			    				rowSize:pageBean.rs_selected
			    				}
			    			})
			    			.then(function (response) {
			    				
			    				 checkedNames = [];
			    				 var data = JSON.parse(response.data);
			    				 
			    				 _this.datas = [];
			    				 var temp = data.resdata;
			    				 for(var i=0;i<temp.length;i++){
			    					 var tn = new TreeNode(temp[i],0,temp[i].children_lg);   //TreeNode 引自 comm.js
			    					 _this.datas.push(tn);
			    					 					 
			    					 _this.updateJsonData(tn);
			    			
			    				 }
			    				 
			    				 checkedArr = [];
			    				  for(var i=0;i<data.resdata.length;i++){
			    					  checkedArr.push(data.resdata[i].classification_ID);
			    				  }
			    			
			    				 _this.$refs.pagecomponent.dealAfterSearch(data.rowCount); 
			    				
			    				  
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
			    					  var temp_order = _this.tempNode.val.displayOrder ;
		  					      _this.tempNode.val.displayOrder = targetNode.displayOrder;
		  					      targetNode.displayOrder = temp_order;
		  						  
		  					      _this.updateOrder(_this.tempNode.val.classification_ID,_this.tempNode.val.displayOrder,
		  					    		  targetNode.classification_ID,targetNode.displayOrder,-1,-1,_this.datas);
		  					      
		 
			    					  checkedNames.push(_this.tempNode.val.classification_ID);
			    					  
			    				  }
			    				
			    				  layer.close(l_index);
			    				  
			    			})
			    			.catch(function (error) {
			    			    console.log(error);
			    			    layer.close(l_index);
			    			});
			        	
				}
			}
		  },
		  mounted:function(){
			  this.search(this.$refs.pagecomponent.pageBean);
			 
		  }
});

Vue.component('ic_tree_dv',ic_tree_dv);
Vue.component('ic_tree_dv_checkbox',ic_tree_dv_checkbox);
</script>
</body>

</html>
