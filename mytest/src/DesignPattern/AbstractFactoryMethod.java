package DesignPattern;

interface AbstractSender{
	public void Send();
}

class AbstractMailSender implements AbstractSender{
	@Override
	public void Send() {
		// TODO Auto-generated method stub
		System.out.println("this is mailsender!");
	}
}

class AbstractSmsSender implements AbstractSender{
	@Override
	public void Send() {
		// TODO Auto-generated method stub
		System.out.println("this is sms sender!");
	}
}

interface Provider{
	public AbstractSender produce();
}

class SendMailFactory implements Provider{
	@Override
	public AbstractSender produce() {
		// TODO Auto-generated method stub
		return new AbstractMailSender();
	}
}

class SendSmsFactory implements Provider{
	@Override
	public AbstractSender produce() {
		// TODO Auto-generated method stub
		return new AbstractSmsSender();
	}
}
abstract class car {
	private String name;
	private String type;
	public String getName() {
		return name;
	}
	car(String name, String type) {
		this.name = name;
		this.type = type;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void describeCar() {
		System.out.println("name: "+name+" type: "+type);
	}
}
//抽象工厂设计模式
public class AbstractFactoryMethod {
	public static void main(String[] args){
		Provider provider=new SendMailFactory();
		AbstractSender sender=provider.produce();
		sender.Send();
	}
}
