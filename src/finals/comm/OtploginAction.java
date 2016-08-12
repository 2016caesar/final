package finals.comm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
			OTPDao dao = new OTPDao();
			MainDao dao2 = new MainDao();
			String inputid = 	request.getParameter("uid");
			String inputotp = 	request.getParameter("otpw");
			
			System.out.println("입력받은 학번 :"+inputid+ "입력받은 오티피 = "+inputotp);
			
			int insertCert = dao.otpGetDB(inputid,inputotp);
			System.out.println("inserted YN : " + insertCert);
			
		    List<HashMap<String,String>> user =  dao2.MainGetDB(param);
			
		}catch(Exception e){
			logger.error(e);
		}
		
			return mapping.findForward("otp_login");
		}

}