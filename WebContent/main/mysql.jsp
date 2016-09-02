<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ include file="session.jsp" %> --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.*" %>
<%@ page import = "java.util.HashMap" %>
<%@ page import = "java.util.Map" %>
<%@ page import = "javax.servlet.http.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>sqlex</title>
<link rel="stylesheet" type="text/css" href="" />
<c:if test="false">
</c:if>
<style>

</style>
</head>
<link href="/css/style.css" rel="stylesheet" type="text/css"/>
<body>
<%
/* String dbURL ="jdbc:mysql://localhost:3306/mysql";
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection(dbURL,"root","1234");
Statement stmt = conn.createStatement();
String SQL = "select * from userinfo";
ResultSet rs = stmt.executeQuery(SQL);
while(rs.next()){
	out.println(rs.getString(1) + "<br>");
	out.println(rs.getString(2) + "<br>");
	out.println(rs.getString(3) + "<br>");
	out.println(rs.getString(4) + "<br>");
}
stmt.close();
conn.close(); 

out.println(request.getParameter("user"));*/
 //Map name = (HashMap)request.getAttribute("user");
//System.out.println(name);

%>

<c:forEach var="result" items="${ userList }" varStatus="status">
	<%-- ${ status.index + 1} : <Br/> --%>
	<%-- <c:out value="${ result }"/> --%>
	seq : <c:out value="${ result.u_seq }"/><Br/>
	id : <c:out value="${ result.u_no }"/><Br/>
	email : <c:out value="${ result.u_mail }"/><Br/>
	pw : <c:out value="${ result.u_pass }"/><Br/>
	time : <c:out value="${ result.u_c_time }"/><Br/>
민주
</c:forEach>
<c:if test="${ isDone eq 'false'}">
	<script>
		window.onload = function(){
			alert('로그인 해주십시오.');
			location.href="/main.do";
			
			
		}
	 
	</script>
</c:if> 
</body>
</html>