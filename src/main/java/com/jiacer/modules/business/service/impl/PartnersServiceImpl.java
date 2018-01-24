package com.jiacer.modules.business.service.impl;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.jiacer.modules.business.bean.HandleStatusType;
import com.jiacer.modules.business.bean.operation.ApplyOrdersOpt;
import com.jiacer.modules.business.bean.operation.PartnersOpt;
import com.jiacer.modules.business.service.PartnersService;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.common.service.ServiceException;
import com.jiacer.modules.common.utils.DateUtils;
import com.jiacer.modules.common.utils.ExcelOutput;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.PartnersMapper;
import com.jiacer.modules.mybatis.dao.UserBaseInfoMapper;
import com.jiacer.modules.mybatis.dao.UserExtendInfoMapper;
import com.jiacer.modules.mybatis.entity.ApplyOrdersEntity;
import com.jiacer.modules.mybatis.entity.LearnTypesEntity;
import com.jiacer.modules.mybatis.entity.PartnersEntity;
import com.jiacer.modules.mybatis.entity.UserExtendInfoEntity;
import com.jiacer.modules.system.config.Message;
import com.jiacer.modules.system.utils.UserUtils;

/**
 * @author 贺章鹏
 * @ClassName: PartnersServiceImpl
 * @Description: TODO
 * @date 2016年10月19日 下午4:19:49
 */
@Service
public class PartnersServiceImpl extends BaseService implements PartnersService {

    @Autowired
    PartnersMapper partnersDao;

    @Autowired
    UserBaseInfoMapper userBaseInfoDao;

    @Autowired
    UserExtendInfoMapper userExtendInfoDao;

    @Override
    public PartnersEntity getPartnersById(Integer id) {
        try {
            //通过id获取合作商
            PartnersEntity part = partnersDao.getById(id);
            if (part == null) {
                return new PartnersEntity();
            }
            /**
             List<PartnersEntity> learn=new ArrayList<PartnersEntity>();
             learn=partnersDao.getBySoucerValueId(part.getId());
             if(learn !=null){
             part.setLeatnList(learn);
             }
             **/
            return part;
        } catch (Exception e) {
            Log.error(Message.buildErrInfo(Message.QUERY_ERROR_EXCEPTION, e));
        }
        return null;
    }

    @Override
    public Page<PartnersEntity> getPartnersPage(PartnersEntity partnersEntity, int pageNumber, int pageSize) {
        try {
            // 获取总条数
            Map<Object, Object> map = PartnersOpt.buildMap(partnersEntity);
            map.put("institutionInfoId", UserUtils.getUser().getInstitutionInfoId());

            Integer totalCount = partnersDao.count(map);
            // 分页实体
            Page<PartnersEntity> page = new Page<PartnersEntity>();
            page.setPage(pageNumber);
            page.setRowNum(pageSize);
            if (totalCount == null) {
                return page;
            }
            // 最大页数判断
            int pageM = maxPage(totalCount, page.getRowNum(), page.getPage());
            if (pageM > 0) {
                page.setPage(pageM);
            }
            if (totalCount > 0) {
                map.put("offset", page.getOffset());
                map.put("pageSize", page.getRowNum());

                List<PartnersEntity> newList = new ArrayList<PartnersEntity>();
                List<PartnersEntity> oldList = partnersDao.getPageList(map);
                for (int i = 0; i < oldList.size(); i++) {
                    PartnersEntity partners = oldList.get(i);
                    map.put("id", partners.getId());
                    partners.setCount(partnersDao.getProCount(map));
                    newList.add(partners);
                }
                page.setRows(newList);
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
    public void addPartners(PartnersEntity partnersEntity) throws Exception {
        partnersDao.insert(PartnersOpt.buildAdd(partnersEntity));
    }

    @Override
    public void modifyPartners(PartnersEntity partnersEntity) throws Exception {
        PartnersEntity bean = this.getPartnersById(partnersEntity.getId());
        if (bean == null) {
            throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
        } else {
            partnersDao.update(PartnersOpt.buildUpdate(bean, partnersEntity));
        }
    }

    @Override
    public void delPartner(PartnersEntity partnersEntity) throws Exception {
        PartnersEntity bean = this.getPartnersById(partnersEntity.getId());
        if (bean == null) {
            throw new ServiceException(Message.UPDATE_DATE_NOEXIST);
        } else {
            partnersDao.update(PartnersOpt.buildDelete(bean));
        }
    }

    @Override
    public Model dealExport(Model model, PartnersEntity partnersEntity, HttpServletResponse response, HttpServletRequest request) {
        try {
            Map<Object, Object> map = PartnersOpt.buildMap(partnersEntity);
            map.put("institutionInfoId", UserUtils.getUser().getInstitutionInfoId());
            map.put("isUsable", "1");
            List<PartnersEntity> part = partnersDao.findAllList(map);

            if (part == null || part.size() < 1) {
                model.addAttribute("message", "没有数据可以导出");
            }

            String fileName = "合作商列表" + DateUtils.getDate("yyyy-MM-dd") + ".xls";
            response.reset();
            response.addHeader("Content-Disposition", "filename="
                    + new String(fileName.getBytes("gb2312"), "iso8859-1"));
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            OutputStream out = new BufferedOutputStream(response.getOutputStream());
            String filePath = "./WEB-INF/excelTemp/partner_temp";
            ExcelOutput e = new ExcelOutput(out, filePath, request);
            int i = 1;
            Map<Object,Object> pm = new HashMap<Object,Object>();
            for (PartnersEntity temp : part) {
                e.createRow(i++);
                e.setCell(0, temp.getPartnerName());
                e.setCell(1, temp.getProviceName() + temp.getCityName() + temp.getCountyName());
                e.setCell(2, temp.getContacts());
                e.setCell(3, temp.getContactPhone());
                e.setCell(4, temp.getSalesman());
                pm.put("institutionInfoId", UserUtils.getUser().getInstitutionInfoId());
                pm.put("id", temp.getId());
                e.setCell(5, partnersDao.getProCount(pm));
            }
            e.export();
        } catch (IOException ex) {
            logger.error("写入excel出错:" + ex);
            ex.printStackTrace();
            model.addAttribute("message", "导出失败，程序异常");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "导出失败，程序异常");
        }
        return model;

    }

    @Override
    public PartnersEntity getByLearnName(Integer id) {
        try {
            return partnersDao.getByLearnName(id);
        } catch (Exception e) {
            Log.error(Message.buildErrInfo(Message.QUERY_ERROR_EXCEPTION, e));
        }
        return null;
    }

    @Override
    public List<PartnersEntity> getBySoucerValueId(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer count(Map<Object, Object> map) {
        // TODO Auto-generated method stub
        return partnersDao.count(map);
    }

    @Override
    public PartnersEntity getById(Integer id) {
        // TODO Auto-generated method stub
        return partnersDao.getById(id);
    }


//	@Override
//	public List<String> getBySoucerValueId(Map<String, Object> map) {
//		try {
//			List<PartnersEntity> PartnersEntity = new ArrayList<PartnersEntity>();
//			PartnersEntity = partnersDao.getBySoucerValueId(map);
//			System.out.println(PartnersEntity);
//			//return part;
//			//return PartnersEntity;
//		} catch (Exception e) {
//			Log.error(Message.buildErrInfo(Message.QUERY_ERROR_EXCEPTION, e));
//		}
//		return null;
//	}


}
