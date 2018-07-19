<%-- 
    Document   : newmeas
    Created on : 2018-1-22, 11:16:18
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
        <script type="text/javascript" src="jquery.form.js"></script>    
        <!--         <script type="text/javascript" src="dhall.js?"></script>-->
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
                $('#hall_con').hide();

            };

        </script>
        <title>录入-磁测数据</title>
    </head>
    <body >
        <h2 style="text-align:center">录入磁测数据</h2> 
        <div class="easyui-panel" style="height: 820px;padding-top:20px" >  
            <div style="margin:0 auto;width:480px"> 
                <form id="file_form" name="UpdExcel" action="UpdExcel" enctype="multipart/form-data" method="post">
                    <div style="">
                        <label for="magtype">磁铁种类：</label> 
                        <select  id="magtype" name="magtype" style="width: 100px; height: 25px" >
                            <option value="none">未选择</option>                                
                        </select>                                            
                        <label for="magfamily">磁铁型号：</label>
                        <select  id="magfamily" name="magfamily" style="width: 100px;height: 25px" >
                            <option value="-1">未选择</option>                                
                        </select>
                        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="margin-right: 20px" onclick="chooseMag()">选择磁铁</a>
                    </div>
                    <div id="showmag" style="padding-top:10px;padding-bottom: 10px"></div>                       
                    <input type="hidden" id="hd1" name="hd1" value=""/>  
                    <div style=""> <label>磁测方法：</label>
                        <input type="radio" name="identity" id="sws" value="sws" onclick="hidehallcon()" checked="checked" /><label for="sws">张力线测磁</label> 
                        <input type="radio" name="identity" id="rcs" value="rcs" onclick="hidehallcon()" /><label for="rcs">旋转螺线圈测磁</label>
                        <input type="radio" name="identity" id="hall" value="hall" onclick="showhallcon()" /><label for="hall">霍尔元件测磁</label>
                    </div>  
                    <div  name="hall_con" id="hall_con" style="padding-top:10px">                        
                        <input  name="current"class="easyui-numberbox" precision="3" label="测量电流[A]：" labelPosition="before" labelAlign="right" style="width:200px">            
                        <input  name="watergage"class="easyui-numberbox" precision="3"label="测量水压[Mpa]：" labelPosition="before"labelAlign="right" style="width:200px">
                    </div>
                    <div style="padding-top:10px">
                        <input id ="measdate" name="measdate" class="easyui-datebox"  editable="false" label="磁测时间：" labelPosition="before" labelAlign="right" style="width:200px">
                        <input name="measby"class="easyui-textbox" label="磁测人：" labelPosition="before"  labelAlign="right" style="width:200px" >                      
                    </div> 
                    <div style="padding-top:10px">                    
                        <input name="measat"class="easyui-textbox" label="磁测地点：" labelPosition="before" labelAlign="right" style="width:200px">            
                        <input name="remark"class="easyui-textbox" label="备注：" labelPosition="before"labelAlign="right" style="width:200px">                    
                    </div> 
                    <div style="padding-top:10px">   
                        <label>请选择处理数据文件(可多选)</label>
                        <input type="file" name="analysisfiles_input" id="analysisfiles_input"multiple="multiple" />     
                    </div>
                    <div style="padding-top:10px">   
                        <label>请选择原始数据(Excel文件)</label>
                        <input type="file" name="rawfile_input" id="rawfile_input" /> 
                        <input type="submit" value="上传" class="a-upload" id='upFile-btn' >
                        <input style="width:90px; font-size: 14px; background:#97CBFF; " class="a-upload" type="button" onclick="location = 'index.html'" value="返回主页" > 
                    </div>
                </form>
            </div>
        </div>
        <div id="dlg" class="easyui-dialog" title="选择磁铁"  style="text-align: center;width:900px;height:500px;padding:10px" data-options="iconCls:'icon-more',closed: true,resizable:true">
            <table id="dg" class="easyui-datagrid"  data-options="singleSelect:true,collapsible:true">
                <thead>
                    <tr>
                        <th data-options="field:'magid',width:70">ID</th>
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
            <div style="margin:5px 0;"></div>
            <a href="#" class="easyui-linkbutton" onclick="setMagnet()" data-options="iconCls:'icon-save'">Save</a>
        </div>

        <!--        <div id="dlg1" class="easyui-dialog" title="录入霍尔元件测磁测试条件"  style="text-align: center;width:440px;height:500px;padding:10px" data-options="iconCls:'icon-more',closed: true,resizable:true">
                    <table id="hall_con1" name="hall_con1" class="easyui-propertygrid" style="width:400px" data-options="
                                           method: 'get',
                                           showGroup: true,
                                           scrollbarSize: 0,                                  
                                           columns: mycolumns                           
                                           ">
                                    </table> 
                </div>-->
        <script type="text/javascript">
            $(function () {
                $("#file_form").submit(
                        function () {
                            if (document.getElementById("hd1").value === "") {
                                alert("未选择磁铁！");
                                return false;
                            } else if (document.getElementById("measdate").value === "") {
                                alert("未填写日期！");
                                return false;
                            } else {
                                //验证文件格式
                                var fileName1 = $('#rawfile_input').val();
                                if (fileName1 === '') {
                                    alert('请选择原始数据文件');
                                    return false;
                                }
                                var fileType = (fileName1.substring(fileName1
                                        .lastIndexOf(".") + 1, fileName1.length))
                                        .toLowerCase();
                                if (fileType !== 'xls' && fileType !== 'xlsx') {
                                    alert('文件格式不正确，原始数据应excel文件！');
                                    return false;
                                }
                                //alert('您已选择原始文件：'+fileNname1+";处理文件："+fileName2);
                                $("#file_form").ajaxSubmit({
                                    dataType: "json",
                                    success: function (data) {
                                        if (data['result'] === 'OK') {
                                            alert('上传文件成功');
                                            document.location.reload();
                                        } else {
                                            alert(data['result']);
                                        }
                                        return false;
                                    }
                                });
                                return false;
                            }
                        });
            });
            function hidehallcon() {
                $('#hall_con').hide();
            }
            function showhallcon() {
                //$('#hall_con').propertygrid('loadData', rowhall);
                //$('#dlg1').dialog('open');
                $('#hall_con').show();
            }
            function setMagnet() {
                var row = $('#dg').datagrid('getSelected');
                var magname = row.magname;
                var magid = row.magid;
                $('#dlg').dialog('close');
                document.getElementById("showmag").innerHTML = "<font size='3px' color='red' >您已选择：" + magname + "</font>";
                document.getElementById("hd1").value = magid;
            }
            function chooseMag() {
                $.ajax({
                    type: 'POST',
                    url: 'QueryMagnet?<%=Math.random()%>',
                    scriptCharset: 'UTF-8',
                    data: "magtype=" + document.getElementById("magtype").value + "&magfamily=" + document.getElementById("magfamily").value + "&datemin=" + "" + "&datemax=" + "",
                    success: function (data) {
                        var str = '{"rows":' + data + '}';
                        var s = $.parseJSON(str);
                        $('#dlg').dialog('open');
                        $('#dg').datagrid('loadData', s);
                    }
                });
            }
//            $('#upFile-btn').click(function (e) {
//                if ( document.getElementById("hd1").value === '') {
//                   alert("未选择磁铁");
//                   return false;
//                }
//            });
        </script>
    </body>
</html>
