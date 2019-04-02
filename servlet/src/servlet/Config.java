package servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletConfig
 */
//@WebServlet("/ServletConfig")
public class Config extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //用于封装servlet的配置信息.
	//从一个servlet被实例化后，对任何客户端在任何时候访问有效，但仅对servlet自身有效，
	//一个servlet的ServletConfig对象不能被另一个servlet访问
	private ServletConfig config;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Config() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(Config)
	 */
    //这里如果是用默认的Config则会提示空指针错误
    //Servlet.service() for servlet [servlet] in context with path [/servlet] threw exception
    //所以必须用ServletConfig
	//public void init(Config config) throws ServletException {
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
    	//这里有个问题，为什么不能在其他地方用this.getServletConfig()来获取，个人感觉是因为这个时候的getServletConfig()
    	//返回值为空，需要重写
    	System.out.println("this is config: "+config);
		this.config = config;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//获取在web.xml中配置的初始化参数
		System.out.println("this is private config: "+config+"; this is inner config: "+this.getServletConfig());
		String paramVal = config.getInitParameter("name");//获取指定的初始化参数
		response.getWriter().print(paramVal);
		response.getWriter().print("<hr/>");
		//获取所有的初始化参数
		Enumeration<String> e = config.getInitParameterNames();
		while(e.hasMoreElements()) {
			String name = e.nextElement();
			String value = config.getInitParameter(name);
			response.getWriter().print(name + "=" + value + "<br/>");
		}
		
		//WEB容器在启动时，它会为每个WEB应用程序都创建一个对应的ServletContext对象，它代表当前web应用.
		//ServletConfig对象中维护了ServletContext对象的引用，开发人员在编写servlet时，
		//可以通过ServletConfig.getServletContext方法获得ServletContext对象.
		//由于一个WEB应用中的所有Servlet共享同一个ServletContext对象，
		//因此Servlet对象之间可以通过ServletContext对象来实现通讯，ServletContext对象通常也被称之为context域对象
		String data = "this is data";
		ServletContext context = config.getServletContext();
		context.setAttribute("data", data);
		response.getWriter().print("data =" + context.getAttribute("data") + "<br/>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
