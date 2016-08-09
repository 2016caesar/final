package finals.util;

import java.io.*;
import java.text.*;
import java.util.*;

import java.net.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.*;

public class DataUtil {

	public static String notnull(Object obj){
		if(obj == null) return "";
		else {
			if(obj.toString().trim()=="")return "";
			else return obj.toString();
		}
	}

	public static String notnull(Object obj,String str){
		if(obj == null) return str;
		else {
			if(obj.toString().trim()=="")return str;
			else return obj.toString();
		}
	}


	public static String mapData(Map param, String key){
		String result = "";
		if(param != null){
			result = replace(replace(notnull(param.get(key)), "<", "&lt;"), ">", "&gt;");
		}
		return result;
	}

	public static String mapData(Map param, String key,String deft){
		String result = deft;
		if(param != null){
			result = notnull(param.get(key),deft);
		}
		return result;
	}
	public static String ank_conver_str(String ans){
		if(!"".equals(ans)){


			ans = ans.replaceAll("'", "`");
			ans = "@@"+ans;
			ans = ans.replaceAll("@@", "ЖΘ").replaceAll("=", "-");
			ans=ans.replaceAll("@@", "ЖΘ");
			//System.out.println("###ans : " + ans);
		}
		return ans;
	}

	public static String ank_conver(String ans){
		if(!"".equals(ans)){

			//System.out.println("###ans1 : " + ans);
			ans = ans.replaceAll("'", "`");
			ans = "@@"+ans;
			ans = ans.replaceAll("@@", "ЖΘ").replaceAll("ξ", "ΘΔ");
			ans=ans.replaceAll("@@", "ЖΘ");
			//System.out.println("###ans2 : " + ans);
		}
		return ans;
	}


	public static String getSessionData(HttpServletRequest req, String key){
		String result = "";
		if(req.getSession()!=null)
			result = DataUtil.notnull(req.getSession().getAttribute(key));
		return result;
	}

	public static String getSessionData(HttpSession session, String key){
		String result = "";
		if(session!=null)
			result = DataUtil.notnull(session.getAttribute(key));
		return result;
	}


    public static String StrToUni(String str){
    	String unicode = "" ;
    	for ( int i = 0 ; i < str.length() ; i++)
    	{
	    	char chr = str.charAt(i) ;
	    	String hex = Integer.toHexString(chr) ;
	    	unicode += "@@u"+hex ;
    	}
    	return unicode ;
    }

    public static String UniToStr(String unicode){
    	String str = "" ;
    	java.util.StringTokenizer str1 = new java.util.StringTokenizer(unicode,"@@u") ;
    	while(str1.hasMoreTokens())
    	{
    		String str2 = str1.nextToken() ;
    		int i = Integer.parseInt(str2,16) ;
    		str += (char)i ;
    	}
    	return str ;
    }

	/*
	 * 숫자체크
	 *   숫자이면 true를  ,아니면 false를 return
	 */
	public static boolean isNumber(String str){
		 if(str.equals("")) return false;

		 char c;
		 for(int i = 0 ; i < str.length() ; i++){
			 c = str.charAt(i);
		     if(c < 48 || c > 59){
		    	 return false;
		    }
		 }
		 return true;
	}


	//==============================================
	// 기존 소스  내용
	//==============================================

	/**
	 * 기  능 : html용 날짜 출력
	 * @param : 해당 날짜 값
	 * @return : String Date
	*/
	public static String rsDateNull(String date) {
		if (date == null || "".equals(date) || "NULL".equals(date)){
			return date = "";
		} else if ("1900-01-01 00:00:00.0".equals(date)){
			return date = "";
		} else if (date.length() < 10){
			return date;
		} else {
			return date.substring(0, 10);
		}
	}



    /**
     * 기  능 : Insert,Update시 항목입력필드구성(문자)
     * @param : koumoku 컬럼 값
     * @return : sb.toString() 조합된 컬럼값
    */
    public static String setCol(String column){
        StringBuffer sb = new StringBuffer();

        //아무것도 없는 값이거나 널값이면 DB에 널값을 등록한다
        if ((column == null) || (column.trim().equals(""))){
             return "''";
        }else{
            //sb.append("'" + convertString(column.trim(),"'","''") + "'");
        	sb.append(convertString(column.trim(),"'","''"));
            return (sb.toString());
        }
    }

    public static int setNum(String column){
        StringBuffer sb = new StringBuffer();

        //아무것도 없는 값이거나 널값이면 DB에 널값을 등록한다
        if ((column == null) || (column.trim().equals(""))){
             return  0 ;
        }else{
            return   Integer.parseInt(column);
        }
    }

    public static String isNull(String column){
        StringBuffer sb = new StringBuffer();

        //아무것도 없는 값이거나 널값이면 DB에 널값을 등록한다
        if ((column == null) || (column.trim().equals(""))){
             return  "" ;
        }else{
            return  column;
        }
    }

    public static String isNull(String column, String value ){
        StringBuffer sb = new StringBuffer();

        //아무것도 없는 값이거나 널값이면 DB에 널값을 등록한다
        if ((column == null) || (column.trim().equals(""))){
             return  "" ;
        }else{
            return  value;
        }
    }


    /**
     * 날짜와 시간취득
     * @return  msgDate 날짜 시간
     * @see Util
     */
    public static String getTodayFull(){
        String msgDate = "";

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMddHHmmss");
        msgDate = simpleDate.format(new Date());
        return msgDate;
    }

    /**
     * 오늘날짜취득
     * @return  msgDate 오늘날짜
     * @see Util
     */
    public static String getToday(){
        String msgDate = "";

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMdd");
        msgDate = simpleDate.format(new Date());
        return msgDate;
    }

    /**
     * 오늘날짜취득
     * @return  msgDate 오늘날짜
     * @see Util
     */
    public static String getYear(){
        String msgDate = "";

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy");
        msgDate = simpleDate.format(new Date());
        return msgDate;
    }

    /**
     * 오늘날짜취득
     * @return  msgDate 오늘날짜
     * @see Util
     */
    public static String getMonth(){
        String msgDate = "";

        SimpleDateFormat simpleDate = new SimpleDateFormat("MM");
        msgDate = simpleDate.format(new Date());
        return msgDate;
    }

    /**
     * 현재시간취득
     * @return  msgDate 취득시간
     * @see Util
     */
    public static String getTime(){
        String msgDate = "";
        SimpleDateFormat simpleDate = new SimpleDateFormat("HHmmss");
        msgDate = simpleDate.format(new Date());
        return msgDate;
    }

    /**
     * 로그출력위한 헤더생성
     * @param : src 헤더로 구성될 소스이름
     * @return  strDate 로그출력헤더
     * @see Util
     */
    public static String getHeader(){
        // 헤더를 위한 날짜 취득
        StringBuffer strBuf = new StringBuffer();
        String strDate= getTime();

        // z32_Log Header : [HH:MM:SS] [Class.method]
        strBuf.append("[");
        strBuf.append(strDate.substring(0, 2));
        strBuf.append(":");
        strBuf.append(strDate.substring(2, 4));
        strBuf.append(":");
        strBuf.append(strDate.substring(4, 6));
        strBuf.append("] ");

        return strBuf.toString();
    }

    /**
     * 한글을 인코딩한다(URL로 한글을 넘길때 암호화)
     * @param  str 대상문자열
     * @return URLEncoder.encode(str) 인코딩된 문자열
     * @see Util
     */
    public static String toEncoding(String str){
        if ((str == null) || str.equals("")){
            return "";
        }
        return URLEncoder.encode(str);
    }

    /**
     * 한글을 디코딩한다(URL로 한글을 넘길때 암호화)
     * @param  str 대상문자열
     * @return URLEncoder.encode(str) 디코딩된 문자열
     * @see Util
     */
    public static String toDecoding(String str){
        if ((str == null) || str.equals("")){
            return "";
        }
        return URLDecoder.decode(str);
    }

    /**
     * 숫자로된 년,월,일을 YYYYMMDD형식으로 변환한다
     * @param  nYear 대상년도
     * @param  nMonth 대상달
     * @param  nDay 대상날
     * @return (YYYYMMDD)
     * @see Util
     */
    public static String getYMD(int nYear, int nMonth, int nDay) {
        // 데이터 포맷취득
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        // 날짜설정
        Calendar cal = new GregorianCalendar(nYear,nMonth-1,nDay);
        String thisDay = df.format(cal.getTime());

        return thisDay;
    }

    /**
     * 년월일에 해당하는 날이 한 주중에 몇번째 요일인지를 구한다
     * @param  data Target대상날짜
     * @return nResult (sun:1,mon:2...sat:7)
     * @see Util
     */
    public static int getWeekDay(String data) {
        int nYear = Integer.parseInt(data.substring(0,4));
        int nMonth = Integer.parseInt(data.substring(4,6)) - 1;
        int nDay = Integer.parseInt(data.substring(6,8));
        int nResult = 0;

        // 날짜설정
        Calendar cal = new GregorianCalendar(nYear,nMonth,nDay);
        nResult = cal.get(Calendar.DAY_OF_WEEK);

        return nResult;
    }

    /**
     * 날짜(YYYY+[]+MM+[]+DD)를Format(YYYYMMDD)로변경
     * @param  data (YYYY+[]+MM+[]+DD)
     * @return sb.toString() (YYYYMMDD)
     * @see Util
     */
    public static String getDeleteFormatYMD(String data) {
        StringBuffer sb = new StringBuffer();
        if (data == null || data.equals("")){
            return "";
        }
        sb.append(data.substring(0,4));
        sb.append(data.substring(5,7));
        sb.append(data.substring(8));

        return sb.toString();
    }

    /**
     * YYYYMMDD를dig로 구분된 YYYY/MM/DD날짜형식변경
     * @param  data (YYYYMMDD)
     * @param  dig
     * @return sb.toString() (YYYY/MM/DD)
     * @see Util
     */
    public static String getFormatedYMD(String data, String dig) {
        StringBuffer sb = new StringBuffer();
        if (data == null || data.equals("")){
            return "";
        }
        sb.append(data.substring(0,4));
        sb.append(dig);
        sb.append(data.substring(4,6));
        sb.append(dig);
        sb.append(data.substring(6,8));

        return sb.toString();
    }

    public static String getFormatedYMD_1(String data, String dig) {
        StringBuffer sb = new StringBuffer();
        if (data == null || data.equals("")){
            return "";
        }
        sb.append(data.substring(0,4));
        sb.append(dig);
        sb.append(data.substring(5,7));
        sb.append(dig);
        sb.append(data.substring(8,10));

        return sb.toString();
    }

    /**
     * 날짜의 차이(크기를 비교)
     * @param  fDate (YYYYMMDD)
     * @param  sDate (YYYYMMDD)
     * @return nReturn
     * @see Util
     */
    public static int getDayDifference(String fDate, String sDate) {
        int nReturn = 0;

        Calendar fCal = new GregorianCalendar();
        fCal.set(Integer.parseInt(fDate.substring(0,4)),
                 Integer.parseInt(fDate.substring(4,6))-1,
                 Integer.parseInt(fDate.substring(6,8)));
        Calendar sCal = new GregorianCalendar();
        sCal.set(Integer.parseInt(sDate.substring(0,4)),
                 Integer.parseInt(sDate.substring(4,6))-1,
                 Integer.parseInt(sDate.substring(6,8)));
        nReturn = (int)((fCal.getTime().getTime()-sCal.getTime().getTime())/(1000*60*60*24));
        return nReturn;
    }

    /**
     * 날짜의 차이(크기를 비교)
     * @param  fDate (YYYYMMDD)
     * @param  sDate (YYYYMMDD)
     * @return nReturn
     * @see Util
     */
    public static int getDayMsDifference(String fDate, String sDate) {
        int nReturn = 0;

        Calendar fCal = new GregorianCalendar();
        fCal.set(Integer.parseInt(fDate.substring(0,4)), Integer.parseInt(fDate.substring(4,6))-1, Integer.parseInt(fDate.substring(6,8)));
        Calendar sCal = new GregorianCalendar();
        sCal.set(Integer.parseInt(sDate.substring(0,4)), Integer.parseInt(sDate.substring(5,7))-1, Integer.parseInt(sDate.substring(8,9)));
        nReturn = (int)((fCal.getTime().getTime()-sCal.getTime().getTime())/(1000*60*60*24));
        return nReturn;
    }

    /**
     * 달의 마지막 날짜를 구한다
     * @param data
     * @return day 날짜
     */
    public static int getLastDay(String data){
        //날짜취득
        String year = data.substring(0,4);
        String month = data.substring(4,6);
        int day = 0; //마지막날짜
        int nYear = Integer.parseInt(year);

        //31일처리
        if (month.equals("01") || month.equals("03") || month.equals("05") ||
            month.equals("07") || month.equals("08") || month.equals("10") || month.equals("12")) {
            day = 31;
        }else if (month.equals("02")) {
            if (((nYear % 4 == 0) && (nYear % 100 != 0))|| (nYear % 400 == 0)) {
                day = 29;
            }else {
                day = 28;
            }
        }else {
            day = 30;
        }

        return day;
    }

    /**
     * 날짜(YYYYMMDD)Format을(YYYYMM)형식으로 변환
     * @param  data YYYYMMDD형식
     * @return data.substring(0,6) YYYYMM형식
     * @see Util
     */
    public static String getYMDate(String data) {
        return data.substring(0,6);
    }

    /**
     * Split함수
     * @param target 목표 문자열
     * @param str:구분자
     * @return vtCsvTemp 구분된 문자열
     */
    public static Vector split(String target,String str){
        StringTokenizer token = new StringTokenizer(target,str,true); //읽을라인을 ","로 항목별로 토큰을 만든다

        Vector vtCsvTemp = new Vector();          // 토큰을 분리해낼 벡터

        //널이거나 문자가 없을경우는 size가 0인 벡터를 넘긴다
        if ((target == null) || (target.equals(""))){
            return vtCsvTemp ;
        }

        //토큰이 하나도 없을 경우는 해당문자열을 담은 size가 1인 벡터를 넘긴다
        if (token.hasMoreTokens()==false){
            vtCsvTemp.addElement(target);
            return vtCsvTemp;
        }

        //첫번째토큰값을 구해서 값이있는지 ","인지를 체크한다
        String oldToken = token.nextToken().trim();
        // 첫번째 토큰이","일경우 공백을 넣어준다
        if (oldToken.equals(str)){
            vtCsvTemp.addElement("");
            // 첫번째 토큰이 값이 있을경우 값을 넣어준다
        }else{
            vtCsvTemp.addElement(oldToken);
        }

        while(token.hasMoreTokens()){
            String tempToken = token.nextToken().trim();
            // 현재토큰이 ","이면 앞의 값을 체크해서 앞의값이","이아니면 처리하지않고 앞의 값이 ","이면 널이므로 공백처리
            if (tempToken.equals(str)){
                //앞의 값도 ","이면 그값은 널이다
                if (oldToken.equals(str)){
                    vtCsvTemp.addElement("");
                }
                // 값이 있는경우는 값을 넣는다
            }else{
                vtCsvTemp.addElement(tempToken);
            }
            oldToken = tempToken;  // 이전데이터 비교를 위해 설정
        }
        return vtCsvTemp;  //구분된 항목의 벡터를 리턴
    }

    /**
     * o문자를 n문자로 치환한다
     * @param target 대상문자열
     * @param o 바꿀대상문자열
     * @param n 바뀔문자열
     * @return 치환된문자열
     */
    public static String replace(String target, String o, String n){
        StringBuffer result = new StringBuffer();
        int idx = 0;
        if ((target == null) || target.equals("")){
            return "";
        }
        //문장이 끝나지 않았으면 재귀호출한다
        while ((idx=target.indexOf(o)) != -1) {
            result.append(target.substring(0,idx));
            result.append(n);
            target = target.substring(idx+o.length());

            idx = target.indexOf(o);
        }
        result.append(target);

        return result.toString();

    }

    /**
     * 대상 문자열의 strOld문자열을 strNew로 변환해 준다.(게시판등의 내용에 사용)
     * @param target 대상문자열
     * @param strOld 변환될문자
     * @param strNew 변환할문자
     * @return sb 변환된 문자열
     */
    public static String convertString (String target,String strOld,String strNew) {
        StringBuffer sb = new StringBuffer();

      if ((target == null) || target.equals("")) {
           return "";
       }

        int idx = 0;

        while((idx = target.indexOf(strOld)) > -1){
            sb.append(target.substring(0,idx));
            sb.append(strNew);
            target = target.substring(idx+1);
        }
        sb.append(target);
        return sb.toString();
    }

     public static String usf_replace(String src, String oldstr, String newstr) {
          if (src == null) return null;

          StringBuffer dest = new StringBuffer("");

          try {
                      int  len = oldstr.length();
                      int  srclen = src.length();
                      int  pos = 0;
                      int  oldpos = 0;

                      while ((pos = src.indexOf(oldstr, oldpos)) >= 0) {
                                  dest.append(src.substring(oldpos, pos));
                                  dest.append(newstr);
                                  oldpos = pos + len;
                      }

                      if (oldpos < srclen)
                                  dest.append(src.substring(oldpos, srclen));

          } catch ( Exception e ) {
                      e.printStackTrace();
          }
          return dest.toString();
     }

    /**
     * 텍스트 데이터의 내용을 화면에 보여주기 위해서 적절히 변환한다
     * @param target 대상문자열
     * @return result 변환된 결과 문자열
     */
    public static String convertText_NO(String s1){

		s1 = usf_replace(s1,  "<", "&lt");
        s1 = usf_replace(s1,  ">", "&gt");
        s1 = usf_replace(s1,  "(", "&#40");
        s1 = usf_replace(s1,  ")", "&#41");
        s1 = usf_replace(s1,  "#", "&#35");
        s1 = usf_replace(s1,  "&", "&#38");

        return s1;
    }

    /**
     * 텍스트 데이터의 내용을 화면에 보여주기 위해서 적절히 변환한다
     * @param target 대상문자열
     * @return result 변환된 결과 문자열
     */
    public static String convertText(String s){
        String s1 = "";
		s1 = usf_replace(s, "<font size", "<font size");
        s1 = convertString(s1, " ", "&nbsp;");
        s1 = usf_replace(s1, " ", " ");
        s1 = convertString(s1, "\n", "<BR>");
        return s1;
    }

    public static String convertText_br(String s){
        s = convertString(s, "\n", "<BR>");
        return s;
    }

    public static String convertText_2(String s){
        String s1 = "";
        return s1;
    }

    /**
     * 제목등을 일정길이만 보여주고 뒤는 (...으로 변환한다)
     * @param str 대상문자열
     * @param size 보여줄 최대길이
     * @return sb 변환된 문자열
     */
    public static String toTruncate(String str, int size){
        StringBuffer sb = new StringBuffer();
        if ((str == null) || str.equals("")){
            return "";
        }
        if(str.length() > size){
            sb.append(str.substring(0, size));
            sb.append("...");
        } else {
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * 널인경우 널값을 ""으로 바꾼다
     * @param str 대상문자열
     * @return result 변경된 문자열
     */
    public static String convertNull(String str){
        if (str == null){
            return "";
        }
        return str.trim();
    }

    /**
     * 주민등록번호의 앞뒤를 분리한다
     * @param str 대상문자열
     * @return result 변경된 문자열
     */
    public static String[] parseRegist(String str){
        String[] result = {"",""};
        if ((str != null) && (!str.equals(""))){
            result[0] = str.substring(0,6);
            result[1] = str.substring(6);
        }
        return result;
    }

    public static int checkImg(String s)
    {
        int i = 0;
        if(s != null && !s.equals(""))
        {
            int j = s.indexOf("예악당");
            if(j != -1)
                return 1;
            j = s.indexOf("우면당");
            if(j != -1)
                return 2;
            j = s.indexOf("야외마당");
            if(j != -1)
                return 3;
            j = s.indexOf("\uB0A8\uC6D0");
            if(j != -1)
                return 4;
            j = s.indexOf("\uBBFC\uC18D");
            if(j != -1)
                return 4;
        }
        return i;
    }

    public static int checkImg(String s, String ss)
    {
        int i = 0;
        if(s != null && !s.equals(""))
        {
            int j = s.indexOf("예악당");
            if(j != -1){
            	  j = ss.indexOf("대관공연");
            	  if(j != -1)
            	  	return 1;
                return 2;
            }

            j = s.indexOf("우면당");
            if(j != -1){
            	  j = ss.indexOf("대관공연");
            	  if(j != -1)
            	  	return 3;
                return 4;
            }

            j = s.indexOf("별맞이터");
            if(j != -1) {
            	  j = ss.indexOf("대관공연");
            	  if(j != -1)
            	  	return 5;
                return 6;
            }

            j = s.indexOf("\uBBFC\uC18D");
            if(j != -1) {
                return 4;
            }
        }
        return i;
    }
    /**
     * DB에 저장된 이메일에서 아이디를 반환한다.
     * @author 정규원
     * @param emailAddress 이메일주소
     * @return emailId 이메일아이디
     */
    public static String splEmail(String emailAddress){
    	String emailId =null;
    	String emailAddr = DataUtil.notnull(emailAddress);
    		for(int i = 0 ; i < emailAddr.length();i++){
    			if(emailAddress.indexOf("@")==i){
    				emailId = emailAddr.substring(0,i);
    			}
    		}
    	return emailId;
    }
    /**
     * DB에 저장된 이메일에서 서비스 주소를 반환한다.
     * @author 정규원
     * @param emailAddress 이메일 주소
     * @return emailSvc 이메일서비스 주소
     */
    public static String splEmailSvc(String emailAddress){
    	String emailSvc = null;
    	String emailAddr = DataUtil.notnull(emailAddress);
    		for(int i = 0; i < emailAddr.length();i++){
    			if(emailAddress.indexOf("@")==i){
    				emailSvc = emailAddr.substring(i+1,emailAddr.length());
    			}
    		}


    	return emailSvc;
    }
    /**
     * DB에 저장된 전화번호를 받아 앞에 지역번호등의 앞자리를 잘라서 반환한다.
     * @author 정규원
     * @param phone DB에 저장된 full 전화번호(01012341234)
     * @return result 리턴되는 전화번호 맨앞자리
     */
    public static String splPhone1(String phone){
    	String result = "";
    	phone = DataUtil.notnull(phone);
    	if(phone.equals("")){
    	}else{
    		if(phone.charAt(1)=='2'){
    			result = "02";
    		}else{
    			result = phone.substring(0,3);
    		}
    	}
    	return result;
    }
    /**
     * DB에 저장된 전화번호를 받아 전화번호 중간을 리턴한다.
     * @author 정규원
     * @param phone DB에 저장된 full 전화번호(01012341234)
     * @return result 리턴되는 전화번호 중간번호
     */
    public static String splPhone2(String phone){
    	String basket = DataUtil.notnull(phone);
    	int end = 0;
    	String result = "";
	    	if(phone.equals("")){
	    	}else{
	    		if(phone.charAt(1)=='2'){
	    			basket = phone.substring(2,basket.length());
	    			basket = basket.trim();
	    		}else{
	    			basket = phone.substring(3,phone.length());
	    			basket = basket.trim();
	    		}
	    	}

	    	if(phone.equals("")){
	    	}else{
		    	if((basket.length())==7){
		    		result = basket.substring(0,3);
		    	}else if(basket.length()==8){
		    		result = basket.substring(0,4);
		    	}
	    	}


    	return result;
    }
    /**
     * DB에 저장된 전화번호를 받아 전화번호 뒷자리를 리턴한다.
     * @author 정규원
     * @param phone DB에 저장된 full 전화번호(01012341234)
     * @return result 리턴되는 전화번호 뒷자리
     */
    public static String splPhone3(String phone){
    	String value = DataUtil.notnull(phone);
      	String result = "";
    	int last = value.length();
    	if(value.equals("")){
    	}else{
    			result = value.substring(last-4,last);
    			result = result.trim();
    	}
    	return result;
    }
    /**
     * DB에 저장된 생년월일를 받아 생년을 리턴한다.
     * @author 정규원
     * @param birth DB에 저장된 생년월일(20130220)
     * @return result 리턴되는 생년
     */
    public static String splBirth1(String birth){
    	String value = DataUtil.notnull(birth);
    	String result = "";
    	if(value.equals("")){
    	}else{
    			result = value.substring(0,4);
    	}
    	return result;
    }
    /**
     * DB에 저장된 생년월일를 받아 생월을 리턴한다.
     * @author 정규원
     * @param birth DB에 저장된 생년월일(20130220)
     * @return result 리턴되는 생월
     */
    public static String splBirth2(String birth){
    	String value = DataUtil.notnull(birth);
    	String result = "";
    	if(value.equals("")){
    	}else{
    			result = birth.substring(4,6);

    	}
    	return result;
    }
    /**
     * DB에 저장된 생년월일를 받아 생일을 리턴한다.
     * @author 정규원
     * @param birth DB에 저장된 생년월일(20130220)
     * @return result 리턴되는 생월
     */
    public static String splBirth3(String birth){
    	String value = DataUtil.notnull(birth);
    	String result = "";
    	if(value.equals("")){
    	}else{
    			result = birth.substring(6,8);
    	}
    	return result;
    }

    public static String splZipcode1(String zipcode){
    	String value = DataUtil.notnull(zipcode);
    	String result = "";
    	if(value.equals("")){
    		for(int i = 0 ; i < value.length();i++){
    			if(value.charAt(i)=='-'){
    				result = value.substring(0,i);
    				return result;
    			}
    		}
    	}
    	return result;
    }

    public static String splZipcode2(String zipcode){
    	String value = DataUtil.notnull(zipcode);
    	String result = "";
    	if(value.equals("")){
    		for(int i = 0 ; i < value.length();i++){
    			if(value.charAt(i)=='-'){
    				result = value.substring(i+1,value.length());
    				return result;
    			}
    		}

    	}
    	return result;
    }

	/**
	 * 지정한 길이 만큼의 숫자 형식의 문자열을 만든다.
	 * @param value		처리할 수치.
	 * @param type		형식.
	 * @return			처리된 수치 문자열.
	 */
	public static String number(int value) {
		return new DecimalFormat("###,###,###,###").format(value);
	}


    private static String strFile_ = "";
}
