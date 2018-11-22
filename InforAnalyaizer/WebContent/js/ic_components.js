﻿﻿/*
 *   Vue组件 JS
 *   
 */


//我的收藏用到的节点类
function CTreeNode(val,depth){   
	this.val = val;
	this.depth = depth;
}


//我的收藏组件
var ic_collectiont = {
		template:'<div class="cy_CIASFE_colle" v-bind:style="collcnt>0?\'background: url(../image/fontend-colle02.png) no-repeat\':\'\'">'+
					'<div class="cy_CIASFE_collebox">'+
						'<div>我的收藏</div>'+
						 '<a v-for="data in c_datas" v-on:click="conllect(data.val.collectionType_ID,data.val.children_lg,$event)" href="#">{{data.val.collectionTypeName}}</a>'+
					'</div>'+
				'</div>',
	    props:['aid','collcnt'],
		data:function(){
			return {
				c_datas:[],     //我的收藏
			}
		},
		methods:{
			 addCnodeChildren:function(ctnode,data){
				  for(var i=0;i<data.length;i++){
					  if(ctnode.val.collectionType_ID==data[i].parent_CollectionType_ID){
						  var acnode = new CTreeNode(data[i],ctnode.depth+1);
						  this.c_datas.push(acnode);
						  if(data[i].children_lg>0){
							  this.addCnodeChildren(acnode,data);
						  }
					  }
				  }
			  },
			conllect:function(id,children_lg,event){
				event.preventDefault();
				if(children_lg==0){
					var _this = this;
					axios.get('../thematicmonitoring/conllect',{
		    			params: {
		    				  Article_ID:_this.aid,
		    				  CollectionType_ID:id
		    				}
		    			})
		    			.then(function (response) {
		    				if(response.data=="ok"){
		    					_this.$parent.updateCollent(_this.aid);
		    					layer.msg(Info.I0023);
		    				}else{
		    					layer.msg(Info.E0022);
		    				}
		    				
		    			})
		    			.catch(function (error) {
		    				console.log(error);
		    				layer.msg(Info.E0022);
		    			});
					
				}
			}
		},
		mounted:function(){
			 var _this = this;
			  axios.get('../thematicmonitoring/getCollectionType')
	  			.then(function (response) {
	  				var data = JSON.parse(response.data);
	  				for(var i=0;i<data.length;i++){
	  					if(data[i].parent_CollectionType_ID==''||data[i].parent_CollectionType_ID=='0'){  //根节点
	  						var ctnode = new CTreeNode(data[i],0);
	  						_this.c_datas.push(ctnode);
	  						if(data[i].children_lg>0){
	  							_this.addCnodeChildren(ctnode,data);
	  						}
	  					}
	  				}
	  			})
	  			.catch(function (error) {
	  			    console.log(error);
	  			});
			  
		}
};


//监测方案组件用到的节点类
function JCNode(i){
	this.val = "监测词组合"+intToChinese(i);   //intToChinese 引自js/comm.js
	this.words = [];
}


//监控方案组件
var ic_jcfa  = {
		
		template:'<div>'+
	'<div class="cy_CIASFE_intmonbodyleft">'+
		'<div class="cy_CIASFE_monpltop">监测方案<a href="#"><img src="../image/monplan-plus.png" v-on:click="NewJCFA()"></a></div>'+
		'<div v-for="(fndata,index) in fa_name_datas" class="cy_CIASFE_monplan">'+
		    '<div class="cy_CIASFE_monplantit">{{fndata.planName}}</div><a href="#"><img v-on:click="editFa(index)" src="../image/monplan-edit.png"></a><a href="#"><img v-on:click="delFA(fndata.plan_ID)" src="../image/monplan-dele.png"></a>'+
		'</div>'+
	'</div>'+
		'<div id="newfadv" class="cy_CIASFE_addmonpl" style="display:none;">'+
			'<div class="cy_CIASFE_addmonpltop"><div class="cy_CIASFE_addmonpltit">{{title}}</div><div class="cy_CMICBMS_addmonplclose" v-on:click="hide()">X</div></div>'+
			'<div class="cy_CIASFE_addmonplbd">'+
				'<div class="cy_CIASFE_addmonplbox"><div class="cy_CIASFE_addmonplbox02">方案名称：</div><input v-model="planinfo_name" type="text" class="cy_CMICBMS_addmonplipbox" placeholder="请输入方案名称"></div>'+
				'<div v-for="(fadata,fi) in fa_datas" class="cy_CIASFE_addmonplbox">'+
				'	<div  class="cy_CIASFE_addmonplbox02">{{fadata.val}}：</div>'+
				'	<div v-for="(wdata,index) in fadata.words" class="cy_CIASFE_addmonplkwed" v-on:click="delglc(fi,index,$event)" v-bind:style="index==0?\'margin-left: 0\':\'\'">{{wdata}}</div>'+
				'    <input v-if="fadata.words.length<4" type="button" v-on:click="addgjc(fi)" value="+添加关联词" v-bind:style="fadata.words.length==0?\'margin-left: 0\':\'\'" class="cy_CIASFE_editkw">'+
				'</div>'+
				'<div class="cy_CIASFE_addmonplbox">'+
				'	<div class="cy_CIASFE_addmonplbox02"></div>'+
				'		<input type="button" value="+添加监测词" v-on:click="addjcc()" class="cy_CIASFE_editkw" style="margin-left: 0;">'+
				'	</div>'+
				'</div>'+
				'<div class="cy_CIASFE_addmonplbox"><div class="cy_CIASFE_addmonplbox02">排除词：</div><input v-model="planinfo_removeWord" type="text" class="cy_CMICBMS_addmonplipbox" placeholder="请输入排除词，词之间用逗号分隔"></div>'+
				'<div class="cy_CIASFE_addmonplbtnbox"><input type="button" class="cy_CIASFE_addmonplcance" v-on:click="hide()" value="取消">'+
				'<input v-if="isnew" type="button" class="cy_CIASFE_addmonpldeter" v-on:click="SaveNewFA()" value="确定"><input v-else type="button" class="cy_CIASFE_addmonpldeter" v-on:click="UpdateFA()" value="确定"></div>'+
			'</div>'+
		'</div>'+
	'</div>',
	data:function(){
		return {
			 isnew:false,
			 edit_id:'',
			 title:'',
			 planinfo_name:'',              //方案名称
		     planinfo_removeWord:'',           //方案排除词
		     fa_datas:[],                   //新增&编辑 监测词方案组合
		     fa_name_datas:[]
		}
	},
	methods:{
		delglc:function(fi,index,event){
			//alert(event.offsetX+"  "+event.offsetY);
			if(event.offsetX>=105&&event.offsetY<=10){
				this.fa_datas[fi].words.splice(index,1);
				if(this.fa_datas[fi].words.length==0){
					this.fa_datas.splice(fi,1);
					for(var i=0;i<this.fa_datas.length;i++){
						this.fa_datas[i].val = "监测词组合"+intToChinese(i+1);
					}
				}
			}
		},
		NewJCFA:function(){
			 this.title = Info.I0017;
			 this.isnew = true;
			 var jcnode = new JCNode(1);
			 this.fa_datas.push(jcnode);
			 $("#newfadv").css("display","block");
		     $("#cy_hidebg").css("display","block");
		     
		},
		editFa:function(index){
			 this.title = Info.I0018;
			 this.isnew = false;
			 this.planinfo_name = this.fa_name_datas[index].planName;
			 this.edit_id  = this.fa_name_datas[index].plan_ID;
			 var _this = this;
			 axios.get('../thematicmonitoring/getDetail',{
					 params:{
						 planid:_this.fa_name_datas[index].plan_ID
				      }
			     })
    			.then(function (response) {
    				 
    				 var data = JSON.parse(response.data);

    				 for(var i=0;i<data.length;i++){
    				    var jcnode = new JCNode(i+1);
    					if(data[i].monitoringWord1!=null){
    						jcnode.words.push(data[i].monitoringWord1);
    					}
    					if(data[i].monitoringWord2!=null){
    						jcnode.words.push(data[i].monitoringWord2);
    					}
    					if(data[i].monitoringWord3!=null){
    						jcnode.words.push(data[i].monitoringWord3);
    					}
    					if(data[i].monitoringWord4!=null){
    						jcnode.words.push(data[i].monitoringWord4);
    					}
    					_this.fa_datas.push(jcnode);
                        _this.planinfo_removeWord = data[i].removeWord;
    				 }
    				
    				 $("#newfadv").css("display","block");
    			     $("#cy_hidebg").css("display","block");
    			})
    			.catch(function (error) {
    			    console.log(error);
    			});
	
		},
		UpdateFA:function(){
			if(this.planinfo_name.trim()==''){
				layer.msg(Info.E0029);
				return;
			}
			 var jcc_json = this.getJCCJson();
		       var _this = this;
	           axios.post('../thematicmonitoring/updatefa',
		   			    {
	        	           plan_id:_this.edit_id,
		   				   planinfo_name:_this.planinfo_name.trim(),
		   				   jcc_json:jcc_json,
		   				   planinfo_removeWord:_this.planinfo_removeWord.trim()
		   				}
		   			)
		   			.then(function (response) {
		   				  if(response.data=="ok"){
		   					  layer.msg(Info.I0012);
		   					  _this.hide();
		   				  	  _this.getAllFA();
		   				  }
		   				  if(response.data=="exist"){
		   					layer.msg(Info.E0030);
		   				  }
		   				  if(response.data=="nok"){
		   				    layer.msg(Info.E0022);
		   				  }
		   			})
		   			.catch(function (error) {
		   				layer.msg(Info.E0022);
		   			});
		},
		hide:function(){
			 $("#newfadv").css("display","none");
		     $("#cy_hidebg").css("display","none");
		     this.planinfo_name = "";
		     this.planinfo_removeWord = "";
		     this.edit_id = "";
		     this.fa_datas = [];
		},
		addjcc:function(){
			var jcnode = new JCNode(this.fa_datas.length+1);
			this.fa_datas.push(jcnode);
		},
		SaveNewFA:function(){
			if(this.planinfo_name.trim()==''){
				layer.msg(Info.E0029);
				return;
			}
	       var jcc_json = this.getJCCJson();
	       var _this = this;
           axios.post('../thematicmonitoring/savenewfa',
	   			    {
	   				   planinfo_name:_this.planinfo_name.trim(),
	   				   jcc_json:jcc_json,
	   				   planinfo_removeWord:_this.planinfo_removeWord.trim()
	   				}
	   			)
	   			.then(function (response) {
	   				  if(response.data=="ok"){
	   					  layer.msg(Info.I0008);
	   					  _this.hide();
	   				  	  _this.getAllFA();
	   				  }
	   				  if(response.data=="exist"){
	   					layer.msg(Info.E0030);
	   				  }
	   				  if(response.data=="nok"){
	   				    layer.msg(Info.E0022);
	   				  }
	   			})
	   			.catch(function (error) {
	   			   
	   			});
		},
		delFA:function(planid){
			  var _this = this;
			  layer.confirm(Info.W0002, {
		            btn : [ '确定', '取消' ]//按钮
		        }, function(index) {
					 axios.get('../thematicmonitoring/delfa',{
						 params:{
							 planid:planid
					      }
							 })
		    			.then(function (response) {
		    				if(response.data=='ok'){
		    					layer.msg(Info.I0014);
		    					_this.getAllFA();
		    				}
		    				if(response.data=='nok'){
		    					layer.msg(Info.E0022);
		    				}
		    			})
		    			.catch(function (error) {
		    			    console.log(error);
		    			});
		        }); 
		},
		getJCCJson:function(){
			var str = '[';
	        for(var i=0;i<this.fa_datas.length;i++){
	        	 if(i==0){
	        		 str+='[';
	        	 }else{
	        		 str+=',[';
	        	 }
	        	for(var j=0;j<this.fa_datas[i].words.length;j++){
	        		if(j==0){
	        			str+='{"jcc":"'+this.fa_datas[i].words[j]+'"}';
	        		}else{
	        			str+=',{"jcc":"'+this.fa_datas[i].words[j]+'"}';
	        		}
	        	}
	        	str+=']';
	        }
	        str+=']';
	        return str;
		},
		addgjc:function(findex){
			var _this = this;
			layer.open({
				id:1,
			        type: 1,
			        title:'添加关联词',
			        skin:'layui-layer-rim',
			        area:['200px', 'auto'],
			        
			        content: ' <div class="row" style="width: 180px;  margin-left:7px; margin-top:10px;">'
			            +'<div class="col-sm-12">'
			            +'<input id="firstpwd" type="text" class="form-control" placeholder="请输入关联词 ">'
			            +'</div>'
			              +'</div>'
			        ,
			        btn:['保存','取消'],
			        btn1: function (index,layero) {
			        	var glc = $.trim($("#firstpwd").val());
			        	if(glc==''){
			        		layer.close(index);
			        	}else{
			        		_this.fa_datas[findex].words.push(glc);
			        		layer.close(index);
			        	}
			    	},
			        btn2:function (index,layero) {
			        	 layer.close(index);
			        }
			 
			    });
		},
		getAllFA:function(){
			this.fa_name_datas = [];
			var _this = this;
	    	axios.get('../thematicmonitoring/getallfa')
				.then(function (response) {
					  var data = JSON.parse(response.data);
					  for(var i=0;i<data.length;i++){
						  _this.fa_name_datas.push(data[i]);
					  }
				})
				.catch(function (error) {

				});
		}
	},
	mounted:function(){
		this.getAllFA();
		
	}
};

//前台页面上部菜单栏组件
var ic_top_menu = {   
		
		template:'<div class="cy_CIASFE_nagiv">'+
		    '<a v-bind:href="mdata.url" v-for="mdata in model"><div class="cy_CIASFE_nagivbox" v-bind:style="mdata.style">{{mdata.name}}</div></a>'+
			'</div>',
	    //props: ['model'],
		data:function(){
    			return {
    				  model:menu_datas,
    				  cur_url : document.URL
    			}
		},
		mounted:function(){
			for(var i=0;i<this.model.length;i++){
				if(this.cur_url.indexOf(this.model[i].url.replace('..',''))>0){
					this.model[i].style = {background: 'url(../image/home-menubg.png)'};
					break;
				}
				if(typeof menu_url_data!="undefined"){
					if(menu_url_data!=null&&this.model[i].url.replace('..','').indexOf(menu_url_data)>0){
						this.model[i].style = {background: 'url(../image/home-menubg.png)'};
						break;
					}
				}
			}
		}
};

//前台页面上部登录用户信息框组件
var ic_user_info = {  //$parent.updateUser
    	template:
    		'<div class="cy_CIASFE_userinfom"><div class="cy_CIASFE_user"><img src="../image/home-user.png"></div><div class="cy_CIASFE_user">{{userData.uname}}<img src="../image/home-arr.png"></div>'+
    		'<div class="cy_CIASFE_usermenu"><a v-on:click="updateUser">个人信息</a><!-- <a href="">修改密码</a> --><a href="#" v-on:click="logout()">退出</a></div>'+
    		'<div id="userform">'+
    		'<div class="cy_hidebg" id="cy_hidebg_temple"></div>'+
    	    '<div id="cy_CMICBMS_add" style="height:600px;">'+
    	     '   <div class="cy_CMICBMS_addtop">'+
    	      '      <div class="cy_CMICBMS_addtit">个人信息</div>'+
    	     '       <div class="cy_CMICBMS_addclose" v-on:click="hide()">X</div>'+
    	     '   </div>'+
    	      '  <div class="cy_CMICBMS_addtb">'+
    	      '      <div class="cy_CMICBMS_addinput">用户角色：{{userData.urolename}}</div><br>'+
    	      '      <div class="cy_CMICBMS_addinput">用户名：{{userData.uid}}</div><br>'+
    	       '     <div class="cy_CMICBMS_addinput">姓名：{{userData.uname}}</div><br>'+
    	      '      <div class="cy_CMICBMS_addinput">'+
    	      '          <span>*</span>&nbsp;&nbsp;旧密码：<input type="password" placeholder="输入原密码" v-model="uPwd">'+
    	       '     </div>'+
    	      '      <div class="cy_CMICBMS_addinput">'+
    	       '         <span>*</span>设置密码：<input type="password" placeholder="设置密码"'+
    	        '                v-model="changePwd"       >'+
    	       '     </div>'+
    	      '      <div  class="cy_CMICBMS_addinput">'+
    	       '         <span>*</span>确认密码：<input type="password" placeholder="确认密码"'+
    	       '                   v-model="checkPwd"     >'+
    	        '    </div>'+
    	        '    <div class="cy_CMICBMS_addinput">所属部门：{{userData.udep}}</div><br>'+
    	        '    <div class="cy_CMICBMS_addinput">手机号码：{{userData.utel}}</div><br>'+
    	        '    <div class="cy_CMICBMS_addinput">邮箱地址：{{userData.uemail}}</div><br>'+

    	       '     <div style="float:right;margin: 20px 20px 0 0;">'+
    	        '        <input type="button" value="确定" class="cy_CMICBMS_schbtn"'+
    	       '               v-on:click="submit()" >'+
    	        '    </div>'+
    	       ' </div>'+
    	    '</div>'+
    	   '</div>'+
    		'</div>',

        data:function(){
        	return {
        		userData:{},
        		changePwd:'',
        	    checkPwd:'',
        	    uPwd:''
        	}
        },
        methods:{
        	updateUser:function(){
        		console.log(document.getElementById("cy_hidebg_temple").style);
        		document.getElementById("cy_hidebg_temple").style.display="block";  //显示隐藏层
        	   	document.getElementById("cy_CMICBMS_add").style.display="block";  //显示弹出层
        	},
        	logout:function(){
        		layer.confirm(Info.W0003, {
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
        	},
        	hide:function(){
        		this.changePwd = '';
        		this.checkPwd = '';
        		this.uPwd = '';
        		document.getElementById("cy_hidebg_temple").style.display="none";
        		document.getElementById("cy_CMICBMS_add").style.display="none";
        	},
        	submit:function(){
        		var _this = this;
                var password="";

                if(_this.changePwd.trim()!=_this.checkPwd.trim()){
                    layer.msg(Info.E0004);
                    return false;
                }else if(_this.changePwd.trim()==""){
                    layer.msg(Info.E0014);
                    return false;
                }
                var user = _this.userData;
                axios.get("../userfront/updateUser", {
                    params: {
                        unum:user.unum,
                        uid:user.uid,
                        uname:user.uname,
                        udep:user.udep,
                        utel:user.utel,
                        uemail:user.uemail,
                        upwd:user.upwd,
                        urole:user.urole,
                        ustatus:user.ustatus,
                        oripwd:_this.uPwd.trim(),
                        changePwd:_this.changePwd.trim()
                    }
                }).then(function (response) {
                    if (response.data == "ok") {
                        _this.hide();
                        layer.msg(Info.I0012);
                    } else if(response.data=="pwd not right"){
                    	 layer.msg(Info.E0025);
                    }else if(response.data=="fault"){
						layer.msg(Info.I0013);
					}
                })
                    .catch(function (error) {
                        console.log(error);
                    });
        	}
        },
        mounted:function(){
        	var that = this;
   			axios.get('../yhgl/user/thisUser')
    			.then(function (response) {
						that.userData = response.data;
    			})
    			.catch(function (error) {
    			    console.log(error);
    			});
        }
    };



//前台分页组件
var ic_front_pager = {
        template:'<div  class="cy_CIASFE_ctpage">'+
			'<div v-if="pageBean.rowCount>0" class="cy_CIASFE_ctpg"><div class="cy_CIASFE_ctpg01">共{{pageBean.rowCount}}条</div>'+
				'<div class="cy_CIASFE_ctpg02">'+
			    '<ul >'+
			    '<li ><a href="#" v-bind:disabled="pageBean.pageNow==1" v-on:click="showPagePre($event)">上一页</a></li>'+
				'<li v-for="ys in ysdatas" v-bind:class="{cy_CIASFE_ctpgfc:ys==pageBean.pageNow}"><a href="#"  v-on:click="showPage(ys,$event)" v-text="ys"></a></li>'+
				'<li style="border-right: 1px solid #e0e0e0"><a href="#"  v-on:click="showPageNext($event)">下一页</a></li></ul></div>'+
				'<div class="cy_CIASFE_ctpg03">到第<input type="text" v-model="pageBean.jump_page" class="cy_CIASFE_pgbox">页<input v-on:click="showJumpPage($event)" type="button" value="确定" class="cy_CIASFE_pgbtn"></div>'+
			'</div>'+
		'</div>',
			
		 props:['model'],
		 data:function(){
		     return {
				 pageBean:{
				     pageNow:1,              //当前第几页
					 pageSize:10,            //总页数
					 rowCount:0,             //总条目数
					 rowSize:[10,20,30,40],              //每页显示条目数
					 jr_rowCount:0  ,        //金融计数法的总条目数
					 row_show_start:0,       //从第几条开始显示
					 row_show_end:0,         //显示到第几条
					 rs_selected:10,          //选中的是哪一条  每页显示条目数
					 jump_page:1              //跳转框的页数
				 },
				 ysdatas : [],
				 max_length:6                //有多少个页面框框
			 }
		 },
		 methods:{
		     showPage:function(index,event){
		    	 event.preventDefault();
		    	
		    	 if(index!='...'){
				     this.pageBean.pageNow = index;
					 this.pageBean.jump_page = index;
					 //this.$parent.clear();
					 if(this.model==null){
						 this.$parent.search(this.pageBean);
					 }else {
						 var f = eval("this.$parent."+this.model);
						 new f(this.pageBean);
					 }
		    	 }
			 },
			 showFirstPage:function(event){
				
			      if(this.pageBean.pageNow>1){
				     this.showPage(1,event);
				  }else{
					  event.preventDefault();
				  }
			 },
		     showLastPage:function(event){
				 if(this.pageBean.pageNow<this.pageBean.pageSize){
			         this.showPage(this.pageBean.pageSize,event);
				 }else{
					  event.preventDefault();
				  }
			 },
			 showPagePre:function(event){
			     if(this.pageBean.pageNow>1){
				     this.pageBean.pageNow-=1;
					 this.showPage(this.pageBean.pageNow,event);
				 }else{
					  event.preventDefault();
				  }
			 },
			 showPageNext:function(event){
			    if(this.pageBean.pageNow<this.pageBean.pageSize){
				     this.pageBean.pageNow+=1;
					 this.showPage(this.pageBean.pageNow,event);
				}else{
					  event.preventDefault();
				  }
			 },
			 showJumpPage:function(event){
			     if(this.pageBean.jump_page>=1&&this.pageBean.jump_page<=this.pageBean.pageSize){
				     this.showPage(parseInt(this.pageBean.jump_page),event);
				 }else{
					  event.preventDefault();
				  }
			 },
			 schange:function(event){
			    this.pageBean.pageSize = parseInt(Math.ceil(this.pageBean.rowCount / this.pageBean.rs_selected)) ;
			    this.showPage(1,event);
			 },
			 dealAfterSearch:function(rowCount){
				this.pageBean.rowCount = rowCount;
				this.pageBean.jr_rowCount = (rowCount+"").replace(/[0-9]+?(?=(?:([0-9]{3}))+$)/g,function(a){return a+','});
 				this.pageBean.pageSize =  parseInt(Math.ceil(this.pageBean.rowCount / this.pageBean.rs_selected));
 				if(this.pageBean.rowCount=='0'){
 					this.pageBean.row_show_start = 0;
 				}else{
 					this.pageBean.row_show_start = (this.pageBean.pageNow-1)*this.pageBean.rs_selected+1;
 				}
 				this.pageBean.row_show_end = this.pageBean.pageNow*this.pageBean.rs_selected;
 				if(this.pageBean.row_show_end>this.pageBean.rowCount){
 					this.pageBean.row_show_end = this.pageBean.rowCount;
 				}
 				
 				this.ysdatas = [];
				if(this.pageBean.pageSize<=this.max_length){
					for(var i=1;i<=this.pageBean.pageSize;i++){
						this.ysdatas.push(i);
					}
				}else if(this.pageBean.pageSize - this.pageBean.pageNow<=this.max_length-1){
					var start = this.pageBean.pageSize - this.max_length + 1;
					for(var i=start;i<=this.pageBean.pageSize;i++){
						this.ysdatas.push(i);
					}
				}else{
					var lg = this.pageBean.pageNow + (this.max_length-3); 
					for(var i=this.pageBean.pageNow;i<=lg;i++){
						this.ysdatas.push(i);
					}
					this.ysdatas.push('...');
					this.ysdatas.push(this.pageBean.pageSize);
				}
			 }
			 
		 }
  };



//后台分页组件
var ic_pager = {
        template:'<div class="cy_CMICBMS_umpage">'+
			'<div v-if="pageBean.pageSize>=1" class="cy_CMICBMS_umrc">显示第{{pageBean.row_show_start}}到{{pageBean.row_show_end}}条记录，总共{{pageBean.jr_rowCount}}条记录</div>'+
			'<div class="cy_CMICBMS_umpg">'+
			'<div>'+
			'<ul v-if="pageBean.pageSize>=1">'+
			'<li><a href="#" v-on:click="showFirstPage($event)"><span><<</span></a></li>'+
			'<li><a href="#" v-on:click="showPagePre($event)"><span><</span></a></li>'+
			'<li v-for="ys in ysdatas"><a href="#" v-on:click="showPage(ys,$event)" v-bind:class="{cy_CMICBMS_umpgfc:ys==pageBean.pageNow}" v-text="ys"></a></li>'+
			'<li><a href="#" v-on:click="showPageNext($event)">></a></li>'+
			'<li><a href="#" v-on:click="showLastPage($event)">>></a></li>'+
			'</ul>'+
			'</div>'+
			'</div></div></div>',
			
		 props:['model'],
		 data:function(){
		     return {
				 pageBean:{
				     pageNow:1,              //当前第几页
					 pageSize:10,            //总页数
					 rowCount:0,             //总条目数
					 rowSize:[10,20,30,40],              //每页显示条目数
					 jr_rowCount:0  ,        //金融计数法的总条目数
					 row_show_start:0,       //从第几条开始显示
					 row_show_end:0,         //显示到第几条
					 rs_selected:10,          //选中的是哪一条  每页显示条目数
					 jump_page:1              //跳转框的页数
				 },
				 ysdatas : [],
				 max_length:6                //有多少个页面框框
			 }
		 },
		 methods:{
		     showPage:function(index,event){
		    	 event.preventDefault();
		    	
		    	 if(index!='...'){
				     this.pageBean.pageNow = index;
					 this.pageBean.jump_page = index;
					 //this.$parent.clear();
					 if(this.model==null){
						 this.$parent.search(this.pageBean);
					 }else {
						 var f = eval("this.$parent."+this.model);
						 new f(this.pageBean);
					 }
		    	 }
			 },
			 showFirstPage:function(event){
				
			      if(this.pageBean.pageNow>1){
				     this.showPage(1,event);
				  }else{
					  event.preventDefault();
				  }
			 },
		     showLastPage:function(event){
				 if(this.pageBean.pageNow<this.pageBean.pageSize){
			         this.showPage(this.pageBean.pageSize,event);
				 }else{
					  event.preventDefault();
				  }
			 },
			 showPagePre:function(event){
			     if(this.pageBean.pageNow>1){
				     this.pageBean.pageNow-=1;
					 this.showPage(this.pageBean.pageNow,event);
				 }else{
					  event.preventDefault();
				  }
			 },
			 showPageNext:function(event){
			    if(this.pageBean.pageNow<this.pageBean.pageSize){
				     this.pageBean.pageNow+=1;
					 this.showPage(this.pageBean.pageNow,event);
				}else{
					  event.preventDefault();
				  }
			 },
			 showJumpPage:function(event){
			     if(this.pageBean.jump_page>=1&&this.pageBean.jump_page<=this.pageBean.pageSize){
				     this.showPage(parseInt(this.pageBean.jump_page),event);
				 }else{
					  event.preventDefault();
				  }
			 },
			 schange:function(event){
			    this.pageBean.pageSize = parseInt(Math.ceil(this.pageBean.rowCount / this.pageBean.rs_selected)) ;
			    this.showPage(1,event);
			 },
			 dealAfterSearch:function(rowCount){
				this.pageBean.rowCount = rowCount;
				this.pageBean.jr_rowCount = (rowCount+"").replace(/[0-9]+?(?=(?:([0-9]{3}))+$)/g,function(a){return a+','});
 				this.pageBean.pageSize =  parseInt(Math.ceil(this.pageBean.rowCount / this.pageBean.rs_selected));
 				if(this.pageBean.rowCount=='0'){
 					this.pageBean.row_show_start = 0;
 				}else{
 					this.pageBean.row_show_start = (this.pageBean.pageNow-1)*this.pageBean.rs_selected+1;
 				}
 				this.pageBean.row_show_end = this.pageBean.pageNow*this.pageBean.rs_selected;
 				if(this.pageBean.row_show_end>this.pageBean.rowCount){
 					this.pageBean.row_show_end = this.pageBean.rowCount;
 				}
 				
 				this.ysdatas = [];
				if(this.pageBean.pageSize<=this.max_length){
					for(var i=1;i<=this.pageBean.pageSize;i++){
						this.ysdatas.push(i);
					}
				}else if(this.pageBean.pageSize - this.pageBean.pageNow<=this.max_length-1){
					var start = this.pageBean.pageSize - this.max_length + 1;
					for(var i=start;i<=this.pageBean.pageSize;i++){
						this.ysdatas.push(i);
					}
				}else{
					var lg = this.pageBean.pageNow + (this.max_length-3); 
					for(var i=this.pageBean.pageNow;i<=lg;i++){
						this.ysdatas.push(i);
					}
					this.ysdatas.push('...');
					this.ysdatas.push(this.pageBean.pageSize);
				}
			 }
			 
		 }
  };



var ic_l_pager = {
		
		template:'	<div class="cy_CMICBMS_infedpg"><div v-if="pageBean.pageSize>=1" class="cy_CMICBMS_infedrc">共{{pageBean.jr_rowCount}}条记录</div>'+
					'<div>'+
					'<ul v-if="pageBean.pageSize>=1">'+
					'<li><a href="#" v-on:click="showFirstPage($event)"><span><<</span></a></li>'+
					'<li><a href="#" v-on:click="showPagePre($event)"><span><</span></a></li>'+
					'<li v-for="ys in ysdatas"><a href="#" v-on:click="showPage(ys,$event)" v-bind:class="{cy_CMICBMS_umpgfc:ys==pageBean.pageNow}" v-text="ys"></a></li>'+
					'<li><a href="#" v-on:click="showPageNext($event)">></a></li>'+
					'<li><a href="#" v-on:click="showLastPage($event)">>></a></li></ul>'+
					'</div></div>',
	
	 props:['model'],
	 data:function(){
	     return {
			 pageBean:{
			     pageNow:1,              //当前第几页
				 pageSize:10,            //总页数
				 rowCount:0,             //总条目数
				 rowSize:[10,20,30,40],              //每页显示条目数
				 jr_rowCount:0  ,        //金融计数法的总条目数
				 row_show_start:0,       //从第几条开始显示
				 row_show_end:0,         //显示到第几条
				 rs_selected:10,          //选中的是哪一条  每页显示条目数
				 jump_page:1              //跳转框的页数
			 },
			 ysdatas : [],
			 max_length:6                //有多少个页面框框
		 }
	 },
	 methods:{
	     showPage:function(index,event){
	    	 event.preventDefault();
	    	
	    	 if(index!='...'){
			     this.pageBean.pageNow = index;
				 this.pageBean.jump_page = index;
				 if(this.model==null){
					 this.$parent.search(this.pageBean);
				 }else {
					 if(this.model=='search_left'){
						 this.$parent.search_left(this.pageBean);
					 }
					 if(this.model=='search_right'){
						 this.$parent.search_right(this.pageBean);
					 }
				 }
	    	 }
		 },
		 showFirstPage:function(event){
			
		      if(this.pageBean.pageNow>1){
			     this.showPage(1,event);
			  }else{
				  event.preventDefault();
			  }
		 },
	     showLastPage:function(event){
			 if(this.pageBean.pageNow<this.pageBean.pageSize){
		         this.showPage(this.pageBean.pageSize,event);
			 }else{
				  event.preventDefault();
			  }
		 },
		 showPagePre:function(event){
		     if(this.pageBean.pageNow>1){
			     this.pageBean.pageNow-=1;
				 this.showPage(this.pageBean.pageNow,event);
			 }else{
				  event.preventDefault();
			  }
		 },
		 showPageNext:function(event){
		    if(this.pageBean.pageNow<this.pageBean.pageSize){
			     this.pageBean.pageNow+=1;
				 this.showPage(this.pageBean.pageNow,event);
			}else{
				  event.preventDefault();
			  }
		 },
		 showJumpPage:function(event){
		     if(this.pageBean.jump_page>=1&&this.pageBean.jump_page<=this.pageBean.pageSize){
			     this.showPage(parseInt(this.pageBean.jump_page),event);
			 }else{
				  event.preventDefault();
			  }
		 },
		 schange:function(event){
		    this.pageBean.pageSize = parseInt(Math.ceil(this.pageBean.rowCount / this.pageBean.rs_selected)) ;
		    this.showPage(1,event);
		 },
		 dealAfterSearch:function(rowCount){
			this.pageBean.rowCount = rowCount;
			this.pageBean.jr_rowCount = (rowCount+"").replace(/[0-9]+?(?=(?:([0-9]{3}))+$)/g,function(a){return a+','});
				this.pageBean.pageSize =  parseInt(Math.ceil(this.pageBean.rowCount / this.pageBean.rs_selected));
				if(this.pageBean.rowCount=='0'){
					this.pageBean.row_show_start = 0;
				}else{
					this.pageBean.row_show_start = (this.pageBean.pageNow-1)*this.pageBean.rs_selected+1;
				}
				this.pageBean.row_show_end = this.pageBean.pageNow*this.pageBean.rs_selected;
				if(this.pageBean.row_show_end>this.pageBean.rowCount){
					this.pageBean.row_show_end = this.pageBean.rowCount;
				}
				
				this.ysdatas = [];
			if(this.pageBean.pageSize<=this.max_length){
				for(var i=1;i<=this.pageBean.pageSize;i++){
					this.ysdatas.push(i);
				}
			}else if(this.pageBean.pageSize - this.pageBean.pageNow<=this.max_length-1){
				var start = this.pageBean.pageSize - this.max_length + 1;
				for(var i=start;i<=this.pageBean.pageSize;i++){
					this.ysdatas.push(i);
				}
			}else{
				var lg = this.pageBean.pageNow + (this.max_length-3); 
				for(var i=this.pageBean.pageNow;i<=lg;i++){
					this.ysdatas.push(i);
				}
				this.ysdatas.push('...');
				this.ysdatas.push(this.pageBean.pageSize);
			}
		 }
		 
	 }
		
};