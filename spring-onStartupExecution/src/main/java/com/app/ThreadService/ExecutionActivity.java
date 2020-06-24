package com.app.ThreadService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.callonstartup.CallOnStartup;

@Service
public class ExecutionActivity {
	Logger logger = LoggerFactory.getLogger(ExecutionActivity.class);

	public void doSomethingonStartUp() {
		logger.info("Called onStrtUp1");
		logger.info("Called onStrtUp1");
		logger.info("Called onStrtUp1");
	}

	public void doSomethingonStartUp2() {
		logger.info("Called onStrtUp2");
		logger.info("Called onStrtUp2");
		logger.info("Called onStrtUp2");
	}

	public void doSomethingonStartUp3() {
		logger.info("Called onStrtUp3");
		logger.info("Called onStrtUp3");
		logger.info("Called onStrtUp3");
	}
}
