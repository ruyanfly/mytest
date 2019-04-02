package oldTest;

class Father {
	public Son son;
	public Father(Son son) {
		this.son = son;
	}
	public Father() {
		
	}
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public void fun1() {
		//使用反射调用子类方法
		try {
			//Class.forName必须指明不能泛指
			Class c = Class.forName("oldTest.Son");
			Son son = (Son)c.newInstance();
			son.fun4();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void fun2() {
		//把子类传递到父类的有参构造函数中，然后调用
		System.out.println("父类方法");
		son.fun4();
	}
	public void fun3() {
		Son.fun5();
	}
}

class Son extends Father {
	public void fun4() {
		System.out.println("子类方法");
	}
	public static void fun5() {
		System.out.println("子类静态方法");
	}
}
//接口之间只能是继承
interface fatherFace extends AFace{
	//only public, static & final are permitted修饰变量
	public int i = 0;
	static int j = 1;
	final int k = 2;
	//only public, private, abstract, default, static and strictfp are permitted
	//修饰方法
	@SuppressWarnings("unused")
	private void fun6() {
		System.out.println("接口私有方法");
	}
	public void fun7();
	public static void fun8() {
		System.out.println("接口公开静态方法");
	}
	//private会提示需要unused
	@SuppressWarnings("unused")
	private static void fun9() {
		System.out.println("接口私有静态方法");
	}
	static void fun10() {
		System.out.println("接口静态方法");
	}
	abstract void fun11();
	default void fun12() {
		System.out.println("接口default方法");
	}
}
class fatherClass {
	@SuppressWarnings("unused")
	private void fun13() {
		System.out.println("父类私有方法");
	}
	public void fun14() {
		System.out.println("父类公共方法");
	}
	protected void fun15() {
		System.out.println("父类保护方法");
	}
	public final void fun16() {
		System.out.println("父类公共final方法");
	}
}

interface AFace {
	default void fun17() {
		System.out.println("A的default方法");
	}
	public void fun18();
	public static void fun19() {
		System.out.println("A的static方法");
	}
}

class sonClass extends fatherClass implements fatherFace {
	//子类继承父类需要重写abstract修饰的方法
	@Override
	public void fun7() {
		// TODO Auto-generated method stub
		System.out.println("子类方法7");
	}
	@Override
	public void fun11() {
		// TODO Auto-generated method stub
		System.out.println("子类方法11");
	}
	@Override
	public void fun18() {
		// TODO Auto-generated method stub
		
	}
}

public class FatherUseSonFunction {
	//void在static后面
	public static void main(String[] args) {
//		Son son = new Son();
//		Father father = new Father(son);
//		father.fun1();
//		father.fun2();
//		father.fun3();
		sonClass son = new sonClass();
		son.fun11();
		son.fun14();
		son.fun15();
		son.fun16();
		son.fun7();
		fatherFace.fun10();
		fatherFace.fun8();
	}
}
