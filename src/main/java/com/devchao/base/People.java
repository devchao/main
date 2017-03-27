package com.devchao.base;

public class People {
	public void smile(){
		System.out.println("people smile!");
	}
	
	public void fly(){
		this.smile();
		System.out.println("people can fly!");
	}
}
