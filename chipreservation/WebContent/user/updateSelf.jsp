
<%@page import="javax.swing.JOptionPane"%>
<%@page import="com.fenjian.util.UserException"%>
<%@page import="com.fenjian.bean.User"%>
<%@page import="com.fenjian.dao.UserDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//获取客户端传递过来的参数
	//获取登录用户
	User loginuser = (User)session.getAttribute("loginUser");	
	String passwd = request.getParameter("passwd");
	
	UserDaoImpl userDao = new UserDaoImpl();
	try{
	User user = userDao.load_edit(loginuser.getUsername());
	
	user.setPasswd(passwd);
	userDao.update(user);
	
	int flag = JOptionPane.showConfirmDialog(null,"信息修改成功！请重新登录系统","提示信息",JOptionPane.CLOSED_OPTION);
	if(flag == 0){
		response.sendRedirect("../LoginInput.jsp");
	}
	}catch(UserException e){
 		
 		
		request.setAttribute("message", e.getMessage());
%>
	<jsp:forward page="updateSelfInput.jsp"></jsp:forward>

<%
 	}
%>








