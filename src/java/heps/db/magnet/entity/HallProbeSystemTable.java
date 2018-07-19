/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.magnet.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author qiaoys
 */
@Entity
@Table(name = "hall_probe_system_table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HallProbeSystemTable.findAll", query = "SELECT h FROM HallProbeSystemTable h")
    , @NamedQuery(name = "HallProbeSystemTable.findByHallProbeRunId", query = "SELECT h FROM HallProbeSystemTable h WHERE h.hallProbeRunId = :hallProbeRunId")
    , @NamedQuery(name = "HallProbeSystemTable.findByMeasCurrent", query = "SELECT h FROM HallProbeSystemTable h WHERE h.measCurrent = :measCurrent")
    , @NamedQuery(name = "HallProbeSystemTable.findByWaterGage", query = "SELECT h FROM HallProbeSystemTable h WHERE h.waterGage = :waterGage")
    , @NamedQuery(name = "HallProbeSystemTable.findByMeasDate", query = "SELECT h FROM HallProbeSystemTable h WHERE h.measDate = :measDate")
    , @NamedQuery(name = "HallProbeSystemTable.findByMeasBy", query = "SELECT h FROM HallProbeSystemTable h WHERE h.measBy = :measBy")
    , @NamedQuery(name = "HallProbeSystemTable.findByMeasAt", query = "SELECT h FROM HallProbeSystemTable h WHERE h.measAt = :measAt")
    , @NamedQuery(name = "HallProbeSystemTable.findByDescription", query = "SELECT h FROM HallProbeSystemTable h WHERE h.description = :description")})
public class HallProbeSystemTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "hall_probe_run_id")
    private Integer hallProbeRunId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "meas_current")
    private Double measCurrent;
    @Column(name = "water_gage")
    private Double waterGage;
    @Column(name = "meas_date")
    @Temporal(TemporalType.DATE)
    private Date measDate;
    @Size(max = 45)
    @Column(name = "meas_by")
    private String measBy;
    @Size(max = 45)
    @Column(name = "meas_at")
    private String measAt;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "runId")
    private Collection<HallDataAllTable> hallDataAllTableCollection;
    @OneToMany(mappedBy = "runId")
    private Collection<HallDataTable> hallDataTableCollection;
    @JoinColumn(name = "device_id", referencedColumnName = "device_id")
    @ManyToOne
    private DeviceInfoTable deviceId;

    public HallProbeSystemTable() {
    }

    public HallProbeSystemTable(Integer hallProbeRunId) {
        this.hallProbeRunId = hallProbeRunId;
    }

    public Integer getHallProbeRunId() {
        return hallProbeRunId;
    }

    public void setHallProbeRunId(Integer hallProbeRunId) {
        this.hallProbeRunId = hallProbeRunId;
    }

    public Double getMeasCurrent() {
        return measCurrent;
    }

    public void setMeasCurrent(Double measCurrent) {
        this.measCurrent = measCurrent;
    }

    public Double getWaterGage() {
        return waterGage;
    }

    public void setWaterGage(Double waterGage) {
        this.waterGage = waterGage;
    }

    public Date getMeasDate() {
        return measDate;
    }

    public void setMeasDate(Date measDate) {
        this.measDate = measDate;
    }

    public String getMeasBy() {
        return measBy;
    }

    public void setMeasBy(String measBy) {
        this.measBy = measBy;
    }

    public String getMeasAt() {
        return measAt;
    }

    public void setMeasAt(String measAt) {
        this.measAt = measAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<HallDataAllTable> getHallDataAllTableCollection() {
        return hallDataAllTableCollection;
    }

    public void setHallDataAllTableCollection(Collection<HallDataAllTable> hallDataAllTableCollection) {
        this.hallDataAllTableCollection = hallDataAllTableCollection;
    }

    @XmlTransient
    public Collection<HallDataTable> getHallDataTableCollection() {
        return hallDataTableCollection;
    }

    public void setHallDataTableCollection(Collection<HallDataTable> hallDataTableCollection) {
        this.hallDataTableCollection = hallDataTableCollection;
    }

    public DeviceInfoTable getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(DeviceInfoTable deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hallProbeRunId != null ? hallProbeRunId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HallProbeSystemTable)) {
            return false;
        }
        HallProbeSystemTable other = (HallProbeSystemTable) object;
        if ((this.hallProbeRunId == null && other.hallProbeRunId != null) || (this.hallProbeRunId != null && !this.hallProbeRunId.equals(other.hallProbeRunId))) {
            return false;
        }
        return true;
    }

    public String DateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    @Override
    public String toString() {
        return "{\"runid\":\"" + hallProbeRunId + "\"," + "\"measCurrent\":\"" + measCurrent + "\"," + "\"waterGage\":\""
                + waterGage + "\"," + "\"measDate\":\"" + DateToString(measDate) + "\"," + "\"measBy\":\"" + measBy + "\","
                + "\"measAt\":\"" + measAt + "\"," + "\"description\":\"" + description + "\"}";
    }

} 
