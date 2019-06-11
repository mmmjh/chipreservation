<%@page import="com.fenjian.bean.User"%>
<%@page import="com.fenjian.bean.UsersStatus"%>
<%@page import="java.util.List"%>
<%@page import="com.fenjian.dao.*"%>
<%@page import="com.fenjian.bean.*"%>
<%@page import="com.fenjian.dao.UsersStatusDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>芯片使用日志</title>
</head>
<%
	String content = request.getParameter("content");
	if(content == null || "".equals(content)){
		content = "";		
	}
	int pageIndex = 1;
	int pageSize = 10;
	try{
		pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
	}catch(Exception e){
	}
	//获取登录用户
	User loginuser = (User)session.getAttribute("loginUser");
	RecordDaoImp usedao=new RecordDaoImp();
	//List<Record> chips = usedao.load(loginuser.getUsername(),content);
	Pager pager = usedao.load(loginuser.getUsername(),content, pageIndex, pageSize);
	//获得pager中保存的list集合
	List<Record> users = pager.getDatas();
	
%>
<body>
	    <%@include file="../inc/top.jsp" %>
<a href="index.jsp">预约芯片</a>	    
<a href="useChip.jsp">芯片预约情况</a>
<a href="useLog.jsp">芯片使用日志</a>	    
	    <h2 align="center" >芯片使用日志</h2>
	    <form action="useLog.jsp" method="post">
	<div align="center">
        芯片型号：
        <input type="text" name="content"   size="60"  />&nbsp;
        <input type="submit" value="搜索"/>
    </div>
    </form>
    
	<table align="center" border="1" width="700" style="margin-top:5px;">
		<td colspan="6">
				一共有<%=pager.getTotalRecord() %>条数据 , 每页显示<%=pager.getPageSize() %>条数据
				页码 : <%=pageIndex %> / <%=pager.getTotalPage() %>
			</td>
		<tr>
		  <td>序号</td>
			<td>流水号</td>
			<td>操作时间</td>
			<td>芯片类型</td>
			<td>操作类型</td>
			<td>预约/归还数量</td>
		
		</tr>
		<%
		int h=0;
			for( Record use : users ){
			h++;
		%>
		<tr>
		<td> <%=h%></td>
			<td> <%=use.getIdentifier() %></td>
			<td> <%=use.getThedate()%></td>
			<td> <%=use.getType()%></td>
			<td> <%=use.getOperate()%></td>
			<td> <%=use.getNumber()%></td>
		</tr>
		<%
			}
		%>
		<tr>
			<td colspan="6" align="center">
				<a href="useLog.jsp?pageIndex=1&content=<%=content %>">首页</a>
				
				<!-- 如果是第一页的话 , 就不显示 上一页 -->
				<%
					if(pageIndex > 1){
				%>
					<a href="useLog.jsp?pageIndex=<%=pageIndex-1 %>&content=<%=content %>">上一页</a>
				<%
					}
				%>
				<!-- 页码的显示 , 如果是当前页 , 就不加超链接, 只是显示就可以 , 其他都加上超链接 -->
				<%
					//先获得总页数,对他进行for循环遍历
					int totalPage = pager.getTotalPage();
					for(int i = 1 ; i <=totalPage ; i++){
						if(pageIndex == i){
				%>	
						<%=i %>
					<%
						}else{
					%>		
						<a href="useLog.jsp?pageIndex=<%=i%>&content=<%=content %>"><%=i %></a>
				<%
						}
					}	
				%>	
				<%
					if( pageIndex < totalPage){
				%>
					<a href="useLog.jsp?pageIndex=<%=pageIndex + 1 %>&content=<%=content %>">下一页</a>
				<%
					}
				%>
				<a href="useLog.jsp?pageIndex=<%=totalPage%>&content=<%=content %>">尾页</a>
			</td>
		
		
		
		</tr>
				</table>
</body>
</html>