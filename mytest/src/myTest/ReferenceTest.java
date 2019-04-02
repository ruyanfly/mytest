//反射机制的测试
package myTest;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceTest {
	public static void main(String[] args) {
		String Ref = new String("good!");
		 SoftReference<String> SoftRef=new SoftReference<String>(Ref);
		 WeakReference<String> WeakRef = new WeakReference<String>(Ref);
		 if(Ref == SoftRef.get())
			 System.out.println("the soft object is the same!");
		 if(Ref == WeakRef.get())
			 System.out.println("the weak object is the same!");
		 System.out.println("the objects are the different!");
	}
}
