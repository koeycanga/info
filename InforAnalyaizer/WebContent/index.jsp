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

<button v-on:click="search">search</button><br/>
 <input v-model="searchinfo"><br/>
 <button v-on:click="pre">上一页</button><br/>
 <button v-on:click="next">下一页</button><br/>
 {{pageNow}}/{{pageSize}}
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
</div>
<br/>
<div id="pagedv">
		<button v-on:click="pre2">上一页</button><br/>
 		<button v-on:click="next2">下一页</button><br/>
 		 {{pageNow}}/{{pageSize}}<br/>
 		 
 		 <select id="u420_input" v-model="rowNumber" v-on:change="schange">
              <option value="10">10</option>
              <option value="20">20</option>
              <option value="30">30</option>
              <option value="40">40</option>
              <option value="50">50</option>
            </select>
 		 <div v-if="isShow">加载中....</div>
</div>
</body>


<script type="text/javascript" src="js/vue.min.js"></script>
<script type="text/javascript" src="js/vue-resource.min.js"></script>
<script type="text/javascript" src="js/axios.min.js"></script>
<script type="text/javascript">

var vm = new Vue({
    el:'#app',
    data:{
    	searchinfo:"",
        msg:'Hello World!',
        sites:[],
        pageNow:1,
        pageSize:'',
        rowCount:0,
        rowNumber:3,
        isA:true,
        istrue: 0,
        items: [
            {name:'首页'},
            {name:'列表'},
            {name:'详情'}
        ]
    },
    
    methods:{

    	search:function(){
    		//alert(this.searchinfo);
    		this.pageNow = 1;
    		this.aget;
    	},
    	next:function(){
    		if(this.pageNow<this.pageSize){
    			this.isA = false;
    			this.pageNow +=1;
    			this.aget;
    		}
    	},
    	pre:function(){
    		if(this.pageNow>1){
    			this.pageNow -= 1;
    			this.aget;
    		}
    	}
    },
    
    computed:{
    	aget:function(){
    		var _this = this;
    		axios.get('koey/vue',{
    			params: {
    				searchinfo:this.searchinfo,
    				pageNow:this.pageNow,
    				rowNumber:this.rowNumber
    				}
    			})
    			.then(function (response) {
    				console.log(response.data);
    				var data = JSON.parse(response.data);
    				this.rowCount = data.rowCount;
    				_this.sites =  data.resdata;
    				_this.pageSize = data.pageSize;
    				pdv.pageSize = data.pageSize;
    			})
    			.catch(function (error) {
    			    console.log(error);
    			});
    		
    	},
    	apost:function(){
    		var _this = this;
    		let postData = this.$qs.stringify({
    			searchinfo:this.searchinfo
    		});
    		axios.post('koey/vue',{data:postData})
   			.then(function (response) {
   				_this.sites =  JSON.parse(response.data);
	   		})
   			.catch(function (error) {
	   			console.log(error);
	   		});
    	},
        get:function(){
            //发送get请求
            this.$http.get('koey/vue',{params:{searchinfo:this.searchinfo}}).then(function(res){
                this.sites =  JSON.parse(res.body);
            },function(){
                console.log('请求失败处理');
            });
        }
    }
});

var pdv = new Vue({
	
	 el:'#pagedv',
	 data:{
		 pageNow:1,
	     pageSize:'',
	     rowCount:0,
	     rowNumber:10,
	     isShow:false
	 },
	 methods:{
	     
	    	next2:function(){
	    		if(this.pageNow<this.pageSize){
	    			this.isShow = true;
	    			this.pageNow +=1;
	    			this.aget;
	    		}
	    	},
	    	pre2:function(){
	    		if(this.pageNow>1){
	    			this.isShow = false;
	    			this.pageNow -= 1;
	    			this.aget;
	    		}
	    	},
	    	schange:function(){
	    		
	    		this.pageNow = 1;
	    		this.aget;
	    	    //window.location.href="https://www.baidu.com";
	    	}
	    },
	    computed:{
	    	
	    	aget:function(){
	    		var _this = this;
	    		axios.get('koey/vue',{
	    			params: {
	    				searchinfo:this.searchinfo,
	    				pageNow:this.pageNow,
	    				rowNumber:this.rowNumber
	    				}
	    			})
	    			.then(function (response) {
	    				console.log(response.data);
	    				var data = JSON.parse(response.data);
	    				this.rowCount = data.rowCount;
	    				vm.sites =  data.resdata;
	    				_this.pageSize = data.pageSize;
	    			})
	    			.catch(function (error) {
	    			    console.log(error);
	    			});
	    		
	    	}
	    }
});

vm.search();

</script>

</html>