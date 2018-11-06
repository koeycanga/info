<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script>
function return_tologin(){
	if (self != top) { 
		//alert('在iframe中');
		window.parent.return_tologin();
	}else{
		window.location.href="../login.jsp";
	}
}

alert("登录超时,请重新登录");
return_tologin();

</script>
</head>
<body>
<!--  登录超时，请<a href="#" onclick="return_tologin()">返回</a>重新登录... -->
</body>
</html>