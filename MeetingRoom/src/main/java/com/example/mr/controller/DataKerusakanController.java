package com.example.mr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.mr.model.DataKerusakan;
import com.example.mr.model.DataLogin;
import com.example.mr.model.Karyawan;
import com.example.mr.model.Ruangan;
import com.example.mr.services.DataKerusakanServices;
import com.example.mr.services.KaryawanServices;
import com.example.mr.services.RuanganServices;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@SessionAttributes("penggunaaktif")
public class DataKerusakanController {

	@Autowired
	RuanganServices ruanganServices;
	@Autowired
	KaryawanServices karyawanServices;
	@Autowired
	DataKerusakanServices dataKerusakanServices;
	
	
	@RequestMapping(value="/formkerusakan",method=RequestMethod.GET)
	public ModelAndView keFormKerusakan(@ModelAttribute("ruangan") Ruangan r, @ModelAttribute("penggunaaktif") DataLogin dl) {
		if(dl.getLoginRole().getId()!=1) {
			return new ModelAndView("redirect:restricted");
		}
		ModelAndView mav = new ModelAndView();
		List<Ruangan> ruangan = ruanganServices.getTampilRuangan();
		mav.addObject("listruangan",ruangan);
		return mav;
	}
	
	@RequestMapping(value="/datakerusakan",method=RequestMethod.GET)
	public ModelAndView keHistoriKerusakan(@ModelAttribute("datakerusakan") DataKerusakan dk, @ModelAttribute("ruangan") Ruangan r, @ModelAttribute("penggunaaktif") DataLogin dl) {
		if(dl.getLoginRole().getId()!=1) {
			return new ModelAndView("redirect:restricted");
		}
		ModelAndView mav = new ModelAndView();
		List<DataKerusakan> ldk = dataKerusakanServices.getPerbaikanBelum();
		List<Ruangan> ruangan = ruanganServices.getTampilRuangan();
		mav.addObject("listdatakerusakan", ldk);
		mav.addObject("listruangan", ruangan);
		return mav;
	}
	
	@RequestMapping(value="/historikerusakan",method=RequestMethod.GET)
	public ModelAndView keHistoriKerusakanSelesai(@ModelAttribute("datakerusakan") DataKerusakan dk, @ModelAttribute("ruangan") Ruangan r, @ModelAttribute("penggunaaktif") DataLogin dl) {
		if(dl.getLoginRole().getId()!=1) {
			return new ModelAndView("redirect:restricted");
		}
		ModelAndView mav = new ModelAndView();
		List<DataKerusakan> ldk = dataKerusakanServices.getPerbaikanSelesai();
		List<Ruangan> ruangan = ruanganServices.getTampilRuangan();
		mav.addObject("listdatakerusakan", ldk);
		mav.addObject("listruangan", ruangan);
		return mav;
	}

	//Save the uploaded file to this folder
    private static String uploadedFolder = "C:/Users/RyanMahardika/Documents/workspace-sts-3.9.4.RELEASE/MeetingRoom/src/main/resources/static/img_kerusakan/";

    @RequestMapping(value ="formkerusakan",method=RequestMethod.POST)
    public String singleFileUpload(@RequestParam("photo") MultipartFile file,
                                   @ModelAttribute("datakerusakan") DataKerusakan dk, @ModelAttribute("penggunaaktif") DataLogin dl) {
        if (file.isEmpty()) {

        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadedFolder + file.getOriginalFilename());
            Files.write(path, bytes);
            dk.setAttachmentFoto(file.getOriginalFilename());
        } catch (Exception e) { }
        
        dk.setKaryawan(dl.getKaryawan());
        dataKerusakanServices.SaveOrUpdate(dk);
        return "redirect:formkerusakan";
    }
    
	@RequestMapping(value ="updatekerusakan",method=RequestMethod.GET)
    public ModelAndView formUpdateKerusakan(@RequestParam("id") int id, @ModelAttribute("ruangan") Ruangan r, @ModelAttribute("karyawan") Karyawan k, Model model, @ModelAttribute("penggunaaktif") DataLogin dl){
		if(dl.getLoginRole().getId()!=1) {
			return new ModelAndView("redirect:restricted");
		}
		List<Ruangan> ruangan = ruanganServices.getTampilRuangan();
		model.addAttribute("listruangan", ruangan);
		List<Karyawan> karyawan = karyawanServices.getAll();
		model.addAttribute("listkaryawan", karyawan);
        return new ModelAndView("updatekerusakan","datakerusakan",dataKerusakanServices.getById(id));
    }
	
	@RequestMapping(value ="updatekerusakan",method=RequestMethod.POST)
    public String updatePengajuan(@RequestParam("photo") MultipartFile file, @ModelAttribute("datakerusakan") DataKerusakan dk){
        if (file.isEmpty()) {

        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadedFolder + file.getOriginalFilename());
            Files.write(path, bytes);
            dk.setAttachmentFoto(file.getOriginalFilename());
            return "redirect:datakerusakan";      
        } catch (Exception e) {
            
        }
        
        dataKerusakanServices.SaveOrUpdate(dk);
        return "redirect:datakerusakan";
    }
    
	@RequestMapping(value ="konfirmasiperbaikan",method=RequestMethod.GET)
    public ModelAndView konfirmasiPerbaikan(@RequestParam("id") int id, @ModelAttribute("ruangan") Ruangan r, @ModelAttribute("karyawan") Karyawan k, Model model, @ModelAttribute("penggunaaktif") DataLogin dl){
		if(dl.getLoginRole().getId()!=1) {
			return new ModelAndView("redirect:restricted");
		}
		List<Ruangan> ruangan = ruanganServices.getTampilRuangan();
		model.addAttribute("listruangan", ruangan);
		List<Karyawan> karyawan = karyawanServices.getAll();
		model.addAttribute("listkaryawan", karyawan);
        return new ModelAndView("konfirmasiperbaikan","datakerusakan",dataKerusakanServices.getById(id));
    }
	
	@RequestMapping(value ="konfirmasiperbaikan",method=RequestMethod.POST)
    public String updateKonfirmasiPerbaikan(@ModelAttribute("datakerusakan") DataKerusakan dk) {
		dataKerusakanServices.SaveOrUpdate(dk);
		return "redirect:datakerusakan";
	}
	
}  
