<%@page import="javax.swing.JOptionPane"%>
<%@page import="com.fenjian.dao.ReserveDaoImpl"%>
<%@page import="com.fenjian.dao.UsersStatusDaoImpl"%>
<%@page import="com.fenjian.bean.User"%>
<%@page import="java.util.List"%>
<%@page import="com.fenjian.bean.Chip"%>
<%@page import="com.fenjian.bean.*"%>
<%@page import="com.fenjian.dao.*"%>
<%@page import="java.util.Date"%>
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

// 精确到秒
SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//获取当前操作时间
SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddHHmmss");


User loginuser = (User)session.getAttribute("loginUser");
/*UsersStatusDaoImpl usedao=new UsersStatusDaoImpl();
List<UsersStatus> chips = usedao.load(loginuser.getUsername());*/

	String type = request.getParameter("type");
	int renumber = Integer.parseInt(request.getParameter("renumber"));
	try{
	UsersStatusDaoImpl useStdao=new UsersStatusDaoImpl();
	useStdao.update(type, renumber, loginuser.getUsername());//归还
	
	Record record=new Record();//写入流水单子
	record.setIdentifier("t_"+loginuser.getUsername()+"_"+df1.format(new Date()));
	record.setThedate(df.format(new Date()));
	record.setUsername(loginuser.getUsername());
	record.setType(type);
	record.setOperate("归还");
	record.setNumber(renumber);
	RecordDaoImp recdao=new RecordDaoImp();
    recdao.add(record);//成功插入数据库
     
     int flag = JOptionPane.showConfirmDialog(null,"归还成功！","提示信息",JOptionPane.CLOSED_OPTION);
	 	if(flag == 0){
	 		response.sendRedirect("useChip.jsp");
	 	}
	}catch(Exception e){
		int flag = JOptionPane.showConfirmDialog(null,"归还失败！\n"+e.getMessage(),"ERROR",JOptionPane.CLOSED_OPTION);
		if(flag == 0){
			response.sendRedirect("useChip.jsp");
		}
	}
%>

</body>
</html>