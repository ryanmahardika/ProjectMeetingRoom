package com.example.mr.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mr.model.DataPengajuan;
import com.example.mr.model.Ruangan;

@Repository
public interface DataPengajuanRepository extends CrudRepository<DataPengajuan, Long> {

	List<DataPengajuan> findByRuanganOrderByCreateDateAsc(Ruangan ruangan);
	List<DataPengajuan> findByTanggalPemakaianOrderByCreateDateAsc(Date tanggalPemakaian);
	List<DataPengajuan> getByStatusPengajuan(String statusPengajuan);
	
	@Query(value="SELECT * FROM datapengajuan dp WHERE dp.status_pengajuan=:status_pengajuan AND dp.id_karyawan=:id_karyawan",nativeQuery=true)
	List<DataPengajuan> getByStatusPengajuanById(@Param("status_pengajuan") String statusPengajuan,@Param("id_karyawan") Long idKaryawan);
	
}
