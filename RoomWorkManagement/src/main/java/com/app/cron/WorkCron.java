package com.app.cron;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.app.event.OnWorkEvent;
import com.app.model.WorkRoom;
import com.app.service.GoogleDriveSheetReader;
import com.app.service.MailService;

import freemarker.template.TemplateException;

@Component
public class WorkCron {
	GoogleDriveSheetReader googleDriveSheetservice;
	private final ApplicationEventPublisher applicationEventPublisher;
	@Autowired
	private MailService mailservice;

	@Autowired
	public WorkCron(GoogleDriveSheetReader googleDriveSheetservice,
			ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
		this.googleDriveSheetservice = googleDriveSheetservice;
	}

	@Scheduled(cron = "${cron.expression}")
	public void sendWorkStatus() throws GeneralSecurityException, IOException {
		this.runScheduler();
	}

	public void runScheduler() throws GeneralSecurityException, IOException {
		Map<Integer, String> userMap = new LinkedHashMap<Integer, String>();
		userMap.put(1, "ankitji344@gmail.com");
		userMap.put(2, "bk04031997@gmail.com");
		userMap.put(3, "raj.maneeshkumar@gmail.com");
		WorkRoom room = new WorkRoom();
		Map<String, Long> rentData = googleDriveSheetservice.readRentExcelSheet();
		userMap.forEach((k, v) -> {
			room.setBathRoomStatus("Done");
			room.setEmailId(v);
			room.setId(k);
			room.setLastMarketGone(new Date());
			room.setPreviousbathRoomStatus("Done");
			room.setSheetData(rentData);
			try {
				mailservice.sendEmailToWorker(room);
			} catch (IOException | TemplateException | MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			 OnWorkEvent workevent=new OnWorkEvent(room);
//			 applicationEventPublisher.publishEvent(workevent);
		});
	}
}
