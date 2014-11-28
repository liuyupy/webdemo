package com.liuyu.java.basic.se.thread.notify;

import com.liuyu.java.basic.annotation.UnThreadSafe;

public class WaitNotify3 {
	
	private Object monitor = new Object();
	
	@UnThreadSafe
	boolean notifyFlag = false;
	//volatile boolean notifyFlag = false;
	
	public void doWait(){
		synchronized (monitor) {
			while(!notifyFlag){  //采用循环式检查解决假唤醒问题
				try{
					System.out.println("waiting!");
					monitor.wait();
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
			System.out.println("end waiting!");
			notifyFlag = false; //
		}
	}
	
	public void doNotify(){
		synchronized (monitor) {
			notifyFlag= true;  
			monitor.notify();
		}
	}
	
	public void doMonitor(){
		Thread notifyTh = new Thread(new Runnable() {
			public void run() {
				WaitNotify3.this.doNotify();
			}
		});
		Thread waitTh1 = new Thread(new Runnable() {
			public void run() {
				WaitNotify3.this.doWait();
			}
		});
		waitTh1.start();
		notifyTh.start();
	}

	public static void main(String[] args) {
		new WaitNotify3().doMonitor();
	}
}
