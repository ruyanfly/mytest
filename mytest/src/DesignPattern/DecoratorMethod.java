package DesignPattern;
//装饰器模式
//这是初始接口，用于实现方法
interface DecoratorSourceable{
	public void method();
}
//基础类继承初始接口并实现接口方法
class DecoratorSource implements DecoratorSourceable{
	@Override
	public void method() {
		// TODO Auto-generated method stub
		System.out.println("the original method!");
	}
}
//这个就是装饰类
//装饰类的写法就是创建一个类继承接口，类中申明一个基础类，接口方法中调用基础类方法
class Decorator implements DecoratorSourceable{
	private DecoratorSourceable source;
	public Decorator(DecoratorSourceable source){
		super();//有跟没有这里的结果都是一样的，那这么写的意义何在？
		//这里是调用父类的构造方法
		this.source=source;
	}
	@Override
	public void method() {
		// TODO Auto-generated method stub
		System.out.println("before decorator!");
		source.method();
		System.out.println("after decorator!");
	}
}
//具体实现装饰类的实现类
class DecoratorClass extends Decorator {
	public DecoratorClass(DecoratorSourceable source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
	public void DecoratorMethod() {
		System.out.println("the decorator method!");
	}
	@Override
	public void method() {
		// TODO Auto-generated method stub
		DecoratorMethod();
		super.method();
	}
}
public class DecoratorMethod {
	public static void main(String[] args){
//		DecoratorSourceable source=new DecoratorSource();
//		DecoratorSourceable obj=new Decorator(new DecoratorSource());
		DecoratorSourceable obj=new Decorator(new DecoratorClass(new DecoratorSource()));
		obj.method();
	}
}
