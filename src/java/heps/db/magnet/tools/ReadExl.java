/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.magnet.tools;

import heps.db.magnet.jpa.MeasureAPI;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.json.JSONObject;
import org.apache.poi.hpsf.MarkUnsupportedException;
import org.apache.poi.hpsf.NoPropertySetStreamException;
import org.apache.poi.hpsf.PropertySetFactory;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.eventfilesystem.POIFSReader;
import org.apache.poi.poifs.eventfilesystem.POIFSReaderEvent;
import org.apache.poi.poifs.eventfilesystem.POIFSReaderListener;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author lv
 * @author chu
 */
public class ReadExl {
// get Workbook by local path
//    public static Workbook getWorkbook(String filePath) {
//        if (filePath == null || "".equals(filePath)) {
//            System.out.println("Warning: Please assign the specific path of the spreadsheet!");
//            return null;
//        } else {
//            FileInputStream inp = FileTools.getFileInputStream(filePath);
//            Workbook wb = FileTools.getWorkbook(inp);
//            return wb;
//        }
//    }

    public static Workbook getWorkbook(InputStream inp) throws InvalidFormatException, IOException {
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(inp);
        } catch (InvalidFormatException ex) {
            return null;
        } catch (IOException ex) {
            Logger.getLogger(ReadExl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return wb;
    }

    public JSONObject getCon(Workbook wb, String sheetName, int row_num) {
        JSONObject sws_con_json = new JSONObject();
        String key = "";
        Double value = 0.0;
        Sheet sheet = wb.getSheet(sheetName);
        for (int i = 0; i < row_num; i++) {
            if (sheet.getRow(i) != null) {
                for (int j = 0; j < sheet.getRow(i).getPhysicalNumberOfCells(); j++) {
                    Cell cell = sheet.getRow(i).getCell(j);
                    if (sheet.getRow(i).getCell(j) != null) {
                        switch (cell.getCellTypeEnum()) {
                            case NUMERIC:
                                value = cell.getNumericCellValue();
                                break;
                            case BLANK:
                                break;
                            case STRING:
                                key = cell.getStringCellValue().trim();
                                break;
                            case FORMULA:
                                break;
                            case BOOLEAN:
                                break;
                            case ERROR:
                                break;
                            case _NONE:
                                break;
                            default:
                                break;
                        }
                        sws_con_json.put(key, value);
                    }
                }
            }
        }
        return sws_con_json;
    }

    public ArrayList getData(Workbook wb, String sheetName, int row_num) {
        ArrayList sws_con = new ArrayList();
        Sheet sheet = wb.getSheet(sheetName);
        for (int i = row_num; i < sheet.getLastRowNum(); i++) {
            if (sheet.getRow(i) != null) {
                for (int j = 0; j < sheet.getRow(i).getPhysicalNumberOfCells(); j++) {
                    Cell cell = sheet.getRow(i).getCell(j);
                    if (sheet.getRow(i).getCell(j) != null) {
                        switch (cell.getCellTypeEnum()) {
                            case NUMERIC:
                                sws_con.add(cell.getNumericCellValue());
                                break;
                            case BLANK:
                                break;
                            case STRING:
                                sws_con.add(cell.getStringCellValue().trim());
                                break;
                            case FORMULA:
                                break;
                            case BOOLEAN:
                                break;
                            case ERROR:
                                break;
                            case _NONE:
                                break;
                            default:
                                break;
                        }
                    }
                }
                sws_con.add("//");
            }
        }
        return sws_con;
    }       
        
    public void insertSWSData(Workbook wb, String sheetName, Integer row_num, Integer magid, String measdate, String measby, String measat, String remark) {
        JSONObject meascon;
        ArrayList measrawdata;
        MeasureAPI m = new MeasureAPI();
        m.init();
        meascon = getCon(wb, sheetName, row_num);
        measrawdata = getData(wb, sheetName, row_num);
        m.insertSWSMeas(meascon, magid, measdate, measby, measat, remark, measrawdata);
        m.destroy();
    }
    public void insertRCSData(Workbook wb, String sheetName, Integer row_num, Integer magid, String measdate, String measby, String measat, String remark) {
        JSONObject meascon;
        ArrayList measdata;
        List<Object> measanadata;
        List<Object> measrawdata;
        Integer splitIndex;
        String[] analysis;
        MeasureAPI m = new MeasureAPI();
        m.init();
        meascon = getCon(wb, sheetName, row_num);        
        measdata = getData(wb, sheetName, row_num);      
        splitIndex=measdata.indexOf("原始数据");         
        measanadata= measdata.subList(0, splitIndex);
        analysis=measanadata.toString().split("//");
        measrawdata= measdata.subList(splitIndex+1,measdata.size() );
        m.insertRCSMeas(meascon, magid, measdate, measby, measat, remark, measanadata,measrawdata,analysis);
        m.destroy();
    }

}
