//公用js方法和扩展属性方法
/**
 * 全局Ajax操作默认设置处理，beforeSend、error、success，如有其它请在此处添加
 */
var _index;
$.ajaxSetup({
	global : true,
	cache : false,
	beforeSend : function(XMLHttpRequest) {
		XMLHttpRequest.setRequestHeader("X-AjaxRequest", "1");
		//滚动条
		_index=layer.load(0,2);
	},
	error : function(XMLHttpRequest, textStatus, errorThrown) {
		if (XMLHttpRequest.status == '403') {
			layer.alert("您没权限访问",8);
		} else if (XMLHttpRequest.status == '500') {
			layer.alert('服务器端发生错误!',8);
		} else if (XMLHttpRequest.status == '911'){ 
			layer.alert('session超时，请重新登录!',8,function(){
				
				  //top.location =ctx;
				top.location.href =ctx;
			});
		} else {
			layer.alert('操作失败,请重新操作!',8);
		
		}
		layer.close(_index);
	},
	success : function(data) {
		//ajax请求成功
		layer.close(_index);
	},
	complete: function(XMLHttpRequest) {
		if (XMLHttpRequest.status == "911") {
			 top.location.href=ctx;
			//top.location=ctx;
		}
		layer.close(_index);
	}
});

String.prototype.trim=function() {
    return this.replace(/(^\s*)|(\s*$)/g, "");
};

//判断是否为空 
function isEmpty(param){
	if($.trim(param)!="" && $.trim(param)!=null){
		return false;
	}else{
		return true;
	}
};

function DateDiff(sDate1, sDate2) { //sDate1和sDate2是yyyy-MM-dd格式
	var aDate, oDate1, oDate2, iDays;
	aDate = sDate1.split("-");
	oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]); //转换为yyyy-MM-dd格式
	aDate = sDate2.split(" ")[0].split("-");
	oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);
	iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24); //把相差的毫秒数转换为天数
	return iDays; //返回相差天数
}

/**
 * 获取选中的数据
 */
function getCheckData(obj,attribute){
    var checkList=$(obj);
    var ids="";
    checkList.each(function(item){
        if(this.checked){
        	ids+=this.getAttribute(attribute)+",";
        };
    });
    ids=ids.substr(0,ids.length-1);
    return ids;
}

function initReload(){
	if (window != top) 
		top.location.href = location.href; 
}

/**
 * 自适应iframe高度
 */
function iFrameHeight() {
    var ifm=document.getElementById("mainFrame");
    var subWeb = document.frames ? document.frames["mainFrame"].top.document :ifm.contentDocument;
    if(ifm != null && subWeb != null) {
    	ifm.height =   window.screen.height-270;
    }
}

//引入js和css文件
function include(id, path, file){
	if (document.getElementById(id)==null){
        var files = typeof file == "string" ? [file] : file;
        for (var i = 0; i < files.length; i++){
            var name = files[i].replace(/^\s|\s$/g, "");
            var att = name.split('.');
            var ext = att[att.length - 1].toLowerCase();
            var isCSS = ext == "css";
            var tag = isCSS ? "link" : "script";
            var attr = isCSS ? " type='text/css' rel='stylesheet' " : " type='text/javascript' ";
            var link = (isCSS ? "href" : "src") + "='" + path + name + "'";
            document.write("<" + tag + (i==0?" id="+id:"") + attr + link + "></" + tag + ">");
        }
	}
}

// 获取URL地址参数
function getQueryString(name, url) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    if (!url || url == ""){
	    url = window.location.search;
    }else{	
    	url = url.substring(url.indexOf("?"));
    }
    r = url.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}
// cookie操作
function cookie(name, value, options) {
    if (typeof value != 'undefined') { // name and value given, set cookie
        options = options || {};
        if (value === null) {
            value = '';
            options.expires = -1;
        }
        var expires = '';
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
            var date;
            if (typeof options.expires == 'number') {
                date = new Date();
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
            } else {
                date = options.expires;
            }
            expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE
        }
        var path = options.path ? '; path=' + options.path : '';
        var domain = options.domain ? '; domain=' + options.domain : '';
        var secure = options.secure ? '; secure' : '';
        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
    } else { // only name given, get cookie
        var cookieValue = null;
        if (document.cookie && document.cookie != '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = jQuery.trim(cookies[i]);
                // Does this cookie string begin with the name we want?
                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                    break;
                }
            }
        }
        return cookieValue;
    }
}

// 数值前补零
function pad(num, n) {
    var len = num.toString().length;
    while(len < n) {
        num = "0" + num;
        len++;
    }
    return num;
}

// 转换为日期
function strToDate(date){
	return new Date(date.replace(/-/g,"/"));
}

// 日期加减
function addDate(date, dadd){  
	date = date.valueOf();
	date = date + dadd * 24 * 60 * 60 * 1000;
	return new Date(date);  
}

//截取字符串，区别汉字和英文
function abbr(name, maxLength){  
	if(!maxLength){  
		maxLength = 20;  
	}  
	if(name==null||name.length<1){  
		return "";  
	}  
	var w = 0;//字符串长度，一个汉字长度为2   
	var s = 0;//汉字个数   
	var p = false;//判断字符串当前循环的前一个字符是否为汉字   
	var b = false;//判断字符串当前循环的字符是否为汉字   
	var nameSub;  
	for (var i=0; i<name.length; i++) {  
		if(i>1 && b==false){  
			p = false;  
		}  
		if(i>1 && b==true){  
			p = true;  
		}  
		var c = name.charCodeAt(i);  
		//单字节加1   
		if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) {  
			w++;  
			b = false;  
		}else {  
			w+=2;  
			s++;  
			b = true;  
		}  
		if(w>maxLength && i<=name.length-1){  
			if(b==true && p==true){  
				nameSub = name.substring(0,i-2)+"...";  
			}  
			if(b==false && p==false){  
				nameSub = name.substring(0,i-3)+"...";  
			}  
			if(b==true && p==false){  
				nameSub = name.substring(0,i-2)+"...";  
			}  
			if(p==true){  
				nameSub = name.substring(0,i-2)+"...";  
			}  
			break;  
		}  
	}  
	if(w<=maxLength){  
		return name;  
	}  
	return nameSub;  
}

function updatePagerIcons(table) {
	var replacement = 
	{
		'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
		'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
		'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
		'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
	};
	$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
		var icon = $(this);
		var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
		
		if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
	});
}	

function initBreadcrumb(){
	$(".breadcrumb",window.top.document).html($("#breadcrumb").html());
}

var div = function (e, classN) {
    return $(document.createElement(e)).addClass(classN);
};

function GetSelectText(obj){
	var txt=$(obj).find("option:selected").text();
	return txt;
}
function GetSelectValue(obj){
	var val=$(obj).find("option:selected").val();
	return val;
}

/**
 * 初始化checkbox选中数据
 * @returns
 */
function initCheckData(boxs,tagName){
	var boxs=isEmpty(boxs)?"":boxs;
	var checkedBoxs=boxs.split(",");
	var boxValues =document.getElementsByName(tagName); 
	for(var M=0;M<checkedBoxs.length;M++){ 
	   	for(var N=0;N<boxValues.length;N++){ 
			if(boxValues[N].value==checkedBoxs[M]){ 
				boxValues[N].checked = true; 
			} 
		};
	}
}

/**
 *格式化时间
 */
function formatDate(cellvalue, options, rawObject){
	return !isEmpty(cellvalue)?dateFormat(new Date(cellvalue),'yyyy-MM-dd hh:mm'):"";
}

function trueFalse(cellvalue, options, rawObject){
	return (cellvalue=="1")?"是":"否";
}
