<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="ss" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources" var="sysInfo" />  <!-- basename: 在classes文件夹下properties文件的文件名 -->
<fmt:message key="please_select_del_data" var="please_select_del_data" bundle="${sysInfo}" />
<fmt:message key="unkonow_err" var="unkonow_err" bundle="${sysInfo}" /> 
<fmt:message key="please_select_edit_data" var="please_select_edit_data" bundle="${sysInfo}" /> 
<fmt:message key="edit_success" var="edit_success" bundle="${sysInfo}" /> 
<fmt:message key="add_success" var="add_success" bundle="${sysInfo}" /> 


<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="../css/cy_CIAS_style.css">
<link rel="stylesheet" type="text/css" href="../css/cy_zg_win.css">
 
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
			      <th scope="col" width="25px">#<!--<input type="checkbox" v-model="checked" v-on:click="changeAllChecked()" class="cy_CMICBMS_checkbox"><label for="cy_CMICBMS_checkbox"></label>  --></th>
			      <th scope="col" width="15%">分类名称</th>
			      <th scope="col" width="50%">备注</th>
			      <th scope="col" width="20%">创建时间</th>
			      <th scope="col" width="auto">创建人</th>
			    </tr>
			    <tr v-for="data in datas" class="cy_CMICBMS_treven">
			      <td>
			        <div v-for="c_data in data.children" v-if="c_data.is_show" v-bind:style="c_data.ch_style">
			      		<input type="checkbox" v-bind:value="c_data.val.classification_ID"  v-model="checkedNames" class="cy_CMICBMS_checkbox"><label for="cy_CMICBMS_checkbox"></label>
			        </div>
			      </td>
			      <td>
			         <div v-for="c_data in data.children" v-if="c_data.is_show" v-bind:style="c_data.style">
			            <img v-if="c_data.imgsrc!=''" v-bind:src="c_data.imgsrc" v-on:click="opClose(c_data.val.classification_ID)" syle="cursor:pointer">
			         	{{c_data.val.classificationName}}
			         </div>
			      </td>
			      <td>
			         <div v-for="c_data in data.children" v-if="c_data.is_show" v-bind:style="c_data.style">
			         	{{c_data.val.description}}
			         </div>
			      </td>
			      <td>
			         <div v-for="c_data in data.children" v-if="c_data.is_show" v-bind:style="c_data.style">
			         	{{c_data.val.createDateTime}}
			         </div>
			       </td>
			      <td>
			          <div v-for="c_data in data.children" v-if="c_data.is_show" v-bind:style="c_data.style">
			         	{{c_data.val.createUser}}
			         </div>
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
<script type="text/javascript" src="${ctx}/js/ic_components.js"></script>
<script type="text/javascript" src="${ctx}/js/comm.js"></script>

<script>

var ic_base_info = {
		  please_select_del_data:'${please_select_del_data}',
		  unkonow_err:'${unkonow_err}',
		  please_select_edit_data:'${please_select_edit_data}',
		  edit_success:'${edit_success}',
		  add_success:'${add_success}'
	};


function exchangeArr(arr,i_index,j_index){  //把arr的第j_index个元素移到  第i_index个位置
	
	var temp = arr[j_index];
	arr[j_index] = arr[i_index];
	arr[i_index] = temp;
}

var app = new Vue({
	el:"#app",
	data:{
		  datas:[],
		  search_key:'',
		  checked: false,      //复选框相关
	      checkedNames: [],    //复选框相关
		  checkedArr: [],      //复选框相关
		  
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
			  if (!this.checked) {
					this.checkedNames = this.checkedArr;
				} else {
					this.checkedNames = [];
				}
		  },
		  checkTypeName:function(){
			    if(this.typeInfo.type_name.trim()==''){
			    	this.errInfo.type_name_err = true;
			    }else{
			    	this.errInfo.type_name_err = false;
			    }
		  },
		  getPreNodeIndex:function(arr,index){
			  var obj = -1;
			  for(var i=index-1;i>=1;i--){
				  if(arr[i].val.parent_Classification_ID==arr[index].val.parent_Classification_ID){
					  obj = i;
					  break;
				  }
			  }
			  return obj;
		  },
		  getNextNodeIndex:function(arr,index){
			  var obj = [];
			  obj[0] = -1;
			  for(var i=index+1;i<arr.length;i++){
				  console.log(arr[i].val.parent_Classification_ID+"  "+arr[index].val.parent_Classification_ID);
				  if(arr[i].val.parent_Classification_ID==arr[index].val.parent_Classification_ID){
					  obj[0] = i;
					  obj[1] = arr[i];
					  break;
				  }
			  }
			  return obj;
		  },
		  MoveUP:function(){    //上移
			if(this.checkedNames.length==1){
				var i_index = this.getIndex(this.checkedNames[0]);
				var obj = this.getClickNode(this.checkedNames[0]);
				var i_i_index = obj[0];
				var click_node = obj[1];
				var click_dv_index = obj[2];
				if(click_node.val.parent_Classification_ID=='0000000000'){    //根节点  涉及到翻页
						if(i_index==0){
							if(this.$refs.pagecomponent.pageBean.pageNow==1){
								layer.msg("已经处在最顶端");
							}else{
								this.tempNode = this.datas[i_index];
								this.btn_wy_disabled = true;
								this.$refs.pagecomponent.pageBean.pageNow-=1;
								this.search(this.$refs.pagecomponent.pageBean);
							}
							
						}else{
							
							var pre_index = i_index - 1;
							
							this.dealchange(pre_index,i_index,this.datas);
						}
				}else{
			    	//非根节点
			    	var arr = this.datas[i_i_index].children;
					if(click_dv_index==1){
						layer.msg("已经处在最顶端");
					}else{ 
						var pre_index = this.getPreNodeIndex(arr,click_dv_index);
						if(pre_index==-1){
							layer.msg("已经处在最顶端");
						}
						else{
							this.dealchange(pre_index,click_dv_index,arr);
						}
					}
			    }
			}else{
				layer.msg("请选择单条信息");
			}
		  },
		  MoveDown:function(){  //下移
			  if(this.checkedNames.length==1){
				    var i_index = this.getIndex(this.checkedNames[0]);
				    var obj = this.getClickNode(this.checkedNames[0]);
				    var i_i_index = obj[0];
					var click_node = obj[1];
					var click_dv_index = obj[2];
				    if(click_node.val.parent_Classification_ID=='0000000000'){    //根节点  涉及到翻页
						if(i_index==this.datas.length-1){
							if(this.$refs.pagecomponent.pageBean.pageNow==this.$refs.pagecomponent.pageBean.pageSize){
								layer.msg("已经处在最末端");
							}else{
								this.tempNode = this.datas[i_index];
								this.btn_wy_disabled = true;
								this.$refs.pagecomponent.pageBean.pageNow+=1;
								this.search(this.$refs.pagecomponent.pageBean);
							}
						}else{
							var pre_index = i_index + 1;
							this.dealchange(pre_index,i_index,this.datas);
						}
				    }else{
				    	//非根节点
				    	var arr = this.datas[i_i_index].children;
						if(click_dv_index==arr.length-1){
							layer.msg("已经处在最末端");
						}else{ 
							var obj = this.getNextNodeIndex(arr,click_dv_index);
							var pre_index = obj[0];
							var pre_node = obj[1];
							if(pre_index==-1){
								layer.msg("已经处在最末端");
							}
							else{
								this.dealchange(pre_index,click_dv_index,arr);
								
								this.diguiMove(pre_node,arr,click_dv_index+1);
								this.diguiMove(click_node,arr,pre_index+1);
							}
						}
				    }
				  
			  }else{
				  layer.msg("请选择单条信息");
			  }
		  },
		  diguiMove:function(node,arr,t_start_index){
              var start_index = t_start_index;
			  /*for(var i=0;i<arr.length;i++){
				if(arr[i].val.parent_Classification_ID==node.val.classification_ID){
					
					exchangeArr(arr,start_index,i);
					start_index++;
				}
			  }*/
			  var tn = new TreeNode(node.val,node.depth,node.children_lg);
			  arr.push(tn);
			  return start_index;
		  },
		  dealchange:function(pre_index,i_index,target_arr){
			 
			    var pre_node = target_arr[pre_index].val ;  
				
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
				_this.btn_wy_disabled = false;
		  },
		  getIndex:function(id){
			  for(var i=0;i<this.datas.length;i++){
				  if(this.datas[i].val.classification_ID==id){
					  return i;
				  }
			  }
			  return null;
		  },
		  btn_search:function(){
			  
			  this.checkedNames = [];
			  this.$refs.pagecomponent.pageBean.pageNow = 1;
			  this.search(this.$refs.pagecomponent.pageBean);
		  },
		  EditData:function(){
			 if(this.checkedNames.length!=1){
				 layer.msg("请选择单条信息编辑");
			 }else{
				 this.isnew = false;
				 this.tcc_title = "编辑分类";
				 this.farther_node_id = "";
				 for(var i=0;i<this.datas.length;i++){
					 if(this.datas[i].val.classification_ID==this.checkedNames[0]){
						 this.typeInfo.type_id = this.datas[i].val.classification_ID;
						 this.typeInfo.type_name = this.datas[i].val.classificationName;
						 this.typeInfo.type_des = this.datas[i].val.description;
						 break;
					 }
				 }
				 this.showtcc = {display:'block'};
			 }  
		  },
		  DelData:function(){
			  if(this.checkedNames.length>0){
				  var _this = this;
				  layer.confirm("确定要删除这"+this.checkedNames.length+"笔数据吗?", {
			            btn : [ '确定', '取消' ]//按钮
			        }, function(index) {
			            layer.close(index);
			            var json = createJSON(_this.checkedNames);   //createJSON() 引自js/comm.js
			            _this.checkedNames = [];
						 axios.post('../classifcationinfo/deldata',
								 {
								 json:json
						      })
			    			.then(function (response) {
			    				if(response.data=='ok'){
			    					_this.search_after_update(_this.$refs.pagecomponent.pageBean);
			    				}
			    				if(response.data=='nok'){
			    					layer.msg("出现未知错误,请稍后重试");
			    				}
			    				
			    			})
			    			.catch(function (error) {
			    			    console.log(error);
			    			});
			        }); 
				 
			  }else{
				  layer.msg("请选择要删除的数据");
			  }
		  },
		  NewData:function(){  //打开新增数据弹出层
			  if(this.checkedNames.length>1){
				  layer.msg("请选择0到1笔数据");
			  }else{
				  this.tcc_title = "新增分类";
				  this.isnew = true;
				  this.farther_node_id = this.checkedNames[0];
				  for(var i=0;i<this.datas.length;i++){
					  var b = false;
					  for(var j=0;j<this.datas[i].children.length;j++){
						  if(this.datas[i].children[j].val.classification_ID==this.checkedNames[0]){
							  
							  this.farther_node_name = this.datas[i].children[j].val.classificationName;
							  b = true;
							  break;
						  }
					  }
					  if(b){break;}
				  }
			  	  this.showtcc = {display:'block'};
			  }
		  },
		  UpdateData:function(){  //编辑分类
			     if(this.typeInfo.type_name.trim()==''){
				      this.errInfo.type_name_err = true;
				  }else{
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
			    					layer.msg("编辑成功");
			    					_this.hideTcc();
			    					_this.search_after_update(_this.$refs.pagecomponent.pageBean);
			    				}
			    				if(response.data=="exist"){
			    					layer.msg("该分类名称已存在");
			    				}
			    				if(response.data=="nok"){
			    					layer.msg("出现未知错误,请稍后重试");
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
		    				if(response.data=="ok"){
		    					layer.msg("添加成功");
		    					_this.hideTcc();
		    					_this.search_after_update(_this.$refs.pagecomponent.pageBean);
		    				}
		    				if(response.data=="exist"){
		    					layer.msg("该分类名称已存在");
		    				}
		    				if(response.data=="nok"){
		    					layer.msg("出现未知错误,请稍后重试");
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
		  getClickNode:function(id){
			  var arr = null;
			  for(var i=0;i<this.datas.length;i++){
				  for(var j=0;j<this.datas[i].children.length;j++){
					  var anode = this.datas[i].children[j];
					  if(anode.val.classification_ID==id){
						  arr = [];
						  arr.push(i);
						  arr.push(anode);
						  arr.push(j);
						  return arr;
					  }
				  }
			  }
			  return arr;
		  },
		  dealDigui:function(a_arr,node,flag){
		
			  if(node.children_lg>0){
				  if(flag){
					  if(node.is_open){
						  for(var i=0;i<a_arr.length;i++){
					    	  if(a_arr[i].val.parent_Classification_ID==node.val.classification_ID){
					    		  a_arr[i].is_show = flag;
					    		  this.dealDigui(a_arr,a_arr[i],flag);
					    	  }  
					      }
					  }
				  }else{
					  for(var i=0;i<a_arr.length;i++){
				    	  if(a_arr[i].val.parent_Classification_ID==node.val.classification_ID){
				    		  a_arr[i].is_show = flag;
				    		  this.dealDigui(a_arr,a_arr[i],flag);
				    	  }  
				      }
				  }
			  }
			  
		  },
		  opClose:function(id){
			  var obj = this.getClickNode(id);
			  var click_index = obj[0];
			  var click_node = obj[1];
			  var a_arr = this.datas[click_index].children ;
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
				    				for(var i=0;i<_this.datas.length;i++){
				    					var b = false;  
				    					for(var j=0;j<_this.datas[i].children.length;j++){
				    						  var anode = _this.datas[i].children[j];
				    						  if(anode.val.classification_ID==id){
				    							  
				    							  var t_arr = _this.datas[i].children;
				    	
				    							  var temp = [];
				    							  
				    							  for(var c=0;c<=j;c++){
				    								  temp.push(t_arr[c]);
				    							  }
				    							  for(var c=0;c<data.length;c++){
				    								  var tn = new TreeNode(data[c],anode.depth+1,data[c].children_lg);
				    								  if(data[c].children_lg>0){
				    									  tn.imgsrc = "../image/is-tbarr01.png";
				    								  }
				    								  if(anode.depth+1>0){
				    									  var pad = 26+40*(anode.depth);
				    									  tn.style = {padding: '10px '+pad+'px !important'};
				    								  }
				    								  temp.push(tn);
				    							  }
				    							  for(var c=j+1;c<t_arr.length;c++){
				    								  temp.push(t_arr[c]);
				    							  }
				    							  _this.datas[i].children = temp;
				    							  b = true;
				    						  }
				    					  }
				    					if(b){break;}
				    				  }
				    				click_node.i_have_ajax = true;
				    			})
				    			.catch(function (error) {
				    			    console.log(error);
				    			});
				        }else{
				        	
				        	
				        }
			  
				        click_node.is_open = true;
			  
				        click_node.imgsrc = "../image/is-tbarr02.png";
			      
				        this.dealDigui(a_arr,click_node,true);
			  
				       
			  }else{         //关上
				  
			  
				  click_node.is_open = false;
				  
				  click_node.imgsrc = "../image/is-tbarr01.png";
				  
				  this.dealDigui(a_arr,click_node,false);
			     
			  }
			 
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
		        	var l_index = layer.msg('加载中', {
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
		    				 _this.checkedNames = [];
		    				 var data = JSON.parse(response.data);
		    				 
		    				 _this.datas = [];
		    				 var temp = data.resdata;
		    				 for(var i=0;i<temp.length;i++){
		    					 var tn = new TreeNode(temp[i]);   //TreeNode 引自 comm.js
		    					 _this.datas.push(tn);
		    					 
		    					 var ch_tn = new TreeNode(temp[i],0,temp[i].children_lg);
		    					 tn.children.push(ch_tn);
		    					 
		    					 if(temp[i].children_lg>0){
		    						 ch_tn.imgsrc = "../image/is-tbarr01.png";
		    					 }else{
		    						 ch_tn.imgsrc = "";
		    					 }
		    					 
		    				 }
		    				
		    				 
		    				 
		    				 _this.$refs.pagecomponent.dealAfterSearch(data.rowCount); 
		    				 _this.checkedArr = [];
		    				  for(var i=0;i<data.resdata.length;i++){
		    					  _this.checkedArr.push(data.resdata[i].classification_ID);
		    				  }
		    				  
		    				  if(_this.btn_wy_disabled){   //如果是位移发生的分页查询
		    					  
		    					  var firstNode = _this.datas[0].val;
		    					  var lastNode = _this.datas[_this.datas.length-1].val ;
		    					  var targetNode;
		    					  if(_this.tempNode.val.displayOrder<lastNode.displayOrder){  //上移
		    						  targetNode = lastNode;
		    						  _this.datas[_this.datas.length-1] = _this.tempNode;
		    						  _this.checkedArr[_this.checkedArr.length-1] = _this.tempNode.val.classification_ID;
		    					  }
		    					  if(_this.tempNode.val.displayOrder>firstNode.displayOrder){  //下移
		    						  targetNode = firstNode;
		    						  _this.datas[0] = _this.tempNode;
		    						  _this.checkedArr[0] = _this.tempNode.val.classification_ID;
		    					  }
		    					  var temp_order = _this.tempNode.val.displayOrder ;
	    					      _this.tempNode.val.displayOrder = targetNode.displayOrder;
	    					      targetNode.displayOrder = temp_order;
	    						  
	    					      _this.updateOrder(_this.tempNode.val.classification_ID,_this.tempNode.val.displayOrder,
	    					    		  targetNode.classification_ID,targetNode.displayOrder,-1,-1,_this.datas);
	    					      
	   
		    					  _this.checkedNames.push(_this.tempNode.val.classification_ID);
		    					  
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

</script>
</body>

</html>