package oldTest;

public class FatherClass {
	
	static {
		System.out.println("父类的静态代码块");
	}
	{
		System.out.println("执行父类的构造代码块");
	}
	public FatherClass() {
		System.out.println("执行父类的构造方法");
	}
	public FatherClass(String name) {
		System.out.println("执行父类的构造方法:名称");
	}
	public void method() {
		System.out.println("执行父类的方法"); 
	}
	
}
