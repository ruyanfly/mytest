package servlet;

//import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestListener;
//import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
//import javax.xml.ws.Endpoint;

//@WebListener
//有上面的标注就不需要在web.xml中编写，所以会自动加载运行
/**
 * Listener是web三大组件之一，是servlet监听器，用来监听请求，监听服务端的操作
 * 按照生命周期可分为三种: ServletContextListener,  HttpSessionListener, ServletRequestListener
 * ServletContextListener：requestInitialized在容器启动时被调用(在servlet被实例化前执行); requestDestroyed在容器销毁时调用(在servlet被销毁后执行);
 * HttpSessionListener: sessionCreated 在HttpSession创建后调用; sessionDestroyed 在HttpSession销毁前调用（执行session.invalidate();方法）;
 * ServletRequestListener: requestDestroyed 在request对象创建后调用（发起请求）;requestInitialized 在request对象销毁前调用（请求结束）;
 * 另外还有属性监听三种: attributeAdded() 添加属性时候; attributeReplaced 替换属性时候; attributeRemoved 删除属性时候;
 * 每个生命周期的监听都有三种属性监听函数，对应的变量名分别为: ServletContextAttributeEvent; HttpSessionBindingEvent; ServletRequestAttributeEvent;
 * 感知Session监听分为两种: HttpSessionBindingListener; HttpSessionActivationListener;
 * HttpSessionBindingListener: 
 * 1, 在需要监听的实体类实现HttpSessionBindingListener接口; 
 * 2, 重写valueBound()方法，这方法是在当该实体类被放到Session中时，触发该方法;
 * 3, 重写valueUnbound()方法，这方法是在当该实体类从Session中被移除时，触发该方法;
 * HttpSessionActivationListener: 
 * 1, 在需要监听的实体类实现HttpSessionActivationListener接口;
 * 2, 重写sessionWillPassivate()方法，这方法是在当该实体类被序列化时，触发该方法;
 * 3, 重写sessionDidActivate()方法，这方法是在当该实体类被反序列化时，触发该方法;
 * @author yanru
 */
public class Listener implements 
ServletContextListener,  HttpSessionListener, ServletRequestListener {
	
	public void contextDestroyed(ServletContextEvent  s) {
		System.out.println("ContextListenser Destroyed!");
	}
	
	public void contextInitialized(ServletContextEvent s) {
		/**
		//WebService的发布地址
		String address = "http://127.0.0.1:8080/WebService";//这里会影响到Servlet的使用
		//发布WebService，WebServiceImpl类是WebServie接口的具体实现类
		Endpoint.publish(address , new ServiceInterfaceImpl());
		System.out.println("使用WebServicePublishListener发布webservice成功!");
		*/
		System.out.println("ContextListenser Initialized!");
	}

	public void sessionCreated(HttpSessionEvent h) {
		System.out.println("Session Created!");
		/**
		//这里是用Session来统计浏览器访问人数
		ServletContext ctx = h.getSession().getServletContext();
		Integer numSessions = (Integer)ctx.getAttribute("numSessions");
		if(numSessions == null) {
			numSessions = new Integer(1);
		}else {
			int count  = numSessions.intValue();
			numSessions = new Integer(count+1);
		}
		ctx.setAttribute("numSessions", numSessions);
		*/
	}
	
	public void sessionDestroyed(HttpSessionEvent h) {
		System.out.println("Session Destroyed!");
		/**
		ServletContext ctx = h.getSession().getServletContext();
		Integer numSessions = (Integer)ctx.getAttribute("numSessions");
		if(numSessions == null) {
			numSessions = new Integer(0);
		}else {
			int count  = numSessions.intValue();
			numSessions = new Integer(count-1);
		}
		ctx.setAttribute("numSessions", numSessions);
		*/
	}
	
}
