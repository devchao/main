package com.devchao.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class IntTest {
	
	private long num = 1000;
	private long[] arr = new long[] {Long.MAX_VALUE / 5, Long.MAX_VALUE / 4 , Long.MAX_VALUE / 3, Long.MAX_VALUE / 2, Long.MAX_VALUE};

	public static void main(String[] args) {
		IntTest test = new IntTest();
		new Thread(test.new ChangeThread()).start();
		new Thread(test.new ReadThread()).start();
		new Thread(test.new ReadThread()).start();
		new Thread(test.new ReadThread()).start();
		new Thread(test.new ReadThread()).start();
		new Thread(test.new ReadThread()).start();
		new Thread(test.new ReadThread()).start();
		new Thread(test.new ReadThread()).start();
		new Thread(test.new ReadThread()).start();
		new Thread(test.new ReadThread()).start();
	}

	class ChangeThread implements Runnable {

		@Override
		public void run() {
			
			while(true) {
				
//				try {
//					Thread.sleep(new Random().nextInt(1));
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				
				int index = new Random().nextInt(5);
				long b = arr[index];
				num = b;
			}
		}
	}
	
	class ReadThread implements Runnable {

		@Override
		public void run() {
			Map<Long, String> map = new HashMap<>();
			for(int i = 0; i < arr.length; i++) {
				map.put(arr[i], "abc");	
			}
			
			while(true) {
				
				try {
					Thread.sleep(new Random().nextInt(1));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				if(map.get(num) == null) {
					System.out.println("run error");
				}
			}
		}
	}
}
