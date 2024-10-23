package com.tom.nhl.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tom.nhl.dto.GameBasicDataDTO;
import com.tom.nhl.dto.PlayoffSpiderDTO;
import com.tom.nhl.dto.TeamStandingsDTO;
import com.tom.nhl.enums.RegulationScope;
import com.tom.nhl.mapper.PlayoffSpiderMapper;

@Component
public class PlayoffSpiderService {
	
	private final TeamStatsService teamStatsService;
	private final GameBasicDataService gameBasicDataService;
	
	private final PlayoffSpiderMapper playoffSpiderMapper;
	
	public PlayoffSpiderService(TeamStatsService teamStatsService, GameBasicDataService gameBasicDataService, PlayoffSpiderMapper playoffSpiderMapper) {
		this.teamStatsService = teamStatsService;
		this.gameBasicDataService = gameBasicDataService;
		this.playoffSpiderMapper = playoffSpiderMapper;
	}

	public PlayoffSpiderDTO getBySeason(int season) {
		TeamStandingsDTO teamStandings = teamStatsService.getStandingsBySeasonAndRegulationScope(season, RegulationScope.OVERALL);
		List<GameBasicDataDTO> playoffGames = gameBasicDataService.getPlayoffGamesBySeason(season);
		return playoffSpiderMapper.toPlayoffSpider(teamStandings, playoffGames);
	}
}
