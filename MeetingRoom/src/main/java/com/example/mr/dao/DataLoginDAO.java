package com.example.mr.dao;

import java.util.List;
import com.example.mr.model.DataLogin;

public interface DataLoginDAO {
    public DataLogin getById(long id);
    public List<DataLogin> getAll();
    public DataLogin getLogin(String username,String password);
}
