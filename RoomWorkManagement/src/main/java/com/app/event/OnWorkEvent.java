package com.app.event;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationEvent;

import com.app.model.WorkRoom;
public class OnWorkEvent extends ApplicationEvent {
	private WorkRoom worker;
	@Autowired
	public OnWorkEvent(WorkRoom user) {
		super(user);
	  this.worker=user;
	}
	public WorkRoom getWorker() {
		return worker;
	}
	public void setWorker(WorkRoom worker) {
		this.worker = worker;
	}
	
	

}
