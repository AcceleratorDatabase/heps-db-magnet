<%-- 
    Document   : newmagnet
    Created on : 2017-12-15, 14:57:27
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
        <script type="text/javascript" src="magnet.js?<%=Math.random()%>"></script>
        <script type="text/javascript">

            window.onload = function () {
                $.ajax({
                    type: 'POST',
                    url: 'LoadType',
                    success: function (data) {
                        var b = data.split(",");
                        var x = document.getElementById("magtype");
                        for (var i = 0; i < b.length; i++) {
                            if (b[i] !== "二极铁") {
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
                    }
                });
                $.ajax({
                    type: 'POST',
                    url: 'LoadFamily',
                    success: function (data) {
                        var b = data.split(",");
                        var x = document.getElementById("magfamily");
                        for (var i = 0; i < b.length; i++) {
                            if (b[i] !== "1") {
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
                    }
                });
                $('#maginfo').propertygrid('loadData', rowm);
                // document.getElementById('num').innerText = "当前编号：";
            };

        </script>
        <style type="text/css">             
            label{
                font-size: 16px
            }
        </style>
        <title>录入-磁铁设计</title>
    </head>
    <body>
        <h2 style="text-align:center">录入磁铁设备信息</h2>
        <div style="background:#fafafa;padding:5px;width:200px;border:1px solid #ccc">
            <a href="#" class="easyui-menubutton" menu="#inputmenu" iconCls="icon-add">录入</a>
            <a href="#" class="easyui-menubutton" menu="#querymenu" iconCls="icon-help">查询</a>
        </div>
        <div id="inputmenu" style="width:150px;">
            <div id='newdesign'>磁铁设计</div>
            <div id='newmagnet'>磁铁设备</div>
            <div id='newmeas'>磁铁测量</div>
        </div>
        <div id="querymenu" style="width:150px;">
            <div id='querydesign'>磁铁设计</div>
            <div id='querymagnet'>磁铁设备</div>
            <div id='querymagnet'>磁铁测量</div>
        </div>
        <div class="easyui-panel" style="height: 820px;padding-top:20px" >  
            <div style="margin:0 auto;width:1000px">
                <form action="NewMagnet" method="post" target="" onsubmit="return submitform();">                    
                    <div id="info1" >
                        <label for="magtype">磁铁种类: </label> 
                        <select  id="magtype" name="magtype">
                            <option value="二极铁">二极铁</option>                            
                        </select>
                        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="margin-right: 20px" onclick="newtype()">新建种类</a>
                        <label for="magfamily">磁铁型号: </label>
                        <select  id="magfamily" name="magfamily" style="" >
                            <option value="1">1</option>                            
                        </select>
                        <a href="#"  class="easyui-linkbutton" data-options="iconCls:'icon-add'"style="margin-right: 20px" onclick="newfamily()">新建型号</a>
                        <label for="magsection">所属区域: </label>
                        <select  id="magsection" name="magsection" style="" >
                            <option value="Linac">Linac</option>  
                            <option value="Booster">Booster</option>
                            <option value="StorageRing">StorageRing</option>
                            <option value="StorageRing">LowEnergyBeamline</option>
                            <option value="StorageRing">HighEnergyBeamline</option>
                        </select>
                        <a href="#"  class="easyui-linkbutton" data-options="iconCls:'icon-add'"style="margin-right: 20px" onclick="newsection()">新建区域</a>
                        <span id="num"></span>                        
                    </div>
                    <div id="info2" style="padding-top:10px;padding-bottom: 10px">   
                        <input type="checkbox" id="batchinsert" name="batchinsert"  ><label>批量录入</label>
                        <label id="geshu" style="left: 20px;display: none" for="batchnum">    个数: </label>
                        <input id="batchnum" name="batchnum"style="display: none" value="">
                    </div> 
                    <div id="table" >
                        <table id="maginfo" name="maginfo" class="easyui-propertygrid" style=" width: 1000px;" data-options="
                               method: 'get',
                               showGroup: true,
                               scrollbarSize: 0,                                  
                               columns: mycolumns                           
                               ">
                        </table>  
                    </div>              
                    <div id="submit" style="text-align: center;padding-top: 10px">                    
                        <input style="width:90px; font-size: 14px" class="a-upload" type="submit" value="提交" >
                        <input style="width:90px; font-size: 14px ;background:#97CBFF ;" class="a-upload" type="button" onclick="location = 'index.html'" value="返回主页" >
                        <input type="hidden" id="hd1" name="hd1"/>                        
                    </div>               
                </form>
            </div>
            <div id="dlg1" class="easyui-dialog" title="磁铁设计"  style="width:1200px;height:400px;padding:10px;text-align: center" data-options="iconCls:'icon-more',closed: true,resizable:true">
                <table id="dg" name="dg" class="easyui-datagrid" title="查询结果" align="center"
                       data-options="
                       singleSelect: true,
                       collapsible: true,                           
                       rownumbers: true,
                       toolbar:toolbar                         
                       ">
                    <thead data-options="frozen:true">
                        <tr>
                            <th data-options="field:'designid',width:80,sortable:true">ID</th>
                            <th data-options="field:'magtype',width:80">磁铁类型</th>
                            <th data-options="field:'magfamily',width:80">磁铁型号</th>
                            <th data-options="field:'magproject',width:80">所属工程</th>
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
                                <th data-options="field:'sys',width:120,formatter:formatPrice">系统高阶场分量[E-4]</th>
                                <th data-options="field:'non_sys',width:130,formatter:formatPrice">非系统高阶场分量[E-4]</th>
                                <th data-options="field:'trans_even',width:140,formatter:formatPrice">积分磁场横向均匀度[E-4]</th>
                                <th data-options="field:'inte_consistency',width:180,formatter:formatPrice">磁铁间积分场一致性（或离散性）</th>

                                <th data-options="field:'offset',width:120,formatter:formatPrice">偏置安装偏移量[mm]</th>
                                <th data-options="field:'ampere_turns',width:100,formatter:formatPrice">励磁安匝数[AT]</th>
                                <th data-options="field:'ampere_turns_each',width:100,formatter:formatPrice">每磁极线圈匝数</th>
                                <th data-options="field:'current',width:90,formatter:formatPrice">励磁电流[A]</th>
                                <th data-options="field:'wire',width:90,formatter:formatPrice">导线规格[mm]</th>
                                <th data-options="field:'current_density',width:110,formatter:formatPrice">电流密度[A/mm2]</th>
                                <th data-options="field:'wire_length',width:120,formatter:formatPrice">磁铁导线总长度[m]</th>
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
                                <th data-options="field:'core_section',width:150,formatter:formatPrice">铁芯截面尺寸[mm宽*高]</th>
                                <th data-options="field:'core_weight',width:80,formatter:formatPrice">铁芯重[kg]</th>
                                <th data-options="field:'copper_weight',width:60,formatter:formatPrice">铜重[kg]</th>
                                <th data-options="field:'designedby',width:70">设计人</th>
                                <th data-options="field:'approvedby',width:70">负责人</th>
                                <th data-options="field:'remark',width:90">备注</th>
                        </tr>
                    </thead>
                </table>
                <div style="margin:5px 0;"></div>
                <a href="#" class="easyui-linkbutton" onclick="setDesign()" data-options="iconCls:'icon-save'">Save</a>
            </div>
            <div id="dlg2" class="easyui-dialog" title="用户自定义参数"  style="width:390px;height:200px;padding:10px" data-options="iconCls:'icon-more',closed: true,resizable:true">

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
            <!--            <div style="position:absolute;top:850px;bottom: 0; left:0;right:0;text-align: center">  
                            <a  href="index.html" class="easyui-linkbutton" data-options="">返回主页</a>
                        </div>-->
        </div>

        <script type="text/javascript">
             $("#inputmenu").menu({               
              onClick: function (item) { 
                 window.location = item.id+'.jsp';
              } 
             });
            $("#querymenu").menu({               
              onClick: function (item) {                  
                 window.location = item.id+'.jsp';
              } 
             });
            function setDesign() {
                var row = $('#dg').datagrid('getSelected');
                var design = row.designid;

                $('#dlg1').dialog('close');
                $('#maginfo').datagrid('updateRow', {
                    index: 6,
                    row: {
                        value: design + "<a href=\"#\" style=\"display:block;float:right\" onclick=\"chooseDesign()\">*更改磁铁设计*</a>"
                    }
                });
            }

            function formatPrice(val, row) {
                if (val === 'null') {
                    return '';
                } else {
                    return val;
                }
            }
            $("#batchinsert").click(function () {

                if ($('#batchinsert').is(':checked')) {
                    document.getElementById("geshu").style.display = "inline";
                    document.getElementById("batchnum").style.display = "inline";
                } else {
                    document.getElementById("geshu").style.display = "none";
                    document.getElementById("batchnum").style.display = "none";
                }
            });

            function submitform() {
                var maginfo = $("#maginfo").datagrid("getData");
                document.getElementById("hd1").value = JSON.stringify(maginfo);
                var bb = $('#batchinsert').is(':checked');
                var a = document.getElementById("batchnum").value;
                if (bb === true && a === "") {
                    alert("未填写批量插入个数！");
                    return false;
                } else {
                    var yn = window.confirm("确认提交？");
                    if (yn) {
                        alert("已提交");
                    } else {
                        return false;
                    }
                }

//                var parameter = $("#design_para").datagrid("getData");
//                document.getElementById("hd2").value = JSON.stringify(parameter);
//                if (JSON.stringify(require).length === 1057 || JSON.stringify(parameter).length === 2048) {
//                    alert("设计要求和基本参数未填写");
//                    return false;
//                } else {
//                    var yn = window.confirm("确认提交？");
//                    if (yn) {
//                        alert("成功提交");
//                    } else {
//                        return false;
//                    }
//                }
            }
            function newtype()
            {
                var name = window.prompt("新建磁铁类型", "");
                if (name !== null && name !== "")
                {
                    var x = document.getElementById("magtype");
                    var option = document.createElement("option");
                    option.text = name;
                    option.value = name;
                    try {
                        x.add(option, x.options[null]);
                    } catch (e) {
                        x.add(option, null);
                    }
                }
            }
            function newfamily()
            {
                var name = window.prompt("新建磁铁型号", "");
                if (name !== null && name !== "")
                {
                    var x = document.getElementById("magfamily");
                    var option = document.createElement("option");
                    option.text = name;
                    option.value = name;
                    try {
                        x.add(option, x.options[null]);
                    } catch (e) {
                        x.add(option, null);
                    }
                }
            }
            function newsection()
            {
                var name = window.prompt("新建区域", "");
                if (name !== null && name !== "")
                {
                    var x = document.getElementById("magsection");
                    var option = document.createElement("option");
                    option.text = name;
                    option.value = name;
                    try {
                        x.add(option, x.options[null]);
                    } catch (e) {
                        x.add(option, null);
                    }
                }
            }
            var mycolumns = [[

                    {field: 'name', title: '设备参数', width: 100, sortable: true},
                    {field: 'value', title: '设备信息', width: 100, resizable: false, formatter: function (value, arr) {
                            var editor = '';
                            if (typeof arr.editor === 'object') {
                                editor = arr.editor.type;
                            } else {
                                editor = arr.editor;
                            }
                            if (editor === "numberbox" && value !== '') {
                                return Number(value);
                            } else
                                return value;

                        }}
                ]];
            function chooseDesign() {
                var type = document.getElementById("magtype").value;
                var family = document.getElementById("magfamily").value;
                $.ajax({
                    type: 'POST',
                    url: 'SetMagnetDesign',
                    scriptCharset: 'UTF-8',
                    data: "magType=" + type + "&magFamily=" + family,
                    success: function (data) {
                        //alert(data);
                        var str = '{"rows":' + data + '}';
                        var s = $.parseJSON(str);
                        $('#dlg1').dialog('open');
                        $('#dg').datagrid('loadData', s);
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
                                    $('#dlg2').dialog('open');
                                    $('#dg_other').datagrid('loadData', s);
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
