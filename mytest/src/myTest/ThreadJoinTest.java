package myTest;

class JoinTest extends Thread {
	
	public JoinTest(String name) {
		super(name);
	}
	public void run() {
		for (int i=0; i<1000; i++) {
			System.out.println(this.getName() + ":" + i);
		}
	}
}

public class ThreadJoinTest {
	
	public static void main(String[] args) throws InterruptedException {
		JoinTest t1 = new JoinTest("小明");
		JoinTest t2 = new JoinTest("小东");
		t1.start();
		//join方法必须在线程start方法调用之后调用才有意义
		/**
		 * join的意思是使得放弃当前线程的执行，并返回对应的线程，例如下面代码的意思就是：
		 * 程序在main线程中调用t1线程的join方法，则main线程放弃cpu控制权，
		 * 并返回t1线程继续执行直到线程t1执行完毕所以结果是t1线程执行完后，
		 * 才到主线程执行，相当于在main线程中同步t1线程，t1执行完了，main线程才有执行的机会
		 */
		//t1.join();
		/**
		 * join方法可以传递参数，join(10)表示main线程会等待t1线程10毫秒，10毫秒过去后，
		 * main线程和t1线程之间执行顺序由串行执行变为普通的并行执行,join(0)等价于join()
		 */
		t1.join(10);
		t2.start();
	}

}
