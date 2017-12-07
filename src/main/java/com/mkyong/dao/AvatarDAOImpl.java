package com.mkyong.dao;

import java.util.List;

import com.mkyong.model.avatar.Avatar;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AvatarDAOImpl implements AvatarDAO {

	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@Override
	public void save(Avatar avatar) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		session.persist(avatar);

		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Avatar> list() {
		Session session = sessionFactory.openSession();

		List<Avatar> avatarList = session.createQuery("from Avatar").list();

		session.close();

		return avatarList;
	}
}
