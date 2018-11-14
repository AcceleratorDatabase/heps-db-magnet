/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.magnet.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author qiaoys
 */
public class DownloadMeasFiles extends HttpServlet {

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
//            out.println("<title>Servlet DownloadMeasFiles</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet DownloadMeasFiles at " + request.getContextPath() + "</h1>");
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
        String FileName=request.getParameter("filename");
        String filetype=request.getParameter("filetype");
        String realPath = this.getServletContext().getRealPath("/WEB-INF");
        String FilePath =realPath+"\\uploadfile\\meas\\"+filetype+"\\";   
        //String FilePath ="E:/analysis/"+filetype+"/";
        System.out.println(FilePath+FileName);
                if (!FileName.equals("")) {
            File f = new File(FilePath + FileName);
            if (f.exists()) {
                FileInputStream fis = new FileInputStream(f);
                String filename = URLEncoder.encode(f.getName(), "utf-8"); //解决中文文件名下载后乱码的问题  
                byte[] b = new byte[fis.available()];
                fis.read(b);
                response.setCharacterEncoding("utf-8");
                response.setHeader("Content-Disposition", "attachment; filename=" + filename + "");
                //输出
                try ( //获取响应报文输出流对象
                        ServletOutputStream out = response.getOutputStream()) {
                    //输出
                    out.write(b);
                    out.flush();
                }
            } else {
                PrintWriter out = response.getWriter();
                out.print("<script language='javascript'>alert('File Not Found.');window.history.go(-1);</script>");
            }
        } else {
            PrintWriter out = response.getWriter();
            out.print("<script language='javascript'>alert('File Not Found.');window.history.go(-1);</script>");
        }
        
        //System.out.println(filetype+":"+filename);
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
