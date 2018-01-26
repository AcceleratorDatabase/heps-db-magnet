/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.magnet.jpa;

import heps.db.magnet.entity.DeviceInfoTable;
import heps.db.magnet.entity.StretchedWireSystemTable;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import net.sf.json.JSONObject;

/**
 *
 * @author qiaoys
 */
public class MeasureAPI {
     String result;
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
    public void insertSWSMeasCon(JSONObject meascon,Integer magid) {
    StretchedWireSystemTable sws;
    DeviceInfoTable device;
    device=(DeviceInfoTable)em.createNamedQuery("DeviceInfoTable.findByDeviceId").setParameter("deviceId", magid).getSingleResult();
    System.out.println("get"+device.getDeviceId());
    
    }
    
}
