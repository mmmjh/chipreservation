<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新个人信息</title>
<!--  更改个人信息，更改的是登陆的这个用户 -->
</head>

<body>
	<%@include file="../inc/top.jsp" %>
	<a href="index.jsp">预约芯片</a>	    
	<a href="useChip.jsp">芯片预约情况</a>
	<a href="useLog.jsp">芯片使用日志</a>		

	    <h2 align="center" >更新个人信息</h2>
<%
	String message = (String)request.getAttribute("message");
	if(message == null){
		message = "";
	}
	else
		JOptionPane.showConfirmDialog(null,message,"提示信息",JOptionPane.CLOSED_OPTION);

%>
	<form action="updateSelf.jsp" method="get">
		<table align="center" border="1" width="500">
			<tr>
				<td>用户名  </td>
				<td>
					<input type="text" readonly="readonly" name="username" value="<%=user.getUsername() %>" />
				</td>
			</tr>
			<tr>
    			<td><span style="color:red;">*</span>密码 </td>
    			<td>
    				<input type="password" name="passwd" id="passwd" />
    			</td>
    		</tr>
    		<tr align="center">
    			<td colspan="2">
    				<input type="submit" value="提交修改" onclick="return validate();"/>
    				<input type="reset" value="重置" />
    			</td>
    		</tr>
		</table>
	</form>
</body>
</html>

<script>
function validate(){  
    var passwd = document.getElementById("passwd").value;
    if(passwd == '' || passwd == null){
    	alert("密码不能为空！");
    	return false;
    }
      
 }  
</script>