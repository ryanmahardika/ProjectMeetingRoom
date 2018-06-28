package com.example.mr.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PesertaPK implements Serializable {
    @Basic(optional = false)
    @Column(insertable = false,updatable = false)
    private long idPengajuan;
    @Basic(optional = false)
    @Column(insertable = false,updatable = false)
    private long idKaryawan;
    
    public PesertaPK(){
        
    }
    
    public PesertaPK(long idPengajuan,long idKaryawan){
        this.idPengajuan = idPengajuan;
        this.idKaryawan = idKaryawan;
    }

    /**
     * @return the idPengajuan
     */
    public long getIdPengajuan() {
        return idPengajuan;
    }

    /**
     * @param idPengajuan the idPengajuan to set
     */
    public void setIdPengajuan(long idPengajuan) {
        this.idPengajuan = idPengajuan;
    }

    /**
     * @return the idKaryawan
     */
    public long getIdKaryawan() {
        return idKaryawan;
    }

    /**
     * @param idKaryawan the idKaryawan to set
     */
    public void setIdKaryawan(long idKaryawan) {
        this.idKaryawan = idKaryawan;
    }
    
}

