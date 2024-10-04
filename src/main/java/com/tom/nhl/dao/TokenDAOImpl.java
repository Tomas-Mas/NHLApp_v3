package com.tom.nhl.dao;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.tom.nhl.entity.Token;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

@Component
public class TokenDAOImpl implements TokenDAO {

	@PersistenceContext
	private final EntityManager entityManager;
	
	public TokenDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public Optional<Token> findByToken(String token) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Token> query = cb.createQuery(Token.class);
		
		Root<Token> tokenRoot = query.from(Token.class);
		tokenRoot.fetch("user", JoinType.INNER);
		
		query.select(tokenRoot)
				.where(cb.equal(tokenRoot.get("token"), token));
		
		Token tokenOutput;
		try {
			tokenOutput = entityManager.createQuery(query).getSingleResult();
		} catch (NoResultException ex) {
			tokenOutput = null;
		}
		
		return Optional.ofNullable(tokenOutput);
	}
}
