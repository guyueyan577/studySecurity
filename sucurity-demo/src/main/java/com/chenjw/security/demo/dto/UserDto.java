package com.chenjw.security.demo.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import com.chenjw.security.demo.validator.MyConstraint;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModelProperty;

public class UserDto {

    public interface userSimpleView {
    };
    public interface userDatailView extends userSimpleView {
    };

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    private String userName;
    @MyConstraint
    @ApiModelProperty(value = "用户密码")
    private String password;
    @ApiModelProperty(value = "用户id")
    private String id;
    @Past(message = "生日必须是过去的时间")
    @ApiModelProperty(value = "用户生日")
    private Date birthday;

    @JsonView(userSimpleView.class)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonView(userDatailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(userSimpleView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonView(userSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


}
