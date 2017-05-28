package com.devchao.base;

public class Son extends Parent {
	public void test() {
		System.out.println("899999999999");
	}
	
	public static void main(String[] args) {
		new Son().test();
		((Parent)new Son()).test();
		((Parent)new Son()).callTest();
	}
}
