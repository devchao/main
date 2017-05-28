package com.devchao.interrupt;

public class Test implements Runnable {

	@Override
	public void run() {
		int i = 0;
		while(!Thread.interrupted() && i < 100000) {
			i++;
			System.out.println("print:" + i);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread th = new Thread(new Test());
		th.start();
		
		Thread.sleep(10);
		th.interrupt();
	}

}
