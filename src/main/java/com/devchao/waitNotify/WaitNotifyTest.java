package com.devchao.waitNotify;

public class WaitNotifyTest {
	
	public static void main(String[] args) {
		Task task = new Task();
		task.doTask();
		System.out.println(task.i);
		task.waken();
	}
}
