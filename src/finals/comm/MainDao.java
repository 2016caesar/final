package finals.comm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainDao extends CommonDao{

	public List MainGetDB(Map param){

			List<HashMap<String,String>> result = null;
	    	Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        StringBuffer query = new StringBuffer();

	        try{

	        	conn = getJndi();
	            query.setLength(0);
	            query.append("select * from userinfo\n");
	            query.append("\n");
	          	query.append("\n");
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
