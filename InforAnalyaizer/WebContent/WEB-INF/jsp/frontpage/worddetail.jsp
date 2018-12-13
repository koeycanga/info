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
<fmt:message key="I0024" var="I0024" bundle="${sysInfo}" />
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>竞争情报分析系统</title>
<link id="lnk" rel="stylesheet" type="text/css" href="">
</head>

<body style="background-color: #f2f3f8;">
<div id="app">
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
	<div class="cy_CIASFE_intmonbodyleft">
		<table cellspacing="0" class="cy_CIASFE_hwc">
			<tbody>
				<tr>
				  <th width="30%">{{table_title}}</th>
				</tr>
				<tr v-for="data in hotword_datas" >
				  <td v-bind:style="data.hotWord_ID==sel_word_id?'background-color:#3a8ddd;':''"><a href="#" v-on:click="tohotword(data.hotWord_ID)">{{data.hotWord}}</a></td>
				</tr>
			</tbody>
		</table>
	</div>	
	<div class="cy_CIASFE_intmonbodyright">
	
	  
<!--	内容-->
	<!--	空数据-->
		<div class="cy_CIASFE_ctnodata">
			<img src="${ctx}/image/fontend-nodata.png">
		</div>
	<!--	详细-->
		<div class="cy_CIASFE_contmain">
		
		<div v-for="(data,index) in datas" class="cy_CIASFE_contpaste" style="border-top: 0px;">
			<div class="cy_CIASFE_contpastetit">
			    <input type="checkbox">
				<label>
				<a v-bind:href="'../detailspage/toDetailsPage?from=comprehensivemonitoring&article_id='+data.article_ID" target="_blank" style="text-decoration: none; color: rgb(0, 0, 0);"><b>{{data.articleTitle}}</b></a>
				</label>
				<div v-if="data.emotionDivision=='0'" class="cy_CIASFE_contpassta03">正</div>
				<div v-if="data.emotionDivision=='1'" class="cy_CIASFE_contpassta02">中</div>
				<div v-if="data.emotionDivision=='2'" class="cy_CIASFE_contpassta01">负</div>
				<div v-if="data.yj_cnt>0" class="cy_CIASFE_contpassta01">已预警</div>
				<div class="cy_CIASFE_contpascol">
					<ic_collectiont v-bind:aid="data.article_ID" v-bind:collcnt="data.collcnt"></ic_collectiont>
					<div class="cy_CIASFE_share" v-bind:data-clipboard-text="data.articleURL" v-bind:id="'sharedv'+index" v-on:click="share(index)"></div>
					<div v-on:click="del_a_article(data.article_ID)" class="cy_CIASFE_dele"></div>
				</div>
			</div>
			<div class="cy_CIASFE_contpastecon">
				{{data.articleAbstract}}
			</div>
			<div class="cy_CIASFE_contpastfoot">
			    <div class="cy_CIASFE_footbox01">&nbsp;</div>
			    <div class="cy_CIASFE_footbox02" >
			        <a target="_blank" v-bind:href="data.articleURL">{{data.releasetime}} {{data.websiteName}}</a>
			        
			        <span class="cy_CIASFE_simart" v-on:mouseover="simcontent(index)">
			                              相似文章：{{data.sim_cnt}}条
			          <div class="cy_CIASFE_simartbox">
			             <div v-for=" sdata in sim_datas[index]" ><a v-bind:href="'../detailspage/toDetailsPage?from=comprehensivemonitoring&article_id='+sdata.article_ID" target="_blank" >{{sdata.articleTitle}}</a></div>
			          </div>
			        </span>
			    
			    </div>
			</div>
		</div>
		<div v-if="datas.length==0">
		   <!-- {{info}}  -->
		</div>
		
<!--		分页-->
		<pager ref="pagecomponent"></pager>  <!-- ref 固定为 pagecomponent  -->
		

		</div>
	</div>
</div>	

</div>
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
<script type="text/javascript" src="${ctx}/js/comm.js"></script>
<script type="text/javascript" src="${ctx}/js/ic_components.js"></script>
<script>

AdaptationResolution('${ctx}');//分辨率适配

var flag = "${flag}";   //0：热词     1：即将发生

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
		  I0024:'${I0024}'
	};

	var menu_datas = JSON.parse('${front_menu}');  //菜单数据来源于 classes/resources.properties
	
	var menu_url_data = "front"; // 用来判断
	
	Vue.component('ic_top_menu',ic_top_menu);   // ic_top_menu 引自 js/ic_component.js
	
	Vue.component('ic_user_info',ic_user_info);  // ic_user_info 引自 js/ic_component.js
	
	Vue.component('ic_collectiont',ic_collectiont); // ic_collectiont 引自 js/ic_component.js
	
	var app = new Vue({
		el:'#app',
		data:{
			info:Info.I0024,
			table_title:'${table_title}',  //左边框标题
			sel_word_id:'${word}',           //选中的热词ID 
			checkedArr:[],
			datas:[],
			hotword_datas:[],             //热词集合
			sim_datas:[]                 //相似文章
		},
		components:{
		     'pager':ic_front_pager  //ic_front_pager 引自 js/ic_components.js
        },
		methods:{
			 tohotword:function(hotword){
				 this.sel_word_id = hotword;
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
			 simcontent:function(index){        //获得相似文章
				if(this.datas[index].sim_cnt>0&&this.sim_datas[index].length==0){
					var _this = this;
					axios.get('../front/getSimContent',{
		    			params: {
		    				   Article_ID:_this.datas[index].article_ID
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
			share:function(index){
				 var id = "sharedv"+index;
				 var clipboard = new Clipboard('#'+id);
		         clipboard.on('success', function(e) {
			           layer.msg(Info.I0019);
		          });
				
			 },
			 getHotWord:function(){     //获得热词集合
				 var _this = this;
    	    	 axios.get('../front/getHotWordFromDetial',{
    	    		 params:{
    	    			 flag:flag
    	    		 }
    	    	 })
     			.then(function (response) {

     				_this.hotword_datas = JSON.parse(response.data);
     				
     			})
     			.catch(function (error) {
     			    console.log(error);
     			});
    	    }
		},
		computed:{
			search:function(){
				return function(pageBean){
					var _this = this;
					this.checkedArr = [];
	    			 layer.msg(Info.I0011, {
		        		  icon: 16
		        		  ,shade: 0.01
		        		});
		    		axios.get('../front/searchbyhotword',{
		    			params: {
		    				    HotWord:_this.sel_word_id,
			    				pageNow:pageBean.pageNow,
			    				rowSize:pageBean.rs_selected,
			    				flag:flag
		    				}
		    			})
		    			.then(function (response) {
		    				
		    				var data = JSON.parse(response.data);
		    				
		    				_this.$refs.pagecomponent.dealAfterSearch(data.rowCount); 
		    				
		    				_this.datas = data.resdata;
		    				
		    				_this.sim_datas = [];
		    				
		    				for(var i=0;i<_this.datas.length;i++){
		    					_this.sim_datas.push(new Array());
		    					_this.checkedArr.push(_this.datas[i]);
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
			
			this.getHotWord();
			
			this.search(this.$refs.pagecomponent.pageBean);
		}
	});

</script>

</html>
