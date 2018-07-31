package com.imooc.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Beauxie
 * @date 2018年7月29日
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTester {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void whenQuerySucess() throws Exception {
		/*
		 *通过get方式请求/user,返回JSON-UTF-8格式，并判断状态是成功的，而且结果是一个长度为3的集合。 
		 */
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/user")
				.param("username", "Beauxie")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);

	}
	
	@Test
	public void whenGetInfoSucess() throws Exception{
		/*
		 * MockMvcRequestBuilders、MockMvcResultMatchers已静态导入，因此可以直接
		 * 调用方法
		 * GET请求/user/1接口，返回的username属性的值必须包含Beauxie
		 */
		String result =  mockMvc.perform(get("/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.username").value("Beauxie"))
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
    public void whenCreateSucess() throws Exception {
    	Date date  = new Date();
    	System.out.println(date.getTime());
    	String content = "{\"username\":\"Beauxie\",\"password\":null,\"birthday\":"+date.getTime()+"}";
    	String result = mockMvc.perform(post("/user")
    			.content(content)
    	        .contentType(MediaType.APPLICATION_JSON_UTF8))
    	        .andExpect(status().isOk())
    	        .andExpect(jsonPath("$.id").value("1"))
    	        .andReturn().getResponse().getContentAsString();
    	System.out.println(result);
    }

}
