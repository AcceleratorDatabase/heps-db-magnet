<%-- 
    Document   : measresult
    Created on : 2018-3-1, 15:43:20
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
        <script type="text/javascript">
            <%
                String filetype = (String) session.getAttribute("filetype");
               
            %>
            var ff = "<%=filetype%>";
          
            window.onload = function () {
                switch (ff) {
                    case "sws":                       
                       
                         $("#div-rcs").hide();
                        $("#div-hall").hide();

                        break;
                    case "rcs":
                         $("#div-sws").hide();
                        $("#div-hall").hide();
                        break;
                    case "hall":
                         $("#div-sws").hide();
                        $("#div-rcs").hide();
                        break;
                }
            };
        </script>
        <title>磁测数据</title>
    </head>
    <body>
        <h2 >磁测数据</h2>
        <div class="easyui-panel" style="width: 1200px;height:820px;padding:10px 60px;">
            <div id="div-sws" style="width: 900px; ">
                sws
                <table id="dg1" class="easyui-datagrid" title="查询结果"  data-options="singleSelect:true, 
                           rownumbers: true,
                           dataType:'json', 
                            toolbar:toolbar,
                          url:'MeasResult',
                           method: 'get'
                           ">  
                    <thead>
                            <tr>
                                <th data-options="field:'runid',width:30">ID</th>
                                <th data-options="field:'samplingRate',width:50">名称</th>
                                <th data-options="field:'speed',width:50">磁铁设计</th>                
                                <th data-options="field:'acceleratedSpeed',width:50">磁铁重量[Kg]</th>
                                <th data-options="field:'distance',width:50">生产序号</th>
                                <th data-options="field:'startEY0',width:50">生产日期</th>                
                                <th data-options="field:'startEY0',width:50">设计单位</th>
                                <th data-options="field:'strain',width:50">制造单位</th>
                                <th data-options="field:'measCurrent',width:98">备注</th> 
                                <th data-options="field:'cutOffFrequency',width:50">生产日期1</th>                
                                <th data-options="field:'measDate',width:50">设计单位1</th>
                                <th data-options="field:'measBy',width:50">制造单位1</th>
                                <th data-options="field:'measAt',width:98">备注1</th> 
                                 <th data-options="field:'description',width:98">备注2</th> 
                            </tr>
                        </thead>
                </table>
                
            </div>
            <div id="div-rcs"  style="">
                rcs
                <table id="dg" class="easyui-datagrid">
                    
                </table>
            </div>
            <div id="div-hall" style=" ">
                hall
                <table id="dg" class="easyui-datagrid">
                    
                </table>
            </div>
        </div>
        <script type="text/javascript">
             var toolbar = [{
                    text: '下载机械设计图纸',
                    iconCls: 'icon-download',
                    handler: function () {
                        var row = $('#dg').datagrid('getSelected');
                        if (row) {
                            location.href = 'DownloadFile?designId=' + row.designid + '&filetype=m';
                        } else {
                            alert("请选择一条记录");
                        }
                    }
                }, {
                    text: '下载物理设计图纸',
                    iconCls: 'icon-download',
                    handler: function () {
                        var row = $('#dg').datagrid('getSelected');
                        if (row) {
                            location.href = 'DownloadFile?designId=' + row.designid + '&filetype=p';
                        } else {
                            alert("请选择一条记录");
                        }
                    }
                }];
        </script>
    </body>
</html>
