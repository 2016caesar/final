package finals.comm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class test extends CommonAction{

	public ActionForward doExecute(
			ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response
			, Map param
		){

		try{


			//안드로이드에서 파라미터 넘어온 학번 및 메일인증코드를 받는다.
			//DB값 비교.
	      
			String stuNum = 	request.getParameter("stuNum");
			String mailCode = 	request.getParameter("mailCode");
			//stuNum = "201011063";
			//mailCode = "xxx";
			//DB조회  - 메일 코드 확인
			OTPRegDao regDao = new OTPRegDao();
			List mailCodeList = (List) regDao.mailCodeCompare(stuNum, mailCode);
			//DB조회 - 메일 코드 확인 갯수
			Map countInfo = regDao.mailCodeCompareCount(stuNum, mailCode);
			int totalNum = Integer.parseInt(countInfo.get("total").toString());			
			System.out.println("get Row Count : " + totalNum);
			
			JSONObject jsonMain = new JSONObject();
			JSONArray jArray = new JSONArray();
			JSONObject jObject = new JSONObject();
			if(totalNum == 1){
				for(int i = 0 ; i < totalNum; i++){
					Map element = (HashMap) mailCodeList.get(i);
					String eStuNum = (String) element.get("stuNum");
					String eMailCode = (String) element.get("mailCode");
					Date eMailTime = (Date) element.get("mailTime");
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					String emailTimeTrans = (String) df.format(eMailTime);
					System.out.println("======================================");
					System.out.println("eStuNum : "+ eStuNum);
					System.out.println("eMailCode : "+ eMailCode);
					System.out.println("eMailTime : "+ eMailTime);
					System.out.println("emailTimeTrans : "+ emailTimeTrans);
					System.out.println("======================================");
					
					jObject.put("verified","succeed");
					jObject.put("count", totalNum+"");
					jObject.put("stuNum", stuNum+"");										
				}
				System.out.println("일치");// size = 1 이면 인증완료
			}else{
				jObject.put("verified","fail");
				jObject.put("count", totalNum+"");
				jObject.put("stuNum", stuNum+"");
				System.out.println("노일치");
			}
			jArray.add(0,jObject);
			jsonMain.put("data",jArray);
			request.setAttribute("jsonData", jsonMain.toJSONString());
			System.out.println("JSON String" +jsonMain.toJSONString());
			

		}catch(Exception e){
			logger.error(e);
		}	
		return mapping.findForward("test");
	}

}