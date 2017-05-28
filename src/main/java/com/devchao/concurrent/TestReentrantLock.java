package com.devchao.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock {
	private ReentrantLock lock = new ReentrantLock();
	public static void main(String[] args) {
		
	}
	
	
	public void testCondition() {
		Condition cond = lock.newCondition();
		
	}
	
	
	
}

 