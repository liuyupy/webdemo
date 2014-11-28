package com.liuyu.java.basic.se.thread.notify;

import com.liuyu.java.basic.annotation.UnThreadSafe;

/**
 * 最简单的wait notify示例. </br>
 * wait线程 和 nofity线程 由于执行先后顺序的不确定性,可能会导致wait线程一直处理等待被唤醒状态.
 * 即<strong>丢失信号问题</strong>
 * 
 * @author pengyao
 */
public class WaitNotify1 {
	
	private Object monitor = new Object();

	@UnThreadSafe(remark="Missed Signals")
	public void doWait(){
		synchronized (monitor) {
			try{
				monitor.wait();
				System.out.println("end waiting!");
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	public void doNotify(){
		synchronized (monitor) {
			try{
				monitor.notify();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	public void doMonitor(){
		Thread notifyTh = new Thread(new Runnable() {
			public void run() {
				WaitNotify1.this.doNotify();
			}
		});
		Thread waitTh = new Thread(new Runnable() {
			public void run() {
				WaitNotify1.this.doWait();
			}
		});
		/**
		 * 说明: 此处很容易让 wait线程 一直处于等待状态. 为了解决wait线程一直等待,可参考 WaitNofity2
		 */
		notifyTh.start();
		waitTh.start();
	}

	public static void main(String[] args) {
		new WaitNotify1().doMonitor();
	}
}
