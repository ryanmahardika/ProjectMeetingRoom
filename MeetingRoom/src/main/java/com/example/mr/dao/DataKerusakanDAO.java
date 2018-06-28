package com.example.mr.dao;

import java.util.List;
import com.example.mr.model.DataKerusakan;

public interface DataKerusakanDAO {
    public DataKerusakan getById(long id);
    public List<DataKerusakan> getAll();
    public List<DataKerusakan> getPerbaikanBelum();
}