<%@page import="com.fenjian.bean.User"%>
<%@page import="com.fenjian.bean.UsersStatus"%>
<%@page import="java.util.List"%>
<%@page import="com.fenjian.dao.UsersStatusDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	//获取登录用户
	User loginuser = (User)session.getAttribute("loginUser");
	UsersStatusDaoImpl usedao=new UsersStatusDaoImpl();
	List<UsersStatus> chips = usedao.load(loginuser.getUsername(),content);
	
%>
<body>
	    <%@include file="../inc/top.jsp" %>
<a href="index.jsp">预约芯片</a>	    
<a href="useChip.jsp">芯片预约情况</a>
<a href="useLog.jsp">芯片使用日志</a>	    
	    <h2 align="center" >芯片预约情况</h2>
<form action="#" method="post">
	<div align="center">
        芯片型号：
        <input type="text" name="content" size="60" />&nbsp;
        <input type="submit" value="搜索"/>
    </div>
    </form>
    
	<table align="center" border="1" width="700" style="margin-top:5px;">
		<tr>
			<td>芯片型号</td>
			<td>预约数量</td>
			<td>待还数量</td>
			<td colspan="2">操作</td>
		</tr>
		<%
			for( UsersStatus use : chips ){
		%>
		<tr>
			<td> <%=use.getType() %></td>
			<td> <%=use.getReservedamount()%></td>
			<td> <%=use.getNeedreturn()%></td>
			<td>
					 <a href="returnchip.jsp?type=<%=use.getType() %>&num=<%=use.getNeedreturn() %>" >归还</a>
			</td>
			
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>