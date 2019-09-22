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

import com.revature.gamesgalore.dao.Role;
import com.revature.gamesgalore.repositories.RoleRepository;
import com.revature.gamesgalore.util.HibernateUtil;

public class RoleRepositoryImpl implements RoleRepository {

	private static Logger logger = LogManager.getLogger();

	@Override
	public Optional<Role> findById(Long roleId) {
		logger.info("In find by id");
		Role role = null;
		try (Session session = HibernateUtil.getSession()) {
			role = (Role) session.get(Role.class, roleId);
			logger.info(role);
		} catch (Exception e) {
			logger.error(e);
		}
		return Optional.ofNullable(role);
	}

	@Override
	public Optional<Role> findByName(String roleName) {
		logger.info("In find find by name");
		Role role = null;
		try (Session session = HibernateUtil.getSession()) {
			String sql = "select * from roles where role_name = ?";
			Query<Role> q = session.createNativeQuery(sql, Role.class);
			q.setParameter(1, roleName);
			List<Role> roles = q.list();
			if(!roles.isEmpty()) {
				role = q.list().get(0);
			}
		}
		return Optional.ofNullable(role);
	}

	@Override
	public Collection<Role> findByQuery(String query) {
		logger.info("In find by query");
		Collection<Role> roles = new ArrayList<Role>();
		try (Session session = HibernateUtil.getSession()) {
			Query<Role> q = session.createNativeQuery(query, Role.class);
			roles = q.list();
		}
		logger.info(roles);
		return roles;
	}
	
	@Override
	public Collection<Role> findAll() {
		logger.info("In find all");
		Collection<Role> roles = new ArrayList<Role>();
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
	public void deleteById(Long roleId) {
		logger.info("In delete");
		try(Session s = HibernateUtil.getSession()){
			Transaction tx = s.beginTransaction();
			s.delete(new Role(roleId));
			tx.commit();
		}
	}

}