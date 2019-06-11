<%@page import="com.fenjian.bean.Chip"%>
<%@page import="com.fenjian.dao.ChipDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<%
	String type = request.getParameter("type");
    int renum = Integer.parseInt(request.getParameter("num"));
    System.out.println(renum);
	ChipDaoImpl chipDao = new ChipDaoImpl();
	Chip chip = chipDao.load_amount(type);
%>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<%int mm=renum; %>
<script>
    $(function () {
    	var a=0;
        $("#renumber").on("input", function () {
        	a="<%=mm%>";
            //在输入框中打印输入的值
        	   if(parseInt($(this).val())<=0){
               	alert("预约数量必须大于0！请重新输入！")
               	document.getElementById('renumber').value="";
               }
        	   else if(parseInt($(this).val())>a){
            	alert("归还数量已超过最大预约数！请重新输入！")
            	document.getElementById('renumber').value="";
            }
        	   else{}
        });
    });
</script>

<%@include file="../inc/top.jsp" %>
<a href="index.jsp">预约芯片</a>	    
<a href="useChip.jsp">芯片预约情况</a>
<a href="useLog.jsp">芯片使用日志</a>	


		    <h2 align="center" >归还芯片</h2>
	
<form action="return.jsp" >
	<table>
		<tr>
			<td>您已选择的型号：<span><%=type %></span></td>
		</tr>
		<tr>
			<input type="hidden" name="type" value="<%=type %>" />
			<input type="hidden" name="amount" value="<%=chip.getAmount() %>" />
			<!-- 该框设置了仅可输入数字 -->
			<td>请输入归还数量：<input type="text" name="renumber"  id="renumber" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td>
		</tr>
		<tr>
			<td><input type="submit" value="确定" onclick="return validate();"/></td>
		</tr>
		
	</table>
</form>

</body>
</html>
<script>
function validate(){  
	var renumber = document.getElementById("renumber").value;
    if(renumber == '' || renumber == null){
    	alert("归还数量不能为空！");
    	return false;
    }
      
 }  
</script>
