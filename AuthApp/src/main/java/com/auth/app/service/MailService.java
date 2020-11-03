package com.auth.app.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.auth.app.model.Mail;
import com.auth.app.util.Util;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Service
public class MailService {


    private final Configuration templateConfiguration;

    @Value("${app.velocity.templates.location}")
    private String basePackagePath;

    @Value("${app.token.password.reset.duration}")
    private Long expiration;
   
    Session mailSession;
    private static Map<String,String> mailProps=null;
    Util utilservice;
    
    @Autowired
    public MailService(Configuration templateConfiguration,Util util) {
        this.templateConfiguration = templateConfiguration;
        this.utilservice=util;
    }

    public void sendEmailVerification(String emailVerificationUrl, String to)
            throws IOException, TemplateException, MessagingException {
        try {
    	Mail mail = new Mail();
        mail.setSubject("Email Verification [Team CEP]");
        mail.setTo(to);
        mail.getModel().put("userName", to);
        mail.getModel().put("userEmailTokenVerificationLink", emailVerificationUrl);
       
        templateConfiguration.setClassForTemplateLoading(getClass(), basePackagePath);
        Template template = templateConfiguration.getTemplate("email-verification.ftl");
        String mailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getModel());
        mail.setContent(mailContent);
        send(mail);
        }catch (Exception e) {
        	e.printStackTrace();
		}
    }

    /**
     * Setting the mail parameters.Send the reset link to the respective user's mail
     */
    public void sendResetLink(String resetPasswordLink, String to)
            throws IOException, TemplateException, MessagingException {
        Long expirationInMinutes = TimeUnit.MILLISECONDS.toMinutes(expiration);
        String expirationInMinutesString = expirationInMinutes.toString();
        Mail mail = new Mail();
        mail.setSubject("Password Reset Link [Team CEP]");
        mail.setTo(to);
        mail.getModel().put("userName", to);
        mail.getModel().put("userResetPasswordLink", resetPasswordLink);
        mail.getModel().put("expirationTime", expirationInMinutesString);

        templateConfiguration.setClassForTemplateLoading(getClass(), basePackagePath);
        Template template = templateConfiguration.getTemplate("reset-link.ftl");
        String mailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getModel());
        mail.setContent(mailContent);
        send(mail);
    }

    /**
     * Send an email to the user indicating an account change event with the correct
     * status
     */
    public void sendAccountChangeEmail(String action, String actionStatus, String to)
            throws IOException, TemplateException, MessagingException {
        Mail mail = new Mail();
        mail.setSubject("Account Status Change [Team CEP]");
        mail.setTo(to);
        mail.getModel().put("userName", to);
        mail.getModel().put("action", action);
        mail.getModel().put("actionStatus", actionStatus);

        templateConfiguration.setClassForTemplateLoading(getClass(), basePackagePath);
        Template template = templateConfiguration.getTemplate("account-activity-change.ftl");
        String mailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getModel());
        mail.setContent(mailContent);
        send(mail);
    }
    
    /*
     * --------------------------------------------------------------------------
     */
    public String send(Mail mail) {
		try {
			mailProps=utilservice.getMailProp();
            mailProps.put("TO_MAIL_LIST", mail.getTo());
			this.validateTokenIsExpire();
			this.setMailServerProperties();
			this.sendEmail(mail.getContent(), mail.getSubject());
			System.out.println("Email Sent please check inbox");
			return "Email Sent Successfully";
		}catch (Exception e) {
			e.printStackTrace();
            return e.getMessage();
		}
		
	}

	private void setMailServerProperties() {
		Properties props = System.getProperties();
		props.put("mail.smtp.ssl.enable", "true"); // required for Gmail
		props.put("mail.smtp.auth.mechanisms", "XOAUTH2");
		mailSession = Session.getDefaultInstance(props, null);
	}

	private MimeMessage draftEmailMessage(String emailBody, String emailSubject)
			throws AddressException, MessagingException {
		String[] toEmails = mailProps.get("TO_MAIL_LIST").split(",");
		MimeMessage emailMessage = new MimeMessage(mailSession);
		/**
		 * Set the mail recipients
		 */
		for (int i = 0; i < toEmails.length; i++) {
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
		}
		emailMessage.setSubject(emailSubject);
		/**
		 * If sending HTML mail
		 */
        emailMessage.setContent(emailBody, "text/html");
		/**
		 * If sending only text mail
		 */
//		emailMessage.setText(emailBody);// for a text email
		return emailMessage;
	}

	private void sendEmail(String textmessage, String subject) throws AddressException, MessagingException {
		/**
		 * Sender's credentials
		 */
		String fromUser = mailProps.get("USER_NAME").trim();
		String emailHost = mailProps.get("HOST").trim();
		Transport transport = mailSession.getTransport("smtp");
		transport.connect(emailHost, fromUser, mailProps.get("ACCESS_TOKEN"));
		/**
		 * Draft the message
		 */
		MimeMessage emailMessage = draftEmailMessage(textmessage, subject);
		/**
		 * Send the mail
		 */
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
		System.out.println("Email sent successfully.");
	}

	private void validateTokenIsExpire() {
		if (System.currentTimeMillis() > Long.parseLong(mailProps.get("TOKEN_EXPIRE"))) {
			try {
				String request = "?client_id=" + URLEncoder.encode(mailProps.get("O_AUTH_CLIENT"), "UTF-8") + "&client_secret="
						+ URLEncoder.encode(mailProps.get("O_AUTH_SECRET"), "UTF-8") + "&refresh_token="
						+ URLEncoder.encode(mailProps.get("REFRESH_TOKEN"), "UTF-8") + "&grant_type=refresh_token";
				HttpURLConnection conn = (HttpURLConnection) new URL(mailProps.get("TOKEN_URL")+request).openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				PrintWriter out = new PrintWriter(conn.getOutputStream());
				out.flush();
				out.close();
				conn.connect();
				try {
					HashMap<String, Object> result;
					result = new ObjectMapper().readValue(conn.getInputStream(),
							new TypeReference<HashMap<String, Object>>() {
							});
					String accesstoken = (String) result.get("access_token");
					mailProps.put("ACCESS_TOKEN", accesstoken);
					Long tokenExpires = System.currentTimeMillis() + (((Number) result.get("expires_in")).intValue() * 1000);
					mailProps.put("TOKEN_EXPIRE", Long.toString(tokenExpires));
					utilservice.updateTokenExpire(Long.toString(tokenExpires),"TOKEN_EXPIRE");
					utilservice.updateAccessToken(accesstoken,"ACCESS_TOKEN");
				} catch (IOException e) {
					String line;
					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
					while ((line = in.readLine()) != null) {
						System.out.println(line);
					}
					System.out.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


}
