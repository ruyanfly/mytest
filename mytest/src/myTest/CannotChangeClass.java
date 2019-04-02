package myTest;

import java.util.HashMap;
import java.util.Iterator;
//生成一个不可变类
public class CannotChangeClass {
	private final int id;
	private final String name;
	@SuppressWarnings("rawtypes")
	private final HashMap map;
	public int getID() {
		return id;
	}
	public String getName() {
		return name;
	}
	@SuppressWarnings("rawtypes")
	public HashMap getMap() {
		return (HashMap) map.clone();
	}
	//final变量初始化可以在构造函数中实现
	//实现深拷贝构造器
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CannotChangeClass(int i, String n, HashMap m) {
		System.out.println("Performing Deep Copy for Object initialization");
		this.id = i;
		this.name = n;
		HashMap tempMap = new HashMap();
		String key;
		Iterator it = m.keySet().iterator();
        while(it.hasNext()){
            key=(String) it.next();
            tempMap.put(key, m.get(key));
        }
        this.map=tempMap;
	}
//	//实现浅拷贝构造器
//	public CannotChangeClass(int i, String n, HashMap m) {
//		System.out.println("Performing Shallow Copy for Object initialization");
//		this.id = i;
//		this.name = n;
//		this.map = m;
//	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		HashMap m = new HashMap();
		m.put("1", "first");
		m.put("2", "second");
		String s = "original";
		int i=10;
		CannotChangeClass ccc = new CannotChangeClass(i,s,m);
		//Lets see whether its copy by field or reference
		System.out.println(s==ccc.getName());
		System.out.println(m == ccc.getMap());
        //print the ccc values
		System.out.println("ccc id:"+ccc.getID());
		System.out.println("ccc name:"+ccc.getName());
		System.out.println("ccc map:"+ccc.getMap());
        //change the local variable values
        i=20;
        s="modified";
        m.put("3", "third");
        //print the values again
        System.out.println("ccc id after local variable change:"+ccc.getID());
        System.out.println("ccc name after local variable change:"+ccc.getName());
        System.out.println("ccc map after local variable change:"+ccc.getMap());
        HashMap h = ccc.getMap();
        h.put("4", "new");
        System.out.println("ccc map after changing variable from accessor methods:"+ccc.getMap());
 
    }
}
