/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.magnet.servlet;

import heps.db.magnet.tools.ReadExl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    static Integer swscon_row = 13;
    static Integer rcscon_row = 13;
    static Integer hallcon_row = 13;
    Integer magid;
    String filetype, measdate, measby, measat, remark;

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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding(request.getCharacterEncoding());
        ArrayList sheetname = new ArrayList();
        ReadExl readexcel = new ReadExl();
        try {
            List<FileItem> list = upload.parseRequest(request);
            for (int i = 0; i < list.size(); i++) {
                FileItem item = list.get(i);
                if (!item.isFormField()) {
                    // if (item.getName().endsWith(".xls") || item.getName().endsWith(".xlsx")) {                        
                    Workbook wb = ReadExl.getWorkbook(item.getInputStream());
                    if (wb == null) {
                        out.write("{\"result\":\"文件格式有误，请另存为xls或xlsx格式后再上传。\"}");
                    } else {
                        int sheetnum = wb.getNumberOfSheets();
                        for (int j = 0; j < sheetnum; j++) {
                            sheetname.add(wb.getSheetName(j));
                        }
                        if (filetype.equals("sws")) {
                            readexcel.insertSWSData(wb, sheetname.get(0).toString(), swscon_row, magid, measdate, measby, measat, remark);
                        } else if (filetype.equals("rcs")) {
                            readexcel.insertRCSData(wb, sheetname.get(0).toString(), rcscon_row, magid, measdate, measby, measat, remark);
                           // System.out.println("rcs");
                        } else if (filetype.equals("hall")) {
                            System.out.println("hall");    
                        } else {
                            out.write("{\"result\":\"wrong meas type\"}");
                        }
                        out.write("{\"result\":\"OK\"}");
                    }
//                    } else {
//                        // 说明文件格式不符合要求
//                        out.write("{\"result\":\"文件格式有误，请上传xls或xlsx格式文件\"}");
//                    }
                } else {
                    // 不是file类型的话，就利用getFieldName判断name属性获取相应的值
                    if (item.getFieldName().equals("identity")) {
                        filetype = item.getString();
                        filetype = new String(filetype.getBytes("ISO-8859-1"), "utf-8");
                        // System.out.println("filetype:" + filetype);
                    } else if (item.getFieldName().equals("hd1")) {
                        magid = Integer.parseInt(item.getString());
                    } else if (item.getFieldName().equals("measdate")) {
                        measdate = item.getString();
                    } else if (item.getFieldName().equals("measby")) {
                        measby = item.getString();
                        measby = new String(measby.getBytes("ISO-8859-1"), "utf-8");
                    } else if (item.getFieldName().equals("measat")) {
                        measat = item.getString();
                        measat = new String(measat.getBytes("ISO-8859-1"), "utf-8");
                    } else if (item.getFieldName().equals("remark")) {
                        remark = item.getString();
                        remark = new String(remark.getBytes("ISO-8859-1"), "utf-8");
                    }
                }
            }
            out.flush();
            out.close();
        } catch (IOException | NumberFormatException | FileUploadException | InvalidFormatException e) {
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
