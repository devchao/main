package com.devchao.waitNotify;

public class WaitNotifyDemo {
	
	private static final Object obj = new Object();
	
	
	public static void main(String[] args) {
		new Thread(new Waiter()).start();
		new Thread(new Notifyer()).start();
	}

	static class Waiter implements Runnable {
		
		@Override
		public void run() {
			
			System.out.println("waiter working");
			
			//do things 
			//......
			
			synchronized (obj) {
				try {
					obj.wait();//wait for another thread's notify
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			//and do things
			//......
			
			System.out.println("waiter done");
		}
	}
	
	static class Notifyer implements Runnable {
		
		@Override
		public void run() {
			
			System.out.println("notifyer working");
			
			//do things 
			//......
			
			synchronized (obj) {
				obj.notify();
			}
			
			System.out.println("notifyer done");
		}
	}
	
	
	
	
}
