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
									v-model='isok' v-on:click='checkedAll' /></th>
								<th scope="col" width="15%">分类名称</th>
								<th scope="col" width="15%">信息向性区分</th>
								<th scope="col" width="35%">敏感词过滤范围</th>
								<th scope="col" width="auto">最近编辑时间</th>
								<th scope="col" width="auto">最近编辑者</th>
							</tr>
							<tr class="cy_CMICBMS_treven" v-for="fwVo in fwVos">
								<td><input type="checkbox" :value="fwVo.classificationId"
									v-model="checkedId"></td>
								<td>{{fwVo.classificationName}}</td>
								<td>{{fwVo.informationtropism==1?'区分':'不区分'}}</td>
								<td>{{fwVo.allcorephrases!=null||fwVo.allexcludephrases!=null?'全部；':''}}
									{{fwVo.titlecorephrases!=null||fwVo.titleexcludephrases!=null?'标题；':''}}
									{{fwVo.summarycorephrases!=null||fwVo.summaryexcludephrases!=null?'摘要；':''}}
									{{fwVo.textcorephrases!=null||fwVo.textexcludephrases!=null?'正文；':''}}
								</td>
								<td>{{fwVo.updatedatetime}}</td>
								<td>{{fwVo.updateuser}}</td>
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
		  mounted:function(){
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
                        	classificationId:fwVo.classificationId,
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
</script>
</body>

</html>
