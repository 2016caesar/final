<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>asdf </title>
</head>
<body style="width:100%; text-align:center;margin:0;padding:0;">
	<div style="padding:230px 0 24px 0;">
		<div style="background:url(/images/error/error.png) no-repeat center 0; height: 280px; width:527px; margin:0 auto">
			<div style="font-size:18px;font-weight:bolder;padding:70px 0 24px 0;">처리에 실패하였습니다.</div>
			<div style="font-size:12px;padding:80px 0px 20px 0px;line-height:20px;">
			요청된 페이지에 문제가 발생하였습니다. 다시 시도해 보시기 바랍니다.
			<c:if test="${bodyAttrib eq 'TEST'}"><br />${exception.message}</c:if>
			</div>
			<p><a href="/" >홈으로 이동</a></p>
		</div>
	</div>
</body>
</html>
