package com.exception.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	@GetMapping
	public String greetings() {
		return "Welcome to Spring Boot Application!";
	}
	
	@GetMapping(value= "/{name}")
	public String greetings(@PathVariable String name) {
		return "Welcome to Spring Boot Application, " + name;
	}
}
