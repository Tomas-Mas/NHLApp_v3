package com.tom.nhl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tom.nhl.service.GameBasicDataService;
import com.tom.nhl.service.GameEventService;

@Controller
@RequestMapping("/c/game")
public class GameController {
	
	private final GameBasicDataService gameBasicDataService;
	private final GameEventService gameEventService;
	
	public GameController(GameBasicDataService gameBasicDataService, GameEventService gameEventService) {
		this.gameBasicDataService = gameBasicDataService;
		this.gameEventService = gameEventService;
	}
	
	@GetMapping("/list/{season}")
	public ModelAndView getGamesList(@PathVariable int season, ModelAndView model) {
		model.addObject("games", gameBasicDataService.getBySeasonWithPeriodGoals(season));
		model.setViewName("components/main-page-games-tbl");
		return model;
	}
	
	@GetMapping("/keyEvents/{gameId}")
	public ModelAndView getKeyEvents(@PathVariable int gameId, ModelAndView model) {
		model.addObject("gameEvents", gameEventService.getKeyEventsByGame(gameId));
		model.setViewName("components/game-keyevents-td");
		return model;
	}

}
