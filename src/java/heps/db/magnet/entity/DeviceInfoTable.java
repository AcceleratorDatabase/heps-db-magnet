/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.magnet.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author qiaoys
 */
@Entity
@Table(name = "device_info_table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DeviceInfoTable.findAll", query = "SELECT d FROM DeviceInfoTable d")
    , @NamedQuery(name = "DeviceInfoTable.findByDeviceId", query = "SELECT d FROM DeviceInfoTable d WHERE d.deviceId = :deviceId")
    , @NamedQuery(name = "DeviceInfoTable.findByNumber", query = "SELECT d FROM DeviceInfoTable d WHERE d.number = :number")
    , @NamedQuery(name = "DeviceInfoTable.findByDeviceName", query = "SELECT d FROM DeviceInfoTable d WHERE d.deviceName = :deviceName")
    , @NamedQuery(name = "DeviceInfoTable.findByWeight", query = "SELECT d FROM DeviceInfoTable d WHERE d.weight = :weight")
    , @NamedQuery(name = "DeviceInfoTable.findBySeries", query = "SELECT d FROM DeviceInfoTable d WHERE d.series = :series")
    , @NamedQuery(name = "DeviceInfoTable.findByDateOfManu", query = "SELECT d FROM DeviceInfoTable d WHERE d.dateOfManu = :dateOfManu")
    , @NamedQuery(name = "DeviceInfoTable.findByDesignedBy", query = "SELECT d FROM DeviceInfoTable d WHERE d.designedBy = :designedBy")
    , @NamedQuery(name = "DeviceInfoTable.findByManuBy", query = "SELECT d FROM DeviceInfoTable d WHERE d.manuBy = :manuBy")
    , @NamedQuery(name = "DeviceInfoTable.findByDescription", query = "SELECT d FROM DeviceInfoTable d WHERE d.description = :description")})
public class DeviceInfoTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
   
    @Column(name = "device_id")
    private Integer deviceId;
    @Column(name = "number")
    private Integer number;
    @Size(max = 45)
    @Column(name = "device_name")
    private String deviceName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "weight")
    private Double weight;
    @Size(max = 45)
    @Column(name = "series")
    private String series;
    @Column(name = "date_of_manu")
    @Temporal(TemporalType.DATE)
    private Date dateOfManu;
    @Size(max = 45)
    @Column(name = "designed_by")
    private String designedBy;
    @Size(max = 45)
    @Column(name = "manu_by")
    private String manuBy;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "design_id", referencedColumnName = "design_id")
    @ManyToOne
    private MagnetDesignTable designId;
    @OneToMany(mappedBy = "deviceId")
    private Collection<StretchedWireSystemTable> stretchedWireSystemTableCollection;
    @OneToMany(mappedBy = "deviceId")
    private Collection<HallProbeSystemTable> hallProbeSystemTableCollection;
    @OneToMany(mappedBy = "deviceId")
    private Collection<RotCoilSystemTable> rotCoilSystemTableCollection;

    public DeviceInfoTable() {
    }

    public DeviceInfoTable(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Date getDateOfManu() {
        return dateOfManu;
    }

    public void setDateOfManu(Date dateOfManu) {
        this.dateOfManu = dateOfManu;
    }

    public String getDesignedBy() {
        return designedBy;
    }

    public void setDesignedBy(String designedBy) {
        this.designedBy = designedBy;
    }

    public String getManuBy() {
        return manuBy;
    }

    public void setManuBy(String manuBy) {
        this.manuBy = manuBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MagnetDesignTable getDesignId() {
        return designId;
    }

    public void setDesignId(MagnetDesignTable designId) {
        this.designId = designId;
    }

    @XmlTransient
    public Collection<StretchedWireSystemTable> getStretchedWireSystemTableCollection() {
        return stretchedWireSystemTableCollection;
    }

    public void setStretchedWireSystemTableCollection(Collection<StretchedWireSystemTable> stretchedWireSystemTableCollection) {
        this.stretchedWireSystemTableCollection = stretchedWireSystemTableCollection;
    }

    @XmlTransient
    public Collection<HallProbeSystemTable> getHallProbeSystemTableCollection() {
        return hallProbeSystemTableCollection;
    }

    public void setHallProbeSystemTableCollection(Collection<HallProbeSystemTable> hallProbeSystemTableCollection) {
        this.hallProbeSystemTableCollection = hallProbeSystemTableCollection;
    }

    @XmlTransient
    public Collection<RotCoilSystemTable> getRotCoilSystemTableCollection() {
        return rotCoilSystemTableCollection;
    }

    public void setRotCoilSystemTableCollection(Collection<RotCoilSystemTable> rotCoilSystemTableCollection) {
        this.rotCoilSystemTableCollection = rotCoilSystemTableCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deviceId != null ? deviceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeviceInfoTable)) {
            return false;
        }
        DeviceInfoTable other = (DeviceInfoTable) object;
        if ((this.deviceId == null && other.deviceId != null) || (this.deviceId != null && !this.deviceId.equals(other.deviceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "heps.db.magnet.entity.DeviceInfoTable[ deviceId=" + deviceId + " ]";
    }
    
}
