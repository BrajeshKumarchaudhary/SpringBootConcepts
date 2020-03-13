package com.java.listner;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
@Component
public class JobCompletionListener extends JobExecutionListenerSupport{
  
	
	private final JdbcTemplate jdbctemplate;
	JobCompletionListener(JdbcTemplate template)
	{
	this.jdbctemplate=template;	
	}
	@Override
	public void afterJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		if(jobExecution.getStatus()==BatchStatus.COMPLETED)
		{
			System.out.println("Job finished");
		}
	}
}
