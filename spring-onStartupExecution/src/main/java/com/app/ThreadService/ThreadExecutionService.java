package com.app.ThreadService;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service
public class ThreadExecutionService {
	private Executor executorService;
	private ScheduledExecutorService scheduledExecutorService;

	public ThreadExecutionService() {
		this.executorService = Executors.newCachedThreadPool();
		this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
	}

	public void executeTask(Runnable runnableTask) {
		this.executorService.execute(runnableTask);
	}

	/**
	 * Schedule A task as thread on particular time 
	 * @param runnableTask
	 * @param delayInSeconds
	 */
	public void executeScheduleTask(Runnable runnableTask, long delayInSeconds) {
		this.scheduledExecutorService.schedule(runnableTask, delayInSeconds, TimeUnit.SECONDS);
	}

}
