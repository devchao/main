package com.devchao.waitNotify;

import java.util.concurrent.CountDownLatch;

public class Task {
	
	private final Object metux = new Object();
	public int i = 0;
	private CountDownLatch latch = new CountDownLatch(2);
	
	public void job1() throws InterruptedException {
		
		synchronized (metux) {
			System.out.println("job1 start");
			for (int i = 0; i < 100; i++) {
				Thread.sleep(100);
			}
			
			i = 1000;
			System.out.println("job1 sleep end");
			latch.countDown();
			metux.wait();
		}
	}
	
	public void job2() throws InterruptedException {
		
		synchronized (metux) {
			System.out.println("job2 start");
			for (int i = 0; i < 100; i++) {
				Thread.sleep(100);
			}
			i = 5000;
			latch.countDown();
		}
	}
	
	
	public void waken() {
		synchronized (metux) {
			metux.notify();
		}
	}
	
	public void doTask() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					job1();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					job2();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("jobs completed");
	}
}
