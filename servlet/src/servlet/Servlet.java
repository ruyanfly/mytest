package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet
 */
//这里有WebServlet注解就不需要在web.xml中配置servlet跟servlet-mapping
//@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		//getServletContext()与getServletConfig().getServletContext()效果是一样的，第一种是直接获取，第二种是先获取config再获取context
		 
		/**
		//这里是读取context字段信息
		//这里可以直接用getServletContext()，并不为空
		ServletContext context = this.getServletContext();
		//System.out.println("this is the servletcontext: "+context);
		String data = (String) context.getAttribute("data");//从ServletContext对象中取出数据
		response.getWriter().print("data="+data+ "<br/>");
		//获取整个Web站点的初始化参数
		//String contextInitParam = context.getInitParameter("url");
		//虽然context跟config一样都有getInitParameterNames()，但很显然两者读取的区域不同
		Enumeration<String> e = context.getInitParameterNames();
		while(e.hasMoreElements()) {
			String name = e.nextElement();
			String value = context.getInitParameter(name);
			response.getWriter().print(name + "=" + value + "<br/>");
		}
		//response.getWriter().print("url="+contextInitParam);
		 */
		
		/**
		response.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		//使用OutputStream读取中文
		OutputStream os = response.getOutputStream();
		os.write("outputstream: 中国".getBytes("utf-8"));
		//两种方式不能同时使用
		//使用PrintWriter输出中文
		PrintWriter pw= response.getWriter();
		pw.write("printwriter: 中国");
		//可以输出中文，但是不能直接输出数字，如果要输出数字，需要把数字转成字符串
		 * */
		
		/**
		//查询某类的.class文件所在目录，查询包上级路径，只需将参数改作“/”
		System.out.println(this.getClass().getResource(""));
		//查询某类的classloader所在目录
		System.out.println(this.getClass().getClassLoader().getResource(""));
		//查询classloader所在目录
		System.out.println(ClassLoader.getSystemResource(""));
		//得到工程目录
		System.out.println(request.getSession().getServletContext().getRealPath(""));
		//得到IE地址栏地址
		System.out.println(request.getRequestURL());
		//得到相对地址
		System.out.println(request.getRequestURI());
		//得到工程名
		System.out.println(request.getContextPath());
		//得到工程名
		System.out.println(request.getServletPath());
		//获取获取系统根目录, C:\Users\yanru
		System.out.println(System.getProperty("user.home"));
		//获取工程目录, D:\Soft\eclipse
		System.out.println(System.getProperty("user.dir"));
		//新建文件，默认位于工程目录
		System.out.println(new File("xxx.txt").getAbsolutePath());
		*/

		/**文件下载
		//1.获取要下载的文件的绝对路径
		String filePath = this.getServletContext().getRealPath("/Download/中文名测试.txt");
		//最重要的就是文件路径
		System.out.println(filePath);
		//2.获取要下载的文件名
		String fileName = filePath.substring(filePath.lastIndexOf("\\")+1);
		//3.设置content-disposition响应头控制浏览器以下载的形式打开文件
		response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(fileName, "UTF-8"));
		//中文文件名要使用URLEncoder.encode方法进行编码(URLEncoder.encode(fileName, "字符编码"))，否则会出现文件名乱码
		//4.获取要下载的文件输入流
		InputStream in = new FileInputStream(filePath);
		int len = 0;
		//5.创建数据缓冲区
		byte[] buffer = new byte[1024];
		//6.通过response对象获取OutputStream流
		//在编写下载文件功能时，要使用OutputStream流，避免使用PrintWriter流，因为OutputStream流是字节流，
		//可以处理任意类型的数据，而PrintWriter流是字符流，只能处理字符数据，如果用字符流处理字节数据，
		//会导致数据丢失
		//不能用PrintWriter out = response.getWriter();
		OutputStream out = response.getOutputStream();
		//7.将FileInputStream流写入到buffer缓冲区
		while ((len = in.read(buffer)) > 0) {
			//8.使用OutputStream将缓冲区的数据输出到客户端浏览器
			out.write(buffer,0,len);
		}
		in.close();
		*/
		
		//System.out.println(this.getServletContext().getContextPath());//输出结果/servlet
		//System.out.println(this.getServletContext().getResourcePaths("/"));//输出结果[/META-INF/, /WEB-INF/]
		//getOutputStream和getWriter方法分别用于得到输出二进制数据、输出文本数据的ServletOuputStream、Printwriter对象
		//getOutputStream和getWriter这两个方法互相排斥，调用了其中的任何一个方法后，就不能再调用另一方法
		//Servlet程序向ServletOutputStream或PrintWriter对象中写入的数据将被Servlet引擎从response里面获取，
		//Servlet引擎将这些数据当作响应消息的正文，然后再与响应状态行和各响应头组合后输出到客户端
		//Serlvet的service方法结束后，Servlet引擎将检查getWriter或getOutputStream方法返回的输出流对象是否已经调用过close方法，
		//如果没有，Servlet引擎将调用close方法关闭该输出流对象
		
		/**
		//生成随机图片，确认可用
		response.setHeader("refresh","5");//设置refresh响应头控制浏览器每隔5秒刷新一次
		//1、在内存中创建一张图片
		BufferedImage image = new BufferedImage(80, 20, BufferedImage.TYPE_INT_RGB);
		//2、得到图片
		Graphics2D g = (Graphics2D)image.getGraphics();
		g.setColor(Color.WHITE);//设置图片的别境色
		g.fillRect(0, 0, 80, 20);//填充背景色
		//3、向图片上写数据
		g.setColor(Color.BLUE);
		g.setFont(new Font(null, Font.BOLD, 20));
		g.drawString(makeNum(), 0, 20);
		//4、设置响应头控制浏览器以图片的方式打开
		response.setContentType("image/jpeg");
		//5、设置响应头控制浏览器不缓存图片数据
		response.setDateHeader("expries", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		//6、将图片写给浏览器
		ImageIO.write(image, "jpg", response.getOutputStream());
		response.setHeader("refresh","5");//设置refresh响应头控制浏览器每隔5秒钟刷新一次
		**/
		
		/**
		 //1、调用sendRedirect方法实现请求重定向
		 //sendRedirect方法内部调用了
		 //这里的‘/’是给浏览器使用，所以此时‘/’代表的就是webapp目录
		 response.sendRedirect("/servlet/index.jsp");
		 //可以改写成response.sendRedirect(request.getContextPath()+"/index.jsp");
		 //2.使用response设置302状态码和设置location响应头实现重定向实现请求重定向
		 response.setHeader("Location", "/servlet/index.jsp");
		 response.setStatus(HttpServletResponse.SC_FOUND);//设置302状态码，等同于response.setStatus(302);
		 */
		//应用服务器把WEB-INF指为禁访目录所以不能直接在浏览器访问到
		//response.sendRedirect("/servlet/index.jsp");
		//如果需要访问WEB-INF可以用下面的方法
		//这里的‘/’是给服务器用，所以此时‘/’代表的就是WEB工程
		request.getRequestDispatcher("/WEB-INF/1.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		//response.sendRedirect("/servlet/index.jsp");
		/**
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
		//后面两句必须在最后，这是输出关闭语句
		out.flush();
		out.close();
		*/
	}
	
	/**
	 * 使用OutputStream流输出中文, 仅直接调用函数没有问题
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void ChineseByOutputSteam(HttpServletResponse response) throws IOException{		
		/**使用OutputStream输出中文注意问题：
		 * * 在服务器端，数据是以哪个码表输出的，那么就要控制客户端浏览器以相应的码表打开，
		 * * 比如：outputStream.write("中国".getBytes("UTF-8"));//使用OutputStream流向客户端浏览器输出中文，以UTF-8的编码进行输出
		 * * 此时就要控制客户端浏览器以UTF-8的编码打开，否则显示的时候就会出现中文乱码，那么在服务器端如何控制客户端浏览器以以UTF-8的编码显示数据呢？
		 * * 可以通过设置响应头控制浏览器的行为，例如：
		 * * response.setHeader("content-type", "text/html;charset=UTF-8");//通过设置响应头控制浏览器以UTF-8的编码显示数据
		 * */
		/**
		 * * data.getBytes()是一个将字符转换成字节数组的过程，这个过程中一定会去查码表，
		 * * 如果是中文的操作系统环境，默认就是查找查GB2312的码表，
		 * * 将字符转换成字节数组的过程就是将中文字符转换成GB2312的码表上对应的数字
		 * * 比如： "中"在GB2312的码表上对应的数字是98
		 * "国"在GB2312的码表上对应的数字是99
		 * */
		/**
		 * * getBytes()方法如果不带参数，那么就会根据操作系统的语言环境来选择转换码表，如果是中文操作系统，那么就使用GB2312的码表
		 * */
		/**
		String chineseData = "中国";
		OutputStream outputStream = response.getOutputStream();//获取OutputStream输出流
		response.setHeader("content-type", "text/html;charset=UTF-8");//通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码		
		byte[] dataByteArr = chineseData.getBytes("UTF-8");//将字符转换成字节数组，指定以UTF-8编码进行转换
		outputStream.write(dataByteArr);//使用OutputStream流向客户端输出字节数组	
		//response.getWriter().print(dataByteArr);
		 */
		
		/**
		 * 使用PrintWriter输出中文
		 * 在获取PrintWriter输出流之前首先"response.setCharacterEncoding(charset)"设置字符输出浏览器编码格式；
		 * 然后再使用response.getWriter();获取PrintWriter输出流;这两步骤不能颠倒
		 * 然后再使用response.setHeader("content-type", "text/html;charset=字符编码");设置响应头，控制浏览器以指定的字符编码进行显示;
		 */
		/**
		//response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");//设置输出到客户端浏览器编码格式为utf-8
		//通过设置响应头控制浏览器以UTF-8的编码显示数据,，如果没有这句，浏览器显示乱码
		//response.setHeader("content-type", "text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//使用HTML语言里面的<meta>标签来控制浏览器行为，模拟通过设置响应头控制浏览器行为
		out.write("<meta http-equiv=\"content-type\" content=\"text/html;charset=UTF-8\"/>");
		String chineseData = "中国";
		//byte[] dataByteArr = chineseData.getBytes("utf-8");//将字符转换成字节数组，指定以UTF-8编码进行转换
		out.write(chineseData);//使用OutputStream流向客户端输出字节数组	
		//ChineseByOutputSteam(response);
		 */
	}
	
	/*
	 * 生成随机数字
	 */
	/**
	private String makeNum() {
		Random random = new Random();
		String num = random.nextInt(999999)+"";
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i < 7-num.length(); i++) {
			buffer.append("0");
		}
		num = buffer.toString() + num;
		return num;
	}
	**/

}
