package com.revature.System;


import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



public class Driver{
	
	private static final Logger log = Logger.getLogger(Driver.class.getName());
	static UserDOA uDoa = new UserDOA();

	public static void main(String[] args) throws IOException{
		
		
		Prompt prom = new Prompt();
		/*
		 * FileOutputStream fos = new FileOutputStream("debug.log"); Layout layout = new
		 * PatternLayout(); WriterAppender app = new WriterAppender(layout, fos);
		 * BasicConfigurator.configure(); log.addAppender(app);
		 */ 
		PropertyConfigurator.configure("log4j.properties");
        log.info("Starting Application");

		AppTaskManager loginSession = new AppTaskManager("loginSession");
		//File 
		uDoa.setLoggedIn(false);

		while(!uDoa.isLoggedIn()) {
		try {
			//Thread t1 = new Thread(loginSession, "T1");
			//t1.start();
			prom.startUp();
			TimeUnit.SECONDS.sleep(60);

		} catch (InterruptedException e) {
			 log.warn("System Timeout");
			 loginSession.stop();
			e.printStackTrace();
		}

		}
	}
}
