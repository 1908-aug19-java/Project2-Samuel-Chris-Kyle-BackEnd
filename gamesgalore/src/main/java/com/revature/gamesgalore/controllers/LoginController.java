package com.revature.gamesgalore.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.gamesgalore.service.LoginService;

@CrossOrigin
@RestController("/login")
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@PostMapping
	public void login(HttpServletRequest request, HttpServletResponse response) {
		loginService.login(request, response);
	}
}
