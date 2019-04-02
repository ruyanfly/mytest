package servlet;

import javax.jws.WebService;

//这里必须要有这个标注，不然会提示has neither @WebService nor @WebServiceProvider annotation错误
@WebService
public class ServiceInterfaceImpl implements ServiceInterface{

	@Override
	//方法名首字母必须是大写，因为在WSDL文件转Java文件时候所有的方法名首字母都会自动变成大写
	//最佳方案包名SayHello 改成 全部小写 sayhello
	//或者生成代码的时候 使用 -p  ruyanfly.ServiceInterfaceImpl.sayhello 重命名报名即可
	//第三那种解决方案不建议,把SayHello.java中的  
	//@XmlType(name = "Sayhello", propOrder = {   "arg0"}) 中的name 改成 sayHello 即可
	public String SayHello(String name) {
		// TODO Auto-generated method stub
		System.out.println("WebService sayHello: "+name);
		return name+" : sayHello";
		//return null;
	}

	@Override
	public String Save(String name, String password) {
		// TODO Auto-generated method stub
		System.out.println("WebService save name : "+name+", password : "+password);
		return "save success!";
		//return null;
	}

}
