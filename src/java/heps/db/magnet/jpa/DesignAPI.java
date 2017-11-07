/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.magnet.jpa;

import static com.sun.xml.ws.security.addressing.impl.policy.Constants.logger;
import heps.db.magnet.entity.MagnetDesignTable;
import heps.db.magnet.entity.DesignOthersTable;
import heps.db.magnet.entity.MagnetDesignParameterTable;
import heps.db.magnet.entity.MagnetDesignRequirementTable;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author qiaoys
 */
public class DesignAPI {

    String result;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("heps-db-magnetPU");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = em.getTransaction();

    MagnetDesignTable design = new MagnetDesignTable();

    public static Double precalc(Object obj) {
        if (obj.toString().isEmpty()) {
            return null;
        } else {
            return Double.parseDouble(obj.toString());
        }
    }

    public void insertDesignBasic(ArrayList designall) {
        et.begin();
//        MagnetDesignTable design = new MagnetDesignTable();  
        try {
            //System.out.println("dadas");
           // design.setType("二极铁");
            design.setType(designall.get(0).toString());
            design.setFamily(Integer.parseInt(designall.get(1).toString()));
            design.setDesignName(designall.get(0).toString() + "-" + Integer.parseInt(designall.get(1).toString()));
            design.setDesignBy(designall.get(2).toString());
            design.setApprovedBy(designall.get(3).toString());
            design.setRemark(designall.get(4).toString());
            em.persist(design);
            et.commit();
        } catch (NumberFormatException e) {
            result = "失败！-磁铁基本信息数据异常：" + e;
        }
    }

    public void insertDesignRequire(ArrayList design_requirement) {
        try {
            et.begin();
            MagnetDesignRequirementTable require = new MagnetDesignRequirementTable();
            require.setDesignId(design.getDesignId());
            require.setLength(precalc(design_requirement.get(0)));
            require.setAperture(precalc(design_requirement.get(1).toString()));
            require.setMinimumGap(precalc(design_requirement.get(2).toString()));
            require.setUsefulField(precalc(design_requirement.get(3).toString()));
            require.setIntensityB(precalc(design_requirement.get(4).toString()));
            require.setIntensityQ(precalc(design_requirement.get(5).toString()));
            require.setIntensityS(precalc(design_requirement.get(6).toString()));
            require.setIntensityO(precalc(design_requirement.get(7).toString()));
            require.setSystemComponent(precalc(design_requirement.get(8).toString()));
            require.setNonSystemComponent(precalc(design_requirement.get(9).toString()));
            em.persist(require);
            et.commit();
        } catch (NumberFormatException e) {
            result = "失败！-磁铁设计要求数据异常：" + e;
        }
    }

    public void insertDesignPara(ArrayList design_para, ArrayList design_plot) {
        try {
            et.begin();
            MagnetDesignParameterTable parameter = new MagnetDesignParameterTable();
            parameter.setDesignId(design.getDesignId());
            parameter.setOffset(precalc(design_para.get(0).toString()));
            parameter.setAmpereTurns(precalc(design_para.get(1).toString()));
            parameter.setAmpereTurnsEach(precalc(design_para.get(2).toString()));
            parameter.setCurrent(precalc(design_para.get(3).toString()));
            parameter.setWire(design_para.get(4).toString());
            parameter.setCurrentDensity(precalc(design_para.get(5).toString()));
            parameter.setWireLength(precalc(design_para.get(6).toString()));
            parameter.setResistance(precalc(design_para.get(7).toString()));
            parameter.setInductance(precalc(design_para.get(8).toString()));
            parameter.setVoltage(precalc(design_para.get(9).toString()));
            parameter.setConsumption(precalc(design_para.get(10).toString()));
            parameter.setCPressureDrop(precalc(design_para.get(11).toString()));
            parameter.setCChannelNum(precalc(design_para.get(12).toString()));
            parameter.setCVelocity(precalc(design_para.get(13).toString()));
            parameter.setCFlow(precalc(design_para.get(14).toString()));
            parameter.setCTemp(precalc(design_para.get(15).toString()));
            parameter.setCoreLength(precalc(design_para.get(16).toString()));
            parameter.setCoreSection(precalc(design_para.get(17).toString()));
            parameter.setCoreWeight(precalc(design_para.get(18).toString()));
            parameter.setCopperWeight(precalc(design_para.get(19).toString()));
            //design_plot
            parameter.setPhysicsPlot(design_plot.get(0).toString());
            parameter.setMechanicalPlot(design_plot.get(1).toString());
            em.persist(parameter);
            et.commit();
        } catch (NumberFormatException e) {
            result = "失败！-磁铁设计要求数据异常：" + e;
        }

    }

    public void insertDesignOthers(ArrayList design_others) {
        try {
            et.begin();

            for (int i = 0; i < design_others.size(); i = i + 3) {
                DesignOthersTable others = new DesignOthersTable();
                others.setDesignId(design);
                others.setProperty(design_others.get(i).toString());
                if (design_others.get(i + 1).toString().equals("text")) {
                    others.setValueText(design_others.get(i + 2).toString());
                } else {
                    others.setValueNum(precalc(design_others.get(i + 2).toString()));
                }
                em.persist(others);
            }
            et.commit();
        } catch (NumberFormatException e) {
            result = "失败！-磁铁其他信息数据异常：" + e;
        }
    }

    public void insertDesign(ArrayList designall, ArrayList design_requirement, ArrayList design_para, ArrayList design_plot, int other_flag, ArrayList design_others) throws UnsupportedEncodingException {
        insertDesignBasic(designall);
        insertDesignRequire(design_requirement);
        insertDesignPara(design_para, design_plot);
        if (other_flag == 1) {
            insertDesignOthers(design_others);
        }
        em.close();
        emf.close();
        //System.out.println("ok");
    }

    public String queryDesignByType(String type) {
        Query query = em.createNamedQuery("MagnetDesignTable.findByType");
        query.setParameter("type", type);
        List<MagnetDesignTable> re = query.getResultList();
        return re.toString();
    }
 public String queryDesignByFamily(Integer family) {
      Query query = em.createQuery("SELECT m FROM MagnetDesignTable m WHERE m.family= :family");
        query.setParameter("family", family);
        List<MagnetDesignTable> re = query.getResultList();
        return re.toString();
 
 }
    public String queryDesignByTypeFamily(String type, Integer family) {
        Query query = em.createQuery("SELECT m FROM MagnetDesignTable m WHERE m.type = :type AND m.family= :family");
        query.setParameter("type", type).setParameter("family", family);
        List<MagnetDesignTable> re = query.getResultList();
        return re.toString();
    }
    
    public Integer deleteDesignById(Integer designId){
        et.begin();
       // MagnetDesignTable demag=em.find(MagnetDesignTable.class, designId);   
       Query query = em.createNamedQuery("MagnetDesignTable.findByDesignId");
        query.setParameter("designId", designId);
       MagnetDesignTable re = (MagnetDesignTable)query.getSingleResult();
      // System.out.println(re);
        em.remove(re);
        et.commit();
        return 1;
    }
}
