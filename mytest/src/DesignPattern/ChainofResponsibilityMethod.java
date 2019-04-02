package DesignPattern;

interface Handler{
	public void operator();
}

abstract class AbstractHandler{
	private Handler handler;
	public Handler getHandler(){
		return handler;
	}
	void setHandle(Handler handler){
		this.handler=handler;
	}
}

class MyHandler extends AbstractHandler implements Handler{
	private String name;
	public MyHandler(String name){
		this.name=name;
	}
	@Override
	public void operator() {
		// TODO Auto-generated method stub
		System.out.println(name+" deal!");
		if(getHandler()!=null){
			getHandler().operator();
		}
	}
	
}

public class ChainofResponsibilityMethod {
	public static void main(String[] args){
		MyHandler h1=new MyHandler("h1");
		MyHandler h2=new MyHandler("h2");
		MyHandler h3=new MyHandler("h3");
		h1.setHandle(h2);
		h2.setHandle(h3);
		h1.operator();
	}
}
