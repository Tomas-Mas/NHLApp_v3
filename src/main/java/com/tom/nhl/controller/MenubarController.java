package com.tom.nhl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tom.nhl.dto.SeasonManagerDTO;
import com.tom.nhl.service.GameService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("c/menubar")
public class MenubarController {
	
	private final GameService gameService;
	
	public MenubarController(GameService gameService) {
		this.gameService = gameService;
	}
	
	@GetMapping("/{season}")
	public ModelAndView showMenubar(
			@PathVariable(value = "season") int season, 
			@CookieValue(value = "season", defaultValue = "0") Integer seasonCookie,
			HttpServletResponse response,
			ModelAndView model) {
		SeasonManagerDTO seasonManager = gameService.getSeasonManager();
		
		if(!seasonManager.isSeasonValid(season)) {
			//TODO invalid season exception
		}
		
		if(season != seasonCookie) {
			addCookie("season", String.valueOf(season), response);
		}
		
		model.addObject("seasons", seasonManager.getSeasons());
		model.addObject("selectedSeason", season);
		model.setViewName("components/menu-bar");
		return model;
	}
	
	private void addCookie(String name, String value, HttpServletResponse response) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(60 * 60 * 24 * 30);
		cookie.setPath("/NHL");
		response.addCookie(cookie);
	}

}
