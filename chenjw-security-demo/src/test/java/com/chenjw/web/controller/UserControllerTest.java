/**
 * 
 */
package com.chenjw.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.chenjw.dto.User;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserControllerTest {

	@Autowired
	private WebApplicationContext wac;

	//伪造的MVC，不用启动tomcat
	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	/**
	 * @Description:   测试查询
	 * @author: chenjianwei     
	 * @date:   2020-02-24  
	 * @version V1.0
	 */
	@Test
	public void whenQuery() throws Exception {
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/user")
				.contentType(MediaType.APPLICATION_JSON_UTF8)) 
				.andExpect(MockMvcResultMatchers.status().isOk())  //期望返回的状态码
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))  //期望返回的集合中数据数量是3个
				.andReturn().getResponse().getContentAsString();  //获取返回结果的字符
		System.out.println(result);
	}
	
	/**
	 * @Description:    测试查询1（简化输入）
	 * (在属性配置中：java->editor->Content Assist->Favorites中添加偏好内容：MockMvcRequestBuilders和MockMvcResultMatchers)后，
	 * 可以省略这个类名，直接调用内部的静态方法
	 * @author: chenjianwei     
	 * @date:   2020-02-24  
	 * @version V1.0
	 */
	@Test
	public void whenQuery1() throws Exception {
		String result = mockMvc.perform(get("/user")
				.param("size", "15")
				.param("page", "3")
				.param("sort", "age,desc")
				.contentType(MediaType.APPLICATION_JSON_UTF8)) 
				.andExpect(status().isOk())  //期望返回的状态码
				.andExpect(jsonPath("$.length()").value(3))  //期望返回的集合中数据数量是3个
				.andReturn().getResponse().getContentAsString();  //获取返回结果的字符
		System.out.println(result);
	}
	
	/**
	 * @Description:    测试查询(带参数查询)
	 * (在属性配置中：java->editor->Content Assist->Favorites中添加偏好内容：MockMvcRequestBuilders和MockMvcResultMatchers)后，
	 * 可以省略这个类名，直接调用内部的静态方法
	 * @author: chenjianwei     
	 * @date:   2020-02-24  
	 * @version V1.0
	 */
	@Test
	public void whenQueryByName() throws Exception {
		String result = mockMvc.perform(get("/userByName")
				.param("userName", "ch")  //调用时使用参数
				.contentType(MediaType.APPLICATION_JSON_UTF8)) 
				.andExpect(status().isOk())  //期望返回的状态码
				.andReturn().getResponse().getContentAsString();  //获取返回结果的字符
		System.out.println(result);
	}
	
	/**
	 * @Description: 获取id为1的用户的信息  
	 * @author: chenjianwei     
	 * @date:   2020-02-26  
	 * @version V1.0
	 */
	@Test
	public void whenGetInfo() throws Exception {
		String result = mockMvc.perform(get("/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.userName").value("aaaWith1"))
				.andReturn().getResponse().getContentAsString();		
		System.out.println(result);
	}

	/**
	 * @Description:  首先通过正则表达式验证参数   
	 * @author: chenjianwei     
	 * @date:   2020-02-27  
	 * @version V1.0
	 */
	@Test
	public void whenGetInfoByString() throws Exception {
		//测试当传递的参数是非数字时，则返回错误信息
		mockMvc.perform(get("/user/a")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().is4xxClientError());
	}
	
	
	@Test
	public void whenCreateUser() throws Exception {
		User u = new User();
		u.setUserName("chen1");
//		u.setPassword("123");
		u.setBirthday(new Date());
		String content = JSONObject.toJSONString(u);
		String result = mockMvc.perform((post("/user")).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("1"))
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/////////////////// 教程提供源码 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void whenUploadSuccess() throws Exception {
		String result = mockMvc.perform(fileUpload("/file")
				.file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "hello upload".getBytes("UTF-8"))))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	

	@Test
	public void whenQuerySuccess() throws Exception {
		String result = mockMvc.perform(
				get("/user").param("username", "jojo").param("age", "18").param("ageTo", "60").param("xxx", "yyy")
						// .param("size", "15")
						// .param("page", "3")
						// .param("sort", "age,desc")
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(3))
				.andReturn().getResponse().getContentAsString();
		
		System.out.println(result);
	}

	@Test
	public void whenGetInfoSuccess() throws Exception {
		String result = mockMvc.perform(get("/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.username").value("tom"))
				.andReturn().getResponse().getContentAsString();
		
		System.out.println(result);
	}
	
	@Test
	public void whenGetInfoFail() throws Exception {
		mockMvc.perform(get("/user/a")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void whenCreateSuccess() throws Exception {
		
		Date date = new Date();
		System.out.println(date.getTime());
		String content = "{\"username\":\"tom\",\"password\":null,\"birthday\":"+date.getTime()+"}";
		String reuslt = mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("1"))
				.andReturn().getResponse().getContentAsString();
		
		System.out.println(reuslt);
	}
	
	@Test
	public void whenCreateFail() throws Exception {
		
		Date date = new Date();
		System.out.println(date.getTime());
		String content = "{\"username\":\"tom\",\"password\":null,\"birthday\":"+date.getTime()+"}";
		String reuslt = mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.id").value("1"))
				.andReturn().getResponse().getContentAsString();
		
		System.out.println(reuslt);
	}
	
	@Test
	public void whenUpdateSuccess() throws Exception {
		
		Date date = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		System.out.println(date.getTime());
		String content = "{\"id\":\"1\", \"username\":\"tom\",\"password\":null,\"birthday\":"+date.getTime()+"}";
		String reuslt = mockMvc.perform(put("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("1"))
				.andReturn().getResponse().getContentAsString();
		
		System.out.println(reuslt);
	}
	
	@Test
	public void whenDeleteSuccess() throws Exception {
		mockMvc.perform(delete("/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
	}

}
