package finals.comm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.codec.binary.Base64;

import finals.util.AES128;
import finals.util.HmacSha256;
import finals.util.SendMail;
public class MainAction extends CommonAction{

	public ActionForward doExecute(
			ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response
			, Map param
		){

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