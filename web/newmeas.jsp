<%-- 
    Document   : newmeas
    Created on : 2018-1-22, 11:16:18
    Author     : qiaoys
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.3/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.3/themes/icon.css?<%=Math.random()%>">     
        <script type="text/javascript" src="jquery-easyui-1.5.3/jquery.min.js"></script>
        <script type="text/javascript" src="jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
        <title>录入-磁测数据</title>
    </head>
    <body>
        <h2>录入磁测数据</h2> 
        <div class="easyui-panel" style="height:820px;padding:10px 60px" > 
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="margin-right: 20px" onclick="">选择磁铁</a>
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="margin-right: 20px" onclick="">选择磁测方法</a>
            <span >请上传对应的物磁测文件（xls格式）</span>  
                <div style="margin:5px 0;"></div>
                <form  id="formId1" action="" method="post"  
                       target="hiddenFrameName1" enctype="multipart/form-data">                       
                    <div>                         
                        <input id="pplotId" type="file" class="a-upload" name="pplotName"  
                               onchange="uploadpplot()" /> 
                        <div style="display: none; color: red;" id="errorTip1">未选择文件  
                        </div>  
                        <div style="display: none; color: green;" id="successTip1"></div>  

                    </div> 
                    <!--    <img id="img" src="" width="200" height="200" style="display: none;" /> -->
                </form>  
                <iframe style="display: none" name='hiddenFrameName1' id="hidden_frame1"></iframe>  
        </div>
    </body>
</html>
