package javaTest;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 * 用于学习Java的泛型
 * @author yanru
 *
 */

public class GenericParadigmTest {
	
	
	/**
	 * 利用反射可以得到这个方法的参数列表的类型
	 * 通过这个变量v是没有办法知道定义它的那个类型的
	 * 但是当把这个变量交给一个方法作为参数或者返回值去使用，
	 * Method类中提供了一系列方法可以获得方法的参数列表
	 * 并且是以泛型的那种形式来获得参数列表
	 * @param v
	 */
	public static void applyVector(Vector<Date> v) {
		
	}

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		
		/**
		 * 通过这种方式得到的字节码中是没有办法得到泛型类的实际类型参数的，
		 * 因为在编译这个泛型类时就已经把这个泛型类的实际参数给去掉了
		 * Vector<Date> v = new Vector<Date>();
		 * v.getClass();
		 */
		Method applyMethod = GenericParadigmTest.class.getMethod("applyVector", Vector.class);
		//得到泛型类型的参数化类型数组，Type类是Class类的父类
		Type[] types = applyMethod.getGenericParameterTypes();
		/**
		 * ParameterizedType这个类是一个参数化类型类，types数组中存储的都是参数化类型的参数，
		 * 这里取出第一个数组元素，并强制转换成ParameterizedType类型
		 */
		ParameterizedType pType = (ParameterizedType) types[0];
		System.out.println(pType.getRawType()/*得到原始类型，输出的结果为：class java.util.Vector*/);
		System.out.println(pType.getActualTypeArguments()[0]/*获得泛型的实际类型参数，输出的结果为：class java.util.Date*/);
		
		//add(3, 5);
		Number x1 = add(3.5, 5);//Integer类型和Double类型的交集就是Number类，Number类是这两个类的父类，所以可以定义Number类型的变量来接收返回值
		Object x2 = add(3, "abc");//Integer类型和String类型的交集就是Object类，因为Object类是所有类的父类，所以可以定义Object类型的变量来接收返回值
		/**
		 * swap(new String[]{"abc","123","xdp"},1,2);的执行结果如下：
		 * abc 123 xdp
		 * abc xdp 123
		 * 从结果来看，索引为1的元素和索引为2的元素的确是交换了位置
		 */
		System.out.println("x1 = "+x1+" ; x2 = "+x2+" ; = "+add(3, 5)); //输出的结果都是null
		swap(new String[] { "abc", "123", "xdp" }, 1, 2);// 调用自定义泛型方法，传入的实际参数必须是引用类型的数组
		// swap(new int[]{1,2,3,4,5},1,3);//只有引用类型才能作为泛型方法的实际参数，这里的int[]数组是属于基本类型，不能作为泛型方法的参数，所以这样会报错
		printArray(new Integer[]{1,2,3});//可以传入Integer类型的数组，因为Integer类型的数组是属于引用类型的
		//printArray(new int[]{10,2,5});不能传入非引用类型的数组作为泛型方法的实际参数
	}
	
	/**
	 * 1.编写一个泛型方法，自动将Object类型对象转换为其他类型
	 * @param <T>
	 * @param obj
	 * @return
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	private static <T> T autoConvert(Object obj) {
		return (T) obj;
	}
	
	/**
	 * 2.定义一个泛型方法，可以将任意类型的数组中的所有元素填充为相应类型的某个对象
	 * @param <T>
	 * @param array
	 * @param obj
	 */
	@SuppressWarnings("unused")
	private static <T> void fillArray(T[] array, T obj) {
		for(int i=0; i<array.length; i++) {
			array[i]=obj;
		}
		printArray(array);
	}
	
	/**
	 * 3.采用自定泛型方法的方式打印出任意参数化类型的集合中的所有内容
	 * @param <T>
	 * @param collection
	 */
	@SuppressWarnings("unused")
	private static <T> void printCollection(Collection<T> collection) {
		System.out.println(collection.size());
		for(Object obj:collection) {
			System.out.println(obj);
		}
	}
	
	/**
	 * 4.定义一个泛型方法，把任意参数类型的集合中的数据安全地复制到相应类型的数组中
	 * @param <T>
	 * @param srcCollection
	 * @param descArray
	 */
	@SuppressWarnings("unused")
	private static <T> void CollectionCopyToarray(Collection<T> srcCollection, T[] descArray) {
		Iterator<T> it=srcCollection.iterator();
		int recordElementPostion=0;
		while(it.hasNext()) {
			descArray[recordElementPostion]=it.next();
			recordElementPostion++;
		}
		printArray(descArray);
	}
	
	/**
	 * 5.定义一个泛型方法，把任意参数类型的一个数组中的数据安全地复制到相应类型的另一个数组中去
	 * @param <T>
	 * @param srcArray
	 * @param descArray
	 */
	@SuppressWarnings("unused")
	private static <T> void srcArrayToDescArray(T[] srcArray, T[] descArray) {
		for(int i=0; i<srcArray.length; i++) {
			descArray[i]=srcArray[i];
		}
		printArray(descArray);
	}
	
	/**
	 * 泛型方法的定义语法： 这里定义的就是一个泛型方法 方法的返回值类型是T，即任意的类型 返回值的具体类型由传入的类型参数来定
	 * @param<T> 代表任意的类型
	 * @param x
	 * @param y
	 * @return
	 */
	private static <T> T add(T x, T y) {
		return null;
		//return (T) (x.toString()+y.toString());//这样不会报错，但是返回的是String
	}
	
	/**
	 * 定义一个泛型方法来交换数组中指定索引位置的两个元素 这里传入的数组可以是任意类型的数组
	 * 传入泛型方法的实际参数类型必须是引用类型的数组，不能是基本类型的数组
	 * @param <T> 不会自动显示出泛型变量
	 * @param a
	 * @param i
	 * @param j
	 */
	private static <T> void swap(T[] a, int i, int j) {
		//数组中元素位置未交换前的打印结果
		printArray(a);
		T temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		System.out.println();
		//数组中元素位置交换后的打印结果
		printArray(a);
	}
	
	/**
	 * 定义打印任意引用数组类型的方法
	 * @param array
	 */
	private static <T> void printArray(T[] array) {
		for (T t : array) {
			System.out.print(t + "\t");
		}
	}
	
	/**
	 * 定义有extends限定符，并且具有多个上边界的泛型方法，各个边界使用&符号分隔
	 * @param <T>
	 */
	public <T extends Serializable & Cloneable> void method() {
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static void GenericTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		/**
		 * 不使用泛型之前ArrayList容器可以存储任意类型的对象
		 */ 
		ArrayList cc = new ArrayList();
		//下面三个在没有使用泛型的时候只能存储其中一种类型
		cc.add(1);//存储Integer对象
		//cc.add(1L);//存储Long对象
		//cc.add("xdp");//存储String对象
		/**
		 * 这里会报异常： JAVA.LANG.CLASSCASTEXCEPTION:
		 * JAVA.LANG.LONG CANNOT BE CAST TO JAVA.LANG.INTEGER 
		 */
		int i = (Integer) cc.get(0);//这里的参数表示的是数组下标
		
		/**
		 * 使用泛型限定ArrayList容器只能存储字符串类型的对象
		 */
		//ArrayList<String> cl  String是它的形参 而加入进去的具体字符串是他的实参
		ArrayList<String> cl = new ArrayList<String>();
		cl.add("ruyanfly");
		cl.add(1, "this is name");
		//cl.add(1)//报错，因为限制了cl只能存储String类的对象，不能加入Integer类型的对象
		//cl.add(1L)//报错，因为限制了cl只能存储String类的对象，不能加入Long类型的对象
		//由于已经指明集合中存储的是字符串类型的对象，因此这里不用再强制转型了
		String s = cl.get(1);
		//泛型是提供给Javac编译器看的，可以限定集合中的输入类型，让编译器挡住源程序中的非法输入，
		//编译器编译带参数类型说明的集合时会去去除掉“类型”信息，使程序运行不受影响，
		//对于参数化的泛型类型，getClass()方法的返回值和原始类型完全一样，
		//由于编译生成的字节码会去掉泛型的类型信息，因此只要能跳过编译器，就可以往某个泛型集合中加入其他类型的数据
		/**
		 * 使用反射得到集合，然后调用add方法往原本只能存储Integer对象的集合中存储一个String类型的对象
		 */
		ArrayList<Integer> cll = new ArrayList<Integer>();
		//对于参数化的泛型类型，getClass()方法的返回值和原始类型完全一样
		System.out.println(cll.getClass());//结果为：java.util.ArrayList
		System.out.println(cll.getClass() == cl.getClass());//结果为true
		//使用反射得到集合，然后调用add方法往原本只能存储Integer对象的集合中存储一个String类型的对象
		cll.getClass().getMethod("add", Object.class).invoke(cll, "abc");
		System.out.println(i+" - "+s+" - "+cll.get(0));
		/**
		 * 泛型是JDK1.5的所有新特性中最难深入掌握的部分，没有使用泛型时，只要是对象，不管是什么类型的对象，都可以存储进同一个集合中
		 * 使用泛型集合，可以将一个集合中的元素限定为一个特定类型，这样集合中就只能存储同一类型的对象，这样更安全;
		 * 并且当从集合中获取一个对象时，编译器也知道这个对象的类型，不需要对对象进行强制类型转换，这样更方便.
		 * 在JDK1.5之后，你还可以按原来的方式将各种不同类型的数据放到同一个集合中，但是编译时会报一个unChecked警告
		 * 泛型中的类型参数严格说明集合中装载的数据类型是什么和可以加入什么类型的数据,
		 * 记住：Collection<String>和Collectin<Object>是两个没有转换关系的参数化的类型
		 */
		/**
		 * 需要用到Java集合相关知识
		 * ArrayList<E>类定义和ArrayList<Integer>类引用中涉及如下术语
		 * 整个称为ArrayList<E>泛型类型
		 * ArrayList<E>中的E称为类型变量或类型参数
		 * 整个ArrayList<Integer>称为参数化类型
		 * ArrayList<Integer>中的Integer称为类型参数的实例或实际类型参数
		 * ArrayList<Integer>中的<>是"typeof"
		 * ArrayList称为原始类型
		 * 参数化类型与原始类型的兼容性：
		 * 参数化类型可以引用一个原始类型的对象，编译时编译器会报警告，例如：Collection<String> c = new Vector()
		 * 原始类型可以引用一个参数化类型的对象，编译时编译器会报警告，例如：Collection c = new Vector<String>()
		 * 参数化类型不考虑类型参数的继承关系：
		 * Vector<String> v = new Vector<Object>();//错误，语法上不通过
		 * Vector<Object> v = new Vector<String>();//错误，语法上不通过
		 * 假设Vector<String> v = new Vector<Object>;可以的话，那么以后从v中取出的对象当作String用，而v实际指向的集合中可以加入任意类型的对象
		 * 假设Vector< Object > v = new Vector< String >;可以的话，那么以后可以向v中加入任意类型的对象，而v实际指向的集合中只能装String类型的对象
		 * 下面的代码不会报错
		 * 需要用到Java存储相关知识
		 * Vector v1 = new Vector<String>();//参数化类型的对象可以给原始类型的引用
		 * Vector<Object> v=v1;//参数化类型的引用可以指向原始类型的对象
		 */
		HashMap<String, Integer> maps = new HashMap<String, Integer>();
		maps.put("lhm", 35);
		maps.put("flx", 33);
		/**
		 * 变量的命名技巧：如果以后不知道一个变量该如何命名，就可以以方法名的形式来命名，
		 * 如果要定义变量接收返回值，如果此时不知道如何定义变量名时，就直接定义成returnValue
		 */
		Set<Map.Entry<String, Integer>> entrySet = maps.entrySet();// 这里的变量名直接以方法名的形式定义
		// 使用增强的for循环迭代Map容器中的key和value
		//这里的Entry是Map类的一个内部类，map类中存储的key和value都是封装在这个Entry内部类中的
		//这个Entry内部类提供了getKey方法取出键，getValue方法取出值
		for (Map.Entry<String, Integer> entry : entrySet) {
			System.out.println(entry.getKey() + "：" + entry.getValue());
		}
		
	}
	
	/**
	 * Collection<Object>中的Object只是说明Collection<Object>实例对象中的方法接收的参数是Object
	 * Collection<Object>是一种具体的类型，new HashSet<Date>也是一种具体的类型，两者没有兼容性问题
	 * @param collection
	 */
	/**static void printCollection(Collection<Object> collection) {
		for(Object obj : collection) {
			System.out.println(obj);
		}
		collection.add("abc");//没错
		//collection = new HashSet<Date>();//提示错误需要把Object改成Date
	}**/
	
	//Collection<?>与Collection<Object>作为函数参数，效果一样，会提示函数重复
	/**
	 * 这里的Collection<?>中的?表示可以传人任意的类型参数
	 * Collection<?> cols可以匹配任意参数化的类型，但是到底匹配的是什么类型，只有以后才知道
	 * 所以 cols=new ArrayList<Integer>和cols = new ArrayList<String>都可以
	 * 但是cols.add("abc")或cols.add(new Date())都不行
	 * @param collection
	 */
	/**static void printCollection(Collection<?> collection) {
		for(Object obj : collection) {
			System.out.println(obj);
		}
		//collection.add("abc");//报错，因为collection不知道未来匹配的一定是String类型
		//错误 is not applicable for the arguments (String)
		collection.size();//不报错，此方法与参数类型没有关系
		collection=new HashSet<Date>();//这是可以的
	}**/
	
	//总结：使用?通配符可以引用其他各种参数化的类型，?通配符定义的变量主要用作引用，可以调用与参数无关的方法，不能调用与参数有关的方法
	/**
	 * 泛型中的?通配符的扩展
	 * 1.限定通配符?的上边界
	 * 正确的写法：Vector<? extends Number> x = new Vector<Integer>();
	 * 这里指的是?所代表的参数化类型必须是继承Number类的，如这里的?所代表的Integer类型就是继承Number类的
	 * 错误的写法：Vector<? extends Number> x = new Vector<String>();
	 * 限定通配符?的下边界
	 * 正确的写法：Vector<? super Integer> y = new Vector<Number>();
	 * 这里指的是?所代表的参数化类型必须是Integer类的父类，如这里的?所代表的Number类型就是Integer类的父类
	 * 错误的写法：Vector<? super Integer> y = new Vector<Byte>();
	 */
	//定义：参数化类型就是给类一个形参类似于方法一样
	//List<String>list String是形参而加入进去的具体字符串是实参
	//作用：进行类型检测防止类型转换异常将运行时的错误提前到编译期
	//比如list.add(56)由于加入的不是字符串就会提示编译错误
	//作用范围：泛型只作用于编译器在运行期间不起作用
	//通配符：Integer与Number存在继承关系，但是List<Integer>与List<Number>不存在继承关系可以使用通配符实现继承关系extends上限super下限
	class Solution {
		class MyNumber<T extends Number>{
			private T t;
			public void setNumber(T t) {
				this.t = t;
			}
			public T getNumber() {
				return this.t;
			}
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void Solution() {
		Solution s = new Solution();
		Solution.MyNumber p = s.new MyNumber<Integer>();//内部类的实例化方法
		p.setNumber(123);
		System.out.print((Integer)p.getNumber());
	}
	//泛型类
//	class Person<T>{
//		private T t;
//		public void setPerson(T t) {
//			this.t = t;
//		}
//		public T getPerson() {
//			return this.t;
//		}
//	}
	//泛型方法：实现两个数相加
	/**
	 * 通过使用泛型 两个数字相加 无论什么类型都可以
	 * @param t1
	 * @param t2
	 * @param T
	 */
	public <T extends Number> void sum(T t1, T t2) {
		if(t1.getClass()==Integer.class&&t2.getClass()==Integer.class){
			System.out.print("Integer:");
			System.out.print( t1.intValue() +  t2.intValue());
        }else if(t1.getClass()==Float.class&&t2.getClass()==Float.class){
        	System.out.print("Float:");
        	DecimalFormat format = new DecimalFormat(".00"); //保留两位小数
        	System.out.print(format.format(t1.floatValue() + t2.floatValue()));
        }else if(t1.getClass()==Double.class&&t2.getClass()==Double.class){
        	System.out.print("Double:");
        	DecimalFormat format = new DecimalFormat(".000"); //保留三位小数
        	System.out.print(format.format(t1.doubleValue() + t2.doubleValue()));
        } else if(t1.getClass()==Long.class&&t2.getClass()==Long.class){
        	System.out.print("Long:");
        	System.out.print( t1.longValue() + t2.longValue());
        }else if(t1.getClass()==Short.class&&t2.getClass()==Short.class){
        	System.out.print("Short:");
        	System.out.print( t1.shortValue() + t2.shortValue());
        }else if(t1.getClass()==Double.class||t2.getClass()==Double.class){
        	System.out.print("强制转换Double:");
        	DecimalFormat format = new DecimalFormat(".000");
        	System.out.print(format.format(t1.doubleValue() + t2.doubleValue()));
        }else if(t1.getClass()==Long.class||t2.getClass()==Long.class){
        	System.out.print("强制转换Long:");
        	System.out.print(t1.longValue() + t2.longValue());
        } else if(t1.getClass()==Float.class||t2.getClass()==Float.class){
        	System.out.print("强制转换Float:");
        	DecimalFormat format = new DecimalFormat(".00");
        	System.out.print(format.format(t1.floatValue() + t2.floatValue()));
        }else if(t1.getClass()==Integer.class||t2.getClass()==Integer.class){
        	System.out.print("强制转换Integer:");
        	System.out.print(t1.intValue() + t2.intValue());
        }
	}

}
