package com.chenjw.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoFirstController {
	
	@GetMapping("/hello")
	public String hello() {
		return "hello spring security!!";
	}
}
