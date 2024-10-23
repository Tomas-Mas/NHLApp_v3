package com.tom.nhl.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tom.nhl.entity.Game;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Component
public class GameDAOImpl implements GameDAO {
	
	@PersistenceContext
	private final EntityManager entityManager;
	
	public GameDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Integer> findAllSeasons() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Integer> query = cb.createQuery(Integer.class);
		
		Root<Game> gameRoot = query.from(Game.class);
		
		query.select(gameRoot.get("season"))
				.distinct(true)
				.orderBy(cb.desc(gameRoot.get("season")));
		
		List<Integer> seasons = entityManager.createQuery(query).getResultList();
		return seasons;
	}
	
}
