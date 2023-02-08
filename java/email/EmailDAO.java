package email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailDAO {
	
	//이메일보내기
	public int email(EmailVO evo) {
		//사용할사이트
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.naver.com"); 
		props.put("mail.smtp.port", "587");         
		props.put("mail.smtp.auth", "true");
		
		//발신인(발신자 이메일,패스워드)
		final String user = "thwlsdl10@naver.com"; //발신자 이메일 주소
		final String password = "!nsj369120";     //해당 이메일의 비밀번호
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		String to = evo.getEmailAddress(); //수신인(메일 받을 주소)
		System.out.println("receiver : "+to);
		String title = evo.getEmailTitle();  //메일 제목
		String content = evo.getEmailContent();  //메일 내용

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("thwlsdl10@naver.com", "관리자", "utf-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			message.setSubject(title);
			message.setContent(content, "text/html; charset=utf-8");

			Transport.send(message); //메일 보내기
			System.out.println("문의답글(이메일) 보내기 성공");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("문의답글(이메일) 보내기 실패");
			return 0;
		}

	}
	
}
