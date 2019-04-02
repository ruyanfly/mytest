package oldTest;

class Person{
	private int age=10;
	public Person(){
		System.out.println("the person's age is "+age);
	}
	public int GetAge(int age){
		this.age=age;
		return this.age;
	}
}

class Country{
	protected String name="China";
	public Country(){
		System.out.println("the country's name is "+name);
	}
	public void SetName(){
		this.name="America";
	}
	public void GetName(){
		System.out.println("the country's name is "+this.name);
	}
	public void Test(){
		
	}
}

class City extends Country{
	protected String name="ShenZhen";
	public City(){
		System.out.println("the city's name is "+name);
	}
	public City(String name){
		this();//we can use this to use another function, now is use City()
		System.out.println("the city's name is "+name);
	}
	public void SetName(){
		this.name="Beijing";
	}
	public void GetName(){
		System.out.println("the city's name is "+this.name);
		super.GetName();
		super.SetName();//is must be in the same name?
		super.Test();
		System.out.println("the country's name is "+super.name);
	}
}

public class ThisSuper {
	public static void main(String[] ages){
		Person Harry=new Person();
		System.out.println("Harry's age is "+Harry.GetAge(12));
		
		City Shanghai =new City("GuangZhou");
		Shanghai.SetName();
		Shanghai.GetName();
	}
}
