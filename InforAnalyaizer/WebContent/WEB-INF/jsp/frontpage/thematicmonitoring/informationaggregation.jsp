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
<fmt:message key="E0052" var="E0052" bundle="${sysInfo}" />
<fmt:message key="I0029" var="I0029" bundle="${sysInfo}"/>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>竞争情报分析系统</title>
<link id="lnk" rel="stylesheet" type="text/css" href="">
<link type="text/css" rel="stylesheet" href="${ctx}/js/date/dhtmlgoodies_calendar/dhtmlgoodies_calendar.css"></LINK>
<script type="text/javascript" src="${ctx}/js/date/dhtmlgoodies_calendar/dhtmlgoodies_calendar.js"></script>
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
      <div class="cy_CIASFE_condition02">
        
        <div class="cy_CIASFE_conbox04">
				<a href="#"><div class="cy_CIASFE_conbox05">信息汇总</div></a>
				<a href="${ctx}/thematicmonitoring/contentanalysis"><div class="cy_CIASFE_conbox06">内容分析</div></a>
				<a href="${ctx}/thematicmonitoring/propagationanalysis"><div class="cy_CIASFE_conbox06">传播分析</div></a>
			</div>
        
		<div class="cy_CIASFE_search">
			<select v-model="search_type">
				<option v-for="opt in options" v-bind:value="opt.description">{{opt.masterValue}}</option>
			</select>
			<div class="cy_CMICBMS_schbox01"><input v-model="search_key"  type="text" placeholder="请输入…" style="cursor: text"></div>
			<input type="button" v-on:click="btn_search()" class="cy_CMICBMS_schbtn" value="检索">
		</div>
		<div class="cy_CIASFE_condition01">
			
			<div class="cy_CIASFE_conbox01">
			  <table border="0">
				   <tr>
				   <td>{{jc_time_name}}：  </td>
				    <td v-for="(data,index) in jc_time_datas">
					   <input  type="radio" name="montime" v-on:change="dealchange()"
						    v-bind:value="data.description" v-model="montime" 
						    v-bind:id="'montime_'+index" style="display: none;"
						     v-bind:checked="index==0">
						     <label v-bind:for="'montime_'+index">{{data.masterValue}}</label> 
					</td>
					<td>
					   <input type="radio" name="montime" value="10" v-model="montime" id="montime_6" style="display: none;"><label for="montime_6">自定义</label>
					   <input type="date" id="fromdate" class="cy_CIASFE_timetb">—<input type="date" id="todate" class="cy_CIASFE_timetb"><input type="button" v-on:click="btn_search()" class="cy_CMICBMS_schbtn" value="确认">
				   </td>
				   </tr>
			   </table>
            </div>
		  <div class="cy_CIASFE_conbox02">
				<div class="cy_CIASFE_conbox03">
			         <table border="0">
		                 <tr>
		                   <td>{{emoana_name}}：  </td>
		                   <td v-for="(data,index) in emoana_datas">
		                      <input type="radio" name="emoana" v-on:change="dealchange()" v-model="emoana" v-bind:value="data.description"
                              v-bind:id="'emoana_'+index" style="display: none;" v-bind:checked="index==0">
                              <label v-bind:for="'emoana_'+index">{{data.masterValue}}</label>
		                   </td>
		                 </tr>
		              </table>
			    </div>
				<div class="cy_CIASFE_conbox03">
				    <table border="0">
				        <tr>
				           <td>{{simart_name}}：</td>
				           <td v-for="(data,index) in simart_datas">
				             <input type="radio" name="simart" v-on:change="dealchange()" v-bind:value="data.description" 
				             v-bind:id="'simart_'+index" v-model="simart" style="display: none;" v-bind:checked="index==0">
				             <label v-bind:for="'simart_'+index">{{data.masterValue}}</label>
				           </td>
				        </tr>
				     </table>
				</div>
			</div>
		  <div class="cy_CIASFE_conbox02">
				<div class="cy_CIASFE_conbox03">
				     <table border="0">
			               <tr>
			                  <td>{{sort_name}}：</td>
			                  <td v-for="(data,index) in sort_datas">
			                     <input type="radio" name="sort" v-on:change="dealchange()" v-bind:value="data.description" 
			                     v-model="sort" v-bind:id="'sort_'+index" style="display: none;" v-bind:checked="index==0">
			                     <label v-bind:for="'sort_'+index">{{data.masterValue}}</label>
			                  </td>
			               </tr>
			          </table>
				</div>
				<div class="cy_CIASFE_conbox03">
				  <table border="0">
				        <tr>
				            <td>{{infsour_name}}：</td>
				            <td v-for="(data,index) in infsour_datas">
				               <input type="radio" name="infsour" v-on:change="dealchange()" v-bind:value="data.description" 
				                v-model="infsour" v-bind:id="'infsour_'+index" style="display: none;" v-bind:checked="index==0">
				               <label v-bind:for="'infsour_'+index">{{data.masterValue}}</label>
				            </td>
				        </tr>
				    </table>
				</div>
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
				<input type="checkbox" v-bind:value="data.article_ID"  v-model="checkedNames">
				<label>
					<a style="text-decoration: none;color: #000;" target="_blank" v-bind:href="'../detailspage/toDetailsPage?from=thematicmonitoring&article_id='+data.article_ID">
					   <b>{{data.articleTitle}}</b>
					</a>
				</label>
				<div v-if="data.emotionDivision=='0'" class="cy_CIASFE_contpassta03">正</div>
				<div v-if="data.emotionDivision=='1'" class="cy_CIASFE_contpassta02">中</div>
				<div v-if="data.emotionDivision=='2'" class="cy_CIASFE_contpassta01">负</div>
				<div v-if="data.isearlywarning=='yes'" class="cy_CIASFE_contpassta01">已预警</div>
				<div class="cy_CIASFE_contpascol">
					<ic_collectiont v-bind:aid="data.article_ID" v-bind:collcnt="data.collcnt" v-bind:collect_datas="collect_datas"></ic_collectiont>
					<div class="cy_CIASFE_share" v-bind:data-clipboard-text="data.articleURL" v-bind:id="'sharedv'+index" v-on:click="share(index)"></div>
					<div v-on:click="del_a_article(data.article_ID)" class="cy_CIASFE_dele"></div>
				</div>
				
			</div>
			<div class="cy_CIASFE_contpastecon">
				{{data.articleAbstract}}
			</div>
			<div class="cy_CIASFE_contpastfoot" >
			    <div class="cy_CIASFE_footbox01">监测词:&nbsp;{{data.monitoringWord}}</div>
			    <div class="cy_CIASFE_footbox02" >
					
					<a v-bind:href="data.articleURL" target="_blank">{{data.releasetime}}
                                {{data.websiteName}}</a>
                                
                   <span class="cy_CIASFE_simart" v-on:mouseover="simcontent(index)">
                                                               相似文章：{{data.sim_cnt}}条
					 <div class="cy_CIASFE_simartbox" >
	                      <div v-for=" sdata in sim_datas[index]">
	                      <a v-bind:href="'../detailspage/toDetailsPage?from=thematicmonitoring&article_id='+sdata.article_ID" 
	                        target="_blank">{{sdata.articleTitle}}</a></div>
                      </div>
				  </span>
				</div>
			    
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
<script type="text/javascript" src="${ctx}/js/laydate/laydate.js"></script>
<script type="text/javascript" src="${ctx}/js/comm.js"></script>
<script type="text/javascript" src="${ctx}/js/ic_components.js"></script>

<script>

AdaptationResolution('${ctx}');//分辨率适配

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
	  E0033:'${E0033}',
	  I0019:'${I0019}',
	  I0023:'${I0023}',
	  E0019:'${E0019}',
	  E0052:'${E0052}',
	  I0029:'${I0029}'
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
	  plan_ID:'',   //方案ID
	 /****************以下是顶部搜索条件相关参数**********************************/
	  search_type:'',
      options: [],
    
      jc_time_name:'',
      jc_time_datas:[],
    
      emoana_name:'',
      emoana_datas:[],
    
      simart_name:'',
      simart_datas:[],
    
      sort_name:'',
      sort_datas:[],
    
      infsour_name:'',
      infsour_datas:[],
    /****************以上是顶部搜索条件相关参数**********************************/
	},
	components:{
		     'pager':ic_front_pager  //ic_front_pager 引自 js/ic_components.js
     }, 	  
	methods:{
		getGeneralPurpose:function(){  //获得顶部筛选条件
			 var _this = this;
			 axios.get('../GeneralPurpose/getSearchInforOps')
                .then(function (response) {
                    var data = JSON.parse(response.data);
                    
                    _this.options = data.options;
                    _this.search_type = data.options[0].description;
                    
					 _this.jc_time_datas = data.jc_time;
                    _this.jc_time_name = data.jc_time[0].controlName;
                    
                    _this.emoana_datas = data.emoana ;
                    _this.emoana_name = data.emoana[0].controlName;
                    
                    _this.simart_datas = data.simart;
                    _this.simart_name = data.simart[0].controlName;
                    
                    _this.sort_datas = data.sort;
                    _this.sort_name = data.sort[0].controlName;
                    
                    _this.infsour_datas = data.infsour ;
                    _this.infsour_name = data.infsour[0].controlName;
                    
                    _this.search(_this.$refs.pagecomponent.pageBean);
                })
                .catch(function (error) {
                    console.log(error);
                });
			 
		 },
		 searchByFA:function(plan_ID){   //根据方案ID查询
			 this.plan_ID  = plan_ID;
			 this.$refs.pagecomponent.pageBean.pageNow = 1;
    		 this.search(this.$refs.pagecomponent.pageBean);
		 },
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
		 share:function(index){   //分析文章
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
		 changeAllChecked:function(){    //复选框相关
			  if (!this.checked) {
					this.checkedNames = this.checkedArr;
				} else {
					this.checkedNames = [];
				}
		  },
		  from_child_search:function(){  //被由此页面打开的子页面调用
              this.search(this.$refs.pagecomponent.pageBean);
          },
		  btn_search:function(){   //检索按钮
			  this.$refs.pagecomponent.pageBean.pageNow = 1;
			  this.search(this.$refs.pagecomponent.pageBean);
		  },
		  del_a_article:function(article_ID){ // 根据文章ID删除文章
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
							 json:json,
							 deletemode: '4', //删除方式,专题监测文章删除
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
		  delarticle:function(){    //删除文章
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
								 json:json,
								 deletemode: '3', //删除方式,专题监测删除
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
		  getDataById:function(id){   //根据文章ID获得文章信息
	        	 for(var i=0;i<this.datas.length;i++){
	        		 if(id==this.datas[i].article_ID){
	        			  return this.datas[i];
	        		 }
	        	 }
	        	 return null;
	         },
		  toyj:function(){   //预警文章
			  if(this.checkedNames.length==0){
				  layer.msg(Info.E0033);
			  }else{
				  var _this = this;
				  var arr = [];
				  for(var i=this.checkedNames.length-1;i>=0;i--){
	            		 var data = this.getDataById(this.checkedNames[i]);
	            		 if(data.isearlywarning!='yes'){
	            			 arr.push(this.checkedNames[i]);
	            		 }
	            	 }
				  var json = createJSON(arr);
				  axios.post('../thematicmonitoring/toyj',
			   			    {
					 			 json:json
			   				}
			   			)
			   			.then(function (response) {
			   				 if(response.data=="ok"){
			   					 for(var i=_this.checkedNames.length-1;i>=0;i--){
				            		 var data = _this.getDataById(_this.checkedNames[i]);
				            		 data.isearlywarning = 'yes' ;
			   					 }
			   					//_this.search(_this.$refs.pagecomponent.pageBean);
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
		  getlastestNews:function(){   //获得最新消息条目数
			    if(this.plan_ID!=''){
		        	var _this = this;
		    		axios.get('../thematicmonitoring/getlastestNews',{
		    			params: {
			    				search_key:_this.search_key.trim(),
			    				Plan_ID:_this.plan_ID,
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
			    }
	    		setTimeout(this.getlastestNews,60000);
		  },
		  searchNew:function(){  //搜索最新消息
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
		  addCnodeChildren:function(ctnode,data){ //处理我的收藏节点，使其按照树形结构组成数组
			  for(var i=0;i<data.length;i++){
				  if(ctnode.val.collectionType_ID==data[i].parent_CollectionType_ID){
					  var acnode = new CTreeNode(data[i],ctnode.depth+1);
					  collect_datas.push(acnode);
				      this.addCnodeChildren(acnode,data);
				  }
			  }
		  },
		  getCollect:function(){ //获得我的收藏节点信息
			  var _this = this;
			  axios.get('../thematicmonitoring/getCollectionType')
	  			.then(function (response) {
	  				var data = JSON.parse(response.data);
	  				for(var i=0;i<data.length;i++){
	  					if(data[i].parent_CollectionType_ID==''||data[i].parent_CollectionType_ID=='C000000000'){  //根节点
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
	    				Plan_ID:_this.plan_ID,
	    				montime:amontime,
	    				emoana:_this.emoana,
	    				simart:_this.simart,
	    				sort:_this.sort,
	    				infsour:_this.infsour,
	    				pageNow:pageBean.pageNow,
	    				rowSize:pageBean.rs_selected,
                        search_type:_this.search_type,
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
		  
		  //this.search(this.$refs.pagecomponent.pageBean);
		  
		  this.getGeneralPurpose();
		  
		  this.getCollect();
		  
		  setTimeout(this.getlastestNews,60000);
	  }
	
});

</script>
</html>
