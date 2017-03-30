package com.devchao.concurrent;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * add时，序号小的排前面，序号相同排后面
 * get时，序号小的排前面，序号相同靠运气
 */
public class TestPriorityBlockingQueue {
	
	public static void main(String[] args) throws InterruptedException {
		PriorityBlockingQueue<Obj2> queue = new PriorityBlockingQueue<Obj2>();
		Obj2 obj = new Obj2(1000);
		Obj2 obj2 = new Obj2(1000);
		Obj2 obj3 = new Obj2(1000);
		Obj2 obj4 = new Obj2(1000);
		Obj2 obj5 = new Obj2(1000);
		Obj2 obj6 = new Obj2(100);
		queue.put(obj);
		queue.put(obj2);
		queue.put(obj3);
		queue.put(obj4);
		queue.put(obj5);
		queue.put(obj6);
		
		System.out.println(queue.take());
		System.out.println(queue.take());
		System.out.println(queue.take());
		System.out.println(queue.take());
		System.out.println(queue.take());
		System.out.println(queue.take());
	}
}


class Obj2 implements Comparable<Obj2> {

	private int proirity;
	
	public Obj2(int proitity) {
		this.proirity = proitity;
		System.out.println("proitity:" + proirity + " " + this);
	}
	
	@Override
	public int compareTo(Obj2 o) {
		return this.proirity - o.proirity;
	}
	
}