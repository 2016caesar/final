<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="org.json.simple.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import = "java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>mail page</title>
<c:if test="false">
</c:if>
<style>
</style>
</head>
<body>

	<%request.setCharacterEncoding("UTF-8");
	
		String uid = request.getParameter("uid");
		String mailcode = request.getParameter("mailcode");
		
		System.out.println("학번 : "+uid);
		System.out.println("코드 : "+mailcode);
		
		JSONObject jsonMain = new JSONObject();
		JSONArray jArray = new JSONArray();
		JSONObject jObject = new JSONObject();
		Class.forName("com.mysql.jdbc.Driver");
		try{
			Connection conn = DriverManager.getConnection("jdbc:mysql://http:192.168.0.6:3306/mylist", "root", "1234");
			Statement stmt = conn.createStatement();
			
			String query = "select * from log";
			ResultSet rs = stmt.executeQuery(query);
			int i=0; while(rs.next()){
				String _uid = rs.getString("uid");
				String _mailcode=  rs.getString("mailcode");
				if(uid.equals(_uid)&&mailcode.equals(_mailcode)){
					i = 1;
					jObject.put("msg1","succed");
					jObject.put("msg2", "two");
					jObject.put("msg3", "three");					
				}
			}
			if(i==0){
				jObject.put("msg1", "failed");
				jObject.put("msg2", "two");
				jObject.put("msg3", "three");
			}
			stmt.close();
			conn.close();
			jArray.add(0,jObject);
			jsonMain.put("List",jArray);
			out.println(jsonMain.toJSONString());
		}catch(Exception e){ }
	
		%>


</body>