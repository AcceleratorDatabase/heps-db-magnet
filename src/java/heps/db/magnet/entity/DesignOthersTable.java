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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author qiaoys
 */
@Entity
@Table(name = "design_others_table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DesignOthersTable.findAll", query = "SELECT d FROM DesignOthersTable d")
    , @NamedQuery(name = "DesignOthersTable.findByIndex", query = "SELECT d FROM DesignOthersTable d WHERE d.otherIndex = :otherIndex")
    , @NamedQuery(name = "DesignOthersTable.findByProperty", query = "SELECT d FROM DesignOthersTable d WHERE d.property = :property")
    , @NamedQuery(name = "DesignOthersTable.findByValueNum", query = "SELECT d FROM DesignOthersTable d WHERE d.valueNum = :valueNum")
    , @NamedQuery(name = "DesignOthersTable.findByValueText", query = "SELECT d FROM DesignOthersTable d WHERE d.valueText = :valueText")})
public class DesignOthersTable implements Serializable {

    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "other_index")
    private Integer otherIndex;
    @Size(max = 45)
    @Column(name = "property")
    private String property;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "value_num")
    private Double valueNum;
    @Size(max = 45)
    @Column(name = "value_text")
    private String valueText;
    @JoinColumn(name = "design_id", referencedColumnName = "design_id")
    @ManyToOne(optional = false)
    private MagnetDesignTable designId;

    public DesignOthersTable() {
    }

    

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Double getValueNum() {
        return valueNum;
    }

    public void setValueNum(Double valueNum) {
        this.valueNum = valueNum;
    }

    public String getValueText() {
        return valueText;
    }

    public void setValueText(String valueText) {
        this.valueText = valueText;
    }

    public MagnetDesignTable getDesignId() {
        return designId;
    }

    public void setDesignId(MagnetDesignTable designId) {
        this.designId = designId;
    }

    

    

    

    public DesignOthersTable(Integer otherIndex) {
        this.otherIndex = otherIndex;
    }

    public Integer getOtherIndex() {
        return otherIndex;
    }

    public void setOtherIndex(Integer otherIndex) {
        this.otherIndex = otherIndex;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (otherIndex != null ? otherIndex.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DesignOthersTable)) {
            return false;
        }
        DesignOthersTable other = (DesignOthersTable) object;
        if ((this.otherIndex == null && other.otherIndex != null) || (this.otherIndex != null && !this.otherIndex.equals(other.otherIndex))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "heps.db.magnet.entity.DesignOthersTable[ otherIndex=" + otherIndex + " ]";
    }
    
}
