/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.magnet.tools;
import heps.db.magnet.entity.HallProbeSystemTable;
import heps.db.magnet.jpa.MeasureAPI;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONObject;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author qiaoys
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
public static String getCellValueFormula(Cell cell, FormulaEvaluator formulaEvaluator) {
        if (cell == null || formulaEvaluator == null) {
            return null;
        }
            return String.valueOf(formulaEvaluator.evaluate(cell).getNumberValue());      
    }
    public JSONObject getCon(Workbook wb,ArrayList sheetNames, int row_num) {
        JSONObject sws_con_json = new JSONObject();
        String key = "";
        Double value = 0.0;
         for (Object sheetName1 : sheetNames) {
            Sheet sheet = wb.getSheet(sheetName1.toString());
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
    }
        return sws_con_json;
    }

    public ArrayList getData(Workbook wb, String sheetName, int row_num) {
        ArrayList sws_con = new ArrayList();
        Sheet sheet = wb.getSheet(sheetName);
        FormulaEvaluator formulaEvaluator=wb.getCreationHelper().createFormulaEvaluator();
        for (int i = row_num; i <= sheet.getLastRowNum(); i++) {
            if (sheet.getRow(i) != null) {
               // System.out.println("row"+(i+1)+"not null");
                for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
                    //System.out.println("cell num:"+sheet.getRow(i).getPhysicalNumberOfCells());
                    Cell cell = sheet.getRow(i).getCell(j);
                    if (cell != null) {
                        switch (cell.getCellTypeEnum()) {
                            case NUMERIC:
                                sws_con.add(cell.getNumericCellValue());
                                break;
                            case BLANK:
                                //sws_con.add("blank");
                                break;
                            case STRING:
                                sws_con.add(cell.getStringCellValue().trim());
                                break;
                            case FORMULA:                                  
                                sws_con.add(String.valueOf(formulaEvaluator.evaluate(cell).getNumberValue()));
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
                sws_con.add("\n");
            }
        }
        return sws_con;
    }       
       public ArrayList getSheetsData(Workbook wb, ArrayList sheetNames, int row_num) {
        ArrayList sws_con = new ArrayList();
        for (Object sheetName1 : sheetNames) {
            Sheet sheet = wb.getSheet(sheetName1.toString());
            FormulaEvaluator formulaEvaluator=wb.getCreationHelper().createFormulaEvaluator();
            for (int i = row_num; i <= sheet.getLastRowNum(); i++) {
                if (sheet.getRow(i) != null) {
                    // System.out.println("row"+(i+1)+"not null");
                    for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
                        //System.out.println("cell num:"+sheet.getRow(i).getPhysicalNumberOfCells());
                        Cell cell = sheet.getRow(i).getCell(j);
                        if (cell != null) {
                            //System.out.println("row"+(i+1)+",column"+(j+1)+"not null");
                            switch (cell.getCellTypeEnum()) {
                                case NUMERIC:
                                    sws_con.add(cell.getNumericCellValue());
                                    break;
                                case BLANK:
                                    //sws_con.add("blank");
                                    break;
                                case STRING:
                                    sws_con.add(cell.getStringCellValue().trim());
                                    break;
                                case FORMULA:
                                    sws_con.add(String.valueOf(formulaEvaluator.evaluate(cell).getNumberValue()));
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
                    sws_con.add("\n");
                }
            }
            sws_con.add("//");
        }
        return sws_con;
    }    
    public Integer insertSWSData(Workbook wb,ArrayList sheetNames, Integer row_num, Integer magid, String measdate, String measby, String measat, String remark) {
        Integer status;
        JSONObject meascon;
        ArrayList measrawdata;
        MeasureAPI m = new MeasureAPI();
        m.init();
        meascon = getCon(wb, sheetNames, row_num);
        //measrawdata = getData(wb, sheetName, row_num);
        measrawdata=getSheetsData(wb, sheetNames, row_num);
        status=m.insertSWSMeas(meascon, magid, measdate, measby, measat, remark, measrawdata);
        m.destroy();
        return status;
    }
    public Integer insertRCSData(Workbook wb, ArrayList sheetNames, Integer row_num, Integer magid, String measdate, String measby, String measat, String remark) {
        Integer status=0;
        JSONObject meascon;
        ArrayList measdata;
        List<Object> measanadata;
        List<Object> measrawdata;
        Integer splitIndex;
        String[] analysis;
        MeasureAPI m = new MeasureAPI();
        m.init();
        meascon = getCon(wb, sheetNames, row_num);        
        measdata = getSheetsData(wb, sheetNames, row_num);     
        if(measdata.contains("原始数据")){
        splitIndex=measdata.indexOf("原始数据"); 
        measanadata= measdata.subList(0, splitIndex);
        analysis=measanadata.toString().split("\n");
        measrawdata= measdata.subList(splitIndex+1,measdata.size() );
        status=m.insertRCSMeas(meascon, magid, measdate, measby, measat, remark, measanadata,measrawdata,analysis);
        m.destroy();
        }
        return status;
    }
    public Integer insertHallData(Workbook wb,ArrayList sheetNames, Integer row_num, Integer magid, Double current, Double pressure,String measdate, String measby, String measat, String remark) {
        Integer status;
        ArrayList measdata;
        MeasureAPI m = new MeasureAPI();
        m.init();
        measdata=getSheetsData(wb,sheetNames, 0); 
        status=m.insertHallMeas(magid,current,pressure, measdate, measby, measat, remark,sheetNames,measdata);
        //System.out.println(measdata.toString());
        m.destroy();
        return status;
    }

}
