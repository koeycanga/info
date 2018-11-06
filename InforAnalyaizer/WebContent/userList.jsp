<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html xmlns:v-on="http://www.w3.org/1999/xhtml" xmlns:v-bind="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="UTF-8">
    <title></title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.8/vue.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue-resource@1.5.0"></script>
        <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/cy_CIAS_style.css">
    <script type="text/javascript" src="${ctx}/resource/layui/layer/layer.js"></script>
        <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<style>
   [v-cloak]{
       display: none;    //配合样式使用
   }
</style>
</head>
<body>
<div id="userInfo" v-cloak>
<div class="cy_hidebg" id="cy_hidebg"></div>
<div class="cy_CMICBMS_bodybg">
		<p>用户管理 / <span>账号管理</span></p>
<div class="cy_CMICBMS_tablebox">
    <form @submit.prevent>
		<div class="cy_CMICBMS_box07">

                <select v-model="userInfoVo2.ustatus">
                    <option value="2">账号状态</option>
                    <option value="1">启用</option>
                    <option value="0">停用</option>
                </select>
			<div class="cy_CMICBMS_schbox01">
                <input type="text" placeholder="请输入用户名检索" v-model="userInfoVo2.uid">
			</div>
			<input type="button" class="cy_CMICBMS_schbtn" value="检索" v-on:click="btn_search">

		</div>
    </form>
		<div class="cy_CMICBMS_box08">
			<input type="button" class="cy_CMICBMS_addbtn" value="新增" v-on:click="addUser">
			<input type="button" class="cy_CMICBMS_edbtn" value="编辑" v-on:click="updateUser">
			<input type="button" class="cy_CMICBMS_dltbtn" value="删除" v-on:click="deleteUser">
		</div>
		<div class="cy_CMICBMS_box08">
	<table width="100%" border="0" class="cy_CMICBMS_accmngtb" 	cellspacing="0">
  	<tbody>
    <tr>
      <th scope="col"><input type="checkbox"  v-model='isok' v-on:click='checkedAll'/></th>
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
      <td ><input type="checkbox" :value="userInfoVo.unum" v-model="checkedId"></td>
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
<script>
var ic_pager = {
        template:'<div class="cy_CMICBMS_umpage">'+
        '<div class="cy_CMICBMS_umrc">显示第{{pageBean.row_show_start}}到{{pageBean.row_show_end}}条记录，总共{{pageBean.jr_rowCount}}条记录</div>'+
        '<div class="cy_CMICBMS_umpg">'+
        '<div>'+
        '<ul>'+
        '<li><a href="#" v-on:click="showFirstPage($event)"><span><<</span></a></li>'+
        '<li><a href="#" v-on:click="showPagePre($event)"><span><</span></a></li>'+
        '<li v-for="ys in ysdatas"><a href="#" v-on:click="showPage(ys,$event)" v-bind:class="{cy_CMICBMS_umpgfc:ys==pageBean.pageNow}" v-text="ys"></a></li>'+
        '<li><a href="#" v-on:click="showPageNext($event)">></a></li>'+
        '<li><a href="#" v-on:click="showLastPage($event)">>></a></li>'+
        '</ul>'+
        '</div>'+
        '</div></div></div>',
        data:function(){
            return {
                pageBean:{
                    pageNow:1,              //当前第几页
                    pageSize:10,            //总页数
                    rowCount:0,             //总条目数
                    rowSize:[10,20,30,40],              //每页显示条目数
                    jr_rowCount:0  ,        //金融计数法的总条目数
                    row_show_start:0,       //从第几条开始显示
                    row_show_end:0,         //显示到第几条
                    rs_selected:10,          //选中的是哪一条  每页显示条目数
                    jump_page:1              //跳转框的页数
                },
                ysdatas : [],
                max_length:6                //有多少个页面框框
            }
        },
        methods:{
            showPage:function(index,event){
                event.preventDefault();
                if(index!='...'){
                    this.pageBean.pageNow = index;
                    this.pageBean.jump_page = index;
                    this.$parent.search(this.pageBean);               //调用父组件的search函数  父组件的computed中需有同名函数
                }
            },
            showFirstPage:function(event){

                if(this.pageBean.pageNow>1){
                    this.showPage(1,event);
                }else{
                    event.preventDefault();
                }
            },
            showLastPage:function(event){
                if(this.pageBean.pageNow<this.pageBean.pageSize){
                    this.showPage(this.pageBean.pageSize,event);
                }else{
                    event.preventDefault();
                }
            },
            showPagePre:function(event){
                if(this.pageBean.pageNow>1){
                    this.pageBean.pageNow-=1;
                    this.showPage(this.pageBean.pageNow,event);
                }else{
                    event.preventDefault();
                }
            },
            showPageNext:function(event){
                if(this.pageBean.pageNow<this.pageBean.pageSize){
                    this.pageBean.pageNow+=1;
                    this.showPage(this.pageBean.pageNow,event);
                }else{
                    event.preventDefault();
                }
            },
            showJumpPage:function(event){
                if(this.pageBean.jump_page>=1&&this.pageBean.jump_page<=this.pageBean.pageSize){
                    this.showPage(parseInt(this.pageBean.jump_page),event);
                }else{
                    event.preventDefault();
                }
            },
            schange:function(event){
                this.pageBean.pageSize = parseInt(Math.ceil(this.pageBean.rowCount / this.pageBean.rs_selected)) ;
                this.showPage(1,event);
            },
            dealAfterSearch:function(rowCount){
                this.pageBean.rowCount = parseInt(rowCount);
                this.pageBean.jr_rowCount = rowCount.replace(/[0-9]+?(?=(?:([0-9]{3}))+$)/g,function(a){return a+','});
                this.pageBean.pageSize =  parseInt(Math.ceil(this.pageBean.rowCount / this.pageBean.rs_selected));
                this.pageBean.row_show_start = (this.pageBean.pageNow-1)*this.pageBean.rs_selected+1;
                this.pageBean.row_show_end = this.pageBean.pageNow*this.pageBean.rs_selected;
                if(this.pageBean.row_show_end>this.pageBean.rowCount){
                    this.pageBean.row_show_end = this.pageBean.rowCount;
                }
				
                this.ysdatas = [];
                if(this.pageBean.pageSize<=this.max_length){
                    for(var i=1;i<=this.pageBean.pageSize;i++){
                        this.ysdatas.push(i);
                    }
                }else if(this.pageBean.pageSize - this.pageBean.pageNow<=this.max_length-1){
                    var start = this.pageBean.pageSize - this.max_length + 1;
                    for(var i=start;i<=this.pageBean.pageSize;i++){
                        this.ysdatas.push(i);
                    }
                }else{
                    var lg = this.pageBean.pageNow + (this.max_length-3);
                    for(var i=this.pageBean.pageNow;i<=lg;i++){
                        this.ysdatas.push(i);
                    }
                    this.ysdatas.push('...');
                    this.ysdatas.push(this.pageBean.pageSize);
                }
            }

        }
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
                        var addLayer = layer.open({
                            type: 2,
                            title: '新建用户',
                            shadeClose: true,
                            shade: 0.8,
                            area: ['380px', '50%'],
                         content: 'toAddUser' //iframe的url
                        });
                    },
                updateUser:                         //修改弹出
                    function () {
                        var checkedId = [];
                        checkedId= this.checkedId;
                        if (checkedId.length!=1){
                            layer.msg('请选择单条信息');
                        }else{
                            var unum = checkedId[0];
                            var updateLayer = layer.open({
                                type: 2,
                                title: '编辑用户',
                                shadeClose: true,
                                shade: 0.8,
                                area: ['380px', '90%'],
                                content: 'toUpdateUser?unum='+unum //iframe的url
                            });
                        }
                    },
                deleteUser:                     //删除提示
                    function () {
                		var _this = this;
                        if(this.checkedId==null){
                            layer.msg('请选择信息');
                        }else{
                            var deleteLayer =layer.confirm('确定要删除吗？', {
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
                                        layer.msg('删除成功', {icon: 1});
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
                    _this.$refs.pagecomponent.dealAfterSearch(100+"");
                	
                }
            	}
            }
        });
    
</script>

</body>

</html>