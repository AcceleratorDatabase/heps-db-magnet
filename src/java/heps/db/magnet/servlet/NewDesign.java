/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.magnet.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import heps.db.magnet.jpa.DesignAPI;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author qiaoys
 */
public class NewDesign extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String type, family, require, parameter, designed_by, approved_by, remark,mplot,pplot;
    private ArrayList design, design_requirement, design_para;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewDesign</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewDesign at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");

        }
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
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        design = new ArrayList();
        design_requirement = new ArrayList();
        design_para = new ArrayList();
        DesignAPI a = new DesignAPI();

        type = request.getParameter("magtype");
        family = request.getParameter("magfamily");
        designed_by = request.getParameter("designed_by");
        approved_by = request.getParameter("approved_by");
        mplot=request.getParameter("mplot");
        pplot=request.getParameter("pplot");
        remark = request.getParameter("remark");
        design.add(type);
        design.add(family);
        design.add(designed_by);
        design.add(approved_by);
        design.add(remark);

        require = request.getParameter("hd1");
        parameter = request.getParameter("hd2");
        
        JSONObject require_jsonobj = JSONObject.fromObject(require);
        JSONArray require_jsonarray = require_jsonobj.getJSONArray("rows");
        if (require_jsonarray.size() > 0) {
            for (int i = 0; i < require_jsonarray.size(); i++) {
                JSONObject job = require_jsonarray.getJSONObject(i);  // 遍历 
                design_requirement.add(job.get("value"));
               
            }
        }
        JSONObject para_jsonobj = JSONObject.fromObject(parameter);
        JSONArray para_jsonarray = para_jsonobj.getJSONArray("rows");
        if (para_jsonarray.size() > 0) {
            for (int i = 0; i < para_jsonarray.size(); i++) {
                JSONObject job = para_jsonarray.getJSONObject(i);
               // if (job.get("value").toString().length() == 0) {
               //     design_para.add(job.get("value"));
               // } else {
                    design_para.add(job.get("value"));
               // }
            }
        }
        //out.println(designall.get(0)+"+"+designall.get(1));
//        out.println(mplot);
        out.println("design= " + design);
        out.println("design_requirement= " + design_requirement);
        out.println("design_para= " + design_para);
        if(design_para.size()>20){
        out.println(design_para.size());
               }else
            out.println("wuxin");
            
       //a.insertDesign(design, design_requirement,design_para);

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