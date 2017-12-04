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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author qiaoys
 */
public class EditDesign extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     private ArrayList sellist, design_para;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet EditDesign</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet EditDesign at " + request.getContextPath() + "</h1>");
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
//         sellist= new ArrayList();
         PrintWriter out = response.getWriter();
         String magtype=request.getParameter("magType");
         Integer magfamily=Integer.parseInt(request.getParameter("magFamily"));
         String seldata=request.getParameter("selData");
         //seldata=seldata.substring(9,seldata.length()-15);
         //String jsonMessage = "{\"语文\":\"88\",\"数学\":\"78\",\"计算机\":\"99\"}";
          JSONObject seljson = JSONObject.fromObject(seldata); 
          //System.out.println("json:"+seljson.getString("length"));
//        JSONObject jsonobj = JSONObject.fromObject(seldata);
//       JSONArray require_jsonarray = require_jsonobj.getJSONArray("rows");       
//       if (seljson.size() > 0) {
//            for (int i = 0; i < seljson.size(); i++) {               
//                sellist.add(seljson.get("value"));
//           }
//        }
        
        // System.out.println("servelt:"+seljson);
         request.getSession().setAttribute("magtype", magtype);
         request.getSession().setAttribute("magfamily", magfamily);
         request.getSession().setAttribute("seldata",seljson.toString());
        
        request.getRequestDispatcher("editdesign.jsp").forward(request, response);
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
