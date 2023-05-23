package Classes;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Orders_History {
    private String customerName;
    private String itemListName;
    private String customerEmail;
    private List<Product> itemList;
    private Date buyListDate;
    private File downloadPDF;
    
    public void getOldList(Integer listId) {
       
    }
  
    public static String sendEmail(String email, String password, String toEmail, String subject, String text) {
        String message;
        final String username = email;
        final String userPassword = password;

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, userPassword);
            }
        });

        try {
            Message emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress(email));
            emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            emailMessage.setSubject(subject);
            emailMessage.setText(text);
            Transport.send(emailMessage);
            message = "true";
            return message;
        } catch (Exception e) {
            return e.toString();
        }
    }
}
