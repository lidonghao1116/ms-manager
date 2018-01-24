<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title>${fns:getConfig('productName')}-学校简介</title>
<%@ include file="/WEB-INF/views/include/datepicker.jsp"%>
<link rel="stylesheet" href="${ctxStatic}/plugins/date-time/css/global.css" />
<meta name="decorator" content="default" />
</head>
	<body>
		<ul id="breadcrumb" style="display: none">
			<li>运营管理&nbsp;>&nbsp;销售管理&nbsp;>&nbsp;学校简介</li>
		</ul>
		<!--内容区-->
		 <!-- <form class="form-horizontal"> -->
            <!--内容区-->
            <div class="frame">
                <div class="content">
                    <div class="content-area">
                        <div class="infoBlock school_info">
                            <h1 class="title">学校简介   
						  <c:if test="${fns:isHasRole('yygl_edit')}" >
							  <span class="edit_sp">编辑</span>
						  </c:if>	
                            </h1>
                            <div class="ib-content clearfix">
                                <table style="width:100%">
                                    <tr>
                                        <td>学校名称：</td>
                                        <td class="secTd">${institution.schoolName}<input type="hidden" id="id" name="" value="${institution.id}"></td>
                                    </tr>
                                    <tr>
                                        <td>联系电话：</td>
                                        <td class="secTd">
                                            <span class="beforeEdit">${institution.schoolPhone}</span>
                                            <span class="afterEdit">
                                                <input type="text" id="schoolPhone" name="" value="${institution.schoolPhone}">
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>联系人：</td>
                                        <td class="secTd">
                                            <span class="beforeEdit">${institution.contacts}</span>
                                            <span class="afterEdit">
                                                <input type="text" id="contacts" name="" value="${institution.contacts}">                                                   
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>学校地址：</td>
                                        <td class="secTd">
                                            <span class="beforeEdit">${institution.privinceValue}${institution.cityValue}${institution.areaValue}${institution.schoolAddress}</span>
                                            <span class="afterEdit">
                                                <select id="province" name="province" ></select>
                                                <select id="city" name="city" ></select>
                                                <select id="area" name="area" ></select> <br />
                                                <input type="text" id="schoolAddress" name="schoolAddress" style="width:500px" value="${institution.schoolAddress}">
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>LOGO：</td>
                                        <td class="secTd">
                                        <span class="beforeEdit"><img src="${institution.logoUrl == null || institution.logoUrl== ''? '/jiacerconsole/static/loginImg/peixun.png':institution.logoUrl }"/></span>
                                        <span class="afterEdit">
                                                <img src="${institution.logoUrl == null || institution.logoUrl== ''? '/jiacerconsole/static/loginImg/peixun.png':institution.logoUrl }" class="logoUrl" />
                                                <input type="hidden" id="logoimg" value="${institution.logoUrl == null || institution.logoUrl== ''? '/jiacerconsole/static/loginImg/peixun.png' : institution.logoUrl }">
                                                <form enctype="multipart/form-data" id="logoUrl">
                                                    <div class="upload afterEdit">
                                                        <h2>选择上传<input type="file" name="file" accept="image/gif,image/png,image/bmp,image/jpeg" value="${institution.logoUrl}"></h2>
                                                        <p>大小2M以内</p>
                                                    </div>
                                                </form>
                                            </span>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <div class="btnCase clearfix afterEdit">
                            <button class="sp-cancel">取消</button>
                            <button class="bc-confirm" id="modifyBtn" type="button">保存</button>
                        </div>
                    </div>
                </div>
            </div>
        <!-- </form> -->

        <input id="o_province" value="${institution.privince}" type="hidden"/>
        <input id="o_city" value="${institution.city}" type="hidden"/>
        <input id="o_area" value="${institution.area}" type="hidden"/>

        <script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="${ctxStatic}/js/jquery.form.js" type="text/javascript" charset="utf-8"></script>
        <script src="js/index.js" type="text/javascript" charset="utf-8"></script>
        <script src="${ctxStatic}/modules/schoolIntro/form.js"></script>
        <script type="text/javascript">

                    function getObjectURL(file) {
                        var url = null ;
                        if (window.createObjectURL!=undefined) { // basic
                            url = window.createObjectURL(file) ;
                        } else if (window.URL!=undefined) { // mozilla(firefox)
                            url = window.URL.createObjectURL(file) ;
                        } else if (window.webkitURL!=undefined) { // webkit or chrome
                            url = window.webkitURL.createObjectURL(file) ;
                        }
                        return url ;
                    }
            $(function(){
                $(".edit_sp").click(function(){
                    $(".beforeEdit").hide();
                    $(".afterEdit").show();
                    $("span.beforeEdit").each(function(){
                         //$(this).siblings(".afterEdit").children("input").val($(this).text());
                    })
                })
                $(".sp-cancel").click(function(){
                    event.preventDefault();
                    $(".beforeEdit").show();
                    $(".afterEdit").hide();
                })
                $(".upload").on("change","input[type=file]",function(){
                    var $this = $(this);
                    var objUrl = getObjectURL(this.files[0]);
                    var fileSize = this.files[0].size;
                    var size = fileSize/1024;
                    var filepath = $(this).val();
                    var extStart = filepath.lastIndexOf(".");
                    var ext = filepath.substring(extStart, filepath.length).toUpperCase();

                    if (ext != ".BMP" && ext != ".PNG" && ext != ".JPG" && ext != ".JPEG") {
                        alert("图片限于bmp,png,jpg,jpeg格式");
                        $(this).val("");
                        return;
                    };
                    if (size > 2048){
                        alert("上传的图片大小不能超过2M！");
                        $(this).val("");
                        return;
                    };
                   $("#logoUrl").ajaxSubmit({
                    url :config.imgUploadUrl,
                    type : "POST",
                    data: {},
                    success: function success(data) {
                        if(data.message){
                            if(data.status == 0){
                                var logoUrl=data.result.url;
                                $(".logoUrl").attr("src",logoUrl);
                                $("#logoimg").val(logoUrl); 
                            }else{
                                console.log(data.msg);
                            }
                        }else{
                            console.log(data.msg);
                        }
                    }
                    });
                })
            })
        </script>

        <script type="text/javascript">
        		var item=["#province","#city","#area"];
        		var itemValue=[$("#o_province").val(),$("#o_city").val(),$("#o_area").val()];
        		var defaultOption = '<option value="">---请选择---</option>';

        		function getIndex($item){
        			for(var i=0;i<item.length;i++){
        				if(item[i]==$item){
        					return i;
        				}
        			}
        			return 0;
        		}

        		function createArearSelect(areaData){
        			resetSelect(item[0]);
        	    	initSelectData(areaData,item[0],itemValue[0]);
        	    		$.each(areaData,function(i,param){
        		            if(param.areaCode == itemValue[0]){
        		                initSelectData(param.childList,$(item[1]),itemValue[1]);
        		                bindChange(item[1],param.childList);
        		                $.each(param.childList,function(i,obj){
        				            if(obj.areaCode == itemValue[1]){
        				                initSelectData(obj.childList,$(item[2]),itemValue[2]);
        				            }
        				        });
        		            }
        		        });
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
        	      				$('<option value="'+obj.areaCode+'" selected="selected">'+obj.areaName+'</option>').appendTo(selectGist);
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

        		$(function(){getArearSelect();});
        </script>
	</body>
</html>
