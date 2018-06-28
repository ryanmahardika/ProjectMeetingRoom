package com.example.mr.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mr.dao.DataLoginDAO;
import com.example.mr.model.DataLogin;
import com.example.mr.repository.DataLoginRepository;

@Service
public class DataLoginServices implements DataLoginDAO {
	
	@Autowired
	DataLoginRepository dataLoginRepository;
	
	public DataLogin getById(long id) {
		DataLogin dl = dataLoginRepository.findById(id).get();
		return dl;
	}

	public List<DataLogin> getAll() {
		List<DataLogin> ldl = new ArrayList<>();
		dataLoginRepository.findAll().forEach(ldl::add);
		return ldl;
	}

	public DataLogin getLogin(String username, String password) {
		return dataLoginRepository.findByUsernameAndPassword(username, password);
	}
	
	public boolean isUserExist(String username, String password) {
		return (dataLoginRepository.findByUsernameAndPassword(username, password)!=null);
	}
	
}
