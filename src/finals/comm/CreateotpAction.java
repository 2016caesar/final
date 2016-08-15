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
				
				String stuNum = (String) request.getAttribute("stuNum");
				OTPUtil otpUtil = new OTPUtil();
				//AES 를 DB 호출 - 호출할때 최신정보를 가져오도록한다. 그리고 1일 이상 된것은 지우도록한다.
				String aesEnc = "";
				
				String code = (String) otpUtil.serverOTPGet(aesEnc);
				
				OTPDao otpDao = new OTPDao();
				int insertCert = otpDao.otpSetDB(stuNum,code);
				System.out.println("============================== ");
				System.out.println("inserted YN : " + insertCert);
				System.out.println("============================== ");
				
				System.out.println("New Created OTP : " + code);
			    request.setAttribute("CreatedOTP", code);
			    
			    MainDao dao = new MainDao();
			    
			    List<Map<String, String>> user =  dao.MainGetDB(param);
			  
				List<Map<String,String>> test =  dao.MainGetDB2(param);
				
				
				request.setAttribute("userList", user);
			
			}catch(Exception e){
				logger.error(e);
			}

				return mapping.findForward("create_otp");
			}

	}

