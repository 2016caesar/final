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
public class LoginAction extends CommonAction{
	private String name,pw,mail,time;
	public void setValue(String i, String p){
		this.name=i;
		this.pw=p;
	}
	public void setValue2(String m, String t){
		this.name=m;
		this.pw=t;
	}
	public String getid(){
		return name;
	}
	public String getpw(){
		return pw;
	}
	public String getmail(){
		return mail;
	}
	public String gettime(){
		return time;
	}

	public ActionForward doExecute(
			ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response
			, Map param
		){
	
		try{
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			System.out.println("여기부터 LoginAction");
			//String userid = request.getParameter("uid");
			//String userpw = request.getParameter("pwd");
			
			//LoginDao lgdao = new LoginDao();
			//LoginDao2 lgdao2 = new LoginDao2();
			//List<Map<String, Object>> userlist = (List<Map<String, Object>>)  lgdao.pwcompare(userid,userpw);
			//System.out.println("userlist"+userlist);
			
		//	Map ucountinfo = lgdao.pwcomparecount(userid, userpw);
		//	int utotalNum = Integer.parseInt(ucountinfo.get("total").toString());
		//	System.out.println("get count: "+ utotalNum);
			
		//	List<Map<String, Object>> userlist = (List<Map<String, Object>>)  lgdao2.getuser(userid,userpw);
		//	System.out.println("userlist"+userlist);
			response.setCharacterEncoding("UTF-8");
			PrintWriter w = response.getWriter();
			//request.setAttribute("test", dao.MainGetDB(param));
			//param.put("test","레이어");
			//JSONObject jsonMain = new JSONObject();
			//JSONObject jObject = new JSONObject();
			if((String)session.getAttribute("mail")== null){
			
				
				if(request.getParameter("uid")==null){
					System.out.println("세션없음");
					w.println("<script type='text/javascript'>");
					w.println("alert('logout');");
					w.println("location.href='/main.do';</script>");
				}
				
				//List<Map<String, Object>> userlist = (List<Map<String, Object>>)  lgdao.pwcompare(userid,userpw);
				//System.out.println("userlist"+userlist);
				else{
					String userid = request.getParameter("uid");
					String userpw = request.getParameter("pwd");
					LoginDao lgdao = new LoginDao();
					LoginDao2 lgdao2 = new LoginDao2();
					Map ucountinfo = lgdao.pwcomparecount(userid, userpw);
					int utotalNum = Integer.parseInt(ucountinfo.get("total").toString());
					System.out.println("get count: "+ utotalNum);
				
					List<Map<String, Object>> userlist = (List<Map<String, Object>>)  lgdao2.getuser(userid,userpw);
					System.out.println("userlist"+userlist);
			     
					if(utotalNum==1){
						session.setAttribute("id",userid);
						session.setAttribute("pw", userpw);
						name =(String)session.getAttribute("id");
						pw = (String)session.getAttribute("pw");
						System.out.println("아이디"+name+"pw"+pw);
						setValue(name,pw);
					
						for(int i = 0 ; i < utotalNum; i++){
							Map element = new HashMap();
							element = userlist.get(i);
							System.out.println("element : "+element);
					
							String eMail =  (String) element.get("mail");
							String eTime = (String) element.get("time");
							session.setAttribute("mail",eMail);
							session.setAttribute("time",eTime);
							mail =(String)session.getAttribute("mail");
							time = (String)session.getAttribute("time");
							System.out.println("메일"+mail+"시간"+time);
							setValue2(mail,time);
					
							System.out.println("======================================");
							System.out.println("Mail "+ getmail());
							System.out.println("Time : "+ gettime());
							//System.out.println("emailTimeTrans : "+ emailTimeTrans);
							System.out.println("======================================");
						}
						return mapping.findForward("login");
					}
				
					else
					{	
						System.out.println("아이디 또는 비밀번호가 잘못되었습니다");
						w.println("<script type='text/javascript'>");
						w.println("alert('id or password incorrect');");
						w.println("location.href='/main.do';</script>");
					}
				}	
				
			}
			
			else{	
				
				return mapping.findForward("login");
			} 
			
				
				//Map ucountinfo1 = lgdao.pwcomparecount(getid(), getpw());
				//System.out.println("=======");	
				//System.out.println(getid());
				//int utotalNum1 = Integer.parseInt(ucountinfo1.get("total").toString());
				//System.out.println("=======");	
				////System.out.println(utotalNum1);
				
		
				
			
				//jObject.put("verified","fail");
				//jObject.put("count", utotalNum+"");
				//jObject.put("stuNum", userid+"");
				//jObject.put("comment", "아이디 또는 비밀번호가 잘못되었습니다"+"");
				
			
			//jsonMain.put("data",jObject);
			//request.setAttribute("jsonData", jsonMain.toJSONString());
			//System.out.println("JSON String" +jsonMain.toJSONString());
		}catch(Exception e){
			logger.error(e);
		}
		
		return null;
		
		}

}