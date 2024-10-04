package com.tom.nhl.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandlerController {

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception exception) {
		ModelAndView model = new ModelAndView();
		exception.printStackTrace();
		model.addObject("exceptionMessage", exception.getMessage());
		model.setViewName("errors/global-error");
		return model;
	}
}
