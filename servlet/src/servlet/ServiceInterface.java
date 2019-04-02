package servlet;

import javax.jws.WebService;
import javax.jws.WebMethod;

//该注解用于标注WebService接口
//这里的注解必须加，不然会提示错误has neither @WebService nor @WebServiceProvider annotation
@WebService
public interface ServiceInterface {
	
	//该注解用于标注WebService接口中的方法
	//这里测试如果不加注解也可以编译成功
	@WebMethod
	String SayHello(String name);
	
	@WebMethod
	String Save(String name, String password);
}
