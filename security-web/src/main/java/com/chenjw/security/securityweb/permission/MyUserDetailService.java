/** @Description: TODO
 * @author: 陈建伟
 * @date: 2020-03-13
 */
package com.chenjw.security.securityweb.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.chenjw.security.securityweb.model.UserModel;

import lombok.extern.slf4j.Slf4j;

/** @Description: TODO
 * @author: 陈建伟
 * @date: 2020-03-13 
 */
@Component
@Slf4j
public class MyUserDetailService implements UserDetailsService {
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("输入的用户名是：" + username);
		UserModel user = new UserModel();
		user.setUserName(username);
		user.setAge(18);
		user.setPassword("123456");
		user.setAccountNonExpired(true);  //账号没有过期
		user.setAccountNonLocked(true);   //账号没有锁定
		user.setCredentialsNonExpired(true); //密码没有过期
		user.setEnabled(true);  //账号是否可用（没有被删除）
		
		log.info("通过业务逻辑得到的用户信息为：" + JSONObject.toJSONString(user));
		String authorityList = "admin,user,root";
//		UserDetails userDetail = new User(user.getUserName(), user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(authorityList));
		
		String encoderPassword = passwordEncoder.encode(user.getPassword());
		log.info("加密后的密码为：" + encoderPassword);
		UserDetails userDetail = new User(user.getUserName(), encoderPassword, 
				user.isEnabled(), user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isAccountNonLocked(),
				AuthorityUtils.commaSeparatedStringToAuthorityList(authorityList));
		
		return userDetail; 
	}
	
//	@Bean
//	public static PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}

}
