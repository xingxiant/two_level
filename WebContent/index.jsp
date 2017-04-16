<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> ajax异步选择城市</title>
<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#countryId").change(function (){
			var country=$(this).val();     //获得选择的城市
			var type="xml";
			if(country!=undefined&&country!=null){
				//根据国家获取该国的城市列表 并设置到城市下拉框中
				$.ajax({
					url:"${pageContext.request.contextPath}/CityServlet?sendType=get&dataType=" + type,
					data:{"country":country},               //，用来设置请求的参数相当于?country=中国
					type:"get",
					dataType:type,							//返回值页面类型
					success:function(data){					//设置成功返回后的回调函数
						if("json" == type){//解析json文件
							if(data != undefined && data != null){
								//将城市列表设置到城市下拉框中
								var cities = data.cities;
								//清空城市列表
								var $citySelect = $("#cityId");
								$citySelect.empty();
								
								$.each(cities, function(i, obj){
									$citySelect.append("<option>"+ obj.city +"</option>");
								});
							} else { alert("操作失败。");}
						} else {//解析xml的文本内容
							var $xmlDocument = $(data);
						    var $cities = $xmlDocument.find("city");
						  	//清空城市列表
							var $citySelect = $("#cityId");
							$citySelect.empty();
							
						    $.each($cities, function(i, obj){//遍历所有的城市列表
						    	//设置城市下拉框中的选项
						    	$citySelect.append("<option>"+ $(obj).text() +"</option>");
						    });
						}
					}
				});
			} else {
				alert("请选择国家！");
			}
		});
	});
</script>
</head>
<body>
<div style="width:100%;text-align: center;margin-top: 30px;">
    	国家：<select id="countryId" style="width:100px;">
    		<option>请选择</option>
    		<option value="中国">中国</option>
    		<option value="美国">美国</option>
    	</select>
    	&nbsp;&nbsp;---&nbsp;&nbsp;
    	城市：<select id="cityId"></select>
    </div>

</body>
</html>