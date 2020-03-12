package com.feed.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import listing.data.ReadData;

@Controller
@Component
public class FeedGenerator {
	public static ExecutorService executorService;
	@Autowired
    private ApplicationContext context;
	public static boolean genrateFeed() {
		try {
			ReadData read = new ReadData();
			Thread t1 = FeedGenerator.makeThread(read, "Lease", true, true);// read
			Thread t2 = FeedGenerator.makeThread(read, "Lease", true, false);// write
			// for sale condo/residentail
			ReadData read1 = new ReadData();
			Thread t3 = FeedGenerator.makeThread(read1, "Sale", true, true);
			Thread t4 = FeedGenerator.makeThread(read1, "Sale", true, false);
			// for lease commercial
			ReadData read2 = new ReadData();
			Thread t5 = FeedGenerator.makeThread(read2, "Lease", false, true);
			Thread t6 = FeedGenerator.makeThread(read2, "Lease", false, false);
			// for sale commercial
			ReadData read3 = new ReadData();

			Thread t7 = FeedGenerator.makeThread(read3, "Sale", false, true);
			Thread t8 = FeedGenerator.makeThread(read3, "Sale", false, false);
			ExecutorService executorService = Executors.newFixedThreadPool(8);
			executorService.submit(t1);
			executorService.submit(t2);
			executorService.submit(t3);
			executorService.submit(t4);
			executorService.submit(t5);
			executorService.submit(t6);
			executorService.submit(t7);
			executorService.submit(t8);
			FeedGenerator.executorService = executorService;
			executorService.shutdown();
		} catch (Exception ex) {
			System.out.print(ex);
		}
		return true;
	}

	/*
	 * 
	 * Task finished then exit from program
	 */
	@Scheduled(fixedRate = 60000)
	public void TerminateProcess() {
		if (FeedGenerator.executorService instanceof ThreadPoolExecutor) {
			System.out.print("current pool size--"+((ThreadPoolExecutor) FeedGenerator.executorService).getActiveCount());
			if (((ThreadPoolExecutor) FeedGenerator.executorService).getActiveCount() == 4) {
				System.out.println("All Feed Generated Succefully");
				 SpringApplication.exit(context);
				System.exit(1);
			}
		}
	}

	/*
	 * THis take argument and return thread object
	 */
	public static Thread makeThread(ReadData read, String type, boolean is_comm, boolean isread) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					if (isread) {
						read.readData(type, is_comm);
					} else {
						read.writeDataToXmlFile();
					}

				} catch (InterruptedException e) {
//                   e.printStackTrace(); 
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		return thread;
	}

}
