package oldTest;

public class ChildClass extends FatherClass {

	static {
		System.out.println("子类的静态代码块");
	}
	{
		System.out.println("执行子类的构造代码块");
	}
	public ChildClass() {
		System.out.println("执行子类的构造方法");
	}
	public void method() {
		System.out.println("执行子类继承的方法");
	}
	public void childMethod() {
		System.out.println("执行子类的方法"); 
	}
	public static void main(String[] args) {
		ChildClass cc = new ChildClass();
		cc.childMethod();
	}
}
