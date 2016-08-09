package finals.comm;


import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import finals.util.EgovDateUtil;


/**
 **********************************************************
 * --History--
 * Description :
 * 	로그인 (세션) 여부에 따른 페이지 이동 처리
 * 	Request 객체를 통한 파라미터 셋팅 처리 (Map)
 * 	정상적인 프로세스 진행인 경우 doExcute() 메소드 호출
 *---------------------------------------------------------
 * @version 1.00  2010. 11. 13
 * @author 	cracky
 **********************************************************/
public abstract class CommonAction extends Action {
	/** 로깅 객체 */
	public final Log logger = LogFactory.getLog(getClass());

	public String today = EgovDateUtil.getToday();
	public String personReserveMinDay = EgovDateUtil.addDay(today, 1);
	public String personReserveMaxDay = EgovDateUtil.addDay(today, 14);

	public String groupReserveMinDay = EgovDateUtil.addDay(today, 15);
	public String groupReserveMaxDay = EgovDateUtil.addDay(today, 180);

	public String dailyProgramdefaultMaxDay = EgovDateUtil.addDay(today, 14);

	/**
	 * 로그인 여부 체크
	 * @param request
	 * @param sid (세션 체크 키값)
	 * @return boolean
	 * @throws Exception
	 */
	public boolean checkLogUsr(HttpServletRequest req,String sid) throws Exception {
		boolean f = false;
		HttpSession ss = req.getSession();
		if(ss.getAttribute(sid) != null && !((String)ss.getAttribute(sid)).trim().equals("")) f = true;
		return f;
	}

	/**
	 * Request 객체를 이용하여 넘어오는 모든 파라미터값을 key 와 value의 형태로 셋팅한다.
	 * @param  HttpServletRequest request
	 * @return Map
	 */
	public Map setParamValues(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			Enumeration<String> en = request.getParameterNames();
			while(en.hasMoreElements()){
				String p_name  = en.nextElement();
				String valus[] = request.getParameterValues(p_name);
				if(valus!= null && valus.length == 1)
					params.put(p_name, valus[0]);
				else
					params.put(p_name, valus);
			}

		} catch(Exception e) {
			logger.error(e.toString());
		}
		return params;
	}

	/**
	 * 맵 객체 안에 담겨있는 파라미터를 화면상에 출력한다.
	 * @param  Map
	 */
	public void printParams(Map map){
		Set keyset = map.keySet();
		String key = "";
		Object val = null;
		java.util.Iterator<String> itr = keyset.iterator();
		while(itr.hasNext()) {
			key = itr.next();
			val = map.get(key);
			if(val instanceof String)
				System.out.println(key + " = " + val.toString());
			else if(val instanceof String[]) {
				String[] vals = (String[])val;
				for(int i = 0 ; i < vals.length ; i++) {
					System.out.println(key+"["+i+"] = "+vals[i] );
				}
			} else {
				System.out.println(key + " = " + val);
			}
		}
	}

	/**
	 * 메세지와 함께 페이지를 이동한다.
	 * @param res
	 * @param msg
	 * @param path
	 * @throws Exception
	 */
	public void setMsg(HttpServletResponse res ,String msg, String path){
		try{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html;charset=utf-8");	// 한글이 깨질 경우 프로젝트에서 사용하는 문자셋으로 바꿔줄것 ! ex)euc-kr
		pw.println("<script>alert('"+msg+"');document.location.href='"+path+"';</script>");
		}catch(Exception e){
			logger.error(e);
		}
	}

	/**
	 * 로그인 체크 및 파라미터 셋팅
	 * (기본 스트러츠 액션 구현 메서드)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			   HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map param = null;

		request.setAttribute("serverMode", new ParamSetting().serverMode); //서버 구분 셋팅 : TEST,REAL

		// 로그인 사용자가 아닌경우 로그인 페이지로 이동 시킨다.   <global-forwards> 사용
	//	if(!checkLogUsr(request,"usr_id")){
	//		return mapping.findForward("login");
	//	}else{
			param = setParamValues(request); //정상적인 사용자인 경우 파라미터값을 param 객체에 셋팅한다.
			return doExecute(mapping, form, request, response, param);
	//	}
	}


	/**
	 * 현 클래스 상속시 doExecute 메서드를 구현해준다.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param param
	 * @return
	 */
	public ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,Map param){
		return null;
	}

}

