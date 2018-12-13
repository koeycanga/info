<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="ss" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources" var="sysInfo" />
<fmt:message key="front_menu" var="front_menu" bundle="${sysInfo}" />
<fmt:message key="E0006" var="E0006" bundle="${sysInfo}" />
<fmt:message key="E0019" var="E0019" bundle="${sysInfo}" />
<fmt:message key="W0002" var="W0002" bundle="${sysInfo}" />
<fmt:message key="I0014" var="I0014" bundle="${sysInfo}" />
<fmt:message key="E0026" var="E0026" bundle="${sysInfo}" />
<fmt:message key="E0004" var="E0004" bundle="${sysInfo}" />
<fmt:message key="I0008" var="I0008" bundle="${sysInfo}" />
<fmt:message key="E0027" var="E0027" bundle="${sysInfo}" />
<fmt:message key="E0031" var="E0031" bundle="${sysInfo}" />
<fmt:message key="E0032" var="E0032" bundle="${sysInfo}" />
<fmt:message key="I0013" var="I0013" bundle="${sysInfo}" />
<fmt:message key="I0012" var="I0012" bundle="${sysInfo}" />
<fmt:message key="E0036" var="E0036" bundle="${sysInfo}" />
<fmt:message key="E0037" var="E0037" bundle="${sysInfo}" />
<fmt:message key="W0004" var="W0004" bundle="${sysInfo}" />
<fmt:message key="I0011" var="I0011" bundle="${sysInfo}" />
<fmt:message key="I0024" var="I0024" bundle="${sysInfo}" />
<fmt:message key="I0002" var="I0002" bundle="${sysInfo}" />
<fmt:message key="E0042" var="E0042" bundle="${sysInfo}" />
<fmt:message key="I0026" var="I0026" bundle="${sysInfo}" />
<fmt:message key="W0001" var="W0001" bundle="${sysInfo}" />
<fmt:message key="MaxSearchCnt" var="MaxSearchCnt" bundle="${sysInfo}" />
<html xmlns:v-on="http://www.w3.org/1999/xhtml"
	xmlns:v-bind="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<title></title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="${ctx}/js/vue.min.js"></script>
<script src="${ctx}/js/vue-resource.min.js"></script>
<!-- <link rel="stylesheet" type="text/css" href="${ctx}/css/cy_CIAS_style.css">   -->

<link rel="stylesheet" type="text/css" href="${ctx}/layui/css/layui.css" />
<link id="lnk" rel="stylesheet" type="text/css" href="">

<script type="text/javascript" src="${ctx}/layer/layer.js"></script>
<script src="${ctx}/js/axios.min.js"></script>
<script type="text/javascript" src="${ctx}/js/polyfill.min.js"></script>
<style>
[v-cloak] {
	display: none;
	/* 配合样式使用 */
}

.mark1 {
	display: inline-block;
	width: 0px;
	height: 0px;
	border-width: 7px;
	border-style: dashed;
	overflow: hidden;
	border-color: #666 transparent transparent transparent;
	top: 12.5%;
	right: 11%;
	position: absolute;
}
input[disabled]{color:#fff;opacity:1}
</style>
</head>
<body>
	<div id="userInfo" v-cloak>
		<div class="cy_hidebg" id="cy_hidebg"></div>
		<div class="cy_CMICBMS_bodybg">
			<p>
				用户管理 > <span>账号管理</span>
			</p>
			<div class="cy_CMICBMS_tablebox">
				<form @submit.prevent>
					<div class="cy_CMICBMS_box07">

						<select v-model="userInfoVo2.ustatus">
							<option value="2">账号状态</option>
							<option value="1">启用</option>
							<option value="0">停用</option>
						</select>
						<div class="cy_CMICBMS_schbox01">
							<input type="text" placeholder="输入用户名检索..."
								v-model="userInfoVo2.uid">
						</div>
						<input type="button" class="cy_CMICBMS_schbtn" value="检索"
							v-on:click="btn_search">

					</div>
				</form>
				<div class="cy_CMICBMS_box08">
					<input type="button" class="cy_CMICBMS_addbtn" value="新增"
						v-on:click="addUser"> <input type="button"
						class="cy_CMICBMS_edbtn" value="编辑" v-on:click="updateUser">
					<input type="button" class="cy_CMICBMS_dltbtn" value="删除"
						v-on:click="deleteUser">
				</div>
				<div class="cy_CMICBMS_box08">
					<table width="100%" border="0" class="cy_CMICBMS_accmngtb"
						cellspacing="0">
						<tbody>
							<tr>
								<th width="25px" scope="col"><input type="checkbox"
									v-model='isok' v-on:click='checkedAll' /></th>
								<th scope="col">用户名</th>
								<th scope="col">用户角色</th>
								<th scope="col">姓名</th>
								<th scope="col">所属部门</th>
								<th scope="col">手机号</th>
								<th scope="col">邮箱</th>
								<th scope="col" style="text-align: center">状态</th>
							</tr>
							<tr class="cy_CMICBMS_treven" v-for="userInfoVo in users">
								<td><input type="checkbox" :value="userInfoVo.unum"
									v-model="checkedId"></td>
								<td>{{userInfoVo.uid}}</td>
								<td>{{userInfoVo.usuperuserflag==0?userInfoVo.urolename:'超级管理员'}}</td>
								<td>{{userInfoVo.uname}}</td>
								<td>{{userInfoVo.udep}}</td>
								<td>{{userInfoVo.utel}}</td>
								<td>{{userInfoVo.uemail}}</td>
								<td>{{userInfoVo.ustatus==1?"启用":"停用"}}</td>
							</tr>
							<tr>
								<td v-if="users.length==0" colspan="8">{{info}}</td>
							</tr>
						</tbody>
					</table>
				</div>

				<pager ref="pagecomponent"></pager>


			</div>
		</div>
	</div>
	<div id="addform">
		<div id="cy_CMICBMS_add">
			<div class="cy_CMICBMS_addtop">
				<div class="cy_CMICBMS_addtit">{{title}}</div>
				<div class="cy_CMICBMS_addclose" onClick="hide();">X</div>
			</div>
			<div class="cy_CMICBMS_addtb">
				<div>
					<span>*</span>用户角色：<select v-model="userInfoVo.urole"
						v-if="userInfoVo.usuperuserflag==0">
						<option value="0" selected="selected">输入用户角色</option>
						<option :value="role.userRole_ID" v-for="role in roles">{{role.userRoleName}}</option>
					</select> <i class="mark1"></i><span v-if="userInfoVo.usuperuserflag==1">该用户为超级管理员</span>
				</div>
				<div>
					<span>*</span>用户名：<input type="text" placeholder="输入用户名"
						class="cy_CMICBMS_addinput" v-model="userInfoVo.uid"
						:disabled="is_disabled" @focus="uid_f" @blur="uid_b"
						maxlength="10" >
					<div class="cy_CMICBMS_errortip" v-if="userInfoVo.uid==''"
						v-show="uidshow">输入用户名！</div>
				</div>
				<div>
					<span>*</span>设置密码：<input type="password" placeholder="设置密码"
						class="cy_CMICBMS_addinput" v-model="userInfoVo.upwd"
						@focus="upwd_f" @blur="upwd_b" maxlength="32">
					<div class="cy_CMICBMS_errortip" v-if="userInfoVo.upwd==''"
						v-show="upwdshow">输入设置密码！</div>
				</div>
				<div>
					<span>*</span>确认密码：<input type="password" placeholder="确认密码"
						class="cy_CMICBMS_addinput" v-model="checkPwd" @focus="checkPwd_f"
						@blur="checkPwd_b" maxlength="32">
					<div class="cy_CMICBMS_errortip" v-if="checkPwd==''"
						v-show="checkPwdshow">确认密码！</div>
				</div>
				<div>
					<span>*</span>姓名：<input type="text" placeholder="输入姓名"
						class="cy_CMICBMS_addinput" v-model="userInfoVo.uname"
						@focus="uname_f" @blur="uname_b" maxlength="20">
					<div class="cy_CMICBMS_errortip" v-if="userInfoVo.uname==''"
						v-show="unameshow">输入姓名！</div>
				</div>
				<div>
					所属部门：<input type="text" placeholder="所属部门"
						class="cy_CMICBMS_addinput" v-model="userInfoVo.udep"
						maxlength="20">
				</div>
				<div>
					手机号码：<input type="text" placeholder="输入手机号"
						class="cy_CMICBMS_addinput" v-model="userInfoVo.utel">
				</div>
				<div>
					邮箱地址：<input type="text" placeholder="输入邮箱地址"
						class="cy_CMICBMS_addinput" v-model="userInfoVo.uemail"
						maxlength="50">
				</div>
				<div id="cy_CMICBMS_status">
					状态：<input type="radio" name="RadioGroup1" value="1"
						id="RadioGroup1_0" v-model="userInfoVo.ustatus">启用 <input
						type="radio" name="RadioGroup1" value="0" id="RadioGroup1_1"
						v-model="userInfoVo.ustatus">停用
				</div>
				<div style="margin: 40px 20px 0 0;">
					<input type="button" value="确定" class="cy_CMICBMS_schbtn"
						v-on:click="submit">
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${ctx}/js/comm.js"></script>
	<script type="text/javascript" src="${ctx}/js/ic_components.js"></script>
	<script>
    AdaptationResolution('${ctx}');//分辨率适配
    
    var Info = {				//提示信息
        E0006:'${E0006}',
        E0019:'${E0019}',
        W0002:'${W0002}',
        I0012:'${I0012}',
        I0014:'${I0014}',
        E0026:'${E0026}',
        E0004:'${E0004}',
        I0008:'${I0008}',
        E0027:'${E0027}',
        E0031:'${E0031}',
        E0032:'${E0032}',
        I0013:'${I0013}',
        E0036:'${E0036}',
        E0037:'${E0037}',
        W0004:'${W0004}',
        I0024:'${I0024}',
        I0002:'${I0002}',
        E0042:'${E0042}',
        I0026:'${I0026}',
        W0001:'${W0001}',
        MaxSearchCnt:'${MaxSearchCnt}',
		I0011:'${I0011}'
    };
    var phoneReg = /^1[3-578]\d{9}$/;				//电话验证
    var mailReg = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;//邮箱验证
    var vm = new Vue({				//列表vue
        el: '#userInfo',
        data: {
            userInfoVo2: {ustatus:2},		//搜索条件vo
            users: [],						//用户信息集合
            isok: false,					//全选按钮状态，false==未选中
            checkedId:[],			//复选框选取的unum集合
            info: Info.I0024
        },
        mounted: function () {
            this.btn_search();				//在页面加载完html后进行搜索
        },
        components:{
            'pager':ic_pager				//分页组件
        },
        methods: {
            checkedAll:			//单击全选
                function () {
                    if (!this.isok) { //全选中 所有CheckBox
                        var users = [];
                        var users = this.users;
                        this.checkedId = [];
                        users.forEach(function (uservo) {
                            this.checkedId.push(uservo.unum);
                        }, this);
                    } else {   //反选清楚所有CheckBox选中
                        this.checkedId = []
                    }
                },
            btn_search:		//搜索按钮
                function () {
                	this.$refs.pagecomponent.pageBean.pageNow = 1;
                    this.search(this.$refs.pagecomponent.pageBean);
                },

            addUser:                             //添加弹出
                function () {
                    vm2.title="添加用户";			//弹出层标题更改
                    vm2.url="addUser";			//弹出层url更改
                    vm2.userInfoVo={uid:'',uname:'',udep:'',utel:'',uemail:'',upwd:'',uupdatedatetime:'',urole:0,usuperuserflag:0,ustatus:"0"};//将弹出层中的user属性置为空
                    vm2.userInfoVo.unum=null;	//将unum、密码置为空
                    vm2.checkPwd="";			
                    vm2.is_disabled = false;	//添加用户状态，可以输入用户名
                    var hideobj=document.getElementById("cy_hidebg");
                    cy_hidebg.style.display="block";  //显示隐藏层
                    document.getElementById("cy_CMICBMS_add").style.display="block";  //显示弹出层

                },
            updateUser:                         //修改弹出
                function () {
                    var checkedId = [];			

                    checkedId= this.checkedId;		//获取选中的id
                    if (checkedId.length!=1){
                        layer.msg(Info.E0006);
                    }else{
                        vm2.title="修改用户";			//修改标题、url
                        vm2.url="updateUser";
                        vm2.is_disabled = true;		//修改用户状态，不可修改用户名
                        vm2.bgc_style = {'background-color': '#949494'};
                        axios.get("queryOne",{		//回显当前user信息
                            params: {
                                unum:checkedId[0]
                            }
                        }).then(function (res) {
                            vm2.userInfoVo=res.data;
                            vm2.userInfoVo.upwd='';
                            vm2.checkPwd='';
                        })
                            .catch(function (error) {
                                console.log(error);
                            });
                        var hideobj=document.getElementById("cy_hidebg");
                        cy_hidebg.style.display="block";  //显示隐藏层
                        document.getElementById("cy_CMICBMS_add").style.display="block";  //显示弹出层
                    }
                },

            deleteUser:                     //删除提示
                function () {
                    var _this = this;
                    if(this.checkedId.length==0){		//如果没选中信息的提示——选择1至1条以上信息
                        layer.msg(Info.E0019);			
                    }else{
                        var deleteLayer =layer.confirm(Info.W0002, {		//提示——确定删除？
                            btn: ['确定', '取消'] //按钮
                        }, function () {
                            var url = "delete";				//更改url
                            var checkedId = [];
                            _this.checkedId.forEach(function (id) {
                                checkedId.push(id);
                            }, this);
                            $.ajax({			//批量删除，使用jq的ajax方法，禁止深度序列化
                                url:url,
                                data:{"checkedId":checkedId},
                                dateType:"json",
                                type:"post",
                                traditional:true,
                                success:function(res){
                                    if ("OK" == res) {
                                        if(checkedId.length==10){
                                            _this.$refs.pagecomponent.pageBean--;
                                        }
                                        layer.msg(Info.I0014);
                                        _this.btn_search()
                                        _this.checkedId=[];
                                    }else if("current"==res){
                                    	layer.msg("当前用户不能被删除!");
                                    } else {
                                        // 超级管理员不能被删除
                                        layer.msg(Info.E0032 + " 超级管理员:" +res+"不能被删除");
                                    }
                                }
                            })
                        });
                    }
                }
        },
        watch: {				//全选按钮控制
            "checkedId": function() {
                if (this.checkedId.length != this.users.length) {
                    this.isok = false
                } else {
                    this.isok = true
                }
            }
        },

        computed:{				//检索方法
            search:function(){
                return function (pageBean) {
                    var l_index = layer.msg(Info.I0011, {
                        icon: 16
                        ,shade: 0.01
                    });
                    var _this = this;
                    var url = "../user/queryAll";
                    var user = this.userInfoVo2;
                    axios.get(url,{
                        params: {
                            uid:user.uid,				//用户名
                            ustatus:user.ustatus,		//用户状态
                            pageNow:pageBean.pageNow,
                            rowSize:pageBean.rs_selected
                        }
                    })
                        .then(function (response) {
                            var rowCount = response.data.rowCount+"";
                            var allUsers = response.data.users;
                            _this.$refs.pagecomponent.dealAfterSearch(rowCount); //rowCount为总条目数  在ajax求返回函数需调用该方法
                            _this.checkedId = [];		//检索完毕将复选框选择取消，防止点击分页等复选框仍然选取
                            _this.users =allUsers;
                            layer.close(l_index);	//关闭动画
                            if(rowCount=='0'){
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
        }
    });
    function hide()  //去除隐藏层和弹出层
    {
        document.getElementById("cy_hidebg").style.display="none";
        document.getElementById("cy_CMICBMS_add").style.display="none";
        vm2.reset_all();
    }

    //+++++++++++++++++++++++++++添加隐藏层++++++++++++++++++++++++
    var vm2 = new Vue({		//弹出层控制vue
        el: '#addform',
        data: {
            userInfoVo:{uid:'',uname:'',udep:'',utel:'',uemail:'',upwd:'',ustatus:''},	//编辑信息
            checkPwd:"",	//确认密码
            roles:[],		//回显的角色信息
            title:"",		//标题
            is_disabled:false,	//用户名文本框是否可用
            url:"",			
            uidshow:false,		//四个必填项的气泡控制
            upwdshow:false,		
            checkPwdshow:false,
            bgc_style:{},
            unameshow:false		
        },
        created: function () {
            this.getRoles();
        },
        methods: {
        	//提示气泡的焦点事件
            uid_f:function () {
                this.uidshow = false;
            },
            uid_b:function () {
                this.uidshow = true;
            },
            upwd_f:function () {
                this.upwdshow = false;
            },
            upwd_b:function () {
                this.upwdshow = true;
            },
            checkPwd_f:function () {
                this.checkPwdshow = false;
            },
            checkPwd_b:function () {
                this.checkPwdshow = true;
            },
            uname_f:function () {
                this.unameshow = false;
            },
            uname_b:function () {
                this.unameshow = true;
            },
            reset_all:function(){		//关闭弹出层后，将属性重置
            	this.uidshow=false		
            	this.upwdshow=false
            	this.checkPwdshow=false
            	this.unameshow=false	
            },
            submit:
                function () {  // 新增&编辑时的提交处理
                    var _this = this;
                	this.reset_all();
                    var user = this.userInfoVo;
                    if(user.urole==""||user.uid==""||user.uname==""||user.urole==null||user.urole==0){
                        layer.msg(Info.E0026);  // 必须项未输入时，E0026输出
                        return false;
                    }else if(this.checkPwd!=user.upwd){
                        layer.msg(Info.E0004);  // 密码和确认密码不一致时，E0004输出
                        return false;
                    }else if(!mailReg.test(user.uemail)&&user.uemail!=''){
                        layer.msg(Info.E0036);   // 邮箱格式输入不对时，E0036=输入正确邮箱格式
                        return false;
                    }else if(!phoneReg.test(user.utel)&&user.utel!=''){
                        layer.msg(Info.E0037);   // 手机格式输入不对时，E0037=输入正确手机号码格式
                        return false;
                    }else if(user.upwd==""&&this.title=="添加用户"){
                        layer.msg(Info.E0026); 

                        return false;
                    }
                    var url = this.url;
                    axios.get(url,{
                        params: {
                            unum:user.unum,
                            uid:user.uid,
                            uname:user.uname,
                            udep:user.udep,
                            utel:user.utel,
                            uemail:user.uemail,
                            upwd:user.upwd,
                            urole:user.urole,
                            uupdatedatetime:user.uupdatedatetime,
                            ustatus:user.ustatus
                        }
                    }).then(function (response) {
                        if(_this.title=="添加用户"&&response.data.msg=="ok"){
                            window.hide();
                            vm.btn_search();
                            layer.msg(Info.I0008);
                        }else if(_this.title=="添加用户"&&response.data.msg=="fault"){
                            // 用户名已存在MSG
                            layer.msg(Info.E0027);
                        }else if(_this.title=="添加用户"&&response.data.msg=="E0031"){
                            // 状态未选择MSG
                            layer.msg(Info.E0031);
                        }else if(_this.title=="修改用户"&&response.data.msg=="fault"){
                        	 // 修改失败MSG
                            layer.msg(Info.I0013);
                        }else if(_this.title=="修改用户"&&response.data.msg=="checkFalse"){
                        	// 排他性MSG
                            layer.msg(Info.W0004);
                        }else if(_this.title=="修改用户"&&response.data.msg=="ok"){
                        	// 修改成功MSG
                            window.hide();
                            vm.btn_search();
                            _this.reset_all();
                            layer.msg(Info.I0012);
                        }
                    })
                        .catch(function (error) {
                            console.log(error);
                        });

                },
            getRoles:		//获取所有角色
                function () {
                    var _this = this;
                    axios.get('getRoles',{
                    })
                        .then(function (response) {

                            var data = response.data;
                            data.forEach(function (role) {
                                _this.roles.push(role);
									
								
                            })
								if(_this.roles.length==0){
									layer.msg(Info.I0026);
								}
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                }
        }

    })

</script>

</body>

</html>