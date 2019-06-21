package com.lwl.demo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lwl.demo.producer.IMessageProducerService;
import com.lwl.demo.service.UserService;
import com.lwl.model.User;

@RestController
public class MainController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private IMessageProducerService messageProducer;
	
	@Resource
    private JavaMailSender javaMailSender ;
	
	@RequestMapping(value = "/")
	public User home() {
		return userService.selectUserById(1);
	}
	
	@RequestMapping(value = "/sendMessage")
	public void sendMessage() {
		for (int x = 0; x < 5000; x++) {
            this.messageProducer.sendMessage("study - " + x);
        }
	}
	
	@RequestMapping("/sendMail")
	public void sendMail() {
		SimpleMailMessage message = new SimpleMailMessage() ;    // 要发送的消息内容
        message.setFrom("1052786220@qq.com");
        message.setTo("1052786220@qq.com");
        message.setSubject("测试邮件）");
        message.setText("好好学习，天天向上");
        this.javaMailSender.send(message);
	}
	
	@RequestMapping("/findAllUser")
	public List<User> findAllUser() {
		return userService.findAllUser();
	}
}
