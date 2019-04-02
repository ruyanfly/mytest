package oldTest;

public class FinalTest {
	
	FinalTest(){
		
	}
	
	final int i = 0;
	
	public static String getHello() {
		return "hello";
	}

	@SuppressWarnings({ "unused", "static-access", "deprecation" })
	public static void main(String[] args)  {
		//final修饰String问题
		//Java中的变量和基本类型的值存放于栈内存，而new出来的对象本身存放于堆内存，指向对象的引用还是存放在栈内存。
		//对于直接赋值的字符串常量（如String s=“Hello World”；中的Hello World）也是存放在栈内存中.
		//hello2存放在常量池中，新建一个String对象a是在堆中，Value指向hello2这个值
		String a = "hello2";
		final String b = "hello";
		//常量池中再保存一个hello值，新建一个String对象value指向这个hello
		String d = "hello";
		//String + 的实现原理是先申明一个stringbuffer或者stringbuilder
		//变量，然后通过append函数增加字段在用toString转化成string字符
		//b在栈中，这里是引用，由于b是final变量，即常量，所以此处b+2等同于 hello + 2所以就等于 c=hello2，与a一样
		String c = b + 2;
		//这里的e是hello2的一个拷贝对象，所以与a不同，因为用的是先申明一个stringbuilder变量,e指向的是new stringbuilder
		//而stringbuilder里的char数组指向常量池中的hell02，所以不一样
		String e = d + 2;
		final String f = getHello();
		//因为这里的b虽然是常量，但是在编译期是不能获得值的，只有在运行的时候才会调用函数，初始化赋值，
		//所以这时的String c = b+2是运行期间计算出来的，所以不一样
        String g = f + 2;
        //h很明显是一样的
        String h = "hello2";
        String j = new String("hello2");
        //通过intern函数把hello2复制到常量池中，如果常量池已存在该字符串，所以直接返回该字符串的引用
        String k = j.intern();
        //b = b + 2;//使用final修饰后不能修改b的值
        //l说明与是否用final修饰无关
		String l = getHello() + 2;
		//这个例子中位置不同结果不同
		//String n = "hello2";
		String m = new String("hello") + new String("2");
		//因为此时数据池中已经有该对象，所以intern是直接把堆引用给m
		m.intern();
		//intern的实现是首先查找栈中的字符串池是否有变量，没有就先在池中创建变量，然后再赋引用，所以此时m的值是栈中
		//字符串池的引用，这个时候新建n时字符串池中已经有数据所以直接引用，所以相等
		String n = "hello2";
		System.out.println((a == c));
		System.out.println((a == e));
		System.out.println((a == g));
		System.out.println((a == h));
		System.out.println((a == j));
		System.out.println((a == k));
		System.out.println((a == l));
		//当字符串从goodidea变成hello2后可知n,m值位置如何变都不一样
		System.out.println((a == m));
		
		final MyClass m1 = new MyClass();
		System.out.println(++m1.a);
		
		final MyClass m2 = new MyClass();
        System.out.println(m1.i);
        System.out.println(m1.j);
        System.out.println(m2.i);
        System.out.println(m2.j);
        
        final int i = 0;
        FinalTest ft = new FinalTest();
        ft.test(i);
        System.out.println(i);
        
        String t = "hello";
        ft.testString(t);
        System.out.println(t);
        
        boolean bb = false;
        Boolean boo = new Boolean(false);
        Boolean[] arr = new Boolean[]{false};
        
        getMiddleOne(bb, boo, arr);
        
        System.out.println(bb);
        System.out.println(boo.toString());
        System.out.println(arr[0]);
        
        ft.first();
	}
	
	public void first() {
		int i = 5;
		Value v = new Value();
		v.i = 25;
		second(v, i);
		System.out.println(v.i);
	}
	
	public void second(Value v, int i) {
		i = 0;
		v.i = 20;
		Value val = new Value();
		v = val;
		System.out.println(v.i);
		System.out.println(i);
	}
	
	@SuppressWarnings("deprecation")
	private static void getMiddleOne(boolean b, Boolean boo, Boolean[] arr){
		b = true;//形参，不会改变原有值
		boo = new Boolean(true);//引用变量的直接操作相当于值传递，不会改变原来的引用变量
		arr[0] = true;//引用变量的属性的操作，会改变原有引用的属性，相当于传址调用  
    } 
	
	void testString(String s) {
		s = "test";
		System.out.println(s);
	}
	
	public void test(int i) {
		i++;
		System.out.println(i);
	}
	
	public void test() {
		System.out.println(i);
	}

}

class MyClass {
	public int a = 0;
	public final double i = Math.random();
    public static double j = Math.random();
}

class Value {
	public int i = 15;
}