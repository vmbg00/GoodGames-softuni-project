package bg.softuni.gamingstore.services.impl;

import bg.softuni.gamingstore.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;


    @Autowired
    public EmailServiceImpl(@Qualifier("getJavaMailSender") JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }


    @Override
    public void sendMail(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@goodgames.com");
        message.setTo(to);
        message.setSubject("Subscription for upcoming game!");
        message.setText("Test test test");
        emailSender.send(message);
    }
}
