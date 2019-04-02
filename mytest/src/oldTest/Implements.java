package oldTest;

class father{
	private static int a=0;
	public static int b=1;
	protected static int c=2;
	public void show(){
		System.out.println("this is static class father!");
	}
//	public static void show(){
//		System.out.println("this is static class father!");
//	}
	@SuppressWarnings("static-access")
	public void setNumber(int n){
		this.a=n;
		this.b=n+1;
		this.c=n+2;
	}
	@SuppressWarnings("static-access")
	public void getNumber(){
		System.out.println("a="+this.a+" ,b="+this.b+" ,c="+this.c);
	}
}

class son extends father{
	public void setNumber(int n){
		super.setNumber(n);
	}
	public void getNumber(){
		super.getNumber();
	}
	public void show(){
		System.out.println("this is static class son!");
	}
}

public class Implements {
	
	public static void main(String args[]){
		son s = new son();
		s.getNumber();
		s.setNumber(5);
		s.getNumber();
	}

}
