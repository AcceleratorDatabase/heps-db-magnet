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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author qiaoys
 */
@Entity
@Table(name = "rot_coil_system_table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RotCoilSystemTable.findAll", query = "SELECT r FROM RotCoilSystemTable r")
    , @NamedQuery(name = "RotCoilSystemTable.findByRotCoilRunId", query = "SELECT r FROM RotCoilSystemTable r WHERE r.rotCoilRunId = :rotCoilRunId")
    , @NamedQuery(name = "RotCoilSystemTable.findByPolarity", query = "SELECT r FROM RotCoilSystemTable r WHERE r.polarity = :polarity")
    , @NamedQuery(name = "RotCoilSystemTable.findByGivenCurrent", query = "SELECT r FROM RotCoilSystemTable r WHERE r.givenCurrent = :givenCurrent")
    , @NamedQuery(name = "RotCoilSystemTable.findByActualCurrent", query = "SELECT r FROM RotCoilSystemTable r WHERE r.actualCurrent = :actualCurrent")
    , @NamedQuery(name = "RotCoilSystemTable.findByGain", query = "SELECT r FROM RotCoilSystemTable r WHERE r.gain = :gain")
    , @NamedQuery(name = "RotCoilSystemTable.findByStartPosition", query = "SELECT r FROM RotCoilSystemTable r WHERE r.startPosition = :startPosition")
    , @NamedQuery(name = "RotCoilSystemTable.findByRotationRate", query = "SELECT r FROM RotCoilSystemTable r WHERE r.rotationRate = :rotationRate")
    , @NamedQuery(name = "RotCoilSystemTable.findByRRef", query = "SELECT r FROM RotCoilSystemTable r WHERE r.rRef = :rRef")
    , @NamedQuery(name = "RotCoilSystemTable.findByDx", query = "SELECT r FROM RotCoilSystemTable r WHERE r.dx = :dx")
    , @NamedQuery(name = "RotCoilSystemTable.findByDy", query = "SELECT r FROM RotCoilSystemTable r WHERE r.dy = :dy")
    , @NamedQuery(name = "RotCoilSystemTable.findByDr", query = "SELECT r FROM RotCoilSystemTable r WHERE r.dr = :dr")
    , @NamedQuery(name = "RotCoilSystemTable.findByMeasDate", query = "SELECT r FROM RotCoilSystemTable r WHERE r.measDate = :measDate")
    , @NamedQuery(name = "RotCoilSystemTable.findByMeasBy", query = "SELECT r FROM RotCoilSystemTable r WHERE r.measBy = :measBy")
    , @NamedQuery(name = "RotCoilSystemTable.findByMeasAt", query = "SELECT r FROM RotCoilSystemTable r WHERE r.measAt = :measAt")
    , @NamedQuery(name = "RotCoilSystemTable.findByDescription", query = "SELECT r FROM RotCoilSystemTable r WHERE r.description = :description")})
public class RotCoilSystemTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rot_coil_run_id")
    private Integer rotCoilRunId;
     @Size(max = 45)
    @Column(name = "polarity")
    private String polarity;    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "given_current")
    private Double givenCurrent;
    @Column(name = "actual_current")
    private Double actualCurrent;   
    @Column(name = "gain")
    private Double gain;
    @Column(name = "start_position")
    private Double startPosition;
    @Column(name = "rotation_rate")
    private Double rotationRate;
    @Column(name = "r_ref")
    private Double rRef;
    @Column(name = "dx")
    private Double dx;
    @Column(name = "dy")
    private Double dy;
    @Column(name = "dr")
    private Double dr;    
    @Column(name = "meas_date")
    @Temporal(TemporalType.DATE)
    private Date measDate;
    @Size(max = 45)
    @Column(name = "meas_by")
    private String measBy;
    @Size(max = 45)
    @Column(name = "meas_at")
    private String measAt;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "device_id", referencedColumnName = "device_id")
    @ManyToOne
    private DeviceInfoTable deviceId;
    @OneToMany(mappedBy = "runId")
    private Collection<RcsDataTable> rcsDataTableCollection;
    @OneToMany(mappedBy = "runId")
    private Collection<RcsDataAllTable> rcsDataAllTableCollection;

    public RotCoilSystemTable() {
    }

    public RotCoilSystemTable(Integer rotCoilRunId) {
        this.rotCoilRunId = rotCoilRunId;
    }

    public Integer getRotCoilRunId() {
        return rotCoilRunId;
    }

    public void setRotCoilRunId(Integer rotCoilRunId) {
        this.rotCoilRunId = rotCoilRunId;
    }

    public String getPolarity() {
        return polarity;
    }

    public void setPolarity(String polarity) {
        this.polarity = polarity;
    }    

    public Double getGivenCurrent() {
        return givenCurrent;
    }

    public void setGivenCurrent(Double givenCurrent) {
        this.givenCurrent = givenCurrent;
    }

    public Double getActualCurrent() {
        return actualCurrent;
    }

    public void setActualCurrent(Double actualCurrent) {
        this.actualCurrent = actualCurrent;
    }

    public Double getGain() {
        return gain;
    }

    public void setGain(Double gain) {
        this.gain = gain;
    }

    public Double getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Double startPosition) {
        this.startPosition = startPosition;
    }

    public Double getRotationRate() {
        return rotationRate;
    }

    public void setRotationRate(Double rotationRate) {
        this.rotationRate = rotationRate;
    }

    public Double getRRef() {
        return rRef;
    }

    public void setRRef(Double rRef) {
        this.rRef = rRef;
    }

    public Double getDx() {
        return dx;
    }

    public void setDx(Double dx) {
        this.dx = dx;
    }

    public Double getDy() {
        return dy;
    }

    public void setDy(Double dy) {
        this.dy = dy;
    }

    public Double getDr() {
        return dr;
    }

    public void setDr(Double dr) {
        this.dr = dr;
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

    public DeviceInfoTable getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(DeviceInfoTable deviceId) {
        this.deviceId = deviceId;
    }

    @XmlTransient
    public Collection<RcsDataTable> getRcsDataTableCollection() {
        return rcsDataTableCollection;
    }

    public void setRcsDataTableCollection(Collection<RcsDataTable> rcsDataTableCollection) {
        this.rcsDataTableCollection = rcsDataTableCollection;
    }

    @XmlTransient
    public Collection<RcsDataAllTable> getRcsDataAllTableCollection() {
        return rcsDataAllTableCollection;
    }

    public void setRcsDataAllTableCollection(Collection<RcsDataAllTable> rcsDataAllTableCollection) {
        this.rcsDataAllTableCollection = rcsDataAllTableCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rotCoilRunId != null ? rotCoilRunId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RotCoilSystemTable)) {
            return false;
        }
        RotCoilSystemTable other = (RotCoilSystemTable) object;
        if ((this.rotCoilRunId == null && other.rotCoilRunId != null) || (this.rotCoilRunId != null && !this.rotCoilRunId.equals(other.rotCoilRunId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "heps.db.magnet.entity.RotCoilSystemTable[ rotCoilRunId=" + rotCoilRunId + " ]";
    }
    
}
