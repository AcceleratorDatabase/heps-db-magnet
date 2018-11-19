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
public Integer precalcInt(Object obj) {
        if (obj.toString().isEmpty()) {
            return null;
        } else {
            return Integer.parseInt(obj.toString());
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
        String result = null;
        String type, family, section, datemin, datemax,manuby;
        Integer bydate;
        Integer bytype;
        Integer byfamily;
        Integer bysection;
        Integer bymanu;
        DeviceAPI a = new DeviceAPI();
        a.init();
        type = request.getParameter("magtype");

        family = request.getParameter("magfamily");
        
        section = request.getParameter("magsection");
        
        manuby = request.getParameter("manuby");

        datemin = request.getParameter("datemin");

        datemax = request.getParameter("datemax");

        if (type.equals("none")) {
            bytype = 0;
        } else {
            bytype = 1;
        }
        if (family.equals("none")) {
            byfamily = 0;
        } else {
            byfamily = 1;
        }
        if (section.equals("none")) {
            bysection = 0;
        } else {
            bysection = 1;
        }
         if (manuby.equals("")) {
            bymanu = 0;
        } else {
            bymanu = 1;
        }
        
        if (datemin.equals("") && datemax.equals("")) {
            bydate = 0;
        } else {
            bydate = 1;
        }
        //System.out.println("type:"+bytype+" family:"+byfamily+ " bysection: "+bysection+ " bymanu: "+bymanu+" date:"+bydate+":"+datemin+":"+datemax);
        if (bytype == 0 && byfamily == 0 && bysection == 0 && bymanu == 0 && bydate == 0) {//query all
            result = a.queryMagnetAll();
        } 
        
        else if (bytype == 1 && byfamily == 0 && bysection == 0 && bymanu == 0 && bydate == 0) {//query by type
            result = a.queryMagnetByType(type);
        } else if (bytype == 0 && byfamily == 1 && bysection == 0 && bymanu == 0 && bydate == 0) {// query by family
            result = a.queryMagnetByFamily(family);
        } else if (bytype == 0 && byfamily == 0 && bysection == 1 && bymanu == 0 && bydate == 0) {// query by section
            System.out.println("sec");
            result = a.queryMagnetBySection(section);
        }else if (bytype == 0 && byfamily == 0 && bysection == 0 && bymanu == 1 && bydate == 0) {// query by manuby
            result = a.queryMagnetByManuby(manuby);
        }else if (bytype == 0 && byfamily == 0 && bysection == 0 && bymanu == 0 && bydate == 1) {// query by date
            result = a.queryMagnetByDate(datemin, datemax);
        }
        
        else if (bytype == 1 && byfamily == 1 && bysection == 0 && bymanu == 0 && bydate == 0) {// query by type family
           result = a.queryMagnetByTypeFamily(type, family);
        }else if (bytype == 1 && byfamily == 0 && bysection == 1 && bymanu == 0 && bydate == 0) {// query by type section
           result = a.queryMagnetByTypeSection(type, section);
        }else if (bytype == 1 && byfamily == 0 && bysection == 0 && bymanu == 1 && bydate == 0) {// query by type manuby
           result = a.queryMagnetByTypeManuby(type, manuby);
        }else if (bytype == 1 && byfamily == 0 && bysection == 0 && bymanu == 0 && bydate == 1) {// query by type date
           result = a.queryMagnetByTypeDate(type, datemin, datemax);
        }else if (bytype == 0 && byfamily == 1 && bysection == 1 && bymanu == 0 && bydate == 0) {// query by family section
           result = a.queryMagnetByFamilySection(family,section);
        }else if (bytype == 0 && byfamily == 1 && bysection == 0 && bymanu == 1 && bydate == 0) {// query by family manuby
           result = a.queryMagnetByFamilyManuby(family,manuby);
        }else if (bytype == 0 && byfamily == 1 && bysection == 0 && bymanu == 0 && bydate == 1) {// query by family date
           result = a.queryMagnetByFamilyDate(family, datemin, datemax);
        }else if (bytype == 0 && byfamily == 0 && bysection == 1 && bymanu == 1 && bydate == 0) {// query by section manueby
           result = a.queryMagnetBySectionManuby(section, manuby);
        }else if (bytype == 0 && byfamily == 0 && bysection == 1 && bymanu == 0 && bydate == 1) {// query by section date
           result = a.queryMagnetBySectionDate(section, datemin, datemax);
        }else if (bytype == 0 && byfamily == 0 && bysection == 0 && bymanu == 1 && bydate == 1) {// query by manuby date
           result = a.queryMagnetByManubyDate(manuby, datemin, datemax);
        }
        
        else if (bytype == 1 && byfamily == 1 && bysection == 1 && bymanu == 0 && bydate == 0) {// query by type family section
           result = a.queryMagnetByTypeFamilySection(type, family, section);
        }else if (bytype == 1 && byfamily == 1 && bysection == 0 && bymanu == 1 && bydate == 0) {// query by type family manuby
           result = a.queryMagnetByTypeFamilyManuby(type, family, manuby);
        }else if (bytype == 1 && byfamily == 1 && bysection == 0 && bymanu == 0 && bydate == 1) {// query by type family date
           result = a.queryMagnetByTypeFamilyDate(type, family, datemin, datemax);
        }else if (bytype == 1 && byfamily == 0 && bysection == 1 && bymanu == 1 && bydate ==0 ) {// query by type section manuby
           result = a.queryMagnetByTypeSectionManuby(type, section, manuby);
        }else if (bytype == 1 && byfamily == 0 && bysection == 1 && bymanu == 0 && bydate ==1 ) {// query by type section date
           result = a.queryMagnetByTypeSectionDate(type, section, datemin, datemax);
        }else if (bytype == 1 && byfamily == 0 && bysection == 0 && bymanu == 1 && bydate ==1 ) {// query by type manuby date
           result = a.queryMagnetByTypeManubyDate(type, manuby, datemin, datemax);
        }else if (bytype == 0 && byfamily == 1 && bysection == 1 && bymanu == 1 && bydate == 0) {// query by family section manuby
           result = a.queryMagnetByFamilySectionManuby(family, section, manuby);
        }else if (bytype == 0 && byfamily == 1 && bysection == 1 && bymanu == 0 && bydate == 1) {// query by family section date
           result = a.queryMagnetByFamilySectionDate(family, section, datemin, datemax);
        }else if (bytype == 0 && byfamily == 1 && bysection == 0 && bymanu == 1 && bydate == 1) {// query by family  manuby date
           result = a.queryMagnetByFamilyManubyDate(family, manuby, datemin, datemax);
        }else if (bytype == 0 && byfamily == 0 && bysection == 1 && bymanu == 1 && bydate == 1) {// query by section manuby date
           result = a.queryMagnetBySectionManubyDate( section, manuby, datemin, datemax);
        }
        
        else if (bytype == 1 && byfamily == 1 && bysection == 1 && bymanu == 1 && bydate == 0) {// query by type family section manuby 
           result = a.queryMagnetByTypeFamilySectionManuby(type, family, section, manuby);
        }else if (bytype == 1 && byfamily == 1 && bysection == 1 && bymanu == 0 && bydate == 1) {// query by type family section  date
           result = a.queryMagnetByTypeFamilySectionDate(type, family, section, datemin, datemax);
        }else if (bytype == 1 && byfamily == 1 && bysection == 0 && bymanu == 1 && bydate == 1) {// query by type family  manuby date
           result = a.queryMagnetByTypeFamilyManubyDate(type, family, manuby, datemin, datemax);
        }else if (bytype == 1 && byfamily == 0 && bysection == 1 && bymanu == 1 && bydate == 1) {// query by type section manuby date
           result = a.queryMagnetByTypeSectionManubyDate(type, section, manuby, datemin, datemax);
        }else if (bytype == 0 && byfamily == 1 && bysection == 1 && bymanu == 1 && bydate == 1) {// query by family section manuby date
           result = a.queryMagnetByFamilySectionManubyDate(family, section, manuby, datemin, datemax);
        }
        
        
        else if (bytype == 1 && byfamily == 1 && bysection == 1 && bymanu == 1 && bydate == 1) {// query by type family section manuby date
           result = a.queryMagnetByTypeFamilySectionManubyDate(type, family, section, manuby, datemin, datemax);
        }
        
        
        out.print(result);
        a.destroy();
        String[] header_referer = request.getHeader("Referer").split("/");
        String page = header_referer[header_referer.length - 1];
        //System.out.println(header_referer[header_referer.length-1]);
        if (page.equals("newmeas.jsp")||page.equals("newmeas.jsp#")) {
            processRequest(request, response);
        } else {
            request.getSession().setAttribute("magvalue", "{\"rows\":" + result + "}");
            request.getSession().setAttribute("magtype", type);
            request.getSession().setAttribute("magfamily", family);
            request.getSession().setAttribute("magsection", section);
            request.getSession().setAttribute("datemin", datemin);
            request.getSession().setAttribute("datemax", datemax);
            request.getSession().setAttribute("manuby", manuby);
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
