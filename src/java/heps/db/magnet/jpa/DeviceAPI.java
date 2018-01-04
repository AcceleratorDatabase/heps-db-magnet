/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.magnet.jpa;

import heps.db.magnet.entity.DeviceInfoTable;
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

    public void insertDevice(ArrayList maginfo, String type, Integer family) {
        MagnetDesignTable design;
        Long num;
        Integer numnow;
        String name;

        if (maginfo.get(5).toString().isEmpty()) {
            design = null;
            numnow = null;
            name = null;
        } else {
            design = (MagnetDesignTable) em.createNamedQuery("MagnetDesignTable.findByDesignId").setParameter("designId", Integer.parseInt(maginfo.get(5).toString())).getSingleResult();
            num = Long.parseLong(em.createQuery("SELECT count(d) FROM DeviceInfoTable d WHERE d.designId=:designId").setParameter("designId", design).getSingleResult().toString());
            numnow = 1 + num.intValue();
            name = type + "-" + family + "-" + numnow;
        }
        //System.out.println(design.toString());          
        DeviceInfoTable device = new DeviceInfoTable();
        device.setDeviceName(name);
        device.setWeight(precalc(maginfo.get(0).toString()));
        device.setSeries(maginfo.get(1).toString());
        device.setDesignedBy(maginfo.get(2).toString());
        device.setManuBy(maginfo.get(3).toString());
        device.setDateOfManu(strToDate(maginfo.get(4).toString()));
        device.setDesignId(design);
        device.setDescription(maginfo.get(6).toString());
        device.setNumber(numnow);
        em.persist(device);
        et.commit();
    }

    public String queryMagnetAll() {
        Query query = em.createNamedQuery("DeviceInfoTable.findAll");
        List<DeviceInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }

    public String queryMagnetById(Integer designId) {
        MagnetDesignTable mag = new MagnetDesignTable();
        mag.setDesignId(designId);
        Query query = em.createQuery("SELECT d FROM DeviceInfoTable d WHERE d.designId =:designId ");
        query.setParameter("designId", mag);
        List<DeviceInfoTable> re = query.getResultList();
        //System.out.println(designId+":"+re.toString());
        return re.toString();
    }

    public String queryMagnetByType(String type) {
        Query query = em.createQuery(" SELECT d FROM DeviceInfoTable d WHERE d.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.type=:type) ");
        query.setParameter("type", type);
        List<DeviceInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }

    public String queryMagnetByFamily(Integer family) {
        Query query = em.createQuery(" SELECT d FROM DeviceInfoTable d WHERE d.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.family=:family) ");
        query.setParameter("family", family);
        List<DeviceInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }

    public String queryMagnetByTypeFamily(String type, Integer family) {
        Query query = em.createQuery(" SELECT d FROM DeviceInfoTable d WHERE d.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.type=:type AND m.family=:family) ");
        query.setParameter("type", type).setParameter("family", family);
        List<DeviceInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }

    public String queryMagnetByDate(String datemin, String datemax) {
        Date qmin, qmax;
        if (datemin.equals("")) {
            Query query = em.createQuery(" SELECT MIN(d.dateOfManu) FROM DeviceInfoTable d");
            qmin = (Date) query.getSingleResult();
        } else {
            qmin = strToDate(datemin);
        }
        if (datemax.equals("")) {
            Query query = em.createQuery(" SELECT MAX(d.dateOfManu) FROM DeviceInfoTable d");
            qmax = (Date) query.getSingleResult();
        } else {
            qmax = strToDate(datemax);
        }
        Query query = em.createQuery(" SELECT d FROM DeviceInfoTable d WHERE d.dateOfManu BETWEEN :datemin AND :datemax");
        query.setParameter("datemin", qmin).setParameter("datemax", qmax);
        List<DeviceInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }

    public String queryMagnetByTypeDate(String type, String datemin, String datemax) {
        Date qmin, qmax;
        if (datemin.equals("")) {
            Query query = em.createQuery(" SELECT MIN(d.dateOfManu) FROM DeviceInfoTable d");
            qmin = (Date) query.getSingleResult();
        } else {
            qmin = strToDate(datemin);
        }
        if (datemax.equals("")) {
            Query query = em.createQuery(" SELECT MAX(d.dateOfManu) FROM DeviceInfoTable d");
            qmax = (Date) query.getSingleResult();
        } else {
            qmax = strToDate(datemax);
        }
        Query query = em.createQuery("SELECT d FROM DeviceInfoTable d WHERE d.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.type=:type) AND d.dateOfManu BETWEEN :datemin AND :datemax");
        query.setParameter("type", type).setParameter("datemin", qmin).setParameter("datemax", qmax);
        List<DeviceInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }

    public String queryMagnetByFamilyDate(Integer family, String datemin, String datemax) {
        Date qmin, qmax;
        if (datemin.equals("")) {
            Query query = em.createQuery(" SELECT MIN(d.dateOfManu) FROM DeviceInfoTable d");
            qmin = (Date) query.getSingleResult();
        } else {
            qmin = strToDate(datemin);
        }
        if (datemax.equals("")) {
            Query query = em.createQuery(" SELECT MAX(d.dateOfManu) FROM DeviceInfoTable d");
            qmax = (Date) query.getSingleResult();
        } else {
            qmax = strToDate(datemax);
        }
        Query query = em.createQuery("SELECT d FROM DeviceInfoTable d WHERE d.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.family=:family) AND d.dateOfManu BETWEEN :datemin AND :datemax");
        query.setParameter("family", family).setParameter("datemin", qmin).setParameter("datemax", qmax);
        List<DeviceInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }

    public String queryMagnetByTypeFamilyDate(String type, Integer family, String datemin, String datemax) {
        Date qmin, qmax;
        if (datemin.equals("")) {
            Query query = em.createQuery(" SELECT MIN(d.dateOfManu) FROM DeviceInfoTable d");
            qmin = (Date) query.getSingleResult();
        } else {
            qmin = strToDate(datemin);
        }
        if (datemax.equals("")) {
            Query query = em.createQuery(" SELECT MAX(d.dateOfManu) FROM DeviceInfoTable d");
            qmax = (Date) query.getSingleResult();
        } else {
            qmax = strToDate(datemax);
        }
        Query query = em.createQuery("SELECT d FROM DeviceInfoTable d WHERE d.designId IN(SELECT m FROM MagnetDesignTable m WHERE m.type=:type AND m.family=:family) AND d.dateOfManu BETWEEN :datemin AND :datemax");
        query.setParameter("family", family).setParameter("datemin", qmin).setParameter("datemax", qmax);
        List<DeviceInfoTable> re = query.getResultList();
        // System.out.println(re.toString());
        return re.toString();
    }
}
