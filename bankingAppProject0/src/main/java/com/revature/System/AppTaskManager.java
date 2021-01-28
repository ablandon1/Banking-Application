package com.revature.System;

public class AppTaskManager implements Runnable{
	
	//private Thread t;
	private String threadName;
	private volatile boolean exit = false;
	
	AppTaskManager (String name) {
		threadName = name;
		//System.out.println("Creating Thread " + threadName);
	}
	
	public void run() {
		//System.out.println("RunningThread " + threadName);
		/*  for(int i = 4; i > 0; i--) {
			System.out.println("Thread: " + threadName + ", " + i);
			//Let the thread sleep for 50 milliseconds
			Thread.sleep(50);
		}
}*/
		if(!exit){
			System.out.println(threadName + " is running..");
		}
		else{
			System.out.println(threadName + " has stopped");
		}

	}
	
	/*
	 * public void start(){
	 * 
	 * //System.out.println("Starting " + threadName); if(t == null) { t = new
	 * Thread (this, threadName); t.start(); } } public void stop() { exit = true; }
	 */
	public void stop() {
		exit = true;
	}

}
