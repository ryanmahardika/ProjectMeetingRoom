package com.example.mr.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.mr.model.Karyawan;

@Repository
public interface KaryawanRepository extends CrudRepository<Karyawan, Long>{
	List<Karyawan> findByNama(String nama);
	List<Karyawan> findAllByOrderByNamaDesc();
}
