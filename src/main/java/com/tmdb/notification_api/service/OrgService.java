package com.tmdb.notification_api.service;

import com.tmdb.notification_api.models.Organization;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.Properties;

@Service
public class OrgService {

    public void notifyAdminForOrgCreation(Organization organization) throws Exception{
        //configure JavaMailSender
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("accioshoppingwebsite@gmail.com");
        mailSender.setPassword("relcfdwhahhcvokv");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        //build email content
        MimeMessage mimeMailMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage);
        mimeMessageHelper.setTo(organization.getAdminEmail());
        mimeMessageHelper.setSubject("Congrats account created successfully!");

        //context is just setting up value for all the variables that are defined under our html template
        Context context = new Context();
        context.setVariable("platformName", "AccioJob");
        context.setVariable("adminName", organization.getAdminName());
        context.setVariable("registeredName", organization.getRegisteredName());
        context.setVariable("websiteUrl", organization.getWebsiteUrl());
        context.setVariable("companySize", organization.getCompanySize());
        context.setVariable("adminEmail", organization.getAdminEmail());
        context.setVariable("adminName", organization.getAdminName());
        context.setVariable("address", organization.getAddress());
        context.setVariable("createdAt", organization.getCreatedAt());

        //setup thymeleaf template engine
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");  //make sure this folder exists in repo
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        String htmlContent = templateEngine.process("org-create-notification", context);
        mimeMessageHelper.setText(htmlContent, true);  //enable html

        //send email
        mailSender.send(mimeMailMessage);
    }
}
