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

	public class ConfirmotpAction extends CommonAction{

		public ActionForward doExecute(
				ActionMapping mapping
				, ActionForm form
				, HttpServletRequest request
				, HttpServletResponse response
				, Map param
			){

	
			try{
				String resultMsg = "";
				System.out.println("호출 페이지는 /otpLOconf.do 입니다.");
				
				OTPDao dao = new OTPDao();
				MainDao dao2 = new MainDao();
				String inputid = 	request.getParameter("stuNum");
				String inputotp = 	request.getParameter("otpNum");
				
				System.out.println("입력받은 학번 :"+inputid+ "입력받은 오티피 = "+inputotp);
				
				List otpList = (List) dao.otpGetDB(inputid,inputotp);
				Map countInfo = dao.otpGetDBCount(inputid, inputotp);
				int totalNum = Integer.parseInt(countInfo.get("total").toString());			
				System.out.println("get Row Count : " + totalNum);
				
				if(totalNum == 1){
					System.out.println("OTP 로그인 되었습니다.");
					resultMsg="OTP 로그인 되었습니다.";
				}else{
					System.out.println("일치하는 정보가 없습니다. 로그인 정보를 확인해주십시오.");
					resultMsg="일치하는 정보가 없습니다. 로그인 정보를 확인해주십시오.";
				}
				request.setAttribute("resultMsg", resultMsg);
			
			}catch(Exception e){
				logger.error(e);
			}

				return mapping.findForward("otpLoginConfirm");
			}

	}

