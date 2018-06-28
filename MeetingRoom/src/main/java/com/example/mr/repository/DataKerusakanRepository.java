package com.example.mr.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.mr.model.DataKerusakan;

@Repository
public interface DataKerusakanRepository extends CrudRepository<DataKerusakan, Long> {

	List<DataKerusakan> getByStatusPerbaikan(String statusPerbaikan);
	
}
