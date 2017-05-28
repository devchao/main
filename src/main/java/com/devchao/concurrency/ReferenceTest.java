package com.devchao.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReferenceTest {

	private List<String> bigList = new ArrayList<>();

	public static void main(String[] args) {
		ReferenceTest test = new ReferenceTest();
		int i = 0;
		while(true) {
			test.bigList.add(new Random().nextLong() + "");
			if(++i >= 1000) break;
		}
		
		new Thread(test.new ChangeThread()).start();
		new Thread(test.new ReadThread()).start();
	}

	class ChangeThread implements Runnable {

		@Override
		public void run() {
			
			while(true) {
				
				try {
					Thread.sleep(new Random().nextInt(1));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				int count = new Random().nextInt(10000) + 1;
				List<String> temp = new ArrayList<>();
				while(true) {
					temp.add(new Random().nextLong() + "");
					if(count-- <= 0) break;
				}
				bigList = temp;
			}
		}
	}
	
	class ReadThread implements Runnable {

		@Override
		public void run() {
			while(true) {
//				try {
//					Thread.sleep(new Random().nextInt(2));
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				
				
				List<String> temp = bigList;
				int i = temp.size();
				System.out.println("bigList的size：" + i);
				System.out.println("bigList的最后值：" + temp.get(i - 1));
			}
		}
	}

}
