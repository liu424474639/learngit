<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.*,java.text.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
        fieldset{
            background-color: #f1f1f1;
            border: none;
            border-radius: 2px;
            margin-bottom: 12px;
            overflow: hidden;
            padding: 0 .625em;
        }

        label{
            cursor: pointer;
            display: inline-block;
            padding: 9px 12px;
            text-align: right;
            width: 550px;
            vertical-align: top;
        }

        input{
            font-size: inherit;
            text-align: center;
        }
    </style>
</head>
<body>
     <form action="update.do" method="post">
       <p>
            <label for="title" >id:</label>
            <input type="text" id="title" name="id" value=<%=request.getParameter("id") %> align="left" readonly="readonly"/>
       </p>
       <p>
            <label for="title" >name:</label>
            <input type="text" id="title" name="name" value=<%=request.getParameter("name")%> align="left">
       </p>
       <p>
            <label for="title" >birthday:</label>
            <input type="text" id="title" name="birthday" value=<%=request.getParameter("birthday")%> align="left">
       </p>
       <p>
            <label for="title" >description:</label>
            <input type="text" id="title" name="description" value=<%=request.getParameter("description")%> align="left">
       </p>
       <p>
            <label for="title" >avgscore:</label>
            <input type="text" id="title" name="avgscore" value=<%=request.getParameter("avgscore")%> align="left">
       </p>
       <p>
            <input type="submit" value="保存">
            <input type="button" value="取消" class="btn_save" onclick="history.back();"/>
       </p>
       
     </form>
</body>
</html>