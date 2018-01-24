<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title>${fns:getConfig('productName')}-学校列表</title>
   <%@ include file="/WEB-INF/views/include/datepicker.jsp"%>
    <link rel="stylesheet" href="${ctxStatic}/plugins/date-time/css/global.css" />
    
    <meta name="decorator" content="default" />
</head>
	<body>
		<ul id="breadcrumb" style="display: none">
			<li>系统管理&nbsp;>&nbsp;账号管理</li>
		</ul>
		<!--内容区-->
		<div class="frame">
			<div class="content">
				<div class="content-area">
					<div class="ca-filter clearfix query_box">
						 <form class="form-horizontal" action="" id="queryForm" method="GET">
							 <div class="query">
								<p>
									<label>姓名</label>
									<input type="text" id="loginName" name="" value="" />
								</p>
								<p>
									<label>创建时间</label>
									<input type="text" class=" date-picker" id="startDate" name="startDate"  data-date-format="yyyy-mm-dd"/>-
									<input type="text" class=" date-picker" id="endDate" name="startDate"  data-date-format="yyyy-mm-dd"/>
								</p>
							 	<a id="queryBtn" class="search-btn"></a>
							 </div>
						</form>
					</div>
                     
                     <!-- 
                     
                      
                     <div class="top_btn">
                        <button class="bc-confirm" ><a href="${ctx}/system/user/form">新增账号</a></button>                   
                    </div>
                     -->
                     
                   	<div class="top_btn">
						<a href="${ctx}/system/user/form">
							<span >新增账号</span>
						</a>
		         	</div>
                    
					<div class="page-body">
						<table id="grid-table"></table>
						<div id="grid-pager"></div>
					</div>
				</div>
		<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
		<script src="${ctxStatic}/modules/system/user_list.js"></script>
		


			</div>
		</div>
	</body>
</html>
