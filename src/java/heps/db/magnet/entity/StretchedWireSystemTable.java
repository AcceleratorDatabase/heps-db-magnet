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
@Table(name = "stretched_wire_system_table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StretchedWireSystemTable.findAll", query = "SELECT s FROM StretchedWireSystemTable s")
    , @NamedQuery(name = "StretchedWireSystemTable.findBySwRunId", query = "SELECT s FROM StretchedWireSystemTable s WHERE s.swRunId = :swRunId")
    , @NamedQuery(name = "StretchedWireSystemTable.findBySamplingRate", query = "SELECT s FROM StretchedWireSystemTable s WHERE s.samplingRate = :samplingRate")
    , @NamedQuery(name = "StretchedWireSystemTable.findBySpeed", query = "SELECT s FROM StretchedWireSystemTable s WHERE s.speed = :speed")
    , @NamedQuery(name = "StretchedWireSystemTable.findByAcceleratedSpeed", query = "SELECT s FROM StretchedWireSystemTable s WHERE s.acceleratedSpeed = :acceleratedSpeed")
    , @NamedQuery(name = "StretchedWireSystemTable.findByDistance", query = "SELECT s FROM StretchedWireSystemTable s WHERE s.distance = :distance")
    , @NamedQuery(name = "StretchedWireSystemTable.findByStartEX0", query = "SELECT s FROM StretchedWireSystemTable s WHERE s.startEX0 = :startEX0")
    , @NamedQuery(name = "StretchedWireSystemTable.findByStartEY0", query = "SELECT s FROM StretchedWireSystemTable s WHERE s.startEY0 = :startEY0")
    , @NamedQuery(name = "StretchedWireSystemTable.findByStrain", query = "SELECT s FROM StretchedWireSystemTable s WHERE s.strain = :strain")
    , @NamedQuery(name = "StretchedWireSystemTable.findByMeasCurrent", query = "SELECT s FROM StretchedWireSystemTable s WHERE s.measCurrent = :measCurrent")
    , @NamedQuery(name = "StretchedWireSystemTable.findByCutOffFrequency", query = "SELECT s FROM StretchedWireSystemTable s WHERE s.cutOffFrequency = :cutOffFrequency")
    , @NamedQuery(name = "StretchedWireSystemTable.findByMeasBy", query = "SELECT s FROM StretchedWireSystemTable s WHERE s.measBy = :measBy")
    , @NamedQuery(name = "StretchedWireSystemTable.findByMeasDate", query = "SELECT s FROM StretchedWireSystemTable s WHERE s.measDate = :measDate")
    , @NamedQuery(name = "StretchedWireSystemTable.findByMeasAt", query = "SELECT s FROM StretchedWireSystemTable s WHERE s.measAt = :measAt")
    , @NamedQuery(name = "StretchedWireSystemTable.findByDescription", query = "SELECT s FROM StretchedWireSystemTable s WHERE s.description = :description")})
public class StretchedWireSystemTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sw_run_id")
    private Integer swRunId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sampling_rate")
    private Double samplingRate;
    @Column(name = "speed")
    private Double speed;
    @Column(name = "accelerated_speed")
    private Double acceleratedSpeed;
    @Column(name = "distance")
    private Double distance;
    @Column(name = "start_EX0")
    private Double startEX0;
    @Column(name = "start_EY0")
    private Double startEY0;
    @Column(name = "strain")
    private Double strain;
    @Column(name = "meas_current")
    private Double measCurrent;
    @Column(name = "cut_off_frequency")
    private Double cutOffFrequency;
    @Size(max = 45)
    @Column(name = "meas_by")
    private String measBy;
    @Column(name = "meas_date")
    @Temporal(TemporalType.DATE)
    private Date measDate;
    @Size(max = 45)
    @Column(name = "meas_at")
    private String measAt;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "runId")
    private Collection<SwsDataTable> swsDataTableCollection;
    @JoinColumn(name = "device_id", referencedColumnName = "device_id")
    @ManyToOne
    private DeviceInfoTable deviceId;

    public StretchedWireSystemTable() {
    }

    public StretchedWireSystemTable(Integer swRunId) {
        this.swRunId = swRunId;
    }

    public Integer getSwRunId() {
        return swRunId;
    }

    public void setSwRunId(Integer swRunId) {
        this.swRunId = swRunId;
    }

    public Double getSamplingRate() {
        return samplingRate;
    }

    public void setSamplingRate(Double samplingRate) {
        this.samplingRate = samplingRate;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getAcceleratedSpeed() {
        return acceleratedSpeed;
    }

    public void setAcceleratedSpeed(Double acceleratedSpeed) {
        this.acceleratedSpeed = acceleratedSpeed;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getStartEX0() {
        return startEX0;
    }

    public void setStartEX0(Double startEX0) {
        this.startEX0 = startEX0;
    }

    public Double getStartEY0() {
        return startEY0;
    }

    public void setStartEY0(Double startEY0) {
        this.startEY0 = startEY0;
    }

    public Double getStrain() {
        return strain;
    }

    public void setStrain(Double strain) {
        this.strain = strain;
    }

    public Double getMeasCurrent() {
        return measCurrent;
    }

    public void setMeasCurrent(Double measCurrent) {
        this.measCurrent = measCurrent;
    }

    public Double getCutOffFrequency() {
        return cutOffFrequency;
    }

    public void setCutOffFrequency(Double cutOffFrequency) {
        this.cutOffFrequency = cutOffFrequency;
    }

    public String getMeasBy() {
        return measBy;
    }

    public void setMeasBy(String measBy) {
        this.measBy = measBy;
    }

    public Date getMeasDate() {
        return measDate;
    }

    public void setMeasDate(Date measDate) {
        this.measDate = measDate;
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
    public Collection<SwsDataTable> getSwsDataTableCollection() {
        return swsDataTableCollection;
    }

    public void setSwsDataTableCollection(Collection<SwsDataTable> swsDataTableCollection) {
        this.swsDataTableCollection = swsDataTableCollection;
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
        hash += (swRunId != null ? swRunId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StretchedWireSystemTable)) {
            return false;
        }
        StretchedWireSystemTable other = (StretchedWireSystemTable) object;
        if ((this.swRunId == null && other.swRunId != null) || (this.swRunId != null && !this.swRunId.equals(other.swRunId))) {
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
        return "{\"runid\":\"" + swRunId + "\"," + "\"samplingRate\":\"" + samplingRate + "\"," + "\"speed\":\"" + speed
                + "\"," + "\"acceleratedSpeed\":\"" + acceleratedSpeed + "\"," + "\"distance\":\"" + distance + "\"," 
                + "\"startEX0\":\"" + startEX0 + "\"," + "\"startEY0\":\"" + startEY0 + "\"," + "\"strain\":\"" + strain 
                + "\"," + "\"measCurrent\":\"" + measCurrent + "\"," + "\"cutOffFrequency\":\"" + cutOffFrequency + "\","
                + "\"measDate\":\"" + DateToString(measDate) + "\"," + "\"measBy\":\"" + measBy + "\"," + "\"measAt\":\"" 
                + measAt + "\"," + "\"description\":\"" + description + "\"}";
    }
    
}
