package com.tom.nhl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tom.nhl.service.GameService;

@Controller
@RequestMapping("home")
public class MainPageController {
	
	private final GameService gameService;
	
	public MainPageController(GameService gameService) {
		this.gameService = gameService;
	}
	
	@GetMapping({"", "/"})
	public String processSeasonlessMainPageUrl(@CookieValue(value = "season", defaultValue = "0") Integer seasonCookie) {
		return "redirect:/home/" + (seasonCookie == 0 ? gameService.getSeasonManager().getDefaultSeason() : seasonCookie);
	}
	
	@GetMapping("/{season}")
	public String showMainPage() {
		return "pages/main-page";
	}
}
