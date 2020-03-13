package com.chenjw.security.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chenjw.security.demo.dto.UserDto;
import com.chenjw.security.demo.exception.UserNotExistException;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class UserController {

    @GetMapping("/user")
    @JsonView(UserDto.userSimpleView.class)
    @ApiOperation(value = "用户查询服务")
    public List<UserDto> query() {
        List<UserDto> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            UserDto u = new UserDto();
            u.setUserName("aaa" + i);
            u.setPassword("123");
            u.setId("1");
            result.add(u);
        }
        return result;
    }

    @GetMapping("/userByName")
    @JsonView(UserDto.userDatailView.class)
    @ApiOperation(value = "根据用户名查询详细信息")
    public UserDto query(@ApiParam(value = "用户名") @RequestParam String userName) {

        UserDto u = new UserDto();
        u.setUserName("aaa");
        u.setPassword("123");
        u.setId("1");
        return u;
    }

    @GetMapping("/user/{id:\\d+}")
    @JsonView(UserDto.userDatailView.class)
    @ApiOperation(value = "按ID查询用户信息")
    public UserDto userInfoById(@ApiParam(value = "用户ID") @PathVariable String id) {

        UserDto u = new UserDto();
        u.setUserName("aaaWith1");
        u.setPassword("123");
        u.setId("1");
        return u;
    }

    @GetMapping("/userException/{id:\\d+}")
    @JsonView(UserDto.userDatailView.class)
    @ApiOperation(value = "按ID查询用户信息-用户不存在时返回错误提示")
    public UserDto userException(@ApiParam(value = "用户ID") @PathVariable String id) {
        throw new UserNotExistException("123");
    }

    @PostMapping("/user")
    @ApiOperation(value = "创建用户")
    public UserDto createUser(@Valid @RequestBody UserDto u, BindingResult erros) {
        if (erros.hasErrors()) {
            //如果验证有错误则进行输出
            erros.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        u.setId("1");
        return u;
    }

    @PutMapping("/user/{id:\\d+}")
    @ApiOperation(value = "更新用户信息")
    public UserDto updateUser(@Valid @RequestBody UserDto u, BindingResult erros, @ApiParam(value = "用户ID") @PathVariable String id) {
        if (erros.hasErrors()) {
            //如果验证有错误则进行输出
            erros.getAllErrors().stream().forEach(error -> {
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
