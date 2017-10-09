/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.magnet.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author qiaoys
 */
@Entity
@Table(name = "magnet_design_parameter_table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MagnetDesignParameterTable.findAll", query = "SELECT m FROM MagnetDesignParameterTable m")
    , @NamedQuery(name = "MagnetDesignParameterTable.findByDesignId", query = "SELECT m FROM MagnetDesignParameterTable m WHERE m.designId = :designId")
    , @NamedQuery(name = "MagnetDesignParameterTable.findByOffset", query = "SELECT m FROM MagnetDesignParameterTable m WHERE m.offset = :offset")
    , @NamedQuery(name = "MagnetDesignParameterTable.findByAmpereTurns", query = "SELECT m FROM MagnetDesignParameterTable m WHERE m.ampereTurns = :ampereTurns")
    , @NamedQuery(name = "MagnetDesignParameterTable.findByAmpereTurnsEach", query = "SELECT m FROM MagnetDesignParameterTable m WHERE m.ampereTurnsEach = :ampereTurnsEach")
    , @NamedQuery(name = "MagnetDesignParameterTable.findByCurrent", query = "SELECT m FROM MagnetDesignParameterTable m WHERE m.current = :current")
    , @NamedQuery(name = "MagnetDesignParameterTable.findByWire", query = "SELECT m FROM MagnetDesignParameterTable m WHERE m.wire = :wire")
    , @NamedQuery(name = "MagnetDesignParameterTable.findByCurrentDensity", query = "SELECT m FROM MagnetDesignParameterTable m WHERE m.currentDensity = :currentDensity")
    , @NamedQuery(name = "MagnetDesignParameterTable.findByWireLength", query = "SELECT m FROM MagnetDesignParameterTable m WHERE m.wireLength = :wireLength")
    , @NamedQuery(name = "MagnetDesignParameterTable.findByResistance", query = "SELECT m FROM MagnetDesignParameterTable m WHERE m.resistance = :resistance")
    , @NamedQuery(name = "MagnetDesignParameterTable.findByInductance", query = "SELECT m FROM MagnetDesignParameterTable m WHERE m.inductance = :inductance")
    , @NamedQuery(name = "MagnetDesignParameterTable.findByVoltage", query = "SELECT m FROM MagnetDesignParameterTable m WHERE m.voltage = :voltage")
    , @NamedQuery(name = "MagnetDesignParameterTable.findByConsumption", query = "SELECT m FROM MagnetDesignParameterTable m WHERE m.consumption = :consumption")
    , @NamedQuery(name = "MagnetDesignParameterTable.findByCPressureDrop", query = "SELECT m FROM MagnetDesignParameterTable m WHERE m.cPressureDrop = :cPressureDrop")
    , @NamedQuery(name = "MagnetDesignParameterTable.findByCChannelNum", query = "SELECT m FROM MagnetDesignParameterTable m WHERE m.cChannelNum = :cChannelNum")
    , @NamedQuery(name = "MagnetDesignParameterTable.findByCVelocity", query = "SELECT m FROM MagnetDesignParameterTable m WHERE m.cVelocity = :cVelocity")
    , @NamedQuery(name = "MagnetDesignParameterTable.findByCFlow", query = "SELECT m FROM MagnetDesignParameterTable m WHERE m.cFlow = :cFlow")
    , @NamedQuery(name = "MagnetDesignParameterTable.findByCTemp", query = "SELECT m FROM MagnetDesignParameterTable m WHERE m.cTemp = :cTemp")
    , @NamedQuery(name = "MagnetDesignParameterTable.findByCoreLength", query = "SELECT m FROM MagnetDesignParameterTable m WHERE m.coreLength = :coreLength")
    , @NamedQuery(name = "MagnetDesignParameterTable.findByCoreSection", query = "SELECT m FROM MagnetDesignParameterTable m WHERE m.coreSection = :coreSection")
    , @NamedQuery(name = "MagnetDesignParameterTable.findByCoreWeight", query = "SELECT m FROM MagnetDesignParameterTable m WHERE m.coreWeight = :coreWeight")
    , @NamedQuery(name = "MagnetDesignParameterTable.findByCopperWeight", query = "SELECT m FROM MagnetDesignParameterTable m WHERE m.copperWeight = :copperWeight")
    , @NamedQuery(name = "MagnetDesignParameterTable.findByPhysicsPlot", query = "SELECT m FROM MagnetDesignParameterTable m WHERE m.physicsPlot = :physicsPlot")
    , @NamedQuery(name = "MagnetDesignParameterTable.findByMechanicalPlot", query = "SELECT m FROM MagnetDesignParameterTable m WHERE m.mechanicalPlot = :mechanicalPlot")})
public class MagnetDesignParameterTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "design_id")
    private Integer designId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "offset")
    private Double offset;
    @Column(name = "ampere_turns")
    private Double ampereTurns;
    @Column(name = "ampere_turns_each")
    private Double ampereTurnsEach;
    @Column(name = "current")
    private Double current;
    @Size(max = 45)
    @Column(name = "wire")
    private String wire;
    @Column(name = "current_density")
    private Double currentDensity;
    @Column(name = "wire_length")
    private Double wireLength;
    @Column(name = "resistance")
    private Double resistance;
    @Column(name = "inductance")
    private Double inductance;
    @Column(name = "voltage")
    private Double voltage;
    @Column(name = "consumption")
    private Double consumption;
    @Column(name = "c_pressure_drop")
    private Double cPressureDrop;
    @Column(name = "c_channel_num")
    private Double cChannelNum;
    @Column(name = "c_velocity")
    private Double cVelocity;
    @Column(name = "c_flow")
    private Double cFlow;
    @Column(name = "c_temp")
    private Double cTemp;
    @Column(name = "core_length")
    private Double coreLength;
    @Column(name = "core_section")
    private Double coreSection;
    @Column(name = "core_weight")
    private Double coreWeight;
    @Column(name = "copper_weight")
    private Double copperWeight;
    @Size(max = 45)
    @Column(name = "physics_plot")
    private String physicsPlot;
    @Size(max = 45)
    @Column(name = "mechanical_plot")
    private String mechanicalPlot;
    @JoinColumn(name = "design_id", referencedColumnName = "design_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private MagnetDesignTable magnetDesignTable;

    public MagnetDesignParameterTable() {
    }

    public MagnetDesignParameterTable(Integer designId) {
        this.designId = designId;
    }

    public Integer getDesignId() {
        return designId;
    }

    public void setDesignId(Integer designId) {
        this.designId = designId;
    }

    public Double getOffset() {
        return offset;
    }

    public void setOffset(Double offset) {
        this.offset = offset;
    }

    public Double getAmpereTurns() {
        return ampereTurns;
    }

    public void setAmpereTurns(Double ampereTurns) {
        this.ampereTurns = ampereTurns;
    }

    public Double getAmpereTurnsEach() {
        return ampereTurnsEach;
    }

    public void setAmpereTurnsEach(Double ampereTurnsEach) {
        this.ampereTurnsEach = ampereTurnsEach;
    }

    public Double getCurrent() {
        return current;
    }

    public void setCurrent(Double current) {
        this.current = current;
    }

    public String getWire() {
        return wire;
    }

    public void setWire(String wire) {
        this.wire = wire;
    }

    public Double getCurrentDensity() {
        return currentDensity;
    }

    public void setCurrentDensity(Double currentDensity) {
        this.currentDensity = currentDensity;
    }

    public Double getWireLength() {
        return wireLength;
    }

    public void setWireLength(Double wireLength) {
        this.wireLength = wireLength;
    }

    public Double getResistance() {
        return resistance;
    }

    public void setResistance(Double resistance) {
        this.resistance = resistance;
    }

    public Double getInductance() {
        return inductance;
    }

    public void setInductance(Double inductance) {
        this.inductance = inductance;
    }

    public Double getVoltage() {
        return voltage;
    }

    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }

    public Double getConsumption() {
        return consumption;
    }

    public void setConsumption(Double consumption) {
        this.consumption = consumption;
    }

    public Double getCPressureDrop() {
        return cPressureDrop;
    }

    public void setCPressureDrop(Double cPressureDrop) {
        this.cPressureDrop = cPressureDrop;
    }

    public Double getCChannelNum() {
        return cChannelNum;
    }

    public void setCChannelNum(Double cChannelNum) {
        this.cChannelNum = cChannelNum;
    }

    public Double getCVelocity() {
        return cVelocity;
    }

    public void setCVelocity(Double cVelocity) {
        this.cVelocity = cVelocity;
    }

    public Double getCFlow() {
        return cFlow;
    }

    public void setCFlow(Double cFlow) {
        this.cFlow = cFlow;
    }

    public Double getCTemp() {
        return cTemp;
    }

    public void setCTemp(Double cTemp) {
        this.cTemp = cTemp;
    }

    public Double getCoreLength() {
        return coreLength;
    }

    public void setCoreLength(Double coreLength) {
        this.coreLength = coreLength;
    }

    public Double getCoreSection() {
        return coreSection;
    }

    public void setCoreSection(Double coreSection) {
        this.coreSection = coreSection;
    }

    public Double getCoreWeight() {
        return coreWeight;
    }

    public void setCoreWeight(Double coreWeight) {
        this.coreWeight = coreWeight;
    }

    public Double getCopperWeight() {
        return copperWeight;
    }

    public void setCopperWeight(Double copperWeight) {
        this.copperWeight = copperWeight;
    }

    public String getPhysicsPlot() {
        return physicsPlot;
    }

    public void setPhysicsPlot(String physicsPlot) {
        this.physicsPlot = physicsPlot;
    }

    public String getMechanicalPlot() {
        return mechanicalPlot;
    }

    public void setMechanicalPlot(String mechanicalPlot) {
        this.mechanicalPlot = mechanicalPlot;
    }

    public MagnetDesignTable getMagnetDesignTable() {
        return magnetDesignTable;
    }

    public void setMagnetDesignTable(MagnetDesignTable magnetDesignTable) {
        this.magnetDesignTable = magnetDesignTable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (designId != null ? designId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MagnetDesignParameterTable)) {
            return false;
        }
        MagnetDesignParameterTable other = (MagnetDesignParameterTable) object;
        if ((this.designId == null && other.designId != null) || (this.designId != null && !this.designId.equals(other.designId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "heps.db.magnet.entity.MagnetDesignParameterTable[ designId=" + designId + " ]";
    }
    
}
