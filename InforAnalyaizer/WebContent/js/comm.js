﻿$(document).ready(function(){
	
	/*alert($(document).height());
	alert($(window).height());
	alert($(document.body).outerHeight(true));
	alert(document.body.scrollHeight);*/
	//alert(document.body.scrollHeight);
	//$(".cy_hidebg").css("height",($(document).height()+document.body.scrollHeight));
});

/*以下是根据屏幕大小做分辨率选择*/
function AdaptationResolution(url){
	var ic_m_screenWidth = window.screen.width;
	if(ic_m_screenWidth>1440){
		document.getElementById("lnk").href = url+"/css/cy_CIAS_style-1920_1080.css";
		$("#wcdv1").css("width","800px");
		$("#wcdv1").css("height","280px");
	}else if(ic_m_screenWidth>1366){
		document.getElementById("lnk").href = url+"/css/cy_CIAS_style-1440_900.css";
		$("#wcdv1").css("width","550px");
		$("#wcdv1").css("height","280px");
	}else if(ic_m_screenWidth>1200){
		document.getElementById("lnk").href = url+"/css/cy_CIAS_style-1366_768.css";
		$("#wcdv1").css("width","400px");
		$("#wcdv1").css("height","250px");
	}else if(ic_m_screenWidth>1024){
		document.getElementById("lnk").href = url+"/css/cy_CIAS_style-1200_800.css";
		$("#wcdv1").css("width","300px");
		$("#wcdv1").css("height","240px");
	}
}

   
/*处理带有{0},{1}这样参数的info, 替换{0}等为attrs中参数*/
function IC_GETINFOBYAttrs(info,attrs){
	for(var i=0;i<attrs.length;i++){
		info = info.replace("{"+i+"}",attrs[i]);
	}
	return info;
}

/*根据arr生成JSON字符串*/
function createJSON(arr){
	 var json = '[';
	  for(var i=0;i<arr.length;i++){
		  if(i==0){
			  json+='{"id":"'+arr[i]+'"}';
		  }else{
			  json+=',{"id":"'+arr[i]+'"}';
		  }
	  }
	  json+=']';
	  return json;
}


//中文标点符号转为英文标点符号
function repSign(s) {
	s = s.replace(/([\u4E00-\u9FA5]|^|\n|\r)([\,\.\?\!])(?=[\u4E00-\u9FA5]|$|\n|\r)/g,function(u,v,w,x) {
		sign = {
			',': '，',
			'.': '。',
			'?': '？',
			'!': '！'
		};
		return sign[w] ? v + sign[w] : u;
	});
	return s;
}

//阿拉伯数字转为大小汉字
function intToChinese ( str ) {
	 str = str+'';
	 var len = str.length-1;
	 var idxs = ['','十','百','千','万','十','百','千','亿','十','百','千','万','十','百','千','亿'];
	 var num = ['零','一','二','三','四','五','六','七','八','九'];
	 return str.replace(/([1-9]|0+)/g,function( $, $1, idx, full) {
	  var pos = 0;
	  if( $1[0] != '0' ){
	   pos = len-idx;
	   if( idx == 0 && $1[0] == 1 && idxs[len-idx] == '十'){
	    return idxs[len-idx];
	   }
	   return num[$1[0]] + idxs[len-idx];
	  } else {
	   var left = len - idx;
	   var right = len - idx + $1.length;
	   if( Math.floor(right/4) - Math.floor(left/4) > 0 ){
	    pos = left - left%4;
	   }
	   if( pos ){
	    return idxs[pos] + num[$1[0]];
	   } else if( idx + $1.length >= len ){
	    return '';
	   }else {
	    return num[$1[0]]
	   }
	  }
	 });
	}


//比较fromDate和toDate日期的先后，如果fromDate比toDate晚返回true   否则返回 false
function IC_compareDate(fromDate,toDate){
	var fds = fromDate.split(" ");
	var tds = toDate.split(" ");
	
	var fds_date = fds[0].split("-");
	var tds_date = tds[0].split("-");

	if(fds_date[0]>tds_date[0]){
		return true;
	}
	if(fds_date[0]==tds_date[0]&&fds_date[1]>tds_date[1]){
		return true;
	}
	if(fds_date[0]==tds_date[0]&&fds_date[1]==tds_date[1]&&fds_date[2]>tds_date[2]){
		return true;
	}
	
	if(fds[0]==tds[0]&&fds[1]>tds[1]){
		return true;
	}
	return false;
}




