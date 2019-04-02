//针对volatile关键字的测试
package myTest;

public class VolatileTest {
	//测试一
//	int a = 1;
//	int b = 2;
//	public void change() {
//		a = 3;
//		b = a;
//	}
//	public void print() {
//		System.out.println("b="+b+" ;a="+a);
//	}
//	public static void main(String[] args) {
//		while(true) {
//			final VolatileTest test = new VolatileTest();
//			new Thread(new Runnable() {
//				public void run() {
//					try {
//						Thread.sleep(10);
//					}catch(InterruptedException e) {
//						e.printStackTrace();
//					}
//					test.change();
//				}
//			}).start();
//			new Thread(new Runnable() {
//				public void run() {
//					try {
//						Thread.sleep(10);
//					}catch(InterruptedException e) {
//						e.printStackTrace();
//					}
//					test.print();
//				}
//			}).start();
//		}
//	}
	//测试二
//	volatile int i;
//	public static int n=0;
//	public synchronized void add() {
//		n++;
//	}
//	public void add() {
//		n++;
//	}
//	public static void main(String[] args) {
//		final VolatileTest test = new VolatileTest();
//		for(int i=0; i<10; i++) {
//			new Thread(new Runnable() {
//				public void run() {					
//					try {
//						Thread.sleep(10);
//					}catch(InterruptedException e) {
//						e.printStackTrace();
//					}
//					for(int j=0; j<100; j++) {
//						test.add();
//					}
//					System.out.println(test.n);
//				}
//			}).start();
//		}
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(test.n);
//	}
	//测试三
	public void sleepMethod() {
		//此处如果没有synchronized修饰sleep方法进入也不是顺序而是跟wait一样其他程序都可以进入
		 System.out.println("Sleep start-----");
		 try {
			 Thread.sleep(1000);
		 }catch(InterruptedException e) {
			 e.printStackTrace();
		 }
		 System.out.println("Sleep end-----");
	}
	public synchronized void waitMethod() {
		 System.out.println("Wait start-----");
		 try {
			 wait(1000);
		 }catch(InterruptedException e) {
			 e.printStackTrace();
		 }
		 System.out.println("Wait end-----");
	}
	public static void main(String[] args) {
		final VolatileTest testSleep = new VolatileTest();
		for(int i = 0;i<3; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					testSleep.sleepMethod();
				}
			}).start();
		}
		
		try {
			Thread.sleep(10000);
		}catch(InterruptedException e) {
			 e.printStackTrace();
		}
		System.out.println("-----分割线-----");
		
		final VolatileTest testWait= new VolatileTest();
		for(int i = 0;i<3;i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					testWait.waitMethod();
				}
			}).start();
		}
	}
}
