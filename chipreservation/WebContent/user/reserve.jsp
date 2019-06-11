<%@page import="javax.swing.JOptionPane"%>
<%@page import="com.fenjian.dao.ReserveDaoImpl"%>
<%@page import="com.fenjian.dao.UsersStatusDaoImpl"%>
<%@page import="com.fenjian.bean.User"%>
<%@page import="com.fenjian.bean.Chip"%>
<%@page import="java.util.Date"%>
<%@page import="com.fenjian.bean.*"%>
<%@page import="com.fenjian.dao.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.fenjian.dao.ChipDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<%
//精确到秒
SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//获取当前操作时间
SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddHHmmss");

	String type = request.getParameter("type");
	int number = Integer.parseInt(request.getParameter("number"));
	try{
		ChipDaoImpl chipDao = new ChipDaoImpl();
		Chip chip = chipDao.load_amount(type);
		
		//判断库存
		//获取登录用户
		User loginuser = (User)session.getAttribute("loginUser");
		
		//写入数据库
		ReserveDaoImpl reserveDao = new ReserveDaoImpl();
		if(number>0)
			reserveDao.reserveAdd(loginuser.getUsername(), type, number);
		Record record=new Record();//写入流水单子
		record.setIdentifier("t_"+loginuser.getUsername()+"_"+df1.format(new Date()));
		record.setThedate(df.format(new Date()));
		record.setUsername(loginuser.getUsername());
		record.setType(type);
		record.setOperate("预约");
		record.setNumber(number);
		RecordDaoImp recdao=new RecordDaoImp();
	     recdao.add(record);//成功插入数据库
	     
	     int flag = JOptionPane.showConfirmDialog(null,"预约成功！","提示信息",JOptionPane.CLOSED_OPTION);
	 	if(flag == 0){
	 		response.sendRedirect("index.jsp");
	 	}
	}catch(Exception e){
		int flag = JOptionPane.showConfirmDialog(null,"预约失败！\n"+e.getMessage(),"ERROR",JOptionPane.CLOSED_OPTION);
		if(flag == 0){
			response.sendRedirect("index.jsp");
		}
	}
%>


</body>
</html>