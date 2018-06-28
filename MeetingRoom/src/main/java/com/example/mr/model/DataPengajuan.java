package com.example.mr.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="datapengajuan")
public class DataPengajuan extends Tambahan implements Serializable {

    @OneToMany(mappedBy = "pengajuan")
    private List<Peserta> pesertas;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "idKaryawan",referencedColumnName = "id")
    private Karyawan karyawan;
    @ManyToOne
    @JoinColumn(name = "idRuangan",referencedColumnName = "id")
    private Ruangan ruangan;
    @Column(name = "subjek_meeting",length = 50,insertable = true,nullable = true)
    private String subjekMeeting;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "tanggal_pemakaian",nullable = false,insertable = true)
    private Date tanggalPemakaian;
    @Column(name = "deskripsi",nullable = true,insertable = true)
    private String deskripsi;
    @Column(name = "status_pengajuan",length = 25,updatable = true)
    private String statusPengajuan;
    @Column(name = "mulai",length = 5,insertable = true)
    private String mulai;
    @Column(name = "selesai",length = 5,insertable = true)
    private String selesai;
    
    

    public DataPengajuan(){
        statusPengajuan = "Pending";
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
        if (!(object instanceof DataPengajuan)) {
            return false;
        }
        DataPengajuan other = (DataPengajuan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.DataPengajuan[ id=" + id + " ]";
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
     * @return the subjekMeeting
     */
    public String getSubjekMeeting() {
        return subjekMeeting;
    }

    /**
     * @param subjekMeeting the subjekMeeting to set
     */
    public void setSubjekMeeting(String subjekMeeting) {
        this.subjekMeeting = subjekMeeting;
    }

    /**
     * @return the pesertas
     */
    public List<Peserta> getPesertas() {
        return pesertas;
    }

    /**
     * @param pesertas the pesertas to set
     */
    public void setPesertas(List<Peserta> pesertas) {
        this.pesertas = pesertas;
    }


    /**
     * @return the tanggalPemakaian
     */
    public Date getTanggalPemakaian() {
        return tanggalPemakaian;
    }

    /**
     * @param tanggalPemakaian the tanggalPemakaian to set
     */
    public void setTanggalPemakaian(Date tanggalPemakaian) {
        this.tanggalPemakaian = tanggalPemakaian;
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
     * @return the statusPengajuan
     */
    public String getStatusPengajuan() {
        return statusPengajuan;
    }

    /**
     * @param statusPengajuan the statusPengajuan to set
     */
    public void setStatusPengajuan(String statusPengajuan) {
        this.statusPengajuan = statusPengajuan;
    }

    /**
     * @return the mulai
     */
    public String getMulai() {
        return mulai;
    }

    /**
     * @param mulai the mulai to set
     */
    public void setMulai(String mulai) {
        this.mulai = mulai;
    }

    /**
     * @return the selesai
     */
    public String getSelesai() {
        return selesai;
    }

    /**
     * @param selesai the selesai to set
     */
    public void setSelesai(String selesai) {
        this.selesai = selesai;
    }

    
    
}

