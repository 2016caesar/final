package finals.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import finals.util.AES128;
import finals.util.HmacSha256;

public class OTPUtil {
	
	
	public String serverOTPGet(String stuNum) throws Exception{
		// Seed for HMAC-SHA256 - 32 bytes
        String seed32 = "3132333435363738393031323334353637383930" +
         "313233343536373839303132";
        
		
        AES128 aes128 = new AES128();
        String crtaes = aes128.encrypt(stuNum);
        
        
		
		HmacSha256 sha256 = new HmacSha256();
		String steps = "0";
		long testTime = 1111111111L;
		long T0 = 0;
        long X = 180;
        
        Date today = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        
        testTime = today.getTime()/1000;
        
        long T = (testTime - T0)/X;
        steps = Long.toHexString(T).toUpperCase();
        
		String code = sha256.generateTOTP256(crtaes, steps, "8");
		
		return code;
	}
	
	
}
