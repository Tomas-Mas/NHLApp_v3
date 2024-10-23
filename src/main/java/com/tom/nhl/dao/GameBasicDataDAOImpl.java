package com.tom.nhl.dao;

import java.util.List;

import com.tom.nhl.entity.Game;
import com.tom.nhl.entity.view.GameBasicData;
import com.tom.nhl.enums.SeasonScope;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

public class GameBasicDataDAOImpl implements GameBasicDataDAO {
	
	//TODO pagination
	private static final int currentPage = 0;
	
	//TODO this is temporary, will be replaced by user settings in near future
	private static final int pageSize = 20;
	
	@PersistenceContext
	private final EntityManager entityManager;
	
	public GameBasicDataDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<GameBasicData> getBySeasonWithPeriodGoals(int season) {
		List<GameBasicData> gameList = getBySeason(season);
		return getByGamesWithGoalsPerPeriod(gameList);
	}
	
	@Override
	public List<GameBasicData> getPlayoffGamesBySeason(int season) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<GameBasicData> query = cb.createQuery(GameBasicData.class);
		Root<GameBasicData> gameViewRoot = query.from(GameBasicData.class);
		
		Subquery<Integer> sqFilter = query.subquery(Integer.class);
		Root<Game> sqGameRoot = sqFilter.from(Game.class);
		sqFilter.select(sqGameRoot.<Integer>get("id"))
				.where(cb.and(cb.equal(sqGameRoot.get("season"), season), cb.equal(sqGameRoot.get("gameType"), SeasonScope.PLAYOFF.getValue())));
		
		query.select(gameViewRoot)
				.where(cb.in(gameViewRoot.get("id")).value(sqFilter))
				.orderBy(cb.asc(gameViewRoot.get("gameDate")));
		List<GameBasicData> playoffGames = entityManager.createQuery(query).getResultList();
		
		return playoffGames;
	}
	
	private List<GameBasicData> getBySeason(int season) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<GameBasicData> query = cb.createQuery(GameBasicData.class);
		Root<GameBasicData> gameViewRoot = query.from(GameBasicData.class);
		
		Subquery<Integer> sqSeasonFilter = query.subquery(Integer.class);
		Root<Game> sqGameRoot = sqSeasonFilter.from(Game.class);
		sqSeasonFilter.select(sqGameRoot.<Integer>get("id"))
				.where(cb.equal(sqGameRoot.get("season"), season));
		
		query.select(gameViewRoot)
				.where(cb.in(gameViewRoot.get("id")).value(sqSeasonFilter))
				.orderBy(cb.desc(gameViewRoot.get("gameDate")));
		List<GameBasicData> gameList = entityManager.createQuery(query)
				.setFirstResult(currentPage * pageSize)
				.setMaxResults(pageSize)
				.getResultList();
		
		return gameList;
	}
	
	private List<GameBasicData> getByGamesWithGoalsPerPeriod(List<GameBasicData> games) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<GameBasicData> finalQuery = cb.createQuery(GameBasicData.class);
		Root<GameBasicData> gameRoot = finalQuery.from(GameBasicData.class);
		gameRoot.fetch("goalsPerPeriod");
		finalQuery.select(gameRoot)
				.distinct(true)
				.where(gameRoot.in(games))
				.orderBy(cb.desc(gameRoot.get("gameDate")));
		games = entityManager.createQuery(finalQuery).getResultList();
		
		return games;
	}
	
}
