package com.example.mr.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mr.model.Ruangan;
import com.example.mr.repository.RuanganRepository;

@Service
public class RuanganServices {
	
	@Autowired
	RuanganRepository ruanganRepository;
	
	public List<Ruangan> getAll(){
		List<Ruangan> lr = new ArrayList<>();
		ruanganRepository.findAll().forEach(lr::add);
		return lr;
	}
	
	public Ruangan getById(long id) {
		Ruangan r = ruanganRepository.findById(id).get();
		return r;
	}
	
	public void SaveOrUpdate(Ruangan r) {
		ruanganRepository.save(r);
	}
	
	public List<Ruangan> getTampilRuangan() {
		List<Ruangan> lr = new ArrayList<>();
		ruanganRepository.getByStatusRuangan("Aktif", "Tersedia").forEach(lr::add);
		return lr;
	}
	
}
