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
<title>login page</title>
<c:if test="false">
</c:if>
<style>
</style>
</head>
<!-- 
	if(session.getAttribute("sessionId")==null){
		
	

<script language="javascript">
function focusIt()
{
	document.inform.id.focus();
}
 function checkIt()
 {
	 inputForm=eval("document.inform");
	 if(!inputForm.id.value){
		 alert("아이디를 입력하세요!");
		 inputForm.id.focus();
		 return false;
	 }
	 if(!inputForm.pw.value){
		 alert("비밀번호를 입력하세요!");
		 inputForm.pw.focus();
		 return false;
	 }
 }
	</script> -->
<link href="/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
<!--
function chk_usr_agent(){
	document.getElementById("oschk").value = AnySign.mPlatform.aName;
	document.getElementById("brochk").value = AnySign.mBrowser.aName;

}
function chk_usr_agent2(){
	document.getElementById("oschks").value = AnySign.mPlatform.aName;
	document.getElementById("brochks").value = AnySign.mBrowser.aName;

}
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}
function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
function f_form1(f){
	chk_usr_agent();

	if(!f.uid.value){
		alert("사용자 아이디를 입력해 주세요!!");
		f.uid.focus();
		return false;
	}
	if(!f.pwd.value){
		alert("사용자 비밀번호를 입력해 주세요!!");
		f.pwd.focus();
		return false;
	}
	//f.passwd_base.value = f.pwd.value; //calcMD5(trim(f.passwd.value));
	//f.submit();
	XecureSubmit(f);
}



function fPkiLogin()
{
	var f = document.cert_login;
	var d = new Date();
	var ts = Math.floor(d.getTime()/1000);
	var tmp_sign2 = "";
	var null_chk = false;
	var sign_pkcs = "";
			chk_usr_agent2();
	Sign_with_option(0, ts, _CB_Sign_with_option_ );

	
	function _CB_Sign_with_option_ (singwith)
	{
		if(singwith != null && singwith !=""){
		
			f.pki_msg.value = singwith;
			XecureSubmit(f);
		}else{
			return false;
		}
	}

}


</script>
<script type="text/javascript">
	PrintObjectTag();

</script>
</head>

	<body>
		<div class="wrap">
		<img src="main/img/korean.png"  width="70" height="21" border="0" id="ko"/></a>
        <img src="main/img/english.png"  width="70" height="21" border="0" id="eng"/></a>
		<img src="main/img/chinese.png" width="70" height="21" border="0" id="ch"/></a>
		<form name="normal_login" method="post" action="login/normal_login.jsp">
			<input type="hidden" name="mode" value="login"/>
			<input type="hidden" id="oschks" name="oschks"/>
			<input type="hidden" id="brochks" name ="brochks"  />
			<input type="hidden" name="pki_msg"/>
		</form>
		<img class="sso_title" src="main/img/title.png" alt="상명대학교 통합로그인" /><br />
		<img class="sso_slogan" src="main/img/slogan.png" alt="Global Sangmyung Dynamic Sangmyung" />
			<div class="box_area">
				<div class="inner_main_container">
					<!-- <p class="sub_tit1">OTP로그인</p>
					<img class="line" src="main/img/line.png" alt="line" /> 
					<p class="sub1_txt">OTP로그인 사용을 위해서는<br />아이디 로그인 이후 기기 본인인증을<br />하셔야 합니다.</p>
					<br /> -->
					<p class="sub_tit4">기기본인인증</p>
						<br /><img class="line1" src="main/img/line.png" alt="line" /> 
					<p class="sub1_txt1">학번과 메일주소를 입력하세요.</p>
					<div class="login_area">
						<form name="otp_login" method="post" action="/otp_login.do" >
							<input class="stuNum_input_txt" type="text" name="stuNum" placeholder="stuNum" ></input> 
							<input class="login_input_txt" type="text" name="useremail" placeholder="e-mail" ></input> 
							<input type="image" class="bt_login1" src="main/img/login1.png" title="로그인" longdesc="로그인"></input>
						</form>
					</div>
					
                	<p class="sub_tit2">아이디/비밀번호 로그인</p>
                	<br/><img class="line2" src="main/img/line.png" alt="line" />
                	<div class="login_area">
						<form name="LOGIN_FORM" method="post" action="/login.do" onSubmit="f_form1(this);return false;">
	 						<input class="input_txt" name="uid"  placeholder="아이디" type="text" maxlength="20"/>
							<input class="input_pw" name="pwd" placeholder="비밀번호" type="password" maxlength="30"/>
							<input type="image" class="bt_login2" src="main/img/login2.png" title = "로그인전송" longdesc="로그인"/>
						</form>
					</div>
				<p class="find_txt">* 초기 비밀번호는 생년월일(주민등록번호 앞 6자리)입니다
				<br>&nbsp;&nbsp;&nbsp;예) 생년월일 : 1986년 02월 10일 -> 비밀번호 : 860210<br><br><br>비밀번호가 생각나지 않으세요?</p>
				<a href="javascript:void(XecureNavigate('/login/info_ini.jsp','_self'))"><img class="bt_find" src="main/img/pwfind.png" alt="비밀번호 찾기" /></a>
			</div>
		</div>
		<div>
			<p class="sub2_txt">서울캠퍼스 서울시 종로구 홍지문 2길 20 (우)03016 / 천안캠퍼스 충남 천안시 동남구 상명대길 31 (우)31066<br /></p>
			<p class="sub3_txt">Copyright(c) 2013 Sangmyung University All Rights Reserved</p>
		</div>
	</div>
		<!-- <input type="button" value="팝업창" onclick="window.open('http://sso.smu.ac.kr:8082/kmcis/kmcis_web_sample_step01.jsp','window팝업','width=400, height=200, menubar=no, status=no, toolbar=no');">-->
		<!--<a href="http://www.kmcert.com/kmcis/web/kmcisReq.jsp">t</a>-->
		<!--<a href="javascript:void(XecureNavigate('/login/info_ini.jsp','_self'))">t</a>-->
</body>
</html>


	  
	  
  
