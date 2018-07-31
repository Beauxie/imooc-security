package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Beauxie
 * @date 2018年7月29日
 */
@SpringBootApplication
@RestController
public class DemoAplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoAplication.class, args);
	}

	@GetMapping("/hello")
	public String hello() {
		return "Hello,Beauxie";
	}

}
