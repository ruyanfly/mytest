package oldTest;

//import java.util.ArrayList;
import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

class MyThread implements Runnable {
	private static int num = 0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			synchronized (MyThread.class) {
				++num;
				try {
					Thread.sleep(500);
				} catch(Exception e) {
					System.out.println(e.toString());
				}
				System.out.println(Thread.currentThread().getName() + " " + num);
			}
		}
	}
}

class MyCallableThread implements Callable<String> {
	private static int num = 0;
	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		for(int i=0; i<5; ++i) {
			synchronized (MyCallableThread.class) {
				++num;
				Thread.sleep(200);
				System.out.println(Thread.currentThread().getName() + " " + num);
			}
		}
		return Thread.currentThread().getName() + " success!";
	}
}

class MyRunnableThread implements Runnable {
	private int priority;
	public MyRunnableThread() {
		
	}
	public MyRunnableThread (int priority) {
		this.priority = priority;
	}
	private static int num = 0;
	@SuppressWarnings("unused")
	private volatile double d;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Thread.currentThread().setPriority(priority);
		while (true) {
			for (int i=0; i<100000; ++i) {
				d += (Math.PI + Math.E)/(double)i;
				if (i%1000 == 0)
					Thread.yield();
			}
			synchronized (MyRunnableThread.class) {
				++num;
				try {
					Thread.sleep(500);
				} catch(Exception e) {
					System.out.println(e.toString());
				}
				System.out.println(Thread.currentThread().getName() + " " + num);
			}
		}
	}
	
}

class SimpleDaemons implements Runnable{
	@Override
	public void run() {
		while(true) {
			try {
				TimeUnit.MILLISECONDS.sleep(200);
				System.out.println(Thread.currentThread().getName());
			} catch(InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + " : InterruptException!");
				e.printStackTrace();
			}
        }
    }
}

class DaemonThreadFactory implements ThreadFactory{
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}

class DaemonFromFactory implements Runnable{
    @Override
    public void run() {
        try{
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

public class ThreadPools {
	public static void main(String[] args) throws InterruptedException {
//		ExecutorService exes = Executors.newCachedThreadPool();
//		ExecutorService exes = Executors.newSingleThreadExecutor();
//		ExecutorService exes = Executors.newSingleThreadScheduledExecutor();
//		ExecutorService exes = Executors.newScheduledThreadPool(3);
		ExecutorService exes = Executors.newFixedThreadPool(3);

//		for(int i=0; i<5; ++i)
//			exes.execute(new MyThread());
//		exes.shutdown();
		
//		ArrayList<Future<String>> rets = new ArrayList<Future<String>>();
//		for(int i=0; i<5; ++i)
//			rets.add((Future<String>) exes.submit(new MyCallableThread()));
//		for(Future<String> fs : rets) {
//			try {
//				System.out.println(fs.get());
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			} catch (ExecutionException e) {
//				e.printStackTrace();
//			}
//        }
		
//        for(int i=0; i<5; ++i)
//        	exes.execute(new MyRunnableThread(Thread.MIN_PRIORITY));
//        exes.execute(new MyRunnableThread(Thread.MAX_PRIORITY));
//        exes.shutdown();
		
//        for(int i=0; i<10; ++i){
//        	Thread daemon = new Thread(new SimpleDaemons());
//        	daemon.setDaemon(true);
//        	daemon.start();
//        }
//        System.out.println("All daemons started");
//        TimeUnit.MILLISECONDS.sleep(1000);
		
        for(int i=0; i<5; ++i)
            exes.execute(new DaemonFromFactory());
        System.out.println("All Daemos Started!");
        TimeUnit.MILLISECONDS.sleep(1000);
	}
}
