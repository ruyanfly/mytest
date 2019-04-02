package myTest;

//多线程的测试
class ThreadOne extends Thread {
	private String name;
	public ThreadOne(String name) {
		this.name = name;
	}
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(name + " 运行: " + i);
			try {
				sleep((int) Math.random() * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class MultiThread {

	public static void main(String[] args) {
		ThreadOne mtOne = new ThreadOne("A");
		ThreadOne mtTwo = new ThreadOne("B");
		mtOne.start();
		mtTwo.start();
	}
}
