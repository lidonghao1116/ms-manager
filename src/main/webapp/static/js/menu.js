//系统菜单js
var menuJson=null;
var menuMap=new Map();
var pathurl=window.location.href;
var pid01=pathurl.split("=")[1]
var index=pid01-2;
console.log(index)



var menu={

	/**
	 * 初始化顶部菜单
	 */
	
	initFirst:function(data){
		console.log(data);
		//查看是否有系统权限
		  var m=0;
	      $.ajax({
	          url: config.sysUserRoleQueryUrl,
	          type: "POST",
	          async:false,
	          contentType: "application/json",
	          success: function (data) {
	        	  for(var i=0;i<data.length;i++)
	               {
	        		if(data[i]=="sys")
	        		{m++;}      		
	        	   }
	          }
	        });
		$.each(data, function (key, value) {
			   
				menuMap.put(value.id,value);
				if(m==0&&value.id==4)
					{}
				else{
					$("#first_menus ul").append(
							div("li","").attr("id",value.id).append(
								div("a","").html(value.text)
							).append(
								div("img","").attr("src","/jiacerconsole/static/loginImg/selected.png")
							)
						)
				}
	
				
			
			
		});

		//执行绑定方法
		$("#first_menus ul li").bind("click",function(){
			var pid=$(this).attr("id");
			console.log("pid"+pid)
			$("#mainFrame").html("");
			menu.initSecond(pid);
			$(this).addClass("active").siblings().removeClass("active");
			$("#sidebar h1").html($(this).children("a").text());
		})

    if (index!=null&&!isNaN(index)){
      $("#first_menus ul li:eq('"+index+"')").trigger("click");
      menu.initSecond($("#first_menus ul li:eq('"+index+"')").attr("id"));
      $("#first_menus ul li:eq('"+index+"')").addClass("active");
      $("#sidebar h1").html($("#first_menus ul li:eq('"+index+"') a").text());
    }else {
      menu.initSecond($("#first_menus ul li:eq(0)").attr("id"));
      $("#first_menus ul li:eq(0)").addClass("active");
      $("#sidebar h1").html($("#first_menus ul li:eq(0) a").text());
		}
	}
	,
    initJsonData:function(){
      $.ajaxSettings.async = false;
    	$.getJSON(ctxStatic+"/js/menu.json",function(data){
    		menu.initFirst(data);
    	});
      $.ajaxSettings.async = true;
    }
	,
	/**
	 * 初始化菜单
	 */
	initSecond:function(pid){
		var data=menuMap.get(pid);
		$('#menu').html("");
		$('#menu').sidebarMenu({
			data: data.menus
  		});

	}
};
