package com.om.springboot.service.application;

import com.om.springboot.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class AppMailerService {

    private static final Logger logger = LoggerFactory.getLogger(AppMailerService.class);

    @Autowired
    JavaMailSender javaMailService;

    @Async
    public void sendOtpEmail(String receiver, String otp) {
        logger.debug("------------>Sending OTP email to " + receiver + " with otp as " + otp);
        MimeMessage mimeMessage = javaMailService.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(new InternetAddress(receiver));
            helper.setCc(new InternetAddress(AppConstants.EMAIL_ADMIN));
            helper.setFrom(AppConstants.EMAIL_SENDER);
            helper.setText("Dear Customer, \n\n Welcome to B-1 Desk Site. "
                    + "\n Please use your One Time Password "
                    + otp + " to proceed further."
                    + "\n\n Regards, "
                    + "\n Balbhas Team."
            );
            helper.setSubject("Your OTP from Balbhas Team ");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        try {
            javaMailService.send(mimeMessage);
        } catch (MailException e) {
            System.err.println("---->MAIL SENDING EXCEPTION<-------------Could not send email to " + receiver + " because of " + e);
            e.printStackTrace();
        }
    }


   /*
    @Async
    public void sendTestMail() {
      //  logger.debug("------------>Sending OTP email to " + receiver + " with otp as " + otp);
        MimeMessage mimeMessage = javaMailService.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(new InternetAddress("swetha.t@balbhas.com"));
           // helper.setCc(new InternetAddress);
            helper.setFrom("swethaece2000@gmail.com");
            helper.setText("Dear Guest, \n\n Welcome to NAC Video Shopping. "
                    + "\n Please use your One Time Password "
                    + ""+ " to proceed further."
                    + "\n\n Regards, "
                    + "\n NAC Videoshopping Team."
            );
            helper.setSubject("Your OTP from NAC Videoshopping Team ");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        try {
            javaMailService.send(mimeMessage);
        } catch (MailException e) {
            System.err.println("---->MAIL SENDING EXCEPTION<-------------Could not send email to " +""+ " because of " + e);
            e.printStackTrace();
        }
    }
    
*/
}
