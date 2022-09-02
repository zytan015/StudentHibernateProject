package org.dxc.service;

import java.util.Iterator;
import java.util.List;

import org.dxc.bean.Student;
import org.dxc.factorydesign.HibernateFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StudentService implements StudentServiceInterface{
	SessionFactory factory = null;
	Session session = null;
	Transaction tx = null;
	
	public Integer insert(Student s) {
		try {
			Integer sid;
			factory = HibernateFactory.getFactoryObject();
			session = factory.openSession();
		
			tx = session.beginTransaction();
			sid = Integer.parseInt((String)session.save(s));
			tx.commit();
			
			return sid;
		}catch (HibernateException e) {
		       if (tx != null) {
		    	   tx.rollback();
		       }
		       e.printStackTrace(); 
		} finally {
			session.close(); 
		}	
		return null;
	}

	public void list() {
		try {
			tx = session.beginTransaction();
			List students = session.createQuery("FROM Student").list();
			Iterator iter = students.iterator();
			while (iter.hasNext()) {
				System.out.println((Student) iter.next());
			}
			tx.commit();
		} catch (HibernateException e) {
		       if (tx != null) {
		    	   tx.rollback();
		       }
		       e.printStackTrace(); 
		} finally {
			session.close(); 
		}	
	}

	public Student get(int id) {
		try {
			tx = session.beginTransaction();
			Student student = (Student) session.get(Student.class, id);
			tx.commit();
			return student;
		} catch (HibernateException e) {
		       if (tx != null) {
		    	   tx.rollback();
		       }
		       e.printStackTrace(); 
		} finally {
			session.close(); 
		}
		return null;
	}

	public void update(Student s) {
		try {
			tx = session.beginTransaction();
		     Student student = (Student) session.get(Student.class, s.getSid()); 
		     student.setSname(s.getSname());
		     student.setSaddr(s.getSaddr());
		     session.update(student);
		     tx.commit();
		} catch (HibernateException e) {
		       if (tx != null) {
		    	   tx.rollback();
		       }
		       e.printStackTrace(); 
		} finally {
			session.close(); 
		}  
	}

	public void delete(int id) {
		try {
			tx = session.beginTransaction();
			Student student = (Student) session.get(Student.class, id); 
		    session.delete(student);
		    tx.commit();
	    } catch (HibernateException e) {
	       if (tx != null) {
	    	   tx.rollback();
	       }
	       e.printStackTrace(); 
	    } finally {
	       session.close(); 
	    }  
	}

}
