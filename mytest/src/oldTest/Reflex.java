package oldTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class myperson{
	private String name;
	private int age;
	
	public myperson(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	public myperson(){
		
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getAge(){
		return age;
	}
	
	public void setAge(int age){
		this.age = age;
	}
}

public class Reflex {
    /** 
     * Java的反射机制是指：反射就是把Java类中的各种成分映射成相应相应的Java类， 然后就可以获取这个类的所有信息  
     * @throws Exception 
     */
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public static void createObj1() throws Exception {
		/**
		 * Class.forName是返回一个类，或者要求JVM查找并加载指定的类，也就是说其实这里已经新建一个person类
		 */
		Class classType = Class.forName("Reflex.person");
		Object obj = classType.newInstance();
		/**
		 * 以上量两句等效于 person obj = new person();
		 * Class下的newInstance()的使用有局限，因为它生成对象只能调用无参的构造函数，
		 * 而使用 new关键字生成对象没有这个限制。
		 */
		System.out.println("使用反射机制创建出来的对象是否是person类的对象"+(obj instanceof myperson));
		/**  
		* instanceof运算符用法  
		* 运算符是双目运算符,左面的操作元是一个对象,右面是一个类.当  
		* 左面的对象是右面的类创建的对象时,该运算符运算的结果是true,否则是false  
		*   
		* 说明:(1)一个类的实例包括本身的实例,以及所有直接或间接子类的实例  
		* (2)instanceof左边操作元显式声明的类型与右边操作元必须是同种类或右边是左边父类的继承关系,  
		* (3)不同的继承关系下,编译出错  
		*/
	}
    /** 
     * 创建带有构造参数的对象的时候我们需要使用另外一种方式即： 1.先获取操作类的Class对象即字节码文件 
     * 2.使用Class对象的getConstructor 
     * (parameterTypes)方法获取该对象的构造方法的对象，注意括号中可以带不等的可变参数, 
     * 3.使用构造方法对象的newInstance(initargs)方法就可以实例化一个对象 4.注意，使用这些方法都不可以访问被 
     * private修饰的方法，需要设置某些访问权限setAccessable()方法 
     * @throws Exception 
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void createObj2() throws Exception {
		Class classType = myperson.class;
		Constructor<myperson> con = classType.getConstructor(String.class, int.class);
		Object obj = con.newInstance("ruyanfly", 28);
		System.out.println("使用constructor对象的newInstance方法创建出来的对象"
				+ "是否是person类的对象"+((myperson)obj).getName());
	}
    /** 
     * 操作方法包括（private方法） 步骤： 1.首先获取要操作类的Class对象 
     * 2.然后通过Class对象的getMethod方法获取要操作的方法的Method对象（两个参数，第一个参数是方法名，第二个参数是参数类型） 
     * 3.调用Method的方法的invoke方法（两个参数，第一个参数是该方法属于的类对象，具体参数） 
     * 4.当方法被private修饰的时候，首先需要调用getDeclaredMethod()方法获取要操作的被private修饰的类。 
     * 在这里要注意这个getDeclaredMethod方法，它既可以用作获取普通方法的对象也可以用来操作private修饰的方法， 
     * 但是操作private修饰的方法的时候，必须使用这个方法，其它方法不可以。普通方法还可以使用getMethod方法， 
     * 且属性操作也是如此。另外，还需要设置访问权限setAccessable(true)才可以 
     * @throws Exception 
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void methodDo() throws Exception {
		myperson p = new myperson();
		Class classType = myperson.class;
		Method method = classType.getMethod("setName", String.class);
		method.invoke(p, "ruyanfly-again");
		System.out.println("使用反射操作SetName方法后，Person对象的name值是：" + p.getName());
		Method method2 = classType.getDeclaredMethod("test");
		/**
		 * AccessibleObject是Method,Field,Constructor的父类，override属性默认为false,可调用setAccessible方法改变，
		 * 如果设置为true,则表示可以忽略访问权限的限制，直接调用。
		 */
		method2.setAccessible(true);
		method2.invoke(p);
	}
	
	@SuppressWarnings("rawtypes")
	public static void FieldDo() throws Exception{
		myperson p = new myperson();
		Class classType = myperson.class;
		Field field1 = classType.getDeclaredField("name");
		Field field2 = classType.getDeclaredField("age");
		field1.setAccessible(true);
		field2.setAccessible(true);
		field1.set(p, "ruyanfly-two");
		field2.set(p, "27");
		System.out.println("使用反射机制操作被private修饰的对象字段后得到的属性值是：" + p.getName());
		System.out.println("使用反射机制操作被private修饰的对象字段后得到的属性值是：" + p.getAge());
	}
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception{
		new Reflex().FieldDo();
	}
}
