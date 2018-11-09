<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<fmt:setBundle basename="resources" var="sysInfo" />
<fmt:message key="front_menu" var="front_menu" bundle="${sysInfo}" />
<fmt:message key="E000000006" var="E000000006" bundle="${sysInfo}" />
<fmt:message key="E0019" var="E0019" bundle="${sysInfo}" />
<fmt:message key="W000000002" var="W000000002" bundle="${sysInfo}" />
<fmt:message key="I0014" var="I0014" bundle="${sysInfo}" />   
<fmt:message key="E0026" var="E0026" bundle="${sysInfo}" />
<fmt:message key="E000000004" var="E000000004" bundle="${sysInfo}" />
<fmt:message key="I000000008" var="I000000008" bundle="${sysInfo}" />
<fmt:message key="E0027" var="E0027" bundle="${sysInfo}" />
<fmt:message key="I0013" var="I0013" bundle="${sysInfo}" />
<fmt:message key="I0012" var="I0012" bundle="${sysInfo}" />

<html xmlns:v-on="http://www.w3.org/1999/xhtml"
	xmlns:v-bind="http://www.w3.org/1999/xhtml">

<head>
<meta charset="UTF-8">
<title></title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="${ctx}/js/vue.min.js"></script>
<script src="${ctx}/js/vue-resource.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/cy_CIAS_style.css">
<script type="text/javascript" src="${ctx}/layer/layer.js"></script>
<script src="${ctx}/js/axios.min.js"></script>
<script type="text/javascript" src="${ctx}/js/polyfill.min.js"></script>
<style>
[v-cloak] {
	display: none;
					/* 配合样式使用 */
}
</style>
</head>
<body>
	<div id="userInfo" v-cloak>
		<div class="cy_hidebg" id="cy_hidebg"></div>
		<div class="cy_CMICBMS_bodybg">
			<p>
				用户管理 / <span>账号管理</span>
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
							<input type="text" placeholder="请输入用户名检索"
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
								<th scope="col"><input type="checkbox" v-model='isok'
									v-on:click='checkedAll' /></th>
								<th scope="col">id</th>
								<th scope="col">用户名</th>
								<th scope="col">用户角色</th>
								<th scope="col">姓名</th>
								<th scope="col">职位</th>
								<th scope="col">手机号</th>
								<th scope="col">邮箱</th>
								<th scope="col" style="text-align: center">状态</th>
							</tr>
							<tr class="cy_CMICBMS_treven" v-for="userInfoVo in users">
								<td><input type="checkbox" :value="userInfoVo.unum"
									v-model="checkedId"></td>
								<td>{{userInfoVo.unum}}</td>
								<td>{{userInfoVo.uid}}</td>
								<td>{{userInfoVo.urole}}</td>
								<td>{{userInfoVo.uname}}</td>
								<td>{{userInfoVo.udep}}</td>
								<td>{{userInfoVo.utel}}</td>
								<td>{{userInfoVo.uemail}}</td>
								<td>{{userInfoVo.ustatus==1?"启用":"停用"}}</td>
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
					<span>*</span>用户角色：<select v-model="userInfoVo.urole" ><option
							:value="role.userRole_ID" v-for="role in roles">{{role.userRoleName}}</option></select>
				</div>
				<div>
					<span>*</span>用户名：<input type="text" placeholder="输入用户名"
						class="cy_CMICBMS_addinput" v-model="userInfoVo.uid" :disabled="is_disabled">
					<div class="cy_CMICBMS_errortip" v-if="userInfoVo.uid==''">请输入用户名！</div>
				</div>
				<div>
					<span>*</span>设置密码：<input type="password" placeholder="设置密码"
						class="cy_CMICBMS_addinput" v-model="userInfoVo.upwd">
					<div class="cy_CMICBMS_errortip" v-if="userInfoVo.upwd==''">请输入设置密码！</div>
				</div>
				<div>
					<span>*</span>确认密码：<input type="password" placeholder="确认密码"
						class="cy_CMICBMS_addinput" v-model="checkPwd">
					<div class="cy_CMICBMS_errortip" v-if="checkPwd==''">请确认密码！</div>
				</div>
				<div>
					<span>*</span>姓名：<input type="text" placeholder="输入姓名"
						class="cy_CMICBMS_addinput" v-model="userInfoVo.uname">
					<div class="cy_CMICBMS_errortip" v-if="userInfoVo.uname==''">请输入姓名！</div>
				</div>
				<div>
					所属部门：<input type="text" placeholder="所属部门"
						class="cy_CMICBMS_addinput" v-model="userInfoVo.udep">
				</div>
				<div>
					手机号码：<input type="text" placeholder="输入手机号"
						class="cy_CMICBMS_addinput" v-model="userInfoVo.utel">
				</div>
				<div>
					邮箱地址：<input type="text" placeholder="输入邮箱地址"
						class="cy_CMICBMS_addinput" v-model="userInfoVo.uemail">
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
	<script type="text/javascript" src="${ctx}/js/ic_components.js"></script>
	
	<script>
	var Info = {
			E000000006:'${E000000006}',
			E0019:'${E0019}',
			W000000002:'${W000000002}',
			I0014:'${I0014}',
			E0026:'${E0026}',
			E000000004:'${E000000004}',
			I000000008:'${I000000008}',
			E0027:'${E0027}',
			I0013:'${I0013}',
			I0012:'${I0012}'
	};
        var vm = new Vue({
            el: '#userInfo',
            data: {
                userInfoVo2: {ustatus:2},
                users: [],
                isok: false,
                checkedId: [],
            },
            mounted: function () {
                this.btn_search();
            },
            components:{
                'pager':ic_pager
            },
            methods: {
                checkedAll:
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
                btn_search:
                	function () {
                	this.search(this.$refs.pagecomponent.pageBean);
                	
                },
                
                addUser:                                        //添加弹出
                    function () {
                		vm2.title="添加用户";
                		vm2.url="addUser";
                		vm2.userInfoVo={uid:'',uname:'',udep:'',utel:'',uemail:'',upwd:''};
                		vm2.userInfoVo.unum=null;
                		vm2.checkPwd="";
                		vm2.is_disabled = false;
                		var hideobj=document.getElementById("cy_hidebg");
                	   	cy_hidebg.style.display="block";  //显示隐藏层
                	   	document.getElementById("cy_CMICBMS_add").style.display="block";  //显示弹出层

                    },
                updateUser:                         //修改弹出
                    function () {
                        var checkedId = [];
                        vm2.userInfoVo.upwd="";
                        checkedId= this.checkedId;
                        if (checkedId.length!=1){
                            layer.msg(Info.E000000006);
                        }else{
                        	vm2.title="修改用户";
                        	vm2.url="updateUser";
                        	vm2.is_disabled = true;
                        	axios.get("queryOne",{
                                params: {
                                	unum:checkedId[0] 
                                }
                            }).then(function (res) {
    							vm2.userInfoVo=res.data;
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
                        if(this.checkedId==null){
                            layer.msg(Info.E0019);
                        }else{
                            var deleteLayer =layer.confirm(Info.W000000002, {
                                btn: ['确定', '取消'] //按钮
                            }, function () {
                                var url = "delete";
                                var option = {emulateJSON: true};
                                var checkedId = [];
                                _this.checkedId.forEach(function (id) {
                                    checkedId.push(id);
                                }, this);
                                $.ajax({
                                    url:url,
                                    data:{"checkedId":checkedId},
                                    dateType:"json",
                                    type:"post",
                                    traditional:true,
                                    success:function(){
                                        layer.msg(Info.I0014, {icon: 1});
                                        _this.btn_search()
                                    }
                                })
                            });
                        }
                    }
            },
            watch: {
                "checkedId": function() {
                    if (this.checkedId.length != this.users.length) {
                        this.isok = false
                    } else {
                        this.isok = true
                    }
                }
            },

            computed:{
              	search:function(){		
            		return function (pageBean) {
                	var _this = this;
                    var url = "../user/queryAll";
                    var user = this.userInfoVo2;
                    axios.get(url,{
                        params: {
                        	uid:user.uid,
                        	ustatus:user.ustatus, 
                            pageNow:pageBean.pageNow,
                            rowSize:pageBean.rs_selected
                        }
                    })
                        .then(function (response) {
							var rowCount = response.data.rowCount+"";
							var allUsers = response.data.users;
                            _this.$refs.pagecomponent.dealAfterSearch(rowCount); //rowCount为总条目数  在ajax请求返回函数需调用该方法
                            _this.users =allUsers;
                            
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
        }
        
        //+++++++++++++++++++++++++++添加隐藏层++++++++++++++++++++++++
        var vm2 = new Vue({
            el: '#addform',
            data: {
                userInfoVo:{uid:'',uname:'',udep:'',utel:'',uemail:'',upwd:''},
                checkPwd:"",
                roles:[],
                title:"",
                is_disabled:false,
                url:""
            },
            created: function () {
                this.getRoles();
            },
            methods: {
                submit:
                    function () {
                		var _this = this;
                        var user = this.userInfoVo;
                		if(user.urole==""||user.uid==""||user.uname==""||user.urole==""){
                			layer.msg(Info.E0026);
                			return false;
                		}else if(this.checkPwd!=user.upwd){
                			layer.msg(Info.E000000004);
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
	                            	ustatus:user.ustatus 
	                            }
	                        }).then(function (response) {
									if(_this.title=="添加用户"&&response.data.msg=="ok"){
										window.hide();
										vm.btn_search();
										layer.msg(Info.I000000008);
									}else if(_this.title=="添加用户"&&response.data.msg=="fault"){
										layer.msg(Info.E0027);
									}else if(_this.title=="修改用户"&&response.data.msg=="fault"){
										layer.msg(Info.I0013);
									}else if(_this.title=="修改用户"&&response.data.msg=="ok"){
										window.hide();
										vm.btn_search();
										layer.msg(Info.I0012);
									}
	                            })
	                            .catch(function (error) {
	                                console.log(error);
	                            });
	                        
	                		
						
                		
                    },
                getRoles:
                    function () {
                		var _this = this; 
                        axios.get('getRoles',{
                        })
                            .then(function (response) {

                                var data = response.data;
                                data.forEach(function (role) {
                                	_this.roles.push(role);

                                })
                            })
                            .catch(function (error) {
                                console.log(error);
                            });
                    }
            },
            
        })
        
</script>

</body>

</html>