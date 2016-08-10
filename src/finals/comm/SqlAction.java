package finals.comm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import finals.comm.CreateotpAction;
import finals.comm.OTPDao;



public class SqlAction extends CommonAction{

	public ActionForward doExecute(
			ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response
			, Map param
		){

		try{

			
			MainDao dao = new MainDao();
		    OTPDao dao2 = new OTPDao();
		    CreateotpAction ctp = new CreateotpAction();
		    
			List<HashMap<String,String>> user =  dao.MainGetDB(param);
			
			//--- Map 타입
			
			System.out.println(user);
			request.setAttribute("userList", user);
			
			//request.setAttribute("test", dao.MainGetDB(param));
			//param.put("userList",user);
			


		}catch(Exception e){
			logger.error(e);
		}

			return mapping.findForward("mysql");
		}

}