package com.tom.nhl.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("nhl")
public class TestController {

	@GetMapping("/home")
	public ModelAndView homePage(HttpSession session, Principal principal) {
		ModelAndView model = new ModelAndView();
		
		/*if(principal != null) {
			System.out.println("principal: " + principal.toString());
		}*/
		
		session.setAttribute("clicks", session.getAttribute("clicks") != null ? (Integer) session.getAttribute("clicks") + 1 : 0);
		
		/*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.toString());*/
		
		//model.addObject("user", auth != null ? auth.getName() : "Unlogged user");
		model.addObject("user", principal.getName());
		model.addObject("clicks", session.getAttribute("clicks"));
		
		model.setViewName("test-views/home");
		return model;
	}
}
