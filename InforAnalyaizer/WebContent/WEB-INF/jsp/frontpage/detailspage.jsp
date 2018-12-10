<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="ss" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources" var="sysInfo" />
<fmt:message key="front_menu" var="front_menu" bundle="${sysInfo}" />
<fmt:message key="I0019" var="I0019" bundle="${sysInfo}"/>
<fmt:message key="W0002" var="W0002" bundle="${sysInfo}"/>
<fmt:message key="E0022" var="E0022" bundle="${sysInfo}"/>
<fmt:message key="I0014" var="I0014" bundle="${sysInfo}"/>
<fmt:message key="E0004" var="E0004" bundle="${sysInfo}"/>
<fmt:message key="E0014" var="E0014" bundle="${sysInfo}"/>
<fmt:message key="I0012" var="I0012" bundle="${sysInfo}"/>
<fmt:message key="E0025" var="E0025" bundle="${sysInfo}"/>
<fmt:message key="I0013" var="I0013" bundle="${sysInfo}"/>
<fmt:message key="W0003" var="W0003" bundle="${sysInfo}"/>
<fmt:message key="I0023" var="I0023" bundle="${sysInfo}"/>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>竞争情报分析系统</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/cy_CIAS_style-1920_1080.css">
</head>

<body style="background-color: #f2f3f8;">

<!--复制成功提示-->
<div id="app" v-cloak>
<div class="cy_CIASFE_copytip">文字链接已复制</div>

<!--头部-->
<div class="cy_CIASFE_top">
	<div class="cy_CIASFE_logo"><img src="${ctx }/image/fontend-logo.png"></div>
	<div class="cy_CIASFE_tit">国药器械-竞争情报分析系统</div>
	<ic_top_menu ></ic_top_menu> <!-- 上部菜单栏 -->
	<ic_user_info></ic_user_info> <!-- 登录用户信息框 -->
</div>

<div class="cy_CIAFE_main">	
	<div class="cy_CIAFE_intmonbox01">${m_title }> <span>${m_title }详情</span></div>
	<div class="cy_CIAFE_intmonbox02">
		<div class="cy_CIAFE_intmonbox03">
		<div class="cy_CIAFE_intmonbox04">
			<div class="cy_CIAFE_intmonbox05">
			<div class="cy_CIASFE_decolle">
					
					<ic_collectiont v-bind:aid="article_data.article_ID" v-bind:collcnt="article_data.collcnt"
                                               v-bind:collect_datas="collect_datas"></ic_collectiont>
					
					<div class="cy_CIASFE_share" v-bind:data-clipboard-text="article_data.articleURL"
                                     v-bind:id="'sharedv'" v-on:click="share()">
					
					</div>
					<div class="cy_CIASFE_dele" v-on:click="del_a_article(article_data.article_ID)"></div>
				</div>
				<div class="cy_CIAFE_intmontit">{{article_data.articleTitle}}
					<span v-if="article_data.emotionDivision=='0'" class="cy_CIASFE_contpassta03">正</span>
					<span v-if="article_data.emotionDivision=='1'" class="cy_CIASFE_contpassta02">中</span>
					<span v-if="article_data.emotionDivision=='2'" class="cy_CIASFE_contpassta01">负</span>
					<span v-if="article_data.isearlywarning=='yes'" class="cy_CIASFE_contpassta01">已预警</span>
				</div>
				<div width="100%">
					
				</div>
				<div style="background-color: #f3f3f3;padding:10px 30px;line-height: 33px;margin-top: 10px;">
				<div class="cy_CIAFE_intmonsr">{{article_data.websiteName}}·{{article_data.releasetime}} <a target="_blank" v-bind:href="article_data.articleURL">原文链接</a>
				<div v-on:mouseover="simcontent()" class="cy_CIASFE_desimart">相似文章：{{article_data.sim_cnt}}条
					<div class="cy_CIASFE_desimartbox">
						<div v-for=" sdata in sim_datas"><a
									v-bind:href="'../detailspage/toDetailsPage?from=${m_url}&article_id='+sdata.article_ID"
									target="_blank">{{sdata.articleTitle}}</a>
					    </div>
					</div>
					</div>
				</div>
					<div>关键词：<span style="color: red">{{article_data.articleKeyWord}}</span>
					</div>
				</div>
				
				<div class="cy_CIAFE_intmonbox06">
					<p>{{article_data.articleText}}</p>
				</div>
			</div></div>
		</div>
		<div class="cy_CIAFE_intmonbox07">
			<div class="cy_CIAFE_intmonbox08">
				<div class="cy_CIAFE_intmonbox09">媒体报道分析</div>
				<div class="cy_CIAFE_intmonbox10">该文章在媒体上出现的次数为：<span>{{bd_data.length}}次</span></div>
					<div class="cy_CIASFE_intmontmln">
							<div class="cy_CIASFE_intmonttmlnwr" v-for="(bdata,index) in bd_data" v-if="(index<5&&!is_open)||is_open" v-bind:style="((index==4&&!is_open)||index==bd_data.length-1)?{'padding-bottom':'0'}:{}">
								<div class="cy_CIASFE_tmlncircle"></div>
								<div class="cy_CIASFE_intmontime">{{bdata.releasetime}}</div>
								<div class="cy_CIASFE_intmonsor"><a v-bind:href="bdata.articleURL" target="_blank">{{bdata.websiteName}}</a></div>
							</div>
							
					</div>
				<div v-if="bd_data.length>5" class="cy_CIASFE_tmlnps"><a v-on:click="open_mt()" href="#">{{is_open?'关闭':'展开'}}</a></div>
			</div>
			
			<div class="cy_CIAFE_intmonbox08">
				<div class="cy_CIAFE_intmonbox09">相关新闻</div>
				<div class="cy_CIAFE_intmonbox11">中消协约谈华帝“夺冠退全款”构成合同邀约</div>
				<div class="cy_CIAFE_intmonbox11">华帝用户退全款遇阻 中消协三度出马管用吗？</div>
				<div class="cy_CIAFE_intmonbox11">华帝“法国队夺冠退款”变“退卡”</div>
				<div class="cy_CIAFE_intmonbox11">华帝员工被欠薪 华帝停摆二十天老板被曝跑路</div>
				<div class="cy_CIAFE_intmonbox11">华帝“夺冠退款”变“退卡” 品牌营销套路到你了吗</div>
			</div>
		</div>
	</div>
</div>
</div>
<div class="cy_CIASFE_footer02"><a href="">使用手册</a>&nbsp;&nbsp;&nbsp;&nbsp;联系我们（电话：1648726161  邮箱：sales@ichangyun.com）    Copyright&copy;2018-2021 &nbsp;&nbsp;&nbsp;&nbsp;湖北畅云时讯软件技术有限公司版权所有</div>

</body>
<script type="text/javascript" src="${ctx}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${ctx}/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/js/vue.min.js"></script>
<script type="text/javascript" src="${ctx}/js/axios.min.js"></script>
<script type="text/javascript" src="${ctx}/js/polyfill.min.js"></script>
<script type="text/javascript" src="${ctx}/js/clipboard.min.js"></script>
<script type="text/javascript" src="${ctx}/js/ic_components.js"></script>
<script type="text/javascript" src="${ctx}/js/comm.js"></script>

<script>

var Info = {
		I0019:'${I0019}',
		W0002:'${W0002}',
		E0022:'${E0022}',
		I0014:'${I0014}',
		E0022:'${E0022}',
		E0004:'${E0004}',
		E0014:'${E0014}',
		I0012:'${I0012}',
		E0025:'${E0025}',
		I0013:'${I0013}',
		W0003:'${W0003}',
		I0023:'${I0023}'
};

var menu_datas = JSON.parse('${front_menu}');  //菜单数据来源于 classes/resources.properties

var menu_url_data = "${m_url}";

var collect_datas = [];  //我的收藏模块对应的数据集合

Vue.component('ic_top_menu',ic_top_menu);   // 顶部菜单栏  ic_top_menu 引自 js/ic_component.js

Vue.component('ic_user_info',ic_user_info);  // 用户信息框 ic_user_info 引自 js/ic_component.js

Vue.component('ic_collectiont', ic_collectiont); // ic_collectiont 引自 js/ic_component.js

var m_article_id = '${m_article_id}';

var app = new Vue({
	el:"#app",
	data:{
		m_article_id:m_article_id,
		article_data:'',//文章数据
		bd_data:[],
        sim_datas: [],   //相似文章
        montime: '0',  //监测时间
		is_open:false
	},
	methods:{
		getArticleByID:function(){  //根据ID获得文章详情
			var _this = this;
			axios.get('../detailspage/getArticleByID',{
				params: {
					   article_id:_this.m_article_id
					}
				})
				.then(function (response) {
					
					var data = JSON.parse(response.data);
					
					_this.article_data = data.article_data;
					
					_this.bd_data = data.bd_data;
					
					if(_this.article_data==''){
						 layer.msg("该文章已经被删除");
                    	 setTimeout(function(){
                    		 open(location, '_self').close();
                    	 },1800);
					}
					
				})
				.catch(function (error) {
				    console.log(error);
				});
		},
		share: function () {
            var id = "sharedv" ;
            var clipboard = new Clipboard('#' + id);
            clipboard.on('success', function (e) {
                layer.msg(Info.I0019);
            });
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
                        deletemode: '8', //删除方式,详情页删除
                    })
                    .then(function (response) {
                        if (response.data == 'ok') {
                        	 layer.msg(Info.I0014);
                        	 setTimeout(function(){
                        		 open(location, '_self').close();
                        	 },1800);
                        	 self.opener.app.from_child_search(); 
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
		open_mt:function(){
			
			this.is_open = !this.is_open;
			
			
		},
        simcontent: function () {        //获得相似文章
            if (this.article_data.sim_cnt > 0 && this.sim_datas.length == 0) {
                var _this = this;
                axios.get('../detailspage/getSimContent', {
                    params: {
                        Article_ID: _this.article_data.article_ID,
                    }
                })
                    .then(function (response) {
                        var data = JSON.parse(response.data);
                        for (var i = 0; i < data.length; i++) {
                            _this.sim_datas.push(data[i]);
                        }

                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }

        },
        updateCollent: function (aid) {
        	this.article_data.collcnt = 1;
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
                    for (var i = 0; i < data.length; i++) {
                        if (data[i].parent_CollectionType_ID == '' || data[i].parent_CollectionType_ID == 'C000000000') {  //根节点
                            var ctnode = new CTreeNode(data[i], 0);
                            collect_datas.push(ctnode);
                            _this.addCnodeChildren(ctnode, data);
                        }
                    }
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
	},
	mounted:function(){
		this.getCollect();
		this.getArticleByID();
	}
});

</script>
</html>
