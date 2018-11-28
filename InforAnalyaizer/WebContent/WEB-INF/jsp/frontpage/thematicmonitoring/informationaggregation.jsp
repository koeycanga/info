<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="ss" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources" var="sysInfo" />
<fmt:message key="front_menu" var="front_menu" bundle="${sysInfo}" />
<fmt:message key="W0003" var="W0003" bundle="${sysInfo}" />
<fmt:message key="E0004" var="E0004" bundle="${sysInfo}" />
<fmt:message key="E0014" var="E0014" bundle="${sysInfo}" />
<fmt:message key="E0025" var="E0025" bundle="${sysInfo}" />
<fmt:message key="I0011" var="I0011" bundle="${sysInfo}" />  
<fmt:message key="I0012" var="I0012" bundle="${sysInfo}" />
<fmt:message key="I0013" var="I0013" bundle="${sysInfo}" /> 
<fmt:message key="W0002" var="W0002" bundle="${sysInfo}" /> 
<fmt:message key="E0022" var="E0022" bundle="${sysInfo}" /> 
<fmt:message key="I0008" var="I0008" bundle="${sysInfo}" />
<fmt:message key="I0013" var="I0013" bundle="${sysInfo}" /> 
<fmt:message key="I0014" var="I0014" bundle="${sysInfo}" />
<fmt:message key="E0029" var="E0029" bundle="${sysInfo}" />
<fmt:message key="E0030" var="E0030" bundle="${sysInfo}" />
<fmt:message key="I0017" var="I0017" bundle="${sysInfo}" />
<fmt:message key="I0018" var="I0018" bundle="${sysInfo}" />
<fmt:message key="I0019" var="I0019" bundle="${sysInfo}" />
<fmt:message key="E0033" var="E0033" bundle="${sysInfo}" /> 
<fmt:message key="E0019" var="E0019" bundle="${sysInfo}" />   
<fmt:message key="I0019" var="I0019" bundle="${sysInfo}" />  
<fmt:message key="I0023" var="I0023" bundle="${sysInfo}" />
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>竞争情报分析系统</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/cy_CIAS_style.css">
</head>

<body style="background-color: #f2f3f8;">
<div id="app" v-cloak>
<div class="cy_hidebg" id="cy_hidebg"></div>
<!--头部-->
<div class="cy_CIASFE_top">
	<div class="cy_CIASFE_logo"><img src="${ctx}/image/fontend-logo.png"></div>
	<div class="cy_CIASFE_tit">国药器械-竞争情报分析系统</div>
	<ic_top_menu></ic_top_menu> <!-- 上部菜单栏 -->
	<ic_user_info ref="ic_user_info"></ic_user_info> <!-- 登录用户信息框 -->
</div>

<!--左侧数据-->
<div class="cy_CIAFE_main">
	<div class="cy_CIASFE_intmonbody">
	<ic_jcfa></ic_jcfa>
	
	<div class="cy_CIASFE_intmonbodyright">
	
<!--条件筛选-->
		<div class="cy_CIASFE_search">
			<select v-model="selected">
				<option v-for="opt in options" v-bind:value="opt.searchtype">{{opt.value}}</option>
			</select>
			<div class="cy_CMICBMS_schbox01"><input v-model="search_key"  type="text" placeholder="请输入…" style="cursor: text"></div>
			<input type="button" v-on:click="btn_search()" class="cy_CMICBMS_schbtn" value="检索">
		</div>
		<div class="cy_CIASFE_condition01">
			<div class="cy_CIASFE_conbox04">
				<a href="#"><div class="cy_CIASFE_conbox05">信息汇总</div></a>
				<a href="${ctx}/thematicmonitoring/contentanalysis"><div class="cy_CIASFE_conbox06">内容分析</div></a>
				<a href="${ctx}/thematicmonitoring/propagationanalysis"><div class="cy_CIASFE_conbox06">传播分析</div></a>
			</div>
			<div class="cy_CIASFE_conbox01">监测时间：
			    <input value="0" type="radio" v-on:change="dealchange()" name="montime" v-model="montime"  id="montime_0" style="display: none;" checked><label for="montime_0">今天</label>
				<input value="-1" type="radio" v-on:change="dealchange()" name="montime" v-model="montime"  id="montime_1" style="display: none;"><label for="montime_1">24小时</label>
           		<input value="-2" type="radio" v-on:change="dealchange()" name="montime" v-model="montime"  id="montime_2" style="display: none;"><label for="montime_2">2天</label>
           		<input value="-3" type="radio" v-on:change="dealchange()" name="montime" v-model="montime"  id="montime_3" style="display: none;"><label for="montime_3">3天</label>
           		<input value="-7" type="radio" v-on:change="dealchange()" name="montime" v-model="montime"  id="montime_4" style="display: none;"><label for="montime_4">7天</label>
           		<input value="-10" type="radio" v-on:change="dealchange()" name="montime" v-model="montime" id="montime_5" style="display: none;"><label for="montime_5">10天</label>
           		<input value="10" type="radio" v-on:change="dealchange()" name="montime" v-model="montime"  id="montime_6" style="display: none;"><label for="montime_6">自定义</label><input id="fromdate" type="date" class="cy_CIASFE_timetb">—<input id="todate" type="date" class="cy_CIASFE_timetb"><input type="button" v-on:click="btn_search()" class="cy_CMICBMS_schbtn" value="确认">
            </div>
		  <div class="cy_CIASFE_conbox02">
				<div class="cy_CIASFE_conbox03">
				情感分析：
					<input value="-1" type="radio" v-on:change="dealchange()" name="emoana" v-model="emoana" id="emoana_0" style="display: none;" checked><label for="emoana_0">全部</label>
					<input value="0" type="radio" v-on:change="dealchange()" name="emoana" v-model="emoana"  id="emoana_1" style="display: none;"><label for="emoana_1">正面</label>
					<input value="1" type="radio" v-on:change="dealchange()" name="emoana" v-model="emoana"  id="emoana_2" style="display: none;"><label for="emoana_2">中性</label>
					<input value="2" type="radio" v-on:change="dealchange()" name="emoana" v-model="emoana"  id="emoana_3" style="display: none;"><label for="emoana_3">负面</label>
				</div>
				<div class="cy_CIASFE_conbox03">相似文章：
					<input value="0" type="radio" v-on:change="dealchange()" name="simart" v-model="simart"  id="simart_0" style="display: none;" checked><label for="simart_0">合并</label>
					<input value="1" type="radio" v-on:change="dealchange()" name="simart" v-model="simart"  id="simart_1" style="display: none;"><label for="simart_1">不合并</label>
				</div>
			</div>
		  <div class="cy_CIASFE_conbox02">
				<div class="cy_CIASFE_conbox03">排序方式： 
					<input type="radio" name="sort" v-on:change="dealchange()" v-model="sort" value="0" id="sort_0" style="display: none;" checked><label for="sort_0">时间降序</label>
					<input type="radio" name="sort" v-on:change="dealchange()" v-model="sort" value="1" id="sort_1" style="display: none;"><label for="sort_1">时间升序</label>
					<input type="radio" name="sort" v-on:change="dealchange()" v-model="sort" value="2" id="sort_2" style="display: none;"><label for="sort_2">权重降序</label>
					<input type="radio" name="sort" v-on:change="dealchange()" v-model="sort" value="3" id="sort_3" style="display: none;"><label for="sort_3">热度排序</label>
				</div>
				<div class="cy_CIASFE_conbox03">信息来源：
					<input type="radio" name="infsour" v-on:change="dealchange()" v-model="infsour" value="-1" id="infsour_0" style="display: none;" checked><label for="infsour_0">全部</label>
					<input type="radio" name="infsour" v-on:change="dealchange()" v-model="infsour" value="0" id="infsour_1" style="display: none;"><label for="infsour_1">新闻</label>
					<input type="radio" name="infsour" v-on:change="dealchange()" v-model="infsour" value="1" id="infsour_2" style="display: none;"><label for="infsour_2">微博</label>
					<input type="radio" name="infsour" v-on:change="dealchange()" v-model="infsour" value="2" id="infsour_3" style="display: none;"><label for="infsour_3">微信</label>
					<input type="radio" name="infsour" v-on:change="dealchange()" v-model="infsour" value="3" id="infsour_4" style="display: none;"><label for="infsour_4">论坛</label>
					<input type="radio" name="infsour" v-on:change="dealchange()" v-model="infsour" value="4" id="infsour_5" style="display: none;"><label for="infsour_5">博客</label>
				</div>
			</div>
	  </div>
	  
<!--	内容-->
	<!--	空数据-->
		<div class="cy_CIASFE_ctnodata">
			<img src="${ctx}/image/fontend-nodata.png">
		</div>
	<!--	详细-->
		<div class="cy_CIASFE_content">
			<div class="cy_CIASFE_contentbox01">
				<label><input type="checkbox" v-model="checked" v-on:click="changeAllChecked()" class="cy_CIASFE_contentall">全选</label>
				
				<input type="button" value="删除" v-on:click="delarticle()" class="cy_CIASFE_contentbtn01">
				<input type="button" value="预警" v-on:click="toyj()" class="cy_CIASFE_contentbtn01">
			</div>
		</div>
		<div v-if="lastest_news>0" v-on:click="searchNew()" class="cy_CIASFE_prompt">
			您有<span>{{lastest_news}}</span>条新信息，点击查看
		</div>
		
		<div class="cy_CIASFE_contmain">
		
		<div v-for="(data,index) in datas" class="cy_CIASFE_contpaste" style="border-top: 0px;">
			<div class="cy_CIASFE_contpastetit">
				<label><input type="checkbox" v-bind:value="data.article_ID"  v-model="checkedNames">
				<b>{{data.articleTitle}}</b></label>
				<div v-if="data.emotionDivision=='0'" class="cy_CIASFE_contpassta03">正</div>
				<div v-if="data.emotionDivision=='1'" class="cy_CIASFE_contpassta02">中</div>
				<div v-if="data.emotionDivision=='2'" class="cy_CIASFE_contpassta01">负</div>
				<div v-if="data.earlyWarningState=='1'" class="cy_CIASFE_contpassta01">已预警</div>
				<div class="cy_CIASFE_contpascol">
					<ic_collectiont v-bind:aid="data.article_ID" v-bind:collcnt="data.collcnt" v-bind:collect_datas="collect_datas"></ic_collectiont>
					<div class="cy_CIASFE_share" v-bind:data-clipboard-text="data.articleURL" v-bind:id="'sharedv'+index" v-on:click="share(index)"></div>
					<div v-on:click="del_a_article(data.article_ID)" class="cy_CIASFE_dele"></div>
				</div>
				
			</div>
			<div class="cy_CIASFE_contpastecon">
				<a style="text-decoration: none;color: #000;" v-bind:href="'../detailspage/toDetailsPage?from=comprehensivemonitoring&article_id='+data.article_ID">{{data.articleAbstract}}</a>
			</div>
			<div class="cy_CIASFE_contpastfoot">
			    <div v-on:mouseover="simcontent(index)" class="cy_CIASFE_simart">相似文章：{{data.sim_cnt}}条
				<div  class="cy_CIASFE_simartbox">
					<div v-for=" sdata in sim_datas[index]" ><a v-bind:href="'../detailspage/toDetailsPage?from=thematicomonitoring&article_id='+sdata.article_ID">{{sdata.articleTitle}}</a></div>
				</div>
				</div>
			<a target="_blank" v-bind:href="data.articleURL">{{data.releasetime}} {{data.websiteName}}</a>
			</div>
			
		</div>
		
		<div ></div>
<!--		分页-->
		 <pager ref="pagecomponent"></pager>  <!-- ref 固定为 pagecomponent  -->
		 
		
		</div>
	</div>
</div>	

</div>
<!--	新增-->



	
<div class="cy_CIASFE_footer02"><a href="">使用手册</a>&nbsp;&nbsp;&nbsp;&nbsp;联系我们（电话：1648726161  邮箱：sales@ichangyun.com）    Copyright&copy;2018-2021 &nbsp;&nbsp;&nbsp;&nbsp;湖北畅云时讯软件技术有限公司版权所有</div>
</div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${ctx}/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/js/echarts.min.js"></script>
<script type="text/javascript" src="${ctx}/js/echarts-wordcloud.min.js"></script>
<script type="text/javascript" src="${ctx}/js/vue.min.js"></script>
<script type="text/javascript" src="${ctx}/js/axios.min.js"></script>
<script type="text/javascript" src="${ctx}/js/polyfill.min.js"></script>
<script type="text/javascript" src="${ctx}/js/clipboard.min.js"></script>
<script type="text/javascript" src="${ctx}/js/ic_components.js"></script>
<script type="text/javascript" src="${ctx}/js/comm.js"></script>

<script>

var Info = {
	  W0003:'${W0003}',
	  E0004:'${E0004}',
	  E0014:'${E0014}',
	  E0025:'${E0025}',
	  I0011:'${I0011}',
	  I0012:'${I0012}',
	  I0013:'${I0013}',
	  W0002:'${W0002}',
	  E0022:'${E0022}',
	  I0008:'${I0008}',
	  I0013:'${I0013}',
	  I0014:'${I0014}',
	  E0030:'${E0030}',
	  I0017:'${I0017}',
	  I0018:'${I0018}',
	  E0029:'${E0029}',
	  E0031:'${E0033}',
	  I0019:'${I0019}',
	  I0023:'${I0023}',
	  E0019:'${E0019}'
};



var menu_datas = JSON.parse('${front_menu}');  //菜单数据来源于 classes/resources.properties

var collect_datas = [];  //我的收藏模块对应的数据集合

Vue.component('ic_top_menu',ic_top_menu);   // ic_top_menu 引自 js/ic_component.js

Vue.component('ic_user_info',ic_user_info);  // ic_user_info 引自 js/ic_component.js

Vue.component('ic_jcfa',ic_jcfa);  // ic_jcfa 引自 js/ic_component.js

Vue.component('ic_collectiont',ic_collectiont); // ic_collectiont 引自 js/ic_component.js




var app = new Vue({
	
	el:"#app",
	data:{
	  checked: false,      //复选框相关
      checkedNames: [],    //复选框相关
	  checkedArr: [],      //复选框相关
	  lastest_news:0,     //有多少条新消息
	  datas:[],
	  sim_datas:[],   //相似文章
	  search_key:'',
	  montime:'0',  //监测时间
	  emoana:'-1',  //情感分析
	  simart:'0',    //相似文章是否合并
	  sort:'0',     //排序方式
	  infsour:'-1',  //信息来源
      selected: 'all', //搜索类型,默认'all' 全文检索
	  options: [
			{ searchtype: 'all', value: '全文匹配' },
			{ searchtype: 'title', value: '标题匹配' },
			{ searchtype: 'text', value: '正文匹配' }
		]
	},
	components:{
		     'pager':ic_front_pager  //ic_front_pager 引自 js/ic_components.js
     }, 	  
	methods:{
		 dealchange:function(){
    		 this.$refs.pagecomponent.pageBean.pageNow = 1;
    		 this.search(this.$refs.pagecomponent.pageBean);
    	 },
		 updateCollent:function(aid){
		   	 for(var i=0;i<this.datas.length;i++){
		   		 if(this.datas[i].article_ID==aid){
		   			this.datas[i].collcnt = 1;
		   			 break;
		   		 }
		   	 }
		 },
		 share:function(index){
			 var id = "sharedv"+index;
			 var clipboard = new Clipboard('#'+id);
	         clipboard.on('success', function(e) {
		           layer.msg(Info.I0019);
	          });
			
		 },
		 simcontent:function(index){        //获得相似文章
			if(this.datas[index].sim_cnt>0&&this.sim_datas[index].length==0){
				var amontime = this.montime;
   			    if(this.montime=='10'){
   				 	amontime = $("#fromdate").val()+"-"+$("#todate").val();
	        	 }
				var _this = this;
				axios.get('../thematicmonitoring/getSimContent',{
	    			params: {
	    				   Article_ID:_this.datas[index].article_ID,
	    				   montime:amontime
	    				}
	    			})
	    			.then(function (response) {
	    				var data = JSON.parse(response.data);
	    				for(var i=0;i<data.length;i++){
	    					_this.sim_datas[index].push(data[i]);
	    				}
	    			
	    			})
	    			.catch(function (error) {
	    			    console.log(error);
	    			});
			 }
		 },
		 changeAllChecked:function(){
			  if (!this.checked) {
					this.checkedNames = this.checkedArr;
				} else {
					this.checkedNames = [];
				}
		  },
		  btn_search:function(){
			  this.$refs.pagecomponent.pageBean.pageNow = 1;
			  this.search(this.$refs.pagecomponent.pageBean);
		  },
		  del_a_article:function(article_ID){
			  var _this = this;
			  var arr = [];
			  arr.push(article_ID);
			  layer.confirm(Info.W0002, {
		            btn : [ '确定', '取消' ]//按钮
		        }, function(index) {
		            layer.close(index);
		            var json = createJSON(arr);   //createJSON() 引自js/comm.js
					 axios.post('../thematicmonitoring/delarticle',
							 {
							 json:json
					      })
		    			.then(function (response) {
		    				if(response.data=='ok'){
		    					if(_this.checkedArr.length==1){
		    						if(_this.$refs.pagecomponent.pageBean.pageNow>1){
		    							_this.$refs.pagecomponent.pageBean.pageNow-=1;
		    							_this.$refs.pagecomponent.pageBean.jump_page = _this.$refs.pagecomponent.pageBean.pageNow;
		    						}
		    					}
		    					_this.checkedNames = [];
		    					_this.search(_this.$refs.pagecomponent.pageBean);
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
		  delarticle:function(){
			  if(this.checkedNames.length==0){
				  layer.msg(Info.E0019);
			  }else{
				  var _this = this;
				  layer.confirm(Info.W0002, {
			            btn : [ '确定', '取消' ]//按钮
			        }, function(index) {
			            layer.close(index);
			            var json = createJSON(_this.checkedNames);   //createJSON() 引自js/comm.js
			            
						 axios.post('../thematicmonitoring/delarticle',
								 {
								 json:json
						      })
			    			.then(function (response) {
			    				if(response.data=='ok'){
			    					if(_this.checkedNames.length==_this.checkedArr.length){
			    						if(_this.$refs.pagecomponent.pageBean.pageNow>1){
			    							_this.$refs.pagecomponent.pageBean.pageNow-=1;
			    							_this.$refs.pagecomponent.pageBean.jump_page = _this.$refs.pagecomponent.pageBean.pageNow;
			    						}
			    					}
			    					_this.checkedNames = [];
			    					_this.search(_this.$refs.pagecomponent.pageBean);
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
		  toyj:function(){
			  if(this.checkedNames.length==0){
				  layer.msg(Info.E0033);
			  }else{
				  var _this = this;
				  var json = createJSON(this.checkedNames);
				  axios.post('../thematicmonitoring/toyj',
			   			    {
					 			 json:json
			   				}
			   			)
			   			.then(function (response) {
			   				 if(response.data=="ok"){
			   					_this.search(_this.$refs.pagecomponent.pageBean);
			   				 }
			   				 if(response.data=="nok"){
			   					layer.msg(Info.E0022);
			   				 }
			   			})
			   			.catch(function (error) {
			   				layer.msg(Info.E0022);
			   			});
			  }
		  },
		  getlastestNews:function(){
			
	        	var _this = this;
	    		axios.get('../thematicmonitoring/getlastestNews',{
	    			params: {
		    				search_key:_this.search_key.trim(),
		    				emoana:_this.emoana,
		    				simart:_this.simart,
		    				sort:_this.sort,
		    				infsour:_this.infsour
	    				}
	    			})
	    			.then(function (response) {
	    				_this.lastest_news = response.data;
	    			})
	    			.catch(function (error) {
	    			    console.log(error);
	    			});
	    		setTimeout(_this.getlastestNews,60000);
		  },
		  searchNew:function(){
			  this.sort = '0';
			  this.search_key = '';
			  this.montime = '0';
			  this.emoana = '-1';
			  this.simart = '0';
			  this.infsour = '-1';
			  this.sim_datas = [];
			  this.$refs.pagecomponent.pageBean.pageNow = 1;
			  this.search(this.$refs.pagecomponent.pageBean);
		  },
		  addCnodeChildren:function(ctnode,data){
			  for(var i=0;i<data.length;i++){
				  if(ctnode.val.collectionType_ID==data[i].parent_CollectionType_ID){
					  var acnode = new CTreeNode(data[i],ctnode.depth+1);
					  collect_datas.push(acnode);
				      this.addCnodeChildren(acnode,data);
				  }
			  }
		  },
		  getCollect:function(){
			  var _this = this;
			  axios.get('../thematicmonitoring/getCollectionType')
	  			.then(function (response) {
	  				var data = JSON.parse(response.data);
	  				for(var i=0;i<data.length;i++){
	  					if(data[i].parent_CollectionType_ID==''||data[i].parent_CollectionType_ID=='0'){  //根节点
	  						var ctnode = new CTreeNode(data[i],0);
	  						collect_datas.push(ctnode);
	  					   _this.addCnodeChildren(ctnode,data);
	  					}
	  				}
	  			})
	  			.catch(function (error) {
	  			    console.log(error);
	  			});
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
	        	var amontime = this.montime;
   			    if(this.montime=='10'){
   				 	amontime = $("#fromdate").val()+"-"+$("#todate").val();
	        	 }
	        	
	        	var _this = this;
	        	layer.msg(Info.I0011, {
	        		  icon: 16
	        		  ,shade: 0.01
	        		});
	    		axios.get('../thematicmonitoring/search',{
	    			params: {
	    				search_key:_this.search_key.trim(),
	    				montime:amontime,
	    				emoana:_this.emoana,
	    				simart:_this.simart,
	    				sort:_this.sort,
	    				infsour:_this.infsour,
	    				pageNow:pageBean.pageNow,
	    				rowSize:pageBean.rs_selected,
                        search_type:_this.selected,
	    				}
	    			})
	    			.then(function (response) {
	    				
	    				var data = JSON.parse(response.data);
	    			
	    				_this.checkedArr = [];
	    				
	    				_this.checkedNames = [];
	    				
	    				_this.$refs.pagecomponent.dealAfterSearch(data.rowCount); 
	    				
	    				_this.datas = data.resdata;
	    				
	    				_this.sim_datas = [];
	    				
	    				for(var i=0;i<_this.datas.length;i++){
	    					_this.checkedArr.push(_this.datas[i].article_ID);
	    					_this.sim_datas.push(new Array());
	    				}
	    			
	    				_this.lastest_news = 0;
	    				
	    				_this.checked = false;
	    				
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
		  
		  this.getCollect();
		  
		  setTimeout(this.getlastestNews,60000);
	  }
	
});

</script>
</html>
