package com.liuyu.java.basic.se.thread.lock;

import java.util.ArrayList;
import java.util.List;

/**
 * 公平锁
 * @author pengyao
 *
 */
public class FairLock {
	private boolean isLocked = false;
	private Thread lockingThread = null;
	private List<PrivateLockObject> waitingThreads = new ArrayList<PrivateLockObject>();

	public void lock() throws InterruptedException {
		PrivateLockObject privateLockObj = new PrivateLockObject();
		boolean isLockedForThisThread = true;
		synchronized (this) {
			waitingThreads.add(privateLockObj);
		}

		while (isLockedForThisThread) {
			synchronized (this) {
				isLockedForThisThread = isLocked
						|| waitingThreads.get(0) != privateLockObj;
				if (!isLockedForThisThread) {
					isLocked = true;
					waitingThreads.remove(privateLockObj);
					lockingThread = Thread.currentThread();
					return;
				}
			}
			try {
				privateLockObj.doWait();
			} catch (InterruptedException e) {
				synchronized (this) {
					waitingThreads.remove(privateLockObj);
				}
				throw e;
			}
		}
	}

	public synchronized void unlock() {
		if (this.lockingThread != Thread.currentThread()) {
			throw new IllegalMonitorStateException(
					"Calling thread has not locked this lock");
		}
		isLocked = false;
		lockingThread = null;
		if (waitingThreads.size() > 0) {
			waitingThreads.get(0).doNotify();
		}
	}
}
