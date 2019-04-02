package DesignPattern;

class Source{
	public void method1(){
		System.out.println("this is original method!");
	}
}

interface Targetable{
	public void method1();
	public void method2();
}
//当继承的类中包含接口函数时，交集函数可以不用重写
//方法重载是指同一个类中的多个方法具有相同的名字，但这些方法具有不同的参数列表，即参数的数量或参数类型不能完全相同
//方法重写是存在子父类之间，子类定义的方法与父类中的方法具有相同的方法名字，相同的参数表和相同的返回类型
class Adapter extends Source implements Targetable{
	@Override
	public void method2() {
		// TODO Auto-generated method stub
		System.out.println("this is targetable method!");
	}
}

class Wrapper implements Targetable{
	private Source source;
	public Wrapper(Source source){
		super();
		this.source=source;
	}
	@Override
	public void method1() {
		// TODO Auto-generated method stub
		source.method1();
	}
	@Override
	public void method2() {
		// TODO Auto-generated method stub
		System.out.println("this is original method!");
	}
}

interface Sourceable{
	public void method1();
	public void method2();
}

abstract class Wrapper2 implements Sourceable{
	public void method1(){
		
	}
	public void method2(){
		
	}
}

class SourceSub1 extends Wrapper2{
	public void method1(){
		System.out.println("the sourceable interface's first Sub1!");
	}
}

class SourceSub2 extends Wrapper2{
	public void method2(){
		System.out.println("the sourceable interface's second Sub2!");
	}
}
//适配器模式用例
//ps2接口
interface Ps2 {
	public void isPs2();
}
//usb接口
interface Usb {
	public void isUsb();
}
//usb接口实现类
class Usber implements Usb {
	@Override
	public void isUsb() {
		// TODO Auto-generated method stub
		System.out.println("USB口");
	}
}
//适配器,类适配器,通过继承来实现适配模式
class UsbAdapterPs2 extends Usber implements Ps2 {
	@Override
	public void isPs2() {
		// TODO Auto-generated method stub
		isUsb();
	}
}
//适配器,对象适配器模式,通过组合来实现适配器
class Ps2AdapterUsb implements Ps2 {
	private Usb usb;
	public Ps2AdapterUsb(Usb usb) {
		this.usb = usb;
	}
	@Override
	public void isPs2() {
		// TODO Auto-generated method stub
		usb.isUsb();
	}
}
//关于虚函数跟接口的测试
abstract class myAbstract {
	public String s = "this is abstract!";
	//虚函数中的方法要不加上abstract，要不就要加上函数体
	public abstract void function1();
	public void function2() {
		System.out.println(s);
	}
}
interface myInterface{
	public String s = "this is interface!";
	public abstract void function3();
	public void function4();//接口函数不需要实现
}
//由下可知，不管是继承虚函数还是接口，都需要实现里面的函数
class myTestAbstract extends myAbstract {
	@Override
	public void function1() {
		// TODO Auto-generated method stub
		
	}
}
class myTestInterface implements myInterface {
	@Override
	public void function3() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void function4() {
		// TODO Auto-generated method stub
		
	}
}
class myTestClass extends myAbstract implements myInterface {
	@Override
	public void function1() {
		// TODO Auto-generated method stub	
	}
	@Override
	public void function3() {
		// TODO Auto-generated method stub	
	}
	@Override
	public void function4() {
		// TODO Auto-generated method stub	
	}
}
public class AdapterMethod {
	public static void main(String[] args){
		Targetable target=new Adapter();
		target.method1();
		target.method2();
		
		Source source=new Source();
		Targetable target1=new Wrapper(source);
		target1.method1();
		target1.method2();
		
		Sourceable source1=new SourceSub1();
		Sourceable source2=new SourceSub2();
		source1.method1();
		source1.method2();
		source2.method1();
		source2.method2();
		
		Ps2 p = new UsbAdapterPs2();
		p.isPs2();
	}
}
