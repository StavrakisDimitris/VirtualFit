package Classes;

import javax.mail.Authenticator;
import java.io.*;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import sun.security.util.Password;

public class Admin {
    private String username;
    private String email;
    private String password;
    private List<Order> orderList;
    private List<Product> productList;
    private Double discountPer;
    private Boolean validationOrder;
    private Double discount;
    
    public Admin(String username, String email, String password, Double discountPer){
        this.username = username;
        this.email = email;
        this.password = password;
        this.discountPer = discountPer;
        
        this.orderList = new ArrayList<Order>();
        this.productList = new ArrayList<Product>();        
    }   
    
    public Boolean validateOrder(){
        return validationOrder;
    }
    
    public List<Order> getOrder(){
        return orderList;
    }
    
    public void calculateOffer(){
        
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getPassword(){
        return password;
    }
    
    
    public static  String SendEmail(String Email,String Password ,String ToEmail,String Subject,String Text){
        String Msg;
        final String username = Email;
        final String password = Password;

        
       Properties props = new Properties();
       props.put("mail.smtp.auth",true);
       props.put("mail.smtp.starttls.enable", true);
       props.put("mail.smtp.host","");
       props.put("mail.smtp.port", "");
       props.setProperty("", "");
       
       Session session = Session.getInstance(props,new javax.mail.Authenticator(){
         
           protected PasswordAuthentication getPassword(){
            return new PasswordAuthentication(username,password);
        }
    });
        try{
            
            MimeMessage message =  new MimeMessage(session);
            message.setFrom(new InternetAddress(Email));
            message.setRecipients(MimeMessage.RecipientType.TO,
                    InternetAddress.parse(Email));
            message.setSubject(Subject);
            message.setText(Text);
            Transport.send(message);
            Msg ="true";
            return Msg;
        }
        catch(Exception e){
            return e.toString();
        }
    }
}

 
