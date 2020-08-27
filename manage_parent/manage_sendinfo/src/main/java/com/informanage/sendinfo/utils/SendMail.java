package com.informanage.sendinfo.utils;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SendMail {
	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.from}")
	private String from;
/**
 * 简单邮件
 * @param to
 * @param subject
 * @param content
 */
	public void sendSimpleMail(String to, String subject, String content,String Cc) {
		// 创建SimpleMailMessage对象
		SimpleMailMessage message = new SimpleMailMessage();
		//抄送，防止出现554发送失败
		message.setCc(Cc);
		// 邮件发送人
		message.setFrom(from);
		// 邮件接收人
		message.setTo(to);
		// 邮件主题
		message.setSubject(subject);
		// 邮件内容
		message.setText(content);
		// 发送邮件
		mailSender.send(message);
	}
	/**
	 * html格式邮件
	 * @param to
	 * @param subject
	 * @param content
	 * @throws Exception
	 */
	 public void sendHtmlMail(String to, String subject, String content) throws Exception {
	        //获取MimeMessage对象
	        MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
	            //邮件发送人
	            messageHelper.setFrom(from);
	            //邮件接收人
	            messageHelper.setTo(to);
	            //邮件主题
	            message.setSubject(subject);
	            //邮件内容，html格式
	            messageHelper.setText(content, true);
	            //发送
	            mailSender.send(message);
	    }

	 /**
	  * 带附件得邮件
	  * @param to
	  * @param subject
	  * @param content
	  * @param filePath
	  * @throws Exception
	  */
	    public void sendAttachmentsMail(String to, String subject, String content, String filePath) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(content, true);

		FileSystemResource file = new FileSystemResource(new File(filePath));
		String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
		helper.addAttachment(fileName, file);
		mailSender.send(message);
	}

}
