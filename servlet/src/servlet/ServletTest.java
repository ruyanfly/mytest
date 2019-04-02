package servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletTest
 */
@WebServlet("/ServletTest")
public class ServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTest() {
        // TODO Auto-generated constructor stub
    	//Constructor call must be the first statement in a constructor
    	//this与super都必须放在第一行，所以两者不能同时出现
        super();
    	System.out.println("this is super");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    /**Servlet是一个供其他Java程序（Servlet引擎）调用的Java类，它不能独立运行，它的运行完全由Servlet引擎来控制和调度.
     * 针对客户端的多次Servlet请求，通常情况下，服务器只会创建一个Servlet实例对象，也就是说Servlet实例对象一旦创建,
     * 它就会驻留在内存中，为后续的其它请求服务，直至web容器退出，servlet实例对象才会销毁.
     * 在Servlet的整个生命周期内，Servlet的init方法只被调用一次.
     * 而对一个Servlet的每次访问请求都导致Servlet引擎调用一次servlet的service方法.
     * 对于每次访问请求，Servlet引擎都会创建一个新的HttpServletRequest请求对象和一个新的HttpServletResponse响应对象，
     * 然后将这两个对象作为参数传递给它调用的Servlet的service()方法，service方法再根据请求方式分别调用doXXX方法.
     * 如果在<servlet>元素中配置了一个<load-on-startup>元素，那么WEB应用程序在启动时，
     * 就会装载并创建Servlet的实例对象、以及调用Servlet实例对象的init()方法
     * */
    @SuppressWarnings("unused")
	private ServletConfig config;
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("this is init");
		//这里把系统的config赋值给函数的config
		this.config = config;
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("this is destroy");
	}

	/**
	 * @see Servlet#getServletConfig()
	 */
	//这里初始是没有config，需要重写，所以如果有这个函数但是没有重写，或者创建config，会导致后面使用提示空值
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		System.out.println("this is ServletConfig");
		//return null;
		//这里不再返回空值，而是返回建立的config
		return this.config;
	}

	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		// TODO Auto-generated method stub
		System.out.println("this is getServletInfo");
		return null; 
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	//init()方法仅在服务器装载Servlet时才由服务器执行一次，而每次客户向服务器发请求时，服务器就会调用service()方法
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Servlet接口自带service函数，这里是重写service，如果重写了，那就不会进入doGet或者doPut
		/**
		//最开始调用service函数，所以如果这里没有对字符串进行编码，那在post和get函数中进行字符串编码无用
		//这里非常重要
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("this is service");
		**/
		doGet(request, response);
		super.service(request, response);//最后末尾加上这条语句，程序就会自动进入doGet或者doPut，同时也会执行service函数
	}

	public int count = 1;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/**
		 * 当多线程并发访问这个方法里面的代码时，会存在线程安全问题吗
		 *  i变量被多个线程并发访问，但是没有线程安全问题，因为i是doGet方法里面的局部变量，
		 *  当有多个线程并发访问doGet方法时，每一个线程里面都有自己的i变量，
		 *  各个线程操作的都是自己的i变量，所以不存在线程安全问题
		 *  多线程并发访问某一个方法的时候，如果在方法内部定义了一些资源(变量，集合等)
		 *   那么每一个线程都有这些东西，所以就不存在线程安全问题了
		 **/
		/**
		 * 加了synchronized后，并发访问i时就不存在线程安全问题了，
		 * 为什么加了synchronized后就没有线程安全问题了呢？
		 * 假如现在有一个线程访问Servlet对象，那么它就先拿到了Servlet对象的那把锁
		 * 等到它执行完之后才会把锁还给Servlet对象，由于是它先拿到了Servlet对象的那把锁，
		 * 所以当有别的线程来访问这个Servlet对象时，由于锁已经被之前的线程拿走了，后面的线程只能排队等候了
		 * */
		/**
		 * 这种做法虽然解决了线程安全问题，但是编写Servlet却万万不能用这种方式处理线程安全问题，
		 * 假如有9999个人同时访问这个Servlet，那么这9999个人必须按先后顺序排队轮流访问.
		 * 针对Servlet的线程安全问题，Sun公司是提供有解决方案的：让Servlet去实现一个SingleThreadModel接口，
		 * 如果某个Servlet实现了SingleThreadModel接口，那么Servlet引擎将以单线程模式来调用其service方法.
		 * 查看Sevlet的API可以看到，SingleThreadModel接口中没有定义任何方法和常量，在Java中，
		 * 把没有定义任何方法和常量的接口称之为标记接口，经常看到的一个最典型的标记接口就是"Serializable"，
		 * 这个接口也是没有定义任何方法和常量的，标记接口在Java中有什么用呢？主要作用就是给某个对象打上一个标志，
		 * 告诉JVM，这个对象可以做什么，比如实现了"Serializable"接口的类的对象就可以被序列化，还有一个"Cloneable"接口，
		 * 这个也是一个标记接口，在默认情况下，Java中的对象是不允许被克隆的，就像现实生活中的人一样，不允许克隆，
		 * 但是只要实现了"Cloneable"接口，那么对象就可以被克隆了.
		 * 让Servlet实现了SingleThreadModel接口，只要在Servlet类的定义中增加实现SingleThreadModel接口的声明即可.
		 * 对于实现了SingleThreadModel接口的Servlet，Servlet引擎仍然支持对该Servlet的多线程并发访问，
		 * 其采用的方式是产生多个Servlet实例对象，并发的每个线程分别调用一个独立的Servlet实例对象.
		 * 实现SingleThreadModel接口并不能真正解决Servlet的线程安全问题，因为Servlet引擎会创建多个Servlet实例对象，
		 * 而真正意义上解决多线程安全问题是指一个Servlet实例对象被多个线程同时调用的问题。事实上，
		 * 在Servlet API 2.4中，已经将SingleThreadModel标记为Deprecated（过时的）.
		 */
		/**
		int i=1;
		i++;
		//synchronized (this) {//在java中，每一个对象都有一把锁，这里的this指的就是Servlet对象
			count++;//如果不放在synchronized中就存在线程安全问题，就是两个浏览器看到的count值是相同
			try {
				Thread.sleep(1000*3);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			response.getWriter().write("this is doGet"+": i="+i+"; count="+count);
		//}
		response.getWriter().append("Served at: ").append(request.getContextPath());
		**/
		
		/**
		//这里有个很奇怪的事情，用outputstream是可以正常输出中文，但是用PrintWriter输出的却是乱码
		//上面的原因找到了，是因为我在service函数中调用了getWriter()函数
		//而且使用outputstream会提示service函数里的getWriter()函数已经调用
		//获取客户机信息
		String requestUrl = request.getRequestURL().toString();//得到请求的URL地址
		String requestUri = request.getRequestURI();//得到请求的资源
		String queryString = request.getQueryString();//得到请求的URL地址中附带的参数
		String remoteAddr = request.getRemoteAddr();//得到来访者的IP地址
		String remoteHost = request.getRemoteHost();
		int remotePort = request.getRemotePort();
		String remoteUser = request.getRemoteUser();
		String method = request.getMethod();//得到请求URL地址时使用的方法
		String pathInfo = request.getPathInfo();
		String localAddr = request.getLocalAddr();//获取WEB服务器的IP地址
		String localName = request.getLocalName();//获取WEB服务器的主机名
		//response.setCharacterEncoding("utf-8");//设置将字符以"UTF-8"编码输出到客户端浏览器
		//通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
		//浏览器通过document.charset查看中文编码格式
		//response.setHeader("content-type", "text/html;charset=UTF-8");
		//response.setContentType("text/html;charset=utf-8");// 目的是为了控制浏览器的行为，即控制浏览器用UTF-8进行解码；
		//<meta http-equiv="content-type" content="text/html"/> 等价于 response.setContentType("text/html");
		//目的是用于response.getWriter()输出的字符流的乱码问题，
		//如果是response.getOutputStream()是不需要此种解决方案的；
		//因为这句话的意思是为了将response对象中的数据以UTF-8解码后发向浏览器；
		//write()：仅支持输出字符类型数据，字符、字符数组、字符串等
		//print()：可以将各种类型（包括Object）的数据通过默认编码转换成bytes字节形式，这些字节都通过write(int c)方法被输出
		//out和response.getWriter的类不一样，一个是JspWriter，另一个是java.io.PrintWriter
		//执行原理不同:JspWriter相当于一个带缓存功能的printWriter，它不是直接将数据输出到页面，而是将数据刷新到response的缓冲区后再输出
		//response.getWriter直接输出数据（response.print()），所以（out.print）只能在其后输出
		//out为jsp的内置对象，刷新jsp页面，自动初始化获得out对象，所以使用out对象是需要刷新页面的
		//而response.getWriter()响应信息通过out对象输出到网页上，当响应结束时它自动被关闭，与jsp页面无关，无需刷新页面
		//形象的比喻：当我们调用response.getWriter()这个对象同时获得了网页的画笔，这时你就可以通过这个画笔在网页上画任何你想要显示的东西
		//out的print()方法和println()方法在缓冲区溢出并且没有自动刷新时候会产生ioexception,而response.getWrite()方法的print和println中都是抑制ioexception异常的，不会有ioexception
		PrintWriter out = response.getWriter();
		//OutputStream out = response.getOutputStream();
		//out.write("获取到的客户机信息如下：".getBytes("utf-8").toString());
		out.write("<hr/>");
		out.write(("请求的URL地址："+requestUrl));
		out.write("<br/>");
		out.write(("请求的资源："+requestUri));
		out.write("<br/>");
		out.write(("请求的URL地址中附带的参数："+queryString));
		out.write("<br/>");
		out.write(("来访者的IP地址："+remoteAddr));
		out.write("<br/>");
		out.write(("来访者的主机名："+remoteHost));
		out.write("<br/>");
		out.write(("使用的端口号："+remotePort));
		out.write("<br/>");
		out.write(("remoteUser："+remoteUser));
		out.write("<br/>");
		out.write(("请求使用的方法："+method));
		out.write("<br/>");
		out.write(("pathInfo："+pathInfo));
		out.write("<br/>");
		out.write(("localAddr："+localAddr));
		out.write("<br/>");
		out.write(("localName："+localName));
		
		Enumeration<String> reqHeadInfos = request.getHeaderNames();//获取所有的请求头
		out.write("获取到的客户端所有的请求头信息如下：");
		out.write("<hr/>");
		while (reqHeadInfos.hasMoreElements()) {
			String headName = (String) reqHeadInfos.nextElement();
			String headValue = request.getHeader(headName);//根据请求头的名字获取对应的请求头的值
			out.write((headName+":"+headValue));
			out.write("<br/>");
		}
		out.write("<br/>");
		out.write("获取到的客户端Accept-Encoding请求头的值：");
		out.write("<hr/>");
		String value = request.getHeader("Accept-Encoding");//获取Accept-Encoding请求头对应的值
		out.write(value);
		**/
		
		/**
		Enumeration<String> e = request.getHeaders("Accept-Encoding");
		while (e.hasMoreElements()) {
			String string = (String) e.nextElement();
			System.out.println(string);
		}
		**/
		/**
		//使用OutputStream和PrintWrite实现下载功能
		//编写下载文件功能时，要使用OutputStream流，避免使用PrintWriter流，因为OutputStream流是字节流，可以处理任意类型的数据，而PrintWriter流是字符流，
		// 只能处理字符数据，如果用字符流处理字节数据，会导致数据丢失
		//获取文件的下载绝对路径
		String filePath=this.getServletContext().getRealPath("/Download/中文名测试.txt");
		System.out.println("this is test !");
		//获取文件名
        String fileName=filePath.substring(filePath.lastIndexOf("\\")+1);
        //设置响应头，告诉浏览器以下载的方式打来文件，设置中文编码，如果不设置会出现乱码
        response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(fileName, "UTF-8"));
        //获取文件流
        int len=0;

        //使用PrintWrite实现
        char[] c=new char[1024];
        FileReader fr=new FileReader(filePath);
        PrintWriter pw=response.getWriter();
        while((len=fr.read(c))!=-1){
            //将缓冲区数据输出到浏览器
        	pw.write(c,0,len);
        }
        fr.close();
        pw.close();
        //这两种方法不能同时使用
        //使用OutputStream方法实现
        byte[]b=new byte[1024];
        InputStream is=new FileInputStream(filePath);
        OutputStream os=response.getOutputStream();
        while((len=is.read(b))!=-1){
        	//将缓冲区数据输出到浏览器
        	os.write(b,0,len);
        }
        is.close();
        os.close();
        **/
		/**
		response.setCharacterEncoding("UTF-8");//设置将字符以"UTF-8"编码输出到客户端浏览器
		//通过设置响应头控制浏览器以UTF-8的编码显示数据
		response.setHeader("content-type", "text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		Enumeration<String> reqHeadInfos = request.getHeaderNames();//获取所有的请求头
		out.write("获取到的客户端所有的请求头信息如下：");
		out.write("<hr/>");
		while (reqHeadInfos.hasMoreElements()) {
			String headName = (String) reqHeadInfos.nextElement();
			String headValue = request.getHeader(headName);//根据请求头的名字获取对应的请求头的值
			out.write(headName+":"+headValue);
			out.write("<br/>");
		}
		out.write("<br/>");
		out.write("获取到的客户端Accept-Encoding请求头的值：");
		out.write("<hr/>");
		String value = request.getHeader("Accept-Encoding");//获取Accept-Encoding请求头对应的值
		out.write(value);
		Enumeration<String> e = request.getHeaders("Accept-Encoding");
		while (e.hasMoreElements()) {
			String string = (String) e.nextElement();
			System.out.println(string);
		}
		**/
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().write("this is doPost");
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().write("this is doPut");
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().write("this is doDelete");
	}

	/**
	 * @see HttpServlet#doHead(HttpServletRequest, HttpServletResponse)
	 */
	protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().write("this is doHead");
	}

	/**
	 * @see HttpServlet#doOptions(HttpServletRequest, HttpServletResponse)
	 */
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().write("this is doOptions");
	}

	/**
	 * @see HttpServlet#doTrace(HttpServletRequest, HttpServletResponse)
	 */
	protected void doTrace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().write("this is doTrace");
	}

}
