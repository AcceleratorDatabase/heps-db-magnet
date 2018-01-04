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
        <link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.3/themes/icon.css?<%=Math.random()%>">     
        <script type="text/javascript" src="jquery-easyui-1.5.3/jquery.min.js"></script>
        <script type="text/javascript" src="jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
        <script type="text/javascript">
             <%
                String magtype = (String) session.getAttribute("magtype");
                Integer magfamily = (Integer) session.getAttribute("magfamily");   
String datemin = (String) session.getAttribute("datemin");
String datemax = (String) session.getAttribute("datemax");
            %>
            var tt = "<%=magtype%>";
            var ff = <%=magfamily%>;
             var ddmin = "<%=datemin%>";
              var ddmax = "<%=datemax%>";
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
                          document.getElementById("magtype").value =tt;
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
                $('#datemin').datebox('setValue', ddmin);
                $('#datemax').datebox('setValue', ddmax);
            };
        </script>
        <title>查询-磁铁信息</title>
    </head>
    <body>
        <h2 >查询磁铁信息</h2>
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
                            <input id="datemax" name="datemax" class="easyui-datebox"  style="width:35%;">
                        </div> 
                    </div>
                    <div style="position:absolute;top:100px;bottom: 0; left:0;right:0;text-align: center">                    
                        <input style="width:90px; font-size: 14px" class="a-upload" type="submit" value="查询" >
                    </div> 
                </form> 
                <div style="position: absolute;left:220px;top:170px;width:900px">
                    <table id="dg_magnet" class="easyui-datagrid"  title="查询结果"  data-options="singleSelect:true, 
                           rownumbers: true,
                           dataType:'json',                           
                           toolbar:toolbar,                          
                           url: 'MagnetResult',
                           method: 'get',
                           collapsible:true">
                        <thead>
                            <tr>
                                <th data-options="field:'magid',width:70">ID</th>
                                <th data-options="field:'magname',width:100">名称</th>
                                <th data-options="field:'designid',width:100,formatter:formatDesign">磁铁设计</th>                
                                <th data-options="field:'weight',width:100">磁铁重量[Kg]</th>
                                <th data-options="field:'series',width:100">生产序号</th>
                                <th data-options="field:'manudate',width:100">生产日期</th>                
                                <th data-options="field:'designedby',width:100">设计单位</th>
                                <th data-options="field:'manuby',width:100">制造单位</th>
                                <th data-options="field:'description',width:98">备注</th>                
                            </tr>
                        </thead>
                    </table>
                    <input type="hidden" id="hd" name="hd"/>
                </div>  
              <div id="dlg1" class="easyui-dialog" title="磁铁设计"  style="width:1200px;height:200px;padding:10px;text-align: center" data-options="iconCls:'icon-more',closed: true,resizable:true">
                <table id="dg" name="dg" class="easyui-datagrid" align="center"
                       data-options="
                       toolbar:toolbarr                         
                       ">
                    <thead data-options="frozen:true">
                        <tr>
                            <th data-options="field:'designid',width:80,sortable:true">ID</th>
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
                            <th data-options="field:'length',width:70,formatter:formatPrice">有效长度</th>
                            <th data-options="field:'aperture',width:70,formatter:formatPrice">磁铁孔径</th>
                            <th data-options="field:'min_gap',width:120,formatter:formatPrice">相邻磁极最小间隙</th>
                            <th data-options="field:'useful_field',width:80,formatter:formatPrice">好场区范围</th>
                            <th data-options="field:'intensityB',width:70,formatter:formatPrice">二极分量</th>
                            <th data-options="field:'intensityQ',width:70,formatter:formatPrice">四极分量</th>
                            <th data-options="field:'intensityS',width:70,formatter:formatPrice">六极分量</th>
                            <th data-options="field:'intensityO',width:70,formatter:formatPrice">八极分量</th>
                            <th data-options="field:'sys',width:70,formatter:formatPrice">系统分量</th>
                            <th data-options="field:'non_sys',width:80,formatter:formatPrice">非系统分量</th>

                            <th data-options="field:'offset',width:100,formatter:formatPrice">偏置安装偏移量</th>
                            <th data-options="field:'ampere_turns',width:80,formatter:formatPrice">励磁安匝数</th>
                            <th data-options="field:'ampere_turns_each',width:100,formatter:formatPrice">每磁极线圈匝数</th>
                            <th data-options="field:'current',width:70,formatter:formatPrice">励磁电流</th>
                            <th data-options="field:'wire',width:70,formatter:formatPrice">导线规格</th>
                            <th data-options="field:'current_density',width:70,formatter:formatPrice">电流密度</th>
                            <th data-options="field:'wire_length',width:100,formatter:formatPrice">磁铁导线总长度</th>
                            <th data-options="field:'resistence',width:80,formatter:formatPrice">磁铁总电阻</th>
                            <th data-options="field:'inductance',width:70,formatter:formatPrice">磁铁电感</th>
                            <th data-options="field:'voltage',width:70,formatter:formatPrice">励磁电压</th>
                            <th data-options="field:'consumption',width:70,formatter:formatPrice">磁铁功耗</th>

                            <th data-options="field:'c_pressure_drop',width:80,formatter:formatPrice">冷却水压降</th>
                            <th data-options="field:'c_channel_num',width:80,formatter:formatPrice">并联水路数</th>
                            <th data-options="field:'c_velocity',width:80,formatter:formatPrice">冷却水流速</th>
                            <th data-options="field:'c_flow',width:80,formatter:formatPrice">冷却水流量</th>
                            <th data-options="field:'c_temp',width:60,formatter:formatPrice">水温升</th>

                            <th data-options="field:'core_length',width:70,formatter:formatPrice">铁芯长度</th>
                            <th data-options="field:'core_section',width:80,formatter:formatPrice">铁芯截面尺寸</th>
                            <th data-options="field:'core_weight',width:60,formatter:formatPrice">铁芯重</th>
                            <th data-options="field:'copper_weight',width:60,formatter:formatPrice">铜重</th>
                            <th data-options="field:'designedby',width:70">设计人</th>
                            <th data-options="field:'approvedby',width:70">负责人</th>
                            <th data-options="field:'remark',width:90">备注</th>
                        </tr>
                    </thead>
                </table>                
            </div>
                <div style="position:absolute;top:780px;bottom: 0; left:0;right:0;text-align: center">  
                    <a  href="index.html" class="easyui-linkbutton" data-options="">返回主页</a>
                </div>
            </div>
        </div>
            <script type="text/javascript">
                function submitform() {

                }
                function formatPrice(val, row) {
                if (val === 'null') {
                    return '';
                } else {
                    return val;
                }
            }
                function formatDesign(val, row) {
                return val+"<a href=\"#\" style=\"display:block;float:right\" onclick=\"checkDesign("+val+")\">*点击查看*</a>";
                }
               
                function checkDesign(designid){
                   
                     $.ajax({
                    type: 'POST',
                    url: 'CheckDesign',
                    scriptCharset: 'UTF-8',
                    data: "designid=" + designid,
                    success: function (data) {
                        //alert(data);
                        var str = '{"rows":' + data + '}';
                        var s = $.parseJSON(str);
                        $('#dg').datagrid('loadData', s);
                        $('#dlg1').dialog('open');
//                        $('#maginfo').datagrid('updateRow', {
//                            index: 6,
//                            row: {
//                                value: data
//                            }
//                        });
                    }
                });
                }
                var toolbar = [{
                        text: '编辑',
                        iconCls: 'icon-edit',
                        handler: function () {
                            var row = $('#dg_magnet').datagrid('getSelected');
                        var index = $('#dg_magnet').datagrid('getRowIndex', row);
                        //alert(index);
                        var seldata = $('#dg_magnet').datagrid('getData').rows[index];
                        document.getElementById("hd").value = JSON.stringify(seldata);                        
                        if (row) {
                            $.ajax({
                                type: 'POST',
                                url: 'EditMagnet',
                                data:  "magid=" + row.magid +"&magname=" + row.magname + "&selData=" + document.getElementById("hd").value,
                                success: function (data) {
                                    window.location.href = 'editmagnet.jsp';
                                }
                            });
                            //alert('Item ID:' + row.designid + "Price:" + row.length);
                            //location.href = 'EditDesign?magType='+row.magtype+'&magFamily='+row.magfamily+'&selData='+document.getElementById("hd").value;
                        } else {
                            alert("请选择一条记录");
                        } 
                        }
                    }, {
                        text: '删除',
                        iconCls: 'icon-clear',
                        handler: function () {
                            var row = $('#dg_magnet').datagrid('getSelected');
                        if (row) {
                            var yn = window.confirm("确认删除此磁铁信息？");
                            if (yn) {
                                $.ajax({
                                    type: 'POST',
                                    url: 'DeleteMagnet',
                                    data: "magid=" + row.magid,
                                    success: function (data) {
                                        alert(data);
                                        window.history.go(0);
                                    }
                                });
                            } else
                                return false;
                        } else {
                            alert("请选择一条记录");
                        }
                        }
                    }];
                var toolbarr = [{
                    text: '下载设计图纸',
                    iconCls: 'icon-download',
                    handler: function () {
                        var row = $('#dg').datagrid('getSelected');
                        if (row) {
                            location.href = 'DownloadFile?designId=' + row.designid;
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
                                    $('#dlg2').dialog('open');
                                    //$('#dlg').dialog('refresh', 'editdesign.jsp');                                   
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
