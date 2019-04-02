package myTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class HelloWorld {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		System.out.println(2 & 11);
		Integer n1 = 10;
		Integer n2 = 10;
		@SuppressWarnings("deprecation")
		Integer n3 = new Integer(10);
		@SuppressWarnings("deprecation")
		Integer n4 = new Integer(10);
		if(n1 == n2)
			System.out.println("the two number 1, 2 are same!");
		if(n1 == n3)
			System.out.println("the two number 1, 3 are same!");
		if(n4 == n3)
			System.out.println("the two number 4, 3 are same!");
		//自动装箱对应的值从–128到127之间的值
		Integer n5 = 128;
		Integer n6 = 128;
		if(n5 == n6)
			System.out.println("the two number 5, 6 are same!");
		System.out.println(n5);
		
		InputStream db = HelloWorld.class.getClassLoader().getResourceAsStream("db.properties");
		Properties prop = new Properties();
		try {
			prop.load(db);
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
