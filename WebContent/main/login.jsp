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
<title>normal login</title>
<!-- <link rel="shortcut icon" href="" type="image/x-icon" /> -->
<link rel="stylesheet" type="text/css" href="" />
<c:if test="false">
</c:if>
<style>

</style>
<script type="text/javascript">
function fsubmit() {
     //var mail = document.getElementsByName("useremail");
     //document.devicecheck.useremail.value = mail;
     document.devicecheck.action = "/mail.do";
     document.devicecheck.submit();
}
</script>
</head>
<link href="/css/style.css" rel="stylesheet" type="text/css"/>
<body>
<div class="wrap">
		<img src="main/img/korean.png"  width="70" height="21" border="0" id="ko"/>
        <img src="main/img/english.png"  width="70" height="21" border="0" id="eng"/>
		<img src="main/img/chinese.png" width="70" height="21" border="0" id="ch"/>
			<form name="loginpage" method="post" >
				<input type="hidden" name="mode" value="login"/>
				<input type="hidden" id="oschks" name="oschks"/>
				<input type="hidden" id="brochks" name ="brochks"  />
				<input type="hidden" name="pki_msg"/>
			</form>
			
			<img class="sso_title" src="main/img/title.png" alt="상명대학교 통합로그인" /><br />
			<img class="sso_slogan" src="main/img/slogan.png" alt="Global Sangmyung Dynamic Sangmyung" />
			<div class="box_area">
			<br /><p class="sub_tit4">기기본인인증</p>
			<br /><img class="line1" src="main/img/line.png" alt="line" /> 
			 <p class="sub1_txt1">메일주소를 입력하세요.</p>
		<form name="devicecheck" method="post" action="return false;">
			<input class="login_input_txt" type="text" name="useremail" placeholder="e-mail" ></input> 
			<img class="bt_login3" src="main/img/enter.png" style="cursor:pointer;" width="90" height="69" onclick="javascript:fsubmit();" >
		</form>
			<br /><br /><img class="line4" src="main/img/line.png" alt="line" /> 
		<form name="Page_FORM" method="post" action="/page.do" >
			<p class="sub3_tit3">통합 로그인화면</p><br />
			<input type="image" class="bt_login5" src="main/img/click.png" width="90" height="69" >
			</input>		
		</form>
		<!--onSubmit="f_form1(this);return false;"  -->
		<br /><br /><br /><br /><br /><br /><img class="line5" src="main/img/line.png" alt="line" /> 
		<p class="find_txt">
				<br>&nbsp;&nbsp;&nbsp;<br><br><br></p>
				<br>
			</div>
			
			<div>
			<p class="sub2_txt">서울캠퍼스 서울시 종로구 홍지문 2길 20 (우)03016 / 천안캠퍼스 충남 천안시 동남구 상명대길 31 (우)31066<br /></p>
			<p class="sub3_txt">Copyright(c) 2013 Sangmyung University All Rights Reserved</p>
	
</body>
</html>