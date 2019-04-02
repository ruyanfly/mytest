package DesignPattern;

import java.util.HashMap;
import java.util.Map;

class Singleton{
	private static Singleton instance=null;
	//private static Singleton testInstance = null;//不理解只有一个实例是指什么
	private Singleton(){
//		System.out.println("This's SingletonMethod!");
	}
	//静态内部类
	private static class SingletonFactory{
		private static Singleton instance=new Singleton();
	}
	public static Singleton getInstance(){
		return SingletonFactory.instance;
//		return instance;
	}
//	public static Singleton getInstance(){
//		if(instance==null){
//			synchronized(instance){
//				if(instance==null){
//					instance=new Singleton();
//				}
//			}
//		}
//		return instance;
//	}
	public Object readResolve(){
		return instance;
	}
}

abstract class abstractSingleton {
	abstractSingleton() {
		System.out.println("This's Abstract Singleton");
	}
	public abstract void printOut();//抽象方法不需要实现
}

class mySingleton extends abstractSingleton {
	mySingleton() {
		System.out.println("This's My Singleton");
	}
	//抽象方法必须被重写
	@Override
	public void printOut() {
		// TODO Auto-generated method stub
		
	}
}

//懒汉式单例类.在第一次调用的时候实例化自己   
class LazySingleton {
	private LazySingleton() {
		
	}
	@SuppressWarnings("unused")
	private static LazySingleton single=null;
	//静态工厂方法
	//三种线程安全的方法
	//1.在getInstance方法上加同步synchronized
//	public static synchronized LazySingleton getInstance() {
//		if (single == null) {
//			single = new LazySingleton();
//		}
//		return single;
//	}
	//2.双重检查锁定
//	public static LazySingleton getInstance() {
//		if (single == null) {
//			synchronized (LazySingleton.class) {
//				if (single == null) {
//					single = new LazySingleton();
//				}
//			}
//		}
//		return single;
//	}
	//3.静态内部类
//	private static class LazyMethod {
//		private static final LazySingleton single = new LazySingleton();
//	}
//	public static final LazySingleton getInstance() {
//		return LazyMethod.single;
//	}     
}

//饿汉式单例类.在类初始化时，已经自行实例化
//饿汉式在类创建的同时就已经创建好一个静态的对象供系统使用，以后不再改变，所以天生是线程安全的
class hungeringleton {
	private hungeringleton() {
		
	}
	private static final hungeringleton single = new hungeringleton();
	//静态工厂方法
	public static hungeringleton getInstance() {
		return single;
	}  
}

//登记式单例(可忽略)
class registerSingleton {
	private static Map<String,registerSingleton> map = new HashMap<String,registerSingleton>();
	static{
		registerSingleton single = new registerSingleton();
		map.put(single.getClass().getName(), single);
	}
	//保护的默认构造子
	protected registerSingleton() {
		
	}
	//静态工厂方法,返还此类惟一的实例
	@SuppressWarnings("deprecation")
	public static registerSingleton getInstance(String name) {
		if(name == null) {
			name = registerSingleton.class.getName();
			System.out.println("name == null"+"--->name="+name);
		}  
        if(map.get(name) == null) {
        	try {
        		map.put(name, (registerSingleton) Class.forName(name).newInstance());
        	} catch (InstantiationException e) {
        		e.printStackTrace();
        	} catch (IllegalAccessException e) {
        		e.printStackTrace();
        	} catch (ClassNotFoundException e) {
        		e.printStackTrace();
        	}
        }
        return map.get(name);
    }
	//一个示意性的商业方法
	public String about() {
		return "Hello, This's Register Singleton!";      
    } 
}


//单例模式
public class SingletonMethod {
	
	private SingletonMethod() {
		
	}
	@SuppressWarnings("unused")
	private static SingletonMethod instance=null;//删除static进行测试，单例失败，说明单例最重要的是static
	//private SingletonMethod instance=null;
	//public static SingletonMethod getInstance() {//删除static变量
	public SingletonMethod getInstance() {
		//常规方法
//		if(instance == null) {
//			instance = new SingletonMethod();
//		}
//		return instance;
		//静态方法
		return SingletonInstance.instance;
	}
	//枚举是单例最好的实现方式,需要提前对变量初始化
	enum singletonEnum{
		instance;
	}
	private static class SingletonInstance{
		private static final SingletonMethod instance = new SingletonMethod();
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		SingletonMethod sm = new SingletonMethod().getInstance();
		SingletonMethod SM = new SingletonMethod().getInstance();
		SingletonMethod sM = new SingletonMethod();
//		if(null == sM) {
//			System.out.println("the  instance is null!");
//		}//sM并不为空
		if(sm == SM) {
			System.out.println("the two instance is the same!");//这里就体现了单例的用途，创建两个对象，分别调用用例函数，但是返回的是同一个
		}
		String a = "good";
		String b = "good";
		String c = new String("good");
		String d = new String("good");
		if(a == b) {
			System.out.println("the two String a, b is the same!");
		}
		if(c == d) {
			System.out.println("the two String c, d is the same!");
		}
		if(c == a) {
			System.out.println("the two String a, c is the same!");
		}
		//Singleton s = new Singleton();//无法初始化
		//abstractSingleton as = new abstractSingleton();//抽象类无法初始化
		mySingleton ms = new mySingleton();
		registerSingleton single3 = registerSingleton.getInstance(null);  
        System.out.println(single3.about());  
	}

}
