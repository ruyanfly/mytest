package dao;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

import beans.User;
import util.HibernateSessionFactory;

@SuppressWarnings("deprecation")
public class UserDao {
	@SuppressWarnings("rawtypes")
	public User getUser(String name) throws HibernateException {
		Session session = null;
		Transaction transaction = null;
		User user = null;
		try {
			session = HibernateSessionFactory.currentSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from User where user_name=?");
			query.setString(0, name.trim());
			user = (User)query.uniqueResult();
			query = null;
			transaction.commit();
			session.close();
		}catch(HibernateException e) {
			throw e;
		}finally{
			if (transaction!=null) {
				transaction.rollback();
			}
			HibernateSessionFactory.closeSession();
		}
		return user;
	}
	
    public void addUser(String name, String password) {
        // 得到当前连接数据库的session
    	Session session = HibernateSessionFactory.currentSession();
        // 开启事务
    	session.beginTransaction();
        try {
        	User user = new User();
    		user.setName(name);
    		user.setPassword(password);
            Serializable id = session.save(user);
            if (id != null) {
                System.out.println("新增成功！");
                session.getTransaction().commit();// 提交事务
            } else {
            	session.getTransaction().rollback();// 失败回滚
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            session.close();
        }
    }

    public User selectUser(int id) {
        // 得到当前连接数据库的session
    	Session session = HibernateSessionFactory.currentSession();
    	User user = null;
        // 开启事务
    	session.beginTransaction();
        try {
            // 查询get
            user = (User) session.get(User.class, id);
            System.out.println("查询结果：" + user.getName());
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            session.close();
        }
        return user;
    }

    public void updateUser(String name, String password) {
        // 得到当前连接数据库的session
    	Session session = HibernateSessionFactory.currentSession();
        // 开启事务
    	session.beginTransaction();
        try {
            // 第1种：update持久化修改：先查询，与数据库有关联
            /*User user = (User) session.get(User.class, 15);
            user.setName("duheyu");
            user.setPassword("duheyu");
            session.update(user);
            tx.commit();*/
            
            //第2种：瞬时状态下修改,会set全部字段，不修改的字段全部清空了
            /*User user =new User();
            user.setId(15);
            user.setName("duxinke");
            user.setPassword("duxinke");
            session.update(user);
            tx.commit();*/
            
            //第3种，新增或修改，拿瞬时状态来测试
            User user =new User();
            user.setName(name);
            user.setPassword(password);
            session.saveOrUpdate(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            session.close();
        }
    }
    
    public void deleteUser(int id){
        // 得到当前连接数据库的session
    	Session session = HibernateSessionFactory.currentSession();
        // 开启事务
    	session.beginTransaction();
        //执行操作，都是用当前Session执行
        try {
            User user =new User();
            user.setId(id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
