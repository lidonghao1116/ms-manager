var obj={
	//保存用户数据 
	save:function(){
		var jw_edit=$("#jwgl_edit").is(':checked');
		var jw_export=$("#jwgl_export").is(':checked');
		var yy_edit=$("#yygl_edit").is(':checked');
		var yy_export=$("#yygl_export").is(':checked');
		var ids='';
		if(jw_edit)
			{ids+='1,';}
		if(jw_export)
			{ids+='2,';}
		if(yy_edit)
			{ids+='3,';}
		if(yy_export)
			{ids+='4,';}
		var params={};
		params.loginName=$("#loginName").val();
		params.loginAccount=$("#loginAccount").val();
		//params.email=$("#email").val();
		params.telephone=$("#telephone").val();
		params.loginFlag=$("#loginFlag").val();
		//params.remarks=$("#remarks").val();
		params.ids=ids;
		var result=obj.validParam(params);
		if(!result){
			return;
		}
		
		layer.confirm('确认保存此用户吗？',function(){
	        $.ajax({
	          url :config.sysUserAddUrl,
	          type : "POST",
	          data: params,
	          async: false,
	          success: function(data){
	            if(data.success){
	              var msg="账号【"+params.loginAccount+"】已创建，初始密码为000000";
	              layer.msg(msg, {
	            	      time: 5000, //2s后自动关闭
	            	      icon: 1
	            	   } 
	            		  );
	            //{icon:1}  
	              window.location.href=config.sysUserListUrl;
	            }else{
	              layer.msg(data.msg);
	            }
	          }
	        });
  		});
	}
	,
	//修改用户信息
	modify:function(){
		var jw_edit=$("#jwgl_edit").is(':checked');
		var jw_export=$("#jwgl_export").is(':checked');
		var yy_edit=$("#yygl_edit").is(':checked');
		var yy_export=$("#yygl_export").is(':checked');
		var ids='';
		if(jw_edit)
			{ids+='1,';}
		if(jw_export)
			{ids+='2,';}
		if(yy_edit)
			{ids+='3,';}
		if(yy_export)
			{ids+='4,';}
		//layer.msg(ids);

		var params={};
		params.id=$("#id").val();
		params.loginName=$("#loginName").val();
		params.loginAccount=$("#loginAccount").val();
		params.email=$("#email").val();
		params.telephone=$("#telephone").val();
		params.loginFlag=$("#loginFlag").val();
		params.remarks=$("#remarks").val();
		params.ids=ids;
		var result=obj.validParam(params);
		if(!result){
			return;
		}

		layer.confirm('确认修改此用户吗？',function(){
	        $.ajax({
	          url :config.sysUserModifyUrl,
	          type : "POST",
	          data: params,
	          async: false,
	          success: function(data){
	            if(data.success){
	              layer.msg(data.msg, {icon:1});
	              window.location.href=config.sysUserListUrl;
	            }else{
	              layer.msg(data.msg);
	            }
	          }
	        });
  		});
	},
     	//修改用户信息
     	resetPwd:function(){
     		var params={};
     		params.id=$("#id").val();

     		$.ajax({
              url :config.sysUserResetPwdUrl,
              type : "POST",
              data: params,
              async: false,
              success: function(data){
                if(data.success){
                    $("#resetPwdTips").html("密码已重置，默认密码000000");
                }else{
                  $("#resetPwdTips").html("密码重置失败，请重试");
                }
              }
            });

     	}
	,
	//校验参数
	validParam:function(params){
		//email=new RegExp("^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$");
		if(isEmpty(params.loginName)){
			layer.msg("请填写[用户名]");
    		return false;
		}
		
		if(isEmpty(params.loginAccount)){
			layer.msg("请填写[用户登陆账号]");
    		return false;
		}
		/*
		 var reg_email=email.test(params.email);
		if(!reg_email){
			layer.msg("请填写正确的[邮箱]");
    		return false;
		}
		 */
		
		
		if(isEmpty(params.telephone)){
			layer.msg("请填写[手机号]");
    		return false;
		}
		var reg_phone=/^1[3|4|5|7|8]\d{9}$/.test(params.telephone);
		if(!reg_phone){
			layer.msg("请填写正确的[手机号]");
    		return false;
		}
		
		return true;
	}
	,
	init:function(){
		//初始化绑定事件
		$("#addBtn").bind("click",function(){
			obj.save();
		});
		
		$("#modifyBtn").bind("click",function(){
			obj.modify();			
		});

		$("#btnResetPwd").bind("click",function(){
            obj.resetPwd();
        });

	}

	
}

$(function(){
	initBreadcrumb();
	obj.init();
});