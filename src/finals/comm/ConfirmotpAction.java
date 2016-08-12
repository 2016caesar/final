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
				
				System.out.println("호출 페이지는 /otpLOconf.do 입니다.");
				
				OTPDao dao = new OTPDao();
				MainDao dao2 = new MainDao();
				String inputid = 	request.getParameter("stuNum");
				String inputotp = 	request.getParameter("otpNum");
				
				System.out.println("입력받은 학번 :"+inputid+ "입력받은 오티피 = "+inputotp);
				
				List<HashMap<String,String>> otpList = dao.otpGetDB(inputid,inputotp);
				System.out.println("get Row Count : " + otpList.size());
				if(otpList.size() == 1){
					System.out.println("로그인 되었습니다.");
				}else{
					System.out.println("일치하는 정보가 없습니다. 로그인 정보를 확인해주십시오.");
				}
				
			
			}catch(Exception e){
				logger.error(e);
			}

				return mapping.findForward("otpLoginConfirm");
			}

	}

