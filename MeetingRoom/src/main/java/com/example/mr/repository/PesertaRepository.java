package com.example.mr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.mr.model.Peserta;

@Repository
public interface PesertaRepository extends CrudRepository<Peserta, Long>{

	@Query(value="SELECT * FROM peserta p WHERE p.id_pengajuan=:id",nativeQuery=true)
	List<Peserta> findByPengajuan(@Param("id") Long id);
	
}
