package com.example.mr.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="loginrole")
public class LoginRole implements Serializable {

    @OneToMany(mappedBy = "loginRole")
    private List<DataLogin> dataLogins;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "login_role",insertable = true,length = 10,nullable = false,unique = true,updatable = false)
    private String loginRole;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoginRole)) {
            return false;
        }
        LoginRole other = (LoginRole) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.LoginRole[ id=" + id + " ]";
    }

    /**
     * @return the loginRole
     */
    public String getLoginRole() {
        return loginRole;
    }

    /**
     * @param loginRole the loginRole to set
     */
    public void setLoginRole(String loginRole) {
        this.loginRole = loginRole;
    }
    
}

