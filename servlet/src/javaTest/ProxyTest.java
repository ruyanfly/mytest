package javaTest;

/**
 * 用来学习Proxy(代理)
 * 动态代理技术就是用来产生一个对象的代理对象的
 * 
 * 代理对象存在的价值主要用于拦截对真实业务对象的访问
 * 代理对象应该具有和目标对象(真实业务对象)相同的方法
 * 现在要生成某一个对象的代理对象，这个代理对象通常也要编写一个类来生成
 * java在JDK1.5之后提供了一个"java.lang.reflect.Proxy"类，通过"Proxy"类提供的一个newProxyInstance方法用来创建一个对象的代理对象
 * static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) 
 * newProxyInstance方法用来返回一个代理对象
 * ClassLoader loader用来指明生成代理对象使用哪个类装载器
 * Class<?>[] interfaces用来指明生成哪个对象的代理对象，通过接口指定
 * nvocationHandler h用来指明产生的这个代理对象要做什么事情
 * 需要调用newProxyInstance方法就可以得到某一个对象的代理对象
 * @author yanru
 *
 */

public class ProxyTest {

}
