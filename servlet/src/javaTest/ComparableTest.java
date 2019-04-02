package javaTest;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 用来学习Java中Comparable方法还有Comparator比较
 * @author yanru
 *
 */

interface animal {
	public void action();
}

class dog implements animal {
	@Override
	public void action() {
		// TODO Auto-generated method stub
		System.out.println("dog action!");
	}	
}

class husky extends dog {		
	@Override
	public void action() {
		// TODO Auto-generated method stub
		System.out.println("husky action!");
	}
}

//Comparator是包内自带的，不需要自己重写，重写方而会在array.sort部分提示错误
//The method sort(T[], Comparator<? super T>) in the type Arrays is not applicable for the arguments 
//(person[], personComparator)
//interface Comparator<T> {
//	int compare(T o1, T o2);
//	boolean equals(Object obj);
//}

class personComparator implements Comparator<person> {
	@Override
	public int compare(person o1, person o2) {
		// TODO Auto-generated method stub
		//return 0;
		return o1.getIntegerAge()-o2.getIntegerAge();
	}
}

public class ComparableTest {
	
	public static void main(String[] args) {
		//Comparable是排序接口，若一个类实现了Comparable接口，就意味着“该类支持排序”.
		//而Comparator是比较器，我们若需要控制某个类的次序，可以建立一个“该类的比较器”来进行排序.
		//Comparable相当于“内部比较器”，而Comparator相当于“外部比较器”.
		//两种方法各有优劣， 用Comparable 简单， 只要实现Comparable 接口的对象直接就成为一个可以比较的对象，
		//但是需要修改源代码. 用Comparator 的好处是不需要修改源代码， 而是另外实现一个比较器，
		//当某个自定义的对象需要作比较的时候，把比较器和对象一起传递过去就可以比大小了，
		//并且在Comparator 里面用户可以自己实现复杂的可以通用的逻辑，使其可以匹配一些比较简单的对象，
		//那样就可以节省很多重复劳动了.
		//implements Comparable<Object>方法
		person[] people = new person[] {
				new person("10", 21),
				new person("11", 25),
				new person("12", 22),
				new person("13", 26),
				new person("14", 20)
		};
		for (person person : people) {
			System.out.print(person.getName()+" : "+person.getIntegerAge()+"\n");
        }
		Arrays.sort(people);
		System.out.println("\n排序后");
		for (person person : people) {
			System.out.print(person.getName()+" : "+person.getIntegerAge()+"\n");
		}
		//implements Comparator<person>方法
        Arrays.sort(people, new personComparator());
        System.out.println("\n排序后");
        for (person person : people)
        {
        	System.out.print(person.getName()+" : "+person.getIntegerAge()+"\n");
        }
		/**
		 * 但是instanceof在Java的编译状态和运行状态是有区别的：
		 * 在编译状态中，class可以是object对象的父类，自身类，子类。在这三种情况下Java编译时不会报错。
		 * 在运行转态中，class可以是object对象的父类，自身类，不能是子类。
		 * 在前两种情况下result的结果为true，最后一种为false。但是class为子类时编译不会报错。运行结果为false。
		 */
		dog d=new dog();
		husky k=new husky();
		System.out.println(d instanceof dog);
		System.out.println(d instanceof husky);
		System.out.println(k instanceof dog);
		System.out.println(k instanceof husky);
	}

}
