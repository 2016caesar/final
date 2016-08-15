package finals.comm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OTPRegDao extends CommonDao{
	
	/*public int otpSetDB(String stuNum,String otpNum){

		boolean result = false;
    	Connection conn = null;
        PreparedStatement pstmt = null;
        int rs = 0;

        StringBuffer query = new StringBuffer();

        try{

        	conn = getJndi();
            query.setLength(0);
            
          	query.append(" UPDATE `mylist`.`c_otp_authen` \n");
          	query.append("	 SET  \n");
          	query.append("`OTP_CODE` = '"+otpNum+"' , \n");
          	query.append("`OTP_TIME` = NOW()\n");
          	query.append("	 WHERE  \n");
          	query.append("	 `U_NO` = '"+stuNum+"';  \n");
          	query.append("	   \n");
          			

          	pstmt = conn.prepareStatement(query.toString());
          	System.out.println(query.toString());
          	rs = pstmt.executeUpdate();
           
            ResultSetMetaData metaData = rs.getMetaData();
            result = rs.rowInserted();
            System.out.println(result);
            
            
        }catch(Exception e){
        	e.printStackTrace();
        	logger.error(e);
        }finally{
        	setClose(conn,pstmt);
        }

    	return rs;
    }*/
	
	
	public List mailCodeCompare(String stuNum, String mailCode){
	
				List result = null;
		    	Connection conn = null;
		        PreparedStatement pstmt = null;
		        ResultSet rs = null;

		        StringBuffer query = new StringBuffer();

		        try{
		        	System.out.println("stuNum : "+stuNum);
		        	System.out.println("mailCode : "+mailCode);
		        	conn = getJndi();
		            query.setLength(0);
		            
		          	query.append(" select 	  \n");
		          	
		          	query.append("	 	MA_SEQ AS mailSeq,  \n");
		          	query.append("	 	U_NO AS stuNum,  \n");
		          	query.append("      MA_CODE AS mailCode,  \n");
		          	query.append("      MA_TIME  AS mailTime \n");
		          	
		          	query.append(" from mylist.c_mail_authen	 \n");
		          	query.append(" where `U_NO` = '"+stuNum+"'\n");
		          	query.append(" AND `MA_CODE` = '"+mailCode+"' \n");
		          	query.append(" ;\n");
		          	
		          			
		          	System.out.println(query.toString());
		          	pstmt = conn.prepareStatement(query.toString());
		          	
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
	
		public Map mailCodeCompareCount(String stuNum, String mailCode){
			
			Map result = null;
	    	Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	
	        StringBuffer query = new StringBuffer();
	
	        try{
	        	System.out.println("stuNum : "+stuNum);
	        	System.out.println("mailCode : "+mailCode);
	        	conn = getJndi();
	            query.setLength(0);
	            
	          	query.append(" select 	  \n");
	          	
	          	query.append("	 	COUNT(MA_SEQ) AS total  \n");
	          	
	          	query.append(" from mylist.c_mail_authen	 \n");
	          	query.append(" where `U_NO` = '"+stuNum+"'\n");
	          	query.append(" AND `MA_CODE` = '"+mailCode+"' \n");
	          	query.append(" ;\n");
	          	
	          			
	          	System.out.println(query.toString());
	          	pstmt = conn.prepareStatement(query.toString());
	          	
	          	rs = pstmt.executeQuery();
	          	
	            result = setData(rs); 
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
