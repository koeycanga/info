﻿/*
 *   Vue组件 JS
 *   
 */

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
			}
		}
};

//前台页面上部登录用户信息框组件
var ic_user_info = {
   	 
    	template:'<div class="cy_CIASFE_userinfom"><div class="cy_CIASFE_user"><img src="../image/home-user.png"></div><div class="cy_CIASFE_user">{{userData.uname}}<img src="../image/home-arr.png"></div>'+
    		'<div class="cy_CIASFE_usermenu"><a href="" onClick="show();">个人信息</a><!-- <a href="">修改密码</a> --><a href="#" v-on:click="logout()">退出</a></div></div>',
        data:function(){
        	return {
        		userData:{}
        	}
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
        },
        mounted:function(){
        	var that = this;
   			axios.get('../user/thisUser')
    			.then(function (response) {
						that.userData = response.data;
    			})
    			.catch(function (error) {
    			    console.log(error);
    			});
        }
    };


//分页组件
var ic_pager = {
        template:'<div class="cy_CMICBMS_umpage">'+
			'<div class="cy_CMICBMS_umrc">显示第{{pageBean.row_show_start}}到{{pageBean.row_show_end}}条记录，总共{{pageBean.jr_rowCount}}条记录</div>'+
			'<div class="cy_CMICBMS_umpg">'+
			'<div>'+
			'<ul>'+
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