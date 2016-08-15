package finals.comm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainDao extends CommonDao{

	public List<Map<String, String>> MainGetDB(Map param){

		    List<Map<String, String>> result = null;
	    	Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        StringBuffer query = new StringBuffer();

	        try{

	        	conn = getJndi();
	            query.setLength(0);
	            query.append("select `U_SEQ` as u_seq,`U_NO` as u_no, `U_PASS` as u_pass,\n");
	            query.append("`U_C_TIME` as u_c_time, `U_MAIL` as u_mail\n");
	          	query.append(" from c_user_info;	\n");
	          	query.append("\n");
	          	query.append("\n");

	          	pstmt = conn.prepareStatement(query.toString());
	          	System.out.println(query.toString());
	          	rs = pstmt.executeQuery();
	            result = setDatas(rs);
	            System.out.println(result);
	            
	            
	        }catch(Exception e){
	        	e.printStackTrace();
	        	logger.error(e);
	        }finally{
	        	setClose(conn,pstmt,rs);
	        }

	    	return result;
	    }

	public List<Map<String, String>> MainGetDB2(Map param){

		List<Map<String, String>> result = null;
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        StringBuffer query = new StringBuffer();

        try{

        	conn = getJndi();
            query.setLength(0);
            query.append("select *\n");
          	query.append(" from c_otp_authen;	\n");
          	query.append("\n");
          	query.append("\n");

          	pstmt = conn.prepareStatement(query.toString());
          	System.out.println(query.toString());
          	rs = pstmt.executeQuery();
            result = setDatas(rs);
            System.out.println(result);
            
            
        }catch(Exception e){
        	e.printStackTrace();
        	logger.error(e);
        }finally{
        	setClose(conn,pstmt,rs);
        }

    	return result;
    }
	
}
