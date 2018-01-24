//系统菜单js
var menuJson=null;
var menuMap=new Map();
var path=window.location.href;
console.log(path)
var pid01=path.split("=")[1]
var index=pid01-2;
console.log(pid01)
console.log(index)

var menu={

	/**
	 * 初始化顶级菜单
	 */
	initFirst:function(data){
		console.log(data)
		$.each(data, function (key, value) {
			menuMap.put(value.id,value),
			$("#first_menus ul").append(
				div("li","").attr("id",value.id).append(
					div("a","").html(value.text)
				).append(
					//div("img","").attr("src","http://192.168.31.233:8080/jiacerconsole/static/loginImg/selected.png")
					div("img","").attr("src","/jiacerconsole/static/loginImg/selected.png")
				)
			)
        });

		//执行绑定方法
		$("#first_menus ul li").bind("click",function(){
			var pid=$(this).attr("id");
			console.log("pid"+pid)
			menu.initSecond(pid);
			$(this).addClass("active").siblings().removeClass("active");
			$("#sidebar h1").html($(this).children("a").text());
		})

/*    if (index!=null){
      $("#first_menus ul li:eq('"+index+"')").trigger("click");
      menu.initSecond($("#first_menus ul li:eq('"+index+"')").attr("id"));
      $("#first_menus ul li:eq('"+index+"')").addClass("active");
      $("#sidebar h1").html($("#first_menus ul li:eq('"+index+"') a").text());
    }else {*/
      menu.initSecond($("#first_menus ul li:eq(0)").attr("id"));
      $("#first_menus ul li:eq(0)").addClass("active");
      $("#sidebar h1").html($("#first_menus ul li:eq(0) a").text());
		// }
	}
	,
    initJsonData:function(){
    	$.getJSON(ctxStatic+"/js/menu.json",function(data){
    		menu.initFirst(data);
    	});
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
