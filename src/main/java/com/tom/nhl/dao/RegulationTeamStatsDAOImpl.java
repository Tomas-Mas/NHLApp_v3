package com.tom.nhl.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tom.nhl.entity.view.RegulationTeamStats;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Component
public class RegulationTeamStatsDAOImpl implements RegulationTeamStatsDAO {
	
	@PersistenceContext
	private final EntityManager entityManager;
	
	public RegulationTeamStatsDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<RegulationTeamStats> getBySeason(int season) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<RegulationTeamStats> query = cb.createQuery(RegulationTeamStats.class);
		Root<RegulationTeamStats> statsRoot = query.from(RegulationTeamStats.class);
		
		Predicate seasonPredicate = cb.equal(statsRoot.get("id").get("season"), season);
		
		query.select(statsRoot)
				.where(seasonPredicate);
		
		List<RegulationTeamStats> stats = entityManager.createQuery(query).getResultList();
		return stats;
	}

}
