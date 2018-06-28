package com.example.mr.dao;

import java.util.List;
import com.example.mr.model.Ruangan;

public interface RuanganDAO {
    public Ruangan getById(long id);
    public List<Ruangan> getAll();
}
