package finals.comm;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


			// Seed for HMAC-SHA256 - 32 bytes
	      
			MainDao dao = new MainDao(); 

		}catch(Exception e){
			logger.error(e);
		}

			return mapping.findForward("main");
		}

}

//커밋 테스트용 주석