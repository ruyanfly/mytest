package util;

import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.mapping.List;
import org.hibernate.query.Query;

import beans.User;

public class HibernateQueryFunctions {
	/** 
	* 添加 
	*/  
	public void save(User user){
		try {
			HibernateSessionFactory.currentSession().save(user);
		    HibernateSessionFactory.currentSession().beginTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}
	  
	/** 
	* 使用HQL全查询 
	*/  
	@SuppressWarnings("rawtypes")
	public List getallbyHQL(){
		List list=null;
		try {
			String hql = "from ruyanfly_user";
			Query query = HibernateSessionFactory.currentSession().createQuery(hql);
			list = (List) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return list;
	}
	   
	/**
	* 根据主键查询 
	*/  
	public User getbyID(int id){
		User user = null;
		try {
			user = HibernateSessionFactory.currentSession().get(User.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return user;
	}
	  
	/**
	* 根据对象属性查询（使用Query） 
	*/  
	public List getbyPropertyQuery(String name){
		List list = null;
		try {
			/**
			 * 这里不能像SQL语一样select * from Stu where SName=:name
		     * Query query=this.GetSession().createQuery("from Stu where SName=:name")
		     * query.setString("name", name)
		     * 或者
		     */
			list = (List) HibernateSessionFactory.currentSession().createQuery("from Stu where SName=?").setString(0, name).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return list;
	}
	
	/** 
	* 根据对象属性查询(使用Criteria) 
	*/  
	@SuppressWarnings("deprecation")
	public List getbyPropertyCriteria(String name){
		List list=null;
		try {
			Criteria criteria = HibernateSessionFactory.currentSession().createCriteria(User.class);
			Criterion criterion = Expression.eq("ruyanfly", name);
			criteria.add(criterion);
			list=(List) criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}  
	   return list;  
	}  
	  
	/**
	* 查询部分属性 
	*/  
	public ArrayList getProperty(){
		ArrayList list = new ArrayList();
		try {
			String hql = "select u.user_name from ruyanfly_user as u";
			Query query = HibernateSessionFactory.currentSession().createQuery(hql);
			list=(ArrayList) query.list();
			Iterator iter=list.iterator();
			while(iter.hasNext()){
				Object[] obj=(Object[]) iter.next();
				User user=new User();
				user.setName(obj[0].toString());
				user.setPassword(obj[1].toString());
				list.add(user);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return list;  
	}
	
	/** 
	* 查询一个属性 
	*/  
	public ArrayList getoneProperty(){
		ArrayList list = new ArrayList();
		try {
			String hql = "select s.SName from Stu as s";
			Query query = HibernateSessionFactory.currentSession().createQuery(hql);
			Iterator iter = query.iterate();
			while(iter.hasNext()){
				Object obj = (Object) iter.next();
				User user = new User();
				user.setName(obj.toString());
				list.add(user);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return list;
	}
	  
	/** 
	*查询一个对象一个属性值 
	*/  
	public Object getonlyProprotyValue(int id){
		Object obj = null;
		try {
			String hql = "select s.SName from Stu as s where s.SId=?";
			Query query = HibernateSessionFactory.currentSession().createQuery(hql);
			query.setInteger(0, id);
			obj = query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return obj;
	}
	
	/** 
	* SQL查询 
	*/  
	public List getallBYSQL(){
		List list = null;
		try {
			String sql = "select {c.*} from stu as c";
			SQLQuery sqlquery = HibernateSessionFactory.currentSession().createSQLQuery(sql);
			sqlquery.addEntity("c", User.class);
			list = (List) sqlquery.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
		   HibernateSessionFactory.closeSession();  
	   }
	   return list;
	}
	  
	/** 
	* 根据对象查询 
	*/  
	public List getallByObject(User user){
		List list = null;
		try {
			String hql = "from ruyanfly_user as u where u=:ruyanfly";
			//或者String hql = "from ruyanfly_user as u where s.user_id=:ruyanfly";
			Query query = HibernateSessionFactory.currentSession().createQuery(hql);
			query.setEntity("ruyanfly", user);
			list = (List) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return list;
	}
	  
	/** 
	* 模糊查询 
	*/  
	public List getallQueryLike(String name){
		List list = null;
		try {
			Query query = HibernateSessionFactory.currentSession().createQuery("from Stu as s where s.SName like :name");
			query.setString("name", "%"+name+"%");
			//不能query.setString("name", "'%"+name+"%'");
			list=(List) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return list;
	}
	   /** 
	* 统计函数 
	*/  
	public int CountStu(){
		int count = 0;
		try {
			count = (Integer) HibernateSessionFactory.currentSession().createQuery("select count(*) from ruyanfly_user").uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
		   HibernateSessionFactory.closeSession();
		}
		return count;
	}
	     
	/** 
	* 条件统计 
	*/  
	public int CountByWhere(String sex){
		int count = 0;
		try {
			Query query = HibernateSessionFactory.currentSession().createQuery("select count(*) from Stu where SSex=:sex");
			query.setString("sex", sex);
			count = (Integer)query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return count;
	}
	  
	/** 
	* 统计平均值 
	*/  
	public float VagAge(){
		float vag = 0;
		try {
			vag = (Float)HibernateSessionFactory.currentSession().createQuery("select avg(SAge) from Stu").uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return vag;
	}
	  
	/** 
	* 求和函数 
	*/  
	public int sumage(){
		int sum = 0;
		try {
			sum = (Integer)HibernateSessionFactory.currentSession().createQuery("select sum(id) from ruyanfly_user").uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return sum;
	}
}
