package com.devchao.base;

import net.sf.cglib.proxy.Enhancer;

public class Test {
	public static void main(String[] args) {
		
	}
	
	/**
	 * 代理对象一个方法里调用了本对象的另外一个方法，2个方法都是代理过的。
	 * 结果表明：spring的事务嵌套，被调用的方法也是代理对象的方法
	 */
	public static void proxyTest(){
		Enhancer enhancer = new Enhancer();  
		enhancer.setSuperclass(People.class);  
		enhancer.setCallback(new ProxyPeople());  
		People userService = (People)enhancer.create();
		userService.fly();
	}
}
