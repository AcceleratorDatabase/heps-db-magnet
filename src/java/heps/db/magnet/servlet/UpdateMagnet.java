/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.magnet.servlet;

import heps.db.magnet.jpa.DeviceAPI;
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
public class UpdateMagnet extends HttpServlet {

    
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
//            out.println("<title>Servlet UpdateMagnet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet UpdateMagnet at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
    String family, type, eqsection, info;

     ArrayList maginfo;
        maginfo = new ArrayList();
        type=(String)request.getParameter("magtype");
        family=(String)request.getParameter("magfamily");
        eqsection=(String)request.getParameter("magsection");
        Integer Id=Integer.parseInt(request.getParameter("hd2"));
        info=(String)request.getParameter("hd1");  
 
        
        JSONObject info_jsonobj = JSONObject.fromObject(info);
        JSONArray info_jsonarray = info_jsonobj.getJSONArray("rows");
        if (info_jsonarray.size() > 0) {
            for (int i = 0; i < info_jsonarray.size(); i++) {
                JSONObject job = info_jsonarray.getJSONObject(i);  // 遍历 
                if(i==5){
                  maginfo.add(job.get("value").toString().split("<")[0]);
                }else
                maginfo.add(job.get("value"));
            }
        }
        DeviceAPI a = new DeviceAPI();
        a.init();
        a.updateDevice(maginfo, Id,eqsection);
        //a.insertDevice(maginfo,type,family);        
        a.destroy();
        out.println("<!DOCTYPE html>");
        out.println("<meta http-equiv=\"refresh\" content=\"3;url=index.html\">");
        out.println("<html>");
        out.println("<script language=\"javascript\"> ");
        out.println("var times=3;");
        out.println("function TimeClose()");
        out.println("{ window.setTimeout('TimeClose()', 1000); ");
        out.println("time.innerHTML =times+\"秒后跳转到首页\";");    
        out.println("times--;}");       
        out.println("</script>");
        out.println("<head>");
        out.println("<title>磁铁信息录入</title>");
        out.println("</head>");
        out.println("<body onLoad=\"TimeClose();\"  style=\"font-size:24px;text-align: center;margin-top:60px\";>");
        out.println("<h1 >磁铁设备信息插入成功</h1>");
        out.println("<div id=\"time\"></div> ");
        out.println("</body>");
        out.println("</html>");
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
