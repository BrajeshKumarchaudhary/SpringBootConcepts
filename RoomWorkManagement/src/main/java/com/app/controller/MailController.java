package com.app.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.cron.WorkCron;
import com.app.service.GoogleDriveSheetReader;

import io.swagger.annotations.ApiOperation;

@RestController
@ApiOperation(value = "Send Mail report")
public class MailController {

	@Autowired
	GoogleDriveSheetReader googledriveSeet;
	@Autowired
	WorkCron cron;
	@GetMapping(value = "send")
	@ApiOperation(value = "Send Mail of Work Status")
	public String readCsv() throws GeneralSecurityException, IOException {
		cron.runScheduler();
		return "Mail Send Successfully";
	}
	
}
