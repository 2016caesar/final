package finals.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import finals.comm.CommonDao;

public class OTPUtil extends CommonDao{
	
	public String AESEncrypt(String stuNum) throws Exception{
		
		String crtaes = "";
		try{
			AES128 aes128 = new AES128();
		    crtaes = aes128.encrypt(stuNum);
		}catch(Exception ex){
			ex.printStackTrace();
        	logger.error(ex);
		}
		
	    return crtaes; 
	}
	
	
	public String serverOTPGet(String aes128) throws Exception{
		// Seed for HMAC-SHA256 - 32 bytes
        /*String seed32 = "3132333435363738393031323334353637383930" +
         "313233343536373839303132";*/     
		String code = "";
		
		try{
			
			HmacSha256 sha256 = new HmacSha256();
			String steps = "0";
			long testTime = 1111111111L;
			long T0 = 0;
	        long X = 180;
	        
	        Date today = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	        //df.setTimeZone(TimeZone.getTimeZone("UTC"));
	        
	        testTime = today.getTime()/1000;
	        
	        long T = (testTime - T0)/X;
	        steps = Long.toHexString(T).toUpperCase();
	        
			
	        code = sha256.generateTOTP256(aes128, steps, "8");
		}catch(Exception ex){
			ex.printStackTrace();
        	logger.error(ex);
		}
		return code;
	}
	
	
}
