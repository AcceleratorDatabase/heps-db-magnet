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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author qiaoys
 */
@Entity
@Table(name = "hall_data_table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HallDataTable.findAll", query = "SELECT h FROM HallDataTable h")
    , @NamedQuery(name = "HallDataTable.findByDataId", query = "SELECT h FROM HallDataTable h WHERE h.dataId = :dataId")
    , @NamedQuery(name = "HallDataTable.findByCur", query = "SELECT h FROM HallDataTable h WHERE h.cur = :cur")
    , @NamedQuery(name = "HallDataTable.findByX", query = "SELECT h FROM HallDataTable h WHERE h.x = :x")
    , @NamedQuery(name = "HallDataTable.findByY", query = "SELECT h FROM HallDataTable h WHERE h.y = :y")
    , @NamedQuery(name = "HallDataTable.findByGl", query = "SELECT h FROM HallDataTable h WHERE h.gl = :gl")
    , @NamedQuery(name = "HallDataTable.findByB", query = "SELECT h FROM HallDataTable h WHERE h.b = :b")})
public class HallDataTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "data_id")
    private Integer dataId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cur")
    private Double cur;
    @Column(name = "x")
    private Double x;
    @Column(name = "y")
    private Double y;
    @Column(name = "gl")
    private Double gl;
    @Column(name = "b")
    private Double b;
    @JoinColumn(name = "run_id", referencedColumnName = "hall_probe_run_id")
    @ManyToOne
    private HallProbeSystemTable runId;

    public HallDataTable() {
    }

    public HallDataTable(Integer dataId) {
        this.dataId = dataId;
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public Double getCur() {
        return cur;
    }

    public void setCur(Double cur) {
        this.cur = cur;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getGl() {
        return gl;
    }

    public void setGl(Double gl) {
        this.gl = gl;
    }

    public Double getB() {
        return b;
    }

    public void setB(Double b) {
        this.b = b;
    }

    public HallProbeSystemTable getRunId() {
        return runId;
    }

    public void setRunId(HallProbeSystemTable runId) {
        this.runId = runId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dataId != null ? dataId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HallDataTable)) {
            return false;
        }
        HallDataTable other = (HallDataTable) object;
        if ((this.dataId == null && other.dataId != null) || (this.dataId != null && !this.dataId.equals(other.dataId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{\"dataId\":\"" + dataId + "\"," + "\"cur\":\"" + cur + "\"," + "\"x\":\"" + x
                + "\"," + "\"y\":\"" + y + "\"," + "\"gl\":\"" + gl + "\"," + "\"b\":\"" + b +"\"}";
    }
    
}
