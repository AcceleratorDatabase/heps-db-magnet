<%-- 
    Document   : querymagnet
    Created on : 2017-12-29, 10:46:53
    Author     : qiaoys
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.3/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="modelcss.css">
        <link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.3/themes/icon.css?<%=Math.random()%>">     
        <script type="text/javascript" src="jquery-easyui-1.5.3/jquery.min.js"></script>
        <script type="text/javascript" src="jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
        <script type="text/javascript">

            window.onload = function () {
                $.ajax({
                    type: 'POST',
                    url: 'LoadType',
                    success: function (data) {
                        var b = data.split(",");
                        var x = document.getElementById("magtype");
                        for (var i = 0; i < b.length; i++) {
                            var option = document.createElement("option");
                            option.text = b[i];
                            option.value = b[i];
                            try {
                                x.add(option, x.options[null]);
                            } catch (e) {
                                x.add(option, null);
                            }
                        }
                    }
                });
                $.ajax({
                    type: 'POST',
                    url: 'LoadFamily',
                    success: function (data) {
                        var b = data.split(",");
                        var x = document.getElementById("magfamily");
                        for (var i = 0; i < b.length; i++) {
                            var option = document.createElement("option");
                            option.text = b[i];
                            option.value = b[i];
                            try {
                                x.add(option, x.options[null]);
                            } catch (e) {
                                x.add(option, null);
                            }
                        }
                    }
                });
            };
        </script>
        <style type="text/css">             
            label{
                font-size: 16px
            }
        </style>
        <title>查询-磁铁信息及磁测数据</title>
    </head>
    <body>
        <h2 style="text-align:center">查询磁铁信息及磁测数据</h2>
        <div class="easyui-panel" style="height:820px;padding:10px 60px;position: relative;" >
            <div style="position:absolute;left:0;right:0;width: 1300px;margin:0 auto;font-size:14px;">
                <form action="QueryMagnet" method="post" target="" onsubmit="return submitform();">
                    <div style="width: 1200px;height: 30px">
                        <div id="info1" style="position:absolute;width: 200px;left: 400px">
                            <label for="magtype">磁铁种类：</label> 
                            <select  id="magtype" name="magtype" style="width: 100px; height: 25px" >
                                <option value="none">未选择</option>                                
                            </select> 
                        </div>
                        <div id="info2" style="position:absolute;width: 200px;left:600px">
                            <label for="magfamily">磁铁型号：</label>
                            <select  id="magfamily" name="magfamily" style="width: 100px;height: 25px" >
                                <option value="-1">未选择</option>                                
                            </select>
                        </div>
                        <div style="margin:10px 0;"></div>
                        <div id="date" style="position:absolute;top: 50px;left: 400px" >                     
                            <label for="datemin">生产日期：</label>                                
                            <input id="datemin"  name="datemin" class="easyui-datebox"  style="width:35%;">
                            <span> - </span>                         
                            <input id="datemax"  name="datemax" class="easyui-datebox"  style="width:35%;">
                        </div> 
                    </div>
                    <div style="position:absolute;top:100px;bottom: 0; left:0;right:0;text-align: center">                    
                        <input style="width:90px; font-size: 14px" class="a-upload" type="submit" value="查询" >
                    </div> 
                </form> 
                <div style="position: absolute;left:220px;top:170px;width:900px">
                    <table class="easyui-datagrid"  title="查询结果"  data-options="singleSelect:true,toolbar:toolbar,collapsible:true">
                        <thead>
                            <tr>
                                <!--                                <th data-options="field:'magid',width:100">ID</th>-->
                                <th data-options="field:'magname',width:100">名称</th>
                                <th data-options="field:'designid',width:100">磁铁设计</th>                
                                <th data-options="field:'weight',width:100">磁铁重量[Kg]</th>
                                <th data-options="field:'series',width:100">生产序号</th>
                                <th data-options="field:'manudate',width:100">生产日期</th>                
                                <th data-options="field:'designedby',width:100">设计单位</th>
                                <th data-options="field:'manuby',width:100">制造单位</th>
                                <th data-options="field:'description',width:100">备注</th>                
                            </tr>
                        </thead>
                    </table>

                </div>  
                <div style="position:absolute;top:770px;bottom: 0; left:0;right:0;text-align: center">  
                    <input style="width:90px; font-size: 14px;background:#97CBFF ;" class="a-upload" type="button" onclick="location = 'index.html'" value="返回主页" >
                </div>
            </div>
            <script type="text/javascript">
                function submitform() {

                }
                var toolbar = [{
                        text: '编辑',
                        iconCls: 'icon-edit',
                        handler: function () {
                            alert('未查询');
                        }
                    }, {
                        text: '删除',
                        iconCls: 'icon-clear',
                        handler: function () {
                            alert('未查询');
                        }
                    }];
            </script>
    </body>
</html>
