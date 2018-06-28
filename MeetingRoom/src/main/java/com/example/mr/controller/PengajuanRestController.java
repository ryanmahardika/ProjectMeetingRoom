package com.example.mr.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.mr.model.DataPengajuan;
import com.example.mr.model.Event;
import com.example.mr.services.DataPengajuanServices;

@RestController
@SessionAttributes("penggunaaktif")
public class PengajuanRestController {
	
	@Autowired
	private DataPengajuanServices dataPengajuanServices;

	@RequestMapping("/getPengajuan")
	public ModelAndView getPengajuan(@ModelAttribute("datapengajuan") DataPengajuan dp) {
		return new ModelAndView("jadwalbulan","datapengajuan",dataPengajuanServices.getAll());
	}
	
	@RequestMapping("/restPengajuanBulan")
	public List<Event> pengajuanBulan() {
		List<Event> le = new ArrayList<>();
		List<DataPengajuan> ldp = dataPengajuanServices.getPengajuanDiterima();
		SimpleDateFormat sdfgmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    sdfgmt.setTimeZone(TimeZone.getTimeZone("UTC+07:00"));
		for(DataPengajuan dp:ldp) {
			Event event = new Event();
			event.setId(dp.getId());
			event.setKarId(dp.getKaryawan().getId());
			String mulai = dp.getTanggalPemakaian().toString() + " " + dp.getMulai();
			String selesai = dp.getTanggalPemakaian().toString() + " " + dp.getSelesai();
		    Date mulaiDate = null;
		    Date selesaiDate = null;
		    try {
		        mulaiDate = sdfgmt.parse(mulai);
		        selesaiDate = sdfgmt.parse(selesai);
		    } catch (ParseException e) {e.printStackTrace();}
			event.setStart(mulaiDate);
			event.setTitle(dp.getSubjekMeeting());
			event.setEnd(selesaiDate);
			event.setRuanganNama(dp.getRuangan().getNamaRuangan());
			event.setColor("#ff9933");
			event.setUrl("updatepengajuan?id="+dp.getId());
			le.add(event);
			
		}	
		return le;
	}
	
	
	//Controller User
	@RequestMapping("/restPengajuanBulanUser")
	public List<Event> pengajuanBulanUser() {
		List<Event> le = new ArrayList<>();
		List<DataPengajuan> ldp = dataPengajuanServices.getPengajuanDiterima();
		SimpleDateFormat sdfgmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    sdfgmt.setTimeZone(TimeZone.getTimeZone("UTC+07:00"));
		for(DataPengajuan dp:ldp) {
			Event event = new Event();
			event.setId(dp.getId());
			event.setKarId(dp.getKaryawan().getId());
			String mulai = dp.getTanggalPemakaian().toString() + " " + dp.getMulai();
			String selesai = dp.getTanggalPemakaian().toString() + " " + dp.getSelesai();
		    Date mulaiDate = null;
		    Date selesaiDate = null;
		    try {
		        mulaiDate = sdfgmt.parse(mulai);
		        selesaiDate = sdfgmt.parse(selesai);
		    } catch (ParseException e) {e.printStackTrace();}
			event.setStart(mulaiDate);
			event.setTitle(dp.getSubjekMeeting());
			event.setEnd(selesaiDate);
			event.setRuanganNama(dp.getRuangan().getNamaRuangan());
			event.setColor("#ff9933");
			le.add(event);
			
		}	
		return le;
	}
	
}
