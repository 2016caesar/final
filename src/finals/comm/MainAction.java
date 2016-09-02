package finals.comm;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
public class MainAction extends CommonAction{

	public ActionForward doExecute(
			ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response
			, Map param
		){//수정 by jung
		 
		try{
			request.setCharacterEncoding("UTF-8");
			
			HttpSession session = request.getSession();
			System.out.println("여기부터 MainAction");
			response.setCharacterEncoding("UTF-8");
			PrintWriter w = response.getWriter();
			
			MainDao dao = new MainDao(); 
			if((String)session.getAttribute("mail")== null){
				return mapping.findForward("main");
			}

			// Seed for HMAC-SHA256 - 32 bytes
			else{
				return mapping.findForward("session");
			}

		}catch(Exception e){
			logger.error(e);
		}

			return null;
		}

}

//커밋 테스트용 주석