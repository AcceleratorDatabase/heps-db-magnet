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
        <link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.3/themes/icon.css?<%=Math.random()%>">     
        <script type="text/javascript" src="jquery-easyui-1.5.3/jquery.min.js"></script>
        <script type="text/javascript" src="jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
        <style type="text/css">             
            label{
                font-size: 16px
            }
        </style>
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
                $.ajax({
                    type: 'POST',
                    url: 'LoadProject',
                    success: function (data) {
                        var b = data.split(",");
                        var x = document.getElementById("magproject");
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
        <title>查询-磁铁设计</title>
        <style type="text/css">
            span{
                font-size: 14px;
                font-weight: bold;
            }

        </style>
    </head>
    <body>
        <h2 style="text-align:center">查询磁铁设计信息</h2> 
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
        <div class="easyui-panel" style="height:820px;padding:10px 60px;position: relative;" >
            <div style="position:absolute;left:0;right:0;width: 1300px;margin:0 auto;font-size:14px;">
                <form action="QueryDesign" method="post" target="" onsubmit="return submitform();">
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
                                <option value="none">未选择</option>                                
                            </select>
                        </div>
                        <div id="info3" style="position:absolute;width: 200px;left:800px">
                            <label for="magproject">所属工程：</label>
                            <select  id="magproject" name="magproject" style="width: 100px;height: 25px" >
                                <option value="none">未选择</option>                                
                            </select>
                        </div>
                        <div style="margin:10px 0;"></div>
                        <div id="designby" style="position:absolute;top: 50px;left: 400px" >                     
                            <label for="magdesignby">设计人：</label>                                
                            <input id="magdesignby" name="magdesignby" type="text"  size= 10 autocomplete="off" value="">                            
                        </div>
                    </div>
                    <div style="position:absolute;top:130px;bottom: 0; left:0;right:0;text-align: center">                    
                        <input style="width:90px; font-size: 14px" class="a-upload" type="submit" value="查询" >
                    </div> 
                </form> 
                <div style="position: absolute;top: 170px;width: 1200px">
                    <table id="dg" name="dg" class="easyui-datagrid" title="查询结果" 
                           data-options="
                           singleSelect: true,                                                    
                           collapsible: true,
                           rownumbers: true,
                           dataType:'json',
                           toolbar:toolbar,                         
                           url: '',
                           method: 'get',
                           onLoadSuccess: function(){                          
                           }
                           ">
                        <thead data-options="frozen:true">
                            <tr>
                                <!--                                <th data-options="field:'designid',width:60,sortable:true">ID</th>-->
                                <th data-options="field:'magtype',width:60">磁铁类型</th>
                                <th data-options="field:'magfamily',width:60">磁铁型号</th>
                                 <th data-options="field:'magproject',width:60">所属工程</th>
                            </tr>
                        </thead>
                        <thead>
                            <tr>
                                <th colspan="13"><span>设计要求</span></th>
                                <th colspan="11"><span>主要参数</span></th>
                                <th colspan="5"><span>水冷参数</span></th>
                                <th colspan="4"><span>尺寸及重量</span></th>
                                <th colspan="3"><span>其他</span></th>
                            </tr>
                            <tr>                              
                                <th data-options="field:'length',width:70">有效长度</th>
                                <th data-options="field:'aperture',width:70">磁铁孔径</th>
                                <th data-options="field:'min_gap',width:120">相邻磁极最小间隙</th>
                                <th data-options="field:'useful_field',width:80">好场区范围</th>
                                <th data-options="field:'intensityB',width:70">二极分量</th>
                                <th data-options="field:'intensityQ',width:70">四极分量</th>
                                <th data-options="field:'intensityS',width:70">六极分量</th>
                                <th data-options="field:'intensityO',width:70">八极分量</th>
                                <th data-options="field:'sys',width:70">系统分量</th>
                                <th data-options="field:'non_sys',width:80">非系统分量</th>

                                <th data-options="field:'offset',width:100">偏置安装偏移量</th>
                                <th data-options="field:'ampere_turns',width:80">励磁安匝数</th>
                                <th data-options="field:'ampere_turns_each',width:100">每磁极线圈匝数</th>
                                <th data-options="field:'current',width:70">励磁电流</th>
                                <th data-options="field:'wire',width:70">导线规格</th>
                                <th data-options="field:'current_density',width:70">电流密度</th>
                                <th data-options="field:'wire_length',width:100">磁铁导线总长度</th>
                                <th data-options="field:'resistence',width:80">磁铁总电阻</th>
                                <th data-options="field:'inductance',width:70">磁铁电感</th>
                                <th data-options="field:'voltage',width:70">励磁电压</th>
                                <th data-options="field:'consumption',width:70">磁铁功耗</th>
                                <th data-options="field:'c_pressure_drop',width:80">冷却水压降</th>
                                <th data-options="field:'c_channel_num',width:80">并联水路数</th>
                                <th data-options="field:'c_velocity',width:80">冷却水流速</th>
                                <th data-options="field:'c_flow',width:80">冷却水流量</th>
                                <th data-options="field:'c_temp',width:60">水温升</th>
                                <th data-options="field:'core_length',width:70">铁芯长度</th>
                                <th data-options="field:'core_section',width:80">铁芯截面尺寸</th>
                                <th data-options="field:'core_weight',width:60">铁芯重</th>
                                <th data-options="field:'copper_weight',width:60">铜重</th>

                                <th data-options="field:'designedby',width:70">设计人</th>
                                <th data-options="field:'approvedby',width:70">负责人</th>
                                <th data-options="field:'remark',width:90">备注</th>
                            </tr>
                        </thead>
                    </table>
                </div>               
            </div>  
            <div style="position:absolute;top:780px;bottom: 0; left:0;right:0;text-align: center">  
                <input style="width:90px; font-size: 14px;background:#97CBFF ;" class="a-upload" type="button" onclick="location = 'index.html'" value="返回主页" >
            </div>
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
                }, {
                    text: '下载机械设计图纸',
                    iconCls: 'icon-download',
                    handler: function () {
                        alert('未查询');
                    }
                }, {
                    text: '下载物理设计图纸',
                    iconCls: 'icon-download',
                    handler: function () {
                        alert('未查询');
                    }
                }, {
                    text: '查看用户自定义参数',
                    iconCls: 'icon-more',
                    handler: function () {
                        alert('未查询');
                    }
                }];
        </script>
    </body>
</html>
