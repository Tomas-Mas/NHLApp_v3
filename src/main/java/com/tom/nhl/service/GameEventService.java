package com.tom.nhl.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tom.nhl.dto.GamePeriodKeyEventsDTO;
import com.tom.nhl.entity.GameEvent;
import com.tom.nhl.mapper.GameEventMapper;
import com.tom.nhl.repository.GameEventRepository;

@Component
public class GameEventService {
	
	private final GameEventRepository gameEventRepo;
	
	private final GameEventMapper gameEventMapper;
	
	public GameEventService(GameEventRepository gameEventRepo, GameEventMapper gameEventMapper) {
		this.gameEventRepo = gameEventRepo;
		this.gameEventMapper = gameEventMapper;
	}
	
	public List<GamePeriodKeyEventsDTO> getKeyEventsByGame(int gameId) {
		List<GameEvent> events = gameEventRepo.getKeyEventsByGame(gameId);
		return gameEventMapper.toGamePeriodKeyEvents(events);
	}

}
