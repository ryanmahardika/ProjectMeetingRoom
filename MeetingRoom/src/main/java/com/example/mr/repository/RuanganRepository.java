package com.example.mr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mr.model.Ruangan;

@Repository
public interface RuanganRepository extends CrudRepository<Ruangan, Long> {
	
	List<Ruangan> findByNamaRuangan(String namaRuangan);
	List<Ruangan> findAllByOrderByNamaRuanganDesc();
	
	@Query
	(value="SELECT * FROM ruangan r where r.keterangan = :keterangan AND r.status_ruangan = :status_ruangan", nativeQuery=true)
	public List<Ruangan> getByStatusRuangan(@Param("keterangan") String keterangan, @Param("status_ruangan") String statusRuangan);
	
}
