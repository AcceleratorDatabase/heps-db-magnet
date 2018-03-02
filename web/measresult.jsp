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
                        $("#div-sws").show();
                        $("#dg1").datagrid("resize");
                        break;
                    case "rcs":
                        $("#div-rcs").show();
                        $("#dg2").datagrid("resize");
                        break;
                    case "hall":
                        $("#div-hall").show();
                        $("#dg3").datagrid("resize");
                        break;
                }
            };
        </script>
        <title>磁测数据</title>
    </head>
    <body>
        <h2 >磁测数据</h2>
        <div class="easyui-panel" style="height:820px;padding:10px 10px;text-align: center">
            <div id="div-sws" style="display: none;">                
                <table id="dg1" class="easyui-datagrid"   title="查询结果"  data-options="singleSelect:true,                            
                       rownumbers: true,
                       dataType:'json', 
                       toolbar:toolbar,
                       url:'MeasResult',                          
                       method: 'get'
                       ">  
                    <thead>
                        <tr>
                            <th data-options="field:'runid',width:80">测试序号</th>
                            <th data-options="field:'samplingRate',width:80">采样率[Hz]</th>
                            <th data-options="field:'speed',width:100">移动速度[m/s]</th>                
                            <th data-options="field:'acceleratedSpeed',width:100">a加速度[m/s2]</th>
                            <th data-options="field:'distance',width:100">移动距离[mm]</th>
                            <th data-options="field:'startEY0',width:100">起始点EX0[mm]</th>                
                            <th data-options="field:'startEY0',width:100">起始点EY0[mm]</th>
                            <th data-options="field:'strain',width:80">张力[kg]</th>
                            <th data-options="field:'measCurrent',width:80">I[A]</th> 
                            <th data-options="field:'cutOffFrequency',width:100">截止频率[Hz]</th>                
                            <th data-options="field:'measDate',width:80">测试时间</th>
                            <th data-options="field:'measBy',width:80">测试人</th>
                            <th data-options="field:'measAt',width:80">测试地点</th> 
                            <th data-options="field:'description',width:80">备注</th> 
                        </tr>
                    </thead>
                </table>                
            </div>
            <div id="div-rcs" style="display: none;" >
                <table id="dg2" class="easyui-datagrid" title="查询结果"  data-options="singleSelect:true, 
                       rownumbers: true,
                       dataType:'json', 
                       toolbar:toolbar,
                       url:'MeasResult',
                       method: 'get'
                       ">  
                    <thead>
                        <tr>
                            <th data-options="field:'runid',width:80">测试序号</th>
                            <th data-options="field:'polarity',width:80">极性</th>
                            <th data-options="field:'givenCurrent',width:80">给定电流A</th>                
                            <th data-options="field:'actualCurrent',width:80">实际电流[A]</th>                                
                            <th data-options="field:'gain',width:80">Gain</th>                
                            <th data-options="field:'startPosition',width:80">起始点</th>
                            <th data-options="field:'rotationRate',width:80">转速</th>
                            <th data-options="field:'rRef',width:80">Rref[mm]</th> 
                            <th data-options="field:'dx',width:80">dx[mm]</th>                
                            <th data-options="field:'dy',width:80">dy[mm]</th>
                            <th data-options="field:'dr',width:80">dr[mm]</th>
                            <th data-options="field:'measDate',width:80">测试时间</th>
                            <th data-options="field:'measBy',width:80">测试人</th>
                            <th data-options="field:'measAt',width:80">测试地点</th> 
                            <th data-options="field:'description',width:80">备注</th> 
                        </tr>
                    </thead>
                </table>
            </div>
            <div id="div-hall" style="display: none;">              
                <table id="dg3" class="easyui-datagrid" title="查询结果"  data-options="singleSelect:true, 
                       rownumbers: true,
                       dataType:'json', 
                       toolbar:toolbar,
                       url:'MeasResult',
                       method: 'get'
                       ">  
                    <thead>
                        <tr>
                            <th data-options="field:'runid',width:80">测试序号</th>
                            <th data-options="field:'measCurrent',width:100">测试电流[A]</th>
                            <th data-options="field:'waterGage',width:100">测试水压[MPa]</th> 
                            <th data-options="field:'measDate',width:80">测试时间</th>
                            <th data-options="field:'measBy',width:80">测试人</th>
                            <th data-options="field:'measAt',width:80">测试地点</th> 
                            <th data-options="field:'description',width:80">备注</th> 
                        </tr>
                    </thead>
                </table>
            </div>
        </div>
        <script type="text/javascript">
            var toolbar = [{
                    text: '查看原始数据',
                    iconCls: 'icon-database',
                    handler: function () {
                        var row = $('#dg').datagrid('getSelected');
                        if (row) {
                            location.href = 'LoadRawData?designId=' + row.runid ;
                        } else {
                            alert("请选择一条记录");
                        }
                    }
                }, {
                    text: '查看处理数据',
                    iconCls: 'icon-calculate',
                    handler: function () {
                        var row = $('#dg').datagrid('getSelected');
                        if (row) {
                            location.href = 'DownloadFile?designId=' + row.runid + '&filetype=p';
                        } else {
                            alert("请选择一条记录");
                        }
                    }
                }];
        </script>
    </body>
</html>
