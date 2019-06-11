<%@page import="javax.swing.JOptionPane"%>
<%@page import="com.fenjian.dao.UserDaoImpl"%>
<%@page import="com.fenjian.dao.IUserDao"%>
<%@page import="com.fenjian.bean.User"%>
<%@page import="com.fenjian.util.UserException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//获取客户端传递过来的参数
	String username = request.getParameter("username");
	String passwd = request.getParameter("passwd");
	try{
		UserDaoImpl userDao = new UserDaoImpl();
		User user = userDao.load(username,passwd);
		
	session.setAttribute("loginUser", user);
	if(user.getUsertype() == 1){//管理员用户
		response.sendRedirect(request.getContextPath() +"/admin/chipList.jsp");
	}else{//普通用户
		response.sendRedirect(request.getContextPath() +"/user/index.jsp");
	}
	}catch(UserException e){
		//验证出错
		request.setAttribute("message", e.getMessage());
		
		int flag = JOptionPane.showConfirmDialog(null,e.getMessage(),"提示信息",JOptionPane.CLOSED_OPTION);
	 	if(flag == 0){ //对话框点击确定后的操作
	 		response.sendRedirect("LoginInput.jsp");
	 	}
	}
%>