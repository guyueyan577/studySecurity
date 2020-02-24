package com.chenjw.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chenjw.dto.User;

@RestController
public class UserController {
	@GetMapping("/user")
	public List<User> query(){
		List<User> result = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			User u = new User();
			u.setUserName("aaa" + i);
			u.setPassword("123");
			
			result.add(u);
		}		
		return result;
	}
	
	@GetMapping("/userByName")
	public List<User> query(@RequestParam String userName){
		List<User> result = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			User u = new User();
			u.setUserName("aaa" + i);
			u.setPassword("123");
			
			result.add(u);
		}		
		return result;
	}
}
