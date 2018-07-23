<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
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
     <form action="add.do" method="post">
       <p>
            <label for="title" >id:</label>
            <input type="text" id="title" name="id" value=<%=session.getAttribute("count") %> align="left" readonly="readonly"/>
       </p>
       <p>
            <label for="title" >name:</label>
            <input type="text" id="title" name="name" align="left">
       </p>
       <p>
            <label for="title" >birthday:</label>
            <input type="text" id="title" name="birthday" align="left"/>
       </p>
       <p>
            <label for="title" >description:</label>
            <input type="text" id="title" name="description" align="left"/>
       </p>
       <p>
            <label for="title" >avgscore:</label>
            <input type="text" id="title" name="avgscore" align="left"/>
       </p>
       <p>
            <input type="submit" value="保存">
            <input type="button" value="取消" class="btn_save" onclick="history.back();"/>
       </p>
       
     </form>
</body>
</html>