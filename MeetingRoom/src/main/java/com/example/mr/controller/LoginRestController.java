package com.example.mr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.mr.services.DataLoginServices;

@RestController
@SessionAttributes("penggunaaktif")
public class LoginRestController {

	@Autowired
	private DataLoginServices dataLoginServices;
	
	@RequestMapping("restUserCheck")
	public boolean isUserExist(@RequestParam("username") String username, @RequestParam("password") String password) {
		return dataLoginServices.isUserExist(username, password);
	}
		
}
