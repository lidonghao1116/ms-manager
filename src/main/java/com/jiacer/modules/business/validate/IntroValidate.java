package com.jiacer.modules.business.validate;

import com.jiacer.modules.business.bean.form.InstitutionQuery;
import com.jiacer.modules.business.service.CoursePackageService;
import com.jiacer.modules.common.utils.JsonResult;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.dao.LearnTypesMapper;
import com.jiacer.modules.mybatis.entity.CoursePackageEntity;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class IntroValidate {
    /**
     * @MethodName:UpdateInstitionValidate
     * @Type:CoursePackageValidate
     * @Description:修改学校简介参数验证
     * @Return:JsonResult
     * @Param:@param institutionQuery
     * @Param:@return
     * @Thrown:
     * @Date:Oct 9, 2017 2:33:45 PM
     */
    public static JsonResult UpdateInstitionValidate(InstitutionQuery institutionQuery) {
        if (institutionQuery.getId() == null || institutionQuery.getId() < 0) {
            return new JsonResult(false, "学校简介Id为空", null);
        }
        if (StringUtils.isEmpty(institutionQuery.getSchoolPhone())) {
            return new JsonResult(false, "学校联系电话为空", null);
        }
        if (StringUtils.isEmpty(institutionQuery.getContacts())) {
            return new JsonResult(false, "联系人为空", null);
        }
        if (StringUtils.isEmpty(institutionQuery.getSchoolAddress())) {
            return new JsonResult(false, "学校地址为空", null);
        }
        if (StringUtils.isEmpty(institutionQuery.getPrivince())) {
            return new JsonResult(false, "学校地址省为空", null);
        }
        if (StringUtils.isEmpty(institutionQuery.getCity())) {
            return new JsonResult(false, "学校地址市为空", null);
        }
        if (StringUtils.isEmpty(institutionQuery.getArea())) {
            return new JsonResult(false, "学校地址县为空", null);
        }
        return new JsonResult(true, null, null);
    }

}
