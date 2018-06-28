package com.example.mr.dao;

import java.util.List;
import com.example.mr.model.DataPengajuan;

public interface DataPengajuanDAO {
    public DataPengajuan getById(long id);
    public List<DataPengajuan> getAll();
    public List<DataPengajuan> getPengajuanPending();
}
