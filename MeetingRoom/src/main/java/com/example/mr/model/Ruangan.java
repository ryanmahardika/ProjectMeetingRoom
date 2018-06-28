package com.example.mr.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Ruangan extends Tambahan implements Serializable {

    @OneToMany(mappedBy = "ruangan")
    private List<DataKerusakan> dataKerusakans;

    @OneToMany(mappedBy = "ruangan")
    private List<DataPengajuan> dataPengajuans;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nama_ruangan",insertable = true,length = 25,nullable = false,unique = true)
    private String namaRuangan;
    @Column(name = "kapasitas",insertable = true,nullable = false)
    private int kapasitas;
    @Column(name = "status_ruangan",updatable = true,nullable = false,length = 20,insertable = true)
    private String statusRuangan;
    @Column(name = "fasilitas_utama",insertable = true,nullable = true,length = 100,updatable = true)
    private String fasilitasUtama;
	@Column(name = "fasilitas_tambahan",insertable = true,nullable = true,length = 100,updatable = true)
    private String fasilitasTambahan;

    public Ruangan(){
        statusRuangan = "Tersedia";
        keterangan = "Aktif";
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
        if (!(object instanceof Ruangan)) {
            return false;
        }
        Ruangan other = (Ruangan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Ruangan[ id=" + id + " ]";
    }

    /**
     * @return the namaRuangan
     */
    public String getNamaRuangan() {
        return namaRuangan;
    }

    /**
     * @param namaRuangan the namaRuangan to set
     */
    public void setNamaRuangan(String namaRuangan) {
        this.namaRuangan = namaRuangan;
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
     * @return the kapasitas
     */
    public int getKapasitas() {
        return kapasitas;
    }

    /**
     * @param kapasitas the kapasitas to set
     */
    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    /**
     * @return the statusRuangan
     */
    public String getStatusRuangan() {
        return statusRuangan;
    }

    /**
     * @param statusRuangan the statusRuangan to set
     */
    public void setStatusRuangan(String statusRuangan) {
        this.statusRuangan = statusRuangan;
    }

    /**
     * @return the fasilitasTambahan
     */
    public String getFasilitasTambahan() {
        return fasilitasTambahan;
    }

    /**
     * @param fasilitasTambahan the fasilitasTambahan to set
     */
    public void setFasilitasTambahan(String fasilitasTambahan) {
        this.fasilitasTambahan = fasilitasTambahan;
    }
    
    public String getFasilitasUtama() {
		return fasilitasUtama;
	}

	public void setFasilitasUtama(String fasilitasUtama) {
		this.fasilitasUtama = fasilitasUtama;
	}
    
}

