var obj = {
    tradeNo: {},
    modify: function() {
        var userBaseInfo = {
            mobile: $("#mobile").val(),
            id: $("#userId").val()
        }
        var sourceValue = '';
        if (GetSelectValue("#sourceType") == '01') {
            sourceValue = GetSelectValue("#sourceValue");
        } else if (GetSelectValue("#sourceType") == '02') {
            sourceValue = $("#sourceTypeValue").val();
        }
        var userExtendInfo = {
            userId: $("#userId").val(),
            // userName: $("#userName").val(),
            // certNo: $("#certNo").val(),
            education: GetSelectValue("#education"),
            // nation: GetSelectValue("#nation"),
            // birthplace: GetSelectValue("#birthplace"),
            // address: $("#address").val(),
            contactAddress: $("#contactAddress").val(),
            contacts: $("#contacts").val(),
            contactPhone: $("#contactPhone").val(),
            sourceType: GetSelectValue("#sourceType"),
            sourceTypeSec: GetSelectValue("#sourceTypeSec"),
            sourceValue: sourceValue,
            sourceRemarks: $("#sourceRemarks").val(),
            isHasPf: $("input[name='isHasPf']:checked").val()
        }

        var params = {
            userInfo: userBaseInfo,
            userExtend: userExtendInfo
        };

        layer.confirm('确认修改此用户信息吗？', function() {
            $.ajax({
                url: config.usersModifyUrl,
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(params),
                success: function(data) {
                    if (data.success) {
                        layer.alert(data.msg, { icon: 1 });
                        window.location.href = config.usersListUrl;
                    } else {
                        // alert(2222);
                        layer.msg(data.msg);
                    }
                }
            });
        });

    },
	goBack:function(){
        layer.confirm('确认添加此订单信息吗？', function() {});
		window.location.href = config.usersListUrl;	
	}

,
    payMent: function() {
        var payPublic = getPayUrl();
        var type = getCheckData("input[name='payment']", "value");
        var params = {
            userId: $("#baseUserId").val(),
            courseId: GetSelectValue("#course"),
            type: getCheckData("input[name='payment']", "value")
        };
        console.log(params);
        $.ajax({
            url: payPublic + "/prepay",
            type: "get",
            data: params,
            success: function(data) {
                if (data.success) {
                    $("#layer_s,.pay_orcode").show();
                    $(".orcode img").attr("src", data.jsonData.codeImgUrl);
                    if (type == "01") { //支付宝
                        $("#type").text("支付宝");
                    } else if (type == "02") {
                        $("#type").text("微信");
                    }
                    obj.tradeNo = data.jsonData.tradeNo;
                    window.setInterval(() => {
                        obj.checkpay();
                    }, 1000);

                } else {
                    layer.msg(data.msg);
                }
            }
        });
        // });

    },
    init: function() {
        $("#modifyBtn").bind("click", function() {
            obj.modify();
        });
        $("#payMentBtn").bind("click", function() {
            obj.payMent();
        });
        
        $("#goBack").bind("click", function() {
            obj.goBack();
        });
    },
    checkpay: function() {
        var payPublic = getPayUrl();
        var tradeNo = obj.tradeNo;
        var params = {
            tradeNo: tradeNo
        };
        $.ajax({
            url: payPublic + "/status",
            type: "get",
            data: params,
            dataType: "json",
            success: function success(data) {
                if (data.success) {
                    if (data.jsonData.status == "SUCCESS") {
                        layer.msg("支付成功");
                        window.location.href = config.usersListUrl;
                    } else {
                        // layer.msg(data.jsonData.msg);
                    }
                } else {
                    console.log(data.msg);
                }
            }
        })
    }

}

$(function() {
    obj.init();
});