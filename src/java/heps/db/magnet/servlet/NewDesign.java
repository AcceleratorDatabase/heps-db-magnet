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
    private String type, family, require, parameter, designed_by, approved_by, remark, mplot, pplot;

    private ArrayList design, design_requirement, design_para, design_plot, design_others;
    private Integer other_flag = 0;
    private String result = "成功";
  //  private Integer del_dup_pplot,del_dup_mplot;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>磁铁设计录入</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>磁铁设计插入结果：</h1>");
//            out.println("</body>");
//            out.println("</html>");

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
        design_plot = new ArrayList();
        design_others = new ArrayList();

        DesignAPI a = new DesignAPI();

        type = request.getParameter("magtype");
        family = request.getParameter("magfamily");
        designed_by = request.getParameter("designed_by");
        approved_by = request.getParameter("approved_by");
        remark = request.getParameter("remark");
        design.add(type);
        design.add(family);
        design.add(designed_by);
        design.add(approved_by);
        design.add(remark);
        mplot = request.getParameter("mplotn");
        pplot = request.getParameter("pplotn");
        //del_dup_pplot=Integer.parseInt(request.getParameter("hd3"));
       // del_dup_mplot=Integer.parseInt(request.getParameter("hd4"));
//        if(del_dup_pplot==1){
//        System.out.println("delp");
//        }
//         if(del_dup_mplot==1){
//        System.out.println("delm");
//        }
        design_plot.add(pplot);
        design_plot.add(mplot);

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
            for (int i = 0; i < 20; i++) {
                JSONObject job = para_jsonarray.getJSONObject(i);
                design_para.add(job.get("value"));
            }
        }
        if (para_jsonarray.size() > 19) {
            for (int i = 20; i < para_jsonarray.size(); i++) {
                JSONObject job = para_jsonarray.getJSONObject(i);
                design_others.add(job.get("name"));
                design_others.add(job.getJSONObject("editor").get("type"));
                design_others.add(job.get("value"));
            }
        }
        //print all design data       
//        out.println("design= " + design);
//        out.println("design_requirement= " + design_requirement);
//        out.println("design_para= " + design_para);
//        out.println("mplot= " + mplot + "pplot" + pplot);

        if (design_para.size() >= 20) {
            other_flag = 1;
        } else {
            other_flag = 0;
        }
        //out.println("design_re= "+design_requirement); 
        //try{
        a.init();
        a.insertDesign(design, design_requirement, design_para, design_plot, other_flag, design_others);
         a.destroy();
        // }catch(UnsupportedEncodingException e){
        // result="失败！"+e;
        // }
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
        out.println("<title>磁铁设计录入</title>");
        out.println("</head>");
        out.println("<body onLoad=\"TimeClose();\"  style=\"font-size:24px;text-align: center;margin-top:60px\";>");
        out.println("<h1 >磁铁设计插入" + result+"</h1>");
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
