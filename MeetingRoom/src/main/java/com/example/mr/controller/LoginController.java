package com.example.mr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.mr.component.EmailServiceImpl;
import com.example.mr.model.DataLogin;
import com.example.mr.services.DataLoginServices;

@Controller
@SessionAttributes("penggunaaktif")
public class LoginController {
	
	@Autowired
	DataLoginServices service;
	@Autowired
	EmailServiceImpl emailServiceImpl;
	
	@RequestMapping("/")
	public String keLogin() {
		return "login";
	}

	@RequestMapping(value="home",method=RequestMethod.POST)
	public ModelAndView getLogin(@ModelAttribute("DataLogin") DataLogin dl) {
		ModelAndView mav = new ModelAndView();
		DataLogin penggunaaktif = service.getLogin(dl.getUsername(), dl.getPassword());
		mav.addObject("penggunaaktif", penggunaaktif);
		if (penggunaaktif.getLoginRole().getId()==1) {
			mav.setViewName("index");
		}else {
			mav.setViewName("indexuser");
		}
		return mav;
	}
	
	@RequestMapping(value="home",method=RequestMethod.GET)
	public ModelAndView keHome(@ModelAttribute("penggunaaktif") DataLogin dl) {
		ModelAndView mav = new ModelAndView();
		if (dl.getLoginRole().getId()==1) {
			mav.setViewName("/index");
		}else {
			mav.setViewName("/indexuser");
		}
		return mav;
	}
	
	@RequestMapping("logout")
	public String getLogout(WebRequest request, SessionStatus status) {
		status.setComplete();
		request.removeAttribute("penggunaaktif", WebRequest.SCOPE_SESSION);
		return "login";
	}
	
}
