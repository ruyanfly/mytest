package hibernateTest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import model.User;

//@Test//我们使用junit进行测试
public class UserTest {
	public static void main(String[] args){
		/***
		 * 相对于3.x.x版本hibernate，我们在4.x.x采用如下方式获取我们的会话工厂：
	     * 1.解析我们在hibernate.cfg.xml中的配置
	     * Configuration configuration = new Configuration().configure();
	     * 2.创建服务注册类,进一步注册初始化我们配置文件中的属性
	     * ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
	     * 3.创建我们的数据库访问会话工厂
	     * SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	     * 但在5.1.0版本汇总，hibernate则采用如下新方式获取：
	     * 1. 配置类型安全的准服务注册类，这是当前应用的单例对象，不作修改，所以声明为final
	     * 在configure("hibernate.cfg.xml")方法中，如果不指定资源路径，默认在类路径下寻找名为hibernate.cfg.xml的文件
	     * 2. 根据服务注册类创建一个元数据资源集，同时构建元数据并生成应用一般唯一的的session工厂
	     */
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		
		/****上面是配置准备，下面开始我们的数据库操作******/
		Session session = sessionFactory.openSession();//从会话工厂获取一个session
		Transaction transaction = session.beginTransaction();//开启一个新的事务
		User user = new User();
		user.setName("ruyanfly");
		session.save(user);
		transaction.commit();//提交事务
	}
}
