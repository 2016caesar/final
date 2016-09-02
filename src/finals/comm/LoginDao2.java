package finals.comm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginDao2 extends CommonDao{

	public List<Map<String, Object>> getuser(String stuNum,String pw){

		List<Map<String, Object>> result = null;
	    	Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        StringBuffer query = new StringBuffer();

	        try{
	        	System.out.println("여기부터 LoginDao2");
	        	System.out.println("stuNum : "+stuNum);
	        	System.out.println("pw : "+pw);
	        	conn = getJndi();
	            query.setLength(0);
	            
	            query.append("select \n");
	            query.append("U_NO AS stunum,\n");
	            query.append("U_MAIL AS mail,\n");
	            query.append("U_C_TIME AS time\n");
	            //query.append("U_PASS AS password\n");
	          	query.append("from mylist.c_user_info\n");
	        	query.append("where U_NO = '"+stuNum+"'\n");
	        	query.append("AND U_PASS = '"+pw+"'\n");
	        	query.append(";\n");
	        	
	       
	          	pstmt = conn.prepareStatement(query.toString());
	          	System.out.println(query.toString());
	          	rs = pstmt.executeQuery();
	            result = setDatas(rs);
	            System.out.println(result);
	            
	            
	        }catch(Exception e){
	        	e.printStackTrace();
	        	logger.error(e);
	        }finally{
	        	setClose(conn,pstmt);
	        }

	    	return result;
	    }
	}