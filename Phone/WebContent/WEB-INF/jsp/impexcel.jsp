<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String rst = (String)request.getAttribute("rst");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>导入EXCEL</title>

<style type="text/css">
textarea {
resize: none;
}

.center { margin:0px auto; }

.padding_top{
	padding-top: 2px;
}


</style>
 <link rel="stylesheet" href="<%=basePath %>bootstrap-2.3.2/css/bootstrap.css"/>
<script type="text/javascript" src='<%=basePath %>js/jquery.js'></script>

<script type="text/javascript">
	
	
	window.onload = function (){
		var rst = "<%=rst%>";
	
		if(null!=rst&&"null"!=rst){
			if("ok"==rst){
				alert("导入成功");
			}
			rst=null;
			window.location = "<%=basePath%>eximp/impexcel";
		}
		
	}
	
	
	function f_imp(){
		
		<%-- 得到文件的路径 --%>
		var filePath = $("#file").val();
		
		
		
		if(null==filePath||""==filePath||"undefined"==filePath){
			alert("请选择要导入的Excel文件！");
			return;
		}else{
			<%-- 判断是不是EXCEL文件 .xls或.xlsx结尾 --%>
			var fileAd = filePath.substring(filePath.lastIndexOf("."));
			
			if(fileAd!=".xlsx"&&fileAd!=".xls"){
				alert("请选择Excel文件！");
				return;
			}
		}
		
		
		$("#uploadEx").submit();
	}
	
	
	function f_download(){
	
		window.location = "<%=basePath%>eximp/download";
		
	}
	
	
	function f_send(){
		var txt=$("#contents").val();
		
		if(""==txt){
			alert("请填写要发送的短信内容！");
			return;
		}
		
		 $.ajax({
	         type: 'POST',
	         url: '<%=basePath%>eximp/sendEx',
	         async:true,
	         data:{
	        	 contents:txt  
	         },
	         success: function(data){
	        	 
	           var jsdata = eval("("+data+")");
	           //var dats= eval("("+jsdata.data+")");
	           if(jsdata.code='000'){
	        	   alert("发送成功");
	           }
	           
	           var list = jsdata.data;
	           
	           if(null!=list&&list.length>0){
	        	   
	        	   $('#t_head').html("");
	        	   $('#t_body').html("");
	        	   
	        	   $('#div_fail').css("display","block");
		           	
		           	
		           $('#t_head').append("<tr><th colspan='2'>序号</th>" +"<th>未发送成功的电话号码</th></tr>");
		           
				      
		           for(var i=0;i<list.length;i++){
		        	   $('#t_body').append(
		        			   "<tr>"+
			        		   "<td colspan='2'>"+(i+1)+"</td>" +
			        		   "<td>"+list[i].phone+"</td>"+
			        		   "</tr>"
			           );
		        	   
		           }
	           }
	           
	         }
	     });
	
	}

</script>




</head>
<body style="text-align: center;">

	<div style="width: 900px; margin-top: 150px;" class="center">
	
		<div>
			<div style="margin-bottom: 5px;text-align: left;">
            	<b>导入EXCEL</b>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="btn btn-info" value="下载Excel模板" onclick="f_download()">
        	</div>
			
			<form action="uploadEx" id="uploadEx" method="post" enctype="multipart/form-data">
			<table id="tb_imp" style="width: 800px; text-align: center;"  class=" table-bordered table-hover padding_top">
				<tr>	
					<td width="40%" style="text-align: right;">请选择要导入的EXCEL文件：</td>
					<td width="45%" ><input id="file" type="file" name="file" ><br></td>
					<td><input type="button" class="btn btn-info" value="导入" onclick="f_imp()"></td>
				</tr>
			
			</table>
			</form>
			   
		</div>
	
		<div>
			<div style="margin-bottom: 5px; text-align: left;">
            	<b>发送短信内容</b> <small style="color: red;">(如果有优惠券信息使用 {code} 字样加入短信内容&nbsp;&nbsp;例:优惠券:{code} , 有机绿色—五常稻花香米!)</small>
        	</div>
			<form id="sendMsg" action="sendEx" method="post">
			   <table style="width: 800px; text-align: center;" class="table-bordered table-hover padding_top" >
				<tr>	
					<td width="40%" style="text-align: right;">请输入要发送的内容：</td>
					<td width="45%" >
						 <textarea id ="contents" style="width: 80%;height: 150px;"></textarea>
					</td>
					<td><input type="button" class="btn btn-success" value="发送" onclick="f_send()"></td>
				</tr>
			</table>
			   
			</form>
		</div>
		
		
		<div id="div_fail" style="display: none;" >
			<div style="margin-bottom: 5px;text-align: left;">
            	<b>未发送成功电话列表</b>
        	</div>
			<table id="tb_fail"  class="table table-bordered table-hover" style="width: 800px;text-align: center;">
				<thead id="t_head" style="text-align: center;">
					
				</thead>
				
				<tbody id="t_body">
				
				</tbody>
			</table>
		</div>
		
	</div>

</body>
</html>