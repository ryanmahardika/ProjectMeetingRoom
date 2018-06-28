package com.example.mr.controller;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("penggunaaktif")
public class MyErrorController implements ErrorController{

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}
	
	@RequestMapping("/error")
	public String tidakAdaHalaman() {
		return "error";
	}
	
	@RequestMapping("/restricted")
	public String tidakAdaAkses() {
		return "restricted";
	}

}
