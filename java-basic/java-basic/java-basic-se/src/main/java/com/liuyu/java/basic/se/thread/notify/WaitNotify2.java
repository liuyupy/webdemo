package com.liuyu.java.basic.se.thread.notify;

import com.liuyu.java.basic.annotation.UnThreadSafe;

/**
 * {@link WaitNotify1} 改进版.通过标志位,解决 丢失信号问题. 
 * 无法解决假唤醒问题.
 * @author pengyao
 */
public class WaitNotify2 {
	
	private Object monitor = new Object();
	boolean notifyFlag = false;
	
	@UnThreadSafe(remark="spurious wakeups.假唤醒问题")
	public void doWait(){
		synchronized (monitor) {
			if(!notifyFlag){ /** {@link WaitNotify3} 通过while循环解决*/
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
			notifyFlag= true;  //注意,一定要先置标志位后再notify
			monitor.notify();
		}
	}
	
	public void doMonitor(){
		Thread notifyTh = new Thread(new Runnable() {
			public void run() {
				WaitNotify2.this.doNotify();
			}
		});
		Thread waitTh1 = new Thread(new Runnable() {
			public void run() {
				WaitNotify2.this.doWait();
			}
		});
		waitTh1.start();
		notifyTh.start();
	}

	public static void main(String[] args) {
		new WaitNotify2().doMonitor();
	}
}
