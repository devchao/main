package com.devchao.base;

public class People {
	
	static {
		System.out.println("People class init");
	}
	
	public People(){
		System.out.println("people init");
	}
	
	public void smile(){
		System.out.println("people smile!");
	}
	
	public void fly(){
		this.smile();
		System.out.println("people can fly!");
	}
	
	
	public void loadString(){
		try {
			System.out.println(Class.forName("java.lang.String").getClassLoader());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  
	}
}
