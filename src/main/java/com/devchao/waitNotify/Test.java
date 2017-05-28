package com.devchao.waitNotify;

public class Test {
	
	private Object obj = new Object();
	
	public synchronized void sync() throws InterruptedException {
		this.wait();//correct usage
	}
	
	public synchronized void sync2() throws InterruptedException {
		obj.wait();//wrong usage
	}
	
	public void sync3() throws InterruptedException {
		synchronized(obj) {
			obj.wait();//correct usage
		}
	}
	
	public void sync4() throws InterruptedException {
		synchronized(this) {
			obj.wait();//wrong usage
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		//new Test().sync();
		outObjSync();
	}
	
	
	public static void outObjSync() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				outObjSync1();
			} 
		}).start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				outObjSync2();
			} 
		}).start();
	}
	
	public static void outObjSync1() {
		synchronized (OutObj.obj) {
			for(int i = 0; i < 1000; i++) {
				System.out.println("outObjSync1:" + i);
			}
		}
	}
	
	public static void outObjSync2() {
		synchronized (OutObj.obj) {
			for(int i = 0; i < 1000; i++) {
				System.out.println("outObjSync2:" + i);
			}
		}
	}
	
}
