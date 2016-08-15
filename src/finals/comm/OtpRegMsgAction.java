package finals.comm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import finals.util.OTPUtil;

	public class OtpRegMsgAction extends CommonAction{

		public ActionForward doExecute(
				ActionMapping mapping
				, ActionForm form
				, HttpServletRequest request
				, HttpServletResponse response
				, Map param
			){

	
			try{
				
				System.out.println("호출 페이지는 /otpRegMsg.do 입니다.");
				
		
				MainDao dao2 = new MainDao();
				String inputid = 	request.getParameter("uid");
				String inputcode = 	request.getParameter("mailcode");
				
				
				JSONObject jsonMain = new JSONObject();
				JSONArray jArray = new JSONArray();
				JSONObject jObject = new JSONObject();
				
				
				
				System.out.println("입력받은 학번 :"+inputid+ "입력받은 코드 = "+inputcode);
				
				request.setCharacterEncoding("UTF-8");
				
				String uid = request.getParameter("uid");
				String mailcode = request.getParameter("mailcode");
				
				System.out.println("학번 : "+uid);
				System.out.println("코드 : "+mailcode);
			
				Class.forName("com.mysql.jdbc.Driver");
	
				Connection conn = DriverManager.getConnection("jdbc:mysql://http:192.168.0.6:3306/mylist", "root", "1234");
				Statement stmt = conn.createStatement();
					
					String query = "select `U_NO`,`MA_CODE` from `mylist`.`c_mail_authen` where  `U_NO` ="+uid+"`MA_CODE` = "+mailcode;
					ResultSet rs = stmt.executeQuery(query);
					
					int i=0; while(rs.next()){
						
						String _uid = rs.getString("uid");
						String _mailcode=  rs.getString("mailcode");
						
						if(uid.equals(_uid)&&mailcode.equals(_mailcode)){
							i = 1;
							jObject.put("msg1","succed");
							jObject.put("msg2", "two");
							jObject.put("msg3", "three");					
						}
					}
					if(i==0){
						jObject.put("msg1", "failed");
						jObject.put("msg2", "two");
						jObject.put("msg3", "three");
					}
					stmt.close();
					conn.close();
					jArray.add(0,jObject);
					jsonMain.put("List",jArray);
					System.out.println(jsonMain.toJSONString());
		
			
			}catch(Exception e){
				logger.error(e);
			}

				return mapping.findForward("otpReg/otpRegMsg");
			}

	}

