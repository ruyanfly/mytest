package myTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;

public class CloneTest {
	public static void main(String[] args) throws OptionalDataException, ClassNotFoundException, IOException {
//		CloneClass c = new CloneClass();
//		CloneClass cc = (CloneClass) c.clone();
//		if (cc == c)
//			System.out.println("the two object is same!");
//		if (cc.hashCode() == c.hashCode())
//			System.out.println("the two object hashcode is same!");
//		System.out.println("the two object is different!");
		//浅拷贝测试
//		Professor p = new Professor("wang", 50);
//		Student s1 = new Student("zhang", 22, p);
//		Student s2 = (Student) s1.clone();
//		System.out.println("学生s1的姓名：" + s1.name + ",学生s1的年纪：" + s1.age
//				+ ",学生s1教授的姓名：" + s1.p.name + ",学生s1教授的年纪" + s1.p.age);
//		System.out.println("学生s2的姓名：" + s2.name + ",学生s2的年纪：" + s2.age 
//				+ ",学生s2教授的姓名：" + s2.p.name + ",学生s2教授的年纪" + s2.p.age);
//		s2.p.name = "li";
//		s2.p.age = 45;
//		s2.name = "chen";
//		s2.age = 25;
//		System.out.println("学生s1的姓名：" + s1.name + ",学生s1的年纪：" + s1.age
//				+ ",学生s1教授的姓名：" + s1.p.name + ",学生s1教授的年纪" + s1.p.age);
//		System.out.println("学生s2的姓名：" + s2.name + ",学生s2的年纪：" + s2.age 
//				+ ",学生s2教授的姓名：" + s2.p.name + ",学生s2教授的年纪" + s2.p.age);
		//深拷贝
		long T = System.currentTimeMillis();
		professor p = new professor("wang", 50);
		student s1 = new student("zhang", 22, p);
		student s2 = (student) s1.deepClone();
		System.out.println("学生s1的姓名：" + s1.name + ",学生s1的年纪：" + s1.age
				+ ",学生s1教授的姓名：" + s1.p.name + ",学生s1教授的年纪" + s1.p.age);
		System.out.println("学生s2的姓名：" + s2.name + ",学生s2的年纪：" + s2.age 
				+ ",学生s2教授的姓名：" + s2.p.name + ",学生s2教授的年纪" + s2.p.age);
		s2.p.name = "li";
		s2.p.age = 45;
		s2.name = "chen";
		s2.age = 25;
		System.out.println("学生s1的姓名：" + s1.name + ",学生s1的年纪：" + s1.age
				+ ",学生s1教授的姓名：" + s1.p.name + ",学生s1教授的年纪" + s1.p.age);
		System.out.println("学生s2的姓名：" + s2.name + ",学生s2的年纪：" + s2.age 
				+ ",学生s2教授的姓名：" + s2.p.name + ",学生s2教授的年纪" + s2.p.age);
		long t = System.currentTimeMillis();
		System.out.println(t - T);
	}

}
//一种基本的实现深拷贝的方法
class Professor implements Cloneable {
	String name;
	int age;
	Professor(String name, int age) {
		this.name = name;
		this.age = age;
	}
//	//这种方法实现的是浅拷贝，学生的信息拷贝了，变成两个不同的对象，但是指向的教授却是同一个对象
//	public Object clone() throws CloneNotSupportedException {
//		return super.clone();
//	}
	//实现深拷贝，教授类中也实现对该类的拷贝
	public Object clone() {
		Object o = null;
		try {
			o = super.clone();
		}catch(CloneNotSupportedException e) {
			System.out.println(e.toString());
		}
		return o;
	}
}
class Student implements Cloneable {
	String name;
	int age;
	Professor p;
	Student(String name, int age, Professor p) {
		this.name = name;
		this.age = age;
		this.p = p;
	}
	public Object clone() {
		//这里的对象指明Student,现在测试换成Object
		Student s = null;
		try {
			s = (Student)super.clone();
		}catch(CloneNotSupportedException e) {
			System.out.println(e.toString());
		}
		//要实现对类中引用的类的拷贝，这里需要实现对教授类的拷贝，不然没有效果
		s.p = (Professor) p.clone();
		return s;
//		//测试结果显示没有区别，还是结果一样
//		Object o = null;
//		try {
//			o = super.clone();
//		}catch(CloneNotSupportedException e) {
//			System.out.println(e.toString());
//		}
//		return o;
	}
}
//使用窜行化实现深拷贝,也叫做序列化，序列化就是把对象写到流中，然后新建一个对象，通过反序列化把流数据写到对象中
class professor implements Serializable {
	private static final long serialVersionUID = 1L;
	String name;
	int age;
	professor(String name, int age){
		this.name = name;
		this.age = age;
	}
}
class student implements Serializable {
	private static final long serialVersionUID = 2L;//这里似乎并不需要保持一致
	String name;
	int age;
	professor p;
	student(String name, int age, professor p) {
		this.name = name;
		this.age = age;
		this.p = p;
	}
	public Object deepClone() throws IOException, OptionalDataException, ClassNotFoundException {
		// 将对象写到流里
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(this);
		// 从流里读出来
		ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (oi.readObject());		
	}
}

class CloneClass implements Cloneable {
	public int a;
	public Object clone() {
		CloneClass o = null;
		try {
			o = (CloneClass)super.clone();
		}catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}
}