package com.devchao.concurrent;

import java.util.concurrent.CountDownLatch;

import com.devchao.utils.StringUs;  
/** 
 * 示例：CountDownLatch的使用举例 
 * Mail: ken@iamcoding.com 
 * @author janeky 
 */  
public class TestCountDownLatch {  
    private static final int N = 10;  
  
    public static void main(String[] args) throws InterruptedException {  
        CountDownLatch doneSignal = new CountDownLatch(N);  
        CountDownLatch startSignal = new CountDownLatch(1);//开始执行信号  
  
        for (int i = 1; i <= N; i++) {  
            new Thread(new Worker(doneSignal, startSignal)).start();//线程启动了  
        }  
        System.out.println("begin------------");  
        startSignal.countDown();//开始执行啦  
        doneSignal.await();//等待所有的线程执行完毕  
        System.out.println("OK");  
    }  
  
    static class Worker implements Runnable {  
        private final CountDownLatch doneSignal;  
        private final CountDownLatch startSignal;  
         
  
        Worker(CountDownLatch doneSignal, CountDownLatch startSignal) {  
            this.startSignal = startSignal;  
            this.doneSignal = doneSignal;  
        }  
  
        public void run() {  
            try {  
                startSignal.await(); //等待开始执行信号的发布  
                System.out.println(StringUs.getRandomString(10));
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            } finally {  
                doneSignal.countDown();  
            }  
        }  
    }  
}  