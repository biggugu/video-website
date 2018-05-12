<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
  <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>js/jquery-easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>js/jquery-easyui/themes/icon.css">
    <script type="text/javascript" src="<%=basePath %>js/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script>
function docut(){
	var row = $('#dg').datagrid('getSelected');
	$.messager.confirm("提示", "您确定要删除这个视频吗吗？", function (res) {//提示是否删除
	if (res) {
	if (row) {
		$.ajax({
		    url: 'delete.do',         
		    data: "ID="+row.id,
		    type: "POST",   
			success:function(msg){
				 if(msg=="success"){
					var rowIndex = $('#dg').datagrid('getRowIndex', row);
					$('#dg').datagrid('deleteRow', rowIndex);  
					var rows = $('#dg').datagrid("getRows");
					$('#dg').datagrid("loadData", rows);
					alert("删除成功");
				 }
				 else{
					 alert("您无权删除该视频");
				 }
			 }
		});
		}
	}
	});
}

function doSearch(){
	var name=$('#findname').val();
	name=name.replace(/\%/g,'%25');//利用正则表达式替换文本框中的所有%
	$.ajax({
	    url: 'search.do',         
	    data: "findname="+name,
	    type: "POST",   
		success:function(msg){
			$('#dg').datagrid("loadData",msg);
		 }
	});
}

function doShowAll(){
	$.ajax({
	    url: 'show.do',         
	    data: "page="+"1"+"&rows="+"10",
	    type: "POST",   
		success:function(msg){
			$('#dg').datagrid("loadData",msg);
		 }
	});
}

function doShowMine(){
	$('#dg').datagrid("reload");
}

function doPlay(){
	var row = $('#dg').datagrid('getSelected');
	var obj = window.open("${pageContext.request.contextPath }/play.do?videoName="+row.videoname);
	obj.onload=function(){
		obj.document.title = "VIDEO PLAYING...";
	}
}

function doDownload(){
	var row = $('#dg').datagrid('getSelected');
	window.location.href="/download.do?fileName="+row.videoname;
}

</script>

<title>Title</title>
</head>
<body class="easyui-layout">
<div align="right">
欢迎<shiro:principal property="truename"/>----><a href="${pageContext.request.contextPath }/logout">退出登录</a></div>
 <div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:24px">
            这个地方放一个漂亮的标题栏图片和企业logo
            <div align="right">
欢迎<shiro:principal property="truename"/>----><a href="${pageContext.request.contextPath }/logout">退出登录</a></div>
        </div>
        <div data-options="region:'west',split:true,title:'菜单栏'" style="width:200px;padding:10px;">
            <ul>
                <li><a href="${pageContext.request.contextPath }/index">我的主页</a></li>
                <li><a href="${pageContext.request.contextPath }/file">视频上传</a></li>
                <li><a href="#">在线转码</a></li>
            </ul>
        </div>
        <div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;text-align:center;">
            版权所有区域
        </div>
        <div data-options="region:'center',title:'内容区域'" style="padding:10px;">
<div id="tb" style="padding:5px;height:auto">    
	<div>
        <span>视频名称:</span>
        <input id="findname" style="line-height:32px;border:1px solid #ccc">
        <a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearch()">搜索</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a class="easyui-linkbutton" iconCls="icon-list" plain="true" onclick="doShowAll()">显示全部视频</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a class="easyui-linkbutton" iconCls="icon-user" plain="true" onclick="doShowMine()">显示本人视频</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a class="easyui-linkbutton" iconCls="icon-videoplay" plain="true" onclick="doPlay()">播放</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a class="easyui-linkbutton" iconCls="icon-download" plain="true" onclick="doDownload()">下载</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="docut()">删除</a>
    </div>
</div>
<table id="dg" title="视频列表" class="easyui-datagrid" style="width:1700px;height:800px" 
            url="videoList.do"  
            toolbar="#tb" pagination="true"  
            rownumbers="true" fitColumns="true" singleSelect="true" nowrap="true" >  
        <thead>  
            <tr>  
                <th field="id" width="300" hidden="true">编号</th>  
                <th field="videoname" width="300">视频名称</th>  
                <th field="author" width="250">上传者</th>  
                <th field="intro" width="550">简介</th> 
                <th field="institution" width="250">学院</th>
                <th field="uploadtime" width="250">上传时间</th>  
            </tr>  
        </thead>  
    </table> 
    </div>
</body>
</html>
