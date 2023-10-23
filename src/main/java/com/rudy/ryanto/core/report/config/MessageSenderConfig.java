package com.rudy.ryanto.core.report.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@ConfigurationProperties(prefix = "spring.application.mail")
@Configuration
public class MessageSenderConfig {

    private String username;
    private String host;
    private String port;
    private String password;

    @Autowired
    private Environment environment;

    @Bean
    public SimpleMailMessage simpleMailMessage() {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(username);
        return mail;
    }

    @Bean
    public JavaMailSender javaMailSender() {
        var j = new JavaMailSenderImpl();
        j.setHost(host);
        j.setPort(Integer.parseInt(port));
        j.setUsername(username);
        j.setPassword(password);
        return j;
    }

    @Bean
    public MimeMessage mimeMessage() {
        return javaMailSender().createMimeMessage();
    }

    @Bean
    public MimeMessageHelper mimeMessageHelper() throws MessagingException {
        var m = new MimeMessageHelper(mimeMessage(), true);
        m.setFrom(username);
        return m;
    }
}
