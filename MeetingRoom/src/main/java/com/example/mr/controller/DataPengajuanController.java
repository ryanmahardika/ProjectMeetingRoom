package com.example.mr.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.mr.model.DataLogin;
import com.example.mr.model.DataPengajuan;
import com.example.mr.model.Karyawan;
import com.example.mr.model.Peserta;
import com.example.mr.model.Ruangan;
import com.example.mr.services.DataPengajuanServices;
import com.example.mr.services.KaryawanServices;
import com.example.mr.services.PesertaServices;
import com.example.mr.services.RuanganServices;

@Controller
@SessionAttributes("penggunaaktif")
public class DataPengajuanController {

	@Autowired
	DataPengajuanServices dataPengajuanServices;
	@Autowired
	RuanganServices ruanganServices;
	@Autowired
	KaryawanServices karyawanServices;
	@Autowired
	PesertaServices pesertaServices;
	
	@RequestMapping(value="/formpengajuan",method=RequestMethod.GET)
	public ModelAndView keFormPengajuan(@ModelAttribute("ruangan") Ruangan r, @ModelAttribute("karyawan") Karyawan k, @ModelAttribute("penggunaaktif") DataLogin dl) {
		if(dl.getLoginRole().getId()!=1) {
			return new ModelAndView("redirect:restricted");
		}
		ModelAndView mav = new ModelAndView();
		List<Karyawan> peserta = karyawanServices.getAll();
		List<Ruangan> ruangan = ruanganServices.getTampilRuangan();
		mav.addObject("listpeserta",peserta);
		mav.addObject("listruangan",ruangan);
		return mav;
	}
	
	@RequestMapping(value="/formpengajuan",method=RequestMethod.POST)
	public ModelAndView insertPengajuan(@RequestParam(value = "peserta",required = false,defaultValue="") List<Long> ids,@ModelAttribute("datapengajuan") DataPengajuan dp, @ModelAttribute("ruangan") Ruangan r, @ModelAttribute("penggunaaktif") DataLogin dl, @ModelAttribute("karyawan") Karyawan k) {
		dp.setCreatedBy(dl.getKaryawan().getNama());
		dp.setKaryawan(dl.getKaryawan());
		if(ids.isEmpty()) {
			dataPengajuanServices.SaveOrUpdate(dp);
		}else {
			dataPengajuanServices.savePengajuan(dp, ids);
		}
		return new ModelAndView("jadwalruangan","listpengajuan",dataPengajuanServices.getAll());
	}
	
	@RequestMapping(value="daftarpengajuan",method=RequestMethod.GET)
	public ModelAndView tampilDaftarPengajuan(@ModelAttribute("daftarpengajuan") DataPengajuan dp, @ModelAttribute("penggunaaktif") DataLogin dl) {
		if(dl.getLoginRole().getId()!=1) {
			return new ModelAndView("redirect:restricted");
		}
		return new ModelAndView("daftarpengajuan","listpengajuan",dataPengajuanServices.getPengajuanPending());
	}
	
	@RequestMapping(value="historipemakaian",method=RequestMethod.GET)
	public ModelAndView tampilHistoriPemakaian(@ModelAttribute("daftarpengajuan") DataPengajuan dp, @ModelAttribute("penggunaaktif") DataLogin dl) {
		if(dl.getLoginRole().getId()!=1) {
			return new ModelAndView("redirect:restricted");
		}
		ModelAndView mav = new ModelAndView();
		List<DataPengajuan> ldp = dataPengajuanServices.getPengajuanSelesai();
		List<Ruangan> ruangan = ruanganServices.getTampilRuangan();
		mav.addObject("listdatapengajuan", ldp);
		mav.addObject("listruangan", ruangan);
		return mav;
	}
	
	@RequestMapping(value ="updatepengajuan",method=RequestMethod.GET)
    public ModelAndView formUpdatePengajuan(@ModelAttribute("penggunaaktif") DataLogin dl, @RequestParam("id") Long id, @ModelAttribute("ruangan") Ruangan r){
		if(dl.getLoginRole().getId()!=1) {
			return new ModelAndView("redirect:restricted");
		}
		ModelAndView mav = new ModelAndView();
		List<Ruangan> ruangan = ruanganServices.getTampilRuangan();
		List<Karyawan> karyawan = karyawanServices.getAll();
		List<Peserta> pesertaPengajuan = pesertaServices.getPesertaPengajuan(id);
		mav.addObject("listruangan", ruangan);
		mav.addObject("listkaryawan",karyawan);
		mav.addObject("listpeserta",pesertaPengajuan);
		mav.addObject("datapengajuan",dataPengajuanServices.getById(id));
		mav.setViewName("updatepengajuan");
        return mav;
    }
	
	@RequestMapping(value ="updatepengajuan",method=RequestMethod.POST)
    public String updatePengajuan(@RequestParam(value = "peserta",required = false,defaultValue="") List<Long> ids,@ModelAttribute("datapengajuan") DataPengajuan dp, @ModelAttribute("penggunaaktif") DataLogin dl){
        dp.setUpdatedBy(dl.getLoginRole().getLoginRole());
    	dp.setCreateDate(new Date());
    	List<Peserta> pesertaLama = pesertaServices.getPesertaPengajuan(dp.getId());
    		for(int a = 0;a<ids.size();a++) {
    			for(int b = 0;b<pesertaLama.size();b++) {
    				if(ids.get(a)==pesertaLama.get(b).getKaryawan().getId()) {
    					continue;
    				}else {
    					pesertaServices.hapusPeserta(pesertaLama.get(b));
    				}
    			}		
    		}
    		if(ids.isEmpty()) {
    			dataPengajuanServices.SaveOrUpdate(dp);
    		}else {
    			dataPengajuanServices.savePengajuan(dp, ids);
    		}
            return "redirect:daftarpengajuan";
    }
	
	@RequestMapping(value="daftarpengajuanditerima",method=RequestMethod.GET)
	public ModelAndView tampilDaftarPengajuanDiterima(@ModelAttribute("daftarpengajuan") DataPengajuan dp, @ModelAttribute("penggunaaktif") DataLogin dl) {
		if(dl.getLoginRole().getId()!=1) {
			return new ModelAndView("redirect:restricted");
		}
		return new ModelAndView("daftarpengajuanditerima","listpengajuanditerima",dataPengajuanServices.getPengajuanDiterima());
	}
	
	@RequestMapping(value ="konfirmasipengajuanditerima",method=RequestMethod.GET)
    public ModelAndView pengajuanDiterima(@ModelAttribute("penggunaaktif") DataLogin dl, @RequestParam("id") Long id,@ModelAttribute("ruangan") Ruangan r){
		if(dl.getLoginRole().getId()!=1) {
			return new ModelAndView("redirect:restricted");
		}
		ModelAndView mav = new ModelAndView();
		List<Ruangan> ruangan = ruanganServices.getTampilRuangan();
		List<Karyawan> karyawan = karyawanServices.getAll();
		List<Peserta> pesertaPengajuan = pesertaServices.getPesertaPengajuan(id);
		mav.addObject("listruangan", ruangan);
		mav.addObject("listkaryawan",karyawan);
		mav.addObject("listpeserta",pesertaPengajuan);
		mav.addObject("datapengajuan",dataPengajuanServices.getById(id));
		mav.setViewName("konfirmasipengajuanditerima");
        return mav;
	}
	
	@RequestMapping(value ="konfirmasipengajuanditerima",method=RequestMethod.POST)
    public String konfirmasiPengajuanDiterima(@ModelAttribute("datapengajuan") DataPengajuan dp, @ModelAttribute("penggunaaktif") DataLogin dl){
        dp.setUpdatedBy(dl.getLoginRole().getLoginRole());
        dp.setUpdateDate(new Date());
    	dataPengajuanServices.SaveOrUpdateWithEmailTerima(dp);
        return "redirect:daftarpengajuan";
	}
	
	@RequestMapping(value ="konfirmasipengajuanditolak",method=RequestMethod.GET)
    public ModelAndView pengajuanDitolak(@ModelAttribute("penggunaaktif") DataLogin dl, @RequestParam("id") Long id, @ModelAttribute("ruangan") Ruangan r){
		if(dl.getLoginRole().getId()!=1) {
			return new ModelAndView("redirect:restricted");
		}
		ModelAndView mav = new ModelAndView();
		List<Ruangan> ruangan = ruanganServices.getTampilRuangan();
		List<Karyawan> karyawan = karyawanServices.getAll();
		List<Peserta> pesertaPengajuan = pesertaServices.getPesertaPengajuan(id);
		mav.addObject("listruangan", ruangan);
		mav.addObject("listkaryawan",karyawan);
		mav.addObject("listpeserta",pesertaPengajuan);
		mav.addObject("datapengajuan",dataPengajuanServices.getById(id));
		mav.setViewName("konfirmasipengajuanditolak");
        return mav;
	}
	
	@RequestMapping(value ="konfirmasipengajuanditolak",method=RequestMethod.POST)
    public String konfirmasiPengajuanDitolak(@ModelAttribute("datapengajuan") DataPengajuan dp, @ModelAttribute("penggunaaktif") DataLogin dl) {
		dp.setUpdatedBy(dl.getLoginRole().getLoginRole());
		dp.setUpdateDate(new Date());
		dataPengajuanServices.SaveOrUpdateWithEmailTolak(dp);
		return "redirect:daftarpengajuan";
	}
	
	@RequestMapping(value ="konfirmasipengajuandibatalkan",method=RequestMethod.GET)
    public ModelAndView pengajuanDibatalkan(@ModelAttribute("penggunaaktif") DataLogin dl, @RequestParam("id") Long id,@ModelAttribute("ruangan") Ruangan r){
		if(dl.getLoginRole().getId()!=1) {
			return new ModelAndView("redirect:restricted");
		}
		ModelAndView mav = new ModelAndView();
		List<Ruangan> ruangan = ruanganServices.getTampilRuangan();
		List<Karyawan> karyawan = karyawanServices.getAll();
		List<Peserta> pesertaPengajuan = pesertaServices.getPesertaPengajuan(id);
		mav.addObject("listruangan", ruangan);
		mav.addObject("listkaryawan",karyawan);
		mav.addObject("listpeserta",pesertaPengajuan);
		mav.addObject("datapengajuan",dataPengajuanServices.getById(id));
		mav.setViewName("konfirmasipengajuandibatalkan");
        return mav;
	}
	
	@RequestMapping(value ="konfirmasipengajuandibatalkan",method=RequestMethod.POST)
    public String konfirmasiPengajuanDibatalkan(@ModelAttribute("datapengajuan") DataPengajuan dp, @ModelAttribute("penggunaaktif") DataLogin dl) {
		dp.setUpdatedBy(dl.getLoginRole().getLoginRole());
		dp.setUpdateDate(new Date());
		dataPengajuanServices.SaveOrUpdate(dp);
		return "redirect:daftarpengajuan";
	}
	
	
	//Controller User
	@RequestMapping(value="pengajuan",method=RequestMethod.GET)
	public ModelAndView keFormPengajuanUser(@ModelAttribute("ruangan") Ruangan r, @ModelAttribute("karyawan") Karyawan k, @ModelAttribute("penggunaaktif") DataLogin dl) {
		if(dl.getLoginRole().getId()!=2) {
			return new ModelAndView("redirect:restricted");
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("penggunaaktif", dl);
		List<Karyawan> peserta = karyawanServices.getAll();
		List<Ruangan> ruangan = ruanganServices.getTampilRuangan();
		mav.addObject("listpeserta",peserta);
		mav.addObject("listruangan",ruangan);
		mav.addObject("datapengajuan",new DataPengajuan());
		mav.setViewName("formpengajuanuser");
		return mav;
	}
	
	@RequestMapping(value="pengajuan",method=RequestMethod.POST)
	public ModelAndView insertPengajuanUser(@RequestParam(value = "peserta",required = false,defaultValue="") List<Long> ids,@ModelAttribute("datapengajuan") DataPengajuan dp, @ModelAttribute("ruangan") Ruangan r, @ModelAttribute("penggunaaktif") DataLogin dl, @ModelAttribute("karyawan") Karyawan k) {
		dp.setCreatedBy(dl.getKaryawan().getNama());
		dp.setKaryawan(dl.getKaryawan());
		if(ids.isEmpty()) {
			dataPengajuanServices.SaveOrUpdate(dp);
		}else {
			dataPengajuanServices.savePengajuan(dp, ids);
		}
		return new ModelAndView("redirect:daftar","listpengajuan",dataPengajuanServices.getAll());
	}

	@RequestMapping(value="histori",method=RequestMethod.GET)
	public ModelAndView tampilHistoriPemakaianUser(@ModelAttribute("daftarpengajuan") DataPengajuan dp, @ModelAttribute("penggunaaktif") DataLogin dl) {
		if(dl.getLoginRole().getId()!=2) {
			return new ModelAndView("redirect:restricted");
		}
		ModelAndView mav = new ModelAndView();
		List<DataPengajuan> ldp = dataPengajuanServices.getPengajuanSelesaiUser("Selesai",dl.getKaryawan().getId());
		mav.addObject("listdatapengajuan", ldp);
		mav.setViewName("historipemakaianuser");
		return mav;
	}
	
	@RequestMapping(value="daftar",method=RequestMethod.GET)
	public ModelAndView tampilPengajuanUser(@ModelAttribute("daftarpengajuan") DataPengajuan dp,@ModelAttribute("penggunaaktif") DataLogin dl) {
		if(dl.getLoginRole().getId()!=2) {
			return new ModelAndView("redirect:restricted");
		}
		ModelAndView mav = new ModelAndView();
		List<DataPengajuan> ldp = dataPengajuanServices.getPengajuanUser(dl.getKaryawan().getId());
		mav.addObject("listdatapengajuan", ldp);
		mav.setViewName("daftarpengajuanuser");
		return mav;
	}
	
	@RequestMapping(value ="update",method=RequestMethod.GET)
    public ModelAndView formUpdatePengajuanUser(@ModelAttribute("penggunaaktif") DataLogin dl,@RequestParam("id") Long id,@ModelAttribute("ruangan") Ruangan r){
		if(dl.getLoginRole().getId()!=2) {
			return new ModelAndView("redirect:restricted");
		}
		ModelAndView mav = new ModelAndView();
		List<Ruangan> ruangan = ruanganServices.getTampilRuangan();
		List<Karyawan> karyawan = karyawanServices.getAll();
		List<Peserta> pesertaPengajuan = pesertaServices.getPesertaPengajuan(id);
		mav.addObject("listruangan", ruangan);
		mav.addObject("listkaryawan",karyawan);
		mav.addObject("listpeserta",pesertaPengajuan);
		mav.addObject("datapengajuan",dataPengajuanServices.getById(id));
		mav.setViewName("updatepengajuanuser");
        return mav;
    }
	
	@RequestMapping(value ="update",method=RequestMethod.POST)
    public String updatePengajuanUser(@RequestParam(value = "peserta",required = false,defaultValue="") List<Long> ids,@ModelAttribute("datapengajuan") DataPengajuan dp, @ModelAttribute("penggunaaktif") DataLogin dl){
		dp.setUpdatedBy(dl.getKaryawan().getNama());
		dp.setCreateDate(new Date());
		dp.setCreatedBy(dp.getUpdatedBy());
		List<Peserta> pesertaLama = pesertaServices.getPesertaPengajuan(dp.getId());
		for(int a = 0;a<ids.size();a++) {
			for(int b = 0;b<pesertaLama.size();b++) {
				if(ids.get(a)==pesertaLama.get(b).getKaryawan().getId()) {
					continue;
				}else {
					pesertaServices.hapusPeserta(pesertaLama.get(b));
				}
			}		
		}
		if(ids.isEmpty()) {
			dataPengajuanServices.SaveOrUpdate(dp);
		}else {
			dataPengajuanServices.savePengajuan(dp, ids);
		}
        return "redirect:daftar";
    }
	
	@RequestMapping(value ="pengajuanuserdibatalkan",method=RequestMethod.GET)
    public ModelAndView pengajuanUserDibatalkan(@ModelAttribute("penggunaaktif") DataLogin dl, @RequestParam("id") Long id,@ModelAttribute("ruangan") Ruangan r){
		if(dl.getLoginRole().getId()!=2) {
			return new ModelAndView("redirect:restricted");
		}
		ModelAndView mav = new ModelAndView();
		List<Ruangan> ruangan = ruanganServices.getTampilRuangan();
		List<Karyawan> karyawan = karyawanServices.getAll();
		List<Peserta> pesertaPengajuan = pesertaServices.getPesertaPengajuan(id);
		mav.addObject("listruangan", ruangan);
		mav.addObject("listkaryawan",karyawan);
		mav.addObject("listpeserta",pesertaPengajuan);
		mav.addObject("datapengajuan",dataPengajuanServices.getById(id));
		mav.setViewName("pengajuanuserdibatalkan");
        return mav;
	}
	
	@RequestMapping(value ="pengajuanuserdibatalkan",method=RequestMethod.POST)
    public String konfirmasiPengajuanUserDibatalkan(@ModelAttribute("datapengajuan") DataPengajuan dp, @ModelAttribute("penggunaaktif") DataLogin dl) {
		dp.setUpdatedBy(dl.getKaryawan().getNama());
		dp.setUpdateDate(new Date());
		dataPengajuanServices.SaveOrUpdate(dp);
		return "redirect:daftar";
	}
	
}
