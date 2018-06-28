package com.example.mr.dao;

import java.util.List;
import com.example.mr.model.LoginRole;

public interface LoginRoleDAO {
    public LoginRole getById(long id);
    public List<LoginRole> getAll();
}
