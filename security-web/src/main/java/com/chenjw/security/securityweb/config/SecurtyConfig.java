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
        	.and()
        	.authorizeRequests()
        	.anyRequest()
        	.authenticated();
    }
 
//    /**
//     * 认证信息管理
//     * spring5中摒弃了原有的密码存储格式，官方把spring security的密码存储格式改了
//     *
//     * @param auth
//     * @throws Exception
//     */
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication() //认证信息存储到内存中
//                .passwordEncoder(passwordEncoder())
//                .withUser("user").password(passwordEncoder().encode("123456")).roles("ADMIN");
//    }    
}