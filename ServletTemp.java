package ruyanfly;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
//import javax.servlet.SingleThreadModel;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet
 */
//@SuppressWarnings("deprecation")
@WebServlet("/ServletTemp")
public class ServletTemp extends HttpServlet 
//implements SingleThreadModel 
{
	private static final long serialVersionUID = 1L;

	// 计数器
	private int count = 0;
	//定义ServletConfig配置文件的初始化参数
	//private ServletConfig config;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletTemp() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletTemp#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		// 运行编号1
		// 只运行一次
		//super.init(config);
		//this.config=config;
		System.out.println("this is init!");
	}

	/**
	 * @see ServletTemp#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		// 结束前运行一次
		System.out.println("this is destroy!");
	}

	/**
	 * @see ServletTemp#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		System.out.println("this is getSerlvetConfig!");
		// TODO Auto-generated method stub
		return null;
		//return this.config;
	}

	/**
	 * @see ServletTemp#getServletInfo()
	 */
	public String getServletInfo() {
		// TODO Auto-generated method stub
		System.out.println("this is getServletInfo!");
		return null;
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		//运行编号2
//		//每次启动时都会运行
//		System.out.println("this is service!");
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
//		out.println("<HTML>");
//		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
//		out.println("  <BODY>");
//		out.print("    This is ");
//		out.print(this.getClass());
//		out.println(", using the service method");
//		out.println("  </BODY>");
//		out.println("</HTML>");
//		out.flush();
//		out.close();
		/**
		 *  ServletConfig对象中维护了ServletContext对象的引用，开发人员在编写servlet时，
		 *  可以通过ServletConfig.getServletContext方法获得ServletContext对象。
		 *   */
		//String data = "xdp_gacl";
		//ServletContext context =  this.getServletConfig().getServletContext();//获得ServletContext对象
		//ServletContext context =  this.getServletContext();
		//这里要非常注意函数getServletConfig的返回值，之前一直返回的是null，所以才有错误
		/*
		 * 非常重要的知识点
		 */
		//这里有个必须注意到的问题，是现在已经申明了init函数，这个函数在这里是重写的子类
		//所以如果不在init函数中添加父类调用即super.init(config)，就会提示空指针
		//得到的结论是以后出现重写如果不调用父类就会提示空指针错误
		//context.setAttribute("data", data);  //将data存储到ServletContext对象中
		
		//获取整个web站点的初始化参数
		//String url = context.getInitParameter("url");
		//String url = getServletContext().getInitParameter("url");
		//System.out.println(" --- this is url : "+url);
	//}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// 当Servlet类中有service函数时浏览器直接访问Web文件中的路径不会运行到这个，而是运行service函数
		// 当没有service时默认优先执行doGet
		System.out.println("this is doGet!");
//		synchronized (this) {
			count++;
//			try {
//				Thread.sleep(1000 * 4);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
			out.println("  <BODY>");
			out.print("    This is ");
			out.print(this.getClass() + "-" + count);
			out.println(", using the GET method");
			//获取在web.xml中配置的初始化参数
			//String paramVal = this.config.getInitParameter("name");//获取指定的初始化参数
			String paramVal = this.getInitParameter("name");//获取指定的初始化参数
			//上面两种方式都是可以得到结果
			response.getWriter().print(paramVal);
			response.getWriter().print("<hr/>");
			out.print(paramVal);
			out.print("<hr/>");
			//获取所有的初始化参数
			//Enumeration<String> e = config.getInitParameterNames();
			Enumeration<String> e = this.getInitParameterNames();
			while(e.hasMoreElements()){
				String name = e.nextElement();
				String value = this.getInitParameter(name);
				response.getWriter().print(name + "=" + value + "<br/>");
				out.print(name + "=" + value + "<br/>");
			}
			out.print(this.getServletContext().getInitParameter("url"));
			out.print("<hr/>");
			out.print(this.getServletContext().getInitParameter("path"));
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		// 浏览器直接访问Web文件中的路径不会运行到这个
		// 如果只有doPost函数，通过浏览器访问会提示405错误，意思是找不到处理方法
		System.out.println("this is doPost!");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		// out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("this is doPut!");
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("this is doDelete!");
	}

	/**
	 * @see HttpServlet#doHead(HttpServletRequest, HttpServletResponse)
	 */
	protected void doHead(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("this is doHead!");
	}

	/**
	 * @see HttpServlet#doOptions(HttpServletRequest, HttpServletResponse)
	 */
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("this is doOptions!");
	}

	/**
	 * @see HttpServlet#doTrace(HttpServletRequest, HttpServletResponse)
	 */
	protected void doTrace(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("this is doTrace!");
	}

}