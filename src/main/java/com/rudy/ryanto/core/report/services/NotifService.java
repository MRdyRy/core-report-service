package com.rudy.ryanto.core.report.services;

import com.rudy.ryanto.core.report.domain.Email;
import com.rudy.ryanto.core.report.exception.NotifEmailException;
import com.rudy.ryanto.core.report.exception.ReportPDFException;
import com.rudy.ryanto.core.report.util.ReportConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.util.Objects;

import static com.rudy.ryanto.core.report.util.ReportHelper.doValidate;

@Service
@Slf4j
public class NotifService {

    @Value("${mail.user.sender}")
    private String sender;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SimpleMailMessage simpleMailMessage;

    @Autowired
    private MimeMessage mimeMessage;

    @Autowired
    private MimeMessageHelper mimeMessageHelper;

    public void sendPlainMessage(Email email) {
        log.info("send basic message !");
        doValidate(email);
        try {
            SimpleMailMessage m = (SimpleMailMessage) constructMessage(simpleMailMessage, email);
            javaMailSender.send(m);
        } catch (Exception e) {
            log.error("error happened, caused : ", e);
            throw new NotifEmailException(ReportConstant.error.GENERAL_ERROR.getDescription(), e);
        }
    }

    public void sendAttachmentMessage(Email email) {
        log.info("send email with attachment!");
        doValidate(email);
        try {
            MimeMessageHelper message = (MimeMessageHelper) constructMessage(mimeMessageHelper, email);
            //attachment
            FileSystemResource fileSystemResource = new FileSystemResource(email.getAttactment());
            message.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()),fileSystemResource);
            //send email
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            log.error("exception happened, caused : ",e);
            throw new ReportPDFException(ReportConstant.error.GENERAL_ERROR.getDescription(),e);
        }
    }

    private Object constructMessage(Object m, Email e) throws MessagingException {
        if (m instanceof MimeMessageHelper) {
            var r = (MimeMessageHelper) m;
            r.setTo(e.getRecipient());
            r.setText(e.getMsgBody());
            r.setSubject(e.getSubject());
            return r;
        }else{
            var s = (SimpleMailMessage) m;
            s.setTo(e.getRecipient());
            s.setText(e.getMsgBody());
            s.setSubject(e.getSubject());
            return s;
        }
    }
}