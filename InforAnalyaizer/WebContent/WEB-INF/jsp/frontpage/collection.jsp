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
<fmt:message key="E0062" var="E0062" bundle="${sysInfo}"/>
<fmt:message key="W0003" var="W0003" bundle="${sysInfo}"/>
<fmt:message key="W0012" var="W0012" bundle="${sysInfo}"/>
<fmt:message key="W0013" var="W0013" bundle="${sysInfo}"/>
<fmt:message key="E0075" var="E0075" bundle="${sysInfo}"/>
<fmt:message key="UseManualFileName" var="UseManualFileName" bundle="${sysInfo}" />
<fmt:message key="DownloadFileTemplatePath" var="DownloadFileTemplatePath" bundle="${sysInfo}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>竞争情报分析系统</title>
    <link rel="stylesheet" type="text/css"
          href="${ctx}/css/cy_CIAS_style-1920_1080.css">
    <style>
        [v-cloak] {
            display: none;
        }
        
        .artileTitle_a{
        	text-decoration:none;
        	 color: #444;
        }
        
    </style>
</head>

<body style="background-color: #f2f3f8;">
<div class="cy_hidebg" id="cy_hidebg"></div>
<!--头部-->
<div class="cy_CIASFE_top" id="head" v-cloak>
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
                    <input type="button" value="新增收藏夹" v-on:click="btn_addType()">
                </div>
                <ul>
                    <li class="cy_CIASFE_colletree1st"><a v-on:click="clickTop()"><img
                            src="${ctx}/image/colle-arrow02.png"><img
                            src="${ctx}/image/colle-folder.png">我的收藏 </a></li>
                    <ul>
                        <div>
                            <ic_tree_collect v-for="(typeVo,index) in typeVos"
                                             :model="typeVo" :index="index" :len="typeVos.length"></ic_tree_collect>
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
                                                                v-model='isok' v-on:click='checkedAll'
                                                                :disabled="vos.length==0"/></th>
                            <th>收藏夹名称</th>
                            <th>标题名称</th>
                            <th>发布时间</th>
                            <th style="text-align: center;" width="13%">操作</th>
                        </tr>
                        <tr class="cy_CMICBMS_treven" v-for="vo in vos">
                            <td><input type="checkbox" :value="vo.id"
                                       v-model="checkedId"></td>
                            <td>{{vo.allparentname}}</td>
                            <td><a :href="pageUrl+vo.articleId" class="artileTitle_a" target="_blank" >{{vo.articleTitle}}</a></td>
                            <td>{{vo.releasetime}}</td>
                            <td style="text-align: center;"><a
                                    v-on:click="deleteOne(vo.id)"><img
                                    src="${ctx}/image/usermana-dele.png"></a></td>
                        </tr>
                        <tr>
                            <td v-if="vos.length==0" colspan="5">{{info}}</td>
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
<div id="footdv">
	<ic_sycc_template></ic_sycc_template>
</div>

<!--弹出层-->
<div id="cy_CMICBMS_newfd">
    <div class="cy_CMICBMS_newbttop">
        <div class="cy_CMICBMS_newbttit">{{title}}</div>
        <div class="cy_CMICBMS_newtbclose" onClick="hide();">X</div>
    </div>
    <div class="cy_CMICBMS_newfdbd">
        <div class="cy_CMICBMS_newfdtb" style="margin-bottom: 20px;">
            <div class="cy_CMICBMS_newfdip1">
                <span>*</span>名称：
            </div>
            <input type="text" class="cy_CMICBMS_newbtinput_collection"
                   placeholder="请输入名称" v-model="voForm.collectiontypename"
                   maxlength="10">
        </div>
        <div class="cy_CMICBMS_infeddn">
            <input type="button" value="确定" v-on:click="submit">
        </div>
    </div>
</div>
</div>
<!--移动文章弹出层-->
<div id="cy_CMICBMS_newfd1">
    <div class="cy_CMICBMS_newbttop">
        <div class="cy_CMICBMS_newbttit">{{title}}</div>
        <div class="cy_CMICBMS_newtbclose" onClick="hide1();">X</div>
    </div>
    <div class="cy_CMICBMS_newfdbd1">
        <div class="cy_CMICBMS_newfdip">
            请选择目标节点：{{voForm.collectiontypename}} <br>

            <ul class="cy_CMICBMS_collectTreeBorder">
                <li class="cy_CIASFE_colletree1st"><a> <img
                        src="${ctx}/image/colle-arrow02.png"> <img
                        src="${ctx}/image/colle-folder.png"> <span
                        style="color: black;">我的收藏</span>
                </a></li>
                <ul>
                    <div v-for="typeVo in typeVosForm">
                        <ic_tree_collect_form :model="typeVo"></ic_tree_collect_form>
                    </div>
                </ul>
            </ul>
        </div>

    </div>
    <div class="cy_CMICBMS_infeddn"
         id="cy_CMICBMS_addtb_submit_collection">
        <input type="button" value="确定" v-on:click="submit">
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
    var menu_datas = JSON.parse('${front_menu}') //菜单数据来源于 classes/resources.properties
    var menu_url_data = 'collection';
    Vue.component('ic_top_menu', ic_top_menu);   // ic_top_menu 引自 js/ic_component.js
    Vue.component('ic_user_info', ic_user_info);  // ic_user_info 引自 js/ic_component.js
    var hvm = new Vue({
        el: '#head'
    });
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
        E0062: '${E0062}',
        I0012: '${I0012}',
        W0003: '${W0003}',
        W0012: '${W0012}',
        E0075: '${E0075}',
        W0013: '${W0013}',
        syccurl:'${ctx }/${DownloadFileTemplatePath}/${UseManualFileName}'
    };
    
    Vue.component('ic_sycc_template', ic_sycc_template);

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

    function show1()  //显示隐藏层和弹出层
    {
        var hideobj = document.getElementById("cy_hidebg");
        cy_hidebg.style.display = "block";  //显示隐藏层
        document.getElementById("cy_CMICBMS_newfd1").style.display = "block";  //显示弹出层
    }

    function hide1()  //去除隐藏层和弹出层
    {
        document.getElementById("cy_hidebg").style.display = "none";
        document.getElementById("cy_CMICBMS_newfd1").style.display = "none";
    }

    
    new Vue({
    	el:'#footdv'
    });

    var vm2 = new Vue({         //控制左侧树状菜单的vue对象
        el: '#collectionType',
        data: {
            typeVos: []			//收藏夹集合
        },
        mounted: function () {
            this.getTypes();	//加载之前执行请求方法

        },

        methods: {
        	clickTop: function(){
        		changeColor();
        		app.voSearch.collectiontypeId = "";
        		app.checkedId = [];
            	app.btn_search();
        	},
            moveType: function (id, targetId) {
                var _this = this;
                axios.get('moveType', {
                    params: {
                        collectiontypeId: id,
                        targetId: targetId
                    }
                })
                    .then(function (res) {
                        if (res.data.msg == "ok") {
                            _this.getTypes();
                        } else if (res.data.msg == "overstep") {
                            var attrs = ['4'];
                            layer.msg(IC_GETINFOBYAttrs(Info.E0055, attrs));//超出最大层数
                        } else if (res.data.msg == "fault") {

                            layer.msg(Info.E0062);//不可向子节点移动
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
                        _this.digui(_this.typeVos, 0, "");
                        app.btn_search();
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
                    type.collectionpath = path + type.collectiontypeId;
                    type.depth = depth;
                    if (type.children != []) {
                        depth++;
                        _this.digui(type.children, depth, type.collectionpath);
                    }
                })
            },

            btn_addType: function (model) {		//新建节点弹出
                var pid = "C000000000";
                var ppath = "";
                vm3.title = "新增节点";
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
                var data = {
                    "childrenNum": model.childrenNum,
                    "collectionpath": model.collectionpath,
                    "collectionstratum": model.collectionstratum,
                    "collectiontypeId": model.collectiontypeId,
                    "collectiontypename": model.collectiontypename,
                    "displayorder": model.displayorder,
                    "isParent": model.isParent,
                    "is_show": model.is_show,
                    "parentCollectiontypeId": model.parentCollectiontypeId,
                    "ppath": model.ppath,
                    "userId": model.userId,
                    "updatedatetime": model.updatedatetime,
                    "validflag": model.validflag
                };
                vm3.voForm = data;
                vm3.url = "updateType";
                window.show();
            },
            btn_deleteType: function (model) {
                var _this = this;
                var deleteLayer = layer.confirm(Info.W0013, {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    var url = "deleteType";
                    var option = {emulateJSON: true};
                    var id = model.collectiontypeId;
                    var pid = model.parentCollectiontypeId;
                    var displayorder = model.displayorder;
                    $.ajax({
                        url: url,
                        data: {
                            "collectiontypeId": id,
                            "parentCollectiontypeId": pid,
                            "displayorder": displayorder
                        },
                        dateType: "json",
                        type: "post",
                        traditional: true,
                        success: function (res) {
                            if ("ok" == res.msg) {
                                _this.getTypes();
                                window.app.voSearch.collectiontypeId="";
                                window.app.btn_search();
                                changeColor();
                                layer.msg(Info.I0014, {icon: 1});
                                
                            }
                        }
                    })
                });

            },
            btn_checkType: function (model) {
            	if(model!=null){
                	app.voSearch.collectiontypeId = model.collectiontypeId;
               		app.checkedId = [];
                	app.btn_search();
            	}else{
            		app.voSearch.collectiontypeId = "";
            		app.checkedId = [];
                	app.btn_search();
            	}
            }
        }
    });
    //右侧列表
    var app = new Vue({
        el: '#collections',
        data: {
            voSearch: {},           //搜索对象
            vos: [],
            isok: false,
            checkedId: [],
            searchOpt: 'M001',            //选择的搜索条件
            opts: [],               //搜索条件集合
            pageUrl: "../detailspage/toDetailsPage?from=front&article_id=",//详情页地址
            info: Info.I0024
        },
        created: function () {
            this.getOpts();
        },
        components: {
            'pager': ic_pager
        },

        methods: {
            from_child_search: function () {
                this.search(this.$refs.pagecomponent.pageBean);
            },
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
                        vm4.title = "移动文章"
                        vm4.url = "move"
                        vm4.voForm.parentCollectiontypeId = "";
                        vm4.voForm.collectiontypename = "";
                        vm4.typeVosForm = vm2.typeVos;
                        window.show1();
                    }
                },

            btn_delete:                     //批量删除
                function () {
                    var _this = this;
                    if (this.checkedId.length == 0) {
                        layer.msg(Info.E0019);
                    } else {
                        var deleteLayer = layer.confirm(Info.W0012, {
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
                                        _this.btn_search()
                                        _this.checkedId = [];
                                        layer.msg(Info.I0014, {icon: 1});
                                    }
                                }
                            })
                        });
                    }
                },

            deleteOne:                              //点击图标删除单个
                function (id) {
                    var _this = this;
                    var deleteLayer = layer.confirm(Info.W0012, {
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
                                    _this.checkedId = [];
                                }
                            }
                        })
                    });

                }
        },
        watch: {
            "checkedId": function () {
                if (this.checkedId.length == this.vos.length && this.checkedId.length != 0) {
                    this.isok = true
                } else {
                    this.isok = false
                }
            }
        },

        computed: {
            search: function () {
                return function (pageBean) {
                    var l_index = layer.msg(Info.I0011, {
                        icon: 16
                        , shade: 0.01,
                        time:false
                    });
                    var _this = this;
                    var url = "../collection/queryAll";
                    var vo = this.voSearch;
                    axios.get(url, {
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
                            if (rowCount == '0' && vo.articleTitle != null && vo.articleTitle != "") {
                                layer.msg(Info.I0002);
                            }
                        })
                        .catch(function (error) {
                        	layer.close(l_index);
                            console.log(error);
                        });
                }
            }
        }
    });
    //树状菜单的节点
    var ic_tree_collect = {            //父节点
        template: '                 <div><div :id="model.collectiontypeId+\'_top\'"  ondragenter="dropEnter(event)"ondragleave="dropLeave(event)" ondragover="allowDrop(event)" ondrop="drop(event)" style="padding:2% 50%"></div>' +
        '                        <li class="cy_CIASFE_colletreefold">' +
        '                            <div class=\'cy_CIASFE_collectionTypeName\'  v-on:click="btn_checkType(model)" name="cname"  :id="model.collectiontypeId" draggable="true" ondragstart="drag(event)" ondrop="drop(event)" ' +
        '                             ondragenter="dropEnter(event)" ondragleave="dropLeave(event)" ondragover="allowDrop(event)">' +
        '<img :id="model.collectiontypeId+\'|img1\'" v-if="model.isParent==1"  :src="model.imgsrc" v-on:click="opClose(model)" draggable="false"><!--三角--><img :id="model.collectiontypeId+\'|img2\'" src="${ctx}/image/colle-folder.png" ><!--文件夹图标-->' +
        '{{model.collectiontypename}} <span class="cy_CIASFE_treefoldered" :id="model.collectiontypeId+\'|img\'">' +
        '                                <img :id="model.collectiontypeId+\'|img3\'" src="${ctx}/image/colle-foldernw.png"  v-on:click.stop="btn_addType(model)">' +
        '                                <img :id="model.collectiontypeId+\'|img4\'" src="${ctx}/image/colle-foldered.png"  v-on:click.stop="btn_updateType(model)">' +
        '                                <img :id="model.collectiontypeId+\'|img5\'" src="${ctx}/image/colle-folderdl.png"  v-on:click.stop="btn_deleteType(model)"></span></div>' +
        '<div :id="model.collectiontypeId+\'_bottom\'" v-if="index==len-1" ondragenter="dropEnter(event)"ondragleave="dropLeave(event)" ondragover="allowDrop(event)" ondrop="drop(event)" style="padding:2% 50%"></div>' +
        '                            <ul v-show="model.is_show"><ic_tree_collect  v-for="(typeVo,index) in model.children"  :model="typeVo" :index="index" :len="model.children.length"></ic_tree_collect></ul>' +
        '                        </li>' +
        '                    </div>',
        props: ['model', 'len', 'index'],
        //onmouseover="this.style.cssText=\'color: white;background-color: #2ea1f8;\'"  onmouseout="this.style.cssText=\'color: black;background-color: #ffffff;\'" 
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
            btn_checkType: function (model,ev) {
                 var el = event.currentTarget;
                 changeColor();
                 el.style.backgroundColor= '#2ea1f8';
            	 this.$parent.btn_checkType(model);
            },

        }
    }
	//点击改变背景颜色的方法
    function changeColor() {
        var doms = document.getElementsByName("cname");
        doms.forEach(function (dom) {
            dom.style.backgroundColor= '#ffffff'
        })
    }
    //鼠标拖动进行的方法
    function drag(ev) {
        var ids = ev.target.id.split("|");
        ev.dataTransfer.setData("Text", ids[0]);  //当开始拖动时，获取id
    }

    function allowDrop(ev) {        //阻止默认事件
        ev.preventDefault()

    }

    function dropEnter(ev) {        //拖动进入目标区域
        document.getElementById(ev.target.id.split("|")[0]).style.backgroundColor = '#2ea1f8'

    }

    function dropLeave(ev) {        //拖动离开目标区域

        document.getElementById(ev.target.id.split("|")[0]).style.backgroundColor = '#ffffff'

    }

    function drop(ev) {         //放下
        var data = ev.dataTransfer.getData("Text");
        document.getElementById(ev.target.id.split("|")[0]).style.backgroundColor = '#ffffff'
        if (ev.target.id.substring(0, 10) == data) {
            return false;
        }
        vm2.moveType(data, ev.target.id.split("|")[0]);

    }

    //表单树状组件
    var ic_tree_collect_form = {
        template: '         <div style="margin-left:10px;">\n' +
        '                        <li class="cy_CIASFE_colletreefold"><img v-if="model.isParent==1"  :src="model.imgsrc" v-on:click="opClose(model)"><!--三角--><img src="${ctx}/image/colle-folder.png"><!--文件夹图标-->\n' +
        '                            <a style="cursor:pointer;"><span v-on:click="clickType(model)" style="color:black;" onmouseover="this.style.cssText=\'color: white;background-color: #2ea1f8;\'"  onmouseout="this.style.cssText=\'color: black;background-color: #ffffff;\'">{{model.collectiontypename}}</span> <span class="cy_CIASFE_treefoldered">\n' +
        '                            </a><ul v-show="model.is_show"><ic_tree_collect_form :model="model" v-for="model in model.children"></ic_tree_collect_form></ul></div>' +
        '                        </li>\n' +
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
                    collectiontypename = model.collectiontypename;
                }
                vm4.voForm.parentCollectiontypeId = pid;
                vm4.voForm.collectiontypename = collectiontypename;
            }
        }
    }


    /* ${ctx}/image/colle-arrow01.png 闭合*/

    Vue.component('ic_tree_collect', ic_tree_collect);  //全局注册树状组件
    Vue.component('ic_tree_collect_form', ic_tree_collect_form);  //全局注册树状组件

    //弹出层控件：收藏节点编辑新增
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
                function (name, vos, flag) {
                    var _this = this;
                    vos.forEach(function (vo) {
                        if (name.replace(/\s*/g, "") == vo.collectiontypename.replace(/\s*/g, "")) {
                            flag = false;
                          return false;
                        } else {
                            flag=_this.checkName(name, vo.children, flag)
                        }
                    }, this);
                    return flag;
                },
            submit:
                function () {
                    var _this = this;
                    var flag = true;
                    var voForm = this.voForm;
                    if (voForm.collectiontypename == ""||voForm.collectiontypename==null) {
                        var attrs = ['收藏夹名称'];
                        layer.msg(IC_GETINFOBYAttrs(Info.E0001, attrs));
                        return false;
                    }
                    flag = this.checkName(voForm.collectiontypename, vm2.typeVos, flag);
                    if (this.title=="新增节点"&&!flag) {//收藏夹名不能相同
                        layer.msg(Info.E0061)
                        return false;
                    }
                    axios.get(_this.url, {
                        params: {
                            parentCollectiontypeId: voForm.parentCollectiontypeId,
                            collectiontypename: voForm.collectiontypename,
                            collectiontypeId: voForm.collectiontypeId,
                            displayorder: voForm.displayorder,
                            ppath: voForm.ppath,
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
                        } else if (response.data.msg == "overstep") {//超出最大层数
                            var attrs = ['4'];
                            layer.msg(IC_GETINFOBYAttrs(Info.E0055, attrs));
                        }
                    })
                        .catch(function (error) {
                            console.log(error);
                        });
                }


        }
    })
    //移动文章弹出层
    var vm4 = new Vue({
        el: '#cy_CMICBMS_newfd1',
        data: {
            typeVosForm: [],        //弹出层树状图对象，在编辑、移动时出现
            title: "",              //弹出层标题
            url: "",                //弹出层指向url
            voForm: {collectiontypename: "", collectiontypeId: "", parentCollectiontypeId: ""}
        },
        methods: {
            opClose:
                function (model) {
                    vm2.opClose(model);
                },
            submit:
                function () {
                    var _this = this;
                    if (this.title == "移动文章") {
                        var checkedId = app.checkedId;
                        var collectiontypeId = _this.voForm.parentCollectiontypeId;
                        var name = _this.voForm.collectiontypename;
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
                                    app.btn_search()
                                    layer.msg(Info.I0025);
                                    window.hide1();
                                    app.checkedId = [];
                                } else if (res.msg == "exist") {
                                    layer.msg(Info.E0041);
                                }
                            }
                        })
                    }
                }
        }
    })
</script>
</body>
</html>