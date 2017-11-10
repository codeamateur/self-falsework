package com.tianqian.self;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.File;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MailTests {

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private TemplateEngine templateEngine;

	@Test
	public void sendSimpleMail() throws Exception {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("1072411603@qq.com");
		message.setTo("zhangjinxing@feicuilicai.com");
		message.setSubject("111");
		message.setText("测试邮件内容");

		mailSender.send(message);
	}

	@Test
	public void sendAttachmentsMail() throws Exception {

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("1072411603@qq.com");
		helper.setTo("zhangjinxing@feicuilicai.com");
		helper.setSubject("主题：有附件");
		helper.setText("有附件的邮件");

		FileSystemResource file = new FileSystemResource(new File("attachment.jpg"));
		helper.addAttachment("附件-1.jpg", file);
		helper.addAttachment("附件-2.jpg", file);

		mailSender.send(mimeMessage);
	}

	@Test
	public void sendInlineMail() throws Exception {

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("1072411603@qq.com");
		helper.setTo("zhangjinxing@feicuilicai.com");
		helper.setSubject("主题：嵌入静态资源");
		helper.setText("<html><body><img src=\"cid:attachment\" ></body></html>", true);

		FileSystemResource file = new FileSystemResource(new File("attachment.jpg"));
		helper.addInline("attachment", file);

		mailSender.send(mimeMessage);
	}

	@Test
	public void sendTemplateMail() throws Exception {

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("1072411603@qq.com");
		helper.setTo("zhangjinxing@feicuilicai.com");
		helper.setSubject("主题：模板邮件");
		Context context =new Context();
		context.setVariable("message","hello mail");
		helper.setText(templateEngine.process("mailTemplate",context), true);
		mailSender.send(mimeMessage);
	}

}
