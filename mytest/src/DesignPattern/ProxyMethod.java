package DesignPattern;

interface ProxySourceable{
	public void method();
}

class ProxySource implements ProxySourceable{
	@Override
	public void method() {
		// TODO Auto-generated method stub
		System.out.println("the original method!");
	}
}

class Proxy implements ProxySourceable{
	private ProxySource source;
	public Proxy(){
		super();
		this.source=new ProxySource();
	}
	@Override
	public void method() {
		// TODO Auto-generated method stub
		before();
		source.method();
		after();
	}
	private void after(){
		System.out.println("after proxy!");
	}
	private void before(){
		System.out.println("before proxy!");
	}
}
public class ProxyMethod {
	public static void main(String[] args){
		ProxySourceable source=new Proxy();
		source.method();
	}
}
