package com.mkyong;

import java.util.Date;
import org.hibernate.Session;
import com.mkyong.util.HibernateUtil;
import com.mkyong.user.DBUser;

import javax.transaction.UserTransaction;

public class App {
	public static void main(String[] args) {
		System.out.println("Maven + Hibernate + Oracle");

		HibernateUtil.createSessionFactory();
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		DBUser user = new DBUser();

		user.setUsername("superman");
		user.setCreatedBy("system");

		session.save(user);
		session.getTransaction().commit();

        System.out.println(user);

        HibernateUtil.shutdown();
	}
}
