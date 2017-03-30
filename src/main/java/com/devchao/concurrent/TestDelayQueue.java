package com.devchao.concurrent;

import java.util.Calendar;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * add时，序号小的排前面，序号相同排后面
 * get时，序号小的排前面，序号相同靠运气
 */
public class TestDelayQueue {
	
	public static void main(String[] args) throws InterruptedException {
		DelayQueue<Obj> dq = new DelayQueue<Obj>();
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.SECOND, 5);
		
		Obj n = new Obj(1, c.getTime().getTime(), 1);
		dq.put(n);
		
		Obj n2 = new Obj(2, c.getTime().getTime(), 1);
		dq.put(n2);
		
		Obj n3 = new Obj(3, c.getTime().getTime(), 10);
		dq.put(n3);
		
		Obj n4 = new Obj(4, c.getTime().getTime(), 1);
		dq.put(n4);
		
		Obj n5 = new Obj(5, c.getTime().getTime() + 8 * 1000, 1);
		dq.put(n5);
		
		while(true) {
			Obj temp = dq.take();
			System.out.println(temp.getIndex());
		}
	}
}

class Obj implements Delayed {

	private long time;
	private int index;
	private int weight;
	
	public Obj(int index, long time, int weight) {
		this.index = index;
		this.time = time;
		this.weight = weight;
	}
	
	@Override
	public long getDelay(TimeUnit unit) {
		System.out.println("delay called：" + index);
		return unit.convert(time - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}

	@Override
	public int compareTo(Delayed o) {
		System.out.println("compareTo called：" + index);
		Obj temp = (Obj)o;
		return this.weight - temp.weight;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
}