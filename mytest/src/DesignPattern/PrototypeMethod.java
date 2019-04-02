package DesignPattern;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Prototype implements Cloneable,Serializable{
	private static final long serialVersionUID=1L;
	private String string;
	private SerializableObject obj;
	public Object clone() throws CloneNotSupportedException{
		Prototype proto=(Prototype)super.clone();
		return proto;
	}
	public Object deepClone() throws IOException,ClassNotFoundException{
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		ObjectOutputStream oos=new ObjectOutputStream(bos);
		oos.writeObject(this);
		
		ByteArrayInputStream bis=new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois=new ObjectInputStream(bis);
		return ois.readObject();
	}
	public String getString(){
		return string;
	}
	public void setString(String string){
		this.string=string;
	}
	public SerializableObject getObj(){
		return obj;
	}
	public void setObj(SerializableObject obj){
		this.obj=obj;
	}
	class SerializableObject implements Serializable{
		private static final long serialVersionUID=1L;
	}
}

class Sheep implements Cloneable {
	private int age;
	private String sex;
	private Admin admin;
	public Sheep(int age, String sex, Admin admin) {
		super();
		this.age = age;
		this.sex = sex;
		this.admin = admin;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public String toString() {
		return "Sheep [age="+age+", sex="+sex+", admin="+admin+"]";
	}
	//调用Object的Clone方法
	//这是浅拷贝
	public Sheep shallowCopy() {
		Sheep sheep = null;
		try {
			sheep = (Sheep)super.clone();
		}catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return sheep;
	}
	//深拷贝
	public Sheep deepCopy() {
		Sheep sheep = null;
		try {
			sheep = (Sheep)super.clone();
			sheep.admin = this.admin.startClone();
		}catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return sheep;
	}
}

class Admin {
	private int age;
	private String sex;
	private Child child;
	public Admin(int age, String sex, Child child) {
		super();
		this.age = age;
		this.sex = sex;
		this.child = child;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setChild(Child child) {
		this.child = child;
	}
	public String toString() {
		return "Admin [age="+age+", sex="+sex+", child="+child+"]";
	}
	//深拷贝增加对Admin的拷贝
	public Admin startClone() {
		Admin admin = null;
		try {
			admin = (Admin)super.clone();
		}catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return admin;
	}
}

class Child {
	//深拷贝增加对Child的拷贝
	public Child startClone() {
		Child child = null;
		try {
			child = (Child)super.clone();
		}catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return child;
	}
}

public class PrototypeMethod {
	public static void main(String[] args) {
		
		//浅拷贝测试
		Sheep old = new Sheep(2, "雄性", new Admin(25, "女", new Child()));
		System.out.println(old.toString());
		Sheep current = old.deepCopy();
		System.out.println(current.toString());
		current.setAge(1);
		current.setSex("雌性");
		current.getAdmin().setAge(34);
		current.getAdmin().setSex("男");
		System.out.println(old.toString());
		System.out.println(current.toString());
	}
}
