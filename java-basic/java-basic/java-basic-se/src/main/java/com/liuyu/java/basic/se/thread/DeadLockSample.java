package com.liuyu.java.basic.se.thread;

public class DeadLockSample {

	private Object lockObjA = new Object();
	private Object lockObjB = new Object();
	
	public void methodA(){
		synchronized (lockObjA) {
			try {
				Thread.sleep(5000);
				synchronized (lockObjB) {
					System.out.println("methodA");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void methodB(){
		synchronized (lockObjB) {
			try {
				Thread.sleep(5000);
				synchronized (lockObjA) {
					System.out.println("methodA");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void doTest(){
		Thread a = new Thread(new Runnable() {
			public void run() {
				methodA();
			}
		});
		
		Thread b = new Thread(new Runnable() {
			public void run() {
				methodB();
			}
		});
		a.start();
		b.start();
	}
	
	public static void main(String[] args) {
		new DeadLockSample().doTest();
	}
}
