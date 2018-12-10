<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib prefix="ss" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources" var="sysInfo"/>
<fmt:message key="front_menu" var="front_menu" bundle="${sysInfo}"/>
<fmt:message key="E0006" var="E0006" bundle="${sysInfo}"/>
<fmt:message key="E0019" var="E0019" bundle="${sysInfo}"/>
<fmt:message key="W0002" var="W0002" bundle="${sysInfo}"/>
<fmt:message key="I0014" var="I0014" bundle="${sysInfo}"/>
<fmt:message key="I0013" var="I0013" bundle="${sysInfo}"/>
<fmt:message key="I0012" var="I0012" bundle="${sysInfo}"/>
<fmt:message key="I0024" var="I0024" bundle="${sysInfo}"/>
<fmt:message key="E0001" var="E0001" bundle="${sysInfo}"/>
<fmt:message key="E0039" var="E0039" bundle="${sysInfo}"/>
<fmt:message key="I0025" var="I0025" bundle="${sysInfo}"/>
<fmt:message key="E0040" var="E0040" bundle="${sysInfo}"/>
<fmt:message key="E0041" var="E0041" bundle="${sysInfo}"/>
<fmt:message key="W0004" var="W0004" bundle="${sysInfo}"/>
<fmt:message key="I0011" var="I0011" bundle="${sysInfo}"/>
<fmt:message key="I0002" var="I0002" bundle="${sysInfo}"/>
<fmt:message key="E0055" var="E0055" bundle="${sysInfo}"/>
<fmt:message key="E0061" var="E0061" bundle="${sysInfo}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>竞争情报分析系统</title>
    <link rel="stylesheet" type="text/css"
          href="${ctx}/css/cy_CIAS_style.css">
    <style>
        [v-cloak] {
            display: none;
        }
    </style>
</head>

<body style="background-color: #f2f3f8;">
<div class="cy_hidebg" id="cy_hidebg"></div>
<!--头部-->
<div class="cy_CIASFE_top" v-cloak>
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
        <!--============左侧收藏夹=====================-->
        <div class="cy_CIASFE_intmonbodyleft" id="collectionType" v-cloak>
            <div class="cy_CIASFE_colletree">
                <div class="cy_CIASFE_newfolder">
                    <input type="button" value="新建文件夹" v-on:click="btn_addType()">
                </div>
                <ul>
                    <li class="cy_CIASFE_colletree1st"><a><img
                            src="${ctx}/image/colle-arrow02.png"><img
                            src="${ctx}/image/colle-folder.png">我的收藏 </a></li>
                    <ul>
                        <div v-for="typeVo in typeVos">
                            <ic_tree_collect :model="typeVo"></ic_tree_collect>
                        </div>
                    </ul>
                </ul>
            </div>
        </div>
        <!--=========================================-->


        <!--============右侧列表=====================-->
        <div class="cy_CIASFE_intmonbodyright" id="collections" v-cloak>
            <div class="cy_CIASFE_search">
                <select v-model="searchOpt">
                    <option :value="opt.id" v-for="opt in opts">{{opt.name}}</option>
                </select>
                <div class="cy_CMICBMS_schbox02">
                    <input type="text" placeholder="请输入关键词检索…" style="cursor: text"
                           v-model="voSearch.articleTitle">
                </div>
                <input type="button" class="cy_CMICBMS_schbtn" value="检索"
                       v-on:click="btn_search">
            </div>
            <div class="cy_CIASFE_condition02">

                <div class="cy_CIASFE_conbox13">
                    <input type="button" class="cy_CMICBMS_mvbtn" value="移动"
                           v-on:click="btn_move"> <input type="button"
                                                         class="cy_CMICBMS_dltbtn" value="删除" v-on:click="btn_delete">
                </div>
                <div class="cy_CIASFE_conbox13">
                    <table width="100%" border="0" class="cy_CIASFE_bttb"
                           cellspacing="0">
                        <tbody>
                        <tr>
                            <th scope="col" width="40px"><input type="checkbox"
                                                                v-model='isok' v-on:click='checkedAll'/></th>
                            <th>收藏夹名称</th>
                            <th>标题名称</th>
                            <th>发布时间</th>
                            <th style="text-align: center;" width="13%">操作</th>
                        </tr>
                        <tr class="cy_CMICBMS_treven" v-for="vo in vos">
                            <td><input type="checkbox" :value="vo.id"
                                       v-model="checkedId"></td>
                            <td>{{vo.allparentname}}</td>
                            <td><a :href="pageUrl+vo.articleId">{{vo.articleTitle}}</a></td>
                            <td>{{vo.releasetime}}</td>
                            <td style="text-align: center;"><a
                                    v-on:click="deleteOne(vo.id)"><img
                                    src="${ctx}/image/usermana-dele.png"></a></td>
                        </tr>
                        <tr>
                            <td v-if="vos.length==0" colspan="4">{{info}}</td>
                        </tr>
                        </tbody>
                    </table>
                    <pager ref="pagecomponent"></pager>
                </div>
            </div>
        </div>
        <!--============================================-->
    </div>

</div>

<div class="cy_CIASFE_footer02">
    <a href="">使用手册</a>&nbsp;&nbsp;&nbsp;&nbsp;联系我们（电话：1648726161
    邮箱：sales@ichangyun.com） Copyright&copy;2018-2021
    &nbsp;&nbsp;&nbsp;&nbsp;湖北畅云时讯软件技术有限公司版权所有
</div>
<!--弹出层-->
<div id="cy_CMICBMS_newfd">
    <div class="cy_CMICBMS_newbttop">
        <div class="cy_CMICBMS_newbttit">{{title}}</div>
        <div class="cy_CMICBMS_newtbclose" onClick="hide();">X</div>
    </div>
    <div class="cy_CMICBMS_newfdbd">
        <div class="cy_CMICBMS_newfdtb" v-if="title!='移动文章'"
             style="margin-bottom: 20px;">
            <div class="cy_CMICBMS_newfdip">
                <span>*</span>名称：
            </div>
            <input type="text" class="cy_CMICBMS_newbtinput" placeholder="请输入名称"
                   v-model="voForm.collectiontypename" maxlength="64">
        </div>
        <!--============点击编辑时树状图出现============-->
        <div class="cy_CMICBMS_newfdtb">
            <div class="cy_CMICBMS_newfdip"
                 v-if="(title=='编辑节点'||title=='移动文章')">
                父节点： <br>

                <ul>
                    <li class="cy_CIASFE_colletree1st"><a><img
                            src="${ctx}/image/colle-arrow02.png"><img
                            src="${ctx}/image/colle-folder.png">
                        <div v-on:click="checkTop">我的收藏</div>
                    </a></li>
                    <ul>
                        <div v-for="typeVo in typeVosForm">
                            <ic_tree_collect_form :model="typeVo"></ic_tree_collect_form>
                        </div>
                    </ul>
                </ul>
            </div>
            <!--=====================================-->
            <div class="cy_CMICBMS_infeddn">
                <input type="button" value="确定" v-on:click="submit">
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${ctx}/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/layui/layui.js"></script>
<script src="${ctx}/js/vue.min.js"></script>
<script src="${ctx}/js/vue-resource.min.js"></script>
<script src="${ctx}/js/axios.min.js"></script>
<script src="//unpkg.com/iview/dist/iview.min.js"></script>
<script type="text/javascript" src="${ctx}/js/ic_components.js"></script>
<script type="text/javascript" src="${ctx}/js/comm.js"></script>


<script>
    var Info = {
        E0006: '${E0006}',
        E0019: '${E0019}',
        W0002: '${W0002}',
        I0014: '${I0014}',
        I0013: '${I0013}',
        I0024: '${I0024}',
        E0001: '${E0001}',
        E0039: '${E0039}',
        I0025: '${I0025}',
        E0040: '${E0040}',
        E0041: '${E0041}',
        I0011: '${I0011}',
        W0004: '${W0004}',
        I0002: '${I0002}',
        E0055: '${E0055}',
        E0061: '${E0061}',
        I0012: '${I0012}'
    };

    function show()  //显示隐藏层和弹出层
    {
        var hideobj = document.getElementById("cy_hidebg");
        cy_hidebg.style.display = "block";  //显示隐藏层
        document.getElementById("cy_CMICBMS_newfd").style.display = "block";  //显示弹出层
    }

    function hide()  //去除隐藏层和弹出层
    {
        document.getElementById("cy_hidebg").style.display = "none";
        document.getElementById("cy_CMICBMS_newfd").style.display = "none";
    }

    var menu_datas = JSON.parse('${front_menu}') //菜单数据来源于 classes/resources.properties

    Vue.component('ic_top_menu', ic_top_menu);   // ic_top_menu 引自 js/ic_component.js

    Vue.component('ic_user_info', ic_user_info);  // ic_user_info 引自 js/ic_component.js

    var vm2 = new Vue({         //控制左侧树状菜单的vue对象
        el: '#collectionType',
        data: {
            typeVos: []			//收藏夹集合
        },
        mounted: function () {
            this.getTypes();	//加载之前执行请求方法

        },

        methods: {
            moveType:function (id,targetId) {
            	var _this = this;
                axios.get('moveType',{ 
                	params:{
                    	collectiontypeId:id,
                    	targetId:targetId
                	}
                })
                    .then(function (res) {
                        if(res.data.msg=="ok"){
                            _this.getTypes();
                        }else if(res.data.msg=="overstep"){
                        	var attrs=['4'];
                            layer.msg(IC_GETINFOBYAttrs(Info.E0055,attrs));//超出最大层数
                        }
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            getTypes: function () {
                var _this = this;
                //获取当前用户的收藏夹
                axios.get('getTypes')
                    .then(function (res) {
                        _this.typeVos = res.data;
                        _this.digui(_this.typeVos, 0,"");
                        vm.btn_search();
                    })
                    .catch(function (error) {
                        console.log(error);
                    });

            },
            opClose: function (model) {
                if (!model.is_show) {               //闭合时点击
                    model.imgsrc = "${ctx}/image/colle-arrow02.png"
                    model.is_show = !model.is_show;
                } else {                  //展开时点击
                    model.imgsrc = "${ctx}/image/colle-arrow01.png"
                    model.is_show = !model.is_show;
                    this.dealdigui(model.children)
                }
            },
            dealdigui: function (children) {			//将当前节点下的子节点的三角图片全部重置
                var _this = this;
                children.forEach(function (model) {
                    model.imgsrc = "${ctx}/image/colle-arrow01.png";        //三角图片
                    if (model.children.length != 0) {
                        _this.dealdigui(model.children);
                    }
                })
            },
            digui: function (typeVos, depth, path) {              //通过递归为所有节点赋图片、path值
                var _this = this;
                typeVos.forEach(function (type) {
                    type.imgsrc = "${ctx}/image/colle-arrow01.png";        //三角图片
                    type.collectionpath = path+type.collectiontypeId;
                    type.depth = depth;
                    if (type.children != []) {
                        depth++;
                        _this.digui(type.children, depth,type.collectionpath );
                    }
                })
            },

            btn_addType: function (model) {		//新建节点弹出
                var pid = "C000000000";
                var ppath = "";
                vm3.title = "新建节点";
                vm3.voForm = {};
                vm3.url = "addType";
                vm3.typeVosForm = this.typeVos;
                if (model != null) {		//如果是通过节点后的编辑按钮改的，就将节点id作为父id赋值
                    pid = model.collectiontypeId;
                    ppath = model.collectionpath;
                }
                vm3.voForm.parentCollectiontypeId = pid;
                vm3.voForm.ppath = ppath;
                window.show();
            },
            btn_updateType: function (model) {
                vm3.title = "编辑节点";
                vm3.voForm = model;
                vm3.url = "updateType";
                vm3.typeVosForm = this.typeVos;
                window.show();
            },
            btn_deleteType: function (model) {
                var _this = this;
                var deleteLayer = layer.confirm(Info.W0002, {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    var url = "deleteType";
                    var option = {emulateJSON: true};
                    var id = model.collectiontypeId;
                    var pid = model.parentCollectiontypeId;
                    $.ajax({
                        url: url,
                        data: {
                            "collectiontypeId": id,
                            "parentCollectiontypeId": pid
                        },
                        dateType: "json",
                        type: "post",
                        traditional: true,
                        success: function (res) {
                            if ("ok" == res.msg) {
                                layer.msg(Info.I0014, {icon: 1});
                                _this.getTypes();
                                window.vm.btn_search();
                            }
                        }
                    })
                });

            },
            btn_checkType: function (model) {
                vm.voSearch.collectiontypeId = model.collectiontypeId;
                vm.btn_search();
            }
        }
    });
    //右侧列表
    var vm = new Vue({
        el: '#collections',
        data: {
            voSearch: {},           //搜索对象
            vos: [],
            isok: false,
            checkedId: [],
            searchOpt: 1,            //选择的搜索条件
            opts: [],               //搜索条件集合
            pageUrl:"../detailspage/toDetailsPage?from=front&article_id=",//详情页地址
            info: Info.I0024
        },
        created: function () {
            this.getOpts();
        },
        components: {
            'pager': ic_pager
        },

        methods: {
            getOpts:
                function () {
                    var url = "getOpts";
                    var _this = this;
                    axios.get(url, {})
                        .then(function (response) {
                            _this.opts = response.data;
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                },
            checkedAll:
                function () {
                    if (!this.isok) { //全选中 所有CheckBox
                        var vos = [];
                        var vos = this.vos;
                        this.checkedId = [];
                        vos.forEach(function (vo) {
                            this.checkedId.push(vo.id);
                        }, this);
                    } else {   //反选清楚所有CheckBox选中
                        this.checkedId = []
                    }
                },
            btn_search:
                function () {
                    this.$refs.pagecomponent.pageBean.pageNow = 1;
                    this.search(this.$refs.pagecomponent.pageBean);

                },


            btn_move:                         //移动弹出
                function () {
                    var checkedId = [];
                    this.checkedId.forEach(function (id) {
                        checkedId.push(id);
                    }, this);
                    if (checkedId.length == 0) {
                        layer.msg(Info.E0039);
                    } else {
                        vm3.title = "移动文章"
                        vm3.url = "move"
                        vm3.voForm.parentCollectiontypeId = ""
                        vm3.typeVosForm = vm2.typeVos;
                        window.show();
                    }
                },

            btn_delete:                     //批量删除
                function () {
                    var _this = this;
                    if (this.checkedId.length == 0) {
                        layer.msg(Info.E0019);
                    } else {
                        var deleteLayer = layer.confirm(Info.W0002, {
                            btn: ['确定', '取消'] //按钮
                        }, function () {
                            var url = "delete";
                            var option = {emulateJSON: true};
                            var checkedId = [];
                            _this.checkedId.forEach(function (id) {
                                checkedId.push(id);
                            }, this);
                            $.ajax({
                                url: url,
                                data: {"checkedId": checkedId},
                                dateType: "json",
                                type: "post",
                                traditional: true,
                                success: function (res) {
                                    if ("ok" == res.msg) {
                                        layer.msg(Info.I0014, {icon: 1});
                                        _this.btn_search()
                                    }
                                }
                            })
                        });
                    }
                },

            deleteOne:                              //点击图标删除单个
                function (id) {
                    var _this = this;
                    var deleteLayer = layer.confirm(Info.W0002, {
                        btn: ['确定', '取消'] //按钮
                    }, function () {
                        var url = "deleteOne";
                        var option = {emulateJSON: true};

                        $.ajax({
                            url: url,
                            data: {"id": id},
                            dateType: "json",
                            type: "post",
                            traditional: true,
                            success: function (res) {
                                if ("ok" == res.msg) {
                                    layer.msg(Info.I0014, {icon: 1});
                                    _this.btn_search()
                                }
                            }
                        })
                    });

                }
        },
        watch: {
            "checkedId": function () {
                if (this.checkedId.length != this.vos.length) {
                    this.isok = false
                } else {
                    this.isok = true
                }
            }
        },

        computed: {
            search: function () {
                return function (pageBean) {
                    var l_index = layer.msg(Info.I0011, {
                        icon: 16
                        , shade: 0.01
                    });
                    var _this = this;
                    var url = "../collection/queryAll";
                    var vo = this.voSearch;
                    axios.get(url,  {
                        params: {
                            articleTitle: vo.articleTitle,           //文章标题
                            id: this.searchOpt,            //检索选项
                            collectiontypeId: vo.collectiontypeId,   //收藏节点ID
                            pageNow: pageBean.pageNow,
                            rowSize: pageBean.rs_selected
                        }
                    })
                        .then(function (response) {
                            var rowCount = response.data.rowCount + "";
                            var allVos = response.data.vos;
                            _this.$refs.pagecomponent.dealAfterSearch(rowCount); //rowCount为总条目数  在ajax请求返回函数需调用该方法
                            _this.vos = allVos;
                            layer.close(l_index);
                            if (rowCount == '0') {
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
    //树状菜单的节点
    var ic_tree_collect = {            //父节点
        template: '                 <div><div :id="model.collectiontypeId+\'_top\'" class="dragArea" ondragenter="dropEnter(event)"ondragleave="dropLeave(event)" ondragover="allowDrop(event)" ondrop="drop(event)" style="padding:2% 50%"></div>'+
        '<div >\n' +
        '                        <li class="cy_CIASFE_colletreefold"><img v-if="model.isParent==1"  :src="model.imgsrc" v-on:click="opClose(model)"><!--三角--><img src="${ctx}/image/colle-folder.png"><!--文件夹图标-->\n' +
        '                            <div v-on:click="btn_checkType(model)" :id="model.collectiontypeId" draggable="true" ondragstart="drag(event)" ondrop="drop(event)"\n' +
        '                             ondragenter="dropEnter(event)" ondragleave="dropLeave(event)" ondragover="allowDrop(event)">{{model.collectiontypename}}</div> <div class="cy_CIASFE_treefoldered">\n' +
        '                                <img src="${ctx}/image/colle-foldernw.png" v-on:click="btn_addType(model)">\n' +
        '                                <img src="${ctx}/image/colle-foldered.png" v-on:click="btn_updateType(model)">\n' +
        '                                <img src="${ctx}/image/colle-folderdl.png" v-on:click="btn_deleteType(model)"></div>\n' +
        '                            <ul v-show="model.is_show"><ic_tree_collect :model="model" v-for="model in model.children"></ic_tree_collect></ul>\n' +
        '                        </li>\n' +
        '                    </div></div>',
        props: ['model'],
        methods: {
            opClose: function (model) {
                this.$parent.opClose(model);
            },
            btn_addType: function (model) {
                this.$parent.btn_addType(model);
            },
            btn_updateType: function (model) {
                this.$parent.btn_updateType(model);
            },
            btn_deleteType: function (model) {
                this.$parent.btn_deleteType(model);
            },
            btn_checkType: function (model) {
                this.$parent.btn_checkType(model);
            }
        }
    }
    //鼠标拖动进行的方法
    function drag(ev) {
        ev.dataTransfer.setData("Text", ev.target.id);  //当开始拖动时，获取id
    }

    function allowDrop(ev) {        //阻止默认事件
        ev.preventDefault()
    }

    function dropEnter(ev) {        //拖动进入目标区域
        document.getElementById(ev.target.id).style.backgroundColor = '#2ea1f8'
    }

    function dropLeave(ev) {        //拖动离开目标区域
        document.getElementById(ev.target.id).style.backgroundColor = '#ffffff'
    }

    function drop(ev) {         //放下
        document.getElementById(ev.target.id).style.backgroundColor = '#ffffff'
        var data = ev.dataTransfer.getData("Text");
        vm2.moveType(data,ev.target.id);

    }
    //表单树状组件
    var ic_tree_collect_form = {
        template: '         <div >\n' +
        '                        <li class="cy_CIASFE_colletreefold"><a><img v-if="model.isParent==1"  :src="model.imgsrc" v-on:click="opClose(model)"><!--三角--><img src="${ctx}/image/colle-folder.png"><!--文件夹图标-->\n' +
        '                            <div v-on:click="clickType(model)">{{model.collectiontypename}}</div> <div class="cy_CIASFE_treefoldered">\n' +
        '                            <ul v-show="model.is_show"><ic_tree_collect_form :model="model" v-for="model in model.children"></ic_tree_collect_form></ul>\n' +
        '                        </a></li>\n' +
        '                    </div>',
        props: ['model'],
        methods: {
            opClose: function (model) {
                this.$parent.opClose(model);
            },
            clickType: function (model) {
                var pid = 0;
                if (model != null) {
                    pid = model.collectiontypeId;
                }
                vm3.voForm.parentCollectiontypeId = pid;
            },
            btn_checkType: function (model) {

            }
        }
    }


    /* ${ctx}/image/colle-arrow01.png 闭合*/

    Vue.component('ic_tree_collect', ic_tree_collect);  //全局注册树状组件
    Vue.component('ic_tree_collect_form', ic_tree_collect_form);  //全局注册树状组件

    //弹出层控件：收藏节点编辑新增、收藏文章移动
    var vm3 = new Vue({
        el: '#cy_CMICBMS_newfd',
        data: {
            typeVosForm: [],        //弹出层树状图对象，在编辑、移动时出现
            title: "",              //弹出层标题
            url: "",                //弹出层指向url
            voForm: {collectiontypename: "", collectiontypeId: "", parentCollectiontypeId: ""}
        },
        methods: {
            checkTop:
                function () {
                    this.voForm.parentCollectiontypeId = 0;
                },
            opClose:
                function (model) {
                    vm2.opClose(model);
                },
            checkName:
            	function(name,vos){
            	var _this = this;
                	vos.forEach(function (vo) {
                        if(name.replace(/\s*/g,"")==vo.collectiontypename.replace(/\s*/g,"")){
                        	return "1";
                        }else{
                        	_this.checkName(name,vo.children)
                        }
                    }, this);
                },
            submit:
                function () {
                    var _this = this;
                    if (this.title == "移动文章") {
                        var checkedId = vm.checkedId;
                        var collectiontypeId = _this.voForm.parentCollectiontypeId;
                        if (collectiontypeId == "") {
                            layer.msg(Info.E0040);
                            return false;
                        }
                        $.ajax({
                            url: _this.url,
                            data: {"checkedId": checkedId, "collectiontypeId": collectiontypeId},
                            dateType: "json",
                            type: "post",
                            traditional: true,
                            success: function (res) {
                                if ("ok" == res.msg) {
                                    layer.msg(Info.I0025, {icon: 1});
                                    vm.btn_search()
                                    window.hide();
                                } else if (res.msg == "exist") {
                                    layer.msg(Info.E0041);
                                }
                            }
                        })
                    } else {
                        var voForm = this.voForm;
                        if (voForm.collectiontypename == "") {
                            var attrs=['收藏夹名称'];
                            layer.msg(IC_GETINFOBYAttrs(Info.E0001,attrs));
                            return false;
                        }else if(this.checkName(voForm.collectiontypename,vm2.typeVos)=='1'){//收藏夹名不能相同
                        	layer.msg(Info.E0061)
                        	return false;
                        }
                        axios.get(_this.url, {
                            params: {
                                parentCollectiontypeId: voForm.parentCollectiontypeId,
                                collectiontypename: voForm.collectiontypename,
                                collectiontypeId: voForm.collectiontypeId,
                                displayorder: voForm.displayorder,
                                ppath:voForm.ppath,
                                updatedatetime: voForm.updatedatetime
                            }
                        }).then(function (response) {
                            if (response.data.msg == "ok") {
                                window.hide();
                                vm2.getTypes();
                                layer.msg(Info.I0012);
                            } else if (response.data.msg == "fault") {
                                layer.msg(Info.I0013);
                            } else if (response.data.msg == "checkFalse") {
                                layer.msg(Info.W0004);
                            }else if (response.data.msg == "overstep") {//超出最大层数
                            	var attrs=['4'];
                                layer.msg(IC_GETINFOBYAttrs(Info.E0055,attrs));
                            }
                        })
                            .catch(function (error) {
                                console.log(error);
                            });
                    }
                }

        }
    })
</script>
</body>
</html>