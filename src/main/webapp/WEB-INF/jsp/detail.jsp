<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.thirdtest.thirddemo.entity.UEntity"
    pageEncoding="UTF-8"%>
<%@ page import="org.apache.shiro.SecurityUtils" %>
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

  <style type="text/css">
        * {
            box-sizing: border-box;
        }
        body {
            margin: 0; 
            padding: 0;
            font: 16px/20px microsft yahei;
        }
        .wrap {
            width: 100%;
            height: 100%;
            padding: 40px 0;
            position: sticky;
            top: 0%;
            margin-top: 0px;
            opacity: 0.8;
            background: linear-gradient(to bottom right,#ffffff, #ffffff);
            background: -webkit-linear-gradient(to bottom right,#ffffff, #ffffff);
        }
        .container {
            width: 60%;
            margin: 0 auto;
        }
        .container h3 {
            color: #000000;
            font-weight: 500;
        }
        
        .r{
        	text-align: right;
        }

		
        @keyframes square {
            0%  {
                    -webkit-transform: translateY(0);
                    transform: translateY(0)
                }
            100% {
                    bottom: 400px;
                    transform: rotate(600deg);
                    -webit-transform: rotate(600deg);
                    -webkit-transform: translateY(-500);
                    transform: translateY(-500)
            }
        }
        @-webkit-keyframes square {
            0%  {
                -webkit-transform: translateY(0);
                transform: translateY(0)
            }
            100% {
                bottom: 400px;
                transform: rotate(600deg);
                -webit-transform: rotate(600deg);
                -webkit-transform: translateY(-500);
                transform: translateY(-500)
            }
        }
    </style>

<script>
var user="<%= ((UEntity) SecurityUtils.getSubject().getPrincipal()).getTruename()%>";
//console.log(user);
$(function(){
	var localhref = window.location.href;
	var localarr = localhref.split('?')[1].split('&');
	//alert(localarr);
	var tempObj = {};
	for (var i = 0; i < localarr.length; i++) {
　　		tempObj[localarr[i].split('=')[0]] = localarr[i].split('=')[1];
　　
	}
	document.getElementById("img").innerHTML='<img src="file/'+tempObj.videopicture+'.jpg" alt="无法显示" width="504" height="328">';
	document.getElementById("name").innerHTML='<h3>'+decodeURI(decodeURI(tempObj.videoname))+'</h3>';
	var page=1;
	var rows=10;
	getComment(page,rows);
	function getComment(page,rows){
	$.ajax({
	    url: 'showComment.do',      
	    data: 'page='+page+"&rows="+rows+"&videoid="+tempObj.videoid,
	    type: "POST",   
		success:function(json){
			var s='<ul >';
			for (var i in json.rows) {     //展示评论
                s += '<li><br><i>' + (parseInt(i)+parseInt(1)) +'楼#  '+ json.rows[i].customer + '：</i>' +  
                	'<div class="r"><input type="button" name="'+"reply"+'" class="btn btn-default" id="'+i+'" value="回复" "/>'+
                	'<input type="button" name="'+"showReply"+'" class="btn btn-default" id="'+i+'show" value="查看回复" "/></div>'+
                    '<br><p>' + json.rows[i].comment + '</p>' +  
                    '<br>————发表时间：' + json.rows[i].time + '<br><div id="'+i+'reply"></div>'+'<br><hr style="height:1px;border:none;border-top:1px solid #555555;" />'+
                    '<br><div id="'+i+'pr" style="background:#efefef;border:1px solid #ccc;display:none;"></div>'+
                    '</li>' ;  
	
            }
			s+='</ul>';
			document.getElementById("comment").innerHTML=s;
			//console.log(json.rows.length);
			for(var j in json.rows){            //存储属性值
				$('#'+j).attr('commentid',json.rows[j].id);
				$('#'+j).attr('targetusername',json.rows[j].customer);
				$('#'+j+'show').attr('replyid',json.rows[j].id);
			}
			
			btnClick();
			function btnClick(){                //回复按钮事件
				$("input[name='reply']").off('click').on('click',function(){
					var reply=prompt("请输入你的回复");
					if(!reply){
						alert("回复不能为空！");
					}
					else{
					var index=$(this).attr("id");
					var replyid = $('#'+index).attr("commentid");
					var replyusername = $('#'+index).attr("targetusername");
					$.ajax({
	                    type: "post",
	             		url: "saveReply.do",
	             		data: "commentid="+replyid+"&targetusername="+replyusername+"&reply="+reply,
	             		datatype: "json",
	             		success: function(msg){
	             			location.reload(); //刷新
	             		},
	             
	             		error: function(msg){
	                 		alert("回复内容写入失败");

	             		}
	             	});
					}
				});
				$("input[name='showReply']").off('click').on('click',function(){
					var p=1;
					var r=10;
					var index=$(this).attr("id"); //index="ishow"
					var replyid = $('#'+index).attr("replyid");  //取出属性值
					getReply(p,r);
					function getReply(p,r){
						$.ajax({
							url: 'showReply.do',      
						    data: 'page='+p+"&rows="+r+"&replyid="+replyid,
						    type: "POST",   
							success:function(data){
								var s='<ul>';
								for (var i in data.rows) {     //展示回复
					                s += '<br><hr style="height:1px;border:none;border-top:1px solid #555555;" />'+
					                	'<li><br><i>' + data.rows[i].fromusername + ' 回复 '+ data.rows[i].tousername+ '：</i>' +  
					                    '<br><p>' + data.rows[i].reply + '</p>' +  
					                    '<br>————回复时间：' + data.rows[i].time + 
					                    '<input type="button" name="'+"replyOfReply"+'" class="btn btn-default" id="'+i+'ror" value="回复" />'+
					                    '</li>' ;  
						
					            }
								s+='</ul>';
								document.getElementById(index.substring(1,0)+'reply').innerHTML=s;

								$('#'+index.substring(1,0)+'pr').pagination({    //分页栏
							    	total:data.total,
							    	layout:['links'],
							    	onSelectPage:function(pageNumber, pageSize){
							    		getReply(pageNumber,pageSize);
							    	}
							    });
								document.getElementById(index.substring(1,0)+'pr').style.display='block';
								
								for(var j in data.rows){            //存储属性值
									$('#'+j+'ror').attr('commentid',data.rows[j].commentid);
									$('#'+j+'ror').attr('fromusername',data.rows[j].fromusername);
								}
								
								btnClick();
								function btnClick(){ 
									$("input[name='replyOfReply']").off('click').on('click',function(){
										var reply=prompt("请输入你的回复");
										if(!reply){
											alert("回复不能为空！");
										}
										else{
										var index=$(this).attr("id"); //index="iror"
										var commentid=$('#'+index).attr("commentid");
										var replyname=$('#'+index).attr("fromusername");
										$.ajax({
						                    type: "post",
						             		url: "saveReply.do",
						             		data: "commentid="+commentid+"&targetusername="+replyname+"&reply="+reply,
						             		datatype: "json",
						             		success: function(msg){
						             			location.reload(); //刷新
						             		},
						             
						             		error: function(msg){
						                 		alert("回复内容写入失败");

						             		}
						             	});
										}
									});
								}
								
							}
						});
						
					}
					
				});
				
			}	
			
			$(document).ready(function(){
				
	            //评论区
			    $("#comment_sub").click(function(){
			        if(!$('#comment_text').val())
			        	alert("评论内容不能为空！");
			        else
			        {
			            //将警告区域的信息清除
			            
			            var content = $('#comment_text').val();

			            /*
			            $('#comment').append('<br><i>'+ (json.total+1) +'楼#  '+ user + "：</i>" + '<button type="submit" id="reply">回复</button>'+ 
			            		'<br><p>' + content + '</p>' + 
			            		'<br>————发表时间：'+mydate.toLocaleDateString());
						*/
			            //清空文本域
			            $('#comment_text').val("");

			            //使用ajax来向后台传递数据
			            $.ajax({
			                    type: "post",
			             		url: "saveComment.do",
			             		data: "videoid="+tempObj.videoid+"&comment="+content,
			             		datatype: "json", //回调函数接收数据的格式
			             		//函数调用成功后
			             		success: function(msg){
			             			location.reload();
			                 		//什么也不用做
			             },
			             //函数调用失败后，
			             error: function(msg){
			                 //弹窗，告知用户写入失败
			                 alert("评论内容写入失败");
			                 //alert(msg);
			             }
			             });
			        } 
			    });
	            
			  //打分区
			    var shixin = "★";
			    var kongxin = "☆";
			    var goals=0;
			    $(".goal li").mouseenter(function(){
			        $(this).text(shixin).prevAll().text(shixin).end().nextAll().text(kongxin);
			    });
			    $(".goal li").on("click",function(){
			    	$(this).addClass("clicked").siblings().removeClass("clicked");
			    	var index = $(this).index();
			    	switch(index){
			    		case 0:
			    			goals=60;
			    			break;
			    		case 1:
			    			goals=70;
			    			break;
			    		case 2:
			    			goals=80;
			    			break;
			    		case 3:
			    			goals=90;
			    			break;
			    		case 4:
			    			goals=100;
			    			break;
			    	}
			    	if(confirm("确认提交分数？"))
			    	{
			    		$.ajax({
		                    type: "post",
		             		url: "updateGoals.do",
		             		data: "goals="+goals+"&videoid="+tempObj.videoid,
		             		success: function(msg){
		             			alert(msg);
		            		 },
		             		error: function(msg){
		                 		alert("打分失败");
		             		}
		             	});
			    	}
			    });
			    $(".goal").mouseleave(function(){
			        $(".goal li").text(kongxin);
			        $(".clicked").text(shixin).prevAll().text(shixin);
			    });
			});
		    $('#pp').pagination({    //分页栏
		    	total:json.total,
		    	layout:['links'],
		    	onSelectPage:function(pageNumber, pageSize){
		    		getComment(pageNumber,pageSize);
		    	}
		    });
		 }
	});
	}
});

</script>
</head>
<body>
 <div class="wrap">
<div class="container">
<div id="img" ></div> 
<div id="name" ></div> 
<p>你的评分：</p>
<ul class="goal" style="font-size: 40px;color: teal;list-style: none;">
    <li style="float: left;cursor: pointer;">☆</li>
    <li style="float: left;cursor: pointer;">☆</li>
    <li style="float: left;cursor: pointer;">☆</li>
    <li style="float: left;cursor: pointer;">☆</li>
    <li style="float: left;cursor: pointer;">☆</li>
</ul>
<br><br>
<hr style="width:80%;
margin:0 auto;
border: 0;
height: 0;
border-top: 1px solid rgba(0, 0, 0, 0.1);
border-bottom: 1px solid rgba(255, 255, 255, 0.3);" />
<div >
    <br><p class="small">评论区：</p>
    <textarea id="comment_text" class="form-control" rows="3" style="width:800px;height:150px;">
    </textarea>
    <p class="text-right"><button id="comment_sub" type="button" class="btn btn-primary btn-xs">提交评论</button></p>
</div>
<br><hr style="height:1px;border:none;border-top:1px solid #555555;" />
<div id="comment"></div> 
<div id="pp" style="background:#efefef;border:1px solid #ccc;"></div>
</div>
</div>
</body>
</html>