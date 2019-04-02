package servlet;

import javax.xml.ws.Endpoint;

public class MainClass {
	
	@SuppressWarnings("unused")
	private void WebServicePublish() {
		//定义WebService的发布地址，这个地址就是提供给外界访问Webervice的URL地址，
		//URL地址格式为：http://ip:端口号/xxxx
		//String address = "http://192.168.1.100:8989/";这个WebService发布地址的写法是合法的
		//String address = "http://192.168.1.100:8989/Webservice";这个WebService发布地址的是合法的
		//String address = "http://127.0.0.1:8080/WebService/ServiceInterface";
		String address = "http://127.0.0.1:8080/WebService";//这里不影响Servlet的使用
		//使用Endpoint类提供的publish方法发布WebService，发布时要保证使用的端口号没有被其他应用程序占用
		//使用Endpoin必须是JDK1.6以上版本
		Endpoint.publish(address , new ServiceInterfaceImpl());
		System.out.println("发布webservice成功!");		
	}
	
	public static void main(String[] args) {
		//MainClass mc = new MainClass();
		//mc.WebServicePublish();
		
		//String s1 = new String("Hello");
		String s1 = "Hello";
		String s2 = new String("Hello");
		String s3 = new String("Hello");
		System.out.println(s1 == s2);
		System.out.println(s2 == s3);
	}

}
