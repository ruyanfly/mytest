package webServiceClient;

public class TheClient {
	
	public static void main(String[] args) {
		//创建一个用于产生ServiceInterfaceImpl实例的工厂，ServiceInterfaceImplService类是wsimport工具生成的
		ServiceInterfaceImplService factory = new ServiceInterfaceImplService();
		//通过工厂生成一个ServiceInterfaceImpl实例，ServiceInterfaceImpl是wsimport工具生成的
		//ServiceInterfaceImpl wsImpl = factory.getWebServiceImplPort();
		ServiceInterfaceImpl wsImpl = factory.getServiceInterfaceImplPort();
		//调用WebService的sayHello方法
		String resResult = wsImpl.sayHello("孤傲苍狼");
		System.out.println("调用WebService的sayHello方法返回的结果是："+resResult);
		System.out.println("---------------------------------------------------");
		//调用WebService的save方法
		resResult = wsImpl.save("孤傲苍狼","123");
		System.out.println("调用WebService的save方法返回的结果是："+resResult);
	}
	
}
