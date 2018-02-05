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
import javax.persistence.Lob;
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
@Table(name = "hall_data_all_table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HallDataAllTable.findAll", query = "SELECT h FROM HallDataAllTable h")
    , @NamedQuery(name = "HallDataAllTable.findByDataId", query = "SELECT h FROM HallDataAllTable h WHERE h.dataId = :dataId")})
public class HallDataAllTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "data_id")
    private Integer dataId;
    @Lob
    @Size(max = 16777215)
    @Column(name = "raw_data")
    private String rawData;
    @Lob
    @Size(max = 16777215)
    @Column(name = "analysis_data")
    private String analysisData;
    @Size(max = 45)
    @Column(name = "content")
    private String content;
    @JoinColumn(name = "run_id", referencedColumnName = "hall_probe_run_id")
    @ManyToOne
    private HallProbeSystemTable runId;

    public HallDataAllTable() {
    }

    public HallDataAllTable(Integer dataId) {
        this.dataId = dataId;
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public String getAnalysisData() {
        return analysisData;
    }

    public void setAnalysisData(String analysisData) {
        this.analysisData = analysisData;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        if (!(object instanceof HallDataAllTable)) {
            return false;
        }
        HallDataAllTable other = (HallDataAllTable) object;
        if ((this.dataId == null && other.dataId != null) || (this.dataId != null && !this.dataId.equals(other.dataId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "heps.db.magnet.entity.HallDataAllTable[ dataId=" + dataId + " ]";
    }

}
