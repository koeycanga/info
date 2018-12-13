﻿/*
 *   Vue组件 JS
 *   
 */


/**
 *
 * 组件用到的信息提示Info  在引入组件时需在页面引入相关信息
 * 
ic_collectiont   I0023   E0022

ic_jcfa          I0017  I0018  E0029  I0012  E0030  E0022  I0008  E0030  I0014 E0052  I0029

ic_user_info    E0004  E0014  I0012  E0025  I0013 W0003
 */


//我的收藏用到的节点类
function CTreeNode(val,depth){
	this.val = val;
	this.depth = depth;
}


//我的收藏组件
var ic_collectiont = {
		template:'<div class="cy_CIASFE_colle" v-bind:style="collcnt>0?\'background: url(../image/fontend-colle02.png) no-repeat\':\'\'">'+
					'<div class="cy_CIASFE_decollebox">'+
						'<div>我的收藏</div>'+
						 '<a v-for="data in collect_datas" v-on:click="conllect(data.val.collectionType_ID,$event)" href="#" style="text-align: left">'+
                         '{{data.val.collectionTypeName}}</a>'+ //<span v-for="n in data.depth+1">&emsp;</span>
					'</div>'+
				'</div>',
	    props:['aid','collcnt','collect_datas'],
		data:function(){
			return {
				//c_datas:[],     //我的收藏
			}
		},
		methods:{

			conllect:function(id,event){
				event.preventDefault();
				
				if(this.collcnt!=0){
					layer.msg("该文章已经被收藏，详情请到我的收藏模块查看");
					return;
				}
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
		'<div v-for="(fndata,index) in fa_name_datas" class="cy_CIASFE_monplan" v-bind:style="sel_index==index?\'background-color: #86c1ea;\':\'\'">'+
		    '<div class="cy_CIASFE_monplantit" style="cursor:pointer;"  v-on:click="searchByFA(fndata.plan_ID,index)">{{fndata.planName}}</div><a href="#"><img v-on:click="editFa(index)" src="../image/monplan-edit.png"></a><a href="#"><img v-on:click="delFA(fndata.plan_ID)" src="../image/monplan-dele.png"></a>'+
		'</div>'+
	'</div>'+
		'<div id="newfadv" class="cy_CIASFE_addmonpl" style="display:none;">'+
			'<div class="cy_CIASFE_addmonpltop"><div class="cy_CIASFE_addmonpltit">{{title}}</div><div class="cy_CMICBMS_addmonplclose" v-on:click="hide()">X</div></div>'+
			'<div class="cy_CIASFE_addmonplbd">'+
			'<div class="cy_CIASFE_addmonplbox"><div class="cy_CIASFE_addmonplbox02">监测时间</div>&nbsp;<input type="text" value=""  style="width:70px;" placeholder="请选择日期" readonly  id="theDate3">&nbsp;<select id="theTime3"><option v-for="tdata in time_ops" v-bind:value="tdata">{{tdata}}</option></select>&nbsp;——&nbsp;<input type="text" value="" style="width:70px;" placeholder="请选择日期" readonly id="theDate4">&nbsp;<select id="theTime4"><option v-for="tdata in time_ops" v-bind:value="tdata">{{tdata}}</option></select></div>'+
				'<div class="cy_CIASFE_addmonplbox">'+
			    '<div class="cy_CIASFE_addmonplbox02">方案名称：</div><input v-model="planinfo_name" type="text" class="cy_CMICBMS_addmonplipbox" placeholder="请输入方案名称"></div>'+
				'<div v-for="(fadata,fi) in fa_datas" class="cy_CIASFE_addmonplbox">'+
				'	<div  class="cy_CIASFE_addmonplbox02">{{fadata.val}}：</div>'+
				'	<div v-for="(wdata,index) in fadata.words" class="cy_CIASFE_addmonplkwed" v-on:click="delglc(fi,index,$event)" v-bind:style="index==0?\'margin-left: 0\':\'\'">{{wdata}}</div>'+
				'    <input v-if="fadata.words.length<4" type="button" v-on:click="addgjc(fi)" value="+添加关联词" v-bind:style="fadata.words.length==0?\'margin-left: 0\':\'\'" class="cy_CIASFE_editkw">'+
				'</div>'+
				'<div class="cy_CIASFE_addmonplbox" v-show="jccnum<3">'+
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
			 isfirst:true,
			 isnew:false,
			 sel_index:-1,
			 edit_id:'',
			 title:'',
			 planinfo_name:'',              //方案名称
		     planinfo_removeWord:'',           //方案排除词
		     fa_datas:[],                   //新增&编辑 监测词方案组合
		     fa_name_datas:[],
		     jccnum:1,
		     time_ops:['00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23']
		}
	},
	methods:{
		searchByFA:function(plan_ID,index){
			this.sel_index = index;
			this.$parent.searchByFA(plan_ID);
		},
		delglc:function(fi,index,event){
			//alert(event.offsetX+"  "+event.offsetY);
			if(event.offsetX>=105&&event.offsetY<=10){
				this.fa_datas[fi].words.splice(index,1);
				if(this.fa_datas[fi].words.length==0){
					this.fa_datas.splice(fi,1);
					for(var i=0;i<this.fa_datas.length;i++){
						this.fa_datas[i].val = "监测词组合"+intToChinese(i+1);
					}
					this.jccnum--;
				}
			}
		},
		NewJCFA:function(){              
			 this.title = Info.I0017;
			 this.isnew = true;
			 this.jccnum = 1;
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
			 var startdate = this.fa_name_datas[index].planStartTime.split(" ");
			 document.getElementById("theDate3").value = startdate[0];
			 if(startdate.length==2){document.getElementById("theTime3").value = startdate[1].substring(0,2);}
			 var enddate = this.fa_name_datas[index].planEndTime.split(" ");
			 document.getElementById("theDate4").value = enddate[0];
			 if(enddate.length==2){document.getElementById("theTime4").value = enddate[1].substring(0,2);}
			 
			 var _this = this;
			 axios.get('../thematicmonitoring/getDetail',{
					 params:{
						 planid:_this.fa_name_datas[index].plan_ID
				      }
			     })
    			.then(function (response) {

    				 var data = JSON.parse(response.data);
    				 
    				 _this.fa_datas = [];
    				 
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
                     
    				 _this.jccnum = _this.fa_datas.length;

    				 $("#newfadv").css("display","block");
    			     $("#cy_hidebg").css("display","block");
    			})
    			.catch(function (error) {
    			    console.log(error);
    			});

		},
		compareDate:function(fromDate,toDate){  //比较fromDate和toDate日期的先后，如果fromDate比toDate晚返回true   否则返回 false
			var fds = fromDate.split(" ");
			var tds = toDate.split(" ");
			
			var fds_date = fds[0].split("\/");
			var tds_date = tds[0].split("\/");
			if(fds_date[0]>tds_date[0]){
				return true;
			}
			if(fds_date[0]==tds_date[0]&&fds_date[1]>tds_date[1]){
				return true;
			}
			if(fds_date[0]==tds_date[0]&&fds_date[1]==tds_date[1]&&fds_date[2]>tds_date[2]){
				return true;
			}
			
			if(fds[0]==tds[0]&&fds[1]>tds[1]){
				return true;
			}
			return false;
		},
		dealRemoveWord:function(word){       //处理排除词    去掉其中重复及为空的元素
			word = word.replace(/，/ig,',');
			var ws  = word.split(",");
			var map = new Map();
			for(var i=ws.length-1;i>=0;i--){
				if(map.get(ws[i].trim())!=null){
					ws.splice(i,1);
				}else{
					map.set(ws[i],ws[i]);
				}
			}
			var res = "";
			var cnt = 0;
			for(var i=0;i<ws.length;i++){
				if(ws[i].trim()!=''){
					if(cnt==0){
						res = ws[i].trim();
						cnt++;
					}else{
						res += ","+ws[i].trim();
					}
				}
			}
			return res;
		},
		UpdateFA:function(){                                 //更新方案
			if(this.planinfo_name.trim()==''){
				layer.msg(Info.E0029);
				return;
			}
			this.planinfo_removeWord = this.dealRemoveWord(this.planinfo_removeWord.trim());
			 var jcc_json = this.getJCCJson();
			 if(jcc_json=='[]'&&this.planinfo_removeWord==''){
				 layer.msg('请输入监测词或排除词');
				 return;
			 }
			 var fromDate = document.getElementById("theDate3").value+" "+document.getElementById("theTime3").value;
			 var toDate = document.getElementById("theDate4").value+" "+document.getElementById("theTime4").value;
             if(document.getElementById("theDate3").value==''){fromDate='';}
             if(document.getElementById("theDate4").value==''){toDate='';}
			 if(fromDate!=''&&toDate!=''&&this.compareDate(fromDate,toDate)){
				 layer.msg(Info.E0052);
				 return;
			 }
		       var _this = this;
	           axios.post('../thematicmonitoring/updatefa',
		   			    {
	        	           plan_id:_this.edit_id,
		   				   planinfo_name:_this.planinfo_name.trim(),
		   				   jcc_json:jcc_json,
		   				   planinfo_removeWord:_this.planinfo_removeWord,
		   				   fromDate:fromDate,
		   				   toDate:toDate
		   				}
		   			)
		   			.then(function (response) {
		   				  if(response.data=="ok"){
		   					  layer.msg(Info.I0029);
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
		     document.getElementById("theDate3").value = ''
			 document.getElementById("theTime3").value = '00';
			 document.getElementById("theDate4").value = ''
			 document.getElementById("theTime4").value = '00';
		},
		addjcc:function(){
			var jcnode = new JCNode(this.fa_datas.length+1);
			this.fa_datas.push(jcnode);
			this.jccnum++;
		},
		SaveNewFA:function(){
			if(this.planinfo_name.trim()==''){
				layer.msg(Info.E0029);
				return;
			}
			this.planinfo_removeWord = this.dealRemoveWord(this.planinfo_removeWord.trim());
	       var jcc_json = this.getJCCJson();
	       if(jcc_json=='[]'&&this.planinfo_removeWord==''){
	    	   layer.msg('请输入监测词或排除词');
	    	   return;
	       }
	       var fromDate = document.getElementById("theDate3").value+" "+document.getElementById("theTime3").value;
		   var toDate = document.getElementById("theDate4").value+" "+document.getElementById("theTime4").value;
           if(document.getElementById("theDate3").value==''){fromDate='';}
           if(document.getElementById("theDate4").value==''){toDate='';}
			 if(fromDate!=''&&toDate!=''&&this.compareDate(fromDate,toDate)){
				 layer.msg(Info.E0052);
				 return;
			 }
	       var _this = this;
           axios.post('../thematicmonitoring/savenewfa',
	   			    {
	   				   planinfo_name:_this.planinfo_name.trim(),
	   				   jcc_json:jcc_json,
	   				   planinfo_removeWord:_this.planinfo_removeWord,
	   				   fromDate:document.getElementById("theDate3").value+" "+document.getElementById("theTime3").value,
	   				   toDate:document.getElementById("theDate4").value+" "+document.getElementById("theTime4").value
	   				}
	   			)
	   			.then(function (response) {
	   				  if(response.data=="ok"){
	   					  layer.msg(Info.I0029);
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
			for(var i=0;i<this.fa_datas.length;i++){  //去除重复的元素
				var map = new Map();
				for(var j=this.fa_datas[i].words.length-1;j>=0;j--){
					if(map.get(this.fa_datas[i].words[j].trim())==null){
						map.set(this.fa_datas[i].words[j].trim(),this.fa_datas[i].words[j].trim());
					}else{
						this.fa_datas[i].words.splice(i,1);  //删除
					}
				}
			}
			var str = '[';
			var cnt = 0;
	        for(var i=0;i<this.fa_datas.length;i++){  //将敏感词和排除词组装成json字面量
	        	 if(this.fa_datas[i].words.length>0){
		        	 if(cnt==0){
		        		 str+='[';
		        		 cnt++;
		        	 }else{
		        		 str+=',[';
		        	 }
		        	for(var j=0;j<this.fa_datas[i].words.length;j++){
		        		if(j==0){
		        			str+='{"jcc":"'+this.fa_datas[i].words[j].trim()+'"}';
		        		}else{
		        			str+=',{"jcc":"'+this.fa_datas[i].words[j].trim()+'"}';
		        		}
		        	}
		        	str+=']';
	        	 }
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
					  
					  _this.jccnum = data.length;
					  for(var i=0;i<data.length;i++){
						  _this.fa_name_datas.push(data[i]);
					  }
					  if(_this.isfirst&&data.length>0){
						  _this.searchByFA(data[0].plan_ID,0);
					  }
					  _this.isfirst = false;
				})
				.catch(function (error) {

				});
		}
	},
	mounted:function(){
		this.getAllFA();
		laydate.render({
			  elem: '#theDate3', //指定元素
			  type:'date',
			  format:'yyyy/MM/dd'
			});
		laydate.render({
			  elem: '#theDate4', //指定元素
			  type:'date',
			  format:'yyyy/MM/dd'
			});
	}
};

//前台页面上部菜单栏组件
var ic_top_menu = {

		template:'<div class="cy_CIASFE_nagiv">'+
		    '<a v-bind:href="mdata.url" v-for="mdata in model"><div v-bind:class="mdata.style">{{mdata.name}}</div></a>'+
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
					this.model[i].style = "cy_CIASFE_nagivboxchk";//{background: 'url(../image/home-menubg.png)'};
					
				}else{
					if(typeof menu_url_data!="undefined"){
						
						if(menu_url_data!=null&&this.model[i].url.indexOf(menu_url_data)>=0){
							
							this.model[i].style = "cy_CIASFE_nagivboxchk";
						}else{
							this.model[i].style = "cy_CIASFE_nagivboxchk_no";
						}
					}else{
						this.model[i].style = "cy_CIASFE_nagivboxchk_no";
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
    	      '   <div ><font color="black" size="3" > 用户角色：{{userData.urolename}}</font></div>'+
    	      '     <div ><font color="black" size="3" >用户名：{{userData.uid}}</font></div>'+
    	       '  <div ><font color="black" size="3" >姓名： {{userData.uname}}</font></div>'+
    	      '      <div align="center">'+
    	      '          <span>*</span>&nbsp;&nbsp;<font color="black" size="3" >旧密码：</font><input class="cy_CMICBMS_addinput" type="password" placeholder="输入原密码" v-model="uPwd" maxlength="32">'+
    	       '     </div>'+
    	      '      <div align="center">'+
    	       '         <span>*</span><font color="black" size="3">新密码：</font></span><input class="cy_CMICBMS_addinput" type="password" placeholder="设置密码"'+
    	        '                v-model="changePwd" maxlength="32">'+
    	       '     </div>'+
    	      '      <div  align="center">'+
    	       '         <span>*</span><font color="black" size="3">确认密码：</font><input class="cy_CMICBMS_addinput" type="password" placeholder="确认密码"'+
    	       '                   v-model="checkPwd" maxlength="32">'+
    	        '    </div>'+
    	        '    <div ><font color="black" size="3" >所属部门：{{userData.udep}}</font></div>'+
    	        '    <div ><font color="black" size="3" >手机号码：{{userData.utel}}</font></div>'+
    	        '    <div ><font color="black" size="3" >邮箱地址：{{userData.uemail}}</font></div>'+

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
   			axios.get('../userfront/thisUser')
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