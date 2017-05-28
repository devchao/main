package com.devchao.memory;

import java.util.LinkedList;
import java.util.List;

public class Test {
	private static List<int[]> intList = new LinkedList<>();
	public static void useMemory() {
		
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		while(true) {
			char[] chars = new char[1024 * 512];
			System.out.println(chars.toString());
			
			intList.add(new int[1024 * 256]);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		useMemory();
	}
}
