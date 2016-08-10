package finals.comm;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import finals.util.OTPUtil;

	public class CreateotpAction extends CommonAction{

		public ActionForward doExecute(
				ActionMapping mapping
				, ActionForm form
				, HttpServletRequest request
				, HttpServletResponse response
				, Map param
			){

	
			try{
				
				OTPUtil otpUtil = new OTPUtil();	
				String code = (String) otpUtil.serverOTPGet("201011032");
				
				OTPDao otpDao = new OTPDao();
				int insertCert = otpDao.otpSetDB("201011032",code);
				System.out.println("============================== ");
				System.out.println("inserted YN : " + insertCert);
				System.out.println("============================== ");
				
				System.out.println("Created OTP : " + code);
			    request.setAttribute("CreatedOTP", code);
			    
			    MainDao dao = new MainDao();
			    
			    List<HashMap<String,String>> user =  dao.MainGetDB(param);
			  
				List<HashMap<String,String>> test =  dao.MainGetDB2(param);
				
				
				request.setAttribute("userList", user);
			
			}catch(Exception e){
				logger.error(e);
			}

				return mapping.findForward("create_otp");
			}

	}

