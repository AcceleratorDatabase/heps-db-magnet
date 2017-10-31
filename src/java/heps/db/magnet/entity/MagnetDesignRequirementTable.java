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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author qiaoys
 */
@Entity
@Table(name = "magnet_design_requirement_table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MagnetDesignRequirementTable.findAll", query = "SELECT m FROM MagnetDesignRequirementTable m")
    , @NamedQuery(name = "MagnetDesignRequirementTable.findByDesignId", query = "SELECT m FROM MagnetDesignRequirementTable m WHERE m.designId = :designId")
    , @NamedQuery(name = "MagnetDesignRequirementTable.findByLength", query = "SELECT m FROM MagnetDesignRequirementTable m WHERE m.length = :length")
    , @NamedQuery(name = "MagnetDesignRequirementTable.findByAperture", query = "SELECT m FROM MagnetDesignRequirementTable m WHERE m.aperture = :aperture")
    , @NamedQuery(name = "MagnetDesignRequirementTable.findByMinimumGap", query = "SELECT m FROM MagnetDesignRequirementTable m WHERE m.minimumGap = :minimumGap")
    , @NamedQuery(name = "MagnetDesignRequirementTable.findByUsefulField", query = "SELECT m FROM MagnetDesignRequirementTable m WHERE m.usefulField = :usefulField")
    , @NamedQuery(name = "MagnetDesignRequirementTable.findByIntensityB", query = "SELECT m FROM MagnetDesignRequirementTable m WHERE m.intensityB = :intensityB")
    , @NamedQuery(name = "MagnetDesignRequirementTable.findByIntensityQ", query = "SELECT m FROM MagnetDesignRequirementTable m WHERE m.intensityQ = :intensityQ")
    , @NamedQuery(name = "MagnetDesignRequirementTable.findByIntensityS", query = "SELECT m FROM MagnetDesignRequirementTable m WHERE m.intensityS = :intensityS")
    , @NamedQuery(name = "MagnetDesignRequirementTable.findByIntensityO", query = "SELECT m FROM MagnetDesignRequirementTable m WHERE m.intensityO = :intensityO")
    , @NamedQuery(name = "MagnetDesignRequirementTable.findBySystemComponent", query = "SELECT m FROM MagnetDesignRequirementTable m WHERE m.systemComponent = :systemComponent")
    , @NamedQuery(name = "MagnetDesignRequirementTable.findByNonSystemComponent", query = "SELECT m FROM MagnetDesignRequirementTable m WHERE m.nonSystemComponent = :nonSystemComponent")})
public class MagnetDesignRequirementTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "design_id")
    private Integer designId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "length")
    private Double length;
    @Column(name = "aperture")
    private Double aperture;
    @Column(name = "minimum_gap")
    private Double minimumGap;
    @Column(name = "useful_field")
    private Double usefulField;
    @Column(name = "intensity_B")
    private Double intensityB;
    @Column(name = "intensity_Q")
    private Double intensityQ;
    @Column(name = "intensity_S")
    private Double intensityS;
    @Column(name = "intensity_O")
    private Double intensityO;
    @Column(name = "system_component")
    private Double systemComponent;
    @Column(name = "non_system_component")
    private Double nonSystemComponent;
    @JoinColumn(name = "design_id", referencedColumnName = "design_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private MagnetDesignTable magnetDesignTable;

    public MagnetDesignRequirementTable() {
    }

    public MagnetDesignRequirementTable(Integer designId) {
        this.designId = designId;
    }

    public Integer getDesignId() {
        return designId;
    }

    public void setDesignId(Integer designId) {
        this.designId = designId;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getAperture() {
        return aperture;
    }

    public void setAperture(Double aperture) {
        this.aperture = aperture;
    }

    public Double getMinimumGap() {
        return minimumGap;
    }

    public void setMinimumGap(Double minimumGap) {
        this.minimumGap = minimumGap;
    }

    public Double getUsefulField() {
        return usefulField;
    }

    public void setUsefulField(Double usefulField) {
        this.usefulField = usefulField;
    }

    public Double getIntensityB() {
        return intensityB;
    }

    public void setIntensityB(Double intensityB) {
        this.intensityB = intensityB;
    }

    public Double getIntensityQ() {
        return intensityQ;
    }

    public void setIntensityQ(Double intensityQ) {
        this.intensityQ = intensityQ;
    }

    public Double getIntensityS() {
        return intensityS;
    }

    public void setIntensityS(Double intensityS) {
        this.intensityS = intensityS;
    }

    public Double getIntensityO() {
        return intensityO;
    }

    public void setIntensityO(Double intensityO) {
        this.intensityO = intensityO;
    }

    public Double getSystemComponent() {
        return systemComponent;
    }

    public void setSystemComponent(Double systemComponent) {
        this.systemComponent = systemComponent;
    }

    public Double getNonSystemComponent() {
        return nonSystemComponent;
    }

    public void setNonSystemComponent(Double nonSystemComponent) {
        this.nonSystemComponent = nonSystemComponent;
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
        if (!(object instanceof MagnetDesignRequirementTable)) {
            return false;
        }
        MagnetDesignRequirementTable other = (MagnetDesignRequirementTable) object;
        if ((this.designId == null && other.designId != null) || (this.designId != null && !this.designId.equals(other.designId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "heps.db.magnet.entity.MagnetDesignRequirementTable[ designId=" + designId + " ]";
        return "{\"length\":\""+length+"\","+"\"aperture\":\""+aperture+"\","+"\"minimumGap\":\""+minimumGap+"\","+"\"usefulField\":\""+usefulField+"\","+"\"intensityB\":\""+intensityB+"\","+"\"intensityQ\":\""+intensityQ+"\","+"\"intensityS\":\""+intensityS+"\","+"\"intensityO\":\""+intensityO+"\","+"\"systemComponent\":\""+systemComponent+"\","+"\"nonSystemComponent\":\""+nonSystemComponent+"\"}";

    }
    
}
