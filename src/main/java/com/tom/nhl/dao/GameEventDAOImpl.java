package com.tom.nhl.dao;

import java.util.Arrays;
import java.util.List;

import com.tom.nhl.entity.EventPlayer;
import com.tom.nhl.entity.GameEvent;
import com.tom.nhl.entity.Roster;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class GameEventDAOImpl implements GameEventDAO {
	
	@PersistenceContext
	private final EntityManager entityManager;
	
	public GameEventDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<GameEvent> getKeyEventsByGame(int gameId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<GameEvent> query = cb.createQuery(GameEvent.class);
		Root<GameEvent> eventRoot = query.from(GameEvent.class);
		eventRoot.fetch("game", JoinType.INNER);
		eventRoot.fetch("event", JoinType.INNER);
		Fetch<GameEvent,EventPlayer> eventPlayerFetch = eventRoot.fetch("players", JoinType.LEFT);
		Fetch<EventPlayer, Roster> rosterFetch = eventPlayerFetch.fetch("roster", JoinType.INNER);
		rosterFetch.fetch("player", JoinType.INNER);
		rosterFetch.fetch("team", JoinType.INNER);
		
		List<String> eventsNamesFilter = Arrays.asList("Goal", "Penalty");
		Predicate eventNamePredicate = eventRoot.get("event").get("name").in(eventsNamesFilter);
		Predicate gamePredicate = cb.equal(eventRoot.get("game").get("id"), gameId);
		Predicate finalPredicate = cb.and(gamePredicate, eventNamePredicate);
		
		query.select(eventRoot)
				.distinct(true)
				.where(finalPredicate)
				.orderBy(cb.asc(eventRoot.get("periodNumber")), cb.asc(eventRoot.get("periodTime")), cb.asc(eventRoot.get("jsonId")));
		
		List<GameEvent> events = entityManager.createQuery(query)
				.getResultList();
		
		return events;
	}

}
