/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.magnet.servlet;
 
import java.io.File;  
import java.io.IOException;  
import java.util.ArrayList;  
import java.util.List;  
  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;    
import org.apache.commons.fileupload.FileItem;  
import org.apache.commons.fileupload.FileItemFactory;  
import org.apache.commons.fileupload.disk.DiskFileItemFactory;  
import org.apache.commons.fileupload.servlet.ServletFileUpload;  
  
/** 
 * Servlet implementation class UploadFile 
 */  
public class UploadFile extends HttpServlet {  
    private static final long serialVersionUID = 1L;  

    /** 
     * Default constructor. 
     */  
    public UploadFile() {  
        // TODO Auto-generated constructor stub  
    }  
  
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        // TODO Auto-generated method stub  
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        //String realPath = "E:/plot";  
        String realPath = this.getServletContext().getRealPath("/WEB-INF");
        String plottype=request.getParameter("plottype");
        String dir="";      
        if(plottype.equals("0")){
           dir="uploadfile/pplot";
        }else if(plottype.equals("1")){
          dir="uploadfile/mplot";
        }
        File uploadPath = new File(realPath, dir);  
//        System.out.println(uploadPath.getPath());
//        System.out.println(uploadPath.getAbsolutePath());
        uploadPath.mkdirs();  
        // 解决Servelt to js乱码问题  
        response.setContentType("text/html;charset=UTF-8");  
        // 判断是否是带有附件的上传  
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
        if (isMultipart) {  
            FileItemFactory factory = new DiskFileItemFactory();  
            ServletFileUpload upload = new ServletFileUpload(factory);  
            ArrayList<String> filename_new,filename_old;  
             String name;
            filename_new = new ArrayList<>();
            filename_old = new ArrayList<>();
            try {  
                @SuppressWarnings("unchecked")  
                List<FileItem> items = upload.parseRequest(request);  
                for (FileItem f : items) {  
                    if (f.isFormField()) {// 说明是表单项  
                    } else {// 说明是文件  
                        String srcName = f.getName();// 取得上传时的文件名  
                        srcName = srcName.toLowerCase();// 统一将文件名改为小写  
                        String extName = "";// 扩展名  
                        int pos = srcName.lastIndexOf('.');  
                        // 因为有的文件上传它可能没有扩展名，所以这里注意要进行一步判断，判断后再取得.扩展名  
                        if (pos != -1) {  
                            extName = srcName.substring(pos);  
                        }  
                        // 取得当前时间的纳秒数加扩展名来做保存文件的文件名  
                        name = srcName.substring(0, pos)+"-"+System.nanoTime()+extName;  
                        File file = new File(uploadPath, name);  
                        f.write(file);// 保存到指定的目录中去  
                        filename_new.add(name);  
                       filename_old.add(srcName); 
  //System.out.println(pics.get(0));
                    }  
                }  
            } catch (Exception e) {  
                if(plottype.equals("0")){
                response.getWriter().write("<script>parent.callback1(false,'上传失败','')</script>");
               
                }else if(plottype.equals("1"))
                {
                response.getWriter().write("<script>parent.callback2(false,'上传失败','')</script>");
                }
            }  
            if(plottype.equals("0")){    
                 response.getWriter().write("<script>parent.callback1(true,'文件:" + filename_old.get(0) + "上传成功!','" + filename_new.get(0) + "')</script>");
                 
                }else if(plottype.equals("1"))
                {
                response.getWriter().write("<script>parent.callback2(true,'文件:" + filename_old.get(0) + "上传成功!','" + filename_new.get(0) + "')</script>");
                }
           // response.getWriter().write("<script>parent.callback(true,'上传成功','" + pics.get(0) + "')</script>");  
            // 一般如果是直接表单提交的方法需要返回  
           //  request.getRequestDispatcher("newdesign.jsp").forward(request,response);  
  
        }  
    }  
  
}  