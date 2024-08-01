package com.shoppingwebsitemail.MailService.Utils;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailUtils {
    //Java mail sender
    //mime message---->in the object of mime message we are gng to  create email
    //Instance of mail helper3
    //Mime message helper-->with the help of mime message helper we are going to create mime message i.e., Mail

    JavaMailSender javaMailSender;



    MimeMessageHelper mimeMessageHelper;



    MimeMessage mimeMessage;

    public MailUtils(){
        javaMailSender =new JavaMailSenderImpl();
        mimeMessage =javaMailSender.createMimeMessage();
        mimeMessageHelper=new MimeMessageHelper(mimeMessage);
    }
    public JavaMailSender getJavaMailSender() {
        return javaMailSender;
    }
    public MimeMessageHelper getMimeMessageHelper() {
        return mimeMessageHelper;
    }
    public MimeMessage getMimeMessage() {
        return mimeMessage;
    }
}
