package com.devchao.base;

import net.sf.cglib.proxy.Enhancer;

public class Test {
	
	public static void main(String[] args) throws ClassNotFoundException {
		customsClassLoaderTest();
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
	
	//================================================================
	
	/**
	 * 结果表明类被加载的时候会实例化静态字段
	 */
	public static void singleTest(){
		System.out.println(Singleton.n);
		System.out.println(Singleton.getInstance());
	}
	
	//================================================================
	
	/**
	 * 结论是改变变量地址无效
	 */
	public static void changeTest() {
		String[] s = new String[5];
		System.out.println(s.length);
		change(s);
		System.out.println(s.length);
		change2(s);
		for (int i = 0; i < s.length; i++) {
			System.out.println(s[i]);
		}
	}
	
	private static void change(String[] s) {
		s = new String[8];
	}
	
	private static void change2(String[] s) {
		s[4] = "123";;
	}
	
	//================================================================
	/*
	 * 静态块和静态变量按顺序在加载的时候执行
	 */
	/*
	static {
		people = new People();//people没有定义，会先定义再实例化
		System.out.println("cccc");
	}
	
	public static People people = new People();
	*/
	
	//================================================================
	
	/**
	 * 不会引起SimpleClass的初始化，却引起了[Lcom.devchao.base.SimpleClass的初始化
	 * 由虚拟机自动生成的，直接继承于Object的子类
	 * 创建动作由字节码指令newarray触发
	 */
	public static void arrayClassInitTest(){
		SimpleClass[] arr = new SimpleClass[10];
		System.out.println(arr.toString());
	}
	
	//================================================================
	
	/**
	 * 对常量SimpleClass.i 的引用实际都被转化为Test类对自身常量池的引用
	 * 这两个类被编译成class后不存在任何联系。
	 */
	public static void finalStaticTest() {
		System.out.println(SimpleClass.i);
	}
	
	//================================================================
	
	/**
	 * ClassLoad的几种现象
	 */
	public static void classLoadTest() throws ClassNotFoundException {
		//Class.forName("com.devchao.base.SimpleClass");//完全加载一个类
		//Class<?>  c = SimpleClass.class;//不会执行static代码块
		//c.getClassLoader().loadClass("com.devchao.base.SimpleClass");//不会执行static代码块
	}
	
	//================================================================
	/**
	 * 双亲委派模型，即使是自定义的ClassLoader，也会先尝试ParentLoader
	 * 作用是防止不同loader加载了同一个类，导致出现多份同样的类在内存中
	 * class.forName会使用调用者的ClassLoader
	 */
	public static void customsClassLoaderTest(){
		MyClassLoader myClassLoader = new MyClassLoader();  
		System.out.println(myClassLoader.toString());
		
		try {
			Class<?> c = myClassLoader.loadClass("java.lang.String");
			System.out.println(c.getClassLoader());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			myClassLoader.setClassFileName("D:\\OutsideClass.class");//跟People.java内容一样
			Class<?> p = myClassLoader.loadClass("com.devchao.base.OutsideClass");
			System.out.println(p.getClassLoader());
			System.out.println(p.getClassLoader().getParent());
			System.out.println(p.getClassLoader().getParent().getParent());
			p.getMethod("loadString").invoke(p.newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
