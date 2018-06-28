package com.example.mr.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="datakerusakan")
public class DataKerusakan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idKaryawan",referencedColumnName = "id")
    private Karyawan karyawan;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idRuangan",referencedColumnName = "id")
    private Ruangan ruangan;
    @Column(name = "deskripsi",length = 200,insertable = true,nullable = true)
    private String deskripsi;
    @Column(name = "attachmentfoto",insertable = true,nullable = true)
    private String attachmentFoto;
    @Column(name = "statusPerbaikan",length = 20,updatable = true,nullable = false)
    private String statusPerbaikan;
    @Column(name = "kodeKerusakan",length = 5,unique = true,insertable = true)
    private String kodeKerusakan;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "tanggalRusak")
    private Date tanggalRusak;
    @Column(name = "fasilitasRusak",length = 100,insertable = true,nullable = false)
    private String fasilitasRusak;

    public DataKerusakan(){
        statusPerbaikan = "Belum Diperbaiki";
    }
    
    public DataKerusakan(Karyawan karyawan,Ruangan ruangan){
        this.karyawan = karyawan;
        this.ruangan = ruangan;
        statusPerbaikan = "Belum Diperbaiki";
    }
    
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
        if (!(object instanceof DataKerusakan)) {
            return false;
        }
        DataKerusakan other = (DataKerusakan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.DataKerusakan1[ id=" + id + " ]";
    }

    /**
     * @return the karyawan
     */
    public Karyawan getKaryawan() {
        return karyawan;
    }

    /**
     * @param karyawan the karyawan to set
     */
    public void setKaryawan(Karyawan karyawan) {
        this.karyawan = karyawan;
    }

    /**
     * @return the ruangan
     */
    public Ruangan getRuangan() {
        return ruangan;
    }

    /**
     * @param ruangan the ruangan to set
     */
    public void setRuangan(Ruangan ruangan) {
        this.ruangan = ruangan;
    }

    /**
     * @return the deskripsi
     */
    public String getDeskripsi() {
        return deskripsi;
    }

    /**
     * @param deskripsi the deskripsi to set
     */
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    /**
     * @return the attachment
     */
    public String getAttachmentFoto() {
        return attachmentFoto;
    }

    /**
     * @param attachment the attachment to set
     */
    public void setAttachmentFoto(String attachmentFoto) {
        this.attachmentFoto = attachmentFoto;
    }

    /**
     * @return the statusPerbaikan
     */
    public String getStatusPerbaikan() {
        return statusPerbaikan;
    }

    /**
     * @param statusPerbaikan the statusPerbaikan to set
     */
    public void setStatusPerbaikan(String statusPerbaikan) {
        this.statusPerbaikan = statusPerbaikan;
    }

    /**
     * @return the kodeKerusakan
     */
    public String getKodeKerusakan() {
        return kodeKerusakan;
    }

    /**
     * @param kodeKerusakan the kodeKerusakan to set
     */
    public void setKodeKerusakan(String kodeKerusakan) {
        this.kodeKerusakan = kodeKerusakan;
    }

    /**
     * @return the tanggalRusak
     */
    public Date getTanggalRusak() {
        return tanggalRusak;
    }

    /**
     * @param tanggalRusak the tanggalRusak to set
     */
    public void setTanggalRusak(Date tanggalRusak) {
        this.tanggalRusak = tanggalRusak;
    }

    /**
     * @return the fasilitasRusak
     */
    public String getFasilitasRusak() {
        return fasilitasRusak;
    }

    /**
     * @param fasilitasRusak the fasilitasRusak to set
     */
    public void setFasilitasRusak(String fasilitasRusak) {
        this.fasilitasRusak = fasilitasRusak;
    }
    
}

