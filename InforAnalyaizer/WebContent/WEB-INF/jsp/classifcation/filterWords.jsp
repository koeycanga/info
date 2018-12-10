<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="ss" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources" var="sysInfo" />
<fmt:message key="front_menu" var="front_menu" bundle="${sysInfo}" />
<fmt:message key="E0006" var="E0006" bundle="${sysInfo}" />
<fmt:message key="I0012" var="I0012" bundle="${sysInfo}" />
<fmt:message key="I0013" var="I0013" bundle="${sysInfo}" />
<fmt:message key="I0015" var="I0015" bundle="${sysInfo}" />
<fmt:message key="I0016" var="I0016" bundle="${sysInfo}" />
<fmt:message key="W0004" var="W0004" bundle="${sysInfo}" />
<fmt:message key="I0011" var="I0011" bundle="${sysInfo}" />
<fmt:message key="I0024" var="I0024" bundle="${sysInfo}" />
<fmt:message key="I0002" var="I0002" bundle="${sysInfo}" />
<fmt:message key="E0043" var="E0043" bundle="${sysInfo}" />

<html xmlns:v-on="http://www.w3.org/1999/xhtml"
	xmlns:v-bind="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!--  <link rel="stylesheet" type="text/css" href="${ctx}/css/cy_CIAS_style.css"> -->
<link rel="stylesheet" type="text/css" href="${ctx}/layui/css/layui.css" />
<link id="lnk" rel="stylesheet" type="text/css" href="">
<style>
[v-cloak] {
	display: none;
}
</style>
<script type="text/javascript" src="${ctx}/js/polyfill.min.js"></script>
</head>
<body>
	<div id="filterWords" v-cloak>
		<div class="cy_hidebg" id="cy_hidebg"></div>
		<div class="cy_CMICBMS_bodybg">
			<p>
				情报规划 > <span>过滤词管理</span>
			</p>
			<!-- 条件检索表单 -->
			<div class="cy_CMICBMS_tablebox">
				<form @submit.prevent>
					<div class="cy_CMICBMS_box07">
						<div class="cy_CMICBMS_schbox01">
							<input type="text" placeholder="请输入关键词检索"
								v-model="fwVoForSearch.classificationName">
						</div>
						<input type="button" class="cy_CMICBMS_schbtn" value="检索"
							v-on:click="btn_search">
					</div>
				</form>
				<!-- 功能按钮 -->
				<table>
				<tr>
				<td><input type="button" class="cy_CMICBMS_edbtn" value="编辑"
					v-on:click="btn_update">&nbsp;&nbsp;</td>
				<td>			 <input type="button"
					class="cy_CMICBMS_impbtn" id="impbtn" value="导入"   lay-data="{url: '${ctx}/qbgh/filterWords/input', accept: 'file'}"
					name="file" @mouseenter="show_import_in" @mouseleave="show_import_out"></td>
				<td>&nbsp;&nbsp;<input type="button" class="cy_CMICBMS_expbtn" value="导出"
					v-on:click="btn_output">&nbsp;&nbsp;</td>
					<td v-if="show_import"><font color="red" size="1">请按照模板格式导入文件，最大不超过1024M</font></td>
				</tr>
				</table>
			</div>
			<form action="output" ref="outputForm">
				<div class="cy_CMICBMS_box08">
					<div class="cy_CMICBMS_box08">
						<table width="100%" border="0" class="cy_CMICBMS_accmngtb"
							cellspacing="0">
							<tbody>
								<tr>
									<th scope="col" width="25px"><input type="checkbox"
										v-model='isok' v-on:click='checkedAll' disabled="disabled" />
									</th>
									<th scope="col" width="40%">分类名称</th>
									<th nowrap scope="col" width="10%">信息向性区分</th>
									<th nowrap scope="col" width="30%">敏感词过滤范围</th>
									<th nowrap scope="col" width="auto">最近编辑时间</th>
									<th nowrap scope="col" width="auto">最近编辑者</th>
								</tr>
								<tr class="cy_CMICBMS_treven" v-for="fwVo in fwVos">
									<td><ic_tree_dv_checkbox :model="fwVo"></ic_tree_dv_checkbox>
									</td>
									<td>
										<div class="cy_tree_node">
											<ic_tree_dv_name :model="fwVo"></ic_tree_dv_name>
										</div>
									</td>
									<td><ic_tree_dv_tropism :model="fwVo"></ic_tree_dv_tropism>
									</td>
									<td><ic_tree_dv_all :model="fwVo"></ic_tree_dv_all></td>
									<td nowrap><ic_tree_dv_time :model="fwVo"></ic_tree_dv_time>
									</td>
									<td><ic_tree_dv_user :model="fwVo"></ic_tree_dv_user></td>
								</tr>
								<tr>
									<td v-if="fwVos.length==0" colspan="6">{{info}}</td>
								</tr>
								</form>
							</tbody>
						</table>
					</div>
				</div>
				<pager ref="pagecomponent"></pager>
		</div>
	</div>
	<!-- ==================隐藏层=========================== -->
	<div id="updateform">
		<div id="cy_CMICBMS_add">
			<div class="cy_CMICBMS_addtop">
				<div class="cy_CMICBMS_addtit">{{title}}</div>
				<div class="cy_CMICBMS_addclose" onClick="hide();">X</div>
			</div>
			<span>所属分类：{{ssfl_msg}}</span>
			<div class="cy_CMICBMS_addtb">
				<div id="cy_CMICBMS_status">
					信息项区分：<input type="radio" name="RadioGroup1" value="1"
						id="RadioGroup1_0" v-model="fwVoForUpdate.informationtropism">区分
					<input type="radio" name="RadioGroup1" value="0" id="RadioGroup1_1"
						v-model="fwVoForUpdate.informationtropism">不区分
				</div>
				<br> <br>
				<div class="cy_CMICBMS_addwords">
					<div class="layui-tab">
						<ul class="layui-tab-title">
							<li class="layui-this">全部</li>
							<li>标题</li>
							<li>摘要</li>
							<li>正文</li>
						</ul>
						<div class="layui-tab-content" style="height: 100px;">
							<div class="layui-tab-item layui-show">

								<div>
									核心词组：<input type="text" placeholder="输入词组"
										class="cy_CMICBMS_addinput"
										v-model="fwVoForUpdate.allcorephrases">
								</div>

								<div>

									排除词组：<input type="text" placeholder="输入词组"
										class="cy_CMICBMS_addinput"
										v-model="fwVoForUpdate.allexcludephrases">
								</div>
							</div>
							<div class="layui-tab-item">
								<div>
									核心词组：<input type="text" placeholder="输入词组"
										class="cy_CMICBMS_addinput"
										v-model="fwVoForUpdate.titlecorephrases">
								</div>
								<div>
									排除词组：<input type="text" placeholder="输入词组"
										class="cy_CMICBMS_addinput"
										v-model="fwVoForUpdate.titleexcludephrases">
								</div>
							</div>
							<div class="layui-tab-item">
								<div>
									核心词组：<input type="text" placeholder="输入词组"
										class="cy_CMICBMS_addinput"
										v-model="fwVoForUpdate.summarycorephrases">
								</div>
								<div>
									排除词组：<input type="text" placeholder="输入词组"
										class="cy_CMICBMS_addinput"
										v-model="fwVoForUpdate.summaryexcludephrases">
								</div>
							</div>
							<div class="layui-tab-item">
								<div>
									核心词组：<input type="text" placeholder="输入词组"
										class="cy_CMICBMS_addinput"
										v-model="fwVoForUpdate.textcorephrases">
								</div>
								<div>
									排除词组：<input type="text" placeholder="输入词组"
										class="cy_CMICBMS_addinput"
										v-model="fwVoForUpdate.textexcludephrases">
								</div>
							</div>
						</div>
					</div>

				</div>
				<div style="margin: 40px 20px 0 0;">
					<input type="button" value="确定" class="cy_CMICBMS_schbtn"
						v-on:click="submit">
				</div>
			</div>

		</div>
	</div>

	<script src="${ctx}/js/vue.min.js"></script>
	<script src="${ctx}/js/vue-resource.min.js"></script>
	<script src="${ctx}/js/axios.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/layer/layer.js"></script>
	<script type="text/javascript" src="${ctx}/layui/layui.js"></script>
	<script type="text/javascript" src="${ctx}/js/ic_components.js"></script>
	<script type="text/javascript" src="${ctx}/js/comm.js"></script>

	<script>

    AdaptationResolution('${ctx}'); //分辨率适配

    //提示信息
    var Info = {
        E0006: '${E0006}',
        I0012: '${I0012}',
        I0013: '${I0013}',
        I0015: '${I0015}',
        W0004: '${W0004}',
        I0011: '${I0011}',
        I0024: '${I0024}',
        I0002: '${I0002}',
        E0043: '${E0043}',
        I0016: '${I0016}'
    };
    //右侧主体控制vm
    var vm = new Vue({
        el: '#filterWords',
        data: {
            fwVoForSearch: {},    //携带搜索条件的vo对象
            fwVos: [],            //返回的节点列表
            isok: false,          //全选判定
            info: Info.I0024,    //提示信息：搜索结果为空
            checkedId: [],
            show_import:false,
            ssfl_msg: ""			//所属分类
        },

        components: {
            'pager': ic_pager   //分页组件
            
        },
        methods: {
        	show_import_in:
        		function () {
					this.show_import=true;
				},
			show_import_out:
				function name() {
					this.show_import=false;
				},
       		upload_ok:function (response, file, fileList){
	        	console.log()
	        	if(response.msg=="ok"){
	        		layer.msg(Info.I0015);
	        	}else{
	        		layer.msg(Info.I0016);
	        	}
	        },
	   		upload_fault:function (err, file, fileList){
	       		layer.msg(Info.I0016);
	        },
            checkedAll:
                function () {
                    if (!this.isok) { //全选中 所有CheckBox
                        var fwVos = [];
                        var fwVos = this.fwVos;
                        this.checkedId = [];
                        fwVos.forEach(function (fwVo) {     //将id装填进checkedId[]中
                            this.checkedId.push(fwVo.classificationId);
                        }, this);
                    } else {   //反选清楚所有CheckBox选中
                        this.checkedId = []
                    }
                },
            btn_search:   //搜索按钮点击事件
                function () {
                    this.search(this.$refs.pagecomponent.pageBean);
                },
            btn_output:
                function () {
                    var checkedId = [];
                    checkedId = this.checkedId;
                    if (checkedId.length ==0) {
                        layer.msg(Info.E0043);      //提示：请选择要导出的数据
                    }else{
                        this.$refs.outputForm.submit();
                    }
                },
            btn_update:										//编辑
                function () {
                    var checkedId = [];
                    checkedId = this.checkedId;
                    if (checkedId.length != 1) {
                        layer.msg(Info.E0006);      //提示：请选择一条数据
                    } else {
                        vm2.title = "编辑分类过滤词";
                        vm2.url = "updateFwVo";
                        this.ssfl_msg = '';
                        var clicknode = this.getClickNode(checkedId[0], this.fwVos, 0)[1];
                        var ssflarr = [];
                        ssflarr.push(clicknode);
                        this.getAllNodeFromANode(clicknode, ssflarr);
                        for (var i = ssflarr.length - 1; i >= 0; i--) {
                            if (i == ssflarr.length - 1) {
                                this.ssfl_msg += ssflarr[i].classificationName;
                            } else {
                                this.ssfl_msg += " > > " + ssflarr[i].classificationName;
                            }
                        }
                        ;
                        vm2.ssfl_msg = this.ssfl_msg;
                        axios.get("queryOne", {     //查询当前节点信息回显在弹出层中
                            params: {
                                classificationId: checkedId[0]
                            }
                        }).then(function (res) {
                            vm2.fwVoForUpdate = res.data;
                            if (vm2.fwVoForUpdate.informationtropism == null) {   //当前节点没有过滤词时，预设不区分
                                vm2.fwVoForUpdate.informationtropism = '0';
                            }
                            window.show();
                        })
                            .catch(function (error) {
                                console.log(error);
                            });

                    }

                },
            opClose: function (id) {       //id：点击时传入当前节点id
                var click_node = this.getClickNode(id, this.fwVos, 0)[1];   //获取当前节点信息的方法
                if (!click_node.is_show) {                      //打开
                    click_node.imgsrc = "${ctx}/image/is-tbarr02.png"   //更改三角图标
                    axios.get('queryChild', {          //查询当前节点的子节点信息
                        params: {
                            parent_Classification_ID: id
                        }
                    })
                        .then(function (response) {
                            click_node.children = response.data;
                            for (var i = 0; i < click_node.children.length; i++) {
                                click_node.children[i].depth = click_node.depth + 1;          //为节点的depth属性赋值
                                click_node.children[i].imgsrc = "${ctx}/image/is-tbarr01.png" //节点三角图标赋值
                                click_node.children[i].is_al_show = true;           //递归插件显示状态
                            }
                            click_node.is_show = true;

                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                } else {         //关上
                    click_node.imgsrc = "${ctx}/image/is-tbarr01.png"
                    click_node.is_show = false;

                }
            },
            getClickNode: function (id, arr, depth) {      //根据id查询arr中符合id的节点信息，depth：方便赋值深度
                var node = null;
                var b = true;
                for (var i = 0; i < arr.length; i++) {
                    if (arr[i].classificationId == id) {
                        node = [];
                        arr[i].depth = depth;
                        node[0] = i;
                        node[1] = arr[i];
                        b = false;
                        break;
                    }
                }
                if (b) {
                    depth++;
                    for (var i = 0; i < arr.length; i++) {
                        if (arr[i].children.length > 0) {
                            node = this.getClickNode(id, arr[i].children, depth);

                            if (node != null) {
                                return node;
                            }
                        }
                    }
                }
                return node;

            },
            dealopClose: function (id, search_key, vos) {  //递归打开ID为id的node节点及其子节点
                var _this = this;
                var click_node = this.getClickNode(id, vos, 0)[1];
                if (!click_node.is_show) {                      //打开
                    click_node.imgsrc = "${ctx}/image/is-tbarr02.png"
                    axios.get('queryChild', {
                        params: {
                            parent_Classification_ID: id
                        }
                    })
                        .then(function (response) {
                            click_node.children = response.data;

                            for (var i = 0; i < click_node.children.length; i++) {
                                click_node.children[i].depth = click_node.depth + 1;
                                click_node.children[i].imgsrc = "${ctx}/image/is-tbarr01.png"
                            }
                            if (click_node.isParent == 1) {   //如果click_node具有子节点,则进一步打开它的所有子节点
                                click_node.is_show = true;
                                click_node.imgsrc = "${ctx}/image/is-tbarr02.png";

                                for (var i = 0; i < click_node.children.length; i++) {   //根据查询条件判断click_node的子节点是否永远不显示
                                    var b = true;
                                    if (click_node.classificationName.indexOf(search_key) >= 0) { //当父节点包含检索信息时，其子节点全部显示
                                        b = true;
                                    } else if (click_node.children[i].classificationName.indexOf(search_key) < 0) {
                                        b = false;
                                    }
                                    click_node.children[i].is_al_show = b;
                                    _this.dealopClose(click_node.children[i].classificationId, search_key, vos);  //递归
                                }
                            }
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                    /*  this.dealDigui(click_node,true); */
                }
            },
            getAllNodeFromANode: function (node, arr) {     //获得父节点名
                if (node.parent_Classification_ID != '0000000000') {
                    var parent_node = this.getClickNode(node.parent_Classification_ID, this.fwVos, 0)[1];
                    arr.push(parent_node);
                    this.getAllNodeFromANode(parent_node, arr);
                }
            }

        },
        watch: {
            "checkedId": function () {
                if (this.checkedId.length != this.fwVos.length) {
                    this.isok = false
                } else {
                    this.isok = true
                }
            }
        },
        computed: {
            search: function () {
                return function (pageBean) {
                    var l_index = layer.msg(Info.I0011, {   //加载中msg
                        icon: 16
                        , shade: 0.01
                    });
                    var _this = this;
                    var fwVo = this.fwVoForSearch;
                    var url = "../filterWords/queryAll";
                    axios.get(url, {
                        params: {
                            classificationName: fwVo.classificationName,    //节点名检索信息
                            pageNow: pageBean.pageNow,                        //分页参数
                            rowSize: pageBean.rs_selected
                        }
                    })
                        .then(function (response) {
                            var rowCount = response.data.rowCount + "";
                            var allfwVos = response.data.fwVos;
                            for (var i = 0; i < allfwVos.length; i++) {
                                allfwVos[i].imgsrc = "${ctx}/image/is-tbarr01.png"
                                allfwVos[i].is_al_show = true;
                            }
                            _this.$refs.pagecomponent.dealAfterSearch(rowCount); //rowCount为总条目数  在ajax请求返回函数需调用该方法
                            _this.fwVos = allfwVos;
                            _this.checkedId = [];
                            if (_this.fwVoForSearch.classificationName != '' && _this.fwVoForSearch.classificationName != null) {    //如果查询词不为空，则递归打开所有节点
                                for (var i = 0; i < _this.fwVos.length; i++) {
                                    _this.dealopClose(_this.fwVos[i].classificationId, _this.fwVoForSearch.classificationName, _this.fwVos);
                                }
                            }
                            layer.close(l_index);	//关闭动画
                            if (rowCount == '0') {
                                layer.msg(Info.I0002);
                            }
                            if(response.data.rowCount>parseInt(Info.MaxSearchCnt)){
                            	layer.msg(Info.W0001)
                            }
                        })
                        .catch(function (error) {
                            console.log(error);
                        });

                }
            }
        
        },
        mounted: function () {
            this.search(this.$refs.pagecomponent.pageBean);
        }
    });
    //弹出层控制vm
    var vm2 = new Vue({
        el: '#updateform',
        data: {
            fwVoForUpdate: {},
            title: "",
            ssfl_msg: "",			//节点名称
            url: ""
        },
        methods: {
            submit:
                function () {
                    var fwVo = this.fwVoForUpdate;
                    var _this = this;
                    axios.get(_this.url, {
                        params: {
                            classificationId: fwVo.classificationId,        //id
                            informationtropism: fwVo.informationtropism,    //是否区分
                            allcorephrases: fwVo.allcorephrases,            //全部
                            allexcludephrases: fwVo.allexcludephrases,
                            titlecorephrases: fwVo.titlecorephrases,      //标题
                            titleexcludephrases: fwVo.titleexcludephrases,
                            summarycorephrases: fwVo.summarycorephrases,    //摘要
                            summaryexcludephrases: fwVo.summaryexcludephrases,
                            textcorephrases: fwVo.textcorephrases,      //内容
                            textexcludephrases: fwVo.textexcludephrases,
                            updatedatetime: fwVo.updatedatetime        //更新时间
                        }
                    }).then(function (response) {
                        if (response.data.msg == "ok") {
                            window.hide();
                            vm.btn_search();
                            layer.msg(Info.I0012);
                        } else if (response.data.msg == "fault") {
                            layer.msg(Info.I0013);      //更新失败msg
                        } else if (response.data.msg == "checkFalse") {
                            layer.msg(Info.W0004);     //排他失败msg
                        }
                    })
                        .catch(function (error) {
                            console.log(error);
                        });
                }
        }
    });

    function hide()  //去除隐藏层和弹出层
    {
        document.getElementById("cy_hidebg").style.display = "none";
        document.getElementById("cy_CMICBMS_add").style.display = "none";
    }

    function show() {
        var hideobj = document.getElementById("cy_hidebg");
        cy_hidebg.style.display = "block";  //显示隐藏层
        document.getElementById("cy_CMICBMS_add").style.display = "block";  //显示弹出层
    }

    //复选框列组件
    var ic_tree_dv_checkbox = {
        template: '<div><div v-bind:style="t_style" v-show="model.is_al_show">' +

        '<input type="checkbox" :disabled="model.isParent==1" v-bind:value="model.classificationId"  v-model="vm.checkedId" class="cy_CMICBMS_checkbox" name="ids">&nbsp;<label for="cy_CMICBMS_checkbox"></label></div>' +
        '<div v-show="model.is_show" ><ic_tree_dv_checkbox  v-for="model in model.children"  v-bind:model="model"></ic_tree_dv_checkbox>&nbsp;</div></div>',
        props: ['model'],
        data: function () {
            return {
                t_style: {padding: '10px 0px !important'},
            }
        },
    };

    //名称列组件
    var ic_tree_dv_name = {
        template: '<div><div v-bind:style="t_style" v-bind:title="model.classificationName" class="cy_tree_node_hover" v-show="model.is_al_show">' +
        '<img v-if="model.isParent==1" v-bind:src="model.imgsrc" style="cursor:pointer" v-on:click="opClose(model.classificationId)">' +
        '{{model.classificationName}}&nbsp;</div>' +
        '<div v-show="model.is_show" ><ic_tree_dv_name  v-for="model in model.children" v-bind:model="model"></ic_tree_dv_name></div></div>'
        ,
        props: ['model'],

        data: function () {
            return {
                t_style: {padding: '10px 0px !important'}
            }
        },
        methods: {    //单击三角图标
            opClose: function (id) {
                this.$parent.opClose(id);
            }
        },
        mounted: function () {
            this.t_style = {padding: '10px ' + (50 * this.model.depth) + 'px !important'};
        }
    };
    //区分列组件
    var ic_tree_dv_tropism = {
        template: '<div><div v-bind:style="t_style" v-show="model.is_al_show">' +
        '{{model.informationtropism==1?\'区分\':\'\'}}&nbsp;</div>' +
        '<div v-show="model.is_show" ><span></span><span> </span><ic_tree_dv_tropism  v-for="model in model.children" v-bind:model="model"></ic_tree_dv_tropism></div></div>'
        ,
        props: ['model'],
        data: function () {
            return {
                t_style: {padding: '10px 0px !important'},
            }
        }
    }
    //过滤词列组件
    var ic_tree_dv_all = {
        template: '<div ><div v-bind:style="t_style" v-show="model.is_al_show">' +

        '{{model.allcorephrases!=""||model.allexcludephrases!=""?\'全部；\':\'\'}}' +
        '{{model.titlecorephrases!=""||model.titleexcludephrases!=""?\'标题；\':\'\'}}' +
        '{{model.summarycorephrases!=""||model.summaryexcludephrases!=""?\'摘要；\':\'\'}}' +
        '{{model.textcorephrases!=""||model.textexcludephrases!=""?\'正文；\':\'\'}}&nbsp;</div>' +
        '<div v-if="model.is_show" ><span> </span><span> </span><ic_tree_dv_all  v-for="model in model.children" v-bind:model="model"></ic_tree_dv_all></div></div>'
        ,
        props: ['model'],
        data: function () {
            return {
                t_style: {padding: '10px 0px !important'},
            }
        }
    }
    //更新时间列组件
    var ic_tree_dv_time = {
        template: '<div ><div v-bind:style="t_style" v-show="model.is_al_show">' +

        '{{model.updatedatetime}}&nbsp;</div>' +
        '<div v-show="model.is_show" ><span> </span><span> </span><ic_tree_dv_time  v-for="model in model.children" v-bind:model="model"></ic_tree_dv_time></div></div>'
        ,
        props: ['model'],
        data: function () {
            return {
                t_style: {padding: '10px 0px !important'},
            }
        }
    }
    //创建人列组件
    var ic_tree_dv_user = {
        template: '<div ><div v-bind:style="t_style" v-show="model.is_al_show">' +

        '{{model.updateuser}}&nbsp;</div>' +
        '<div v-show="model.is_show" ><span> </span><span> </span><ic_tree_dv_user  v-for="model in model.children" v-bind:model="model"></ic_tree_dv_user></div></div>'
        ,
        props: ['model'],
        data: function () {
            return {
                t_style: {padding: '10px 0px !important'},
            }
        }
    }
    //组件注册
    Vue.component('ic_tree_dv_checkbox', ic_tree_dv_checkbox);
    Vue.component('ic_tree_dv_name', ic_tree_dv_name);
    Vue.component('ic_tree_dv_tropism', ic_tree_dv_tropism);
    Vue.component('ic_tree_dv_all', ic_tree_dv_all);
    Vue.component('ic_tree_dv_time', ic_tree_dv_time);
    Vue.component('ic_tree_dv_user', ic_tree_dv_user);
    

    //layui的tab、上传文件插件
    layui.use(['upload', 'element'], function () {
         var upload = layui.upload; 
        var element = layui.element;
        
        //执行实例
        upload.render({
            elem: '#impbtn' //绑定元素
            ,size: 1048576		//最大大小
            , done: function (res) {
                //上传完毕回调
                if(res.msg=="ok"){
	                vm.btn_search();
	                layer.msg(Info.I0015);	
                }else if(res.msg=="fault"){
                	vm.btn_search();
	                layer.msg(Info.I0016);
                }
                
            }
            , error: function () {
                //请求异常回调
                layer.msg(Info.I0016);
            }
        });
    });

</script>

</body>

</html>