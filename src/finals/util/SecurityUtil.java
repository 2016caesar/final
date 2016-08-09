/**
 * @Class Name  : SecurityUtil.java
 * @Description : 보안대책 관련 유틸리티
 * @Modification Information
 *
 * @author kensu
 * @since 2013. 01. 14
 * @version 1.0
 * @see
 *
 */

package finals.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class SecurityUtil {

	/**
     * Server URL 제공.
     * @param String type - basic : Web domain
     * @return String serverUrl
    */
	public static String getServerUrl(String type){
		String serverUrl = "";

		//--- Web domain : 수정 필요 20130114
		if(type.equals("basic")){
			serverUrl = "http://localhost/";
		}else if(type.equals("sangsang")){
			serverUrl = "/home1/www/sangsang/";
		}else if(type.equals("local")){
			serverUrl = "/Eclipse/Workspace/sangsang/src/sangsang/";
		}

		return serverUrl;
	}

	/**
     * XSS 방지 처리.
     *
     * @param String data
     * @param boolean use_html - true: 사용 , false: 사용불가
     * @return String test_str
    */
    public static String getUnscript(String data, boolean use_html) {
        if (data == null || data.trim().equals("")) {
            return "";
        }

        String test_str = data;
        String test_str_low = "";

        test_str = test_str.replaceAll("<(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;script");
        test_str = test_str.replaceAll("</(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;/script");

        test_str = test_str.replaceAll("<(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;object");
        test_str = test_str.replaceAll("</(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;/object");

        test_str = test_str.replaceAll("<(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;applet");
        test_str = test_str.replaceAll("</(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;/applet");

        test_str = test_str.replaceAll("<(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
        test_str = test_str.replaceAll("</(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");

        test_str = test_str.replaceAll("<(F|f)(O|o)(R|r)(M|m)", "&lt;form");
        test_str = test_str.replaceAll("</(F|f)(O|o)(R|r)(M|m)", "&lt;form");


        //스크립트 문자열 필터링 (선별함 - 필요한 경우 보안가이드에 첨부된 구문 추가)
        test_str_low= test_str.toLowerCase();

        if(
        	test_str_low.contains("javascript")	|| test_str_low.contains("script")		||
	        test_str_low.contains("iframe")		|| test_str_low.contains("document")	||
	        test_str_low.contains("vbscript")	|| test_str_low.contains("applet")		||
	        test_str_low.contains("embed")		|| test_str_low.contains("object")		||
	        test_str_low.contains("frame")		|| test_str_low.contains("grameset")	||
	        test_str_low.contains("layer")		|| test_str_low.contains("bgsound")		||
	        test_str_low.contains("alert")		|| test_str_low.contains("onblur")		||
	        test_str_low.contains("onchange")	|| test_str_low.contains("onclick")		||
	        test_str_low.contains("ondblclick")	|| test_str_low.contains("enerror")		||
	        test_str_low.contains("onfocus")	|| test_str_low.contains("onload")		||
	        test_str_low.contains("onmouse")	|| test_str_low.contains("onscroll")	||
	        test_str_low.contains("onsubmit")   || test_str_low.contains("onunload")
	    ){
	        test_str = test_str_low;

	        test_str = test_str.replaceAll("javascript", "x-javascript-x");
	        test_str = test_str.replaceAll("script", "x-script-x");
	        test_str = test_str.replaceAll("iframe", "x-iframe-x");
	        test_str = test_str.replaceAll("document", "x-document-x");
	        test_str = test_str.replaceAll("vbscript", "x-vbscript-x");
	        test_str = test_str.replaceAll("applet", "x-applet-x");
	        test_str = test_str.replaceAll("embed", "x-embed-x");
	        test_str = test_str.replaceAll("object", "x-object-x");
	        test_str = test_str.replaceAll("frame", "x-frame-x");
	        test_str = test_str.replaceAll("grameset", "x-grameset-x");
	        test_str = test_str.replaceAll("layer", "x-layer-x");
	        test_str = test_str.replaceAll("bgsound", "x-bgsound-x");
	        test_str = test_str.replaceAll("alert", "x-alert-x");
	        test_str = test_str.replaceAll("onblur", "x-onblur-x");
	        test_str = test_str.replaceAll("onchange", "x-onchange-x");
	        test_str = test_str.replaceAll("onclick", "x-onclick-x");
	        test_str = test_str.replaceAll("ondblclick","x-ondblclick-x");
	        test_str = test_str.replaceAll("enerror", "x-enerror-x");
	        test_str = test_str.replaceAll("onfocus", "x-onfocus-x");
	        test_str = test_str.replaceAll("onload", "x-onload-x");
	        test_str = test_str.replaceAll("onmouse", "x-onmouse-x");
	        test_str = test_str.replaceAll("onscroll", "x-onscroll-x");
	        test_str = test_str.replaceAll("onsubmit", "x-onsubmit-x");
	        test_str = test_str.replaceAll("onunload", "x-onunload-x");
        }

/*
        String test_str_low = "";

        if(use_html){
        	// HTML tag를 사용하게 할 경우 부분 허용

        	//HTML tag를 모두 제거
	        test_str = test_str.replaceAll("<","&lt;");
	        test_str = test_str.replaceAll(">","&gt;");

	        // 허용할 HTML tag만 변경
	        test_str = test_str.replaceAll("&lt;p&gt;", "<p>");
	        test_str = test_str.replaceAll("&lt;P&gt;", "<P>");
	        test_str = test_str.replaceAll("&lt;br&gt;", "<br />");
	        test_str = test_str.replaceAll("&lt;BR&gt;", "<BR />");

	        //스크립트 문자열 필터링 (선별함 - 필요한 경우 보안가이드에 첨부된 구문 추가)
	        test_str_low= test_str.toLowerCase();

	        if(
	        	test_str_low.contains("javascript")	|| test_str_low.contains("script")		||
		        test_str_low.contains("iframe")		|| test_str_low.contains("document")	||
		        test_str_low.contains("vbscript")	|| test_str_low.contains("applet")		||
		        test_str_low.contains("embed")		|| test_str_low.contains("object")		||
		        test_str_low.contains("frame")		|| test_str_low.contains("grameset")	||
		        test_str_low.contains("layer")		|| test_str_low.contains("bgsound")		||
		        test_str_low.contains("alert")		|| test_str_low.contains("onblur")		||
		        test_str_low.contains("onchange")	|| test_str_low.contains("onclick")		||
		        test_str_low.contains("ondblclick")	|| test_str_low.contains("enerror")		||
		        test_str_low.contains("onfocus")	|| test_str_low.contains("onload")		||
		        test_str_low.contains("onmouse")	|| test_str_low.contains("onscroll")	||
		        test_str_low.contains("onsubmit")   || test_str_low.contains("onunload")
		    ){
		        test_str = test_str_low;

		        test_str = test_str.replaceAll("javascript", "x-javascript");
		        test_str = test_str.replaceAll("script", "x-script");
		        test_str = test_str.replaceAll("iframe", "x-iframe");
		        test_str = test_str.replaceAll("document", "x-document");
		        test_str = test_str.replaceAll("vbscript", "x-vbscript");
		        test_str = test_str.replaceAll("applet", "x-applet");
		        test_str = test_str.replaceAll("embed", "x-embed");
		        test_str = test_str.replaceAll("object", "x-object");
		        test_str = test_str.replaceAll("frame", "x-frame");
		        test_str = test_str.replaceAll("grameset", "x-grameset");
		        test_str = test_str.replaceAll("layer", "x-layer");
		        test_str = test_str.replaceAll("bgsound", "x-bgsound");
		        test_str = test_str.replaceAll("alert", "x-alert");
		        test_str = test_str.replaceAll("onblur", "x-onblur");
		        test_str = test_str.replaceAll("onchange", "x-onchange");
		        test_str = test_str.replaceAll("onclick", "x-onclick");
		        test_str = test_str.replaceAll("ondblclick","x-ondblclick");
		        test_str = test_str.replaceAll("enerror", "x-enerror");
		        test_str = test_str.replaceAll("onfocus", "x-onfocus");
		        test_str = test_str.replaceAll("onload", "x-onload");
		        test_str = test_str.replaceAll("onmouse", "x-onmouse");
		        test_str = test_str.replaceAll("onscroll", "x-onscroll");
		        test_str = test_str.replaceAll("onsubmit", "x-onsubmit");
		        test_str = test_str.replaceAll("onunload", "x-onunload");
	        }
        }else{
        	// HTML tag를 사용하지 못하게 할 경우

        	//HTML tag를 모두 제거
        	test_str = test_str.replaceAll("<","&lt;");
        	test_str = test_str.replaceAll(">","&gt;");
        }
*/
        return test_str;
    }

	/**
     * XSS 방지 - javascript 변환.
     *
     * - javascript를 x-javascript-x 로 변환
     *
     * @param String data
     * @param boolean use_html - true: 사용 , false: 사용불가
     * @return String test_str
    */
    public static String getChangeJavascript(String data, boolean use_html) {
        if (data == null || data.trim().equals("")) {
            return "";
        }

        String test_str = data;
        String test_str_low = "";

        test_str = test_str.replaceAll("(J|j)(A|a)(V|v)(A|a)(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "javascript");

        //스크립트 문자열 필터링 (선별함 - 필요한 경우 보안가이드에 첨부된 구문 추가)
        test_str_low= test_str;

        if(
        	test_str_low.contains("javascript")
	    ){
	        test_str = test_str_low;
	        test_str = test_str.replaceAll("javascript", "x-javascript-x");
        }

        return test_str;
    }

    /**
     * XSS 방지 - javascript 복원.
     *
     * - x-javascript-x를 javascript 로 변환
     *
     * @param String data
     * @param boolean use_html - true: 사용 , false: 사용불가
     * @return String test_str
    */
    public static String getRecoverJavascript(String data, boolean use_html) {
        if (data == null || data.trim().equals("")) {
            return "";
        }

        String test_str = data;
        String test_str_low = "";

        test_str = test_str.replaceAll("(J|j)(A|a)(V|v)(A|a)(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "javascript");

        //스크립트 문자열 필터링 (선별함 - 필요한 경우 보안가이드에 첨부된 구문 추가)
        test_str_low= test_str;

        test_str = test_str_low;
        test_str = test_str.replaceAll("x-javascript-x", "javascript");

        return test_str;
    }

    /**
     * 파일 다운로드 경로 체크 함수
     * @param String dn_path - 다운로드 디렉토리 경로
     * @return true: String FilePath - 다운로드 파일 경로, false: "error"
    */
    public static String checkPath(String dn_path) {

         //입력되는 디렉토리명에서 특수문자 유무 검사
         if((dn_path.indexOf("..\\") != -1) || (dn_path.indexOf("../") != -1)) {
        	 return "error";
         }

         //사용자 입력값으로 다운로드 파일 경로 생성
         if (dn_path.equals("")) {
         } else {
        	 dn_path = dn_path + "/";
         }

         String origfile = dn_path ;

         //분리한 파일명과 절대 경로를 재구성
         String FilePath = dn_path ;

         //사용자 입력값과 재구성한 입력값을 비교하여 공격 위험성이 존재하는지 확인
         if (origfile.equals(FilePath)) {
        	 return (FilePath);
         } else {
        	 return "error";
         }
  }

    /**
     * PASSWORD 내의 특수문자 보정 메서드
     *
     * @param strString
     * @return HTML 태그를 치환한 문자열
     */
    public static String convertPassword(String strString) {

		String tmpString = strString;

		try{
			tmpString = tmpString.replaceAll("&lt;", "<");
			tmpString = tmpString.replaceAll("&gt;", ">");
			tmpString = tmpString.replaceAll("&amp;", "&");
			tmpString = tmpString.replaceAll("&nbsp;", " ");
			tmpString = tmpString.replaceAll("&apos;", "\'");
			tmpString = tmpString.replaceAll("&quot;", "\"");
		}catch (Exception ex){
			ex.printStackTrace();
		}

		return  tmpString;
    }

    /**
     * 스팸방지용 captcha
     * @param HttpServletRequest request
     * @return boolean
     */
    public static boolean checkCaptcha(HttpServletRequest request) {
    	boolean result = false;

        HttpSession session = request.getSession();
		String temp = EgovStringUtil.isNullToString( request.getMethod() );
		String inCaptcha = EgovStringUtil.isNullToString( session.getAttribute("captcha") );
		String inCaptcha2 = EgovStringUtil.isNullToString( request.getParameter("captcha") );

		if( temp.equals("POST") && inCaptcha.equals(inCaptcha2) ){
			result = true;
		}

    	return result;
    }

    /**
     * redirect시의 메시지 변환 메소드
     *
     * - get방식으로 한글 전달 못하는 상황을 보조함.
     *
     * @param strString
     * @return 반환할 메시지, 값이 없으면 "".
     */
    public static String convertResultMsg(String strString) {

		String tmpString = strString;

		try{
			if(EgovStringUtil.isEmpty(tmpString)){
				tmpString = "";
			}else{

				if(tmpString.equals("A01")){
					tmpString = "등록되었습니다.";
				}else{
					tmpString = strString;
				}
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}

		return  tmpString;
    }

    /**
     * 사용자 세션정보 등록 메소드
     * @param HttpServletRequest request , String name , String value
     * @return void
     */
    public static void writeSession(HttpServletRequest request, String name , String value) {
    	HttpSession session = request.getSession();
		session.setAttribute( name , value );
    }
    public static void removeSession(HttpServletRequest request, String name) {
    	HttpSession session = request.getSession();
    	session.removeAttribute(name);
    }

    /**
     * 사용자 세션정보 읽기 메소드
     * @param HttpServletRequest request
     * @param String key
     * @return String
     */
    public static String readSession(HttpServletRequest request,String key) {

    	HttpSession session = request.getSession();

    	return EgovStringUtil.isNullToString((String) session.getAttribute(key));
    }

    /**
     * 사용자 세션정보 삭제 메소드
     * @param HttpServletRequest request
     * @return void
     */
    public static void clearSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
    }

    /**
     * URL의 .* 앞 문자열만 가져온다.
     * @param url 원본 문자열
     * @return url에서 마지막/ 와 . 사이의 문자열.
     */
    public static String convertUrl( String url ) throws NullPointerException {
        String[] returnVal = null;
        String result = "";

        //--- /로 나눈 값.
        returnVal = EgovStringUtil.split( url , "/" );
        result = returnVal[returnVal.length-1];

        //--- .로 나눈 값.
        returnVal = EgovStringUtil.split( result , "." );

        return returnVal[0];
    }


}
