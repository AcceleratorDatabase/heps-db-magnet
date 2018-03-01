/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.magnet.jpa;

import heps.db.magnet.entity.DeviceInfoTable;
import heps.db.magnet.entity.HallDataAllTable;
import heps.db.magnet.entity.HallDataTable;
import heps.db.magnet.entity.HallProbeSystemTable;
import heps.db.magnet.entity.RcsDataAllTable;
import heps.db.magnet.entity.RcsDataTable;
import heps.db.magnet.entity.RotCoilSystemTable;
import heps.db.magnet.entity.StretchedWireSystemTable;
import heps.db.magnet.entity.SwsDataTable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import net.sf.json.JSONException;
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

    public JSONObject combine2Json(ArrayList sheetNames, ArrayList alldata) {
        JSONObject json = new JSONObject();
        String[] alldata_piece = alldata.toString().split("//");

        for (int i = 0; i < sheetNames.size(); i++) {
            json.put(sheetNames.get(i), alldata_piece[i]);
        }
        //System.out.println(json.get("横向场").toString());
        return json;
    }

    public Integer insertSWSMeas(JSONObject meascon, Integer magid, String measdate, String measby, String measat, String remark, ArrayList rawdata) {
        StretchedWireSystemTable sws = new StretchedWireSystemTable();
        DeviceInfoTable device;
        try {
            device = (DeviceInfoTable) em.createNamedQuery("DeviceInfoTable.findByDeviceId").setParameter("deviceId", magid).getSingleResult();
            sws.setDeviceId(device);
            sws.setSamplingRate(Double.parseDouble(meascon.getString("采样率（Hz）：")));
            sws.setSpeed(Double.parseDouble(meascon.getString("移动速度（m/s）：")));
            sws.setAcceleratedSpeed(Double.parseDouble(meascon.getString("a加速度（m/s2）：")));
            sws.setDistance(Double.parseDouble(meascon.getString("移动距离（mm）：")));
            sws.setStartEX0(Double.parseDouble(meascon.getString("起始点EX0（mm）：")));
            sws.setStartEY0(Double.parseDouble(meascon.getString("起始点EY0(mm)")));
            sws.setStrain(Double.parseDouble(meascon.getString("张力（kg）：")));
            sws.setMeasCurrent(Double.parseDouble(meascon.getString("I(A)")));
            sws.setCutOffFrequency(Double.parseDouble(meascon.getString("截止频率(Hz)")));
            sws.setMeasBy(measby);
            sws.setMeasAt(measat);
            sws.setDescription(remark);
            sws.setMeasDate(strToDate(measdate));
            em.persist(sws);
            et.commit();

            et.begin();
            SwsDataTable swsdata = new SwsDataTable();
            swsdata.setRunId(sws);
            swsdata.setRawData(rawdata.toString());
            em.persist(swsdata);
            et.commit();
            return 1;
        } catch (JSONException e) {
            return 0;
        }
    }

    public String querySWSBymagid(Integer magid) {
        DeviceInfoTable device;
        device = (DeviceInfoTable) em.createNamedQuery("DeviceInfoTable.findByDeviceId").setParameter("deviceId", magid).getSingleResult();
        Query query = em.createQuery("SELECT s FROM StretchedWireSystemTable s WHERE s.deviceId =:deviceId ");
        query.setParameter("deviceId", device);
        List<StretchedWireSystemTable> re = query.getResultList();
        return re.toString();
    }

    public String querySWSDataByrunid(Integer runId) {
        StretchedWireSystemTable sws;
        sws = (StretchedWireSystemTable) em.createNamedQuery("StretchedWireSystemTable.findBySwRunId").setParameter("swRunId", runId).getSingleResult();
        Query query = em.createQuery("SELECT s FROM SwsDataTable s WHERE s.runId =:runId ");
        query.setParameter("runId", sws);
        List<DeviceInfoTable> re = query.getResultList();
        //System.out.println(re.toString());
        return re.toString();
    }
    

    public Integer insertRCSMeas(JSONObject meascon, Integer magid, String measdate, String measby, String measat, String remark, List<Object> anadata, List<Object> rawdata, String[] analysis) {
        String[] analysis_piece;
        RotCoilSystemTable rcs = new RotCoilSystemTable();
        DeviceInfoTable device;
        try {
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

            et.begin();
            RcsDataAllTable rcsdataall = new RcsDataAllTable();
            rcsdataall.setRunId(rcs);
            rcsdataall.setAnalysisData(anadata.toString());
            rcsdataall.setRawData(rawdata.toString());
            em.persist(rcsdataall);
            et.commit();

            for (int i = 3; i < analysis.length - 1; i++) {
                analysis_piece = analysis[i].split(",");
                et.begin();
                RcsDataTable rcsdata = new RcsDataTable();
                rcsdata.setRunId(rcs);
                rcsdata.setPhi(Double.parseDouble(analysis_piece[2]));
                rcsdata.setAngle(Double.parseDouble(analysis_piece[3]));
                rcsdata.setBnB2(Double.parseDouble(analysis_piece[4]));
                rcsdata.setBn(Double.parseDouble(analysis_piece[5]));
                rcsdata.setAn(Double.parseDouble(analysis_piece[6]));
                em.persist(rcsdata);
                et.commit();
            }
            return 1;
        } catch (JSONException e) {
            return 0;
        }
    }
public String queryRCSBymagid(Integer magid) {
        DeviceInfoTable device;
        device = (DeviceInfoTable) em.createNamedQuery("DeviceInfoTable.findByDeviceId").setParameter("deviceId", magid).getSingleResult();
        Query query = em.createQuery("SELECT r FROM RotCoilSystemTable r WHERE r.deviceId =:deviceId ");
        query.setParameter("deviceId", device);
        List<RotCoilSystemTable> re = query.getResultList();
        return re.toString();
    }

 public String queryRCSDataAllByrunid(Integer runId) {
        RotCoilSystemTable rcs;
        rcs = (RotCoilSystemTable) em.createNamedQuery("RotCoilSystemTable.findByRotCoilRunId").setParameter("rotCoilRunId", runId).getSingleResult();
        Query query = em.createQuery("SELECT r FROM RcsDataAllTable r WHERE r.runId =:runId ");
        query.setParameter("runId", rcs);
        List<RcsDataAllTable> re = query.getResultList();
        //System.out.println(re.toString());
        return re.toString();
    }
 public String queryRCSDataByrunid(Integer runId) {
        RotCoilSystemTable rcs;
        rcs = (RotCoilSystemTable) em.createNamedQuery("RotCoilSystemTable.findByRotCoilRunId").setParameter("rotCoilRunId", runId).getSingleResult();
        Query query = em.createQuery("SELECT s FROM RcsDataTable s WHERE s.runId =:runId ");
        query.setParameter("runId", rcs);
        List<RcsDataTable> re = query.getResultList();
        //System.out.println(re.toString());
        return re.toString();
    }
    public Integer insertHallMotiCurve(HallProbeSystemTable hall, String content, String measdata) {
        String[] analysis;
        String[] analysis_piece;
        analysis = measdata.split("\n");
        try {
            for (int i = 2; i < analysis.length - 1; i++) {
                analysis_piece = analysis[i].split(",");
                et.begin();
                HallDataTable halldata = new HallDataTable();
                halldata.setRunId(hall);
                halldata.setCurrent(Double.parseDouble(analysis_piece[1]));
                halldata.setB(Double.parseDouble(analysis_piece[2]));
                em.persist(halldata);
                et.commit();

            }
            et.begin();
            HallDataAllTable halldataall = new HallDataAllTable();
            halldataall.setRunId(hall);
            halldataall.setContent(content);
            halldataall.setAnalysisData(measdata);
            em.persist(halldataall);
            et.commit();
            return 1;
        } catch (JSONException e) {
            return 0;
        }
    }

    public Integer insertHallXFiled(HallProbeSystemTable hall, String content, Double current, String measdata) {
        String[] analysis;
        String[] analysis_piece;
        analysis = measdata.split("\n");
        try {
            for (int i = 3; i < analysis.length - 1; i++) {
                analysis_piece = analysis[i].split(",");
                et.begin();
                HallDataTable halldata = new HallDataTable();
                halldata.setRunId(hall);
                halldata.setCurrent(current);
                halldata.setX(Double.parseDouble(analysis_piece[1]));
                halldata.setB(Double.parseDouble(analysis_piece[2]));
                em.persist(halldata);
                et.commit();
            }
            for (int i = 3; i < analysis.length - 1; i++) {
                analysis_piece = analysis[i].split(",");
                et.begin();
                HallDataTable halldata = new HallDataTable();
                halldata.setRunId(hall);
                halldata.setCurrent(current);
                halldata.setY(Double.parseDouble(analysis_piece[4]));
                halldata.setB(Double.parseDouble(analysis_piece[5]));
                em.persist(halldata);
                et.commit();
            }
            et.begin();
            HallDataAllTable halldataall = new HallDataAllTable();
            halldataall.setRunId(hall);
            halldataall.setContent(content);
            halldataall.setAnalysisData(measdata);
            em.persist(halldataall);
            et.commit();
            return 1;
        } catch (JSONException e) {
            return 0;
        }

    }

    public Integer insertHallIntegMC(HallProbeSystemTable hall, String content, String measdata) {
        String[] analysis;
        String[] analysis_piece;
        analysis = measdata.split("\n");
        try {
            for (int i = 2; i < analysis.length - 1; i++) {
                analysis_piece = analysis[i].split(",");
                et.begin();
                HallDataTable halldata = new HallDataTable();
                halldata.setRunId(hall);
                halldata.setX(0.0);
                halldata.setY(0.0);
                halldata.setCurrent(Double.parseDouble(analysis_piece[1]));
                halldata.setGl(Double.parseDouble(analysis_piece[2]));
                em.persist(halldata);
                et.commit();

            }
            et.begin();
            HallDataAllTable halldataall = new HallDataAllTable();
            halldataall.setRunId(hall);
            halldataall.setContent(content);
            halldataall.setAnalysisData(measdata);
            em.persist(halldataall);
            et.commit();
            return 1;
        } catch (JSONException e) {
            return 0;
        }
    }

    public Integer insertHallIntegFiled(HallProbeSystemTable hall, String content, Double current, String measdata) {

        String[] analysis;
        String[] analysis_piece;
        analysis = measdata.split("\n");
        try {
            for (int i = 3; i < analysis.length - 1; i++) {
                analysis_piece = analysis[i].split(",");
                et.begin();
                HallDataTable halldata = new HallDataTable();
                halldata.setRunId(hall);
                halldata.setCurrent(current);
                halldata.setX(Double.parseDouble(analysis_piece[1]));
                halldata.setGl(Double.parseDouble(analysis_piece[2]));
                em.persist(halldata);
                et.commit();
            }
            for (int i = 3; i < analysis.length - 1; i++) {
                analysis_piece = analysis[i].split(",");
                et.begin();
                HallDataTable halldata = new HallDataTable();
                halldata.setRunId(hall);
                halldata.setCurrent(current);
                halldata.setY(Double.parseDouble(analysis_piece[4]));
                halldata.setGl(Double.parseDouble(analysis_piece[5]));
                em.persist(halldata);
                et.commit();
            }
            et.begin();
            HallDataAllTable halldataall = new HallDataAllTable();
            halldataall.setRunId(hall);
            halldataall.setContent(content);
            halldataall.setAnalysisData(measdata);
            em.persist(halldataall);
            et.commit();
            return 1;
        } catch (JSONException e) {
            return 0;
        }

    }

    public Integer insertHallTemp(HallProbeSystemTable hall, String content, String measdata) {
        try {
            et.begin();
            HallDataAllTable halldataall = new HallDataAllTable();
            halldataall.setRunId(hall);
            halldataall.setContent(content);
            halldataall.setAnalysisData(measdata);
            em.persist(halldataall);
            et.commit();
            return 1;
        } catch (JSONException e) {
            return 0;
        }
    }

    public Integer insertHallMeas(Integer magid, Double current, Double pressure, String measdate, String measby, String measat, String remark, ArrayList sheetNames, ArrayList measdata) {
        JSONObject jsondata;
        jsondata = combine2Json(sheetNames, measdata);
        HallProbeSystemTable hall = new HallProbeSystemTable();
        DeviceInfoTable device;
        //measure condition 
        device = (DeviceInfoTable) em.createNamedQuery("DeviceInfoTable.findByDeviceId").setParameter("deviceId", magid).getSingleResult();
        hall.setDeviceId(device);
        hall.setMeasCurrent(current);
        hall.setWaterGage(pressure);
        hall.setMeasAt(measat);
        hall.setMeasBy(measby);
        hall.setMeasDate(strToDate(measdate));
        hall.setDescription(remark);
        em.persist(hall);
        et.commit();
        insertHallMotiCurve(hall, "励磁曲线", jsondata.get("励磁曲线").toString());
        insertHallXFiled(hall, "横向场", current, jsondata.get("横向场").toString());
        insertHallIntegMC(hall, "积分励磁", jsondata.get("积分励磁").toString());
        insertHallIntegFiled(hall, "积分场", current, jsondata.get("积分场").toString());
        insertHallTemp(hall, "温度记录", jsondata.get("温度记录").toString());
        return 1;
    }
public String queryHallBymagid(Integer magid) {
        DeviceInfoTable device;
        device = (DeviceInfoTable) em.createNamedQuery("DeviceInfoTable.findByDeviceId").setParameter("deviceId", magid).getSingleResult();
        Query query = em.createQuery("SELECT h FROM HallProbeSystemTable h WHERE h.deviceId =:deviceId ");
        query.setParameter("deviceId", device);
        List<HallProbeSystemTable> re = query.getResultList();
        return re.toString();
    }

 public String queryHallDataAllByrunid(Integer runId) {
        HallProbeSystemTable hall;
        hall = (HallProbeSystemTable) em.createNamedQuery("HallProbeSystemTable.findByHallProbeRunId").setParameter("hallProbeRunId", runId).getSingleResult();
        Query query = em.createQuery("SELECT h FROM HallDataAllTable h WHERE h.runId =:runId ");
        query.setParameter("runId", hall);
        List<HallDataAllTable> re = query.getResultList();
        //System.out.println(re.toString());
        return re.toString();
    }
// public String queryHallDataByrunid(Integer runId) {
//        RotCoilSystemTable rcs;
//        rcs = (RotCoilSystemTable) em.createNamedQuery("RotCoilSystemTable.findByRotCoilRunId").setParameter("rotCoilRunId", runId).getSingleResult();
//        Query query = em.createQuery("SELECT s FROM RcsDataTable s WHERE s.runId =:runId ");
//        query.setParameter("runId", rcs);
//        List<DeviceInfoTable> re = query.getResultList();
//        //System.out.println(re.toString());
//        return re.toString();
//    }
}
