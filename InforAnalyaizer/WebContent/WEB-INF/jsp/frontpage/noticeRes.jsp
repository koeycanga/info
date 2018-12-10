<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="ss" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources" var="sysInfo" />
<fmt:message key="front_menu" var="front_menu" bundle="${sysInfo}" />
<fmt:message key="E0019" var="E0019" bundle="${sysInfo}" />
<fmt:message key="E0004" var="E0004" bundle="${sysInfo}" />
<fmt:message key="E0014" var="E0014" bundle="${sysInfo}" />
<fmt:message key="I0012" var="I0012" bundle="${sysInfo}" />
<fmt:message key="I0013" var="I0013" bundle="${sysInfo}" />
<fmt:message key="I0002" var="I0002" bundle="${sysInfo}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>竞争情报分析系统</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/cy_CIAS_style.css">

</head>

<body style="background-color: #f2f3f8;">
	<div class="cy_hidebg" id="cy_hidebg"></div>
	<div id="notice">
		<!--头部-->
		<div class="cy_CIASFE_top">
			<div class="cy_CIASFE_logo">
				<img src="${ctx}/image/fontend-logo.png">
			</div>
			<div class="cy_CIASFE_tit">国药器械-竞争情报分析系统</div>
			<ic_top_menu></ic_top_menu>
			<!-- 上部菜单栏 -->
			<ic_user_info></ic_user_info>
			<!-- 登录用户信息框 -->
		</div>

		<div class="cy_CIAFE_main">
			<div class="cy_CIASFE_intmonbody">
				<div class="cy_CIASFE_intmonbodyright">

					<div class="cy_CIASFE_condition02">
						<div class="cy_CIASFE_conbox04">
							<a href="toNoticeRes"><div class="cy_CIASFE_conbox05">简报列表</div></a>
							<a href="../notice/toNoticeList"><div
									class="cy_CIASFE_conbox06">简报任务</div></a>
						</div>
						<div class="cy_CIASFE_conbox12">
							<table width="100%" border="0" class="cy_CIASFE_bltb"
								cellspacing="0">
								<tbody>
									<tr>
										<th width="25%">任务名称</th>
										<th width="25%">生成时间</th>
										<th width="25%">生成状态</th>
										<th style="text-align: center" width="25%">操作</th>
									</tr>
									<tr class="cy_CMICBMS_treven" v-for="vo in vos">

										<td>{{vo.title}}</td>
										<td>{{vo.createdatetime}}</td>
										<td>{{vo.executeresult==0?"成功":"失败"}}</td>
										<td style="text-align: center"><input type="button"
											value="查看" v-on:click="btn_showRes(vo.executeid)">&nbsp;&nbsp;&nbsp;
											<input type="button" value="下载"
											v-on:click="btn_downloadRes(vo.executeid)"></td>
									</tr>
								</tbody>
							</table>
							<pager ref="pagecomponent"></pager>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<div class="cy_CIASFE_footer02">
		<a href="">使用手册</a>&nbsp;&nbsp;&nbsp;&nbsp;联系我们（电话：1648726161
		邮箱：sales@ichangyun.com） Copyright&copy;2018-2021
		&nbsp;&nbsp;&nbsp;&nbsp;湖北畅云时讯软件技术有限公司版权所有
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="${ctx}/js/vue.min.js"></script>
	<script src="${ctx}/js/vue-resource.min.js"></script>
	<script src="${ctx}/js/axios.min.js"></script>
	<script type="text/javascript" src="${ctx}/layer/layer.js"></script>
	<script type="text/javascript" src="${ctx}/js/ic_components.js"></script>
	<script>
var Info = {
		E0019:'${E0019}',
		E0004:'${E0004}',
		E0014:'${E0014}',
		I0012:'${I0012}',
        I0002:'${I0002}',
		I0013:'${I0013}'
		
};


var menu_datas = JSON.parse('${front_menu}') //菜单数据来源于 classes/resources.properties
var menu_url_data  = 'noticeRes';

Vue.component('ic_top_menu',ic_top_menu);   // ic_top_menu 引自 js/ic_component.js

Vue.component('ic_user_info',ic_user_info);  // ic_user_info 引自 js/ic_component.js

    var vm = new Vue({
        el: '#notice',
        data: {
            vos: []
        },
        mounted: function () {
        	
            this.btn_search();
        },
        components: {
            'pager': ic_pager
        },
        methods: {
            btn_search:
                function () {
	            	this.$refs.pagecomponent.pageBean.pageNow = 1;
                    this.search(this.$refs.pagecomponent.pageBean);
                },
            btn_showRes:function(id){
            	layer.msg(Info.E0019);
			},
            btn_downloadRes:function(id){
					layer.msg(id);
			}

        },

        computed: {
            search: function () {
                return function (pageBean) {
                    var _this = this;
                    var url = "queryAll";
                    axios.get(url, {
                        params: {
                            pageNow: pageBean.pageNow,
                            rowSize: pageBean.rs_selected
                        }
                    })
                        .then(function (response) {
                            var rowCount = response.data.rowCount + "";
                            _this.$refs.pagecomponent.dealAfterSearch(rowCount); //rowCount为总条目数  在ajax请求返回函数需调用该方法
                            _this.vos = response.data.vos;
                            if(rowCount=='0'){
      	    					 layer.msg(Info.I0002);
      	    				  	}
                        })
                        .catch(function (error) {
                            console.log(error);
                        });

                }
            }
        }
    });
</script>
</body>

</html>