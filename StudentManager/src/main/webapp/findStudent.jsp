<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.io.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
ul
{
list-style-type:none;
margin:0;
padding:0;
}
li
{
display:inline;
}
</style>
<script type="text/javascript">
    function jump(){
    	var page = document.getElementById("page1").value;
    	//request.setAttribute("page",page);
        self.location= "jump.do?page="+page;   	
    }
    
</script>
</head>
<body>
     <h1 align="center">学生管理系统</h1>
     <table align = "center" border="1" cellspacing=0 cellpadding=10 >
        <tr>
            <a href="add.jsp" style="margin-left:950px">新增</a>
            <td align = "center">id</td>
            <td align = "center">name</td>
            <td align = "center">birthday</td>
            <td align = "center">description</td>
            <td align = "center">avgscore</td>
            <td>修改</td>
            <td>删除</td>
        </tr>
     <c:forEach items="${list}" var="stu" varStatus="status">
		<tr> 
			 <td align = "center">${stu.id}</td>
			 <td align = "center">${stu.name}</td>			 
			 <td align = "center">${stu.birthday}</td>
			 <td align = "center">${stu.description}</td>
			 <td align = "center">${stu.avgscore}</td> 
			 <td><a href="update.jsp?id=${stu.id}&name=${stu.name}&birthday=${stu.birthday}&description=${stu.description}&avgscore=${stu.avgscore}">修改</a></td>
             <td><a href="delete.do?id=${stu.id}">删除</a></td>
		 </tr>
</c:forEach>
    </table>
        <ul align = "center">
            <li><a href="FindStudentServlet.do">首页</a></li>
            <li><a href="up.do?page=<%=request.getAttribute("page") %>">上一页</a></li>
			<li><a href="next.do?page=<%=request.getAttribute("page") %>">下一页</a></li>
			<li><input id="page1" type="text" value=<%=request.getAttribute("page") %>></li>
			<li><input type="button" value="跳转" onclick="jump()"></li>
		</ul>
</body>
</html>