//普通的测试
package myTest;

class Resume {
	private String name;
	private String sex;
	private String age;
	public Resume(String name) {
		this.name = name;
	}
	public void SetPersonalInfo(String sex, String age) {
		this.sex = sex;
		this.age = age;
	}
	public void Display() {
		System.out.println(name + ";" + sex + ";" + age);
	}
}

interface myInterface {
	public abstract void test();
}

class myClass implements myInterface {
	@Override
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("good!");
	}
}

public class Test {
	
	public static void main(String[] args) {
		Resume a = new Resume("ruyanfly");
		a.SetPersonalInfo("man", "29");
		Resume b = a;
		Resume c = a;
		a.Display();
		b.Display();
		c.Display();
		a.SetPersonalInfo("man", "27");
		a.Display();
		b.Display();
		c.Display();
		float aFloat = 0.6710339f;
		double aDouble = 0.04150553411984792d;
		double sum = aFloat + aDouble;
		float quotient = (float)(aFloat / aDouble);
		System.out.println("float: " + aFloat);
		System.out.println("double: " + aDouble);
		System.out.println("sum: " + sum);
		System.out.println("quotient: " + quotient);
	}

}
