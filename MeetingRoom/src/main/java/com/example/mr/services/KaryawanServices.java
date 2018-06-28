package com.example.mr.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mr.dao.KaryawanDAO;
import com.example.mr.model.Karyawan;
import com.example.mr.repository.KaryawanRepository;

@Service
public class KaryawanServices implements KaryawanDAO {

	@Autowired
	KaryawanRepository karyawanRepository;
	
	@Override
	public Karyawan getById(long id) {
		Karyawan k = karyawanRepository.findById(id).get();
		return k;
	}

	@Override
	public List<Karyawan> getAll() {
		List<Karyawan> lk = new ArrayList<>();
		karyawanRepository.findAll().forEach(lk::add);
		return lk;
	}
	
	public void SaveOrUpdate(Karyawan k) {
		karyawanRepository.save(k);
	}
	
}
