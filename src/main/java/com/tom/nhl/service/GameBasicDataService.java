package com.tom.nhl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tom.nhl.dto.GameBasicDataDTO;
import com.tom.nhl.entity.view.GameBasicData;
import com.tom.nhl.mapper.GameBasicDataMapper;
import com.tom.nhl.repository.GameBasicDataRepository;

@Service
public class GameBasicDataService {
	
	private final GameBasicDataRepository gameBasicDataRepo;
	
	private final GameBasicDataMapper gameMapper;
	
	public GameBasicDataService(GameBasicDataRepository gameBasicDataRepo, GameBasicDataMapper gameMapper) {
		this.gameBasicDataRepo = gameBasicDataRepo;
		this.gameMapper = gameMapper;
	}
	
	public List<GameBasicDataDTO> getBySeasonWithPeriodGoals(int season) {
		List<GameBasicData> games = gameBasicDataRepo.getBySeasonWithPeriodGoals(season);
		return gameMapper.toGameBasicDataWithPeriodGoalsDTO(games);
	}
	
	public List<GameBasicDataDTO> getPlayoffGamesBySeason(int season) {
		List<GameBasicData> games = gameBasicDataRepo.getPlayoffGamesBySeason(season);
		return gameMapper.toGameBasicDataDTO(games);
	}
}
