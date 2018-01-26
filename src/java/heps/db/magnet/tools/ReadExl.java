/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.magnet.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONArray;
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

    private SummaryInformation si;
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
public ArrayList getSWSCon(Workbook wb, String sheetName, int row_num){
    JSONObject Json = new JSONObject();  
JSONArray JsonArray = new JSONArray();  
    ArrayList sws_con=new ArrayList();
    String key="";
    Double value=0.0;
    Sheet sheet = wb.getSheet(sheetName);
        for (int i = 0; i < row_num; i++) {
            if (sheet.getRow(i) != null) {               
                for (int j = 0; j < sheet.getRow(i).getPhysicalNumberOfCells(); j++) {
                    Cell cell = sheet.getRow(i).getCell(j);
                    if (sheet.getRow(i).getCell(j) != null) {
                        switch (cell.getCellTypeEnum()) {
                            case NUMERIC:
                                sws_con.add(cell.getNumericCellValue());
                                value=cell.getNumericCellValue();
                                //System.out.print("NUMERIC类型数据" + j + cell.getNumericCellValue()+"\t");
                                break;
                            case BLANK:
                                //System.out.print("空类型" + j + "\t");
                                break;
                            case STRING:
                                key=cell.getStringCellValue();
                               // System.out.print("字符串" + j + cell.getStringCellValue() + "\t");
                                break;
                            case FORMULA:
                               // System.out.print("公式" + j + "\t");
                                break;
                            case BOOLEAN:
                               // System.out.print("布尔型" + j + "\t");
                                break;
                            case ERROR:
                               // System.out.print("错误" + j + "\t");
                                break;
                            case _NONE:
                               // System.out.print("没有" + j + "\t");
                                break;
                            default:
                                //System.out.print("其他类型的数据" + j + "\t");
                                break;
                        }
                        Json.put(key, value);
                        System.out.println(Json.toString());
                    } 
                }               
            } else {
                //System.out.println("第" + (i + 1) + "行是空行");
            }
        }
return sws_con;
}
    public void checkData(Workbook wb, String sheetName, int row_num) {
        Sheet sheet = wb.getSheet(sheetName);
        for (int i = 0; i <= row_num; i++) {
            if (sheet.getRow(i) != null) {
                System.out.print("第" + (i + 1) + "行数据\t");
                for (int j = 0; j < sheet.getRow(i).getPhysicalNumberOfCells() - 1; j++) {
                    Cell cell = sheet.getRow(i).getCell(j);
                    if (sheet.getRow(i).getCell(j) != null) {

                        switch (cell.getCellTypeEnum()) {
                            case NUMERIC:
                                System.out.print("NUMERIC类型数据" + j + "\t");
                                break;
                            case BLANK:
                                System.out.print("空类型" + j + "\t");
                                break;
                            case STRING:
                                System.out.print("字符串" + j + cell.getStringCellValue() + "\t");
                                break;
                            case FORMULA:
                                System.out.print("公式" + j + "\t");
                                break;
                            case BOOLEAN:
                                System.out.print("布尔型" + j + "\t");
                                break;
                            case ERROR:
                                System.out.print("错误" + j + "\t");
                                break;
                            case _NONE:
                                System.out.print("没有" + j + "\t");
                                break;
                            default:
                                System.out.print("其他类型的数据" + j + "\t");
                                break;
                        }
                    } else {
                        System.out.print("空的" + j + "\t");
                        continue;
                    }
                }
                System.out.println();
            } else {
                System.out.println("第" + (i + 1) + "行是空行");
            }
        }
    }   
}
