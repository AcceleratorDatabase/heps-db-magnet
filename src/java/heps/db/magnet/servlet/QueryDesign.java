/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.magnet.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
public class QueryDesign extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String type,family;
    private Double lengthmin,lengthmax;
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
//        PrintWriter out = response.getWriter();
//        DesignAPI a = new DesignAPI();
//        type=request.getParameter("magtype");//        out.println(type);
//        System.out.println("{\"rows\":"+a.queryDesignByType(type)+"}");
//        request.getSession().setAttribute("aaa", "aaa");
//        request.getSession().setAttribute("value","{\"rows\":"+a.queryDesignByType(type)+"}");
//        //out.print("{\"rows\":"+a.queryDesignByType(type)+"}");
//       //out.print("{\"rows\":[{\"designid\":\"1\",\"magtype\":\"六极铁\",\"magfamily\":\"2\",\"designedby\":\"31\",\"approvedby\":\"\",\"remark\":\"\"}, {\"designid\":\"3\",\"magtype\":\"六极铁\",\"magfamily\":\"2\",\"designedby\":\"小明\",\"approvedby\":\"\",\"remark\":\"\"}, {\"designid\":\"4\",\"magtype\":\"六极铁\",\"magfamily\":\"1\",\"designedby\":\"呜呜\",\"approvedby\":\"\",\"remark\":\"\"}]}\n" );
//      //  out.close();
//
////		String account="5"; 		
////		JSONObject json = new JSONObject();
////		JSONArray array = new JSONArray();
////		JSONObject member = null;
////			for (int i=0;i<3;i++) {
////				member = new JSONObject();
////				member.put("name", "qiao");
////				member.put("age", 23);
////				member.put("phone", "212");
////				member.put("email", "2121");
////				array.add(member);
////			}		
////		PrintWriter pw;
////		try {                   
////			pw = response.getWriter();
////                        System.out.println("{\"rows\":"+array.toString()+"}");
////			pw.print("{\"rows\":"+array.toString()+"}");
////			pw.close();
////		} catch (IOException e) {
////			e.printStackTrace();
////		}     
//        request.getRequestDispatcher("queryresult.jsp").forward(request, response);
//        //processRequest(request, response);
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
        DesignAPI a = new DesignAPI();
        type=request.getParameter("magtype");//        out.println(type);
        //System.out.println("{\"rows\":"+a.queryDesignByType(type)+"}");
       // request.getSession().setAttribute("aaa", "aaa");
        request.getSession().setAttribute("value","{\"rows\":"+a.queryDesignByType(type)+"}");
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
