package DesignPattern;

interface Mediator{
	public void createMediator();
	public void workAll();
}

class MyMediator implements Mediator{
	private User1 user1;
	private User2 user2;
	public User getUser1(){
		return user1;
	}
	public User getUser2(){
		return user2;
	}
	@Override
	public void createMediator() {
		// TODO Auto-generated method stub
		user1=new User1(this);
		user2=new User2(this);
	}
	@Override
	public void workAll() {
		// TODO Auto-generated method stub
		user1.work();
		user2.work();
	}
}

abstract class User{
	private Mediator mediator;
	public Mediator getMediator(){
		return mediator;
	}
	public User(Mediator mediator){
		this.mediator=mediator;
	}
}

class User1 extends User{
	public User1(Mediator mediator) {
		super(mediator);
		// TODO Auto-generated constructor stub
	}
	public void work(){
		System.out.println("user1 exe!");
	}
}

class User2 extends User{
	public User2(Mediator mediator) {
		super(mediator);
		// TODO Auto-generated constructor stub
	}
	public void work(){
		System.out.println("user2 exe!");
	}
}

public class MediatorMethod {
	public static void main(String[] args){
		Mediator mediator=new MyMediator();
		mediator.createMediator();
		mediator.workAll();
	}
}
