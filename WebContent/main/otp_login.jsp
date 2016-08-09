<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ include file="session.jsp" %> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>OTP login</title>
<link rel="shortcut icon" href="" type="image/x-icon" />

<link rel="stylesheet" type="text/css" href="" />
<c:if test="false">
</c:if>
<link href="/css/style.css" rel="stylesheet" type="text/css"/>
</head>

<body>
	<div class="wrap">
		<img src="main/img/korean.png"  width="70" height="21" border="0" id="ko"/></a>
        <img src="main/img/english.png"  width="70" height="21" border="0" id="eng"/></a>
		<img src="main/img/chinese.png" width="70" height="21" border="0" id="ch"/></a>
			<form name="otppage" method="post" >
				<input type="hidden" name="mode" value="login"/>
				<input type="hidden" id="oschks" name="oschks"/>
				<input type="hidden" id="brochks" name ="brochks"  />
				<input type="hidden" name="pki_msg"/>
			</form>
			
		<img class="sso_title" src="main/img/title.png" alt="상명대학교 통합로그인" /><br />
		<img class="sso_slogan" src="main/img/slogan.png" alt="Global Sangmyung Dynamic Sangmyung" />
	<div class="box_area">
	<p class="sub_tit5">OTP로그인</p><br>
		<img class="line3" src="main/img/line.png" alt="line" /> 
	<form class="OTP_FORM" method="post" alert="로그인 성공" >
		<input class="INPUT_TXT2" name="uid" placeholder=" 아이디" type="text" maxlength="20">
		<input class="input_pw2" name="otpw" placeholder=" one time password" type="text">
		<input type="image" class="bt_login4" src="main/img/login2.png" title="otp전송" longdesc="로그인"></input>
	</input></form>
	</div><div><br />
	<br />
			<p class="sub2_txt">서울캠퍼스 서울시 종로구   홍지문 2길 20 (우)03016 / 천안캠퍼스 충남 천안시 동남구 상명대길 31 (우)31066<br /></p>
			<p class="sub3_txt">Copyright(c) 2013 Sangmyung University All Rights Reserved</p>
		</div>
		</div>
</body>
</html>