package oldTest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

class F{
	
}

public class IteratorTest {
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		HashMap<String,Integer> map = new HashMap<>();
		map.put("1", 1);
		map.put("2", 2);
		map.put("3", 3);
		map.put("4", 4);
		Iterator<Entry<String, Integer>> im = map.entrySet().iterator();
		System.out.println(map.toString());
		int i = 0;
		while(im.hasNext()) {
			if(++i==1) {
				System.out.println("执行修改!");
				map.put("1", 5);
				//map.put("5", 5);//改变map结构出现异常
			}
			System.out.println(im.next());
		}
		System.out.println(map.toString());
		
		F f = new F();
		Class c1 = F.class;
		Class c2 = f.getClass();
		Class c3 = null;
		try {
			c3 = Class.forName("MyTest.F");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (c1 == c3)
			System.out.println("the two object are same!");
		
		c1 = int.class;//int 的类类型
		c2 = String.class;//String类的类类型
		c3 = void.class;
		System.out.println(c1.getName());
		System.out.println(c2.getName());
		System.out.println(c2.getSimpleName());
		System.out.println(c3.getName());
		
		Class c = Object.class;
		System.out.println("类的名称是:"+c.getName());
		//类方法的反射
		Method[] mt = c.getMethods();
		for(int j = 0; j < mt.length; j++){
			Class returnType = mt[j].getReturnType();
			System.out.print(returnType.getName()+" ");
			System.out.print(mt[j].getName()+"(");
			Class[] paramTypes = mt[j].getParameterTypes();
			for (Class cc : paramTypes) {
				System.out.print(cc.getName()+",");
			}
			System.out.println(")");
		}
		//成员变量的反射
		//Field[] fd = c.getFields();
		//声明的成员变量
		Field[] fd = c.getDeclaredFields();
		for (Field field : fd) {
			Class fieldType = field.getType();
			String fieldName = field.getName();
			System.out.println(fieldType+": "+fieldName);
		}
	}

}
