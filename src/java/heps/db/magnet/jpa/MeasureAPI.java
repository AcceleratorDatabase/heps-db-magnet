/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.magnet.jpa;

import heps.db.magnet.entity.DeviceInfoTable;
import heps.db.magnet.entity.RotCoilSystemTable;
import heps.db.magnet.entity.StretchedWireSystemTable;
import heps.db.magnet.entity.SwsDataTable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
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

    public String getObjectKey(JSONObject json, String name) {
        ArrayList keys = new ArrayList();
        Iterator<String> it = json.keys();
        while (it.hasNext()) {
            String key = it.next();
            keys.add(key);
        }
        return keys.get(keys.indexOf(name) + 1).toString();
    }

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

    public void insertSWSMeas(JSONObject meascon, Integer magid, String measdate, String measby, String measat, String remark, ArrayList rawdata) {
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
        sws.setMeasBy(measby);
        sws.setMeasAt(measat);
        sws.setDescription(remark);
        sws.setMeasDate(strToDate(measdate));
        em.persist(sws);
        et.commit();
//       Integer id=sws.getSwRunId();
//       System.out.println(id);
        et.begin();
        SwsDataTable swsdata = new SwsDataTable();
        swsdata.setRunId(sws);
        swsdata.setRawData(rawdata.toString());
        em.persist(swsdata);
        et.commit();
    }

    public void insertRCSMeas(JSONObject meascon, Integer magid, String measdate, String measby, String measat, String remark, ArrayList rawdata) {
        RotCoilSystemTable rcs = new RotCoilSystemTable();
        DeviceInfoTable device;
        device = (DeviceInfoTable) em.createNamedQuery("DeviceInfoTable.findByDeviceId").setParameter("deviceId", magid).getSingleResult();
        rcs.setDeviceId(device);
        String po = getObjectKey(meascon, "Polarity：");
        rcs.setPolarity(po);
        rcs.setGivenCurrent(Double.parseDouble(meascon.getString("给定电流(A):")));
        rcs.setActualCurrent(Double.parseDouble(meascon.getString("实际电流(A):")));
        rcs.setGain(Double.parseDouble(meascon.getString("Gain：")));
        rcs.setStartPosition(Double.parseDouble(meascon.getString("start position：")));
        rcs.setRotationRate(Double.parseDouble(meascon.getString("转速：")));
        rcs.setRRef(Double.parseDouble(meascon.getString("Rref(mm)：")));
        rcs.setDx(Double.parseDouble(meascon.getString("dx=")));
        rcs.setDy(Double.parseDouble(meascon.getString("dy=")));
        rcs.setDr(Double.parseDouble(meascon.getString("dr=")));
        rcs.setMeasBy(measby);
        rcs.setMeasAt(measat);
        rcs.setDescription(remark);
        rcs.setMeasDate(strToDate(measdate));
        em.persist(rcs);
        et.commit();
//       Integer id=sws.getSwRunId();
//       System.out.println(id);
//        et.begin();
//        SwsDataTable swsdata=new SwsDataTable();
//        swsdata.setRunId(sws);
//        swsdata.setRawData(rawdata.toString().trim());
//        em.persist(swsdata);
//        et.commit();
    }
}