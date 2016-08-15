package finals.util;
 
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
 
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class SendMail {
	public SendMail(){}
	
	
	
	
    
    public static int getAddr(final String fromEmail, final String password, final String toEmail)
    		throws MessagingException, UnsupportedEncodingException{	
    	
        // ================ 6자리 랜덤 숫자 =======================
    	
    	
        Random generator = new Random();
        int authnum = generator.nextInt(900000)+100000;

    	
     // =================================================
    	
    	
        // 메일 내용
        String subject="[상명대] 기기 등록 확인 메일";
        String body ="※ 인증번호: " + authnum+"\n";


        body +="\n본 메일은 상명대학교 OTP 로그인 시스템에 기기를 등록하시려는 회원에게 본인 확인을 위해 자동으로 발송됩니다.\n\n";
        
        body +="\n";
        body +="■ 본 메일이 고객님의 정보가 아닐 경우\n";
        body +="본 메일은 상명대 OTP 로그인을 위해 기기를 등록하려는 분께 발송되는 확인 메일입니다.\n";
        body +="이메일 주소를 잘못 입력하여 다른 분께 메일이 발송되는 경우가 있습니다.\n\n그런 경우 반드시 본 메일을 삭제해 주시기 바랍니다.\n또한 본 메일에 기재된 인증번호를 입력하지 않으면 해당 기기는 등록되지 않으니 안심하세요.\n";
        
        body +="\n\n- 상명대학교 로그인 시스템";



        Properties props = new Properties();
        // SSL 사용하는 경우
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port
         
        // TLS 사용하는 경우
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
         
        // 인증
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
                
                
                
            }
        };
         
        // 메일 세션
        Session session = Session.getInstance(props, auth);
         
        MimeMessage msg = new MimeMessage(session);
        //set message headers
        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
        msg.addHeader("format", "flowed");
        msg.addHeader("Content-Transfer-Encoding", "8bit");
         
        msg.setFrom(new InternetAddress(fromEmail, "상명대학교"));
        msg.setReplyTo(InternetAddress.parse("no_reply@smu.ac.kr", false));
 
        msg.setSubject(subject, "UTF-8");
        
        msg.setText(body, "UTF-8");
        msg.setSentDate(new Date());
 
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
        Transport.send(msg); 
        
        return authnum;
 
    }
}

