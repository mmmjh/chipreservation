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
	ChipDaoImpl chipDao = new ChipDaoImpl();
	Chip chip = chipDao.load_amount(type);
%>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<%int mm=chip.getAmount() ; %>
<script>
    $(function () {
    	var a=0;
        $("#number").on("input", function () {
        	a="<%=mm%>";
        	if(parseInt($(this).val())<=0){
            	alert("预约数量必须大于0！请重新输入！")
            	document.getElementById('number').value="";
            }
            //在输入框中打印输入的值
            else if(parseInt($(this).val())>a){
            	alert("预约数量已超过最大限额！请重新输入！")
            	document.getElementById('number').value="";
            }
            else{}
           
        });
    });
</script>
	    <%@include file="../inc/top.jsp" %>
<a href="index.jsp">预约芯片</a>	    
<a href="useChip.jsp">芯片预约情况</a>
<a href="useLog.jsp">芯片使用日志</a>	

	    <h2 align="center" >预约芯片</h2>

<form action="reserve.jsp"  id="myForm">
<table>
	<tr>
		<td>您已选择的型号：<span><%=type %></span></td>
	</tr>
	<tr>
		<input type="hidden" name="type" value="<%=type %>" />
		<input type="hidden" name="amount" value="<%=chip.getAmount() %>" />
		<!-- 该框设置了仅可输入数字 -->
		<td>请选择预约数量：<input type="text" name="number"  id="number" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td>
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
	var number = document.getElementById("number").value;
    if(number == '' || number == null){
    	alert("预约数量不能为空！");
    	return false;
    }
      
 }  
</script>
