package com.example.mr.dao;

import java.util.List;
import com.example.mr.model.Peserta;

public interface PesertaDAO {
    public Peserta getById(long id);
    public List<Peserta> getAll();
}
