<%-- 
    Document   : homepage
    Created on : 2017-9-5, 17:03:12
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
        
        <title>录入-磁铁设计</title>
        <style type="text/css">
            p{font-size:14px;}
            span {font-size:14px;}
            
        </style>

    </head>
    <body>
        <h2>录入磁铁设计信息</h2>  
        <div class="easyui-panel"  style="width:100%;padding:30px 60px 60px;position: relative;" >
            <form action="NewDesign" method="POST" target="_blank" onsubmit="return submitform();" >
                <div style="margin:0 auto;text-align: center">
                    <span>磁铁种类: </span> 
                    <select  id="magtype" name="magtype" style="width:15%;height: 25px" >
                        <option value="二极铁">二极铁</option>
                        <option value="四极铁">四极铁</option>
                        <option value="六极铁">六极铁</option>
                        <option value="八极铁">八极铁</option>
                    </select>
                    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="margin-right: 50px" onclick="newtype()">新建种类</a>
                    <span>磁铁型号: </span>
                    <select  id="magfamily" name="magfamily" style="width:15%;height: 25px" >
                        <option value="1">I</option>
                        <option value="2">II</option>
                    </select>
                    <a href="#"  class="easyui-linkbutton" data-options="iconCls:'icon-add'"style="margin-right: 50px" onclick="newfamily()">新建型号</a>
                </div>
                <div id="table" style="width: 1000px;margin:0 auto; ">
                    <div id="table1" style="margin-left:20px ;float: left ">
                        <p> 请输入磁铁设计要求：
                        <div style="margin:25px 0;"></div>
                        <!--<a href="javascript:void(0)" class="easyui-linkbutton" onclick="getChanges1()">查看修改项</a>-->                         
                        </p>                                               
                        <table id="design_require" name="design_require" class="easyui-propertygrid" style="width:400px" data-options="
                               url: 'design_require.json',
                               method: 'get',
                               showGroup: true,
                               scrollbarSize: 0,
                               columns: mycolumns                           
                               ">
                        </table>   
                        <div style="margin:5px 0;"></div>
                        <div class="easyui-panel" title="其他信息"  >
                            <div style="margin-bottom:5px;margin-top: 5px">
                                <input  class="easyui-textbox" name="designed_by" label="磁铁设计人：（多人请用分号隔开）" labelPosition="top" style="width:100%">
                            </div>
                            <div style="margin-bottom:5px">
                                <input class="easyui-textbox" name="approved_by" label="磁铁负责人：（多人请用分号隔开）" labelPosition="top" style="width:100%">
                            </div>
                            <!--                               -->
                            <div style="margin-bottom:5px">
                                <input class="easyui-textbox" name="remark" label="备注" labelPosition="top" style="width:100%">
                            </div>
<!--                                                        <div style="margin-bottom:5px">
                                                            <input class="easyui-filebox" name="mmplot" id="mmplot" label="上传机械、物理设计图:（PDF格式）" labelPosition="top" data-options="prompt:'选择机械设计文件...',accept: 'application/pdf'" style="width:100%">
                                                        </div>
                            <div align="right">             <a href="#" class="easyui-linkbutton" style="width:100%" onclick="return ajaxFileUpload();">上  传</a>         </div> 
                                                        <div style="margin-bottom:5px">
                                                                <input class="easyui-filebox"  name="mpplot" id="ppplot" data-options="prompt:'选择物理设计文件...',accept: 'application/pdf'" style="width:100%">
                            </div>-->
                        </div>                                                          
                    </div>
                    <div id="table2" style="margin-left:20px ;float: right">
                        <p>请输入磁铁设计参数：
                            <!--<a href="javascript:void(0)" class="easyui-linkbutton" onclick="getChanges2()">查看修改项</a>-->
                            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="addrow()">新增设计参数</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="delrow()">删除设计参数</a>
                        </p>
                        <table id="design_para" name="design_para" class="easyui-propertygrid" style="width:400px" data-options="
                               url: 'design_para.json',
                               method: 'get',
                               showGroup: true,
                               scrollbarSize: 0,
                               columns: mycolumns
                               ">
                        </table>                    
                    </div>

                </div>
                <div style="width:1800px;position: absolute;bottom: 0;margin:0 auto;text-align: center">
                    <p>输入完成请点击提交
                        <input style="width:90px; " type="submit" value="提交" >
                        <input type="hidden" id="hd1" name="hd1"/>
                        <input type="hidden" id="hd2" name="hd2"/>

                    </p>
                </div>
            </form>
<!--            <div style="width:1800px;position: absolute;bottom: 100px;margin:0 auto;text-align: left">
                <form id="form1" action="UploadHandleServlet" enctype="multipart/form-data" method="post" target="nm_iframe" onsubmit="beginUpload()">
                   
                    上传文件1：<input type="file" name="file1"><br/>

                    上传文件2：<input type="file" name="file2"><br/>
                    <input type="submit" value="提交" onclick="formSubmit()" >
                </form>
              <span id="spann"></span>  
    <table width="300px;" border="0"><tr><td>  
   <table id="tablee" height="20px;" style="background-color: gray;"><tr><td></td></tr></table>  
   </td>  
   
   </tr>  
  </table> 
                <iframe id="id_iframe" name="nm_iframe" style="display:none;"></iframe>
</div>-->
        <div>    
<form id="formId" action="IframeAjax" method="post"  
        target="hiddenFrameName" enctype="multipart/form-data">  
        <div>  
            <input id="imageId" type="file" name="imageName"  
                onchange="uploadImg()" />  
            <div style="display: none; color: red;" id="errorTip">活动海报不可为空  
            </div>  
            <div style="display: none; color: green;" id="successTip"></div>  
        </div> 
    
<!--    <img id="img" src="" width="200" height="200" style="display: none;" /> -->
    </form>  
    <iframe style="display: none" name='hiddenFrameName' id="hidden_frame"></iframe>  
    
  </div>
     
        <script type="text/javascript">
  function uploadImg() {  
        var names = $("#imageId").val().split(".");  
        if (names[1] !== "gif" && names[1] !== "GIF" && names[1] !== "jpg"  
                && names[1] !== "JPG" && names[1] !== "png" && names[1] !== "PNG") {  
            $("#errorTip").html("海报必须为gif,jpg,png格式");  
            $("#errorTip").show();  
            return;  
        }  
        $("#formId").submit();  
    }  
  
    function callback(success, message, url) {  
        if (success === false) {  
            $("#errorTip").html(message);  
            $("#errorTip").show();  
        } else {  
            $("#errorTip").hide();  
            $("#successTip").html(message);  
            $("#successTip").show();  
//            $("#img").attr("src", "E:/plot/uploads/" + url);  
//            $("#img").show();  
        }  
    }  

//            function callback() {
//                $.ajax({  
//            type:"post",  
//            url:"UploadHandleServlet",//响应文件上传进度的servlet   
//            success:function(msg){  
//            document.getElementById("spann").innerHTML="已上传："+msg;//显示读取百分比   
//            //document.getElementById("tablee").width=message;//通过表格宽度 实现进度条   
//           }  
//       });
//            }
            function formSubmit() {
                window.setInterval("callback()", 100);//每隔100毫秒执行    
                document.getElementById("#form1").form.submit();
            }
         
    
          
            function submitform() {
                var require = $("#design_require").datagrid("getData");
                document.getElementById("hd1").value = JSON.stringify(require);
                var parameter = $("#design_para").datagrid("getData");
                document.getElementById("hd2").value = JSON.stringify(parameter);
               
                var yn = window.confirm("确认提交？");
                if (yn) {
                    alert("您已提交成功");
                } else
                    return false;
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
            function getChanges1() {
                //var all=$('#table_require').getData();
                //  alert(all);
                var s = '';
                var rows = $('#design_require').propertygrid('getChanges');
                for (var i = 0; i < rows.length; i++) {
                    s += rows[i].name + ':' + rows[i].value + ',';
                }
                alert(s);
            }
            function getChanges2() {
                var s = '';
                var rows = $('#design_para').propertygrid('getChanges');
                for (var i = 0; i < rows.length; i++) {
                    s += rows[i].name + ':' + rows[i].value + ',';
                }
                alert(s);
            }
            function addrow() {
                var input = window.prompt("新建设计参数,格式：参数名/参数组名/参数类型(text或number)", "property/其他/text");
                var slice = input.split("/");
                var property = slice[0];
                var group = slice[1];
                var type;
                if (slice[2] === "text") {
                    type = "text";
                } else if (slice[2] === "number") {
                    type = "numberbox";
                } else {
                    alert("格式错误");
                }
                var row = {
                    name: property,
                    value: '',
                    group: group,
                    editor: {type: type, options: {precision: 5}}
                };
                $('#design_para').propertygrid('appendRow', row);
            }
            function delrow() {
                var row = $('#design_para').propertygrid('getSelected');
                var index = $('#design_para').propertygrid('getRowIndex', row);
                if (index > 19) {
                    $('#design_para').propertygrid('deleteRow', index);
                } else
                    alert("只能删除自定义参数！");
            }
        </script>  

    </body>
</html>
