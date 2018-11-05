<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
.class-a{
    background: lightpink;
 }
 .class-b{
    background: #eee;
 }
  .active {
        color: #f00
    }
    ul li {
        float: left;
        padding: 10px
    }
</style>
</head>
<body>
sdasdas

<img src="image/a.jpg"/>

<div id="app">
{{msg}}

<table border="1">
        <tr v-for="site in sites">
			<td>{{site.id}}</td>
			<td>{{site.name}}</td>
		</tr>
	</table>

<div  v-bind:class="{'class-a':isA,'class-b':!isA}">载入中....</div>

 <ul>
        <li v-for='(item,index) in items' :class="{active:istrue==index}" @click="istrue=index">
            {{ item.name }}
        </li>
   </ul>
   <br/>
   <pager ref="pagecomponent"></pager>  <!-- ref 固定为 pagecomponent  -->
</div>

</body>


<script type="text/javascript" src="js/vue.min.js"></script>
<script type="text/javascript" src="js/vue-resource.min.js"></script>
<script type="text/javascript" src="js/axios.min.js"></script>
<script type="text/javascript">

var pager = {
        template:'<div><select v-model="pageBean.rs_selected" v-on:change="schange"><option v-bind:value="rsize" v-for="rsize in pageBean.rowSize">{{rsize}}</option></select>'+
		 
		 '{{pageBean.pageNow}}/{{pageBean.pageSize}}'+
		 '<button v-on:click="showFirstPage()">首页</button>'+
		 '<button v-on:click="showPagePre()">上一页</button>'+
		 '<button v-on:click="showPageNext()">下一页</button>'+
		 '<button v-on:click="showLastPage()">尾页</button>'+
		 '第<input type="text" v-model="pageBean.jump_page"/>页<button v-on:click="showJumpPage()">转到</button>'+
		 '</div>',
		 data:function(){
		     return {
				 pageBean:{
				     pageNow:1,              //当前第几页
					 pageSize:10,            //总页数
					 rowCount:0,             //总条目数
					 rowSize:[10,20,30,40],              //每页显示条目数
					 rs_selected:10,          //选中的是哪一条  每页显示条目数
					 jump_page:1              //跳转框的页数
				 }
			 }
		 },
		 methods:{
		     showPage:function(index){
			     this.pageBean.pageNow = index;
				 this.pageBean.jump_page = index;
				 this.$parent.search(this.pageBean);
			 },
			 showFirstPage:function(){
			      if(this.pageBean.pageNow>1){
				     this.showPage(1);
				  }
			 },
		     showLastPage:function(){
				 if(this.pageBean.pageNow<this.pageBean.pageSize){
			         this.showPage(this.pageBean.pageSize);
				 }
			 },
			 showPagePre:function(){
			     if(this.pageBean.pageNow>1){
				     this.pageBean.pageNow-=1;
					 this.showPage(this.pageBean.pageNow);
				 }
			 },
			 showPageNext:function(){
			    if(this.pageBean.pageNow<this.pageBean.pageSize){
				     this.pageBean.pageNow+=1;
					 this.showPage(this.pageBean.pageNow);
				}
			 },
			 showJumpPage:function(){
			     if(this.pageBean.jump_page>=1&&this.pageBean.jump_page<=this.pageBean.pageSize){
				     this.showPage(parseInt(this.pageBean.jump_page));
				 }
			 },
			 schange:function(){
			    this.pageBean.pageSize = parseInt(Math.ceil(this.pageBean.rowCount / this.pageBean.rs_selected)) ;
			    this.showPage(1);
			 }
		 }
  };

var vm = new Vue({
    el:'#app',
    data:{
    	searchinfo:"",
        msg:'Hello World!',
        sites:[],
        isA:true,
        istrue: 0,
        items: [
            {name:'首页'},
            {name:'列表'},
            {name:'详情'}
        ]
    },
    components:{
	     'pager':pager
	  },
	  computed:{
		  
		search:function(){
	        return function(pageBean){
	        	var _this = this;
	        	var data;
	    		axios.get('koey/vue',{
	    			params: {
	    				searchinfo:this.searchinfo,
	    				pageNow:pageBean.pageNow,
	    				rowNumber:pageBean.rs_selected
	    				}
	    			})
	    			.then(function (response) {
	    				 var data = JSON.parse(response.data);
	    				 pageBean.rowCount = data.rowCount;
	    				 pageBean.pageSize =  parseInt(Math.ceil(pageBean.rowCount / pageBean.rs_selected));
	    				
	    				  _this.sites =  data.resdata;
	    				
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




</script>

</html>