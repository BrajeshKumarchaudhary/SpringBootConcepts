package com.app.listener;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.MailSendException;
import org.springframework.scheduling.annotation.Async;

import com.app.event.OnWorkEvent;
import com.app.model.WorkRoom;
import com.app.service.MailService;

import freemarker.template.TemplateException;

public class OnWorkListener implements ApplicationListener<OnWorkEvent>  {

	 private final MailService mailService;
	
	 @Autowired
	    public  OnWorkListener(MailService mailService) {
	        this.mailService = mailService;
	    }
	 @Override
	 @Async
	public void onApplicationEvent(OnWorkEvent event) {
          this.sendEmailToWorker(event);		
	}
	    /**
	     * Send email verification to the user and persist the token in the database.
	     */
	    private void sendEmailToWorker(OnWorkEvent event) {
	    	WorkRoom user = event.getWorker();
	        String recipientAddress = user.getEmailId();

	        try {
	            mailService.sendEmailToWorker(user);
	        } catch (IOException | TemplateException | MessagingException e) {
	            throw new MailSendException(recipientAddress);
	        }
	    }
}
