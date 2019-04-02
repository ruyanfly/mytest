package oldTest;

public class ThreadLocalTest {
	
//	static class ResourceClass {
//		public final static ThreadLocal<String> RESOURCE_1 = new ThreadLocal<String>();
//		public final static ThreadLocal<String> RESOURCE_2 = new ThreadLocal<String>();		
//	}
//	static class A {
//		public void setOne(String value) {
//			ResourceClass.RESOURCE_1.set(value);
//		}
//		public void setTwo(String value) {
//			ResourceClass.RESOURCE_2.set(value);
//		}
//	}
//	static class B {
//		public void display() {
//			System.out.println(ResourceClass.RESOURCE_1.get()+" : "+ResourceClass.RESOURCE_2.get());
//		}
//	}
//	public static void main(String[] args) {
//		final A a = new A();
//		final B b = new B();
//		for(int i = 0; i < 15; i++) {
//			final String resouce1 = "线程" + i;
//			final String resouce2 = "value = (" + i + ")";
//			new Thread() {
//				public void run() {
//					try {
//						a.setOne(resouce1);
//						a.setTwo(resouce2);
//						b.display();
//					}finally {
//						ResourceClass.RESOURCE_1.remove();
//						ResourceClass.RESOURCE_2.remove();
//					}
//				}
//			}.start();
//		}
//	}
	
	ThreadLocal<Long> longLocal = new ThreadLocal<Long>() {
		//如果没有这段初始化代码执行会提示找不到对象空指针
//		protected Long initialValue() {
//			return Thread.currentThread().getId();
//		}
	};
	ThreadLocal<String> stringLocal = new ThreadLocal<String>() {
		//如果没有这段初始化代码执行会提示找不到对象空指针
//		protected String initialValue() {
//			return Thread.currentThread().getName();
//		}
	};
	public void set() {
		longLocal.set(Thread.currentThread().getId());
		stringLocal.set(Thread.currentThread().getName());
	}
	public long getLong() {
		return longLocal.get();
	}
	public String getString() {
		return stringLocal.get();
	}
	public static void main(String[] args) throws InterruptedException {
		final ThreadLocalTest test = new ThreadLocalTest();
		test.set();
		System.out.println(test.getLong());
		System.out.println(test.getString());
		Thread thread1 = new Thread(){
			public void run() {
				test.set();
				System.out.println(test.getLong());
				System.out.println(test.getString());
			};
		};
		thread1.start();
		thread1.join();
		System.out.println(test.getLong());
		System.out.println(test.getString());
	}
	
}
