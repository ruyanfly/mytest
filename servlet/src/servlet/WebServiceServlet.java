package servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Endpoint;

/**
 * Servlet implementation class WebServiceServlet
 */
//使用Servlet3.0提供的@WebServlet注解将继承HttpServlet类的普通Java类标注为一个Servlet
//将value属性设置为空字符串，这样WebServicePublishServlet就不提供对外访问的路径
//loadOnStartup属性设置WebServicePublishServlet的初始化时机
//@WebServlet(value="",loadOnStartup=0)
@WebServlet("/WebServiceServlet")
public class WebServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebServiceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		//WebService的发布地址
		String address = "http://127.0.0.1:8080/WebService";
		//String address = "";
		//发布WebService，WebServiceImpl类是WebServie接口的具体实现类
		Endpoint.publish(address , new ServiceInterfaceImpl());
		System.out.println("使用WebServicePublishServlet发布webservice成功!");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
