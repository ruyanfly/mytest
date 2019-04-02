package oldTest;

abstract class myAbstract {
	String name;
	int age;
	myAbstract() {
		
	}
	@SuppressWarnings("unused")
	private void getDescribe() {
		
	}
	static protected void getVersion() {
		
	}
}

abstract class A {
	@SuppressWarnings("unused")
	private String a;
	protected String b;
	public String c;
	static String d;
	final String e = "good!";
	A(){
		System.out.println("this is the A!");
	}
	@SuppressWarnings("unused")
	private void getSecret() {
		System.out.println("this is the secret!");
	}
	protected void getVersion() {
		System.out.println("this is the version!");
	}
	public void getName() {
		System.out.println("this is the name!");
	}
	//public abstract void just();
	//abstract修饰只能是public跟protected,C中也有abstract just函数所以同时继承函数只需要一个
	//如果不说明是public或者是protected,那么继承的函数自动添加会提示标明public还是protected
}
//final类无法被继承
final class B {
	
}

interface myInterface {
	static String name = "myTest";
	int age = 10;
	//only public & abstract are permitted
	public void test();
}

interface C {
	// only public, static & final are permitted
	//这里可以看出接口中的变量默认是final类型
	public String a = "good!";
	static String b = "very good!";
	//final,static可以共同修饰
	final static String c = "so good!";
	static final String d = "excite!";
	void A();
	abstract void just();//A中也有这个函数,只需要继承一个
}
//接口与接口之间只有继承关系,继承后,其他类继承该接口就会同时继承C
interface D extends C {
	//只能够用pbulic,abstract修饰
	//static方法不能被重写
	abstract void B();
}

class N extends A implements C, D {
	@Override
	public void A() {
		// TODO Auto-generated method stub
		//B();
		System.out.println(C.b);
	}
	@Override
	public void B() {
		// TODO Auto-generated method stub
		//A();
		System.out.println(C.a);
	}
	N(){
		System.out.println("this is the N!");
	}
	private void getSecret() {
		System.out.println("this is the secret!");
	}
	protected void getVersion() {
		System.out.println("this is the version!");
	}
	public void getName() {
		getSecret();
		getVersion();
		System.out.println("this is the name!");
		System.out.println(A.d);
	}
	@Override
	public void just() {
		// TODO Auto-generated method stub
		
	}
	//继承顺序优先是从虚类,然后是接口类,经过测试如果虚类中不标明是public还是protected,
	//即使接口中标明还是提示需要手动添加,如果虚类中没有从接口中继承,可以无视接口类中是否有public或者是protected
	//自动补齐public
}

//class M {
//	public void A() {
//		B();
//	}
//	public void B() {
//		A();
//	}
//}

public class AbstractInterface {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException {
		N n = new N();
		n.getName();
		//java.lang.StackOverflowError
//		M m = new M();
//		m.A();
		//就算是反射也只能获取public类型
		//System.out.println(Class.forName("MyTest.A").getMethod("getName", null));
		System.out.println(Class.forName("MyTest.C").getFields());
	}
}
