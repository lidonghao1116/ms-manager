/**
 * 左侧菜单初始化方法
 */
(function ($) {
  $.fn.sidebarMenu = function (options) {
    options = $.extend({}, $.fn.sidebarMenu.defaults, options || {});
    var target = $(this);
    target.addClass('nav');
    target.addClass('nav-list');
    if (options.data) {
      init(target, options.data);
    }
    else {
      if (!options.url) return;
      $.getJSON(options.url, options.param, function (data) {
        init(target, data);
      });
    }
    var url = window.location.pathname;
    //menu = target.find("[href='" + url + "']");
    //menu.parent().addClass('active');
    //menu.parent().parentsUntil('.nav-list', 'li').addClass('active').addClass('open');
    function init(target, data) {
    	 var isShowSysMenu=0; 
	        $.ajax({
		          url :config.getIsShowSysMenuUrl,
		          type : "get",
		          data: {constKey: "IS_SYS_MENU_VIEW"},
		          async: false,
		          success: function(data){
		        	  isShowSysMenu=data;
		          }
		        });
        
      $.each(data, function (i, item) {
        var li = $('<li></li>');
        var a = $('<a></a>');
        var icon = $('<i></i>');
        //icon.addClass('glyphicon');
        icon.addClass(item.icon);
        var text = $('<span></span>');
        text.addClass('menu-text').text(item.text);
        a.append(icon);
        a.append(text);      
       console.log("isShowSysMenu:"+isShowSysMenu);
       
        if (item.menus&&item.menus.length>0) {
        	if(isShowSysMenu == 0 && item.id==41)
        	{
        		console.log("===41");
        	}else{
                a.attr('href', '#');
                a.addClass('dropdown-toggle');
                var arrow = $('<b></b>');
                arrow.addClass('arrow').addClass("fa fa-angle-down");
                a.append(arrow);
                li.append(a);
                var arrow = $('<b></b>');
                arrow.addClass('arrow');
                li.append(arrow);
                var menus = $('<ul></ul>');
                menus.addClass('submenu');
                init(menus, item.menus);
                li.append(menus);
              
        	}
        	
        }else {
        	
        	if(isShowSysMenu == 0 && (item.id==422||item.id==423))
        	{
        		console.log(item.id);
        	}else{

                //var href = 'javascript:addTabs({id:\'' + item.id + '\',title: \'' + item.text + '\',close: true,url: \'' + item.url + '\'});';
                var href=ctx+item.url;
                a.attr('href', href);
                if(!isEmpty(item.target)){
              	  a.attr('target', item.target);
                }
                //if (item.istab)
                //  a.attr('href', href);
                //else {
                //  a.attr('href', item.url);
                //  a.attr('title', item.text);
                //  a.attr('target', '_blank')
                //}
                li.append(a);
              
        	}
        }
       
        target.append(li);
      });
    }
  }
 
  $.fn.sidebarMenu.defaults = {
    url: null,
    param: null,
    data: null
  };
})(jQuery);