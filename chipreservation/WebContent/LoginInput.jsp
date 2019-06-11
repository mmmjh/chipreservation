<%@page import="javax.swing.JOptionPane"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
</head>
<body>
<h2 align="center">用户登录界面</h2>
<hr width="100%" color="blue"  />
	<form action="Login.jsp" method="post">
		用户名称 : <input type="text" name="username" id="username"/><br>
		用户密码 : <input type="passwd" name="passwd" id="passwd"/><br>
		<input type="submit" value="用户登录" onclick="return validate();"/>
	</form>
</body>
</html>

<script>
function validate(){  
    var username = document.getElementById("username").value; 
    var passwd = document.getElementById("passwd").value;
    if(username == '' || username == null){
    	alert("用户名不能为空！");
    	return false;
    }else if(passwd == '' || passwd == null){
    	alert("密码不能为空！");
    	return false;
    }
 }  
</script>