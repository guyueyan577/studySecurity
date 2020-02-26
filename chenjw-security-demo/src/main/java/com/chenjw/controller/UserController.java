package com.chenjw.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chenjw.dto.User;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class UserController {
	
	@GetMapping("/user")
	@JsonView(User.userSimpleView.class)
	public List<User> query(@PageableDefault(page = 2, size = 17, sort = "username,asc") Pageable pageable){
		System.out.println(pageable);
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
	@JsonView(User.userDatailView.class)
	public User query(@RequestParam String userName){

			User u = new User();
			u.setUserName("aaa");
			u.setPassword("123");
			
			
		return u;
	}
	
	@GetMapping("/user/{id:\\d+}")
	@JsonView(User.userDatailView.class)
	public User userInfoById(@PathVariable String id){
		
			User u = new User();
			u.setUserName("aaaWith1" );
			u.setPassword("1");
			
			
		return u;
	}
}
