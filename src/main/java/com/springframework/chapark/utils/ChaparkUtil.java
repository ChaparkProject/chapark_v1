package com.springframework.chapark.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 공통유틸리티
 */
@Component
public class ChaparkUtil {

	public static void sendEmail(String mberEamil, String tempPassword ) {
		// 1. 발신자의 메일 계정과 비밀번호 설정
		final String user = "pjchapark@gmail.com";
		final String password = "oxylxzuqrovoenrx";

		// 2. Property에 SMTP 서버 정보 설정
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 587);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

		// 3. SMTP 서버정보와 사용자 정보를 기반으로 Session 클래스의 인스턴스 생성
		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// 4. Message 클래스의 객체를 사용하여 수신자와 내용, 제목의 메시지를 작성한다.
		// 5. Transport 클래스를 사용하여 작성한 메세지를 전달한다.

		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(user));

			// 수신자 메일 주소
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mberEamil));

			// Subject
			message.setSubject("임시 비밀번호 발급");

			// Text
			message.setText("귀하의 임시 비밀번호는" + tempPassword + "입니다.");

			Transport.send(message); // send message

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	// 예외처리
	public static String alertException(HttpServletResponse response, String message) {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");

		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println("<html><body>");
			out.println("<script>");
			out.println("alert('" + message + "');");
			out.println("window.history.back();");
			out.println("</script>");
			out.println("</body></html>");

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		return null;
	}

	private static String escapeJavaScript(String input) {
		if (input == null) {
			return "";
		}
		return input.replace("\\", "\\\\").replace("'", "\\'").replace("\"", "\\\"").replace("\n", "\\n")
				.replace("\r", "\\r").replace("\b", "\\b").replace("\f", "\\f");
	}
}
