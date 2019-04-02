package oldTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadTest {

	public static class MyThread extends Thread {
		public void run() {
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName() + "在运行!");
			}
		}
	}
	
	public static class MyRunnable implements  Runnable {
		public volatile boolean flag = true;
		public void run() {
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName() + "在运行!");
			}
	        
			while (flag) {
				
	        }  
	        //System.out.println(Thread.currentThread().getName() + "线程终止");
	        
	        while(Thread.currentThread().isInterrupted()){
	        	
	        }  
	        //System.out.println(Thread.currentThread().getName() + " 线程被中断"); 
		}
	}
	
	static int array;
	
	public void fixedThreadPool() {
		ExecutorService fixedThreadPool =Executors.newFixedThreadPool(3);
		for (int i = 1; i <= 5; i++){
			final int index = i;
			fixedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("第" +index + "个线程" +Thread.currentThread().getName());
						Thread.sleep(1000);
					} catch(InterruptedException e ) {
						e .printStackTrace();
					}
				}
			});
		}
	}
	
	public void cacheThreadPool() {
		ExecutorService cacheThreadPool =Executors.newCachedThreadPool();
		for(int i = 1; i <= 5; i++) {
			final int index = i;
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e ) {
				e.printStackTrace();
			}
			cacheThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("第" +index +"个线程" +Thread.currentThread().getName());
				}
			});
		}
	}
	
	public void scheduledThreadPool() {
		ScheduledExecutorService scheduledThreadPool= Executors.newScheduledThreadPool(3);
		scheduledThreadPool.schedule(new Runnable() {
			@Override
			public void run() {
				System.out.println("延迟三秒");
			}
		}, 3, TimeUnit.SECONDS);
	}
	
	public void singleThreadPool() {
		ExecutorService singleThreadPool= Executors.newSingleThreadExecutor();
		for(int i = 1; i <= 5; i++) {
			final int index = i;//这里需要用final修饰的原因是内部类只能调用fianl修饰变量
			singleThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("第"+index+"个线程");
						Thread.sleep(2000);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}		
	}
	
	public static void main(String[] args) throws InterruptedException {
//		MyThread t = new MyThread();
//		//MyRunnable mt0 = new MyRunnable();
//		//Thread t = new Thread(mt0);
//		//t.setPriority(5);//对main函数无效或者说根本就没有赋值成功
//		t.start();
//		//t.interrupt();//使用了这个已经进行没有用stop这么直接
//		//t.stop();	
//		for (int i = 0; i < 5; i++) {
//			System.out.println(Thread.currentThread().getName() + "在运行！" + t.getPriority());
//		}
//		array = 10;
//		//mt0.flag = false;
		//new ThreadTest().cacheThreadPool();
		//new ThreadTest().fixedThreadPool();
		//new ThreadTest().scheduledThreadPool();
		new ThreadTest().singleThreadPool();
	}
}
