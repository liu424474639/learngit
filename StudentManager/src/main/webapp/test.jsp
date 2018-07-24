<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
    function jump(){
    	var page = document.getElementById("page1").value;
    	//request.setAttribute("page",page);
    	alert("page="+page);
        self.location= "jump.do?page="+page;   	
    }
    function up(){
    	var page = document.getElementById("page1").value;
    	alert("page="+page);
    	if(page=1&&page<1){
    		page = 1;
    		alert("page="+page);
    		request.setAttribute("page",page);
    		self.location="FindStudentServlet.do";
    	}else{
    		self.location= "up.do?page="+(page-1);
    	}
    }
    function next(){
    	var page = document.getElementById("page1").value;
    	//alert("page="+page);
    	var count ='<%=session.getAttribute("userName")%>';
    	alert(count);
    	//alert(page);
    	if(page>(count/10+1)){
    		page = count/10+1;
    		self.location="FindStudentServlet.do";
    	}else{
    		self.location= "next.do?page="+(page+1);
    	}
    }
</script>
</head>
<body>
     <ul>
        <li><input type="button" value="上一页" onclick="up()"></li>
        <li><input type="button" value="下一页" onclick="next()"></li>
        <li><input id="page1" type="text" value=<%=request.getAttribute("page") %>></li>
		<li><input type="button" value="跳转" onclick="jump()"></li>
     </ul>
</body>
</html>