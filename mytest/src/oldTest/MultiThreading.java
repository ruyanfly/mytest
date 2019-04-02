package oldTest;

import oldTest.ThreadThree.Inner;

class ThreadOne implements Runnable{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized(this){
			for(int i=0; i<5; i++){
				System.out.println(Thread.currentThread().getName()+"synchronized loop"+i);
			}
		}
	}
}

class ThreadTwo implements Runnable{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	public void fun1(){
		synchronized(this){
			int i=5;
			while(i-- > 0){
                System.out.println(Thread.currentThread().getName() + " : " + i);  
                try {  
                     Thread.sleep(500);  
                } catch (InterruptedException ie) {  
                }  
			}
		}
	}
	
	public void fun2(){
//		synchronized(this){
			int i=5;
			while(i-- > 0){
                System.out.println(Thread.currentThread().getName() + " : " + i);  
                try {  
                     Thread.sleep(500);  
                } catch (InterruptedException ie) {  
                }  
			}
//		}
	}
	
	public synchronized void fun3(){
		int i=5;
		while(i-- > 0){
			System.out.println(Thread.currentThread().getName() + " : " + i);  
            try {
            	Thread.sleep(500);
            } catch (InterruptedException ie) {
            }
        }
	}
}

class ThreadThree implements Runnable{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	class  Inner{
		private void fun1(){
			int i=5;
			while(i-- > 0){
				System.out.println(Thread.currentThread().getName() + " :Inner.fun1()= " + i);  
	            try {
	            	Thread.sleep(500);
	            } catch (InterruptedException ie) {
	            }
	        }
		}
		
		private void fun2(){
			int i=5;
			while(i-- > 0){
				System.out.println(Thread.currentThread().getName() + " :Inner.fun2()= " + i);  
	            try {
	            	Thread.sleep(500);
	            } catch (InterruptedException ie) {
	            }
	        }
		}
	}
	
	public void fun1(Inner inner){
		synchronized(inner){
			inner.fun1();
		}
	}
	
	public void fun2(Inner inner){
		synchronized(inner){
			inner.fun2();
		}
	}
}

public class MultiThreading {
	public static void main(String[] args){
//		ThreadOne t1 = new ThreadOne();
//		Thread td1 = new Thread(t1, "A");
//		Thread td2 = new Thread(t1, "B");
//		td1.start();
//		td2.start();
		
//		final ThreadTwo t2 = new ThreadTwo();
////		Thread td1 = new Thread(new Runnable(){public void run(){t2.fun1();}}, "td1" );
////		Thread td2 = new Thread(new Runnable(){public void run(){t2.fun2();}}, "td2" );
////		Thread td2 = new Thread(new Runnable(){public void run(){t2.fun1();}}, "td2" );
//		Thread td1 = new Thread(new Runnable(){public void run(){t2.fun3();}}, "td1" );
//		Thread td2 = new Thread(new Runnable(){public void run(){t2.fun3();}}, "td2" );
//		td1.start();
//		td2.start();
		
		final ThreadThree t3 = new ThreadThree();
		final Inner inner = t3.new Inner();
		Thread td1 = new Thread(new Runnable(){public void run(){t3.fun1(inner);}}, "td1");
		Thread td2 = new Thread(new Runnable(){public void run(){t3.fun2(inner);}}, "td2");
		td1.start();
		td2.start();
	}
}
