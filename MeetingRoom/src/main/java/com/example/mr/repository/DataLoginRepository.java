package com.example.mr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.mr.model.DataLogin;

@Repository
public interface DataLoginRepository extends CrudRepository<DataLogin, Long> {
	
	DataLogin findByUsernameAndPassword(String username,String password);

}
