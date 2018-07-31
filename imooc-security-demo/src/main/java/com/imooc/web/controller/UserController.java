package com.imooc.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;

/**
 * @author Beauxie
 * @date 2018年7月29日
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@GetMapping
	@JsonView(User.UserSimpleView.class)
	public List<User> query(@RequestParam(required = false) String username, Pageable pageable) {
		System.out.println(username);
		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;
	}

	/**
	 * BindingResult的作用:即使有错误，也会继续进入到方法里面
	 * 
	 * @param user
	 * @param errors
	 * @return
	 */
	@PostMapping
//	@JsonView(User.UserDetailView.class)
	public User create(@Valid @RequestBody User user, BindingResult errors) {
		if (errors.hasErrors()) {
			errors.getAllErrors().forEach(error -> System.out.println(error));
		}
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());
		user.setId("1");
		return user;
	}

	/**
	 * \\d+正则表达式 表示参数id的值只能为数字
	 * 
	 * @param id
	 * @return
	 */
	@JsonView(User.UserDetailView.class)
	@GetMapping(value = "/{id:\\d+}")
	public User getInfo(@PathVariable String id) {
		User user = new User();
		user.setUsername("Beauxie");
		return user;
	}

}
