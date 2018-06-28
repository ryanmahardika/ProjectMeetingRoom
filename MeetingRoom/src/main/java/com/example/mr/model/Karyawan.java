package com.example.mr.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Karyawan extends Tambahan implements Serializable {

    @OneToMany(mappedBy = "karyawan")
    private List<Peserta> pesertas;

    @OneToMany(mappedBy = "karyawan")
    private List<DataKerusakan> dataKerusakans;

    @OneToMany(mappedBy = "karyawan")
    private List<DataPengajuan> dataPengajuans;

    @OneToOne(mappedBy = "karyawan")
    private DataLogin dataLogin;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nik",insertable = true,length = 10,nullable = false,unique = true,updatable = false)
    private String nik;
    @Column(name = "nama",insertable = true,length = 50,nullable = false)
    private String nama;
    @Column(name = "jabatan",insertable = true,length = 10,nullable = false)
    private String jabatan;
    @Column(name = "no_telpon",insertable = true,length = 20,nullable = false,updatable = true)
    private String nomorTelpon;
    @Column(name = "alamat",insertable = true,length = 150,nullable = false,updatable = true)
    private String alamat;
    @Column(name = "ktp",insertable = true,length = 25,nullable = false,updatable = true)
    private String ktp;

    
    public boolean isPeserta(Long idPengajuan) {
    	boolean selected = false;
    	for(Peserta p:this.pesertas) {
    		if (p.pengajuan.getId()==idPengajuan) {
    			selected = true;	
    		}
    	}
    	return selected;
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
        if (!(object instanceof Karyawan)) {
            return false;
        }
        Karyawan other = (Karyawan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Karyawan[ id=" + id + " ]";
    }

    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * @return the dataLogin
     */
    public DataLogin getDataLogin() {
        return dataLogin;
    }

    /**
     * @param dataLogin the dataLogin to set
     */
    public void setDataLogin(DataLogin dataLogin) {
        this.dataLogin = dataLogin;
    }

    /**
     * @return the dataPengajuans
     */
    public List<DataPengajuan> getDataPengajuans() {
        return dataPengajuans;
    }

    /**
     * @param dataPengajuans the dataPengajuans to set
     */
    public void setDataPengajuans(List<DataPengajuan> dataPengajuans) {
        this.dataPengajuans = dataPengajuans;
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
     * @return the dataKerusakans
     */
    public List<DataKerusakan> getDataKerusakans() {
        return dataKerusakans;
    }

    /**
     * @param dataKerusakans the dataKerusakans to set
     */
    public void setDataKerusakans(List<DataKerusakan> dataKerusakans) {
        this.dataKerusakans = dataKerusakans;
    }

    /**
     * @return the nik
     */
    public String getNik() {
        return nik;
    }

    /**
     * @param nik the nik to set
     */
    public void setNik(String nik) {
        this.nik = nik;
    }

    /**
     * @return the jabatan
     */
    public String getJabatan() {
        return jabatan;
    }

    /**
     * @param jabatan the jabatan to set
     */
    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    /**
     * @return the nomorTelpon
     */
    public String getNomorTelpon() {
        return nomorTelpon;
    }

    /**
     * @param nomorTelpon the nomorTelpon to set
     */
    public void setNomorTelpon(String nomorTelpon) {
        this.nomorTelpon = nomorTelpon;
    }

    /**
     * @return the alamat
     */
    public String getAlamat() {
        return alamat;
    }

    /**
     * @param alamat the alamat to set
     */
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    /**
     * @return the ktp
     */
    public String getKtp() {
        return ktp;
    }

    /**
     * @param ktp the ktp to set
     */
    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    
    
}
