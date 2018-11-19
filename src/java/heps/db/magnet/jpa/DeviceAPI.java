/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.magnet.jpa;

import heps.db.magnet.entity.EquipmentInfoTable;
import heps.db.magnet.entity.MagnetDesignTable;
//import java.sql.Date;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class DeviceAPI {

    String result;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("heps-db-magnetPU");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = em.getTransaction();

    public java.sql.Date strToDate(String strDate) {
        if (strDate != null) {
            String s[] = strDate.split("/");
            String n = s[2] + "-" + s[0] + "-" + s[1];
            String str = n;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d = null;
            try {
                d = format.parse(str);
            } catch (ParseException e) {

            }
            java.sql.Date date = new java.sql.Date(d.getTime());
            return date;
        } else {
            return null;
        }
    }

    public static Double precalc(Object obj) {
        if (obj.toString().isEmpty()) {
            return null;
        } else {
            return Double.parseDouble(obj.toString());
        }
    }

    public void init() {
        emf = Persistence.createEntityManagerFactory("heps-db-magnetPU");
        em = emf.createEntityManager();
        et = em.getTransaction();
        et.begin();
    }

    public void destroy() {
        em.close();
        emf.close();
    }

    public void insertDevice(ArrayList maginfo, String type, String family, String eqsection) {
        MagnetDesignTable design;
        Long num;
        Integer numnow;
        String name;

        if (maginfo.get(6).toString().isEmpty()) {
            design = null;
            numnow = null;
            name = null;
        } else {
            design = (MagnetDesignTable) em.createNamedQuery("MagnetDesignTable.findByDesignId").setParameter("designId", Integer.parseInt(maginfo.get(6).toString())).getSingleResult();
            //num = Long.parseLong(em.createQuery("SELECT count(d) FROM EquipmentInfoTable d WHERE d.designId=:designId").setParameter("designId", design).getSingleResult().toString());
            //numnow = 1 + num.intValue();
            num = Long.parseLong(em.createQuery("SELECT count(e) FROM EquipmentInfoTable e WHERE e.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.type=:type AND m.family=:family)").setParameter("type", type).setParameter("family", family).getSingleResult().toString());
            numnow = 1 + num.intValue();
            name = type + "-" + family + "-" + numnow;
        }
        System.out.println(maginfo.toString());          
        EquipmentInfoTable device = new EquipmentInfoTable();
        device.setEquipmentName(name);
        device.setEqsection(eqsection);
        device.setWeight(precalc(maginfo.get(0).toString()));
        device.setPrice(precalc(maginfo.get(1).toString()));
        device.setSeries(maginfo.get(2).toString());
        device.setDesignedBy(maginfo.get(3).toString());
        device.setManuBy(maginfo.get(4).toString());
        device.setDateOfManu(strToDate(maginfo.get(5).toString()));
        device.setDesignId(design);
        device.setDescription(maginfo.get(7).toString());
        device.setNumber(numnow);
        em.persist(device);
        et.commit();
    }

    public void updateDevice(ArrayList maginfo, Integer magId, String eqsection) {
        MagnetDesignTable design;
        design = (MagnetDesignTable) em.createNamedQuery("MagnetDesignTable.findByDesignId").setParameter("designId", Integer.parseInt(maginfo.get(5).toString())).getSingleResult();
        Double weight=precalc(maginfo.get(0).toString());
        Double price=precalc(maginfo.get(1).toString());
        String series=maginfo.get(2).toString();
        String designedby=maginfo.get(3).toString();
        String manuBy=maginfo.get(4).toString();
        Date deteOfManu=strToDate(maginfo.get(5).toString());
        String description=maginfo.get(6).toString();        
       EquipmentInfoTable device =(EquipmentInfoTable)em.createNamedQuery("EquipmentInfoTable.findByEquipmentId").setParameter("equipmentId", magId).getSingleResult();
       device.setEqsection(eqsection);
       device.setWeight(weight);
       device.setPrice(price);
       device.setSeries(series);
       device.setDesignedBy(designedby);
       device.setManuBy(manuBy);
       device.setDateOfManu(deteOfManu);
       device.setDesignId(design);
       device.setDescription(description);
       em.persist(device);
       et.commit();

    }

    public String queryMagnetAll() {
        Query query = em.createNamedQuery("EquipmentInfoTable.findAll");
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }
    public String queryAllSections() {
        String re;
        Query query = em.createQuery("SELECT DISTINCT e.eqsection from EquipmentInfoTable e");
        List s = query.getResultList();
        re = s.toString().substring(1, s.toString().length() - 1);
        return re;
    }

    public String queryMagnetById(Integer designId) {
        MagnetDesignTable mag = new MagnetDesignTable();
        mag.setDesignId(designId);
        Query query = em.createQuery("SELECT e FROM EquipmentInfoTable e WHERE e.designId =:designId ");
        query.setParameter("designId", mag);
        List<EquipmentInfoTable> re = query.getResultList();
        //System.out.println(designId+":"+re.toString());
        return re.toString();
    }

    public String queryMagnetByType(String type) {
        Query query = em.createQuery(" SELECT e FROM EquipmentInfoTable e WHERE e.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.type=:type) ");
        query.setParameter("type", type);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }

    public String queryMagnetByFamily(String family) {
        Query query = em.createQuery(" SELECT e FROM EquipmentInfoTable e WHERE e.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.family=:family) ");
        query.setParameter("family", family);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }

    public String queryMagnetByTypeFamily(String type, String family) {
        Query query = em.createQuery(" SELECT e FROM EquipmentInfoTable e WHERE e.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.type=:type AND m.family=:family) ");
        query.setParameter("type", type).setParameter("family", family);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }
    public String queryMagnetByTypeFamilySection(String type, String family, String eqsection) {
        Query query = em.createQuery(" SELECT e FROM EquipmentInfoTable e WHERE e.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.type=:type AND m.family=:family) AND e.eqsection=:eqsection");
        query.setParameter("type", type).setParameter("family", family).setParameter("eqsection", eqsection);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }
    public String queryMagnetByTypeFamilyManuby(String type, String family, String manuBy) {
        Query query = em.createQuery(" SELECT e FROM EquipmentInfoTable e WHERE e.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.type=:type AND m.family=:family) AND e.manuBy=:manuBy");
        query.setParameter("type", type).setParameter("family", family).setParameter("manuBy", manuBy);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }
    public String queryMagnetByTypeSectionManuby(String type, String eqsection, String manuBy) {
        Query query = em.createQuery(" SELECT e FROM EquipmentInfoTable e WHERE e.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.type=:type) AND e.eqsection=:eqsection AND e.manuBy=:manuBy");
        query.setParameter("type", type).setParameter("eqsection", eqsection).setParameter("manuBy", manuBy);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }
    public String queryMagnetByTypeFamilySectionManuby(String type, String family, String eqsection, String manuBy) {
        Query query = em.createQuery(" SELECT e FROM EquipmentInfoTable e WHERE e.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.type=:type AND m.family=:family) AND e.eqsection=:eqsection AND e.manuBy=:manuBy");
        query.setParameter("type", type).setParameter("family", family).setParameter("eqsection", eqsection).setParameter("manuBy", manuBy);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }
    public String queryMagnetBySection(String eqsection) {
        Query query = em.createNamedQuery("EquipmentInfoTable.findByEqsection");
        query.setParameter("eqsection", eqsection);
        List<MagnetDesignTable> re = query.getResultList();
        return re.toString();
    }
     public String queryMagnetByTypeSection(String type, String eqsection) {
        Query query = em.createQuery(" SELECT e FROM EquipmentInfoTable e WHERE e.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.type=:type) AND e.eqsection=:eqsection ");
        query.setParameter("type", type).setParameter("eqsection", eqsection);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }
     public String queryMagnetByFamilySection(String family, String eqsection) {
        Query query = em.createQuery(" SELECT e FROM EquipmentInfoTable e WHERE e.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.family=:family) AND e.eqsection=:eqsection ");
        query.setParameter("family", family).setParameter("eqsection", eqsection);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }
      public String queryMagnetByFamilySectionManuby(String family, String eqsection, String manuBy) {
        Query query = em.createQuery(" SELECT e FROM EquipmentInfoTable e WHERE e.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.family=:family) AND e.eqsection=:eqsection AND e.manuBy=:manuBy");
        query.setParameter("family", family).setParameter("eqsection", eqsection).setParameter("manuBy", manuBy);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }
     public String queryMagnetByTypeManuby(String type, String manuBy) {
        Query query = em.createQuery(" SELECT e FROM EquipmentInfoTable e WHERE e.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.type=:type) AND e.manuBy=:manuBy ");
        query.setParameter("type", type).setParameter("manuBy", manuBy);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }
     public String queryMagnetByFamilyManuby(String family, String manuBy) {
        Query query = em.createQuery(" SELECT e FROM EquipmentInfoTable e WHERE e.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.family=:family) AND e.manuBy=:manuBy ");
        query.setParameter("family", family).setParameter("manuBy", manuBy);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }
     public String queryMagnetBySectionManuby(String eqsection, String manuBy) {
        Query query = em.createQuery(" SELECT e FROM EquipmentInfoTable e WHERE e.eqsection=:eqsection AND e.manuBy=:manuBy ");
        query.setParameter("eqsection", eqsection).setParameter("manuBy", manuBy);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }
    public String queryMagnetByManuby(String manuBy) {
        Query query = em.createNamedQuery("EquipmentInfoTable.findByManuBy");
        query.setParameter("manuBy", manuBy);
        List<MagnetDesignTable> re = query.getResultList();
        return re.toString();
    }
    public String queryMagnetByDate(String datemin, String datemax) {
        Date qmin, qmax;
        if (datemin.equals("")) {
            Query query = em.createQuery(" SELECT MIN(e.dateOfManu) FROM EquipmentInfoTable e");
            qmin = (Date) query.getSingleResult();
        } else {
            qmin = strToDate(datemin);
        }
        if (datemax.equals("")) {
            Query query = em.createQuery(" SELECT MAX(e.dateOfManu) FROM EquipmentInfoTable e");
            qmax = (Date) query.getSingleResult();
        } else {
            qmax = strToDate(datemax);
        }
        Query query = em.createQuery(" SELECT e FROM EquipmentInfoTable e WHERE e.dateOfManu BETWEEN :datemin AND :datemax");
        query.setParameter("datemin", qmin).setParameter("datemax", qmax);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }

    public String queryMagnetByTypeDate(String type, String datemin, String datemax) {
        Date qmin, qmax;
        if (datemin.equals("")) {
            Query query = em.createQuery(" SELECT MIN(e.dateOfManu) FROM EquipmentInfoTable e");
            qmin = (Date) query.getSingleResult();
        } else {
            qmin = strToDate(datemin);
        }
        if (datemax.equals("")) {
            Query query = em.createQuery(" SELECT MAX(e.dateOfManu) FROM EquipmentInfoTable e");
            qmax = (Date) query.getSingleResult();
        } else {
            qmax = strToDate(datemax);
        }
        Query query = em.createQuery("SELECT e FROM EquipmentInfoTable e WHERE e.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.type=:type) AND e.dateOfManu BETWEEN :datemin AND :datemax");
        query.setParameter("type", type).setParameter("datemin", qmin).setParameter("datemax", qmax);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }

    public String queryMagnetByFamilyDate(String family, String datemin, String datemax) {
        Date qmin, qmax;
        if (datemin.equals("")) {
            Query query = em.createQuery(" SELECT MIN(e.dateOfManu) FROM EquipmentInfoTable e");
            qmin = (Date) query.getSingleResult();
        } else {
            qmin = strToDate(datemin);
        }
        if (datemax.equals("")) {
            Query query = em.createQuery(" SELECT MAX(e.dateOfManu) FROM EquipmentInfoTable e");
            qmax = (Date) query.getSingleResult();
        } else {
            qmax = strToDate(datemax);
        }
        Query query = em.createQuery("SELECT e FROM EquipmentInfoTable e WHERE e.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.family=:family) AND e.dateOfManu BETWEEN :datemin AND :datemax");
        query.setParameter("family", family).setParameter("datemin", qmin).setParameter("datemax", qmax);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }
    public String queryMagnetByFamilySectionDate(String family, String eqsection, String datemin, String datemax) {
        Date qmin, qmax;
        if (datemin.equals("")) {
            Query query = em.createQuery(" SELECT MIN(e.dateOfManu) FROM EquipmentInfoTable e");
            qmin = (Date) query.getSingleResult();
        } else {
            qmin = strToDate(datemin);
        }
        if (datemax.equals("")) {
            Query query = em.createQuery(" SELECT MAX(e.dateOfManu) FROM EquipmentInfoTable e");
            qmax = (Date) query.getSingleResult();
        } else {
            qmax = strToDate(datemax);
        }
        Query query = em.createQuery("SELECT e FROM EquipmentInfoTable e WHERE e.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.family=:family) AND e.eqsection=:eqsection AND e.dateOfManu BETWEEN :datemin AND :datemax");
        query.setParameter("family", family).setParameter("eqsection", eqsection).setParameter("datemin", qmin).setParameter("datemax", qmax);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }
public String queryMagnetBySectionDate(String eqsection, String datemin, String datemax) {
        Date qmin, qmax;
        if (datemin.equals("")) {
            Query query = em.createQuery(" SELECT MIN(e.dateOfManu) FROM EquipmentInfoTable e");
            qmin = (Date) query.getSingleResult();
        } else {
            qmin = strToDate(datemin);
        }
        if (datemax.equals("")) {
            Query query = em.createQuery(" SELECT MAX(e.dateOfManu) FROM EquipmentInfoTable e");
            qmax = (Date) query.getSingleResult();
        } else {
            qmax = strToDate(datemax);
        }
        Query query = em.createQuery("SELECT e FROM EquipmentInfoTable e WHERE e.eqsection=:eqsection AND( e.dateOfManu BETWEEN :datemin AND :datemax)");
        query.setParameter("eqsection", eqsection).setParameter("datemin", qmin).setParameter("datemax", qmax);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }
public String queryMagnetBySectionManubyDate(String eqsection, String manuBy, String datemin, String datemax) {
        Date qmin, qmax;
        if (datemin.equals("")) {
            Query query = em.createQuery(" SELECT MIN(e.dateOfManu) FROM EquipmentInfoTable e");
            qmin = (Date) query.getSingleResult();
        } else {
            qmin = strToDate(datemin);
        }
        if (datemax.equals("")) {
            Query query = em.createQuery(" SELECT MAX(e.dateOfManu) FROM EquipmentInfoTable e");
            qmax = (Date) query.getSingleResult();
        } else {
            qmax = strToDate(datemax);
        }
        Query query = em.createQuery("SELECT e FROM EquipmentInfoTable e WHERE e.eqsection=:eqsection AND e.manuBy=:manuBy AND ( e.dateOfManu BETWEEN :datemin AND :datemax)");
        query.setParameter("eqsection", eqsection).setParameter("manuBy", manuBy).setParameter("datemin", qmin).setParameter("datemax", qmax);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }
public String queryMagnetByManubyDate(String manuBy, String datemin, String datemax) {
        Date qmin, qmax;
        if (datemin.equals("")) {
            Query query = em.createQuery(" SELECT MIN(e.dateOfManu) FROM EquipmentInfoTable e");
            qmin = (Date) query.getSingleResult();
        } else {
            qmin = strToDate(datemin);
        }
        if (datemax.equals("")) {
            Query query = em.createQuery(" SELECT MAX(e.dateOfManu) FROM EquipmentInfoTable e");
            qmax = (Date) query.getSingleResult();
        } else {
            qmax = strToDate(datemax);
        }
        Query query = em.createQuery("SELECT e FROM EquipmentInfoTable e WHERE e.manuBy=:manuBy AND( e.dateOfManu BETWEEN :datemin AND :datemax)");
        query.setParameter("manuBy", manuBy).setParameter("datemin", qmin).setParameter("datemax", qmax);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }
    public String queryMagnetByTypeFamilyDate(String type, String family, String datemin, String datemax) {
        Date qmin, qmax;
        if (datemin.equals("")) {
            Query query = em.createQuery(" SELECT MIN(e.dateOfManu) FROM EquipmentInfoTable e");
            qmin = (Date) query.getSingleResult();
        } else {
            qmin = strToDate(datemin);
        }
        if (datemax.equals("")) {
            Query query = em.createQuery(" SELECT MAX(e.dateOfManu) FROM EquipmentInfoTable e");
            qmax = (Date) query.getSingleResult();
        } else {
            qmax = strToDate(datemax);
        }
        Query query = em.createQuery("SELECT e FROM EquipmentInfoTable e WHERE e.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.type=:type AND m.family=:family) AND e.dateOfManu BETWEEN :datemin AND :datemax");
        query.setParameter("type", type).setParameter("family", family).setParameter("datemin", qmin).setParameter("datemax", qmax);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }
    public String queryMagnetByTypeSectionDate(String type, String eqsection, String datemin, String datemax) {
        Date qmin, qmax;
        if (datemin.equals("")) {
            Query query = em.createQuery(" SELECT MIN(e.dateOfManu) FROM EquipmentInfoTable e");
            qmin = (Date) query.getSingleResult();
        } else {
            qmin = strToDate(datemin);
        }
        if (datemax.equals("")) {
            Query query = em.createQuery(" SELECT MAX(e.dateOfManu) FROM EquipmentInfoTable e");
            qmax = (Date) query.getSingleResult();
        } else {
            qmax = strToDate(datemax);
        }
        Query query = em.createQuery("SELECT e FROM EquipmentInfoTable e WHERE e.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.type=:type)  AND e.eqsection=:eqsection AND e.dateOfManu BETWEEN :datemin AND :datemax");
        query.setParameter("type", type).setParameter("eqsection", eqsection).setParameter("datemin", qmin).setParameter("datemax", qmax);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }
    public String queryMagnetByTypeManubyDate(String type, String manuBy, String datemin, String datemax) {
        Date qmin, qmax;
        if (datemin.equals("")) {
            Query query = em.createQuery(" SELECT MIN(e.dateOfManu) FROM EquipmentInfoTable e");
            qmin = (Date) query.getSingleResult();
        } else {
            qmin = strToDate(datemin);
        }
        if (datemax.equals("")) {
            Query query = em.createQuery(" SELECT MAX(e.dateOfManu) FROM EquipmentInfoTable e");
            qmax = (Date) query.getSingleResult();
        } else {
            qmax = strToDate(datemax);
        }
        Query query = em.createQuery("SELECT e FROM EquipmentInfoTable e WHERE e.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.type=:type)  AND e.manuBy=:manuBy AND e.dateOfManu BETWEEN :datemin AND :datemax");
        query.setParameter("type", type).setParameter("manuBy", manuBy).setParameter("datemin", qmin).setParameter("datemax", qmax);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }
     public String queryMagnetByFamilyManubyDate(String family, String manuBy, String datemin, String datemax) {
        Date qmin, qmax;
        if (datemin.equals("")) {
            Query query = em.createQuery(" SELECT MIN(e.dateOfManu) FROM EquipmentInfoTable e");
            qmin = (Date) query.getSingleResult();
        } else {
            qmin = strToDate(datemin);
        }
        if (datemax.equals("")) {
            Query query = em.createQuery(" SELECT MAX(e.dateOfManu) FROM EquipmentInfoTable e");
            qmax = (Date) query.getSingleResult();
        } else {
            qmax = strToDate(datemax);
        }
        Query query = em.createQuery("SELECT e FROM EquipmentInfoTable e WHERE e.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.family=:family)  AND e.manuBy=:manuBy AND e.dateOfManu BETWEEN :datemin AND :datemax");
        query.setParameter("family", family).setParameter("manuBy", manuBy).setParameter("datemin", qmin).setParameter("datemax", qmax);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }
    public String queryMagnetByTypeFamilySectionDate(String type, String family, String eqsection, String datemin, String datemax) {
        Date qmin, qmax;
        if (datemin.equals("")) {
            Query query = em.createQuery(" SELECT MIN(e.dateOfManu) FROM EquipmentInfoTable e");
            qmin = (Date) query.getSingleResult();
        } else {
            qmin = strToDate(datemin);
        }
        if (datemax.equals("")) {
            Query query = em.createQuery(" SELECT MAX(e.dateOfManu) FROM EquipmentInfoTable e");
            qmax = (Date) query.getSingleResult();
        } else {
            qmax = strToDate(datemax);
        }
        Query query = em.createQuery("SELECT e FROM EquipmentInfoTable e WHERE e.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.type=:type AND m.family=:family) AND e.eqsection=:eqsection AND e.dateOfManu BETWEEN :datemin AND :datemax");
        query.setParameter("type", type).setParameter("family", family).setParameter("eqsection", eqsection).setParameter("datemin", qmin).setParameter("datemax", qmax);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }
    public String queryMagnetByTypeFamilyManubyDate(String type, String family, String manuBy, String datemin, String datemax) {
        Date qmin, qmax;
        if (datemin.equals("")) {
            Query query = em.createQuery(" SELECT MIN(e.dateOfManu) FROM EquipmentInfoTable e");
            qmin = (Date) query.getSingleResult();
        } else {
            qmin = strToDate(datemin);
        }
        if (datemax.equals("")) {
            Query query = em.createQuery(" SELECT MAX(e.dateOfManu) FROM EquipmentInfoTable e");
            qmax = (Date) query.getSingleResult();
        } else {
            qmax = strToDate(datemax);
        }
        Query query = em.createQuery("SELECT e FROM EquipmentInfoTable e WHERE e.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.type=:type AND m.family=:family) AND e.manuBy=:manuBy AND e.dateOfManu BETWEEN :datemin AND :datemax");
        query.setParameter("type", type).setParameter("family", family).setParameter("manuBy", manuBy).setParameter("datemin", qmin).setParameter("datemax", qmax);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }
    public String queryMagnetByTypeSectionManubyDate(String type, String eqsection, String manuBy, String datemin, String datemax) {
        Date qmin, qmax;
        if (datemin.equals("")) {
            Query query = em.createQuery(" SELECT MIN(e.dateOfManu) FROM EquipmentInfoTable e");
            qmin = (Date) query.getSingleResult();
        } else {
            qmin = strToDate(datemin);
        }
        if (datemax.equals("")) {
            Query query = em.createQuery(" SELECT MAX(e.dateOfManu) FROM EquipmentInfoTable e");
            qmax = (Date) query.getSingleResult();
        } else {
            qmax = strToDate(datemax);
        }
        Query query = em.createQuery("SELECT e FROM EquipmentInfoTable e WHERE e.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.type=:type) AND e.eqsection=:eqsection AND e.manuBy=:manuBy AND e.dateOfManu BETWEEN :datemin AND :datemax");
        query.setParameter("type", type).setParameter("eqsection", eqsection).setParameter("manuBy", manuBy).setParameter("datemin", qmin).setParameter("datemax", qmax);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }
     public String queryMagnetByFamilySectionManubyDate(String family, String eqsection, String manuBy, String datemin, String datemax) {
        Date qmin, qmax;
        if (datemin.equals("")) {
            Query query = em.createQuery(" SELECT MIN(e.dateOfManu) FROM EquipmentInfoTable e");
            qmin = (Date) query.getSingleResult();
        } else {
            qmin = strToDate(datemin);
        }
        if (datemax.equals("")) {
            Query query = em.createQuery(" SELECT MAX(e.dateOfManu) FROM EquipmentInfoTable e");
            qmax = (Date) query.getSingleResult();
        } else {
            qmax = strToDate(datemax);
        }
        Query query = em.createQuery("SELECT e FROM EquipmentInfoTable e WHERE e.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.family=:family) AND e.eqsection=:eqsection AND e.manuBy=:manuBy AND e.dateOfManu BETWEEN :datemin AND :datemax");
        query.setParameter("family", family).setParameter("eqsection", eqsection).setParameter("manuBy", manuBy).setParameter("datemin", qmin).setParameter("datemax", qmax);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }
     public String queryMagnetByTypeFamilySectionManubyDate(String type, String family, String eqsection, String manuBy, String datemin, String datemax) {
        Date qmin, qmax;
        if (datemin.equals("")) {
            Query query = em.createQuery(" SELECT MIN(e.dateOfManu) FROM EquipmentInfoTable e");
            qmin = (Date) query.getSingleResult();
        } else {
            qmin = strToDate(datemin);
        }
        if (datemax.equals("")) {
            Query query = em.createQuery(" SELECT MAX(e.dateOfManu) FROM EquipmentInfoTable e");
            qmax = (Date) query.getSingleResult();
        } else {
            qmax = strToDate(datemax);
        }
        Query query = em.createQuery("SELECT e FROM EquipmentInfoTable e WHERE e.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.type=:type AND m.family=:family) AND e.eqsection=:eqsection AND e.manuBy=:manuBy AND e.dateOfManu BETWEEN :datemin AND :datemax");
        query.setParameter("family", family).setParameter("family", family).setParameter("eqsection", eqsection).setParameter("manuBy", manuBy).setParameter("datemin", qmin).setParameter("datemax", qmax);
        List<EquipmentInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }

    public Integer deleteMagnetById(Integer magid) {
        // et.begin();
        EquipmentInfoTable demag = em.find(EquipmentInfoTable.class, magid);
        //Query query = em.createNamedQuery("MagnetDesignTable.findByDesignId");
        //query.setParameter("designId", designId);
        //MagnetDesignTable re = (MagnetDesignTable)query.getSingleResult();
        // System.out.println(re);
        em.remove(demag);
        et.commit();
        //em.close();
        //emf.close();
        return 1;
    }
}
