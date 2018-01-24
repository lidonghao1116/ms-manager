package com.jiacer.modules.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiacer.modules.business.bean.operation.CoursePackageOpt;
import com.jiacer.modules.business.service.CoursePackageService;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.common.service.ServiceException;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.CoursePackageMapper;
import com.jiacer.modules.mybatis.dao.CoursesBaseInfoMapper;
import com.jiacer.modules.mybatis.dao.LearnTypesMapper;
import com.jiacer.modules.mybatis.entity.ControlEntity;
import com.jiacer.modules.mybatis.entity.CourseBaseInfoEntity;
import com.jiacer.modules.mybatis.entity.CoursePackageEntity;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.mybatis.entity.SysUsersEntity;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.utils.UserUtils;

/**
 * @author 贺章鹏
 * @ClassName: CoursePackageServiceImpl
 * @Description: TODO
 * @date 2016年10月19日 下午4:18:58
 */
@Service
public class CoursePackageServiceImpl extends BaseService implements CoursePackageService {

    @Autowired
    CoursePackageMapper coursePackageDao;

    @Resource
    LearnTypesMapper coursesDao;

    @Autowired
    CoursesBaseInfoMapper coursesBaseInfoMapper;

    @Override
    public CoursePackageEntity getCoursePackageById(Integer id) {
        try {
            return coursePackageDao.getById(id);
        } catch (Exception e) {
            Log.error(Message.buildErrInfo(Message.QUERY_ERROR_EXCEPTION, e));
        }
        return null;
    }

    @Override
    public Page<CoursePackageEntity> getCoursePackagePage(CoursePackageEntity coursePackageEntity, int pageNumber,
                                                          int pageSize) {
        try {
            //获取总条数
            Map<Object, Object> map = CoursePackageOpt.buildMap(coursePackageEntity);
            SysUsersEntity sysUsersEntity = UserUtils.getUser();
            map.put("institutionInfoId", UserUtils.getUser().getInstitutionInfoId());
            Integer totalCount = coursePackageDao.count(map);
            //分页实体
            Page<CoursePackageEntity> page = new Page<CoursePackageEntity>();
            page.setPage(pageNumber);
            page.setRowNum(pageSize);
            if (totalCount == null) {
                return page;
            }
            //最大页数判断
            int pageM = maxPage(totalCount, page.getRowNum(), page.getPage());
            if (pageM > 0) {
                page.setPage(pageM);
            }
            if (totalCount > 0) {
                map.put("offset", page.getOffset());
                map.put("pageSize", page.getRowNum());
                List<CoursePackageEntity> list = coursePackageDao.getPageList(map);
                page.setRows(list);
                page.setRecords(totalCount.longValue());
            }
            return page;
        } catch (Exception e) {
            e.printStackTrace();
            Log.error(String.format(Message.QUERY_ERROR_EXCEPTION, e));
            return null;
        }
    }

    @Override
    public void addCoursePackage(CoursePackageEntity coursePackageEntity) throws Exception {
        coursePackageDao.insert(CoursePackageOpt.buildAdd(coursePackageEntity));
    }

    @Override
    public void modifyCoursePackage(CoursePackageEntity coursePackageEntity) throws Exception {
        CoursePackageEntity bean = this.getCoursePackageById(coursePackageEntity.getId());
        if (bean == null) {
            throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
        } else {
            coursePackageDao.update(CoursePackageOpt.buildUpdate(bean, coursePackageEntity));
        }
    }

    @Override
    public void delCoursePackage(CoursePackageEntity coursePackageEntity) throws Exception {
        CoursePackageEntity bean = this.getCoursePackageById(coursePackageEntity.getId());
        if (bean == null) {
            throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
        } else {
            coursePackageDao.update(CoursePackageOpt.buildDelete(bean));
        }
    }

    @Override
    public List<ControlEntity> getCoursePackageIdName() {
        List<ControlEntity> cpList = new ArrayList<ControlEntity>();
        CoursePackageEntity coursePackage = new CoursePackageEntity();
        coursePackage.setInstitutionInfoId(UserUtils.getUser().getInstitutionInfoId());
        List<CoursePackageEntity> list = coursePackageDao.findAllList(coursePackage);

        for (int i = 0; i < list.size(); i++) {
            CoursePackageEntity coursePackageEntity = list.get(i);
            cpList.add(new ControlEntity(coursePackageEntity.getId().toString(), coursePackageEntity.getPackageName()));
        }
        return cpList;
    }

    @Override
    public Integer count(Map<Object, Object> map) {
        // TODO Auto-generated method stub
        return coursePackageDao.count(map);
    }

    @Override
    public Integer getPackageNameCount(Map<Object, Object> map) {
        // TODO Auto-generated method stub
        return coursePackageDao.getPackageNameCount(map);
    }

    @Override
    public CoursePackageEntity getById(Integer id) {
        // TODO Auto-generated method stub
        return coursePackageDao.getById(id);
    }

    @Override
    public List<CoursePackageEntity> getPayCoursePackage(Map<Object, Object> map) {
        // TODO Auto-generated method stub
        return coursePackageDao.getPayCoursePackage(map);
    }

}
