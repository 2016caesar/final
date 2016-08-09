package finals.comm;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OtploginAction extends CommonAction{

	public ActionForward doExecute(
			ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response
			, Map param
		){

		try{

			MainDao dao = new MainDao();

			//--- Map 타입
			//request.setAttribute("test", dao.MainGetDB(param));
			//param.put("test","레이어");
			

		}catch(Exception e){
			logger.error(e);
		}

			return mapping.findForward("otp_login");
		}

}