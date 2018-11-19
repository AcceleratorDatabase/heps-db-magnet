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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author qiaoys
 */
@Entity
@Table(name = "equipment_info_table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EquipmentInfoTable.findAll", query = "SELECT e FROM EquipmentInfoTable e")
    , @NamedQuery(name = "EquipmentInfoTable.findByEquipmentId", query = "SELECT e FROM EquipmentInfoTable e WHERE e.equipmentId = :equipmentId")
    , @NamedQuery(name = "EquipmentInfoTable.findByNumber", query = "SELECT e FROM EquipmentInfoTable e WHERE e.number = :number")
    , @NamedQuery(name = "EquipmentInfoTable.findByEquipmentName", query = "SELECT e FROM EquipmentInfoTable e WHERE e.equipmentName = :equipmentName")
    , @NamedQuery(name = "EquipmentInfoTable.findByEqsection", query = "SELECT e FROM EquipmentInfoTable e WHERE e.eqsection = :eqsection")
    , @NamedQuery(name = "EquipmentInfoTable.findByWeight", query = "SELECT e FROM EquipmentInfoTable e WHERE e.weight = :weight")
    , @NamedQuery(name = "EquipmentInfoTable.findByPrice", query = "SELECT e FROM EquipmentInfoTable e WHERE e.price = :price")
    , @NamedQuery(name = "EquipmentInfoTable.findBySeries", query = "SELECT e FROM EquipmentInfoTable e WHERE e.series = :series")
    , @NamedQuery(name = "EquipmentInfoTable.findByDateOfManu", query = "SELECT e FROM EquipmentInfoTable e WHERE e.dateOfManu = :dateOfManu")
    , @NamedQuery(name = "EquipmentInfoTable.findByDesignedBy", query = "SELECT e FROM EquipmentInfoTable e WHERE e.designedBy = :designedBy")
    , @NamedQuery(name = "EquipmentInfoTable.findByManuBy", query = "SELECT e FROM EquipmentInfoTable e WHERE e.manuBy = :manuBy")
    , @NamedQuery(name = "EquipmentInfoTable.findByDescription", query = "SELECT e FROM EquipmentInfoTable e WHERE e.description = :description")})
public class EquipmentInfoTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)

    @Column(name = "equipment_id")
    private Integer equipmentId;
    @Column(name = "number")
    private Integer number;
    @Size(max = 45)
    @Column(name = "equipment_name")
    private String equipmentName;
    @Size(max = 45)
    @Column(name = "eqsection")
    private String eqsection;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "weight")
    private Double weight;
    @Column(name = "price")
    private Double price;
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
    @OneToMany(mappedBy = "equipmentId")
    private Collection<StretchedWireSystemTable> stretchedWireSystemTableCollection;
    @OneToMany(mappedBy = "equipmentId")
    private Collection<HallProbeSystemTable> hallProbeSystemTableCollection;
    @OneToMany(mappedBy = "equipmentId")
    private Collection<RotCoilSystemTable> rotCoilSystemTableCollection;

    public EquipmentInfoTable() {
    }

    public EquipmentInfoTable(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }
    public String getEqsection() {
        return eqsection;
    }

    public void setEqsection(String eqsection) {
        this.eqsection = eqsection;
    }
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
    
    public Double getPrice() {
        return weight;
    }

    public void setPrice(Double price) {
        this.price = price;
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
        hash += (equipmentId != null ? equipmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipmentInfoTable)) {
            return false;
        }
        EquipmentInfoTable other = (EquipmentInfoTable) object;
        if ((this.equipmentId == null && other.equipmentId != null) || (this.equipmentId != null && !this.equipmentId.equals(other.equipmentId))) {
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
        return "{\"magid\":\"" + equipmentId + "\"," + "\"magtype\":\"" + designId.getType() + "\","+ "\"magfamily\":\"" + designId.getFamily() + "\","+ "\"magname\":\"" + equipmentName + "\"," + "\"magsection\":\"" + eqsection + "\","+ "\"designid\":\"" + designId.getDesignId()
                + "\"," + "\"weight\":\"" + weight + "\"," + "\"price\":\"" + price + "\"," + "\"series\":\"" + series + "\"," + "\"manudate\":\"" + DateToString(dateOfManu) + "\","
                + "\"designedby\":\"" + designedBy + "\"," + "\"manuby\":\"" + manuBy + "\"," + "\"description\":\"" + description + "\"}";
    }

}
