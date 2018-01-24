package com.jiacer.modules.business.service.impl;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.jiacer.modules.business.bean.form.LearnRecordsQuery;
import com.jiacer.modules.business.bean.operation.LearnRecordsOpt;
import com.jiacer.modules.business.service.LearnRecordsService;
import com.jiacer.modules.common.page.Page;
import com.jiacer.modules.common.service.BaseService;
import com.jiacer.modules.common.utils.DateUtils;
import com.jiacer.modules.common.utils.ExcelOutput;
import com.jiacer.modules.log.Log;
import com.jiacer.modules.mybatis.dao.UserAnswersBatchMapper;
import com.jiacer.modules.mybatis.dao.UserBaseInfoMapper;
import com.jiacer.modules.mybatis.dao.UserExtendInfoMapper;
import com.jiacer.modules.mybatis.entity.UserBaseInfoEntity;
import com.jiacer.modules.mybatis.entity.UserExtendInfoEntity;
import com.jiacer.modules.mybatis.entity.extend.LearnRecordEntity;
import com.jiacer.modules.system.config.Message;

/**
 * @ClassName: LearnRecordsServiceImpl
 * @Description: TODO
 * @author 贺章鹏
 * @date 2016年11月7日 下午3:28:11
 * 
 */
@Service
public class LearnRecordsServiceImpl extends BaseService implements LearnRecordsService {

	@Autowired
	UserAnswersBatchMapper userAnswersBatchDao;

	@Autowired
	UserExtendInfoMapper userExtendDao;

	@Autowired
	UserBaseInfoMapper userBaseDao;

	@Override
	public Page<LearnRecordEntity> getPage(LearnRecordsQuery learnRecordsQuery, int pageNumber, int pageSize) {
		try {
			// 获取总条数
			Map<Object, Object> map = LearnRecordsOpt.buildMap(learnRecordsQuery);
			Integer totalCount = userAnswersBatchDao.learnCount(map);
			// 分页实体
			Page<LearnRecordEntity> page = new Page<LearnRecordEntity>();
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
				List<LearnRecordEntity> list = userAnswersBatchDao.getLearnPageList(map);
				for (LearnRecordEntity learnRecordEntity : list) {
					UserBaseInfoEntity userBaseInfoEntity = userBaseDao.getById(learnRecordEntity.getUserId());
					UserExtendInfoEntity extendInfoEntity = userExtendDao.getById(learnRecordEntity.getUserId());
					if (userBaseInfoEntity != null) {
						learnRecordEntity.setUserInfo(userBaseInfoEntity);
					}
					if (extendInfoEntity != null) {
						learnRecordEntity.setUserExtend(extendInfoEntity);
					}
				}
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
	public Model dealExport(Model model, LearnRecordsQuery learnRecordsQuery, HttpServletResponse response,
			HttpServletRequest request) {

		try {
			Map<Object, Object> map = LearnRecordsOpt.buildMap(learnRecordsQuery);
			List<LearnRecordEntity> list = userAnswersBatchDao.getLearnPageList(map);
			if (list == null || list.size() < 1) {
				model.addAttribute("message", "没有数据可以导出");
			}

			String fileName = "学习记录表" + DateUtils.getDate("yyyy-MM-dd") + ".xls";
			response.reset();
			response.addHeader("Content-Disposition",
					"filename=" + new String(fileName.getBytes("gb2312"), "iso8859-1"));
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			OutputStream out = new BufferedOutputStream(response.getOutputStream());
			System.out.println("==================================" + out);
			ExcelOutput e = new ExcelOutput(out);
			int i = 0;
			e.createRow(0);// 表头
			e.setCell(0, "姓名");
			e.setCell(1, "联系方式");
			e.setCell(2, "练习次数");
			e.setCell(3, "最高成绩");
			UserBaseInfoEntity userBaseInfoEntity = null;
			UserExtendInfoEntity extendInfoEntity = null;
			for (LearnRecordEntity records : list) {
				userBaseInfoEntity = new UserBaseInfoEntity();
				extendInfoEntity = new UserExtendInfoEntity();
				userBaseInfoEntity = userBaseDao.getById(records.getUserId());
				extendInfoEntity = userExtendDao.getById(records.getUserId());
				e.createRow(i + 1);// 创建行数
				if (userBaseInfoEntity != null) {
					e.setCell(0, extendInfoEntity.getUserName());
				}
				if (extendInfoEntity != null) {
					e.setCell(1, userBaseInfoEntity.getMobile());
				}
				e.setCell(2, records.getAnswersNum());
				if (records.getScores() != null && !"".equals(records.getScores())) {

					e.setCell(3, records.getScores().doubleValue());
				} else {
					e.setCell(3, "");
				}
				i++;
			}

			e.export();
		} catch (IOException ex) {
			logger.error("写入excel出错:" + ex);
			model.addAttribute("message", "导出失败，程序异常");
		} catch (Exception e) {
			model.addAttribute("message", "导出失败，程序异常");
		}
		return model;
	}

}
