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
        <link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.3/themes/icon.css?param=Math.ramdom()">     
        <script type="text/javascript" src="jquery-easyui-1.5.3/jquery.min.js"></script>
        <script type="text/javascript" src="jquery-easyui-1.5.3/jquery.easyui.min.js"></script>

        <title>查询-磁铁设计</title>
        <style type="text/css">
            span{
                font-size: 14px;
                font-weight: bold;
            }
            .a-upload {
                padding: 4px 10px;
                width: 350px;
                line-height: 20px;
                position: relative;
                cursor: pointer;
                color: #444;
                background: #fafafa;
                background-repeat: repeat-x;    
                border: 1px solid #bbb;
                border-radius: 5px 5px 5px 5px;
                overflow: hidden;
                display: inline-block;
                *display: inline;
                *zoom: 1
            }
            .a-upload  input {
                position: absolute;
                font-size: 100px;
                right: 0;
                top: 0;
                opacity: 0;
                filter: alpha(opacity=0);
                cursor: pointer
            }
            .a-upload:hover {
                color:  #000000;
                background: #eaf2ff;
                border: 1px solid #b7d2ff;
                text-decoration: none
            } 
        </style>
    </head>
    <body>
        <h2 >查询磁铁设计信息</h2>                
        <div class="easyui-panel" style="height:820px;padding:10px 60px;position: relative;" >
            <div style="position:absolute;left:0;right:0;width: 1300px;margin:0 auto;font-size:14px;">
                <form action="QueryDesign" method="post" target="" onsubmit="return submitform();">
                    <div style="width: 1200px;height: 30px">
                        <div id="info1" style="position:absolute;width: 200px">
                            <span>磁铁种类：</span> 
                            <select  id="magtype" name="magtype" style="width: 100px; height: 25px" >
                                <option value="none">未选择</option>
                                <option value="二极铁">二极铁</option>
                                <option value="四极铁">四极铁</option>
                                <option value="六极铁">六极铁</option>
                                <option value="八极铁">八极铁</option>
                            </select> 
                        </div>
                        <div id="info2" style="position:absolute;width: 200px;left:200px">
                            <span>磁铁型号：</span>
                            <select  id="magfamily" name="magfamily" style="width: 100px;height: 25px" >
                                <option value="-1">未选择</option>
                                <option value="1">I</option>
                                <option value="2">II</option>
                            </select>
                        </div>
                        <div style="margin:10px 0;"></div>
                        <div id="length" style="position:absolute;top: 50px" >                     
                            <span>有效长度范围：</span>                                
                            <input id="lengthmin" name="lengthmin" type="text"  size= 8 autocomplete="off" value="">
                            <span> - </span>                         
                            <input id="lengthmax" name="lengthmax" type="text" size= 8 autocomplete="off" value="">
                        </div>                  
                    </div>
                    <div id="intensity" style="position:absolute;top: 90px">
                        <span>磁场强度范围：</span>
                        <input name="sel_b" type="checkbox" value="" /><label>二极分量 </label>     
                        <input type="text" size=8 autocomplete="off" value="">
                        <span> - </span>
                        <input type="text" size= 8 autocomplete="off" value="">
                        <input name="sel_q" type="checkbox" value="" /><label>四极分量 </label>     
                        <input type="text" size=8 autocomplete="off" value="">
                        <span> - </span>
                        <input type="text" size= 8 autocomplete="off" value="">
                        <input name="sel_s" type="checkbox" value="" /><label>六极分量 </label>     
                        <input type="text" size=8 autocomplete="off" value="">
                        <span> - </span>
                        <input type="text" size= 8 autocomplete="off" value="">
                        <input name="sel_o" type="checkbox" value="" /><label>八极分量 </label>     
                        <input type="text" size=8 autocomplete="off" value="">
                        <span> - </span>
                        <input type="text" size= 8 autocomplete="off" value="">
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
                                <th data-options="field:'designid',width:60,sortable:true">ID</th>
                                <th data-options="field:'magtype',width:60">磁铁类型</th>
                                <th data-options="field:'magfamily',width:60">磁铁型号</th>
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
                    <a  href="index.html" class="easyui-linkbutton" data-options="">返回主页</a>
              </div>
        </div>
        <script type="text/javascript">
            function submitform() {
//               var lengthmin=document.getElementById("lengthmin");
//                var lengthmax=document.getElementById("lengthmax");                
//                if ((lengthmin.value!==''&&lengthmax.value==='')||(lengthmin.value===''&&lengthmax.value!=='')) {
//                    alert("有效长度范围未填写完整");                    
//                    return false;
//                } 

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
                    text: '下载设计图纸',
                    iconCls: 'icon-download',
                    handler: function () {
                        alert('未查询');
                    }
                },{
                    text: '查看用户自定义参数',
                    iconCls: 'icon-more',
                    handler: function () {
                        alert('未查询');
                    }
                }];
        </script>
    </body>
</html>
