<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html xmlns:v-on="http://www.w3.org/1999/xhtml"
      xmlns:v-bind="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css"
          href="${ctx}/css/cy_CIAS_style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/layui/css/layui.css" />

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
                       v-on:click="btn_update">
                <button type="button" class="cy_CMICBMS_impbtn" id="impbtn">
                    		导入
                </button>
                <input type="button" class="cy_CMICBMS_expbtn" value="导出" v-on:click="">
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
                            <ic_tree_dv_checkbox :model="fwVo"></ic_tree_dv_checkbox>
                        </td>
                        <td>
                            <ic_tree_dv_name :model="fwVo"></ic_tree_dv_name>
                        </td>
                        <td>
                            <ic_tree_dv_tropism :model="fwVo"></ic_tree_dv_tropism>
                        </td>
                        <td>
                            <ic_tree_dv_all :model="fwVo">
                            </ic_tree_dv_all>
                        </td>
                        <td>
                            <ic_tree_dv_time :model="fwVo"></ic_tree_dv_time>
                        </td>
                        <td>
                            <ic_tree_dv_user :model="fwVo"></ic_tree_dv_user>
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
                            class="cy_CMICBMS_addinput" v-model="fwVoForUpdate.allcorephrases">
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
<script type="text/javascript" src="${ctx}/layui/layui.js"></script>
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

                },

            dealDigui:function(node,flag){
                if(node.children.length>0){
                    if(flag){
                        if(node.is_show){
                            for(var i=0;i<node.children.length;i++){
                                if(node.children[i].parent_Classification_ID==node.classificationId){
                                    node.children[i].is_show = flag;
                                    this.dealDigui(node.children[i],flag);
                                }
                            }
                        }
                    }else{
                        for(var i=0;i<node.children.length;i++){
                            if(node.children[i].parent_Classification_ID==node.classificationId){
                                node.children[i].is_show = flag;
                                this.dealDigui(node.children[i],flag);
                            }
                        }
                    }
                }
            },
            opClose:function(id){

                var click_node = this.getClickNode(id,this.fwVos,0)[1];
                console.log(click_node)
                if(!click_node.is_show){                      //打开
                    axios.get('queryChild',{
                        params: {
                            parent_Classification_ID:id
                        }
                    })
                        .then(function (response) {
                            click_node.children=response.data;
                            for (var i = 0; i < click_node.children.length; i++) {
                                click_node.children[i].depth = click_node.depth+1;
                            }
                            click_node.is_show = true;

                        })
                        .catch(function (error) {
                            console.log(error);
                        });


                    /*  this.dealDigui(click_node,true); */
                }else{         //关上

                    click_node.is_show = false;

                    this.dealDigui(click_node,false);



                }
            },
            getClickNode:function(id,arr,depth){

                var node = null;
                var b = true;
                for(var i=0;i<arr.length;i++){
                    if(arr[i].classificationId==id){
                        node = [];
                        arr[i].depth = depth;
                        node[0] = i;
                        node[1] = arr[i];
                        b = false;
                        break;
                    }
                }
                if(b){
                    for(var i=0;i<arr.length;i++){

                        if(arr[i].children.length>0){
                            depth++;
                            node = this.getClickNode(id,arr[i].children,depth);

                            if(node!=null){
                                return node;
                            }
                        }
                    }
                }
                return node;

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
        template: '<div><div v-bind:style="t_style">' +

        '<input type="checkbox"  v-if="model.isParent!=1" v-bind:value="model.classificationId"  v-model="vm.checkedId" class="cy_CMICBMS_checkbox">&nbsp;<label for="cy_CMICBMS_checkbox"></label></div>' +
        '<div v-show="model.is_show" ><ic_tree_dv_checkbox  v-for="model in model.children"  v-bind:model="model"></ic_tree_dv_checkbox></div></div>' ,
        props: ['model'],
        data: function () {
            return {
                t_style: {padding: '10px 0px !important'},
            }
        },
    };


    var ic_tree_dv_name = {
        template: '<div><div v-bind:style="t_style">' +
        '<img v-if="model.isParent==1" v-bind:src="imgsrc" v-on:click="opClose(model.classificationId)">' +
        '{{model.classificationName}}&nbsp;</div>'+
        '<div v-show="model.is_show" ><ic_tree_dv_name  v-for="model in model.children" v-bind:model="model"></ic_tree_dv_name></div></div>'
        ,
        props: ['model'],

        data: function () {
            return {
                t_style: {padding: '10px 0px !important'},
                imgsrc: this.is_show?"../image/is-tbarr02.png":"../image/is-tbarr01.png",
            }
        },
        methods: {
            opClose: function (id) {
                if(this.model.is_show){
                    this.imgsrc = "../image/is-tbarr01.png";
                }else{
                    this.imgsrc = "../image/is-tbarr02.png";
                }
                this.$parent.opClose(id);
            }
        },
        mounted:function(){
            this.t_style = {padding: '10px '+(20*this.model.depth)+'px !important'} ;
        }
    };
    var ic_tree_dv_tropism = {
        template: '<div><div v-bind:style="t_style">' +
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
    var ic_tree_dv_all = {
        template: '<div ><div v-bind:style="t_style">' +

        '{{model.allcorephrases!=null||model.allexcludephrases!=null?\'全部；\':\'\'}}' +
        '{{model.titlecorephrases!=null||model.titleexcludephrases!=null?\'标题；\':\'\'}}' +
        '{{model.summarycorephrases!=null||model.summaryexcludephrases!=null?\'摘要；\':\'\'}}' +
        '{{model.textcorephrases!=null||model.textexcludephrases!=null?\'正文；\':\'\'}}&nbsp;</div>' +
        '<div v-if="model.is_show" ><span> </span><span> </span><ic_tree_dv_all  v-for="model in model.children" v-bind:model="model"></ic_tree_dv_all></div></div>'
        ,
        props: ['model'],
        data: function () {
            return {
                t_style: {padding: '10px 0px !important'},
            }
        }
    }
    var ic_tree_dv_time = {
        template: '<div ><div v-bind:style="t_style">' +

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
    var ic_tree_dv_user = {
        template: '<div ><div v-bind:style="t_style">' +

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
    Vue.component('ic_tree_dv_checkbox',ic_tree_dv_checkbox);
    Vue.component('ic_tree_dv_name',ic_tree_dv_name);
    Vue.component('ic_tree_dv_tropism',ic_tree_dv_tropism);
    Vue.component('ic_tree_dv_all',ic_tree_dv_all);
    Vue.component('ic_tree_dv_time',ic_tree_dv_time);
    Vue.component('ic_tree_dv_user',ic_tree_dv_user);

    layui.use(['upload','element'], function(){
        var upload = layui.upload;
        var element = layui.element;
        //执行实例
        var uploadInst = upload.render({
            elem: '#impbtn' //绑定元素
            ,url: 'upload' //上传接口
            ,done: function(res){
                //上传完毕回调
                layer.msg('上传成功');
            }
            ,error: function(){
                //请求异常回调
                layer.msg('上传失败');
            }
        });
    });
</script>

</body>

</html>