<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ taglib prefix="ss" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources" var="sysInfo" />
<fmt:message key="front_menu" var="front_menu" bundle="${sysInfo}" />
<fmt:message key="E0006" var="E0006" bundle="${sysInfo}" />
<fmt:message key="E0019" var="E0019" bundle="${sysInfo}" />
<fmt:message key="I0014" var="I0014" bundle="${sysInfo}" />
<fmt:message key="I0022" var="I0022" bundle="${sysInfo}" />
<fmt:message key="E0026" var="E0026" bundle="${sysInfo}" />
<fmt:message key="E0035" var="E0035" bundle="${sysInfo}" />
<fmt:message key="I0008" var="I0008" bundle="${sysInfo}" />
<fmt:message key="I0012" var="I0012" bundle="${sysInfo}" />
<fmt:message key="I0020" var="I0020" bundle="${sysInfo}" />
<fmt:message key="I0021" var="I0021" bundle="${sysInfo}" />
<fmt:message key="E0036" var="E0036" bundle="${sysInfo}" />
<fmt:message key="E0004" var="E0004" bundle="${sysInfo}" />
<fmt:message key="E0014" var="E0014" bundle="${sysInfo}" />
<fmt:message key="I0012" var="I0012" bundle="${sysInfo}" />
<fmt:message key="I0013" var="I0013" bundle="${sysInfo}" />
<fmt:message key="W0004" var="W0004" bundle="${sysInfo}" />
<fmt:message key="I0002" var="I0002" bundle="${sysInfo}" />
<fmt:message key="W0011" var="W0011" bundle="${sysInfo}" />
<fmt:message key="I0024" var="I0024" bundle="${sysInfo}"/>
<fmt:message key="E0075" var="E0075" bundle="${sysInfo}" />
<fmt:message key="E0077" var="E0077" bundle="${sysInfo}" />
<fmt:message key="UseManualFileName" var="UseManualFileName" bundle="${sysInfo}" />
<fmt:message key="DownloadFileTemplatePath" var="DownloadFileTemplatePath" bundle="${sysInfo}" />
<html>
<head>
<meta charset="UTF-8">
<title>竞争情报分析系统</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/cy_CIAS_style.css">
<!-- <link rel="stylesheet" href="//unpkg.com/iview/dist/styles/iview.css">
 -->    <style>
        [v-cloak] {
            display: none;
        }
    </style>
</head>

<body style="background-color: #f2f3f8;">
	<div class="cy_hidebg" id="cy_hidebg"></div>
	<div id="notice" v-cloak>
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
							<a href="../noticeRes/toNoticeRes">
								<div class="cy_CIASFE_conbox06">简报列表</div>
							</a> <a href="../notice/toNoticeList">
								<div class="cy_CIASFE_conbox05">简报任务</div>
							</a>
						</div>
						<div class="cy_CIASFE_conbox13">
							<input type="button" class="cy_CMICBMS_addbtn" value="新增任务"
								v-on:click="addNotice"> <input type="button"
								class="cy_CMICBMS_edbtn" value="编辑" v-on:click="updateNotice">
							<input type="button" class="cy_CMICBMS_dltbtn" value="删除"
								v-on:click="deleteNotice">
						</div>
						<div class="cy_CIASFE_conbox12">
							<table width="100%" border="0" class="cy_CIASFE_bttb"
								cellspacing="0">
								<tbody>
									<tr>
										<th scope="col" width="30px"><input type="checkbox"
											v-model='isok' v-on:click='checkedAll' /></th>
										<th>任务名称</th>
										<th>执行计划</th>
										<th>简报类型</th>
										<th>创建人</th>
										<th>创建时间</th>
										<th>生成时间</th>
									</tr>
									<tr class="cy_CMICBMS_treven" v-for="vo in vos">
										<td><input type="checkbox" :value="vo.noticeid"
											v-model="checkedId"></td>
										<td>{{vo.title}}</td>
										<td>{{vo.plan}}</td>
										<td>{{vo.typename}}</td>
										<td>{{vo.createuser}}</td>
										<td>{{vo.createdatetime}}</td>
										<td>{{vo.generationtime}}</td>
									</tr>
									<tr>
										<td v-if="vos.length==0" colspan="7">{{info}}</td>
									</tr>
								</tbody>
							</table>
							<pager ref="pagecomponent"></pager>

						</div>
						<div class="cy_CIASFE_conbox07" style="color: red">注：后台规定日报、周报、月报的固定模板，这一版本不提供自定义模板或多种模板选择</div>
					</div>
				</div>
			</div>
		</div>
		<ic_sycc_template></ic_sycc_template>
	</div>


	
	<div id="noticeForm">
		<div id="cy_CMICBMS_newbt">
			<div class="cy_CMICBMS_newbttop">
				<div class="cy_CMICBMS_newbttit">{{formTitle}}</div>
				<div class="cy_CMICBMS_newtbclose" onClick="hide();">X</div>
			</div>
			<div class="cy_CMICBMS_newbtbd">
				<div class="cy_CMICBMS_newbttb">
					简报名称：<input type="text" class="cy_CMICBMS_newbtinput"
						v-model="noticeVo.title" placeholder="请输入名称" maxlength="100">
				</div>
				<div class="cy_CMICBMS_newbttb">
					简报类型： <label v-for="type in types"><input type="radio"
						name="newbt" :value="type.typeId" v-model="noticeVo.noticetype"
						id="newbt_0">{{type.typeName}}</label>
				</div>
				<ic_day :model="noticeVo" v-if="noticeVo.noticetype==1"></ic_day>
				<ic_week :model="noticeVo" v-if="noticeVo.noticetype==2"></ic_week>
				<ic_month :model="noticeVo" v-if="noticeVo.noticetype==3"></ic_month>
				<div class="cy_CMICBMS_newbttb">
					邮件推送：<input type="text" class="cy_CMICBMS_newbtinput"
						placeholder="请输入邮箱地址，多个邮箱请用逗号隔开" v-model="noticeVo.email" maxlength="50">
				</div>
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
	<script type="text/javascript" src="${ctx}/js/ic_components.js"></script>
	<script>
	//提示信息
    var Info = {
        E0006: '${E0006}',
        E0019: '${E0019}',
        I0014: '${I0014}',
        I0024: '${I0024}',
        I0022: '${I0022}',
        E0035: '${E0035}',
        E0026: '${E0026}',
        I0008: '${I0008}',
        I0012: '${I0012}',
        I0020: '${I0020}',
		E0036: '${E0036}',
        I0021: '${I0021}',
    	E0004:'${E0004}',
		E0014:'${E0014}',
		W0004:'${W0004}',
		I0012:'${I0012}',
        I0002:'${I0002}',
		I0013:'${I0013}',
		E0075:'${E0075}',
		E0077:'${E0077}',
		W0011:'${W0011}',
		syccurl:'${ctx }/${DownloadFileTemplatePath}/${UseManualFileName}'
    };
	
    Vue.component('ic_sycc_template', ic_sycc_template);
	
	var mailReg = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;//邮箱验证

    function show()  //显示隐藏层和弹出层
    {
        var hideobj = document.getElementById("cy_hidebg");
        cy_hidebg.style.display = "block";  //显示隐藏层
        document.getElementById("cy_CMICBMS_newbt").style.display = "block";  //显示弹出层
    }

    function hide()  //去除隐藏层和弹出层
    {
        document.getElementById("cy_hidebg").style.display = "none";
        document.getElementById("cy_CMICBMS_newbt").style.display = "none";
    }
	//主体块
    var vm = new Vue({
        el: '#notice',
        data: {
            vos: [],		//返回的视图类集合
            isok: false,
            checkedId: [],
            info: Info.I0024
        },
        mounted: function () {
            this.btn_search();
        },
        components: {
            'pager': ic_pager
        },
        methods: {
            checkedAll:
            	//全选反选按钮
                function () {
                    if (!this.isok) { //全选中 所有CheckBox
                        var vos = [];
                        var vos = this.vos;
                        this.checkedId = [];
                        vos.forEach(function (vo) {
                            this.checkedId.push(vo.noticeid);
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

            addNotice:                                        //添加弹出
                function () {
                    vm2.formTitle = "新建简报任务";
                    vm2.url = "../notice/addNotice";
                    vm2.noticeVo = {
                        title: '',
                        type: '',
                        monitordatestart: '',
                        monitordateend: '',
                        monitortimestart: '',
                        monitortimeend: '',
                        senddate: '',
                        sendtime: ''
                    };
                    vm2.noticeVo.noticeid = null;
                    window.show();

                },
            updateNotice:                         //修改弹出
                function () {
                    var checkedId = [];
                    checkedId = this.checkedId;
                    if (checkedId.length != 1) {
                        layer.msg(Info.E0006);
                    } else {
                        vm2.formTitle = "编辑简报任务";
                        vm2.url = "updateNotice";
                        axios.get("queryOne", {
                            params: {
                                noticeid: checkedId[0]
                            }
                        }).then(function (res) {
                            vm2.noticeVo = res.data;
                        })
                            .catch(function (error) {
                                console.log(error);
                            });
                        window.show();
                    }
                },

            deleteNotice:                     //删除提示
                function () {
                    var _this = this;
                    if (this.checkedId == null) {
                        layer.msg(Info.E0019);
                    } else {
                        var deleteLayer = layer.confirm(Info.W0011, {
                            btn: ['确定', '取消'] //按钮
                        }, function () {
                            var url = "deleteNotice";
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
                                    if (res.msg == 'ok') {
                                        layer.msg(Info.I0014);
                                        _this.checkedId=[];
                                        _this.btn_search()
                                    } else if (res.msg == 'fault') {
                                        layer.msg(Info.I0022);
                                        _this.btn_search()
                                    }

                                }
                            })
                        });
                    }
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
                    var _this = this;
                    var url = "../notice/queryAll";
                    axios.get(url, {
                        params: {
                            pageNow: pageBean.pageNow,
                            rowSize: pageBean.rs_selected
                        }
                    })
                        .then(function (response) {
                            var rowCount = response.data.rowCount + "";
                            var vos = response.data.vos;
                            _this.$refs.pagecomponent.dealAfterSearch(rowCount); //rowCount为总条目数  在ajax请求返回函数需调用该方法
                            _this.vos = vos;
/*                             if(rowCount=='0'){
   	    					 layer.msg(Info.I0002);
   	    				  	} */
                            _this.checkedId=[]
                        })
                        .catch(function (error) {
                            console.log(error);
                        });

                }
            }
        }
    });
    var vm2 = new Vue({
        el: '#noticeForm',
        data: {
            noticeVo: {
                title: '',
                type: '',
                monitordatestart: '',
                monitordateend: '',
                monitortimestart: '',
                monitortimeend: '',
                senddate: '',
                sendtime: '',
                email:''
            },
            types: [],
            formTitle: "",
            url: ""
        },
        created: function () {
            this.getTypes();
        },
        methods: {
            submit:
                function () {
            		var _this = this;
                    var notice = this.noticeVo;
                    if (notice.title == "" || notice.noticetype == ""
                        || notice.monitortimestart == "" || notice.monitortimeend == "" || notice.sendtime == "") {
                        layer.msg(Info.E0026);
                        return false;
                    } else if (notice.type == 1 && notice.monitortimestart > notice.monitortimeend) {
                            layer.msg(Info.E0035);
                            return false;
                    }else if((notice.type == 2||notice.type==3) && (notice.monitordatestart=="" && notice.monitordateend=="" &&notice.senddate=="")){
                    	layer.msg(Info.E0026);
                            return false;
                    }else if(!mailReg.test(notice.email)){
            			layer.msg(Info.E0036);
            			return false;
            		}
                    var url = this.url;
                    axios.get(url, {
                        params: {
                        	noticeid:notice.noticeid,
                            title: notice.title,
                            noticetype: notice.noticetype,
                            monitordatestart: notice.monitordatestart,
                            monitordateend: notice.monitordateend,
                            monitortimestart: notice.monitortimestart,
                            monitortimeend: notice.monitortimeend,
                            senddate: notice.senddate,
                            sendtime: notice.sendtime,
                            email:notice.email,
                            updatedatetime:notice.updatedatetime,
                            createuser : notice.createuser
                        }
                    }).then(function (response) {
                        if (response.data.msg == "ok") {
                        	
                            if(_this.formTitle=="新建简报任务"){
                                layer.msg(Info.I0008);
                            }else if(_this.formTitle=="编辑简报任务"){
                                layer.msg(Info.I0012);    
                            }
                            window.hide();
                            vm.btn_search();
                        } else if (response.data.msg == "fault") {
                            if(_this.formTitle=="新建简报任务"){
                                layer.msg(Info.I0020);
                            }else if(_this.formTitle=="编辑简报任务"){
                                layer.msg(Info.I0021);
                            }
                        }else if (response.data.msg == "checkFalse") {
                        	 layer.msg(Info.W0004);
						}else if (response.data.msg == "checkName") {
                        	 layer.msg(Info.E0077);
						}
                    })
                        .catch(function (error) {
                            console.log(error);
                        });


                },
            getTypes:
                function () {
                    var _this = this;
                    axios.get('getTypes', {})
                        .then(function (response) {
                            var data = response.data;
                            data.forEach(function (type) {
                                _this.types.push(type);
                            })
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                }
        },

    })
    var ic_day = {
        template: '<div><div class="cy_CMICBMS_newbttb">监测时间：<input type="time" class="cy_CMICBMS_newbttime" v-model="model.monitortimestart">\n' +
        '    — <input type="time" class="cy_CMICBMS_newbttime" v-model="model.monitortimeend">\n' +
        '</div>\n' +
        '<div class="cy_CMICBMS_newbttb">发送时间：\n' +
        '    <input type="time" class="cy_CMICBMS_newbttime" v-model="model.sendtime">\n' +
        '</div></div>\n'
        ,
        props: ['model']


    };
    var ic_week = {
        template: '<div><div class="cy_CMICBMS_newbttb">监测时间：<select class="cy_CMICBMS_newbtdate" v-model="model.monitordatestart">\n' +
        '    <option value="1" >周一</option>\n' +
        '    <option value="2" >周二</option>\n' +
        '    <option value="3" >周三</option>\n' +
        '    <option value="4" >周四</option>\n' +
        '    <option value="5" >周五</option>\n' +
        '    <option value="6" >周六</option>\n' +
        '    <option value="7" >周日</option>\n' +
        '</select>\n' +
        '    <input type="time" class="cy_CMICBMS_newbttime" v-model="model.monitortimestart">\n' +
        '    — <select class="cy_CMICBMS_newbtdate" v-model="model.monitordateend">\n' +
        '        <option value="1" v-if="model.monitordatestart<=1">周一</option>\n' +
        '        <option value="2" v-if="model.monitordatestart<=2">周二</option>\n' +
        '        <option value="3" v-if="model.monitordatestart<=3">周三</option>\n' +
        '        <option value="4" v-if="model.monitordatestart<=4">周四</option>\n' +
        '        <option value="5" v-if="model.monitordatestart<=5">周五</option>\n' +
        '        <option value="6" v-if="model.monitordatestart<=6">周六</option>\n' +
        '        <option value="7" v-if="model.monitordatestart<=7">周日</option>\n' +
        '    </select>\n' +
        '    <input type="time" class="cy_CMICBMS_newbttime" v-model="model.monitortimeend">\n' +
        '</div>\n' +
        '<div class="cy_CMICBMS_newbttb">发送时间：<select class="cy_CMICBMS_newbtdate" v-model="model.senddate">\n' +
        '    <option value="1" >周一</option>\n' +
        '    <option value="2" >周二</option>\n' +
        '    <option value="3" >周三</option>\n' +
        '    <option value="4" >周四</option>\n' +
        '    <option value="5" >周五</option>\n' +
        '    <option value="6" >周六</option>\n' +
        '    <option value="7" >周日</option>\n' +
        '</select>\n' +
        '    <input type="time" class="cy_CMICBMS_newbttime" v-model="model.sendtime">\n' +
        '</div></div>',
        props: ['model']
    };
    var ic_month = {
        template: '<div><div class="cy_CMICBMS_newbttb">监测时间：<select class="cy_CMICBMS_newbtdate" v-model="model.monitordatestart">\n' +
        '    <option :value="index+1" v-for="(day, index) in month">{{day}}号</option>\n' +
        '</select>\n' +
        '    <input type="time" class="cy_CMICBMS_newbttime" v-model="model.monitortimestart">\n' +
        '    — <select class="cy_CMICBMS_newbtdate" v-model="model.monitordateend"><option :value="index+1" v-for="(day, index) in month" v-if="model.monitordatestart<=index+1">{{day}}号</option></select>\n' +
        '    <input type="time" class="cy_CMICBMS_newbttime" v-model="model.monitortimeend">\n' +
        '</div>\n' +
        '<div class="cy_CMICBMS_newbttb">发送时间：<select class="cy_CMICBMS_newbtdate" v-model="model.senddate"><option :value="index+1" v-for="(day, index) in month">{{day}}号</option></select>\n' +
        '    <input type="time" class="cy_CMICBMS_newbttime" v-model="model.sendtime">\n' +
        '</div></div>'
        ,
        props: ['model'],
        data: function () {
            return {
                month: ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十",
                    "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十",
                    "二十一", "二十二", "二十三", "二十四", "二十五", "二十六", "二十七", "二十八", "二十九", "三十", "三十一"]
            }
        }
    };
    

    var menu_datas = JSON.parse('${front_menu}') //菜单数据来源于 classes/resources.properties
    
    var menu_url_data  = 'noticeRes';
    
    Vue.component('ic_top_menu',ic_top_menu);   // ic_top_menu 引自 js/ic_component.js

    Vue.component('ic_user_info',ic_user_info);  // ic_user_info 引自 js/ic_component.js
    
    Vue.component('ic_day', ic_day);
    Vue.component('ic_week', ic_week);
    Vue.component('ic_month', ic_month);
</script>

</body>
</html>