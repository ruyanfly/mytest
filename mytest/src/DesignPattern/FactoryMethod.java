package DesignPattern;

interface Sender{
	public void Send();
}

class MailSender implements Sender{
	@Override
	public void Send() {
		// TODO Auto-generated method stub
		System.out.println("this is mailsender!");
	}
}

class SmsSender implements Sender{
	@Override
	public void Send() {
		// TODO Auto-generated method stub
		System.out.println("this is sms sender!");
	}
}

class SendFactory{
	//1.简单工厂(静态工厂),通过区分输入值判断用户调用的具体工厂
	public Sender produce(String type){
		if("mail".equals(type)){
			return new MailSender();
		}else if("sms".equals(type)){
			return new SmsSender();
		}else{
			System.out.println("please input the right type");
			return null;
		}
	}
	//2.多工厂方法，创建具体工厂的实现函数，用户通过调用具体函数实现工厂
	Sender produceMail(){
		return new MailSender();
	}
	Sender produceSms(){
		return new SmsSender();
	}
	//3.多方法静态工厂
	static Sender produceStaticMail(){
		return new MailSender();
	}
	static Sender produceStaticSms(){
		return new SmsSender();
	}
	//4.使用反射获取类
	//好处不需要修改方法，缺点是不同的产品需要不同额外参数时不支持
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static <T extends Sender> T reflectFactory(Class<T> sender) {
		T result  = null;
		try {
			result = (T)Class.forName(sender.getName()).newInstance();
		}catch(Exception e) {
			
		}
		return result;
	}
}

//工厂模式
public class FactoryMethod {
	void multifactory(){
		SendFactory factory=new SendFactory();
		Sender sender=factory.produceMail();
		sender.Send();
	}
	void staticfactory(){
		Sender sender=SendFactory.produceStaticMail();
		sender.Send();
	}
	
	abstract class Animal {
		public abstract void describe();
	}
	class Dog extends Animal {
		@Override
		public void describe() {
			// TODO Auto-generated method stub
			System.out.println("This is a dog!");
		}
	}
	class Fish extends Animal {
		@Override
		public void describe() {
			// TODO Auto-generated method stub
			System.out.println("This is a fish!");
		}
	}
	class Bird extends Animal {
		@Override
		public void describe() {
			// TODO Auto-generated method stub
			System.out.println("This is a bird!");
		}
	}
	class Unknow extends Animal {
		@Override
		public void describe() {
			// TODO Auto-generated method stub
			System.out.println("This is a new animal!");
		}
	}
	abstract class AnimalFactory {
		//简单工厂类
		//可扩展性差，不同产品需要不同额外参数的时候不支持
		public Animal seeAnimal(String name) {
			switch(name) {
			case "dog":return new Dog();
			case "fish":return new Fish();
			case "bird":return new Bird();
			default:return new Unknow();
			}
		}
		//多方法工厂
		//优点是不同产品提供不同的生产方法，使用方便，容错率高
		public Animal seeDog() {
			return new Dog();
		}
		public Animal seeFish() {
			return new Fish();
		}
		public Animal seeBird() {
			return new Bird();
		}
	}
	//抽象工厂
	public abstract class AnimalAbstractFactory {
		public abstract void see();
	}
	public class seeDog extends AnimalAbstractFactory {
		@Override
		public void see() {
			// TODO Auto-generated method stub
			System.out.println("Oh! This is a dog!");
		}
	}
	public class seeFish extends AnimalAbstractFactory {
		@Override
		public void see() {
			// TODO Auto-generated method stub
			System.out.println("Oh! This is a fish!");
		}
	}
	public class seeBird extends AnimalAbstractFactory {
		@Override
		public void see() {
			// TODO Auto-generated method stub
			System.out.println("Oh! This is a bird!");
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args){
//		SendFactory factory=new SendFactory();
//		Sender sender=factory.produce("sms");
		SendFactory factory=new SendFactory();
		Sender sender=SendFactory.reflectFactory(SmsSender.class);
		sender.Send();
	}
}
