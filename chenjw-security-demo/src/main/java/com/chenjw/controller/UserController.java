package com.chenjw.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chenjw.dto.User;
import com.chenjw.exception.UserNotExistException;
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
			u.setId("1");
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
			u.setId("1");
		return u;
	}
	
	@GetMapping("/user/{id:\\d+}")
	@JsonView(User.userDatailView.class)
	public User userInfoById(@PathVariable String id){
		
			User u = new User();
			u.setUserName("aaaWith1" );
			u.setPassword("123");	
			u.setId("1");
		return u;
	}
	
	@GetMapping("/userException/{id:\\d+}")
	@JsonView(User.userDatailView.class)
	public User userException(@PathVariable String id){
		throw new UserNotExistException("123");			
	}
	
	@PostMapping("/user")
	public User createUser(@Valid @RequestBody User u, BindingResult erros) {
		if (erros.hasErrors()) {
			//如果验证有错误则进行输出
			erros.getAllErrors().stream().forEach(error->System.out.println(error.getDefaultMessage()));
		}		
		u.setId("1");
		return u;
	}
	
	@PutMapping("/user/{id:\\d+}")
	public User updateUser(@Valid @RequestBody User u, BindingResult erros, @PathVariable String id) {
		if (erros.hasErrors()) {
			//如果验证有错误则进行输出
			erros.getAllErrors().stream().forEach(error->{
//				FieldError fieldError = (FieldError) error;
//				String errorMessage = fieldError.getField() + "  " +  error.getDefaultMessage();
				String errorMessage = error.getDefaultMessage();
				System.out.println(errorMessage);				
			});
		}
		u.setId(id);
		return u;
	}
}
