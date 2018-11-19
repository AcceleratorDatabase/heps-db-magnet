/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.magnet.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import heps.db.magnet.jpa.DesignAPI;

/**
 *
 * @author qiaoys
 */
public class QueryDesign extends HttpServlet {

    public Integer precalcInt(Object obj) {
        if (obj.toString().isEmpty()) {
            return null;
        } else {
            return Integer.parseInt(obj.toString());
        }
    }

    public Double precalcDouble(Object obj) {
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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet QueryDesign</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet QueryDesign at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
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
        request.setCharacterEncoding("UTF-8");        
        String result = new String();
        String type;
        String family;
        String project;
        String designby;
        Integer bytype;
        Integer byfamily;
        Integer byproject;
        Integer bydesignby;

        DesignAPI a = new DesignAPI();
        a.init();
        type = request.getParameter("magtype");
        family = request.getParameter("magfamily");
        project = request.getParameter("magproject");
        designby = request.getParameter("magdesignby");
       

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
        if (project.equals("none")) {
            byproject = 0;
        } else {
            byproject = 1;
        }
        if (designby.equals("")) {
            bydesignby = 0;
        } else {
            bydesignby = 1;
        }
        //System.out.println(bytype+"~"+byfamily+"~"+byproject+"~"+bydesignby);
        if (bytype == 0 && byfamily == 0 && byproject == 0 && bydesignby == 0) {//no query conditions
            result = a.queryDesignAll();
        } else if (bytype == 1 && byfamily == 0 && byproject == 0 && bydesignby == 0 ) {//query by type             
            result = a.queryDesignByType(type);            
        } else if (bytype == 0 && byfamily == 1 && byproject == 0 && bydesignby == 0) {//query by family
            result = a.queryDesignByFamily(family);
        } else if (bytype == 0 && byfamily == 0 && byproject == 1 && bydesignby == 0) {//query by project
            result = a.queryDesignByProject(project);
        } else if (bytype == 0 && byfamily == 0 && byproject == 0 && bydesignby == 1) {//query by designby
            result = a.queryDesignByDesignby(designby);
        } else if (bytype == 1 && byfamily == 1 && byproject == 0 && bydesignby == 0) {//query by type & family
            result = a.queryDesignByTypeFamily(type, family);
        } else if (bytype == 1 && byfamily == 0 && byproject == 1 && bydesignby == 0) {//query by type & project
            result = a.queryDesignByTypeProject(type, project);
        }else if (bytype == 1 && byfamily == 0 && byproject == 0 && bydesignby == 1) {//query by type & designby
            result = a.queryDesignByTypeDesignby(type, designby);
        } else if (bytype == 0 && byfamily == 1 && byproject == 1 && bydesignby == 0) {//query by family & project
            result = a.queryDesignByFamilyProject(family, project);
        }else if (bytype == 0 && byfamily == 1 && byproject == 0 && bydesignby == 1) {//query by family & designby
            result = a.queryDesignByFamilyDesignby(family, designby);
        }else if (bytype == 0 && byfamily == 0 && byproject == 1 && bydesignby == 1) {//query by project & designby
            result = a.queryDesignByProjectDesignby(project, designby);
        }else if (bytype == 1 && byfamily == 1 && byproject == 1 && bydesignby == 0) {//query by type & family & project
            result = a.queryDesignByTypeFamilyProject(type, family, project);
        }else if (bytype == 1 && byfamily == 1 && byproject == 0 && bydesignby == 1) {//query by type & family & designby
            result = a.queryDesignByTypeFamilyDesignby(type, family, designby);
        }else if (bytype == 1 && byfamily == 0 && byproject == 1 && bydesignby == 1) {//query by type & project & designby
            result = a.queryDesignByTypeProjectDesignby(type, project, designby);
        }else if (bytype == 0 && byfamily == 1 && byproject == 1 && bydesignby == 1) {//query by family & project & designby
            result = a.queryDesignByFamilyProjectDesignby(family, project, designby);
        }else if (bytype == 1 && byfamily == 1 && byproject == 1 && bydesignby == 1) {//query by type & family & project & designby 
            result = a.queryDesignByTypeFamilyProjectDesignby(type, family, project, designby);
        }

        a.destroy();
        request.getSession().setAttribute("designvalue", "{\"rows\":" + result + "}");
        request.getSession().setAttribute("magtype", type);
        request.getSession().setAttribute("magfamily", family);
        request.getSession().setAttribute("magproject", project);
        request.getSession().setAttribute("magdesignby", designby);
        request.getRequestDispatcher("designresult.jsp").forward(request, response);
        // processRequest(request, response);
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
