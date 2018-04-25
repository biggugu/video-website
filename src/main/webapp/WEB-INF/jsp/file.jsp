<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
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

<title>视频上传</title>
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
        <div data-options="region:'center',title:'内容区域'" style="padding:10px;">
         <p>请选择要上传的视频（必须为mp4格式,文件小于3G）</p>
			<form action="/upload" method="post" enctype="multipart/form-data">
			<br><input type="file" name="file"/>
			<td>所属学院:</td>
			<select id="cc" class="easyui-combobox" name="institution" style="width:200px;">
				<option value="计算机科学与技术学院">计算机科学与技术学院</option>
				<option value="物理与光电学院">物理与光电学院</option>
				<option value="凤凰传媒学院">凤凰传媒学院</option>
				<option value="纳米学院">纳米学院</option>
				<option value="纺织学院">纺织学院</option>
				<option value="东吴商学院">东吴商学院</option>
				</select>
			<br><textarea name="intro" placeholder="简介：" style="width:800px;height:450px;" maxlength="255"></textarea>
			<br><input type="submit" value="提交上传">
			</form>
        </div>
  </body>
</html>
