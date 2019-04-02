package oldTest;

public class ClassTest {
	
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	private String str;
	public final int myage = 29;
	public void outerDisplay() {
		System.out.println("outerClass...");
	}
	class InnerClass{
		static final int maxAge = 100;//不能有static但是可以有static  final
		final String name = "god";
		public void innerDisplay() {
			//maxAge = 120;//无法对maxAge进行修改
			str = "this is my innerClass...";
			System.out.println(str);
			outerDisplay();
		}
		public InnerClass() {
			//name = "ruyanfly";
			age = 28;
			System.out.println("name: "+name+" age: "+age);
			System.out.println("people is max age is: "+maxAge);
		}
		class InnerInnerClass{
			public void output() {
				System.out.println(str);
			}
		}
	}
	interface innerInterface {
		
	}
	public InnerClass getInnerClass() {
		@SuppressWarnings("unused")
		class SecondInnerClass implements innerInterface{
			
		}
		return new InnerClass();
	}
	//静态内部类
	@SuppressWarnings("unused")
	private String sex;
	public static String theName = "chenssy";
	static class StaticInnerClass {
		/* 在静态内部类中可以存在静态成员 */
		public static String _name1 = "chenssy_static";
		public void display() {
			/* 
			 * 静态内部类只能访问外围类的静态成员变量和方法
             * 不能访问外围类的非静态成员变量和方法
             */
			System.out.println("OutClass name :" + theName);
		}
		/**
	     * 非静态内部类
	     */
		class InnerClass2 {
			/*非静态内部类中不能存在静态成员 */
			public String _name2 = "chenssy_inner";
			/*非静态内部类中可以调用外围类的任何成员,不管是静态的还是非静态的 */
			public void display() {
				System.out.println("OuterClass name：" + theName);
			}
		}
		/**
		 * 外围方法
		 */
		public void staticDisplay() {
			/*外围类访问静态内部类：内部类*/
			System.out.println(StaticInnerClass._name1);
			/*静态内部类 可以直接创建实例不需要依赖于外围类*/
			new StaticInnerClass().display();
			/*非静态内部的创建需要依赖于外围类 */
			StaticInnerClass.InnerClass2 inner2 = new StaticInnerClass().new InnerClass2();
			inner2.display();
		}
	}
	public static void main(String[] arg) {
//		ClassTest ct = new ClassTest();
//		ClassTest.InnerClass inner = ct.getInnerClass();
//		ClassTest.InnerClass.InnerInnerClass ii = inner.new InnerInnerClass();
//		inner.innerDisplay();
//		ii.output();
		
//		BirdTest bt = new BirdTest();
//		bt.test(new Bird() {
//			public final int length = 1000;//可以是final但不能是static
//			public int fly() {
//				return 10000;
//			}
//			public String getName() {
//				return "大雁";
//			}
//		});
		
//		MapImpl mi = new MapImpl();
//		MapImpl.ImplEntry il = mi.new ImplEntry();
//		mi.clear();
//		System.out.println("number is : "+il.getKey());
		
		//静态内部类
		StaticInnerClass sic = new StaticInnerClass();
		sic.display();
	}
}

abstract class Bird {
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public abstract int fly();
}
class BirdTest {
	public void test(Bird bird) {
		System.out.println(bird.getName() + "能够飞 " + bird.fly() + "米");
	}
}
interface Map {
	interface Entry {
		int getKey();
	}
	void clear();
}
class MapImpl implements Map {
	class ImplEntry implements Map.Entry {
		public int getKey() {
			return 0;
		}
	}
	public void clear() {
		System.out.println("this is a clear function!");
	}
}
