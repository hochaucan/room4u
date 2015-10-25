/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author JixthSune
 */
@ManagedBean(name = "sendMail")
@SessionScoped
public class SendMailController extends Authenticator{

    private String content;
    private String email;
    private String name;
    private Session mailSession;
    /**
     * Creates a new instance of SendMailController
     */
    public SendMailController() {
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
   
//   public void sendMailSupport() {
//    // OUR EMAIL SETTINGS
//    String host = "smtp.gmail.com";// Gmail
//    int port = 587;
//    String serviceUsername = "room4u.FAT2.HCM@gmail.com";
//    String servicePassword = "123456?a";// Our Gmail password
//
//        Properties props = new Properties();
//        props.put("mail.smtp.user", serviceUsername);
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.port", port);
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.debug", "true");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.socketFactory.port", port);
//        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.socketFactory.fallback", "false");
//
//        // Destination of the email
//        String to = "tranminhcuong6691@gmail.com";
//        String from = "room4u.FAT2.HCM@gmail.com";
//
//        // Creating a javax.Session with the our properties
//        Session session = Session.getDefaultInstance(props,
//			new javax.mail.Authenticator() {
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication("room4u.FAT2.HCM@gmail.com","123456?a");
//				}
//			});
//
//        try {
//            Message message = new MimeMessage(session);
//            // From: is our service
//            message.setFrom(new InternetAddress(from));
//            // To: destination given
//            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
//            message.setSubject("Need support form: " + name);
//            // Instead of simple text, a .html template should be added here!
//            message.setText(content + "/n/n" + email);
//
//            Transport transport = session.getTransport("smtp");
//            transport.connect(host, port, serviceUsername, servicePassword);
//            Transport.send(message, message.getAllRecipients());
//            transport.close();
//
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
   
   public String sendMailSupport() {
        final String username = "room4u.FAT2.HCM@gmail.com";
        final String password = "123456?a";

	Properties props = new Properties();
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");

	Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("tranminhcuong6691@gmail.com"));
			message.setSubject("Need support form customer: " + name);
			message.setText(content + "\r\nEmail reply: " + email);
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
                return "contact";
   }
}
