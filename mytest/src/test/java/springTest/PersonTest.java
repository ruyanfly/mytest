package springTest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import action.Person;
import helloworld.HelloWorld;
import helloworld.HelloWorldService;

public class PersonTest {
	
	public static void main(String[] args){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		Person p = context.getBean("person", Person.class);
		p.info();
		
		HelloWorldService service = (HelloWorldService) context.getBean("helloWorldService");
		HelloWorld hw= service.getHelloWorld();
		hw.sayHello();
	}

}
