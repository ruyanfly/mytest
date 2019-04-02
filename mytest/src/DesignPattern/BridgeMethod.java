package DesignPattern;

interface BridgeSourceable{
	public void method();
}

class BridgeSourceSub1 implements BridgeSourceable{
	@Override
	public void method() {
		// TODO Auto-generated method stub
		System.out.println("this is the first sub!");
	}
}

class BridgeSourceSub2 implements BridgeSourceable{
	@Override
	public void method() {
		// TODO Auto-generated method stub
		System.out.println("this is the second sub!");
	}
}

abstract class Bridge{
	private BridgeSourceable source;
	public void method(){
		source.method();
	}
	public BridgeSourceable getSource(){
		return source;
	}
	public void setBridgeSourceable(BridgeSourceable source){
		this.source=source;
	}
}

class MyBridge extends Bridge{
	public void method(){
		getSource().method();
	}
}

public class BridgeMethod {
	public static void main(String[] args){
		Bridge bridge=new MyBridge();
		BridgeSourceable source1=new BridgeSourceSub1();
		bridge.setBridgeSourceable(source1);
		bridge.method();
		BridgeSourceable source2=new BridgeSourceSub2();
		bridge.setBridgeSourceable(source2);
		bridge.method();
	}
}
