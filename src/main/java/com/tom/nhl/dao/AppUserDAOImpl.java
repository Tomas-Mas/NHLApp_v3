package com.tom.nhl.dao;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.tom.nhl.entity.AppUser;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

@Repository("oracleRepo")
public class AppUserDAOImpl implements AppUserDAO {
	
	@PersistenceContext
	private final EntityManager entityManager;
	
	public AppUserDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Optional<AppUser> findUserWithRoleByUsername(String username) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<AppUser> query = cb.createQuery(AppUser.class);
		
		Root<AppUser> userRoot = query.from(AppUser.class);
		userRoot.fetch("role", JoinType.INNER);
		
		query.select(userRoot)
				.where(cb.equal(userRoot.get("username"), username));
		
		AppUser appUser;
		try {
			appUser = entityManager.createQuery(query).getSingleResult();
		} catch (NoResultException ex) {
			appUser = null;
		}
		
		return Optional.ofNullable(appUser);
	}
}
