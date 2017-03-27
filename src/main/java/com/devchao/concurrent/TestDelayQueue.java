package com.devchao.concurrent;

import java.util.Calendar;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class TestDelayQueue {
	
	public static void main(String[] args) throws InterruptedException {
		DelayQueue<Obj> dq = new DelayQueue<Obj>();
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.SECOND, 5);
		
		Obj n = new Obj(c.getTime().getTime());
		System.out.println(n.getDelay(TimeUnit.MILLISECONDS) + n.toString());
		dq.put(n);
		
		Obj n2 = new Obj(c.getTime().getTime() + 15 * 1000);
		System.out.println(n2.getDelay(TimeUnit.MILLISECONDS) + n2.toString());
		dq.put(n2);
		
		Obj n3 = new Obj(c.getTime().getTime());
		System.out.println(n3.getDelay(TimeUnit.MILLISECONDS) + n3.toString());
		dq.put(n3);
		
		Obj n4 = new Obj(c.getTime().getTime());
		System.out.println(n4.getDelay(TimeUnit.MILLISECONDS) + n4.toString());
		dq.put(n4);
		
		Obj n5 = new Obj(c.getTime().getTime() + 8 * 1000);
		System.out.println(n5.getDelay(TimeUnit.MILLISECONDS) + n5.toString());
		dq.put(n5);
		
		Obj temp = null;
		temp = dq.take();
		System.out.println(temp.toString() + temp.getDelay(TimeUnit.SECONDS));
		temp = dq.take();
		System.out.println(temp.toString() + temp.getDelay(TimeUnit.SECONDS));
		temp = dq.take();
		System.out.println(temp.toString() + temp.getDelay(TimeUnit.SECONDS));
		temp = dq.take();
		System.out.println(temp.toString() + temp.getDelay(TimeUnit.SECONDS));
		temp = dq.take();
		System.out.println(temp.toString() + temp.getDelay(TimeUnit.SECONDS));
	}
}

class Obj implements Delayed {

	private long time;
	
	public Obj(long time) {
		this.time = time;
	}
	
	@Override
	public int compareTo(Delayed o) {
		return this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS) >= 0 ? 1 : -1;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(time - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}
	
}