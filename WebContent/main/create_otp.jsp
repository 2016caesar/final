<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="org.json.simple.*"%>
	<%@ page import ="java.sql.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>OTP login</title>

<link rel="stylesheet" type="text/css" href="" />
<c:if test="false">
</c:if>
<link href="/css/style.css" rel="stylesheet" type="text/css"/>


</head>

<body>
<%
String id = request.getParameter("id");
String pw = request.getParameter("pw");

JSONObject jsonMain = new JSONObject();
JSONArray jArray = new JSONArray();

JSONObject jObject = new JSONObject();

//json을 만드는 부분인데,, 설명을 못쓰겠습니다. 고수님들 도와주세요.~
 try{
  Connection conn = DriverManager.getConnection(
    "jdbc:mysql://192:168:43:235:3306/mylist", "root", "1234");
  Statement stmt = conn.createStatement();
  //스테이트먼트 객체를 생성합니다.
  String query = "select * from log";	
ResultSet rs = stmt.executeQuery(query);

  int i =0;
  while(rs.next())
  {
//쿼리문 중 id와 pw를 비교해서 일치하는 게 있는경우 첫번째 메시지에 succed를 담습니다.
//두세번째는 그냥 아무거나 상관없습니다.
   String _id = rs.getString("uid");
   String _pw = rs.getString("mailcode");
   if(id.equals(_id)&&pw.equals(_pw)){
    i =1;
    jObject.put("msg1", "succed");
    jObject.put("msg2", "two");
    jObject.put("msg3", "three");
   }
  }
//없는 경우에는 failed를 담습니다.
  if(i==0){
   jObject.put("msg1", "failed");
   jObject.put("msg2", "two");
   jObject.put("msg3", "three");
   
  }
  
  stmt.close();
  conn.close();
//stmt와 conn객체를 닫습니다.
  jArray.add(0,jObject);
//담은 데이터를 배열로 만듭니다.
  jsonMain.put("List",jArray);
//안드로이드로 데이터를 날립니다.
  out.println(jsonMain.toJSONString());
 }catch(Exception e){
 }
%>

	opt check : <c:out value="${CreatedOTP}" /><br/>
</body>

</html>