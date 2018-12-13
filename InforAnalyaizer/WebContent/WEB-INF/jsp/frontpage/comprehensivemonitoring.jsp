<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="ss" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources" var="sysInfo"/>
<fmt:message key="front_menu" var="front_menu" bundle="${sysInfo}"/>
<fmt:message key="W0002" var="W0002" bundle="${sysInfo}"/>
<fmt:message key="W0003" var="W0003" bundle="${sysInfo}"/>
<fmt:message key="E0004" var="E0004" bundle="${sysInfo}"/>
<fmt:message key="E0014" var="E0014" bundle="${sysInfo}"/>
<fmt:message key="E0025" var="E0025" bundle="${sysInfo}"/>
<fmt:message key="I0011" var="I0011" bundle="${sysInfo}"/>
<fmt:message key="I0012" var="I0012" bundle="${sysInfo}"/>
<fmt:message key="I0013" var="I0013" bundle="${sysInfo}"/>
<fmt:message key="I0019" var="I0019" bundle="${sysInfo}"/>
<fmt:message key="I0023" var="I0023" bundle="${sysInfo}"/>
<fmt:message key="E0019" var="E0019" bundle="${sysInfo}"/>
<fmt:message key="E0022" var="E0022" bundle="${sysInfo}"/>
<fmt:message key="E0033" var="E0033" bundle="${sysInfo}"/>

<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>竞争情报分析系统</title>
    <link id="lnk" rel="stylesheet" type="text/css" href="">
</head>

<body style="background-color: #f2f3f8;">
<div id="app">
    <!--复制成功提示-->
    <div class="cy_CIASFE_copytip">文字链接已复制</div>

    <!--头部-->
    <div class="cy_CIASFE_top">
        <div class="cy_CIASFE_logo"><img src="${ctx}/image/fontend-logo.png"></div>
        <div class="cy_CIASFE_tit">国药器械-竞争情报分析系统</div>
        <ic_top_menu></ic_top_menu> <!-- 上部菜单栏 -->
        <ic_user_info></ic_user_info> <!-- 登录用户信息框 -->
    </div>

    <!--二级导航-->
    <div class="cy_CIASFE_2ndmenu">
        <div v-bind:class="fl_index==-1?'cy_CIASFE_2ndmenutabchk':'cy_CIASFE_2ndmenutab'" v-on:click="dealtopmenu(-1)">
            综合监测
        </div>
        <div v-for="(fdata,findex) in fl_datas" v-on:click="dealtopmenu(findex)"
             v-bind:class="fl_index==findex?'cy_CIASFE_2ndmenutabchk':'cy_CIASFE_2ndmenutab'"
             v-bind:style="findex==fl_datas.length-1?'border: solid 1px #e0e0e0':''">{{fdata.classificationName}}
            <img v-if="fdata.today_cnt>0&&!fdata.isclick" src="${ctx}/image/fontend-newtip.png">
        </div>

    </div>

    <!--左侧数据-->
    <div class="cy_CIAFE_main">
        <div class="cy_CIASFE_intmonbody">


            <div class="cy_CIASFE_intmonbodyleft">
                <table cellspacing="0" v-if="fl_index==-1">
                    <tbody>
                    <tr>
                        <th width="30%">名称</th>
                        <th width="35%" align="center">今天总数</th>
                        <th width="35%" align="center">昨天总数</th>
                    </tr>
                    <tr>
                        <td>监测总数</td>
                        <td class="cy_CIASFE_intmonjr" align="center">{{total_today}}</td>
                        <td class="cy_CIASFE_intmonzr" align="center">{{total_yesterday}}</td>
                    </tr>
                    <tr v-for="data in fl_datas">
                        <td>{{data.classificationName}}</td>
                        <td class="cy_CIASFE_intmonjr" align="center">{{data.today_cnt}}</td>
                        <td class="cy_CIASFE_intmonzr" align="center">{{data.yesterday_cnt}}</td>
                    </tr>
                    </tbody>
                </table>

                <div class="cy_CIASFE_colletree" v-else>
                    <div class="cy_CIASFE_newfolder"></div>

                    <ul v-for="model in tree_datas">
                        <ic_comprehensive_tree v-bind:model="model"></ic_comprehensive_tree>
                    </ul>

                </div>

            </div>

            <div class="cy_CIASFE_intmonbodyright">

                <!--条件筛选-->
                <div class="cy_CIASFE_condition">
                    <div class="cy_CIASFE_search">
                        <select v-model="search_type">
                            <option v-for="opt in options" v-bind:value="opt.description">{{opt.masterValue}}</option>
                        </select>
                        <div class="cy_CMICBMS_schbox01"><input type="text" v-model="search_key" placeholder="请输入…"
                                                                style="cursor: text"></div>
                        <input type="button" v-on:click="btn_search()" class="cy_CMICBMS_schbtn" value="检索">
                    </div>
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

                <!--	内容-->
                <!--	空数据-->
                <div class="cy_CIASFE_ctnodata">
                    <img src="${ctx}/image/fontend-nodata.png">
                </div>
                <!--	详细-->
                <div class="cy_CIASFE_content">
                    <div class="cy_CIASFE_contentbox01">
                        <label><input type="checkbox" v-model="checked" v-on:click="changeAllChecked()"
                                      class="cy_CIASFE_contentall">全选</label>

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
                            <input type="checkbox" v-bind:value="data.article_ID" v-model="checkedNames">
                            <label>
                                 <a style="text-decoration: none;color: #000;" target="_blank"
	                               v-bind:href="'../detailspage/toDetailsPage?from=comprehensivemonitoring&article_id='+data.article_ID">
	                               <b style="cursor:pointer;">{{data.articleTitle}}</b>
                               </a>
                            </label>
                            <div v-if="data.emotionDivision=='0'" class="cy_CIASFE_contpassta03">正</div>
                            <div v-if="data.emotionDivision=='1'" class="cy_CIASFE_contpassta02">中</div>
                            <div v-if="data.emotionDivision=='2'" class="cy_CIASFE_contpassta01">负</div>
                            <div v-if="data.isearlywarning=='yes'" class="cy_CIASFE_contpassta01">已预警</div>
                            <div class="cy_CIASFE_contpascol">
                                <ic_collectiont v-bind:aid="data.article_ID" v-bind:collcnt="data.collcnt"
                                                v-bind:collect_datas="collect_datas"></ic_collectiont>
                                <div class="cy_CIASFE_share" v-bind:data-clipboard-text="data.articleURL"
                                     v-bind:id="'sharedv'+index" v-on:click="share(index)"></div>
                                <div v-on:click="del_a_article(data.article_ID)" class="cy_CIASFE_dele"></div>
                            </div>

                        </div>
                        <div class="cy_CIASFE_contpastecon">
                           {{data.articleAbstract}}
                        </div>

                        <div class="cy_CIASFE_contpastfoot">
                           <div class="cy_CIASFE_footbox01">&nbsp;</div>
                           <div class="cy_CIASFE_footbox02" >
					
								<a v-bind:href="data.articleURL" target="_blank">{{data.releasetime}}
			                                {{data.websiteName}}</a>
			                                
			                   <span class="cy_CIASFE_simart" v-on:mouseover="simcontent(index)">
			                                                               相似文章：{{data.sim_cnt}}条
								 <div class="cy_CIASFE_simartbox" >
				                      <div v-for=" sdata in sim_datas[index]">
				                      <a v-bind:href="'../detailspage/toDetailsPage?from=comprehensivemonitoring&article_id='+sdata.article_ID" 
				                        target="_blank">{{sdata.articleTitle}}</a></div>
			                      </div>
							  </span>
							</div>

                        </div>

                    </div>

                    <div></div>
                    <!--		分页-->
                    <pager ref="pagecomponent"></pager>  <!-- ref 固定为 pagecomponent  -->

                </div>
            </div>
        </div>

    </div>
    <div class="cy_CIASFE_footer02"><a href="">使用手册</a>&nbsp;&nbsp;&nbsp;&nbsp;联系我们（电话：1648726161
        邮箱：sales@ichangyun.com） Copyright&copy;2018-2021 &nbsp;&nbsp;&nbsp;&nbsp;湖北畅云时讯软件技术有限公司版权所有
    </div>
</div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${ctx}/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/js/vue.min.js"></script>
<script type="text/javascript" src="${ctx}/js/axios.min.js"></script>
<script type="text/javascript" src="${ctx}/js/polyfill.min.js"></script>
<script type="text/javascript" src="${ctx}/js/clipboard.min.js"></script>
<script type="text/javascript" src="${ctx}/js/comm.js"></script>
<script type="text/javascript" src="${ctx}/js/ic_components.js"></script>
<script>

    AdaptationResolution('${ctx}');//分辨率适配

    var Info = {
        W0003: '${W0003}',
        E0004: '${E0004}',
        E0014: '${E0014}',
        E0025: '${E0025}',
        I0011: '${I0011}',
        I0012: '${I0012}',
        I0013: '${I0013}',
        I0019: '${I0019}',
        I0023: '${I0023}',
        W0002: '${W0002}',
        E0019: '${E0019}',
        E0022: '${E0022}',
        E0033: '${E0033}'
    };


    function TreeNode(val, depth, children_lg) {
        this.val = val;
        this.is_show = true;
        this.is_open = true;
        this.depth = depth;
        this.children_lg = children_lg;
        if (children_lg > 0) {
            this.imgsrc = "../image/colle-folder.png";
        } else {
            this.imgsrc = "../image/colle-file.png";
        }
        this.style = {};//{"background-color":"#2ea1f8"}
        this.children = [];
    }

    var m_isop_click = false;

    var ic_comprehensive_tree = {

        template: '<div>' +
            '<li v-show="model.is_show" v-bind:class="model.children_lg>0?\'cy_CIASFE_comdettree2ed\':\'cy_CIASFE_comdettreefile\'" v-bind:style="style">' +
            '<a v-on:click="search_fl(model.val.classification_ID)" href="#"><img v-on:click="opClose(model.val.classification_ID)" v-if="model.children_lg>0" v-bind:src="model.is_open?\'../image/colle-arrow02.png\':\'../image/colle-arrow01.png\'">' +
            '<img v-bind:src="model.children_lg>0?\'../image/colle-folder.png\':\'../image/colle-file.png\'"><span v-bind:id="\'ic_tree_span\'+model.val.classification_ID" name="ic_tree_span">{{model.val.classificationName}}</span></a></li>' +
            '<ul><ic_comprehensive_tree v-for="model in model.children" v-bind:model="model"></ic_comprehensive_tree></ul>' +
            '</div>',
        props: ['model'],
        data: function () {
            return {
                style: {}
            }
        },
        methods: {
            opClose: function (id) {
                m_isop_click = true;
                this.$parent.opClose(id);
            },
            search_fl: function (id) {
                if (!m_isop_click) {
                    this.$parent.search_fl(id);
                }
                m_isop_click = false;
            }
        },
        mounted: function () {
            this.style = {"padding-left": (20 + 30 * (this.model.depth)) + "px"};

        }
    }

    var menu_datas = JSON.parse('${front_menu}') //菜单数据来源于 classes/resources.properties

    var collect_datas = [];  //我的收藏模块对应的数据集合

    Vue.component('ic_top_menu', ic_top_menu);   // ic_top_menu 引自 js/ic_component.js

    Vue.component('ic_user_info', ic_user_info);  // ic_user_info 引自 js/ic_component.js

    Vue.component('ic_collectiont', ic_collectiont); // ic_collectiont 引自 js/ic_component.js

    Vue.component('ic_comprehensive_tree', ic_comprehensive_tree);

    var m_Map = new Map();

    var app = new Vue({
        el: "#app",
        data: {
            checked: false,      //复选框相关
            checkedNames: [],    //复选框相关
            checkedArr: [],      //复选框相关
            datas: [],
            fl_datas: [],     //分类体系根节点
            fl_all_datas: [],     //分类体系全部
            tree_datas: [],      //树形结构
            fl_index: -1,      //当前选中分类体系的标识位数
            sim_datas: [],   //相似文章
            search_key: '',
            montime: '0',  //监测时间
            emoana: '-1',  //情感分析
            simart: '0',    //相似文章是否合并
            sort: '0',     //排序方式
            infsour: '-1',  //信息来源
            lastest_news: 0,     //有多少条新消息
            showzhjc: true,      //是否显示综合监测面板
            total_yesterday: 0,  //昨日监测总数
            total_today: 0,       //今日监测总数
            Classification_ID: '', //分类体系ID
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
    	    infsour_datas:[]
            /****************以上是顶部搜索条件相关参数**********************************/
        },
        components: {
            'pager': ic_front_pager  //ic_front_pager 引自 js/ic_components.js
        },
        methods: {
        	getGeneralPurpose:function(){
        		 var _this = this;
    			 axios.get('../GeneralPurpose/getSearchZHJCOps')
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
            dealchange: function () {
                this.$refs.pagecomponent.pageBean.pageNow = 1;
                this.search(this.$refs.pagecomponent.pageBean);
            },
            updateCollent: function (aid) {
                for (var i = 0; i < this.datas.length; i++) {
                    if (this.datas[i].article_ID == aid) {
                        this.datas[i].collcnt = 1;
                        break;
                    }
                }
            },
            opClose: function (id) {
                var clicknode = this.getClickNode(id, this.tree_datas);
                if (clicknode.is_open) {  //关闭

                    clicknode.is_open = false;

                    this.dealDigui(clicknode, false);

                } else {  //打开
                    clicknode.is_open = true;
                    this.dealDigui(clicknode, true);
                }
            },
            dealDigui: function (node, b) {
                if (b) {
                    for (var i = 0; i < node.children.length; i++) {
                        node.children[i].is_show = true;
                        if (node.children[i].children.length > 0 && node.children[i].is_open) {
                            this.dealDigui(node.children[i], true);
                        }
                    }
                } else {
                    for (var i = 0; i < node.children.length; i++) {
                        node.children[i].is_show = false;
                        this.dealDigui(node.children[i], false);
                    }
                }
            },
            getClickNode: function (id, arr) {
                var node = null;
                for (var i = 0; i < arr.length; i++) {
                    if (arr[i].val.classification_ID == id) {
                        node = arr[i];
                        break;
                    } else {
                        node = this.getClickNode(id, arr[i].children);
                        if (node != null) {
                            return node;
                        }
                    }
                }
                return node;
            },
            dealtopmenu: function (index) {   //处理顶部的分类体系菜单栏
                this.fl_index = index;
                m_Map.clear();
                if (index != -1) {
                    var tdata = this.fl_datas[index];
                    tdata.isclick = true;
                    var node = new TreeNode(tdata, 0, tdata.children_lg);
                    this.tree_datas = [];
                    this.tree_datas.push(node);
                    this.addnode2tree(node);
                    this.search_fl(tdata.classification_ID);
                    setTimeout(function () {
                        $("span[name='ic_tree_span']").eq(0).css("background-color", "#2ea1f8");
                    }, 50);
                } else {
                    this.Classification_ID = "";
                    this.search_fl("");
                }
            },
            addnode2tree: function (node) {
                if (node.children_lg > 0) {
                    for (var i = 0; i < this.fl_all_datas.length; i++) {
                        if (this.fl_all_datas[i].parent_Classification_ID == node.val.classification_ID) {
                            var cnode = new TreeNode(this.fl_all_datas[i], node.depth + 1, this.fl_all_datas[i].children_lg);
                            if (m_Map.get(cnode.val.classification_ID) == null) {
                                m_Map.set(cnode.val.classification_ID, cnode);
                                node.children.push(cnode);
                                this.addnode2tree(cnode);
                            }
                        }
                    }
                }
            },
            toDetailsPage: function (article_id) {  //进入到详情页
                window.location.href = "../detailspage/toDetailsPage?from=comprehensivemonitoring&article_id=" + article_id;
            },
            getDataById:function(id){   //根据文章ID获得文章信息
	        	 for(var i=0;i<this.datas.length;i++){
	        		 if(id==this.datas[i].article_ID){
	        			  return this.datas[i];
	        		 }
	        	 }
	        	 return null;
	         },
            toyj: function () {  //预警文章
                if (this.checkedNames.length == 0) {
                    layer.msg(Info.E0033);
                } else {
                    var _this = this;
                    var arr = [];
  				    for(var i=this.checkedNames.length-1;i>=0;i--){
  	            		 var data = this.getDataById(this.checkedNames[i]);
  	            		 if(data.isearlywarning!='yes'){
  	            			 arr.push(this.checkedNames[i]);
  	            		 }
  	            	 }
  				   var json = createJSON(arr);
                    axios.post('../comprehensivemonitoring/toyj',  
                        {
                            json: json
                        }
                    )
                        .then(function (response) {
                            if (response.data == "ok") {
                            	for(var i=_this.checkedNames.length-1;i>=0;i--){
				            		 var data = _this.getDataById(_this.checkedNames[i]);
				            		 data.isearlywarning = 'yes' ;
			   					 }
                                //_this.search(_this.$refs.pagecomponent.pageBean);
                            }
                            if (response.data == "nok") {
                                layer.msg(Info.E0022);
                            }
                        })
                        .catch(function (error) {
                            layer.msg(Info.E0022);
                        });
                }
            },
            del_a_article: function (article_ID) {
                var _this = this;
                var arr = [];
                arr.push(article_ID);
                layer.confirm(Info.W0002, {
                    btn: ['确定', '取消']//按钮
                }, function (index) {
                    layer.close(index);
                    var json = createJSON(arr);   //createJSON() 引自js/comm.js

                    axios.post('../comprehensivemonitoring/delarticle',
                        {
                            json: json,
                            deletemode: '2', //删除方式,综合监测文章删除
                        })
                        .then(function (response) {
                            if (response.data == 'ok') {
                                if (_this.checkedArr.length == 1) {
                                    if (_this.$refs.pagecomponent.pageBean.pageNow > 1) {
                                        _this.$refs.pagecomponent.pageBean.pageNow -= 1;
                                        _this.$refs.pagecomponent.pageBean.jump_page = _this.$refs.pagecomponent.pageBean.pageNow;
                                    }
                                }
                                _this.checkedNames = [];
                                _this.search(_this.$refs.pagecomponent.pageBean);
                            }
                            if (response.data == 'nok') {
                                layer.msg(Info.E0022);
                            }
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                });
            },
            delarticle: function () {
                if (this.checkedNames.length == 0) {
                    layer.msg(Info.E0019);
                } else {
                    var _this = this;
                    layer.confirm(Info.W0002, {
                        btn: ['确定', '取消']//按钮
                    }, function (index) {
                        layer.close(index);
                        var json = createJSON(_this.checkedNames);   //createJSON() 引自js/comm.js

                        axios.post('../comprehensivemonitoring/delarticle',
                            {
                                json: json,
                                deletemode: '1', //删除方式,综合监测删除
                            })
                            .then(function (response) {
                                if (response.data == 'ok') {
                                    if (_this.checkedNames.length == _this.checkedArr.length) {
                                        if (_this.$refs.pagecomponent.pageBean.pageNow > 1) {
                                            _this.$refs.pagecomponent.pageBean.pageNow -= 1;
                                            _this.$refs.pagecomponent.pageBean.jump_page = _this.$refs.pagecomponent.pageBean.pageNow;
                                        }
                                    }
                                    _this.checkedNames = [];
                                    _this.search(_this.$refs.pagecomponent.pageBean);
                                }
                                if (response.data == 'nok') {
                                    layer.msg(Info.E0022);
                                }
                            })
                            .catch(function (error) {
                                console.log(error);
                            });
                    });
                }
            },
            simcontent: function (index) {        //获得相似文章
                if (this.datas[index].sim_cnt > 0 && this.sim_datas[index].length == 0) {
                    var amontime = this.montime;
                    if (this.montime == '10') {
                        amontime = $("#fromdate").val() + "-" + $("#todate").val();
                    }
                    var _this = this;
                    axios.get('../comprehensivemonitoring/getSimContent', {
                        params: {
                            Article_ID: _this.datas[index].article_ID,
                            montime: amontime
                        }
                    })
                        .then(function (response) {
                            var data = JSON.parse(response.data);
                            for (var i = 0; i < data.length; i++) {
                                _this.sim_datas[index].push(data[i]);
                            }
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                }

            },
            changeAllChecked: function () {
                if (!this.checked) {
                    this.checkedNames = this.checkedArr;
                } else {
                    this.checkedNames = [];
                }
            },
            from_child_search:function(){
            	this.search(this.$refs.pagecomponent.pageBean);
            },
            btn_search: function () {
                this.$refs.pagecomponent.pageBean.pageNow = 1;
                this.search(this.$refs.pagecomponent.pageBean);
            },
            search_fl: function (id) {  //根据分类体系查询
                this.Classification_ID = id;
                this.sort = '0';
                this.search_key = '';
                this.montime = '0';
                this.emoana = '-1';
                this.simart = '0';
                this.infsour = '-1';
                this.$refs.pagecomponent.pageBean.pageNow = 1;
                this.search(this.$refs.pagecomponent.pageBean);
                $("span[name='ic_tree_span']").css("background-color", "");
                $("#ic_tree_span" + id).css("background-color", "#2ea1f8");
            },
            share: function (index) {
                var id = "sharedv" + index;
                var clipboard = new Clipboard('#' + id);
                clipboard.on('success', function (e) {
                    layer.msg(Info.I0019);
                });
            },
            getlastestNews: function () {

                var _this = this;
                axios.get('../comprehensivemonitoring/getlastestNews', {
                    params: {
                        search_key: _this.search_key.trim(),
                        Classification_ID: _this.Classification_ID,
                        emoana: _this.emoana,
                        simart: _this.simart,
                        sort: _this.sort,
                        infsour: _this.infsour
                    }
                })
                    .then(function (response) {
                        _this.lastest_news = response.data;
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
                setTimeout(_this.getlastestNews, 60000);
            },
            searchNew: function () {
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
            getFenTixi: function () {   //获得所有的分类体系根节点

                var _this = this;
                axios.get('../comprehensivemonitoring/getAllClassification')
                    .then(function (response) {

                        _this.fl_all_datas = JSON.parse(response.data);

                        _this.fl_datas = [];

                        for (var i = 0; i < _this.fl_all_datas.length; i++) {
                            if (_this.fl_all_datas[i].parent_Classification_ID == '0000000000') {
                                _this.fl_datas.push(_this.fl_all_datas[i]);
                            }
                        }

                        for (var i = 0; i < _this.fl_datas.length; i++) {
                            _this.total_yesterday += _this.fl_datas[i].yesterday_cnt;
                            _this.total_today += _this.fl_datas[i].today_cnt;
                        }

                    })
                    .catch(function (error) {
                        console.log(error);
                    });

            },
            addCnodeChildren: function (ctnode, data) {
                for (var i = 0; i < data.length; i++) {
                    if (ctnode.val.collectionType_ID == data[i].parent_CollectionType_ID) {
                        var acnode = new CTreeNode(data[i], ctnode.depth + 1);
                        collect_datas.push(acnode);
                        this.addCnodeChildren(acnode, data);
                    }
                }
            },
            getCollect: function () {
                var _this = this;
                axios.get('../thematicmonitoring/getCollectionType')
                    .then(function (response) {
                        var data = JSON.parse(response.data);
                        console.log(data);
                        for (var i = 0; i < data.length; i++) {
                            if (data[i].parent_CollectionType_ID == '' || data[i].parent_CollectionType_ID == 'C000000000') {  //根节点
                                var ctnode = new CTreeNode(data[i], 0);
                                collect_datas.push(ctnode);
                                _this.addCnodeChildren(ctnode, data);
                            }
                        }
                        console.log(collect_datas);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        },
        watch: {
            "checkedNames": function () {
                if (this.checkedNames.length != this.checkedArr.length || this.checkedNames.length == 0) {
                    this.checked = false;
                } else {
                    this.checked = true;
                }
            }
        },
        computed: {
            search: function () {
                return function (pageBean) {
                    var amontime = this.montime;
                    if (this.montime == '10') {
                        amontime = $("#fromdate").val() + "-" + $("#todate").val();
                    }

                    var _this = this;

                    layer.msg(Info.I0011, {
                        icon: 16
                        , shade: 0.01
                    });
                    axios.get('../comprehensivemonitoring/search', {
                        params: {
                            search_key: _this.search_key.trim(),
                            Classification_ID: _this.Classification_ID,
                            montime: amontime,
                            emoana: _this.emoana,
                            simart: _this.simart,
                            sort: _this.sort,
                            infsour: _this.infsour,
                            pageNow: pageBean.pageNow,
                            rowSize: pageBean.rs_selected,
                            search_type: _this.search_type,
                        }
                    })
                        .then(function (response) {

                            var data = JSON.parse(response.data);

                            _this.checkedArr = [];

                            _this.checkedNames = [];

                            _this.$refs.pagecomponent.dealAfterSearch(data.rowCount);

                            _this.datas = data.resdata;

                            _this.sim_datas = [];

                            for (var i = 0; i < _this.datas.length; i++) {
                                _this.checkedArr.push(_this.datas[i].article_ID);
                                _this.sim_datas.push([]);
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
        mounted: function () {
        	this.getGeneralPurpose();
            this.getFenTixi();
            this.getCollect();
            setTimeout(this.getlastestNews, 60000);
        }
    });
</script>
</html>
