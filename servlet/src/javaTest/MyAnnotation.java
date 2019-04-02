package javaTest;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

enum EumTrafficLamp {
	RED, YELLOW, GREEN
}

/**
 * 这是一个自定义的注解类，在定义注解类是使用了另一个注解类Retention
 * 在注解类上使用另一个注解类，那么被使用的注解类就被称为元注解
 * @author yanru
 *
 */
//元注解:@interface上面按需要注解上一些东西,包括@Retention, @Target, @Document, @Inherited四种
//RetentionPolicy.SOURCE, // 注解仅存在于源码中, 在class字节码文件中不包含
//RetentionPolicy.CLASS, //默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得
//RetentionPolicy.RUNTIME, //注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Documented
@Inherited
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PACKAGE, ElementType.TYPE })
//这里会显示注解的添加地方，如果不对会提示The annotation @MyAnnotation is disallowed for this location
//type: 接口, 类, 枚举, 注解;
//field: 字段, 枚举的常量;
//method: 方法;
//parameter: 方法参数;
//constructor: 构造函数;
//local_variable: 局部变量;
//annotation_type: 注解;
//package: 包;
@Retention(RetentionPolicy.RUNTIME)
//Retention注解决定MyAnnotation注解的生命周期
//被MyAnnotation注解类标识的类被类加载器加载到内存中后MyAnnotation注解就不存在了
public @interface MyAnnotation {
	//这里的函数限制为：only public & abstract are permitted；
	public String value() default "this is my annotation";
	public int number() default 0;
	//abstract函数依旧可以打印出来
	//所以，抽象类是为了把相同的但不确定的东西的提取出来，为了以后的重用。
	//定义成抽象类的目的，就是为了在子类中实现抽象类。
	//就算是抽象变量也可以重新赋值
	abstract String name() default "ruyanfly";
	//定义基本属性
	String color();//导致在使用注解的时候要写@MyAnnotation(color = "")
	int[] arrayAtrr() default {1,2,4};
	//添加一个枚举类型的属性，并指定枚举属性的缺省值，缺省值只能从枚举类EumTrafficLamp中定义的枚举对象中取出任意一个作为缺省值
	EumTrafficLamp lamp() default EumTrafficLamp.RED;
	//为注解添加一个注解类型的属性,并指定注解属性的缺省值
}
/**
 * 当在Java源程序上加了一个注解，这个Java源程序要由javac去编译，javac把java源文件编译成.class文件，
 * 在编译成class时可能会把Java源程序上的一些注解给去掉，java编译器(javac)在处理java源程序时，
 * 可能会认为这个注解没有用了，于是就把这个注解去掉了，那么此时在编译好的class中就找不到注解了， 
 * 这是编译器编译java源程序时对注解进行处理的第一种可能情况，假设java编译器在把java源程序编译成class时，
 * 没有把java源程序中的注解去掉，那么此时在编译好的class中就可以找到注解，当程序使用编译好的class文件时，
 * 需要用类加载器把class文件加载到内存中，class文件中的东西不是字节码，
 * class文件里面的东西由类加载器加载到内存中去，类加载器在加载class文件时，会对class文件里面的东西进行处理，
 * 如安全检查，处理完以后得到的最终在内存中的二进制的东西才是字节码，
 * 类加载器在把class文件加载到内存中时也有转换，转换时是否把class文件中的注解保留下来，这也有说法，
 * 所以说一个注解的生命周期有三个阶段：java源文件是一个阶段，class文件是一个阶段，
 * 内存中的字节码是一个阶段,javac把java源文件编译成.class文件时，有可能去掉里面的注解，
 * 类加载器把.class文件加载到内存时也有可能去掉里面的注解，
 * 因此在自定义注解时就可以使用Retention注解指明自定义注解的生命周期，
 * 自定义注解的生命周期是在RetentionPolicy.SOURCE阶段(java源文件阶段)，
 * 还是在RetentionPolicy.CLASS阶段(class文件阶段)，或者是在RetentionPolicy.RUNTIME阶段
 * (内存中的字节码运行时阶段)，根据JDK提供的API可以知道默认是在RetentionPolicy.CLASS阶段 (JDK的API写到：
 * the retention policy defaults to RetentionPolicy.CLASS.)
 * */
//其实从代码的写法上来看，注解更像是一种特殊的接口，注解的属性定义方式就和接口中定义方法的方式一样，
//而应用了注解的类可以认为是实现了这个特殊的接口
