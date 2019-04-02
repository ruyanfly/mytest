package oldTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionsTest {

	public int add(int a, int b) {
		return a + b;
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		Class<?> aClass = ReflectionsTest.class;
		Object reflection = aClass.newInstance();
		System.out.println( reflection instanceof ReflectionsTest);
		Method add = aClass.getMethod("add", new Class[] {int.class, int.class});
		Object result = add.invoke(reflection, new Object[] {1, 2});
		System.out.println((Integer)result);
	}
}
