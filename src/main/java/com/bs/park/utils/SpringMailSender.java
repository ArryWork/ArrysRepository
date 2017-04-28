package com.bs.park.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;


/**
 * Created by 林强 on 2017-03-24.
 */
@Component
public class SpringMailSender {
    @Resource
    private JavaMailSenderImpl mailSender;

    public JavaMailSenderImpl getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    public void simpleSend(String userEmail, String userName,String dept, BigDecimal balance) {
      /*  mailSender.setProtocol("smtp");
        mailSender.setHost("smtp.qq.com");
        mailSender.setPort(587);
        mailSender.setUsername("707037055@qq.com");
        mailSender.setPassword("xnjtynabyxthbebf");*/
        // 构建简单邮件对象，见名知意
        SimpleMailMessage smm = new SimpleMailMessage();
        // 设定邮件参数
        smm.setFrom(mailSender.getUsername());
        smm.setTo(userEmail);
        smm.setSubject("缴款通知");
        StringBuffer text =new StringBuffer();
        text.append("尊敬的");
        text.append(userName);
        text.append(":您的账户当前余额为");
        text.append(balance);
        text.append("元，欠款为");
        text.append(dept);
        text.append("请及时上线缴款");
        smm.setText(text.toString());
        // 发送邮件
        mailSender.send(smm);
    }

    public void sendResetMail(String mail,String password) {
        SimpleMailMessage sm = new SimpleMailMessage();
        SimpleMailMessage smm = new SimpleMailMessage();
        // 设定邮件参数
        smm.setFrom(mailSender.getUsername());
        smm.setTo(mail);
        smm.setSubject("密码重置通知");
        StringBuffer text =new StringBuffer();
        text.append("您的密码已重置为：");
        text.append(password);
        text.append("。请您尽快上线修改您的密码");
        smm.setText(text.toString());
        // 发送邮件
        mailSender.send(smm);
    }
}

