<%@page import="javax.swing.JOptionPane"%>
<%@page import="com.fenjian.util.UserException"%>
<%@page import="com.fenjian.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	User user =null;
	//获取session中保存的用户
	try{
		user = (User)session.getAttribute("loginUser");
		if(user == null){
			int flag = JOptionPane.showConfirmDialog(null,"请登录后操作！","提示信息",JOptionPane.CLOSED_OPTION);
		 	if(flag == 0){ //对话框点击确定后的操作
		 		response.sendRedirect("../LoginInput.jsp");
		 	}
		}else{
%>
				<div style="color:red ; text-align:right">
					欢迎[<%=user.getUsername() %> &nbsp; <%=user.getName() %>]使用该系统&nbsp;
					<a href="<%=request.getContextPath()%>/Logout.jsp">退出系统</a>&nbsp;
					<a href="<%=request.getContextPath()%>/user/updateSelfInput.jsp">更改个人信息</a>
				</div>
<%
		}
	}catch(UserException e){
		response.sendRedirect("../LoginInput.jsp");
		
	}
%>
