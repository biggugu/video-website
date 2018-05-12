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
    <script type="text/javascript" src="<%=basePath %>js/jquery-easyui/datagrid-detailview.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script>

$(function(){
	$('#dg').datagrid({
		title:"视频列表",
		width:'1500px',
		height:'800px',
		pagination:true,
		rownumbers:true,
		fitColumns:true,
		singleSelect:true,
		nowrap:true,
		toolbar:'tb',
		url:"videoList.do",
		columns:[[
			{field:'id',title:'Item ID',width:100,hidden:true},
            {field:'videoname',title:'视频名称',width:170},
            {field:'author',title:'上传者',width:70},
            {field:'intro',title:'简介',width:300},
            {field:'institution',title:'学院',width:150},
            {field:'uploadtime',title:'上传时间',width:75}
			]],
		view: detailview,
		detailFormatter:function(index,row){
			return '<table><tr>' +
            '<td rowspan=2 style="border:0"><img src="file/'+row.videopicture+'.jpg" style="height:150px;"></td>' +
            '<td style="border:0">' +
            '<p>简介: ' + row.intro + '</p>' +
            '<p>视频大小: ' + (row.videosize/1000000).toFixed(2) + 'Mb</p>' +
            '<p>综合评分: ' + (row.goals).toFixed(2) + '</p>' +
            '</td>' +
            '</tr></table>';
		},
		
		onDblClickRow:function(index,row){    //双击进行操作的方法  
			var videoname=encodeURI(encodeURI(row.videoname));
			var obj = window.open("${pageContext.request.contextPath }/detail?videoid="+row.id+"&videoname="+videoname+"&videopicture="+row.videopicture);
			obj.onload=function(){
				obj.document.title = "VIDEO DETAIL";
			}  
   		}  
		
	});
});

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
	$('#dg').datagrid({
		title:"视频列表",
		width:'1500px',
		height:'800px',
		pagination:true,
		rownumbers:true,
		fitColumns:true,
		singleSelect:true,
		nowrap:true,
		toolbar:'tb',
		url:"show.do",
		columns:[[
			{field:'id',title:'Item ID',width:100,hidden:true},
            {field:'videoname',title:'视频名称',width:170},
            {field:'author',title:'上传者',width:70},
            {field:'intro',title:'简介',width:300},
            {field:'institution',title:'学院',width:150},
            {field:'uploadtime',title:'上传时间',width:75}
			]],
		view: detailview,
		detailFormatter:function(index,row){
			return '<table><tr>' +
            '<td rowspan=2 style="border:0"><img src="file/'+row.videopicture+'.jpg" style="height:150px;"></td>' +
            '<td style="border:0">' +
            '<p>简介: ' + row.intro + '</p>' +
            '<p>视频大小: ' + (row.videosize/1000000).toFixed(2) + 'Mb</p>' +
            '<p>综合评分: ' + (row.goals).toFixed(2) + '</p>' +
            '</td>' +
            '</tr></table>';
		},
		
		onDblClickRow:function(index,row){    //双击进行操作的方法  
			var obj = window.open("${pageContext.request.contextPath }/detail?videoid="+row.id+"&videoname="+row.videoname+"&videopicture="+row.videopicture);
			obj.onload=function(){
				obj.document.title = "VIDEO DETAIL";
			}  
   		}  
		
	});
}

function doShowMine(){
	$('#dg').datagrid({
		title:"视频列表",
		width:'1500px',
		height:'800px',
		pagination:true,
		rownumbers:true,
		fitColumns:true,
		singleSelect:true,
		nowrap:true,
		toolbar:'tb',
		url:"videoList.do",
		columns:[[
			{field:'id',title:'Item ID',width:100,hidden:true},
            {field:'videoname',title:'视频名称',width:170},
            {field:'author',title:'上传者',width:70},
            {field:'intro',title:'简介',width:300},
            {field:'institution',title:'学院',width:150},
            {field:'uploadtime',title:'上传时间',width:75}
			]],
		view: detailview,
		detailFormatter:function(index,row){
			return '<table><tr>' +
            '<td rowspan=2 style="border:0"><img src="file/'+row.videopicture+'.jpg" style="height:150px;"></td>' +
            '<td style="border:0">' +
            '<p>简介: ' + row.intro + '</p>' +
            '<p>视频大小: ' + (row.videosize/1000000).toFixed(2) + 'Mb</p>' +
            '<p>综合评分: ' + (row.goals).toFixed(2) + '</p>' +
            '</td>' +
            '</tr></table>';
		},
		
		onDblClickRow:function(index,row){    //双击进行操作的方法  
			var obj = window.open("${pageContext.request.contextPath }/detail?videoid="+row.id+"&videoname="+row.videoname+"&videopicture="+row.videopicture);
			obj.onload=function(){
				obj.document.title = "VIDEO DETAIL";
			}  
   		}  
		
	});
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
        <div data-options="region:'east',split:true,title:'最近播放'" style="width:200px;padding:10px;">
            
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
<table id="dg" ></table> 
</div>
</body>
</html>
