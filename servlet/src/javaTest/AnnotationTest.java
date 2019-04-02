package javaTest;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

//用Java作为包名会提示错误Prohibited package name: java
/**
 * 学习Java注解(Annotation)，注解是JDK1.5.新增特性之一
 * JDK1.5内部提供的三种注解是@SuppressWarnings(":deprecation"), @Deprecated, @Override
 * @author yanru
 */
/**
 * 类名的命名是有讲究的，类名、属性名、变量名一般是名词，或者是形容词+名词，方法一般是动词，或者是动词+名词,
 * 以AnnotationTest作为类名和以TestAnnotation作为类名是有区别的，
 * 前者是注解的测试，符合名词的特征，后者是测试注解，听起来就是一个动作名称，是方法的命名特征
 */

class person implements Comparable<Object> {
//class person implements {
	private String name;
	private String age;
	
	public String getName() {
		return name;
	}
	//这里是ElementType.METHOD
	@MyAnnotation(value =  "ruyanfly", color = "")
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	@MyAnnotation(value =  "28", color = "")
	public void setAge(String age) {
		this.age = age;
	}
	//只有抽象类中才可以有抽象函数
	//有抽象方法的类一定是抽象类
	//抽象类不一定有抽象方法，但是依然不能用关键字new进行实例化操作
	//抽象类不能用final来定义，抽象类必须有子类，而final不能有子类
	//抽象类只是比普通类多了构造方法而已，普通类中的所有结构抽象类都可以定义，
	//包括普通方法、构造方法、属性、常量等内容；而且子类对象也符合对象实例化流程，
	//先调用父类中的无参构造方法，而后再执行子类自己的构造操作
	//abstract void out();
	
	/**
	 * 用来学习Java中Comparable方法还有Comparator比较
	 */
	Integer Age;
	
	public Integer getIntegerAge() {
		return Age;
	}

	public void setIntegerAge(Integer age) {
		this.Age = age;
	}
	
	person(String name, Integer age) {
		super();
		this.name = name;
		this.Age = age;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		System.out.println("this is result : " + result);
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		person  other = (person ) obj;
		if(Age != other.Age)
			return false;
		if(name == null) {
			if(other.name != null)
				return false;
		}else if(!name.equals(other.name)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "person [ age = " + Age + " , name = " + name + " ]";
	}
	
	public person() {
		super();
	}
	
	public person(int age, String name) {
		super();
		this.Age = age;
		this.name = name;
	}
	
	//添加person类的对象时候就依据该方法按照指定属性排序
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if(o instanceof person) {
			//java 中的instanceof 运算符是用来在运行时指出对象是否是特定类的一个实例
			//ps这里的age name因为是Integer 和String类型的，
			//所以都已经重写了compareTo方法，我们就可以直接拿来用
			person p = (person) o;
			//return -this.Age.compareTo(p.Age);
			//return this.name.compareTo(p.name);
			int i = this.Age.compareTo(p.Age);
			if(i == 0) {
				return this.name.compareTo(p.name);////如果age是一样的，我们就来判断名字是不是一样的
			}else {
				return i;
			}
		}
		return 0;//返回的数是0代表两个元素相同，正数说明大于，负数说明小于
	}
}
//这里是ElementType.TYPE 
@MyAnnotation(color = "red", name = "wo shi shui", arrayAtrr= {3, 5, 7})
//不能重复多个@MyAnnotation("change value")
//可以在这里随便重新赋值
//如果一个注解中有一个名称为value的属性，且你只想设置value属性(即其他属性都采用默认值或者你只有一个value属性)，
//那么可以省略掉“value=”部分。

public class AnnotationTest {
	//注解(Annotation)相当于一种标记，在程序中加入注解就等于为程序打上某种标记
	//没有加，则等于没有任何标记，以后，javac编译器、开发工具和其他程序可以通过反射来了解你的类及各种元素上有无何种标记
	//看你的程序有什么标记，就去干相应的事，标记可以加在包、类，属性、方法，方法的参数以及局部变量上
	//注解就相当于一个你的源程序要调用一个类，在源程序中应用某个注解，得事先准备好这个注解类。就像你要调用某个类，得事先开发好这个类
	
	//这里就是注解，称为压缩警告，这是JDK内部自带的一个注解，一个注解就是一个类，
	//在这里使用了这个注解就是创建了SuppressWarnings类的一个实例对象
	@SuppressWarnings("deprecation")
	/**
	 * @Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
	 * @Retention(RetentionPolicy.SOURCE)
	 * public @interface SuppressWarnings {String[] value();}
	 * @param args
	 */
	public static void main(String[] args) {
		System.runFinalizersOnExit(true);
		person p = new person();
		System.out.println("name: "+p.getName()+" age: "+p.getAge());
		if(AnnotationTest.class.isAnnotationPresent(MyAnnotation.class)) {
			/*
			 * MyAnnotation是一个类，这个类的实例对象annotation是通过反射得到的，这个实例对象是如何创建的呢？
			 * 一旦在某个类上使用了@MyAnnotation，那么这个MyAnnotation类的实例对象annotation就会被创建出来了
			 * 假设很多人考驾照，教练在有些学员身上贴一些绿牌子、黄牌子，贴绿牌子的表示送礼送得比较多的，
			 * 贴黄牌子的学员表示送礼送得比较少的，不贴牌子的学员表示没有送过礼的，通过这个牌子就可以标识出不同的学员
			 * 教官在考核时一看，哦，这个学员是有牌子的，是送过礼给他的，优先让有牌子的学员过，此时这个牌子就是一个注解
			 * 一个牌子就是一个注解的实例对象，实实在在存在的牌子就是一个实实在在的注解对象，把牌子拿下来(去掉注解)注解对象就不存在了
			 * */
			MyAnnotation annotation = (MyAnnotation) AnnotationTest.class.getAnnotation(MyAnnotation.class);
			//注解中为虚函数依旧可以打印出来
			System.out.println(annotation+" color : "+annotation.color());//打印MyAnnotation对象，输出结果是@javaTest.MyAnnotation(number=0, value=this is my annotation)
			
			Annotation[] annotations = RepeatAnn.class.getAnnotations();  
            System.out.println(annotations.length); //1  
            Arrays.stream(annotations).forEach(System.out::println);//@com.github.jdk8.ebook.java8_recipes2nd_edition.Chapter2Code$Roles(value=[@com.github.jdk8.ebook.java8_recipes2nd_edition.Chapter2Code$Role(name=doctor), @com.github.jdk8.ebook.java8_recipes2nd_edition.Chapter2Code$Role(name=who)])  
      
            Annotation[] annotations2 = Annotations.class.getAnnotations();  
            System.out.println(annotations2.length);//1  
            Arrays.stream(annotations2).forEach(System.out::println);//@com.github.jdk8.ebook.java8_recipes2nd_edition.Chapter2Code$Roles(value=[@com.github.jdk8.ebook.java8_recipes2nd_edition.Chapter2Code$Role(name=doctor), @com.github.jdk8.ebook.java8_recipes2nd_edition.Chapter2Code$Role(name=who)])  

		}
	}
	@Deprecated
	/**
	 * @Target(ElementType.METHOD)
	 * @Retention(RetentionPolicy.SOURCE)
	 * public @interface Override {}
	 */
	//这也是JDK内部自带的一个注解，意思就是说这个方法已经废弃了，不建议使用了
	public static void sayHello() {
		System.out.println("hi, ruyanfly");
	}
	@Override
	/**
	 * @Target(ElementType.METHOD)
	 * @Retention(RetentionPolicy.SOURCE)
	 * public @interface Override {}
	 */
	//这也是JDK1.5之后内部提供的一个注解，意思就是要重写(覆盖)JDK内部的toString()方法
	public String toString() {
		return "ruyanfly";
	}
	
	/**
	 * The same annotation can be applied to a declaration or type more than
	 * once, given that each annotation is marked as @Repeatable. In the
	 * following code, the @Repeatable annotation is used to develop an
	 * annotation that can be repeated, rather than grouped together as in
	 * previous releases of Java. In this situation, an annotation named Role is
	 * being created, and it will be used to signify a role for an annotated
	 * class or method.
	 * @author yanru
	 *
	 */
	@Repeatable(value = Roles.class)
	public static @interface Role {
		String name() default "doctor";
	}
	
	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	public static @interface Roles {
		Role[] value();
	}
	
	@Role(name = "doctor")
	@Role(name = "who")
	public static class RepeatAnn {
		
	}
	
	@Roles({@Role(name="doctor"),  @Role(name="who")})
	public static class Annotations {
		
	}
}
