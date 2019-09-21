package com.revature.gamesgalore.repositoriesimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
//import org.springframework.stereotype.Repository;

import com.revature.gamesgalore.models.Role;
import com.revature.gamesgalore.repositories.RoleRepository;
import com.revature.gamesgalore.util.HibernateUtil;

public class RoleRepositoryImpl implements RoleRepository {

	private static Logger logger = LogManager.getLogger();

	@Override
	public Optional<Role> findById(Long id) {
		logger.info("In find by id");
		Role role = null;
		try (Session session = HibernateUtil.getSession()) {
			role = (Role) session.get(Role.class, id);
			logger.info(role);
		} catch (Exception e) {
			logger.error(e);
		}
		return Optional.of(role);
	}

	@Override
	public Optional<Role> findByName(String name) {
		logger.info("In find find by name");
		Role role = null;
		try (Session session = HibernateUtil.getSession()) {
			String sql = "select * from roles where role_name = ?";
			Query<Role> q = session.createNativeQuery(sql, Role.class);
			q.setParameter(1, name);
			role = q.list().get(0);
		}
		return Optional.of(role);
	}

	@Override
	public List<Role> findAll() {
		logger.info("In find all");
		List<Role> roles = new ArrayList<Role>();
		try (Session session = HibernateUtil.getSession()) {
			String sql = "select * from roles";
			Query<Role> q = session.createNativeQuery(sql, Role.class);
			roles = q.list();
		}
		logger.info(roles);
		return roles;
	}

	@Override
	public void save(Role role) {
		logger.info("In save");
		try (Session session = HibernateUtil.getSession()) {
			Transaction tx = session.beginTransaction();
			session.persist(role);
			tx.commit();
			session.close();
		}
	}

	@Override
	public void update(Role role) {
		logger.info("In update");
		try (Session session = HibernateUtil.getSession()) {
			Transaction tx = session.beginTransaction();
			session.update(role);
			tx.commit();
		}
	}
	
	@Override
	public void deleteById(Long id) {
		logger.info("In delete");
		try(Session s = HibernateUtil.getSession()){
			Transaction tx = s.beginTransaction();
			s.delete(new Role(id, null));
			tx.commit();
		}
	}

	

}