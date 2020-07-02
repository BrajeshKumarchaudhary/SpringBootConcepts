package com.app.callonstartup;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import com.app.ThreadService.ExecutionActivity;
import com.app.ThreadService.ThreadExecutionService;

@Component
public class CallOnStartup implements ApplicationListener<ContextRefreshedEvent> {
	Logger logger = LoggerFactory.getLogger(CallOnStartup.class);
	@Autowired
	private ApplicationContext context;
	@Autowired
	ThreadExecutionService threadservice;
	@Autowired
	ExecutionActivity executionactivity;
	@Autowired
	TaskScheduler taskscheduler;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext context = (ApplicationContext) event.getSource();
		if (Objects.requireNonNull(this.context.getId()).equalsIgnoreCase(context.getId())) {
			this.doSomethingonStartUp();
			this.doSomethingonStartUp2();
			this.doSomethingonStartUp3();
			ScheduleTaskUsingTaskScheduler();
		}
	}

	private void doSomethingonStartUp() {
		Runnable exe1 = () -> {
			try {
				this.executionactivity.doSomethingonStartUp();
			} catch (Exception ex) {
				logger.info(ex.getMessage());
			}
		};
		//called after onstartUp 10 miniutes;
		this.threadservice.executeScheduleTask(exe1,10);
	}

	private void doSomethingonStartUp2() {
		Runnable exe1 = () -> {
			try {
				this.executionactivity.doSomethingonStartUp2();
			} catch (Exception ex) {
				logger.info(ex.getMessage());
			}
		};
		this.threadservice.executeTask(exe1);
	}

	private void doSomethingonStartUp3() {
		Runnable exe1 = () -> {
			try {
				this.executionactivity.doSomethingonStartUp3();
			} catch (Exception ex) {
				logger.info(ex.getMessage());
			}
		};
		this.threadservice.executeTask(exe1);
	}
	
	//Schedule a task after using TaskScheduler
  private void ScheduleTaskUsingTaskScheduler()
  {
	  Runnable exe1 = () -> {
			try {
				this.executionactivity.doSomethingonStartUp3();
			} catch (Exception ex) {
				logger.info(ex.getMessage());
			}
		};
		this.taskscheduler.schedule(exe1,addSecondsToDate(new Date(), 15));
  }
  public static Date addSecondsToDate(Date settledDate, Integer seconds) {
      Calendar cal = Calendar.getInstance();
      cal.setTime(settledDate);
      cal.add(Calendar.MINUTE, seconds);
      return cal.getTime();
  }
	
	
}
