package finals.comm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONObject;

import java.lang.*;
public class LogoutAction extends CommonAction{
	

	public ActionForward doExecute(
			ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response
			, Map param
		){
	
		try{
			HttpSession session = request.getSession();
			session.invalidate(); 
		}catch(Exception e){
			logger.error(e);
		}
		
		return mapping.findForward("logout");
		
		}

}