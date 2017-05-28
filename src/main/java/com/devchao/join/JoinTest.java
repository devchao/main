package com.devchao.join;

import java.util.Random;

public class JoinTest implements Runnable {
	private int i;
	
	public JoinTest(int i) {
		this.i = i;
	}
	
	@Override
	public void run() {
		int seconds = new Random().nextInt(100) * 100;
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("thread" + i + " completed. cost:" + seconds);
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread th1 = new Thread(new JoinTest(1));
		Thread th2 = new Thread(new JoinTest(2));
		th1.start();
		th2.start();
		
		th1.join();
		th2.join();
		System.out.println("all completed");
	}
}
