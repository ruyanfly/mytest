package oldTest;

//Java这里只能有Class区域,不能存在变量或者函数,任何修饰符修饰的变量或函数都不行
//final类不能被继承
final class finalClass {
	public int i = 10;
	public static int j = 11;
	public final int k = 12;
}

class hasFinal {
	public final void finalFunction() {
		System.out.println("outside final function!");
	}
	public void testFunction() {
		System.out.println("outside test function!");
	}
}

class testFunction extends hasFinal {
	//final方法不能被重写
//	public void finalFunction() {
//		System.out.println("outside final function!");
//	}
	//public void testFunction()这么写会提示有问题，不知道是不是重写都不用加void
	public testFunction() {
		System.out.println("outside test function!");
	}
}

//这里不能有用static修饰的类
//static class staticClass {
//	
//}

public final class StaticTest {
	
	static {
		System.out.println("innner static block!");
	}
	
	int i = 10;
	
	//static方法中的引用同类中的变量必须是static类型
	//static方法就是没有this的方法,在static方法内部中不能调用非静态方法,反过来可以
	//而且可以在没有创建任何对象的前提下,通过类本身直接调用static方法
	static void staticFunction() {
		int i = 10;
		System.out.println("inner static function! : " + i);
	}
	
	public void function() {
		System.out.println("inner function! : " + this.i);
	}
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		finalClass f = new finalClass();
		f.i = 11;
		f.j = 12;
		//f.k = 13;//final变量不能被修改重新赋值
		System.out.println(f.i + "-" + f.j);
	}

}
