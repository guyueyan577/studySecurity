package com.chenjw.security.securityweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurtyConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
    /**
     * 自定义配置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() //基于Form表单登录验证
        	.loginPage("/authentication/request")  //自定义登录页
        	.loginProcessingUrl("/login")  //表单提交相匹配，否则无法登录
        	.and()
        	.authorizeRequests()
        	.antMatchers("/mylogin.html","/authentication/request").permitAll()   //防止登录页也需要进行登录验证，添加这个说明登录页不进行验证
        	.anyRequest()
        	.authenticated()
        	.and()
        	.csrf().disable();	//	关闭spring security的跨站伪造防护功能
    }  
}