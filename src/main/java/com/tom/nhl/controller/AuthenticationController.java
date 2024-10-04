package com.tom.nhl.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tom.nhl.dto.LoginFormDTO;
import com.tom.nhl.dto.RegistrationFormDTO;
import com.tom.nhl.service.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("auth")
public class AuthenticationController {
	
	private final AuthenticationService authService;
	
	public AuthenticationController(AuthenticationService authenticationService) {
		this.authService = authenticationService;
	}

	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		model.addAttribute("userRegistrationForm", new RegistrationFormDTO());
		return "test-views/register-user";
	}
	
	@PostMapping("/register-submit")
	public ModelAndView registerUser(
			@ModelAttribute("userRegistrationForm") @Valid RegistrationFormDTO userRegistrationForm,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		
		if(result.hasErrors()) {
			model.setViewName("test-views/register-user");
			return model;
		}
		
		authService.registerUser(userRegistrationForm);
		
		model.setViewName("redirect:/nhl/home");
		return model;
	}
	
	@GetMapping("/login")
	public ModelAndView showLoginPage(Principal principal) {
		ModelAndView model = new ModelAndView();
		model.addObject("user", principal.getName());
		model.addObject("loginForm", new LoginFormDTO());
		model.setViewName("test-views/login");
		return model;
	}
	
	@PostMapping("/login-submit")
	public ModelAndView loginUser(
			@ModelAttribute("loginForm") @Valid LoginFormDTO loginForm,
			BindingResult result,
			HttpServletRequest request,
			HttpSession session) {
		ModelAndView model = new ModelAndView();
		
		if(result.hasErrors()) {
			model.setViewName("test-views/login");
			return model;
		}
		
		authService.loginUser(loginForm, request, session);
		
		model.setViewName("redirect:/nhl/home");
		return model;
	}
	
	//logout handled in security filter chain
	/*@PostMapping("/logout")
	public ModelAndView logoutUser(
			HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		
		new SecurityContextLogoutHandler().logout(request, response, null);
		
		authService.loginGuest(request, request.getSession());
		
		model.setViewName("redirect:/nhl/home");
		return model;
	}*/
	
	//for now user is automaticaly activated after submitting registration
	/*@GetMapping("/activate/{token}")
	public ModelAndView activateUser(@PathVariable String token) {
		ModelAndView model = new ModelAndView();
		
		authService.activateUser(token);
		
		model.setViewName("redirect:/nhl/home");
		return model;
	}*/
	
	
}
