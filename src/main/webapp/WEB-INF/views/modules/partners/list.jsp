<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<html>
<head>
<title>${fns:getConfig('productName')}-合作商列表</title>
<meta name="decorator" content="default" />
<link rel="stylesheet" href="${ctxStatic}/plugins/date-time/css/global.css" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li>运营管理&nbsp;>&nbsp;合作商管理&nbsp;>&nbsp;合作商列表</li>
	</ul>
	<div class="frame">
			<div class="content">
				<div class="content-area">
					<div class="ca-filter clearfix query_box">
						 <form class="form-horizontal" action="" id="queryForm" method="GET">
							 <div class="query">
								<p>
									<label>合作商名称</label>
		              				<input type="text" id="partnerName" name="" value="">
								</p>
								<p>
									<label>合作类型</label>
									<select name="" id="partnerType">
										<option value="">--请选择--</option>
										<c:forEach items="${fns:getParams(5,28)}" var="obj" varStatus="item">
					            			<option value="${obj.value}"  <c:if test="${model.partnerType==obj.value}">selected="selected" </c:if>>${obj.text}</option>
					            		</c:forEach>
									</select>
								</p>
								<p>
									<label>合作商电话</label>
									<input type="text" id="contactPhone" name="" value="">
								</p>
								<p>
									<label>区域</label>
									<select class="" id="province" name="">
									</select>
									<select class="" id="city" name="">
									</select>
									<select class="" id="county" name="">
									</select>
								</p>
							 	<a id="queryBtn" class="search-btn"></a>
							</div>
						</form>
					</div>
					<div class="top_btn">      
                       <c:if test="${fns:isHasRole('yygl_edit')}" >
					     <a href="${ctx}/partners/form"><span id="add_class">+新增合作商</span></a>
					  </c:if>
					  <c:if test="${fns:isHasRole('yygl_export')}" >
					     <a id="exportBtn"><span id="add_class">EXCEL下载</span></a>
					  </c:if>
						
					</div>
					<div class="page-body">
						<table id="grid-table"></table>
						<div id="grid-pager"></div>
					</div>
				</div>
				<script src="${ctxStatic}/modules/partners/list.js"></script>
			</div>
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
