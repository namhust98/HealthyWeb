package vn.edu.hust.pthtwat.pthtwat.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import vn.edu.hust.pthtwat.pthtwat.common.EmailTemplate;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class EmailService {
    private final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(final EmailTemplate messageTemplate) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                    MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);
                    mailMsg.setFrom(new InternetAddress("admin@pthtwat.com", "PHÁT TRIỂN HỆ THỐNG WEB AN TOÀN", "utf-8"));
                    mailMsg.setSubject(messageTemplate.getSubject());
                    mailMsg.setTo(messageTemplate.getReceiver());
                    mailMsg.setText(messageTemplate.getContent(), true);
                    javaMailSender.send(mimeMessage);
                } catch (MessagingException | UnsupportedEncodingException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }).start();

    }

}
