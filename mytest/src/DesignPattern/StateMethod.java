package DesignPattern;

class State{
	private String value;
	public String getValue(){
		return value;
	}
	public void setValue(String value){
		this.value=value;
	}
	public void method1(){
		System.out.println("execute the first opt!");
	}
	public void method2(){
		System.out.println("execute the second opt!");
	}
}

class Context{
	private State state;
	public Context(State state){
		this.state=state;
	}
	public State getState(){
		return state;
	}
	public void setState(State state){
		this.state=state;
	}
	public void method(){
		if(state.getValue().equals("state1")){
			state.method1();
		}else if(state.getValue().equals("state2")){
			state.method2();
		}
	}
}

public class StateMethod {
	public static void main(String[] args){
		State state=new State();
		Context context=new Context(state);
		state.setValue("state1");
		context.method();
		state.setValue("state2");
		context.method();
	}
}
