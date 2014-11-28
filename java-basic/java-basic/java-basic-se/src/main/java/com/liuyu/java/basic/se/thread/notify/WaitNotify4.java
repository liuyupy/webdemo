package com.liuyu.java.basic.se.thread.notify;

import com.liuyu.java.basic.annotation.UnThreadSafe;

/**
 * 
 * @author pengyao
 */
public class WaitNotify4 {

	@UnThreadSafe(remark="shared monitors.1) spurious wakeups ; 2) missed signals")
	String monitor = "";
	
	boolean notifyFlag = false;
	
	public void doWait(){
		synchronized (monitor) {
			while(!notifyFlag){  //采用循环式检查解决假唤醒问题
				try{
					System.out.println("waiting!" + Thread.currentThread().getName());
					monitor.wait();
					System.out.println("waiting2!" + Thread.currentThread().getName());
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
	
	public void startWait(String name){
		Thread waitTh1 = new Thread(new Runnable() {
			public void run() {
				WaitNotify4.this.doWait();
			}
		},name);
		waitTh1.start();
	}
	
	public void startNotify(){
		Thread notifyTh = new Thread(new Runnable() {
			public void run() {
				WaitNotify4.this.doNotify();
			}
		});
		notifyTh.start();
	}

	public static void main(String[] args) {
		WaitNotify4 w1 = new WaitNotify4();
		w1.startWait("1");
		w1.startWait("2");
		w1.startWait("3");
		WaitNotify4 w2 = new WaitNotify4();
		w2.startWait("4");
		w2.startNotify(); //将会通知到 w1对应的wait线程,但由于notifyFlag未共享,导致 1)假唤醒 2)信号丢失
	}
}
