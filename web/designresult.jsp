<%-- 
    Document   : querydesignjsp
    Created on : 2017-10-24, 15:40:32
    Author     : qiaoys
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.3/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="modelcss.css">
        <link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.3/themes/icon.css?param=Math.ramdom()">     
        <script type="text/javascript" src="jquery-easyui-1.5.3/jquery.min.js"></script>
        <script type="text/javascript" src="jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
        <script>
            <%
                String magtype = (String) session.getAttribute("magtype");
                Integer magfamily = (Integer) session.getAttribute("magfamily");
                Double lengthmin = (Double) session.getAttribute("lengthmin");
                Double lengthmax = (Double) session.getAttribute("lengthmax");
                Integer intensity = (Integer) session.getAttribute("intensity");
                Double intensitymin = (Double) session.getAttribute("intensitymin");
                Double intensitymax = (Double) session.getAttribute("intensitymax");
            %>
            var tt = "<%=magtype%>";
            var ff = <%=magfamily%>;

            var llmin =<%=lengthmin%>;
            if (llmin === null) {
                var llmin = "";
            }
            var llmax =<%=lengthmax%>;
            if (llmax === null) {
                var llmax = "";
            }
            var ii = "<%=intensity%>";
            var iimin =<%=intensitymin%>;
            if (iimin === null) {
                var iimin = "";
            }
            var iimax =<%=intensitymax%>;
            if (iimax === null) {
                var iimax = "";
            }
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
                        document.getElementById("magtype").value = tt;
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
                        document.getElementById("magfamily").value = ff;
                    }
                });
                document.getElementById("lengthmin").value = llmin;
                document.getElementById("lengthmax").value = llmax;
                document.getElementById("selintensity").value = ii;
                document.getElementById("intensitymin").value = iimin;
                document.getElementById("intensitymax").value = iimax;
            };
        </script>
        <style type="text/css">
           label{
                font-size: 16px;               
            }
        </style>
        <title>查询-磁铁设计</title>
        
    </head>
    <body>
        <h2 style="text-align:center">查询磁铁设计</h2>  
        <div class="easyui-panel" style="height:820px;padding:10px 60px;position: relative;" >
            <div style="position:absolute;left:0;right:0;width: 1300px;margin:0 auto;">
                <form action="QueryDesign" method="post" onsubmit="return submitform();" >
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
                        <div id="length" style="position:absolute;top: 50px;left: 400px" >                     
                            <label for="lengthmin">有效长度范围[mm]：</label>                                  
                            <input id="lengthmin" name="lengthmin"type="text"  size= 8 autocomplete="off" value="">
                            <span> - </span>                         
                            <input id="lengthmax" name="lengthmax"type="text" size= 8 autocomplete="off" value="">
                        </div>                  

                        <div id="intensity" style="position:absolute;top: 90px;left: 400px" >
                            <label for="selintensity">磁场强度范围[T]：</label>
                            <select  id="selintensity" name="selintensity" style="width: 100px;height: 25px" >
                                <option value="-1">未选择</option>
                                <option value="1">二极分量</option>
                                <option value="2">四极分量</option>
                                <option value="3">六极分量</option>
                                <option value="4">八极分量</option>
                            </select>                         
                            <input id="intensitymin" name="intensitymin" type="text" size=8 autocomplete="off" value="">
                            <span> - </span>
                            <input id="intensitymax" name="intensitymax" type="text" size= 8 autocomplete="off" value="">                        
                        </div>
                    </div>
                    <div style="position:absolute;top:130px;bottom: 0; left:0;right:0;text-align: center">                    
                        <input style="width:90px; font-size: 14px" class="a-upload" type="submit" value="查询" >                      
                    </div>                   
                </form>     

                <div style="position: absolute;top: 170px;width: 1200px">
                    <table id="dg" name="dg" height=550 class="easyui-datagrid" title="查询结果" align="center"
                           data-options="
                           singleSelect: true,
                           collapsible: true,                           
                           rownumbers: true,
                           dataType:'json',
                           remoteSort:true,
                           toolbar:toolbar,
                           url: 'DesignResult?<%=Math.random()%>',
                           method: 'get',                           
                           onLoadSuccess: function(){    
                           }
                           ">
                        <thead data-options="frozen:true">
                            <tr>
                                <!--                                <th data-options="field:'designid',width:80,sortable:true">ID</th>-->
                                <th data-options="field:'magtype',width:80">磁铁类型</th>
                                <th data-options="field:'magfamily',width:80">磁铁型号</th>
                            </tr>
                        </thead>
                        <thead>
                            <tr> 
                                <th colspan="10"><span>设计要求</span></th>
                                <th colspan="11"><span>主要参数</span></th>
                                <th colspan="5"><span>水冷参数</span></th>
                                <th colspan="4"><span>尺寸及重量</span></th>
                                <th colspan="3"><span>其他</span></th>
                            </tr>
                            <tr>                           
                                <th data-options="field:'length',width:90,formatter:formatPrice">有效长度[mm]</th>
                                <th data-options="field:'aperture',width:90,formatter:formatPrice">磁铁孔径[mm]</th>
                                <th data-options="field:'min_gap',width:140,formatter:formatPrice">相邻磁极最小间隙[mm]</th>
                                <th data-options="field:'useful_field',width:100,formatter:formatPrice">好场区范围[mm]</th>
                                <th data-options="field:'intensityB',width:90,formatter:formatPrice">二极分量[T]</th>
                                <th data-options="field:'intensityQ',width:90,formatter:formatPrice">四极分量[T/m]</th>
                                <th data-options="field:'intensityS',width:100,formatter:formatPrice">六极分量[T/m2]</th>
                                <th data-options="field:'intensityO',width:100,formatter:formatPrice">八极分量[T/m3]</th>
                                <th data-options="field:'sys',width:70,formatter:formatPrice">系统分量</th>
                                <th data-options="field:'non_sys',width:70,formatter:formatPrice">非系统分量</th>

                                <th data-options="field:'offset',width:120,formatter:formatPrice">偏置安装偏移量[mm]</th>
                                <th data-options="field:'ampere_turns',width:100,formatter:formatPrice">励磁安匝数[AT]</th>
                                <th data-options="field:'ampere_turns_each',width:100,formatter:formatPrice">每磁极线圈匝数</th>
                                <th data-options="field:'current',width:90,formatter:formatPrice">励磁电流[A]</th>
                                <th data-options="field:'wire',width:90,formatter:formatPrice">导线规格[mm]</th>
                                <th data-options="field:'current_density',width:110,formatter:formatPrice">电流密度[A/mm2]</th>
                                <th data-options="field:'wire_length',width:140,formatter:formatPrice">磁铁导线总长度[T/m2]</th>
                                <th data-options="field:'resistence',width:100,formatter:formatPrice">磁铁总电阻[mΩ]</th>
                                <th data-options="field:'inductance',width:90,formatter:formatPrice">磁铁电感[mH]</th>
                                <th data-options="field:'voltage',width:90,formatter:formatPrice">励磁电压[V]</th>
                                <th data-options="field:'consumption',width:90,formatter:formatPrice">磁铁功耗[kW]</th>

                                <th data-options="field:'c_pressure_drop',width:120,formatter:formatPrice">冷却水压降[kg/cm2]</th>
                                <th data-options="field:'c_channel_num',width:80,formatter:formatPrice">并联水路数</th>
                                <th data-options="field:'c_velocity',width:100,formatter:formatPrice">冷却水流速[m/s]</th>
                                <th data-options="field:'c_flow',width:100,formatter:formatPrice">冷却水流量[l/s]</th>
                                <th data-options="field:'c_temp',width:80,formatter:formatPrice">水温升[℃]</th>

                                <th data-options="field:'core_length',width:90,formatter:formatPrice">铁芯长度[mm]</th>
                                <th data-options="field:'core_section',width:110,formatter:formatPrice">铁芯截面尺寸[mm]</th>
                                <th data-options="field:'core_weight',width:80,formatter:formatPrice">铁芯重[kg]</th>
                                <th data-options="field:'copper_weight',width:60,formatter:formatPrice">铜重[kg]</th>
                                <th data-options="field:'designedby',width:70">设计人</th>
                                <th data-options="field:'approvedby',width:70">负责人</th>
                                <th data-options="field:'remark',width:90">备注</th>
                            </tr>
                        </thead>
                    </table>
                    <input type="hidden" id="hd" name="hd"/>
                </div>
            </div>            
            <div id="dlg" class="easyui-dialog" title="用户自定义参数"  style="width:390px;height:200px;padding:10px" data-options="iconCls:'icon-more',closed: true,resizable:true">
                <table id="dg_other" class="easyui-datagrid" align="center" data-options="">
                    <thead data-options="singleSelect: true,fitColumns:true">
                        <tr>
                            <th data-options="field:'property',width:120,formatter:formatPrice">参数名</th>
                            <th data-options="field:'valueNum',width:120,formatter:formatPrice">值(Num)</th>
                            <th data-options="field:'valueText',width:120,formatter:formatPrice">值(Text)</th>
                        </tr>
                    </thead>
                </table>
            </div>
            <div id="dlg1" class="easyui-dialog" title="该设计下磁铁信息"  style="width:900px;height:200px;padding:10px" data-options="iconCls:'icon-more',closed: true,resizable:true">
                <table id="dg_magnet" class="easyui-datagrid"  title="查询结果"  data-options="singleSelect:true, 
                       rownumbers: true,
                       dataType:'json',
                       collapsible:true">
                    <thead>
                        <tr>
                            <!--                                <th data-options="field:'magid',width:70">ID</th>-->
                            <th data-options="field:'magname',width:100">名称</th>
                            <th data-options="field:'designid',width:100">磁铁设计</th>                
                            <th data-options="field:'weight',width:100">磁铁重量[Kg]</th>
                            <th data-options="field:'series',width:100">生产序号</th>
                            <th data-options="field:'manudate',width:100">生产日期</th>                
                            <th data-options="field:'designedby',width:100">设计单位</th>
                            <th data-options="field:'manuby',width:100">制造单位</th>
                            <th data-options="field:'description',width:98">备注</th>                
                        </tr>
                    </thead>
                </table>
            </div>
            <div style="position:absolute;top:780px;bottom: 0; left:0;right:0;text-align: center">  
                <input style="width:90px; font-size: 14px;background:#97CBFF ;" class="a-upload" type="button" onclick="location = 'index.html'" value="返回主页" >
            </div>
        </div>
        <script type="text/javascript">
            function submitform() {
                var selintensity = document.getElementById("selintensity");
                var intensitymin = document.getElementById("intensitymin");
                var intensitymax = document.getElementById("intensitymax");
                if (selintensity.value !== '-1') {
                    if (intensitymin.value === '' && intensitymax.value === '') {
                        alert("请输入范围");
                        return false;
                    }
                } else {
                    if (intensitymin.value !== '' || intensitymax.value !== '') {
                        alert("请选择分量");
                        return false;
                    }
                }
            }
            function formatPrice(val, row) {
                if (val === 'null') {
                    return '';
                } else {
                    return val;
                }
            }
            var toolbar = [{
                    text: '编辑',
                    iconCls: 'icon-edit',
                    handler: function () {
                        var row = $('#dg').datagrid('getSelected');
                        var index = $('#dg').datagrid('getRowIndex', row);
                        //alert(index);
                        var seldata = $('#dg').datagrid('getData').rows[index];
                        document.getElementById("hd").value = JSON.stringify(seldata);
                        if (row) {
                            var yn = window.confirm("修改现有磁铁设计会导致该设计下所有磁铁信息失效，建议新增磁铁设计。确认修改？");
                            if (yn) {
                                $.ajax({
                                    type: 'POST',
                                    url: 'EditDesign',
                                    data: "designId=" + row.designid + "&magType=" + row.magtype + "&magFamily=" + row.magfamily + "&selData=" + document.getElementById("hd").value,
                                    success: function (data) {
                                        window.location.href = 'editdesign.jsp';
                                    }
                                });
                            } else {
                                return false;
                            }


                            //alert('Item ID:' + row.designid + "Price:" + row.length);
                            //location.href = 'EditDesign?magType='+row.magtype+'&magFamily='+row.magfamily+'&selData='+document.getElementById("hd").value;
                        } else {
                            alert("请选择一条记录");
                        }
                    }
                },
//                {
//                    text: '删除',
//                    iconCls: 'icon-clear',
//                    handler: function () {
//                        var row = $('#dg').datagrid('getSelected');
//                        if (row) {
//                            var yn = window.confirm("确认删除此磁铁设计？（删除后对应的磁铁设备需重新选择磁铁设计）");
//                            if (yn) {
//                                $.ajax({
//                                    type: 'POST',
//                                    url: 'DeleteDesign',
//                                    data: "designId=" + row.designid,
//                                    success: function (data) {
//                                        alert(data);
//                                        window.history.go(0);
//                                    }
//                                });
//                            } else
//                                return false;
//                        } else {
//                            alert("请选择一条记录");
//                        }
//                    }
//                },
                {
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
                }, {
                    text: '查看用户自定义参数',
                    iconCls: 'icon-more',
                    handler: function () {
                        var row = $('#dg').datagrid('getSelected');
                        if (row) {
                            $.ajax({
                                type: 'POST',
                                url: 'UserDefineDesign',
                                scriptCharset: 'UTF-8',
                                data: "designId=" + row.designid,
                                success: function (data) {
                                    var str = '{"total":2,"rows":' + data + '}';
                                    var s = $.parseJSON(str);
                                    $('#dg_other').datagrid('loadData', s);
                                    $('#dlg').dialog('open');
                                    //$('#dlg').dialog('refresh', 'editdesign.jsp');                                   
                                }
                            });
                        } else {
                            alert("请选择一条记录");
                        }
                    }
                }, {
                    text: '查看该设计下磁铁信息',
                    iconCls: 'icon-more',
                    handler: function () {
                        var row = $('#dg').datagrid('getSelected');
                        //alert(row.designid);
                        if (row) {
                            $.ajax({
                                type: 'POST',
                                url: 'CheckMagnet',
                                scriptCharset: 'UTF-8',
                                data: "designId=" + row.designid,
                                success: function (data) {
                                    if (data === "[]") {
                                        alert("该磁铁设计暂时没有磁铁设备");
                                    } else {
                                        var str = '{"total":2,"rows":' + data + '}';
                                        var s = $.parseJSON(str);
                                        $('#dg_magnet').datagrid('loadData', s);
                                        $('#dlg1').dialog('open');}
                                }
                            });
                        } else {
                            alert("请选择一条记录");
                        }
                    }
                }];
        </script>
    </body>
</html>
