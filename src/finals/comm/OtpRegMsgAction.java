package finals.comm;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
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

				System.out.println("여기는 /otpRegMsg.do 입니다.");
				//안드로이드에서 파라미터 넘어온 학번 및 메일인증코드를 받는다.
				//DB값 비교.
		      
				String stuNum = 	request.getParameter("stuNum");
				String mailCode = 	request.getParameter("mailCode");
				//stuNum = "201011063";
				//mailCode = "xxx";
				//DB조회  - 메일 코드 확인
				OTPRegDao regDao = new OTPRegDao();
				List<Map<String, Object>> mailCodeList = (List<Map<String, Object>>) regDao.mailCodeCompare(stuNum, mailCode);
				System.out.println(mailCodeList);
				//DB조회 - 메일 코드 확인 갯수
				Map countInfo = regDao.mailCodeCompareCount(stuNum, mailCode);
				int totalNum = Integer.parseInt(countInfo.get("total").toString());			
				System.out.println("get Row Count : " + totalNum);
				
				JSONObject jsonMain = new JSONObject();
				//JSONArray jArray = new JSONArray();
				JSONObject jObject = new JSONObject();
				if(totalNum == 1){
					for(int i = 0 ; i < totalNum; i++){
						Map element = new HashMap();
						element = mailCodeList.get(i);
						System.out.println("element : "+element);
						String eStuNum = (String) element.get("stuNum");
						String eMailCode =  (String) element.get("mailCode");
						String eMailTime = (String) element.get("mailTime");
						//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						//String emailTimeTrans = (String) df.format(eMailTime);
						System.out.println("======================================");
						System.out.println("eStuNum : "+ eStuNum);
						System.out.println("eMailCode : "+ eMailCode);
						System.out.println("eMailTime : "+ eMailTime);
						//System.out.println("emailTimeTrans : "+ emailTimeTrans);
						System.out.println("======================================");
					}	
					jObject.put("verified","succeed");
					System.out.println("유저정보 출력 End");// size = 1 이면 인증완료
					//AES 키 정보 테이블  insert
					OTPUtil otpUtil = new OTPUtil();
					String aesEnc = otpUtil.AESEncrypt(stuNum);
					int AESInsertResult = regDao.AESKeyInsert(stuNum,aesEnc);
					String AESInserted = "fail";
					//AES DB insert
					if(AESInsertResult == 1){
						AESInserted = "succeed";
					}else{
						AESInserted = "fail";
					}
					System.out.println("AESInsertResult : "+ AESInsertResult);// size = 1 이면 인증완료
					jObject.put("AESInserted",AESInserted);
					//otp 정보 테이블  insert
					int OTPInsertedResult = regDao.OTPInfoInsert(stuNum);
					String OTPInserted = "fail";
					//otp DB insert
					if(OTPInsertedResult == 1){
						OTPInserted = "succeed";
					}else{
						OTPInserted = "fail";
					}
					System.out.println("OTPInsertedResult : "+ OTPInsertedResult);// size = 1 이면 인증완료
					jObject.put("OTPInserted",OTPInserted);
					
					jObject.put("count", totalNum+"");
					jObject.put("stuNum", stuNum+"");	
					jObject.put("comment", "메일 인증이 완료되었습니다."+"");
					
				}else{
					jObject.put("verified","fail");
					jObject.put("AESInserted","fail");
					jObject.put("OTPInserted","fail");
					jObject.put("count", totalNum+"");
					jObject.put("stuNum", stuNum+"");
					jObject.put("comment", "메일 인증이 일치 하지 않습니다."+"");
					System.out.println("노일치");
				}
				//jArray.add(0,jObject);
				jsonMain.put("data",jObject);
				request.setAttribute("jsonData", jsonMain.toJSONString());
				System.out.println("JSON String" +jsonMain.toJSONString());
				

			}catch(Exception e){
				logger.error(e);
			}	
			return mapping.findForward("otpRegMsg");
		}
}
