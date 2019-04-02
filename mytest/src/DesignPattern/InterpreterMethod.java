package DesignPattern;

interface Expression{
	public int interpret(MyContext context);
}

class Plus implements Expression{
	@Override
	public int interpret(MyContext context) {
		// TODO Auto-generated method stub
		return context.getNum1()+context.getNum2();
	}
}

class Minus implements Expression{
	@Override
	public int interpret(MyContext context) {
		// TODO Auto-generated method stub
		return context.getNum1()-context.getNum2();
	}
}

class MyContext{
	private int num1;
	private int num2;
	public MyContext(int num1, int num2){
		this.num1=num1;
		this.num2=num2;
	}
	public int getNum1(){
		return num1;
	}
	public void setNum1(int num1){
		this.num1=num1;
	}
	public int getNum2(){
		return num2;
	}
	public void setNum2(int num2){
		this.num2=num2;
	}
}

public class InterpreterMethod {
	public static void main(String[] args){
		int result=new Minus().interpret((new MyContext(new Plus().interpret(new MyContext(9, 2)), 8)));
		System.out.println(result);
	}
}
