# 学校管理系统 接口文档
/*2018-01-03 randeyi*/
update user_base_info set gender=case cast(substring(cert_no,17,1) as UNSIGNED)%2
when 1 then '1'
when 0 then '0'
end where length(cert_no)=18

update user_extend_info set sex=case cast(substring(cert_no,17,1) as UNSIGNED)%2
when 1 then '1'
when 0 then '0'
end where length(cert_no)=18

update stu_user_info set sex=case cast(substring(cert_no,17,1) as UNSIGNED)%2
when 1 then '1'
when 0 then '0'
end where length(cert_no)=18

ALTER TABLE `exam_class`
ADD COLUMN `remark` VARCHAR(1000) NULL DEFAULT NULL COMMENT '备注' AFTER `shool_id`;

update apply_orders tao set tao.handler = (
 select tsu.id from sys_users tsu where tsu.login_account = tao.handler and tsu.is_usable = 1
) where tao.handler in (select su.login_account from sys_users su group by su.login_account);

update apply_orders ao set ao.handler = 3 where ao.handler = 'lcming';

update apply_orders ao set ao.handler = 7 where ao.handler = 'qianjing';

update apply_orders ao set ao.handler = 4 where ao.handler = 'yhfang';

update apply_orders ao set ao.handler = 11 where ao.handler = 'zheng';

update apply_orders ao set ao.handler = 6 where ao.handler = 'zhouwj';

update apply_orders ao set ao.handler = 5 where ao.handler = 'zhping';

ALTER TABLE `jiacerdb`.`apply_orders` 
CHANGE COLUMN `handle_time` `handle_time` DATETIME NULL DEFAULT NULL COMMENT '审核时间' ,
CHANGE COLUMN `handler` `handler` INT(11) NULL DEFAULT NULL COMMENT '审核人id' ,
CHANGE COLUMN `modify_time` `modify_time` DATETIME NULL DEFAULT NULL COMMENT '处理时间' ,
ADD COLUMN `modify_account` INT(11) NULL DEFAULT NULL COMMENT '处理人id' AFTER `modify_time`;

ALTER TABLE `jiacerdb`.`apply_orders` 
ADD COLUMN `staging_clear_time` DATETIME NULL DEFAULT NULL COMMENT '分期首付结清时间' AFTER `is_deposit_clear`,
ADD COLUMN `staging_clear_account` INT(11) NULL DEFAULT NULL COMMENT '分期首付结清账号' AFTER `staging_clear_time`,
ADD COLUMN `deposit_clear_time` DATETIME NULL DEFAULT NULL COMMENT '押金费退还时间' AFTER `staging_clear_account`,
ADD COLUMN `deposit_clear_account` INT(11) NULL DEFAULT NULL COMMENT '押金费退还账号' AFTER `deposit_clear_time`;




# 学校管理系统课程和学校简介接口文档
## 本文档仅适用于以下接口，其余未涉及接口均为上一个版本接口文档
### 一、课程管理--首页页面
### 请求路径：/courses/list
### 请求方式：get/post
### 请求参数：无
### 响应案例：进入课程列表页面，页面地址和名称modules/courses/list

### 二、课程管理--课程名称
### 请求路径：/params/courses
### 请求方式：get/post
### 请求参数：无
### 响应案例：
                [
    {
        "id": 13,
        "typeName": "育婴员",
        "isUsable": "1",
        "addTime": 1502246343000,
        "addAccount": "admin",
        "status": "01",
        "totalHours": 120,
        "examType": "03",
        "remarks": "",
        "examFee": 200,
        "isNeedHasPf": "1",
        "institutionInfoId": 0,
        "authenticateGrade": "02",
        "authorityId": "1",
        "learnTypesId": 0,
        "coursePackageId": 0,
        "examTypeName": "理论+实操",
        "statusName": "正常"
    }
    ]

### 三、课程管理--鉴定等级
### 请求路径：/params/cfgParamsGrade
### 请求方式：get/post
### 请求参数：无
### 响应案例：
                [
    {
        "text": "专项职业能力",
        "value": "01"
    },
    {
        "text": "初级/五级",
        "value": "02"
    },
    {
        "text": "中级/四级",
        "value": "03"
    },
    {
        "text": "高级/三级",
        "value": "04"
    },
    {
        "text": "技师/二级",
        "value": "05"
    },
    {
        "text": "高级技师/一级",
        "value": "06"
    }
    ]
    
### 四、课程管理--课程状态
### 请求路径：/params/cfgParamsStatus
### 请求方式：get/post
### 请求参数：无
### 响应案例：
                [
    {
        "text": "上架",
        "value": "01"
    },
    {
        "text": "下架",
        "value": "02"
    }
    ]
    
### 五、课程管理--查询
### 请求路径：/courses/query
### 请求方式：get/post
### 请求参数：
请求参数 | 类型|是否必填|备注
---|---|---|---
pageNumber |int| 否|默认当前第几页
pageSize|int|否|默认分页条数
courseId|int|否|课程名称对应的ID
authenticateGrade|string|否|鉴定等级对应的value
status|string|否|课程状态对应value
### 响应案例：
                {
    "page": 1,
    "rowNum": 10,
    "records": 1,
    "total": 1,
    "rows": [
        {
            "id": 13,
            "typeName": "育婴员",
            "isUsable": "1",
            "addTime": 1502246343000,
            "addAccount": "admin",
            "status": "上架",
            "totalHours": 120,
            "examType": "03",
            "remarks": "",
            "examFee": 200,
            "isNeedHasPf": "1",
            "institutionInfoId": 0,
            "authenticateGrade": "初级/五级",
            "authorityId": "1",
            "learnTypesId": 0,
            "coursePackageId": 0,
            "examTypeName": "理论+实操",
            "statusName": ""
            }
        ]
    }
    
### 六、课程管理--课程新增页面 
### 请求路径：/courses/form
### 请求方式：get/post
### 请求参数：无
### 响应案例：将以下对象返回给modules/courses/form页面。对象信息具体如下
# authenticateGrade(鉴定等级)：

                [
    {
        "id": "01",
        "value": "专项职业能力"
    },
    {
        "id": "02",
        "value": "初级/五级"
    },
    {
        "id": "03",
        "value": "中级/四级"
    },
    ]
# examType(考试形式)：
                [
    {
        "id": "01",
        "value": "理论"
    },
    {
        "id": "02",
        "value": "实操"
    },
    {
        "id": "03",
        "value": "理论+实操"
    },
    {
        "id": "04",
        "value": "其他"
    }
    ]
# timeList(上课时间)

                [
    {
        "id": "4",
        "value": "周末班"
    }
    ]   

# timeSlot

                [
    {
        "id": "08:00",
        "value": "08:00"
    },
    {
        "id": "08:30",
        "value": "08:30"
    }
    ]
# certList(发证机构)

                [
    {
        "id": "1",
        "value": "上海市人力资源和社会保障局"
    },
    {
        "id": "2",
        "value": "上海家策商学院"
    }
    ]
# courses(课程名称)
               [
    {
        "id": 1,
        "typeName": "母婴护理",
        "learnTypesId": 0,
        "coursePackageId": 0,
        "examTypeName": "",
        "statusName": ""
    },
    {
        "id": 2,
        "typeName": "家政服务",
        "learnTypesId": 0,
        "coursePackageId": 0,
        "examTypeName": "",
        "statusName": ""
    }
    ]
# isNeedHasPf(是否上架)
                [
                    {
                    text: "是",
                    value: "1"
                    },
                    {
                    text: "否",
                    value: "0"
                    }
    ]

### 七、课程管理--获取课程对应的证书名称    
### 请求路径：/courses/certName
### 请求方式：get/post
### 请求参数：
请求参数 | 类型|是否必填|备注
---|---|---|---
courseId | int|是|课程ID
### 响应案例：
               母婴护理
               
### 八、课程管理--新增课程   
### 请求路径：/courses/add
### 请求方式：get/post
### 请求参数：
请求参数 | 类型|是否必填|备注
---|---|---|---
typeName | String|是|课程名称
certName|String|是|证书名称
authenticateGrade|String|是 |鉴定等级的value
examType|String|是|考试形式value
authorityId|String |是|发证机构ID
totalHours|int|是|总课时
remarks|String|是|备注
timeTemplate|String|是|上课时间的value，多条记录时用逗号【，】分割
isNeedHasPf|String|是|交社保要求 0-否，1-是
status|String|是|课程状态 01-上架 02-下架
examFee|double|否|鉴考费
certificateFee|double|否|证书费
otherFee|double|否|其他费用
### 响应案例：
                {
    "success": true,
    "msg": "操作成功"
    }

### 九、课程管理--修改课程页面   
### 请求路径：/courses/form
### 请求方式：get/post
### 请求参数：
请求参数 | 类型|是否必填|备注
---|---|---|---
id | int|是|课程ID
### 响应案例：将一下对象返回给modules/courses/updateForm页面。对象信息具体如下
# update(更新标记):true
# model(课程信息对象):
                {
    "id": 1,
    "typeName": "母婴护理",
    "isUsable": "1",
    "addTime": 1479351897000,
    "addAccount": "admin",
    "modifyTime": 1507140113000,
    "modifyAccount": "admin",
    "status": "01",
    "totalHours": 50,
    "examType": "03",
    "remarks": "从事为孕产妇在分娩前后及新生儿、婴儿提供生活护理服务",
    "examFee": 130,
    "certificateFee": 0,
    "otherFee": 0,
    "isNeedHasPf": "1",
    "institutionInfoId": 1,
    "authenticateGrade": "专项职业能力",
    "authorityId": 1,
    "certName": "母婴护理",
    "courseBaseInfoId": 1,
    "learnTypesId": 0,
    "coursePackageId": 0,
    "examTypeName": "理论+实操",
    "statusName": "正常",
    "authorityName": "上海市人力资源和社会保障局"
    }
# courseTimeTableList
                [
    {
        "id": 1,
        "templateName": "周日班",
        "institutionInfoId": 1
    },
    {
        "id": 2,
        "templateName": "连续班",
        "institutionInfoId": 1
    }
    ]

### 十、课程管理--修改课程
### 请求路径：/courses/modify
### 请求方式：get/post
### 请求参数：
请求参数 | 类型|是否必填|备注
---|---|---|---
id|int|是|课程ID
remarks|String|是|备注
timeTemplate|String|是|上课时间的value，多条记录时用逗号【，】分割
isNeedHasPf|String|是|交社保要求 0-否，1-是
status|String|是|课程状态 01-上架 02-下架
examFee|double|否|鉴考费
certificateFee|double|否|证书费
otherFee|double|否|其他费用
### 响应案例：
                {
    "success": true,
    "msg": "操作成功"
    }

### 十一、运营管理--学校简介页面
### 请求路径：/intro/getSchoolIntroduce
### 请求方式：get/post
### 请求参数：无
### 响应案例：将institution对象返回给modules/intro/schoolIntroduce页面，对象信息如下.页面增加一个联系人的联系方式。
# institution
                {
    "id": 1,
    "schoolName": "家策商学院",
    "privince": "310000",
    "city": "310100",
    "area": "310104",
    "schoolAddress": "南丹东路300号9幢亚都商务楼1201室",
    "schoolPhone": "021-64183797",
    "contacts": "刘驰明",
    "contactPhone": "13524301787",
    "companyName": "上海家策教育科技有限公司",
    "licenceNo": "91310109MA1G54MG27",
    "licenceImg": "http://image-jiacer.oss-cn-shanghai.aliyuncs.com/2017080414352119822.jpg",
    "agentName": "刘驰明",
    "agentIdNumber": "310227198709290018",
    "idcardFrontImg": "http://image-jiacer.oss-cn-shanghai.aliyuncs.com/2017080414363202223.jpg",
    "idcardBackImg": "http://image-jiacer.oss-cn-shanghai.aliyuncs.com/2017080414363662024.jpg",
    "saler": "",
    "appltTime": 1501766662000,
    "isUsable": "1",
    "addTime": 1501766662000,
    "addAccount": "admin",
    "modifyTime": 1501830004000,
    "modifyAccount": "admin"
    }
                
### 十二、运营管理--学校简介修改
### 请求路径：/intro/updateInstitution
### 请求方式：get/postt
### 请求参数：
请求参数 | 类型|是否必填|备注
---|---|---|---
id|int|是|学校简介ID
schoolAddress|String|是|学校地址
schoolPhone|String|是|学校联系方式
contacts|String|是|联系人
logoUrl|String|是|LOGO地址
### 响应案例：
                {
    "success": true,
    "msg": "操作成功"
    }
	