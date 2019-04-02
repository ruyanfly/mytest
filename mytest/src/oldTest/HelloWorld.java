package oldTest;

//import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.sql.*;
//import java.util.Scanner;

class Value3{
	static int c=0;
	Value3(){
		c=15;
	}
	Value3(int i){
		c=i;
	}
	static void inc(){
		c++;
	}
}

abstract class Animal{
	
}

@SuppressWarnings("static-access")
public class HelloWorld {
	
	public HelloWorld(){
		System.out.println("this is begin!");
	}
	
	public void init(){
		System.out.println("this is first run function!");
	}
	
	public static void prt(String s){
		System.out.println(s);
	}
	//static Value3 v = new Value3(10);
	Value3 v = new Value3(10);
	static Value3 v1, v2;
	static{
		//prt("v.c="+v.c);
		prt("v1.c"+v1.c+" v2.c="+v2.c);
		v1 = new Value3(27);
		prt("v1.c="+v1.c+" v2.c"+v2.c);
		v2=new Value3(15);
		prt("v1.c"+v1.c+" v2.c"+v2.c);
	}
	
	static int arr[] = new int[5];
	
	@SuppressWarnings("unused")
	public static void main(String args[]) throws IOException{//String[] args and String args[]
		int x=1;
		int y=2;
		int z=3;
		System.out.println(++y/3);
		
		System.out.println("x>y is "+(x>y)+" x<y is "+(x<y));
		
		System.out.println(arr[0]);
		
		x=-3;
		y=-10;
		System.out.println(y%x);
		
		int i="abc".length();
		char c = 17;
		float f = 1.0f;
		int k = (int)056L;
		System.out.println(k);
		int a = 5;
		System.out.println("value is "+((a<5)?10.9:9));
		
//		Scanner s = new Scanner(System.in);
//		System.out.println("请输入字符串(q结束)：");
//		while (true) {
//			String line = s.nextLine();
//			if (line.equals("q")) break;
//			System.out.println(">>>" + line);
//		}
		
//		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
//		System.out.print("请输入一个字符串：");
//		String str = in.readLine();//需要加入读写异常
//		System.out.println("第一个："+str);
//		//in.close();可以不用加，读取后就自动结束
		
		//验证Java位运算
//		System.out.println(Integer.toBinaryString(-1)+" & "+Integer.toBinaryString(-2)+" = "+(-1&-2));
//		System.out.println(Integer.valueOf("10000000000000000000000000000000",2).toString());
		
		//考察Int与Integer的区别，主要是测试Integer的缓存 IntegerCache.cache
		//在 Java 5 中，为 Integer 的操作引入了一个新的特性，用来节省内存和提高性能。整型对象在内部实现中通过使用相同的对象引用实现了缓存和重用。
		//上面的规则适用于整数区间 -128 到 +127。
		System.out.println("---int---");
		int a1 = 127, b1 = 127;
		System.out.println(a1 == b1);//true  
	    a1 = 128;
	    b1 = 128;
	    System.out.println(a1 == b1);//true
	    
	    System.out.println("---Integer---");
	    Integer aa = 127, bb = 127;
	    System.out.println(aa == bb);//true
	    aa = 128;
	    bb = 128;
	    System.out.println(aa == bb);//false
	    System.out.println("aa:"+aa+",bb:"+bb);
	    System.out.println(aa.equals(bb));//true
	    aa = 126;
	    bb = 126;
	    System.out.println(aa == bb);//false
	    System.out.println(aa.equals(bb));//true
	    
	    String s1 = "good";
	    String s2 = "good";
	    String s3 = new String("good");
	    String s4 = new String("good");
	    if(s1 == s2)
	    	System.out.println("s1 and s2 is same!");
	    if(s1 == s3)
	    	System.out.println("s1 and s3 is same!");
	    if(s4 == s3)
	    	System.out.println("s4 and s3 is same!");
	    
	    float ff = 3.4f;
	    short ss = 1;
	    ss = (short) (ss + 1);
	    ss += 1;
	    
	    int [] intarray = {1, 2};
	    System.out.println(intarray.length);
	    
	    String slen = "Hello World！";
	    slen.length();
	    System.out.println(slen.equals(slen));
	}
	
	@SuppressWarnings("unused")
	private String sayHello="Hello World!";
	
	public static void sayHello(){
		//System.out.println(sayHello);//static函数只能调用本类中static变量
	}
	
	@SuppressWarnings("unused")
	public void ConnectDataBase(){
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/ruyanfly";
		String username="root";
		String password="";
		try {
			Class.forName(driver);
			Connection conn=DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

//abstract class MyTest{
//	public int add(int x){
//		return x+1;
//	}
//}
