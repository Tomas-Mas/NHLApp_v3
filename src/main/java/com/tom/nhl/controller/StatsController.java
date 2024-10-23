package com.tom.nhl.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tom.nhl.enums.RegulationScope;
import com.tom.nhl.enums.SeasonScope;
import com.tom.nhl.service.PlayoffSpiderService;
import com.tom.nhl.service.TeamStatsService;

@Controller
@RequestMapping("/c/stats")
public class StatsController {
	
	//TODO temporary constants - they will be replaced by user settings
	private static final SeasonScope SIDEBAR_STATS_DEFAULT_SEASON_SCOPE = SeasonScope.REGULATION;
	private static final RegulationScope TEAM_STANDINGS_DEFAULT_REGULATION_SCOPE = RegulationScope.OVERALL;
	
	private final PlayoffSpiderService playoffSpiderService;
	private final TeamStatsService teamStatsService;
	
	public StatsController(PlayoffSpiderService playoffSpiderService, TeamStatsService teamStatsService) {
		this.playoffSpiderService = playoffSpiderService;
		this.teamStatsService = teamStatsService;
	}
	
	@GetMapping(value = {"/sidebarStats/{season}", "/sidebarStatsSeasonScope/{season}/{seasonScope}"})
	public ModelAndView getSidebarStats(@PathVariable int season, @PathVariable(required = false) Optional<String> seasonScope, ModelAndView model) {
		SeasonScope scope = SeasonScope.valueOfType(seasonScope.orElse(SIDEBAR_STATS_DEFAULT_SEASON_SCOPE.getType()));
		model.addObject("seasonScope", scope);
		
		if(scope == SeasonScope.PLAYOFF) {
			model.addObject("playoff", playoffSpiderService.getBySeason(season));
			model.setViewName("components/sidebar-playoff-spiders");
		} else if(scope == SeasonScope.REGULATION) {
			model.addObject("standings", teamStatsService.getStandingsBySeasonAndRegulationScope(season, TEAM_STANDINGS_DEFAULT_REGULATION_SCOPE));
			model.addObject("regulationScope", TEAM_STANDINGS_DEFAULT_REGULATION_SCOPE);
			model.setViewName("components/sidebar-stats-standings");
		}
		return model;
	}
	
	@GetMapping(value = "/sidebarStatsRegulationScope/{season}/{regulationScope}")
	public ModelAndView getSidebarStatsByRegulationScope(@PathVariable int season, @PathVariable String regulationScope, ModelAndView model) {
		RegulationScope scope = RegulationScope.valueOfType(regulationScope);
		model.addObject("seasonScope", SeasonScope.REGULATION);
		model.addObject("regulationScope", scope);
		model.addObject("standings", teamStatsService.getStandingsBySeasonAndRegulationScope(season, scope));
		model.setViewName("components/sidebar-stats-standings");
		return model;
	}
}
