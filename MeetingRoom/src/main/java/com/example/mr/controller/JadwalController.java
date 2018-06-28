package com.example.mr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.mr.model.DataLogin;
import com.example.mr.model.Ruangan;
import com.example.mr.services.RuanganServices;

@Controller
@SessionAttributes("penggunaaktif")
public class JadwalController {
	
	@Autowired
	RuanganServices ruanganServices;
	
	@RequestMapping("jadwalruangan")
	public ModelAndView keJadwalRuangan(@ModelAttribute("penggunaaktif") DataLogin dl) {
		if(dl.getLoginRole().getId()!=1) {
			return new ModelAndView("redirect:restricted");
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jadwalruangan");
		return mav;
	}
	
	@RequestMapping("jadwalbulan")
	public ModelAndView keJadwalBulan(@ModelAttribute("penggunaaktif") DataLogin dl) {
		if(dl.getLoginRole().getId()!=1) {
			return new ModelAndView("redirect:restricted");
		}
		return new ModelAndView("jadwalbulan","listruangan",ruanganServices.getTampilRuangan());
	}
	
	
	//Controller User
	@RequestMapping("bulan")
	public ModelAndView keJadwalBulanUser(@ModelAttribute("ruangan") Ruangan r, @ModelAttribute("penggunaaktif") DataLogin dl) {
		if(dl.getLoginRole().getId()!=2) {
			return new ModelAndView("redirect:restricted");
		}
		return new ModelAndView("jadwalbulanuser","listruangan",ruanganServices.getTampilRuangan());
	}
	
	@RequestMapping("jadwalruanganuser")
	public ModelAndView keJadwalRuanganUser(@ModelAttribute("penggunaaktif") DataLogin dl) {
		if(dl.getLoginRole().getId()!=2) {
			return new ModelAndView("redirect:restricted");
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jadwalruanganuser");
		return mav;
	}
	
}
