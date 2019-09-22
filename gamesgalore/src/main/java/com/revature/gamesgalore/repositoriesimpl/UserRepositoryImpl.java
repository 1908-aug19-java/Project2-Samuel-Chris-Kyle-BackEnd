package com.revature.gamesgalore.repositoriesimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.gamesgalore.models.User;
import com.revature.gamesgalore.repositories.UserRepository;
import com.revature.gamesgalore.util.HibernateUtil;

public class UserRepositoryImpl implements UserRepository{

	private static Logger logger = LogManager.getLogger();

	@Override
	public Optional<User> findById(Long userId) {
		logger.info("In find by id");
		User user = null;
		try (Session session = HibernateUtil.getSession()) {
			user = (User) session.get(User.class, userId);
			logger.info(user);
		} catch (Exception e) {
			logger.error(e);
		}
		return Optional.ofNullable(user);
	}
	
	@Override
	public Optional<User> findByEmail(String userEmail) {
		logger.info("In find by email");
		User user = null;
		try (Session session = HibernateUtil.getSession()) {
			String sql = "select * from users where user_email=?";
			Query<User> q = session.createNativeQuery(sql, User.class);
			q.setParameter(1, userEmail);
			List<User> users = q.list();
			if(!users.isEmpty()) {
				user = q.list().get(0);
			}
		}
		logger.info(user);
		return Optional.ofNullable(user);
	}

	@Override
	public Collection<User> findByFirstName(String userFirstName) {	
		logger.info("In find by first name");
		Collection<User> users = new ArrayList<User>();
		try (Session session = HibernateUtil.getSession()) {
			String sql = "select * from users where user_first_name=?";
			Query<User> q = session.createNativeQuery(sql, User.class);
			q.setParameter(1, userFirstName);
			users = q.list();
		}
		logger.info(users);
		return users;
	}
	
	@Override
	public Collection<User> findByLastName(String userLastName) {
		logger.info("In find by last name");
		Collection<User> users = new ArrayList<User>();
		try (Session session = HibernateUtil.getSession()) {
			String sql = "select * from users where user_last_name=?";
			Query<User> q = session.createNativeQuery(sql, User.class);
			q.setParameter(1, userLastName);
			users = q.list();
		}
		logger.info(users);
		return users;
	}

	@Override
	public Collection<User> findByQuery(String query) {
		logger.info("In find by query");
		Collection<User> users = new ArrayList<User>();
		try (Session session = HibernateUtil.getSession()) {
			Query<User> q = session.createNativeQuery(query, User.class);
			users = q.list();
		}
		logger.info(users);
		return users;
	}
	
	@Override
	public Collection<User> findAll() {
		logger.info("In find all");
		Collection<User> users = new ArrayList<User>();
		try (Session session = HibernateUtil.getSession()) {
			String sql = "select * from users";
			Query<User> q = session.createNativeQuery(sql, User.class);
			users = q.list();
		}
		logger.info(users);
		return users;
	}

	@Override
	public void save(User user) {
		logger.info("In save");
		try (Session session = HibernateUtil.getSession()) {
			Transaction tx = session.beginTransaction();
			session.persist(user);
			tx.commit();
			session.close();
		}
	}

	@Override
	public void update(User user) {
		logger.info("In update");
		try (Session session = HibernateUtil.getSession()) {
			Transaction tx = session.beginTransaction();
			session.update(user);
			tx.commit();
		}
	}
	
	@Override
	public void deleteById(Long userId) {
		logger.info("In delete");
		try(Session s = HibernateUtil.getSession()){
			Transaction tx = s.beginTransaction();
			s.delete(new User(userId));
			tx.commit();
		}
	}

}
