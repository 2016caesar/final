package finals.include.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import finals.comm.CommonAction;

public class IndexAction extends CommonAction
{
	public ActionForward doExecute(
			ActionMapping mapping
			, ActionForm form
			, HttpServletRequest req
			, HttpServletResponse res
			, Map param
		){
			return mapping.findForward("index");
		}

}