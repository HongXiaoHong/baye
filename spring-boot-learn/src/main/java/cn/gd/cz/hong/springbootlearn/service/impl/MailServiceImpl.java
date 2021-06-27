package cn.gd.cz.hong.springbootlearn.service.impl;

import cn.gd.cz.hong.springbootlearn.service.MailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 实现发送邮件
 */
@Service
public class MailServiceImpl implements MailService {

    @Resource
    private JavaMailSender mailSender;

    @PostConstruct
    public void init() {
        sendText("spring学习中");
    }

    @Override
    public void sendText(String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1908711045@qq.com");
        message.setTo("m15220004896@163.com");
        message.setSubject("test");
        message.setText(content);
        mailSender.send(message);
    }
}
