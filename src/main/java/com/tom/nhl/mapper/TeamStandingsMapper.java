package com.tom.nhl.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tom.nhl.dto.TeamStandingsDTO;
import com.tom.nhl.dto.TeamStats;
import com.tom.nhl.entity.view.RegulationTeamStats;
import com.tom.nhl.enums.RegulationScope;
import com.tom.nhl.util.DefaultNullableValue;

@Component
public class TeamStandingsMapper {
	
	private static final int DEFAULT_POINT_PERCENTAGE_WHEN_ZERO_GAMES = 0;

	public TeamStandingsDTO toTeamStandingsDTO(int season, List<RegulationTeamStats> regulationTeamStats, RegulationScope statsType) {
		ArrayList<TeamStats> teamStats = new ArrayList<TeamStats>();
		for(RegulationTeamStats team : regulationTeamStats) {
			TeamStats stats = new TeamStats();
			stats.setTeamName(team.getTeamName());
			stats.setTeamAbbreviation(team.getTeamAbbreviation());
			stats.setTeamId(team.getId().getTeamId());
			stats.setConference(team.getConference());
			stats.setDivision(team.getDivision());
			stats.setSeason(team.getId().getSeason());

			if(statsType == RegulationScope.HOME) {
				stats.setGames(DefaultNullableValue.getInt(team.getHomeGames()));
				stats.setGoalsFor(DefaultNullableValue.getInt(team.getHomeGoalsFor()));
				stats.setGoalsAgainst(DefaultNullableValue.getInt(team.getHomeGoalsAgainst()));
				stats.setPoints(DefaultNullableValue.getInt(team.getHomePoints()));
				stats.setPointPercentage(team.getHomeGames() == null || team.getHomeGames() == 0 ? DEFAULT_POINT_PERCENTAGE_WHEN_ZERO_GAMES :
						(DefaultNullableValue.getInt(team.getHomePoints()) * 1000) / (DefaultNullableValue.getInt(team.getHomeGames()) * 2));
				stats.setRegWins(DefaultNullableValue.getInt(team.getHomeRegWins()));
				stats.setRegLoses(DefaultNullableValue.getInt(team.getHomeRegLoses()));
				stats.setOtWins(DefaultNullableValue.getInt(team.getHomeOtWins()));
				stats.setOtLoses(DefaultNullableValue.getInt(team.getHomeOtLoses()));
			} else if(statsType == RegulationScope.AWAY) {
				stats.setGames(DefaultNullableValue.getInt(team.getAwayGames()));
				stats.setGoalsFor(DefaultNullableValue.getInt(team.getAwayGoalsFor()));
				stats.setGoalsAgainst(DefaultNullableValue.getInt(team.getAwayGoalsAgainst()));
				stats.setPoints(DefaultNullableValue.getInt(team.getAwayPoints()));
				stats.setPointPercentage(team.getAwayGames() == null || team.getAwayGames() == 0 ? DEFAULT_POINT_PERCENTAGE_WHEN_ZERO_GAMES :
						(DefaultNullableValue.getInt(team.getAwayPoints()) * 1000) / (DefaultNullableValue.getInt(team.getAwayGames()) * 2));
				stats.setRegWins(DefaultNullableValue.getInt(team.getAwayRegWins()));
				stats.setRegLoses(DefaultNullableValue.getInt(team.getAwayRegLoses()));
				stats.setOtWins(DefaultNullableValue.getInt(team.getAwayOtWins()));
				stats.setOtLoses(DefaultNullableValue.getInt(team.getAwayOtLoses()));
			} else {
				stats.setGames(DefaultNullableValue.getInt(team.getHomeGames())
						+ DefaultNullableValue.getInt(team.getAwayGames()));
				stats.setGoalsFor(DefaultNullableValue.getInt(team.getHomeGoalsFor())
						+ DefaultNullableValue.getInt(team.getAwayGoalsFor()));
				stats.setGoalsAgainst(DefaultNullableValue.getInt(team.getHomeGoalsAgainst())
						+ DefaultNullableValue.getInt(team.getAwayGoalsAgainst()));
				stats.setPoints(DefaultNullableValue.getInt(team.getHomePoints())
						+ DefaultNullableValue.getInt(team.getAwayPoints()));
				stats.setPointPercentage(DefaultNullableValue.getInt(team.getHomeGames()) + DefaultNullableValue.getInt(team.getAwayGames()) == 0 ?
						DEFAULT_POINT_PERCENTAGE_WHEN_ZERO_GAMES :
						((DefaultNullableValue.getInt(team.getHomePoints()) + DefaultNullableValue.getInt(team.getAwayPoints())) * 1000)
						/ ((DefaultNullableValue.getInt(team.getHomeGames()) + DefaultNullableValue.getInt(team.getAwayGames())) * 2));
				stats.setRegWins(DefaultNullableValue.getInt(team.getHomeRegWins())
						+ DefaultNullableValue.getInt(team.getAwayRegWins()));
				stats.setRegLoses(DefaultNullableValue.getInt(team.getHomeRegLoses())
						+ DefaultNullableValue.getInt(team.getAwayRegLoses()));
				stats.setOtWins(DefaultNullableValue.getInt(team.getHomeOtWins())
						+ DefaultNullableValue.getInt(team.getAwayOtWins()));
				stats.setOtLoses(DefaultNullableValue.getInt(team.getHomeOtLoses())
						+ DefaultNullableValue.getInt(team.getAwayOtLoses()));
			}
			teamStats.add(stats);
		}
		return new TeamStandingsDTO(season, teamStats);
	}
}
