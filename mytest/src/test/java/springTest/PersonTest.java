package springTest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import action.Person;

public class PersonTest {
	
	public static void main(String[] args){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");
		Person p = ctx.getBean("person", Person.class);
		p.info();
	}

}
