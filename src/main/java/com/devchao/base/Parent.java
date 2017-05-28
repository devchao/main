package com.devchao.base;

public class Parent {
	public void test() {
		System.out.println("88888" + this);
	}
	
	public void callTest() {
		this.test();
		System.out.println(this);
	}
}
