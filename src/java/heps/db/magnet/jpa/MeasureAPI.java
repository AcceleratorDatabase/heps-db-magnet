/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.magnet.jpa;

import heps.db.magnet.entity.DeviceInfoTable;
import heps.db.magnet.entity.StretchedWireSystemTable;
import heps.db.magnet.entity.SwsDataTable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public void insertSWSMeas(JSONObject meascon, Integer magid, String measdate, String measby, String measat, String remark,ArrayList rawdata) {
        StretchedWireSystemTable sws = new StretchedWireSystemTable();
        DeviceInfoTable device;
        device = (DeviceInfoTable) em.createNamedQuery("DeviceInfoTable.findByDeviceId").setParameter("deviceId", magid).getSingleResult();
        sws.setDeviceId(device);
        sws.setSamplingRate(Double.parseDouble(meascon.getString("采样率（Hz）：")));
        sws.setSpeed(Double.parseDouble(meascon.getString("移动速度（m/s）：")));
        sws.setAcceleratedSpeed(Double.parseDouble(meascon.getString("a加速度（m/s2）：")));
        sws.setDistance(Double.parseDouble(meascon.getString("移动距离（mm）：")));
        sws.setStartEX0(Double.parseDouble(meascon.getString("起始点EX0（mm）：")));
        sws.setStartEY0(Double.parseDouble(meascon.getString("起始点EY0(mm)")));
        sws.setStrain(Double.parseDouble(meascon.getString("张力（kg）：")));
        sws.setCurrent(Double.parseDouble(meascon.getString("I(A)")));
        sws.setCutOffFrequency(Double.parseDouble(meascon.getString("截止频率(Hz)")));
        sws.setMeasBy(measby);
        sws.setMeasAt(measat);
        sws.setDescription(remark);
        sws.setMeasDate(strToDate(measdate));
        em.persist(sws);
        et.commit();
//       Integer id=sws.getSwRunId();
//       System.out.println(id);
        et.begin();
        SwsDataTable swsdata=new SwsDataTable();
        swsdata.setRunId(sws);
        swsdata.setRawData(rawdata.toString().trim());
        em.persist(swsdata);
        et.commit();
    }
    
}
