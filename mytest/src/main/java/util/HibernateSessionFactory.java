package util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
	/** 
     * Location of hibernate.cfg.xml file.
     * NOTICE: Location should be on the classpath as Hibernate uses
     * #resourceAsStream style lookup for its configuration file. That
     * is place the config file in a Java package - the default location
     * is the default Java package.<br><br>
     * Examples: <br>
     * <code>CONFIG_FILE_LOCATION = "/hibernate.conf.xml". 
     * CONFIG_FILE_LOCATION = "/com/foo/bar/myhiberstuff.conf.xml".</code> 
     */
    private static String CONFIG_FILE_LOCATION = "hibernate.cfg.xml";

    /** Holds a single instance of Session */
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

    /** The single instance of hibernate configuration */
    private static final Configuration cfg = new Configuration();

    /** The single instance of hibernate SessionFactory */
    private static org.hibernate.SessionFactory sessionFactory;

    /**
     * Returns the ThreadLocal Session instance. Lazy initialize
     * the <code>SessionFactory</code> if needed.
     *
     * @return Session
     * @throws HibernateException
     */
    public static Session currentSession() throws HibernateException {
        Session session = (Session) threadLocal.get();
        if (session == null) {
            if (sessionFactory == null) {
                try {
//                    cfg.configure(CONFIG_FILE_LOCATION);
//                    sessionFactory = cfg.buildSessionFactory();
//                	sessionFactory = cfg.configure().buildSessionFactory();
                	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
                	sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
                }
                catch (Exception e) {
                    System.err.println("%%%% Error Creating SessionFactory %%%%");
                    e.printStackTrace();
                }
            }
            session = sessionFactory.openSession();
            threadLocal.set(session);
        }
        return session;
    }

    /**
     * Close the single hibernate session instance.
     *
     * @throws HibernateException
     */
    public static void closeSession() throws HibernateException {
        Session session = (Session) threadLocal.get();
        threadLocal.set(null);
        if (session != null) {
            session.close();
        }
    }

    /**
     * Default constructor.
     */
    private HibernateSessionFactory() {
    }
}
