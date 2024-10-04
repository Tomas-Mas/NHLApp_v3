package com.tom.nhl.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin")
public class AdminController {

	@GetMapping("/")
	public ModelAndView loadAdminPage(ModelAndView model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		model.addObject("user", auth.getName());
		model.addObject("roles", auth.getAuthorities().toString());
		
		model.setViewName("test-views/admin");
		
		return model;
	}
}
