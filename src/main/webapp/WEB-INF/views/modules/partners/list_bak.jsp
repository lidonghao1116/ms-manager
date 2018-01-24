<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title>${fns:getConfig('productName')}-合作商列表</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">主页</a></li>
		<li class="active">合作商列表</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li class="active"><a href="#">合作商列表</a></li>
		<li><a href="${ctx}/partners/form?id=">合作商新增</a></li>
	</ul>
	<div class="searchbar"> 
        <form class="form-horizontal" action="" id="queryForm" method="GET">
            <div class="form-group">
            	<label class="col-md-2 control-label">合作商名称</label>
	            <div class="col-md-2">
	            	<input type="text" class="form-control" id="partnerName" name="partnerName" value="" />
	            </div>
	            <label class="col-md-2 control-label">合作类型</label>
	            <div class="col-md-2">
	            	<select class="chosen-select form-control" id="partnerType">
						<option value="">--请选择--</option>
						<c:forEach items="${fns:getParams(5,28)}" var="obj" varStatus="item">
	            			<option value="${obj.value}"  <c:if test="${model.partnerType==obj.value}">selected="selected" </c:if>>${obj.text}</option>
	            		</c:forEach>
					</select>
	            </div>
           	</div>
           	<div class="form-group">
            	<label class="col-md-2 control-label">合作商电话</label>
	            <div class="col-md-2">
	            	<input type="text" class="form-control" id="contactPhone" name="contactPhone" value="" />
	            </div>
           	</div>
       		<div class="form-group">
            	<label class="col-md-2 control-label">区域</label>
				<div class="col-sm-2">
					<select class="chosen-select form-control" id="province" >
					</select>
				</div>
				<div class="col-sm-2">
					<select class="chosen-select form-control" id="city">
					</select>
				</div>
				<div class="col-sm-2">
					<select class="chosen-select form-control" id="county">
					</select>
				</div>
           	</div>
        </form>
    </div>
    <div class="page-content">
    	<div class="page-heading mb5">
        	<div class="text-left small">
	        	<button class="btn btn-sm btn-primary" type="button" id="queryBtn"><span class="fa fa-search"></span> 查询</button>
	    		<label class="col-md-4 controls text-right" style="float: right;margin-top:16px;">合作商家信息：<a style="cursor: pointer;" id="exportBtn">EXCEL下载</a></label>
	    	</div>
	    </div>
		<div class="page-body">
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
		</div>
	</div>
	<script src="${ctxStatic}/modules/partners/list.js"></script>
	<script type="text/javascript">
		var item=["#province","#city","#county"];
		var itemValue=["","",""];
		var defaultOption = '<option value="">---请选择---</option>';
		var flag=true;
		
		function getIndex($item){
			for(var i=0;i<item.length;i++){
				if(item[i]==$item){
					return i;
				}
			}
			return 0;
		}
		$(function(){
			  $("#exportBtn").bind("click",function(){
				  $("#queryForm").attr("action",ctx+"/partners/export/01");
					$("#queryForm").submit();
			}); 
			
		});
		function createArearSelect(areaData){
			resetSelect(item[0]);
	    	initSelectData(areaData,item[0],itemValue[0]);
	    	bindChange(item[0],areaData);
		};
	
		function bindChange($item,areaData){
			var index=getIndex($item)+1;
			if(index==item.length){
				flag=false;
		   	}else{
		   		flag=true;
		   	}
			
			if(!flag){
				return;
			}
			var selectGist1 = $(item[index-1]);
			selectGist1.bind('change',function(){
				var $this=$(this).val();
				resetSelect("#"+$(this).attr("id"));
				getDataInfo(areaData,$this,itemValue[index-1],index)
			});
		}
		//获取精确的数据根据arercode
		function getDataInfo(areaData,areaCode,selectValue,index){
			var selectGist2 = $(item[index]);
			$.each(areaData,function(i,param){
	            if(param.areaCode == areaCode){
	                initSelectData(param.childList,selectGist2,selectValue);
	                bindChange(item[index],param.childList);
	                return false;
	            }
	        });
		}
		
		function resetSelect($item){
			for(var i=0;i<item.length;i++){
				if($item==item[i]){
					for(var j=i+1;j<item.length;j++){
						$(item[j]).html("")
						$(item[j]).append(defaultOption);
					}
					 return false;
				}
			}
		};
		
		function initSelectData(areaData,$item,value){
			var selectGist = $($item);
			selectGist.html('');
			selectGist.append(defaultOption);
			if(areaData){
	      		$.each(areaData,function(i,obj){
	      			if(value==obj.areaCode){
	      				$('<option value="'+obj.areaCode+'">'+obj.areaName+'</option> selected="selected"').appendTo(selectGist);
	      			}else{
	      				$('<option value="'+obj.areaCode+'">'+obj.areaName+'</option>').appendTo(selectGist);
	      			}
		      	});
		    }
		}
	  
		function getArearSelect(pCode,item_1,item_2,item_next){
	 		$.post(ctx+'/system/areas/init', {}, function(data){
		  		createArearSelect(data);
	 		});
		};
		
		$(function(){
			getArearSelect();
		});
	</script>
</body>
</html>