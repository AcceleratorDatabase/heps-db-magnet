/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.magnet.jpa;

import heps.db.magnet.entity.MagnetDesignParameterTable;
import heps.db.magnet.entity.MagnetDesignRequirementTable;
import heps.db.magnet.entity.MagnetDesignTable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author qiaoys
 */
public class DesignAPI {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("heps-db-magnetPU");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = em.getTransaction();    

    public static Double precalc(Object obj) {
        if (obj.toString().isEmpty()) {
            return null;
        } else {
            return Double.parseDouble(obj.toString());
        }
    }

    ;
    public void insertDesign(ArrayList designall, ArrayList design_requirement, ArrayList design_para) throws UnsupportedEncodingException {
        
        et.begin();        
        MagnetDesignTable design = new MagnetDesignTable();        
        design.setType(designall.get(0).toString());
        design.setFamily(Integer.parseInt(designall.get(1).toString()));
        design.setDesignName(designall.get(0).toString() + "-" + Integer.parseInt(designall.get(1).toString()));
        design.setDesignBy(designall.get(2).toString());
        design.setApprovedBy(designall.get(3).toString());
        design.setRemark(designall.get(4).toString());
        em.persist(design);
        et.commit();
        //requirement
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
//        parameter
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
        em.persist(parameter);
        et.commit();
//        design_others

        em.close();        
        System.out.println("ok");
    }
    
}
