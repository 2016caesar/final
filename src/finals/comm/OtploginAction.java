package finals.comm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import finals.util.AES128;
import finals.util.HmacSha256;

public class OtploginAction extends CommonAction{

	public ActionForward doExecute(
			ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response
			, Map param
		){

		
		try{


			// Seed for HMAC-SHA256 - 32 bytes
	        String seed32 = "3132333435363738393031323334353637383930" +
	         "313233343536373839303132";
			
	        AES128 aes128 = new AES128();
	        String crtaes = aes128.encrypt("201011032");
	        
	        
			MainDao dao = new MainDao();
			HmacSha256 sha256 = new HmacSha256();
			String steps = "0";
			long testTime = 1111111111L;
			long T0 = 0;
	        long X = 180;
	        
	        Date today = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	        df.setTimeZone(TimeZone.getTimeZone("UTC"));
	        
	        testTime = today.getTime()/1000;
	        
	        long T = (testTime - T0)/X;
	        steps = Long.toHexString(T).toUpperCase();
	        
			String code = sha256.generateTOTP256(crtaes, steps, "8");
			
			System.out.println("Created OTP : " + code);
		    request.setAttribute("CreatedOTP", code);
		    
		    
		    List<HashMap<String,String>> user =  dao.MainGetDB(param);
		    String dbinfo = "jdbc:mysql://localhost:3306/mylist";
		    String dbid = "root";
		    String dbpw= "1234";
		    		
			java.sql.Connection conn = java.sql.DriverManager.getConnection(dbinfo,dbid,dbpw);
		    java.sql.Statement stmt = conn.createStatement();
			String dbCommand = "update c_otp_authen set OTP_CODE = ("+code+")";
		    
			
			System.out.println(dbCommand);
			System.out.println("db Insert 실행 결과");
			System.out.println();
			//System.out.println(user);
			List<HashMap<String,String>> test =  dao.MainGetDB2(param);
			
			
			request.setAttribute("userList", user);
		
		}catch(Exception e){
			logger.error(e);
		}

			return mapping.findForward("otp_login");
		}

}