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
        <link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.3/themes/icon.css">     
        <script type="text/javascript" src="jquery-easyui-1.5.3/jquery.min.js"></script>
        <script type="text/javascript" src="jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="magnet.js"></script>
        <script>
         window.onload = function () {                
                $('#magnetinfo').propertygrid('loadData', rowm);  
              document.getElementById('num').innerText="当前编号：";
           };
        </script>
        <title>录入-磁铁设计</title>
    </head>
    <body>
         <h2>录入磁铁设备信息</h2> 
           <div class="easyui-panel" style="height:820px;padding:10px 60px;position: relative;" >
            
                <form action="" method="post" target="" onsubmit="return submitform();">
                     <div style="width: 1200px;margin:1px 470px;font-size:14px;">
                         <div id="info" >
                             <label for="magtype">磁铁种类: </label> 
                        <select  id="magtype" name="magtype" style="width:10%;height: 25px" >
                            <option value="二极铁">二极铁</option>
                            <option value="四极铁">四极铁</option>
                            <option value="六极铁">六极铁</option>
                            <option value="八极铁">八极铁</option>
                        </select>
                        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="margin-right: 20px" onclick="newtype()">新建种类</a>
                        <label for="magfamily">磁铁型号: </label>
                        <select  id="magfamily" name="magfamily" style="width:10%;height: 25px" >
                            <option value="1">I</option>
                            <option value="2">II</option>
                        </select>
                        <a href="#"  class="easyui-linkbutton" data-options="iconCls:'icon-add'"style="margin-right: 20px" onclick="newfamily()">新建型号</a>
                        <span id="num"></span>
                        </div>
                    <div style="margin:50px 0;"></div>
                  <div style="position: absolute;top: 70px;width: 700px;left: 530px;text-align: center">
                         <table id="magnetinfo" name="magnetinfo" class="easyui-propertygrid" style=" width: 800px;margin:auto" data-options="
                                   method: 'get',
                                   showGroup: true,
                                   scrollbarSize: 0,                                  
                                   columns: mycolumns                           
                                   ">
                            </table>  
                    </div>
                    <div style="position:absolute;top:350px;bottom: 0; left:0;right:0;text-align: center">                    
                        <input style="width:90px; font-size: 14px" class="a-upload" type="submit" value="提交" >
                        <input type="hidden" id="hd1" name="hd1"/>                        
                    </div>
                     </div>
                </form>
           
          </div>
         <script type="text/javascript">
             function submitform() {
//                var require = $("#design_require").datagrid("getData");
//                document.getElementById("hd1").value = JSON.stringify(require);
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
            var mycolumns = [[
                    {field: 'name', title: '设计参数', width: 100, sortable: true},
                    {field: 'value', title: '数值', width: 100, resizable: false, formatter: function (value, arr) {

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
             </script>
    </body>
</html>
