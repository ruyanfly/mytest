package DesignPattern;

import java.util.Enumeration;
import java.util.Vector;

interface Observer{
	public void update();
}

class Observer1 implements Observer{
	@Override
	public void update() {
		// TODO Auto-generated method stub
		System.out.println("observer1 has received!");
	}
}

class Observer2 implements Observer{
	@Override
	public void update() {
		// TODO Auto-generated method stub
		System.out.println("observer2 has received!");
	}
}

interface ObserverSubject{
	public void add(Observer observer);
	public void del(Observer observer);
	public void notifyObservers();
	public void operation();
}

abstract class AbstractSubject implements ObserverSubject{
	private Vector<Observer>vector=new Vector<Observer>();
	public void add(Observer observer){
		vector.add(observer);
	}
	public void del(Observer observer){
		vector.remove(observer);
	}
	public void notifyObservers(){
		Enumeration<Observer> enumo=vector.elements();
		while(enumo.hasMoreElements()){
			enumo.nextElement().update();
		}
	}
}

class MyObserverSubject extends AbstractSubject{
	@Override
	public void operation() {
		// TODO Auto-generated method stub
		System.out.println("update self!");
		notifyObservers();
	}
}

public class ObserverMethod {
	public static void main(String[] args){
		AbstractSubject sub=new MyObserverSubject();
		sub.add(new Observer1());
		sub.add(new Observer2());
		sub.operation();
	}
}
