

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








