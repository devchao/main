package com.devchao.memoryError;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class Test {
	
	private long i;
	
	public long getI() {
		return i;
	}

	public void setI(long i) {
		this.i = i;
	}
	
	public Test(long i) {
		this.setI(i);
	}
	
	public Test() {
	}
	
	public static void main(String[] args) {
//		test1();
		
//		try {
//			test2();
//		} catch(Throwable e) {
//			System.out.println(dep);
//			e.printStackTrace();
//		}
		
//		test3();
//		new Test().proxyTest();
//		memoryAll();
	}
	
	/**
	 * 堆内存溢出
	 * 先设置vm参数：-Xmx2m.
	 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	 */
	public static void test1() {
		List<Test> list = new ArrayList<Test>();
		for(long i = 0; i < 1000000L; i++) {
			list.add(new Test(i));
		}
	}
	
	/**
	 * 栈内存溢出
	 * 先设置参数：-Xss2m
	 * java.lang.StackOverflowError
	 */
	public static int dep = 0;
	public static void test2() {
		dep++;
		test2();
	}
	
	/**
	 * -Xmx20m -Xms20m -XX:-UseGCOverheadLimit（关闭gc占时过长98%的检测）
	 * 运行时常量池溢出，jdk1.7常量池改为存放引用，不会报
	 * Exception in thread "main" java.lang.OutOfMemoryError: PermGen space 
	 * 由于对象存放到了堆，故报了
	 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	 */
	public static void test3() {
		List<String> list = new ArrayList<>();
		for(int i = 0; i < Integer.MAX_VALUE; i++) {
			list.add(new String(i + "").intern());
		}
	}

	public void proxyTest(){
		List<Object> list = new ArrayList<>();
		
		try {
			while(true) {
				Enhancer enhancer = new Enhancer();  
				enhancer.setSuperclass(Test.class);  
				enhancer.setUseCache(false); 
				enhancer.setCallback(new MethodInterceptor() {
	
					@Override
					public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
						return proxy.invokeSuper(obj, args);
					}
					
				});  
				list.add(enhancer.create());
			}
		} catch(Throwable t) {
			t.printStackTrace();
		}
	}
	
	
	public static void memoryAll() {
		System.out.println(Runtime.getRuntime().totalMemory() / 1024 / 1024);
		System.out.println(Runtime.getRuntime().freeMemory() / 1024 / 1024);
		System.out.println(Runtime.getRuntime().maxMemory() / 1024 / 1024);
	}
	
}
