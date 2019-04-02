package myTest;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class BaseLock implements Runnable {	
	public static ReentrantLock lock = new ReentrantLock();
	public static int i = 0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int j = 0; j < 10000; j++) {
			lock.lock();
			try {
				i++;
			}finally {
				lock.unlock();
			}
		}
	}
}

class  KillDeadLock implements Runnable{
	public static ReentrantLock lock1 = new ReentrantLock();
	public static ReentrantLock lock2 = new ReentrantLock();
	int lock;
	public KillDeadLock(int lock) {
		this.lock = lock;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			if (lock == 1) {
				lock1.lockInterruptibly();  // 以可以响应中断的方式加锁
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {}
				lock2.lockInterruptibly();
			} else {
				lock2.lockInterruptibly();  // 以可以响应中断的方式加锁
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {}
				lock1.lockInterruptibly();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (lock1.isHeldByCurrentThread()) lock1.unlock();  // 注意判断方式
			if (lock2.isHeldByCurrentThread()) lock2.unlock();
			System.err.println(Thread.currentThread().getId() + "退出！");
		}
	}	
}

class TryLock implements Runnable{
	public static ReentrantLock lock = new ReentrantLock();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			if(lock.tryLock(1, TimeUnit.SECONDS)) { // 等待1秒
				Thread.sleep(2000); //休眠2秒
			}else {
				System.err.println(Thread.currentThread().getName() + "获取锁失败！");
			}
		}catch(Exception e){
			if (lock.isHeldByCurrentThread()) lock.unlock();
		}
	}
}

class FairLock implements Runnable{
	public static ReentrantLock lock = new ReentrantLock(true); 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				lock.lock();
				System.err.println(Thread.currentThread().getName() + "获取到了锁！");
			} finally {
				lock.unlock();
			}
		}	
	}
}

interface Condition {
	void await() throws InterruptedException; // 类似于Object.wait()
	void awaitUninterruptibly(); // 与await()相同，但不会再等待过程中响应中断
	long awaitNanos(long nanosTimeout) throws InterruptedException;
	boolean await(long time, TimeUnit unit) throws InterruptedException;
	boolean awaitUntil(Date deadline) throws InterruptedException;
	void signal(); // 类似于Obejct.notify()
	void signalAll();
}

interface Lock {
	void lock();
	void lockInterruptibly() throws InterruptedException;
	boolean tryLock();
	boolean tryLock(long time, TimeUnit unit) throws InterruptedException;
	void unlock();
	Condition newCondition();
}

class ReentrantLockWithConditon implements Runnable{
	public static ReentrantLock lock = new ReentrantLock(true);
	public static Condition condition = (Condition) lock.newCondition();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		lock.newCondition();
		try {
			lock.lock();
			System.err.println(Thread.currentThread().getName() + "-线程开始等待...");
			condition.await();
			System.err.println(Thread.currentThread().getName() + "-线程继续进行了");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

public class ReentrantLockTest {	
	public static void main(String[] args) throws InterruptedException {
//		BaseLock baseLock = new BaseLock();
//		Thread t1 = new Thread(baseLock);
//		Thread t2 = new Thread(baseLock);
//		t1.start();t2.start();
//		t1.join();t2.join();
//		System.err.println(baseLock.i);
		
//		KillDeadLock deadLock1 = new KillDeadLock(1);
//		KillDeadLock deadLock2 = new KillDeadLock(2);
//		Thread t3 = new Thread(deadLock1);
//		Thread t4 = new Thread(deadLock2);
//        t3.start();t4.start();
//        Thread.sleep(1000);
//        t4.interrupt();
        
//        TryLock tryLock = new TryLock();
//        Thread t1 = new Thread(tryLock); t1.setName("线程1");
//        Thread t2 = new Thread(tryLock); t1.setName("线程2");
//        t1.start();t2.start();
		
//		FairLock fairLock = new FairLock();
//		Thread t1 = new Thread(fairLock, "线程1");
//		Thread t2 = new Thread(fairLock, "线程2");
//		t1.start();t2.start();
		
//		ReentrantLockWithConditon test = new ReentrantLockWithConditon();
//        Thread t = new Thread(test, "线程ABC");
//        t.start();
//        Thread.sleep(1000);
//        System.err.println("过了1秒后...");
//        lock.lock();
//        condition.signal(); // 调用该方法前需要获取到创建该对象的锁否则会产生
//                            // java.lang.IllegalMonitorStateException异常
//        lock.unlock();
	}
}
