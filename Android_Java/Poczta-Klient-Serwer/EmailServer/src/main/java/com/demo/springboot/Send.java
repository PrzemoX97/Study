package com.demo.springboot;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


public class Send
{
    public static void send(String to,  String subject, String body){

        final String from = "nisp.java@interia.pl";
        final String pass = "1234qwer";

        //Get the session object
        Properties properties = new Properties();

        properties.setProperty("mail.smtp.host", "smtp.interia.pl");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, pass);
                    }
                });

        //compose the message
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            // Send message
            Transport.send(message);
            System.out.println("message sent successfully....");

        }catch (MessagingException mex) {mex.printStackTrace();}
    }
}





