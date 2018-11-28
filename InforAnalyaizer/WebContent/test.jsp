<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="layer/layer.js"></script>
	<script src="js/echarts.min.js"></script>
	<script src='js/echarts-wordcloud.min.js'></script>
	<script type="text/javascript" src="js/vue.min.js"></script>
	<script type="text/javascript" src="js/axios.min.js"></script>
	<script type="text/javascript" src="js/polyfill.min.js"></script>
	<script type="text/javascript" src="js/ic_components.js"></script>
	<script type="text/javascript" src="js/comm.js"></script>
</head>
<body>
        <style>
            html, body, #main {
                width: 100%;
                height: 100%;
                margin: 0;
            }
        </style>
        <div id="app">
        		<div id='main'></div>
       
                  <div class="residual" >
                 <chart :options="optoin" style="width:100%;height:100%"></chart>
                </div>
        </div>
    </body>
     <script>

     Vue.prototype.$echarts = echarts;       
     
     var c_t = {
    		 
    		 
    		 template:''
    		 
     };

     
            var app = new Vue({
            	el:"#app",
            	data:{
            		option : {
            		         tooltip: {},
            		         series: [ {
            		             type: 'wordCloud',
            		             gridSize: 2,
            		             sizeRange: [12, 50],
            		             rotationRange: [-90, 90],
            		             shape: 'pentagon',
            		             width: 314,
            		             height: 204,
            		             drawOutOfBound: true,
            		             textStyle: {
            		                 normal: {
            		                     color: function () {
            		                         return 'rgb(' + [
            		                             Math.round(Math.random() * 160),
            		                             Math.round(Math.random() * 160),
            		                             Math.round(Math.random() * 160)
            		                         ].join(',') + ')';
            		                     }
            		                 },
            		                 emphasis: {
            		                     shadowBlur: 10,
            		                     shadowColor: '#333'
            		                 }
            		             },
            		             data: [
            		                 {
            		                     name: '长江',
            		                     value: 10000,
            		                     textStyle: {
            		                         normal: {
            		                             color: 'black'
            		                         },
            		                         emphasis: {
            		                             color: 'red'
            		                         }
            		                     }
            		                 },
            		                 {
            		                     name: '跨越式',
            		                     value: 6181
            		                 },
            		                 {
            		                     name: '企业',
            		                     value: 4386
            		                 },
            		                 {
            		                     name: '公司',
            		                     value: 4055
            		                 },
            		                 {
            		                     name: '市场',
            		                     value: 2467
            		                 },
            		                 {
            		                     name: '亚洲',
            		                     value: 2244
            		                 },
            		                 {
            		                     name: '中国',
            		                     value: 1898
            		                 },
            		                 {
            		                     name: '长城',
            		                     value: 1484
            		                 },
            		                 {
            		                     name: '星球',
            		                     value: 1112
            		                 },
            		                 {
            		                     name: '美好山河',
            		                     value: 965
            		                 },
            		                 {
            		                     name: '利剑',
            		                     value: 847
            		                 },
            		                 {
            		                     name: '大海',
            		                     value: 582
            		                 },
            		                 {
            		                     name: '天穹',
            		                     value: 555
            		                 },
            		                 {
            		                     name: '速度',
            		                     value: 550
            		                 },
            		                 {
            		                     name: '蓝天',
            		                     value: 462
            		                 }
            		             ]
            		         } ]
            		     }
            		
            	},
            	monuted:function(){
            		  var chart = echarts.init(document.getElementById('main'));

                      var option = {
                          tooltip: {},
                          series: [ {
                              type: 'wordCloud',
                              gridSize: 2,
                              sizeRange: [12, 50],
                              rotationRange: [-90, 90],
                              shape: 'pentagon',
                              width: 314,
                              height: 204,
                              drawOutOfBound: true,
                              textStyle: {
                                  normal: {
                                      color: function () {
                                          return 'rgb(' + [
                                              Math.round(Math.random() * 160),
                                              Math.round(Math.random() * 160),
                                              Math.round(Math.random() * 160)
                                          ].join(',') + ')';
                                      }
                                  },
                                  emphasis: {
                                      shadowBlur: 10,
                                      shadowColor: '#333'
                                  }
                              },
                              data: [
                                  {
                                      name: '长江',
                                      value: 10000,
                                      textStyle: {
                                          normal: {
                                              color: 'black'
                                          },
                                          emphasis: {
                                              color: 'red'
                                          }
                                      }
                                  },
                                  {
                                      name: '跨越式',
                                      value: 6181
                                  },
                                  {
                                      name: '企业',
                                      value: 4386
                                  },
                                  {
                                      name: '公司',
                                      value: 4055
                                  },
                                  {
                                      name: '市场',
                                      value: 2467
                                  },
                                  {
                                      name: '亚洲',
                                      value: 2244
                                  },
                                  {
                                      name: '中国',
                                      value: 1898
                                  },
                                  {
                                      name: '长城',
                                      value: 1484
                                  },
                                  {
                                      name: '星球',
                                      value: 1112
                                  },
                                  {
                                      name: '美好山河',
                                      value: 965
                                  },
                                  {
                                      name: '利剑',
                                      value: 847
                                  },
                                  {
                                      name: '大海',
                                      value: 582
                                  },
                                  {
                                      name: '天穹',
                                      value: 555
                                  },
                                  {
                                      name: '速度',
                                      value: 550
                                  },
                                  {
                                      name: '蓝天',
                                      value: 462
                                  }
                              ]
                          } ]
                      };

                      chart.setOption(option);
                    
            	}
            });
     
          
        </script>
</html>