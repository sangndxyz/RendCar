/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.utils;

import java.util.Properties;
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
 * @author nds72
 */
public class JavaMail {

    public static void sendMail(String recepient, String code) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myAccountMail = "sangndse140959@fpt.edu.vn";
        String password = "0983106331sa";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountMail, password);
            }
        });
        Message message = prepareMessage(session, myAccountMail, recepient, code);
        Transport.send(message);
    }

    private static Message prepareMessage(Session session, String myAccountMail, String recepient, String code) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myAccountMail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Assigment_3NguyenDangSang");
            String messageText = "Your Code : <h4 style='color:red;'> " + code + 
                    "</h4> <br/> Input code in verify page to continue";

            message.setContent(messageText, "text/html; charset=utf-8");
            return message;
        } catch (Exception ex) {
        }
        return null;
    }
}
