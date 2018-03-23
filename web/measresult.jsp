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
                        $("#sws").datagrid("resize");
                        break;
                    case "rcs":
                        $("#div-rcs").show();
                        $("#rcs").datagrid("resize");
                        break;
                    case "hall":
                        $("#div-hall").show();
                        $("#hall").datagrid("resize");
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
                <table id="sws" class="easyui-datagrid" height=650 title="查询结果"  data-options="singleSelect:true,  
                       rownumbers: true,
                       dataType:'json', 
                       toolbar:toolbar,
                       pagination: true,
                       url:'MeasResult?<%=Math.random()%>',                          
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
                <table id="rcs" class="easyui-datagrid" height=650 title="查询结果"  data-options="singleSelect:true, 
                       rownumbers: true,
                       dataType:'json', 
                       toolbar:toolbar,
                       url:'MeasResult?<%=Math.random()%>',
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
                <table id="hall" class="easyui-datagrid" height=650 title="查询结果"  data-options="singleSelect:true, 
                       rownumbers: true,
                       dataType:'json', 
                       toolbar:toolbar,
                       url:'MeasResult?<%=Math.random()%>',
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
            <div id="rcsdlg" class="easyui-dialog" title="处理数据"  style="width:450px;height:700px;padding:10px;" data-options="iconCls:'icon-calculate',closed: true,resizable:true">       
                <table id="rcsdata" class="easyui-datagrid"  data-options="singleSelect:true,rownumbers: true">  
                    <thead>
                        <tr>                            
                            <th data-options="field:'phi',width:80">phi</th>
                            <th data-options="field:'angle',width:80">angle</th> 
                            <th data-options="field:'bnb2',width:80">bnb2</th>
                            <th data-options="field:'bn',width:80">bn</th>
                            <th data-options="field:'an',width:80">an</th>                             
                        </tr>
                    </thead>
                </table>
            </div>
<div id="swsdlg" class="easyui-dialog" title="处理数据"  style="width:450px;height:700px;padding:10px" data-options="iconCls:'icon-calculate',closed: true,resizable:true">  
    
</div>
<div id="halldlg" class="easyui-dialog" title="处理数据"  style="width:450px;height:700px;padding:10px" data-options="iconCls:'icon-calculate',closed: true,resizable:true">  
</div>
            <div style="position:absolute;top:850px;bottom: 0; left:0;right:0;text-align: center">  
                <a  href="index.html" class="easyui-linkbutton" data-options="">返回主页</a>
            </div>
        </div>
        <script type="text/javascript">

            var toolbar = [
//                {
//                    text: '下载原始数据',
//                    iconCls: 'icon-database',
//                    handler: function () {
//                        var dg = document.getElementById(ff); //DOM Object 
//                        var $dg = $(dg);//JQuery Object                        
//                        var row = $dg.datagrid('getSelected');
//                        if (row) {
//                            location.href = 'LoadRawData?runid=' + row.runid + '&filetype=' + ff;
//                        } else {
//                            alert("请选择一条记录");
//                        }
//                    }
//                }, 
                {
                    text: '查看相关数据文件',
                    iconCls: 'icon-calculate',
                    handler: function () {
                        var dg = document.getElementById(ff); //DOM Object 
                        var $dg = $(dg);//JQuery Object    
                        var datadg_name = ff + "data";
                        var datadg = document.getElementById(datadg_name);
                        var $datadg = $(datadg);
                        var dlg_name = ff + "dlg";
                        var dlg = document.getElementById(dlg_name);
                        var $dlg = $(dlg);
                        var row = $dg.datagrid('getSelected');
                        if (row) {
                            //location.href = 'LoadData?runid=' + row.runid + '&filetype=' + ff;
                            $.ajax({
                                type: 'POST',
                                url: 'LoadData',
                                data: "runid=" + row.runid + "&filetype=" + ff,
                                success: function (data) {
                                    //alert(data);
                                    if (data === "[]" || data==="") {
                                        alert("没有相关数据");
                                    } else {
                                        if (ff === "rcs") {
                                            var datapiece=data.split("//");
                                            var files=datapiece[0].split(",");                                            
                                            var str = '{"rows":' + datapiece[1] + '}';
                                            var s = $.parseJSON(str);
                                           // alert(s);
                                            $dlg.dialog('open');
                                             $datadg.datagrid('loadData', s);
                                            dlg.innerHTML+="数据列表 点击下载（最后一个是原始数据）</br>";
                                            for(file in files){                                               
                                              dlg.innerHTML+="<a id="+files[file]+" href=\"#\" onclick=DownlodMeasFiles(this)>"+files[file]+"</a></br>";
                                           }
                                                                                                                                   
                                        } else {
                                           $dlg.dialog('open');
                                           dlg.innerHTML="数据列表 点击下载（最后一个是原始数据）</br>";
                                            var files=data.split(",");
                                           for(file in files){                                               
                                              dlg.innerHTML+="<a id="+files[file]+" href=\"#\" onclick=DownlodMeasFiles(this)>"+files[file]+"</a></br>";
                                           }
                                        }
                                    }
                                }
                            });
                        } else {
                            alert("请选择一条记录");
                        }
                    }
                }];
            function DownlodMeasFiles(obj){
                //alert(document.getElementById(obj.id).innerText);
                 location.href ="DownloadMeasFiles?filename="+document.getElementById(obj.id).innerText+"&filetype="+ff;
            }
        </script>
    </body>
</html>
