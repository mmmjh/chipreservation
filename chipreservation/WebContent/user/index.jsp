<%@page import="com.fenjian.bean.User"%>
<%@page import="com.fenjian.bean.Chip"%>
<%@page import="java.util.List"%>
<%@page import="com.fenjian.dao.ChipDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>芯片预约</title>
</head>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>芯片预约</title>
</head>
<%
	String content = request.getParameter("content");
	if(content == null || "".equals(content)){
		content = "";		
	}
	ChipDaoImpl chipDao = new ChipDaoImpl();
	List<Chip> chips = chipDao.load(content);
	
	//获取登录用户
	User loginuser = (User)session.getAttribute("loginUser");
%>
<body>
	    <%@include file="../inc/top.jsp" %>
<a href="index.jsp">预约芯片</a>	    
<a href="useChip.jsp">芯片预约情况</a>
<a href="useLog.jsp">芯片使用日志</a>
	    
	    <h2 align="center" >预约芯片</h2>
	    <form action="index.jsp" method="post">
	<div align="center">
        芯片型号：
        <input type="text" name="content" size="60" />&nbsp;
        <input type="submit" value="搜索"/>
    </div>
    </form>
    
	<table align="center" border="1" width="700" style="margin-top:5px;">
		<!-- <tr>
			<form action="index.jsp" method="post">
				<td colspan="8">
					请输入用户名或昵称:&nbsp;
					<input type="text" name="content" size="60" />&nbsp;
					<input type="submit" value="搜索"/>
				</td>
			</form>
		</tr> -->
		<tr>
			<td>芯片型号</td>
			<td>数量</td>
			<td colspan="2">操作</td>
		</tr>
		<%
			for( Chip chip : chips ){
		%>
		<tr>
			<td> <%=chip.getType() %></td>
			<td> <%=chip.getAmount() %></td>
			<td> <a href="reserveInput.jsp?type=<%=chip.getType() %>" >预约</a>
			</td>
			
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>