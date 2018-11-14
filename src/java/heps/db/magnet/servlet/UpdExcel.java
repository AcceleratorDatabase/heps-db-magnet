/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.magnet.servlet;

import heps.db.magnet.tools.ReadExl;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author qiaoys
 */
public class UpdExcel extends HttpServlet {

    public Double precalc(Object obj) {
        if (obj.toString().isEmpty()) {
            return null;
        } else {
            return Double.parseDouble(obj.toString());
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet UpdExcel</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet UpdExcel at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Integer magid = 0, status = 0;
        String filetype = null, measdate = null, measby = null, measat = null, remark = null;
//        String realPath = "E:/analysis";
        String realPath = this.getServletContext().getRealPath("/WEB-INF");
        String dir = "";
        Double hall_current = 0.0, hall_gage = 0.0, roomtemp=0.0;
        ArrayList<String> ana_files = new ArrayList<>();
//        String button=request.getParameter("button");
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding(request.getCharacterEncoding());
        ArrayList sheetname = new ArrayList();
        ReadExl readexcel = new ReadExl();
        try {
            List<FileItem> list = upload.parseRequest(request);   
            for (int i = 0; i <11; i++) {
                System.out.println(i);
                FileItem f = list.get(i);
                if (f.isFormField()) {
                     System.out.println("field");
                    if (f.getFieldName().equals("identity")) {
                        filetype = f.getString();
                        filetype = new String(filetype.getBytes("ISO-8859-1"), "utf-8");
                    } else if (f.getFieldName().equals("hd1")) {
                        magid = Integer.parseInt(f.getString());
                    } else if (f.getFieldName().equals("current")) {
                        hall_current = precalc(f.getString());
                    } else if (f.getFieldName().equals("watergage")) {
                        hall_gage = precalc(f.getString());
                    } else if (f.getFieldName().equals("measdate")) {
                        measdate = f.getString();
                    } else if (f.getFieldName().equals("measby")) {
                        measby = f.getString();
                        measby = new String(measby.getBytes("ISO-8859-1"), "utf-8");
                    } else if (f.getFieldName().equals("measat")) {
                        measat = f.getString();
                        measat = new String(measat.getBytes("ISO-8859-1"), "utf-8");
                    }  else if (f.getFieldName().equals("roomtemp")) {
                        roomtemp =precalc(f.getString()) ;            
                    }  else if (f.getFieldName().equals("remark")) {
                        remark = f.getString();
                        remark = new String(remark.getBytes("ISO-8859-1"), "utf-8");
                    }
                } else {                   
                }

            }            
            for (int i = list.size()-1; i >10; i--) {               
                FileItem f = list.get(i);
                if (f.isFormField()) {                    
                } else {
                   
                    switch (filetype){
                            case "sws":
                              dir="uploadfile/meas/sws";
                                break;
                            case "rcs":
                                 dir="uploadfile/meas/rcs";
                                 System.out.println("rcs");
                                break;
                            case "hall":
                                 dir="uploadfile/meas/hall";
                                break;                        
                        }
                    String srcName = f.getName();// 取得上传时的文件名  
                    System.out.println(i+':'+srcName);
                    srcName = srcName.toLowerCase();// 统一将文件名改为小写  
                    String extName = "";// 扩展名  
                    int pos = srcName.lastIndexOf('.');
                    // 因为有的文件上传它可能没有扩展名，所以这里注意要进行一步判断，判断后再取得.扩展名  
                    if (pos != -1) {
                        extName = srcName.substring(pos);
                    }
                    // 取得当前时间的纳秒数加扩展名来做保存文件的文件名  
                    File uploadPath = new File(realPath, dir);
                    uploadPath.mkdirs();
                    String ana_name = srcName.substring(0, pos) + "-" + System.nanoTime() + extName;
                    File file = new File(uploadPath, ana_name);
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    try {
                        f.write(file);// 保存到指定的目录中去  
                    } catch (Exception ex) {
                        Logger.getLogger(UpdExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    ana_files.add(ana_name);
                    System.out.println(ana_files.toString());
                    if (i == 11) {
                        try {
                            //System.out.println("working");
                            Workbook wb = ReadExl.getWorkbook(f.getInputStream());
                            if (wb == null) {
                                out.write("{\"result\":\"文件格式有误，请另存为xls或xlsx格式后再上传。\"}");
                            } else {
                                int sheetnum = wb.getNumberOfSheets();
                                for (int j = 0; j < sheetnum; j++) {
                                    sheetname.add(wb.getSheetName(j));
                                }
                                switch (filetype) {
                                    case "sws":
                                        status = readexcel.insertSWSData(wb, sheetname, magid, measdate, measby, measat, roomtemp, remark, ana_files.toString());
                                        break;
                                    case "rcs":
                                        status = readexcel.insertRCSData(wb, sheetname, magid, measdate, measby, measat, roomtemp, remark, ana_files.toString());
                                        break;
                                    case "hall":
                                        status = readexcel.insertHallData(wb, sheetname, magid, hall_current, hall_gage, measdate, measby, measat, roomtemp, remark, ana_files.get(ana_files.size() - 1), ana_files.toString());
                                        break;
                                    default:
                                        break;
                                }
                                if (status == 1) {
                                    out.write("{\"result\":\"OK\"}");
                                } else {
                                    out.write("{\"result\":\"文件格式有误\"}");
                                }
                            }
                        } catch (InvalidFormatException ex) {
                            Logger.getLogger(UpdExcel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            }
            out.flush();
            out.close();
        } catch (IOException | NumberFormatException | FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
