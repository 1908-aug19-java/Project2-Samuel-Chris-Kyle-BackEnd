package com.revature.gamesgalore.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Service
public interface LoginService {

	 void login(HttpServletRequest request, HttpServletResponse response);
}
