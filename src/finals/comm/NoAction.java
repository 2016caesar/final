package finals.comm;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class NoAction extends CommonAction
{
	public ActionForward doExecute(
			ActionMapping mapping
			, ActionForm form
			, HttpServletRequest req
			, HttpServletResponse res
			, Map param
		){

			return mapping.findForward("noaction");
		}

}
