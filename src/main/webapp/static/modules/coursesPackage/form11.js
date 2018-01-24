function add(){
	var params={
		packageName:$("#packageName").val(),
		originalPrice:$("#originalPrice").val(),
		summary:encodeURIComponent($("#summary").val()),
		applyCourses:GetSelectValue("#applyCourses"),
		remarks:$("#remarks").val(),
		sortNo:$("#sortNo").val(),
		//originalPrice 标准价
		price:$("#price").val(),
		isDiscount:$("#isDiscount").is(":checked")?"1":"0",
	};
	//提交问题
	layer.confirm('确认添加此销售产品吗？',function(){
        $.ajax({
          url :config.productAddUrl,
          type : "POST",
          data: params,
          success: function(data){
            if(data.success){
            	layer.alert(data.msg, {icon:1});
            	window.location.href=config.productListUrl;
            }else{
              	layer.msg(data.msg);
            }
          }
        });
	});
}

function update(){
	var params={
		id:$("#id").val(),
		packageName:$("#packageName").val(),
		originalPrice:$("#originalPrice").val(),
		summary:encodeURIComponent($("#summary").val()),
		applyCourses:GetSelectValue("#applyCourses"),
		remarks:$("#remarks").val(),
		sortNo:$("#sortNo").val(),
		//originalPrice 标准价
		price:$("#price").val(),
		isDiscount:$("#isDiscount").is(":checked")?"1":"0",
	};
	//提交问题
	layer.confirm('确认修改此销售产品吗？',function(){
        $.ajax({
          url :config.productModifyUrl,
          type : "POST",
          data: params,
          success: function(data){
            if(data.success){
            	layer.alert(data.msg, {icon:1});
            	window.location.href=config.productListUrl;
            }else{
              	layer.msg(data.msg);
            }
          }
        });
	});
}

$(function(){
	//obj.init();
});