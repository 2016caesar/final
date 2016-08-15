package finals.comm;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import finals.util.SendMail;
public class MailAction extends CommonAction{

	public ActionForward doExecute(
			ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response
			, Map param
		){
		
		
		try{
			
			String umail = request.getParameter("useremail");
			String stuNum = request.getParameter("stuNum");
			//param.put("user", dao.MainGetDB(param));
			
			SendMail aa = new SendMail();
			request.setAttribute("userMail", umail); // jsp로 전송한다
			//request.setAttribute("stuNum", stuNum);
			
			int mailCode = aa.getAddr("aann217@gmail.com", "dksdyd5237", umail);// 계정 정보 입력
			
			
			
		}catch(Exception e){
			logger.error(e);
			
		}

			return mapping.findForward("mail");
		}

}