package com.example.mr.dao;

import java.util.List;
import com.example.mr.model.Karyawan;

public interface KaryawanDAO {
    public Karyawan getById(long id);
    public List<Karyawan> getAll();
}
