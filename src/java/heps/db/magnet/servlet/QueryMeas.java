/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.magnet.servlet;

import heps.db.magnet.jpa.MeasureAPI;
import java.io.IOException;
import java.io.PrintWriter;
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
public class QueryMeas extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet QueryMeas</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet QueryMeas at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
     
        JSONArray re;        
        Integer magId = Integer.parseInt(request.getParameter("magId"));
        String filetype = request.getParameter("filetype");
        MeasureAPI m = new MeasureAPI();
        m.init();
        //System.out.println("id:"+magId+"filetype:"+filetype);
        switch (filetype) {
            case "sws":
                result = m.querySWSBymagid(magId);
//                re = JSONArray.fromObject(result);               
//                for (int i = 0; i < re.size(); i++) {
//                   System.out.print(re.getJSONObject(i));                                    
//                }
                break;
            case "rcs":
                result=m.queryRCSBymagid(magId);
//                re = JSONArray.fromObject(result);               
//                for (int i = 0; i < re.size(); i++) {
//                   out.print(re.getJSONObject(i).toString());                                    
//               }
                break;
            case "hall":
               result=m.queryHallBymagid(magId);
//               re = JSONArray.fromObject(result);               
//                for (int i = 0; i < re.size(); i++) {
//                   out.print(re.getJSONObject(i).toString());                                    
//               }
                break;
        }
        m.destroy();
        request.getSession().setAttribute("measvalue", "{\"rows\":" + result + "}");
        //System.out.println(result);
        request.getSession().setAttribute("filetype", filetype);
        request.getRequestDispatcher("measresult.jsp").forward(request, response);
//processRequest(request, response);
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
