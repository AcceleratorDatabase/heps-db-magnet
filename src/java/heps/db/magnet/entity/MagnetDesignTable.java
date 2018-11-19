/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.magnet.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author qiaoys
 */
@Entity
@Table(name = "magnet_design_table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MagnetDesignTable.findAll", query = "SELECT m FROM MagnetDesignTable m")
    , @NamedQuery(name = "MagnetDesignTable.findByDesignId", query = "SELECT m FROM MagnetDesignTable m WHERE m.designId = :designId")
    , @NamedQuery(name = "MagnetDesignTable.findByType", query = "SELECT m FROM MagnetDesignTable m WHERE m.type = :type")
    , @NamedQuery(name = "MagnetDesignTable.findByFamily", query = "SELECT m FROM MagnetDesignTable m WHERE m.family = :family")    
    , @NamedQuery(name = "MagnetDesignTable.findByDesignName", query = "SELECT m FROM MagnetDesignTable m WHERE m.designName = :designName")
    , @NamedQuery(name = "MagnetDesignTable.findByProject", query = "SELECT m FROM MagnetDesignTable m WHERE m.project = :project")
    , @NamedQuery(name = "MagnetDesignTable.findByDesignBy", query = "SELECT m FROM MagnetDesignTable m WHERE m.designBy = :designBy")
    , @NamedQuery(name = "MagnetDesignTable.findByApprovedBy", query = "SELECT m FROM MagnetDesignTable m WHERE m.approvedBy = :approvedBy")
    , @NamedQuery(name = "MagnetDesignTable.findByRemark", query = "SELECT m FROM MagnetDesignTable m WHERE m.remark = :remark")})
public class MagnetDesignTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "design_id")
    private Integer designId;
    @Size(max = 45)
    @Column(name = "type")
    private String type;
    @Size(max = 45)
    @Column(name = "family")
    private String family;   
    @Size(max = 45)
    @Column(name = "design_name")
    private String designName;
    @Size(max = 45)
    @Column(name = "project")
    private String project;
    @Size(max = 45)
    @Column(name = "design_by")
    private String designBy;
    @Size(max = 45)
    @Column(name = "approved_by")
    private String approvedBy;
    @Size(max = 255)
    @Column(name = "remark")
    private String remark;
    @OneToMany(mappedBy = "designId")
    private Collection<EquipmentInfoTable> equipmentInfoTableCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "magnetDesignTable")
    private MagnetDesignRequirementTable magnetDesignRequirementTable;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "magnetDesignTable")
    private MagnetDesignParameterTable magnetDesignParameterTable;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "designId")
    private Collection<DesignOthersTable> designOthersTableCollection;

    public MagnetDesignTable() {
    }

    public MagnetDesignTable(Integer designId) {
        this.designId = designId;
    }

    public Integer getDesignId() {
        return designId;
    }

    public void setDesignId(Integer designId) {
        this.designId = designId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getDesignName() {
        return designName;
    }

    public void setDesignName(String designName) {
        this.designName = designName;
    }
     public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDesignBy() {
        return designBy;
    }

    public void setDesignBy(String designBy) {
        this.designBy = designBy;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @XmlTransient
    public Collection<EquipmentInfoTable> getEquipmentInfoTableCollection() {
        return equipmentInfoTableCollection;
    }

    public void setEquipmentInfoTableCollection(Collection<EquipmentInfoTable> equipmentInfoTableCollection) {
        this.equipmentInfoTableCollection = equipmentInfoTableCollection;
    }

    public MagnetDesignRequirementTable getMagnetDesignRequirementTable() {
        return magnetDesignRequirementTable;
    }

    public void setMagnetDesignRequirementTable(MagnetDesignRequirementTable magnetDesignRequirementTable) {
        this.magnetDesignRequirementTable = magnetDesignRequirementTable;
    }

    public MagnetDesignParameterTable getMagnetDesignParameterTable() {
        return magnetDesignParameterTable;
    }

    public void setMagnetDesignParameterTable(MagnetDesignParameterTable magnetDesignParameterTable) {
        this.magnetDesignParameterTable = magnetDesignParameterTable;
    }

    @XmlTransient
    public Collection<DesignOthersTable> getDesignOthersTableCollection() {
        return designOthersTableCollection;
    }

    public void setDesignOthersTableCollection(Collection<DesignOthersTable> designOthersTableCollection) {
        this.designOthersTableCollection = designOthersTableCollection;
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
        if (!(object instanceof MagnetDesignTable)) {
            return false;
        }
        MagnetDesignTable other = (MagnetDesignTable) object;
        if ((this.designId == null && other.designId != null) || (this.designId != null && !this.designId.equals(other.designId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "heps.db.magnet.entity.MagnetDesignTable[ designId=" + designId + " ]";
    return "{\"designid\":\"" + designId + "\"," + "\"magtype\":\"" + type + "\"," + "\"magfamily\":\"" + family + 
            "\"," + "\"magproject\":\"" + project+ "\","+"\"designedby\":\"" + designBy + "\"," + "\"approvedby\":\"" + approvedBy + "\"," + "\"remark\":\"" 
            + remark + "\"," + "\"length\":\"" + magnetDesignRequirementTable.getElength() +  "\"," + "\"aperture\":\"" +
            magnetDesignRequirementTable.getAperture()+ "\"," + "\"min_gap\":\"" + magnetDesignRequirementTable.getMinimumGap()+
            "\"," + "\"useful_field\":\"" + magnetDesignRequirementTable.getUsefulField()+"\"," + "\"intensityB\":\"" + 
            magnetDesignRequirementTable.getIntensityB()+"\"," + "\"intensityQ\":\"" + magnetDesignRequirementTable.getIntensityQ()+
            "\"," + "\"intensityS\":\"" + magnetDesignRequirementTable.getIntensityS()+"\"," + "\"intensityO\":\"" + 
            magnetDesignRequirementTable.getIntensityO()+"\"," + "\"sys\":\"" + magnetDesignRequirementTable.getSystemComponent()+
            "\"," + "\"non_sys\":\"" + magnetDesignRequirementTable.getNonSystemComponent()+"\"," + "\"offset\":\"" + 
            magnetDesignParameterTable.getOffset()+"\"," + "\"ampere_turns\":\"" + magnetDesignParameterTable.getAmpereTurns()+
            "\"," + "\"ampere_turns_each\":\"" + magnetDesignParameterTable.getAmpereTurnsEach()+"\"," + "\"current\":\"" +
            magnetDesignParameterTable.getCur()+ "\"," + "\"wire\":\"" + magnetDesignParameterTable.getWire()+ 
            "\"," + "\"current_density\":\"" + magnetDesignParameterTable.getCurrentDensity()+ "\"," + "\"wire_length\":\"" +
            magnetDesignParameterTable.getWireLength()+ "\"," + "\"resistence\":\"" + magnetDesignParameterTable.getResistance()+ 
            "\"," + "\"inductance\":\"" + magnetDesignParameterTable.getInductance()+ "\"," + "\"voltage\":\"" + 
            magnetDesignParameterTable.getVoltage()+ "\"," + "\"consumption\":\"" + magnetDesignParameterTable.getConsumption()+
            "\"," + "\"c_pressure_drop\":\"" + magnetDesignParameterTable.getCPressureDrop()+ "\"," + "\"c_channel_num\":\"" +
            magnetDesignParameterTable.getCChannelNum()+ "\"," + "\"c_velocity\":\"" + magnetDesignParameterTable.getCVelocity()+
            "\"," + "\"c_flow\":\"" + magnetDesignParameterTable.getCFlow()+ "\"," + "\"c_temp\":\"" + 
            magnetDesignParameterTable.getCTemp()+ "\"," + "\"core_length\":\"" + magnetDesignParameterTable.getCoreLength()+ 
            "\"," + "\"core_section\":\"" + magnetDesignParameterTable.getCoreSection()+ "\"," + "\"core_weight\":\"" + 
            magnetDesignParameterTable.getCoreWeight()+ "\"," + "\"copper_weight\":\"" + magnetDesignParameterTable.getCopperWeight()+ 
            "\"," + "\"pplot\":\"" + magnetDesignParameterTable.getPhysicsPlot()+ "\"," + "\"mplot\":\"" + 
            magnetDesignParameterTable.getMechanicalPlot()+"\"}";
    }
    
}
