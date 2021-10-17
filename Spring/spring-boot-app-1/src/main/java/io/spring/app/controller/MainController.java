package io.spring.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@Value("${welcome.message}")
	public String welcome;
	
	// get
	@GetMapping(path = "/welcome", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> welcomeMessage() {
		return new ResponseEntity<String>(welcome, HttpStatus.OK);
	}
	// post
	// put
	// delete
}
