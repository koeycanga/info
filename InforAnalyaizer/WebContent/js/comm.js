﻿

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









