<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html xmlns:v-on="http://www.w3.org/1999/xhtml"
      xmlns:v-bind="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css"
          href="${ctx}/css/cy_CIAS_style.css">

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
            情报规划 / <span>过滤词管理</span>
        </p>
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
            <div class="cy_CMICBMS_box08">
                <input type="button" class="cy_CMICBMS_edbtn" value="编辑"
                       v-on:click="btn_update"> <input type="button"
                                                       class="cy_CMICBMS_impbtn" value="导入" v-on:click=""> <input
                    type="button" class="cy_CMICBMS_expbtn" value="导出" v-on:click="">
            </div>
            <div class="cy_CMICBMS_box08">
                <table width="100%" border="0" class="cy_CMICBMS_accmngtb"
                       cellspacing="0">
                    <tbody>
                    <tr>
                        <th scope="col" width="25px"><input type="checkbox"
                                                            v-model='isok' v-on:click='checkedAll'/></th>
                        <th scope="col" width="15%">分类名称</th>
                        <th scope="col" width="15%">信息向性区分</th>
                        <th scope="col" width="35%">敏感词过滤范围</th>
                        <th scope="col" width="auto">最近编辑时间</th>
                        <th scope="col" width="auto">最近编辑者</th>
                    </tr>
                    <tr class="cy_CMICBMS_treven" v-for="fwVo in fwVos">
                        <td>
                            <ic_tree_dv_checkbox :model="fwVo"><input type="checkbox" :value="fwVo.classificationId" v-model="checkedId">
                            </ic_tree_dv_checkbox>
                        </td>
                        <td>
                            <ic_tree_dv_name :model="fwVo">{{fwVo.classificationName}}</ic_tree_dv_name>
                        </td>
                        <td>
                            <ic_tree_dv_tropism :model="fwVo">{{fwVo.informationtropism==1?'区分':'不区分'}}</ic_tree_dv_tropism>
                        </td>
                        <td>
                            <ic_tree_dv_all :model="fwVo">{{fwVo.allcorephrases!=null||fwVo.allexcludephrases!=null?'全部；':''}}
                                {{fwVo.titlecorephrases!=null||fwVo.titleexcludephrases!=null?'标题；':''}}
                                {{fwVo.summarycorephrases!=null||fwVo.summaryexcludephrases!=null?'摘要；':''}}
                                {{fwVo.textcorephrases!=null||fwVo.textexcludephrases!=null?'正文；':''}}
                            </ic_tree_dv_all>
                        </td>
                        <td>
                            <ic_tree_dv_time :model="fwVo">{{fwVo.updatedatetime}}</ic_tree_dv_time>
                        </td>
                        <td>
                            <ic_tree_dv_user :model="fwVo">{{fwVo.updateuser}}</ic_tree_dv_user>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <pager ref="pagecomponent"></pager>
    </div>
</div>

<div id="updateform">
    <div id="cy_CMICBMS_add">
        <div class="cy_CMICBMS_addtop">
            <div class="cy_CMICBMS_addtit">{{title}}</div>
            <div class="cy_CMICBMS_addclose" onClick="hide();">X</div>
        </div>
        <div class="cy_CMICBMS_addtb">
            <div id="cy_CMICBMS_status">
                信息项区分：<input type="radio" name="RadioGroup1" value="1"
                             id="RadioGroup1_0" v-model="fwVoForUpdate.informationtropism">区分
                <input type="radio" name="RadioGroup1" value="0" id="RadioGroup1_1"
                       v-model="fwVoForUpdate.informationtropism">不区分
            </div>
            <div>
                全部核心词组：<input type="text" placeholder="输入词组"
                              class="cy_CMICBMS_addinput" v-model="fwVoForUpdate.allcorephrases">
            </div>
            <div>
                全部排除词组：<input type="text" placeholder="输入词组"
                              class="cy_CMICBMS_addinput"
                              v-model="fwVoForUpdate.allexcludephrases">
            </div>
            <div>
                标题核心词组：<input type="text" placeholder="输入词组"
                              class="cy_CMICBMS_addinput"
                              v-model="fwVoForUpdate.titlecorephrases">
            </div>
            <div>
                标题排除词组：<input type="text" placeholder="输入词组"
                              class="cy_CMICBMS_addinput"
                              v-model="fwVoForUpdate.titleexcludephrases">
            </div>
            <div>
                摘要核心词组：<input type="text" placeholder="输入词组"
                              class="cy_CMICBMS_addinput"
                              v-model="fwVoForUpdate.summarycorephrases">
            </div>
            <div>
                摘要排除词组：<input type="text" placeholder="输入词组"
                              class="cy_CMICBMS_addinput"
                              v-model="fwVoForUpdate.summaryexcludephrases">
            </div>
            <div>
                正文核心词组：<input type="text" placeholder="输入词组"
                              class="cy_CMICBMS_addinput"
                              v-model="fwVoForUpdate.textcorephrases">
            </div>
            <div>
                正文排除词组：<input type="text" placeholder="输入词组"
                              class="cy_CMICBMS_addinput"
                              v-model="fwVoForUpdate.textexcludephrases">
            </div>

            <div style="margin: 40px 20px 0 0;">
                <input type="button" value="确定" class="cy_CMICBMS_schbtn"
                       v-on:click="submit">
            </div>
        </div>

    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="${ctx}/js/vue.min.js"></script>
<script src="${ctx}/js/vue-resource.min.js"></script>
<script src="${ctx}/js/axios.min.js"></script>
<script type="text/javascript" src="${ctx}/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/js/ic_components.js"></script>

<script>

    var vm = new Vue({
        el: '#filterWords',
        data: {
            fwVoForSearch: {},
            fwVos: [],
            isok: false,
            checkedId: []
        },

        components: {
            'pager': ic_pager
        },
        methods: {
            checkedAll:
                function () {
                    if (!this.isok) { //全选中 所有CheckBox
                        var fwVos = [];
                        var fwVos = this.fwVos;
                        this.checkedId = [];
                        fwVos.forEach(function (fwVo) {
                            this.checkedId.push(fwVo.classificationId);
                        }, this);
                    } else {   //反选清楚所有CheckBox选中
                        this.checkedId = []
                    }
                },
            btn_search:
                function () {
                    this.search(this.$refs.pagecomponent.pageBean);
                },
            btn_update:
                function () {
                    var checkedId = [];
                    checkedId = this.checkedId;
                    if (checkedId.length != 1) {
                        layer.msg('请选择单条信息');
                    } else {
                        vm2.title = "编辑分类过滤词";
                        vm2.url = "updateFwVo";
                        axios.get("queryOne", {
                            params: {
                                classificationId: checkedId[0]
                            }
                        }).then(function (res) {
                            vm2.fwVoForUpdate = res.data;

                        })
                            .catch(function (error) {
                                console.log(error);
                            });
                        var hideobj = document.getElementById("cy_hidebg");
                        cy_hidebg.style.display = "block";  //显示隐藏层
                        document.getElementById("cy_CMICBMS_add").style.display = "block";  //显示弹出层
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
                    var _this = this;
                    var fwVo = this.fwVoForSearch;
                    var url = "../filterWords/queryAll";
                    axios.get(url, {
                        params: {
                            classificationName: fwVo.classificationName,
                            pageNow: pageBean.pageNow,
                            rowSize: pageBean.rs_selected
                        }
                    })
                        .then(function (response) {
                            var rowCount = response.data.rowCount + "";
                            var allfwVos = response.data.fwVos;
                            _this.$refs.pagecomponent.dealAfterSearch(rowCount); //rowCount为总条目数  在ajax请求返回函数需调用该方法
                            _this.fwVos = allfwVos;
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
    var vm2 = new Vue({
        el: '#updateform',
        data: {
            fwVoForUpdate: {},
            title: "",
            url: ""
        },
        methods: {
            submit:
                function () {
                    var fwVo = this.fwVoForUpdate;
                    var _this = this;
                    axios.get(_this.url, {
                        params: {
                            classificationId: fwVo.classificationId,
                            informationtropism: fwVo.informationtropism,
                            allcorephrases: fwVo.allcorephrases,
                            allexcludephrases: fwVo.allexcludephrases,
                            titlecorephrases: fwVo.titlecorephrases,
                            titleexcludephrases: fwVo.titleexcludephrases,
                            summarycorephrases: fwVo.summarycorephrases,
                            summaryexcludephrases: fwVo.summaryexcludephrases,
                            textcorephrases: fwVo.textcorephrases,
                            textexcludephrases: fwVo.textexcludephrases
                        }
                    }).then(function (response) {
                        if (response.data.msg == "ok") {
                            window.hide();
                            vm.btn_search();
                            layer.msg('编辑成功');
                        } else if (response.data.msg == "fault") {
                            layer.msg('编辑失败');
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

    var ic_tree_dv_checkbox = {
        template: '<div>' +

        '<input type="checkbox"  v-bind:value="model.classificationId"  v-model="checkedId" class="cy_CMICBMS_checkbox"><label for="cy_CMICBMS_checkbox"></label></div>' +
        '<div v-show="is_show" v-bind:style="t_style"><ic_tree_dv_checkbox  v-for="model in model.children"  v-bind:model="model"></ic_tree_dv_checkbox></div>' ,
        props: ['model'],
        data: function () {
            return {
                t_style: {padding: '10px 0px !important'},
                is_show:false
            }
        },
    };


    var ic_tree_dv_name = {
        template: '<div >' +
        '<img v-if="model.isParent==1" v-bind:src="imgsrc" v-on:click="opClose(model.classificationId)" style="cursor:pointer">' +
        '{{model.classificationName}}</div>' +
        '<div v-show="is_show" v-bind:style="t_style"><ic_tree_dv_name  v-for="model in model.children" v-bind:model="model"></ic_tree_dv_name></div>'
        ,
        props: ['model'],
        data: function () {
            return {
                t_style: {padding: '10px ' + (26 + 20 * this.model.depth) + 'px !important'},
                imgsrc: is_show?"../image/is-tbarr02.png":"../image/is-tbarr01.png",
                is_show:false
            }
        },
        methods: {
                    opClose: function (id) {
                        if(this.model.isParent==1) {
                            this.is_show = !this.is_show;
                            ic_tree_dv_checkbox.is_show=!ic_tree_dv_checkbox.is_show;
                            ic_tree_dv_tropism.is_show=!ic_tree_dv_tropism.is_show;
                            ic_tree_dv_all.is_show=!ic_tree_dv_all.is_show;
                            ic_tree_dv_time.is_show=!ic_tree_dv_time.is_show;
                            ic_tree_dv_user.is_show=!ic_tree_dv_user.is_show;
                        }
                        axios.get("queryChild",{
                            params: {
                                parent_Classification_ID:id
                            }
                        }).then(function (res) {
                            this.model.children=res.data;
                            ic_tree_dv_checkbox.model=res.data;
                            ic_tree_dv_tropism.model=res.data;
                            ic_tree_dv_all.model=res.data;
                            ic_tree_dv_time.model=res.data;
                            ic_tree_dv_user.model=res.data;

                        }).catch(function (error) {
                            console.log(error);
                        });
            }
        }
    };
    var ic_tree_dv_tropism = {
        template: '<div >' +

        '{{model.informationtropism==1?\'区分\':\'不区分\'}}</div>' +
        '<div v-show="is_show" v-bind:style="t_style"><span> </span><span> </span><ic_tree_dv_tropism  v-for="model in model.children" v-bind:model="model"></ic_tree_dv_tropism></div>'
        ,
        props: ['model'],
        data: function () {
            return {
                t_style: {padding: '10px ' + (26 + 20 * this.model.depth) + 'px !important'},
                is_show: false
            }
        }
    }
    var ic_tree_dv_all = {
        template: '<div >' +

        '{{model.allcorephrases!=null||model.allexcludephrases!=null?\'全部；\':\'\'}}\n' +
        '                                {{model.titlecorephrases!=null||model.titleexcludephrases!=null?\'标题；\':\'\'}}\n' +
        '                                {{model.summarycorephrases!=null||model.summaryexcludephrases!=null?\'摘要；\':\'\'}}\n' +
        '                                {{model.textcorephrases!=null||model.textexcludephrases!=null?\'正文；\':\'\'}}</div>' +
        '<div v-show="is_show" v-bind:style="t_style"><span> </span><span> </span><ic_tree_dv_all  v-for="model in model.children" v-bind:model="model"></ic_tree_dv_all></div>'
        ,
        props: ['model'],
        data: function () {
            return {
                t_style: {padding: '10px ' + (26 + 20 * this.model.depth) + 'px !important'},
                is_show: false
            }
        }
    }
    var ic_tree_dv_time = {
        template: '<div >' +

        '{{model.updatedatetime}}</div>' +
        '<div v-show="is_show" v-bind:style="t_style"><span> </span><span> </span><ic_tree_dv_time  v-for="model in model.children" v-bind:model="model"></ic_tree_dv_time></div>'
        ,
        props: ['model'],
        data: function () {
            return {
                t_style: {padding: '10px ' + (26 + 20 * this.model.depth) + 'px !important'},
                is_show: false
            }
        }
    }
    var ic_tree_dv_user = {
        template: '<div >' +

        '{{model.updateuser}}</div>' +
        '<div v-show="is_show" v-bind:style="t_style"><span> </span><span> </span><ic_tree_dv_user  v-for="model in model.children" v-bind:model="model"></ic_tree_dv_user></div>'
        ,
        props: ['model'],
        data: function () {
            return {
                t_style: {padding: '10px ' + (26 + 20 * this.model.depth) + 'px !important'},
                is_show: false
            }
        }
    }
    Vue.component('ic_tree_dv_checkbox',ic_tree_dv_checkbox);
    Vue.component('ic_tree_dv_name',ic_tree_dv_name);
    Vue.component('ic_tree_dv_tropism',ic_tree_dv_tropism);
    Vue.component('ic_tree_dv_all',ic_tree_dv_all);
    Vue.component('ic_tree_dv_time',ic_tree_dv_time);
    Vue.component('ic_tree_dv_user',ic_tree_dv_user);
</script>

</body>

</html>