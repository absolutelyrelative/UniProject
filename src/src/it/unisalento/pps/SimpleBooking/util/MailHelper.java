package it.unisalento.pps.SimpleBooking.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class MailHelper {

    private static MailHelper instance;

    //Aggiunto 'final' modifier
    private final static String FROM = "SimpleBooking.Paolo@gmail.com";
    private final static String PASSWORD = "SimpleBooking";

    public static synchronized MailHelper getInstance() {
        if (instance == null)
            instance = new MailHelper();
        return instance;
    }


    public void send(String to, String sub, String msg) {
        //Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //get Session
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(FROM, PASSWORD);
                    }
                });
        //compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(FROM)); //WARNING: EXCEPTION THROWN IF NOT SPECIFIED!!
            message.setSubject(sub);
            message.setText(msg);
            //send message
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("message not sent");
        }

    }

}