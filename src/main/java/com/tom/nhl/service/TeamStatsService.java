package com.tom.nhl.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tom.nhl.dto.TeamStandingsDTO;
import com.tom.nhl.entity.view.RegulationTeamStats;
import com.tom.nhl.enums.RegulationScope;
import com.tom.nhl.mapper.TeamStandingsMapper;
import com.tom.nhl.repository.RegulationTeamStatsRepository;

@Component
public class TeamStatsService {
	
	private final RegulationTeamStatsRepository regulationTeamStatsRepo;
	
	private final TeamStandingsMapper teamStandingsMapper;
	
	public TeamStatsService(RegulationTeamStatsRepository regulationTeamStatsRepo, TeamStandingsMapper teamStandingsMapper) {
		this.regulationTeamStatsRepo = regulationTeamStatsRepo;
		this.teamStandingsMapper = teamStandingsMapper;
	}
	
	public TeamStandingsDTO getStandingsBySeasonAndRegulationScope(int season, RegulationScope regulationScope) {
		List<RegulationTeamStats> teamStats = regulationTeamStatsRepo.getBySeason(season);
		return teamStandingsMapper.toTeamStandingsDTO(season, teamStats, regulationScope);
	}

}
