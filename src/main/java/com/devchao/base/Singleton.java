package com.devchao.base;

public class Singleton {

	public static int n = 100;

	private Singleton() {
		System.out.println("new singleton");
	}

	public static Singleton getInstance() {
		// 外围类能直接访问内部类（不管是否是静态的）的私有变量
		return Holder.instance;
	}

	public static int get123() {
		return Holder.i;
	}

	static class Holder {
		private static int i = 123;
		private static Singleton instance = new Singleton();
	}
}
