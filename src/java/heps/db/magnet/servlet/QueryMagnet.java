/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.magnet.servlet;

import heps.db.magnet.jpa.DeviceAPI;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author qiaoys
 */
public class QueryMagnet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String result = null;
    private String type, datemin, datemax;
    private Integer family;

    public static Integer precalcInt(Object obj) {
        if (obj.toString().isEmpty()) {
            return null;
        } else {
            return Integer.parseInt(obj.toString());
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet QueryMagnet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet QueryMagnet at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        Integer bydate=0;
        Integer bytype=0;
        Integer byfamily=0;
        DeviceAPI a = new DeviceAPI();
        a.init();
        type = request.getParameter("magtype");
       
        family = precalcInt(request.getParameter("magfamily"));
        
        datemin = request.getParameter("datemin");
       
        datemax = request.getParameter("datemax");
         
        if (type.equals("none")) {
            bytype = 0;
        } else {
            bytype = 1;
        }
        if (family == -1) {
            byfamily = 0;
        } else {
            byfamily = 1;
        }
        //System.out.println("type:"+bytype+" family:"+byfamily+" date:"+datemin+":"+datemax);
        if (datemin.equals("") && datemax.equals("")) {
            bydate = 0;
        } else {
            bydate = 1;
        }

        if (bytype == 0 && byfamily == 0 && bydate == 0) {
            result = a.queryMagnetAll();
        } else if (bytype == 1 && byfamily == 0 && bydate == 0) {
            result = a.queryMagnetByType(type);
        } else if (bytype == 0 && byfamily == 1 && bydate == 0) {
            result = a.queryMagnetByFamily(family);
        } else if (bytype == 1 && byfamily == 1 && bydate == 0) {
            result = a.queryMagnetByTypeFamily(type, family);
        } else if (bytype == 0 && byfamily == 0 && bydate == 1) {
            result = a.queryMagnetByDate(datemin, datemax);
        } else if (bytype == 1 && byfamily == 0 && bydate == 1) {
            result = a.queryMagnetByTypeDate(type, datemin, datemax);
        } else if (bytype == 0 && byfamily == 1 && bydate == 1) {
            result = a.queryMagnetByFamilyDate(family, datemin, datemax);
        } else if (bytype == 0 && byfamily == 1 && bydate == 1) {
            result = a.queryMagnetByTypeFamilyDate(type, family, datemin, datemax);
        }  
        out.print(result);
        a.destroy();
        String[] header_referer = request.getHeader("Referer").split("/");
        String page=header_referer[header_referer.length-1];
        //System.out.println(header_referer[header_referer.length-1]);
        if(page.equals("newmeas.jsp")){
        processRequest(request, response);
        }else{
        request.getSession().setAttribute("value", "{\"rows\":" + result + "}");
        request.getSession().setAttribute("magtype", type);
        request.getSession().setAttribute("magfamily", family);
        request.getSession().setAttribute("datemin", datemin);
        request.getSession().setAttribute("datemax", datemax);
        request.getRequestDispatcher("magnetresult.jsp").forward(request, response);
        }
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
