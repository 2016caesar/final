package finals.comm;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import finals.util.EgovDateUtil;

public class CommonDao
{

	protected final Log logger = LogFactory.getLog(getClass());

	/**
	 * JNDI를 통한 커넥션 얻어오기
	 * @return Connection
	 */
	protected Connection getConnection(){
		Connection con = null;
		try{
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/fsda");	// JNDI명을 넣어준다. ex) jdbc/career
			con = ds.getConnection();
		}catch(Exception e){
			e.printStackTrace();
			logger.debug(e.toString());
		}
		return con;
	}

	/**
	 * getConnection overloading method
	 * 사용자가 jndi명을 넣어 커넥션 객체를 얻어온다.
	 * @param jndi
	 * @return
	 */
	public Connection getConnection(String jndi){
		Connection con = null;
		try{
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup(jndi);
			con = ds.getConnection();

		}catch(Exception e){
			e.printStackTrace();
			logger.debug(e.toString());
		}
		return con;
	}

	/**
	 * JNDI를 이용한 커넥션 얻어오기
	 * @return
	 */
	public Connection getJdbc(){
		Connection con = null;
		try{
			Context initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/ads");	// JNDI명을 넣어준다. ex) jdbc/career
			con = ds.getConnection();
		}catch(Exception e){
			e.printStackTrace();
			logger.debug(e.toString());
		}
		return con;
	}

	/**
	 * 커넥션 불러오기
	 * @return
	 */
	public Connection getJndi(){

			return getJndiRealServer(); //리얼 서버
	}

	/**
	 * 실 서버에서 구동시
	 * @return
	 */
	public Connection getJndiRealServer(){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylist","root", "1234");	// url ,port , id ,password를 수정한다.
		}catch(Exception e){
			System.out.println(e.toString());
			logger.debug(e.toString());
		}
		return con;
	}


	/**
	 * ResultSet 객체를 파라미터로 받아 복수 레코드에 대한 처리를 한다.
	 * @param ResultSet 객체
	 * @return List
	 */
	public List setDatas(ResultSet rs){
		List lis = new ArrayList();
		try{
			ResultSetMetaData metaData = rs.getMetaData();
			int numberOfColumns = metaData.getColumnCount();
			while(rs.next()){
				Map dtMap = new HashMap();
				for(int i=1; i <= numberOfColumns;i++) {
					String key_nam = metaData.getColumnLabel(i);
					int typ = metaData.getColumnType(i);
					if(typ == java.sql.Types.NUMERIC){
						String num = rs.getString(i);
						dtMap.put(key_nam,num);
					}else{
						String str = rs.getString(i);
						if(rs.getString(i) == null){
							str = "";
						}
						dtMap.put(key_nam,str);
					}
				}
				lis.add((Map) dtMap);
			}
		}catch(Exception e){
			logger.error(e);
		}
		return lis;
	}
	/**
	 * ResultSet 객체를 파라미터로 받아 단일 레코드에 대한 처리를 한다.
	 * @param ResultSet 객체
	 * @return Map
	 */
	public Map setData(ResultSet rs){
		Map map = null;
		try{
			ResultSetMetaData metaData = rs.getMetaData();
			int numberOfColumns = metaData.getColumnCount();
			if(rs.next()){
				map = new HashMap();
				for(int i=1; i <= numberOfColumns;i++) {
					String key_nam = metaData.getColumnName(i);
					int typ = metaData.getColumnType(i);
					if(typ == java.sql.Types.NUMERIC){
						String num = rs.getString(i);
						map.put(key_nam.toLowerCase(),num);
					}else{
						String str = rs.getString(i);
						if(rs.getString(i) == null){
							str = "";
						}
						map.put(key_nam.toLowerCase(),str);
					}
				}
			}
		}catch(Exception e){
			logger.error(e);
		}
		return map;
	}

	protected void setClose(Connection con , PreparedStatement ps , ResultSet rs ){
		if(rs != null) {
			try{
				rs.close();
			}catch(Exception e){
				logger.error(e);
			}
		}
		if(ps != null) {
			try{
				ps.close();
			}catch(Exception e){
				logger.error(e);
			}
		}
		if(con != null) {
			try{
				con.close();
			}catch(Exception e){
				logger.error(e);
			}
		}
	}

	protected void setClose(Connection con , PreparedStatement ps ){
		if(ps != null) {
			try{
				ps.close();
			}catch(Exception e){
				logger.error(e);
			}
		}
		if(con != null) {
			try{
				con.close();
			}catch(Exception e){
				logger.error(e);
			}
		}
	}

	protected void setClose( Connection con, CallableStatement cstmt ){
		if(cstmt != null) {
			try{
				cstmt.close();
			}catch(Exception e){
				logger.error(e);
			}
		}
		if(con != null) {
			try{
				con.close();
			}catch(Exception e){
				logger.error(e);
			}
		}
	}
}
